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
import com.quasistellar.rpd.actors.buffs.Ally;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.Terror;
import com.quasistellar.rpd.actors.mobs.npcs.NPC;
import com.quasistellar.rpd.effects.particles.ShadowParticle;
import com.quasistellar.rpd.items.weapon.enchantments.Grim;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.DeathlingSprite;
import com.quasistellar.rpd.sprites.WraithSprite;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class DeathlingMob extends NPC {
	
	private int level;
	
	{
		spriteClass = DeathlingSprite.class;

		flying = true;

		alignment = Alignment.ALLY;
		intelligentAlly = true;
		WANDERING = new Wandering();

		state = HUNTING;

		//before other mobs
		actPriority = MOB_PRIO + 1;

		properties.add(Property.UNDEAD);
	}
	
	private static final String LEVEL = "level";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEVEL, level );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getInt( LEVEL );
		adjustStats( level );
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1 + level/2, 2 + level );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 10 + level;
	}
	
	public void adjustStats( int level ) {
		this.level = level;
		HP = HT = 8 + level;
		defenseSkill = attackSkill( null ) / 2;
		enemySeen = true;
	}

	@Override
	public boolean reset() {
		state = WANDERING;
		return true;
	}
	
	public static DeathlingMob spawnAt(int pos ) {
		if (Dungeon.level.passable[pos] && Actor.findChar( pos ) == null) {
			
			DeathlingMob d = new DeathlingMob();
			d.adjustStats( Dungeon.hero.lvl );
			d.pos = pos;
			GameScene.add( d, 0 );
			
			d.sprite.alpha( 0 );
			d.sprite.parent.add( new AlphaTweener( d.sprite, 1, 0.5f ) );
			
			d.sprite.emitter().burst( ShadowParticle.CURSE, 5 );
			
			return d;
		} else {
			return null;
		}
	}

	private class Wandering extends Mob.Wandering {

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			if ( enemyInFOV ) {

				enemySeen = true;

				notice();
				alerted = true;
				state = HUNTING;
				target = enemy.pos;

			} else {

				enemySeen = false;

				int oldPos = pos;
				target = Dungeon.hero.pos;
				//always move towards the hero when wandering
				if (getCloser( target )) {
					//moves 2 tiles at a time when returning to the hero
					if (!Dungeon.level.adjacent(target, pos)){
						getCloser( target );
					}
					spend( 1 / speed() );
					return moveSprite( oldPos, pos );
				} else {
					spend( TICK );
				}

			}
			return true;
		}

	}
	
	{
		immunities.add( Grim.class );
		immunities.add( Terror.class );
	}
}
