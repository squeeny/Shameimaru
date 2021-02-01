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
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

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
        setRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDraw(DRAW);
        if(!isAttackIntent(m.intent)){ doPow(m, new VulnerablePower(m, magicNumber, false)); }
    }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : getAliveMonsters()) {
            if (!isAttackIntent(m.intent)) {
                glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}