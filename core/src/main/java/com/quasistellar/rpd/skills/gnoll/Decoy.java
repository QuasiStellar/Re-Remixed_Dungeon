package com.quasistellar.rpd.skills.gnoll;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.actors.mobs.npcs.GnollSheep;
import com.quasistellar.rpd.effects.CellEmitter;
import com.quasistellar.rpd.effects.Speck;
import com.quasistellar.rpd.items.stones.StoneOfAggression;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.skills.mage.Flock;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Decoy extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(decoy);
    }

    private CellSelector.Listener decoy = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            if (!Dungeon.level.heroFOV[cell]){
                GLog.w( Messages.get(Decoy.class, "bad_target") );
            } else {

                if (!Dungeon.level.solid[cell]
                        && !Dungeon.level.pit[cell]
                        && Actor.findChar(cell) == null) {

                    GnollSheep sheep = new GnollSheep();
                    sheep.lifespan = 5;
                    sheep.pos = cell;
                    GameScene.add(sheep);
                    Dungeon.level.press(sheep.pos, sheep);
                    for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
                        if (Dungeon.level.heroFOV[mob.pos]) {
                            mob.enemy = sheep;
                        }
                    }

                    CellEmitter.get(sheep.pos).burst(Speck.factory(Speck.WOOL), 4);
                } else {
                    GLog.w( Messages.get(Decoy.class, "bad_target") );
                    return;
                }

                CellEmitter.get(cell).burst(Speck.factory(Speck.WOOL), 4);
                Sample.INSTANCE.play(Assets.SND_PUFF);

                Dungeon.hero.earnMana(-manaCost());
                Dungeon.hero.spendAndNext(1f);
            }
        }

        @Override
        public String prompt() {
            return Messages.get(Flock.class, "prompt");
        }
    };

    @Override
    public int manaCost() { return 10; }

    @Override
    public int icon() {
        return Skill.DECOY;
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
