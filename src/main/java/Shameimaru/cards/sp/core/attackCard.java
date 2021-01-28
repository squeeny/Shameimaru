package Shameimaru.cards.sp.core;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.unc.WidespreadPropaganda;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

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
        boolean hasWideSpread = p.hasPower(WidespreadPropagandaPower.POWER_ID);
        for(int i = 0; i <= magicNumber; i+=1){
            if(hasWideSpread){ doAllDmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL, false); }
            else { doDmg(m, damage); }
        }
    }
}
