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
import com.quasistellar.rpd.Bones;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Actor;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.blobs.Fire;
import com.quasistellar.rpd.actors.buffs.Burning;
import com.quasistellar.rpd.actors.mobs.Mob;
import com.quasistellar.rpd.actors.mobs.npcs.Citizen;
import com.quasistellar.rpd.actors.mobs.npcs.Cleric;
import com.quasistellar.rpd.actors.mobs.npcs.FortuneTeller;
import com.quasistellar.rpd.actors.mobs.npcs.PlagueDoctor;
import com.quasistellar.rpd.actors.mobs.npcs.Shopkeeper;
import com.quasistellar.rpd.actors.mobs.npcs.TownGuard;
import com.quasistellar.rpd.effects.particles.FlameParticle;
import com.quasistellar.rpd.items.Heap;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.tiles.CustomTilemap;
import com.quasistellar.rpd.tiles.DungeonTilemap;
import com.watabou.noosa.Group;
import com.watabou.noosa.Tilemap;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.PointF;

public class TownLevel extends Level {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public String tilesTex() {
		return Assets.TILES_TOWN;
	}

	@Override
	public String waterTex() {
		return Assets.WATER_SEWERS;
	}

	@Override
	protected boolean build() {

		setSize(32, 32);

		map = MAP_TOWN.clone();

        CustomTilemap vis = new townBehind();
        vis.pos(0, 0);
        customTiles.add(vis);
        //((GameScene) RPD.scene()).addCustomTile(vis);

        vis = new townAbove();
        vis.pos(0, 0);
        customWalls.add(vis);
        //((GameScene) RPD.scene()).addCustomWall(vis);

		buildFlagMaps();
		cleanWalls();

		entrance = 0;
		exit = 28+3*32;

		return true;
	}
	
	@Override
	public Mob createMob() {
		return null;
	}
	
	@Override
	protected void createMobs() {
		PlagueDoctor doctor = new PlagueDoctor();
		doctor.pos = 11*32+16;
		mobs.add( doctor );
		FortuneTeller teller = new FortuneTeller();
		teller.pos = 23*32+5;
		mobs.add( teller );
		Cleric cleric= new Cleric();
		cleric.pos = 17*32+9;
		mobs.add( cleric);
		Shopkeeper keeper = new Shopkeeper();
		keeper.pos = 21*32+21;
		mobs.add( keeper );
		TownGuard guard1 = new TownGuard();
		guard1.pos = 10*32+21;
		mobs.add(guard1);
		TownGuard guard2 = new TownGuard();
		guard2.pos = 10*32+24;
		mobs.add(guard2);
		TownGuard guard3 = new TownGuard();
		guard3.pos = 25*32+23;
		mobs.add(guard3);
		TownGuard guard4 = new TownGuard();
		guard4.pos = 14*32+4;
		mobs.add(guard4);
		for (int i=0; i<10; i++) {
            Citizen citizen = new Citizen();
            do {
                citizen.pos = randomRespawnCell();
            } while (MAP_TOWN_INFO[citizen.pos].equals("door") || MAP_TOWN_INFO[citizen.pos].equals("fireplace") ||
				citizen.pos == 11*32+16 || citizen.pos == 23*32+5 || citizen.pos == 17*32+9 || citizen.pos == 21*32+21 ||
				citizen.pos == 10*32+21 || citizen.pos == 10*32+24 || citizen.pos == 25*32+23 || citizen.pos == 14*32+4 ||
				citizen.pos == 16+16*32);
            mobs.add( citizen );
        }
	}

	@Override
	protected void createItems() {
		Item item = Bones.get();
		if (item != null) {
			drop( item, randomRespawnCell() ).setHauntedIfCursed(1f).type = Heap.Type.REMAINS;
		}
	}

	public Actor respawner() {
		return null;
	}

