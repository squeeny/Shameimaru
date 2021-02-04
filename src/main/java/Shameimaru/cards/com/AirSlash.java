package Shameimaru.cards.com;

import Shameimaru.actions.unique.airSlash.airSlashAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class AirSlash extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            AirSlash.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 12;
    private static final int UPG_DMG = 4;
    private static final int DRAW = 1;
    private static final int REFUND_GOOD = 2;
    private static final int REFUND_BAD = 1;
    public AirSlash() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        doDraw(magicNumber);
        atb(new airSlashAction(REFUND_GOOD, REFUND_BAD));
    }
}