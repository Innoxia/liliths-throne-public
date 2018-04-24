package com.lilithsthrone.game.inventory.weapon;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.2.4
 * @author Innoxia
 */
public abstract class AbstractWeaponType extends AbstractCoreType implements Serializable {

	protected static final long serialVersionUID = 1L;

	private int baseValue;
	private boolean melee;
	private String determiner;
	private String pronoun;
	private String name;
	private String namePlural;
	private String attackDescriptor;
	private String description;
	private String pathName;
	protected int damage;
	protected int arcaneCost;
	protected DamageVariance damageVariance;
	private InventorySlot slot;
	private List<DamageType> availableDamageTypes;
	private Rarity rarity;
	private Map<Attribute, Integer> attributeModifiers;
	private Map<DamageType, String> SVGStringMap;
	private List<Spell> spells;

	public AbstractWeaponType(int baseValue,
			boolean melee,
			String determiner,
			String pronoun,
			String name,
			String namePlural,
			String attackDescriptor,
			String description,
			InventorySlot slot,
			String pathName,
			Rarity rarity,
			List<DamageType> availableDamageTypes,
			int damage,
			int arcaneCost,
			DamageVariance damageVariance,
			Map<Attribute, Integer> attributeModifiers,
			List<Spell> spells) {

		this.baseValue = baseValue;
		
		this.melee = melee;
		
		this.determiner = determiner;
		this.pronoun = pronoun;
		this.name = name;
		this.namePlural = namePlural;
		this.attackDescriptor = attackDescriptor;
		this.description = description;
		this.rarity = rarity;

		this.slot = slot;
		this.availableDamageTypes = availableDamageTypes;

		this.damage = damage;
		this.damageVariance = damageVariance;

		this.arcaneCost = arcaneCost;
		
		this.pathName = pathName;
		
		if(attributeModifiers==null) {
			this.attributeModifiers = new HashMap<>();
		} else {
			this.attributeModifiers = attributeModifiers;
		}
		
		if(spells==null) {
			this.spells = new ArrayList<>();
		} else {
			this.spells = spells;
		}

		SVGStringMap = new EnumMap<>(DamageType.class);
		for (DamageType dt : this.availableDamageTypes)
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/weapons/"
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
	
	@Override
	public boolean equals (Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractWeaponType){
				if(((AbstractWeaponType)o).getName().equals(getName())
						&& ((AbstractWeaponType)o).isMelee() == isMelee()
						&& ((AbstractWeaponType)o).getPathName().equals(getPathName())
						&& ((AbstractWeaponType)o).getDamage() == getDamage()
						&& ((AbstractWeaponType)o).getDamageVariance() == getDamageVariance()
						&& ((AbstractWeaponType)o).getSlot() == getSlot()
						&& ((AbstractWeaponType)o).getRarity() == getRarity()
						&& ((AbstractWeaponType)o).getAvailableDamageTypes().equals(getAvailableDamageTypes())
						&& ((AbstractWeaponType)o).getAttributeModifiers().equals(getAttributeModifiers())
						&& ((AbstractWeaponType)o).getSpells().equals(getSpells())
						){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() { // I know it doesn't include everything, but this should be enough to check for equality.
		int result = super.hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getPathName().hashCode();
		result = 31 * result + getDamage();
		result = 31 * result + getDamageVariance().hashCode();
		result = 31 * result + (melee ? 1 : 0);
		result = 31 * result + getSlot().hashCode();
		result = 31 * result + getRarity().hashCode();
		result = 31 * result + getAvailableDamageTypes().hashCode();
		result = 31 * result + getAttributeModifiers().hashCode();
		result = 31 * result + getSpells().hashCode();
		return result;
	}

	public static AbstractWeapon generateWeapon(AbstractWeaponType wt, DamageType dt) {
		
		if (wt.getAvailableDamageTypes() != null) {
			if (!wt.getAvailableDamageTypes().contains(dt)) {
				dt = wt.getAvailableDamageTypes().get(Util.random.nextInt(wt.getAvailableDamageTypes().size()));
			}
		}
		
		return new AbstractWeapon(wt, dt) {
			private static final long serialVersionUID = 1L;

			@Override
			public String onEquip(GameCharacter character) {
				if (character.isPlayer()) {
					if (Main.getProperties().addWeaponDiscovered(wt)) {
						Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(wt.getName(), wt.getRarity().getColour()), true);
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
	
	public String getId() {
		return WeaponType.weaponToIdMap.get(this);
	}

	public abstract String equipText(GameCharacter character);

	public abstract String unequipText(GameCharacter character);
	
	public abstract String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit);

	public static String genericMeleeAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
		if(isHit) {
			if(character.isPlayer()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
							"Darting forwards, you deliver a solid punch to [npc.name]'s [npc.arm].",
							"You throw a punch at [npc.name], grinning as you feel it connect with [npc.her] [npc.arm].",
							"You kick out at [npc.name], smiling to yourself as you feel your [pc.foot] connect with [npc.her] [npc.leg]."));
				
			} else {
				if(target.isPlayer()) {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"Darting forwards, [npc.name] delivers a solid punch to your [pc.arm].",
								"[npc.Name] throws a punch at you, grinning as [npc.her] attack connects with your [pc.arm].",
								"[npc.Name] kicks out at you, smiling to [npc.herself] as [npc.her] [npc.foot] connects with your [pc.leg]."));
				} else {
					return UtilText.parse(character, target,
							UtilText.returnStringAtRandom(
								"Darting forwards, [npc1.name] delivers a solid punch to [npc2.name]'s [npc2.arm].",
								"[npc1.Name] throws a punch at [npc2.name], grinning as [npc1.her] attack connects with [npc2.her] [npc2.arm].",
								"[npc1.Name] kicks out at [npc2.name], smiling to [npc1.herself] as [npc1.her] [npc1.foot] connects with [npc2.name]'s [npc2.leg]."));
				}
			}
			
		} else {
			if(character.isPlayer()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
							"Darting forwards, you try to deliver a punch to [npc.name]'s [npc.arm], but [npc.she] manages to step out of the way in time.",
							"You try to throw a punch at [npc.name], but fail to make contact with any part of [npc.her] body.",
							"You kick out at [npc.name], but your [pc.foot] sails harmlessly through the air."));
				
			} else {
				if(target.isPlayer()) {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"Darting forwards, [npc.name] tries to deliver a punch to your [pc.arm], but you manage to step out of the way in time.",
								"[npc.Name] throws a punch at you, but fails to make contact with any part of your body.",
								"[npc.Name] kicks out at you, but [npc.her] [npc.foot] sails harmlessly through the air."));
				} else {
					return UtilText.parse(character, target,
							UtilText.returnStringAtRandom(
								"Darting forwards, [npc1.name] tries to deliver a punch to [npc2.name]'s [npc2.arm], but [npc2.she] manages to step out of the way in time.",
								"[npc1.Name] throws a punch at [npc2.name], but fails to make contact with any part of [npc2.her] body.",
								"[npc1.Name] kicks out at [npc2.name], but [npc1.her] [npc1.foot] sails harmlessly through the air."));
				}
			}
		}
	}
	
	public static String genericRangedAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
		if(isHit) {
			if(character.isPlayer()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
							"Punching your fist out towards [npc.name], a bolt of arcane energy shoots out to strike [npc.her] [npc.arm].",
							"Striking out towards [npc.name], a bolt of arcane energy shoots out of your fist to connect with [npc.her] [npc.arm].",
							"You kick out at [npc.name], and as you do, a bolt of arcane energy shoots out of your [pc.foot] to connect with [npc.her] [npc.leg]."));
				
			} else {
				if(target.isPlayer()) {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"Punching [npc.her] fist out towards you, a bolt of arcane energy shoots out to strike your [pc.arm].",
								"Striking out towards you, a bolt of arcane energy shoots out of [npc.name]'s fist to connect with your [pc.arm].",
								"[npc.Name] kicks out at you, and as [npc.she] does so, a bolt of arcane energy shoots out of [npc.her] [npc.foot] to connect with your [pc.leg]."));
				} else {
					return UtilText.parse(character, target,
							UtilText.returnStringAtRandom(
								"Punching [npc1.her] fist out towards [npc2.name], a bolt of arcane energy shoots out to strike [npc2.her] [npc2.arm].",
								"Striking out towards [npc2.name], a bolt of arcane energy shoots out of [npc1.name]'s fist to connect with [npc2.her] [npc2.arm].",
								"[npc1.Name] kicks out at [npc2.name], and as [npc1.she] does so, a bolt of arcane energy shoots out of [npc1.her] [npc1.foot] to connect with [npc2.name]'s [npc2.leg]."));
				}
			}
			
		} else {
			if(character.isPlayer()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
							"Punching your fist out towards [npc.name], a bolt of arcane energy shoots out of your fist, sailing harmlessly through the air as [npc.she] dodges your attack.",
							"Striking out towards [npc.name], a bolt of arcane energy shoots out of your fist to sail harmlessly through the air as [npc.she] dodges your attack.",
							"You kick out at [npc.name], and as you do, a bolt of arcane energy shoots out of your [pc.foot] to sail harmlessly through the air as [npc.she] dodges your attack."));
				
			} else {
				if(target.isPlayer()) {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"Punching [npc.her] fist out towards you, a bolt of arcane energy shoots out to sail harmlessly through the air as you dodge [npc.her] attack.",
								"Striking out towards you, a bolt of arcane energy shoots out of [npc.name]'s fist to sail harmlessly through the air as you dodge [npc.her] attack.",
								"[npc.Name] kicks out at you, and as [npc.she] does so, a bolt of arcane energy shoots out of [npc.her] [npc.foot] to sail harmlessly through the air as you dodge [npc.her] attack."));
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"Punching [npc1.her] fist out towards [npc2.name], a bolt of arcane energy shoots out to sail harmlessly through the air as [npc2.name] dodges [npc1.her] attack.",
								"Striking out towards [npc2.name], a bolt of arcane energy shoots out of [npc1.name]'s fist to sail harmlessly through the air as [npc2.name] dodges [npc1.her] attack.",
								"[npc1.Name] kicks out at [npc2.name], and as [npc1.she] does so, a bolt of arcane energy shoots out of [npc1.her] [npc1.foot] to sail harmlessly through the air as [npc2.name] dodges [npc1.her] attack."));
				}
			}
		}
	}



	public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
		if(this.getArcaneCost()>0) {
			return user.getEssenceCount(TFEssence.ARCANE) > 0;
		} else {
			return true;
		}
	}

	public String getUnableToBeUsedDescription() {
		if(this.getArcaneCost()>0) {
			return "You need at least [style.boldBad(one)] [style.boldArcane(arcane essence)] in order to use this weapon!";
		} else {
			return "";
		}
	}
	
	public String applyExtraEfects(GameCharacter user, GameCharacter target, boolean isHit) {
		if(this.getArcaneCost()>0) {
			user.incrementEssenceCount(TFEssence.ARCANE, -this.getArcaneCost(), false);
			if(user.isPlayer()) {
				return "<p>"
							+ "Firing the "+this.getName()+" drains [style.boldBad("+Util.intToString(this.getArcaneCost())+")] [style.boldArcane(arcane essence)] from your aura!"
						+ "</p>";
			} else {
				return "<p>"
							+ UtilText.parse(user, "Firing the "+this.getName()+" drains [style.boldBad("+Util.intToString(this.getArcaneCost())+")] [style.boldArcane(arcane essence)] from [npc.name]'s aura!")
						+ "</p>";
			}
		} else {
			return "";
		}
	}
	
	public int getBaseValue() {
		return baseValue;
	}
	
	public boolean isMelee() {
		return melee;
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
	
	public String getNamePlural() {
		return namePlural;
	}

	public String getAttackDescriptor() {
		return attackDescriptor;
	}

	public abstract String getAttackDescription(GameCharacter user, GameCharacter target);

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

	public int getDamage() {
		return damage;
	}

	public DamageVariance getDamageVariance() {
		return damageVariance;
	}

	public int getArcaneCost() {
		return arcaneCost;
	}

	public Map<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public Map<Attribute, Integer> getGenerationAttributeModifiers(DamageType dt) {
		return null;
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
	
	public List<Spell> getGenerationSpells(DamageType dt) {
		return null;
	}
}
