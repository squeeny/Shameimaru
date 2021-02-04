package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.EvasivePower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class EvasiveManeuver extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            EvasiveManeuver.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;
    private static final int ENERGY = 1;
    private static final int DRAW = 2;
    public EvasiveManeuver() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, damage);
        if(p.hasPower(EvasivePower.POWER_ID)){
            atb(new GainEnergyAction(ENERGY));
            doDraw(magicNumber);
        }
    }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if(p().hasPower(EvasivePower.POWER_ID)){ glowColor = GOLD_BORDER_GLOW_COLOR; }
    }
}