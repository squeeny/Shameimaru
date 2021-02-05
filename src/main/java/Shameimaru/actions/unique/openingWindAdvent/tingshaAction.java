package Shameimaru.actions.unique.openingWindAdvent;

import Shameimaru.powers.TingshaPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Tingsha;

public class tingshaAction extends AbstractGameAction {
    public tingshaAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public void update() {
        this.isDone = false;
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractRelic r : p.relics) {
            if (r.relicId.equals(Tingsha.ID)) {
                this.isDone = true;
                return;
            }
        }
        AbstractRelic r = new Tingsha();
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new TingshaPower(r)));
        this.isDone = true;
    }
}