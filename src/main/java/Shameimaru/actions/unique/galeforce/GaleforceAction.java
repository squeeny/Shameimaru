package Shameimaru.actions.unique.galeforce;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static Shameimaru.util.actionShortcuts.att;
import static Shameimaru.util.actionShortcuts.doDraw;

public class GaleforceAction extends AbstractGameAction {

    private DamageInfo info;
    private int ENERGY;
    private int DRAW;

    public GaleforceAction(AbstractMonster target, DamageInfo info, int e, int d) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
        ENERGY = e;
        DRAW = d;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            this.target.damage(this.info);
            if ((((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {/* 38 */
                doDraw(DRAW, true);
                att(new GainEnergyAction(ENERGY));
            }
        }
        tickDuration();
    }
}