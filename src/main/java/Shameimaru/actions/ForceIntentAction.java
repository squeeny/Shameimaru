package Shameimaru.actions;

import Shameimaru.enums.IntentSwitcher;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.beyond.GiantHead;
import com.megacrit.cardcrawl.monsters.beyond.Maw;
import com.megacrit.cardcrawl.monsters.city.BookOfStabbing;
import com.megacrit.cardcrawl.monsters.city.BronzeAutomaton;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;

import java.util.function.Predicate;

import static Shameimaru.util.actionShortcuts.isAttackIntent;
import static Shameimaru.util.actionShortcuts.isBlockIntent;

public class ForceIntentAction extends AbstractGameAction {

    private IntentSwitcher intent;
    public static Predicate<AbstractMonster> attackTest = (mo) -> isAttackIntent(mo.intent);
    public static Predicate<AbstractMonster> blockTest = (mo) -> !isAttackIntent(mo.intent);

    private AbstractPlayer p;
    private AbstractMonster m;

    private int count;

    public ForceIntentAction(AbstractPlayer p, AbstractMonster m, IntentSwitcher type) {
        this.p = p;
        this.m = m;
        this.intent = type;
    }

    public void update() { this.isDone = this.newIntent(this.m, intent); }

    public boolean newIntent(AbstractMonster m, IntentSwitcher type) {

        Predicate<AbstractMonster> predicateIntentChanger;

        switch (type){
            case ATTACK:
                predicateIntentChanger = attackTest;
                break;
            case NOT_ATTACK:
                predicateIntentChanger = blockTest;
                break;
            default:
                predicateIntentChanger = blockTest;
                break;
        }
        //Does nothing if the intent already matches
        if (predicateIntentChanger.test(m)) { return true; }

        //Stores the original move then tries to find another appropriate move
        EnemyMoveInfo originalMove = ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");
        int tries = 0;
        getBaseValues(m);
        while (tries < 10) {
            m.rollMove();
            m.createIntent();
            returnToBaseValues(m);
            if (predicateIntentChanger.test(m)) { return true; }
            tries++;
        }
        //sets intent back to original move if no suitable moves were found
        m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
        m.createIntent();
        return true;
    }


    public void getBaseValues(AbstractMonster m){
        switch (m.id){
            case GiantHead.ID:
                count = ReflectionHacks.getPrivate(m, m.getClass(), "count");
                break;
            case BronzeAutomaton.ID:
                count = ReflectionHacks.getPrivate(m, m.getClass(), "numTurns");
                break;
            case Maw.ID:
                count = ReflectionHacks.getPrivate(m, m.getClass(), "turnCount");
                break;
            case CorruptHeart.ID:
                count = ReflectionHacks.getPrivate(m, m.getClass(), "moveCount");
                break;
        }

    }

    public void returnToBaseValues(AbstractMonster m){
        switch (m.id){
            case GiantHead.ID:
                ReflectionHacks.setPrivate(m, m.getClass(), "count", count);
                break;
            case BronzeAutomaton.ID:
                ReflectionHacks.setPrivate(m, m.getClass(), "numTurns", count);
                break;
            case Maw.ID:
                ReflectionHacks.setPrivate(m, m.getClass(), "turnCount", count);
                break;
            case CorruptHeart.ID:
                ReflectionHacks.setPrivate(m, m.getClass(), "moveCount", count);
                break;
        }
    }
}