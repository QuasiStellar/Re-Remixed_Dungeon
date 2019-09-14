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
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.ClericSprite;
import com.quasistellar.rpd.sprites.FortuneTellerSprite;
import com.quasistellar.rpd.windows.WndCleric;
import com.quasistellar.rpd.windows.WndFortuneTeller;
import com.watabou.utils.Bundle;

public class Cleric extends NPC {

	{
		spriteClass = ClericSprite.class;

		properties.add(Property.IMMOVABLE);
	}

	public static int price = 150;
	public static int uncurse_price = 500;
	
	@Override
	protected boolean act() {
		throwItem();
		return super.act();
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

	private static final String PRICE		  = "price";
	private static final String UNCURSE_PRICE = "uncurse_price";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( PRICE, price );
		bundle.put( UNCURSE_PRICE, uncurse_price );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		price = bundle.getInt( PRICE );
		uncurse_price = bundle.getInt( UNCURSE_PRICE );
	}
	
	@Override
	public boolean reset() {
		price = 150;
		uncurse_price = 500;
		return true;
	}
	
	@Override
	public boolean interact() {
		
		sprite.turnTo( pos, Dungeon.hero.pos );

		GameScene.show(new WndCleric(this, Dungeon.hero, price, uncurse_price));

		return false;
	}
}
