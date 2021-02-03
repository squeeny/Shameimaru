package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class ForseenReaction extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            ForseenReaction.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int BLOCK = 0;
    public ForseenReaction() {
        super(cardInfo, false);
        setMagic(BLOCK);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDef(magicNumber); }
    @Override
    public void applyPowers(){
        magicNumber = baseMagicNumber;
        int bonusBlock = 0;
        if(upgraded) {
            for (AbstractMonster m : getAliveMonsters()) {
                if (!m.isDead && !m.isDying) {
                    if (isAttackIntent(m.intent)) { bonusBlock += getEnemyDamage(m); }
                }
            }
            magicNumber += bonusBlock;
        }
        super.applyPowers();
        isMagicNumberModified = magicNumber != baseMagicNumber;
        if(isMagicNumberModified){ this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] : cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0]; }
        else { this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION: cardStrings.DESCRIPTION; }
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo){
        magicNumber = baseMagicNumber;
        int bonusBlock = 0;
        if(!upgraded){ bonusBlock += getEnemyDamage(mo); }
        magicNumber += bonusBlock;
        super.calculateCardDamage(mo);
        isMagicNumberModified = magicNumber != baseMagicNumber;
        if(isMagicNumberModified){ this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] : cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0]; }
        else { this.rawDescription = this.upgraded ? cardStrings.UPGRADE_DESCRIPTION: cardStrings.DESCRIPTION; }
    }

    public int getEnemyDamage(AbstractMonster m) {
        int damage = 0;
        damage = (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");
        if (ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) { damage *= (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt"); }
        return damage;
    }

    @Override
    public void upgrade(){
        super.upgrade();
        this.target = CardTarget.ALL_ENEMY;
    }

}