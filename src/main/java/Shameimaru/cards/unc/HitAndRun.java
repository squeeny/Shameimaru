package Shameimaru.cards.unc;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;


public class HitAndRun extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            HitAndRun.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int EVASION = 5;
    private static final int UPG_EVASION = 5;
    public HitAndRun() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(EVASION, UPG_EVASION);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage);
        atb(new dynamicGainEvasiveAction(magicNumber));
    }
}