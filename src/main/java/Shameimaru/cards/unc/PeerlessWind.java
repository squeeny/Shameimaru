package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.peerlessWind.peerlessWindOption;
import Shameimaru.cards.sp.statements.Distraught;
import Shameimaru.cards.sp.statements.Enrage;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class PeerlessWind extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            PeerlessWind.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 15;
    private static final int UPG_DMG = 5;
    private static final int DRAW = 2;
    public PeerlessWind() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { createDummyCardListForAction(); }
    public void createDummyCardListForAction(){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new peerlessWindOption(true);
        optionCards.add(c);
        c = new peerlessWindOption(false);
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){
                cc.upgrade();
            }
        }
        atb(new ChooseOneAction(optionCards));
    }
}