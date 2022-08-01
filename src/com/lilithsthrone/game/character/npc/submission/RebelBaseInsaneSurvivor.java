package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.RebelBaseInsaneSurvivorDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.21
 * @version 0.3.21
 * @author DSG
 */
public class RebelBaseInsaneSurvivor extends NPC {
    
    public static final int RECRUITMENT_YEAR = 1990;
    
    public RebelBaseInsaneSurvivor() {
        this(Gender.getGenderFromUserPreferences(false, false), false);
    }
    
    public RebelBaseInsaneSurvivor(Gender gender) {
        this(gender, false);
    }
    
    public RebelBaseInsaneSurvivor(boolean isImported) {
        this(Gender.M_P_MALE, isImported);
    }
    
    public RebelBaseInsaneSurvivor(Gender gender, boolean isImported) {
        super(isImported, null, null, "",
				(Main.game.getDateNow().getYear() - RECRUITMENT_YEAR) + 18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5,
				gender, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.REBEL_BASE, PlaceType.REBEL_BASE_SLEEPING_AREA, false);
        
        if(!isImported) {
            this.setLocation(Main.game.getPlayer(), true);
            
            // Spawn with level between 12 and 15
            setLevel(12 + Util.random.nextInt(4));
            
            setName(Name.getRandomTriplet(this.getRace()));
            this.setPlayerKnowsName(false);
            this.setGenericName("insane survivor");
            this.setEssenceCount(500);
            setDescription(UtilText.parse(this, "While apparently mostly human, [npc.name] has evidently spent a long time underground and has lost [npc.her] mind."));
            
            Main.game.getCharacterUtils().generateItemsInInventory(this);
            this.addItem(Main.game.getItemGen().generateItem(ItemType.MUSHROOM), 10, false, false);
        
            initPerkTreeAndBackgroundPerks();
            this.setStartingCombatMoves();
            
            //no character image (yet)
            //loadImages();
        
            initHealthAndManaToMax();
            this.addStatusEffect(StatusEffect.PSYCHOACTIVE, 3600);
        }
    }
    
    // Do not add tease as nobody wants to see the gyrations of a living crack zombie
    @Override
    public void setStartingCombatMoves() {
        this.clearEquippedMoves();
        this.equipMove("strike");
        this.equipMove("twin-strike");
    }

    @Override
    public float getMoveWeight(AbstractCombatMove move, List<GameCharacter> enemies, List<GameCharacter> allies) {
        // Death or Glory!
        if (move.getType() == CombatMoveType.TEASE || move.getType() == CombatMoveType.DEFEND) {
            return 0;
        } else {
            return super.getMoveWeight(move, enemies, allies);
        }
    }
    
    @Override
    public void setupPerks(boolean autoSelectPerks) {
            PerkManager.initialisePerks(this,
                Util.newArrayListOfValues(),
                Util.newHashMapOfValues(
                    new Value<>(PerkCategory.PHYSICAL, 5),
                    new Value<>(PerkCategory.LUST, 0),
                    new Value<>(PerkCategory.ARCANE, 0)));
    }


    @Override
    public void setStartingBody(boolean setPersona) {
        if(setPersona) {
            this.clearPersonalityTraits();
            this.clearFetishes();
            this.clearFetishDesires();
            
            this.setPersonalityTraits(PersonalityTrait.COWARDLY);
            this.setHistory(Occupation.NPC_REBEL_FIGHTER);
            this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
        }
        this.setBodyToGenderIdentity(true);
        this.setMuscle(Muscle.TWO_TONED.getMedianValue());
        this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
        
        // Coverings befitting someone who's spent 3 decades or more hiding underground subsisting on glowing mushrooms        
        this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
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
        switch(Util.random.nextInt(2)) {
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
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_stomach_sarashi", PresetColour.CLOTHING_WHITE, false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_WHITE, false), true, this);
        
        AbstractClothing tunic = Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtunic", false);
        tunic.setSticker("wearntear", "patched");
        
        this.equipClothingFromNowhere(tunic, true, this);
        this.isAbleToBeDisplaced(this.getClothingInSlot(InventorySlot.TORSO_UNDER), DisplacementType.UNBUTTONS, true, true, this);
        
        AbstractClothing trousers = Main.game.getItemGen().generateClothing("dsg_hlf_equip_rtrousers", false);
        trousers.setSticker("wearntear", "patched");
        
        this.equipClothingFromNowhere(trousers, true, this);
        
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_hlf_equip_rwebbing", false), true, this);
        this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_hlf_equip_vcboots", false), true, this);
        
        if(this.hasBreasts())
        {
            this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_loinCloth_ragged_chest_wrap", false), true, this);
        }
        if(this.isFeminine())
        {
            this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", false), true, this);
        }
        else
        {
            this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", false), true, this);
        }
        
    }

    @Override
    public DialogueNode getEncounterDialogue() {
        return RebelBaseInsaneSurvivorDialogue.INSANE_SURVIVOR_ATTACK;
    }

    @Override
    public boolean isUnique() {
        return false;
    }
    
    @Override
    public int getEscapeChance() {
        return 0;
    }

    @Override
	public boolean isLootingPlayerAfterCombat() {
		return false; // Looting is handled in the INSANE_SURVIVOR_DEFEATED effects
	}
	
    @Override
    public Response endCombat(boolean applyEffects, boolean victory) {
        if(victory) {
            return new Response("", "", RebelBaseInsaneSurvivorDialogue.INSANE_SURVIVOR_VICTORY);			
        
        } else {
            return new Response("", "", RebelBaseInsaneSurvivorDialogue.INSANE_SURVIVOR_DEFEATED);
        
        }
    }
    
}
