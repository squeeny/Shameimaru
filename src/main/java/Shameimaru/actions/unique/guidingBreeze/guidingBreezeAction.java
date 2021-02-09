package Shameimaru.actions.unique.guidingBreeze;

import Shameimaru.cards.sp.photograph.Photograph;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Shameimaru.util.actionShortcuts.doDmg;
import static Shameimaru.util.actionShortcuts.doDraw;

public class guidingBreezeAction extends AbstractGameAction {

    public guidingBreezeAction(int draw) { amount = draw; }

    public void update() {
        boolean viable = false;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof Photograph) {
                viable = true;
                break;
            }
        }
        if(viable){ doDraw(amount, true); }
        this.isDone = true;
    }
}