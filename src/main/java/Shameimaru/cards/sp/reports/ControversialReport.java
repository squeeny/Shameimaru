package Shameimaru.cards.sp.reports;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class ControversialReport extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            ControversialReport.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int STR_GAIN = 6;
    private static final int UPG_STR_GAIN = 2;
    public ControversialReport() {
        super(cardInfo, false);
        setMagic(STR_GAIN, UPG_STR_GAIN);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        for(AbstractMonster m: getAliveMonsters()){ doPow(m, new StrengthPower(m, magicNumber)); }
    }
}