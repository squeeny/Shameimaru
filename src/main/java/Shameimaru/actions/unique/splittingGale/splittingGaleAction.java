package Shameimaru.actions.unique.splittingGale;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static Shameimaru.util.actionShortcuts.p;


public class splittingGaleAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float DURATION = Settings.ACTION_DUR_XFAST;
    private AbstractPlayer p;
    private int DECREASE = 2;
    private AbstractCard pair;
    public splittingGaleAction(AbstractCard c, int amount) {
        this.p = p();
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.duration = DURATION;
        pair = c;
    }

    public void update() {
        if (this.duration == DURATION) {
            if (this.p.hand.size() == 0) {
                pair.baseDamage -= DECREASE;
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
            if(AbstractDungeon.handCardSelectScreen.selectedCards.group.size() == 0){ pair.baseDamage -= DECREASE; }
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}