package com.lilithsthrone.game.character.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.ForcedTFTendency;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.FitnessLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.StrengthLevel;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Ear;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Face;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public abstract class NPC extends GameCharacter implements XMLSaving {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_TIME_START_VALUE = -1;
	
	protected long lastTimeEncountered = DEFAULT_TIME_START_VALUE;
	
	protected long lastTimeHadSex = DEFAULT_TIME_START_VALUE;
	protected long lastTimeOrgasmed = DEFAULT_TIME_START_VALUE;
	
	protected int romanceProgress = 0;
	
	protected float buyModifier, sellModifier;

	protected boolean addedToContacts;

	public Set<NPCFlagValue> NPCFlagValues;
	
	protected Set<SexPositionSlot> sexPositionPreferences;
	
	protected Body bodyPreference = null;
	
	protected NPC(NameTriplet nameTriplet, String description, int level, Gender startingGender, RacialBody startingRace,
			RaceStage stage, CharacterInventory inventory, WorldType worldLocation, PlaceType startingPlace, boolean addedToContacts) {
		super(nameTriplet, description, level, startingGender, startingRace, stage, inventory, worldLocation, startingPlace);

		this.addedToContacts = addedToContacts;
		
		sexPositionPreferences = new HashSet<>();
		
		buyModifier=0.75f;
		sellModifier=1.5f;
		
		NPCFlagValues = new HashSet<>();
		
		if(getLocation().equals(Main.game.getPlayer().getLocation()) && getWorldLocation()==Main.game.getPlayer().getWorldLocation()) {
			for(CoverableArea ca : CoverableArea.values()) {
				if(isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
					getPlayerKnowsAreasMap().put(ca, true);
				}
			}
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element npcSpecific = doc.createElement("npcSpecific");
		properties.appendChild(npcSpecific);

		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "lastTimeEncountered", String.valueOf(lastTimeEncountered));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "lastTimeHadSex", String.valueOf(lastTimeHadSex));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "lastTimeOrgasmed", String.valueOf(lastTimeOrgasmed));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "romanceProgress", String.valueOf(romanceProgress));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "buyModifier", String.valueOf(buyModifier));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "sellModifier", String.valueOf(sellModifier));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "addedToContacts", String.valueOf(addedToContacts));

		Element valuesElement = doc.createElement("NPCValues");
		npcSpecific.appendChild(valuesElement);
		for(NPCFlagValue value : NPCFlagValues) {
			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "NPCValue", value.toString());
		}

		// Recalculated in method, so don't need to save
