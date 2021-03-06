package Shameimaru.actions.evasive;

import Shameimaru.cards.com.BlindingSpeed;
import Shameimaru.powers.EvasionPower;
import Shameimaru.powers.EvasivePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.util.actionShortcuts.*;

public class dynamicGainEvasiveAction extends AbstractGameAction {

    private float startingDuration;
    private int EVASIVE_CAP = 25;
    private int EVASIVE = 1;

    public dynamicGainEvasiveAction(int amount) {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.amount = amount;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            AbstractPower x = p().getPower(EvasionPower.POWER_ID);
            if(x != null){
                int evasion = (x.amount + amount) / EVASIVE_CAP;
                int leftoverEvasion = (x.amount + amount) % EVASIVE_CAP;
                if(evasion > 0){
                    doPow(p(), new EvasivePower(evasion), true);
                    if(leftoverEvasion > 0){ doPow(p(), new EvasionPower(leftoverEvasion), true); }
                    att(new ReducePowerAction(p(), p(), EvasionPower.POWER_ID, EVASIVE_CAP));
                    for(AbstractCard c: p().hand.group){ if(c instanceof BlindingSpeed){ ((BlindingSpeed) c).onEvasive(); } }
                    for(AbstractCard c: p().drawPile.group){ if(c instanceof BlindingSpeed){ ((BlindingSpeed) c).onEvasive(); } }
                    for(AbstractCard c: p().discardPile.group){ if(c instanceof BlindingSpeed){ ((BlindingSpeed) c).onEvasive(); } }
                }
                else { doPow(p(), new EvasionPower(amount), true); }
            }
            else {
                while(amount >= EVASIVE_CAP){
                    amount -= EVASIVE_CAP;
                    doPow(p(), new EvasivePower(EVASIVE), true);
                    for(AbstractCard c: p().hand.group){ if(c instanceof BlindingSpeed){ ((BlindingSpeed) c).onEvasive(); } }
                    for(AbstractCard c: p().drawPile.group){ if(c instanceof BlindingSpeed){ ((BlindingSpeed) c).onEvasive(); } }
                    for(AbstractCard c: p().discardPile.group){ if(c instanceof BlindingSpeed){ ((BlindingSpeed) c).onEvasive(); } }
                }
                if(amount > 0){ doPow(p(), new EvasionPower(amount), true); }
            }
            tickDuration();
        }
        else { this.isDone = true; }
    }
}