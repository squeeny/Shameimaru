package Shameimaru.cards.sp.reports;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doPow;
import static Shameimaru.util.actionShortcuts.getAliveMonsters;

@AutoAdd.Ignore
public class FlatteringReport extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            FlatteringReport.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int STR_GAIN = 3;
    private static final int UPG_STR_GAIN = 2;
    private static AbstractCard parentCard;
    public FlatteringReport() {
        super(cardInfo, false);
        setMagic(STR_GAIN, UPG_STR_GAIN);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        for(AbstractMonster m: getAliveMonsters()){
            doPow(m, new StrengthPower(m, magicNumber));
            if (!m.hasPower(ArtifactPower.POWER_ID)) { doPow(m, new GainStrengthPower(m, -magicNumber)); }
        }
    }
}