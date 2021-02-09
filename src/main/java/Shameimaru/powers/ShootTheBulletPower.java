package Shameimaru.powers;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.sp.photograph.Photograph;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class ShootTheBulletPower extends AbstractPower {

    public static final String POWER_ID = makeID(ShootTheBulletPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private Photograph storedCopy;
    private static int IDOffset;
    private static int AMOUNT = 1;
    public ShootTheBulletPower(AbstractCreature owner, AbstractMonster source, boolean snapShotPlus) {
        name = NAME;
        ID = POWER_ID + IDOffset;
        IDOffset++;
        this.owner = owner;
        this.amount = AMOUNT;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        atb(new createPhotographAction(source, snapShotPlus, this));
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
            flash();
            amount = -1;
        }
        return damageAmount;
    }

   @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        if(this.amount != -1){ storedCopy.doubleAllValues(); }
        atb(new MakeTempCardInHandAction(storedCopy));
        atb(new RemoveSpecificPowerAction(p(), p(), this));
    }

    @Override
    public void updateDescription() { this.description = this.amount == -1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1]; }

    public void setCard(Photograph p){ storedCopy = p; }
}