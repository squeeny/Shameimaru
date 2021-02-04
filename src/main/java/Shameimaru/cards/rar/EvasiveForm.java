package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.DeadlineDayPower;
import Shameimaru.powers.EvasiveFormPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doPow;

public class EvasiveForm extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            EvasiveForm.class.getSimpleName(),
            COSTS[2],
            CardType.POWER,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int EVASION = 1;
    private static final int EVASION_UPG = 1;
    public EvasiveForm() {
        super(cardInfo, false);
        setInnate(false, true);
        setMagic(EVASION, EVASION_UPG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doPow(p, new EvasiveFormPower(magicNumber)); }
}