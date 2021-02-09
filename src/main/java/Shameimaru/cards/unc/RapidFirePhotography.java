package Shameimaru.cards.unc;

import Shameimaru.actions.photograph.createPhotographAction;
import Shameimaru.actions.photograph.duplicatePhotographAction;
import Shameimaru.actions.unique.rapidFirePhotography.rapidFirePhotographyAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class RapidFirePhotography extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            RapidFirePhotography.class.getSimpleName(),
            COST_X,
            AbstractCard.CardType.SKILL,
            CardTarget.ENEMY
    );
    private static final int BASE_MAGIC = 0;
    private static final int UPG_MAGIC = 1;
    public RapidFirePhotography() {
        super(cardInfo, true);
        setMagic(BASE_MAGIC, UPG_MAGIC);
        tags.add(CardENUMs.SNAPSHOT);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new rapidFirePhotographyAction(this, (effect, params) -> {
            for (int i = 0; i < effect + params[0]; i++) { att(new createPhotographAction(m, this.upgraded)); }
            return true;
        }, magicNumber));
    }
}