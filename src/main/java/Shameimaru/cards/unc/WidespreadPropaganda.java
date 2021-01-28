package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class WidespreadPropaganda extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            WidespreadPropaganda.class.getSimpleName(),
            COSTS[2],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int AMOUNT = -1;
    private static final int UPG_COST = 1;
    public WidespreadPropaganda() {
        super(cardInfo, false);
        setCostUpgrade(UPG_COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doPow(p(), new WidespreadPropagandaPower(p()));
    }
}