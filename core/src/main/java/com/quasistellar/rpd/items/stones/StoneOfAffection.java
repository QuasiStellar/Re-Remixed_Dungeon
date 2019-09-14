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

package com.quasistellar.rpd.items.stones;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Charm;
import com.quasistellar.rpd.effects.CellEmitter;
import com.quasistellar.rpd.effects.Speck;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class StoneOfAffection extends Runestone {
	
	{
		image = ItemSpriteSheet.STONE_AFFECTION;
	}
	
	@Override
	protected void activate(int cell) {
		
		for (int i : PathFinder.NEIGHBOURS9){
			
			CellEmitter.center(cell + i).start( Speck.factory( Speck.HEART ), 0.2f, 5 );
			
			
			Char ch = Actor.findChar( cell + i );
			
			if (ch != null && ch.alignment == Char.Alignment.ENEMY){
				Buff.prolong(ch, Charm.class, 10f).object = curUser.id();
			}
		}
		
		Sample.INSTANCE.play( Assets.SND_CHARMS );
		
	}
	
}
