package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ToolsOfTheTradePower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doPow;
import static Shameimaru.util.actionShortcuts.p;

public class LibraryResearch extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            LibraryResearch.class.getSimpleName(),
            COSTS[1],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 1;
    private static final int UPG_COST = 0;
    public LibraryResearch() {
        super(cardInfo, false);
        setCostUpgrade(UPG_COST);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doPow(p(), new ToolsOfTheTradePower(p, magicNumber)); }
}