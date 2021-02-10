package Shameimaru.actions.unique.massDeletion;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.*;

public class massDeletionAction extends AbstractGameAction {
    private float startingDuration;
    private cardMODE mode;
    private AbstractCard field;
    public enum cardMODE{
        DAMAGE,
        BLOCK
    }
    public massDeletionAction(AbstractCard pairedCard, boolean isDamage){
        field = pairedCard;
        actionType = AbstractGameAction.ActionType.WAIT;
        startingDuration = Settings.ACTION_DUR_FAST;
        mode = isDamage ? cardMODE.DAMAGE : cardMODE.BLOCK;
        duration = startingDuration;
    }
    public void update() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(AbstractCard card : p().drawPile.group){ tmp.addToRandomSpot(card); }
        if (this.duration == this.startingDuration) {
            int count = tmp.size();
            if (count > 0) {
                int i;
                if(mode == cardMODE.BLOCK){
                    for(i = 0; i < count; i += 1){
                        field.applyPowers();
                        doDef(field.block, true);
                        AbstractCard card = tmp.getNCardFromTop(i);
                        p().drawPile.moveToDiscardPile(card);
                        card.triggerOnManualDiscard();
                        GameActionManager.incrementDiscard(false);
                    }
                }
                else {
                    for(i = 0; i < count; i += 1){
                        att(new AttackDamageRandomEnemyAction(field, AttackEffect.NONE));
                        AbstractCard card = tmp.getNCardFromTop(i);
                        p().drawPile.moveToDiscardPile(card);
                        card.triggerOnManualDiscard();
                        GameActionManager.incrementDiscard(false);
                    }
                }
            }
            tickDuration();
        }
        else{ this.isDone = true; }
    }
}