package Shameimaru.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class PolaroidPower extends AbstractPower {

    public static final String POWER_ID = makeID(PolaroidPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public PolaroidPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            if(!p().hasPower(EvasivePower.POWER_ID)){ doPow(p(), new EvasivePower(amount), true); }
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount); }
}