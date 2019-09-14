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

import com.quasistellar.rpd.RPD;
import com.quasistellar.rpd.SPDSettings;
import com.quasistellar.rpd.effects.Flare;
import com.quasistellar.rpd.ui.Archs;
import com.quasistellar.rpd.ui.ExitButton;
import com.quasistellar.rpd.ui.Icons;
import com.quasistellar.rpd.ui.RenderedTextMultiline;
import com.quasistellar.rpd.ui.Window;
import com.watabou.input.PointerEvent;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Image;
import com.watabou.noosa.PointerArea;
import com.watabou.noosa.RenderedText;
import com.watabou.utils.DeviceCompat;

public class AboutScene extends PixelScene {

	private static final String TTL_RPD = "Re-Remixed Pixel Dungeon";

	private static final String LNK_RPD = "quasistellar.org";

	private static final String TXT_RPD =
			"Code: QuasiStellar\n" +
			"Graphics: QuasiStellar & AlexGnite\n" +
			"\n" +
			"Based on Shattered Pixel Dungeon by 00-Evan\n" +
			"Inspired by Remixed Dungeon by Nyrds";

	@Override
	public void create() {
		super.create();

		Image rpd = Icons.RPD.get();
		rpd.x = (Camera.main.width - rpd.width()) / 2;
		rpd.y = SPDSettings.landscape() ? 40 : 90;
		align(rpd);
		add(rpd);

		new Flare( 7, 64 ).color( 0x555511, true ).show( rpd, 0 ).angularSpeed = +20;

		RenderedText rpdtitle = renderText( TTL_RPD, 8 );
		rpdtitle.hardlight( Window.TITLE_COLOR );
		add(rpdtitle);

		rpdtitle.x = (Camera.main.width - rpdtitle.width()) / 2;
		rpdtitle.y = rpd.y + rpd.height + 5;
		align(rpdtitle);

		RenderedTextMultiline rpdxtext = renderMultiline( TXT_RPD, 6 );
		rpdxtext.maxWidth((int)Math.min(Camera.main.width, 130));
		add( rpdxtext );

		rpdxtext.setPos((Camera.main.width - rpdxtext.width()) / 2, rpdtitle.y + rpdtitle.height() + 12);
		align(rpdxtext);

		RenderedTextMultiline rpdlink = renderMultiline( LNK_RPD, 8 );
		rpdlink.maxWidth(rpdxtext.maxWidth());
		rpdlink.hardlight( Window.TITLE_COLOR );
		add(rpdlink);

		rpdlink.setPos((Camera.main.width - rpdlink.width()) / 2, rpdxtext.bottom() + 6);
		align(rpdlink);

		PointerArea rpdhotArea = new PointerArea( rpdlink.left(), rpdlink.top(), rpdlink.width(), rpdlink.height() ) {
			@Override
			protected void onClick( PointerEvent event ) {
				DeviceCompat.openURI( "https://quasistellar.neocities.org/" );
			}
		};
		add( rpdhotArea );

		Image nyrds = Icons.NYRDS.get();
		nyrds.x = (Camera.main.width - nyrds.width()) / 2 - nyrds.width()*2;
		nyrds.y = rpdlink.bottom() + 13;
		align(nyrds);
		add(nyrds);

		Image shph = Icons.SHPX.get();
		shph.x = (Camera.main.width - shph.width()) / 2;
		shph.y = rpdlink.bottom() + 10;
		align(shph);
		add(shph);

		Image wata = Icons.WATA.get();
		wata.x = (Camera.main.width - wata.width()) / 2 + wata.width()*2;
		wata.y = rpdlink.bottom() + 12;
		align(wata);
		add(wata);

		
		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		RPD.switchNoFade(TitleScene.class);
	}
}
