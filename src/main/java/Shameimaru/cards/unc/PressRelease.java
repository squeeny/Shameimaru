package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.duplicatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDef;

public class PressRelease extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            PressRelease.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;
    private static final int COPIES_AMOUNT = 1;
    public PressRelease() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(COPIES_AMOUNT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        atb(new duplicatePhotographAction(magicNumber));
    }
}