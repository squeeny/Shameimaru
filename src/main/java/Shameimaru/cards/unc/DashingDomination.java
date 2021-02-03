package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDraw;

public class DashingDomination extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            DashingDomination.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int DRAW_N = 1;
    private int DRAW_D = 2;
    private int ENERGY = 1;
    public DashingDomination() {
        super(cardInfo, true);
        setMagic(DRAW_N);
        setAyaMagic(DRAW_D);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }
    @Override
    public void triggerWhenDrawn(){
        doDraw(magicNumber);
        if(upgraded){ atb(new GainEnergyAction(ENERGY)); }
    }
    @Override
    public void triggerOnManualDiscard(){ doDraw(ayaSecondMagicNumber); }
}