package Shameimaru.actions.unique.franticSearch;

import Shameimaru.powers.DiscardNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static Shameimaru.util.actionShortcuts.*;

public class franticSearchAction extends AbstractGameAction {
    private int DISCARD = 3;
    public franticSearchAction(int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }
    public void update() {
        int toDraw = this.amount - p().hand.size();
        if (toDraw > 0) { doDraw(toDraw, true); }
        this.isDone = true;
    }
}