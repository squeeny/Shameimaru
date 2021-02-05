package Shameimaru.cards.rar;

import Shameimaru.actions.PhotographicDisposalAction;
import Shameimaru.actions.unique.plagiarism.plagiarismAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

public class Plagiarism extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Plagiarism.class.getSimpleName(),
            COST_X,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    public Plagiarism() {
        super(cardInfo, true);
        purgeOnUse = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new plagiarismAction(freeToPlayOnce, upgraded)); }
}