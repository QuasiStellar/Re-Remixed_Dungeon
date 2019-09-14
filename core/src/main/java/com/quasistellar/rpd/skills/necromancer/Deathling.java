package com.quasistellar.rpd.skills.necromancer;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.mobs.DeathlingMob;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Deathling extends Skill {

    @Override
    public void act() {
        ArrayList<Mob> existingDeathlings = new ArrayList<Mob>();

        for (Mob mob : Dungeon.level.mobs){
            if (mob instanceof DeathlingMob) {
                existingDeathlings.add( mob );
            }
        }

        if (existingDeathlings.size() >= 3) {
            GLog.w( Messages.get(this, "three") );
            return;
        }

        ArrayList<Integer> respawnPoints = new ArrayList<Integer>();

        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            int p = Dungeon.hero.pos + PathFinder.NEIGHBOURS8[i];
            if (Actor.findChar( p ) == null && Dungeon.level.passable[p]) {
                respawnPoints.add( p );
            }
        }

        if (respawnPoints.size() > 0) {
            int index = Random.index( respawnPoints );
            DeathlingMob.spawnAt(respawnPoints.get(index));
            Dungeon.hero.earnMana(-manaCost());
            Dungeon.hero.spend(1f);
            Dungeon.hero.busy();
            Dungeon.hero.sprite.operate(Dungeon.hero.pos);
        } else {
            GLog.w( Messages.get(this, "no_space") );
        }
    }

    @Override
    public int manaCost() {
        return 5;
    }

    @Override
    public int icon() {
        return Skill.DEATHLING;
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
