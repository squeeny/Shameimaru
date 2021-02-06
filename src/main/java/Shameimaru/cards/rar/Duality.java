package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card_dm;
import Shameimaru.cards.sp.duality.theNegative;
import Shameimaru.cards.sp.duality.thePolaroid;
import Shameimaru.cards.sp.reports.ControversialReport;
import Shameimaru.cards.sp.reports.FlatteringReport;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

public class Duality extends abs_aya_card_dm {
    private final static CardInfo cardInfo = new CardInfo(
            Duality.class.getSimpleName(),
            COSTS[3],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    public Duality() {
        super(cardInfo, true);
        this.cardToPreview = createDummyCardListForPreview();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { createDummyCardListForAction(); }
    public void createDummyCardListForAction(){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new thePolaroid();
        optionCards.add(c);
        c = new theNegative();
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){ cc.upgrade(); }
        }
        atb(new ChooseOneAction(optionCards));
    }
    public ArrayList<AbstractCard> createDummyCardListForPreview(){
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new thePolaroid();
        optionCards.add(c);
        c = new theNegative();
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){ cc.upgrade(); }
        }
        return optionCards;
    }
    public void upgrade(){
        super.upgrade();
        this.cardToPreview = createDummyCardListForPreview();
    }
}