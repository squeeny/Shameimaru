package Shameimaru.cards.sp.photograph.core;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
    private int UPG_DMG = 1;
    private int BASE_HITS = 1;
    public attackCard(int damage, int times) {
        super(cardInfo, false);
        setDamage(damage);
        setMagic(times);
        timesUpgraded = 0;
        if(magicNumber > BASE_HITS){ this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
        else { setMagic(BASE_HITS); }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i+=1){
            if(hasWideSpread){ doAllDmg(damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL, false); }
            else { doDmg(m, damage); }
        }
    }
    @Override
    public void applyPowers(){
        super.applyPowers();
        hasWideSpread = p().hasPower(WidespreadPropagandaPower.POWER_ID);
        if (hasWideSpread) { this.rawDescription = magicNumber > BASE_HITS ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]; }
        else { this.rawDescription = magicNumber > BASE_HITS ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION; }
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        hasWideSpread = p().hasPower(WidespreadPropagandaPower.POWER_ID);
        if (hasWideSpread) { this.rawDescription = magicNumber > BASE_HITS ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]; }
        else { this.rawDescription = magicNumber > BASE_HITS ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION; }
    }
    public boolean canUpgrade() { return true; }
    @Override
    public void upgrade() {
        upgradeDamage(UPG_DMG + (this.timesUpgraded * 2));
        this.timesUpgraded++;
        this.upgraded = true;
    }
}
