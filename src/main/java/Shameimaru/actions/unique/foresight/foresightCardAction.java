package Shameimaru.actions.unique.foresight;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static Shameimaru.util.actionShortcuts.doDef;

public class foresightCardAction extends AbstractGameAction {
    private AbstractCard guessedCard;
    private int blockGain;
    private int failedBlockGain;

    public foresightCardAction(AbstractCard guessedType,  int blockGain, int failedBlockGain) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        guessedCard = guessedType;
        this.blockGain = blockGain;
        this.failedBlockGain = failedBlockGain;
    }

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.type == guessedCard.type) { doDef(blockGain, true);
            } else { doDef(failedBlockGain, true); }
            break;
        }
        this.isDone = true;
    }
}
