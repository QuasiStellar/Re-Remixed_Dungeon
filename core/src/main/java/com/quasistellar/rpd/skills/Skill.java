package com.quasistellar.rpd.skills;

import com.quasistellar.rpd.Dungeon;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public class Skill implements Bundlable {

    public static final int OVERCOMING = 0;
    public static final int HEADBUTT = 1;
    public static final int BATTLECRY = 2;
    public static final int RECHARGE = 3;
    public static final int FLOCK = 4;
    public static final int DEVASTATION = 5;
    public static final int THEFT = 6;
    public static final int BYPASS = 7;
    public static final int ILLUSION = 8;
    public static final int PIERCING = 9;
    public static final int FLOWERFIRE = 10;
    public static final int BLAST = 11;
//    public static final int ... = 12;
//    public static final int ... = 13;
//    public static final int ... = 14;
    public static final int DEATHLING = 15;
    public static final int PLUNDER = 16;
    public static final int REVITALIZATION = 17;
    public static final int MADNESS = 18;
    public static final int DECOY = 19;
    public static final int DISGUISE = 20;

    public static final int NONE = 23;

    public void act() {
        Dungeon.hero.spend(1);
        Dungeon.hero.busy();
        Dungeon.hero.sprite.operate( Dungeon.hero.pos );
    }

    public int manaCost() {
        return 1;
    }

    public boolean visible() {
        return true;
    }

    public int icon() {
        return NONE;
    }

    public String desc(){
        return "";
    }

    @Override
    public void storeInBundle( Bundle bundle ) {
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
    }

}
