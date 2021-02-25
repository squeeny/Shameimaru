package Shameimaru.cards.sp.openingWindAdvent;

import Shameimaru.actions.unique.openingWindAdvent.bandagesAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.rar.OpeningWindAdvent;
import Shameimaru.util.CardInfo;
import Shameimaru.util.TexLoader;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.atb;

@AutoAdd.Ignore
public class bandagesOption extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            bandagesOption.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.POWER,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private final int BLOCK = 3;
    public bandagesOption() {
        super(cardInfo, false);
        setMagic(BLOCK);
        img = TexLoader.getAndLoadCardTextureString(OpeningWindAdvent.class.getSimpleName());
        this.textureImg = img;
        loadCardImage(textureImg);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){ atb(new bandagesAction()); }
}