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

import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.Bleeding;
import com.quasistellar.rpd.actors.buffs.BlobImmunity;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Cripple;
import com.quasistellar.rpd.actors.buffs.Drowsy;
import com.quasistellar.rpd.actors.buffs.MagicalSleep;
import com.quasistellar.rpd.actors.buffs.Poison;
import com.quasistellar.rpd.actors.buffs.Slow;
import com.quasistellar.rpd.actors.buffs.Vertigo;
import com.quasistellar.rpd.actors.buffs.Weakness;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.actors.hero.HeroSubClass;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.quasistellar.rpd.utils.GLog;

public class Dreamfoil extends Plant {

	{
		image = 7;
	}

	@Override
	public void activate( Char ch ) {

		if (ch != null) {
			if (ch instanceof Mob) {
				Buff.affect(ch, MagicalSleep.class);
			} else if (ch instanceof Hero){
				GLog.i( Messages.get(this, "refreshed") );
				Buff.detach( ch, Poison.class );
				Buff.detach( ch, Cripple.class );
				Buff.detach( ch, Weakness.class );
				Buff.detach( ch, Bleeding.class );
				Buff.detach( ch, Drowsy.class );
				Buff.detach( ch, Slow.class );
				Buff.detach( ch, Vertigo.class);
				
				if (((Hero) ch).subClass == HeroSubClass.WARDEN){
					Buff.affect(ch, BlobImmunity.class, 10f);
				}
				
			}
		}
	}

	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_DREAMFOIL;

			plantClass = Dreamfoil.class;
		}
	}
}