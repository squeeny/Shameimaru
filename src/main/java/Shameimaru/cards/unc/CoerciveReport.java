package Shameimaru.cards.unc;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.abs.abs_aya_card_dm;
import Shameimaru.cards.sp.reports.ControversialReport;
import Shameimaru.cards.sp.reports.FlatteringReport;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class CoerciveReport extends abs_aya_card_dm {
    private final static CardInfo cardInfo = new CardInfo(
            CoerciveReport.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    public CoerciveReport() {
        super(cardInfo, true);
        this.cardToPreview = createDummyCardListForPreview();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { createDummyCardListForAction(); }
    public void createDummyCardListForAction(){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new ControversialReport();
        optionCards.add(c);
        c = new FlatteringReport();
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){ cc.upgrade(); }
        }
        atb(new ChooseOneAction(optionCards));
    }
    public ArrayList<AbstractCard> createDummyCardListForPreview(){
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new ControversialReport();
        optionCards.add(c);
        c = new FlatteringReport();
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