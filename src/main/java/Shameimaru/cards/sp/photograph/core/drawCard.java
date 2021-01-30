package Shameimaru.cards.sp.photograph.core;

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
    private int UPG_DRAW = 1;
    public drawCard(int draw) {
        super(cardInfo, true);
        setMagic(draw);
        timesUpgraded = 0;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDraw(magicNumber); }
    public boolean canUpgrade() { return true; }
    @Override
    public void upgrade() {
        upgradeMagicNumber(UPG_DRAW);
        this.timesUpgraded++;
        this.upgraded = true;
    }
}