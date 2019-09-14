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

package com.quasistellar.rpd.windows;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.actors.mobs.npcs.FortuneTeller;
import com.quasistellar.rpd.actors.mobs.npcs.Imp;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.items.armor.Armor;
import com.quasistellar.rpd.items.quest.DwarfToken;
import com.quasistellar.rpd.items.wands.Wand;
import com.quasistellar.rpd.items.weapon.Weapon;
import com.quasistellar.rpd.items.weapon.melee.MeleeWeapon;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.scenes.PixelScene;
import com.quasistellar.rpd.sprites.ItemSprite;
import com.quasistellar.rpd.ui.QuickSlotButton;
import com.quasistellar.rpd.ui.RedButton;
import com.quasistellar.rpd.ui.RenderedTextMultiline;
import com.quasistellar.rpd.ui.Window;
import com.quasistellar.rpd.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class WndFortuneTeller extends Window {

	private static final int WIDTH      = 120;
	private static final int BTN_HEIGHT = 20;
	private static final int GAP        = 2;

	public WndFortuneTeller(FortuneTeller teller, Hero hero, int price ) {
		
		super();

		IconTitle titlebar = new IconTitle();
		titlebar.icon( teller.sprite() );
		titlebar.label( Messages.titleCase( teller.name ) );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );
		
		RenderedTextMultiline message = PixelScene.renderMultiline( Messages.get(this, "message", price), 6 );
		message.maxWidth(WIDTH);
		message.setPos(0, titlebar.bottom() + GAP);
		add( message );
		
		RedButton btnIdentify = new RedButton( Messages.get(this, "identify", price) ) {
			@Override
			protected void onClick() {
				GameScene.selectItem( itemSelector, WndBag.Mode.UNIDED_WEP_ARM_RING_ART_WAND, Messages.get(WndFortuneTeller.class, "select") );
			}
		};
		btnIdentify.setRect( 0, message.top() + message.height() + GAP, WIDTH, BTN_HEIGHT );
		btnIdentify.enable( price <= Dungeon.gold );
		add( btnIdentify );
		
		resize( WIDTH, (int)btnIdentify.bottom() );
	}

	protected WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect( Item item ) {
			if (item != null) {
				Dungeon.gold -= FortuneTeller.price;
				item.identify();
				FortuneTeller.price += 50;

				if (Random.Int(1) == 0) {
					Sample.INSTANCE.play(Assets.SND_CURSED);

					hide();
					GameScene.show( new WndQuest( new FortuneTeller(), Messages.get(WndFortuneTeller.class, "curse", item.name()) ) );
					item.cursed = true;
					if (item instanceof MeleeWeapon) {
						Weapon w = (Weapon) item;
						if (w.enchantment != null) {
							w.enchant(Weapon.Enchantment.randomCurse(w.enchantment.getClass()));
						} else {
							w.enchant(Weapon.Enchantment.randomCurse());
						}
						w.curseInfusionBonus = true;
					} else if (item instanceof Armor){
						Armor a = (Armor) item;
						if (a.glyph != null){
							a.inscribe(Armor.Glyph.randomCurse(a.glyph.getClass()));
						} else {
							a.inscribe(Armor.Glyph.randomCurse());
						}
						a.curseInfusionBonus = true;
					} else if (item instanceof Wand){
						((Wand) item).curseInfusionBonus = true;
						((Wand) item).updateLevel();
					}
					QuickSlotButton.refresh();
				}
			}
		}
	};
}
