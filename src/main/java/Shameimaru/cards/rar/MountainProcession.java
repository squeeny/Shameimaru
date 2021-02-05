package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class MountainProcession extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            MountainProcession.class.getSimpleName(),
            COSTS[5],
            CardType.ATTACK,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 30;
    private static final int UPG_BLOCK = 10;
    private static final int WEAK_VULN = 3;

    public MountainProcession() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(WEAK_VULN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(block);
        for(AbstractMonster mo: getAliveMonsters()){
            doPow(mo, new WeakPower(mo, magicNumber, false));
            doPow(mo, new VulnerablePower(mo, magicNumber, false));
        }
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