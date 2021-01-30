package Shameimaru.cards.sp.photograph.blacklist;

import com.megacrit.cardcrawl.powers.HexPower;

public class blacklistedPowers {

    public static boolean notBlacklistedPower(String pID){
        if(pID.equals(HexPower.POWER_ID)){ return false; }
        return true;
    }
}
