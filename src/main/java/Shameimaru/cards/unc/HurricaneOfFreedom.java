package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.duplicatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class HurricaneOfFreedom extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            HurricaneOfFreedom.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 5;
    private static final int DISCARD = 1;
    public HurricaneOfFreedom() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doAllDmg(damage, AbstractGameAction.AttackEffect.NONE, false);
        atb(new DiscardAction(p, p, magicNumber, !upgraded));
    }
}