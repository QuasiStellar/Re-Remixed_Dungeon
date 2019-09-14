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

package com.quasistellar.rpd.items.rings;

import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.blobs.Electricity;
import com.quasistellar.rpd.actors.blobs.ToxicGas;
import com.quasistellar.rpd.actors.buffs.Burning;
import com.quasistellar.rpd.actors.buffs.Charm;
import com.quasistellar.rpd.actors.buffs.Chill;
import com.quasistellar.rpd.actors.buffs.Corrosion;
import com.quasistellar.rpd.actors.buffs.Frost;
import com.quasistellar.rpd.actors.buffs.Ooze;
import com.quasistellar.rpd.actors.buffs.Paralysis;
import com.quasistellar.rpd.actors.buffs.Poison;
import com.quasistellar.rpd.actors.buffs.Weakness;
import com.quasistellar.rpd.actors.mobs.Eye;
import com.quasistellar.rpd.actors.mobs.Shaman;
import com.quasistellar.rpd.actors.mobs.Warlock;
import com.quasistellar.rpd.actors.mobs.Yog;
import com.quasistellar.rpd.items.wands.WandOfBlastWave;
import com.quasistellar.rpd.items.wands.WandOfDisintegration;
import com.quasistellar.rpd.items.wands.WandOfFireblast;
import com.quasistellar.rpd.items.wands.WandOfFrost;
import com.quasistellar.rpd.items.wands.WandOfLightning;
import com.quasistellar.rpd.items.wands.WandOfLivingEarth;
import com.quasistellar.rpd.items.wands.WandOfMagicMissile;
import com.quasistellar.rpd.items.wands.WandOfPrismaticLight;
import com.quasistellar.rpd.items.wands.WandOfTransfusion;
import com.quasistellar.rpd.items.wands.WandOfWarding;
import com.quasistellar.rpd.levels.traps.DisintegrationTrap;
import com.quasistellar.rpd.levels.traps.GrimTrap;
import com.quasistellar.rpd.messages.Messages;

import java.text.DecimalFormat;
import java.util.HashSet;

public class RingOfElements extends Ring {
	
	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats", new DecimalFormat("#.##").format(100f * (1f - Math.pow(0.80f, soloBonus()))));
		} else {
			return Messages.get(this, "typical_stats", new DecimalFormat("#.##").format(20f));
		}
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Resistance();
	}

	//FIXME probably should add wands here
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( Burning.class );
		RESISTS.add( Charm.class );
		RESISTS.add( Chill.class );
		RESISTS.add( Frost.class );
		RESISTS.add( Ooze.class );
		RESISTS.add( Paralysis.class );
		RESISTS.add( Poison.class );
		RESISTS.add( Corrosion.class );
		RESISTS.add( Weakness.class );
		
		RESISTS.add( DisintegrationTrap.class );
		RESISTS.add( GrimTrap.class );

		RESISTS.add( WandOfBlastWave.class );
		RESISTS.add( WandOfDisintegration.class );
		RESISTS.add( WandOfFireblast.class );
		RESISTS.add( WandOfFrost.class );
		RESISTS.add( WandOfLightning.class );
		RESISTS.add( WandOfLivingEarth.class );
		RESISTS.add( WandOfMagicMissile.class );
		RESISTS.add( WandOfPrismaticLight.class );
		RESISTS.add( WandOfTransfusion.class );
		RESISTS.add( WandOfWarding.Ward.class );
		
		RESISTS.add( ToxicGas.class );
		RESISTS.add( Electricity.class );
		
		RESISTS.add( Shaman.LightningBolt.class );
		RESISTS.add( Warlock.DarkBolt.class );
		RESISTS.add( Eye.DeathGaze.class );
		RESISTS.add( Yog.BurningFist.DarkBolt.class );
	}
	
	public static float resist( Char target, Class effect ){
		if (getBonus(target, Resistance.class) == 0) return 1f;
		
		for (Class c : RESISTS){
			if (c.isAssignableFrom(effect)){
				return (float)Math.pow(0.80, getBonus(target, Resistance.class));
			}
		}
		
		return 1f;
	}
	
	public class Resistance extends RingBuff {
	
	}
}
