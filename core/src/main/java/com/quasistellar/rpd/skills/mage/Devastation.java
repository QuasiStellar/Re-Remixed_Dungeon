package com.quasistellar.rpd.skills.mage;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
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
import com.quasistellar.rpd.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Devastation extends Skill {

    @Override
    public void act() {
        GameScene.flash( 0xFFFFFF );
        Sample.INSTANCE.play( Assets.SND_BLAST );
        Invisibility.dispel();

        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (Dungeon.level.heroFOV[mob.pos]) {
                if (!mob.properties().contains(Char.Property.BOSS) &&
                    !mob.properties().contains(Char.Property.MINIBOSS)) mob.damage(Math.round(mob.HT/2f + mob.HP/2f), this);
                else mob.damage(Math.round(mob.HT/5f + mob.HP/5f), this);
                if (mob.isAlive()) {
                    Buff.prolong(mob, Blindness.class, 10);
                }
            }
        }

        Buff.prolong(Dungeon.hero, Weakness.class, 50);

        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.spend( 1f );
        Dungeon.hero.busy();
        ((HeroSprite)Dungeon.hero.sprite).read();

    }

    @Override
    public int manaCost() { return 100; }

    @Override
    public int icon() {
        return Skill.DEVASTATION;
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
