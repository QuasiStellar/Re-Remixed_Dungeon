package com.quasistellar.rpd.skills.warrior;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.EarthImbue;
import com.quasistellar.rpd.actors.buffs.FireImbue;
import com.quasistellar.rpd.actors.buffs.FrostImbue;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.items.wands.WandOfBlastWave;
import com.quasistellar.rpd.mechanics.Ballistica;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.ui.AttackIndicator;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Headbutt extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(headbutt);
    }

    private CellSelector.Listener headbutt = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            final Char enemy = Actor.findChar( cell );
            if (enemy == null
                    || !Dungeon.level.heroFOV[cell]
                    || !Dungeon.level.adjacent(Dungeon.hero.pos, enemy.pos)
                    || Dungeon.hero.isCharmedBy( enemy )){
                GLog.w( Messages.get(Headbutt.class, "bad_target") );
            } else {
                Dungeon.hero.sprite.attack(cell, new Callback() {
                    @Override
                    public void call() {
                        doAttack(enemy);
                    }
                });
            }
        }

        private void doAttack(final Char enemy){

            AttackIndicator.target(enemy);

            int dmg = Math.round(Dungeon.hero.damageRoll()/4f);

            dmg = enemy.defenseProc(Dungeon.hero, dmg);
            dmg -= enemy.drRoll();
            dmg = Dungeon.hero.attackProc(enemy, dmg);
            enemy.damage( dmg, this );

            Ballistica trajectory = new Ballistica(Dungeon.hero.pos, enemy.pos, Ballistica.STOP_TARGET);
            trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
            WandOfBlastWave.throwChar(enemy, trajectory, 2);

            trajectory = new Ballistica(enemy.pos, Dungeon.hero.pos, Ballistica.STOP_TARGET);
            trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
            WandOfBlastWave.throwChar(Dungeon.hero, trajectory, 2);

            if (Dungeon.hero.buff(FireImbue.class) != null)
                Dungeon.hero.buff(FireImbue.class).proc(enemy);
            if (Dungeon.hero.buff(EarthImbue.class) != null)
                Dungeon.hero.buff(EarthImbue.class).proc(enemy);
            if (Dungeon.hero.buff(FrostImbue.class) != null)
            Dungeon.hero.buff(FrostImbue.class).proc(enemy);

            Sample.INSTANCE.play( Assets.SND_EVOKE, 1, 1, Random.Float( 0.8f, 1.25f ) );
            enemy.sprite.bloodBurstA( Dungeon.hero.sprite.center(), dmg );
            enemy.sprite.flash();

            if (!enemy.isAlive()){
                GLog.i( Messages.capitalize(Messages.get(Char.class, "defeat", enemy.name)) );
            }

            Dungeon.hero.earnMana(-manaCost());
            Dungeon.hero.spendAndNext(1f);
        }

        @Override
        public String prompt() {
            return Messages.get(Headbutt.class, "prompt");
        }
    };

    @Override
    public int manaCost() { return 5; }

    @Override
    public boolean visible() {
        int v = Dungeon.hero.visibleEnemies();
        for (int i=0; i < v; i++) {
            Mob mob = Dungeon.hero.visibleEnemy( i );
            if ( Dungeon.level.adjacent(Dungeon.hero.pos, mob.pos) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int icon() {
        return Skill.HEADBUTT;
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
