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

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.History;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
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
import com.lilithsthrone.game.settings.ForcedTFTendency;
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
 * @version 0.2.2
 * @author Innoxia
 */
public abstract class NPC extends GameCharacter implements XMLSaving {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_TIME_START_VALUE = -1;
	
	protected long lastTimeEncountered = DEFAULT_TIME_START_VALUE;
	protected long lastTimeHadSex = DEFAULT_TIME_START_VALUE;
	protected long lastTimeOrgasmed = DEFAULT_TIME_START_VALUE;
	
	protected float buyModifier;
	protected float sellModifier;
	
	protected boolean addedToContacts;
	
	public Set<NPCFlagValue> NPCFlagValues;
	
	protected Set<SexPositionSlot> sexPositionPreferences;
	
	protected Body bodyPreference = null;
	
	protected Value<String, AbstractItem> heldTransformativePotion = null;
	
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
					getPlayerKnowsAreas().add(ca);
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
	
	/**
	 * Applies an hourly update to this NPC.
	 */
	public void hourlyUpdate() {
	}
	
	/**
	 * Applies an update to this NPC every time the game makes a turn.
	 */
	public void turnUpdate() {
	}
	
	public abstract void changeFurryLevel();
	
	public abstract DialogueNodeOld getEncounterDialogue();
	
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		CharacterUtils.equipClothing(this, replaceUnsuitableClothing, onlyAddCoreClothing);
	}
	
	public boolean isClothingStealable() {
		return false;
	}
	
	public String getPresentInTileDescription() {
		StringBuilder tileSB = new StringBuilder();
		
		tileSB.append(
				UtilText.parse(this,
						"<p style='text-align:center;'>"
						+ "<b style='color:"+Femininity.valueOf(this.getFemininityValue()).getColour().toWebHexString()+";'>[npc.A_femininity]</b>"
						+ " <b style='color:"+this.getRaceStage().getColour().toWebHexString()+";'>[npc.raceStage]</b>"
						+ " <b style='color:"+this.getRace().getColour().toWebHexString()+";'>[npc.race]</b> <b>is prowling this area!</b></p>"
						
						+ "<p style='text-align:center;'>"));
				
		// Combat:
		if(this.getFoughtPlayerCount()>0) {
			tileSB.append(
					UtilText.parse(this,"You have <b style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+";'>fought</b> [npc.herHim] <b>"));
					
					if(this.getFoughtPlayerCount()==1) {
						tileSB.append("once.");
					} else if(this.getFoughtPlayerCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getFoughtPlayerCount())+" times.");
					}
					
			tileSB.append("</b>"
							+ "</br>"
							+ "You have <b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>won</b> <b>");
					
					if(this.getLostCombatCount()==1) {
						tileSB.append("once.");
					} else if(this.getLostCombatCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getLostCombatCount())+" times.");
					}
							
			tileSB.append("</b>"
					+ "</br>"
					+ "You have <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>lost</b> <b>");
					if(this.getWonCombatCount()==1) {
						tileSB.append("once.");
					} else if(this.getWonCombatCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getWonCombatCount())+" times.");
					}
					tileSB.append("</b></p>");
		}
		
		// Sex:
		if(this.getSexPartners().containsKey(Main.game.getPlayer().getId())) {
			tileSB.append("<p style='text-align:center;'>");
					
			tileSB.append(
					UtilText.parse(this,
							"You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submissive sex</b> with [npc.herHim]<b> "));
			
					if(this.getSexAsDomCount()==1) {
						tileSB.append("once.");
					} else if(this.getSexAsDomCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getSexAsDomCount())+" times.");
					}
					
			tileSB.append(
					UtilText.parse(this,
							"</b>"
							+ "</br>"
							+ "You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>dominant sex</b> with  [npc.herHim]<b> "));
			
					if(this.getSexAsSubCount()==1) {
						tileSB.append("once.");
					} else if(this.getSexAsSubCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getSexAsSubCount())+" times.");
					}
					tileSB.append("</b></p>");
		}
		
		return tileSB.toString();
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
		return Math.max(1.0f, (sellModifier * (Main.game.getPlayer().hasTrait(Perk.JOB_STUDENT, true)?0.75f:1)));
	}

	public void setSellModifier(float sellModifier) {
		this.sellModifier = sellModifier;
	}

	// Combat:
	
	public List<Spell> getSpellsAbleToCast() {
		List<Spell> spellsAbleToCast = new ArrayList<>();
		
		for(Spell spell : this.getAllSpells()) {
			if(this.getMana()>spell.getModifiedCost(this)) {
				spellsAbleToCast.add(spell);
			}
		}
		
		return spellsAbleToCast;
	}
	
	public List<SpecialAttack> getSpecialAttacksAbleToUse() {
		List<SpecialAttack> specialAttacksAbleToUse = new ArrayList<>();
		
		for(SpecialAttack sa : this.getSpecialAttacks()) {
			if(Main.game.isInCombat()) {
				if(Combat.getCooldown(this, sa)==0) {
					specialAttacksAbleToUse.add(sa);
				}
			} else {
				specialAttacksAbleToUse.add(sa);
			}
		}
		
		return specialAttacksAbleToUse;
	}
	
	public Attack attackType() {
		boolean canCastASpell = !getSpellsAbleToCast().isEmpty();
		boolean canCastASpecialAttack = !getSpecialAttacksAbleToUse().isEmpty();
		
		Map<Attack, Integer> attackWeightingMap = new HashMap<>();
		
		attackWeightingMap.put(Attack.MAIN, this.getRace().getPreferredAttacks().contains(Attack.MAIN)?75:50);
		attackWeightingMap.put(Attack.OFFHAND, this.getOffhandWeapon()==null?0:(this.getRace().getPreferredAttacks().contains(Attack.MAIN)?50:25));
		attackWeightingMap.put(Attack.SEDUCTION, this.getRace().getPreferredAttacks().contains(Attack.SEDUCTION)?100:(int)this.getAttributeValue(Attribute.MAJOR_CORRUPTION));
		attackWeightingMap.put(Attack.SPELL, !canCastASpell?0:(this.getRace().getPreferredAttacks().contains(Attack.MAIN)?100:50));
		attackWeightingMap.put(Attack.SPECIAL_ATTACK, !canCastASpecialAttack?0:(this.getRace().getPreferredAttacks().contains(Attack.MAIN)?100:50));
		
		int total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
		}
		
		int index = Util.random.nextInt(total);
		total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
			if(index<total) {
				return entry.getKey();
			}
		}
		
		return Attack.MAIN;
	}

	public String getCombatDescription() {
		return UtilText.parse(this, "[npc.Name] is ready for a fight!");
	}
	
	public Response endCombat(boolean applyEffects, boolean playerVictory) {
		return null;
	};

	public int getEscapeChance() {
		return 30;
	}

	public boolean isSurrendersAtZeroMana() {
		return true;
	}

	// Post-combat:

	public int getExperienceFromVictory() {
		return getLevel() * 2;
	}

	public int getLootMoney() {
		return (int) ((getLevel() * 25) * (1 + Math.random() - 0.5f));
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
				case SLIME:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_SLIME_QUENCHER)));
				case ANGEL:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
				case DEMON:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.COR_INGREDIENT_LILITHS_GIFT)));
				case IMP:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.COR_INGREDIENT_IMPISH_BREW)));
				case HARPY:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_HARPY_PERFUME)));
				case ALLIGATOR_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.STR_INGREDIENT_SWAMP_WATER)));
				case SQUIRREL_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.FIT_INGREDIENT_SQUIRREL_JAVA)));
				case BAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.INT_INGREDIENT_FRUIT_BAT_SQUASH)));
				case RAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.STR_INGREDIENT_BLACK_RATS_RUM)));
				case RABBIT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_BUNNY_JUICE)));
				case ELEMENTAL_AIR:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.AIR))));
				case ELEMENTAL_ARCANE:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.ARCANE))));
				case ELEMENTAL_EARTH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.EARTH))));
				case ELEMENTAL_FIRE:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.FIRE))));
				case ELEMENTAL_WATER:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.WATER))));
			}
			
		} else if(rnd <= 0.8 && !Main.game.getPlayer().getRacesDiscoveredFromBook().contains(getRace())) {
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
				case SLIME:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_SLIME)));
				case ANGEL:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
				case DEMON:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_DEMON)));
				case IMP:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_IMP)));
				case HARPY:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_HARPY)));
				case ALLIGATOR_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_ALLIGATOR_MORPH)));
				case SQUIRREL_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_SQUIRREL_MORPH)));
				case BAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_BAT_MORPH)));
				case RAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_RAT_MORPH)));
				case RABBIT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.BOOK_RABBIT_MORPH)));
				case ELEMENTAL_AIR: //TODO books
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.AIR))));
				case ELEMENTAL_ARCANE:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.ARCANE))));
				case ELEMENTAL_EARTH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.EARTH))));
				case ELEMENTAL_FIRE:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.FIRE))));
				case ELEMENTAL_WATER:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.WATER))));
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
				case SLIME:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.SEX_INGREDIENT_SLIME_QUENCHER)));
				case ANGEL:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HUMAN)));
				case DEMON: case IMP:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_DEMON)));
				case HARPY:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HARPY)));
				case ALLIGATOR_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH)));
				case SQUIRREL_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_SQUIRREL_MORPH)));
				case BAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_BAT_MORPH)));
				case RAT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_RAT_MORPH)));
				case RABBIT_MORPH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_RABBIT_MORPH)));
				case ELEMENTAL_AIR:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.AIR))));
				case ELEMENTAL_ARCANE:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.ARCANE))));
				case ELEMENTAL_EARTH:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.EARTH))));
				case ELEMENTAL_FIRE:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.FIRE))));
				case ELEMENTAL_WATER:
					return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.idToItemMap.get("SPELL_SCROLL_"+SpellSchool.WATER))));
			}
		}
		
		return Util.newArrayListOfValues(new ListValue<>(AbstractItemType.generateItem(ItemType.DYE_BRUSH)));
	}
	
	public Map<TFEssence, Integer> getLootEssenceDrops() {
		return Util.newHashMapOfValues(new Value<>(TFEssence.ARCANE, Util.random.nextInt(this.getLevel())+1));
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

	public boolean hasFlag(NPCFlagValue flag) {
		return NPCFlagValues.contains(flag);
	}
	
	public boolean addFlag(NPCFlagValue flag) {
		return NPCFlagValues.add(flag);
	}
	
	public boolean removeFlag(NPCFlagValue flag) {
		return NPCFlagValues.remove(flag);
	}
	
	public boolean setFlag(NPCFlagValue flag, boolean value) {
		if(value) {
			return addFlag(flag);
		} else {
			return removeFlag(flag);
		}
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

	public boolean isAddedToContacts() {
		return addedToContacts;
	}
	
	public String getPreferredBodyDescription(String tag) {
		return "<"+tag+" style='color:"+getPreferredBody().getGender().getColour().toWebHexString()+";'>"+getPreferredBody().getGender().getName()+"</"+tag+">"
				+ " <"+tag+" style='color:"+getPreferredBody().getRace().getColour().toWebHexString()+";'>"+getPreferredBody().getRace().getName()+"</"+tag+">";
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
	
	
	public Value<String, AbstractItem> getTransfomativePotion( ) {
		
		return getTransfomativePotion(false);
	}
	
	public Value<String, AbstractItem> getTransfomativePotion(boolean generateNew ) {
		
		if(generateNew) {
			this.heldTransformativePotion = null;
			
			if(hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING) && hasFetish(Fetish.FETISH_KINK_GIVING)) {
				int randNum = Util.random.nextInt(100);
				Boolean pairedFetishAvailable = generateFetishPotion(true) == null ? false : true;
				
				// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check the results of potion selection:
//				System.out.println("Random Both Fetishes"); 
//				System.out.println(randNum); 
//				System.out.println(pairedFetishAvailable); 
				
				// If there's a paired fetish to use, bigger chance of fetish adding, otherwise better chance of TF since there's far more interesting variation to be had from TFs that forced fetishes:
				if(pairedFetishAvailable && randNum < 60 ) {
					this.heldTransformativePotion = generateFetishPotion(true);
					
				} else if ( randNum < 25) {
					this.heldTransformativePotion = generateFetishPotion(false);
					
				} else {
					this.heldTransformativePotion = generateTransformativePotion();
				}
				
			} else if(hasFetish(Fetish.FETISH_KINK_GIVING)) {
				
				int randNum = Util.random.nextInt(100);
				Boolean pairedFetishAvailable = generateFetishPotion(true) == null ? false : true;
				
				// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check the results of potion selection:
//				System.out.println("Random Fetish Only"); 
//				System.out.println(randNum); 
//				System.out.println(pairedFetishAvailable); 
				
				// If there's a paired fetish to use, large chance to just choose from paired pool:
				if(pairedFetishAvailable && randNum < 80 ) {
					this.heldTransformativePotion = generateFetishPotion(true);
					
				} else {
					this.heldTransformativePotion = generateFetishPotion(false);
				}
				
			} else if(hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) {
				this.heldTransformativePotion = generateTransformativePotion();
			}
		}
		
		return this.heldTransformativePotion;
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
				raceName = getPreferredBody().getGender().getName() + " " + getPreferredBody().getSubspecies().getSingularFemaleName();
			} else {
				raceName = getPreferredBody().getGender().getName() + " " + getPreferredBody().getSubspecies().getSingularMaleName();
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
				case RAT_MORPH:
					itemType = ItemType.RACE_INGREDIENT_RAT_MORPH;
					break;
				case BAT_MORPH:
					itemType = ItemType.RACE_INGREDIENT_BAT_MORPH;
					break;
				case RABBIT_MORPH:
					itemType = ItemType.RACE_INGREDIENT_RABBIT_MORPH;
					break;
				case ANGEL:
				case DEMON:
				case IMP:
				case HUMAN:
				case SLIME:
				case ELEMENTAL_AIR:
				case ELEMENTAL_ARCANE:
				case ELEMENTAL_EARTH:
				case ELEMENTAL_FIRE:
				case ELEMENTAL_WATER:
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
		
		// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check
		// the results of gender selection and the forcedTFTendency setting
//		System.out.println("PREFERRED GENDER");
//		System.out.println(preferredGender);
//		System.out.println(desiredGenders);
		
		// Preferred race:
		
		Subspecies species = getSubspecies();
		RaceStage stage = getRaceStage();
		
		if(Main.getProperties().forcedTFPreference==FurryPreference.HUMAN) {
			species = Subspecies.HUMAN;
			stage = RaceStage.HUMAN;
			
		} else {
		
			// Chance for predator races to prefer prey races:
			if(getRace()==Race.CAT_MORPH && Math.random()>0.8f) {
				species = Subspecies.HARPY;
			}
			if((getRace()==Race.WOLF_MORPH || getRace()==Race.DOG_MORPH) && Math.random()>0.8f) {
				List<Subspecies> availableRaces = new ArrayList<>();
				availableRaces.add(Subspecies.CAT_MORPH);
				availableRaces.add(Subspecies.HARPY);
				availableRaces.add(Subspecies.COW_MORPH);
				availableRaces.add(Subspecies.SQUIRREL_MORPH);
				species = availableRaces.get(Util.random.nextInt(availableRaces.size()));
			}
			
			// Chance for race to be random:
			if(Math.random()>0.85f) {
				List<Subspecies> availableRaces = new ArrayList<>();
				availableRaces.add(Subspecies.CAT_MORPH);
				availableRaces.add(Subspecies.DOG_MORPH);
				availableRaces.add(Subspecies.HARPY);
				availableRaces.add(Subspecies.HORSE_MORPH);
				availableRaces.add(Subspecies.HUMAN);
				availableRaces.add(Subspecies.SQUIRREL_MORPH);
				availableRaces.add(Subspecies.COW_MORPH);
				availableRaces.add(Subspecies.WOLF_MORPH);
				species = availableRaces.get(Util.random.nextInt(availableRaces.size()));
			}
			
			// Preferred race stage:
			
			if(preferredGender.isFeminine()) {
				switch(Main.getProperties().subspeciesFeminineFurryPreferencesMap.get(species)) {
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
				switch(Main.getProperties().subspeciesMasculineFurryPreferencesMap.get(species)) {
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
		
		Body body = CharacterUtils.generateBody(preferredGender, species, stage);
		
		// Apply fetish modifiers:
		
		GameCharacter genericOwner = Main.game.getGenericAndrogynousNPC();
		
		//Ass:
		if(hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.RIBBED);
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.MUSCLE_CONTROL);
				body.getAss().getAnus().getOrificeAnus().addOrificeModifier(genericOwner, OrificeModifier.PUFFY);
			}
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
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
			
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.RIBBED);
				body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.MUSCLE_CONTROL);
			}
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
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
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.THREE_DIRTY.getMinimumValue()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(genericOwner, OrificeModifier.RIBBED);
				body.getVagina().getOrificeVagina().addOrificeModifier(genericOwner, OrificeModifier.MUSCLE_CONTROL);
			}
			if(this.getAttributeValue(Attribute.MAJOR_CORRUPTION) >= CorruptionLevel.FOUR_LUSTFUL.getMinimumValue()) {
				body.getVagina().getOrificeVagina().addOrificeModifier(genericOwner, OrificeModifier.TENTACLED);
			}
			
			body.getVagina().getOrificeVagina().setWetness(genericOwner, Wetness.THREE_WET.getValue() + Util.random.nextInt(4));
		}
		
		return body;
	}
	
	
	
	/**
	 * Example return value: ["Let's see if you don't enjoy sucking my dick after this!", AbstractItem]
	 * @return NPC's speech as a reaction to giving you this potion, along with the potion itself.
	 */
	public Value<String, AbstractItem> generateFetishPotion(Boolean pairedFetishesOnly) {
		
		ItemEffect selectedEffect = null; // this will be the ultimately selected effect, or null if none available
		String selectedEffectString ; // this will be a flavor text string paired with the effect
		
		Map<ItemEffect, Integer> possibleEffects = new HashMap<>();
		
		AbstractItemType itemType = ItemType.FETISH_UNREFINED;
		
		Fetish currentTopFetish = null, currentBottomFetish = null;
		TFModifier currentTopModifier = null, currentBottomModifier = null;
		TFPotency currentTopPotency = null, currentBottomPotency = null, currentTopRemovePotency = null, currentBottomRemovePotency = null;;
		
		int baseTopChance = 5, baseBottomChance = 5,  baseTopRemoveChance = 0, baseBottomRemoveChance = 0; 
		int currentTopChance = 0, currentBottomChance = 0, currentTopRemoveChance = 0, currentBottomRemoveChance = 0;
		
		int pairedFetishMultiplier = 5;  
		int matchedFetishDecrement = 8;  // heavy tendency can still allow small chance giving a matched fetish, otherwise no chance at all
		int matchedFetishRemoveIncrement = 1;  // only a modest increase in chances to matched fetish
		
		int desiredFetishIncrement = 2;  // for now, keeping it simple, only modifying add chances based on desires, one increment (or decrement) per level
		int expFetishIncrement = 1;  // for now, keeping it simple, only modifying add chances based on exp, one increment per level
		
		switch(Main.getProperties().forcedFetishTendency) {
			case NEUTRAL:
				baseTopChance = 5;
				baseBottomChance = 5;
				baseTopRemoveChance = 2;
				baseBottomRemoveChance = 2;
				break;
		
			case BOTTOM:
				baseTopChance = 1;
				baseBottomChance = 8;
				baseTopRemoveChance = 3;
				baseBottomRemoveChance = 0;
				break;
			
			case BOTTOM_HEAVY:
				baseTopChance = -2;
				baseBottomChance = 10;
				baseTopRemoveChance = 4;
				baseBottomRemoveChance = -1;
				break;
			
			case TOP:
				baseTopChance = 8;
				baseBottomChance = 1;
				baseTopRemoveChance = 0;
				baseBottomRemoveChance = 3;
				break;
			
			case TOP_HEAVY:
				baseTopChance = 10;
				baseBottomChance = -2;
				baseTopRemoveChance = -1;
				baseBottomRemoveChance = 4;
				break;
		
		}
		
		
		// map of top -> bottom paired fetishes; NPCs with a paired fetish will greatly favor 
		// giving the player it's pair, and remove that fetish if there is a match
		Map<Fetish, Fetish> pairedFetishMap = new HashMap<>();

		pairedFetishMap.put(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING);
		pairedFetishMap.put(Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING);
		pairedFetishMap.put(Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF);
		pairedFetishMap.put(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING);
		pairedFetishMap.put(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER);
		
		pairedFetishMap.put(Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE);
		pairedFetishMap.put(Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT);
		pairedFetishMap.put(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN);
		pairedFetishMap.put(Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY);
		pairedFetishMap.put(Fetish.FETISH_SEEDER, Fetish.FETISH_BROODMOTHER);
		pairedFetishMap.put(Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST);
		pairedFetishMap.put(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB);
		pairedFetishMap.put(Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF);
		pairedFetishMap.put(Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST);
		
		// in a request for paired fetishes only, let's leave these out, otherwise they'll get selected 
		// way too often, since our NPCs will almost always have them -- possibly, they should be left
		// out of the list entirely, but for now let's have them in there
		if(!pairedFetishesOnly) {
			pairedFetishMap.put(Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING);
			pairedFetishMap.put(Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING);
		}
		
		
		
		for(Entry<Fetish, Fetish> entry : pairedFetishMap.entrySet()) {
			currentTopFetish = entry.getKey();
			currentBottomFetish = entry.getValue();
			
			currentTopModifier = TFModifier.valueOf( "TF_MOD_" + currentTopFetish);
			currentBottomModifier = TFModifier.valueOf( "TF_MOD_" + currentBottomFetish);
			
			currentTopPotency = TFPotency.MINOR_BOOST;
			currentBottomPotency = TFPotency.MINOR_BOOST;
			currentTopRemovePotency = TFPotency.MINOR_DRAIN;
			currentBottomRemovePotency = TFPotency.MINOR_DRAIN;
			
			currentTopChance = baseTopChance;
			currentBottomChance = baseBottomChance;
			currentTopRemoveChance = baseTopRemoveChance;
			currentBottomRemoveChance = baseBottomRemoveChance;
			
			// Increase base add chances based on NPC's desire levels for these fetishes
			switch(this.getFetishDesire(currentBottomFetish)) {
				case THREE_LIKE:
					currentTopChance += desiredFetishIncrement;
					break;
					
				case FOUR_LOVE:
					currentTopChance += desiredFetishIncrement * 2;
					break;
					
				case ONE_DISLIKE:
					currentTopChance -= desiredFetishIncrement;
					break;
					
				case ZERO_HATE:
					currentTopChance = 0;
					break;
					
				default:
			}
			
			switch(this.getFetishDesire(currentTopFetish)) {
				case THREE_LIKE:
					currentBottomChance += desiredFetishIncrement;
					break;
					
				case FOUR_LOVE:
					currentBottomChance += desiredFetishIncrement * 2;
					break;
					
				case ONE_DISLIKE:
					currentBottomChance -= desiredFetishIncrement;
					break;
					
				case ZERO_HATE:
					currentBottomChance = 0;
					break;
					
				default:
			}
			
			// Increase base add chances based on NPC's experience levels for these fetishes
			switch(this.getFetishLevel(currentBottomFetish)) {
				case ONE_AMATEUR:
					currentTopChance += expFetishIncrement;
					break;
				case TWO_EXPERIENCED:
					currentTopChance += expFetishIncrement * 2;
					break;
					
				case THREE_EXPERT:
					currentTopChance += expFetishIncrement * 3;
					break;
					
				case FOUR_MASTERFUL:
					currentTopChance += expFetishIncrement * 4;
					break;
					
				default:
			}
			
			switch(this.getFetishLevel(currentTopFetish)) {
				case ONE_AMATEUR:
					currentBottomChance += expFetishIncrement;
					break;
				case TWO_EXPERIENCED:
					currentBottomChance += expFetishIncrement * 2;
					break;
				
				case THREE_EXPERT:
					currentBottomChance += expFetishIncrement * 3;
					break;
				
				case FOUR_MASTERFUL:
					currentBottomChance += expFetishIncrement * 4;
					break;
					
				default:
			}
			
			
			// set chances if NPC has top fetish
			if(this.hasFetish(currentTopFetish)) {
				currentBottomChance *= pairedFetishMultiplier;
				currentTopChance -= matchedFetishDecrement;
				currentBottomRemoveChance = 0;
				if(!pairedFetishesOnly) {
					currentTopRemoveChance += matchedFetishRemoveIncrement;
				}
			}
			else if(pairedFetishesOnly) {
				currentBottomChance = 0;
				// in paired only mode, we're only adding fetishes
				currentTopRemoveChance = 0;
				currentBottomRemoveChance = 0;
			}
			
			// set chances if NPC has bottom fetish
			if(this.hasFetish(currentBottomFetish)) {
				currentTopChance *= pairedFetishMultiplier;
				currentBottomChance -= matchedFetishDecrement;
				currentTopRemoveChance = 0;
				if(!pairedFetishesOnly) {
					currentBottomRemoveChance += matchedFetishRemoveIncrement;
				}
			}
			else if(pairedFetishesOnly) {
				currentTopChance = 0;
				// in paired only mode, we're only adding fetishes
				currentTopRemoveChance = 0;
				currentBottomRemoveChance = 0;
			}
				
			
			
			// if player has positive bottom fetish desire, adjust potency level to fully add fetish, not just desire
			if( Main.game.getPlayer().getFetishDesire(currentBottomFetish) == FetishDesire.THREE_LIKE ||
					Main.game.getPlayer().getFetishDesire(currentBottomFetish) == FetishDesire.FOUR_LOVE) {
				currentBottomPotency = TFPotency.BOOST;
			} 
			else if( Main.game.getPlayer().getFetishDesire(currentBottomFetish) == FetishDesire.TWO_NEUTRAL) {
				int rand = Util.random.nextInt(100);
				
				// if the player is neutral, but the NPC has fetish,small chance to fully add rather than just boost desire
				if(this.hasFetish(currentTopFetish) && rand < 30) {
					currentBottomPotency = TFPotency.BOOST;
				}
			} 
			else {
				// if they are already less than neutral, don't remove any more
				currentBottomRemoveChance = 0;
			}
			
			
			// prevent extraneous effects if player has bottom fetish, plus alter remove potency to drop fetish, not just desire
			if(Main.game.getPlayer().hasFetish(currentBottomFetish)) {
				currentBottomChance = 0;
				currentBottomRemovePotency = TFPotency.DRAIN;
			} 
			
			
			// if player has positive top fetish desire, adjust potency level to fully add fetish, not just desire
			if( Main.game.getPlayer().getFetishDesire(currentTopFetish) == FetishDesire.THREE_LIKE ||
				Main.game.getPlayer().getFetishDesire(currentTopFetish) == FetishDesire.FOUR_LOVE) {
				currentTopPotency = TFPotency.BOOST;
			}
			else if( Main.game.getPlayer().getFetishDesire(currentTopFetish) == FetishDesire.TWO_NEUTRAL) {
				int rand = Util.random.nextInt(100);
				
				// if the player is neutral, but the NPC has paired fetish,small chance to fully add rather than just boost desire
				if(this.hasFetish(currentBottomFetish) && rand < 30) {
					currentTopPotency = TFPotency.BOOST;
				}
			} 
			else {
				// if they are already less than neutral, don't remove any more
				currentTopRemoveChance = 0;
			}
			
			
			// prevent extraneous effects if player has top fetish, plus alter remove potency to drop fetish, not just desire
			if(Main.game.getPlayer().hasFetish(currentTopFetish)) {
				currentTopChance = 0;
				currentTopRemovePotency = TFPotency.DRAIN;
			} 
			
			
			// some settings and status combinations can create negative values, so let's zero those out
			if(currentTopChance < 0) { currentTopChance = 0 ;}
			if(currentBottomChance < 0) { currentBottomChance = 0 ;}
			if(currentTopRemoveChance < 0) { currentTopRemoveChance = 0 ;}
			if(currentBottomRemoveChance < 0) { currentBottomRemoveChance = 0 ;}
			
			if(currentTopChance > 0) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), 
						TFModifier.NONE, 
						currentTopModifier, 
						currentTopPotency, 
						1), 
						currentTopChance);
			}
			
			if(currentTopRemoveChance > 0) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), 
						TFModifier.NONE, 
						currentTopModifier, 
						currentTopRemovePotency, 
						1), 
						currentTopRemoveChance);
			}
			
			if(currentBottomChance > 0) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), 
						TFModifier.NONE, 
						currentBottomModifier, 
						currentBottomPotency, 
						1), 
						currentBottomChance);
			}
			
			if(currentBottomRemoveChance > 0) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), 
						TFModifier.NONE, 
						currentBottomModifier, 
						currentBottomRemovePotency, 
						1), 
						currentBottomRemoveChance);
			}
		}
		
		
		// map of unpaired fetish -> boolean stating whether it wants to be shared, or hoarded
		// currently, all unpaired fetishes seem like they are something the owner would want to share,
		// but setting the second argument to false will cause the NPC to instead have an aversion to 
		// giving the player the same fetish
		Map<Fetish, Boolean> unpairedFetishMap = new HashMap<>();

		unpairedFetishMap.put(Fetish.FETISH_BIMBO, true);
		unpairedFetishMap.put(Fetish.FETISH_CROSS_DRESSER, true);
		unpairedFetishMap.put(Fetish.FETISH_INCEST, true);
		unpairedFetishMap.put(Fetish.FETISH_MASTURBATION, true);
		
		
		for(Entry<Fetish, Boolean> entry : unpairedFetishMap.entrySet()) {
			currentTopFetish = entry.getKey();
			Boolean wantsToShare = entry.getValue();
			
			currentTopModifier = TFModifier.valueOf( "TF_MOD_" + currentTopFetish);
			
			currentTopPotency = TFPotency.MINOR_BOOST;
			currentTopRemovePotency = TFPotency.MINOR_DRAIN;
			
			currentTopChance = baseTopChance;
			currentTopRemoveChance = baseTopRemoveChance;
			
			
			if(wantsToShare) {
				// Increase base add chances based on NPC's experience levels for this fetishes
				switch(this.getFetishDesire(currentTopFetish)) {
					case THREE_LIKE:
						currentTopChance += desiredFetishIncrement;
						break;
						
					case FOUR_LOVE:
						currentTopChance += desiredFetishIncrement * 2;
						break;
						
					case ONE_DISLIKE:
						currentTopChance -= desiredFetishIncrement;
						break;
						
					case ZERO_HATE:
						currentTopChance = 0;
						break;
						
					default:
				}
				
				// Increase base add chances based on NPC's experience levels for this fetishes
				switch(this.getFetishLevel(currentTopFetish)) {
					case ONE_AMATEUR:
						currentTopChance += expFetishIncrement;
						break;
					case TWO_EXPERIENCED:
						currentTopChance += expFetishIncrement * 2;
						break;
						
					case THREE_EXPERT:
						currentTopChance += expFetishIncrement * 3;
						break;
						
					case FOUR_MASTERFUL:
						currentTopChance += expFetishIncrement * 4;
						break;
						
					default:
				}
			}
			
				
			// set chances if NPC has top fetish
			if(this.hasFetish(currentTopFetish)) {
				if(wantsToShare) {
					currentTopChance *= pairedFetishMultiplier;
					currentTopRemoveChance = 0;
				}
				else if(pairedFetishesOnly) {
					currentTopChance = 0;
				}
				else {
					currentTopChance -= matchedFetishDecrement;
					currentTopRemoveChance += matchedFetishRemoveIncrement;
				}
			}
			else if(pairedFetishesOnly && wantsToShare) {
				currentTopChance = 0;
				currentTopRemoveChance = 0;
			}
			
			
			
			// if player has positive top fetish desire, adjust potency level to fully add fetish, not just desire
			if( Main.game.getPlayer().getFetishDesire(currentTopFetish) == FetishDesire.THREE_LIKE ||
				Main.game.getPlayer().getFetishDesire(currentTopFetish) == FetishDesire.FOUR_LOVE) {
				currentTopPotency = TFPotency.BOOST;
			}
			else if( Main.game.getPlayer().getFetishDesire(currentTopFetish) == FetishDesire.TWO_NEUTRAL) {
				int rand = Util.random.nextInt(100);
				
				// if the player is neutral, but the NPC has paired fetish,small chance to fully add rather than just boost desire
				if(wantsToShare && this.hasFetish(currentBottomFetish) && rand < 30) {
					currentTopPotency = TFPotency.BOOST;
				}
			} 
			else {
				// if they are already less than neutral, don't remove any more
				currentTopRemoveChance = 0;
			}
			
			
			// prevent extraneous effects if player has top fetish, plus alter remove potency to drop fetish, not just desire
			if(Main.game.getPlayer().hasFetish(currentTopFetish)) {
				currentTopChance = 0;
				currentTopRemovePotency = TFPotency.DRAIN;
			} 
			
		
			// some setting and status combos can result in negative values, so let's zero those out
			if(currentTopChance < 0) { currentTopChance = 0 ;}
			if(currentTopRemoveChance < 0) { currentTopRemoveChance = 0 ;}
			
			if(currentTopChance > 0) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), 
						TFModifier.NONE, 
						currentTopModifier, 
						currentTopPotency, 
						1), 
						currentTopChance);
			}
			
			if(currentTopRemoveChance > 0) {
				possibleEffects.put(new ItemEffect(itemType.getEnchantmentEffect(), 
						TFModifier.NONE, 
						currentTopModifier, 
						currentTopRemovePotency, 
						1), 
						currentTopRemoveChance);
			}
			
		}
				
				
		
		
		// randomly select from possible effects 
		int total = 0;
		for(Entry<ItemEffect, Integer> entry : possibleEffects.entrySet()) {
			total+=entry.getValue();
		}
		
		// no valid options found
		if (total == 0) {
			return null;
		}
		
		int count = Util.random.nextInt(total)+1;
		total = 0;
		for(Entry<ItemEffect, Integer> entry : possibleEffects.entrySet()) {
			if(total < count && total+entry.getValue()>= count) {
				selectedEffect = entry.getKey();
				break;
			}
			total+=entry.getValue();
		}
		
		// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check
		// the results of fetish selection for potion generation
