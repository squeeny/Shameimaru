package Shameimaru.cards.com;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class BlindingSpeed extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            BlindingSpeed.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 20;
    private static final int UPG_DMG = 8;
    private static final int COST = 0;
    public BlindingSpeed() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDmg(m, this.damage); }
    public void onEvasive(){ costForTurn = COST; }
}