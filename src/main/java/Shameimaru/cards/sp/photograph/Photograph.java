package Shameimaru.cards.sp.photograph;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.enums.CardENUMs;
import Shameimaru.enums.PhotoModes;
import Shameimaru.powers.WidespreadPropagandaPower;
import Shameimaru.util.CardInfo;
import basemod.AutoAdd;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

@AutoAdd.Ignore
public class Photograph extends abs_aya_card {
    private final static CardInfo cardInfo = new CardInfo(
            Photograph.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.SELF_AND_ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private PhotoModes currentMode;
    private static final int UPG_AMOUNTS = 4;
    private static final int EMERGENCY_DRAW = 1;
    public Photograph(PhotoModes mode, int d, int dt, int b, int bt) {
        super(cardInfo, false);
        currentMode = mode;
        switch (currentMode){
            case ATTACK_BLOCK:
            case ATTACK:
                setDamage(d, UPG_AMOUNTS);
                setMagic(dt);
                target = CardTarget.ENEMY;
                if(currentMode.equals(PhotoModes.ATTACK)){ break; }
            case BLOCK:
                setBlock(b, UPG_AMOUNTS);
                setAyaMagic(bt);
                target = CardTarget.SELF;
                break;
            case DRAW:
                setMagic(EMERGENCY_DRAW, EMERGENCY_DRAW);
                target = CardTarget.SELF;
                break;
        }
        setRetain(true);
        setExhaust(true);
        this.tags.add(CardENUMs.PHOTOGRAPH);
        initDesc();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        switch (currentMode){
            case ATTACK_BLOCK:
            case ATTACK:
                for(int i = 0; i < magicNumber; i+=1){
                    if(p.hasPower(WidespreadPropagandaPower.POWER_ID)){ doAllDmg(damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL, false); }
                    else { doDmg(m, damage); }
                }
                if(currentMode.equals(PhotoModes.ATTACK)){ break; }
            case BLOCK:
                for(int i = 0; i < ayaSecondMagicNumber; i+=1){ doDef(block); }
                break;
            case DRAW:
                doDraw(magicNumber);
                target = CardTarget.SELF;
                break;
        }

    }
    public void applyPowers(){
        super.applyPowers();
        initDesc();
    }
    public AbstractCard makeCopy(){
        // hopefully this never gets called in a useful place in game.
        return new Photograph(currentMode, damage, magicNumber, block, ayaSecondMagicNumber);
    }
    public void doubleAllValues(){
        baseDamage *= 2;
        baseBlock *= 2;
        baseMagicNumber *= 2;
        ayaBaseSecondMagicNumber *= 2;
    }
    public void initDesc(){
        String desc = cardStrings.DESCRIPTION;
        switch (currentMode){
            case ATTACK_BLOCK:
            case ATTACK:
                if(p().hasPower(WidespreadPropagandaPower.POWER_ID)){ desc += magicNumber >= 2 ? cardStrings.EXTENDED_DESCRIPTION[3] : cardStrings.EXTENDED_DESCRIPTION[2]; }
                else { desc += magicNumber >= 2 ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0]; }
                if(currentMode.equals(PhotoModes.ATTACK)){ break; }
            case BLOCK:
                desc += ayaSecondMagicNumber >= 2 ? cardStrings.EXTENDED_DESCRIPTION[5] : cardStrings.EXTENDED_DESCRIPTION[4];
                break;
            case DRAW:
                desc += magicNumber >= 2 ? cardStrings.EXTENDED_DESCRIPTION[7] : cardStrings.EXTENDED_DESCRIPTION[6];
                break;
        }
        rawDescription = desc;
        initializeDescription();
    }
}