package com.quasistellar.rpd.skills.rogue;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.mobs.npcs.MirrorImage;
import com.quasistellar.rpd.items.scrolls.ScrollOfTeleportation;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.utils.GLog;

public class Illusion extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(illusion);
    }

    private CellSelector.Listener illusion = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            final Char mob = Actor.findChar( cell );
            if (mob != null
                    || !Dungeon.level.heroFOV[cell]
                    || !Dungeon.level.adjacent(Dungeon.hero.pos, cell)
                    || !(Dungeon.level.passable[cell] || Dungeon.level.avoid[cell])){
                GLog.w( Messages.get(Bypass.class, "bad_target") );
            } else {
                Dungeon.hero.busy();
                final int heroPos = Dungeon.hero.pos;

                Dungeon.hero.sprite.move(heroPos, cell);
                Dungeon.hero.move( cell );

                Dungeon.level.press(cell, Dungeon.hero);

                MirrorImage illusion = new MirrorImage();
                illusion.duplicate( Dungeon.hero );
                GameScene.add( illusion );
                ScrollOfTeleportation.appear( illusion, heroPos );

                Dungeon.observe();
                GameScene.updateFog();

                Dungeon.hero.earnMana(-manaCost());
                Dungeon.hero.spendAndNext(1f);
            }
        }

        @Override
        public String prompt() {
            return Messages.get(Bypass.class, "prompt");
        }
    };

    @Override
    public int manaCost() { return 4; }

    @Override
    public int icon() {
        return Skill.ILLUSION;
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
