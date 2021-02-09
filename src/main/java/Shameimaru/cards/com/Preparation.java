package Shameimaru.cards.com;

import Shameimaru.actions.DiscardAnyAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.foresight.foresightAttack;
import Shameimaru.cards.sp.foresight.foresightPower;
import Shameimaru.cards.sp.foresight.foresightSkill;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class Preparation extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Preparation.class.getSimpleName(),
            COSTS[0],
            CardType.SKILL,
            CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DRAW = 2;
    private static final int DISCARD = 2;
    public Preparation() {
        super(cardInfo, true);
        setMagic(DRAW);
        setAyaMagic(DISCARD);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        doDraw(magicNumber);
        if(upgraded){ atb(new DiscardAnyAction(ayaSecondMagicNumber)); }
        else { atb(new DiscardAction(p(), p(), ayaSecondMagicNumber, false)); }
    }
}