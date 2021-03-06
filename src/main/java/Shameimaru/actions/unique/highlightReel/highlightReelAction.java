package Shameimaru.actions.unique.highlightReel;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;

import static Shameimaru.util.actionShortcuts.att;
import static Shameimaru.util.actionShortcuts.p;

public class highlightReelAction extends AbstractGameAction {
    public highlightReelAction(int cards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = cards;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard c: p().drawPile.group) { tmp.addToTop(c); }
            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }
            tmp.sortAlphabetically(true);
            tmp.sortByRarityPlusStatusCardType(false);
            AbstractDungeon.gridSelectScreen.open(tmp, Math.min(tmp.size(), amount), "", false);
            tickDuration();
        }

        else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            ArrayList<AbstractCard> cards = AbstractDungeon.gridSelectScreen.selectedCards;
            Collections.reverse(cards);
            for (AbstractCard card : cards) {
                p().drawPile.group.remove(card);
                (AbstractDungeon.getCurrRoom()).souls.remove(card);
                att(new NewQueueCardAction(card, true, false, true));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}