//		Element positionsElement = doc.createElement("sexPositionPreferences");
//		npcSpecific.appendChild(positionsElement);
//		for(SexPositionSlot position : sexPositionPreferences) {
//			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "position", position.toString());
//		}
		
		Element preferredBody = doc.createElement("preferredBody");
		npcSpecific.appendChild(preferredBody);
		getPreferredBody().saveAsXML(preferredBody, doc);
		
		return properties;
	}
	
	public abstract void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings);
	
	public static void loadNPCVariablesFromXML(NPC npc, StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {
		
		GameCharacter.loadGameCharacterVariablesFromXML(npc, log, parentElement, doc, settings);
		
		Element npcSpecificElement = (Element) parentElement.getElementsByTagName("npcSpecific").item(0);
		
		if(npcSpecificElement!=null) {
			npc.setLastTimeEncountered(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeEncountered").item(0)).getAttribute("value")));
			npc.setLastTimeHadSex(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeHadSex").item(0)).getAttribute("value")), false);
			
			if(((Element)npcSpecificElement.getElementsByTagName("lastTimeOrgasmed").item(0))!=null) {
				npc.setLastTimeOrgasmed(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeOrgasmed").item(0)).getAttribute("value")));
			} else {
				npc.setLastTimeOrgasmed(npc.getLastTimeHadSex());
			}
			
			npc.setRomanceProgress(Integer.valueOf(((Element)npcSpecificElement.getElementsByTagName("romanceProgress").item(0)).getAttribute("value")));
			npc.setBuyModifier(Float.valueOf(((Element)npcSpecificElement.getElementsByTagName("buyModifier").item(0)).getAttribute("value")));
			npc.setSellModifier(Float.valueOf(((Element)npcSpecificElement.getElementsByTagName("sellModifier").item(0)).getAttribute("value")));
			npc.addedToContacts = (Boolean.valueOf(((Element)npcSpecificElement.getElementsByTagName("addedToContacts").item(0)).getAttribute("value")));
		
	
			for(int i=0; i<((Element) npcSpecificElement.getElementsByTagName("NPCValues").item(0)).getElementsByTagName("NPCValue").getLength(); i++){
				Element e = (Element) ((Element) npcSpecificElement.getElementsByTagName("NPCValues").item(0)).getElementsByTagName("NPCValue").item(i);
				npc.NPCFlagValues.add(NPCFlagValue.valueOf(e.getAttribute("value")));
			}
			
			// Recalculated in method, so don't need to save
//			for(int i=0; i<((Element) npcSpecificElement.getElementsByTagName("sexPositionPreferences").item(0)).getElementsByTagName("position").getLength(); i++){
//				Element e = (Element) ((Element) npcSpecificElement.getElementsByTagName("sexPositionPreferences").item(0)).getElementsByTagName("position").item(i);
//				try {
//					npc.sexPositionPreferences.add(SexPositionType.valueOf(e.getAttribute("value")));
//				} catch(Exception ex) {
//				}
//			}
			
			npc.bodyPreference = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("preferredBody").item(0), doc);
		}
	}
	
	public void resetSlaveFlags() {
		NPCFlagValues.remove(NPCFlagValue.flagSlaveBackground);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveSmallTalk);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveEncourage);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveHug);
		NPCFlagValues.remove(NPCFlagValue.flagSlavePettings);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveInspect);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveSpanking);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveMolest);
	}
	
	/**
	 * Resets this character to their default state.
	 */
	public void dailyReset() {
	}

	public abstract void changeFurryLevel();
	
	public abstract DialogueNodeOld getEncounterDialogue();
	
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		CharacterUtils.equipClothing(this, replaceUnsuitableClothing, onlyAddCoreClothing);
	}
	
	public boolean isClothingStealable() {
		return false;
	}
	
	// Trader:

	public String getTraderDescription() {
		return UtilText.parse(this,
				"<p>"
					+ "You have a look at what [npc.name] has for sale."
				+ "</p>");
	}

	public boolean isTrader() {
		return false;
	}

	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
	
	/**
	 * Handles any extra effects that need to be taken into account when selling an item to the player.
	 * @param item
	 */
	public void handleSellingEffects(AbstractCoreItem item, int count, int itemPrice) {
	}

	public float getBuyModifier() {
		return buyModifier;
	}

	public void setBuyModifier(float buyModifier) {
		this.buyModifier = buyModifier;
	}

	public float getSellModifier() {
		return sellModifier;
	}

	public void setSellModifier(float sellModifier) {
		this.sellModifier = sellModifier;
	}

	// Combat:
	
	public abstract String getCombatDescription();

	public abstract String getAttackDescription(Attack attackType, boolean isHit);

	public abstract Response endCombat(boolean applyEffects, boolean playerVictory);

	public abstract Attack attackType();

	public Spell getSpell() {
		return null;
	}
	
	public int getEscapeChance() {
		return 30;
	}

	public boolean isSurrendersAtZeroMana() {
		return true;
	}

	// Post-combat:

	public int getExperienceFromVictory() {
		if (Main.game.getPlayer().getLevel() - getLevel() >= 3) {
			return getLevel();
		} else {
			return (getLevel() * 2);
		}
	}

	public int getLootMoney() {
		return (int) ((getLevel() * 20) * (1 + Math.random() - 0.5f));
	}
	
	public List<AbstractCoreItem> getLootItems() {
		double rnd = Math.random();
		
		if(rnd<=0.05) {
			return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.FETISH_UNREFINED)));
			
		} else if(rnd<=0.1) {
			return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.ADDICTION_REMOVAL)));
			
		} else if(rnd <= 0.6) {
			switch(getRace()) {
				case CAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.INT_INGREDIENT_FELINE_FANCY)));
				case COW_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.STR_INGREDIENT_BUBBLE_MILK)));
				case DOG_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.FIT_INGREDIENT_CANINE_CRUSH)));
				case HORSE_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.STR_INGREDIENT_EQUINE_CIDER)));
				case REINDEER_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.FIT_INGREDIENT_EGG_NOG)));
				case WOLF_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.STR_INGREDIENT_WOLF_WHISKEY)));
				case HUMAN:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.INT_INGREDIENT_VANILLA_WATER)));
				case ANGEL:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
				case DEMON:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.COR_INGREDIENT_LILITHS_GIFT)));
				case HARPY:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_HARPY_PERFUME)));
				case SLIME:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
				case ALLIGATOR_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.STR_INGREDIENT_SWAMP_WATER)));
				case SQUIRREL_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.FIT_INGREDIENT_SQUIRREL_JAVA)));
			}
			
		} else if(rnd <= 0.8 && !Main.getProperties().isRaceDiscovered(getRace())) {
			switch(getRace()) {
				case CAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_CAT_MORPH)));
				case COW_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_COW_MORPH)));
				case DOG_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_DOG_MORPH)));
				case HORSE_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_HORSE_MORPH)));
				case REINDEER_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_REINDEER_MORPH)));
				case WOLF_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_WOLF_MORPH)));
				case HUMAN:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_HUMAN)));
				case ANGEL:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
				case DEMON:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_DEMON)));
				case HARPY:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_HARPY)));
				case SLIME:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
				case ALLIGATOR_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_ALLIGATOR_MORPH)));
				case SQUIRREL_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_SQUIRREL_MORPH)));
			}
		
		} else {
			switch(getRace()) {
				case CAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_CAT_MORPH)));
				case COW_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_COW_MORPH)));
				case DOG_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_DOG_MORPH)));
				case HORSE_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HORSE_MORPH)));
				case REINDEER_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_REINDEER_MORPH)));
				case WOLF_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_WOLF_MORPH)));
				case HUMAN:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HUMAN)));
				case ANGEL:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HUMAN)));
				case DEMON:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_DEMON)));
				case HARPY:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HARPY)));
				case SLIME:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HUMAN)));
				case ALLIGATOR_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH)));
				case SQUIRREL_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_SQUIRREL_MORPH)));
			}
		}
		
		return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
	}
	
	public Map<TFEssence, Integer> getLootEssenceDrops() {
		return Util.newHashMapOfValues(new Value<>(TFEssence.ARCANE, Util.random.nextInt(this.getLevel())+1+this.getLevel()));
	}
	
	
	// Relationships:
	
	public float getHourlyAffectionChange(int hour) {
		if(this.workHours[hour]) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				return this.getHomeLocationPlace().getHourlyAffectionChange();
			}
			// To get rid of e.g. 2.3999999999999999999999:
			return Math.round(this.getSlaveJob().getAffectionGain(this)*100)/100f;
		}
		
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(this.getHomeLocationPlace().getHourlyAffectionChange()*100)/100f;
	}
	
	public float getDailyAffectionChange() {
		float totalAffectionChange = 0;
		
		for (int workHour = 0; workHour < this.getTotalHoursWorked(); workHour++) {
			if(this.getSlaveJob()==SlaveJob.IDLE) {
				totalAffectionChange+=this.getHomeLocationPlace().getHourlyAffectionChange();
			}
			totalAffectionChange += this.getSlaveJob().getAffectionGain(this);
		}
		
		for (int homeHour = 0; homeHour < 24-this.getTotalHoursWorked(); homeHour++) {
			totalAffectionChange += this.getHomeLocationPlace().getHourlyAffectionChange();
		}
		
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(totalAffectionChange*100)/100f;
	}
	
	
	// Misc:

	/**
	 * By default, NPCs can't be impregnated.
	 * 
	 * @return
	 */
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	public boolean isKnowsPlayerGender() {
		return NPCFlagValues.contains(NPCFlagValue.knowsPlayerGender);
	}

	public void setKnowsPlayerGender(boolean knowsPlayerGender) {
		if(knowsPlayerGender) {
			NPCFlagValues.add(NPCFlagValue.knowsPlayerGender);
		} else {
			NPCFlagValues.remove(NPCFlagValue.knowsPlayerGender);
		}
	}
	
	public boolean isIntroducedToPlayer() {
		return NPCFlagValues.contains(NPCFlagValue.introducedToPlayer);
	}

	public void setIntroducedToPlayer(boolean introducedToPlayer) {
		if(introducedToPlayer) {
			NPCFlagValues.add(NPCFlagValue.introducedToPlayer);
		} else {
			NPCFlagValues.remove(NPCFlagValue.introducedToPlayer);
		}
	}
	
	public boolean isReactedToPregnancy() {
		return NPCFlagValues.contains(NPCFlagValue.reactedToPregnancy);
	}
	public void setReactedToPregnancy(boolean reactedToPregnancy) {
		if(reactedToPregnancy) {
			NPCFlagValues.add(NPCFlagValue.reactedToPregnancy);
		} else {
			NPCFlagValues.remove(NPCFlagValue.reactedToPregnancy);
		}
	}
	
	public boolean isReactedToPlayerPregnancy() {
		return NPCFlagValues.contains(NPCFlagValue.reactedToPlayerPregnancy);
	}
	public void setReactedToPlayerPregnancy(boolean reactedToPlayerPregnancy) {
		if(reactedToPlayerPregnancy) {
			NPCFlagValues.add(NPCFlagValue.reactedToPlayerPregnancy);
		} else {
			NPCFlagValues.remove(NPCFlagValue.reactedToPlayerPregnancy);
		}
	}
	
	public boolean isPendingClothingDressing() {
		return NPCFlagValues.contains(NPCFlagValue.pendingClothingDressing);
	}
	public void setPendingClothingDressing(boolean pendingClothingDressing) {
		if(pendingClothingDressing) {
			NPCFlagValues.add(NPCFlagValue.pendingClothingDressing);
		} else {
			NPCFlagValues.remove(NPCFlagValue.pendingClothingDressing);
		}
	}
	
	public boolean isPendingTransformationToGenderIdentity() {
		return NPCFlagValues.contains(NPCFlagValue.pendingTransformationToGenderIdentity);
	}
	public void setPendingTransformationToGenderIdentity(boolean pendingTransformationToGenderIdentity) {
		if(pendingTransformationToGenderIdentity) {
			NPCFlagValues.add(NPCFlagValue.pendingTransformationToGenderIdentity);
		} else {
			NPCFlagValues.remove(NPCFlagValue.pendingTransformationToGenderIdentity);
		}
	}
	
	public long getLastTimeEncountered() {
		return lastTimeEncountered;
	}

	public void setLastTimeEncountered(long minutesPassed) {
		this.lastTimeEncountered = minutesPassed;
	}

	public long getLastTimeHadSex() {
		return lastTimeHadSex;
	}
	
	public void setLastTimeHadSex(long lastTimeHadSex, boolean orgasmed) {
		this.lastTimeHadSex = lastTimeHadSex;
		if(orgasmed) {
			setLastTimeOrgasmed(lastTimeHadSex);
		}
	}
	
	public long getLastTimeOrgasmed() {
		return lastTimeOrgasmed;
	}
	
	public void setLastTimeOrgasmed(long lastTimeOrgasmed) {
		this.lastTimeOrgasmed = lastTimeOrgasmed;
	}


	public int getRomanceProgress() {
		return romanceProgress;
	}

	public void setRomanceProgress(int romanceProgress) {
		this.romanceProgress = romanceProgress;
	}

	public boolean isAddedToContacts() {
		return addedToContacts;
	}
	
	public Body getPreferredBody() {
		if(bodyPreference == null) {
			regenerateBodyPreference();
		}
		
		return bodyPreference;
	}
	
	public void regenerateBodyPreference() {
		bodyPreference = generatePreferredBody();
	}
	
	/**
	 * Example return value: ["Let's give you bigger breasts!", AbstractItem]
	 * @return NPC's speech as a reaction to giving you this potion, along with the potion itself.
	 */
	public Value<String, AbstractItem> generateTransformativePotion() {
		
		/* TODO
		 * Body Size
		 * Muscle mass
		 * Penis modifiers
		 * Vagina modifiers
		 * Throat modifiers
		 */
		
		int numberOfTransformations = Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)?2 + Util.random.nextInt(7):1 + Util.random.nextInt(4);
		List<ItemEffect> effects = new ArrayList<>();
		
		AbstractItemType itemType = ItemType.RACE_INGREDIENT_HUMAN;
		String reaction = "Time to transform you!";
		String raceName = "human";
		
		if(Main.getProperties().forcedTFPreference != FurryPreference.HUMAN) {
			if (getPreferredBody().getGender().isFeminine()) {
				raceName = getPreferredBody().getGender().getName() + " " + getPreferredBody().getRace().getSingularFemaleName();
			} else {
				raceName = getPreferredBody().getGender().getName() + " " + getPreferredBody().getRace().getSingularMaleName();
			}
		
			switch(getPreferredBody().getRace()) {
				case CAT_MORPH:
					itemType = ItemType.RACE_INGREDIENT_CAT_MORPH;
					reaction = "Time to turn you into a cute little "+raceName+"!";
					break;
				case DOG_MORPH:
					itemType = ItemType.RACE_INGREDIENT_DOG_MORPH;
					reaction = "Time to turn you into an excitable little "+raceName+"!";
					break;
				case HARPY:
					itemType = ItemType.RACE_INGREDIENT_HARPY;
					reaction = "Time to turn you into a hot little "+raceName+"!";
					break;
				case HORSE_MORPH:
					itemType = ItemType.RACE_INGREDIENT_HORSE_MORPH;
					if (getPreferredBody().getGender().isFeminine()) {
						reaction = "Time to turn you into my little mare!";
					} else {
						reaction = "Time to turn you into my very own stallion!";
					}
					break;
				case REINDEER_MORPH:
					itemType = ItemType.RACE_INGREDIENT_REINDEER_MORPH;
					if (getPreferredBody().getGender().isFeminine()) {
						reaction = "Time to turn you into my little doe!";
					} else {
						reaction = "Time to turn you into my very own buck!";
					}
					break;
				case SQUIRREL_MORPH:
					itemType = ItemType.RACE_INGREDIENT_SQUIRREL_MORPH;
					reaction = "Time to turn you into a cute little "+raceName+"!";
					break;
				case WOLF_MORPH:
					itemType = ItemType.RACE_INGREDIENT_WOLF_MORPH;
					reaction = "Time to turn you into a "+raceName+"!";
					break;
				case ALLIGATOR_MORPH:
					itemType = ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH;
					reaction = "Time to turn you into a "+raceName+"!";
					break;
				case COW_MORPH:
					itemType = ItemType.RACE_INGREDIENT_COW_MORPH;
					break;
				case ANGEL:
				case DEMON:
				case SLIME:
				case HUMAN:
					itemType = ItemType.RACE_INGREDIENT_HUMAN;
					break;
			}
		}
		
		AbstractItemType genitalsItemType = itemType;
		boolean skipGenitalsTF = false;
		
		if(Main.getProperties().forcedTFPreference==FurryPreference.HUMAN || Main.getProperties().forcedTFPreference==FurryPreference.MINIMUM) {
			genitalsItemType = ItemType.RACE_INGREDIENT_HUMAN;
			
			boolean vaginaSet = false;
			boolean penisSet = false;
			
			if((Main.game.getPlayer().getVaginaType() == getPreferredBody().getVagina().getType()) || (getPreferredBody().getVagina().getType()!=VaginaType.NONE && Main.game.getPlayer().hasVagina())) {
				vaginaSet = true;
			}
			
			if((Main.game.getPlayer().getPenisType() == getPreferredBody().getPenis().getType()) || (getPreferredBody().getPenis().getType()!=PenisType.NONE && Main.game.getPlayer().hasPenis())) {
				penisSet = true;
			}
			
			skipGenitalsTF = vaginaSet && penisSet;
		}
		
		
		Map<ItemEffect, String> possibleEffects = new HashMap<>();
		
		// Order of transformation preferences are: Sexual organs -> minor parts -> Legs & arms -> Face & skin 
		
		
		if(!skipGenitalsTF) {
			// Sexual transformations:
			boolean removingVagina = false;
			boolean addingVagina = false;
			boolean removingPenis = false;
			boolean addingPenis = false;
			if(!Main.game.getPlayer().isHasAnyPregnancyEffects()) { // Vagina cannot be transformed if pregnant, so skip this
				if(Main.game.getPlayer().getVaginaType() != getPreferredBody().getVagina().getType()) {
					if(getPreferredBody().getVagina().getType() == VaginaType.NONE) {
						if(Main.game.getPlayer().getVaginaRawCapacityValue() > 1) {
							possibleEffects.put(new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY, TFPotency.DRAIN, 1), "Let's get to work on getting rid of that little cunt of yours!");
							removingVagina = true;
						} else {
							possibleEffects.put(new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), "Let's get rid of that tight little cunt of yours!");
							removingVagina = true;
						}
					} else if((Main.getProperties().forcedTFPreference != FurryPreference.HUMAN && Main.getProperties().forcedTFPreference != FurryPreference.MINIMUM) || getPreferredBody().getVagina().getType()==VaginaType.HUMAN) {
						possibleEffects.put(new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
								"Let's give you a nice "+getPreferredBody().getVagina().getName(Main.game.getPlayer(), false)+"!");
						addingVagina = true;
					}
				}
			}
			if(Main.game.getPlayer().getPenisType() != getPreferredBody().getPenis().getType()) {
				if(getPreferredBody().getPenis().getType() == PenisType.NONE) {
					if(Main.game.getPlayer().getPenisRawSizeValue() > 1) {
						possibleEffects.put(new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1), "Let's get to work on getting rid of that cock of yours!");
						removingPenis = true;
					} else {
						possibleEffects.put(new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), "Let's get rid of that pathetic little cock of yours!");
						removingPenis = true;
					}
				} else if((Main.getProperties().forcedTFPreference != FurryPreference.HUMAN && Main.getProperties().forcedTFPreference != FurryPreference.MINIMUM) || getPreferredBody().getPenis().getType()==PenisType.HUMAN) {
					possibleEffects.put(new ItemEffect(genitalsItemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
							"Let's give you a nice "+getPreferredBody().getPenis().getName(Main.game.getPlayer(), false)+"!");
					addingPenis = true;
				}
			}
			if(!possibleEffects.isEmpty()) {
				String s = "";
				if(possibleEffects.size()>1) {
					if(removingVagina) {
						s+="Let's get to work on getting rid of that cunt of yours,";
						if(removingPenis) {
							s+=" and I thinking it's also time to say goodbye to your pathetic cock as well!";
						} else if(addingPenis) {
							s+=" and give you a nice cock instead!";
						}
					} else if(addingVagina) {
						s+="Let's give you a "+getPreferredBody().getVagina().getName(Main.game.getPlayer(), false)+",";
						if(removingPenis) {
							s+=" and I think I'll get rid of your pathetic cock at the same time!";
						} else if(addingPenis) {
							s+=" and a nice cock as well!";
						}
					}
				}
				
				for(Entry<ItemEffect, String> entry : possibleEffects.entrySet()) {
					if(possibleEffects.size()==1){
						s = entry.getValue();
					}
					effects.add(entry.getKey());
				}
				return new Value<>(s, EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), effects));
			}
		}
		
		
		// All minor part transformations:
		if(Main.getProperties().forcedTFPreference != FurryPreference.HUMAN) {
			if(possibleEffects.isEmpty() || Math.random()>0.33f) {
				if(Main.game.getPlayer().getAntennaType() != getPreferredBody().getAntenna().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ANTENNA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
				if(Main.getProperties().forcedTFPreference != FurryPreference.MINIMUM) {
					if(Main.game.getPlayer().getAssType() != getPreferredBody().getAss().getType()) {
						possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
					}
					if(Main.game.getPlayer().getBreastType() != getPreferredBody().getBreast().getType()) {
						possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
					}
				}
				if(Main.game.getPlayer().getEarType() != getPreferredBody().getEar().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_EARS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
				if(Main.game.getPlayer().getEyeType() != getPreferredBody().getEye().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_EYES, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
				if(Main.game.getPlayer().getHairType() != getPreferredBody().getHair().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
				if(Main.game.getPlayer().getHornType() != getPreferredBody().getHorn().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HORNS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
				if(Main.game.getPlayer().getTailType() != getPreferredBody().getTail().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_TAIL, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
				if(Main.game.getPlayer().getWingType() != getPreferredBody().getWing().getType()) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_WINGS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
				}
			}
			
			// Leg & Arm transformations:
			if(Main.getProperties().forcedTFPreference != FurryPreference.MINIMUM) {
				if(possibleEffects.isEmpty()) {
					if(Main.game.getPlayer().getArmType() != getPreferredBody().getArm().getType()) {
						possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ARMS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
					}
					if(Main.game.getPlayer().getLegType() != getPreferredBody().getLeg().getType()) {
						possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_LEGS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
					}
				}
			}
			// Face & Skin transformations:
			if(Main.getProperties().forcedTFPreference == FurryPreference.NORMAL || Main.getProperties().forcedTFPreference == FurryPreference.MAXIMUM) {
				if(possibleEffects.isEmpty()) {
					if(Main.game.getPlayer().getSkinType() != getPreferredBody().getSkin().getType()) {
						possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_SKIN, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
					}
					if(Main.game.getPlayer().getFaceType() != getPreferredBody().getFace().getType()) {
						possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.NONE, TFPotency.MINOR_BOOST, 1), reaction);
					}
				}
			}
		}
		
		// 50% chance of type TF:
		if(Math.random()<0.5f && !possibleEffects.isEmpty()) {
			List<ItemEffect> keysAsArray = new ArrayList<>(possibleEffects.keySet());
			
			for (int i = 0 ; i < numberOfTransformations ; i++) {
				if(!keysAsArray.isEmpty()) {
					ItemEffect e = keysAsArray.get(Util.random.nextInt(keysAsArray.size()));
					effects.add(e);
					keysAsArray.remove(e);
				}
			}
			
			return new Value<>(
					reaction,
					EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), effects));
		}
		
		
		// Other transformations:
		
		// Cum production:
		if(getPreferredBody().getPenis().getType()!=PenisType.NONE && Main.game.getPlayer().getPenisRawCumProductionValue() < getPreferredBody().getPenis().getTesticle().getRawCumProductionValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CUM, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), "Mmm! You're gonna make lots of cum for me!");
			
		}
		
		// Femininity:
		if(Main.game.getPlayer().getFemininityValue() < getPreferredBody().getFemininity() && Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) != Femininity.valueOf(getPreferredBody().getFemininity())) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1), "I'm gonna need you to be more feminine!");
			
		} else if(Main.game.getPlayer().getFemininityValue() > getPreferredBody().getFemininity() && Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) != Femininity.valueOf(getPreferredBody().getFemininity())) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1), "I'm gonna need you to be more of a man!");
		}
		
		// Height:
		if(Main.game.getPlayer().getHeightValue() < getPreferredBody().getHeightValue() && (getPreferredBody().getHeightValue() - Main.game.getPlayer().getHeightValue() > 5)) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), "Let's make you a little taller!");
			
		} else if(Main.game.getPlayer().getHeightValue() > getPreferredBody().getHeightValue() && (Main.game.getPlayer().getHeightValue() - getPreferredBody().getHeightValue() > 5)) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1), "Let's make you a little shorter!");
		}
		
		// Breast size:
		if(Main.game.getPlayer().getBreastSize().getMeasurement() < getPreferredBody().getBreast().getSize().getMeasurement()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1), "Your breasts need to be bigger!");
			
		} else if(Main.game.getPlayer().getBreastSize().getMeasurement() > getPreferredBody().getBreast().getSize().getMeasurement()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1), "Your breasts are too big!");
		}
		
		// Ass size:
		if(Main.game.getPlayer().getAssSize().getValue() < getPreferredBody().getAss().getAssSize().getValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1), "Your ass needs to be bigger");
			
		} else if(Main.game.getPlayer().getAssSize().getValue() > getPreferredBody().getAss().getAssSize().getValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1), "Your ass is too big!");
		}
		
		// Hip size:
		if(Main.game.getPlayer().getHipSize().getValue() < getPreferredBody().getAss().getHipSize().getValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MINOR_BOOST, 1), "Your hips need to be wider!");
			
		} else if(Main.game.getPlayer().getHipSize().getValue() > getPreferredBody().getAss().getHipSize().getValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MINOR_DRAIN, 1), "Your hips are too wide!");
		}
		

		if(Main.game.getPlayer().getPenisType() != PenisType.NONE && getPreferredBody().getPenis().getType() != PenisType.NONE) {
			// Penis size:
			if(Main.game.getPlayer().getPenisRawSizeValue() < getPreferredBody().getPenis().getRawSizeValue()) {
				if(getPreferredBody().getPenis().getRawSizeValue() - Main.game.getPlayer().getPenisRawSizeValue() > 5) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), "Your cock needs to be a lot bigger!");
				} else {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1), "Your cock needs to be a little bigger!");
				}
				
			} else if(Main.game.getPlayer().getPenisRawSizeValue() > getPreferredBody().getPenis().getRawSizeValue()) {
				if(Main.game.getPlayer().getPenisRawSizeValue() - getPreferredBody().getPenis().getRawSizeValue() > 5) {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1), "Your cock needs to be a lot smaller!");
				} else {
					possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1), "Your cock needs to be a little smaller!");
				}
			}
		}
		
		if(Main.game.getPlayer().getVaginaType() != VaginaType.NONE && getPreferredBody().getVagina().getType() != VaginaType.NONE) {
			// Vagina wetness:
			if(Main.game.getPlayer().getVaginaWetness().getValue() < getPreferredBody().getVagina().getOrificeVagina().getWetness(Main.game.getGenericAndrogynousNPC()).getValue()) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MINOR_BOOST, 1), "Your pussy isn't wet enough!");
			}
		}
		
		// Hair length:
		if(Main.game.getPlayer().getHairRawLengthValue() < getPreferredBody().getHair().getRawLengthValue() && (getPreferredBody().getHair().getRawLengthValue() - Main.game.getPlayer().getHairRawLengthValue() > 5)) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), "Your [pc.hair] "+(Main.game.getPlayer().getHairType().isDefaultPlural()?"are":"is")+" too short!");
			
		} else if(Main.game.getPlayer().getHairRawLengthValue() > getPreferredBody().getHair().getRawLengthValue() && (Main.game.getPlayer().getHairRawLengthValue() - getPreferredBody().getHair().getRawLengthValue() > 5)) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1), "Your [pc.hair] "+(Main.game.getPlayer().getHairType().isDefaultPlural()?"are":"is")+" too long!");
		}
		
		// Lip size:
		if(Main.game.getPlayer().getLipSize().getValue() < getPreferredBody().getFace().getMouth().getLipSize().getValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_BOOST, 1), "Your [pc.lips] are too small!");
			
		} else if(Main.game.getPlayer().getLipSize().getValue() > getPreferredBody().getFace().getMouth().getLipSize().getValue()) {
			possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.MINOR_DRAIN, 1), "Your [pc.lips] are too big!");
		}
		
		if(possibleEffects.isEmpty()) {
			return null;
		}
		
		
