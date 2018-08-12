package com.lilithsthrone.game.inventory.weapon;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.2.6
 * @author Innoxia
 */
public abstract class AbstractWeaponType extends AbstractCoreType implements Serializable {

	protected static final long serialVersionUID = 1L;

	private int baseValue;
	private boolean melee;
	private String determiner;
	private String pronoun;
	boolean plural;
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
	private Map<DamageType, Map<Colour, Map<Colour, String>>> SVGStringMap;
	private List<Spell> spells;
	
	private List<Colour> availablePrimaryColours;
	private List<Colour> availablePrimaryDyeColours;
	private List<Colour> allAvailablePrimaryColours;
	
	private List<Colour> availableSecondaryColours;
	private List<Colour> availableSecondaryDyeColours;
	private List<Colour> allAvailableSecondaryColours;

	private List<ItemTag> itemTags;

	public AbstractWeaponType(int baseValue,
			boolean melee,
			String determiner,
			String pronoun,
			boolean plural,
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
			List<Spell> spells,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<ItemTag> itemTags) {

		this.baseValue = baseValue;
		
		this.melee = melee;
		
		this.determiner = determiner;
		this.pronoun = pronoun;
		this.plural = plural;
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
		
		if(itemTags==null) {
			this.itemTags = new ArrayList<>();
		} else {
			this.itemTags = itemTags;
		}

		setUpColours(availablePrimaryColours,
				availablePrimaryDyeColours,
				availableSecondaryColours,
				availableSecondaryDyeColours);

		SVGStringMap = new HashMap<>();
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
		
		return generateWeapon(wt, dt, null, null);
	}
	
