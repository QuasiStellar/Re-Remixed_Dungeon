package com.quasistellar.rpd.skills.huntress;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.RPD;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.items.bombs.Bomb;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.quasistellar.rpd.sprites.MissileSprite;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.utils.Callback;

public class Blast extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(blast);
    }

    private CellSelector.Listener blast = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            if (!Dungeon.level.heroFOV[cell] || !Dungeon.level.passable[cell]){
                GLog.w( Messages.get(Blast.class, "bad_target") );
            } else {
                final int targetCell = cell;
                final Hero hero = Dungeon.hero;
                hero.sprite.operate(hero.pos, new Callback() {
                    @Override
                    public void call() {

                        hero.sprite.idle();
                        hero.sprite.zap(targetCell);
                        hero.busy();

//                        Ballistica trajectory = new Ballistica(targetCell, Dungeon.hero.pos, Ballistica.STOP_TARGET);
//                        trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
//                        WandOfBlastWave.throwChar(Dungeon.hero, trajectory, 2);

                        ((MissileSprite) RPD.scene().recycle(MissileSprite.class)).
                                reset(Dungeon.hero.pos, targetCell, new Bullet(), new Callback() {
                                    @Override
                                    public void call() {

                                        new Bomb().explode(targetCell);
                                        hero.spendAndNext(1f);

                                    }
                                });

                    }
                });

                Dungeon.hero.earnMana(-manaCost());
            }
        }

        @Override
        public String prompt() {
            return Messages.get(Blast.class, "prompt");
        }
    };

    private class Bullet extends Item {
        {
            image = ItemSpriteSheet.BULLET;

            bones = false;
        }

        @Override
        public boolean isUpgradable() {
            return false;
        }

        @Override
        public boolean isIdentified() {
            return true;
        }
    }

    @Override
    public int manaCost() { return 15; }

    @Override
    public int icon() {
        return Skill.BLAST;
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
