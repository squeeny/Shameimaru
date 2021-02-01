package Shameimaru.cards.com;

import Shameimaru.cards.abs.abs_aya_card;
import Shameimaru.cards.abs.abs_aya_card_dm;
import Shameimaru.cards.sp.foresight.foresightAttack;
import Shameimaru.cards.sp.foresight.foresightPower;
import Shameimaru.cards.sp.foresight.foresightSkill;
import Shameimaru.cards.sp.statements.Distraught;
import Shameimaru.cards.sp.statements.Enrage;
import Shameimaru.util.CardInfo;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;

import static Shameimaru.Shameimaru.makeID;
import static Shameimaru.util.actionShortcuts.*;

public class LoadedStatement extends abs_aya_card_dm {
    private final static CardInfo cardInfo = new CardInfo(
            LoadedStatement.class.getSimpleName(),
            COSTS[1],
            CardType.SKILL,
            CardTarget.ENEMY
    );
    public static final String ID = makeID(cardInfo.cardName);
    private static final int WEAK = 2;
    private static final int UPG_WEAK = 1;
    private static final int ENRAGE = 2;
    private static final int UPG_ENRAGE = 1;
    public LoadedStatement() {
        super(cardInfo, false);
        setMagic(WEAK, UPG_WEAK);
        setAyaMagic(ENRAGE, UPG_ENRAGE);
        this.cardToPreview = createDummyCardListForPreview();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { createDummyCardListForAction(m); }
    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : getAliveMonsters()) {
            if (!isAttackIntent(m.intent)) {
                glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
    public void createDummyCardListForAction(AbstractMonster m){
        // Create unique choiceCardList
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new Distraught(m);
        optionCards.add(c);
        c = new Enrage(m);
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){
                cc.upgrade();
            }
        }
        atb(new ChooseOneAction(optionCards));
    }
    public ArrayList<AbstractCard> createDummyCardListForPreview(){
        ArrayList<AbstractCard> optionCards = new ArrayList<>();
        AbstractCard c = new Distraught();
        optionCards.add(c);
        c = new Enrage();
        optionCards.add(c);
        for(AbstractCard cc: optionCards){
            if(this.upgraded){
                cc.upgrade();
            }
        }
        return optionCards;
    }
}