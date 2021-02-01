package Shameimaru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

import static Shameimaru.util.actionShortcuts.p;

public class DiscardAnyAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private AbstractPlayer p;
    public static int numDiscarded;

    public DiscardAnyAction(int amount) {
        this.p = p();
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.duration = DURATION;
    }

    public void update() {

        if (this.duration == DURATION) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (this.amount > 0) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true);
                p().hand.applyPowers();
                tickDuration();
                return;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}