package Shameimaru.actions.unique.exclusiveScoop;

import Shameimaru.cards.sp.photograph.Photograph;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Shameimaru.util.actionShortcuts.doDmg;


public class exclusiveScoopAction extends AbstractGameAction {

    public exclusiveScoopAction(AbstractCreature target, int damage) {
        this.target = target;
        amount = damage;
    }

    public void update() {
        if (this.target != null && this.target.currentHealth > 0) {
            int count = 0;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                if (c instanceof Photograph) { count++; }
            }
            for (int i = 0; i < count; i += 1){ doDmg(target, amount, DamageInfo.DamageType.NORMAL, AttackEffect.NONE, Settings.FAST_MODE, true); }
        }
        this.isDone = true;
    }
}