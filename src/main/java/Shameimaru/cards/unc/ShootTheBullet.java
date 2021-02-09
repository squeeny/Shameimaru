package Shameimaru.cards.unc;

import Shameimaru.actions.unique.galeforce.GaleforceAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.powers.ShootTheBulletPower;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doPow;

public class ShootTheBullet extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            ShootTheBullet.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    public ShootTheBullet() {
        super(cardInfo, true);
        setRetain(false, true);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doPow(p, new ShootTheBulletPower(p, m, this.upgraded)); }
}