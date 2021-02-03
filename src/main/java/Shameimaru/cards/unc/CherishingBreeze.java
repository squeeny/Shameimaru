package Shameimaru.cards.unc;

import Shameimaru.actions.unique.cherishingBreeze.cherishingBreezeAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class CherishingBreeze extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            CherishingBreeze.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;
    private static final int ENERGY = 1;
    public CherishingBreeze() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(block);
        atb(new cherishingBreezeAction(p.hand.size()));
    }
}