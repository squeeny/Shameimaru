package Shameimaru.cards.bas;

import Limitbreaker.cards.abs.abs_lb_card;
import Limitbreaker.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Limitbreaker.Limitbreaker.makeID;
import static Limitbreaker.util.actionShortcuts.doDmg;


public class Strike extends abs_lb_card {
    private final static CardInfo cardInfo = new CardInfo(
            Strike.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    private static final int ENERGY = 2;
    public Strike() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        m.takeTurn();
        m.applyTurnPowers();
        m.rollMove();
        m.createIntent();
    }
}