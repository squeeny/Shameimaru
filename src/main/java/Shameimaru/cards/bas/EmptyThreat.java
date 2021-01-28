package Shameimaru.cards.bas;

import Shameimaru.actions.ForceIntentAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.IntentSwitcher;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class EmptyThreat extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            EmptyThreat.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    public EmptyThreat() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isBlockIntent(m.intent)) { doDmg(m, this.damage); }
        else { atb(new ForceIntentAction(p, m, IntentSwitcher.DEFEND)); }
    }
}