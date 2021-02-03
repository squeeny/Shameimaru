package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class InstantShotJournalist extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            InstantShotJournalist.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private int DRAW_D = 2;
    private float cooldownF = 1F;
    private boolean coolDownReady = true;
    public InstantShotJournalist() {
        super(cardInfo, true);
        setMagic(DRAW_D);
        setInnate(true);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }
    @Override
    public void triggerWhenDrawn(){
        doDraw(magicNumber);
        atb(new ExhaustSpecificCardAction(this, p().hand));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractMonster m: getAliveMonsters()){
                    m.createIntent();
                }
                att(new multiCreatePhotographAction(upgraded));
                this.isDone = true;
            }
        });
    }
}