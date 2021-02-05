package Shameimaru.cards.sp.photograph.core;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doDef;
import static Shameimaru.util.actionShortcuts.doDmg;

@AutoAdd.Ignore
public class blockCard extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            blockCard.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.ALL
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int UPG_BLOCK = 1;
    public blockCard(int block) {
        super(cardInfo, false);
        setDamage(block);
        timesUpgraded = 0;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDef(block); }
    public boolean canUpgrade() { return true; }
    @Override
    public void upgrade() {
        upgradeBlock(UPG_BLOCK + (this.timesUpgraded * 2));
        this.timesUpgraded++;
        this.upgraded = true;
    }
}