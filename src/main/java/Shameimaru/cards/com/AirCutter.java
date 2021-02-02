package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class AirCutter extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            CandidShot.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 9;
    private static final int UPG_DMG = 3;
    private static final int DISCARD = 1;
    private static final int DRAW = 1;

    public AirCutter() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(DRAW);
        setAyaMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        doDraw(magicNumber);
        atb(new DiscardAction(p, p, ayaSecondMagicNumber, false));
    }
}