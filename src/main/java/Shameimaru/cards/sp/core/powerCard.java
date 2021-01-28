package Shameimaru.cards.sp.core;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import Shameimaru.util.actionShortcuts;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doPow;

@AutoAdd.Ignore
public class powerCard extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            powerCard.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            CardTarget.ALL
    );
    public static final String ID = makeID(cardInfo.cardName);
    private AbstractPower power;
    private int UPG_POWER = 1;
    public powerCard(AbstractPower p, int amount) {
        super(cardInfo, false);
        power = p;
        setMagic(amount);
        power.amount = magicNumber;
        if(power.type == AbstractPower.PowerType.BUFF){
            power.owner = actionShortcuts.p();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        }
        timesUpgraded = 0;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(power.type == AbstractPower.PowerType.BUFF){ doPow(p, power); }
        else { doPow(m, power); }
    }
    public boolean canUpgrade() { return true; }
    @Override
    public void upgrade() {
        upgradeMagicNumber(UPG_POWER);
        this.timesUpgraded++;
        this.upgraded = true;
    }
}