package com.quasistellar.rpd.skills.necromancer;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Healing;
import com.quasistellar.rpd.actors.mobs.DeathlingMob;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;

public class Revitalization extends Skill {

    @Override
    public void act() {
        for (Mob mob : Dungeon.level.mobs){
            if (mob instanceof DeathlingMob) {
                Buff.affect( mob, Healing.class ).setHeal((int)(0.5f*mob.HT + 30), 0.25f, 0);
            }
        }
        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.spendAndNext(1f);
    }

    @Override
    public int manaCost() { return 10; }

    @Override
    public boolean visible() {
        for (Mob mob : Dungeon.level.mobs){
            if (mob instanceof DeathlingMob) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int icon() {
        return Skill.REVITALIZATION;
    }

    @Override
    public String toString() {
        return Messages.get(this, "name");
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc");
    }

}
