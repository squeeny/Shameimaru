package Shameimaru.cards.unc;

import Shameimaru.actions.PhotographicDisposalAction;
import Shameimaru.actions.photograph.duplicatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDef;

public class PhotographicDisposal extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            PhotographicDisposal.class.getSimpleName(),
            COSTS[0],
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 7;
    private static final int UPG_DRAW = 1;
    private static final int STR = 1;
    private static final int UPG_STR = 1;
    public PhotographicDisposal() {
        super(cardInfo, false);
        setMagic(DRAW, UPG_DRAW);
        setAyaMagic(STR, UPG_STR);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new PhotographicDisposalAction(magicNumber, ayaSecondMagicNumber)); }
}