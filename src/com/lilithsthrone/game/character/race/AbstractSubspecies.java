package com.lilithsthrone.game.character.race;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.4
 * @version 0.4.0
 * @author Innoxia
 */
public abstract class AbstractSubspecies {

	private boolean mod;
	private boolean fromExternalFile;

	private boolean mainSubspecies;
	
	private int baseSlaveValue;
	private int subspeciesOverridePriority;
	
	private boolean shortStature;
	private boolean bipedalSubspecies;
	private boolean aquatic;
	
	private String applySubspeciesChanges;
	private String subspeciesWeighting;
	
	private String attributeItemId;
	private String transformativeItemId;
	
	private String name;
	private String namePlural;
	private String singularMaleName;
	private String singularFemaleName;
	private String pluralMaleName;
	private String pluralFemaleName;

	private String nameHalfDemon;
	private String namePluralHalfDemon;
	private String singularMaleNameHalfDemon;
	private String singularFemaleNameHalfDemon;
	private String pluralMaleNameHalfDemon;
	private String pluralFemaleNameHalfDemon;
	
	private FeralAttributes feralAttributes;
	
	private String statusEffectDescription;
	private Map<PerkCategory, Integer> perkWeightingFeminine;
	private Map<PerkCategory, Integer> perkWeightingMasculine;
	private Map<AbstractAttribute, Float> statusEffectAttributeModifiers;
	private List<String> extraEffects;

	private String bookName;
	private String bookNamePlural;
	private String bookIdFolderPath;
	private String basicDescriptionId;
	private String advancedDescriptionId;
	
	private AbstractRace race;
	private SubspeciesPreference subspeciesPreferenceDefault;
	private String description;

	// SVGs:
	private Colour colour;
	private Colour secondaryColour;
	private Colour tertiaryColour;
	
	protected int iconSize;
	protected String pathName;
	protected String backgroundPathName;
	protected boolean externalFileBackground;
	
	protected String SVGString;
	protected String SVGStringUncoloured;
	protected String SVGStringNoBackground;
	protected String SVGStringDesaturated;
	protected String slimeSVGString;
	protected String halfDemonSVGString;
	protected String demonSVGString;
	
	protected String bookPathName;
	protected String bookSVGString;

	private Map<WorldRegion, SubspeciesSpawnRarity> regionLocations;
	private Map<AbstractWorldType, SubspeciesSpawnRarity> worldLocations;
	
	private List<SubspeciesFlag> flags;

	protected static Map<Integer, String> youkoIconMap;
	protected static Map<Integer, String> youkoDesaturatedIconMap;
	protected static Map<Integer, String> youkoHalfDemonIconMap;

	public static Map<LegConfiguration, String[]> demonLegConfigurationNames = Util.newHashMapOfValues(
			new Value<>(LegConfiguration.ARACHNID,
					new String[] {
						"demonid",
						"demonids",
						"incunid",
						"succunid",
						"incunids",
						"succunids"}),
			new Value<>(LegConfiguration.BIPEDAL,
					new String[] {
						"demon",
						"demons",
						"incubus",
						"succubus",
						"incubi",
						"succubi"}),
			new Value<>(LegConfiguration.CEPHALOPOD,
					new String[] {
						"demopus",
						"demopuses",
						"incupus",
						"succupus",
						"incupuses",
						"succupuses"}),
			new Value<>(LegConfiguration.QUADRUPEDAL,
					new String[] {
						"demotaur",
						"demotaurs",
						"incutaur",
						"succutaur",
						"incutaurs",
						"succutaurs"}),
			new Value<>(LegConfiguration.TAIL,
					new String[] {
						"demomer",
						"demomers",
						"incumer",
						"succumer",
						"incumers",
						"succumers"}),
			new Value<>(LegConfiguration.TAIL_LONG,
					new String[] {
						"demomia",
						"demomias",
						"incumia",
						"succumia",
						"incumias",
						"succumias"}),
			new Value<>(LegConfiguration.AVIAN,
					new String[] {
						"demoa",
						"demoas",
						"incumoa",
						"succumoa",
						"incumoas",
						"succumoas"}));
	
