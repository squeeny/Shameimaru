package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.Photograph;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static Shameimaru.util.actionShortcuts.p;

public class createPhotographAction extends AbstractGameAction {
    private int damage;
    private int attack_times;
    private int block;

    private boolean firstFrame;
    private AbstractMonster s;

    public createPhotographAction(AbstractMonster source) {
        this.firstFrame = true;
        this.duration = Settings.ACTION_DUR_FAST;
        s = source;
    }



    public void update() {
        if (this.firstFrame) {
            Photograph photograph = new Photograph();
            GameActionManager tmp = AbstractDungeon.actionManager;
            AbstractDungeon.actionManager = new GameActionManager();
            getEnemyDamage(s);
            s.takeTurn();
            for(AbstractGameAction a: AbstractDungeon.actionManager.actions) {
                if(a instanceof GainBlockAction) { block += a.amount; }
                else if(a instanceof ApplyPowerAction){
                    // access power to apply
                }
            }
            AbstractDungeon.actionManager = tmp;
            this.firstFrame = false;
            tickDuration();
        }
    }

    public void getEnemyDamage(AbstractMonster m) {
        damage = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");
        if (ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) { attack_times = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt"); }
    }

}