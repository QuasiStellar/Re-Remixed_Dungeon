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
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.PixelScene;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.ui.RenderedTextMultiline;
import com.quasistellar.rpd.ui.Window;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;

public class WndInfoSkill extends Window {

	private static final float GAP	= 2;

	private static final int WIDTH = 120;

	public WndInfoSkill(Skill skill){
		super();

		SmartTexture icons;
		TextureFilm film;

		IconTitle titlebar = new IconTitle();

		icons = TextureCache.get( Assets.SKILLS );
		film = new TextureFilm( icons, 18, 18 );

		Image skillIcon = new Image( icons );
		skillIcon.frame( film.get(skill.icon()) );

		titlebar.icon( skillIcon );
		titlebar.label( Messages.titleCase(skill.toString()), Window.TITLE_COLOR );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );

		RenderedTextMultiline txtInfo = PixelScene.renderMultiline(skill.desc(), 6);
		txtInfo.maxWidth(WIDTH);
		txtInfo.setPos(titlebar.left(), titlebar.bottom() + GAP);
		add( txtInfo );

		resize( WIDTH, (int)(txtInfo.top() + txtInfo.height()) );
	}
}
