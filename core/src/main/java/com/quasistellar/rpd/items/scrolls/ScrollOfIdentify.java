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

package com.quasistellar.rpd.items.scrolls;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Badges;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.hero.HeroClass;
import com.quasistellar.rpd.effects.Identification;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.utils.GLog;
import com.quasistellar.rpd.windows.WndBag;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ScrollOfIdentify extends InventoryScroll {

	{
		initials = 0;
		mode = WndBag.Mode.UNIDENTIFED;

		bones = true;
	}
	
	@Override
	public void empoweredRead() {
		ArrayList<Item> unIDed = new ArrayList<>();
		
		for( Item i : curUser.belongings){
			if (!i.isIdentified()){
				unIDed.add(i);
			}
		}
		
		if (unIDed.size() > 1) {
			Random.element(unIDed).identify();
			Sample.INSTANCE.play( Assets.SND_TELEPORT );
		}
		
		doRead();
	}
	
	@Override
	protected void onItemSelected( Item item ) {
		
		curUser.sprite.parent.add( new Identification( curUser.sprite.center().offset( 0, -16 ) ) );
		
		item.identify();
		GLog.i( Messages.get(this, "it_is", item) );
		
		Badges.validateItemLevelAquired( item );
	}
	
	@Override
	public int price() {
		return isKnown() ? 30 * quantity : super.price();
	}
}
