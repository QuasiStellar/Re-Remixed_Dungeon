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

package com.quasistellar.rpd.items;

import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.RPD;
import com.quasistellar.rpd.items.armor.Armor;
import com.quasistellar.rpd.items.armor.ClothArmor;
import com.quasistellar.rpd.items.armor.LeatherArmor;
import com.quasistellar.rpd.items.armor.MailArmor;
import com.quasistellar.rpd.items.armor.PlateArmor;
import com.quasistellar.rpd.items.armor.ScaleArmor;
import com.quasistellar.rpd.items.artifacts.AlchemistsToolkit;
import com.quasistellar.rpd.items.artifacts.Artifact;
import com.quasistellar.rpd.items.artifacts.CapeOfThorns;
import com.quasistellar.rpd.items.artifacts.ChaliceOfBlood;
import com.quasistellar.rpd.items.artifacts.CloakOfShadows;
import com.quasistellar.rpd.items.artifacts.DriedRose;
import com.quasistellar.rpd.items.artifacts.EtherealChains;
import com.quasistellar.rpd.items.artifacts.HornOfPlenty;
import com.quasistellar.rpd.items.artifacts.LloydsBeacon;
import com.quasistellar.rpd.items.artifacts.MasterThievesArmband;
import com.quasistellar.rpd.items.artifacts.SandalsOfNature;
import com.quasistellar.rpd.items.artifacts.TalismanOfForesight;
import com.quasistellar.rpd.items.artifacts.TimekeepersHourglass;
import com.quasistellar.rpd.items.artifacts.UnstableSpellbook;
import com.quasistellar.rpd.items.bags.Bag;
import com.quasistellar.rpd.items.food.Food;
import com.quasistellar.rpd.items.food.MysteryMeat;
import com.quasistellar.rpd.items.food.Pasty;
import com.quasistellar.rpd.items.potions.Potion;
import com.quasistellar.rpd.items.potions.PotionOfExperience;
import com.quasistellar.rpd.items.potions.PotionOfFrost;
import com.quasistellar.rpd.items.potions.PotionOfHaste;
import com.quasistellar.rpd.items.potions.PotionOfHealing;
import com.quasistellar.rpd.items.potions.PotionOfInvisibility;
import com.quasistellar.rpd.items.potions.PotionOfLevitation;
import com.quasistellar.rpd.items.potions.PotionOfLiquidFlame;
import com.quasistellar.rpd.items.potions.PotionOfMindVision;
import com.quasistellar.rpd.items.potions.PotionOfParalyticGas;
import com.quasistellar.rpd.items.potions.PotionOfPurity;
import com.quasistellar.rpd.items.potions.PotionOfStrength;
import com.quasistellar.rpd.items.potions.PotionOfToxicGas;
import com.quasistellar.rpd.items.rings.Ring;
import com.quasistellar.rpd.items.rings.RingOfAccuracy;
import com.quasistellar.rpd.items.rings.RingOfElements;
import com.quasistellar.rpd.items.rings.RingOfEnergy;
import com.quasistellar.rpd.items.rings.RingOfEvasion;
import com.quasistellar.rpd.items.rings.RingOfForce;
import com.quasistellar.rpd.items.rings.RingOfFuror;
import com.quasistellar.rpd.items.rings.RingOfHaste;
import com.quasistellar.rpd.items.rings.RingOfMight;
import com.quasistellar.rpd.items.rings.RingOfSharpshooting;
import com.quasistellar.rpd.items.rings.RingOfTenacity;
import com.quasistellar.rpd.items.rings.RingOfWealth;
import com.quasistellar.rpd.items.scrolls.Scroll;
import com.quasistellar.rpd.items.scrolls.ScrollOfIdentify;
import com.quasistellar.rpd.items.scrolls.ScrollOfLullaby;
import com.quasistellar.rpd.items.scrolls.ScrollOfMagicMapping;
import com.quasistellar.rpd.items.scrolls.ScrollOfMirrorImage;
import com.quasistellar.rpd.items.scrolls.ScrollOfRage;
import com.quasistellar.rpd.items.scrolls.ScrollOfRecharging;
import com.quasistellar.rpd.items.scrolls.ScrollOfRemoveCurse;
import com.quasistellar.rpd.items.scrolls.ScrollOfRetribution;
import com.quasistellar.rpd.items.scrolls.ScrollOfTeleportation;
import com.quasistellar.rpd.items.scrolls.ScrollOfTerror;
import com.quasistellar.rpd.items.scrolls.ScrollOfTransmutation;
import com.quasistellar.rpd.items.scrolls.ScrollOfUpgrade;
import com.quasistellar.rpd.items.stones.Runestone;
import com.quasistellar.rpd.items.stones.StoneOfAffection;
import com.quasistellar.rpd.items.stones.StoneOfAggression;
import com.quasistellar.rpd.items.stones.StoneOfAugmentation;
import com.quasistellar.rpd.items.stones.StoneOfBlast;
import com.quasistellar.rpd.items.stones.StoneOfBlink;
import com.quasistellar.rpd.items.stones.StoneOfClairvoyance;
import com.quasistellar.rpd.items.stones.StoneOfDeepenedSleep;
import com.quasistellar.rpd.items.stones.StoneOfDisarming;
import com.quasistellar.rpd.items.stones.StoneOfEnchantment;
import com.quasistellar.rpd.items.stones.StoneOfFlock;
import com.quasistellar.rpd.items.stones.StoneOfIntuition;
import com.quasistellar.rpd.items.stones.StoneOfShock;
import com.quasistellar.rpd.items.wands.Wand;
import com.quasistellar.rpd.items.wands.WandOfBlastWave;
import com.quasistellar.rpd.items.wands.WandOfCorrosion;
import com.quasistellar.rpd.items.wands.WandOfCorruption;
import com.quasistellar.rpd.items.wands.WandOfDisintegration;
import com.quasistellar.rpd.items.wands.WandOfFireblast;
import com.quasistellar.rpd.items.wands.WandOfFrost;
import com.quasistellar.rpd.items.wands.WandOfLightning;
import com.quasistellar.rpd.items.wands.WandOfLivingEarth;
import com.quasistellar.rpd.items.wands.WandOfMagicMissile;
import com.quasistellar.rpd.items.wands.WandOfPrismaticLight;
import com.quasistellar.rpd.items.wands.WandOfRegrowth;
import com.quasistellar.rpd.items.wands.WandOfTransfusion;
import com.quasistellar.rpd.items.wands.WandOfWarding;
import com.quasistellar.rpd.items.weapon.melee.AssassinsBlade;
import com.quasistellar.rpd.items.weapon.melee.BattleAxe;
import com.quasistellar.rpd.items.weapon.melee.Crossbow;
import com.quasistellar.rpd.items.weapon.melee.Dagger;
import com.quasistellar.rpd.items.weapon.melee.Dirk;
import com.quasistellar.rpd.items.weapon.melee.Flail;
import com.quasistellar.rpd.items.weapon.melee.Gauntlet;
import com.quasistellar.rpd.items.weapon.melee.Glaive;
import com.quasistellar.rpd.items.weapon.melee.Gloves;
import com.quasistellar.rpd.items.weapon.melee.Greataxe;
import com.quasistellar.rpd.items.weapon.melee.Greatshield;
import com.quasistellar.rpd.items.weapon.melee.Greatsword;
import com.quasistellar.rpd.items.weapon.melee.HandAxe;
import com.quasistellar.rpd.items.weapon.melee.Longsword;
import com.quasistellar.rpd.items.weapon.melee.Mace;
import com.quasistellar.rpd.items.weapon.melee.MagesStaff;
import com.quasistellar.rpd.items.weapon.melee.MeleeWeapon;
import com.quasistellar.rpd.items.weapon.melee.Quarterstaff;
import com.quasistellar.rpd.items.weapon.melee.RoundShield;
import com.quasistellar.rpd.items.weapon.melee.RunicBlade;
import com.quasistellar.rpd.items.weapon.melee.Sai;
import com.quasistellar.rpd.items.weapon.melee.Scimitar;
import com.quasistellar.rpd.items.weapon.melee.Shortsword;
import com.quasistellar.rpd.items.weapon.melee.Spear;
import com.quasistellar.rpd.items.weapon.melee.Sword;
import com.quasistellar.rpd.items.weapon.melee.WarHammer;
import com.quasistellar.rpd.items.weapon.melee.Whip;
import com.quasistellar.rpd.items.weapon.melee.WornShortsword;
import com.quasistellar.rpd.items.weapon.missiles.HeavyBoomerang;
import com.quasistellar.rpd.items.weapon.missiles.Bolas;
import com.quasistellar.rpd.items.weapon.missiles.FishingSpear;
import com.quasistellar.rpd.items.weapon.missiles.ForceCube;
import com.quasistellar.rpd.items.weapon.missiles.Javelin;
import com.quasistellar.rpd.items.weapon.missiles.Kunai;
import com.quasistellar.rpd.items.weapon.missiles.MissileWeapon;
import com.quasistellar.rpd.items.weapon.missiles.Shuriken;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingClub;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingHammer;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingKnife;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingSpear;
import com.quasistellar.rpd.items.weapon.missiles.ThrowingStone;
import com.quasistellar.rpd.items.weapon.missiles.Tomahawk;
import com.quasistellar.rpd.items.weapon.missiles.Trident;
import com.quasistellar.rpd.plants.Blindweed;
import com.quasistellar.rpd.plants.Dreamfoil;
import com.quasistellar.rpd.plants.Earthroot;
import com.quasistellar.rpd.plants.Fadeleaf;
import com.quasistellar.rpd.plants.Firebloom;
import com.quasistellar.rpd.plants.Icecap;
import com.quasistellar.rpd.plants.Plant;
import com.quasistellar.rpd.plants.Rotberry;
import com.quasistellar.rpd.plants.Sorrowmoss;
import com.quasistellar.rpd.plants.Starflower;
import com.quasistellar.rpd.plants.Stormvine;
import com.quasistellar.rpd.plants.Sungrass;
import com.quasistellar.rpd.plants.Swiftthistle;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 6,    MeleeWeapon.class),
		WEP_T1	( 0,    MeleeWeapon.class),
		WEP_T2	( 0,    MeleeWeapon.class),
		WEP_T3	( 0,    MeleeWeapon.class),
		WEP_T4	( 0,    MeleeWeapon.class),
		WEP_T5	( 0,    MeleeWeapon.class),
		
		ARMOR	( 4,    Armor.class ),
		
		MISSILE ( 3,    MissileWeapon.class ),
		MIS_T1  ( 0,    MissileWeapon.class ),
		MIS_T2  ( 0,    MissileWeapon.class ),
		MIS_T3  ( 0,    MissileWeapon.class ),
		MIS_T4  ( 0,    MissileWeapon.class ),
		MIS_T5  ( 0,    MissileWeapon.class ),
		
		WAND	( 3,    Wand.class ),
		RING	( 1,    Ring.class ),
		ARTIFACT( 1,    Artifact.class),
		
		FOOD	( 0,    Food.class ),
		
		POTION	( 20,   Potion.class ),
		SEED	( 0,    Plant.Seed.class ), //dropped by grass
		
		SCROLL	( 20,   Scroll.class ),
		STONE   ( 2,    Runestone.class),
		
		GOLD	( 18,   Gold.class );
		
		public Class<?>[] classes;
		public float[] probs;
		
		public float prob;
		public Class<? extends Item> superClass;
		
		private Category( float prob, Class<? extends Item> superClass ) {
			this.prob = prob;
			this.superClass = superClass;
		}
		
		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}
			
			return item instanceof Bag ? Integer.MAX_VALUE : Integer.MAX_VALUE - 1;
		}
		
		private static final float[] INITIAL_ARTIFACT_PROBS = new float[]{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1};
		
		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };
			
			POTION.classes = new Class<?>[]{
					PotionOfStrength.class, //2 drop every chapter, see Dungeon.posNeeded()
					PotionOfHealing.class,
					PotionOfMindVision.class,
					PotionOfFrost.class,
					PotionOfLiquidFlame.class,
					PotionOfToxicGas.class,
					PotionOfHaste.class,
					PotionOfInvisibility.class,
					PotionOfLevitation.class,
					PotionOfParalyticGas.class,
					PotionOfPurity.class,
					PotionOfExperience.class};
			POTION.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };
			
			SEED.classes = new Class<?>[]{
					Rotberry.Seed.class, //quest item
					Blindweed.Seed.class,
					Dreamfoil.Seed.class,
					Earthroot.Seed.class,
					Fadeleaf.Seed.class,
					Firebloom.Seed.class,
					Icecap.Seed.class,
					Sorrowmoss.Seed.class,
					Stormvine.Seed.class,
					Sungrass.Seed.class,
					Swiftthistle.Seed.class,
					Starflower.Seed.class};
			SEED.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1 };
			
			SCROLL.classes = new Class<?>[]{
					ScrollOfUpgrade.class, //3 drop every chapter, see Dungeon.souNeeded()
					ScrollOfIdentify.class,
					ScrollOfRemoveCurse.class,
					ScrollOfMirrorImage.class,
					ScrollOfRecharging.class,
					ScrollOfTeleportation.class,
					ScrollOfLullaby.class,
					ScrollOfMagicMapping.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfTransmutation.class
			};
			SCROLL.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };
			
			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,   //1 is guaranteed to drop on floors 6-19
					StoneOfAugmentation.class,  //1 is sold in each shop
					StoneOfIntuition.class,     //1 additional stone is also dropped on floors 1-3
					StoneOfAggression.class,
					StoneOfAffection.class,
					StoneOfBlast.class,
					StoneOfBlink.class,
					StoneOfClairvoyance.class,
					StoneOfDeepenedSleep.class,
					StoneOfDisarming.class,
					StoneOfFlock.class,
					StoneOfShock.class
			};
			STONE.probs = new float[]{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

			WAND.classes = new Class<?>[]{
					WandOfMagicMissile.class,
					WandOfLightning.class,
					WandOfDisintegration.class,
					WandOfFireblast.class,
					WandOfCorrosion.class,
					WandOfBlastWave.class,
					WandOfLivingEarth.class,
					WandOfFrost.class,
					WandOfPrismaticLight.class,
					WandOfWarding.class,
					WandOfTransfusion.class,
					WandOfCorruption.class,
					WandOfRegrowth.class };
			WAND.probs = new float[]{ 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3 };
			
			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};
			
			WEP_T1.classes = new Class<?>[]{
					WornShortsword.class,
					Gloves.class,
					Dagger.class,
					MagesStaff.class
			};
			WEP_T1.probs = new float[]{ 1, 1, 1, 0 };
			
			WEP_T2.classes = new Class<?>[]{
					Shortsword.class,
					HandAxe.class,
					Spear.class,
					Quarterstaff.class,
					Dirk.class
			};
			WEP_T2.probs = new float[]{ 6, 5, 5, 4, 4 };
			
			WEP_T3.classes = new Class<?>[]{
					Sword.class,
					Mace.class,
					Scimitar.class,
					RoundShield.class,
					Sai.class,
					Whip.class
			};
			WEP_T3.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			WEP_T4.classes = new Class<?>[]{
					Longsword.class,
					BattleAxe.class,
					Flail.class,
					RunicBlade.class,
					AssassinsBlade.class,
					Crossbow.class
			};
			WEP_T4.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			WEP_T5.classes = new Class<?>[]{
					Greatsword.class,
					WarHammer.class,
					Glaive.class,
					Greataxe.class,
					Greatshield.class,
					Gauntlet.class
			};
			WEP_T5.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{
					ClothArmor.class,
					LeatherArmor.class,
					MailArmor.class,
					ScaleArmor.class,
					PlateArmor.class };
			ARMOR.probs = new float[]{ 0, 0, 0, 0, 0 };
			
			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};
			
			MIS_T1.classes = new Class<?>[]{
					ThrowingStone.class,
					ThrowingKnife.class
			};
			MIS_T1.probs = new float[]{ 6, 5 };
			
			MIS_T2.classes = new Class<?>[]{
					FishingSpear.class,
					ThrowingClub.class,
					Shuriken.class
			};
			MIS_T2.probs = new float[]{ 6, 5, 4 };
			
			MIS_T3.classes = new Class<?>[]{
					ThrowingSpear.class,
					Kunai.class,
					Bolas.class
			};
			MIS_T3.probs = new float[]{ 6, 5, 4 };
			
			MIS_T4.classes = new Class<?>[]{
					Javelin.class,
					Tomahawk.class,
					HeavyBoomerang.class
			};
			MIS_T4.probs = new float[]{ 6, 5, 4 };
			
			MIS_T5.classes = new Class<?>[]{
					Trident.class,
					ThrowingHammer.class,
					ForceCube.class
			};
			MIS_T5.probs = new float[]{ 6, 5, 4 };
			
			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };
			
			RING.classes = new Class<?>[]{
					RingOfAccuracy.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfForce.class,
					RingOfFuror.class,
					RingOfHaste.class,
					RingOfEnergy.class,
					RingOfMight.class,
					RingOfSharpshooting.class,
					RingOfTenacity.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			
			ARTIFACT.classes = new Class<?>[]{
					CapeOfThorns.class,
					ChaliceOfBlood.class,
					CloakOfShadows.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					UnstableSpellbook.class,
					AlchemistsToolkit.class,
					DriedRose.class,
					LloydsBeacon.class,
					EtherealChains.class
			};
			ARTIFACT.probs = INITIAL_ARTIFACT_PROBS.clone();
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 70, 20,  8,  2},
			{0, 25, 50, 20,  5},
			{0, 10, 40, 40, 10},
			{0,  5, 20, 50, 25},
			{0,  2,  8, 20, 70}
	};
	
	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();
	
	public static void reset() {
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, cat.prob );
		}
	}
	
	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			reset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);
		return random( cat );
	}
	
	public static Item random( Category cat ) {
		try {
			
			switch (cat) {
			case ARMOR:
				return randomArmor();
			case WEAPON:
				return randomWeapon();
			case MISSILE:
				return randomMissile();
			case ARTIFACT:
				Item item = randomArtifact();
				//if we're out of artifacts, return a ring instead.
				return item != null ? item : random(Category.RING);
			default:
				return ((Item)cat.classes[Random.chances( cat.probs )].newInstance()).random();
			}
			
		} catch (Exception e) {

			RPD.reportException(e);
			return null;
			
		}
	}
	
	public static Item random( Class<? extends Item> cl ) {
		try {
			
			return ((Item)cl.newInstance()).random();
			
		} catch (Exception e) {

			RPD.reportException(e);
			return null;
			
		}
	}

	public static Armor randomArmor(){
		return randomArmor(Dungeon.depth / 5);
	}
	
	public static Armor randomArmor(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Armor a = (Armor)Category.ARMOR.classes[Random.chances(floorSetTierProbs[floorSet])].newInstance();
			a.random();
			return a;
		} catch (Exception e) {
			RPD.reportException(e);
			return null;
		}
	}

	public static final Category[] wepTiers = new Category[]{
			Category.WEP_T1,
			Category.WEP_T2,
			Category.WEP_T3,
			Category.WEP_T4,
			Category.WEP_T5
	};

	public static MeleeWeapon randomWeapon(){
		return randomWeapon(Dungeon.depth / 5);
	}
	
	public static MeleeWeapon randomWeapon(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Category c = wepTiers[Random.chances(floorSetTierProbs[floorSet])];
			MeleeWeapon w = (MeleeWeapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			RPD.reportException(e);
			return null;
		}
	}
	
	public static final Category[] misTiers = new Category[]{
			Category.MIS_T1,
			Category.MIS_T2,
			Category.MIS_T3,
			Category.MIS_T4,
			Category.MIS_T5
	};
	
	public static MissileWeapon randomMissile(){
		return randomMissile(Dungeon.depth / 5);
	}
	
	public static MissileWeapon randomMissile(int floorSet) {
		
		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);
		
		try {
			Category c = misTiers[Random.chances(floorSetTierProbs[floorSet])];
			MissileWeapon w = (MissileWeapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			RPD.reportException(e);
			return null;
		}
	}

	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		try {
			Category cat = Category.ARTIFACT;
			int i = Random.chances( cat.probs );

			//if no artifacts are left, return null
			if (i == -1){
				return null;
			}
			
			Class<?extends Artifact> art = (Class<? extends Artifact>) cat.classes[i];

			if (removeArtifact(art)) {
				Artifact artifact = art.newInstance();
				
				artifact.random();
				
				return artifact;
			} else {
				return null;
			}

		} catch (Exception e) {
			RPD.reportException(e);
			return null;
		}
	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		if (spawnedArtifacts.contains(artifact))
			return false;

		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++)
			if (cat.classes[i].equals(artifact)) {
				if (cat.probs[i] == 1){
					cat.probs[i] = 0;
					spawnedArtifacts.add(artifact);
					return true;
				} else
					return false;
			}

		return false;
	}

	//resets artifact probabilities, for new dungeons
	public static void initArtifacts() {
		Category.ARTIFACT.probs = Category.INITIAL_ARTIFACT_PROBS.clone();
		spawnedArtifacts = new ArrayList<>();
	}

	private static ArrayList<Class<?extends Artifact>> spawnedArtifacts = new ArrayList<>();
	
	private static final String GENERAL_PROBS = "general_probs";
	private static final String SPAWNED_ARTIFACTS = "spawned_artifacts";
	
	public static void storeInBundle(Bundle bundle) {
		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);
		
		bundle.put( SPAWNED_ARTIFACTS, spawnedArtifacts.toArray(new Class[0]));
	}

	public static void restoreFromBundle(Bundle bundle) {
		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		} else {
			reset();
		}
		
		initArtifacts();
		
		for ( Class<?extends Artifact> artifact : bundle.getClassArray(SPAWNED_ARTIFACTS) ){
			removeArtifact(artifact);
		}
		
	}
}
