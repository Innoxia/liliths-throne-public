package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.dominion.NatalyaSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public class Natalya extends NPC {

	public Natalya() {
		this(false);
	}
	
	public Natalya(boolean isImported) {
		super(isImported, new NameTriplet("Natalya"), "Lunettemartu",
				"Holding the prestigious title of 'Stable Mistress' at the delivery company, 'Dominion Express', Natalya is responsible for the training and care of over fifty centaur slaves."
					+ " While she tries her best to remain calm and professional at all times, her lustful demonic urges sometimes get the better of her...",
				84, Month.OCTOBER, 12,
				15,
				null, null, null,
				new CharacterInventory(10),
				WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_OFFICE_STABLE,
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("strict succutaur");
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.5")) {
			this.setLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_OFFICE_STABLE, true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setStartingBody(false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.6")) {
			if(this.getClothingInSlot(InventorySlot.LEG)==null) {
				this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bdsm_riding_crop", DamageType.PHYSICAL));
			this.setHeight(186);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(84);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 3),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STABLE_MISTRESS);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.addFetish(Fetish.FETISH_SIZE_QUEEN);
			this.setFetishDesire(Fetish.FETISH_MASTURBATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
		}
		
		
		// Body:
		this.setAgeAppearanceAbsolute(35);
		this.setBody(Gender.F_P_B_SHEMALE, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setWingType(WingType.NONE);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.ZERO_TINY.getMedianValue());
		this.setLegType(LegType.DEMON_HORSE_HOOFED);
		this.setLegConfiguration(LegType.DEMON_HORSE_HOOFED, LegConfiguration.QUADRUPEDAL, true);
		this.setBreastCrotchType(BreastType.NONE);
		
		// Core:
		this.setHeight(186);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_GREY_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_LILAC_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BLACK), true);

		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_LILAC), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_EBONY), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_LILAC), false);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_BLACK), false);
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.BUN);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PURPLE));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.E.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.setAssCapacity(Capacity.FIVE_ROOMY, true);
		this.setAssWetness(Wetness.FIVE_SLOPPY);
		// Horse-like modifiers:
		this.addAssOrificeModifier(OrificeModifier.PUFFY);
		this.addAssOrificeModifier(OrificeModifier.RIBBED);
		this.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(46);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(500);
		this.setPenisCumExpulsion(85);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		// Horse-like modifiers:
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.VEINY);
		this.addPenisModifier(PenetrationModifier.SHEATHED);
		this.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
