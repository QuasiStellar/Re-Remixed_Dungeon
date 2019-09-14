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

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.blobs.Blob;
import com.quasistellar.rpd.levels.Terrain;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.PixelScene;
import com.quasistellar.rpd.tiles.CustomTilemap;
import com.quasistellar.rpd.tiles.DungeonTerrainTilemap;
import com.quasistellar.rpd.tiles.DungeonTilemap;
import com.quasistellar.rpd.ui.RenderedTextMultiline;
import com.quasistellar.rpd.ui.Window;
import com.watabou.noosa.Image;

public class WndInfoCell extends Window {
	
	private static final float GAP	= 2;
	
	private static final int WIDTH = 120;
	
	public WndInfoCell( int cell ) {
		
		super();
		
		int tile = Dungeon.level.map[cell];
		if (Dungeon.level.water[cell]) {
			tile = Terrain.WATER;
		} else if (Dungeon.level.pit[cell]) {
			tile = Terrain.CHASM;
		}

		CustomTilemap customTile = null;
		Image customImage = null;
		int x = cell % Dungeon.level.width();
		int y = cell / Dungeon.level.width();
		for (CustomTilemap i : Dungeon.level.customTiles){
			if ((x >= i.tileX && x < i.tileX+i.tileW) &&
					(y >= i.tileY && y < i.tileY+i.tileH)){
				if ((customImage = i.image(x - i.tileX, y - i.tileY)) != null) {
					x -= i.tileX;
					y -= i.tileY;
					customTile = i;
					break;
				}
			}
		}


		String desc = "";

		IconTitle titlebar = new IconTitle();
		if (customTile != null){
			titlebar.icon(customImage);

			String customName = customTile.name(x, y);
			if (customName != null) {
				titlebar.label(customName);
			} else if (Dungeon.depth == 0) {
				titlebar.label(Dungeon.level.tileNameByCell(cell));
			} else {
				titlebar.label(Dungeon.level.tileName(tile));
			}

			String customDesc = customTile.desc(x, y);
			if (customDesc != null) {
				desc += customDesc;
			} else if (Dungeon.depth == 0) {
				desc += Dungeon.level.tileDescByCell(cell);
			} else {
				desc += Dungeon.level.tileDesc(tile);
			}

		} else {

			if (tile == Terrain.WATER) {
				Image water = new Image(Dungeon.level.waterTex());
				water.frame(0, 0, DungeonTilemap.SIZE, DungeonTilemap.SIZE);
				titlebar.icon(water);
			} else {
				titlebar.icon(DungeonTerrainTilemap.tile( cell, tile ));
			}
			titlebar.label(Dungeon.level.tileName(tile));
			desc += Dungeon.level.tileDesc(tile);

		}
		titlebar.setRect(0, 0, WIDTH, 0);
		add(titlebar);

		RenderedTextMultiline info = PixelScene.renderMultiline(6);
		add(info);

		for (Blob blob:Dungeon.level.blobs.values()) {
			if (blob.volume > 0 && blob.cur[cell] > 0 && blob.tileDesc() != null) {
				if (desc.length() > 0) {
					desc += "\n\n";
				}
				desc += blob.tileDesc();
			}
		}
		
		info.text( desc.length() == 0 ? Messages.get(this, "nothing") : desc );
		info.maxWidth(WIDTH);
		info.setPos(titlebar.left(), titlebar.bottom() + GAP);
		
		resize( WIDTH, (int)(info.top() + info.height()) );
	}
}
