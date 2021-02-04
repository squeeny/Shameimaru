package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class FlutteringFeyFan extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            FlutteringFeyFan.class.getSimpleName(),
            COSTS[3],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int HITS = 3;

    public FlutteringFeyFan() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(HITS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i += 1) { doDmg(m, damage); }
    }

    public void didDiscard() { setCostForTurn(this.costForTurn - 1); }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        setCostForTurn(this.cost - GameActionManager.totalDiscardedThisTurn);
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }
}