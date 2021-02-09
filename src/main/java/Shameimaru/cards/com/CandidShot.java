package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class CandidShot extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            CandidShot.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 8;
    private static final int UPG_DMG = 2;
    public CandidShot() {
        super(cardInfo, true);
        setDamage(DMG, UPG_DMG);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        atb(new createPhotographAction(m, this.upgraded));
    }
}