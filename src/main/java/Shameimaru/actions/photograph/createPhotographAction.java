package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.cards.sp.photograph.blacklist.blacklistedPowers;
import Shameimaru.cards.sp.photograph.core.attackCard;
import Shameimaru.cards.sp.photograph.core.blockCard;
import Shameimaru.cards.sp.photograph.core.drawCard;
import Shameimaru.cards.sp.photograph.core.powerCard;
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
import com.megacrit.cardcrawl.monsters.beyond.Transient;
import com.megacrit.cardcrawl.monsters.exordium.Lagavulin;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.*;

public class createPhotographAction extends AbstractGameAction {
    private int damage;
    private int attack_times;
    private int block;
    private ArrayList<AbstractGameAction> powerActions = new ArrayList<>();
    private ArrayList<AbstractCard> c = new ArrayList<>();

    private boolean firstFrame;
    private AbstractMonster m;
    private boolean snapshotPlus;

    public createPhotographAction(AbstractMonster source) { this(source, false); }

    public createPhotographAction(AbstractMonster source, boolean snapshotPlus) {
        this.firstFrame = true;
        this.duration = Settings.ACTION_DUR_FAST;
        m = source;
        this.snapshotPlus = snapshotPlus;
    }

    public void update() {
        if (this.firstFrame) {
            Photograph photograph;
            GameActionManager tmp = AbstractDungeon.actionManager;
            AbstractDungeon.actionManager = new GameActionManager();
            getEnemyDamage(m);
            m.takeTurn();
            stopTurnChanging(m);
            for(AbstractGameAction a: AbstractDungeon.actionManager.actions) {
                if(a instanceof GainBlockAction) { block += a.amount; }
                else if(a instanceof ApplyPowerAction){ powerActions.add(a); }
            }
            setBlock(m);
            setPowerActions();
            photograph = new Photograph(c);
            AbstractDungeon.actionManager = tmp;
            if(snapshotPlus){ photograph.upgrade(); }
            att(new MakeTempCardInHandAction(photograph));
            this.isDone = true;
        }
    }

    public void getEnemyDamage(AbstractMonster m){
        if (isAttackIntent(m.intent)) {
            damage = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");
            if (ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) { attack_times = ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt"); }
            attackCard a = new attackCard(damage, attack_times);
            c.add(a);
        }
    }

    public void setBlock(AbstractMonster m){
        if (isBlockIntent(m.intent)) {
            blockCard b = new blockCard(block);
            c.add(b);
        }
    }

    public void setPowerActions(){
        for(AbstractGameAction a: powerActions) {
            if(a instanceof ApplyPowerAction){
                AbstractPower powerToApply = ReflectionHacks.getPrivate(a, ApplyPowerAction.class, "powerToApply");
                if(blacklistedPowers.notBlacklistedPower(powerToApply.ID)) {
                    powerCard p = new powerCard(ReflectionHacks.getPrivate(a, ApplyPowerAction.class, "powerToApply"), a.amount);
                    c.add(p);
                }
            }
        }
    }

    public void stopTurnChanging(AbstractMonster m) {
        switch (m.id) {

            case Transient.ID:
                int count = ReflectionHacks.getPrivate(m, m.getClass(), "count");
                ReflectionHacks.setPrivate(m, m.getClass(), "count", count - 1);
                break;

            case Lagavulin.ID:
                int idleCount = ReflectionHacks.getPrivate(m, m.getClass(), "idleCount");
                ReflectionHacks.setPrivate(m, m.getClass(), "idleCount", idleCount - 1);
                int debuffTurnCount = ReflectionHacks.getPrivate(m, m.getClass(), "debuffTurnCount");
                ReflectionHacks.setPrivate(m, m.getClass(), "debuffTurnCount", debuffTurnCount - 1);
                break;

        }
    }

}