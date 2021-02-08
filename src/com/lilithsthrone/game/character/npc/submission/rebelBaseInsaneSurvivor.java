/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lilithsthrone.game.character.npc.submission;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author dustland.crow
 */
public class rebelBaseInsaneSurvivor extends NPC {
    
    public static final int RECRUITMENT_YEAR = 1990;
    
    public rebelBaseInsaneSurvivor() {
        this(Gender.M_P_MALE, false);
    }
    
    public rebelBaseInsaneSurvivor(Gender gender) {
        this(gender, false);
    }
    
    public rebelBaseInsaneSurvivor(boolean isImported) {
        this(Gender.M_P_MALE, isImported);
    }
    
    public rebelBaseInsaneSurvivor(Gender gender, boolean isImported, NPCGenerationFlag... generationFlags) {
        super(isImported, null, null, "",
				(Main.game.getDateNow().getYear() - RECRUITMENT_YEAR) + 18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5,
				null, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.REBEL_BASE, PlaceType.REBEL_BASE_COMMON_AREA_SEARCHED, false,
				generationFlags);
        
        if(!isImported) {
            this.setLocation(Main.game.getPlayer(), true);
            
            // Spawn with level between 7 and 10
            setLevel(7 + Util.random.nextInt(4));
            
            setName(Name.getRandomTriplet(this.getRace()));
            this.setPlayerKnowsName(false);
            setDescription(UtilText.parse(this, "While apparently mostly human, [npc.name] has evidently spent a long time underground and has lost [npc.her] mind."));
            setBodyToGenderIdentity(true);
            CharacterUtils.randomiseBody(this, false);
            CharacterUtils.generateItemsInInventory(this);
        
            initPerkTreeAndBackgroundPerks();
            this.setStartingCombatMoves();
            loadImages();
        
            initHealthAndManaToMax();
        }
        
    }

    @Override
    public void setStartingBody(boolean setPersona) {
        if(setPersona) {
            this.clearPersonalityTraits();
            this.clearFetishes();
            this.clearFetishDesires();
            
            this.setPersonalityTraits(PersonalityTrait.COWARDLY);
            this.setHistory(Occupation.NPC_UNEMPLOYED);
            this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
        }
        
        this.setMuscle(Muscle.TWO_TONED.getMedianValue());
        this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
        
        // Coverings befitting someone who's spent 3 decades or more hiding underground subsisting on glowing mushrooms        
        this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PORCELAIN), true);
        this.getCovering(BodyCoveringType.HUMAN).setPrimaryGlowing(true);
        
        this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
        this.setHairStyle(HairStyle.MESSY);
        this.setUnderarmHair(BodyHair.FIVE_UNKEMPT);
        this.setAssHair(BodyHair.FIVE_UNKEMPT);
        this.setPubicHair(BodyHair.FIVE_UNKEMPT);
        this.setFacialHair(BodyHair.FIVE_UNKEMPT);
        
        this.getCovering(BodyCoveringType.EYE_HUMAN).setPrimaryGlowing(true);       
        this.getCovering(BodyCoveringType.MOUTH).setSecondaryGlowing(true);
        this.getCovering(BodyCoveringType.ANUS).setSecondaryGlowing(true);
        this.getCovering(BodyCoveringType.VAGINA).setSecondaryGlowing(true);
        this.getCovering(BodyCoveringType.PENIS).setSecondaryGlowing(true);
        this.getCovering(BodyCoveringType.NIPPLES).setSecondaryGlowing(true);
        
        // Add some random TFs our survivor as picked up over the years       
        switch(Util.random.nextInt(1)) {
            case 0:
                this.setTailType(TailType.ALLIGATOR_MORPH);
                this.setTailGirth(PenetrationGirth.THREE_AVERAGE);
                this.setEarType(EarType.RAT_MORPH);
                break;
            case 1:
                this.setTailType(TailType.RAT_MORPH);
                this.setTailGirth(PenetrationGirth.THREE_AVERAGE);
                this.setEarType(EarType.BAT_MORPH);
                break;
        }
        
        
    }

    @Override
    public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
        loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
    }

    @Override
    public void changeFurryLevel() {
    }
    
    @Override
    public void equipClothing(List<EquipClothingSetting> settings) {
        this.unequipAllClothingIntoVoid(true, true);
        
        if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
            this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbsmg"));
        }
        
        this.setMoney(0);
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.STOMACH_SARASHI, PresetColour.CLOTHING_WHITE, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_WHITE, false), true, this);
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtunic", false), true, this);
        this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_UNDER), DisplacementType.UNBUTTONS, true, true, this);
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtrousers", false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rwebbing", false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_hlf_equip_vcboots", false), true, this);
        
    }

    @Override
    public DialogueNode getEncounterDialogue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUnique() {
        return false;
    }
    
}