	public Actor reigniter() {
		return new Actor() {
			@Override
			protected boolean act() {
				spend(1f);
				Fire.burn(12*32+16);
				return true;
			}
		};
	}

	@Override
	public Group addVisuals() {
		super.addVisuals();

		visuals.add( new FireEmitter( 12*32+16 ) );
		return visuals;
	}

	@Override
	public void press( int cell, Char ch ) {
		super.press( cell, ch );
		if (MAP_TOWN_INFO[cell].equals("snow")) {
			Burning burning = ch.buff(Burning.class);
			if (burning != null){
				burning.detach();
			}
		}
	}

	@Override
	public String tileNameByCell( int tile ) {
		return Messages.get(TownLevel.class, MAP_TOWN_INFO[tile] + "_name");
	}

	@Override
	public String tileDescByCell(int tile) {
		return Messages.get(TownLevel.class, MAP_TOWN_INFO[tile] + "_desc");
	}

	private static final int W = Terrain.WALL;
	private static final int D = Terrain.DOOR;
	private static final int e = Terrain.EMPTY;
	private static final int X = Terrain.EXIT;
	private static final int S = Terrain.STATUE;

	private static final int[] MAP_TOWN =
			{       W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
					W, W, W, W, W, W, W, S, S, S, S, S, S, S, S, S, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
					W, W, W, W, W, W, S, S, e, e, e, e, S, S, S, S, W, W, W, W, W, W, W, W, e, e, W, e, e, e, W, W,
					W, W, W, W, W, W, S, e, e, e, e, e, S, S, e, S, W, W, W, W, W, W, W, e, e, e, e, e, X, e, W, W,
					W, W, W, W, S, S, S, e, e, e, e, S, e, e, S, S, S, W, W, W, W, W, W, e, e, e, W, e, e, e, W, W,
					W, W, W, W, S, e, e, e, e, e, e, e, e, e, S, S, S, W, W, W, W, W, W, e, e, e, e, e, e, W, W, W,
					W, W, W, W, S, e, e, W, W, W, W, W, e, e, e, S, S, W, W, W, W, W, e, e, e, e, e, e, e, e, W, W,
					W, W, W, W, S, e, S, W, e, e, e, W, e, e, e, e, S, S, S, S, W, e, e, e, e, e, e, e, W, e, e, W,
					W, W, W, W, S, e, e, W, e, e, e, W, e, e, e, e, e, e, e, e, W, e, e, e, e, e, e, e, e, e, e, W,
					W, W, W, W, S, e, e, W, e, e, e, W, e, e, e, e, e, e, e, e, W, W, e, e, W, W, W, W, W, W, e, W,
					W, W, W, W, S, S, e, W, e, e, e, W, e, e, e, e, e, e, e, S, e, e, e, e, e, e, e, e, e, W, W, W,
					W, W, W, W, S, e, e, W, e, e, e, W, e, e, S, e, e, e, S, e, S, e, e, e, e, e, e, e, e, S, S, W,
					W, W, W, W, S, e, e, W, e, e, e, W, e, e, S, e, e, e, S, e, e, e, e, e, e, e, e, e, e, e, e, W,
					W, W, S, S, S, e, e, W, e, e, e, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W,
					W, W, S, e, e, e, e, W, e, e, e, W, S, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W,
					W, W, S, e, e, e, e, W, W, D, W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W,
					W, W, S, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W,
					W, S, S, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W,
					W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W, W, W, W, W, W, e, e, e, e, e, e, W,
					W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, W,
					W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, W,
					W, e, e, W, W, W, W, W, W, W, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, W,
					W, e, e, W, S, S, e, e, S, W, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, W, e, W, W, W, W, W,
					W, e, e, W, e, e, S, e, e, D, e, e, e, e, e, e, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, W,
					W, e, e, W, e, e, e, e, e, W, e, e, e, e, e, e, e, e, W, W, W, D, W, W, W, e, e, e, S, e, e, W,
					W, S, e, W, W, W, W, W, W, W, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, S, e, e, e, W,
					W, e, S, S, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, S, e, S, S, S, W, W,
					W, S, S, S, S, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, e, W, W, W, W, W, S, S, S, S, W, W,
					W, W, W, W, W, e, e, e, e, e, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
					W, W, W, W, W, e, e, e, e, e, e, e, e, e, e, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
					W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W,
					W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W};

