package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.DeadlineDayPower;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class DeadlineDay extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            DeadlineDay.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int VULNERABLE = 99;
    private static final int DRAW = 2;
    public DeadlineDay() {
        super(cardInfo, false);
        setInnate(false, true);
        setMagic(VULNERABLE);
        setAyaMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p, new DeadlineDayPower(ayaSecondMagicNumber));
        doPow(p, new VulnerablePower(p, magicNumber, true));
    }
}