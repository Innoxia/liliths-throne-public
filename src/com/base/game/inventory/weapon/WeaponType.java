package com.base.game.inventory.weapon;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.base.game.character.GameCharacter;
import com.base.game.character.attributes.Attribute;
import com.base.game.combat.DamageLevel;
import com.base.game.combat.DamageType;
import com.base.game.combat.DamageVariance;
import com.base.game.combat.Spell;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.Rarity;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.75
 * @author Innoxia
 */
public enum WeaponType {

	// MAGIC CRYSTALS:
	// MELEE
	MELEE_CHAOS_RARE("an",
			"it",
			"opaque demonstone",
			"A common type of demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			InventorySlot.WEAPON_MAIN,
			"meleeCrystal1",
			Rarity.RARE,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL), new ListValue<DamageType>(DamageType.FIRE), new ListValue<DamageType>(DamageType.ICE), new ListValue<DamageType>(DamageType.POISON)),
			DamageLevel.POOR,
			DamageVariance.MEDIUM,
			null,
			null) {

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the crystal. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a demonstone.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target) {
			return genericMeleeAttackDescription(character, target);
		}
	},
	MELEE_CHAOS_EPIC("a",
			"it",
			"misty demonstone",
			"A powerful demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			InventorySlot.WEAPON_MAIN,
			"meleeCrystal2",
			Rarity.EPIC,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL), new ListValue<DamageType>(DamageType.FIRE), new ListValue<DamageType>(DamageType.ICE), new ListValue<DamageType>(DamageType.POISON)),
			DamageLevel.NORMAL,
			DamageVariance.MEDIUM,
			null,
			null) {

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the crystal. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a demonstone.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target) {
			return genericMeleeAttackDescription(character, target);
		}
	},
	MELEE_CHAOS_LEGENDARY("a",
			"it",
			"clear demonstone",
			"An extremely powerful demonstone, the power of which can be harnessed as a weapon."
					+ " Demonstones are rumoured to be crystallised essences of a Lilin's orgasm.",
			InventorySlot.WEAPON_MAIN,
			"meleeCrystal3",
			Rarity.LEGENDARY,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.FIRE)),
			DamageLevel.HIGH,
			DamageVariance.LOW,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STRENGTH, 5)),
			Util.newArrayListOfValues(new ListValue<Spell>(Spell.FIREBALL_1), new ListValue<Spell>(Spell.FIREBALL_1), new ListValue<Spell>(Spell.FIREBALL_1))) {

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the crystal. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a demonstone.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target) {
			return genericMeleeAttackDescription(character, target);
		}
	},

	// OFFHAND
	OFFHAND_CHAOS_RARE("a",
			"it",
			"chaos feather",
			"A magical feather, the power of which can be harnessed as a weapon."
					+ " Feathers like this are rumoured to have been plucked from a Lilin's wings.",
			InventorySlot.WEAPON_OFFHAND,
			"rangedFeather1",
			Rarity.RARE,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL), new ListValue<DamageType>(DamageType.FIRE), new ListValue<DamageType>(DamageType.ICE), new ListValue<DamageType>(DamageType.POISON)),
			DamageLevel.NORMAL,
			DamageVariance.HIGH,
			null,
			null) {

		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the feather. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks at range.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a magical feather.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target) {
			return genericRangedAttackDescription(character, target);
		}
	},
	OFFHAND_CHAOS_EPIC("a",
			"it",
			"chaos feather",
			"A well-preserved magical feather, the power of which can be harnessed as a weapon."
					+ " Feathers like this are rumoured to have been plucked from a Lilin's wings.",
			InventorySlot.WEAPON_OFFHAND,
			"rangedFeather2",
			Rarity.EPIC,
			Util.newArrayListOfValues(new ListValue<DamageType>(DamageType.PHYSICAL), new ListValue<DamageType>(DamageType.FIRE), new ListValue<DamageType>(DamageType.ICE), new ListValue<DamageType>(DamageType.POISON)),
			DamageLevel.HIGH,
			DamageVariance.HIGH,
			null,
			null) {
		@Override
		public String equipText(GameCharacter character) {
			return "You focus on the energy in the feather. As you do so, it dissolves and flows into your body, granting you the ability to perform magical attacks at range.";
		}

		@Override
		public String unequipText(GameCharacter character) {
			return "You focus on the energy inside of you, forcing it out from your body. As you do so, it reforms back into a magical feather.";
		}

		@Override
		public String getAttackDescription(GameCharacter character, GameCharacter target) {
			return genericRangedAttackDescription(character, target);
		}
	};

	private static List<WeaponType> rareWeapons;

	static {
		rareWeapons = new ArrayList<>();
		for (WeaponType w : WeaponType.values())
			if (w.getRarity() == Rarity.RARE)
				rareWeapons.add(w);
	}

	private String determiner, pronoun, name, description, pathName;
	protected DamageLevel damageLevel;
	protected DamageVariance damageVariance;
	private InventorySlot slot;
	private List<DamageType> availableDamageTypes;
	private Rarity rarity;
	private Map<Attribute, Integer> attributeModifiers;
	private Map<DamageType, String> SVGStringMap;
	private List<Spell> spells;

	private WeaponType(String determiner, String pronoun, String name, String description, InventorySlot slot, String pathName, Rarity rarity, List<DamageType> availableDamageTypes, DamageLevel damageLevel, DamageVariance damageVariance,
			Map<Attribute, Integer> attributeModifiers, List<Spell> spells) {

		this.determiner = determiner;
		this.pronoun = pronoun;
		this.name = name;
		this.description = description;
		this.rarity = rarity;

		this.slot = slot;
		this.availableDamageTypes = availableDamageTypes;

		this.damageLevel = damageLevel;
		this.damageVariance = damageVariance;

		this.pathName = pathName;
		this.attributeModifiers = attributeModifiers;
		this.spells = spells;

		SVGStringMap = new EnumMap<>(DamageType.class);
		for (DamageType dt : this.availableDamageTypes)
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/base/res/weapons/"
						+ pathName
						+ ".svg");
				String s = Util.inputStreamToString(is);

				s = s.replaceAll("#ff2a2a", dt.getMultiplierAttribute().getColour().getShades()[0]);
				s = s.replaceAll("#ff5555", dt.getMultiplierAttribute().getColour().getShades()[1]);
				s = s.replaceAll("#ff8080", dt.getMultiplierAttribute().getColour().getShades()[2]);
				s = s.replaceAll("#ffaaaa", dt.getMultiplierAttribute().getColour().getShades()[3]);
				s = s.replaceAll("#ffd5d5", dt.getMultiplierAttribute().getColour().getShades()[4]);
				SVGStringMap.put(dt, s);

				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	public static AbstractWeapon generateWeapon(WeaponType wt, DamageType dt) {
		DamageType damageType = dt;

		if (wt.getAvailableDamageTypes() != null)
			if (!wt.getAvailableDamageTypes().contains(dt))
				dt = wt.getAvailableDamageTypes().get(Util.random.nextInt(wt.getAvailableDamageTypes().size()));

		return new AbstractWeapon(wt, damageType) {
			private static final long serialVersionUID = 1L;

			@Override
			public String onEquip(GameCharacter character) {
				if (character.isPlayer()) {
					if (Main.game.getPlayer().getWeaponsDiscovered().add(wt)) {
						Main.game.getPlayer().setNewWeaponDiscovered(true);
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
									+ "<b style='color:"+Colour.GENERIC_EXCELLENT()+";'>New entry in your phone's encyclopedia:</b>"
									+ "</br>"
									+ "<b>Weapon:</b> <b style='color:"+wt.getRarity().getColour()()+";'>"+Util.capitaliseSentence(wt.getName())+"</b>"
								+ "</p>");
					}
				}
				return wt.equipText(character);
			}

			@Override
			public String onUnequip(GameCharacter character) {
				return wt.unequipText(character);
			}
		};
	}

	/**
	 * Generates a weapon with random damage type
	 * 
	 * @param wt
	 * @param level
	 * @return
	 */
	public static AbstractWeapon generateWeapon(WeaponType wt) {
		return WeaponType.generateWeapon(wt, wt.getAvailableDamageTypes().get(Util.random.nextInt(wt.getAvailableDamageTypes().size())));
	}

	public abstract String equipText(GameCharacter character);

	public abstract String unequipText(GameCharacter character);
	
	public abstract String getAttackDescription(GameCharacter character, GameCharacter target);

	
	public static String genericMeleeAttackDescription(GameCharacter character, GameCharacter target) {
		if(character.isPlayer()) {
			return UtilText.returnStringAtRandom(
					"Darting forwards, you deliver a solid punch to [npc.name]'s [npc.arm].",
					
					"You throw a punch at [npc.name], grinning as you feel it connect with [npc.her] [npc.arm].",
					
					"You kick out at [npc.name], smiling to yourself as you feel your foot connect with [npc.her] [npc.leg].");
			
		} else {
			return UtilText.returnStringAtRandom(
					character.getName("The")+" punches you!");
			
		}	
	}
	
	public static String genericRangedAttackDescription(GameCharacter character, GameCharacter target) {
		if(character.isPlayer()) {
			return UtilText.returnStringAtRandom(
					"Punching your fist out towards [npc.name], a bolt of arcane energy shoots out to strike [npc.her] [npc.arm].",
					
					"Striking out towards [npc.name], a bolt of arcane energy shoots out of your fist to connect with [npc.her] [npc.arm].",
					
					"You kick out at [npc.name], and as you do, a bolt of arcane energy shoots out of your foot to connect with [npc.her] [npc.leg].");
			
		} else {
			return UtilText.returnStringAtRandom(
					character.getName("The")+" punches you!");
			
		}	
	}
	
	public String getDeterminer() {
		return determiner;
	}

	public String getPronoun() {
		return pronoun;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public InventorySlot getSlot() {
		return slot;
	}

	public String getPathName() {
		return pathName;
	}

	public DamageLevel getDamageLevel() {
		return damageLevel;
	}

	public DamageVariance getDamageVariance() {
		return damageVariance;
	}

	public Map<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<DamageType> getAvailableDamageTypes() {
		return availableDamageTypes;
	}

	public Map<DamageType, String> getSVGStringMap() {
		return SVGStringMap;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public static List<WeaponType> getRareWeapons() {
		return rareWeapons;
	}
}
