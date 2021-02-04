package Shameimaru.cards.rar;

import Shameimaru.actions.PhotographicDisposalAction;
import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doPow;

public class IllusoryDominance extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            IllusoryDominance.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int EVASION = 5;
    private static final int UPG_EVASION = 3;
    private static final int STR = 3;
    private static final int UPG_STR = 1;
    private static final int EXHAUSTIVE_USES = 2;
    public IllusoryDominance() {
        super(cardInfo, true);
        setMagic(EVASION, UPG_EVASION);
        setAyaMagic(STR, UPG_STR);
        setExhaust(true, false);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new dynamicGainEvasiveAction(magicNumber));
        doPow(p, new StrengthPower(p, ayaSecondMagicNumber));
    }
    @Override
    public void upgrade(){
        super.upgrade();
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, EXHAUSTIVE_USES);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, EXHAUSTIVE_USES);
        ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
    }
}