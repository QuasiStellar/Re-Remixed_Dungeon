package com.quasistellar.rpd.skills.warrior;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.utils.GLog;

public class Overcoming extends Skill {

    @Override
    public void act() {
        Hero hero = Dungeon.hero;
        if (hero.HP > 5) {
            hero.HP -= 5;
            hero.earnMana(1);
            hero.spend(1);
            hero.busy();
            hero.sprite.operate( hero.pos );
        } else {
            GLog.w( Messages.get(Overcoming.class, "lowhp") );
        }
    }

    @Override
    public int manaCost() { return 0; }

    @Override
    public boolean visible() {
        return Dungeon.hero.HP > 5;
    }

    @Override
    public int icon() {
        return Skill.OVERCOMING;
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
