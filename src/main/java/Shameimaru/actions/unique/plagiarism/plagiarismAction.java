package Shameimaru.actions.unique.plagiarism;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import static Shameimaru.util.actionShortcuts.att;
import static Shameimaru.util.actionShortcuts.p;

public class plagiarismAction extends AbstractGameAction {

    private boolean freeToPlayOnce;
    private boolean upgraded;
    private int CARD = 1;

    public plagiarismAction(boolean freeToPlayOnce, boolean upgraded) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.upgraded = upgraded;
        this.amount = CARD;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int effect = EnergyPanel.totalCount;
            effect += 1;
            if (p().hasRelic(ChemicalX.ID)) {
                effect += ChemicalX.BOOST;
                p().getRelic(ChemicalX.ID).flash();
            }
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            int finalEffect = effect;
            CardLibrary.getAllCards().stream().filter(c -> addCardToBounds(c, finalEffect)).forEach(c -> tmp.group.add(c.makeCopy()));
            if (tmp.size() < amount) {
                this.isDone = true;
                return;
            }
            tmp.sortAlphabetically(true);
            for(AbstractCard c: tmp.group){ if(upgraded){ c.upgrade(); } }
            AbstractDungeon.gridSelectScreen.open(tmp, amount, "", false);
            tickDuration();
        }
        else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            if (!this.freeToPlayOnce) { p().energy.use(EnergyPanel.totalCount); }
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) { att(new NewQueueCardAction(card, true, false, true)); }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }

    public boolean addCardToBounds(AbstractCard c, int effect){
        if(c.cost <= effect && c.cost >= 0 && (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS) && c.color != AbstractCard.CardColor.COLORLESS) { return true; }
        else { return false; }
    }
}
