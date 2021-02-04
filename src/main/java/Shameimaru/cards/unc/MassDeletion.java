package Shameimaru.cards.unc;

import Shameimaru.actions.unique.massDeletion.massDeletionAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.reports.ControversialReport;
import Shameimaru.cards.sp.massDeletion.massDeletionOption;
import Shameimaru.cards.sp.reports.FlatteringReport;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

public class MassDeletion extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            MassDeletion.class.getSimpleName(),
            COSTS[2],
            CardType.ATTACK,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    public MassDeletion() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        createDummyCardListForAction();
    }
    public void createDummyCardListForAction(){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new massDeletionOption(this, massDeletionAction.cardMODE.DAMAGE);
        optionCards.add(c);
        c = new massDeletionOption(this, massDeletionAction.cardMODE.BLOCK);
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){ cc.upgrade(); }
        }
        atb(new ChooseOneAction(optionCards));
    }
}