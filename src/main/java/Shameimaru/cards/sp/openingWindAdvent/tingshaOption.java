package Shameimaru.cards.sp.openingWindAdvent;

import Shameimaru.actions.unique.openingWindAdvent.tingshaAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class tingshaOption extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            tingshaOption.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private final int DAMAGE = 3;
    public tingshaOption() {
        super(cardInfo, false);
        setMagic(DAMAGE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){ atb(new tingshaAction()); }
}