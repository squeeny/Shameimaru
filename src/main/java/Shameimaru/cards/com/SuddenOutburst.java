package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;


public class SuddenOutburst extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            SuddenOutburst.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 8;
    private static final int UPG_DMG = 2;
    public SuddenOutburst() {
        super(cardInfo, true);
        setDamage(DMG, UPG_DMG);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        m = getRandomAliveMonster(AbstractDungeon.getMonsters(), AbstractDungeon.cardRng);
        doDmg(m, this.damage);
        atb(new createPhotographAction(m, this.upgraded));
    }
}