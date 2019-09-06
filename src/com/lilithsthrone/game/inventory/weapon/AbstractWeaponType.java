package com.lilithsthrone.game.inventory.weapon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.SvgUtil;
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

	public String getAuthorDescription() {
		return authorDescription;
	}

	private ClothingSet clothingSet;
	private Rarity rarity;
	private float physicalResistance;
	
	private String equipText;
	private String unequipText;
	private List<String> hitDescriptions;
	private List<String> missDescriptions;
	
	private String pathName;
	private String pathNameEquipped;
	
	private String authorDescription;
	
	protected int damage;
	protected int arcaneCost;
	protected DamageVariance damageVariance;
	private List<DamageType> availableDamageTypes;
	
	private List<Spell> spells;
	

	// Enchantments:
	@SuppressWarnings("unused")
	private int enchantmentLimit; // Removed as part of 0.3.3.7's update to add enchantment capacity mechanics.
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
			float physicalResistance,
			ClothingSet clothingSet,
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
				physicalResistance,
				clothingSet,
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
			float physicalResistance,
			ClothingSet clothingSet,
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
		this.physicalResistance = physicalResistance;
		this.clothingSet = clothingSet;

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
		
		this.authorDescription = ""; // Do not give attribution to Innoxia's items.
		
	}
	
	@SuppressWarnings("deprecation")
	public AbstractWeaponType(File weaponXMLFile, String author) {
		this.itemTags = new ArrayList<>();

		if (weaponXMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(weaponXMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element weaponElement = Element.getDocumentRootElement(weaponXMLFile); // Loads the document and returns the root element - in clothing mods it's <clothing>
				Element coreAttributes = null;
				try {
					coreAttributes = weaponElement.getMandatoryFirstOf("coreAtributes");
				} catch (XMLMissingTagException ex) {
					coreAttributes = weaponElement.getMandatoryFirstOf("coreAttributes");
				}
				
				this.itemTags = coreAttributes
					.getMandatoryFirstOf("itemTags")
					.getAllOf("tag").stream()
					.map(Element::getTextContent).map(ItemTag::valueOf)
					.collect(Collectors.toList());
				
				this.isMod = true;
				
				this.baseValue = Integer.valueOf(coreAttributes.getMandatoryFirstOf("value").getTextContent());
				this.melee = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("melee").getTextContent());
				this.twoHanded = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("twoHanded").getTextContent());
				
				this.determiner = coreAttributes.getMandatoryFirstOf("determiner").getTextContent();
				this.plural = Boolean.valueOf(((Element)coreAttributes.getMandatoryFirstOf("namePlural")).getAttribute("pluralByDefault"));
				this.name = coreAttributes.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreAttributes.getMandatoryFirstOf("namePlural").getTextContent();
				this.description = coreAttributes.getMandatoryFirstOf("description").getTextContent();
				this.attackDescriptor = coreAttributes.getMandatoryFirstOf("attackDescriptor").getTextContent();
				this.attackTooltipDescription = coreAttributes.getMandatoryFirstOf("attackTooltipDescription").getTextContent();
				
				if(coreAttributes.getOptionalFirstOf("weaponAuthorTag").isPresent()) {
					this.authorDescription = coreAttributes.getMandatoryFirstOf("weaponAuthorTag").getTextContent();
				} else if(coreAttributes.getOptionalFirstOf("authorTag").isPresent()) {
					this.authorDescription = coreAttributes.getMandatoryFirstOf("authorTag").getTextContent();
				} else if(!author.equalsIgnoreCase("innoxia")){
					this.authorDescription = "A discreet inscription on the surface of the "+(plural?namePlural:name)+" informs you that "+(plural?"they were":"it was")+" made by a certain '"+Util.capitaliseSentence(author)+"'.";
				} else {
					this.authorDescription = "";
				}

				this.equipText = coreAttributes.getMandatoryFirstOf("equipText").getTextContent();
				this.unequipText = coreAttributes.getMandatoryFirstOf("unequipText").getTextContent();
				
				this.pathName = weaponXMLFile.getParentFile().getAbsolutePath() + "/" + coreAttributes.getMandatoryFirstOf("imageName").getTextContent();

				Predicate<Element> filterEmptyElements = element -> !element.getTextContent().isEmpty(); //helper function to filter out empty elements.
				
				this.pathNameEquipped = coreAttributes.getOptionalFirstOf("imageEquippedName")
					.filter(filterEmptyElements)
					.map(o -> weaponXMLFile.getParentFile().getAbsolutePath() + "/" + o.getTextContent())
					.orElse(pathName);

				this.damage = Integer.valueOf(coreAttributes.getMandatoryFirstOf("damage").getTextContent());
				this.arcaneCost = Integer.valueOf(coreAttributes.getMandatoryFirstOf("arcaneCost").getTextContent());
				this.damageVariance = DamageVariance.valueOf(coreAttributes.getMandatoryFirstOf("damageVariance").getTextContent());
				
				if(coreAttributes.getOptionalFirstOf("availableDamageTypes").isPresent()) {
					this.availableDamageTypes = coreAttributes
							.getMandatoryFirstOf("availableDamageTypes")
							.getAllOf("damageType").stream()
							.map(Element::getTextContent).map(DamageType::valueOf)
							.collect(Collectors.toList());
				} else {
					this.availableDamageTypes = new ArrayList<>();
				}
				
				if(coreAttributes.getOptionalFirstOf("spells").isPresent()) {
					this.spells = coreAttributes
							.getMandatoryFirstOf("spells")
							.getAllOf("spell").stream()
							.map(o -> o.getTextContent().replaceAll("DARK_SIREN_BANEFUL_FISSURE", "DARK_SIREN_SIRENS_CALL"))
							.map(Spell::valueOf)
							.collect(Collectors.toList());
				} else {
					this.spells = new ArrayList<>();
				}
				
				enchantmentLimit = Integer.valueOf(coreAttributes.getMandatoryFirstOf("enchantmentLimit").getTextContent());

				this.clothingSet = coreAttributes.getOptionalFirstOf("weaponSet")
					.filter(filterEmptyElements)
					.map(Element::getTextContent).map(ClothingSet::valueOf)
					.orElse(null);

				this.effects = coreAttributes
					.getMandatoryFirstOf("effects") 
					.getAllOf("effect") // Get all child elements with this tag (checking only contents of parent element) and return them as List<Element>
					.stream() // Convert this list to Stream<Element>, which lets us do some nifty operations on every element at once
					.map( e -> ItemEffect.loadFromXML(e.getInnerElement(), e.getDocument())) // Take every element and do something with them, return a Stream of results after this action. Here we load item effects and get Stream<ItemEffect>
					.filter(Objects::nonNull) // Ensure that we only add non-null effects
					.collect(Collectors.toList()); // Collect stream back into a list, but this time we get List<ItemEffect> we need! 
				

				this.rarity = Rarity.valueOf(coreAttributes.getMandatoryFirstOf("rarity").getTextContent());
				
				if(coreAttributes.getOptionalFirstOf("physicalResistance").isPresent()) {
					this.physicalResistance = Float.valueOf(coreAttributes.getMandatoryFirstOf("physicalResistance").getTextContent());
				}
				
				// Hit/miss descriptions:
				
				if(weaponElement.getOptionalFirstOf("hitDescriptions").isPresent()) {
					this.hitDescriptions = weaponElement
							.getMandatoryFirstOf("hitDescriptions")
							.getAllOf("hitText").stream()
							.map(o -> o.getTextContent())
							.collect(Collectors.toList());
				} else {
					this.hitDescriptions = new ArrayList<>();
				}
				
				if(weaponElement.getOptionalFirstOf("missDescriptions").isPresent()) {
					this.missDescriptions = weaponElement
							.getMandatoryFirstOf("missDescriptions")
							.getAllOf("missText").stream()
							.map(o -> o.getTextContent())
							.collect(Collectors.toList());
				} else {
					this.missDescriptions = new ArrayList<>();
				}
				

				Function< Element, List<Colour> > getColoursFromElement = (colorsElement) -> { //Helper function to get the colors depending on if it's a specified group or a list of individual colors
					String values = colorsElement.getAttribute("values");
					try {
						if(values.isEmpty()) {
							return colorsElement.getAllOf("colour").stream()
									.map(Element::getTextContent).map(Colour::valueOf)
									.collect(Collectors.toList());
						} else {
							return ColourListPresets.getColourListFromId(values);
						}
					} catch (Exception e) {
						printHelpfulErrorForEnumValueMismatches(e);
						throw new IllegalStateException("Colour tag reading failure: "+colorsElement.getTagName()+" " + e.getMessage(), e);
					}
				};
				

				List<Colour> importedPrimaryColours = getColoursFromElement
					.apply(coreAttributes.getMandatoryFirstOf("primaryColours"));	
				List<Colour> importedPrimaryColoursDye = getColoursFromElement
					.apply(coreAttributes.getMandatoryFirstOf("primaryColoursDye"));		

				List<Colour> importedSecondaryColours = coreAttributes.getOptionalFirstOf("secondaryColours")
					.map(getColoursFromElement::apply)
					.orElseGet(ArrayList::new);
				List<Colour> importedSecondaryColoursDye = coreAttributes.getOptionalFirstOf("secondaryColoursDye")
					.map(getColoursFromElement::apply)
					.orElseGet(ArrayList::new);
				
				setUpColours(
						importedPrimaryColours,
						importedPrimaryColoursDye,
						importedSecondaryColours,
						importedSecondaryColoursDye);
				
				SVGStringMap = new HashMap<>();
				SVGStringEquippedMap = new HashMap<>();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("WeaponType was unable to be loaded from file! (" + weaponXMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	@Override
	public boolean equals(Object o) { // I know it doesn't include everything, but this should be enough to check for equality.
		if(super.equals(o)){
			if(o instanceof AbstractWeaponType){
				if(((AbstractWeaponType)o).getName().equals(getName())
						&& ((AbstractWeaponType)o).isMelee() == isMelee()
						&& ((AbstractWeaponType)o).isTwoHanded() == isTwoHanded()
						&& ((AbstractWeaponType)o).getPathName().equals(getPathName())
						&& ((AbstractWeaponType)o).getPhysicalResistance() == getPhysicalResistance()
						&& ((AbstractWeaponType)o).getDamage() == getDamage()
						&& ((AbstractWeaponType)o).getDamageVariance() == getDamageVariance()
						&& ((AbstractWeaponType)o).getRarity() == getRarity()
						&& ((AbstractWeaponType)o).getAvailableDamageTypes().equals(getAvailableDamageTypes())
						&& ((AbstractWeaponType)o).getSpells().equals(getSpells())
						&& ((AbstractWeaponType)o).getEffects().equals(getEffects())
						&& ((AbstractWeaponType)o).getClothingSet() == getClothingSet()
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
		result = 31 * result + Float.floatToIntBits(getPhysicalResistance());
		result = 31 * result + getDamage();
		result = 31 * result + getDamageVariance().hashCode();
		result = 31 * result + (melee ? 1 : 0);
		result = 31 * result + (twoHanded ? 1 : 0);
		result = 31 * result + getRarity().hashCode();
		result = 31 * result + getAvailableDamageTypes().hashCode();
		result = 31 * result + getSpells().hashCode();
		result = 31 * result + getEffects().hashCode();
		if(getClothingSet()!=null) {
			result = 31 * result + getClothingSet().hashCode();
		}
		return result;
	}

	public static AbstractWeapon generateWeapon(AbstractWeaponType wt, DamageType dt) {
		return generateWeapon(wt, dt, null, null);
	}
	
	public static AbstractWeapon generateWeapon(String id, DamageType dt) {
		return generateWeapon(id, dt, null, null);
	}
	
	public static AbstractWeapon generateWeapon(String id, DamageType dt, Colour primaryColour, Colour secondaryColour) {
		return generateWeapon(WeaponType.getWeaponTypeFromId(id), dt, primaryColour, secondaryColour);
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

	public static AbstractWeapon generateWeapon(AbstractWeapon weapon) {
		return new AbstractWeapon(weapon) {
			@Override
			public String onEquip(GameCharacter character) {
				if (character.isPlayer()) {
					if (Main.getProperties().addWeaponDiscovered(weapon.getWeaponType())) {
						Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(weapon.getWeaponType().getName(), weapon.getWeaponType().getRarity().getColour()), true);
					}
				}
				return weapon.getWeaponType().equipText(character);
			}

			@Override
			public String onUnequip(GameCharacter character) {
				return weapon.getWeaponType().unequipText(character);
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

	@SuppressWarnings("rawtypes")
	private static void printHelpfulErrorForEnumValueMismatches(Exception ex) {
		Map<Class, Set<String>> possibleEnumValues = new HashMap<>();
		possibleEnumValues.put(ColourListPresets.class, ColourListPresets.getIdToColourListMap().keySet());
		String exMessage = ex.getMessage();
		if (exMessage.startsWith("No ColourListPreset constant")){
			for (Entry<Class, Set<String>> possibleMatch : possibleEnumValues.entrySet()) {
				if (exMessage.contains(possibleMatch.getKey().getCanonicalName())) {
					StringJoiner valueLister = new StringJoiner(",");
					Arrays.asList(possibleMatch.getValue()).forEach(enumValue -> valueLister.add(enumValue.toString()));
					System.err.println("Possible values for "+possibleMatch.getKey().getSimpleName()+" are " + valueLister.toString());
				}
			}
		}
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
	
	public String applyExtraEffects(GameCharacter user, GameCharacter target, boolean isHit) {
		if(this.getArcaneCost()>0) {
			user.incrementEssenceCount(TFEssence.ARCANE, -this.getArcaneCost(), false);
			if(user.isPlayer()) {
				return (this.isMelee()?"Using":"Firing")+" the "+this.getName()+" drains [style.boldBad("+Util.intToString(this.getArcaneCost())+")] [style.boldArcane(arcane essence)] from your aura!";
			} else {
				return UtilText.parse(user, (this.isMelee()?"Using":"Firing")+" the "+this.getName()+" drains [style.boldBad("+Util.intToString(this.getArcaneCost())+")] [style.boldArcane(arcane essence)] from [npc.namePos] aura!");
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
		if(isPlural()) {
			return namePlural;
		}
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

	public float getPhysicalResistance() {
		return physicalResistance;
	}

	public ClothingSet getClothingSet() {
		return clothingSet;
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
			
			s = SvgUtil.colourReplacement(this.getId(), dt.getColour(), colourPrimary, colourSecondary, s);
			
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
			
			s = SvgUtil.colourReplacement(this.getId()+"equipped", dt.getColour(), colourPrimary, colourSecondary, s);
			
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
	
	public boolean isAbleToBeSold() {
		return getRarity()!=Rarity.QUEST;
	}
	
	public boolean isAbleToBeDropped() {
		return getRarity()!=Rarity.QUEST;
	}
	
	public int getEnchantmentLimit() {
		return 100;
//		if(enchantmentLimit==-1) {
//			return (getClothingSet()==null?5:10);
//		} else {
//			return enchantmentLimit;
//		}
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
