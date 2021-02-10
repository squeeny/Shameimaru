package Shameimaru.cards.sp.massDeletion;

import Shameimaru.actions.unique.massDeletion.massDeletionAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.actions.unique.massDeletion.massDeletionAction.cardMODE.BLOCK;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class massDeletionOption extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            massDeletionOption.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private AbstractCard parentCard;
    private massDeletionAction.cardMODE mode;
    private final int DRAW = 2;
    public massDeletionOption(AbstractCard parentCard, massDeletionAction.cardMODE option) {
        super(cardInfo, false);
        mode = option;
        this.parentCard = parentCard;
        setDamage(parentCard.damage);
        setBlock(parentCard.block);
        if(mode == BLOCK){ this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
        this.initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        if(mode == BLOCK){ atb(new massDeletionAction(parentCard,false)); }
        else { atb(new massDeletionAction(parentCard, true)); }
    }
}