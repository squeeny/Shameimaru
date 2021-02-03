package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class WindsOfChange extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            WindsOfChange.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int drawNumber;
    public WindsOfChange() {
        super(cardInfo, true);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        drawNumber = p.hand.size();
        drawNumber -= 1;
        if(upgraded){ drawNumber += 2; }
        atb(new ShuffleAllAction());
        atb(new ShuffleAction(p.drawPile));
        doDraw(drawNumber);
    }
}