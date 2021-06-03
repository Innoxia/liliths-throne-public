package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class LunetteRanged extends NPC {

	private static List<String> defaultNamePrefixes = Util.newArrayListOfValues("malevolent", "malicious", "spiteful");
	private static String defaultName = "raider";
	
	public LunetteRanged() {
		this(defaultNamePrefixes, defaultName, false);
	}
	
	public LunetteRanged(boolean isImported) {
		this(defaultNamePrefixes, defaultName, isImported);
	}
	
	public LunetteRanged(List<String> namePrefixes, String name, boolean isImported) {
		super(isImported,
				null, null, "",
				Util.random.nextInt(100)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				30,
				null, null, null,
				new CharacterInventory(10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			setLevel(25 + Util.random.nextInt(11)); // 25-35
			
			// RACE & NAME:
			this.setStartingBody(true);
			
			this.setGenericName(Util.randomItemFrom(namePrefixes)+" "+name);
			setName(Name.getRandomTriplet(this.getRace()));
			this.setSurname("Lunettemartu");
			this.setPlayerKnowsName(false);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(1000 + Util.random.nextInt(getLevel()*25));
			
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			// Set starting attributes based on the character's race
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_RANGED_EXPERT);
		this.addSpecialPerk(Util.randomItemFrom(new AbstractPerk[] {
				Perk.SPECIAL_MARTIAL_BACKGROUND,
				Perk.SPECIAL_HEALTH_FANATIC
		}));
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 10),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		equipMove("strike");
		equipMove("offhand-strike");
		equipMove("twin-strike");
		equipMove("block");
		this.equipAllSpecialMoves();
		this.equipAllSpellMoves();
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		Gender gender = Util.randomItemFrom(new Gender[] {
				Gender.F_P_V_B_FUTANARI,
				Gender.F_P_V_B_FUTANARI,
				Gender.F_P_B_SHEMALE,
				Gender.F_V_B_FEMALE,
				Gender.F_V_B_FEMALE,
				Gender.F_V_B_FEMALE,
		});
		
		this.setBody(gender, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_HORSE_HOOFED);
		this.setLegConfiguration(LegType.DEMON_HORSE_HOOFED, LegConfiguration.QUADRUPEDAL, true);
		if(!gender.getGenderName().isHasVagina()) {
			this.setBreastCrotchType(BreastType.NONE);
		}

		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Randomisation:
		Main.game.getCharacterUtils().randomiseBody(this, true);
		
		this.setHeight(190+Util.random.nextInt(21));
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);

			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_LUNETTE_HERD);
			
			// Fetishes:
			this.clearFetishes();
			this.clearFetishDesires();

			boolean oral = Math.random()<0.25f;
			boolean anal = Math.random()<0.25f && Main.game.isAnalContentEnabled();
			
			this.setAssVirgin(!anal);
			this.setPenisVirgin(false);
			this.setVaginaVirgin(false);
			this.setFaceVirgin(!oral);

			if(Math.random()<0.8f) {
				this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			}
			
			if(anal) {
				this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			}
			if(oral) {
				this.addFetish(Fetish.FETISH_ORAL_GIVING);
			}
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
			this.addFetish(Fetish.FETISH_SADIST);
			
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.FOUR_LOVE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.FOUR_LOVE);
			if(gender.getGenderName().isHasPenis()) {
				this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.FOUR_LOVE);
				this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			}
			if(gender.getGenderName().isHasVagina()) {
				this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.FOUR_LOVE);
			}
			if(Main.game.isAnalContentEnabled()) {
				this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			}

			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, "This demonic centaur is a member of the faction 'The Hundred Paces', and delights in playing wicked games with [npc.her] enemies.");
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		Util.random.setSeed(this.getDayOfBirth()*this.getBirthMonth().getValue()); // Set seed based on birthday so that the clothing is always the same
		
		Colour clothingColour = Util.randomItemFromValues(
				PresetColour.CLOTHING_WHITE,
				PresetColour.CLOTHING_WHITE,
				PresetColour.CLOTHING_WHITE,
				PresetColour.CLOTHING_PINK,
				PresetColour.CLOTHING_PINK_LIGHT,
				PresetColour.CLOTHING_PURPLE,
				PresetColour.CLOTHING_PURPLE_LIGHT
		);

		Colour accessoryColour = Util.randomItemFromValues(
				PresetColour.CLOTHING_PLATINUM,
				PresetColour.CLOTHING_GOLD
		);
		
		if(!this.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(Util.randomItemFromValues(ClothingType.CHEST_TUBE_TOP, ClothingType.CHEST_SPORTS_BRA), clothingColour, false), true, this);
		}
		
		if(Math.random()<0.2f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WRISTBANDS, clothingColour, false), true, this);
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, accessoryColour, false), true, this);
		}
		
		if(Math.random()<0.25f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_horseshoe_necklace", accessoryColour, accessoryColour, accessoryColour, false), true, this);
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_gemstone_necklace", accessoryColour, clothingColour, accessoryColour, false), true, this);
		}
		
		if(this.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
			if(Math.random()<0.75f) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(
						Util.randomItemFromValues("innoxia_buttPlugs_butt_plug_jewel", "innoxia_buttPlugs_butt_plug_heart"), accessoryColour, clothingColour, accessoryColour, false), true, this);
			} else {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_anus_ribbed_dildo", PresetColour.CLOTHING_PINK_HOT, false), true, this);
			}
		}
		
		// Piercings:
		if(this.isPiercedEar()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(Util.randomItemFromValues("innoxia_piercing_ear_ball_studs", "innoxia_piercing_ear_ring"), accessoryColour, false), true, this);
		}
		if(this.isPiercedNose() && Math.random()<0.9f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", accessoryColour, false), true, this);
		}
		if(this.isPiercedTongue() && Math.random()<0.9f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", accessoryColour, false), true, this);
		}
		if(this.isPiercedLip() && Math.random()<0.9f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", accessoryColour, false), true, this);
		}
		if(this.isPiercedNavel() && Math.random()<0.9f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(Util.randomItemFromValues("innoxia_piercing_gemstone_barbell", "innoxia_piercing_ringed_barbell"), accessoryColour, false), true, this);
		}
		if(this.isPiercedNipple() && this.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(
					Util.randomItemFromValues("innoxia_piercing_basic_barbell_pair", "norin_piercings_piercing_nipple_rings", "norin_piercings_piercing_nipple_chain"), accessoryColour, false), true, this);
		}
		
		
		// Weapon:
		
		// Randomise weapon based on birth month:
		if(this.getBirthday().getMonth().getValue()<=4) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", DamageType.POISON));
			
		} else if(this.getBirthday().getMonth().getValue()<=8) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.POISON));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_feather_epic", DamageType.POISON));
			
		} else {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.POISON));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.POISON));
		}
		
		Util.random.setSeed(System.nanoTime()); // Reset seed to be close to random
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Combat:

	@Override
	public void applyEscapeCombatEffects() {
		Main.game.banishNPC(this);
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return null; //TODO
		} else {
			return null; //TODO
		}
	}
	
}