package com.quasistellar.rpd.skills.gnoll;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Charm;
import com.quasistellar.rpd.actors.buffs.Invisibility;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.effects.Speck;
import com.quasistellar.rpd.items.armor.Armor;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.sprites.HeroSprite;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class Disguise extends Skill {

    @Override
    public void act() {
        Dungeon.hero.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 5 );
        Sample.INSTANCE.play( Assets.SND_CHARMS );
        Invisibility.dispel();

        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (Dungeon.level.heroFOV[mob.pos]) {
                Buff.affect( mob, Charm.class, 20f ).object = Dungeon.hero.id();
                mob.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f, 5 );
            }
        }

        Armor armor = Dungeon.hero.belongings.armor;

        if (armor != null && !armor.cursed) {
            Dungeon.hero.belongings.armor = null;
            Dungeon.quickslot.convertToPlaceholder(armor);
            armor.updateQuickslot();
            Dungeon.level.drop(armor, Dungeon.hero.pos).sprite.drop();
            ((HeroSprite)Dungeon.hero.sprite).updateArmor();
        }

        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.spendAndNext(1f);
    }

    @Override
    public int manaCost() { return 15; }

    @Override
    public int icon() {
        return Skill.DISGUISE;
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
