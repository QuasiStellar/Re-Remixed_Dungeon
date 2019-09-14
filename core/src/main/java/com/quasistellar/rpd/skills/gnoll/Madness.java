package com.quasistellar.rpd.skills.gnoll;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.Blindness;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Invisibility;
import com.quasistellar.rpd.actors.buffs.Weakness;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.sprites.HeroSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class Madness extends Skill {

    @Override
    public void act() {
        Sample.INSTANCE.play( Assets.SND_HIT );
        Invisibility.dispel();

        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            int p = Dungeon.hero.pos + PathFinder.NEIGHBOURS8[i];
            Char ch = Actor.findChar( p );
            if (ch != null) {
                Dungeon.hero.attack(ch);
            }
        }

        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.busy();
        ((HeroSprite)Dungeon.hero.sprite).attack(Dungeon.hero.pos);

    }

    @Override
    public int manaCost() { return 5; }

    @Override
    public boolean visible() {
        int v = Dungeon.hero.visibleEnemies();
        for (int i=0; i < v; i++) {
            Mob mob = Dungeon.hero.visibleEnemy( i );
            if ( Dungeon.level.adjacent(Dungeon.hero.pos, mob.pos) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int icon() {
        return Skill.MADNESS;
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
