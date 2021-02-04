package Shameimaru.relics.starter;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import Shameimaru.relics.AbstractAyaRelic;
import Shameimaru.util.RelicInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

public class blackbirdfeather extends AbstractAyaRelic {
    private static final RelicInfo relicInfo = new RelicInfo(
            blackbirdfeather.class.getSimpleName(),
            RelicTier.STARTER,
            LandingSound.MAGICAL
    );
    public static final String ID = makeID(relicInfo.relicName);
    private static int EVASION_PER_DRAW = 1;
    public blackbirdfeather() {
        super(ID, relicInfo);
        fixDescription();
    }
    @Override
    public String getUpdatedDescription() { return String.format(DESCRIPTIONS[0], EVASION_PER_DRAW); }
    @Override
    public void onCardDraw(AbstractCard drawnCard) { atb(new dynamicGainEvasiveAction(EVASION_PER_DRAW)); }
}
