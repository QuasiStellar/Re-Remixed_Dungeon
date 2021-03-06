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

package com.quasistellar.rpd.actors.mobs.npcs;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.sprites.CitizenSprite1;
import com.quasistellar.rpd.sprites.CitizenSprite2;
import com.quasistellar.rpd.sprites.CitizenSprite3;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.utils.Random;

public class Citizen extends NPC {

	{
		switch (Random.Int(3)) {
			case 0:
				spriteClass = CitizenSprite1.class;
				break;
			case 1:
				spriteClass = CitizenSprite2.class;
				break;
			case 2:
				spriteClass = CitizenSprite3.class;
				break;
		}

		properties.add(Property.IMMOVABLE);
	}
	
	@Override
	public int defenseSkill( Char enemy ) {
		return 100_000_000;
	}

	@Override
	public void damage( int dmg, Object src ) {
	}
	
	@Override
	public void add( Buff buff ) {
	}
	
	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	public boolean interact() {
		sprite.turnTo( pos, Dungeon.hero.pos );
		String replica;
		switch (Random.Int(5)) {
			case 0:
				replica = "replica1";
				break;
			case 1:
				replica = "replica2";
				break;
			case 2:
				replica = "replica3";
				break;
			case 3:
				replica = "replica4";
				break;
			case 4:
				replica = "replica5";
				break;
			default:
				replica = "replica1";
				break;
		}
		switch (Random.Int(2)) {
			case 0:
				GLog.i(Messages.get(this, replica));
				break;
			case 1:
				GLog.p(Messages.get(this, replica));
				break;
		}
		return true;
	}
}
