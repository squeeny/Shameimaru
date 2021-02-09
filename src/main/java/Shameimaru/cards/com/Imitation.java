package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class Imitation extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Imitation.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 1;
    private static final int PHOTO_AMOUNT = 1;
    public Imitation() {
        super(cardInfo, true);
        setMagic(DRAW);
        setRetain(true);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDraw(magicNumber);
        atb(new createPhotographAction(m, this.upgraded));
    }
}