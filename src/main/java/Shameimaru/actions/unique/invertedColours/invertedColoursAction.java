package Shameimaru.actions.unique.invertedColours;

import Shameimaru.cards.sp.photograph.Photograph;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.*;

public class invertedColoursAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
    public static final String[] TEXT = uiStrings.TEXT;
    public invertedColoursAction(int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
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
            AbstractDungeon.gridSelectScreen.open(tmp, this.amount, "", false);
            tickDuration();
        }

        else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                if(card instanceof Photograph){
                    ArrayList<AbstractCard> dummies = ReflectionHacks.getPrivate(card, Photograph.class, "card");
                    for(AbstractCard c: dummies){
                        if(c.baseBlock >= 0){
                            AbstractMonster m = getRandomAliveMonster(AbstractDungeon.getMonsters(), AbstractDungeon.cardRng);
                            doDmg(m, c.block, DamageInfo.DamageType.NORMAL, AttackEffect.NONE, true);
                        }
                        if(c.baseDamage >= 0){ doDef(c.damage * c.magicNumber, true); }
                    }
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}