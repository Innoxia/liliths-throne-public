package com.lilithsthrone.game.character.npc.submission;
import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
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
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Vengar extends NPC {

	public Vengar() {
		this(false);
	}
	
	public Vengar(boolean isImported) {
		super(isImported, new NameTriplet("Vengar"), "Ironfist",
				"",
				27, Month.JUNE, 2,
				25, Gender.M_P_MALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(500), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) { // Reset character
			setupPerks(true);
			setStartingBody(true);
			equipClothing(null);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.clearPersonalityTraits();
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.setPersonalityTraits(
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_GANG_LEADER);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
	
			this.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		// Core:
		this.setHeight(192);
		this.setFemininity(0);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, Colour.EYE_HAZEL), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, Colour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, Colour.SKIN_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_BROWN), false);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, Colour.COVERING_BROWN_DARK), false);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, Colour.COVERING_BROWN_DARK), false);
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
		this.setBreastShape(BreastShape.WIDE);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.THREE_THICK);
		this.setPenisSize(20);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(CumProduction.FOUR_LARGE.getMedianValue());
		this.fillCumToMaxStorage();
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}

	public void applySissification() {
		this.clearFetishes();
		this.clearFetishDesires();
		
		this.addFetish(Fetish.FETISH_SUBMISSIVE);
		this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
		this.addFetish(Fetish.FETISH_PENIS_RECEIVING);
		this.setFetishDesire(Fetish.FETISH_CUM_ADDICT, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		
		this.setHeight(177);
		this.setFemininity(65);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setSkinCovering(new Covering(BodyCoveringType.EYE_RAT, Colour.EYE_HAZEL), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, Colour.COVERING_BROWN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, Colour.SKIN_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_BROWN), false);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, Colour.COVERING_BROWN), false);
		this.setHairLength(HairLength.FOUR_MID_BACK);
		this.setHairStyle(HairStyle.PONYTAIL);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, Colour.COVERING_BROWN_DARK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.TWO_MOIST);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.TWO_AVERAGE);
		this.setPenisSize(6);
		this.setTesticleSize(TesticleSize.ONE_TINY);
		this.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue());
		this.fillCumToMaxStorage();
		
		AbstractClothing cage = AbstractClothingType.generateClothing("innoxia_bdsm_ornate_chastity_cage", Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_WHITE, Colour.CLOTHING_BRASS, false);
		cage.setSealed(false);
		this.equipClothingFromNowhere(cage, true, Main.game.getNpc(Roxy.class));
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_buttPlugs_butt_plug_heart", Colour.CLOTHING_SILVER, Colour.CLOTHING_PINK_LIGHT, null, false), true, Main.game.getNpc(Roxy.class));
		
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, Colour.CLOTHING_BLACK, false, null, null));
		
		this.setScar(InventorySlot.CHEST, new Scar(ScarType.CLAW_MARKS, true));
		this.setScar(InventorySlot.TORSO_OVER, new Scar(ScarType.JAGGED_SCAR, false));
		this.setScar(InventorySlot.MOUTH, new Scar(ScarType.STRAIGHT_SCAR, false));

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLUE_GREY, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_SARASHI, Colour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_hand_fingerless_gloves", Colour.CLOTHING_DESATURATED_BROWN, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_BLACK, false), true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_UNDER), DisplacementType.UNBUTTONS, true, true, this);
		
		AbstractClothing cargo = AbstractClothingType.generateClothing("innoxia_leg_cargo_trousers", Colour.CLOTHING_BLACK, false);
		cargo.setPattern("multi_camo");
		cargo.setPatternColour(Colour.CLOTHING_BLACK);
		cargo.setPatternSecondaryColour(Colour.CLOTHING_BLACK_JET);
		cargo.setPatternTertiaryColour(Colour.CLOTHING_GREY);
		this.equipClothingFromNowhere(cargo, true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_BLACK_STEEL, false), true, this);
	}
	
	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		
		if(this.getHomeLocationPlace().getPlaceType()==PlaceType.GAMBLING_DEN_TRADER) {
			sb.append("While at one time he was the leader of a ruthless criminal gang, Vengar now finds himself at the mercy of his ex-girlfriend, Roxy."
					+ " Thoroughly subdued and hopelessly addicted to her pussy, this once-proud rat-boy now finds himself doing anything his abusive ex demands of him.");
			
		} else {
			sb.append("Strong, scarred, and with an almost-perpetual snarl on his face, Vengar strikes the very sort of figure that's needed to ensure the unwavering loyalty of several dozen criminal rat-morphs."
					+ " Always ready to back up his words with action, this rat-boy is considered to be one of the most dangerous inhabitants of Submission.");
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean isUnique() {
		return true;
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
		if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END)) {
			this.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER);
			
		} else {
			if(!Main.game.getCharactersPresent().contains(this)
					&& Main.game.getPlayer().isCaptive()
					&& !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
				if(!Main.game.isExtendedWorkTime() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					this.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
				} else {
					this.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
				}
			}
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated(){
		return true;
	}

	@Override
	public void endSex() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarCaptiveVengarSatisfied, true);
	}

	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(victory) {
			return new Response("", "", RatWarrensDialogue.VENGAR_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getNpc(Silence.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS, true);
					Main.game.getNpc(Shadow.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_BOUNTY_HUNTERS, true);
				}
			};

		} else {
			return new Response("", "", RatWarrensDialogue.VENGAR_COMBAT_DEFEAT) {
				@Override
				public void effects() {
					RatWarrensDialogue.applyCombatDefeatFlagsReset();
				}
			};
		}
	}
}