//		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
//		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
//		this.setVaginaSquirter(true);
//		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
//		this.setVaginaWetness(Wetness.FOUR_SLIMY);
//		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
//		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_leg_taur_skirt"), PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_torso_feminine_short_sleeve_shirt"), PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_torsoOver_feminine_blazer"), PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_sticks", PresetColour.CLOTHING_PURPLE_ROYAL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_horseshoe_necklace", PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_thick_rim_glasses", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_SILVER, false), true, this);

		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bdsm_riding_crop", DamageType.PHYSICAL));
		}
	}

	@Override
	public String getDescription() {
		if(this.isPlayerKnowsName()) {
			return super.getDescription();
		}
		return UtilText.parse(this, "This smartly dressed succutaur has an air of superiorty about her...");
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#e4a1e0";
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void dailyUpdate() {
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
			Main.game.getDialogueFlags().incrementNatalyaPoints(-2);
			if(Main.game.getDateNow().getDayOfMonth()==1) {
				Main.game.getDialogueFlags().setNatalyaPoints(0);
			}
		}
	}
	
	@Override
	public void endSex() {
		if(this.getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP
				|| this.getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK) {
			if(this.getClothingInSlot(InventorySlot.ANUS)!=null) {
				this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.ANUS), true, Main.game.getPlayer());
			}
			this.replaceAllClothing();
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public SexPace getSexPaceDomPreference() {
		return SexPace.DOM_NORMAL;
	}

	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character) {
		return SexPace.SUB_EAGER;
	}
	
	public void insertDildo() {
		AbstractClothing dildo = Main.game.getItemGen().generateClothing("innoxia_anus_ribbed_dildo", PresetColour.CLOTHING_BLACK, false);
		
		dildo.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_VIBRATION, TFPotency.MAJOR_BOOST, 0));
		dildo.setName("Natalya's "+UtilText.applyVibration("vibrating", dildo.getRarity().getColour())+" dildo");
		
		this.equipClothingFromNowhere(dildo, true, this);
	}
	
	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		return true;
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(NatalyaSpecials.class);
	}
	
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(SexActionInterface sexAction, OrgasmCumTarget target, boolean applyExtraEffects, String description) {
		if(this.getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) { // Scene in alleyway behind Helena's shop:
			StringBuilder sb = new StringBuilder();
//			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			sb.append(UtilText.parseFromXMLFile("characters/dominion/natalya", "HELENA_ALLEYWAY_ORGASM"));
			
			// Natalya facial reactions:
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
				sb.append(UtilText.parseFromXMLFile("characters/dominion/natalya", "HELENA_ALLEYWAY_ORGASM_FACIAL"));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("characters/dominion/natalya", "HELENA_ALLEYWAY_ORGASM_NO_FACIAL"));
			}
			
			return new SexActionOrgasmOverride(true) {
				@Override
				public String getDescription() {
					return sb.toString();
				}
				@Override
				public void applyEffects() {
					if(applyExtraEffects) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 10));
							
						} else {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					}
				}
			};
			
		} else if(this.getLocationPlace().getPlaceType()==PlaceType.DOMINION_PARK && !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
			StringBuilder sb = new StringBuilder();
//			sb.append(GenericOrgasms.getGenericOrgasmDescription(sexAction, this, target));
			sb.append(UtilText.parseFromXMLFile("characters/dominion/natalya", "PARK_ORGASM"));
			
			// Natalya facial reactions:
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
				sb.append(UtilText.parseFromXMLFile("characters/dominion/natalya", "PARK_ORGASM_FACIAL"));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("characters/dominion/natalya", "PARK_ORGASM_NO_FACIAL"));
			}
			
			return new SexActionOrgasmOverride(true) {
				@Override
				public String getDescription() {
					return sb.toString();
				}
				@Override
				public void applyEffects() {
					if(applyExtraEffects) {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 10));
							
						} else {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -10));
						}
					}
				}
			};
		}
		
		return super.getSexActionOrgasmOverride(sexAction, target, applyExtraEffects, description);
	}
	
	public String getDirtyTalk() {
		if(this.getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
			return UtilText.parseFromXMLFile("characters/dominion/natalya", "HELENA_ALLEYWAY_DIRTY_TALK");
		} else {
			if(Main.sex.getOngoingCharactersUsingAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS).contains(this)) {
				return UtilText.parseFromXMLFile("characters/dominion/natalya", "DIRTY_TALK_RECEIVING_BLOWJOB");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.game.getPlayer(), SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS).contains(this)) {
				return UtilText.parseFromXMLFile("characters/dominion/natalya", "DIRTY_TALK_RECEIVING_RIMJOB");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, SexAreaPenetration.PENIS).contains(this)) {
				return UtilText.parseFromXMLFile("characters/dominion/natalya", "DIRTY_TALK_GIVING_ANAL");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH).contains(this)) {
				return UtilText.parseFromXMLFile("characters/dominion/natalya", "DIRTY_TALK_GIVING_BLOWJOB");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE).contains(this)) {
				return UtilText.parseFromXMLFile("characters/dominion/natalya", "DIRTY_TALK_GIVING_RIMJOB");
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.game.getPlayer(), SexAreaPenetration.PENIS, SexAreaOrifice.ANUS).contains(this)) {
				return UtilText.parseFromXMLFile("characters/dominion/natalya", "DIRTY_TALK_RECEIVING_ANAL");
				
			}
		}
		
		return super.getDirtyTalk();
	}

	@Override
	public String getVaginaRevealDescription(GameCharacter characterBeingRevealed, GameCharacter characterReacting) {
		if(characterBeingRevealed.isPlayer()) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>");
				sb.append(UtilText.parse(characterBeingRevealed, characterReacting,
						"[npc2.Name] [npc2.verb(sneer)] when [npc2.she] [npc2.verb(see)] "
								+ (Main.sex.hasLubricationTypeFromAnyone(characterBeingRevealed, SexAreaOrifice.VAGINA, LubricationType.GIRLCUM)
										? "[npc.namePos] wet [npc.pussy] betraying [npc.her] arousal, and in a tone of absolute disgust, [npc2.she] snaps, "
										: "[npc.namePos] [npc.pussy+], and in a tone of absolute disgust, [npc2.she] snaps, ")
								+ "[npc2.speech(How repulsive! I <i>hate</i> seeing dirty little fuck-holes! I'll have that removed later...)]"));
			sb.append("</p>");
			
			return sb.toString();
		}
		
		return super.getVaginaRevealDescription(characterBeingRevealed, characterReacting);
	}
	
	@Override
	public TransformativePotion generateTransformativePotion(GameCharacter target) {
		AbstractItemType itemType = ItemType.getItemTypeFromId("innoxia_race_horse_sugar_carrot_cube");
		
		List<PossibleItemEffect> effects = new ArrayList<>();

		// Feminine form:
		for(int i=target.getFemininityValue(); i<65; i+=15) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1), ""));
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1), ""));
		}
		if(target.hasHair()) {
			for(int i=target.getHairRawLengthValue(); i<HairLength.THREE_SHOULDER_LENGTH.getMaximumValue(); i+=15) {
				effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.TWO_FULL.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), ""));
		}
		
		// Breasts:
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.D.getMeasurement(); i+=3) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		for(int i=target.getNippleSize().getValue(); i<NippleSize.TWO_BIG.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1), ""));
		}
		for(int i=target.getAreolaeSize().getValue(); i<AreolaeSize.TWO_BIG.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.BOOST, 1), ""));
		}
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_ORIFICE_PUFFY, TFPotency.MINOR_BOOST, 1), ""));

		// Ass:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_DEPTH, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_ORIFICE_PUFFY, TFPotency.MINOR_BOOST, 1), ""));
		if(target.getHipSize().getValue()<HipSize.ONE_VERY_NARROW.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1), ""));
		} else if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1), ""));
		}
		if(target.getAssSize().getValue()<AssSize.ONE_TINY.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		} else if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), ""));
		}

		// Remove vagina:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), ""));
		
		// Add penis:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
		if(target.getPenisRawSizeValue()<15) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getPenisRawSizeValue()<30) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		for(int i=target.getPenisRawGirthValue(); i<PenetrationGirth.THREE_AVERAGE.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), ""));
		}
		
		// Balls & cum:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_BOOST, 1), ""));
		if(target.isInternalTesticles()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_INTERNAL, TFPotency.MINOR_DRAIN, 1), ""));
		}
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));

		return new TransformativePotion(itemType, effects);
	}

}