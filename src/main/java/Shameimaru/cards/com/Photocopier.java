package Shameimaru.cards.com;

import Shameimaru.actions.DiscardAnyAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;
import static Shameimaru.util.actionShortcuts.p;

public class Photocopier extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Photocopier.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int COPY = 1;
    private static final int DRAW = 1;
    private static final int ACTIVATE_PER_COPIES = 3;
    private static final int UPG_ACTIVATE_PER_COPIES = -1;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem");
    public Photocopier() {
        super(cardInfo, true);
        setMagic(DRAW);
        setAyaMagic(ACTIVATE_PER_COPIES, UPG_ACTIVATE_PER_COPIES);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsAction(p.masterDeck.group, COPY, uiStrings.TEXT[2], (cards) -> {
            AbstractCard c = cards.get(0);
            int countOfCard = 0;
            for(AbstractCard cc: p.masterDeck.group){
                if(cc.cardID.equals(c.cardID)){ countOfCard += 1; }
            }
            countOfCard += 1;
            att(new AddCardToDeckAction(c.makeCopy()));
            doDraw(countOfCard / ayaSecondMagicNumber, true);
            att(new GainEnergyAction(countOfCard / ayaSecondMagicNumber));
        }));
    }
}