package Shameimaru.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;


public class EvasionPower extends AbstractPower {
    public static final String POWER_ID = makeID(EvasionPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int EVASIVE_CAP = 25;
    private int EVASION = 1;
    public EvasionPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.loadRegion("focus");
        updateDescription();
    }
    @Override
    public void updateDescription() { description = DESCRIPTIONS[0]; }
}