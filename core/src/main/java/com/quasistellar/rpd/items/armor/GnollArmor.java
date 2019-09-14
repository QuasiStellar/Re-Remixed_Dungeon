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
import com.quasistellar.rpd.actors.mobs.Brute;
import com.quasistellar.rpd.actors.mobs.GnollScout;
import com.quasistellar.rpd.actors.mobs.Shaman;
import com.quasistellar.rpd.effects.Speck;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Random;

public class GnollArmor extends ClassArmor {

	private static final float SPAWN_DELAY	= 2f;
	
	{
		image = ItemSpriteSheet.ARMOR_GNOLL;
	}
	
	@Override
	public void doSpecial() {
		
		spawnGnoll(curUser.pos, -2,  2);
		spawnGnoll(curUser.pos, -1,  2);
		spawnGnoll(curUser.pos, 0,   2);
		spawnGnoll(curUser.pos, 1,   2);
		spawnGnoll(curUser.pos, 2,   2);
		spawnGnoll(curUser.pos, 2,   1);
		spawnGnoll(curUser.pos, 2,   0);
		spawnGnoll(curUser.pos, 2,  -1);
		spawnGnoll(curUser.pos, 2,  -2);
		spawnGnoll(curUser.pos, 1,  -2);
		spawnGnoll(curUser.pos, 0,  -2);
		spawnGnoll(curUser.pos, -1, -2);
		spawnGnoll(curUser.pos, -2, -2);
		spawnGnoll(curUser.pos, -2, -1);
		spawnGnoll(curUser.pos, -2,  0);
		spawnGnoll(curUser.pos, -2,  1);

		curUser.HP -= (curUser.HP / 3);
		
		curUser.spend( Actor.TICK );
		curUser.sprite.operate( curUser.pos );
		curUser.busy();

		Sample.INSTANCE.play( Assets.SND_CHALLENGE );
	}

	private void spawnGnoll(int cell, int dx, int dy) {
		int spawnCell = cell + dx + Dungeon.level.width()*dy;
		if (Dungeon.level.passable[spawnCell] && (Actor.findChar( spawnCell ) == null)) {
			switch (Random.Int(1, 4)) {
				case 1:
					GnollScout g = new GnollScout();
					g.pos = spawnCell;
					g.state = g.HUNTING;
					g.HP = 1;
					g.HT = 1;
					Buff.affect(g, Ally.class);
					GameScene.add(g, SPAWN_DELAY);
					g.sprite.alpha(0);
					g.sprite.parent.add(new AlphaTweener(g.sprite, 1, 0.5f));
					g.sprite.emitter().burst(Speck.factory( Speck.WOOL ), 10 );
					break;
				case 2:
					Shaman s = new Shaman();
					s.pos = spawnCell;
					s.state = s.HUNTING;
					s.HP = 1;
					s.HT = 1;
					Buff.affect(s, Ally.class);
					GameScene.add(s, SPAWN_DELAY);
					s.sprite.alpha(0);
					s.sprite.parent.add(new AlphaTweener(s.sprite, 1, 0.5f));
					s.sprite.emitter().burst(Speck.factory( Speck.WOOL ), 10 );
					break;
				case 3:
					Brute b = new Brute();
					b.pos = spawnCell;
					b.state = b.HUNTING;
					b.HP = 1;
					b.HT = 1;
					Buff.affect(b, Ally.class);
					GameScene.add(b, SPAWN_DELAY);
					b.sprite.alpha(0);
					b.sprite.parent.add(new AlphaTweener(b.sprite, 1, 0.5f));
					b.sprite.emitter().burst(Speck.factory( Speck.WOOL ), 10 );
					break;
			}
		}
	}

}