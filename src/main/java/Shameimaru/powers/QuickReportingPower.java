package Shameimaru.powers;

import Shameimaru.actions.photograph.createPhotographAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class QuickReportingPower extends AbstractPower {

    public static final String POWER_ID = makeID(QuickReportingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int INITIAL_AMOUNT = 1;
    public QuickReportingPower() {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = INITIAL_AMOUNT;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        for(int i = 0; i < amount; i+=1){
            AbstractMonster m = getRandomAliveMonster(AbstractDungeon.getMonsters(), AbstractDungeon.cardRng);
            atb(new createPhotographAction(m));
        }
    }

    @Override
    public void updateDescription() { this.description = this.amount == 1 ? DESCRIPTIONS[0] : String.format(DESCRIPTIONS[1], this.amount); }
}