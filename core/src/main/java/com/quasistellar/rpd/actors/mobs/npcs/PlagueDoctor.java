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

package com.quasistellar.rpd.actors.mobs.npcs;

import com.quasistellar.rpd.Badges;
import com.quasistellar.rpd.Dungeon;
import com.quasistellar.rpd.actors.Char;
import com.quasistellar.rpd.actors.buffs.Buff;
import com.quasistellar.rpd.items.Ankh;
import com.quasistellar.rpd.items.Honeypot;
import com.quasistellar.rpd.items.Item;
import com.quasistellar.rpd.items.bombs.ArcaneBomb;
import com.quasistellar.rpd.items.bombs.Bomb;
import com.quasistellar.rpd.items.bombs.Firebomb;
import com.quasistellar.rpd.items.bombs.Flashbang;
import com.quasistellar.rpd.items.bombs.FrostBomb;
import com.quasistellar.rpd.items.bombs.HolyBomb;
import com.quasistellar.rpd.items.bombs.Noisemaker;
import com.quasistellar.rpd.items.bombs.RegrowthBomb;
import com.quasistellar.rpd.items.bombs.ShockBomb;
import com.quasistellar.rpd.items.bombs.ShrapnelBomb;
import com.quasistellar.rpd.items.bombs.WoollyBomb;
import com.quasistellar.rpd.items.potions.brews.BlizzardBrew;
import com.quasistellar.rpd.items.potions.brews.CausticBrew;
import com.quasistellar.rpd.items.potions.brews.InfernalBrew;
import com.quasistellar.rpd.items.potions.brews.ShockingBrew;
import com.quasistellar.rpd.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.quasistellar.rpd.items.potions.elixirs.ElixirOfArcaneArmor;
import com.quasistellar.rpd.items.potions.elixirs.ElixirOfDragonsBlood;
import com.quasistellar.rpd.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.quasistellar.rpd.items.potions.elixirs.ElixirOfIcyTouch;
import com.quasistellar.rpd.items.potions.elixirs.ElixirOfToxicEssence;
import com.quasistellar.rpd.items.potions.exotic.PotionOfCleansing;
import com.quasistellar.rpd.items.potions.exotic.PotionOfCorrosiveGas;
import com.quasistellar.rpd.items.potions.exotic.PotionOfDragonsBreath;
import com.quasistellar.rpd.items.potions.exotic.PotionOfEarthenArmor;
import com.quasistellar.rpd.items.potions.exotic.PotionOfHolyFuror;
import com.quasistellar.rpd.items.potions.exotic.PotionOfMagicalSight;
import com.quasistellar.rpd.items.potions.exotic.PotionOfShielding;
import com.quasistellar.rpd.items.potions.exotic.PotionOfShroudingFog;
import com.quasistellar.rpd.items.potions.exotic.PotionOfSnapFreeze;
import com.quasistellar.rpd.items.potions.exotic.PotionOfStamina;
import com.quasistellar.rpd.items.potions.exotic.PotionOfStormClouds;
import com.quasistellar.rpd.items.quest.RatHide;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfAffection;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfAntiMagic;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfConfusion;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfDivination;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfForesight;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfPassage;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfPetrification;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfPrismaticImage;
import com.quasistellar.rpd.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.quasistellar.rpd.items.spells.Alchemize;
import com.quasistellar.rpd.items.spells.AquaBlast;
import com.quasistellar.rpd.items.spells.BeaconOfReturning;
import com.quasistellar.rpd.items.spells.CurseInfusion;
import com.quasistellar.rpd.items.spells.FeatherFall;
import com.quasistellar.rpd.items.spells.MagicalInfusion;
import com.quasistellar.rpd.items.spells.MagicalPorter;
import com.quasistellar.rpd.items.spells.PhaseShift;
import com.quasistellar.rpd.items.spells.ReclaimTrap;
import com.quasistellar.rpd.items.spells.Recycle;
import com.quasistellar.rpd.items.spells.WildEnergy;
import com.quasistellar.rpd.messages.Messages;
import com.quasistellar.rpd.scenes.GameScene;
import com.quasistellar.rpd.sprites.PlagueDoctorSprite;
import com.quasistellar.rpd.utils.GLog;
import com.quasistellar.rpd.windows.WndDoctor;
import com.quasistellar.rpd.windows.WndQuest;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;

public class PlagueDoctor extends NPC {
	
	{
		spriteClass = PlagueDoctorSprite.class;

		properties.add(Property.IMMOVABLE);
	}
	
