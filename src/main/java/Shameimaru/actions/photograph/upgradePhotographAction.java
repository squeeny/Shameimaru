package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.photograph.Photograph;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static Shameimaru.util.actionShortcuts.p;

public class upgradePhotographAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private int upgTimes;
    public upgradePhotographAction(int amount, int timesToUpgrade) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        upgTimes = timesToUpgrade;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard c: p().hand.group) {
                if (c instanceof Photograph) { tmp.addToTop(c); }
            }
            if (tmp.size() < amount) {
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
            tickDuration();
        }

        else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                for(int i = 0; i < upgTimes; i+= 1){ card.upgrade(); }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}
