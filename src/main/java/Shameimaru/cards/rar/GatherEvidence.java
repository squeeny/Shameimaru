package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.powers.GatherEvidencePower;
import Shameimaru.powers.OverclockedCameraPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.EnergyDownPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doPow;

public class GatherEvidence extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            GatherEvidence.class.getSimpleName(),
            COSTS[2],
            CardType.POWER,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public GatherEvidence() {
        super(cardInfo, false);
        setInnate(false, true);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doPow(p, new GatherEvidencePower()); }
}