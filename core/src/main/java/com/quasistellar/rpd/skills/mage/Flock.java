package com.quasistellar.rpd.skills.mage;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.mobs.npcs.Sheep;
import com.quasistellar.rpd.effects.CellEmitter;
import com.quasistellar.rpd.effects.Speck;
import com.quasistellar.rpd.items.stones.StoneOfFlock;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Flock extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(bypass);
    }

    private CellSelector.Listener bypass = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            if (!Dungeon.level.heroFOV[cell]){
                GLog.w( Messages.get(Flock.class, "bad_target") );
            } else {
                //Dungeon.hero.busy();
                for (int i : PathFinder.NEIGHBOURS9){

                    if (!Dungeon.level.solid[cell + i]
                            && !Dungeon.level.pit[cell + i]
                            && Actor.findChar(cell + i) == null) {

                        Sheep sheep = new Sheep();
                        sheep.lifespan = Random.IntRange(5, 8);
                        sheep.pos = cell + i;
                        GameScene.add(sheep);
                        Dungeon.level.press(sheep.pos, sheep);

                        CellEmitter.get(sheep.pos).burst(Speck.factory(Speck.WOOL), 4);
                    } else {
                        GLog.w( Messages.get(Flock.class, "bad_target") );
                        return;
                    }
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
    public int manaCost() { return 40; }

    @Override
    public int icon() {
        return Skill.FLOCK;
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
