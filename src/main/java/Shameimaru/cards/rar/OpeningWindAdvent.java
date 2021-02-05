package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.openingWindAdvent.bandagesOption;
import Shameimaru.cards.sp.openingWindAdvent.tingshaOption;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

public class OpeningWindAdvent extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            OpeningWindAdvent.class.getSimpleName(),
            COSTS[3],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int DAMAGE_BLOCK = 3;
    private int COST = 2;
    public OpeningWindAdvent() {
        super(cardInfo, false);
        setInnate(true);
        setMagic(DAMAGE_BLOCK);
        setCostUpgrade(COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { createDummyCardListForAction(); }
    public void createDummyCardListForAction(){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new tingshaOption();
        optionCards.add(c);
        c = new bandagesOption();
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){ cc.upgrade(); }
        }
        atb(new ChooseOneAction(optionCards));
    }
}