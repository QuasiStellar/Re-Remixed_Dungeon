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

package com.quasistellar.rpd.actors.mobs;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.items.FakeAmulet;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.MimicAmuletSprite;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Random;

public class MimicAmulet extends Mob {

	private static final float SPAWN_DELAY	= 1f;

	{
		spriteClass = MimicAmuletSprite.class;
		
		HP = HT = 80;
		defenseSkill = 30;

		EXP = 15;

		properties.add(Property.BLOB_IMMUNE);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 25, 40 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 30;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 5);
	}

	public static MimicAmulet spawnAt(int pos ) {
		if (Dungeon.level.passable[pos] && Actor.findChar( pos ) == null) {

			MimicAmulet f = new MimicAmulet();
			f.pos = pos;
			f.state = f.HUNTING;
			GameScene.add( f, SPAWN_DELAY );

			f.sprite.alpha( 0 );
			f.sprite.parent.add( new AlphaTweener( f.sprite, 1, 0.5f ) );

			return f;
		} else {
			return null;
		}
	}
}
