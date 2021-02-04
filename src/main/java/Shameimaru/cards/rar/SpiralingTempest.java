package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doAllDmg;
import static Shameimaru.util.actionShortcuts.doDmg;

public class SpiralingTempest extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            SpiralingTempest.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 20;
    private static final int UPG_DMG = 8;
    private static final int AOE_DMG = 13;
    private static final int UPG_AOE_DMG = 5;
    public SpiralingTempest() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(AOE_DMG, UPG_AOE_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDmg(m, damage); }
    public void triggerOnManualDiscard(){ doAllDmg(this.magicNumber, AbstractGameAction.AttackEffect.NONE, false); }
    @Override
    public void applyPowers()
    {
        magicNumber = baseMagicNumber;
        int tmp = baseDamage;
        baseDamage = baseMagicNumber;
        super.applyPowers();
        magicNumber = damage;
        baseDamage = tmp;
        super.applyPowers();
        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }
}