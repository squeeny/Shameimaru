package Shameimaru.powers;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.sp.photograph.core.attackCard;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class GatherEvidencePower extends AbstractPower {

    public static final String POWER_ID = makeID(GatherEvidencePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int INITIAL_AMOUNT = 1;

    public GatherEvidencePower() {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = INITIAL_AMOUNT;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            for (int i = 1; i <= amount; i += 1) { atb(new createPhotographAction((AbstractMonster) info.owner)); }
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() { this.description = this.amount == 1 ? String.format(DESCRIPTIONS[0], this.amount) : String.format(DESCRIPTIONS[1], this.amount); }
}