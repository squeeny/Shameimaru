package Shameimaru.cards.unc;

import Shameimaru.actions.unique.telescoping.telescopingAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.QuickReportingPower;
import Shameimaru.powers.TelescopingPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class Telescoping extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Telescoping.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int CARDS = 4;
    public Telescoping() {
        super(cardInfo, false);
        setInnate(false, true);
        setMagic(CARDS);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new telescopingAction()); }
}