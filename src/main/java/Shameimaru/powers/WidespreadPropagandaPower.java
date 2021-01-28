package Shameimaru.powers;

import Shameimaru.cards.unc.WidespreadPropaganda;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;


public class WidespreadPropagandaPower extends AbstractPower
{
    public static final String POWER_ID = makeID(WidespreadPropagandaPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public WidespreadPropagandaPower(AbstractCreature owner) { this(owner, -1); }
    public WidespreadPropagandaPower(AbstractCreature owner, int amount)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        updateDescription();
        loadRegion("flameBarrier");
        canGoNegative = false;
    }
    @Override
    public void updateDescription() { this.description = DESCRIPTIONS[0]; }
}