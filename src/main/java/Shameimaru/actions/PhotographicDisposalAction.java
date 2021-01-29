package Shameimaru.actions;

import Shameimaru.cards.sp.Photograph;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.*;

public class PhotographicDisposalAction extends AbstractGameAction {

    private float startingDuration;
    private int draw;
    private int str;
    public PhotographicDisposalAction(int draw, int str) {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.draw = draw;
        this.str = str;
    }

    public void update() {
        if(this.duration == this.startingDuration){
            ArrayList<AbstractCard> cards = new ArrayList<>(p().hand.group);
            doDraw(draw, true);
            for(AbstractCard c: cards){
                if(c instanceof Photograph) {
                    att(new ExhaustSpecificCardAction(c, p().hand));
                    doPow(p(), new StrengthPower(p(), str), true);
                }
                else { att(new DiscardSpecificCardAction(c));}
            }
            tickDuration();
        }
        else { this.isDone = true; }
    }
}