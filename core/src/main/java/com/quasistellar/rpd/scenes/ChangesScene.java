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

package com.quasistellar.rpd.scenes;

import com.quasistellar.rpd.Chrome;
import com.quasistellar.rpd.RPD;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.ui.Archs;
import com.quasistellar.rpd.ui.ExitButton;
import com.quasistellar.rpd.ui.RedButton;
import com.quasistellar.rpd.ui.ScrollPane;
import com.quasistellar.rpd.ui.Window;
import com.quasistellar.rpd.ui.changelist.ChangeInfo;
import com.quasistellar.rpd.ui.changelist.r0_1_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_1_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_2_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_3_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_4_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_5_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_6_X_Changes;
import com.quasistellar.rpd.ui.changelist.v0_7_X_Changes;
import com.watabou.noosa.Camera;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class ChangesScene extends PixelScene {
	
	public static int changesSelected = 0;
	
	@Override
	public void create() {
		super.create();

		int w = Camera.main.width;
		int h = Camera.main.height;

		RenderedText title = PixelScene.renderText( Messages.get(this, "title"), 9 );
		title.hardlight(Window.TITLE_COLOR);
		title.x = (w - title.width()) / 2f;
		title.y = (20 - title.baseLine()) / 2f;
		align(title);
		add(title);

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		NinePatch panel = Chrome.get(Chrome.Type.TOAST);

		int pw = 135 + panel.marginLeft() + panel.marginRight() - 2;
		int ph = h - 35;

		panel.size( pw, ph );
		panel.x = (w - pw) / 2f;
		panel.y = title.y + title.height();
		align( panel );
		add( panel );
		
		final ArrayList<ChangeInfo> changeInfos = new ArrayList<>();
		
		switch (changesSelected){
			case 0: default:
				r0_1_X_Changes.addAllChanges(changeInfos);
				break;
			case 1:
				v0_7_X_Changes.addAllChanges(changeInfos);
				v0_6_X_Changes.addAllChanges(changeInfos);
				v0_5_X_Changes.addAllChanges(changeInfos);
				v0_4_X_Changes.addAllChanges(changeInfos);
				v0_3_X_Changes.addAllChanges(changeInfos);
				v0_2_X_Changes.addAllChanges(changeInfos);
				v0_1_X_Changes.addAllChanges(changeInfos);
				break;
		}

		ScrollPane list = new ScrollPane( new Component() ){

			@Override
			public void onClick(float x, float y) {
				for (ChangeInfo info : changeInfos){
					if (info.onClick( x, y )){
						return;
					}
				}
			}

		};
		add( list );

		Component content = list.content();
		content.clear();

		float posY = 0;
		float nextPosY = 0;
		boolean second = false;
		for (ChangeInfo info : changeInfos){
			if (info.major) {
				posY = nextPosY;
				second = false;
				info.setRect(0, posY, panel.innerWidth(), 0);
				content.add(info);
				posY = nextPosY = info.bottom();
			} else {
				if (!second){
					second = true;
					info.setRect(0, posY, panel.innerWidth()/2f, 0);
					content.add(info);
					nextPosY = info.bottom();
				} else {
					second = false;
					info.setRect(panel.innerWidth()/2f, posY, panel.innerWidth()/2f, 0);
					content.add(info);
					nextPosY = Math.max(info.bottom(), nextPosY);
					posY = nextPosY;
				}
			}
		}

		content.setSize( panel.innerWidth(), (int)Math.ceil(posY) );

		list.setRect(
				panel.x + panel.marginLeft(),
				panel.y + panel.marginTop() - 1,
				panel.innerWidth(),
				panel.innerHeight() + 2);
		list.scrollTo(0, 0);
		
		RedButton rpd = new RedButton("Re-Remixed"){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 0) {
					changesSelected = 0;
					RPD.seamlessResetScene();
				}
			}
		};
		if (changesSelected == 0) rpd.textColor(Window.TITLE_COLOR);
		rpd.setRect(list.left()-3, list.bottom()+5, 60, 14);
		add(rpd);
		
		RedButton shp = new RedButton("Shattered"){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 1) {
					changesSelected = 1;
					RPD.seamlessResetScene();
				}
			}
		};
		if (changesSelected == 1) shp.textColor(Window.TITLE_COLOR);
		shp.setRect(list.right()+3-60, list.bottom()+5, 60, 14);
		add(shp);

		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		RPD.switchNoFade(TitleScene.class);
	}

}
