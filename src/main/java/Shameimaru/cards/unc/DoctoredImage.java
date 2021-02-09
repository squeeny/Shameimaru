package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.upgradePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDef;

public class DoctoredImage extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            DoctoredImage.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;
    private static final int UPGRADE_AMOUNT = 1;
    private static final int UPG_UPGRADE_AMOUNT = 1;

    public DoctoredImage() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(UPGRADE_AMOUNT, UPG_UPGRADE_AMOUNT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        atb(new upgradePhotographAction(magicNumber));
    }
}