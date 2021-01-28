package Shameimaru.cards.sp.core;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doDmg;
import static Shameimaru.util.actionShortcuts.doPow;

@AutoAdd.Ignore
public class attackCard extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            attackCard.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.ATTACK,
            CardTarget.ALL
    );
    public static final String ID = makeID(cardInfo.cardName);
    public attackCard(int damage, int times) {
        super(cardInfo, false);
        setDamage(damage, damage + 3);
        setMagic(times, times + 1);
        if(magicNumber > 0){ this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i <= magicNumber; i+=1){ doDmg(m, damage); }
    }
}
