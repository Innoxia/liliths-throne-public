package com.base.game.inventory.weapon;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import com.base.game.inventory.AbstractCoreType;
import com.base.game.inventory.InventorySlot;
import com.base.game.inventory.Rarity;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.84
 * @version 0.1.84
 * @author Innoxia
 */
public abstract class AbstractWeaponType extends AbstractCoreType implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	private String determiner, pronoun, name, description, pathName;
	protected DamageLevel damageLevel;
	protected DamageVariance damageVariance;
	private InventorySlot slot;
	private List<DamageType> availableDamageTypes;
	private Rarity rarity;
	private Map<Attribute, Integer> attributeModifiers;
	private Map<DamageType, String> SVGStringMap;
	private List<Spell> spells;

	public AbstractWeaponType(String determiner, String pronoun, String name, String description, InventorySlot slot, String pathName, Rarity rarity, List<DamageType> availableDamageTypes, DamageLevel damageLevel, DamageVariance damageVariance,
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

	public static AbstractWeapon generateWeapon(AbstractWeaponType wt, DamageType dt) {
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
									+ "<b style='color:"+Colour.GENERIC_EXCELLENT.toWebHexString()+";'>New entry in your phone's encyclopedia:</b>"
									+ "</br>"
									+ "<b>Weapon:</b> <b style='color:"+wt.getRarity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(wt.getName())+"</b>"
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
	public static AbstractWeapon generateWeapon(AbstractWeaponType wt) {
		return AbstractWeaponType.generateWeapon(wt, wt.getAvailableDamageTypes().get(Util.random.nextInt(wt.getAvailableDamageTypes().size())));
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
}
