package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.Photograph;
import Shameimaru.cards.sp.core.attackCard;
import Shameimaru.cards.sp.core.blockCard;
import Shameimaru.cards.sp.core.powerCard;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.att;

public class createPhotographAction extends AbstractGameAction {
    private int damage;
    private int attack_times;
    private int block;
    private ArrayList<AbstractGameAction> powerActions = new ArrayList<>();
    private ArrayList<AbstractCard> c = new ArrayList<>();

    private boolean firstFrame;
    private AbstractMonster m;

    public createPhotographAction(AbstractMonster source) {
        this.firstFrame = true;
        this.duration = Settings.ACTION_DUR_FAST;
        m = source;
    }

    public void update() {
        if (this.firstFrame) {
            Photograph photograph;
            GameActionManager tmp = AbstractDungeon.actionManager;
            AbstractDungeon.actionManager = new GameActionManager();
            getEnemyDamage(m);
            m.takeTurn();
            for(AbstractGameAction a: AbstractDungeon.actionManager.actions) {
                if(a instanceof GainBlockAction) { block += a.amount; }
                else if(a instanceof ApplyPowerAction){ powerActions.add(a); }
            }
            setBlock();
            setPowerActions();
            photograph = new Photograph(c);
            AbstractDungeon.actionManager = tmp;
            att(new MakeTempCardInHandAction(photograph));
            this.isDone = true;
        }
    }

    public void getEnemyDamage(AbstractMonster m){
        damage = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");
        if (ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) { attack_times = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt"); }
        attackCard a = new attackCard(damage, attack_times);
        if(a.baseDamage > 0){ c.add(a); }
    }

    public void setBlock(){
        blockCard b = new blockCard(block);
        if(b.block > 0){ c.add(b); }
    }

    public void setPowerActions(){
        for(AbstractGameAction a: powerActions) {
            if(a instanceof ApplyPowerAction){
                powerCard p = new powerCard(ReflectionHacks.getPrivate(a, ApplyPowerAction.class, "powerToApply"), a.amount);
                c.add(p);
            }
        }
    }


}