	public static AbstractWeapon generateWeapon(AbstractWeaponType wt, DamageType dt, Colour primaryColour, Colour secondaryColour) {
		
		if (wt.getAvailableDamageTypes() != null) {
			if (!wt.getAvailableDamageTypes().contains(dt)) {
				dt = wt.getAvailableDamageTypes().get(Util.random.nextInt(wt.getAvailableDamageTypes().size()));
			}
		}
		
		Colour c1 = primaryColour;
		Colour c2 = secondaryColour;
		
		if (primaryColour == null || !wt.getAllAvailablePrimaryColours().contains(primaryColour)) {
			if(wt.getAvailablePrimaryColours().isEmpty()) {
				c1 = Colour.CLOTHING_BLACK;
			} else {
				c1 = wt.getAvailablePrimaryColours().get(Util.random.nextInt(wt.getAvailablePrimaryColours().size()));
			}
		}
		
		if (secondaryColour == null || !wt.getAllAvailableSecondaryColours().contains(secondaryColour)) {
			if(wt.getAvailableSecondaryColours().isEmpty()) {
				c2 = Colour.CLOTHING_BLACK;
			} else {
				c2 = wt.getAvailableSecondaryColours().get(Util.random.nextInt(wt.getAvailableSecondaryColours().size()));
			}
		}
		
		return new AbstractWeapon(wt, dt, c1, c2) {
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

	private void setUpColours(List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours) {
		
		this.availablePrimaryColours = new ArrayList<>();
		if (availablePrimaryColours != null) {
			this.availablePrimaryColours.addAll(availablePrimaryColours);
		}

		Set<Colour> colourSet = new HashSet<>();
		
		this.availablePrimaryDyeColours = new ArrayList<>();
		if (availablePrimaryDyeColours != null) {
			this.availablePrimaryDyeColours.addAll(availablePrimaryDyeColours);
		}
		
		this.allAvailablePrimaryColours = new ArrayList<>();
		colourSet.addAll(this.availablePrimaryColours);
		if(availablePrimaryDyeColours!=null) {
			colourSet.addAll(availablePrimaryDyeColours);
		}
		this.allAvailablePrimaryColours.addAll(colourSet);
		this.allAvailablePrimaryColours.sort((c1, c2) -> c1.compareTo(c2));
		
		this.availableSecondaryColours = new ArrayList<>();
		if (availableSecondaryColours != null) {
			this.availableSecondaryColours.addAll(availableSecondaryColours);
		}
		
		this.availableSecondaryDyeColours = new ArrayList<>();
		if (availableSecondaryDyeColours != null) {
			this.availableSecondaryDyeColours.addAll(availableSecondaryDyeColours);
		}

		colourSet.clear();
		this.allAvailableSecondaryColours = new ArrayList<>();
		if(availableSecondaryColours!=null) {
			colourSet.addAll(availableSecondaryColours);
		}
		if(availableSecondaryDyeColours!=null) {
			colourSet.addAll(availableSecondaryDyeColours);
		}
		this.allAvailableSecondaryColours.addAll(colourSet);
		this.allAvailableSecondaryColours.sort((c1, c2) -> c1.compareTo(c2));
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

	protected static String getDescriptions(GameCharacter character, GameCharacter target, boolean isHit,
			String playerStrikingNPC,
			String NPCStrikingPlayer,
			String NPCStrikingNPC,
			String playerMissingNPC,
			String NPCMissingPlayer,
			String NPCMissingNPC) {
		if(isHit) {
			if(character.isPlayer()) {
				return UtilText.parse(target, playerStrikingNPC);
				
			} else {
				if(target.isPlayer()) {
					return UtilText.parse(character, NPCStrikingPlayer);
				} else {
					return UtilText.parse(character, target, NPCStrikingNPC);
				}
			}
			
		} else {
			if(character.isPlayer()) {
				return UtilText.parse(target, playerMissingNPC);
				
			} else {
				if(target.isPlayer()) {
					return UtilText.parse(character, NPCMissingPlayer);
				} else {
					return UtilText.parse(character, target, NPCMissingNPC);
				}
			}
		}
	}
	
	public static String genericMeleeAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
		if(isHit) {
			if(character.isPlayer()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
							"Darting forwards, you deliver a solid punch to [npc.namePos] [npc.arm].",
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
								"Darting forwards, [npc1.name] delivers a solid punch to [npc2.namePos] [npc2.arm].",
								"[npc1.Name] throws a punch at [npc2.name], grinning as [npc1.her] attack connects with [npc2.her] [npc2.arm].",
								"[npc1.Name] kicks out at [npc2.name], smiling to [npc1.herself] as [npc1.her] [npc1.foot] connects with [npc2.namePos] [npc2.leg]."));
				}
			}
			
		} else {
			if(character.isPlayer()) {
				return UtilText.parse(target,
						UtilText.returnStringAtRandom(
							"Darting forwards, you try to deliver a punch to [npc.namePos] [npc.arm], but [npc.she] manages to step out of the way in time.",
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
								"Darting forwards, [npc1.name] tries to deliver a punch to [npc2.namePos] [npc2.arm], but [npc2.she] manages to step out of the way in time.",
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
								"Striking out towards you, a bolt of arcane energy shoots out of [npc.namePos] fist to connect with your [pc.arm].",
								"[npc.Name] kicks out at you, and as [npc.she] does so, a bolt of arcane energy shoots out of [npc.her] [npc.foot] to connect with your [pc.leg]."));
				} else {
					return UtilText.parse(character, target,
							UtilText.returnStringAtRandom(
								"Punching [npc1.her] fist out towards [npc2.name], a bolt of arcane energy shoots out to strike [npc2.her] [npc2.arm].",
								"Striking out towards [npc2.name], a bolt of arcane energy shoots out of [npc1.namePos] fist to connect with [npc2.her] [npc2.arm].",
								"[npc1.Name] kicks out at [npc2.name], and as [npc1.she] does so, a bolt of arcane energy shoots out of [npc1.her] [npc1.foot] to connect with [npc2.namePos] [npc2.leg]."));
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
								"Striking out towards you, a bolt of arcane energy shoots out of [npc.namePos] fist to sail harmlessly through the air as you dodge [npc.her] attack.",
								"[npc.Name] kicks out at you, and as [npc.she] does so, a bolt of arcane energy shoots out of [npc.her] [npc.foot] to sail harmlessly through the air as you dodge [npc.her] attack."));
				} else {
					return UtilText.parse(character,
							UtilText.returnStringAtRandom(
								"Punching [npc1.her] fist out towards [npc2.name], a bolt of arcane energy shoots out to sail harmlessly through the air as [npc2.name] dodges [npc1.her] attack.",
								"Striking out towards [npc2.name], a bolt of arcane energy shoots out of [npc1.namePos] fist to sail harmlessly through the air as [npc2.name] dodges [npc1.her] attack.",
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
							+ UtilText.parse(user, "Firing the "+this.getName()+" drains [style.boldBad("+Util.intToString(this.getArcaneCost())+")] [style.boldArcane(arcane essence)] from [npc.namePos] aura!")
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

	public boolean isPlural() {
		return plural;
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

	public List<Spell> getSpells() {
		return spells;
	}
	
	public List<Spell> getGenerationSpells(DamageType dt) {
		return null;
	}
	

	public List<Colour> getAvailablePrimaryColours() {
		return availablePrimaryColours;
	}
	
	public List<Colour> getAvailablePrimaryDyeColours() {
		return availablePrimaryDyeColours;
	}
	
	public List<Colour> getAllAvailablePrimaryColours() {
		return allAvailablePrimaryColours;
	}

	public List<Colour> getAvailableSecondaryColours() {
		return availableSecondaryColours;
	}

	public List<Colour> getAvailableSecondaryDyeColours() {
		return availableSecondaryDyeColours;
	}
	
	public List<Colour> getAllAvailableSecondaryColours() {
		return allAvailableSecondaryColours;
	}

	private void addSVGStringMapping(DamageType dt, Colour colourSecondary, Colour colourTertiary, String s) {
		if(SVGStringMap.get(dt)==null) {
			SVGStringMap.put(dt, new HashMap<>());
			SVGStringMap.get(dt).put(colourSecondary, new HashMap<>());
			
		} else if(SVGStringMap.get(dt).get(colourSecondary)==null) {
			SVGStringMap.get(dt).put(colourSecondary, new HashMap<>());
		}
		
		SVGStringMap.get(dt).get(colourSecondary).put(colourTertiary, s);
	}
	
	private String getSVGStringFromMap(DamageType dt, Colour colourSecondary, Colour colourTertiary) {
		if(SVGStringMap.get(dt)==null) {
			return null;
		} else {
			if(SVGStringMap.get(dt).get(colourSecondary)==null) {
				return null;
			} else {
				return SVGStringMap.get(dt).get(colourSecondary).get(colourTertiary);
			}
		}
	}
	

	public String getSVGImage() {
		
		DamageType dt = DamageType.PHYSICAL;
		if (this.getAvailableDamageTypes() != null) {
			if (!this.getAvailableDamageTypes().contains(dt)) {
				dt = this.getAvailableDamageTypes().get(0);
			}
		}
		
		Colour pColour = Colour.CLOTHING_BLACK;
		if(this.getAllAvailablePrimaryColours()!=null && !this.getAllAvailablePrimaryColours().isEmpty()) {
			pColour = this.getAllAvailablePrimaryColours().get(0);
		}
		Colour sColour = Colour.CLOTHING_BLACK;
		if(this.getAllAvailableSecondaryColours()!=null && !this.getAllAvailableSecondaryColours().isEmpty()) {
			sColour = this.getAllAvailableSecondaryColours().get(0);
		}
		
		return getSVGImage(dt, pColour, sColour);
	}
	
	public String getSVGImage(DamageType dt, Colour colourPrimary, Colour colourSecondary) {
		if (!this.getAvailableDamageTypes().contains(dt)) {
			return "";
		}
		
		String stringFromMap = getSVGStringFromMap(dt, colourPrimary, colourSecondary);
		if(stringFromMap!=null) {
			return stringFromMap;
		}
		
		try {
			
			InputStream is;
			String s;
//			if(isMod) { TODO
//				List<String> lines = Files.readAllLines(Paths.get(pathName));
//				StringBuilder sb = new StringBuilder();
//				for(String line : lines) {
//					sb.append(line);
//				}
//				s = sb.toString();
//			} else {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/weapons/" + pathName + ".svg");
				s = Util.inputStreamToString(is);
				is.close();
//			}
			
			s = Util.colourReplacement(this.getId(), dt.getColour(), colourPrimary, colourSecondary, s);
			
			addSVGStringMapping(dt, colourPrimary, colourSecondary, s);
			
			return s;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "";
	}

	public List<ItemTag> getItemTags() {
		return itemTags;
	}
}
