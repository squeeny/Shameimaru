package Shameimaru.cards.com;

import Shameimaru.actions.unique.guidingBreeze.guidingBreezeAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class GuidingBreeze extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            GuidingBreeze.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;
    private static final int DRAW = 1;
    public GuidingBreeze() {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(block);
        doDraw(magicNumber);
        atb(new guidingBreezeAction(magicNumber));
    }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof Photograph) {
                glowColor = GOLD_BORDER_GLOW_COLOR;
                break;
            }
        }
    }
}