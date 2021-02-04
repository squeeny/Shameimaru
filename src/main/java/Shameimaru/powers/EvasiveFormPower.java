package Shameimaru.powers;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class EvasiveFormPower extends AbstractPower {

    public static final String POWER_ID = makeID(EvasiveFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public EvasiveFormPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = false;
        loadRegion("flameBarrier");
        updateDescription();
    }
    public void onCardDraw(AbstractCard card) { atb(new dynamicGainEvasiveAction(amount)); }
    @Override
    public void updateDescription() { this.description = String.format(DESCRIPTIONS[0], this.amount);  }
}