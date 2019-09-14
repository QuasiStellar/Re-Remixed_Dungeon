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
import com.quasistellar.rpd.actors.buffs.Ally;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Charm;
import com.quasistellar.rpd.actors.buffs.Ooze;
import com.quasistellar.rpd.actors.buffs.Roots;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.actors.mobs.Skeleton;
import com.quasistellar.rpd.effects.particles.ElmoParticle;
import com.quasistellar.rpd.effects.particles.ShadowParticle;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;

public class NecromancerArmor extends ClassArmor {

	private static final float SPAWN_DELAY	= 2f;
	
	{
		image = ItemSpriteSheet.ARMOR_NECROMANCER;
	}
	
	@Override
	public void doSpecial() {

		for (int n : PathFinder.NEIGHBOURS4) {
			int cell = curUser.pos + n;
			if (Dungeon.level.passable[cell] && Actor.findChar( cell ) == null) {
				spawnAt( cell );
			}
		}

		curUser.HP -= (curUser.HP / 3);
		
		curUser.spend( Actor.TICK );
		curUser.sprite.operate( curUser.pos );
		curUser.busy();

		Sample.INSTANCE.play( Assets.SND_BONES );
	}

	public static Skeleton spawnAt(int pos ) {
		if (Dungeon.level.passable[pos] && Actor.findChar( pos ) == null) {

			Skeleton s = new Skeleton();
			s.pos = pos;
			s.state = s.HUNTING;
			Buff.affect(s, Ally.class);
			GameScene.add( s, SPAWN_DELAY );

			s.sprite.alpha( 0 );
			s.sprite.parent.add( new AlphaTweener( s.sprite, 1, 0.5f ) );

			s.sprite.emitter().burst( ShadowParticle.CURSE, 5 );

			return s;
		} else {
			return null;
		}
	}
}