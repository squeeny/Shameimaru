package Shameimaru.relics;

import Shameimaru.Shameimaru;
import Shameimaru.chr.chr_aya;

import static Shameimaru.Shameimaru.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, chr_aya.Enums.AYA_COLOUR);
    }
}
