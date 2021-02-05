package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doDmg;

public class WindSickleVeiling extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            WindSickleVeiling.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int HITS = 1;

    public WindSickleVeiling() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(HITS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i += 1) { doDmg(m, damage); }
    }

    public void didDiscard() { setMagic(HITS + GameActionManager.totalDiscardedThisTurn); }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        setMagic(HITS + GameActionManager.totalDiscardedThisTurn);
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }

    public void applyPowers(){
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        if(magicNumber > 1){ rawDescription = cardStrings.EXTENDED_DESCRIPTION[0]; }
        this.initializeDescription();
    }
}