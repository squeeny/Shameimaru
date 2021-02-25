package Shameimaru.cards.sp.duality;

import Shameimaru.actions.unique.openingWindAdvent.bandagesAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.unc.MassDeletion;
import Shameimaru.powers.NegativePower;
import Shameimaru.powers.PolaroidPower;
import Shameimaru.util.CardInfo;
import Shameimaru.util.TexLoader;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class theNegative extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            theNegative.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private final int DAMAGE = 8;
    private final int UPG_DAMAGE = 4;
    private final int REFUND_ENERGY = 1;
    public theNegative() {
        super(cardInfo, false);
        setMagic(DAMAGE, UPG_DAMAGE);
        setAyaMagic(REFUND_ENERGY);
        img = TexLoader.getAndLoadCardTextureString("DualityB");
        this.textureImg = img;
        loadCardImage(textureImg);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        doPow(p(), new NegativePower(magicNumber));
        atb(new GainEnergyAction(ayaSecondMagicNumber));
    }
}