	static {
		youkoIconMap = new HashMap<>();
		youkoHalfDemonIconMap = new HashMap<>();
		for(int i=1; i<=9; i++) {
			try {
				String SVGStringBackground = "";
				InputStream is = Subspecies.class.getClassLoader().getResourceAsStream("com/lilithsthrone/res/statusEffects/race/raceBackground.svg");
				if(is==null) {
					System.err.println("Error! Subspecies background icon file does not exist (Trying to read from 'statusEffects/race/raceBackground')! (Code 1f)");
				}
				SVGStringBackground = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+Util.inputStreamToString(is)+"</div>";

				is.close();
				
				String baseSVGString = SVGStringBackground + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getFoxTail(i)+"</div>";
				youkoIconMap.put(i, baseSVGString);

				baseSVGString = SvgUtil.colourReplacement("youkohalfDemon"+i,
							PresetColour.RACE_HALF_DEMON,
							PresetColour.RACE_HALF_DEMON,
							PresetColour.RACE_HALF_DEMON,
							"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>" + SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundDemon()+"</div>"
								+ "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDemon(i)+"</div>");
				youkoHalfDemonIconMap.put(i, baseSVGString);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		youkoDesaturatedIconMap = new HashMap<>();
		for(int i=1; i<=9; i++) {
			try {
				String SVGStringBackground = "";
				InputStream is = Subspecies.class.getClassLoader().getResourceAsStream("com/lilithsthrone/res/statusEffects/race/raceBackground.svg");
				if(is==null) {
					System.err.println("Error! Subspecies background icon file does not exist (Trying to read from 'statusEffects/race/raceBackground')! (Code 2f)");
				}
				SVGStringBackground = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+Util.inputStreamToString(is)+"</div>";
				
				is.close();
				
				String baseSVGString = SVGStringBackground + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getFoxTailDesaturated(i)+"</div>";
				
				baseSVGString = SvgUtil.colourReplacement("youkoGradient"+i,
						PresetColour.BASE_GREY,
						PresetColour.BASE_GREY,
						PresetColour.BASE_GREY,
						baseSVGString);
				
				youkoDesaturatedIconMap.put(i, baseSVGString);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public AbstractSubspecies(
			boolean mainSubspecies,
			int baseSlaveValue,
			String attributeItemId,
			String transformativeItemId,
			String pathName,
			String backgroundPathName,
			String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,
			FeralAttributes feralAttributes,
			String statusEffectDescription,
			Map<AbstractAttribute, Float> statusEffectAttributeModifiers,
			List<String> extraEffects,
			String bookName,
			String bookNamePlural,
			String basicDescription,
			String advancedDescription,
			AbstractRace race,
			Map<PerkCategory, Integer> perkWeightingFeminine,
			Map<PerkCategory, Integer> perkWeightingMasculine,
			Colour colour,
			SubspeciesPreference subspeciesPreferenceDefault,
			String description,
			Map<WorldRegion, SubspeciesSpawnRarity> regionLocations,
			Map<AbstractWorldType, SubspeciesSpawnRarity> worldLocations,
			List<SubspeciesFlag> flags) {
		
		this.mainSubspecies = mainSubspecies;
		
		this.baseSlaveValue = baseSlaveValue;
		this.subspeciesOverridePriority = 0;
		
		this.aquatic = false;
		this.shortStature = false;
		this.bipedalSubspecies = true;
		
		this.attributeItemId = attributeItemId;
		this.transformativeItemId = transformativeItemId;
		
		this.name = name;
		this.namePlural = namePlural;

		this.singularMaleName = singularMaleName;
		this.singularFemaleName = singularFemaleName;
		
		this.pluralMaleName = pluralMaleName;
		this.pluralFemaleName = pluralFemaleName;
		
		this.feralAttributes = feralAttributes;
		
		this.statusEffectDescription = statusEffectDescription;
		
		this.statusEffectAttributeModifiers = statusEffectAttributeModifiers;
		if(this.statusEffectAttributeModifiers!=null) {
			this.statusEffectAttributeModifiers.entrySet().removeIf((entry) -> entry.getValue()==0);
		}
		
		if(perkWeightingFeminine!=null) {
			this.perkWeightingFeminine = perkWeightingFeminine;
		} else {
			this.perkWeightingFeminine = new HashMap<>();
		}
		if(perkWeightingMasculine!=null) {
			this.perkWeightingMasculine = perkWeightingMasculine;
		} else {
			this.perkWeightingMasculine = new HashMap<>();
		}

		if(extraEffects == null) {
			this.extraEffects = new ArrayList<>();
		} else {
			this.extraEffects = extraEffects;
		}
		
		this.bookName = bookName;
		this.bookNamePlural = bookNamePlural;
		
		this.bookIdFolderPath = "";
		this.basicDescriptionId = basicDescription;
		this.advancedDescriptionId = advancedDescription;

		this.colour = colour;
		this.secondaryColour = colour;
		this.tertiaryColour = colour;
		
		this.race = race;
		this.subspeciesPreferenceDefault = subspeciesPreferenceDefault;
		this.description = description;
		
		if(regionLocations == null) {
			this.regionLocations = new HashMap<>();
		} else {
			this.regionLocations = regionLocations;
		}
		
		if(worldLocations == null) {
			this.worldLocations = new HashMap<>();
		} else {
			this.worldLocations = worldLocations;
		}
		
		if(flags == null) {
			this.flags = new ArrayList<>();
		} else {
			this.flags = flags;
		}
		
		this.externalFileBackground = false;
		this.pathName = "/com/lilithsthrone/res/" + pathName;
		this.bookPathName = "/com/lilithsthrone/res/" + pathName;
		this.backgroundPathName = "/com/lilithsthrone/res/" + backgroundPathName;
		this.SVGString = null;
	}
	
	public AbstractSubspecies(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);
				
				this.mod = mod;
				this.fromExternalFile = true;

				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				
				this.colour = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("colour").getTextContent());
				this.secondaryColour = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("secondaryColour").getTextContent());
				this.tertiaryColour = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("tertiaryColour").getTextContent());
				
				this.mainSubspecies = Boolean.valueOf(coreElement.getMandatoryFirstOf("mainSubspecies").getTextContent());
				this.baseSlaveValue = Integer.valueOf(coreElement.getMandatoryFirstOf("baseSlaveValue").getTextContent());

				this.attributeItemId = coreElement.getMandatoryFirstOf("attributeItemId").getTextContent();
				if(this.attributeItemId.isEmpty()) {
					this.attributeItemId = "innoxia_race_human_vanilla_water";
				}
				
				this.transformativeItemId = coreElement.getMandatoryFirstOf("transformativeItemId").getTextContent();
				if(this.transformativeItemId.isEmpty()) {
					this.transformativeItemId = "innoxia_race_human_bread_roll";
				}
				
				this.subspeciesOverridePriority = Integer.valueOf(coreElement.getMandatoryFirstOf("subspeciesOverridePriority").getTextContent());
				
				this.shortStature = Boolean.valueOf(coreElement.getMandatoryFirstOf("shortStature").getTextContent());
				this.bipedalSubspecies = Boolean.valueOf(coreElement.getMandatoryFirstOf("bipedalSubspecies").getTextContent());
				this.aquatic = Boolean.valueOf(coreElement.getMandatoryFirstOf("aquatic").getTextContent());
				
				this.applySubspeciesChanges = coreElement.getMandatoryFirstOf("applySubspeciesChanges").getTextContent();
				this.subspeciesWeighting = coreElement.getMandatoryFirstOf("subspeciesWeighting").getTextContent();
				
				this.pathName = XMLFile.getParentFile().getAbsolutePath() + "/"+ coreElement.getMandatoryFirstOf("iconName").getTextContent();
				if(!coreElement.getMandatoryFirstOf("iconName").getAttribute("displaySize").isEmpty()) {
					this.iconSize = Integer.valueOf(coreElement.getMandatoryFirstOf("iconName").getAttribute("displaySize"));
				} else {
					this.iconSize = 100;
				}
				
				if(coreElement.getOptionalFirstOf("backgroundName").isPresent() && !coreElement.getMandatoryFirstOf("backgroundName").getTextContent().isEmpty()) {
					this.backgroundPathName = XMLFile.getParentFile().getAbsolutePath() + "/"+ coreElement.getMandatoryFirstOf("backgroundName").getTextContent();
					this.externalFileBackground = true;
				} else {
					this.backgroundPathName = "/com/lilithsthrone/res/statusEffects/race/raceBackground";
					this.externalFileBackground = false;
				}
				this.SVGString = null;
				
				this.bookPathName = XMLFile.getParentFile().getAbsolutePath() + "/" + coreElement.getMandatoryFirstOf("bookIconName").getTextContent();
				
				this.bookName = coreElement.getMandatoryFirstOf("bookName").getTextContent();
				this.bookNamePlural = bookName; // There is no need for a plural
				
				this.bookIdFolderPath = XMLFile.getParentFile().getAbsolutePath();
				bookIdFolderPath = "res"+bookIdFolderPath.split("res")[1];
//				System.out.println(bookIdFolderPath);
				this.basicDescriptionId = coreElement.getMandatoryFirstOf("basicDescriptionId").getTextContent();
				this.advancedDescriptionId = coreElement.getMandatoryFirstOf("advancedDescriptionId").getTextContent();
				
				this.subspeciesPreferenceDefault = SubspeciesPreference.valueOf(coreElement.getMandatoryFirstOf("defaultPreference").getTextContent());
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				this.singularMaleName = coreElement.getMandatoryFirstOf("singularMaleName").getTextContent();
				this.singularFemaleName = coreElement.getMandatoryFirstOf("singularFemaleName").getTextContent();
				this.pluralMaleName = coreElement.getMandatoryFirstOf("pluralMaleName").getTextContent();
				this.pluralFemaleName = coreElement.getMandatoryFirstOf("pluralFemaleName").getTextContent();

				this.description = coreElement.getMandatoryFirstOf("description").getTextContent();

				if(coreElement.getOptionalFirstOf("nameHalfDemon").isPresent()) {
					this.nameHalfDemon = coreElement.getMandatoryFirstOf("nameHalfDemon").getTextContent();
					this.namePluralHalfDemon = coreElement.getMandatoryFirstOf("namePluralHalfDemon").getTextContent();
					this.singularMaleNameHalfDemon = coreElement.getMandatoryFirstOf("singularMaleNameHalfDemon").getTextContent();
					this.singularFemaleNameHalfDemon = coreElement.getMandatoryFirstOf("singularFemaleNameHalfDemon").getTextContent();
					this.pluralMaleNameHalfDemon = coreElement.getMandatoryFirstOf("pluralMaleNameHalfDemon").getTextContent();
					this.pluralFemaleNameHalfDemon = coreElement.getMandatoryFirstOf("pluralFemaleNameHalfDemon").getTextContent();
				} else {
					this.nameHalfDemon = null;
					this.namePluralHalfDemon = null;
					this.singularMaleNameHalfDemon = null;
					this.singularFemaleNameHalfDemon = null;
					this.pluralMaleNameHalfDemon = null;
					this.pluralFemaleNameHalfDemon = null;
				}
				
				this.feralAttributes = null;
				if(coreElement.getOptionalFirstOf("feralAttributes").isPresent()
						&& coreElement.getMandatoryFirstOf("feralAttributes").getOptionalFirstOf("feralName").isPresent()) {
					try {
						Element feralElement = coreElement.getMandatoryFirstOf("feralAttributes");
						this.feralAttributes = new FeralAttributes(
								feralElement.getMandatoryFirstOf("feralName").getTextContent(),
								feralElement.getMandatoryFirstOf("feralNamePlural").getTextContent(),
								feralElement.getMandatoryFirstOf("feralSingularMaleName").getTextContent(),
								feralElement.getMandatoryFirstOf("feralSingularFemaleName").getTextContent(),
								feralElement.getMandatoryFirstOf("feralPluralMaleName").getTextContent(),
								feralElement.getMandatoryFirstOf("feralPluralFemaleName").getTextContent(),

								LegConfiguration.valueOf(feralElement.getMandatoryFirstOf("legConfiguration").getTextContent()),
								Boolean.valueOf(coreElement.getMandatoryFirstOf("sizeHeight").getTextContent()),
								Integer.valueOf(coreElement.getMandatoryFirstOf("size").getTextContent()),

								Integer.valueOf(coreElement.getMandatoryFirstOf("breastRowCount").getTextContent()),
								Integer.valueOf(coreElement.getMandatoryFirstOf("nipplesPerBreastCount").getTextContent()),
								Integer.valueOf(coreElement.getMandatoryFirstOf("crotchBreastRowCount").getTextContent()),
								Integer.valueOf(coreElement.getMandatoryFirstOf("nipplesPerCrotchBreastCount").getTextContent()),

								Boolean.valueOf(coreElement.getMandatoryFirstOf("armsOrWingsPresent").getTextContent()),
								Boolean.valueOf(coreElement.getMandatoryFirstOf("fingerActionsAvailable").getTextContent()),
								Boolean.valueOf(coreElement.getMandatoryFirstOf("hairPresent").getTextContent()));
					} catch(Exception ex) {
						System.err.println("Error in AbstractSubspecies loading: feralAttributes failed to initialise!<br/>"+ex.getMessage());
					}
				}

				this.statusEffectDescription = coreElement.getMandatoryFirstOf("statusEffectDescription").getTextContent();
				
				this.statusEffectAttributeModifiers = new HashMap<>();
				for(Element e : coreElement.getMandatoryFirstOf("statusEffectAttributeModifiers").getAllOf("attribute")) {
					statusEffectAttributeModifiers.put(Attribute.getAttributeFromId(e.getTextContent()), Float.valueOf(e.getAttribute("value")));
				}
				this.statusEffectAttributeModifiers.entrySet().removeIf((entry) -> entry.getValue()==0);
				
				this.perkWeightingFeminine = new HashMap<>();
				this.perkWeightingMasculine = new HashMap<>();
				for(Element e : coreElement.getMandatoryFirstOf("perkWeightings").getAllOf("category")) {
					PerkCategory cat = PerkCategory.valueOf(e.getTextContent());
					perkWeightingFeminine.put(cat, Integer.valueOf(e.getAttribute("feminineWeighting")));
					perkWeightingMasculine.put(cat, Integer.valueOf(e.getAttribute("masculineWeighting")));
				}
				
				this.extraEffects = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("extraEffects").getAllOf("effect")) {
					extraEffects.add(e.getTextContent());
				}

				this.regionLocations = new HashMap<>();
				for(Element e : coreElement.getMandatoryFirstOf("regionLocations").getAllOf("region")) {
					regionLocations.put(WorldRegion.valueOf(e.getTextContent()), SubspeciesSpawnRarity.valueOf(e.getAttribute("rarity")));
				}
				
				this.worldLocations = new HashMap<>();
				for(Element e : coreElement.getMandatoryFirstOf("worldLocations").getAllOf("world")) {
					worldLocations.put(WorldType.getWorldTypeFromId(e.getTextContent()), SubspeciesSpawnRarity.valueOf(e.getAttribute("rarity")));
				}
				
				this.flags = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("flags").getAllOf("flag")) {
					flags.add(SubspeciesFlag.valueOf(e.getTextContent()));
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractSubspecies was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	
	@Override
	public String toString() {
		new AccessException("WARNING: AbstractSubspecies is calling toString()!").printStackTrace(System.err);
		return Subspecies.getIdFromSubspecies(this);
	}
	
	/**
	 * Changes that should be applied to characters of this species upon generation. Called <b>after</b> this Subspecies' Race.applyRaceChanges().
	 */
	public void applySpeciesChanges(Body body) {
		if(this.isFromExternalFile() && Main.game.isStarted()) {
			UtilText.setBodyForParsing("targetedBody", body);
			UtilText.parse(applySubspeciesChanges);
		}
	}

	/**
	 * Changes that should be applied to any offspring of this species.
	 */
	public void applyOffspringSpeciesChanges(Body body) {
		applySpeciesChanges(body);
	}

	public static AbstractSubspecies getMainSubspeciesOfRace(AbstractRace race) {
		AbstractSubspecies backup = Subspecies.HUMAN;
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			if(sub.getRace()==race) {
				if(sub.isMainSubspecies()) {
					return sub;
				}
				backup = sub;
			}
		}
		System.err.println("Warning: getMainSubspeciesOfRace("+Race.getIdFromRace(race)+") did not find a main subspecies!");
		return backup;
	}
	
	/**
	 * @return The race of this body if it were made from flesh. (i.e. The body's race ignoring slime/elemental modifiers.)
	 */
	public static AbstractSubspecies getFleshSubspecies(GameCharacter character) {
		return getSubspeciesFromBody(character.getBody(), character.getBody().getRaceFromPartWeighting());
	}
	
	/**
	 * @param body The body being checked.
	 * @param race The race of the body being checked.
	 * @return 0 if this Subspecies' requirements are not met by the supplied body/race. Typically return 100 if they are met, or something higher if the Subspecies should have a higher priority.
	 */
	public int getSubspeciesWeighting(Body body, AbstractRace race) {
		if(this.isFromExternalFile() && Main.game.isStarted()) {
			UtilText.setBodyForParsing("targetedBody", body);
			UtilText.setRaceForParsing("targetedRace", race);
//			if(race==Race.HUMAN) {
//				new IllegalArgumentException().printStackTrace();
//			}
			return Integer.valueOf(UtilText.parse(subspeciesWeighting).trim());
		}
		return 0;
	}
	
	public static AbstractSubspecies getSubspeciesFromBody(Body body, AbstractRace race) {
		AbstractSubspecies subspecies = null;
		
		int highestWeighting = 0;
		for(AbstractSubspecies sub : Subspecies.getAllSubspecies()) {
			if(sub.getSubspeciesWeighting(body, race)>highestWeighting
					&& (!body.isFeral() || sub.isFeralConfigurationAvailable())) {
				subspecies = sub;
				highestWeighting = sub.getSubspeciesWeighting(body, race);
			}
		}
		if(subspecies==null) {
			if(Main.game.isStarted()) { // Races get recalculated after the game starts in Game.handlePostGameInit(), so only show errors if the detection is still failing after that
				System.err.println("Error: getSubspeciesFromBody() did not find a suitable Subspecies!");
			}
			return Subspecies.HUMAN;
		}
		return subspecies;
	}
	
	protected static void applyFoxColoring(Body body) {
		Colour c1 = body.getCoverings().get(BodyCoveringType.FOX_FUR).getPrimaryColour();
		Colour c2 = PresetColour.COVERING_WHITE;
		CoveringPattern pat = CoveringPattern.MARKED;
		
		if(c1==PresetColour.COVERING_BLACK) {
			c2 = c1;
			pat = CoveringPattern.NONE;
		}
		
		// Old:
//		if(c1==PresetColour.COVERING_BROWN) {
//			if(rand<0.5f) {
//				c2 = PresetColour.COVERING_BROWN;
//				pat = CoveringPattern.NONE;
//			} else {
//				c2 = PresetColour.COVERING_TAN;
//			}
//			
//		} else if(c1==PresetColour.COVERING_BROWN_DARK) {
//			if(rand<0.5f) {
//				c2 = PresetColour.COVERING_BROWN_DARK;
//				pat = CoveringPattern.NONE;
//			} else {
//				c2 = PresetColour.COVERING_BROWN;
//			}
//			
//		} else if(c1==PresetColour.COVERING_SILVER || c1==PresetColour.COVERING_GREY) {
//			if(rand<0.5f) {
//				c2 = c1;
//				pat = CoveringPattern.NONE;
//			}
//			
//		} else if(c1==PresetColour.COVERING_BLACK) {
//			if(rand<0.5f) {
//				c2 = PresetColour.COVERING_BLACK;
//				pat = CoveringPattern.NONE;
//			} else {
//				c2 = PresetColour.COVERING_GREY;
//			}
//			
//		} else if(c1==PresetColour.COVERING_TAN || c1==PresetColour.COVERING_WHITE) {
//			c2 = c1;
//			pat = CoveringPattern.NONE;
//			
//		} else {
//			// Set primary colour to GINGER if we have a colour that otherwise wouldn't be accounted for.
//			if(c1 != PresetColour.COVERING_BLONDE) {c1 = PresetColour.COVERING_GINGER;}
//			if(rand<0.025f) {
//				c2 = PresetColour.COVERING_BLACK;
//			} else if(rand<0.05f) {
//				c2 = PresetColour.COVERING_BROWN;
//			} else if(rand<0.5f) {
//				c2 = PresetColour.COVERING_GREY;
//			}
//		}
				
		body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, pat, c1, false, c2, false));
	}
	

	public static Body getPreGeneratedBody(GameCharacter linkedCharacter, Gender startingGender, GameCharacter mother, GameCharacter father) {
		return getPreGeneratedBody(linkedCharacter, startingGender, mother.getTrueSubspecies(), mother.getHalfDemonSubspecies(), father.getTrueSubspecies(), father.getHalfDemonSubspecies());
	}
	
	/**
	 * Only used for subspecies that have special offspring generation - i.e. demons.<br/><br/>
	 * 
	 * <b>Demon breeding</b><br/>
	 * Lilin<br/>
	 * + lilin = lilin<br/>
	 * + demon = demon<br/>
	 * + half-demon = half-demon<br/>
	 * + human half-demon = human half-demon<br/>
	 * + non-demon = half-demon<br/>
	 * + imps = alpha-imps<br/>
	 * Demon<br/>
	 * + lilin = demon<br/>
	 * + demon = demon<br/>
	 * + half-demon = half-demon<br/>
	 * + human half-demon = human half-demon<br/>
	 * + non-demon = half-demon<br/>
	 * + imps = alpha-imps<br/>
	 * Half-demon<br/>
	 * + lilin = half-demon<br/>
	 * + demon = half-demon<br/>
	 * + half-demon = half-demon<br/>
	 * + human half-demon = human half-demon<br/>
	 * + non-demon = half-demon<br/>
	 * + imps = alpha-imps<br/>
	 * Human half-demon<br/>
	 * + lilin = human half-demon<br/>
	 * + demon = human half-demon<br/>
	 * + half-demon = human half-demon<br/>
	 * + human half-demon = imps<br/>
	 * + non-demon = imps<br/>
	 * + imps = imps<br/>
	 * Imps and alpha-imps<br/>
	 * + anything = imps<br/>
	 * @return The pre-generated body to use as an offspring's core body.
	 */
	public static Body getPreGeneratedBody(GameCharacter linkedCharacter,
			Gender startingGender,
			AbstractSubspecies motherSubspecies,
			AbstractSubspecies motherHalfDemonSubspecies,
			AbstractSubspecies fatherSubspecies,
			AbstractSubspecies fatherHalfDemonSubspecies) {
		
		if(startingGender==null) {
			startingGender = Math.random()>0.5f?Gender.F_V_B_FEMALE:Gender.M_P_MALE;
		}
		
		// Any type of demonic mother will result in special cases for offspring:
		if(motherSubspecies==Subspecies.ELDER_LILIN || motherSubspecies==Subspecies.LILIN || motherSubspecies==Subspecies.DEMON) {
			if(fatherSubspecies==Subspecies.ELDER_LILIN || fatherSubspecies==Subspecies.LILIN) {
				if(motherSubspecies==Subspecies.ELDER_LILIN || motherSubspecies==Subspecies.LILIN) {
					return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.LILIN, RaceStage.GREATER);
				} else {
					return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, RaceStage.GREATER);
				}
				
			} else if(fatherSubspecies==Subspecies.DEMON) {
				return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, RaceStage.GREATER);
				
			} else if(fatherSubspecies==Subspecies.HALF_DEMON) {
				return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, fatherHalfDemonSubspecies, true);
				
			} else if(fatherSubspecies==Subspecies.IMP || fatherSubspecies==Subspecies.IMP_ALPHA) {
				return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.IMP_ALPHA, RaceStage.GREATER);
				
			} else {
				return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, fatherSubspecies, true);
			}
			
		} else if(motherSubspecies==Subspecies.HALF_DEMON) {
			if(motherHalfDemonSubspecies==Subspecies.HUMAN) {
				if(fatherSubspecies==Subspecies.ELDER_LILIN || fatherSubspecies==Subspecies.LILIN || fatherSubspecies==Subspecies.DEMON || fatherSubspecies==Subspecies.HALF_DEMON) {
					if(fatherHalfDemonSubspecies==Subspecies.HUMAN) {
						return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.IMP, RaceStage.GREATER);	
					}
					return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, motherHalfDemonSubspecies, true);
				} else {
					return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.IMP, RaceStage.GREATER);
				}
				
			} else {
				if(fatherSubspecies==Subspecies.ELDER_LILIN || fatherSubspecies==Subspecies.LILIN || fatherSubspecies==Subspecies.DEMON) {
					return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, motherHalfDemonSubspecies, true);
					
				} else if(fatherSubspecies==Subspecies.HALF_DEMON) { // If both are non-human half-demons, it's random as to whose species is birthed
					if(Math.random()<0.5f || fatherHalfDemonSubspecies==Subspecies.HUMAN) {
						return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, motherHalfDemonSubspecies, true);
					} else {
						return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, fatherHalfDemonSubspecies, true);
					}
					
				} else if(fatherSubspecies==Subspecies.IMP || fatherSubspecies==Subspecies.IMP_ALPHA) {
					return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.IMP_ALPHA, RaceStage.GREATER);
					
				} else {
					return Main.game.getCharacterUtils().generateHalfDemonBody(linkedCharacter, startingGender, motherHalfDemonSubspecies, true);
				}
			}
			
		} else if(motherSubspecies==Subspecies.IMP_ALPHA || motherSubspecies==Subspecies.IMP) {
			if(fatherSubspecies==Subspecies.IMP) {
				return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.IMP, RaceStage.GREATER);
			} else {
				return Main.game.getCharacterUtils().generateBody(linkedCharacter, startingGender, RacialBody.DEMON, Subspecies.IMP_ALPHA, RaceStage.GREATER);
			}
			
		} else {
			if(fatherSubspecies==Subspecies.ELDER_LILIN
					|| fatherSubspecies==Subspecies.LILIN
					|| fatherSubspecies==Subspecies.DEMON
					|| fatherSubspecies==Subspecies.HALF_DEMON
					|| fatherSubspecies==Subspecies.IMP
					|| fatherSubspecies==Subspecies.IMP_ALPHA) {
					// Just return this method, but with mother & father swapped, as all demonic offspring types are unaffected by who is the mother or father:
				return getPreGeneratedBody(linkedCharacter, startingGender, fatherSubspecies, fatherHalfDemonSubspecies, motherSubspecies, motherHalfDemonSubspecies);
				
			} else {
				return null;
			}
		}
	}
	
	public boolean isShortStature() {
		return shortStature;
	}
	
	public boolean isNonBiped() {
		return !bipedalSubspecies;
	}
	
	/**
	 * @return true if this Subspecies is able to self-transform. Race.isAbleToSelfTransform() is factored into this.
	 */
	public boolean isAbleToSelfTransform() {
		return this.getRace().isAbleToSelfTransform();
	}
	
	// Items:
	
	public String getAttributeItemId() {
		return attributeItemId;
	}
	
	/**
	 * @return The ItemType which this Subspecies has as its attribute-related item. <b>Returns null if no item is defined.</b>
	 */
	public AbstractItemType getAttributeItem(GameCharacter owner) {
		if(getAttributeItemId()==null || getAttributeItemId().isEmpty()) {
//			System.err.println("Warning: AbstractSubspecies is calling getAttributeItem() where attributeItemId does not exist!");
			return null;	
		}
		return ItemType.getItemTypeFromId(getAttributeItemId());
	}
	
	public String getTransformativeItemId() {
		return transformativeItemId;
	}

	/**
	 * @return The ItemType which this Subspecies has as its transformation-related item. <b>Returns null if no item is defined.</b>
	 */
	public AbstractItemType getTransformativeItem(GameCharacter owner) {
		if(getTransformativeItemId()==null || getTransformativeItemId().isEmpty()) {
//			System.err.println("Warning: AbstractSubspecies is calling getTransformativeItem() where transformativeItemId does not exist!");
			return null;	
		}
		return ItemType.getItemTypeFromId(getTransformativeItemId());
	}

	public AbstractItemType getBook() {
		return ItemType.getLoreBook(this);
	}
	
	public boolean isMainSubspecies() {
		return mainSubspecies;
	}
	
	/**
	 * @return An integer representing how important this Subspecies is to be defined as a character's Subspecies override (meaning that this Subspecies will always be their true Subspecies).
	 * <b/>Default value is <b>0</b>, which, along with any negative integer value, means that this Subspecies does not set an Override.
	 *  <br/>A Subspecies which has a higher value than a character's current Subspecies Override will replace the current Override with this one.
	 */
	public int getSubspeciesOverridePriority() {
		return subspeciesOverridePriority;
	}
	
	private String getTaurEnding() {
		return getFeralName(null).charAt(getFeralName(null).length()-1)=='t'?"-taur":"taur";
	}
	
	protected String applyNonBipedNameChange(GameCharacter character, String baseName, boolean applyFeminineForm, boolean plural) {
		switch(character.getLegConfiguration()) {
			case ARACHNID:
				return baseName+"-arachne"+(plural?"s":"");
			case AVIAN:
				return baseName+"-moa"+(plural?"s":"");
			case BIPEDAL:
				break;
			case CEPHALOPOD:
				return baseName+"-kraken"+(plural?"s":"");
			case TAIL:
				return "mer"+baseName+(plural?"s":"");
			case TAIL_LONG:
				return baseName+"-lamia"+(plural?"s":"");
			case QUADRUPEDAL:
				return baseName+getTaurEnding()+(applyFeminineForm?"ess"+(plural?"es":""):(plural?"s":""));
		}
		return baseName;
	}
	
	/**
	 * @param   The character whose subspecies's name is to be returned. Can pass in null.
	 * @return  The singular name of this character's subspecies.
	 */
	public String getName(GameCharacter character) {
		if(character!=null && this.isFeralConfigurationAvailable()) {
			if(character.isFeral()) {
				return getFeralAttributes().getFeralName();
			}
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL && !isNonBiped()) {
				return applyNonBipedNameChange(character, getNonBipedRaceName(character), false, false);
			}
		}
		return name;
	}

	/**
	 * @param   The character whose subspecies's pluralised name is to be returned. Can pass in null.
	 * @return  The plural name of this character's subspecies.
	 */
	public String getNamePlural(GameCharacter character) {
		if(character!=null && this.isFeralConfigurationAvailable()) {
			if(character.isFeral()) {
				return getFeralAttributes().getFeralNamePlural();
			}
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL && !isNonBiped()) {
				return applyNonBipedNameChange(character, getNonBipedRaceName(character), false, true);
			}
		}
		return namePlural;
	}
	
	/**
	 * @param   The character whose male subspecies name is to be returned. Can pass in null.
	 * @return  The singular male name of this character's subspecies.
	 */
	public String getSingularMaleName(GameCharacter character) {
		if(character!=null && this.isFeralConfigurationAvailable()) {
			if(character.isFeral()) {
				return getFeralAttributes().getFeralSingularMaleName();
			}
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL && !isNonBiped()) {
				return applyNonBipedNameChange(character, getNonBipedRaceName(character), false, false);
			}
		}

		if(Main.game.isSillyModeEnabled() && character!=null) {
			if(character.getSubspecies() == Subspecies.WOLF_MORPH && Main.game.isSillyModeEnabled()){
				return "awoo-boi";
			} else if(character.getSubspecies() == Subspecies.CAT_MORPH && Main.game.isSillyModeEnabled()){
				return "catte-boi";
			} else if(character.getSubspecies() == Subspecies.HARPY && Main.game.isSillyModeEnabled()){
				return "birb";
			}
		}
		return singularMaleName;
	}

	/**
	 * @param   The character whose female subspecies name is to be returned. Can pass in null.
	 * @return  The singular female name of this character's subspecies.
	 */
	public String getSingularFemaleName(GameCharacter character) {
		if(character!=null && this.isFeralConfigurationAvailable()) {
			if(character.isFeral()) {
				return getFeralAttributes().getFeralSingularFemaleName();
			}
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL && !isNonBiped()) {
				return applyNonBipedNameChange(character, getNonBipedRaceName(character), true, false);
			}
		}
		
		if(Main.game.isSillyModeEnabled() && character!=null) {
			if(character.getSubspecies() == Subspecies.WOLF_MORPH){
				return "awoo-girl";
			} else if(character.getSubspecies() == Subspecies.CAT_MORPH && Main.game.isSillyModeEnabled()){
				return "catte-girl";
			} else if(character.getSubspecies() == Subspecies.HARPY && Main.game.isSillyModeEnabled()){
				return "birb";
			}
		}
		
		return singularFemaleName;
	}

	/**
	 * @param   The character whose male subspecies's pluralised name is to be returned. Can pass in null.
	 * @return  The plural male name of this character's subspecies.
	 */
	public String getPluralMaleName(GameCharacter character) {
		if(character!=null && this.isFeralConfigurationAvailable()) {
			if(character.isFeral()) {
				return getFeralAttributes().getFeralPluralMaleName();
			}
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL && !isNonBiped()) {
				return applyNonBipedNameChange(character, getNonBipedRaceName(character), false, true);
			}
		}
		return pluralMaleName;
	}

	/**
	 * @param   The character whose female subspecies's pluralised name is to be returned. Can pass in null.
	 * @return  The plural female name of this character's subspecies.
	 */
	public String getPluralFemaleName(GameCharacter character) {
		if(character!=null && this.isFeralConfigurationAvailable()) {
			if(character.isFeral()) {
				return getFeralAttributes().getFeralPluralFemaleName();
			}
			if(character.getLegConfiguration()!=LegConfiguration.BIPEDAL && !isNonBiped()) {
				return applyNonBipedNameChange(character, getNonBipedRaceName(character), false, true);
			}
		}
		return pluralFemaleName;
	}

	public String getNonBipedRaceName(GameCharacter character) {
		return getFeralName(character);
	}
	
	public String getFeralName(GameCharacter character) {
		if(isFeralConfigurationAvailable()) {
			return getFeralAttributes().getFeralName();
		}
		return name;
	}

	public FeralAttributes getFeralAttributes() {
		return feralAttributes;
	}
	
	public boolean isFeralConfigurationAvailable() {
		return getFeralAttributes()!=null;
	}

	public String getStatusEffectDescription(GameCharacter character) {
		return UtilText.parse(character, statusEffectDescription);
	}

	public Map<AbstractAttribute, Float> getStatusEffectAttributeModifiers(GameCharacter character) {
		return statusEffectAttributeModifiers;
	}

	public Map<PerkCategory, Integer> getPerkWeighting(GameCharacter character) {
		if(character==null || !character.isFeminine()) {
			return perkWeightingMasculine;
		}
		return perkWeightingFeminine;
	}
	
	public List<String> getExtraEffects(GameCharacter character) {
		if(character!=null) {
			List<String> effectsModified = new ArrayList<>(extraEffects);
			
			int landSpeed = character.getLandSpeedModifier();
			int waterSpeed =  character.getWaterSpeedModifier();
			if(landSpeed!=0) {
				effectsModified.add((landSpeed<0?"[style.boldExcellent("+landSpeed+"%)]":"[style.boldTerrible(+"+landSpeed+"%)]")+" travel time on land");
			}
			if(waterSpeed!=0) {
				effectsModified.add((waterSpeed<0?"[style.boldExcellent("+waterSpeed+"%)]":"[style.boldTerrible(+"+waterSpeed+"%)]")+" travel time in water");
			}
			
			if(character.getLegConfiguration()==LegConfiguration.TAIL) {
				effectsModified.add("[style.boldTan(Grows legs on land)]");
				effectsModified.add("[style.boldBlueLight(Loses legs in water)]");
			}
			
			return effectsModified;
		}
		return extraEffects;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookNamePlural() {
		return bookNamePlural;
	}

	public String getBasicDescription(GameCharacter character) {
		if(this.isFromExternalFile()) {
			return UtilText.parseFromXMLFile(new ArrayList<>(), bookIdFolderPath, "bookEntries", getBasicDescriptionId(), new ArrayList<>());
		}
		return UtilText.parseFromXMLFile("characters/raceInfo", getBasicDescriptionId());
	}

	public String getAdvancedDescription(GameCharacter character) {
		if(this.isFromExternalFile()) {
			return UtilText.parseFromXMLFile(new ArrayList<>(), bookIdFolderPath, "bookEntries", getAdvancedDescriptionId(), new ArrayList<>());
		}
		return UtilText.parseFromXMLFile("characters/raceInfo", getAdvancedDescriptionId());
	}
	
	public String getBasicDescriptionId() {
		return basicDescriptionId;
	}

	public String getAdvancedDescriptionId() {
		return advancedDescriptionId;
	}

	public AbstractRace getRace() {
		return race;
	}

	public AbstractAttribute getDamageMultiplier() {
		return getRace().getDefaultDamageMultiplier();
	}
	
	public Colour getColour(GameCharacter character) {
		return colour;
	}
	
	public SubspeciesPreference getSubspeciesPreferenceDefault() {
		return subspeciesPreferenceDefault;
	}
	
	public String getDescription(GameCharacter character) {
		return description;
	}
	
	/**
	 * @param character The character being checked
	 * @return true if the supplied character has a LegConfiguration of type TAIL, or if the aquatic variable is set to true.
	 */
	public boolean isAquatic(GameCharacter character) {
		if(character==null) {
			return aquatic;
		}
		return aquatic || character.getLegConfiguration()==LegConfiguration.TAIL;
	}

	protected String getBipedBackground(String svg, GameCharacter character, Colour colour) {
		String returnString = svg;
		
		if(character!=null) {
			//character.isTorsoFeral() 
			if(character.isFeral() || (character.isElemental() && !((Elemental)character).getSummoner().isElementalActive())) {
				try {
					String feralBackground = "";
					InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/race/raceBackgroundFeral.svg");
					feralBackground = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+Util.inputStreamToString(is)+"</div>";
					is.close();
					feralBackground = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this)+"FERAL",
							colour,
							colour,
							colour,
							feralBackground);
					
					returnString = returnString + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>" + feralBackground +"</div>";
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				String backgroundPath = character.getLegConfiguration().getSubspeciesStatusEffectBackgroundPath();
				if(!backgroundPath.isEmpty()) {
					try {
						String SVGStringLegConfigurationBackground = "";
						InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/"+backgroundPath+".svg");
						SVGStringLegConfigurationBackground = "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+Util.inputStreamToString(is)+"</div>";
						is.close();
						SVGStringLegConfigurationBackground = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this)+"NBPID",
								colour,
								colour,
								colour,
								SVGStringLegConfigurationBackground);
						returnString = SVGStringLegConfigurationBackground + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>" + svg +"</div>";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return returnString;
	}

	protected void initBookSVGString() {
		try {
			if(this.isFromExternalFile()) {
				List<String> lines = Files.readAllLines(Paths.get(bookPathName + ".svg"));
				StringBuilder sb = new StringBuilder();
				for(String line : lines) {
					sb.append(line);
				}
				bookSVGString = sb.toString();
				
			} else {
				InputStream is = this.getClass().getResourceAsStream(bookPathName + ".svg");
				if(is==null) {
					System.err.println("Error! Subspecies book icon file does not exist (Trying to read from '"+bookPathName+"')! (Code 1)");
				}
				bookSVGString = Util.inputStreamToString(is);
				is.close();
			}
			
			bookSVGString = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
					colour,
					secondaryColour,
					tertiaryColour,
					"<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+bookSVGString+"</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void initSVGStrings() {
		if(pathName!=null) {
			String fullDivStyle = "width:100%;height:100%;margin:0;padding:0;position:absolute;left:0;bottom:0;";
			
			try {
				if(this.isFromExternalFile()) {
					List<String> lines = Files.readAllLines(Paths.get(pathName+".svg"));
					StringBuilder sb = new StringBuilder();
					for(String line : lines) {
						sb.append(line);
					}
					SVGStringUncoloured = sb.toString();
					float iconResizeBorder = (100-iconSize)/2f;
					SVGStringUncoloured = "<div style='width:"+iconSize+"%;height:"+iconSize+"%;position:absolute;left:"+iconResizeBorder+"%;bottom:"+iconResizeBorder+"%;'>"+SVGStringUncoloured+"</div>";
					
				} else {
					InputStream is = this.getClass().getResourceAsStream(pathName + ".svg");
					if(is==null) {
						System.err.println("Error! Subspecies icon file does not exist (Trying to read from '"+pathName+"')! (Code 1)");
					}
					SVGStringUncoloured = Util.inputStreamToString(is);
					is.close();
				}
				
				
				String SVGStringBackground = "";

				if(this.externalFileBackground) {
					List<String> lines = Files.readAllLines(Paths.get(backgroundPathName+".svg"));
					StringBuilder sb = new StringBuilder();
					for(String line : lines) {
						sb.append(line);
					}
					SVGStringBackground = "<div style='"+fullDivStyle+"'>"+sb.toString()+"</div>";
					
				} else {
					if(!backgroundPathName.isEmpty()) {
						InputStream is = this.getClass().getResourceAsStream(backgroundPathName + ".svg");
						if(is==null) {
							System.err.println("Error! Subspecies background icon file does not exist (Trying to read from '"+backgroundPathName+"')! (Code 1)");
						}
						SVGStringBackground = "<div style='"+fullDivStyle+"'>"+Util.inputStreamToString(is)+"</div>";
						
						is.close();
					}
				}
				
				initBookSVGString();
				
				SVGStringNoBackground = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
						colour,
						secondaryColour,
						tertiaryColour,
						"<div style='"+fullDivStyle+"'>"+SVGStringUncoloured+"</div>");
				
				SVGStringUncoloured = SVGStringBackground + "<div style='"+fullDivStyle+"'>"+SVGStringUncoloured+"</div>";
				
				slimeSVGString = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
						PresetColour.RACE_SLIME,
						PresetColour.RACE_SLIME,
						PresetColour.RACE_SLIME,
						"<div style='"+fullDivStyle+"'>" + SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundSlime()+"</div>"
						+ "<div style='"+fullDivStyle+"'>"+SVGStringUncoloured+"</div>");

				halfDemonSVGString = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
						PresetColour.RACE_HALF_DEMON,
						PresetColour.RACE_HALF_DEMON,
						PresetColour.RACE_HALF_DEMON,
						"<div style='"+fullDivStyle+"'>" + SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundDemon()+"</div>"
						+ "<div style='"+fullDivStyle+"'>"+SVGStringUncoloured+"</div>");

				demonSVGString = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
						PresetColour.RACE_DEMON,
						PresetColour.RACE_DEMON,
						PresetColour.RACE_DEMON,
						"<div style='"+fullDivStyle+"'>" + SVGImages.SVG_IMAGE_PROVIDER.getRaceBackgroundDemon()+"</div>"
						+ "<div style='"+fullDivStyle+"'>"+SVGStringUncoloured+"</div>");
				
				SVGStringDesaturated = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
						PresetColour.BASE_GREY,
						PresetColour.BASE_GREY,
						PresetColour.BASE_GREY,
						SVGStringUncoloured);
				
				SVGString = SvgUtil.colourReplacement(Subspecies.getIdFromSubspecies(this),
						colour,
						secondaryColour,
						tertiaryColour,
						SVGStringUncoloured);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			SVGString = "";
		}
	}
	
	public String getBookSVGString() {
		if(bookSVGString==null) {
			initBookSVGString();
		}
		return bookSVGString;
	}

	public String getSVGString(GameCharacter character) {
		if(SVGString==null) {
			initSVGStrings();
		}
		return getBipedBackground(SVGString, character, this.getColour(character));
	}
	
	public String getSVGStringNoBackground() {
		if(SVGString==null) {
			initSVGStrings();
		}
		return SVGStringNoBackground;
	}

	public String getSVGStringDesaturated(GameCharacter character) {
		if(SVGString==null) {
			initSVGStrings();
		}
		return getBipedBackground(SVGStringDesaturated, character, PresetColour.BASE_GREY);
	}

	public String getSlimeSVGString(GameCharacter character) {
		if(SVGString==null) {
			initSVGStrings();
		}
		return getBipedBackground(slimeSVGString, character, PresetColour.RACE_SLIME);
	}

	public String getHalfDemonSVGString(GameCharacter character) {
		if(SVGString==null) {
			initSVGStrings();
		}
		if(character!=null && character.getSubspeciesOverride()!=null && character.getSubspeciesOverride().equals(Subspecies.DEMON)) {
			return getBipedBackground(demonSVGString, character, PresetColour.RACE_DEMON);
		} else {
			return getBipedBackground(halfDemonSVGString, character, PresetColour.RACE_HALF_DEMON);
		}
	}

	public Map<WorldRegion, SubspeciesSpawnRarity> getRegionLocations() {
		return regionLocations;
	}

	public Map<AbstractWorldType, SubspeciesSpawnRarity> getWorldLocations() {
		return worldLocations;
	}
	
	/**
	 * @param worldType
	 * @return true if this subspecies is able to spawn in the worldType, either due to having a spawn chance in that worldType directly, or in the WorldRegion in which that worldType is located.
	 */
	public boolean isAbleToNaturallySpawnInLocation(AbstractWorldType worldType) {
		return getRegionLocations().containsKey(worldType.getWorldRegion()) || getWorldLocations().containsKey(worldType);
	}
	
	public List<SubspeciesFlag> getFlags() {
		return flags;
	}
	
	public boolean hasFlag(SubspeciesFlag flag) {
		return getFlags().contains(flag);
	}
	
	/**
	 * @return A String array of length 6, consisting of:<br/>
	 * <b>[0]:</b> Singular generic demon name<br/>
	 * <b>[1]:</b> Plural generic demon name<br/>
	 * <b>[2]:</b> Singular male demon name<br/>
	 * <b>[3]:</b> Singular female demon name<br/>
	 * <b>[4]:</b> Plural male demon name<br/>
	 * <b>[5]:</b> Plural female demon name<br/>
	 */
	public String[] getHalfDemonName(GameCharacter character) {
		String[] names = null;
		
		if(this.getRace()==Race.DEMON
				|| this.getRace()==Race.ELEMENTAL
				|| this.getRace()==Race.HUMAN) {
			
			String[] demonNames = demonLegConfigurationNames.get(character==null?LegConfiguration.BIPEDAL:character.getLegConfiguration());
			
			names = new String[] {
				"half-"+demonNames[0],
				"half-"+demonNames[1],
				"half-"+demonNames[2],
				"half-"+demonNames[3],
				"half-"+demonNames[4],
				"half-"+demonNames[5]};	
		}
		
		if(names==null) {
			if(this.isFromExternalFile() && this.nameHalfDemon!=null && !this.nameHalfDemon.isEmpty()) {
				names = new String[] {
						nameHalfDemon,
						namePluralHalfDemon,
						singularMaleNameHalfDemon,
						singularFemaleNameHalfDemon,
						pluralMaleNameHalfDemon,
						pluralFemaleNameHalfDemon};
				
			} else if(character==null) {
				names = new String[] {
						"demonic-"+name,
						"demonic-"+namePlural,
						"demonic-"+singularMaleName,
						"demonic-"+singularFemaleName,
						"demonic-"+pluralMaleName,
						"demonic-"+pluralFemaleName};
				
			} else {
				names = new String[] {
						"demonic-"+this.getName(character),
						"demonic-"+this.getNamePlural(character),
						"demonic-"+this.getSingularMaleName(character),
						"demonic-"+this.getSingularFemaleName(character),
						"demonic-"+this.getPluralMaleName(character),
						"demonic-"+this.getPluralFemaleName(character)};
			}
		}
		
		return names;
	}

	public FurryPreference getDefaultFemininePreference() {
		if(this.isNonBiped()) {
			return FurryPreference.MINIMUM;
		}
		return this.getRace().getDefaultFemininePreference();
	}

	public FurryPreference getDefaultMasculinePreference() {
		if(this.isNonBiped()) {
			return FurryPreference.MINIMUM;
		}
		return this.getRace().getDefaultMasculinePreference();
	}

	/**
	 * @return true if this subspecies should be displayed in the furry preferences options screen.
	 */
	public boolean isDisplayedInFurryPreferences() {
		return !this.hasFlag(SubspeciesFlag.HIDDEN_FROM_PREFERENCES);
	}

	/**
	 * @return true if this subspecies can have its FurryPreference modified in the furry preferences options screen.
	 */
	public boolean isFurryPreferencesEnabled() {
		return !this.hasFlag(SubspeciesFlag.DISBALE_FURRY_PREFERENCE);
	}

	/**
	 * @return true if this subspecies can have its spawn frequency modified in the furry preferences options screen.
	 */
	public boolean isSpawnPreferencesEnabled() {
		return !this.hasFlag(SubspeciesFlag.DISBALE_SPAWN_PREFERENCE);
	}
	
	public int getBaseSlaveValue(GameCharacter character) {
		return baseSlaveValue;
	}
	
	public static Map<AbstractSubspecies, Integer> getGenericSexPartnerSubspeciesMap(Gender gender, AbstractSubspecies... subspeciesToExclude) {
		Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
		List<AbstractSubspecies> subspecies = new ArrayList<>(Subspecies.getAllSubspecies());
		subspecies.removeAll(Arrays.asList(subspeciesToExclude));
		
		for(AbstractSubspecies s : subspecies) {
			if(s==Subspecies.REINDEER_MORPH
					&& Main.game.getSeason()==Season.WINTER
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
				addToSubspeciesMap(10, gender, s, availableRaces);
				
			} else if(s.getRace()!=Race.DEMON
					&& s.getRace()!=Race.ANGEL
					&& s.getRace()!=Race.ELEMENTAL
					&& s!=Subspecies.FOX_ASCENDANT
					&& s!=Subspecies.FOX_ASCENDANT_FENNEC
					&& s!=Subspecies.FOX_ASCENDANT_ARCTIC
					&& s!=Subspecies.SLIME) {
				if(AbstractSubspecies.getMainSubspeciesOfRace(s.getRace())==s) {
					addToSubspeciesMap(10, gender, s, availableRaces);
				} else {
					addToSubspeciesMap(3, gender, s, availableRaces);
				}
			}
		}
		
		return availableRaces;
	}

	public static void addToSubspeciesMap(int weight, Gender gender, AbstractSubspecies subspecies, Map<AbstractSubspecies, Integer> map) {
		addToSubspeciesMap(weight, gender, subspecies, map, null);
	}
	
	public static void addToSubspeciesMap(int weight, Gender gender, AbstractSubspecies subspecies, Map<AbstractSubspecies, Integer> map, SubspeciesPreference userPreferenceOverride) {
		if(gender.isFeminine()) {
			if((Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN && Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue()>0)
					|| userPreferenceOverride!=null) {
				map.put(subspecies, weight*(userPreferenceOverride!=null?userPreferenceOverride:Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies)).getValue());
			}
			
		} else {
			if((Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN && Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue()>0)
					|| userPreferenceOverride!=null) {
				map.put(subspecies, weight*(userPreferenceOverride!=null?userPreferenceOverride:Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies)).getValue());
			}
		}
	}
}
