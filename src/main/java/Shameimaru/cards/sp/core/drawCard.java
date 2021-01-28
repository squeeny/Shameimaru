package Shameimaru.cards.sp.core;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doDef;
import static Shameimaru.util.actionShortcuts.doDraw;

@AutoAdd.Ignore
public class drawCard extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            drawCard.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.NONE
    );
    public static final String ID = makeID(cardInfo.cardName);
    public drawCard(int draw) {
        super(cardInfo, true);
        setMagic(draw, draw + 1);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDraw(magicNumber); }
}