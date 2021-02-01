package Shameimaru.cards.com;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.foresight.foresightAttack;
import Shameimaru.cards.sp.foresight.foresightPower;
import Shameimaru.cards.sp.foresight.foresightSkill;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.getAliveMonsters;

public class Opportunity extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Opportunity.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int VULN = 2;
    private static final int UPG_VULNERABLE = 1;
    private static final int DRAW = 1;

    public Opportunity() {
        super(cardInfo, false);
        setMagic(VULN, UPG_VULNERABLE);
        setAyaMagic(DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        createDummyCardListForAction();
    }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : getAliveMonsters()) {
            if (m.getIntentBaseDmg() >= 0) {
                glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}