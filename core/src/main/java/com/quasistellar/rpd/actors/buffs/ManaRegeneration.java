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

package com.quasistellar.rpd.actors.buffs;

import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.items.artifacts.CloakOfShadows;
import com.watabou.utils.Bundle;

public class ManaRegeneration extends Buff {
	
	{
		actPriority = HERO_PRIO - 1;
	}
	
	private static final float GAINING_DELAY = 1;
	private static final float LOSING_DELAY = 2;

	private static int currentDelay = 0;
	
	@Override
	public boolean act() {
		if (target.isAlive()) {

			if (target.buff( Invisibility.class ) != null || target.buff( CloakOfShadows.cloakStealth.class ) != null) {
				((Hero)target).earnMana(1);
				spend(GAINING_DELAY);
			} else {
				if (currentDelay == LOSING_DELAY - 1) {
					((Hero)target).earnMana(-1);
					currentDelay = 0;
				} else {
					currentDelay++;
				}
				spend(1f);
			}
			
		} else {
			
			diactivate();
			
		}
		
		return true;
	}

	private static final String CURRENT    = "current";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( CURRENT, currentDelay );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		currentDelay = bundle.getInt( CURRENT );
	}
}
