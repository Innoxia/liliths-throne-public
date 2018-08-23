package com.lilithsthrone.game.inventory.weapon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.2.11
 * @author Innoxia
 */
public abstract class AbstractWeaponType extends AbstractCoreType {

	private int baseValue;
	private boolean isMod;
	
	private boolean melee;
	private boolean twoHanded;
	
	private String determiner;
	boolean plural;
	private String name;
	private String namePlural;
	private String attackDescriptor;
	private String attackTooltipDescription;
	private String description;

	private Rarity rarity;
	
	private String equipText;
	private String unequipText;
	private List<String> hitDescriptions;
	private List<String> missDescriptions;
	
	private String pathName;
	private String pathNameEquipped;
	
	protected int damage;
	protected int arcaneCost;
	protected DamageVariance damageVariance;
	private List<DamageType> availableDamageTypes;
	
	private List<Spell> spells;
	

	// Enchantments:
	private int enchantmentLimit;
	protected List<ItemEffect> effects;
	
	private Map<DamageType, Map<Colour, Map<Colour, String>>> SVGStringMap;
	private Map<DamageType, Map<Colour, Map<Colour, String>>> SVGStringEquippedMap;
	
	private List<Colour> availablePrimaryColours;
	private List<Colour> availablePrimaryDyeColours;
	private List<Colour> allAvailablePrimaryColours;
	
	private List<Colour> availableSecondaryColours;
	private List<Colour> availableSecondaryDyeColours;
	private List<Colour> allAvailableSecondaryColours;

	private List<ItemTag> itemTags;

	public AbstractWeaponType(int baseValue,
			boolean melee,
			boolean twoHanded,
			String pronoun,
			boolean plural,
			String name,
			String namePlural,
			String attackDescriptor,
			String description,
			String pathName,
			String pathNameEquipped,
			Rarity rarity,
			List<DamageType> availableDamageTypes,
			int damage,
			int arcaneCost,
			DamageVariance damageVariance,
			int enchantmentLimit,
			List<ItemEffect> effects,
			List<Spell> spells,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<ItemTag> itemTags) {
		this(baseValue,
				melee,
				twoHanded,
				pronoun,
				plural,
				name,
				namePlural,
				attackDescriptor,
				null,
				description,
				pathName,
				pathNameEquipped,
				rarity,
				availableDamageTypes,
				damage,
				arcaneCost,
				damageVariance,
				enchantmentLimit,
				effects,
				spells,
				availablePrimaryColours,
				availablePrimaryDyeColours,
				availableSecondaryColours,
				availableSecondaryDyeColours,
				itemTags,
				null,
				null,
				null,
				null);
	}
	
