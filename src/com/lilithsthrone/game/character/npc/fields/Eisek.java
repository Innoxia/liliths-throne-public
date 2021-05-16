/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lilithsthrone.game.character.npc.fields;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
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
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import java.time.Month;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author DSG
 */
public class Eisek extends NPC {
    
    public Eisek() {
	this(false);
    }
    
    public Eisek(boolean isImported) {
	super(isImported, new NameTriplet("Eisek","Emio","Eylean"), "Seawings",
		"",
		25, Month.FEBRUARY, 9,
		20,
		Gender.M_P_MALE, Subspecies.getSubspeciesFromId("dsg_dragon_subspecies_dragon"),
		RaceStage.GREATER,
		new CharacterInventory(10),
		WorldType.getWorldTypeFromId("innoxia_fields_elis_town"),
		PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_market"),
		true);
	if(!isImported) {
	    this.setPlayerKnowsName(false);
            setDescription(UtilText.parse(this, "[npc.Name] runs a produce stall in the Elis Farmer's Market. As a dragon, [npc.he] is often unwelcome in civilised areas, a fact that he is well aware of."));
	}
    }
    
    @Override
    public void setupPerks(boolean autoSelectPerks) {
	    this.addSpecialPerk(Perk.SPECIAL_CHILD_OF_THE_CRAG);

	    PerkManager.initialisePerks(this,
			    Util.newArrayListOfValues(),
			    Util.newHashMapOfValues(
					    new Util.Value<>(PerkCategory.PHYSICAL, 1),
					    new Util.Value<>(PerkCategory.LUST, 0),
					    new Util.Value<>(PerkCategory.ARCANE, 0)));
    }

    @Override
    public void setStartingBody(boolean setPersona) {
	
	// Persona:

	if(setPersona) {
	    
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

		this.setHistory(Occupation.NPC_FARMER);

		this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);

		this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		this.setFetishDesire(Fetish.FETISH_DENIAL, FetishDesire.ZERO_HATE);
	}

	// Body:

	// Core:
	this.setHeight(211);
	this.setFemininity(5);
	this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
	this.setBodySize(BodySize.THREE_LARGE.getMedianValue());

	// Coverings:
	this.setEyeCovering(new Covering("dsg_dragon_eye", PresetColour.EYE_ORANGE));
	this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
	this.setSkinCovering(new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE_LIGHT, false, PresetColour.ORIFICE_INTERIOR, false), true);
	this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_BLUE_LIGHT, false), true);
	this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_BLUE_LIGHT, false), true);
	this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_BLUE_LIGHT, false), true);
	this.setSkinCovering(new Covering(BodyCoveringType.TONGUE, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE_LIGHT, false, PresetColour.ORIFICE_INTERIOR, false), true);
	this.setSkinCovering(new Covering("dsg_dragon_scales", CoveringPattern.MARKED, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_WHITE, false), true);

	this.setHairCovering(new Covering("dsg_dragon_hairMane", CoveringPattern.NONE, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), false);
	this.setHairLength(11);
	this.setHairStyle(HairStyle.NONE);

	this.setHairCovering(new Covering("dsg_dragon_body_hair", PresetColour.COVERING_BLACK), false);
	this.setUnderarmHair(BodyHair.FOUR_NATURAL);
	this.setAssHair(BodyHair.FOUR_NATURAL);
	this.setPubicHair(BodyHair.FOUR_NATURAL);
	this.setFacialHair(BodyHair.ZERO_NONE);

	// Face:
	this.setFaceVirgin(true);
	this.setLipSize(LipSize.ONE_AVERAGE);
	this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
	// Throat settings and modifiers
	this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
	// Tongue modifiers

	// Chest:
	this.setNippleVirgin(true);
	this.setBreastSize(CupSize.FLAT.getMeasurement());
	this.setBreastShape(BreastShape.SIDE_SET);
	this.setNippleSize(NippleSize.ZERO_TINY);
	this.setAreolaeSize(AreolaeSize.ZERO_TINY);
	// Nipple settings and modifiers

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
	this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
	this.setPenisSize(28);
	this.setTesticleSize(TesticleSize.THREE_LARGE);
	this.setPenisCumStorage(120);
	this.fillCumToMaxStorage();
	// Leave cum as normal value

	// Vagina:
	// No vagina

	// Feet:
	this.setFootStructure(FootStructure.DIGITIGRADE);
    }

    @Override
    public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
	    loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
    }
    
    @Override
    public boolean isAbleToBeImpregnated() {
	    return false;
    }
    
    @Override
    public void equipClothing(List<EquipClothingSetting> settings) {
	//TODO
    }
    
    @Override
    public boolean isUnique() {
	    return true;
    }
    
    @Override
    public void changeFurryLevel() {
    }

    @Override
    public DialogueNode getEncounterDialogue() {
	return null;
    }
    
    @Override
    public void dailyUpdate() {
    }
    
}
