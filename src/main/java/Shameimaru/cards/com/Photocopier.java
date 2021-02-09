package Shameimaru.cards.com;

import Shameimaru.actions.DiscardAnyAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
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
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int COST = 0;
    private static final int AMOUNT = 1;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem");
    public Photocopier() {
        super(cardInfo, false);
        setCostUpgrade(COST);
        FleetingField.fleeting.set(this, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup eligible = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(AbstractCard c: p.masterDeck.group){
            if(!(c instanceof Photocopier) && c.rarity == CardRarity.COMMON){ eligible.addToBottom(c); }
        }
        atb(new SelectCardsAction(eligible.group, AMOUNT, uiStrings.TEXT[2], (cards) -> {
            AbstractCard c = cards.get(0);
            att(new AddCardToDeckAction(c.makeCopy()));
        }));
    }
}