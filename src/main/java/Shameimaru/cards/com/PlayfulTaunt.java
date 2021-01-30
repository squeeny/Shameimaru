package Shameimaru.cards.com;

import Shameimaru.actions.ForceIntentAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.IntentSwitcher;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;
import static Shameimaru.util.actionShortcuts.doDmg;

public class PlayfulTaunt extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            PlayfulTaunt.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;
    public PlayfulTaunt() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(this.block);
        atb(new ForceIntentAction(p, m, IntentSwitcher.ATTACK));
    }
}