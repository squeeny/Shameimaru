package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.com.Imitation;
import Shameimaru.enums.CardENUMs;
import Shameimaru.util.CardInfo;
import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
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
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public InstantShotJournalist() {
        super(cardInfo, false);
        setInnate(false, true);
        setExhaust(true);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if(p.drawPile.isEmpty()){
                    isDone = true;
                    return;
                }
                else {
                    CardGroup drawPileCopy = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for(AbstractCard c: p.drawPile.group){ drawPileCopy.group.add(c); }
                    for(AbstractCard c: drawPileCopy.group){
                        if(c.hasTag(CardENUMs.SNAPSHOT)){
                            c.applyPowers();
                            att(new NewQueueCardAction(c, true, false, true));
                            this.isDone = true;
                            return;
                        }
                        else {
                            p.drawPile.moveToDiscardPile(c);
                            c.triggerOnManualDiscard();
                            GameActionManager.incrementDiscard(false);
                        }
                    }
                }
                this.isDone = true;
            }
        });
    }
}