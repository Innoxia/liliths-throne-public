package com.lilithsthrone.game.character.npc.fields;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.WingType;
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
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
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
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import java.time.Month;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @since 0.4.8.6
 * @version 0.4.8.6
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
			Gender.M_P_MALE, Subspecies.getSubspeciesFromId("dsg_dragon_subspecies_dragon"), RaceStage.GREATER,
			new CharacterInventory(10),
			WorldType.getWorldTypeFromId("innoxia_fields_elis_market"),
			PlaceType.getPlaceTypeFromId("dsg_fields_elis_market_produce"),
			true);
		if(!isImported) {
		    this.setPlayerKnowsName(false);
		    this.dailyUpdate();
		}
    }
    
    @Override
    public void setupPerks(boolean autoSelectPerks) {
	    this.addSpecialPerk(Perk.SPECIAL_CHILD_OF_THE_CRAG);
	    this.setHistory(Occupation.NPC_FARMER);
	    
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
		this.setWingType(WingType.getWingTypeFromId("dsg_dragon_wing"));
		this.setWingSize(WingSize.THREE_LARGE.getValue());
	
		// Coverings:
		this.setEyeCovering(new Covering("dsg_dragon_eye", PresetColour.EYE_ORANGE));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE_LIGHT, false, PresetColour.ORIFICE_INTERIOR, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_BLUE_LIGHT, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_BLUE_LIGHT, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_BLUE_LIGHT, false), true);
		this.setSkinCovering(new Covering(BodyCoveringType.TONGUE, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE_LIGHT, false, PresetColour.ORIFICE_INTERIOR, false), true);
		this.setSkinCovering(new Covering("dsg_dragon_scales", CoveringPattern.MARKED, CoveringModifier.SMOOTH, PresetColour.COVERING_BLUE, false, PresetColour.COVERING_WHITE, false), true);
	
		this.setHairCovering(new Covering("dsg_dragon_hair", PresetColour.COVERING_WHITE, PresetColour.COVERING_WHITE), false);
		this.setHairType(HairType.getHairTypeFromId("dsg_dragon_hairMane"));
		this.setHairLength(HairLength.ZERO_BALD);
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
	this.unequipAllClothingIntoVoid(true, true);
	
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_WHITE, false), InventorySlot.GROIN, true, this);
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_GREY_LIGHT, false), InventorySlot.SOCK, true, this);
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eisek_sundshirt", PresetColour.CLOTHING_WHITE, false), InventorySlot.TORSO_UNDER, true, this);
	
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eisek_rspntrousers", PresetColour.CLOTHING_GREY_LIGHT, false), InventorySlot.LEG, true, this);
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eisek_rspntunic", PresetColour.CLOTHING_BLACK, false), InventorySlot.TORSO_OVER, true, this);
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eisek_oleathboots", PresetColour.CLOTHING_DESATURATED_BROWN, false), InventorySlot.FOOT, true, this);
	this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eisek_seawings_ring", PresetColour.CLOTHING_GOLD, false), InventorySlot.FINGER, true, this);
    }
    
    @Override
    public boolean isUnique() {
	    return true;
    }
    
    @Override
    public String getDescription() {
	    if(this.isPlayerKnowsName() == false && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_banished"))) {
		    return "You found this dragon-boy being harrassed by a mob while manning a stall at The Farmer's Market in Elis.";
		    
	    } else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_banished"))) {
		    return "You found this dragon-boy being harrassed by a mob while manning a stall at The Farmer's Market in Elis. Intentionally or not, you sided with the mob and drove him off.";
		    
	    } else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_asked_himself"))) {
		    return "Eisek is a produce merchant at The Farmer's Market in Elis. As a dragon, [npc.he] is often unwelcome in civilised areas, a fact that he is well aware of."
		    		+ " He spends most of his time tending to his crops high up in the mountains and only visits Elis three days out of the month.";
		    
	    } else {
		    return "Eisek runs a produce stall in the Elis Farmer's Market. As a dragon, [npc.he] is often unwelcome in civilised areas, a fact that he is well aware of.";
	    }
    }
    
    @Override
    public void changeFurryLevel() {
    }

    @Override
    public DialogueNode getEncounterDialogue() {
    	return null;
    }
    
    @Override
    public boolean isTrader() {
	    return true;
    }
    
    @Override
    public boolean willBuy(AbstractCoreItem item) {
	    return false;
    }

	@Override
	public String getTraderDescription() {
		return UtilText.parseFromXMLFile("dsg/characters/eisek/stall", "VENDOR");
	}
	
    @Override
    public void applyItemTransactionEffects(AbstractCoreItem itemSold, int quantity, int individualPrice, boolean soldToPlayer) {
		if(soldToPlayer) {
		    Main.game.appendToTextEndStringBuilder(UtilText.parse(this, "<p>[eisek.speech([game.random(Thank you for your patronage.|I hope you enjoy it.|A handy snack, is it not?)])]</p>"));
		    if(this.getAffection(Main.game.getPlayer()) < 15 && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_EISEK_MOB)) {
		    	if(this.getAffection(Main.game.getPlayer()) + (quantity * 3) > 15) {
			    	Main.game.appendToTextEndStringBuilder(this.setAffection(Main.game.getPlayer(), 15));
		    	} else {
			    	Main.game.appendToTextEndStringBuilder(this.incrementAffection(Main.game.getPlayer(), quantity * 3));
		    	}
		    }
		}
    }
    
    @Override
    public float getSellModifier(AbstractCoreItem item) {
	    return 1.1f;
    }
    
    @Override
    public void dailyUpdate() {
		if (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_banished"))) {
		    if (Main.game.getDateNow().getDayOfMonth() >= 1 && Main.game.getDateNow().getDayOfMonth() <= 3) {
				// Probably could reduce this to only update on the first day, but that might get annoying when adding items in future updates or for modders
				clearNonEquippedInventory(false);	
				for(AbstractItemType item : ItemType.getAllItems()) {
					if(item.getItemTags().contains(ItemTag.SOLD_BY_EISEK)
							&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
						this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(2+Util.random.nextInt(2)), false, false);
					}
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_persuade"))
						&& Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_EISEK_MOB)) {
				    this.addItem(Main.game.getItemGen().generateItem("dsg_race_dragon_dragonfruit_yellow"), 1+Util.random.nextInt(1), false, false);
				    this.addItem(Main.game.getItemGen().generateItem("dsg_race_dragon_dragonfruit_red"), 1+Util.random.nextInt(1), false, false);
				    this.addItem(Main.game.getItemGen().generateItem("dsg_race_dragon_dragonfruit_pink"), 1+Util.random.nextInt(1), false, false);
				}
				if(this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("dsg_fields_elis_market_produce")) {
				    this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_market"), PlaceType.getPlaceTypeFromId("dsg_fields_elis_market_produce"));
				}
				
		    } else { //Shadow realm'd until his real home exists
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE);
		    }
		}
    }
    
    @Override
    public String getSpeechColour() {
    	if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
        	return "#55a2d5";
        }
        return "#55a2d5";
    }
    
}
