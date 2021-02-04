package Shameimaru.cards.rar;

import Shameimaru.actions.unique.outburstingGust.outburstingGustAction;
import Shameimaru.actions.unique.splittingGale.splittingGaleAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.statements.Enrage;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class OutburstingGust extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            OutburstingGust.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 16;
    private static final int UPG_DAMAGE = 5;
    private static final int ENERGY = 2;
    private static final int DRAW = 2;
    public OutburstingGust() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DRAW);
        setAyaMagic(ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage);
        atb(new outburstingGustAction(ayaSecondMagicNumber, magicNumber));
    }
}