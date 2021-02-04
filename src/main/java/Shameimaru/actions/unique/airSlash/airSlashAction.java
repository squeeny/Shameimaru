package Shameimaru.actions.unique.airSlash;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Shameimaru.util.actionShortcuts.p;

public class airSlashAction extends AbstractGameAction {

    private int goodEnergy;
    private int badEnergy;
    public airSlashAction(int goodEnergy, int badEnergy) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.badEnergy = badEnergy;
        this.goodEnergy = goodEnergy;
    }

    public void update() {
        if (GameActionManager.totalDiscardedThisTurn > 0) {
            p().gainEnergy(goodEnergy);
            AbstractDungeon.actionManager.updateEnergyGain(goodEnergy);
            for (AbstractCard c : AbstractDungeon.player.hand.group) { c.triggerOnGainEnergy(goodEnergy, true); }
        }
        else {
            p().gainEnergy(badEnergy);
            AbstractDungeon.actionManager.updateEnergyGain(badEnergy);
            for (AbstractCard c : AbstractDungeon.player.hand.group) { c.triggerOnGainEnergy(badEnergy, true); }
        }
        this.isDone = true;
    }
}