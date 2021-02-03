package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.QuickReportingPower;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doPow;
import static Shameimaru.util.actionShortcuts.p;

public class QuickReporting extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            QuickReporting.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public QuickReporting() {
        super(cardInfo, false);
        setInnate(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doPow(p(), new QuickReportingPower()); }
}