//		System.out.println("POSSIBLE"); 
//		for(Entry<ItemEffect, Integer> entry : possibleEffects.entrySet()) {
//			System.out.println(entry.getValue()+ " " + entry.getKey().getSecondaryModifier()+ " " + entry.getKey().getPotency()); 
//		}
//		System.out.println("SELECTED"); 
//		System.out.println(selectedEffect.getSecondaryModifier() + " " + selectedEffect.getPotency()); 
//		System.out.println(count); 
		
		
		// no fetish to add, so we have nothing to return
		if(selectedEffect == null) {
			return null;
		}
			
		
		// Let's figure out what flavor text string to pair with our selected effect

		// I'm VERY uncertain that you'll like any of this flavor text at all, so please feel free to modify as you see fit
		// Some of it I do like, but mostly I just wanted to be sure there were unique placeholder values for every current fetish
		
		// Also, simply removing/commenting out an entry will cause the fetish in question to go to the default, if you'd like 
		// to get rid of one of my placeholders without having to write your own replacement
		Map<TFModifier, String> fetishAddFlavorText = new HashMap<>(), fetishRemoveFlavorText = new HashMap<>();
		
		String defaultFetishAddFlavorText = "Why not expand your horizons a bit, eh?";
		String defaultFetishRemoveFlavorText = "Maybe you should cool down a bit about the more extreme stuff, eh?.";
		
		
		// body part
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_GIVING, "You're going to love doing it in the ass after this.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_GIVING, "Maybe you should cool down a bit about fucking people in the ass.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_RECEIVING, "You're going to love taking it in the ass after this.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ANAL_RECEIVING, "Maybe you should cool down a bit about getting fucked in the ass.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_OTHERS, "Don't you just love a nice pair of tits?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_OTHERS, "You're way too into breasts.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_SELF, "Wouldn't you love to put your breasts to good use?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BREASTS_SELF, "You're way too into your breasts.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_GIVING, "That beautiful mouth of yours is about to get a lot more use");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_GIVING, "You don't really need to suck every cock you come across, do you?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_RECEIVING, "Don't you just love getting head?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_ORAL_RECEIVING, "Not everyone enjoys getting fucked in the face, you know.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_GIVING, "Nothing quite compares to fucking a wet pussy, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_GIVING, "There's more to sex than just pussy. Expand your horizons a bit.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_RECEIVING, "When it comes down to it, you just want to get fucked in the pussy, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_VAGINAL_RECEIVING, "There's more to sex than just pussy. Expand your horizons a bit.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_LEG_LOVER, "A nice pair of stems makes all the difference, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_LEG_LOVER, "Maybe focus a bit more on what's above the waist -- or at least around the hips?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_STRUTTER, "You've got legs that don't quit -- you really ought to use them");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_STRUTTER, "Maybe focus a bit more on what's above your waist -- or at least around the hips?");
		
		
		// Behavioral
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DOMINANT, "Don't you think you deserve to be the one in charge?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DOMINANT, "You're really not as intimidating as you think.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_SUBMISSIVE, "Give in to it, and admit that you want nothing more than to be my plaything.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_SUBMISSIVE, "Sometimes it's nice to get what you want too, right?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_STUD, "Nothing really compares to filling a juicy hole hole with your seed, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_STUD, "Sex should be about the journey, not the destination.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_ADDICT, "I know a dirty little cum dumpster when I see one.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_CUM_ADDICT, "You can be more than a receptacle for other people's jizz if you want, you know.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DEFLOWERING, "There's something special about being the first to the party, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DEFLOWERING, "Trust me, it's a lot more fun when they have a bit of experience.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_PURE_VIRGIN, "You should treasure whatever innocence you have left while it lasts.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_PURE_VIRGIN, "Fuck virginity. You'll have a lot more fun doing it than having it.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_IMPREGNATION, "A stud like you really ought to be breeding as many bitches as you can");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_IMPREGNATION, "Get over yourself. No one wants to have your baby.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_PREGNANCY, "Being fucked is nice, but being bred is better, isn't it?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_PREGNANCY, "Being knocked up is a bit of a drag, don't you think?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_SEEDER, "You ought to be making babies, and lots of them.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_SEEDER, "You don't really need more mouths to feed, do you?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BROODMOTHER, "You ought to be making babies, and lots of them.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BROODMOTHER, "You don't really need more mouths to feed, do you?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_SADIST, "Isn't it nice when you hurt them and they beg for more?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_SADIST, "Not everyone likes being your punching bag.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_MASOCHIST, "It's time for you to embrace the pain. You'll thank me later.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_MASOCHIST, "You should really take better care of yourself.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_DOM, "When they beg for you to stop it just drives you crazy, doesn't it?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_DOM, "Most of the time, no really means no.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_SUB, "Every time you say 'no' I can see 'fuck me harder' in your eyes.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_NON_CON_SUB, "You really can get off without being forced to, believe it or not.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL, "The only thing better than coming is telling your partner they can't, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL, "If they're willing to fuck you, at least let them come once in a while.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL_SELF, "Where's the fun in coming right away? Wouldn't you rather savor the experience?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_DENIAL_SELF, "What's the point if you aren't getting off?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_VOYEURIST, "Sometimes it's just fun to watch, isn't it?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_VOYEURIST, "Privacy is a thing worth respecting.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_EXHIBITIONIST, "You've got it -- you should flaunt it");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_EXHIBITIONIST, "Not everyone wants to see what you've got to offer.");
		

		// Behavioral unpaired
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_BIMBO, "I think it's time you embraced your inner braindead slut.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_BIMBO, "Maybe have just a little self respect?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_CROSS_DRESSER, "You should wear what you want, and enjoy it.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_CROSS_DRESSER, "It wouldn't kill you to be a bit more reserved.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_MASTURBATION, "Nobody knows your body quite like you do, right?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_MASTURBATION, "Maybe you should think getting your hands on someone else's junk once in a while?");

		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_INCEST, "You know it wouldn't be a taboo if it wasn't at least a little bit fun.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_INCEST, "You what? Gross.");
		
		
		
		// Behavioral transformative
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_GIVING, "You strike me as someone who should be an agent of change.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_GIVING, "You should really just let people be who they are.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_RECEIVING, "Don't you just love becoming something new?");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_TRANSFORMATION_RECEIVING, "I think you're good just as you are.");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_GIVING, "You're into so many interesting things -- you really should share them with others.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_GIVING, "Just let people enjoy what they enjoy, okay?");
		
		fetishAddFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_RECEIVING, "You strike me as someone who would really enjoy trying new things.");
		fetishRemoveFlavorText.put(TFModifier.TF_MOD_FETISH_KINK_RECEIVING, "I think you're already excitable enough as it is.");
		
		
		
		if(selectedEffect.getPotency() == TFPotency.MINOR_BOOST || selectedEffect.getPotency() == TFPotency.BOOST) {
			// default for adding a fetish, just in case a fetish is somehow selected without a string defined in the lookup
			selectedEffectString = defaultFetishAddFlavorText;
			
			if(fetishAddFlavorText.get(selectedEffect.getSecondaryModifier()) != null ) {
				selectedEffectString = fetishAddFlavorText.get(selectedEffect.getSecondaryModifier());
			}
			
		} else {
			// default for removing a fetish, just in case a fetish is somehow selected without a string defined in the lookup
			selectedEffectString = defaultFetishRemoveFlavorText;
			
			if(fetishRemoveFlavorText.get(selectedEffect.getSecondaryModifier()) != null ) {
				selectedEffectString = fetishRemoveFlavorText.get(selectedEffect.getSecondaryModifier());
			}
		}
		
		
		// finally, build and return our fetish potion
		List<ItemEffect> effects = new ArrayList<>();
		effects.add(selectedEffect);
		
		return new Value<>(
				selectedEffectString,
				EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), effects));
	}
	
	
	
	
	// Sex:
	
	
	public void calculateGenericSexEffects(boolean isDom, NPC partner, SexType sexType, boolean allowPregnancy) {
		this.setLastTimeHadSex(Main.game.getMinutesPassed(), true);
		partner.setLastTimeHadSex(Main.game.getMinutesPassed(), true);
		
		if(isDom) {
			this.setSexAsDomCount(this.getSexAsSubCount()+1);
			partner.setSexAsSubCount(partner.getSexAsSubCount()+1);
			
		} else {
			partner.setSexAsDomCount(partner.getSexAsSubCount()+1);
			this.setSexAsSubCount(this.getSexAsSubCount()+1);
		}
		
		SexParticipantType type = sexType.getAsParticipant();
		SexType partnerSexType = sexType;
		PenetrationType penetration = sexType.getPenetrationType();
		OrificeType orifice = sexType.getOrificeType();
		
		this.addSexPartner(partner, sexType);
		if(type == SexParticipantType.PITCHER) {
			partnerSexType = new SexType(SexParticipantType.CATCHER, sexType.getPenetrationType(), sexType.getOrificeType());
			partner.addSexPartner(this, partnerSexType);
			
		} else if(type == SexParticipantType.CATCHER) {
			partnerSexType = new SexType(SexParticipantType.PITCHER, sexType.getPenetrationType(), sexType.getOrificeType());
			partner.addSexPartner(this, partnerSexType);
		} 
		
		if(type.isUsingSelfOrificeType()) {
			if(penetration.isTakesVirginity()) {
				this.setVirginityLoss(sexType, partner.getName("a") + " " + partner.getLostVirginityDescriptor());
				switch(orifice) {
					case ANUS:
						this.setAssVirgin(false);
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						this.setFaceVirgin(false);
						break;
					case NIPPLE:
						this.setNippleVirgin(false);
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						this.setUrethraVirgin(false);
						break;
					case URETHRA_VAGINA:
						this.setVaginaUrethraVirgin(false);
						break;
					case VAGINA:
						this.setVaginaVirgin(false);
						break;
				}
			}
			switch(sexType.getPenetrationType()) {
				case FINGER:
					break;
				case PENIS:
					this.setVirginityLoss(partnerSexType, this.getName("a") + " " + this.getLostVirginityDescriptor());
					partner.setPenisVirgin(false);
					if(partner.getPenisRawCumProductionValue()>0) {
						this.ingestFluid(partner, partner.getCumType(), orifice, partner.getPenisRawCumProductionValue(), partner.getCumModifiers());
						this.incrementCummedInArea(orifice, partner.getPenisRawCumProductionValue());
						if(allowPregnancy) {
							if(this.getBodyMaterial()==BodyMaterial.SLIME || orifice == OrificeType.VAGINA) {
								this.rollForPregnancy(partner);
							}
						}
					}
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case TONGUE:
					break;
			}
		}
		
	}
	
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

	private boolean isKeenToPerformFetishAction(GameCharacter target, Fetish fetish) {
		if(fetish == Fetish.FETISH_ORAL_GIVING) {
			for(Addiction add : this.getAddictions()) {
				if(target.hasPenis() && add.getFluid() == target.getCumType()) {
					return true;
				}
				if(target.hasVagina() && add.getFluid() == target.getGirlcumType()) {
					return true;
				}
			}
		}
		if(fetish == Fetish.FETISH_BREASTS_OTHERS) {
			for(Addiction add : this.getAddictions()) {
				if(target.getBreastRawMilkStorageValue()>0 && add.getFluid() == target.getMilkType()) {
					return true;
				}
			}
		}
		return this.getFetishDesire(fetish)==FetishDesire.THREE_LIKE || this.getFetishDesire(fetish)==FetishDesire.FOUR_LOVE;
	}
	
	private boolean isKeenToAvoidFetishAction(GameCharacter target, Fetish fetish) {
		if(fetish == Fetish.FETISH_ORAL_GIVING) {
			for(Addiction add : this.getAddictions()) {
				if(target.hasPenis() && add.getFluid() == target.getCumType()) {
					return false;
				}
				if(target.hasVagina() && add.getFluid() == target.getGirlcumType()) {
					return false;
				}
			}
		}
		if(fetish == Fetish.FETISH_BREASTS_OTHERS) {
			for(Addiction add : this.getAddictions()) {
				if(target.getBreastRawMilkStorageValue()>0 && add.getFluid() == target.getMilkType()) {
					return false;
				}
			}
		}
		return this.getFetishDesire(fetish)==FetishDesire.ZERO_HATE || this.getFetishDesire(fetish)==FetishDesire.ONE_DISLIKE;
	}
	
	public void generateSexChoices(GameCharacter target) {
		List<SexType> foreplaySexTypes = new ArrayList<>();
		List<SexType> mainSexTypes = new ArrayList<>();
		
		// ************************ Populate possibilities from fetishes and likes. ************************ //
		
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_BREASTS_OTHERS)) {
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
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_BREASTS_SELF)) {
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
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ANAL_GIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.ANUS));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.ANUS));
			
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.TAIL, OrificeType.ANUS));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ANAL_RECEIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.FINGER, OrificeType.ANUS));
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.ANUS));

			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TAIL, OrificeType.ANUS));
		}
		if((isKeenToPerformFetishAction(target, Fetish.FETISH_DEFLOWERING) && target.isVaginaVirgin()) || isKeenToPerformFetishAction(target, Fetish.FETISH_IMPREGNATION) || isKeenToPerformFetishAction(target, Fetish.FETISH_SEEDER)) {
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_PREGNANCY) || isKeenToPerformFetishAction(target, Fetish.FETISH_BROODMOTHER)) {
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ORAL_RECEIVING)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA));
			foreplaySexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH));
			mainSexTypes.add(new SexType(SexParticipantType.CATCHER, PenetrationType.TONGUE, OrificeType.VAGINA));
			mainSexTypes.add(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ORAL_GIVING)) {
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
		if(!target.hasPenis()
				|| !target.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS && !sexType.getAsParticipant().isUsingSelfPenetrationType());
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS && !sexType.getAsParticipant().isUsingSelfPenetrationType());
		}
		if(!this.hasPenis()
				|| !this.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS && sexType.getAsParticipant().isUsingSelfPenetrationType());
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.PENIS && sexType.getAsParticipant().isUsingSelfPenetrationType());
		}
		// Vagina:
		if(!target.hasVagina()
				|| !target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_VAGINAL_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA && !sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA && !sexType.getAsParticipant().isUsingSelfOrificeType());
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_PURE_VIRGIN)
				|| !this.hasVagina()
				|| !this.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_VAGINAL_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA && sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.VAGINA && sexType.getAsParticipant().isUsingSelfOrificeType());
		}
		// Anus:
		if(!target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || isKeenToAvoidFetishAction(target, Fetish.FETISH_ANAL_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS && !sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS && !sexType.getAsParticipant().isUsingSelfOrificeType());
		}
		if(!this.isAbleToAccessCoverableArea(CoverableArea.ANUS, true) || isKeenToAvoidFetishAction(target, Fetish.FETISH_VAGINAL_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS && sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.ANUS && sexType.getAsParticipant().isUsingSelfOrificeType());
		}
		// Oral:
		if(!target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_ORAL_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH && !sexType.getAsParticipant().isUsingSelfOrificeType());
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE && !sexType.getAsParticipant().isUsingSelfPenetrationType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH && !sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE && !sexType.getAsParticipant().isUsingSelfPenetrationType());
		}
		if(!this.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_ORAL_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH && sexType.getAsParticipant().isUsingSelfOrificeType());
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE && sexType.getAsParticipant().isUsingSelfPenetrationType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.MOUTH && sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TONGUE && sexType.getAsParticipant().isUsingSelfPenetrationType());
		}
		// Breasts:
		if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
				|| (!target.hasBreasts() && !target.isBreastFuckableNipplePenetration())
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_BREASTS_OTHERS)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE && !sexType.getAsParticipant().isUsingSelfOrificeType());
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST && !sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE && !sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST && !sexType.getAsParticipant().isUsingSelfOrificeType());
		}
		if(!this.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
				|| (!this.hasBreasts() && !this.isBreastFuckableNipplePenetration())
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_BREASTS_SELF)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE && sexType.getAsParticipant().isUsingSelfOrificeType());
			foreplaySexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST && sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.NIPPLE && sexType.getAsParticipant().isUsingSelfOrificeType());
			mainSexTypes.removeIf(sexType -> sexType.getOrificeType()==OrificeType.BREAST && sexType.getAsParticipant().isUsingSelfOrificeType());
		}
		// Tail:
		if(!target.getTailType().isSuitableForPenetration()) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL && !sexType.getAsParticipant().isUsingSelfPenetrationType());
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL && !sexType.getAsParticipant().isUsingSelfPenetrationType());
		}
		if(!this.getTailType().isSuitableForPenetration()) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL && sexType.getAsParticipant().isUsingSelfPenetrationType());
			mainSexTypes.removeIf(sexType -> sexType.getPenetrationType()==PenetrationType.TAIL && sexType.getAsParticipant().isUsingSelfPenetrationType());
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
		if(Sex.isInForeplay()) {
			return getForeplaySexPositionPreferences();
		} else {
			return getMainSexPositionPreferences();
		}
	}
	
	
	public Set<SexPositionSlot> getForeplaySexPositionPreferences() {
		sexPositionPreferences.clear();
		
		// Foreplay:
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
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
		}
		
		return sexPositionPreferences;
	}
	
	public Set<SexPositionSlot> getMainSexPositionPreferences() {
		sexPositionPreferences.clear();
		
		// Main sex:
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
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
				
			} else if(mainSexPreference.equals(new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA))) {
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
				
			} else if(mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.ANUS))) {
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_ON_ALL_FOURS);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
				
			} else if(mainSexPreference.equals(new SexType(SexParticipantType.CATCHER, PenetrationType.PENIS, OrificeType.VAGINA))) {
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_ON_ALL_FOURS);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
				
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
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
		}
		
		return sexPositionPreferences;
	}
	
	public boolean isWillingToRape(GameCharacter character) {
		return this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)!=FetishDesire.ONE_DISLIKE && this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)!=FetishDesire.ZERO_HATE;
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
		
		if(this.isRelatedTo(character)) {
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
	
	public List<Class<?>> getUniqueSexClasses() {
		return new ArrayList<>();
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
				if(target.isSlave() && target.getOwner()!=null && target.getOwner().isPlayer()) {
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

}
