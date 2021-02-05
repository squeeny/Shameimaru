package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.actions.unique.invertedColours.invertedColoursAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDef;

public class InvertedColours extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            InvertedColours.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int COST_UPG = 0;
    private int AMOUNT = 1;
    public InvertedColours() {
        super(cardInfo, false);
        setRetain(true);
        setCostUpgrade(COST_UPG);
        setMagic(AMOUNT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new invertedColoursAction(magicNumber));
    }
}