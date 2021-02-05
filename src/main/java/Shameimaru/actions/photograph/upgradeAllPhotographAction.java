package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.photograph.Photograph;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.util.actionShortcuts.*;

public class upgradeAllPhotographAction extends AbstractGameAction {

    public upgradeAllPhotographAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for(AbstractCard c: p().hand.group){
                if(c instanceof Photograph){ c.upgrade(); }
            }
            this.isDone = true;
        }
    }
}