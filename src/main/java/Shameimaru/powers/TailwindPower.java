package Shameimaru.powers;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class TailwindPower extends AbstractPower {
    public static final String POWER_ID = makeID(TailwindPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public TailwindPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.loadRegion("flameBarrier");
        updateDescription();
    }
    @Override
    public void updateDescription() { description = String.format(DESCRIPTIONS[0], amount); }
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        atb(new dynamicGainEvasiveAction(amount));
    }
}