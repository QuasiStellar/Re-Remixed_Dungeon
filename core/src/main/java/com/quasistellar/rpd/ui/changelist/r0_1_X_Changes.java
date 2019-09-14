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

package com.quasistellar.rpd.ui.changelist;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.items.Stylus;
import com.quasistellar.rpd.items.weapon.melee.Quarterstaff;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.ChangesScene;
import com.quasistellar.rpd.sprites.ItemSprite;
import com.quasistellar.rpd.ui.Icons;
import com.quasistellar.rpd.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class r0_1_X_Changes {
	
	//just the one update this time
	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_r0_1_0_Changes(changeInfos);
	}
	
	public static void add_r0_1_0_Changes( ArrayList<ChangeInfo> changeInfos ){
		ChangeInfo changes = new ChangeInfo("v0.1", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(Icons.get(Icons.RPD), "Developer Commentary",
				"_-_ Released September 11th, 2019\n" +
				"_-_ 32 days after the beginning of development\n" +
				"\n" +
				"Dev commentary will be added here in the future."));
		
		changes.addButton( new ChangeButton(new Image(Assets.SKILLS, 36, 90, 18, 18), "3 new classes!",
				"Three new heroes appeared!\n\n" +
				"_-_ Elf\n" +
				"_-_ Necromancer\n" +
				"_-_ Gnoll\n\n" +
				"Every hero has unique abilities, skills and play style."));
		
		changes.addButton( new ChangeButton(new Image(Assets.TILES_NECROPOLIS, 16, 96, 16, 16), "Alternative locations!",
				"There is a chance for an alterlocation to generate instead of a normal chapter\n\n" +
					"_-_ Necropolis for levels 6-9\n\n" +
					"_-_ Ice Caves for levels 11-14\n\n" +
					"_-_ Spider Nest for levels 16-19"));
		
		changes.addButton( new ChangeButton(new Image(Assets.SKILLS, 0, 108, 18, 18), "Skills!",
				"There are 18 skills in the game currently.\n\n" +
						"_-_ Warrior: Overcoming, Headbutt, Battlecry\n\n" +
						"_-_ Mage: Recharge, Flock, Devastation\n\n" +
						"_-_ Rogue: Theft, Bypass, Illusion\n\n" +
						"_-_ Huntress: Piercing, Flowerfire, Blast\n\n" +
						"_-_ Necromancer: Deathling, Plunder, Revitalization\n\n" +
						"_-_ Gnoll: Madness, Decoy, Disguise\n\n"));

		changes.addButton( new ChangeButton(new Image(Assets.SKILLS, 0, 18, 18, 18), "Mana!",
				"Skills need mana to work.\n" +
						"Each hero class has its own way of gaining mana:\n\n" +
						"_-_ Warrior can convert some health into mana\n\n" +
						"_-_ Mage regenerates it over time\n\n" +
						"_-_ Rogue gain mana while being invisible and lose it over time\n\n" +
						"_-_ Huntress relies on her dew vial\n\n" +
						"_-_ Necromancer uses his Mystical Skull\n\n" +
						"_-_ Gnoll's mana is closely linked with his satiety"));

		changes.addButton( new ChangeButton(new Image(Assets.DOCTOR, 0, 0, 16, 16), "Overworld town!",
				"Each run start in the town on the surface.\n" +
						"Town provides access to 4 NPCs:\n\n" +
						"_-_ Cleric - healing and cleansing services\n" +
						"_-_ Fortune Teller - identify your gear\n" +
						"_-_ Plague Doctor - complete quests, get rewards\n" +
						"_-_ Shopkeeper - sell useless stuff for gold"));

	}
	
}
