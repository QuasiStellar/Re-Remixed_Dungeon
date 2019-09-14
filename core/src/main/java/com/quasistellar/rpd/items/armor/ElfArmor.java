/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.quasistellar.rpd.items.armor;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.blobs.Blob;
import com.quasistellar.rpd.actors.blobs.Regrowth;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Ooze;
import com.quasistellar.rpd.actors.buffs.Roots;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.effects.particles.ElmoParticle;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class ElfArmor extends ClassArmor {
	
	{
		image = ItemSpriteSheet.ARMOR_ELF;
	}
	
	@Override
	public void doSpecial() {
		
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Dungeon.level.heroFOV[mob.pos]) {
				Buff.affect(mob, Ooze.class).set( 20f );
				Buff.prolong( mob, Roots.class, 5 );
				GameScene.add(Blob.seed(mob.pos, 30, Regrowth.class));
			}
		}

		curUser.HP -= (curUser.HP / 3);
		
		curUser.spend( Actor.TICK );
		curUser.sprite.operate( curUser.pos );
		curUser.busy();
		
		curUser.sprite.centerEmitter().start( ElmoParticle.FACTORY, 0.15f, 4 );
		Sample.INSTANCE.play( Assets.SND_PLANT );
	}

}