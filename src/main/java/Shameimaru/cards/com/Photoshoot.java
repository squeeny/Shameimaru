package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class Photoshoot extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Photoshoot.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;
    public Photoshoot() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(block);
        atb(new createPhotographAction(m, this.upgraded));
    }
}