package com.quasistellar.rpd.skills.huntress;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.RPD;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.actors.mobs.npcs.PlagueDoctor;
import com.quasistellar.rpd.effects.CellEmitter;
import com.quasistellar.rpd.effects.particles.PurpleParticle;
import com.quasistellar.rpd.items.Generator;
import com.quasistellar.rpd.items.weapon.SpiritBow;
import com.quasistellar.rpd.items.weapon.missiles.darts.Dart;
import com.quasistellar.rpd.levels.Terrain;
import com.quasistellar.rpd.mechanics.Ballistica;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.plants.Blindweed;
import com.quasistellar.rpd.plants.Dreamfoil;
import com.quasistellar.rpd.plants.Plant;
import com.quasistellar.rpd.plants.Sungrass;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.skills.huntress.Blast;
import com.quasistellar.rpd.sprites.MissileSprite;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Flowerfire extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(piercing);
    }

    private CellSelector.Listener piercing = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            if (!Dungeon.level.heroFOV[cell]){
                GLog.w( Messages.get(Blast.class, "bad_target") );
            } else {
                final int targetCell = cell;
                final Hero hero = Dungeon.hero;

                hero.sprite.idle();
                hero.sprite.zap(targetCell);
                hero.busy();

                ((MissileSprite) RPD.scene().recycle(MissileSprite.class)).
                        reset(Dungeon.hero.pos, targetCell, new Dart(), new Callback() {
                            @Override
                            public void call() {

                                final Ballistica shot = new Ballistica( Dungeon.hero.pos, targetCell, Ballistica.PROJECTILE);

                                for (int c : shot.subPath(1, shot.path.size()-1)) {
                                    if (c == targetCell) break;
                                    if (Dungeon.level.map[c] != Terrain.ALCHEMY
                                            && !Dungeon.level.pit[c]
                                            && Dungeon.level.traps.get(c) == null
                                            && Dungeon.level.passable[c]) {

                                        Plant.Seed plant;
                                        do {
                                            plant = (Plant.Seed) Generator.random(Generator.Category.SEED);
                                        } while (plant instanceof Sungrass.Seed);
                                        Dungeon.level.plant(plant, c );

                                    }
                                }

                                SpiritBow bow = new SpiritBow();
                                bow.level(Dungeon.hero.lvl / 5);
                                SpiritBow heroBow = (SpiritBow) Dungeon.hero.belongings.getSimilar(bow);
                                if (heroBow != null) bow = heroBow;
                                Char enemy = Actor.findChar( targetCell );
                                if (enemy != null) {
                                    enemy.damage(bow.damageRoll(Dungeon.hero), this);
                                }

                                hero.spendAndNext(1f);
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

    @Override
    public int manaCost() { return 10; }

    @Override
    public int icon() {
        return Skill.FLOWERFIRE;
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
