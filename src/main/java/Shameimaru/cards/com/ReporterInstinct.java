package Shameimaru.cards.com;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;
import static Shameimaru.util.actionShortcuts.isAttackIntent;

public class ReporterInstinct extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            ReporterInstinct.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 18;
    private static final int UPG_DMG = 6;
    private static final int BLOCK = 15;
    private static final int UPG_BLOCK = 5;
    public ReporterInstinct() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isAttackIntent(m.intent)) { doDmg(m, this.damage); }
        else { doDef(this.block); }
    }
}