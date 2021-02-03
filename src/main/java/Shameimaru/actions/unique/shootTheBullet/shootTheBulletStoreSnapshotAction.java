package Shameimaru.actions.unique.shootTheBullet;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.powers.ShootTheBulletPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.util.actionShortcuts.att;

public class shootTheBulletStoreSnapshotAction extends createPhotographAction {
    private ShootTheBulletPower stb;
    public shootTheBulletStoreSnapshotAction(AbstractMonster source, ShootTheBulletPower power) {
        super(source);
        stb = power;
    }
    public shootTheBulletStoreSnapshotAction(AbstractMonster source, ShootTheBulletPower power, boolean snapshotPlus) {
        super(source, snapshotPlus);
        stb = power;
    }

    @Override
    public void update() {
        if (firstFrame) {
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
            stb.setCard(photograph);
            this.isDone = true;
        }
    }
}