//		List<ItemEffect> keysAsArray = new ArrayList<>(possibleEffects.keySet());
//		ItemEffect effect = keysAsArray.get(Util.random.nextInt(keysAsArray.size()));
//
//		for (int i = 0; i < numberOfTransformations; i++) {
//			if (!keysAsArray.isEmpty()) {
//				ItemEffect e = keysAsArray.get(Util.random.nextInt(keysAsArray.size()));
//				effects.add(e);
//				keysAsArray.remove(e);
//			}
//		}
//
//		return new Value<>(
//				possibleEffects.get(effect),
//				EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), effects));
		
		List<ItemEffect> keysAsArray = new ArrayList<>(possibleEffects.keySet());
//		ItemEffect effect = keysAsArray.get(Util.random.nextInt(keysAsArray.size()));
		
		for (int i = 0 ; i < numberOfTransformations ; i++) {
			if(!keysAsArray.isEmpty()) {
				ItemEffect e = keysAsArray.get(Util.random.nextInt(keysAsArray.size()));
				effects.add(e);
				keysAsArray.remove(e);
			}
		}
		
		return new Value<>(
				possibleEffects.get(effects.get(0)),
				EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), effects));
	}
	
	private Body generatePreferredBody() {
		
		// Preferred gender:
		
		Gender preferredGender = Gender.N_P_V_B_HERMAPHRODITE;
		Map<Gender, Integer> desiredGenders = new HashMap<>();
		
		switch(this.getSexualOrientation()) {
			case AMBIPHILIC:
				if(this.isFeminine() && 
						// ambiphilic characters respect forcedTFTendency setting by not entering this case if the
						// player has requested a feminine tendency; admittedly, this specific logic does slightly skew 
						// towards pushing the player feminine in neutral scenarios, but only to a small degree, so more
						// complex but fair logic doesn't feel too required
						Main.getProperties().forcedTFTendency != ForcedTFTendency.FEMININE &&
						Main.getProperties().forcedTFTendency != ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.M_P_MALE, 14);
					// maybe it would be appropriate to raise these chances for impregnators?
					desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
					desiredGenders.put(Gender.M_V_CUNTBOY, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				} else {
					// basic chances of cis-female preference
					desiredGenders.put(Gender.F_V_B_FEMALE, 14);
					
					// increase chances of growing a penis if fetishes increase desirability 
					if(this.hasVagina() && (this.hasFetish(Fetish.FETISH_PREGNANCY) || this.hasFetish(Fetish.FETISH_BROODMOTHER))) {
						desiredGenders.put(Gender.F_P_V_B_FUTANARI, 4);
						desiredGenders.put(Gender.F_P_B_SHEMALE, 4);
						desiredGenders.put(Gender.F_P_TRAP, 4);
					}
					else
					{
						desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
						desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
						desiredGenders.put(Gender.F_P_TRAP, 2);
					};
					
					// heavy masculine forcedTFTendency option adds a bit of a chance for masculine preferenes here
					if (Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE_HEAVY) {
						desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 4);
						desiredGenders.put(Gender.M_V_CUNTBOY, 4);
						desiredGenders.put(Gender.F_P_TRAP, 4);
						desiredGenders.put(Gender.M_V_B_BUTCH, 4);
					}
				}
				break;
			case ANDROPHILIC:
				// Heavy feminine forcedTFTendency causes androphiles to lose the majority of masculine options
				if (Main.getProperties().forcedTFTendency != ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.M_P_MALE, 14);
				}
				
				// base chance options regardless of forcedTFTendency option
				desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
				desiredGenders.put(Gender.M_V_CUNTBOY, 2);
				
				// both feminine forcedTFTendency options add decent chances to get some feminine options despite tastes
				if(Main.getProperties().forcedTFTendency == ForcedTFTendency.FEMININE || 
				   Main.getProperties().forcedTFTendency == ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
					desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
					desiredGenders.put(Gender.M_V_B_BUTCH, 2);
				}
				break;
			case GYNEPHILIC:
				// increase chances of growing a penis if fetishes increase desirability; also, this is a reasonable
				// base level of feminine options even if forcedTFTendency is heavy male
				if(this.hasVagina() && (this.hasFetish(Fetish.FETISH_PREGNANCY) || this.hasFetish(Fetish.FETISH_BROODMOTHER))) {
					desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
					desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				// much lower base chance of pure female preference for heavy masculine forcedTFTendency
				} else if (Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE_HEAVY) {
					desiredGenders.put(Gender.F_V_B_FEMALE, 4);
				}
				else {
					desiredGenders.put(Gender.F_V_B_FEMALE, 14);
				}
				
				// both masculine forcedTFTendency options add decent chances to get some masculine options despite tastes
				if(Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE || 
				   Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE_HEAVY) {
					desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
					desiredGenders.put(Gender.M_V_CUNTBOY, 2);
					desiredGenders.put(Gender.M_V_B_BUTCH, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				}
				break;
		}
		
		int total = 0;
		for(Entry<Gender, Integer> entry : desiredGenders.entrySet()) {
			total+=entry.getValue();
		}
		int count = Util.random.nextInt(total)+1;
		total = 0;
		for(Entry<Gender, Integer> entry : desiredGenders.entrySet()) {
			if(total < count && total+entry.getValue()>= count) {
				preferredGender = entry.getKey();
				break;
			}
			total+=entry.getValue();
		}
		
		// Leaving this hear commented out so it can be easily re-enabled by anyone wanting to tweak or check
		// the resuluts of gender selection and the forcedTFTendency setting
