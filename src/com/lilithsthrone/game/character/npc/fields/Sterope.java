package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.7.10
 * @version 0.4.7.10
 * @author Innoxia
 */
public class Sterope extends NPC {

	public Sterope() {
		this(false);
	}
	
	public Sterope(boolean isImported) {
		super(isImported, new NameTriplet("Sterope"), "Hippos",
				"Sterope is an Enforcer Constable who works as the receptionist at the station in Elis.",
				33, Month.DECEMBER, 2,
				15,
				Gender.F_V_B_FEMALE, Subspecies.HORSE_MORPH, RaceStage.PARTIAL,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_elis_enforcer_station"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_enforcer_station_reception"),
				true);
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.8.8")) {
			this.setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9")) {
			this.equipClothing();
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ENFORCER_PATROL_CONSTABLE);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_PENIS_RECEIVING);
			this.addFetish(Fetish.FETISH_SIZE_QUEEN);
			this.addFetish(Fetish.FETISH_BONDAGE_VICTIM);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING);
			
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DENIAL_SELF, FetishDesire.THREE_LIKE);
		}
		
		// Body:
		
		// Core:
		this.setHeight(186);
		this.setFemininity(90);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_GREEN));
		
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BUN);
		
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, PresetColour.SKIN_DARK, false, PresetColour.ORIFICE_INTERIOR, false), false);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK_LIGHT));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.D.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
//		this.addNippleOrificeModifier(OrificeModifier.PUFFY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		this.setAssCapacity(Capacity.FIVE_ROOMY, true);
		this.setAssDepth(OrificeDepth.FOUR_DEEP.getValue());
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		// Anus modifiers
		
		// Penis:
