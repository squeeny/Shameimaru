package Shameimaru.cards.unc;

import Shameimaru.actions.unique.exclusiveScoop.exclusiveScoopAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDmg;

public class ExclusiveScoop extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            ExclusiveScoop.class.getSimpleName(),
            COSTS[1],
            CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int BASE = 0;
    public ExclusiveScoop() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(BASE);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new exclusiveScoopAction(m, damage)); }
    public void applyPowers() {
        super.applyPowers();
        magicNumber = baseMagicNumber;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof Photograph) { magicNumber++; }
        }
        isMagicNumberModified = magicNumber != baseMagicNumber;
        this.rawDescription = cardStrings.DESCRIPTION;
        if(magicNumber > 0){ this.rawDescription += magicNumber == 1 ? cardStrings.EXTENDED_DESCRIPTION[0] : cardStrings.EXTENDED_DESCRIPTION[1]; }
        initializeDescription();
    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}