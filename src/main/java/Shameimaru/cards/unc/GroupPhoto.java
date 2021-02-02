package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.multiCreatePhotographAction;
import Shameimaru.actions.unique.galeforce.GaleforceAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDef;

public class GroupPhoto extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            GroupPhoto.class.getSimpleName(),
            COSTS[2],
            CardType.SKILL,
            CardTarget.ALL_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 6;
    public GroupPhoto() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDef(block);
        atb(new multiCreatePhotographAction(!upgraded));
    }
}