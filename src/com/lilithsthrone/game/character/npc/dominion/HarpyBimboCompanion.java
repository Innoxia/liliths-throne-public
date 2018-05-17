package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestBimbo;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8
 * @version 0.1.89
 * @author Innoxia
 */
public class HarpyBimboCompanion extends NPC {

	private static final long serialVersionUID = 1L;

	public HarpyBimboCompanion() {
		this(false);
	}
	
	public HarpyBimboCompanion(boolean isImported) {
		super(new NameTriplet("Lauren"),
				"Brittany's girlfriend, Lauren, does everything she can to please her matriarch."
						+ " Just like most of the harpies in Brittany's nest, she's a ditzy bimbo, and does absolutely anything her matriarch orders her to.",
				5, Gender.F_V_B_FEMALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HARPY_NEST_YELLOW, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
	
			this.addFetish(Fetish.FETISH_BIMBO);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.FEATHERS_BLEACH_BLONDE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.FEATHERS_BLEACH_BLONDE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
			
			this.setFemininity(90);
			
			this.setVaginaVirgin(false);
			this.setVaginaWetness(Wetness.THREE_WET.getValue());
			this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), true);
			
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setBreastSize(CupSize.D.getMeasurement());
			this.setAssSize(AssSize.FIVE_HUGE.getValue());
			this.setHipSize(HipSize.FIVE_VERY_WIDE.getValue());
			
			this.setHeight(162);
			
			this.setPiercedEar(true);
			this.setPiercedNavel(true);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_CROPTOP_BRA, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_BELTED, Colour.CLOTHING_WHITE, false), true, this);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_SILVER, false), true, this);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#BE09A3";
			
		} else {
			return "#F986E7";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}
	
	// Combat:
	
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public String getCombatDescription() {
		return UtilText.parse(this,
				"[npc.Name] is eager to do [bimboHarpy.name]'s bidding, and under the watchful eyes of the rest of the flock, she moves forwards to attack you.");
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", HarpyNestBimbo.HARPY_NEST_BIMBO_FIGHT_BEAT_GF);
		} else {
			return new Response("", "", HarpyNestBimbo.HARPY_NEST_BIMBO_FIGHT_LOSE);
		}
	}
	
}