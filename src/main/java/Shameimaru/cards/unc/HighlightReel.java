package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.actions.unique.highlightReel.highlightReelAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDef;

public class HighlightReel extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            HighlightReel.class.getSimpleName(),
            COSTS[3],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int PLAYED_CARDS = 2;
    private static final int UPG_PLAYED_CARDS = 1;
    private static final int DISCARD = 1;
    public HighlightReel() {
        super(cardInfo, false);
        setMagic(PLAYED_CARDS, UPG_PLAYED_CARDS);
        setAyaMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, ayaSecondMagicNumber, false));
        atb(new highlightReelAction(magicNumber));
    }
}