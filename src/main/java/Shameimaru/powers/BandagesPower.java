package Shameimaru.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.p;

public class BandagesPower extends AbstractPower {
    public static final String POWER_ID = makeID(BandagesPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractRelic relic;
    public BandagesPower(AbstractRelic relic) {
        name = NAME;
        ID = POWER_ID;
        this.owner = p();
        this.relic = relic;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.loadRegion("focus");
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, relic);
        updateDescription();
    }
    @Override
    public void onVictory() { AbstractDungeon.player.loseRelic(this.relic.relicId); }
    @Override
    public void updateDescription() { description = DESCRIPTIONS[0]; }
}