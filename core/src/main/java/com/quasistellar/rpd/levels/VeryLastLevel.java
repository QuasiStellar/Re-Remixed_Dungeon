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

package com.quasistellar.rpd.levels;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.items.Amulet;
import com.quasistellar.rpd.levels.painters.Painter;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.tiles.CustomTilemap;
import com.watabou.noosa.Group;
import com.watabou.noosa.Tilemap;
import com.watabou.utils.Random;

public class VeryLastLevel extends Level {
	
	{
		color1 = 0x801500;
		color2 = 0xa68521;
	}
	
	@Override
	public String tilesTex() {
		return Assets.TILES_HALLS;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_HALLS;
	}
	
	@Override
	protected boolean build() {
		
		setSize(9, 9);

		Painter.fill( this, 1, 1, 7, 7, Terrain.WATER );
		Painter.fill( this, 2, 2, 5, 5, Terrain.EMPTY );
		Painter.fill( this, 3, 3, 3, 3, Terrain.EMPTY_SP );

		Painter.set(this, 3, 4, Terrain.STATUE_SP);
		Painter.set(this, 4, 4, Terrain.PEDESTAL);
		Painter.set(this, 5, 4, Terrain.STATUE_SP);
		
		entrance = 0;
		
		exit = 0;

		int x = Random.Int(2, 7);
		int y = (x == 2 || x == 6) ? Random.Int(2, 7) : Random.Int(2)*4+2;
		YendorSkeleton s = new YendorSkeleton();
		s.pos(x, y);
		this.customTiles.add(s);
		Painter.set(this, 2, 3, Terrain.EMPTY_DECO);
		
		return true;
	}
	
	@Override
	public Mob createMob() {
		return null;
	}
	
	@Override
	protected void createMobs() {
	}

	public Actor respawner() {
		return null;
	}

	@Override
	protected void createItems() {
		drop(new Amulet(), width*4 + 4);
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HallsLevel.class, "water_name");
			case Terrain.GRASS:
				return Messages.get(HallsLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(HallsLevel.class, "high_grass_name");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HallsLevel.class, "statue_name");
			default:
				return super.tileName( tile );
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(HallsLevel.class, "water_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(HallsLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(HallsLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}

	@Override
	public Group addVisuals () {
		super.addVisuals();
		HallsLevel.addHallsVisuals(this, visuals);
		return visuals;
	}

	public static class YendorSkeleton extends CustomTilemap {

		{
			texture = Assets.YENDOR_SKELETON;

			tileW = tileH = 1;
		}

		final int TEX_WIDTH = 16;

		@Override
		public Tilemap create() {
			Tilemap v = super.create();
			v.map(mapSimpleImage(0, 0, TEX_WIDTH), 1);
			return v;
		}

		@Override
		public String name(int tileX, int tileY) {
			return Messages.get(VeryLastLevel.class, "name");
		}

		@Override
		public String desc(int tileX, int tileY) {
			return Messages.get(VeryLastLevel.class, "desc");
		}
	}
}
