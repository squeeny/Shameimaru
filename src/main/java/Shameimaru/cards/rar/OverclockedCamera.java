package Shameimaru.cards.rar;

import Shameimaru.actions.unique.plagiarism.plagiarismAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.powers.OverclockedCameraPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Fasting;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.EnergyDownPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doPow;

public class OverclockedCamera extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            OverclockedCamera.class.getSimpleName(),
            COSTS[0],
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int ENERGY = 2;
    public OverclockedCamera() {
        super(cardInfo, false);
        setMagic(ENERGY);
        setRetain(false, true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        doPow(p, new OverclockedCameraPower());
        doPow(p, new EnergyDownPower(p, magicNumber));
    }
}