package Shameimaru.cards.com;

import Shameimaru.actions.DiscardAnyAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;
import static Shameimaru.util.actionShortcuts.p;

public class WhispersOfTheWind extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            WhispersOfTheWind.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int FETCH = 1;
    private static final int DISCARD = 1;
    public WhispersOfTheWind() {
        super(cardInfo, true);
        setMagic(FETCH);
        setAyaMagic(DISCARD);
        setRetain(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SeekAction(magicNumber));
        atb(new DiscardAction(p, p, ayaSecondMagicNumber, !this.upgraded));
        setExhaust(true);
    }
}