package com.quasistellar.rpd.skills.huntress;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.RPD;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.effects.CellEmitter;
import com.quasistellar.rpd.effects.particles.PurpleParticle;
import com.quasistellar.rpd.items.bombs.Bomb;
import com.quasistellar.rpd.items.weapon.SpiritBow;
import com.quasistellar.rpd.items.weapon.missiles.darts.Dart;
import com.quasistellar.rpd.mechanics.Ballistica;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.sprites.MissileSprite;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Piercing extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(piercing);
    }

    private CellSelector.Listener piercing = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;

            final int targetCell = cell;
            final Hero hero = Dungeon.hero;

            hero.sprite.idle();
            hero.sprite.zap(targetCell);
            hero.busy();

            ((MissileSprite) RPD.scene().recycle(MissileSprite.class)).
                    reset(Dungeon.hero.pos, targetCell, new Dart(), 2f, new Callback() {
                        @Override
                        public void call() {

                            ArrayList<Char> chars = new ArrayList<>();

                            final Ballistica shot = new Ballistica( Dungeon.hero.pos, targetCell, Ballistica.WONT_STOP);
                            for (int c : shot.subPath(1, shot.path.size()-1)) {

                                Char ch;
                                if ((ch = Actor.findChar( c )) != null) {
                                    chars.add( ch );
                                }

                            }

                            float power = (float) Math.pow(1.5, chars.size());
                            SpiritBow bow = new SpiritBow();
                            bow.level(Dungeon.hero.lvl / 5);
                            SpiritBow heroBow = (SpiritBow) Dungeon.hero.belongings.getSimilar(bow);
                            if (heroBow != null) bow = heroBow;
                            for (Char ch : chars) {
                                ch.damage( Math.round(bow.damageRoll(Dungeon.hero) * power), this );
                                ch.sprite.flash();
                            }

                            hero.spendAndNext(1f);

                        }
                    });

            Dungeon.hero.earnMana(-manaCost());

        }

        @Override
        public String prompt() {
            return Messages.get(Blast.class, "prompt");
        }
    };

    @Override
    public int manaCost() { return 5; }

    @Override
    public int icon() {
        return Skill.PIERCING;
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
