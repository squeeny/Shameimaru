package Shameimaru.relics.starter;

import Shameimaru.actions.evasive.dynamicGainEvasiveAction;
import Shameimaru.relics.AbstractAyaRelic;
import Shameimaru.util.RelicInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.p;

public class blackbirdwings extends AbstractAyaRelic {
    private static final RelicInfo relicInfo = new RelicInfo(
            blackbirdfeather.class.getSimpleName(),
            RelicTier.BOSS,
            LandingSound.MAGICAL
    );
    public static final String ID = makeID(relicInfo.relicName);
    private static int EVASION_PER_DRAW = 1;
    public blackbirdwings() {
        super(ID, relicInfo);
        fixDescription();
    }
    @Override
    public String getUpdatedDescription() { return String.format(DESCRIPTIONS[0], EVASION_PER_DRAW); }
    @Override
    public void onCardDraw(AbstractCard drawnCard) { atb(new dynamicGainEvasiveAction(EVASION_PER_DRAW)); }
    public void onManualDiscard() { atb(new dynamicGainEvasiveAction(EVASION_PER_DRAW)); }
    @Override
    public void obtain() {
        if (p().hasRelic(blackbirdfeather.ID)) {
            for (int i = 0; i < p().relics.size(); ++i) {
                if (p().relics.get(i).relicId.equals(blackbirdfeather.ID)) {
                    instantObtain(p(), i, true);
                    break;
                }
            }
        } else { super.obtain(); }
    }
    @Override
    public boolean canSpawn() { return p() != null && p().hasRelic(blackbirdfeather.ID); }
}