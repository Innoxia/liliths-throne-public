package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @author DSG
 */
public class Felicia extends NPC {
    
    public Felicia() {
        this(false);
    }
    
    public Felicia(boolean isImported) {
        super (isImported, new NameTriplet("Felicia"), "Delilah-Hope Renmorre",
                "Felicia is Arthur’s neighbor and sometimes Caregiver." + 
                        " While she tries to act calm and collected, she lights up at the sight of anything she remotely enjoys." + 
                        " Is known for the smoothest, fluffiest fur in town.",
                30,
                Month.FEBRUARY, 17,
                10,
                Gender.F_B_DOLL, Subspecies.getSubspeciesFromId("dsg_dog_subspecies_samoyed"), RaceStage.GREATER,
                new CharacterInventory(10),
                WorldType.FELICIA_APARTMENT, PlaceType.FELICIA_APARTMENT_LIVING_AREA,
                true);
        
        if(!isImported) {
            this.setPlayerKnowsName(false);
        }
    }
    
    @Override
    public void setStartingBody(boolean setPersona) {
        if (setPersona) {
            this.setPersonalityTraits(
                    PersonalityTrait.CONFIDENT,
                    PersonalityTrait.KIND,
                    PersonalityTrait.PRUDE);
            //TODO: fetishes
        }
        
        // Core:
        this.setHeight(157);
        this.setFemininity(75);
        this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
        this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
        
        // Coverings:
        this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, PresetColour.EYE_BROWN));
        this.setSkinCovering(new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.NONE, CoveringModifier.FLUFFY, PresetColour.COVERING_WHITE, false, PresetColour.COVERING_WHITE, false), true);
        this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
        this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_LIGHT), true);
        this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_LIGHT), true);

        this.setHairCovering(new Covering(BodyCoveringType.HAIR_CANINE_FUR, PresetColour.COVERING_WHITE), true);
        this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
        this.setHairStyle(HairStyle.PONYTAIL);

        this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_WHITE), false);
        this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_CANINE_FUR, PresetColour.COVERING_WHITE), false);
        this.setUnderarmHair(BodyHair.ZERO_NONE);
        this.setAssHair(BodyHair.ZERO_NONE);
        this.setPubicHair(BodyHair.ZERO_NONE);
        this.setFacialHair(BodyHair.ZERO_NONE);
        
        // Face:
        this.setFaceVirgin(true);
        this.setLipSize(LipSize.ONE_AVERAGE);
        this.setFaceCapacity(Capacity.TWO_TIGHT, true);
        this.setFaceElasticity(OrificeElasticity.ONE_RIGID.getValue());
        this.setFacePlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
        // Throat settings and modifiers
        this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
        // Tongue modifiers

        // Chest:
        this.setBreastRows(3);
        this.setNippleVirgin(true);
        this.setBreastSize(CupSize.C.getMeasurement());
        this.setBreastShape(BreastShape.ROUND);
        this.setNippleSize(NippleSize.ONE_SMALL.getValue());
        this.setAreolaeSize(AreolaeSize.ONE_SMALL.getValue());
        // Nipple settings and modifiers

        // Ass:
        this.setAssVirgin(true);
        this.setAssBleached(false);
        this.setAssSize(AssSize.FOUR_LARGE);
        this.setHipSize(HipSize.FOUR_WOMANLY);
        this.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
        this.setAssWetness(Wetness.ZERO_DRY);
        this.setAssElasticity(OrificeElasticity.TWO_FIRM.getValue());
        this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
        
        // Anus modifiers
		
        // Penis:
        // No benis

        // Vagina:
        // No vagene

        // Feet:
	this.setFootStructure(FootStructure.DIGITIGRADE);
    }

    @Override
    public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
        loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
    }
    
    @Override
    public void setupPerks(boolean autoSelectPerks) {
            PerkManager.initialisePerks(this,
                            Util.newArrayListOfValues(),
                            Util.newHashMapOfValues(
                                            new Util.Value<>(PerkCategory.PHYSICAL, 1),
                                            new Util.Value<>(PerkCategory.LUST, 0),
                                            new Util.Value<>(PerkCategory.ARCANE, 0)));
    }

    @Override
    public void changeFurryLevel() {
    }
    
    @Override
    public void equipClothing(List<EquipClothingSetting> settings) {

        this.unequipAllClothingIntoVoid(true, true);
                       
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_BLACK_JET, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_FULLCUP_BRA, PresetColour.CLOTHING_BLACK_JET, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK_JET, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_RED_BURGUNDY, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, false), true, this);
        equipInsideClothing();

    }
    
    public void equipInsideClothing() {
        if(this.getClothingInSlot(InventorySlot.FOOT) != null){
            this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.FOOT), true, this);
        }
        if(this.getClothingInSlot(InventorySlot.LEG) != null){
            this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.LEG), true, this);
        }
        if(this.getClothingInSlot(InventorySlot.TORSO_UNDER) != null){
            this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.TORSO_UNDER), true, this);
        }
        if(this.getClothingInSlot(InventorySlot.TORSO_OVER) != null){
            this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.TORSO_OVER), true, this);
        }
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_RIBBED_SWEATER, PresetColour.CLOTHING_BROWN, false), true, this); 
    }
    
    public void equipOutsideClothing() {
        if(this.getClothingInSlot(InventorySlot.TORSO_OVER) != null){
            this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.TORSO_OVER), true, this);
        }
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_chelsea_boots", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_feminine_blazer", PresetColour.CLOTHING_RED_VERY_DARK, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_GREY, false), true, this); 
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_feminine_short_sleeve_shirt", PresetColour.CLOTHING_ORANGE, PresetColour.CLOTHING_GREY, PresetColour.CLOTHING_WHITE, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers_women", PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_STEEL, false), true, this);        
    }
    
    @Override
    public DialogueNode getEncounterDialogue() {
        return null;
    }
    
    @Override
    public boolean isUnique() {
        return true;
    }
    
//    @Override
//    public String getSpeechColour() {
//            if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
//                    return "#e6e68a";
//            }
//            return "#fff0c1";
//    }
}
