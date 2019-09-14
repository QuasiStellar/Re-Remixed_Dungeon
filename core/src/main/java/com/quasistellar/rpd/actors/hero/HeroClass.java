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

package com.quasistellar.rpd.actors.hero;

import com.quasistellar.rpd.Assets;
import com.quasistellar.rpd.Badges;
import com.quasistellar.rpd.Challenges;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.items.BrokenSeal;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.items.MysticalSkull;
import com.quasistellar.rpd.items.armor.ClothArmor;
import com.quasistellar.rpd.items.artifacts.AlchemistsToolkit;
import com.quasistellar.rpd.items.artifacts.CloakOfShadows;
import com.quasistellar.rpd.items.bags.MagicalHolster;
import com.quasistellar.rpd.items.bags.PotionBandolier;
import com.quasistellar.rpd.items.bags.ScrollHolder;
import com.quasistellar.rpd.items.bags.VelvetPouch;
import com.quasistellar.rpd.items.food.Food;
import com.quasistellar.rpd.items.food.SmallRation;
import com.quasistellar.rpd.items.potions.PotionOfHaste;
import com.quasistellar.rpd.items.potions.PotionOfHealing;
import com.quasistellar.rpd.items.potions.PotionOfInvisibility;
import com.quasistellar.rpd.items.potions.PotionOfLiquidFlame;
import com.quasistellar.rpd.items.potions.PotionOfMindVision;
import com.quasistellar.rpd.items.potions.PotionOfStrength;
import com.quasistellar.rpd.items.potions.PotionOfToxicGas;
import com.quasistellar.rpd.items.scrolls.ScrollOfIdentify;
import com.quasistellar.rpd.items.scrolls.ScrollOfLullaby;
import com.quasistellar.rpd.items.scrolls.ScrollOfMagicMapping;
import com.quasistellar.rpd.items.scrolls.ScrollOfMirrorImage;
import com.quasistellar.rpd.items.scrolls.ScrollOfRage;
import com.quasistellar.rpd.items.scrolls.ScrollOfRemoveCurse;
import com.quasistellar.rpd.items.scrolls.ScrollOfTerror;
import com.quasistellar.rpd.items.scrolls.ScrollOfUpgrade;
import com.quasistellar.rpd.items.wands.WandOfMagicMissile;
import com.quasistellar.rpd.items.weapon.SpiritBow;
import com.quasistellar.rpd.items.weapon.melee.Dagger;
import com.quasistellar.rpd.items.weapon.melee.ElvenDagger;
import com.quasistellar.rpd.items.weapon.melee.Gloves;
import com.quasistellar.rpd.items.weapon.melee.HandAxe;
import com.quasistellar.rpd.items.weapon.melee.MagesStaff;
import com.quasistellar.rpd.items.weapon.melee.MeleeWeapon;
import com.quasistellar.rpd.items.weapon.melee.StoneSword;
import com.quasistellar.rpd.items.weapon.melee.WornShortsword;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingKnife;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingStone;
import com.quasistellar.rpd.items.weapon.missiles.Tomahawk;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.skills.Skill;
import com.quasistellar.rpd.skills.huntress.Flowerfire;
import com.quasistellar.rpd.skills.gnoll.Madness;
import com.quasistellar.rpd.skills.gnoll.Decoy;
import com.quasistellar.rpd.skills.gnoll.Disguise;
import com.quasistellar.rpd.skills.huntress.Blast;
import com.quasistellar.rpd.skills.huntress.Piercing;
import com.quasistellar.rpd.skills.mage.Devastation;
import com.quasistellar.rpd.skills.mage.Flock;
import com.quasistellar.rpd.skills.mage.Recharge;
import com.quasistellar.rpd.skills.necromancer.Deathling;
import com.quasistellar.rpd.skills.necromancer.Plunder;
import com.quasistellar.rpd.skills.necromancer.Revitalization;
import com.quasistellar.rpd.skills.rogue.Illusion;
import com.quasistellar.rpd.skills.rogue.Theft;
import com.quasistellar.rpd.skills.rogue.Bypass;
import com.quasistellar.rpd.skills.warrior.Battlecry;
import com.quasistellar.rpd.skills.warrior.Headbutt;
import com.quasistellar.rpd.skills.warrior.Overcoming;
import com.watabou.utils.Bundle;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	WARRIOR( "warrior", HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR ),
	MAGE( "mage", HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK ),
	ROGUE( "rogue", HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER ),
	HUNTRESS( "huntress", HeroSubClass.SNIPER, HeroSubClass.WARDEN ),
	ELF( "elf", HeroSubClass.SCOUT, HeroSubClass.ALCHEMIST ),
	NECROMANCER( "necromancer", HeroSubClass.LICH, HeroSubClass.SHADOWMASTER ),
	GNOLL( "gnoll", HeroSubClass.BRUTE, HeroSubClass.SHAMAN );

	private String title;
	private HeroSubClass[] subClasses;

	HeroClass( String title, HeroSubClass...subClasses ) {
		this.title = title;
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;

		initCommon( hero );

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case ELF:
				initElf( hero );
				break;

			case NECROMANCER:
				initNecromancer( hero );
				break;

			case GNOLL:
				initGnoll( hero );
				break;
		}
		
	}

	private static void initCommon( Hero hero ) {
		Item i = new ClothArmor().identify();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;

		i = new Food();
		if (!Challenges.isItemBlocked(i)) i.collect();

		if (Dungeon.isChallenged(Challenges.NO_FOOD)){
			new SmallRation().collect();
		}
		
		new ScrollOfIdentify().identify();

	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case ELF:
				return Badges.Badge.MASTERY_ELF;
			case NECROMANCER:
				return Badges.Badge.MASTERY_NECROMANCER;
			case GNOLL:
				return Badges.Badge.MASTERY_GNOLL;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}

		new PotionBandolier().collect();
		Dungeon.LimitedDrops.POTION_BANDOLIER.drop();

		new PotionOfHealing().identify();
		new ScrollOfRage().identify();

		hero.firstSkill = new Overcoming();
		hero.secondSkill = new Headbutt();
		hero.thirdSkill = new Battlecry();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;

		hero.MM = 100;
		
		staff = new MagesStaff(new WandOfMagicMissile());

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollHolder().collect();
		Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
		
		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();

		hero.firstSkill = new Recharge();
		hero.secondSkill = new Flock();
		hero.thirdSkill = new Devastation();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.misc1 = cloak).identify();
		hero.belongings.misc1.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();
		
		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();

		hero.firstSkill = new Theft();
		hero.secondSkill = new Bypass();
		hero.thirdSkill = new Illusion();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();

		Dungeon.quickslot.setSlot(0, bow);

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();

		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();

		hero.firstSkill = new Piercing();
		hero.secondSkill = new Flowerfire();
		hero.thirdSkill = new Blast();
	}

	private static void initElf( Hero hero ) {

		hero.baseSpeed = 1.25f;

		hero.HT = hero.HP = 15;

		(hero.belongings.weapon = new ElvenDagger()).identify();

		AlchemistsToolkit toolkit = new AlchemistsToolkit();
		(hero.belongings.misc1 = toolkit).identify();

		new PotionBandolier().collect();
		Dungeon.LimitedDrops.POTION_BANDOLIER.drop();

		new PotionOfHaste().identify();
		new ScrollOfMirrorImage().identify();

		hero.firstSkill = new Skill();
		hero.secondSkill = new Skill();
		hero.thirdSkill = new Skill();
	}

	private static void initNecromancer( Hero hero ) {

		(hero.belongings.weapon = new StoneSword()).identify();
		((MeleeWeapon)hero.belongings.weapon).affixSkull(new MysticalSkull());

		new ScrollHolder().collect();
		Dungeon.LimitedDrops.SCROLL_HOLDER.drop();

		new ScrollOfTerror().identify();
		new PotionOfToxicGas().identify();

		hero.firstSkill = new Deathling();
		hero.secondSkill = new Plunder();
		hero.thirdSkill = new Revitalization();
	}

	private static void initGnoll( Hero hero ) {

		hero.STR = 15;
		hero.MM = 25;
		hero.MP = 25;

		hero.belongings.armor = null;

		(hero.belongings.weapon = new HandAxe()).identify();
		Tomahawk tomahawks = new Tomahawk();
		tomahawks.quantity(5).collect();
		Dungeon.quickslot.setSlot(0, tomahawks);

		new MagicalHolster().collect();
		Dungeon.LimitedDrops.MAGICAL_HOLSTER.drop();

		new PotionOfStrength().identify();
		new ScrollOfRemoveCurse().identify();

		hero.firstSkill = new Madness();
		hero.secondSkill = new Decoy();
		hero.thirdSkill = new Disguise();
	}

	public String title() {
		return Messages.get(HeroClass.class, title);
	}
	
	public HeroSubClass[] subClasses() {
		return subClasses;
	}
	
	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.WARRIOR;
			case MAGE:
				return Assets.MAGE;
			case ROGUE:
				return Assets.ROGUE;
			case HUNTRESS:
				return Assets.HUNTRESS;
			case ELF:
				return Assets.ELF;
			case NECROMANCER:
				return Assets.NECROMANCER;
			case GNOLL:
				return Assets.GNOLL;
		}
	}
	
	public String[] perks() {
		switch (this) {
			case WARRIOR: default:
				return new String[]{
						Messages.get(HeroClass.class, "warrior_perk1"),
						Messages.get(HeroClass.class, "warrior_perk2"),
						Messages.get(HeroClass.class, "warrior_perk3"),
						Messages.get(HeroClass.class, "warrior_perk4"),
						Messages.get(HeroClass.class, "warrior_perk5"),
				};
			case MAGE:
				return new String[]{
						Messages.get(HeroClass.class, "mage_perk1"),
						Messages.get(HeroClass.class, "mage_perk2"),
						Messages.get(HeroClass.class, "mage_perk3"),
						Messages.get(HeroClass.class, "mage_perk4"),
						Messages.get(HeroClass.class, "mage_perk5"),
				};
			case ROGUE:
				return new String[]{
						Messages.get(HeroClass.class, "rogue_perk1"),
						Messages.get(HeroClass.class, "rogue_perk2"),
						Messages.get(HeroClass.class, "rogue_perk3"),
						Messages.get(HeroClass.class, "rogue_perk4"),
						Messages.get(HeroClass.class, "rogue_perk5"),
				};
			case HUNTRESS:
				return new String[]{
						Messages.get(HeroClass.class, "huntress_perk1"),
						Messages.get(HeroClass.class, "huntress_perk2"),
						Messages.get(HeroClass.class, "huntress_perk3"),
						Messages.get(HeroClass.class, "huntress_perk4"),
						Messages.get(HeroClass.class, "huntress_perk5"),
				};
			case ELF:
				return new String[]{
						Messages.get(HeroClass.class, "elf_perk1"),
						Messages.get(HeroClass.class, "elf_perk2"),
						Messages.get(HeroClass.class, "elf_perk3"),
						Messages.get(HeroClass.class, "elf_perk4"),
						Messages.get(HeroClass.class, "elf_perk5"),
				};
			case NECROMANCER:
				return new String[]{
						Messages.get(HeroClass.class, "necromancer_perk1"),
						Messages.get(HeroClass.class, "necromancer_perk2"),
						Messages.get(HeroClass.class, "necromancer_perk3"),
						Messages.get(HeroClass.class, "necromancer_perk4"),
						Messages.get(HeroClass.class, "necromancer_perk5"),
				};
			case GNOLL:
				return new String[]{
						Messages.get(HeroClass.class, "gnoll_perk1"),
						Messages.get(HeroClass.class, "gnoll_perk2"),
						Messages.get(HeroClass.class, "gnoll_perk3"),
						Messages.get(HeroClass.class, "gnoll_perk4"),
						Messages.get(HeroClass.class, "gnoll_perk5"),
				};
		}
	}
	
	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;
		
		switch (this){
			case WARRIOR: default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case ROGUE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case ELF:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ELF);
			case NECROMANCER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_NECROMANCER);
			case GNOLL:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_GNOLL);
		}
	}
	
	public String unlockMsg() {
		switch (this){
			case WARRIOR: default:
				return "";
			case MAGE:
				return Messages.get(HeroClass.class, "mage_unlock");
			case ROGUE:
				return Messages.get(HeroClass.class, "rogue_unlock");
			case HUNTRESS:
				return Messages.get(HeroClass.class, "huntress_unlock");
			case ELF:
				return Messages.get(HeroClass.class, "elf_unlock");
			case NECROMANCER:
				return Messages.get(HeroClass.class, "necromancer_unlock");
			case GNOLL:
				return Messages.get(HeroClass.class, "gnoll_unlock");
		}
	}

	private static final String CLASS	= "class";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( CLASS, toString() );
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( CLASS );
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
