package Shameimaru.cards.unc;

import Shameimaru.actions.unique.galeforce.GaleforceAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doAllDmg;

public class Galeforce extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Galeforce.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int DRAW = 1;
    private static final int ENERGY = 1;
    public Galeforce() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DRAW);
        setAyaMagic(ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new GaleforceAction(m, new DamageInfo(p, damage, damageTypeForTurn), magicNumber, ayaSecondMagicNumber)); }
}