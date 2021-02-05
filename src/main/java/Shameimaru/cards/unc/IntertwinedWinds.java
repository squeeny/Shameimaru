package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class IntertwinedWinds extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            IntertwinedWinds.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int SEEK = 1;
    public IntertwinedWinds() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(SEEK);
        this.shuffleBackIntoDrawPile = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doDmg(m, damage); }
    @Override
    public void triggerOnManualDiscard(){ atb(new SeekAction(magicNumber)); }
}