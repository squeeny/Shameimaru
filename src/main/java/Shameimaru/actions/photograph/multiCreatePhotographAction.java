package Shameimaru.actions.photograph;

import Shameimaru.cards.sp.photograph.Photograph;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.util.actionShortcuts.*;

public class multiCreatePhotographAction extends AbstractGameAction {

    private boolean upgraded;
    public multiCreatePhotographAction(){ this(false); }
    public multiCreatePhotographAction(boolean upg) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        upgraded = upg;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for(AbstractMonster m: getAliveMonsters()){ att(new createPhotographAction(m, upgraded)); }
            this.isDone = true;
        }
    }
}