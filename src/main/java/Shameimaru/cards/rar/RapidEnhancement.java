package Shameimaru.cards.rar;

import Shameimaru.actions.photograph.upgradeAllPhotographAction;
import Shameimaru.actions.unique.outburstingGust.outburstingGustAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;


public class RapidEnhancement extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            RapidEnhancement.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;
    public RapidEnhancement() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage);
        atb(new upgradeAllPhotographAction());
    }
}