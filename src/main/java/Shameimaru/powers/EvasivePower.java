package Shameimaru.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class EvasivePower extends AbstractPower {
    public static final String POWER_ID = makeID(EvasivePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EvasivePower(int amount) {
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
    public void updateDescription() { description = amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], amount); }

    public int onAttacked(DamageInfo info, int damageAmount) { return 0; }
    
    public void atEndOfRound() {
        flash();
        if (this.amount == 0) { atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else { atb(new ReducePowerAction(this.owner, this.owner, this, 1)); }
    }
}