	@Override
	protected boolean act() {
		throwItem();
		return super.act();
	}
	
	@Override
	public boolean interact() {

		sprite.turnTo( pos, Dungeon.hero.pos );

		if (Quest.orderNumber == 1 && Quest.reqItem == null) {
			Quest.reqItem = (Item)Random.element(Quest.firstTierReq);
			Quest.firstTierReq.remove(Quest.reqItem);

			Quest.prize1 = (Item)Random.element(Quest.firstTierPrize);
			Quest.firstTierPrize.remove(Quest.prize1);

			Quest.prize2 = (Item)Random.element(Quest.firstTierPrize);
			Quest.firstTierPrize.remove(Quest.prize2);
		}
		if (Quest.orderNumber <= 9) {
			GameScene.show( new WndDoctor( this, Dungeon.hero ) );
		} else {
			tell( Messages.get(this, "all_quests") );
			Badges.validatePlagueDoctor();
		}

		return false;
	}

	public static void nextQuest() {
		switch (Quest.orderNumber) {
			case 1:
				break;
			case 2:
			case 3:
			case 4:
				Quest.reqItem = (Item)Random.element(Quest.secondTierReq);
				Quest.secondTierReq.remove(Quest.reqItem);

				Quest.prize1 = (Item)Random.element(Quest.secondTierPrize);
				Quest.secondTierPrize.remove(Quest.prize1);

				Quest.prize2 = (Item)Random.element(Quest.secondTierPrize);
				Quest.secondTierPrize.remove(Quest.prize2);
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				Quest.reqItem = (Item)Random.element(Quest.thirdTierReq);
				Quest.thirdTierReq.remove(Quest.reqItem);

				Quest.prize1 = (Item)Random.element(Quest.thirdTierPrize);
				Quest.thirdTierPrize.remove(Quest.prize1);

				Quest.prize2 = (Item)Random.element(Quest.thirdTierPrize);
				Quest.thirdTierPrize.remove(Quest.prize2);
				break;
		}

	}

	private void tell( String text ) {
		GameScene.show( new WndQuest( this, text ) );
	}

	@Override
	public int defenseSkill( Char enemy ) {
		return 100_000_000;
	}
	
	@Override
	public void damage( int dmg, Object src ) {
	}
	
	@Override
	public void add( Buff buff ) {
	}
	
	@Override
	public boolean reset() {
		return true;
	}

	public static class Quest {

		public static int orderNumber = 1;

		public static Item reqItem;

		public static Item prize1;
		public static Item prize2;

		public static boolean hideDropped;

		private static Collection<Bundlable> firstTierReq = new ArrayList<>();
		private static Collection<Bundlable> secondTierReq = new ArrayList<>();
		private static Collection<Bundlable> thirdTierReq = new ArrayList<>();
		private static Collection<Bundlable> firstTierPrize = new ArrayList<>();
		private static Collection<Bundlable> secondTierPrize = new ArrayList<>();
		private static Collection<Bundlable> thirdTierPrize = new ArrayList<>();

