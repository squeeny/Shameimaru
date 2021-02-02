package Shameimaru.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class DiscardNextTurnPower extends AbstractPower {

    public static final String POWER_ID = makeID(DiscardNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DiscardNextTurnPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        atb(new DiscardAction(this.owner, this.owner, this.amount, false));
        atb(new RemoveSpecificPowerAction(p(), p(), this));
    }

    @Override
    public void updateDescription() { this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount); }
}