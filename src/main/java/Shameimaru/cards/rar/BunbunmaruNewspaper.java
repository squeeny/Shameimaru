package Shameimaru.cards.rar;

import Shameimaru.actions.unique.bunbunmaruNewspaper.bunbunmaruNewspaperAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.abs.abs_aya_card_dm;
import Shameimaru.cards.sp.photograph.core.drawCard;
import Shameimaru.cards.sp.photograph.core.powerCard;
import Shameimaru.powers.DeadlineDayPower;
import Shameimaru.util.CardInfo;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doPow;

public class BunbunmaruNewspaper extends abs_aya_card_dm {
    private final static CardInfo cardInfo = new CardInfo(
            BunbunmaruNewspaper.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private ArrayList<AbstractCard> newspaperCards = new ArrayList<>();
    private static final int CARDS = 3;
    private static final int CARDS_NON_PHOTO = 2;
    private static final int COST = 1;
    public BunbunmaruNewspaper() {
        super(cardInfo, false);
        setMagic(CARDS);
        setAyaMagic(CARDS_NON_PHOTO);
        setCostUpgrade(COST);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(newspaperCards.isEmpty()){ atb(new bunbunmaruNewspaperAction(this)); }
        else { for(AbstractCard c: newspaperCards){ c.use(p, m); } }
    }
    public void calculateNewTargets(){
        int enemyC = 0;
        int selfC = 0;
        for(AbstractCard c: newspaperCards){
            if(c.target == CardTarget.ENEMY){ enemyC += 1; }
            else if(c.target == CardTarget.SELF){ selfC += 1; }
        }
        if(selfC > 0 && enemyC > 0){ target = CardTarget.SELF_AND_ENEMY; }
        else if (enemyC > 0){ target = CardTarget.ENEMY; }
        else { target = CardTarget.SELF; }
        cardToPreview = newspaperCards;
        rawDescription = String.format(cardStrings.EXTENDED_DESCRIPTION[0], newspaperCards.get(0).name, newspaperCards.get(1).name, newspaperCards.get(2).name);
        this.initializeDescription();
    }
    public void applyPowers(){
        super.applyPowers();
        for(AbstractCard c: newspaperCards){ c.applyPowers(); }
    }
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        for(AbstractCard c: newspaperCards){ c.calculateCardDamage(mo); }
    }
}