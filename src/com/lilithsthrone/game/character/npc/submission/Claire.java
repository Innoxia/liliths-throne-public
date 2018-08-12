package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
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
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class Claire extends NPC {

	public Claire() {
		this(false);
	}
	
	public Claire(boolean isImported) {
		super(new NameTriplet("Claire"),
				"Claire is the messenger who's responsible for keeping all of Submission's Enforcer posts up-to-date with one another."
					+ " Thanks to her special 'level three clearance', she's allowed to use the teleportation stations that link all of these posts together."
					+ " As a result of this, she seems to be in multiple places at once, and whenever you enter one of the enforcer posts, Claire is sure to already be there.",
				31, Month.DECEMBER, 14,
				10, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, true);
		
		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_PINK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_PINK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);

			this.setSkinCovering(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK), true);
			
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_STRUTTER);
			this.setFetishDesire(Fetish.FETISH_VOYEURIST, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_LEG_LOVER, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ZERO_HATE);
			
			this.setFemininity(85);
			
			this.setAssVirgin(true);
			this.setAssSize(AssSize.FIVE_HUGE.getValue());
			this.setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
			
			this.setFaceVirgin(false);
			
			this.setVaginaVirgin(false);
			
			this.setBreastSize(CupSize.FF.getMeasurement());
			
			this.setHeight(149);
			
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_GREY, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_MINI_SKIRT, Colour.CLOTHING_GREY, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void turnUpdate() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.SUBMISSION) {
			this.setLocation(WorldType.SUBMISSION, Main.game.getWorlds().get(WorldType.SUBMISSION).getClosestCell(Main.game.getPlayer().getLocation(), PlaceType.SUBMISSION_ENTRANCE).getLocation(), true);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
	}

}