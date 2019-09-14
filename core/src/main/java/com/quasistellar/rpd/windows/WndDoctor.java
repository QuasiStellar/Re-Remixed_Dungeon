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
import com.quasistellar.rpd.Chrome;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.hero.Hero;
import com.quasistellar.rpd.actors.mobs.npcs.PlagueDoctor;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.PixelScene;
import com.quasistellar.rpd.ui.ItemSlot;
import com.quasistellar.rpd.ui.RenderedTextMultiline;
import com.quasistellar.rpd.ui.Window;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Component;

public class WndDoctor extends Window {

	private static final int BTN_SIZE	= 36;
	private static final float GAP		= 2;
	private static final float BTN_GAP	= 10;
	private static final int WIDTH		= 116;

	private ItemButton btnItem1;
	private ItemButton btnItem2;

	public WndDoctor(PlagueDoctor doctor, Hero hero ) {
		
		super();

		PlagueDoctor p = new PlagueDoctor();
		IconTitle titlebar = new IconTitle();
		titlebar.icon( p.sprite() );
		titlebar.label( Messages.titleCase( doctor.name ) );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );

		RenderedTextMultiline message = PixelScene.renderMultiline( Messages.get(PlagueDoctor.class, "prompt", PlagueDoctor.Quest.reqItem.name()), 6);
		message.maxWidth( WIDTH);
		message.setPos(0, titlebar.bottom() + GAP);
		add( message );

		Item req = Dungeon.hero.belongings.getItem(PlagueDoctor.Quest.reqItem.getClass());
		
		btnItem1 = new ItemButton() {
			@Override
			protected void onClick() {
				Item req = Dungeon.hero.belongings.getItem(PlagueDoctor.Quest.reqItem.getClass());
				if (req != null) {
					hide();
					req.detach(Dungeon.hero.belongings.backpack);
					PlagueDoctor.Quest.prize1.collect(Dungeon.hero.belongings.backpack);
					PlagueDoctor.Quest.orderNumber += 1;
					PlagueDoctor.nextQuest();
				}
			}
		};
		btnItem1.item(PlagueDoctor.Quest.prize1);
		btnItem1.setRect( (WIDTH - BTN_GAP) / 2 - BTN_SIZE, message.top() + message.height() + BTN_GAP, BTN_SIZE, BTN_SIZE );
		add( btnItem1 );

		btnItem2 = new ItemButton() {
			@Override
			protected void onClick() {
				Item req = Dungeon.hero.belongings.getItem(PlagueDoctor.Quest.reqItem.getClass());
				if (req != null) {
					hide();
					req.detach(Dungeon.hero.belongings.backpack);
					PlagueDoctor.Quest.prize2.collect(Dungeon.hero.belongings.backpack);
					PlagueDoctor.Quest.orderNumber += 1;
					PlagueDoctor.nextQuest();
				}
			}
		};
		btnItem2.item(PlagueDoctor.Quest.prize2);
		btnItem2.setRect( btnItem1.right() + BTN_GAP, btnItem1.top(), BTN_SIZE, BTN_SIZE );
		add( btnItem2 );


		if (req == null) {
			btnItem1.brightness = 0.5f;
			btnItem2.brightness = 0.5f;
			btnItem1.bg.brightness(btnItem1.brightness);
			btnItem2.bg.brightness(btnItem2.brightness);
		} else {
			btnItem1.brightness = 1f;
			btnItem2.brightness = 1f;
			btnItem1.bg.brightness(btnItem1.brightness);
			btnItem2.bg.brightness(btnItem2.brightness);
		}

		resize( WIDTH, (int)(btnItem1.bottom() + BTN_GAP));
	}
	
	public static class ItemButton extends Component {
		
		protected NinePatch bg;
		protected ItemSlot slot;

		protected float brightness = 1f;
		
		public Item item = null;
		
		@Override
		protected void createChildren() {
			super.createChildren();
			
			bg = Chrome.get( Chrome.Type.BUTTON);
			add( bg );
			
			slot = new ItemSlot() {
				@Override
				protected void onPointerDown() {
					bg.brightness( brightness + 0.2f );
					Sample.INSTANCE.play( Assets.SND_CLICK );
				}
				@Override
				protected void onPointerUp() {
					bg.brightness( brightness );
				}
				@Override
				protected void onClick() {
					ItemButton.this.onClick();
				}
			};
			slot.enable(true);
			add( slot );
		}
		
		protected void onClick() {}
		
		@Override
		protected void layout() {
			super.layout();
			
			bg.x = x;
			bg.y = y;
			bg.size( width, height );
			
			slot.setRect( x + 2, y + 2, width - 4, height - 4 );
		}
		
		public void item( Item item ) {
			slot.item( this.item = item );
		}
	}
}
