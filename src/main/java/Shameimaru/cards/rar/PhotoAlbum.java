package Shameimaru.cards.rar;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.photograph.Photograph;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.doAllDmg;
import static Shameimaru.util.actionShortcuts.doDmg;

public class PhotoAlbum extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            PhotoAlbum.class.getSimpleName(),
            COSTS[2],
            AbstractCard.CardType.ATTACK,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 7;
    private static final int UPG_DMG = 3;
    private static final int PHOTO_DMG = 7;
    private static final int UPG_PHOTO_DMG = 3;
    private static final int HITS = 2;

    public PhotoAlbum() {
        super(cardInfo, false);
        setDamage(DMG, UPG_DMG);
        setMagic(PHOTO_DMG, UPG_PHOTO_DMG);
        setAyaMagic(HITS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < ayaSecondMagicNumber; i += 1) { doDmg(m, damage); }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countExtraDMG();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countExtraDMG();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public static int countExtraDMG(){
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof Photograph) { count++; }
        }
        return count;
    }
}