	private static final String Q = "snow";
	private static final String R = "wall";
	private static final String T = "tree";
	private static final String Y = "snowdrift";
	private static final String U = "fireplace";
	private static final String I = "shelves";
	private static final String O = "table";
	private static final String P = "sewers_wall";
	private static final String A = "mud";
	private static final String F = "portal";
	private static final String G = "exit";
	private static final String H = "floor";
	private static final String J = "bed";
	private static final String K = "door";
	private static final String L = "wooden_floor";
	private static final String Z = "fences";

	private static final String[] MAP_TOWN_INFO =
			{       Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q,
					Q, Q, Q, Q, Q, Q, Q, R, R, R, R, R, R, R, R, R, Q, Q, Q, Q, Q, Q, Q, P, P, P, P, P, P, P, P, Q,
					Q, Q, Q, Q, Q, Q, R, R, Q, Q, Q, Q, T, T, T, R, Q, Q, Q, Q, Q, Q, P, P, H, H, P, H, H, H, P, Q,
					Q, Q, Q, Q, Q, Q, R, Q, Q, Q, Q, Q, T, T, Q, R, Q, Q, Q, Q, Q, Q, P, H, H, H, H, H, G, H, P, Q,
					Q, Q, Q, Q, R, R, R, Q, Q, Q, Q, T, Q, Q, T, R, R, Q, Q, Q, Q, Q, P, H, H, H, P, H, H, H, P, Q,
					Q, Q, Q, Q, R, Q, Q, Q, Q, Q, Q, Q, Q, Q, T, T, R, Q, Q, Q, Q, P, P, H, H, H, H, H, H, P, P, Q,
					Q, Q, Q, Q, R, Q, Q, R, R, R, R, R, Q, Q, Q, T, R, Q, Q, Q, P, P, H, H, H, H, H, H, H, H, P, P,
					Q, Q, Q, Q, R, Q, T, R, O, O, J, R, Q, Q, Q, Q, R, R, R, R, R, Q, H, H, H, H, H, H, F, H, H, P,
					Q, Q, Q, Q, R, Q, Q, R, H, H, J, R, Y, Y, Q, Q, Q, Q, Q, Q, R, Q, Q, Q, Q, H, H, H, H, H, H, P,
					Q, Q, Q, Q, R, Q, Q, R, H, H, H, R, Y, Y, Q, Q, Q, Q, Q, Q, R, R, Q, Q, R, R, R, R, R, R, H, P,
					Q, Q, Q, Q, R, T, Q, R, H, H, H, R, Q, Q, Q, Q, Q, Q, Q, T, Q, Q, Q, Q, Q, Q, Q, T, T, R, P, P,
					Q, Q, Q, Q, R, Q, Q, R, H, H, H, R, Q, Q, Z, Q, Q, Q, Z, Q, T, Q, Q, Q, Q, Q, T, T, Q, R, R, R,
					Q, Q, Q, Q, R, Q, Q, R, H, H, H, R, Q, Q, Z, Q, U, Q, Z, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, R,
					Q, Q, R, R, R, Q, Q, R, H, H, H, R, R, Q, Q, Q, A, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, R,
					Q, Q, R, Q, Q, Q, Q, R, H, H, H, R, R, Q, Q, A, A, A, Q, Q, Q, Q, Q, Q, A, A, A, A, Q, Q, A, R,
					Q, Q, R, Q, Q, Q, Q, R, R, K, R, R, Q, Q, Q, A, A, A, A, A, Q, Q, Q, Q, Q, Q, A, A, A, A, A, R,
					Q, Q, R, Q, Q, Q, Q, Q, Q, A, Q, Q, Q, Q, A, A, A, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, A, A, A, R,
					R, R, R, Q, Q, Q, Q, Q, Q, A, A, Q, Q, A, A, A, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, A, A, R,
					R, Q, Q, Q, Q, Q, Q, Q, Q, Q, A, A, A, A, A, Q, Q, Q, R, I, I, I, I, I, R, Q, Q, Q, Q, Q, A, R,
					R, Q, Y, Y, Q, Q, Q, Q, Q, Q, Q, A, A, A, A, Q, Q, Q, R, L, L, L, L, L, R, Q, Q, Q, Q, Q, Q, R,
					R, Q, Y, Y, Q, Q, Q, Q, Q, Q, Q, Q, A, A, Q, Q, Q, Q, R, L, L, L, L, L, R, Q, Q, Q, Q, Q, Q, R,
					R, Q, Q, R, R, R, R, R, R, R, Q, Q, Q, A, A, Q, Q, Q, R, L, L, L, L, L, R, Q, Q, Q, Q, Q, Q, R,
					R, Q, Q, R, O, O, L, L, L, R, Q, Q, A, A, A, Q, Q, Q, R, L, L, L, L, L, R, R, K, R, R, R, R, R,
					R, Q, Q, R, L, L, O, L, L, K, Q, A, A, Q, A, A, Q, Q, R, L, L, L, L, L, R, Q, Q, Q, Q, Q, Q, R,
					R, Q, Q, R, L, L, L, L, L, R, Q, Q, Q, Q, Q, A, A, Q, R, R, R, K, R, R, R, Q, Q, Q, T, Y, Y, R,
					R, T, Q, R, R, R, R, R, R, R, Q, Q, Q, Q, Q, Q, A, A, Q, Q, Q, Q, Q, Q, Q, Q, Q, T, Q, Y, Y, R,
					R, Q, T, T, Q, Q, Q, Q, Q, Q, Q, Y, Y, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, T, Q, T, T, T, R, R,
					R, T, T, T, T, Q, Q, Q, Q, Q, Q, Y, Y, Q, Q, Q, Q, Q, Q, Q, Q, R, R, R, R, R, T, T, T, T, R, Q,
					R, R, R, R, R, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, R, R, R, R, R, R, R, Q, Q, Q, R, R, R, R, R, R, Q,
					Q, Q, Q, Q, R, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, R, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q,
					Q, Q, Q, Q, R, R, R, R, R, R, R, R, R, R, R, R, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q,
					Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q, Q};

    public static class townBehind extends CustomTilemap {

        {
            texture = Assets.TOWN_BEHIND;

            tileW = 32;
            tileH = 32;
        }

        final int TEX_WIDTH = 32*16;

        @Override
        public Tilemap create() {

            Tilemap v = super.create();

            int[] data = mapSimpleImage(0, 0, TEX_WIDTH);

            v.map(data, tileW);
            return v;
        }

    }

    public static class townAbove extends CustomTilemap {

        {
            texture = Assets.TOWN_ABOVE;

            tileW = 32;
            tileH = 32;
        }

        final int TEX_WIDTH = 32*16;

        @Override
        public Tilemap create() {

            Tilemap v = super.create();

            int[] data = mapSimpleImage(0, 0, TEX_WIDTH);

            v.map(data, tileW);
            return v;
        }

    }

	private static class FireEmitter extends Emitter {

		private int pos;

		public FireEmitter(int pos ) {
			super();

			this.pos = pos;

			PointF p = DungeonTilemap.tileCenterToWorld( pos );
			pos( p.x - 6, p.y - 6, 12, 12 );

			pour( FlameParticle.FACTORY, 0.06f );
		}

		@Override
		public void update() {
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				super.update();
			}
		}
	}

}
