package Shameimaru.cards.sp.photograph.blacklist;

import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.HexPower;

public class blacklistedPowers {

    public static boolean notBlacklistedPower(String pID){
        switch (pID){
            case HexPower.POWER_ID:
            case FrailPower.POWER_ID:
                return false;
        }
        return true;
    }
}
