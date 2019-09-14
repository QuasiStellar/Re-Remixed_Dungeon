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

package com.quasistellar.rpd.ui;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.actors.hero.HeroClass;
import com.watabou.noosa.Image;

public enum Icons {

	SKULL,
	BUSY,
	COMPASS,
	INFO,
	PREFS,
	WARNING,
	TARGET,
	MASTERY,
	WATA,
	WARRIOR,
	MAGE,
	ROGUE,
	HUNTRESS,
	ELF,
	NECROMANCER,
	GNOLL,
	CLOSE,
	DEPTH,
	SLEEP,
	ALERT,
	LOST,
	BACKPACK,
	SEED_POUCH,
	SCROLL_HOLDER,
	POTION_BANDOLIER,
	WAND_HOLSTER,
	CHECKED,
	UNCHECKED,
	EXIT,
	LANGS,
	CHALLENGE_OFF,
	CHALLENGE_ON,
	RESUME,
	ENTER,
	GOLD,
	RANKINGS,
	BADGES,
	CHANGES,
	SHPX,
	LIBGDX,
	RPD,
	NYRDS;

	public Image get() {
		return get( this );
	}

	public static Image get( Icons type ) {
		Image icon = new Image( Assets.ICONS );
		switch (type) {
			case SKULL:
				icon.frame( icon.texture.uvRect( 0, 0, 8, 8 ) );
				break;
			case BUSY:
				icon.frame( icon.texture.uvRect( 8, 0, 16, 8 ) );
				break;
			case COMPASS:
				icon.frame( icon.texture.uvRect( 0, 8, 7, 13 ) );
				break;
			case INFO:
				icon.frame( icon.texture.uvRect( 16, 0, 30, 14 ) );
				break;
			case PREFS:
				icon.frame( icon.texture.uvRect( 30, 0, 46, 16 ) );
				break;
			case WARNING:
				icon.frame( icon.texture.uvRect( 46, 0, 58, 12 ) );
				break;
			case TARGET:
				icon.frame( icon.texture.uvRect( 0, 13, 16, 29 ) );
				break;
			case MASTERY:
				icon.frame( icon.texture.uvRect( 16, 14, 30, 28 ) );
				break;
			case WATA:
				icon.frame( icon.texture.uvRect( 30, 16, 45, 26 ) );
				break;
			case WARRIOR:
				icon.frame( icon.texture.uvRect( 0, 29, 9, 44 ) );
				break;
			case MAGE:
				icon.frame( icon.texture.uvRect( 16, 29, 31, 43 ) );
				break;
			case ROGUE:
				icon.frame( icon.texture.uvRect( 32, 29, 41, 44 ) );
				break;
			case HUNTRESS:
				icon.frame( icon.texture.uvRect( 48, 29, 64, 45 ) );
				break;
			case ELF:
				icon.frame( icon.texture.uvRect( 64, 29, 79, 42 ) );
				break;
			case NECROMANCER:
				icon.frame( icon.texture.uvRect( 80, 29, 96, 40 ) );
				break;
			case GNOLL:
				icon.frame( icon.texture.uvRect( 96, 29, 109, 42 ) );
				break;
			case CLOSE:
				icon.frame( icon.texture.uvRect( 0, 45, 13, 58 ) );
				break;
			case DEPTH:
				icon.frame( icon.texture.uvRect( 38, 46, 54, 62 ) );
				break;
			case SLEEP:
				icon.frame( icon.texture.uvRect( 13, 45, 22, 53 ) );
				break;
			case ALERT:
				icon.frame( icon.texture.uvRect( 22, 45, 30, 53 ) );
				break;
			case LOST:
				icon.frame( icon.texture.uvRect( 30, 45, 38, 53 ) );
				break;
			case BACKPACK:
				icon.frame( icon.texture.uvRect( 58, 0, 68, 10 ) );
				break;
			case SCROLL_HOLDER:
				icon.frame( icon.texture.uvRect( 68, 0, 78, 10 ) );
				break;
			case SEED_POUCH:
				icon.frame( icon.texture.uvRect( 78, 0, 88, 10 ) );
				break;
			case WAND_HOLSTER:
				icon.frame( icon.texture.uvRect( 88, 0, 98, 10 ) );
				break;
			case POTION_BANDOLIER:
				icon.frame( icon.texture.uvRect( 98, 0, 108, 10 ) );
				break;
			case CHECKED:
				icon.frame( icon.texture.uvRect( 54, 12, 66, 24 ) );
				break;
			case UNCHECKED:
				icon.frame( icon.texture.uvRect( 66, 12, 78, 24 ) );
				break;
			case EXIT:
				icon.frame( icon.texture.uvRect( 108, 0, 124, 16 ) );
				break;
			case LANGS:
				icon.frame( icon.texture.uvRect( 112, 32, 124, 41 ) );
				break;
			case CHALLENGE_OFF:
				icon.frame( icon.texture.uvRect( 78, 12, 92, 24 ) );
				break;
			case CHALLENGE_ON:
				icon.frame( icon.texture.uvRect( 92, 12, 108, 24 ) );
				break;
			case RESUME:
				icon.frame( icon.texture.uvRect( 13, 53, 24, 64 ) );
				break;
			case ENTER:
				icon.frame( icon.texture.uvRect( 0, 64, 17, 81 ) );
				break;
			case GOLD:
				icon.frame( icon.texture.uvRect( 83, 26, 100, 42 ) );
				break;
			case RANKINGS:
				icon.frame( icon.texture.uvRect( 100, 26, 117, 43 ) );
				break;
			case BADGES:
				icon.frame( icon.texture.uvRect( 66, 46, 83, 63 ) );
				break;
			case CHANGES:
				icon.frame( icon.texture.uvRect( 112, 16, 122, 27 ) );
				break;
			case SHPX:
				icon.frame( icon.texture.uvRect( 112, 48, 126, 62 ) );
				break;
			case LIBGDX:
				icon.frame( icon.texture.uvRect( 96, 48, 112, 61 ) );
				break;
			case RPD:
				icon.frame( icon.texture.uvRect( 64, 48, 78, 61 ) );
				break;
			case NYRDS:
				icon.frame( icon.texture.uvRect( 80, 48, 95, 57 ) );
				break;
		}
		return icon;
	}

	public static Image get( HeroClass cl ) {
		switch (cl) {
		case WARRIOR:
			return get( WARRIOR );
		case MAGE:
			return get( MAGE );
		case ROGUE:
			return get( ROGUE );
		case HUNTRESS:
			return get( HUNTRESS );
		case ELF:
			return get( ELF );
		case NECROMANCER:
			return get( NECROMANCER );
		case GNOLL:
			return get( GNOLL );
		default:
			return null;
		}
	}
}