		public static void reset() {

			orderNumber = 1;

			reqItem = null;
			prize1 = null;
			prize2 = null;

			hideDropped = false;

			firstTierReq.clear();
			secondTierReq.clear();
			thirdTierReq.clear();
			firstTierPrize.clear();
			secondTierPrize.clear();
			thirdTierPrize.clear();

			firstTierReq.add(new RatHide());

			//secondTierReq.add(new PotionOfAdrenalineSurge());
			secondTierReq.add(new PotionOfCleansing());
			secondTierReq.add(new PotionOfCorrosiveGas());
			secondTierReq.add(new PotionOfDragonsBreath());
			secondTierReq.add(new PotionOfEarthenArmor());
			secondTierReq.add(new PotionOfHolyFuror());
			secondTierReq.add(new PotionOfMagicalSight());
			secondTierReq.add(new PotionOfShielding());
			secondTierReq.add(new PotionOfShroudingFog());
			secondTierReq.add(new PotionOfSnapFreeze());
			secondTierReq.add(new PotionOfStamina());
			secondTierReq.add(new PotionOfStormClouds());
			secondTierReq.add(new ScrollOfAffection());
			secondTierReq.add(new ScrollOfAntiMagic());
			secondTierReq.add(new ScrollOfConfusion());
			secondTierReq.add(new ScrollOfDivination());
			//secondTierReq.add(new ScrollOfEnchantment());
			secondTierReq.add(new ScrollOfForesight());
			secondTierReq.add(new ScrollOfMysticalEnergy());
			secondTierReq.add(new ScrollOfPassage());
			secondTierReq.add(new ScrollOfPetrification());
			//secondTierReq.add(new ScrollOfPolymorph());
			secondTierReq.add(new ScrollOfPrismaticImage());
			secondTierReq.add(new ScrollOfPsionicBlast());

			thirdTierReq.add(new BlizzardBrew());
			thirdTierReq.add(new CausticBrew());
			thirdTierReq.add(new InfernalBrew());
			thirdTierReq.add(new ShockingBrew());
			thirdTierReq.add(new ElixirOfAquaticRejuvenation());
			thirdTierReq.add(new ElixirOfArcaneArmor());
			thirdTierReq.add(new ElixirOfDragonsBlood());
			thirdTierReq.add(new ElixirOfHoneyedHealing());
			thirdTierReq.add(new ElixirOfIcyTouch());
			//thirdTierReq.add(new ElixirOfMight());
			thirdTierReq.add(new ElixirOfToxicEssence());

			firstTierPrize.add(new Bomb());
			firstTierPrize.add(new Honeypot());

			secondTierPrize.add(new Firebomb());
			secondTierPrize.add(new Flashbang());
			secondTierPrize.add(new FrostBomb());
			secondTierPrize.add(new HolyBomb());
			secondTierPrize.add(new Noisemaker());
			secondTierPrize.add(new RegrowthBomb());
			secondTierPrize.add(new ShockBomb());
			secondTierPrize.add(new WoollyBomb());
			secondTierPrize.add(new Alchemize().quantity(3));
			secondTierPrize.add(new AquaBlast().quantity(5));
			secondTierPrize.add(new CurseInfusion().quantity(3));
			secondTierPrize.add(new FeatherFall().quantity(2));
			secondTierPrize.add(new MagicalPorter().quantity(5));
			secondTierPrize.add(new Recycle().quantity(3));

			thirdTierPrize.add(new ArcaneBomb());
			thirdTierPrize.add(new ShrapnelBomb());
			thirdTierPrize.add(new BeaconOfReturning().quantity(5));
			thirdTierPrize.add(new MagicalInfusion());
			thirdTierPrize.add(new PhaseShift().quantity(5));
			thirdTierPrize.add(new ReclaimTrap().quantity(5));
			thirdTierPrize.add(new WildEnergy().quantity(5));
			thirdTierPrize.add(new Ankh());
		}
		
		private static final String NODE	= "doctor";

		private static final String FREQ	= "freq";
		private static final String SREQ	= "sreq";
		private static final String TREQ	= "treq";
		private static final String FPRIZE	= "fprize";
		private static final String SPRIZE	= "fprize";
		private static final String TPRIZE	= "fprize";

		private static final String ORDER   = "order";

		private static final String REQ     = "req";
		private static final String PRIZE1  = "prize1";
		private static final String PRIZE2  = "prize2";

		private static final String HIDE    = "hide";
		
		public static void storeInBundle( Bundle bundle ) {
			
			Bundle node = new Bundle();

			node.put( FREQ, firstTierReq );
			node.put( SREQ, secondTierReq );
			node.put( TREQ, thirdTierReq );

			node.put( FPRIZE, firstTierPrize );
			node.put( SPRIZE, secondTierPrize );
			node.put( TPRIZE, thirdTierPrize );

			node.put( ORDER, orderNumber );

			node.put( REQ, reqItem );
			node.put( PRIZE1, prize1 );
			node.put( PRIZE2, prize2 );

			node.put( HIDE, hideDropped );

			bundle.put( NODE, node );
		}
		
		public static void restoreFromBundle( Bundle bundle ) {

			Bundle node = bundle.getBundle( NODE );
			
			if (!node.isNull()) {
				firstTierReq = node.getCollection(FREQ);
				secondTierReq = node.getCollection(SREQ);
				thirdTierReq = node.getCollection(TREQ);

				firstTierPrize = node.getCollection(FPRIZE);
				secondTierPrize = node.getCollection(SPRIZE);
				thirdTierPrize = node.getCollection(TPRIZE);

				orderNumber = node.getInt(ORDER);

				reqItem = (Item)node.get(REQ);
				prize1 = (Item)node.get(PRIZE1);
				prize2 = (Item)node.get(PRIZE2);

				hideDropped = node.getBoolean(HIDE);

			} else {
				reset();
			}
		}

		public static void process(int pos) {
			if (!hideDropped && Random.Int(3) == 0) {
				Dungeon.level.drop(new RatHide(), pos).sprite.drop();
				hideDropped = true;
			}
		}
	}
}
