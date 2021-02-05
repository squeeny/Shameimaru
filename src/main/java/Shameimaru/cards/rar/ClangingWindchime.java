package Shameimaru.cards.rar;

import Shameimaru.actions.unique.plagiarism.plagiarismAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.OmniscienceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;
import static Shameimaru.util.actionShortcuts.doDraw;

public class ClangingWindchime extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            ClangingWindchime.class.getSimpleName(),
            COST_UNPLAYABLE,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardTarget.SELF
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;
    private static final int DRAW = 2;
    public ClangingWindchime() {
        super(cardInfo, true);
        setMagic(DRAW);
        setAyaMagic(ENERGY, UPG_ENERGY);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { atb(new plagiarismAction(freeToPlayOnce, upgraded)); }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }
    @Override
    public void triggerOnManualDiscard(){
        atb(new GainEnergyAction(ayaSecondMagicNumber));
        doDraw(magicNumber);
    }
}