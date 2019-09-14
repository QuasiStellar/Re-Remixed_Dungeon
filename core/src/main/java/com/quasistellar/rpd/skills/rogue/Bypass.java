package com.quasistellar.rpd.skills.rogue;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.utils.GLog;

public class Bypass extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(bypass);
    }

    private CellSelector.Listener bypass = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            final Char enemy = Actor.findChar( cell );
            if (enemy == null
                    || !Dungeon.level.heroFOV[cell]
                    || enemy.properties().contains(Char.Property.IMMOVABLE)
                    || Dungeon.level.distance(Dungeon.hero.pos, enemy.pos) > 3){
                GLog.w( Messages.get(Bypass.class, "bad_target") );
            } else {
                Dungeon.hero.busy();
                final int heroPos = Dungeon.hero.pos;
                final int enemyPos = enemy.pos;

                enemy.sprite.move(enemyPos, heroPos);
                enemy.move( heroPos );

                Dungeon.hero.sprite.jump(heroPos, enemyPos, null);
                Dungeon.hero.move( enemyPos );

                Dungeon.level.press(enemyPos, Dungeon.hero);
                Dungeon.level.press(heroPos, enemy);

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
    public boolean visible() {
        int v = Dungeon.hero.visibleEnemies();
        for (int i=0; i < v; i++) {
            Mob mob = Dungeon.hero.visibleEnemy( i );
            if ( Dungeon.level.distance(Dungeon.hero.pos, mob.pos) <= 3 ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int icon() {
        return Skill.BYPASS;
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
