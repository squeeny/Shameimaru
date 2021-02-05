package Shameimaru.powers;

import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.cards.sp.photograph.core.attackCard;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class OverclockedCameraPower extends AbstractPower {

    public static final String POWER_ID = makeID(OverclockedCameraPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int INITIAL_AMOUNT = 1;

    public OverclockedCameraPower() {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = INITIAL_AMOUNT;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public void atEndOfRound() {
        flash();
        if (this.amount == 0) { atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else { atb(new ReducePowerAction(this.owner, this.owner, this, 1)); }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard c) {
        if (c instanceof attackCard) { damage = damage * 2f; }
        return damage;
    }

    @Override
    public void updateDescription() { this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount); }
}