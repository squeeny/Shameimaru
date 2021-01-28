package Shameimaru.cards.sp.core;

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
            CardType.ATTACK,
            CardTarget.ALL
    );
    public static final String ID = makeID(cardInfo.cardName);
    public blockCard(int block) {
        super(cardInfo, false);
        setDamage(block, block + 3);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDef(block); }
}