//		System.out.println("PREFERRED GENDER");
//		System.out.println(preferredGender);
//		System.out.println(desiredGenders);
		
		// Preferred race:
		
		Race race = getRace();
		RaceStage stage = getRaceStage();
		RacialBody startingBodyType = RacialBody.valueOfRace(race);
		
		if(Main.getProperties().forcedTFPreference==FurryPreference.HUMAN) {
			race = Race.HUMAN;
			stage = RaceStage.HUMAN;
			startingBodyType = RacialBody.valueOfRace(race);
			
		} else {
		
			// Chance for predator races to prefer prey races:
			if(getRace()==Race.CAT_MORPH && Math.random()>0.8f) {
				race = Race.HARPY;
			}
			if((getRace()==Race.WOLF_MORPH || getRace()==Race.DOG_MORPH) && Math.random()>0.8f) {
				List<Race> availableRaces = new ArrayList<>();
				availableRaces.add(Race.CAT_MORPH);
				availableRaces.add(Race.HARPY);
				availableRaces.add(Race.COW_MORPH);
				availableRaces.add(Race.SQUIRREL_MORPH);
				race = availableRaces.get(Util.random.nextInt(availableRaces.size()));
			}
			
			// Chance for race to be random:
			if(Math.random()>0.85f) {
				List<Race> availableRaces = new ArrayList<>();
				availableRaces.add(Race.CAT_MORPH);
				availableRaces.add(Race.DOG_MORPH);
				availableRaces.add(Race.HARPY);
				availableRaces.add(Race.HORSE_MORPH);
				availableRaces.add(Race.HUMAN);
				availableRaces.add(Race.SQUIRREL_MORPH);
				availableRaces.add(Race.COW_MORPH);
				availableRaces.add(Race.WOLF_MORPH);
				race = availableRaces.get(Util.random.nextInt(availableRaces.size()));
			}
			
			startingBodyType = RacialBody.valueOfRace(race);
			
			// Preferred race stage:
			
			if(preferredGender.isFeminine()) {
				switch(Main.getProperties().raceFemininePreferencesMap.get(race)) {
					case HUMAN:
						stage = RaceStage.HUMAN;
						break;
					case MAXIMUM:
						stage = RaceStage.GREATER;
						break;
					case MINIMUM:
						stage = RaceStage.PARTIAL_FULL;
						break;
					case NORMAL:
						stage = RaceStage.GREATER;
						break;
					case REDUCED:
						stage = RaceStage.LESSER;
						break;
				}
			} else {
				switch(Main.getProperties().raceMasculinePreferencesMap.get(race)) {
					case HUMAN:
						stage = RaceStage.HUMAN;
						break;
					case MAXIMUM:
						stage = RaceStage.GREATER;
						break;
					case MINIMUM:
						stage = RaceStage.PARTIAL_FULL;
						break;
					case NORMAL:
						stage = RaceStage.GREATER;
						break;
					case REDUCED:
						stage = RaceStage.LESSER;
						break;
				}
			}
		}
		
		Body body = new Body.BodyBuilder(
				new Arm((stage.isArmFurry()?startingBodyType.getArmType():ArmType.HUMAN), startingBodyType.getArmRows()),
				new Ass(stage.isAssFurry()?startingBodyType.getAssType():AssType.HUMAN,
						(preferredGender.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
						startingBodyType.getAnusWetness(),
						startingBodyType.getAnusCapacity(),
						startingBodyType.getAnusElasticity(),
						startingBodyType.getAnusPlasticity(),
						true),
				new Breast(stage.isBreastFurry()?startingBodyType.getBreastType():BreastType.HUMAN,
						BreastShape.getRandomBreastShape(),
						(preferredGender.isFeminine() ? startingBodyType.getBreastSize() : startingBodyType.getNoBreastSize()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
						((stage.isSkinFurry() && Main.getProperties().multiBreasts==1) || (stage.isBreastFurry() && Main.getProperties().multiBreasts==2)
								?(preferredGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale())
								:1),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleNippleSize() : startingBodyType.getMaleNippleSize()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleNippleShape() : startingBodyType.getMaleNippleShape()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleAreolaeSize() : startingBodyType.getMaleAreolaeSize()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleNippleCountPerBreast() : startingBodyType.getMaleNippleCountPerBreast()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleBreastCapacity() : startingBodyType.getMaleBreastCapacity()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleBreastElasticity() : startingBodyType.getMaleBreastElasticity()),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleBreastPlasticity() : startingBodyType.getMaleBreastPlasticity()), 
						true),
				new Face((stage.isFaceFurry()?startingBodyType.getFaceType():FaceType.HUMAN),
						(preferredGender.isFeminine() ? startingBodyType.getFemaleLipSize() : startingBodyType.getMaleLipSize())),
				new Eye(stage.isEyeFurry()?startingBodyType.getEyeType():EyeType.HUMAN),
				new Ear(stage.isEarFurry()?startingBodyType.getEarType():EarType.HUMAN),
				new Hair(stage.isHairFurry()?startingBodyType.getHairType():HairType.HUMAN,
						(startingBodyType.isHairTypeLinkedToFaceType()
								?(stage.isFaceFurry()
										?(preferredGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())
										:(preferredGender.isFeminine() ? RacialBody.HUMAN.getFemaleHairLength() : RacialBody.HUMAN.getMaleHairLength()))
								:(preferredGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength())),
						HairStyle.getRandomHairStyle((preferredGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()))),
				new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN),
				new Skin(stage.isSkinFurry()?startingBodyType.getSkinType():SkinType.HUMAN),
				startingBodyType.getBodyMaterial(),
				startingBodyType.getGenitalArrangement(),
				(preferredGender.isFeminine() ? startingBodyType.getFemaleHeight() : startingBodyType.getMaleHeight()),
				(preferredGender.isFeminine() ? startingBodyType.getFemaleFemininity() : startingBodyType.getMaleFemininity()),
				(preferredGender.isFeminine() ? startingBodyType.getFemaleBodySize() : startingBodyType.getMaleBodySize()),
				(preferredGender.isFeminine() ? startingBodyType.getFemaleMuscle() : startingBodyType.getMaleMuscle()))
						.vagina((preferredGender.getGenderName().isHasVagina())
								? new Vagina(stage.isVaginaFurry()?startingBodyType.getVaginaType():VaginaType.HUMAN,
										LabiaSize.getRandomLabiaSize().getValue(),
										startingBodyType.getClitSize(),
										startingBodyType.getVaginaWetness(),
										startingBodyType.getVaginaCapacity(),
										startingBodyType.getVaginaElasticity(),
										startingBodyType.getVaginaPlasticity(),
										true)
								: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 3, 3, true))
						.penis((preferredGender.getGenderName().isHasPenis())
								? new Penis(stage.isPenisFurry()?startingBodyType.getPenisType():PenisType.HUMAN,
									startingBodyType.getPenisSize(),
									startingBodyType.getTesticleSize(),
									startingBodyType.getCumProduction(),
									startingBodyType.getTesticleQuantity())
								: new Penis(PenisType.NONE, 0, 0, 0, 2))
						.horn(new Horn((stage.isHornFurry()?startingBodyType.getRandomHornType(false):HornType.NONE), (preferredGender.isFeminine() ? startingBodyType.getFemaleHornLength() : startingBodyType.getMaleHornLength())))
						.antenna(new Antenna(stage.isAntennaFurry()?startingBodyType.getAntennaType():AntennaType.NONE))
						.tail(new Tail(stage.isTailFurry()?startingBodyType.getTailType():TailType.NONE))
						.wing(new Wing((stage.isWingFurry()?startingBodyType.getWingType():WingType.NONE), (preferredGender.isFeminine() ? startingBodyType.getFemaleWingSize() : startingBodyType.getMaleWingSize())))
						.build();
		
		// Apply fetish modifiers:
		
		GameCharacter genericOwner = Main.game.getGenericAndrogynousNPC();
		
		//Ass:
		if(hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			if(this.getAttributeValue(Attribute.CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.RIBBED);
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.MUSCLE_CONTROL);
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.PUFFY);
			}
			if(this.getAttributeValue(Attribute.CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.TENTACLED);
			}
			
			body.getAss().setAssSize(genericOwner, AssSize.FIVE_HUGE.getValue());
			body.getAss().setAssSize(genericOwner, HipSize.FIVE_VERY_WIDE.getValue());
		}
		
		// Body:
		if(preferredGender.isFeminine()) {
			// Want feminine bodies to be smaller than them:
			body.setHeight(getHeightValue() - Util.random.nextInt(25));
			
		} else {
			// Want masculine bodies to be taller than them:
			body.setHeight(getHeightValue() + Util.random.nextInt(25));
		}
		
		//Breasts:
		if(Main.getProperties().multiBreasts==0) {
			body.getBreast().setRows(genericOwner, 1);
			
		} else if(Main.getProperties().multiBreasts==1) {
			if(stage != RaceStage.GREATER) {
				body.getBreast().setRows(genericOwner, 1);
			}
		}

		if(hasFetish(Fetish.FETISH_BREASTS_OTHERS) && preferredGender.isFeminine()) {
			body.getBreast().setSize(genericOwner, CupSize.DD.getMeasurement() + (Util.random.nextInt(5)));
		}
		
		// Face:
		if(hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
			body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.PUFFY);
			body.getFace().getMouth().setLipSize(genericOwner, LipSize.FOUR_HUGE.getValue());
			
			if(this.getAttributeValue(Attribute.CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.RIBBED);
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.MUSCLE_CONTROL);
			}
			if(this.getAttributeValue(Attribute.CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.TENTACLED);
			}
		}
		
		// Hair:
		if(preferredGender.isFeminine()) {
			body.getHair().setLength(genericOwner, HairLength.THREE_SHOULDER_LENGTH.getMedianValue() + Util.random.nextInt(HairLength.SEVEN_TO_FLOOR.getMinimumValue() - HairLength.THREE_SHOULDER_LENGTH.getMedianValue()));
			
		} else {
			body.getHair().setLength(genericOwner, HairLength.ONE_VERY_SHORT.getMedianValue() + Util.random.nextInt(HairLength.THREE_SHOULDER_LENGTH.getMinimumValue() - HairLength.ONE_VERY_SHORT.getMedianValue()));
		}
		
		// Penis:
		if(body.getPenis().getType()!=PenisType.NONE) {
			if(preferredGender==Gender.F_P_TRAP && Math.random()>=0.1f) { // Most traps have a small cock:
				body.getPenis().setPenisSize(genericOwner, PenisSize.ONE_TINY.getMedianValue() + Util.random.nextInt(4));
				body.getPenis().getTesticle().setTesticleSize(genericOwner, TesticleSize.ONE_TINY.getValue());
				body.getPenis().getTesticle().setCumProduction(genericOwner, CumProduction.ONE_TRICKLE.getMedianValue());
			} else {
				body.getPenis().setPenisSize(genericOwner,body.getPenis().getSize().getMinimumValue() + Util.random.nextInt(body.getPenis().getSize().getMaximumValue() - body.getPenis().getSize().getMinimumValue()) +1);
			}
		}
		
		// Vagina:
		if(body.getVagina().getType()!=VaginaType.NONE) {
			if(this.getAttributeValue(Attribute.CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(genericOwner, OrificeModifier.RIBBED);
				body.getVagina().getOrificeVagina().addOrificeModifier(genericOwner, OrificeModifier.MUSCLE_CONTROL);
			}
			if(this.getAttributeValue(Attribute.CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(genericOwner, OrificeModifier.TENTACLED);
			}
			
			body.getVagina().getOrificeVagina().setWetness(genericOwner, Wetness.THREE_WET.getValue() + Util.random.nextInt(4));
		}
		
		return body;
	}
	
	// Sex:
	
	public void endSex(boolean applyEffects) {
	}
	
	public boolean getSexBehaviourDeniesRequests() {
		return hasFetish(Fetish.FETISH_DOMINANT);
	}
	
	protected SexType foreplayPreference;
	protected SexType mainSexPreference;
	
	public SexType getForeplayPreference() {
		return foreplayPreference;
	}

	public void setForeplayPreference(SexType foreplayPreference) {
		this.foreplayPreference = foreplayPreference;
	}

	public SexType getMainSexPreference() {
		return mainSexPreference;
	}

	public void setMainSexPreference(SexType mainSexPreference) {
		this.mainSexPreference = mainSexPreference;
	}

	public void generateSexChoices() {
		List<SexType> foreplaySexTypes = new ArrayList<>();
		List<SexType> mainSexTypes = new ArrayList<>();
		
		NPC partner = this;
		
		// ************************ Populate possibilities from fetishes. These all have the same weighting, as all fetishes are equally important. ************************ //
		
		if(partner.hasFetish(Fetish.FETISH_BREASTS_OTHERS)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.BREAST));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.BREAST));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.NIPPLE));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.NIPPLE));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.BREAST));
			
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.BREAST));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TAIL, OrificeType.BREAST));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.NIPPLE));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TAIL, OrificeType.NIPPLE));
		}
		if(partner.hasFetish(Fetish.FETISH_BREASTS_SELF)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.BREAST));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.BREAST));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.NIPPLE));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.NIPPLE));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.BREAST));

			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.BREAST));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TAIL, OrificeType.BREAST));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TAIL, OrificeType.NIPPLE));
		}
		if(partner.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.ANUS));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.ANUS));
			
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TAIL, OrificeType.ANUS));
		}
		if(partner.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.ANUS));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.ANUS));

			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TAIL, OrificeType.ANUS));
		}
		if((partner.hasFetish(Fetish.FETISH_DEFLOWERING) && Main.game.getPlayer().isVaginaVirgin()) || partner.hasFetish(Fetish.FETISH_IMPREGNATION) || partner.hasFetish(Fetish.FETISH_SEEDER)) {
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA));
		}
		if(partner.hasFetish(Fetish.FETISH_PREGNANCY) || partner.hasFetish(Fetish.FETISH_BROODMOTHER)) {
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA));
		}
		if(partner.hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH));
		}
		if(partner.hasFetish(Fetish.FETISH_ORAL_GIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH));
		}
		
		// ************************ This section deals with the possibilities that no fetish-related SexTypes were chosen ************************ //
		
		// If no preferences from fetishes, add all common foreplay actions:
		if(foreplaySexTypes.isEmpty()) {
			// Player penetrates:
			List<PenetrationType> penTypes = Util.newArrayListOfValues(
					new ListValue<>(PenetrationType.FINGER),
					new ListValue<>(PenetrationType.TONGUE));

			List<OrificeType> orificeTypes = Util.newArrayListOfValues(
					new ListValue<>(OrificeType.BREAST),
					new ListValue<>(OrificeType.NIPPLE),
					new ListValue<>(OrificeType.VAGINA));
			
			for(PenetrationType pen : penTypes) {
				for(OrificeType orifice : orificeTypes) {
					foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, pen, orifice));
					foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, pen, orifice));
				}
			}
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH));
			
		}
		// If no preferences from fetishes, add all common sex actions:
		if(mainSexTypes.isEmpty()) { //TODO make a better weighting method, rather than just adding multiple entries
			// Player penetrates:
			List<PenetrationType> penTypes = Util.newArrayListOfValues(
					new ListValue<>(PenetrationType.PENIS),
					new ListValue<>(PenetrationType.PENIS),
					new ListValue<>(PenetrationType.PENIS),
					new ListValue<>(PenetrationType.TAIL));

			List<OrificeType> orificeTypes = Util.newArrayListOfValues(
					new ListValue<>(OrificeType.BREAST),
					new ListValue<>(OrificeType.VAGINA),
					new ListValue<>(OrificeType.VAGINA),
					new ListValue<>(OrificeType.VAGINA));
			
			for(PenetrationType pen : penTypes) {
				for(OrificeType orifice : orificeTypes) {
					mainSexTypes.add(new SexType(SexParticipantType.CATCHER, pen, orifice));
					mainSexTypes.add(new SexType(SexParticipantType.PITCHER, pen, orifice));
				}
			}
			
		}

		// ************************ Remove SexTypes that are physically impossible to perform, or that are not wanted by the NPC. ************************ //
		
		// Penis:
		if(!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS);
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS);
		}
		if(!partner.hasPenis() || !partner.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS);
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS);
		}
		// Vagina:
		if(!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA);
		}
		if(partner.hasFetish(Fetish.FETISH_PURE_VIRGIN) || !partner.hasVagina() || !partner.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA);
		}
		// Anus:
		if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS);
		}
		if(!partner.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS);
		}
		// Oral:
		if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH);
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH);
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE);
		}
		if(!partner.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH);
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH);
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE);
		}
		// Breasts:
		if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true) || (!Main.game.getPlayer().hasBreasts() && !Main.game.getPlayer().isBreastFuckableNipplePenetration())) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE);
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST);
		}
		if(!partner.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true) || (!partner.hasBreasts() && !partner.isBreastFuckableNipplePenetration())) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE);
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE);
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST);
		}
		// Tail:
		if(Main.game.getPlayer().getTailType() == TailType.NONE || (!Main.game.getPlayer().getTailType().isSuitableForPenetration() && !Main.getProperties().furryTailPenetrationContent)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL);
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL);
		}
		if(partner.getTailType() == TailType.NONE || (!partner.getTailType().isSuitableForPenetration() && !Main.getProperties().furryTailPenetrationContent)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL);
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL);
		}
		
		
		// ************************ Finally, set preferences from the resulting lists. ************************ //
		
		if(foreplaySexTypes.isEmpty()) {
			foreplayPreference = null;
		} else {
			foreplayPreference = foreplaySexTypes.get(Util.random.nextInt(foreplaySexTypes.size()));
//			System.out.println("Foreplay: "+foreplayPreference.getPenetrationType().toString()+" "+foreplayPreference.getOrificeType().toString());
		}
		
		if(mainSexTypes.isEmpty()) {
			mainSexPreference = null;
		} else {
			mainSexPreference = mainSexTypes.get(Util.random.nextInt(mainSexTypes.size()));
//			System.out.println("Main: "+mainSexPreference.getPenetrationType().toString()+" "+mainSexPreference.getOrificeType().toString());
		}
	}
	
	public Set<SexPositionSlot> getSexPositionPreferences() {
		sexPositionPreferences.clear();
		
		if(Sex.isInForeplay()) {
			if(foreplayPreference!=null) {
				if(foreplayPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH))
						|| foreplayPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA))) {
					sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
					
				} else if(foreplayPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH))
						|| foreplayPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA))){
					sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
					sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND_ORAL);
					
				} else if(foreplayPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.ANUS))){
					sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND_ORAL);
					
				} else if(foreplayPreference.getOrificeType()==OrificeType.ANUS){
					sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
					
				} else if(foreplayPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.BREAST))
						|| foreplayPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.NIPPLE))) {
					sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
					
				} else if(foreplayPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.BREAST))
						|| foreplayPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE))) {
					sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
					
				}
				
			} 
			if(sexPositionPreferences.isEmpty()){ // If no preferences found, add 'standard' positions:
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
			}
			
		} else {
			if(mainSexPreference!=null) {
				if(mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH))
						|| mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA))) {
					sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
					sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.MOUTH))
						|| mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA))){
					sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
					sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
					sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND_ORAL);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS))) {
					sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
					sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA))) {
					sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
					sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
					sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS))) {
					sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
					sexPositionPreferences.add(SexPositionSlot.DOGGY_ON_ALL_FOURS);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA))) {
					sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
					sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
					sexPositionPreferences.add(SexPositionSlot.DOGGY_ON_ALL_FOURS);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.BREAST))
						|| mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.NIPPLE))) {
					sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
					
				} else if(mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.BREAST))
						|| mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.NIPPLE))) {
					sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
				}
				
			}
			if(sexPositionPreferences.isEmpty()){ // If no preferences found, add 'standard' positions:
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
			}
		}
		
		return sexPositionPreferences;
	}
	
	
	public boolean isAttractedTo(GameCharacter character) {
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) { // If they're vulnerable to arcane storms, they will always be eager during a storm:
			return true;
		}
		
