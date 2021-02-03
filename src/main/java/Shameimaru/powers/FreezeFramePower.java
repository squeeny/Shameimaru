package Shameimaru.powers;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.unc.FreezeFrame;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class FreezeFramePower extends AbstractPower {

    public static final String POWER_ID = makeID(FreezeFramePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int INITIAL_AMOUNT = 1;
    private EnemyMoveInfo info;

    public FreezeFramePower(AbstractMonster target) {
        name = NAME;
        ID = POWER_ID;
        this.owner = target;
        this.amount = INITIAL_AMOUNT;
        type = PowerType.BUFF;
        isTurnBased = false;
        info = ReflectionHacks.getPrivate(this.owner, AbstractMonster.class, "move");;
        loadRegion("flameBarrier");
        updateDescription();
    }

    public void atEndOfRound() {
        if(owner instanceof AbstractMonster){
            ((AbstractMonster) owner).setMove(info.nextMove, info.intent, info.baseDamage, info.multiplier, info.isMultiDamage);
            ((AbstractMonster) owner).createIntent();
        }
        if (this.amount == 0) { addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else { addToBot(new ReducePowerAction(this.owner, this.owner, this, 1)); }
    }

    @Override
    public void updateDescription() { this.description = this.amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], this.amount); }
}