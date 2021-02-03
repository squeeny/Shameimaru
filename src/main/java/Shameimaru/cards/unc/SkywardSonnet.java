package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class SkywardSonnet extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            SkywardSonnet.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 2;
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;
    private static final int DISCARD = 1;
    public SkywardSonnet() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(DRAW, UPG_DRAW);
        setAyaMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(block);
        doDraw(magicNumber);
        atb(new DiscardAction(p, p, ayaSecondMagicNumber, false));
    }
}