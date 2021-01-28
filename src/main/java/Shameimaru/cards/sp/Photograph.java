package Shameimaru.cards.sp;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doDmg;

public class Photograph extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Photograph.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.ALL
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    private static final int ENERGY = 2;
    public Photograph() {
        super(cardInfo, false);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
}