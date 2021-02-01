package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.foresight.foresightAttack;
import Shameimaru.cards.sp.foresight.foresightPower;
import Shameimaru.cards.sp.foresight.foresightSkill;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class Foresight extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Foresight.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 1;
    private static final int CORRECT_BLOCK = 5;
    private static final int UPG_CORRECT_BLOCK = 3;
    private static final int INCORRECT_BLOCK = 3;
    private static final int UPG_INCORRECT_BLOCK = 2;
    public Foresight() {
        super(cardInfo, false);
        setBlock(CORRECT_BLOCK, UPG_CORRECT_BLOCK);
        setMagic(INCORRECT_BLOCK, UPG_INCORRECT_BLOCK);
        setAyaMagic(DRAW);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        createDummyCardListForAction();
    }
    @Override
    public void applyPowers()
    {
        magicNumber = baseMagicNumber;
        int tmp = baseBlock;
        baseBlock = baseMagicNumber;
        super.applyPowers();
        magicNumber = block;
        baseBlock = tmp;
        super.applyPowers();
        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }
    public void createDummyCardListForAction(){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new foresightAttack(this);
        optionCards.add(c);
        c = new foresightSkill(this);
        optionCards.add(c);
        c = new foresightPower(this);
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){ cc.upgrade(); }
        }
        atb(new ChooseOneAction(optionCards));
    }
}