	public AbstractWeaponType(int baseValue,
			boolean melee,
			boolean twoHanded,
			String determiner,
			boolean plural,
			String name,
			String namePlural,
			String attackDescriptor,
			String attackTooltipDescription,
			String description,
			String pathName,
			String pathNameEquipped,
			Rarity rarity,
			List<DamageType> availableDamageTypes,
			int damage,
			int arcaneCost,
			DamageVariance damageVariance,
			int enchantmentLimit,
			List<ItemEffect> effects,
			List<Spell> spells,
			List<Colour> availablePrimaryColours,
			List<Colour> availablePrimaryDyeColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableSecondaryDyeColours,
			List<ItemTag> itemTags,
			String equipText,
			String unequipText,
			List<String> hitDescriptions,
			List<String> missDescriptions) {

		this.baseValue = baseValue;
		this.isMod = false;
		
		this.melee = melee;
		this.twoHanded = twoHanded;
		
		this.determiner = determiner;
		this.plural = plural;
		this.name = name;
		this.namePlural = namePlural;
		this.attackDescriptor = attackDescriptor;
		if(attackTooltipDescription!=null) {
			this.attackTooltipDescription = attackTooltipDescription;
		} else {
			this.attackTooltipDescription = "Use your "+this.getName()+" to strike at [npc2.name].";
		}
		this.description = description;
		this.rarity = rarity;

		this.availableDamageTypes = availableDamageTypes;
		
		if(spells==null) {
			this.spells = new ArrayList<>();
		} else {
			this.spells = spells;
		}

		this.damage = damage;
		this.damageVariance = damageVariance;

		this.arcaneCost = arcaneCost;
		
		this.pathName = pathName;
		this.pathNameEquipped = pathNameEquipped;

		this.enchantmentLimit = enchantmentLimit;

		if (effects != null) {
			this.effects = new ArrayList<>(effects);
		} else {
			this.effects = new ArrayList<>();
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
		SVGStringEquippedMap = new HashMap<>();
		
		if(equipText!=null) {
			this.equipText = equipText;
		} else {
			this.equipText = "[npc.Name] [npc.verb(equip)] [npc.her] "+this.getName()+".";
		}
		
		if(unequipText!=null) {
			this.unequipText = unequipText;
		} else {
			this.unequipText = "[npc.Name] [npc.verb(unequip)] [npc.her] "+this.getName()+".";
		}
		
		if(hitDescriptions!=null) {
			this.hitDescriptions = hitDescriptions;
		} else {
			this.hitDescriptions = Util.newArrayListOfValues("[npc.Name] hits [npc2.name] with [npc.her] "+this.getName()+"!");
		}
		
		if(missDescriptions!=null) {
			this.missDescriptions = missDescriptions;
		} else {
			this.missDescriptions = Util.newArrayListOfValues("[npc.Name] misses [npc2.name] with [npc.her] "+this.getName()+"!");
		}
		
	}
	
	public AbstractWeaponType(File weaponXMLFile) {
		this.itemTags = new ArrayList<>();

		if (weaponXMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(weaponXMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element weaponElement = (Element) doc.getElementsByTagName("weapon").item(0);
				
				Element coreAttributes = (Element) weaponElement.getElementsByTagName("coreAttributes").item(0);
				
				List<ItemTag> defaultItemTags = new ArrayList<>();
				Element itemTagsElement = (Element)coreAttributes.getElementsByTagName("itemTags").item(0);
				try {
					for(int i=0; i<itemTagsElement.getElementsByTagName("tag").getLength(); i++){
						Element e = ((Element)itemTagsElement.getElementsByTagName("tag").item(i));
						defaultItemTags.add(ItemTag.valueOf(e.getTextContent()));
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'itemTags' element unable to be parsed.");
				}
				this.itemTags = defaultItemTags;
				
				
				this.isMod = true;
				
				this.baseValue = Integer.valueOf(coreAttributes.getElementsByTagName("value").item(0).getTextContent());
				this.melee = Boolean.valueOf(coreAttributes.getElementsByTagName("melee").item(0).getTextContent());
				this.twoHanded = Boolean.valueOf(coreAttributes.getElementsByTagName("twoHanded").item(0).getTextContent());
				
				this.determiner = coreAttributes.getElementsByTagName("determiner").item(0).getTextContent();
				this.plural = Boolean.valueOf(((Element)coreAttributes.getElementsByTagName("namePlural").item(0)).getAttribute("pluralByDefault"));
				this.name = coreAttributes.getElementsByTagName("name").item(0).getTextContent();
				this.namePlural = coreAttributes.getElementsByTagName("namePlural").item(0).getTextContent();
				this.description = coreAttributes.getElementsByTagName("description").item(0).getTextContent();
				this.attackDescriptor = coreAttributes.getElementsByTagName("attackDescriptor").item(0).getTextContent();
				this.attackTooltipDescription = coreAttributes.getElementsByTagName("attackTooltipDescription").item(0).getTextContent();
				

				this.equipText = coreAttributes.getElementsByTagName("equipText").item(0).getTextContent();
				this.unequipText = coreAttributes.getElementsByTagName("unequipText").item(0).getTextContent();
				
				this.pathName = weaponXMLFile.getParentFile().getAbsolutePath() + "/" + coreAttributes.getElementsByTagName("imageName").item(0).getTextContent();
				
				this.pathNameEquipped = !coreAttributes.getElementsByTagName("imageEquippedName").item(0).hasChildNodes()
									? null
									: weaponXMLFile.getParentFile().getAbsolutePath() + "/" + coreAttributes.getElementsByTagName("imageEquippedName").item(0).getTextContent();

				this.damage = Integer.valueOf(coreAttributes.getElementsByTagName("damage").item(0).getTextContent());
				this.arcaneCost = Integer.valueOf(coreAttributes.getElementsByTagName("arcaneCost").item(0).getTextContent());
				this.damageVariance = DamageVariance.valueOf(coreAttributes.getElementsByTagName("damageVariance").item(0).getTextContent());
				
				this.availableDamageTypes = new ArrayList<>();
				try {
					if(coreAttributes.getElementsByTagName("availableDamageTypes").getLength() > 0) {
						for(int i=0; i<coreAttributes.getElementsByTagName("damageType").getLength(); i++){
							this.availableDamageTypes.add(DamageType.valueOf(coreAttributes.getElementsByTagName("damageType").item(i).getTextContent()));
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'availableDamageTypes' element unable to be parsed.");
				}
				
				this.spells = new ArrayList<>();
				try {
					if(coreAttributes.getElementsByTagName("spells").getLength() > 0) {
						for(int i=0; i<coreAttributes.getElementsByTagName("spell").getLength(); i++){
							this.spells.add(Spell.valueOf(coreAttributes.getElementsByTagName("spell").item(0).getTextContent()));
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'spells' element unable to be parsed.");
				}

				enchantmentLimit = Integer.valueOf(coreAttributes.getElementsByTagName("enchantmentLimit").item(0).getTextContent());
				
				List<ItemEffect> defaultEffects = new ArrayList<>();
				try {
					Element effectsElement = (Element)coreAttributes.getElementsByTagName("effects").item(0);
					for(int i=0; i<effectsElement.getElementsByTagName("effect").getLength(); i++){
						Element e = ((Element)effectsElement.getElementsByTagName("effect").item(i));
						try {
							ItemEffect ie = ItemEffect.loadFromXML(e, doc);
							if(ie!=null) {
								defaultEffects.add(ie);
							}
						}catch(Exception ex) {
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'effects' element unable to be parsed.");
				}
				this.effects = defaultEffects;

				this.rarity = Rarity.valueOf(coreAttributes.getElementsByTagName("rarity").item(0).getTextContent());
				
				// Hit/miss descriptions:
				
				this.hitDescriptions = new ArrayList<>();
				try {
					if(weaponElement.getElementsByTagName("hitDescriptions").getLength() > 0) {
						for(int i=0; i<weaponElement.getElementsByTagName("hitText").getLength(); i++){
							this.hitDescriptions.add(weaponElement.getElementsByTagName("hitText").item(i).getTextContent());
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'hitDescriptions' element unable to be parsed.");
				}
				
				this.missDescriptions = new ArrayList<>();
				try {
					if(weaponElement.getElementsByTagName("missDescriptions").getLength() > 0) {
						for(int i=0; i<weaponElement.getElementsByTagName("missText").getLength(); i++){
							this.missDescriptions.add(weaponElement.getElementsByTagName("missText").item(i).getTextContent());
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'missDescriptions' element unable to be parsed.");
				}
				
				
				List<Colour> importedPrimaryColours = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("primaryColours").item(0)).getAttribute("values").isEmpty()) {
						Element primaryColoursElement = ((Element)coreAttributes.getElementsByTagName("primaryColours").item(0));
						if(primaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<primaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedPrimaryColours.add(Colour.valueOf(((Element)primaryColoursElement.getElementsByTagName("colour").item(i)).getTextContent()));
							}
						}
					} else {
						importedPrimaryColours = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("primaryColours").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'primaryColours' element unable to be parsed.");
				}

				List<Colour> importedPrimaryColoursDye = new ArrayList<>();
				try {
					if(((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0)).getAttribute("values").isEmpty()) {
						Element primaryColoursElement = ((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0));
						if(primaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
							for(int i=0; i<primaryColoursElement.getElementsByTagName("colour").getLength(); i++){
								importedPrimaryColoursDye.add(Colour.valueOf(((Element)primaryColoursElement.getElementsByTagName("colour").item(i)).getTextContent()));
							}
						}
					} else {
						importedPrimaryColoursDye = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("primaryColoursDye").item(0)).getAttribute("values")).getPresetColourList();
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'primaryColoursDye' element unable to be parsed.");
				}

				List<Colour> importedSecondaryColours = new ArrayList<>();
				try {
					if((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0)!=null) {
						if(((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0)).getAttribute("values").isEmpty()) {
							Element secondaryColoursElement = ((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0));
							if(secondaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
								for(int i=0; i<secondaryColoursElement.getElementsByTagName("colour").getLength(); i++){
									importedSecondaryColours.add(Colour.valueOf(((Element)secondaryColoursElement.getElementsByTagName("colour").item(i)).getTextContent()));
								}
							}
						} else {
							importedSecondaryColours = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("secondaryColours").item(0)).getAttribute("values")).getPresetColourList();
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'secondaryColours' element unable to be parsed.");
				}

				List<Colour> importedSecondaryColoursDye = new ArrayList<>();
				try {
					if((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0)!=null) {
						if(((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0)).getAttribute("values").isEmpty()) {
							Element secondaryColoursElement = ((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0));
							if(secondaryColoursElement.getElementsByTagName("colour").getLength() > 0) {
								for(int i=0; i<secondaryColoursElement.getElementsByTagName("colour").getLength(); i++){
									importedSecondaryColoursDye.add(Colour.valueOf(((Element)secondaryColoursElement.getElementsByTagName("colour").item(i)).getTextContent()));
								}
							}
						} else {
							importedSecondaryColoursDye = ColourListPresets.valueOf(((Element)coreAttributes.getElementsByTagName("secondaryColoursDye").item(0)).getAttribute("values")).getPresetColourList();
						}
					}
				} catch(Exception ex) {
					System.err.println("AbstractWeaponType loading failed. Cause: 'secondaryColoursDye' element unable to be parsed.");
				}
				
				setUpColours(
						importedPrimaryColours,
						importedPrimaryColoursDye,
						importedSecondaryColours,
						importedSecondaryColoursDye);
				
				SVGStringMap = new HashMap<>();
				SVGStringEquippedMap = new HashMap<>();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("WeaponType was unable to be loaded from file! Exception message: "+ex.getMessage());
			}
		}
	}
	
	@Override
	public boolean equals (Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractWeaponType){
				if(((AbstractWeaponType)o).getName().equals(getName())
						&& ((AbstractWeaponType)o).isMelee() == isMelee()
						&& ((AbstractWeaponType)o).isTwoHanded() == isTwoHanded()
						&& ((AbstractWeaponType)o).getPathName().equals(getPathName())
						&& ((AbstractWeaponType)o).getDamage() == getDamage()
						&& ((AbstractWeaponType)o).getDamageVariance() == getDamageVariance()
						&& ((AbstractWeaponType)o).getRarity() == getRarity()
						&& ((AbstractWeaponType)o).getAvailableDamageTypes().equals(getAvailableDamageTypes())
						&& ((AbstractWeaponType)o).getSpells().equals(getSpells())
						&& ((AbstractWeaponType)o).getEffects().equals(getEffects())
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
		result = 31 * result + (twoHanded ? 1 : 0);
		result = 31 * result + getRarity().hashCode();
		result = 31 * result + getAvailableDamageTypes().hashCode();
		result = 31 * result + getSpells().hashCode();
		result = 31 * result + getEffects().hashCode();
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

	public String equipText(GameCharacter character) {
		return UtilText.parse(character, equipText);
	}

	public String unequipText(GameCharacter character) {
		return UtilText.parse(character, unequipText);
	}
	
	public String getAttackDescription(GameCharacter character, GameCharacter target, boolean isHit) {
		if(isHit) {
			return UtilText.parse(character, target, getHitText(character, target));
		} else {
			return UtilText.parse(character, target, getMissText(character, target));
		}
	}

	public String getHitText(GameCharacter character, GameCharacter target) {
		return UtilText.parse(character, target, Util.randomItemFrom(hitDescriptions));
	}

	public String getMissText(GameCharacter character, GameCharacter target) {
		return UtilText.parse(character, target, Util.randomItemFrom(missDescriptions));
	}

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

	public boolean isTwoHanded() {
		return twoHanded;
	}

	public String getDeterminer() {
		return determiner;
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

	public String getAttackDescription(GameCharacter user, GameCharacter target) {
		return UtilText.parse(user, target, attackTooltipDescription);
	}

	public String getDescription() {
		return description;
	}

	public Rarity getRarity() {
		return rarity;
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

	public List<DamageType> getAvailableDamageTypes() {
		return availableDamageTypes;
	}
	
	public List<Spell> getGenerationSpells(DamageType dt) {
		return null;
	}

	public List<Spell> getSpells() {
		return spells;
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
			if(isMod) {
				List<String> lines = Files.readAllLines(Paths.get(pathName));
				StringBuilder sb = new StringBuilder();
				for(String line : lines) {
					sb.append(line);
				}
				s = sb.toString();
				
			} else {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/weapons/" + pathName + ".svg");
				s = Util.inputStreamToString(is);
				is.close();
			}
			
			s = Util.colourReplacement(this.getId(), dt.getColour(), colourPrimary, colourSecondary, s);
			
			addSVGStringMapping(dt, colourPrimary, colourSecondary, s);
			
			return s;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "";
	}
	
	private void addSVGStringEquippedMapping(DamageType dt, Colour colourSecondary, Colour colourTertiary, String s) {
		if(SVGStringEquippedMap.get(dt)==null) {
			SVGStringEquippedMap.put(dt, new HashMap<>());
			SVGStringEquippedMap.get(dt).put(colourSecondary, new HashMap<>());
			
		} else if(SVGStringEquippedMap.get(dt).get(colourSecondary)==null) {
			SVGStringEquippedMap.get(dt).put(colourSecondary, new HashMap<>());
		}
		
		SVGStringEquippedMap.get(dt).get(colourSecondary).put(colourTertiary, s);
	}
	
	private String getSVGStringEquippedFromMap(DamageType dt, Colour colourSecondary, Colour colourTertiary) {
		if(SVGStringEquippedMap.get(dt)==null) {
			return null;
		} else {
			if(SVGStringEquippedMap.get(dt).get(colourSecondary)==null) {
				return null;
			} else {
				return SVGStringEquippedMap.get(dt).get(colourSecondary).get(colourTertiary);
			}
		}
	}
	
	public String getSVGEquippedImage() {
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
		
		return getSVGEquippedImage(dt, pColour, sColour);
	}
	
	public String getSVGEquippedImage(DamageType dt, Colour colourPrimary, Colour colourSecondary) {
		if (!this.getAvailableDamageTypes().contains(dt)) {
			return "";
		}
		
		String stringFromMap = getSVGStringEquippedFromMap(dt, colourPrimary, colourSecondary);
		if(stringFromMap!=null) {
			return stringFromMap;
		}
		
		try {
			InputStream is;
			String s;
			if(isMod) {
				List<String> lines = Files.readAllLines(Paths.get(pathNameEquipped));
				StringBuilder sb = new StringBuilder();
				for(String line : lines) {
					sb.append(line);
				}
				s = sb.toString();
				
			} else {
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/weapons/" + pathNameEquipped + ".svg");
				s = Util.inputStreamToString(is);
				is.close();
			}
			
			s = Util.colourReplacement(this.getId(), dt.getColour(), colourPrimary, colourSecondary, s);
			
			addSVGStringEquippedMapping(dt, colourPrimary, colourSecondary, s);
			
			return s;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "";
	}
	
	// Enchantments:

	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public int getEnchantmentLimit() {
		return enchantmentLimit;
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return ItemEffectType.WEAPON;
	}
	
	public TFEssence getRelatedEssence() {
		return TFEssence.ARCANE;
	}
	
	public AbstractWeaponType getEnchantmentItemType(List<ItemEffect> effects) {
		return this;
	}

	public List<ItemTag> getItemTags() {
		return itemTags;
	}
}
