package Shameimaru.cards.rar;

import Shameimaru.actions.unique.splittingGale.splittingGaleAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class SplittingGale extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            SplittingGale.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;
    private static final int HITS = 2;
    private static final int DISCARD = 1;
    private static final int REDUCE = 2;
    public SplittingGale() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(REDUCE);
        setAyaMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < HITS; i+=1){ doDmg(m, damage); }
        atb(new splittingGaleAction(this, magicNumber));
    }
}