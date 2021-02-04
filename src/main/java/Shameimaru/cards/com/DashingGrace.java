package Shameimaru.cards.com;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;
import static Shameimaru.util.actionShortcuts.isAttackIntent;

public class DashingGrace extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            DashingGrace.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 13;
    private static final int UPG_DMG = 3;
    private static final int EVASION = 2;
    private static final int UPG_EVASION = 1;
    private static final int DISCARD_EVASION = 3;
    public DashingGrace() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(EVASION, UPG_EVASION);
        setAyaMagic(DISCARD_EVASION);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        atb(new dynamicGainEvasiveAction(magicNumber));
    }
    @Override
    public void triggerOnManualDiscard() { atb(new dynamicGainEvasiveAction(ayaSecondMagicNumber)); }
}