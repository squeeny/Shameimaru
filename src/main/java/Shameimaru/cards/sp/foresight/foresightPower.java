package Shameimaru.cards.sp.foresight;

import Shameimaru.actions.unique.foresight.foresightCardAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

@AutoAdd.Ignore
public class foresightPower extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            foresightPower.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private AbstractCard foresightMaster;
    private final int DRAW = 1;
    public foresightPower(AbstractCard parentCard) {
        super(cardInfo, false);
        foresightMaster = parentCard;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        atb(new DrawCardAction(DRAW, new foresightCardAction(this, foresightMaster.block, foresightMaster.magicNumber)));
    }
}