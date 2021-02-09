package Shameimaru.cards.sp.duality;

import Shameimaru.actions.unique.openingWindAdvent.bandagesAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.PolaroidPower;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class thePolaroid extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            thePolaroid.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private final int EVASION = 1;
    private final int REFUND_ENERGY = 1;
    public thePolaroid() {
        super(cardInfo, true);
        setMagic(EVASION);
        setAyaMagic(REFUND_ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        doPow(p(), new PolaroidPower(magicNumber));
        if(upgraded){ atb(new GainEnergyAction(ayaSecondMagicNumber)); }
    }
}