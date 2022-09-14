package com.lilithsthrone.game.character.npc.submission;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.ScarType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensCaptiveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.submission.SAMurkSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Murk extends NPC {

	public Murk() {
		this(false);
	}
	
	public Murk(boolean isImported) {
		super(isImported, new NameTriplet("Murk"), "Triche",
				"",
				36, Month.JANUARY, 12,
				10, Gender.M_P_MALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(2500), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE, true);
		
		if(!isImported) {
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 60);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.7")) {  // Reset character
			this.setName(new NameTriplet("Murk", "Missy", "Missy"));
			setupPerks(true);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			setStartingBody(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_PALE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setBirthday(LocalDateTime.of(Main.game.getStartingDate().getYear()-36, Month.JANUARY, 12, 12, 0));
			this.setPenisCumStorage(350);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.2")) {
			if(this.isFeminine()) {
				this.setVaginaType(VaginaType.RAT_MORPH);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9")) {
			this.addCumModifier(FluidModifier.MUSKY);
			this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
			this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREY_GREEN), false);
			this.setPenisSize(38);
			this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.4")) {
			this.setName(new NameTriplet("Murk"));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.17")) {
			this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1")) {
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
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
		// Persona:
		if(setPersona) {
			this.clearPersonalityTraits();
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.setPersonalityTraits(
					PersonalityTrait.COWARDLY,
					PersonalityTrait.LEWD,
					PersonalityTrait.SELFISH,
					PersonalityTrait.SLOVENLY);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_GANG_MEMBER);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
			
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		// Core:
		this.setHeight(146);
		this.setFemininity(30);
		this.setMuscle(Muscle.ZERO_SOFT.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREY_GREEN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_PALE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_DARK), false);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisSize(38);
		this.setPenisGirth(PenetrationGirth.SEVEN_FAT);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(350);
		this.fillCumToMaxStorage();
		this.addCumModifier(FluidModifier.MUSKY);
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_WHITE, false, null, null));
			this.setScar(InventorySlot.LEG, new Scar(ScarType.CLAW_MARKS, true));
		}
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bat_metal", DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_GUNMETAL, PresetColour.CLOTHING_BLACK)));
		}
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_crotchless_chaps", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_BLACK, false), true, this);
		
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		
		if(this.getHomeLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE) {
			if(this.isFeminine()) {
				sb.append("Having convinced Epona to punish Murk in a manner fitting his crimes, the once rat-'boy' is now a rat-'girl'."
						+ " 'Missy', as she's now known, is still responsible for cleaning up the pregnancy roulette stalls, although Epona seems to be a little more lenient towards her now.");
				
			} else {
				sb.append("Having been enslaved as punishment for his crimes of rape and kidnap, Murk has ended up being given to [axel.name] as a form of compensation for the protection money he was made to pay in the past."
						+ " Not at all interested in owning such a depraved creature, [axel.name] was quick to give him to Epona, who, furious at discovering the facts about Murk's past, has put him to hard work cleaning up the pregnancy roulette stalls.");
			}
			
		} else {
			sb.append("Short, chubby, and with a love for openly displaying his disproportionately huge cock, the rat-boy known as Murk is not exactly known for either his looks or modesty."
					+ " Instead, his undesirable reputation throughout the Rat Warrens has been gained from the open knowledge that he's selfish, cowardly, and has a cruel streak bordering on sadism.");

			sb.append("<br/>"
					+ "Although anyone else would have been subjected to relentless bullying for continuously displaying these less-than-admirable qualities,"
						+ " Murk's fellow gang members tolerate him due to the fact that he runs what he calls the 'Milking Room'."
					+ " This aptly-named area is home to several kidnapped humans, who have been transformed so as to provide both a steady supply of milk and a welcome source of sexual release to Vengar's gang members.");
		}
		
		return sb.toString();
	}
	
	public String applyFeminisation() {
		StringBuilder sb = new StringBuilder();
		
		this.clearFetishes();
		this.clearFetishDesires();
		
		sb.append(this.addFetish(Fetish.FETISH_SUBMISSIVE));
		sb.append(this.addFetish(Fetish.FETISH_MASOCHIST));
		sb.append(this.addFetish(Fetish.FETISH_EXHIBITIONIST));
		
		this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.ZERO_HATE);
		this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		
		// Body:
		// Core:
		this.setHeight(146);
		sb.append(this.setFemininity(75));
		this.setMuscle(Muscle.ZERO_SOFT.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_YELLOW), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_PINK_PALE), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_PINK_PALE), false);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		sb.append(this.setHairLength(HairLength.THREE_SHOULDER_LENGTH));
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		sb.append(this.setLipSize(LipSize.TWO_FULL));
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		sb.append(this.setBreastSize(CupSize.DD.getMeasurement()));
		this.setBreastShape(BreastShape.POINTY);
		sb.append(this.setNippleSize(NippleSize.THREE_LARGE));
		sb.append(this.setAreolaeSize(AreolaeSize.THREE_LARGE));
		
		// Ass:
		sb.append(this.setAssSize(AssSize.FOUR_LARGE));
		sb.append(this.setHipSize(HipSize.FOUR_WOMANLY));
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ONE_SLIGHTLY_MOIST);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		// Anus modifiers
		
		// Penis:
		sb.append(this.setPenisType(PenisType.NONE));
		
		// Vagina:
		sb.append(this.setVaginaType(VaginaType.RAT_MORPH));
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		sb.append(this.setVaginaLabiaSize(LabiaSize.THREE_LARGE));
		sb.append(this.setVaginaSquirter(true));
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		sb.append(this.setVaginaWetness(Wetness.FOUR_SLIMY));
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
		// Foot shape
		
		return sb.toString();
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getSpeechColour() {
		if(this.isFeminine()) {
			return PresetColour.BASE_TAN.toWebHexString();
		}
		return PresetColour.BASE_BROWN.toWebHexString();
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void turnUpdate() {
	}

	@Override
	public boolean isAbleToBeImpregnated(){
		return true;
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(victory) {
			return new Response("", "", RatWarrensCaptiveDialogue.CAPTIVE_ESCAPE_FIGHT_VICTORY);
			
		} else {
			return new Response("", "", RatWarrensCaptiveDialogue.CAPTIVE_ESCAPE_FIGHT_DEFEAT);
		}
	}
	
	// Sex:

	@Override
	public int calculateSexTypeWeighting(SexType type, GameCharacter target, List<SexType> request, boolean lustOrArousalCalculation) {
		if(type.getPerformingSexArea()==SexAreaPenetration.TAIL) {
			return -10000;
		}
		return super.calculateSexTypeWeighting(type, target, request, lustOrArousalCalculation);
	}
	
	@Override
	public int getOrgasmsBeforeSatisfied() {
		if(Main.game.getPlayer().isCaptive() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
			return RatWarrensCaptiveDialogue.murkOrgasmsRequired;
		}
		return super.getOrgasmsBeforeSatisfied();
	}
	
//	@Override
//	public SexPace getSexPaceDomPreference(){
//		return SexPace.DOM_NORMAL;
//	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SAMurkSpecials.class);
	}

	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		if(Main.game.getPlayer().isCaptive() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.badEnd)) {
			return true; // Always deny requests before transformations are finished.
		}
		return super.getSexBehaviourDeniesRequests(requestingCharacter, sexTypeRequest);
	}
	
	// Dirty talk:

	@Override
	public String getDirtyTalkPenisPenetrating(GameCharacter target, boolean isPlayerDom){
		List<String> availableLines = new ArrayList<>();
		
		if(!Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target).isEmpty()) {
			for(SexAreaOrifice orifice : Main.sex.getOrificesBeingPenetratedBy(this, SexAreaPenetration.PENIS, target)) {
				if(Main.game.isGapeContentEnabled()) {
					switch(orifice) {
						case ANUS:
							if(Capacity.isPenetrationDiameterTooBig(
									target.getAssElasticity(), target.getAssStretchedCapacity(), this.getPenisDiameter(), true)) {
								availableLines.add(UtilText.returnStringAtRandom(
										"Ya feel me fat cock stretchin' yer ass out?! Yer gonna end up as nothin' but me slutty cock-sleeve!",
										"Ya horny bitch! I'm gonna make ya into me filthy little butt-slut!",
										"Ya feel that, fuck toy?! Ya feel me fat cock plungin' deep into ya slutty little ass an' stretchin' ya out?!",
										"Yer slutty asshole is gonna be ruined by the time I'm done with ya!",
										"Ain't nothin' betta than the feel o' stretchin' fresh butts out an' turnin' 'em into ruined gapin' 'oles!",
										"Yer slutty asshole is stretchin' out nicely! Ya gonna be well an' truly ruined soon enough!"));
							} else {
								availableLines.add(UtilText.returnStringAtRandom(
										"Ya feel me fat cock sinkin' deep into yer ruined asshole?! Ain't nothin' but me fat cock that can satisfy ya now!",
										"Ya horny bitch! Take me fat cock deep into yer gapin' butt!",
										"Ya feel that, fuck toy?! Ya feel me fat cock plungin' deep into ya ruined ass?!"));
							}
							break;
						case VAGINA:
							if(Capacity.isPenetrationDiameterTooBig(
									target.getVaginaElasticity(), target.getVaginaStretchedCapacity(), this.getPenisDiameter(), true)) {
								availableLines.add(UtilText.returnStringAtRandom(
										"Ya feel me fat cock stretchin' yer filthy cunt out?! Yer gonna end up as nothin' but me slutty gapin' cock-sleeve!",
										"Ya horny bitch! I'm gonna ruin yer pussy an turn it into a gapin' hole that'll only fit me fat cock!",
										"Ya feel that, fuck toy?! Ya feel me fat cock plungin' deep into ya slutty cunt an' stretchin' ya out?!",
										"Yer filthy cunt is gonna be ruined by the time I'm done with ya!",
										"Ain't nothin' betta than the feel o' stretchin' fresh pussies out an' turnin' 'em into ruined gapin' 'oles!",
										"Yer dirty cunt is stretchin' out nicely! Ya gonna be well an' truly ruined soon enough!"));
							} else {
								availableLines.add(UtilText.returnStringAtRandom(
										"Ya feel me fat cock sinkin' deep into yer ruined cunt?! Ain't nothin' but me fat cock that can satisfy ya now!",
										"Ya horny bitch! Take me fat cock deep into yer gapin' pussy!",
										"Ya feel that, fuck toy?! Ya feel me fat cock plungin' deep into ya ruined pussy?!"));
							}
							break;
						default:
							break;
					}
				}
				if(availableLines.isEmpty()) {
					switch(orifice) {
						case ANUS:
							availableLines.add(UtilText.returnStringAtRandom(
									"Ya feel me fat cock sinkin' deep into yer ass?! Yer nothin' but me slutty cock-sleeve!",
									"Ya horny bitch! I'm gonna make ya into me filthy little butt-slut!",
									"Ya feel that, fuck toy?! Ya feel me fat cock plungin' deep into ya slutty little ass?!"));
							break;
						case VAGINA:
							availableLines.add(UtilText.returnStringAtRandom(
									"Ya feel me fat cock sinkin' deep into yer slutty cunt?! Yer nothin' but me dirty little cock-sleeve!",
									"Ya horny bitch! Take me fat cock deep into yer dirty cunt!",
									"Ya feel that, fuck toy?! Ya feel me fat cock plungin' deep into ya slutty pussy?!"));
							break;
						default:
							break;
					}
				}
			}
		}

		if(!availableLines.isEmpty()) {
			String returnedLine = Util.randomItemFrom(availableLines);
			return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
		}
		return super.getDirtyTalkPenisPenetrating(target, isPlayerDom);
	}
	

	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(receivingCharacter.isPlayer() && receivingCharacter.isCaptive()) {
			if(penetrated==SexAreaOrifice.VAGINA) {
				return "";
			} else if(penetrated==SexAreaOrifice.ANUS) {
				return "";
			}
		}
		
		return super.getSpecialPlayerVirginityLoss(penetratingCharacter, penetrating, receivingCharacter, penetrated);
	}

	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
				+ "</p>"
				+ "<p>"
					+ "As Murk's fat cock continues thrusting in and out of your ugly cunt, the sudden realisation of what's just happened hits you like a sledgehammer."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(I've lost my virginity?!"
						+ "<br/>Like... <b>this</b>?!)]"
				+ "</p>"
				+ "<p>"
					+ "You don't know what's worse; losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
					+ " As your massive, dangling labia lewdly spread to accommodate the rat-boy's fat, throbbing dick, you start to think of yourself as nothing more than just another one of his milker sluts..."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(As I've given my virginity to Murk, that makes me his slut...<br/>"
						+ "Yes...<br/>"
						+ "I'll be his good milker slut...)]"
				+ "</p>"
				+ "<p>"
					+ "Completely surrendering to Murk, you let out a horny moan and start desperately bucking your hips back against him, resigning yourself to the fact that now you're nothing more than a"
					+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
				+ "</p>";
	}
}
