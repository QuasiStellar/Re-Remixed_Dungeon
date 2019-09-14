package com.quasistellar.rpd.skills.necromancer;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Necromancy;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;

public class Plunder extends Skill {

    @Override
    public void act() {
        Buff.affect(Dungeon.hero, Necromancy.class, 20f);
        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.spendAndNext(1f);
    }

    @Override
    public int manaCost() { return 10; }

    @Override
    public int icon() {
        return Skill.PLUNDER;
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
