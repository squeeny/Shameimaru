package Shameimaru.vars;

import Shameimaru.cards.abs.abs_aya_card;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Shameimaru.Shameimaru.makeID;


public class aya_card_magic extends DynamicVariable {

    @Override
    public String key() { return makeID("M"); }
    //TODO: Change this, if you want. It's already modID prefixed, so no worries about conflicts (ASSUMING YOU CHANGED YOUR MODID!)

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof abs_aya_card) {
            return ((abs_aya_card) card).isAYASecondMagicNumberModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof abs_aya_card) {
            return ((abs_aya_card) card).ayaSecondMagicNumber;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof abs_aya_card) {
            ((abs_aya_card) card).isAYASecondMagicNumberModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof abs_aya_card) {
            return ((abs_aya_card) card).ayaBaseSecondMagicNumber;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof abs_aya_card) {
            return ((abs_aya_card) card).upgradedAYASecondMagicNumber;
        }
        return false;
    }
}