//		if(isSlave()) {
//			if(this.getAffection(character)<AffectionLevel.POSITIVE_ONE_FRIENDLY.getMinimumValue()) {
//				return false;
//			}
//		}
		
		if((getSexualOrientation()==SexualOrientation.ANDROPHILIC && character.isFeminine())
				|| (getSexualOrientation()==SexualOrientation.GYNEPHILIC && !character.isFeminine())
				) {
			return false;
		}
		
		if(motherId.equals(character.getId()) || fatherId.equals(character.getId())) {
			if (!hasFetish(Fetish.FETISH_INCEST)) {
				return false;
			}
		}
		
		return true;
	}

	public SexPace getSexPaceSubPreference(GameCharacter character){
		if(!isAttractedTo(character) || this.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
			if(Main.game.isNonConEnabled()) {
				if(isSlave()) {
					if(this.getObedienceValue()>=ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMinimumValue()) {
						return SexPace.SUB_EAGER;
						
					} else if(this.getObedienceValue()>=ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMinimumValue()) {
						return SexPace.SUB_NORMAL;
					}
				}
				
				if (getHistory() == History.PROSTITUTE) {
					if(Sex.isConsensual()) {
						return SexPace.SUB_NORMAL;
					}
				}
				
				return SexPace.SUB_RESISTING;
				
			} else {
				return SexPace.SUB_NORMAL;
				
			}
		}
		
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) { // If they're vulnerable to arcane storms, they will always be eager during a storm:
			return SexPace.SUB_EAGER;
		}
		
		if (hasFetish(Fetish.FETISH_SUBMISSIVE) // Subs like being sub I guess ^^
				|| (hasFetish(Fetish.FETISH_PREGNANCY) && character.hasPenis() && hasVagina()) // Want to get pregnant
				|| (hasFetish(Fetish.FETISH_IMPREGNATION) && character.hasVagina() && hasPenis()) // Want to impregnate player
				) {
			return SexPace.SUB_EAGER;
		}
		
		return SexPace.SUB_NORMAL;
	}
	
	public SexPace getSexPaceDomPreference(){
		if(hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)
				|| (hasFetish(Fetish.FETISH_SUBMISSIVE) && !hasFetish(Fetish.FETISH_DOMINANT)) // Subs like being sub I guess ^^
				) {
			return SexPace.DOM_GENTLE;
		}
		
		if(hasFetish(Fetish.FETISH_SADIST)
				|| hasFetish(Fetish.FETISH_DOMINANT)
				) {
			return SexPace.DOM_ROUGH;
		}
		
		return SexPace.DOM_NORMAL;
	}
	
	

	/**
	 * Returns a description of how this npc reacts to item usage.
	 * @param item
	 * @return
	 */
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()) {
			// Player uses item on themselves:
			if(target.isPlayer()) {
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			} else {
				if(target.isSlave()) {
					return Main.game.getPlayer().useItem(item, target, false);
					
				} else {
					return "<p>"
								+ "You try to give [npc.name] the "+item.getName()+", but [npc.she] refuses to take it. You put the "+item.getName()+" back in your inventory."
							+ "</p>";
					
				}
			}
			
		// NPC is using an item:
		} else {
			return this.useItem(item, target, false);
		}
	}
	
	private static StringBuilder infoScreenSB = new StringBuilder();
	
	public static String getCharacterInformationScreen(NPC character) {
		infoScreenSB.setLength(0);
		
		infoScreenSB.append(
				"<h4>Background</h4>"
				+ "<p>"
					+ character.getDescription()
				+ "</p>"
				+ "</br>"
				+ "<h4>Relationships</h4>"
				+ "<p>"
					+ "[style.boldAffection(Affection:)]</br>"
					+ AffectionLevel.getDescription(character, Main.game.getPlayer(),
							AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer())), true));
		
		for(Entry<String, Float> entry : character.getAffectionMap().entrySet()) {
			GameCharacter target = Main.game.getNPCById(entry.getKey());
			if(!target.isPlayer()) {
				infoScreenSB.append("</br>" + AffectionLevel.getDescription(character, target, AffectionLevel.getAffectionLevelFromValue(character.getAffection(target)), true));
			}
		}
		
		infoScreenSB.append("</br></br>"
					+ "[style.boldObedience(Obedience:)]</br>"
					+ UtilText.parse(character,
							(character.isSlave()
								?"[npc.Name] [style.boldArcane(is a slave)], owned by "+(character.getOwner().isPlayer()?"you!":character.getOwner().getName("a")+".")
								:"[npc.Name] [style.boldGood(is not a slave)]."))
					+ "</br>"+ObedienceLevel.getDescription(character, ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue()), true, true)
					+"</br></br>"
					+ "[style.boldArcane(Slaves owned:)]");
		
		if(character.getSlavesOwned().isEmpty()) {
			infoScreenSB.append("</br>[style.colourDisabled(None)]");
		} else {
			for(String id : character.getSlavesOwned()) {
				infoScreenSB.append(UtilText.parse(Main.game.getNPCById(id), "</br>[npc.Name]"));
			}
		}
		
		infoScreenSB.append("</p>"
				+ "</br>"
					+ "<h4>Appearance</h4>"
				+ "<p>"
					+ character.getBodyDescription()
				+ "</p>"
				+ getStats(character));
		
		return infoScreenSB.toString();
	}

	private static StringBuilder infoBoxSB = new StringBuilder();

	public static String getCharacterInfoBox(NPC character) {
		infoBoxSB.setLength(0);
		
		infoBoxSB.append("<div class='combat-display left' style='float:right;'>"

		// Display Name and level:
		+ "<div class='combat-inner-container'>"
				+ "<div class='combat-container name'>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title name' style='color:" + Femininity.valueOf(character.getFemininityValue()).getColour().toWebHexString() + ";'>" 
							+ "<b>" + Util.capitaliseSentence(character.getName()) + "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='combat-container'>"
						+ "<p class='combatant-title level'>"
							+ "<b>Level " + character.getLevel() +"</b>"
							+ "</br>"
							+(character.getRaceStage().getName()!=""
								?"<b style='color:"+character.getRaceStage().getColour().toWebHexString()+";'>" + Util.capitaliseSentence(character.getRaceStage().getName())+"</b> ":"")
							+ "<b style='color:"+character.getRace().getColour().toWebHexString()+";'>"
							+ (character.isFeminine()?Util.capitaliseSentence(character.getRace().getSingularFemaleName()):Util.capitaliseSentence(character.getRace().getSingularMaleName()))
							+ "</b>"
						+ "</p>"
					+ "</div>"
					+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_ATTRIBUTES'></div>" 
				+ "</div>"
				+ "</div>"

		// Attributes:
		+ "<div class='combat-inner-container'>"
			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + StrengthLevel.getStrengthLevelFromValue(character.getAttributeValue(Attribute.STRENGTH)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
					+ "<b style='color:" + Attribute.STRENGTH.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.STRENGTH) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STRENGTH + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + IntelligenceLevel.getIntelligenceLevelFromValue(character.getAttributeValue(Attribute.INTELLIGENCE)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.INTELLIGENCE.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.INTELLIGENCE) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.INTELLIGENCE + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + FitnessLevel.getFitnessLevelFromValue(character.getAttributeValue(Attribute.FITNESS)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.FITNESS.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.FITNESS) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.FITNESS + "'></div>"
			+ "</div>"

			+ "<div class='combat-container attribute'>"
				+ "<div class='combat-resource-icon'>" + CorruptionLevel.getCorruptionLevelFromValue(character.getAttributeValue(Attribute.CORRUPTION)).getRelatedStatusEffect().getSVGString(character) + "</div>"
				+ "<div class='combat-resource-number'>"
				+ "<b style='color:" + Attribute.CORRUPTION.getColour().toWebHexString() + ";'>" + (int) character.getAttributeValue(Attribute.CORRUPTION) + "</b>"
				+ "</div>"
				+ "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.CORRUPTION + "'></div>"
			+ "</div>"
		+ "</div>"

		// Display health, willpower and stamina:
		+ "<div class='combat-inner-container'>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.HEALTH_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((character.getHealth() / character.getAttributeValue(Attribute.HEALTH_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>"
				+ (int) Math.ceil(character.getHealth()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.HEALTH_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.MANA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((character.getMana() / character.getAttributeValue(Attribute.MANA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_MANA.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>" + (int) Math.ceil(character.getMana()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.MANA_MAXIMUM + "'></div>" + "</div>"

				+ "<div class='combat-resource'>" + "<div class='combat-resource-icon'>" + Attribute.STAMINA_MAXIMUM.getSVGString() + "</div>" + "<div class='combat-resource-bar'>" + "<div style='height:10px; width:"
				+ (int) ((character.getStamina() / character.getAttributeValue(Attribute.STAMINA_MAXIMUM)) * 100f) + "%;" + "background:" + Colour.ATTRIBUTE_FITNESS.toWebHexString() + "; border-radius: 2px;'></div>" + "</div>"
				+ "<div class='combat-resource-number'>"
				+ (int) Math.ceil(character.getStamina()) + "</div>" + "<div class='overlay no-pointer' id='COMBAT_OPPONENT_" + Attribute.STAMINA_MAXIMUM + "'></div>" + "</div>"

				+ "</div>"

		// Display status effects:
		+ "<div class='combat-inner-container status-effects'>");
		
		for (Perk p : character.getPerks()) {
			infoBoxSB.append("<div class='combat-status-effect'>" + p.getSVGString() + "<div class='overlay no-pointer' id='PERK_COMBAT_" + p + "'></div>" + "</div>");
		}
		for (Fetish f : character.getFetishes()) {
			infoBoxSB.append("<div class='combat-status-effect'>" + f.getSVGString() + "<div class='overlay no-pointer' id='FETISH_COMBAT_" + f + "'></div>" + "</div>");
		}
		for (StatusEffect se : character.getStatusEffects()) {
			if (se.renderInEffectsPanel()) {
				if (se.isCombatEffect()) {
					infoBoxSB.append("<div class='combat-status-effect" + (!se.isBeneficial() ? " negativeCombat" : " positiveCombat") + "'>"
								+ se.getSVGString(character) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
				} else {
					infoBoxSB.append(
							"<div class='combat-status-effect'>" + se.getSVGString(character) + "<div class='overlay no-pointer' id='SE_COMBAT_" + se + "'></div>" + "</div>");
				}
			}
		}
		for (SpecialAttack sa : character.getSpecialAttacks()) {
			infoBoxSB.append(
					"<div class='combat-status-effect'>" + sa.getSVGString() + "<div class='overlay no-pointer' id='SA_COMBAT_" + sa + "'></div>" + "</div>");
		}
		if (character.getMainWeapon() != null) {
			for (Spell s : character.getMainWeapon().getSpells()) {
				infoBoxSB
						.append("<div class='combat-status-effect'>" + s.getSVGString() + "<div class='overlay' id='SPELL_MAIN_COMBAT_" + s + "'></div>" + "</div>");
			}
		}
		if (character.getOffhandWeapon() != null) {
			for (Spell s : character.getOffhandWeapon().getSpells()) {
				infoBoxSB
						.append("<div class='combat-status-effect'>" + s.getSVGString() + "<div class='overlay' id='SPELL_OFFHAND_COMBAT_" + s + "'></div>" + "</div>");
			}
		}
		
		infoBoxSB.append("</div></div>");
			
		return infoBoxSB.toString();
	}
	
	public static String getStats(NPC character) {
		return "<h4 style='text-align:center;'>Stats</h4>"
				+"<table align='center'>"

				+ "<tr style='height:8px; color:"+character.getGender().getColour().toWebHexString()+";'><th>Core</th></tr>"
				+ statRow("Femininity", String.valueOf(character.getFemininityValue()))
				+ statRow("Height (cm)", String.valueOf(character.getHeightValue()))
				+ statRow("Hair length (inches)", String.valueOf(Util.conversionInchesToCentimetres(character.getHairRawLengthValue())))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Breasts</th></tr>"
				+ statRow("Cup size", character.getBreastRawSizeValue() == 0 ? "N/A" : Util.capitaliseSentence(character.getBreastSize().getCupSizeName()))
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.NIPPLES)
					?statRow("Milk production (mL)", String.valueOf(character.getBreastRawLactationValue()))
						+ statRow("Capacity (inches)", String.valueOf(character.getNippleRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getNippleElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getNippleElasticity().getDescriptor())+")")
					:statRow("Milk production (mL)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Capacity (inches)","<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Penis</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.PENIS)
					?statRow("Length (inches)", character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawSizeValue()))
						+ statRow("Ball size", character.getPenisType() == PenisType.NONE ? "N/A" : Util.capitaliseSentence(character.getTesticleSize().getDescriptor()))
						+ statRow("Cum production (mL)", character.getPenisType() == PenisType.NONE ? "N/A" : String.valueOf(character.getPenisRawCumProductionValue()))
					:statRow("Length (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Ball size", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Cum production (mL)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Vagina</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.VAGINA)
					?statRow("Capacity (inches)", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getVaginaElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getVaginaElasticity().getDescriptor())+")")
						+ statRow("Wetness", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaWetness().getValue()) +" ("+Util.capitaliseSentence(character.getVaginaWetness().getDescriptor())+")")
						+ statRow("Clit size (inches)", character.getVaginaType() == VaginaType.NONE ? "N/A" : String.valueOf(character.getVaginaRawClitorisSizeValue()))
					:statRow("Capacity (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Wetness", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Clit size (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "<tr style='height:8px;'></tr>"

				+ "<tr style='height:8px; color:"+Colour.TRANSFORMATION_SEXUAL.toWebHexString()+";'><th>Anus</th></tr>"
				+ (character.getPlayerKnowsAreasMap().get(CoverableArea.ANUS)
					?statRow("Capacity (inches)", String.valueOf(character.getAssRawCapacityValue()))
						+ statRow("Elasticity", String.valueOf(character.getAssElasticity().getValue()) + " ("+Util.capitaliseSentence(character.getAssElasticity().getDescriptor())+")")
						+ statRow("Wetness", String.valueOf(character.getAssWetness().getValue()) +" ("+Util.capitaliseSentence(character.getAssWetness().getDescriptor())+")")
					:statRow("Capacity (inches)", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Elasticity", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>")
						+ statRow("Wetness", "<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Undiscovered</span>"))
				+ "</table>";
	}
	
	private static String statRow(String title, String value) {
		return "<tr>"
					+ "<td style='min-width:100px;'>"
						+ "<b>"+title+"</b>"
					+ "</td>"
					+ "<td style='min-width:100px;'>"
						+ "<b>"+value+"</b>"
					+ "</td>"
				+ "</tr>";
	}
	
	
}
