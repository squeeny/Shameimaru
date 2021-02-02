package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class GaleBlade extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            GaleBlade.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;
    public GaleBlade() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(ENERGY, UPG_ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage);
        atb(new GainEnergyAction(magicNumber)
    }
}