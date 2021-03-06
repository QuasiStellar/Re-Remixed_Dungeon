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

package com.quasistellar.rpd.plants;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.AdrenalineSurge;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.actors.hero.HeroSubClass;
import com.quasistellar.rpd.effects.CellEmitter;
import com.quasistellar.rpd.effects.particles.LeafParticle;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;

public class Rotberry extends Plant {

	{
		image = 0;
	}

	@Override
	public void activate( Char ch ) {
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, AdrenalineSurge.class).reset(1, 200f);
		}
		
		Dungeon.level.drop( new Seed(), pos ).sprite.drop();
	}
	
	@Override
	public void wither() {
		Dungeon.level.uproot( pos );
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
		}
		
		//no warden benefit
	}

	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_ROTBERRY;

			plantClass = Rotberry.class;
		}
		
		@Override
		public int price() {
			return 30 * quantity;
		}
	}
}