//		this.setPenisVirgin(false);
//		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
//		this.setPenisSize(8);
//		this.setTesticleSize(TesticleSize.ONE_TINY);
//		this.setPenisCumStorage(15);
//		this.setPenisCumExpulsion(75);
//		this.fillCumToMaxStorage();
//		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.setPiercedNavel(true);
		this.setPiercedVagina(true);
		this.setPiercedNipples(true);
		this.setPiercedTongue(true);
		this.setPiercedLip(true);
		
		if(this.getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) { // Slutty dress:
			if(this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_stable")) { // Only equip when not in stable:
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_plunge_club_dress", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_ROSE_GOLD, null, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_PINK_HOT, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_strappy_sandals", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_TAN, PresetColour.CLOTHING_SILVER, false), true, this);
			}
			
			AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_SILVER, null, false);
			choker.setSticker("top_txt", "worthless");
			choker.setSticker("btm_txt", "cunt");
			this.equipClothingFromNowhere(choker, true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_thin_bangles", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_meander_ring", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_chain_dangle", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_nipple_rings", PresetColour.CLOTHING_ROSE_GOLD, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_vertical_hood", PresetColour.CLOTHING_ROSE_GOLD, PresetColour.CLOTHING_PINK, null, false), true, this);

			this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
			this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
			this.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK_LIGHT));

		} else { // Enforcer uniform:
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_sticks", PresetColour.CLOTHING_BLACK, false), true, this);
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, null, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_sterope");
			jacket.setSticker("ribbon", "ribbon_sterope");
			this.equipClothingFromNowhere(jacket, true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfpumps", PresetColour.CLOTHING_BLACK, false), true, this);

			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_elis");
			this.equipClothingFromNowhere(hat, true, this);
			
			if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
				this.setEssenceCount(10);
				this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("dsg_eep_pbweap_pbpistol")));
			}
			
			if(settings.contains(EquipClothingSetting.ADD_ACCESSORIES)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);
			}
			
			this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
			this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_LIGHT));
			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_NONE));
			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_NONE));
		}
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			// Tramp stamp:
			this.addTattoo(InventorySlot.TORSO_UNDER,
					new Tattoo(
						TattooType.getTattooTypeFromId("innoxia_symbol_tribal"),
						PresetColour.CLOTHING_BLACK,
						null,
						null,
						false,
						new TattooWriting(
								"Break this bitch!",
								PresetColour.CLOTHING_BLACK,
								false),
						null));
			// Hoof tattoo:
			this.addTattoo(InventorySlot.ANKLE,
					new Tattoo(
						TattooType.getTattooTypeFromId("innoxia_animal_hoof"),
						PresetColour.CLOTHING_BLACK,
						null,
						null,
						false,
						null,
						null));
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#f282f7";
	}
	
	@Override
	public void hourlyUpdate(int hour) {
		if(!Main.game.getCharactersPresent().contains(this)) {
			// Increasing chance per hour of Sterope leaving with a centaur:
			if(!Main.game.getDialogueFlags().hasFlag("innoxia_sterope_with_centaur")
					&& !Main.game.getDialogueFlags().hasFlag("innoxia_sterope_sex")
					&& ((hour==22 && Math.random()<0.5f) || hour==23)) {
				try {
					String centaurID = Main.game.addNPC("misc.GenericSexualPartner", "centaur");
					NPC centaur = (NPC) Main.game.getNPCById(centaurID);
					this.initCentaur(centaur);
					Main.game.getDialogueFlags().setFlag("innoxia_sterope_with_centaur", true);
					this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_stable"));
					centaur.setLocation(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else if((hour>=1 && hour<8) && Main.game.getDialogueFlags().hasFlag("innoxia_sterope_with_centaur")) { // After 1 in the morning, Sterope finishes sex with centaur
				Main.game.getDialogueFlags().setFlag("innoxia_sterope_sex", true);
				try {
					NPC centaur = Main.game.getCharactersPresent(this.getCell()).stream().filter(npc -> npc instanceof GenericSexualPartner).findFirst().get();
					Main.game.banishNPC(centaur);
					
					// This will now work as parsing targets are saved as of v0.4.9, but the above code has been left to cover older versions:
//					UtilText.parse("[#game.banishNPC(centaur)]");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.game.getDialogueFlags().setFlag("innoxia_sterope_with_centaur", false);
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
		
		if((this.isPregnant() && hour==8)
				|| this.getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_fields_elis_enforcer_station")) { // Sterope always gives birth before work
			this.endPregnancy(true);
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(8, 20)) {
				this.returnToHome(); // Enforcer station
				this.equipClothing();
				
			} else if(Main.game.isHourBetween(21, 00)
					&& Main.game.getDialogueFlags().hasFlag("innoxia_sterope_unlocked")
					&& !Main.game.getDialogueFlags().hasFlag("innoxia_sterope_sex")
					&& !Main.game.getDialogueFlags().hasFlag("innoxia_sterope_with_centaur")) {
				if(this.getWorldLocation()!=WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) {
					this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_bar"), false);
					this.moveToAdjacentMatchingCellType(false, PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_tables"));
					this.equipClothing();
				}
				
			} else if(!Main.game.getDialogueFlags().hasFlag("innoxia_sterope_with_centaur")) {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Sex:

	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}
	
	@Override
	public void endSex() {
//		this.cleanAllDirtySlots(true);
//		this.equipClothing();
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	// Centaur:
	public void initCentaur(NPC centaur) {
		if(Math.random()<0.75f) { // Standard centaur subspecies:
			Map<AbstractSubspecies, Integer> taurSubspecies = new HashMap<>();
			taurSubspecies.put(Subspecies.CENTAUR, 16);
			taurSubspecies.put(Subspecies.UNITAUR, 4);
			taurSubspecies.put(Subspecies.PEGATAUR, 4);
			taurSubspecies.put(Subspecies.ALITAUR, 1);
			centaur.setBodyFromSubspeciesPreference(Gender.getGenderFromUserPreferences(false, true), taurSubspecies, false, false);
			
		} else { // Other tauric races:
			Map<AbstractSubspecies, Integer> taurSubspecies = new HashMap<>();
			for(AbstractSubspecies subspecies : Subspecies.allSubspecies) {
				if(subspecies.getRace().getRacialBody().getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL)) {
					taurSubspecies.put(subspecies, 1);
				}
			}
			centaur.setBodyFromSubspeciesPreference(Gender.getGenderFromUserPreferences(false, true), taurSubspecies, false, false);
			if(centaur.getLegConfiguration()!=LegConfiguration.QUADRUPEDAL) {
				centaur.setLegType(centaur.getRace().getRacialBody().getLegType());
				Main.game.getCharacterUtils().applyTaurConversion(centaur);
			}
		}
		
		centaur.setLocation(this, false);
		centaur.setPlayerKnowsName(false);
		centaur.setGenericName(UtilText.parse(centaur, "dominant [npc.race]"));
		centaur.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		centaur.clearPersonalityTraits();
		centaur.addPersonalityTrait(PersonalityTrait.SELFISH);
		centaur.addPersonalityTrait(PersonalityTrait.CONFIDENT);

		Main.game.getCharacterUtils().randomiseBody(centaur, true);
		
		centaur.clearFetishes();
		centaur.addFetish(Fetish.FETISH_DOMINANT);
		centaur.addFetish(Fetish.FETISH_SADIST);
		centaur.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
		Main.game.getCharacterUtils().addFetishes(centaur, Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BIMBO, Fetish.FETISH_PURE_VIRGIN, Fetish.FETISH_CROSS_DRESSER);
		
		centaur.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		centaur.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.TWO_NEUTRAL);
		
		centaur.resetInventory(true);
		centaur.equipClothing(EquipClothingSetting.getAllClothingSettings());
		if(centaur.getClothingInSlot(InventorySlot.PENIS)!=null) {
			centaur.unequipClothingIntoVoid(InventorySlot.PENIS, true, centaur); // Remove cages
		}
	}
	
	private List<String> centaurPunishments;
	
	public void initCentaurPunishments() {
		centaurPunishments = Util.newArrayListOfValues(
				"SPANKING",
				"WHIPPING",
				"DEGRADING_TALK",
				"WAX",
				"NIPPLES",
				"SLAPPING");
	}
	
	public String getRandomCentaurPunishment() {
		String punishment = Util.randomItemFrom(centaurPunishments);
		centaurPunishments.remove(punishment);
		return punishment;
	}
	
	// Transform:
	
	public void applyTransformation() {
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.HORSE_MORPH, RaceStage.GREATER, false);
		setStartingBody(false); // Sets covering colours, breast size, etc.
		resetJewelleryPostTransformation();
	}
	
	public void applyPlayerTransformation() {
		CupSize breasts = Main.game.getPlayer().getBreastSize();
		Main.game.getCharacterUtils().reassignBody(Main.game.getPlayer(), Main.game.getPlayer().getBody(), Gender.F_V_B_FEMALE, Subspecies.HORSE_MORPH, RaceStage.GREATER, false);
		if(breasts.getMeasurement()>Main.game.getPlayer().getBreastSize().getMeasurement()) {
			Main.game.getPlayer().setBreastSize(breasts);
		}
	}
	
	public void resetToStartingBody() {
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.HORSE_MORPH, RaceStage.PARTIAL, false);
		setStartingBody(false); // Sets covering colours, breast size, etc.
		resetJewelleryPostTransformation();
	}
	
	private void resetJewelleryPostTransformation() {
		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.setPiercedNavel(true);
		this.setPiercedVagina(true);
		this.setPiercedNipples(true);
		this.setPiercedTongue(true);
		this.setPiercedLip(true);
		
		for(Entry<AbstractClothing, Integer> clothing : new HashMap<>(this.getAllClothingInInventory()).entrySet()) {
			InventorySlot clothingSlot = clothing.getKey().getClothingType().getEquipSlots().get(0);
			if(clothingSlot.isJewellery()
					&& (clothingSlot!=InventorySlot.PIERCING_EAR || this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_stable"))) {
				this.equipClothingFromInventory(clothing.getKey(), true, this, this);
			}
		}
	}
	
	// Clothing:
	
	@Override
	public Value<Boolean, String> isInventoryEquipAllowed(AbstractClothing clothing, InventorySlot slotToEquipTo) {
		if(this.hasClothing(clothing)) {
			return new Value<>(true, "");
		}
		return new Value<>(false, "You can only equip Sterope's sex toys onto her!");
	}
	
	public void stripForStables() {
		for(InventorySlot slot : InventorySlot.getClothingSlots()) {
			AbstractClothing clothing = this.getClothingInSlot(slot);
			if(clothing!=null
					&& slot!=InventorySlot.NECK
					&& (!slot.isJewellery() || slot!=InventorySlot.PIERCING_EAR)) {
				this.unequipClothingIntoVoid(clothing, true, this);
			}
		}
	}
	
	public void applyTack(GameCharacter equipper, GameCharacter target) {
		// Bridle:
		AbstractClothing bridle = Main.game.getItemGen().generateClothing("innoxia_feral_bridle", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_WHITE, false);
		bridle.clearEffects();
		bridle.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_BONDAGE_VICTIM, TFPotency.MAJOR_BOOST, 0));
		bridle.setName("Bridle of Bondage");
		target.equipClothingFromNowhere(bridle, true, equipper);
		
		// Saddle:
		AbstractClothing saddle = Main.game.getItemGen().generateClothing(
				ClothingType.getClothingTypeFromId("innoxia_feral_saddle"), Util.newArrayListOfValues(PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE), null);
		saddle.clearEffects();
		saddle.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0));
		saddle.setName("Saddle of Submission");
		target.equipClothingFromNowhere(saddle, true, equipper);
		
		// Horseshoes:
		AbstractClothing horseshoes = Main.game.getItemGen().generateClothing("innoxia_feral_horse_shoe", PresetColour.CLOTHING_SILVER, false);
		horseshoes.clearEffects();
		horseshoes.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_MASOCHIST, TFPotency.MAJOR_BOOST, 0));
		horseshoes.setName("Horseshoes of Hurt");
		target.equipClothingFromNowhere(horseshoes, true, equipper);
		
		// Tail bandage:
		AbstractClothing bandage = Main.game.getItemGen().generateClothing("innoxia_feral_bandage", PresetColour.CLOTHING_PINK_HOT, false);
		bandage.clearEffects();
		bandage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_EXHIBITIONIST, TFPotency.MAJOR_BOOST, 0));
		bandage.setName("Bandage of Betrayal");
		target.equipClothingFromNowhere(bandage, true, equipper);
	}
	
	public void wearNippleClamps() {
		AbstractClothing clamps = Main.game.getItemGen().generateClothing("norin_clover_clamps_clover_clamps", PresetColour.CLOTHING_SILVER, false);
		clamps.setName("Sterope's punishment clamps");
		this.equipClothingFromNowhere(clamps, true, this);
	}
	
	public void wearRingGag() {
		AbstractClothing gag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_SILVER, false);
		gag.setName("Sterope's oral trainer");
		this.equipClothingFromNowhere(gag, true, this);
	}
	
	public void wearBlindfold() {
		AbstractClothing blindfold = Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_PINK_HOT, PresetColour.CLOTHING_SILVER, null, false);
		blindfold.setName("Sterope's blindfold");
		this.equipClothingFromNowhere(blindfold, true, this);
	}
	
	public void wearDildo() {
		AbstractClothing dildo = Main.game.getItemGen().generateClothing("norin_dildos_realistic_dildo", PresetColour.CLOTHING_PINK_HOT, false);
		dildo.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_VIBRATION, TFPotency.MAJOR_BOOST, 0));
		dildo.setName("Sterope's cunt filler");
		this.equipClothingFromNowhere(dildo, true, this);
	}
	
	public void wearAnalBeads() {
		AbstractClothing beads = Main.game.getItemGen().generateClothing("norin_anal_beads_anal_beads", PresetColour.CLOTHING_PINK_HOT, false);
		beads.setName("Sterope's anal beads");
		this.equipClothingFromNowhere(beads, true, this);
	}
}
