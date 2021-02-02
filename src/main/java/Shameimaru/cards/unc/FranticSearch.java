package Shameimaru.cards.unc;

import Shameimaru.actions.DiscardAnyAction;
import Shameimaru.actions.unique.franticSearch.franticSearchAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class FranticSearch extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            FranticSearch.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 7;
    private static final int DISCARD = 3;
    public FranticSearch() {
        super(cardInfo, true);
        setDamage(DAMAGE);
        setMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage);
        atb(new franticSearchAction(BaseMod.MAX_HAND_SIZE));
        atb(new DiscardAction(p, p, magicNumber, !upgraded));
    }
}