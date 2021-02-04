package Shameimaru.actions.unique.outburstingGust;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Shameimaru.util.actionShortcuts.doDraw;
import static Shameimaru.util.actionShortcuts.p;

public class outburstingGustAction extends AbstractGameAction {

    private int ENERGY;
    private int DRAW;
    public outburstingGustAction(int E, int D) {
        this.duration = Settings.ACTION_DUR_FAST;
        ENERGY = E;
        DRAW = D;
    }

    public void update() {
        if (GameActionManager.totalDiscardedThisTurn > 0) {
            p().gainEnergy(ENERGY);
            AbstractDungeon.actionManager.updateEnergyGain(ENERGY);
            for (AbstractCard c : AbstractDungeon.player.hand.group) { c.triggerOnGainEnergy(ENERGY, true); }
            doDraw(DRAW, true);
        }
        this.isDone = true;
    }
}