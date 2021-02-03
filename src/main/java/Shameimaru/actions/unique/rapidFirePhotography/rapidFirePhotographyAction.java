package Shameimaru.actions.unique.rapidFirePhotography;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.function.BiFunction;

public class rapidFirePhotographyAction extends AbstractGameAction {
    public BiFunction<Integer, int[], Boolean> xActionUpdate;
    public int[] params;
    protected int baseValue;
    protected boolean freeToPlayOnce;
    protected int effect;
    private boolean firstUpdate = true;

    public rapidFirePhotographyAction(AbstractCard card, BiFunction<Integer, int[], Boolean> xActionUpdate, int... params) {
        this.baseValue = card.energyOnUse;
        this.freeToPlayOnce = card.freeToPlayOnce;
        this.xActionUpdate = xActionUpdate;
        this.params = params;
    }

    @Override
    public void update() {
        if (firstUpdate) {
            effect = EnergyPanel.totalCount;
            if (this.baseValue != -1) { effect = this.baseValue; }
            if (AbstractDungeon.player.hasRelic(ChemicalX.ID)) {
                effect += 2;
                AbstractDungeon.player.getRelic(ChemicalX.ID).flash();
            }
            isDone = xActionUpdate.apply(effect, params) || duration < 0.0f;
            firstUpdate = false;
            if (!this.freeToPlayOnce) { AbstractDungeon.player.energy.use(EnergyPanel.totalCount); }
        } else {
            isDone = xActionUpdate.apply(effect, params) || duration < 0.0f;
        }
    }
}