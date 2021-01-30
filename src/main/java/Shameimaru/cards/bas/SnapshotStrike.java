package Shameimaru.cards.bas;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class SnapshotStrike extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            SnapshotStrike.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    public SnapshotStrike() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        this.tags.remove(CardTags.STARTER_STRIKE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDmg(m, this.damage);
        atb(new createPhotographAction(m));
    }
}