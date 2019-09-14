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

package com.quasistellar.rpd.items;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.buffs.ShieldBuff;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.items.armor.Armor;
import com.quasistellar.rpd.items.weapon.melee.MeleeWeapon;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.ItemSpriteSheet;
import com.quasistellar.rpd.utils.GLog;
import com.quasistellar.rpd.windows.WndBag;
import com.quasistellar.rpd.windows.WndItem;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class MysticalSkull extends Item {

	public static final String AC_EMBED = "EMBED";

	//only to be used from the quickslot, for tutorial purposes mostly.
	public static final String AC_INFO = "INFO_WINDOW";

	{
		image = ItemSpriteSheet.RAT_SKULL;

		cursedKnown = levelKnown = true;
		unique = true;

		bones = false;

		defaultAction = AC_INFO;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions =  super.actions(hero);
		actions.add(AC_EMBED);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_EMBED)){
			curItem = this;
			GameScene.selectItem(weaponSelector, WndBag.Mode.WEAPON, Messages.get(this, "embed"));
		} else if (action.equals(AC_INFO)) {
			GameScene.show(new WndItem(null, this, true));
		}
	}

	@Override
	//scroll of upgrade can be used directly once, same as upgrading armor the seal is affixed to then removing it.
	public boolean isUpgradable() {
		return level() == 0;
	}

	protected static WndBag.Listener weaponSelector = new WndBag.Listener() {
		@Override
		public void onSelect( Item item ) {
			if (item != null && item instanceof MeleeWeapon) {
				MeleeWeapon weapon = (MeleeWeapon) item;
				if (!weapon.levelKnown){
					GLog.w(Messages.get(MysticalSkull.class, "unknown_weapon"));
				} else if (weapon.cursed || weapon.level() < 0){
					GLog.w(Messages.get(MysticalSkull.class, "degraded_weapon"));
				} else {
					GLog.p(Messages.get(MysticalSkull.class, "embed"));
					Dungeon.hero.sprite.operate(Dungeon.hero.pos);
					Sample.INSTANCE.play(Assets.SND_UNLOCK);
					weapon.affixSkull((MysticalSkull)curItem);
					curItem.detach(Dungeon.hero.belongings.backpack);
				}
			}
		}
	};
}
