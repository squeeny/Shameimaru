package Shameimaru.cards.sp.peerlessWind;

import Shameimaru.actions.unique.foresight.foresightCardAction;
import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.rar.OpeningWindAdvent;
import Shameimaru.cards.unc.PeerlessWind;
import Shameimaru.util.CardInfo;
import Shameimaru.util.TexLoader;
import basemod.AutoAdd;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class peerlessWindOption extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            peerlessWindOption.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private boolean optionChosen;
    private final int DRAW = 2;
    public peerlessWindOption(boolean option) {
        super(cardInfo, false);
        optionChosen = option;
        setMagic(DRAW);
        if(optionChosen){ this.rawDescription = cardStrings.UPGRADE_DESCRIPTION; }
        this.initializeDescription();
        img = TexLoader.getAndLoadCardTextureString(PeerlessWind.class.getSimpleName());
        this.textureImg = img;
        loadCardImage(textureImg);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    @Override
    public void onChoseThisOption(){
        if(optionChosen){ doDraw(magicNumber); }
        else { doPow(p(), new DrawCardNextTurnPower(p(), magicNumber)); }
    }
}