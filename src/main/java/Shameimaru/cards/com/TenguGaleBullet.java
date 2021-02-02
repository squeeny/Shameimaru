package Shameimaru.cards.com;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class TenguGaleBullet extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            TenguGaleBullet.class.getSimpleName(),
            COSTS[1],
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 8;
    private static final int UPG_DMG = 3;
    public TenguGaleBullet() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { doAllDmg(this.damage, AbstractGameAction.AttackEffect.NONE, false); }
    public void triggerOnManualDiscard(){ doAllDmg(this.damage, AbstractGameAction.AttackEffect.NONE, false); }
}