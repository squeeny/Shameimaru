package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.enums.PhotoModes;
import Shameimaru.powers.ShootTheBulletPower;
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

import java.util.ArrayList;

import static Shameimaru.util.actionShortcuts.*;

public class createPhotographAction extends AbstractGameAction {
    protected ArrayList<AbstractGameAction> powerActions = new ArrayList<>();
    protected ArrayList<AbstractCard> c = new ArrayList<>();

    protected boolean firstFrame;
    protected AbstractMonster m;
    protected boolean snapshotPlus;
    protected ShootTheBulletPower stb = null;
    public createPhotographAction(AbstractMonster source) { this(source, false, null); }
    public createPhotographAction(AbstractMonster source, boolean snapshotPlus) { this(source, snapshotPlus, null); }
    public createPhotographAction(AbstractMonster source, boolean snapshotPlus, ShootTheBulletPower power) {
        this.firstFrame = true;
        this.duration = Settings.ACTION_DUR_FAST;
        m = source;
        this.snapshotPlus = snapshotPlus;
        if(power != null){ stb = power; }
    }

    public void update() {
        if (this.firstFrame) {
            Photograph photograph;
            GameActionManager tmp = AbstractDungeon.actionManager;
            AbstractDungeon.actionManager = new GameActionManager();
            int damage = getEnemyDamage(m);
            int damageM = getEnemyAttacks(m);
            m.takeTurn();
            stopTurnChanging(m);
            int block = 0;
            int blockM = 0;
            for(AbstractGameAction a: AbstractDungeon.actionManager.actions) {
                if(a instanceof GainBlockAction) {
                    if (block == 0) { block += a.amount; }
                    blockM += 1;
                }
                else if(a instanceof ApplyPowerAction){ powerActions.add(a); }
            }
            PhotoModes mode = PhotoModes.DRAW;
            if(damage > -1){ mode = PhotoModes.ATTACK; }
            if(block > 0){ mode = mode.equals(PhotoModes.ATTACK) ? PhotoModes.ATTACK_BLOCK : PhotoModes.BLOCK; }
            photograph = new Photograph(
                    mode,
                    damage,
                    damageM,
                    block,
                    blockM
            );
            AbstractDungeon.actionManager = tmp;
            if(snapshotPlus){ photograph.upgrade(); }
            if(stb != null){ stb.setCard(photograph); }
            else { att(new MakeTempCardInHandAction(photograph)); }
            tickDuration();
        }
        this.isDone = true;
    }

    public int getEnemyDamage(AbstractMonster m){
        if (isAttackIntent(m.intent)) { return ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg"); }
        return -1;
    }

    public int getEnemyAttacks(AbstractMonster m){
        if (isAttackIntent(m.intent)) {
            if (ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) { return ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt"); }
        }
        return 1;
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