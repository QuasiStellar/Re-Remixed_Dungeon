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

package com.quasistellar.rpd.items.potions.elixirs;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.actors.buffs.FireImbue;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.effects.particles.FlameParticle;
import com.quasistellar.rpd.items.potions.AlchemicalCatalyst;
import com.quasistellar.rpd.items.potions.exotic.PotionOfDragonsBreath;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class ElixirOfDragonsBlood extends Elixir {
	
	{
		//TODO finish visuals
		image = ItemSpriteSheet.ELIXIR_DRAGON;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.affect(hero, FireImbue.class).set(FireImbue.DURATION);
		Sample.INSTANCE.play( Assets.SND_BURNING );
		hero.sprite.emitter().burst(FlameParticle.FACTORY, 10);
	}
	
	@Override
	protected int splashColor() {
		return 0xFFFF002A;
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (50 + 40);
	}
	
	public static class Recipe extends com.quasistellar.rpd.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfDragonsBreath.class, AlchemicalCatalyst.class};
			inQuantity = new int[]{1, 1};

			cost = 6;
			
			output = ElixirOfDragonsBlood.class;
			outQuantity = 1;
		}
		
	}
}
