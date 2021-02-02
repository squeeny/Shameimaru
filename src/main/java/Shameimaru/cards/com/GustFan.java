package Shameimaru.cards.com;

import Shameimaru.actions.ForceIntentAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.IntentSwitcher;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class GustFan extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            GustFan.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 8;
    private static final int UPG_DMG = 2;
    private static final int DRAW = 1;
    public GustFan() {
        super(cardInfo, true);
        setDamage(DMG, UPG_DMG);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        doDraw(magicNumber);
        if (isAttackIntent(m.intent)) {
            doDraw(magicNumber);
        }
    }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : getAliveMonsters()) {
            if (isAttackIntent(m.intent)) {
                glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}