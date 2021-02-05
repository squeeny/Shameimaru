package Shameimaru.actions.unique.bunbunmaruNewspaper;

import Shameimaru.cards.rar.BunbunmaruNewspaper;
import Shameimaru.cards.sp.photograph.Photograph;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.att;
import static Shameimaru.util.actionShortcuts.p;

public class bunbunmaruNewspaperAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private int CARDS = 3;
    private BunbunmaruNewspaper newspaper;
    public bunbunmaruNewspaperAction(BunbunmaruNewspaper pairedCard) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = CARDS;
        newspaper = pairedCard;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            boolean photoValidity = false;
            for(AbstractCard c: p().hand.group) {
                if(c instanceof Photograph){ photoValidity = true; }
                tmp.addToTop(c);
            }
            if (tmp.size() < amount) {
                this.isDone = true;
                return;
            }
            if(!photoValidity){
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
            tickDuration();
        }

        else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            boolean dualPhotoValidityCheck = false;
            ArrayList<AbstractCard>dummyCards = new ArrayList<AbstractCard>();
            for(AbstractCard c: AbstractDungeon.gridSelectScreen.selectedCards){
                if(c instanceof Photograph){ dualPhotoValidityCheck = true; }
            }
            if(dualPhotoValidityCheck) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    dummyCards.add(c.makeCopy());
                    p().hand.group.remove(c);
                }
                ReflectionHacks.setPrivate(newspaper, BunbunmaruNewspaper.class, "newspaperCards", dummyCards);
                newspaper.calculateNewTargets();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            this.isDone = true;
        }
    }
}
