package com.quasistellar.rpd.skills.warrior;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Doom;
import com.quasistellar.rpd.actors.buffs.Invisibility;
import com.quasistellar.rpd.actors.mobs.Mimic;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.effects.Speck;
import com.quasistellar.rpd.items.Heap;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Battlecry extends Skill {

    @Override
    public void act() {
        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
                Buff.affect(mob, Doom.class);
            }
        }

        for (Heap heap : Dungeon.level.heaps.values()) {
            if (heap.type == Heap.Type.MIMIC) {
                Mimic m = Mimic.spawnAt( heap.pos, heap.items );
                if (m != null) {
                    heap.destroy();
                }
            }
        }

        GLog.n( Messages.get(this, "cry") );

        Dungeon.hero.sprite.centerEmitter().start( Speck.factory( Speck.SCREAM ), 0.5f, 5 );
        Sample.INSTANCE.play( Assets.SND_CHALLENGE );
        Invisibility.dispel();

        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.spend( 1f );
        Dungeon.hero.busy();
        Dungeon.hero.sprite.operate( Dungeon.hero.pos );
    }

    @Override
    public int manaCost() {
        return 15;
    }

    @Override
    public int icon() {
        return Skill.BATTLECRY;
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
