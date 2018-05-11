package com.lilithsthrone.game.inventory.weapon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.84
 * @version 0.2.4
 * @author Innoxia
 */
public class WeaponType {
	
	public static AbstractWeaponType MELEE_CHAOS_RARE = new AbstractWeaponType(1000,
			true,
			"an",
			"it",
			"opaque demonstone",
			"opaque demonstones",
			"Crystal Strike",
			"A common type of demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			InventorySlot.WEAPON_MAIN,
			"meleeCrystal1",
			Rarity.RARE,
			Util.newArrayListOfValues(
					new ListValue<DamageType>(DamageType.PHYSICAL),
					new ListValue<DamageType>(DamageType.FIRE),
					new ListValue<DamageType>(DamageType.ICE),
					new ListValue<DamageType>(DamageType.POISON)),
			8,
			0,
			DamageVariance.MEDIUM,
			null,
			null) {
		
		private static final long serialVersionUID = 1L;
				
		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the crystal. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a demonstone.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericMeleeAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] in melee, using your crystal's power to inflict extra damage!");
		}
		
		@Override
		public Map<Attribute, Integer> getGenerationAttributeModifiers(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 5));
				case ICE:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 5));
				case LUST:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5));
				case POISON:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 5));
			}
			return null;
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.FIREBALL));
				case ICE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ICE_SHARD));
				case LUST:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ARCANE_AROUSAL));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(new ListValue<>(Spell.SLAM));
				case POISON:
					return Util.newArrayListOfValues(new ListValue<>(Spell.POISON_VAPOURS));
			}
			return null;
		}
	};
	
	public static AbstractWeaponType MELEE_CHAOS_EPIC = new AbstractWeaponType(1500,
			true,
			"a",
			"it",
			"misty demonstone",
			"misty demonstones",
			"Crystal Strike",
			"A powerful demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			InventorySlot.WEAPON_MAIN,
			"meleeCrystal2",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					new ListValue<DamageType>(DamageType.PHYSICAL),
					new ListValue<DamageType>(DamageType.FIRE),
					new ListValue<DamageType>(DamageType.ICE),
					new ListValue<DamageType>(DamageType.POISON)),
			14,
			0,
			DamageVariance.MEDIUM,
			null,
			null) {

		private static final long serialVersionUID = 1L;

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the crystal. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a demonstone.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericMeleeAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] in melee, using your crystal's power to inflict extra damage!");
		}

		@Override
		public Map<Attribute, Integer> getGenerationAttributeModifiers(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_FIRE, 10));
				case ICE:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_ICE, 10));
				case LUST:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 10));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 10));
				case POISON:
					return Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_POISON, 10));
			}
			return null;
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.FIREBALL));
				case ICE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ICE_SHARD));
				case LUST:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ARCANE_AROUSAL));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(new ListValue<>(Spell.SLAM));
				case POISON:
					return Util.newArrayListOfValues(new ListValue<>(Spell.POISON_VAPOURS));
			}
			return null;
		}
	};
	
	public static AbstractWeaponType MELEE_CHAOS_LEGENDARY = new AbstractWeaponType(2500,
			true,
			"a",
			"it",
			"clear demonstone",
			"clear demonstones",
			"Crystal Strike",
			"An extremely powerful demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			InventorySlot.WEAPON_MAIN,
			"meleeCrystal3",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(
					new ListValue<DamageType>(DamageType.PHYSICAL),
					new ListValue<DamageType>(DamageType.FIRE),
					new ListValue<DamageType>(DamageType.ICE),
					new ListValue<DamageType>(DamageType.POISON)),
			20,
			0,
			DamageVariance.LOW,
			null,
			null){

		private static final long serialVersionUID = 1L;

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the crystal. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a demonstone.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericMeleeAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Strike at [npc.name] in melee, using your crystal's power to inflict extra damage!");
		}

		@Override
		public Map<Attribute, Integer> getGenerationAttributeModifiers(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newHashMapOfValues(
							new Value<>(Attribute.DAMAGE_FIRE, 10),
							new Value<>(Attribute.RESISTANCE_FIRE, 5));
				case ICE:
					return Util.newHashMapOfValues(
							new Value<>(Attribute.DAMAGE_ICE, 10),
							new Value<>(Attribute.RESISTANCE_ICE, 5));
				case LUST:
					return Util.newHashMapOfValues(
							new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
							new Value<>(Attribute.RESISTANCE_PHYSICAL, 5));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newHashMapOfValues(
							new Value<>(Attribute.DAMAGE_PHYSICAL, 10),
							new Value<>(Attribute.RESISTANCE_PHYSICAL, 5));
				case POISON:
					return Util.newHashMapOfValues(
							new Value<>(Attribute.DAMAGE_POISON, 10),
							new Value<>(Attribute.RESISTANCE_POISON, 5));
			}
			return null;
		}

		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.FIREBALL));
				case ICE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ICE_SHARD));
				case LUST:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ARCANE_AROUSAL));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(new ListValue<>(Spell.SLAM));
				case POISON:
					return Util.newArrayListOfValues(new ListValue<>(Spell.POISON_VAPOURS));
			}
			return null;
		}
	};

	// OFFHAND
	public static AbstractWeaponType OFFHAND_CHAOS_RARE = new AbstractWeaponType(1000,
			false,
			"a",
			"it",
			"chaos feather",
			"chaos feathers",
			"Feather Bolt",
			"A magical feather, the power of which can be harnessed as a weapon."
					+ " Feathers like this are rumoured to have been plucked from a Lilin's wings.",
			InventorySlot.WEAPON_OFFHAND,
			"rangedFeather1",
			Rarity.RARE,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL), new ListValue<DamageType>(DamageType.FIRE), new ListValue<DamageType>(DamageType.ICE), new ListValue<DamageType>(DamageType.POISON)),
			8,
			0,
			DamageVariance.HIGH,
			null,
			null) {

		private static final long serialVersionUID = 1L;

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the feather. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks at range.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a magical feather.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericRangedAttackDescription(character, target, isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Use your feather's power to shoot a bolt of energy at [npc.name]!");
		}
		
		@Override
		public Map<Attribute, Integer> getGenerationAttributeModifiers(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 5));
				case ICE:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_ICE, 5));
				case LUST:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 5));
				case POISON:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 5));
			}
			return null;
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.FIREBALL));
				case ICE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ICE_SHARD));
				case LUST:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ARCANE_AROUSAL));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(new ListValue<>(Spell.SLAM));
				case POISON:
					return Util.newArrayListOfValues(new ListValue<>(Spell.POISON_VAPOURS));
			}
			return null;
		}
	};
	
	public static AbstractWeaponType OFFHAND_CHAOS_EPIC = new AbstractWeaponType(1500,
			false,
			"a",
			"it",
			"chaos feather",
			"chaos feathers",
			"Feather Bolt",
			"A well-preserved magical feather, the power of which can be harnessed as a weapon."
					+ " Feathers like this are rumoured to have been plucked from a Lilin's wings.",
			InventorySlot.WEAPON_OFFHAND,
			"rangedFeather2",
			Rarity.EPIC,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL), new ListValue<DamageType>(DamageType.FIRE), new ListValue<DamageType>(DamageType.ICE), new ListValue<DamageType>(DamageType.POISON)),
			14,
			0,
			DamageVariance.HIGH,
			null,
			null) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the feather. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks at range.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a magical feather.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return genericRangedAttackDescription(character, target,isHit);
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Use your feather's power to shoot a bolt of energy at [npc.name]!");
		}

		@Override
		public Map<Attribute, Integer> getGenerationAttributeModifiers(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_FIRE, 10));
				case ICE:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_ICE, 10));
				case LUST:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 10));
				case POISON:
					return Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_POISON, 10));
			}
			return null;
		}
		
		@Override
		public List<Spell> getGenerationSpells(DamageType dt) {
			switch(dt) {
				case FIRE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.FIREBALL));
				case ICE:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ICE_SHARD));
				case LUST:
					return Util.newArrayListOfValues(new ListValue<>(Spell.ARCANE_AROUSAL));
				case MISC:
					break;
				case PHYSICAL:
					return Util.newArrayListOfValues(new ListValue<>(Spell.SLAM));
				case POISON:
					return Util.newArrayListOfValues(new ListValue<>(Spell.POISON_VAPOURS));
			}
			return null;
		}
	};
	
	// I made this in one of my lunch breaks x_x
	public static AbstractWeaponType MAIN_WESTERN_KKP = new AbstractWeaponType(25000,
			false,
			"a",
			"it",
			"Western KKP",
			"Western KKPs",
			"Fire KKP",
			"A blowback-operated semi-automatic pistol, featuring an exposed hammer, a traditional double-action trigger mechanism, a single-column magazine, and a fixed barrel that also acts as the guide rod for the recoil spring.",
			InventorySlot.WEAPON_MAIN,
			"western_kkp",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL)),
			100000,
			0,
			DamageVariance.LOW,
			null,
			null) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String equipText(GameCharacter character) {
			return "You ready your pistol.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You stow your pistol away.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			return "You just shoot them... Thankfully, there seems to be some kind of arcane force preventing your gun from actually killing them...";
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"The name's [pc.surname]... [pc.name] [pc.surname].");
		}
	};
	
	public static AbstractWeaponType RANGED_MUSKET = new AbstractWeaponType(15000,
			false,
			"an",
			"it",
			"Arcane Musket",
			"Arcane Muskets",
			"Fire Musket",
			"Carried by Lyssieth's demonic guard, these smoothbore long guns fire bolts of arcane energy instead of bullets."
					+ " Each discharge drains one arcane essence from the user, meaning that only those who have the ability to absorb arcane essences can fire it.",
			InventorySlot.WEAPON_MAIN,
			"arcaneMusket",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(
					new ListValue<DamageType>(DamageType.PHYSICAL)),
			25,
			1,
			DamageVariance.LOW,
			null,
			null) {
		
		private static final long serialVersionUID = 1L;
				
		@Override
		public String equipText(GameCharacter character) {
			return "You ready your musket.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You put your musket away.";
		}
		
		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			if(isHit) {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You point your arcane musket at [npc.name] and pull the trigger, unleashing a blast of arcane power that strikes [npc.herHim] right in the chest!",
								"Taking aim at [npc.name] with your musket, you pull the trigger, unleashing a powerful blast of arcane energy that slams into [npc.her] torso!",
								"You fire your musket at [npc.name], blasting forth a powerful jolt of arcane energy that strikes [npc.herHim] straight in the chest!"));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] points [npc.her] arcane musket at you and pulls the trigger, unleashing a blast of arcane power that strikes you right in the chest!",
								"Taking aim at you with [npc.her] musket, [npc.name] pulls the trigger, unleashing a powerful blast of arcane energy that slams into your torso!",
								"[npc.Name] fires [npc.her] musket at you, blasting forth a powerful jolt of arcane energy that strikes you straight in the chest!"));
				}
				
			} else {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You point you arcane musket at [npc.name] and pull the trigger, but, much to your dismay, the blast of arcane power misses its target!",
								"Taking aim at [npc.name] with your musket, you pull the trigger, but unfortunately the blast of arcane power that you unleash misses your target!",
								"You fire your musket at [npc.name], blasting forth a powerful jolt of arcane energy that unfortunately fails to hit your target!"));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
									"[npc.Name] points [npc.her] arcane musket at you and pulls the trigger, but, much to [npc.her] dismay, the blast of arcane power misses you!",
									"Taking aim at you with [npc.her] musket, [npc.name] pulls the trigger, but thankfully the blast of arcane power that [npc.she] unleashes misses you!",
									"[npc.Name] fires [npc.her] musket at you, blasting forth a powerful jolt of arcane energy that thankfully fails to hit you!"));
				}
			}
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Point your musket at [npc.name] and fire!");
		}
	};
	
	public static AbstractWeaponType MAIN_WITCH_BROOM = new AbstractWeaponType(5000,
			true,
			"a",
			"it",
			"Witch's Broom",
			"Witch's Brooms",
			"Broom Swipe",
			"An old-fashioned wooden broom, consisting of a long pole with a bundle of flexible twigs attached to one end."
					+ " The opposite end of the pole widens out a little, where there's a curious engraving of a pentagram etched into the wood.",
			InventorySlot.WEAPON_MAIN,
			"primary_witch_broom",
			Rarity.EPIC,
			Util.newArrayListOfValues(
					new ListValue<DamageType>(DamageType.PHYSICAL),
					new ListValue<DamageType>(DamageType.FIRE),
					new ListValue<DamageType>(DamageType.ICE),
					new ListValue<DamageType>(DamageType.POISON)),
			5,
			0,
			DamageVariance.LOW,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 5)),
			Util.newArrayListOfValues(
					new ListValue<Spell>(Spell.WITCH_SEAL),
					new ListValue<Spell>(Spell.WITCH_CHARM))) {
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public String equipText(GameCharacter character) {
			return "You ready your broom.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You put your broom away.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			if(isHit) {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You whack [npc.name]'s [npc.arm] with your broom.",
								"You swipe your broom at [npc.name]'s [npc.legs], and manage to hit [npc.herHim] in the shins.",
								"You swing your broom at [npc.name], grinning as the end makes contact with [npc.her] torso."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] whacks your [pc.arm] with [npc.her] broom.",
								"[npc.Name] swipes [npc.her] broom at your [pc.legs], and manages to hit you in the shins.",
								"[npc.Name] swings [npc.her] broom at you, grinning as the end makes contact with your torso."));
				}
				
			} else {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You try to whack [npc.name] with your broom, but [npc.she] manages to grab the shaft and push you away.",
								"You attempt to swipe your broom at [npc.name]'s [npc.legs], but [npc.she] jumps back and manages to avoid the blow.",
								"You swing your broom at [npc.name], but [npc.she] manages to duck at the last moment and avoid the blow."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] tries to whack you with [npc.her] broom, but you manage to grab the shaft and push [npc.herHim] away.",
								"[npc.Name] attempts to swipe [npc.her] broom at your [pc.legs], but you jump back and manage to avoid the blow.",
								"[npc.Name] swings [npc.her] broom at you, but you manage to duck at the last moment and avoid the blow."));
				}
			}
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Swipe the broom at [npc.name]!");
		}
	};
	
	public static AbstractWeaponType MAIN_FEATHER_DUSTER = new AbstractWeaponType(250,
			true,
			"a",
			"it",
			"feather duster",
			"feather dusters",
			"Duster Tickle",
			"A short-handled feather duster, ideal for keeping a house clean, but not much use in combat...",
			InventorySlot.WEAPON_MAIN,
			"feather_duster",
			Rarity.EPIC,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL)),
			2,
			0,
			DamageVariance.LOW,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		
		private static final long serialVersionUID = 1L;
		
		
		@Override

		public String getDescription() {
			if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP) {
				return "A short-handled feather duster, ideal for keeping a house clean, but not much use in combat..."
						+ " [Ashley.speech(A feather duster: the epitome of romance, at least for those who don't know anything about their lover, other than that they're the person who keeps the house clean.)]";
//						+ " Surely, that's all that's going on with their lives, right?)]";
			} else {
				return "A short-handled feather duster, ideal for keeping a house clean, but not much use in combat...";
			}
		}
		
		@Override
		public String equipText(GameCharacter character) {
			return "You ready your feather duster.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You put your feather duster away.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
			if(isHit) {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You tickle [npc.name]'s [npc.arm] with your feather duster.",
								"You swipe your feather duster at [npc.name]'s [npc.legs], and manage to tickle [npc.her] shins.",
								"You swing your feather duster at [npc.name], grinning as you tickle [npc.her] torso."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] tickles your [pc.arm] with [npc.her] feather duster.",
								"[npc.Name] swipes [npc.her] feather duster at your [pc.legs], and manages to tickle your shins.",
								"[npc.Name] swings [npc.her] feather duster at you, grinning as [npc.she] tickles your torso."));
				}
				
			} else {
				if(character.isPlayer()) {
					return UtilText.parse(target,
							UtilText.returnStringAtRandom(
								"You try to tickle [npc.name] with your feather duster, but [npc.she] manages to push you away.",
								"You attempt to swipe your feather duster at [npc.name]'s [npc.legs], but [npc.she] jumps back and manages to avoid the tickling.",
								"You swing your feather duster at [npc.name], but [npc.she] manages to duck at the last moment and avoid the tickling."));
					
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"[npc.Name] tries to tickle you with [npc.her] feather duster, but you manage to grab the shaft and push [npc.herHim] away.",
								"[npc.Name] attempts to swipe [npc.her] feather duster at your [pc.legs], but you jump back and manage to avoid the tickling.",
								"[npc.Name] swings [npc.her] feather duster at you, but you manage to duck at the last moment and avoid the tickling."));
				}
			}
		}

		@Override
		public String getAttackDescription(GameCharacter user, GameCharacter target) {
			return UtilText.parse(target,
					"Tickle [npc.name] with your feather duster!");
		}
	};

	public static List<AbstractWeaponType> rareWeapons = new ArrayList<>(), allweapons = new ArrayList<>();
	
	public static Map<AbstractWeaponType, String> weaponToIdMap = new HashMap<>();
	public static Map<String, AbstractWeaponType> idToWeaponMap = new HashMap<>();

	static {
		
		Field[] fields = WeaponType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractWeaponType.class.isAssignableFrom(f.getType())) {
				
				AbstractWeaponType weapon;
				
				try {
					weapon = ((AbstractWeaponType) f.get(null));

					// I feel like this is stupid :thinking:
					weaponToIdMap.put(weapon, f.getName());
					idToWeaponMap.put(f.getName(), weapon);
					
					if(weapon != MAIN_WESTERN_KKP) {
						allweapons.add(weapon);
					
						if (weapon.getRarity() == Rarity.RARE) {
							rareWeapons.add(weapon);
						}
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
