package Shameimaru.cards.sp;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.sp.core.powerCard;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class Photograph extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Photograph.class.getSimpleName(),
            COST_UNPLAYABLE,
            CardType.SKILL,
            CardTarget.SELF_AND_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int DMG = 6;
    private static final int UPG_DMG = 3;
    private static final int ENERGY = 2;
    private boolean attacking;
    private boolean blocking;

    private ArrayList<AbstractCard> card = new ArrayList<>();
    public Photograph(ArrayList<AbstractCard> cards) {
        super(cardInfo, false);
        card = cards;
        for(AbstractCard c: cards){
            if(c.baseDamage > 0){ attacking = true; }
            if(c.baseBlock > 0){ blocking = true; }
        }
        if(attacking){
            if(blocking){ target = CardTarget.SELF_AND_ENEMY; }
            else { target = CardTarget.ENEMY; }
        }
        else { target = CardTarget.SELF; }
    }
    public Photograph() {
        super(cardInfo, false);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for(AbstractCard c: card){
            c.use(p, m);
        }
    }
    public void applyPowers(){
        super.applyPowers();
        String desc = cardStrings.DESCRIPTION;
        for(AbstractCard c: card){
            c.applyPowers();
            String x;
            if(c instanceof powerCard){
                AbstractPower p = ReflectionHacks.getPrivate(c, powerCard.class, "power");
                x = String.format(c.rawDescription, p.name);
            }
            else { x = c.rawDescription; }
            if (x.contains("!D!")) { x = x.replaceAll("!D!", getDynamicValue(c, 'D')); }
            if (x.contains("!B!")) { x = x.replaceAll("!B!", getDynamicValue(c, 'B')); }
            if (x.contains("!M!")) { x = x.replaceAll("!M!", getDynamicValue(c, 'M')); }
            desc += x;
        }
        rawDescription = desc;
        initializeDescription();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        String desc = cardStrings.DESCRIPTION;
        for(AbstractCard c: card){
            c.calculateCardDamage(mo);
            String x;
            if(c instanceof powerCard){
                AbstractPower p = ReflectionHacks.getPrivate(c, powerCard.class, "power");
                x = String.format(c.rawDescription, p.name);
            }
            else { x = c.rawDescription; }
            if (x.contains("!D!")) { x = x.replaceAll("!D!", getDynamicValue(c, 'D')); }
            if (x.contains("!B!")) { x = x.replaceAll("!B!", getDynamicValue(c, 'B')); }
            if (x.contains("!M!")) { x = x.replaceAll("!M!", getDynamicValue(c, 'M')); }
            desc += x;
        }
        rawDescription = desc;
        initializeDescription();
    }
    public AbstractCard makeCopy(){ return new Photograph(card); }
    private static String getDynamicValue(AbstractCard card, char key) {
        switch (key) {
            case 'B':
                if (card.isBlockModified) {
                    if (card.block >= card.baseBlock) {
                        return "[#7fff00]" + card.block + "[]";
                    }
                    return "[#ff6563]" + card.block + "[]";
                }
                return Integer.toString(card.baseBlock);
            case 'D':
                if (card.isDamageModified) {
                    if (card.damage >= card.baseDamage) {
                        return "[#7fff00]" + card.damage + "[]";
                    }
                    return "[#ff6563]" + card.damage + "[]";
                }
                return Integer.toString(card.baseDamage);
            case 'M':
                if (card.isMagicNumberModified) {
                    if (card.magicNumber >= card.baseMagicNumber) {
                        return "[#7fff00]" + card.magicNumber + "[]";
                    }

                    return "[#ff6563]" + card.magicNumber + "[]";
                }
                return Integer.toString(card.baseMagicNumber);
            default:
                return Integer.toString(-99);
        }
    }
}