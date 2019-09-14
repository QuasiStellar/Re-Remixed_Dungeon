package com.quasistellar.rpd.skills.rogue;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.actors.mobs.npcs.Shopkeeper;
import com.quasistellar.rpd.items.Heap;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.items.potions.Potion;
import com.quasistellar.rpd.items.potions.PotionOfStrength;
import com.quasistellar.rpd.items.scrolls.Scroll;
import com.quasistellar.rpd.items.scrolls.ScrollOfUpgrade;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.CellSelector;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.skills.warrior.Headbutt;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.utils.Random;

public class Theft extends Skill {

    @Override
    public void act() {
        GameScene.selectCell(headbutt);
    }

    private CellSelector.Listener headbutt = new CellSelector.Listener() {

        @Override
        public void onSelect(Integer cell) {
            if (cell == null) return;
            Heap heap = Dungeon.level.heaps.get( cell );
            if (heap != null) {
                switch (heap.type) {
                    case HEAP:
                        transport( heap );
                        break;
                    case CHEST:
                    case MIMIC:
                    case TOMB:
                    case SKELETON:
                    case REMAINS:
                        heap.open( Dungeon.hero );
                        break;
                    case FOR_SALE:
                        int success = Random.Int(3);
                        while (success != 0 && Dungeon.hero.MP >= manaCost()) {
                            Dungeon.hero.earnMana(-manaCost());
                            success = Random.Int(3);
                        }
                        if (success != 0) {
                            for (Mob mob : Dungeon.level.mobs){
                                if (mob instanceof Shopkeeper) {
                                    mob.yell(Messages.get(mob, "thief"));
                                    ((Shopkeeper) mob).flee();
                                    break;
                                }
                            }
                        } else {
                            transport( heap );
                        }
                    default:
                }
            } else {
                GLog.w( Messages.get(Theft.class, "bad_target") );
            }
        }

        @Override
        public String prompt() {
            return Messages.get(Theft.class, "prompt");
        }
    };

    private void transport(Heap heap) {
        Item item = heap.pickUp();
        if (item.doPickUp( Dungeon.hero )) {
            boolean important =
                    (item instanceof ScrollOfUpgrade && ((Scroll)item).isKnown()) ||
                    (item instanceof PotionOfStrength && ((Potion)item).isKnown());
            if (important) {
                GLog.p( Messages.get(this, "you_now_have", item.name()) );
            } else {
                GLog.i( Messages.get(this, "you_now_have", item.name()) );
            }
        } else {
            Dungeon.level.drop( item, Dungeon.hero.pos ).sprite.drop();
        }
        Dungeon.hero.earnMana(-manaCost());
    }

    @Override
    public int manaCost() { return 4; }

    @Override
    public int icon() {
        return Skill.THEFT;
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
