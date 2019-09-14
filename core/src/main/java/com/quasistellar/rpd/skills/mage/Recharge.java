package com.quasistellar.rpd.skills.mage;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Recharging;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;

public class Recharge extends Skill {

    @Override
    public void act() {
        Buff.affect( Dungeon.hero, Recharging.class, 4f );
        Dungeon.hero.earnMana(-manaCost());
        Dungeon.hero.spendAndNext(1f);
    }

    @Override
    public int manaCost() { return 20; }

    @Override
    public int icon() {
        return Skill.RECHARGE;
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
