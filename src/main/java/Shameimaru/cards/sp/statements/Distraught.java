package Shameimaru.cards.sp.statements;

import Shameimaru.actions.unique.foresight.foresightCardAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AngerPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doPow;

@AutoAdd.Ignore
public class Distraught extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Distraught.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private AbstractMonster storedMonster;
    private static final int Distraught = 2;
    private static final int UPG_Distraught = 1;
    public Distraught(){
        super(cardInfo, false);
        setMagic(Distraught, UPG_Distraught);
    }
    public Distraught(AbstractMonster m) {
        super(cardInfo, false);
        setMagic(Distraught, UPG_Distraught);
        storedMonster = m;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){ doPow(storedMonster, new WeakPower(storedMonster, magicNumber, false)); }
}