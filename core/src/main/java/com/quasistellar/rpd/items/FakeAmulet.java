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

package com.quasistellar.rpd.items;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.actors.mobs.MimicAmulet;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class FakeAmulet extends Item {
	
	{
		image = ItemSpriteSheet.AMULET;
		
		unique = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		return new ArrayList<String>();
	}

	@Override
	public boolean doPickUp( Hero hero ) {
		if (super.doPickUp( hero )) {

			detach( Dungeon.hero.belongings.backpack );
			int pos = Dungeon.hero.pos;
			for (int n : PathFinder.NEIGHBOURS8) {
				int cell = pos + n;
				if (Dungeon.level.passable[cell] && Actor.findChar( cell ) == null) {
					MimicAmulet.spawnAt(cell);
					return true;
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}

}
