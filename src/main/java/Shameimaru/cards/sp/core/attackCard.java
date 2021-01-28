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
    private boolean hasWideSpread;
    public attackCard(int damage, int times) {
        super(cardInfo, false);
        setDamage(damage, damage + 3);
        setMagic(times, times + 1);
        if(magicNumber > 0){ this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i <= magicNumber; i+=1){
            if(hasWideSpread){ doAllDmg(damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL, false); }
            else { doDmg(m, damage); }
        }
    }
    @Override
    public void applyPowers(){
        super.applyPowers();
        hasWideSpread = p().hasPower(WidespreadPropagandaPower.POWER_ID);
        if (hasWideSpread) { this.rawDescription = magicNumber > 0 ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]; }
        else { this.rawDescription = magicNumber > 0 ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION; }
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        hasWideSpread = p().hasPower(WidespreadPropagandaPower.POWER_ID);
        if (hasWideSpread) { this.rawDescription = magicNumber > 0 ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]; }
        else { this.rawDescription = magicNumber > 0 ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION; }
    }
}
