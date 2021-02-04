package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.OmniscienceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDraw;

public class AutumnalFallWind extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            AutumnalFallWind.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int DRAW = 2;
    private int DISCARD_ACTION = 2;
    public AutumnalFallWind() {
        super(cardInfo, true);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }
    @Override
    public void triggerOnManualDiscard(){
        atb(new OmniscienceAction(DISCARD_ACTION));
        if(upgraded){ doDraw(magicNumber); }
    }
}