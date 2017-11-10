package com.lilithsthrone.game.character.npc.generic;

import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.Name;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.Personality;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.DominionAlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;

/**
 * @since 0.1.66
 * @version 0.1.89
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends NPC {

	private static final long serialVersionUID = 1L;

	public DominionAlleywayAttacker(Gender gender) {
		super(null, "", 3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, Dominion.CITY_BACK_ALLEYS, false);

		setAttribute(Attribute.STRENGTH, (int)(this.getAttributeValue(Attribute.STRENGTH) * (0.5f+Math.random())));
		setAttribute(Attribute.INTELLIGENCE, (int)(this.getAttributeValue(Attribute.INTELLIGENCE) * (0.5f+Math.random())));
		setAttribute(Attribute.FITNESS, (int)(this.getAttributeValue(Attribute.FITNESS) * (0.5f+Math.random())));
		setAttribute(Attribute.CORRUPTION, (int)(20 * (0.5f+Math.random())));

		this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
		this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
		
		// Set random level from 1 to 3:
		setLevel(Util.random.nextInt(3) + 1);
		
		// RACE & NAME:
		
		Race race = Race.DOG_MORPH;
		
		double humanChance = 0;
		
		if(Main.getProperties().humanEncountersLevel==1) {
			humanChance = 0.05f;
			
		} else if(Main.getProperties().humanEncountersLevel==2) {
			humanChance = 0.25f;
			
		} else if(Main.getProperties().humanEncountersLevel==3) {
			humanChance = 0.5f;
			
		} else if(Main.getProperties().humanEncountersLevel==4) {
			humanChance = 0.75f;
		}
		
		Map<Race, Integer> availableRaces = Util.newHashMapOfValues(
				new Value<>(Race.DOG_MORPH, 20),
				new Value<>(Race.CAT_MORPH, 20),
				new Value<>(Race.HORSE_MORPH, 20),
				new Value<>(Race.WOLF_MORPH, 20),
				new Value<>(Race.SQUIRREL_MORPH, 10),
				new Value<>(Race.COW_MORPH, 10));
		
		if(gender.isFeminine()) {
			for(Entry<Race, FurryPreference> entry : Main.getProperties().raceFemininePreferencesMap.entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					availableRaces.remove(entry.getKey());
				}
			}
		} else {
			for(Entry<Race, FurryPreference> entry : Main.getProperties().raceMasculinePreferencesMap.entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					availableRaces.remove(entry.getKey());
				}
			}
		}
		
		if(availableRaces.isEmpty() || Math.random()<humanChance) {
			setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
			
		} else {
			int total = 0;
			for(int i : availableRaces.values()) {
				total+=i;
			}
			
			int choice = Util.random.nextInt(total) + 1;
			
			total = 0;
			for(Entry<Race, Integer> entry : availableRaces.entrySet()) {
				total+=entry.getValue();
				if(choice<=total) {
					race = entry.getKey();
					break;
				}
			}
			
			if(gender.isFeminine()) {
				switch(Main.getProperties().raceFemininePreferencesMap.get(race)) {
					case HUMAN:
						setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
						break;
					case MINIMUM:
						setBodyFromPreferences(1, gender, race);
						break;
					case REDUCED:
						setBodyFromPreferences(2, gender, race);
						break;
					case NORMAL:
						setBodyFromPreferences(3, gender, race);
						break;
					case MAXIMUM:
						setBody(gender, RacialBody.valueOfRace(race), RaceStage.GREATER);
						break;
				}
			} else {
				switch(Main.getProperties().raceMasculinePreferencesMap.get(race)) {
					case HUMAN:
						setBody(gender, RacialBody.HUMAN, RaceStage.HUMAN);
						break;
					case MINIMUM:
						setBodyFromPreferences(1, gender, race);
						break;
					case REDUCED:
						setBodyFromPreferences(2, gender, race);
						break;
					case NORMAL:
						setBodyFromPreferences(3, gender, race);
						break;
					case MAXIMUM:
						setBody(gender, RacialBody.valueOfRace(race), RaceStage.GREATER);
						break;
				}
			}
		}
			
		setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));

		setName(Name.getRandomTriplet(race));
		this.setPlayerKnowsName(false);
		setDescription(UtilText.parse(this,
				"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
		
		// ADDING FETISHES:
		
		CharacterUtils.addFetishes(this);
		
		// BODY RANDOMISATION:
		
		CharacterUtils.randomiseBody(this);
		
		// INVENTORY:
		
		resetInventory();
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

		CharacterUtils.equipClothing(this, true, false);
		CharacterUtils.applyMakeup(this, true);

		this.setEnslavementDialogue(DominionAlleywayAttackerDialogue.ENSLAVEMENT_DIALOGUE);
		
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	public static DominionAlleywayAttacker loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		DominionAlleywayAttacker character = new DominionAlleywayAttacker(Gender.F_V_B_FEMALE);
		
		// Core info:
		NodeList nodes = parentElement.getElementsByTagName("core");
		Element element = (Element) nodes.item(0);

		// Name:
		character.setName(new NameTriplet(((Element)element.getElementsByTagName("name").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set name: " + ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value"));
		
		// Surname:
		if(element.getElementsByTagName("surname")!=null && element.getElementsByTagName("surname").getLength()>0) {
			character.setSurname(((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
			CharacterUtils.appendToImportLog(log, "</br>Set surname: " + ((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
		}
		
		// Level:
		character.setLevel(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set level: " + Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
		
		// Sexual Orientation:
		if(element.getElementsByTagName("sexualOrientation").getLength()!=0) {
			if(!((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value").equals("null")) {
				character.setSexualOrientation(SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set Sexual Orientation: " + SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
			} else {
				character.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				CharacterUtils.appendToImportLog(log, "</br>Set Sexual Orientation: " + SexualOrientation.AMBIPHILIC);
			}
		}

		if(element.getElementsByTagName("description").getLength()!=0) {
			character.setDescription(((Element)element.getElementsByTagName("description").item(0)).getAttribute("value"));
		}
		if(element.getElementsByTagName("playerPetName").getLength()!=0) {
			character.setPlayerPetName(((Element)element.getElementsByTagName("playerPetName").item(0)).getAttribute("value"));
		}
		if(element.getElementsByTagName("playerKnowsName").getLength()!=0) {
			character.setPlayerKnowsName(Boolean.valueOf(((Element)element.getElementsByTagName("playerKnowsName").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("history").getLength()!=0) {
			character.setHistory(History.valueOf(((Element)element.getElementsByTagName("history").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("personality").getLength()!=0) {
			character.setPersonality(Personality.valueOf(((Element)element.getElementsByTagName("personality").item(0)).getAttribute("value")));
		}
		if(element.getElementsByTagName("obedience").getLength()!=0) {
			character.setObedience(Float.valueOf(((Element)element.getElementsByTagName("obedience").item(0)).getAttribute("value")));
		}
		
		// Temp fix for perk points:
		character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
		CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
		
		int extraLevelUpPoints=0;
		// If there is a version number, attributes should be working correctly:
		if(element.getElementsByTagName("version").item(0)!=null) {
			nodes = parentElement.getElementsByTagName("attributes");
			element = (Element) nodes.item(0);
			for(int i=0; i<element.getElementsByTagName("attribute").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("attribute").item(i));
				
				try {
					character.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
				}catch(IllegalArgumentException ex){
				}
			}
			
		} else {
			extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
			CharacterUtils.appendToImportLog(log, "</br>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
		}
		
		
		nodes = parentElement.getElementsByTagName("characterRelationships");
		element = (Element) nodes.item(0);
		for(int i=0; i<element.getElementsByTagName("characterRelationships").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("relationship").item(i));
			
			if(e.getAttribute("character").equals("PlayerCharacter") && !character.isPlayer()) {
				character.setAffection(Main.game.getPlayer(), Float.valueOf(e.getAttribute("value")));
				CharacterUtils.appendToImportLog(log, "</br>Set Relationship: "+e.getAttribute("character") +" , "+ Float.valueOf(e.getAttribute("value")));
			}
		}
		
		// Fetishes:
		nodes = parentElement.getElementsByTagName("fetishes");
		element = (Element) nodes.item(0);
		if(element!=null) {
			for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
				Element e = ((Element)element.getElementsByTagName("fetish").item(i));
				
				try {
					if(Fetish.valueOf(e.getAttribute("type")) != null) {
						character.addFetish(Fetish.valueOf(e.getAttribute("type")));
						CharacterUtils.appendToImportLog(log, "</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(character));
					}
				}catch(IllegalArgumentException ex){
				}
			}
		}
		
		// Perks:
//				nodes = parentElement.getElementsByTagName("perks");
//				element = (Element) nodes.item(0);
//				for(int i=0; i<element.getElementsByTagName("perk").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("perk").item(i));
//					
//					try {
//						if(Boolean.valueOf(e.getAttribute("value"))) {
//							character.addPerk(Perk.valueOf(e.getAttribute("type")));
//							CharacterUtils.appendToImportLog(log, "</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(character));
//						}
//					}catch(IllegalArgumentException ex){
//					}
//				}
		
		// Status Effects:
		nodes = parentElement.getElementsByTagName("statusEffects");
		element = (Element) nodes.item(0);
		for(int i=0; i<element.getElementsByTagName("statusEffect").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("statusEffect").item(i));
			
			try {
				if(Integer.valueOf(e.getAttribute("value"))!=-1) {
					character.addStatusEffect(StatusEffect.valueOf(e.getAttribute("type")), Integer.valueOf(e.getAttribute("value")));
					CharacterUtils.appendToImportLog(log, "</br>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(character)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
				}
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Combat:
		// TODO Combat is all derived from weapons/perks/body parts, so there's no reason for this to even be here...
		
		// Pregnancy: TODO
//				nodes = parentElement.getElementsByTagName("pregnancy");
//				// Possibilities:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnancyPossibilities").item(0);
//				for(int i=0; i<element.getElementsByTagName("possibility").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("possibility").item(i));
//					
//					try {
//						character.getPotentialPartnersAsMother().add(new PregnancyPossibility(
//								null,
//								null,
//								Float.valueOf(e.getAttribute("probability"))));
//						CharacterUtils.appendToImportLog(log, "</br>Added Pregnancy Possibility: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Litter:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnantLitter").item(0);
//				for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("litter").item(i));
//					
//					try {
//						character.setPregnantLitter(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						CharacterUtils.appendToImportLog(log, "</br>Added Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Birthed Litters:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("birthedLitters").item(0);
//				for(int i=0; i<element.getElementsByTagName("birthedLitter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("birthedLitter").item(i));
//					
//					try {
//						character.getLittersBirthed().add(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						CharacterUtils.appendToImportLog(log, "</br>Added Birthed Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
		
		// Body:
		character.body = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("body").item(0), doc);
		
		// Player Core info:
		nodes = parentElement.getElementsByTagName("playerCore");
		element = (Element) nodes.item(0);

		// Experience:
		character.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
		CharacterUtils.appendToImportLog(log, "</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
		
		// Level up points:
		character.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
		CharacterUtils.appendToImportLog(log, "</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));

		// Perk points:
		//character.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
//				character.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
//				
//				CharacterUtils.appendToImportLog(log, "</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
		
		// Sex stats:
		nodes = parentElement.getElementsByTagName("sexStats");
		
		// Cum counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cumCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("cumCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("cumCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
					character.incrementCumCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				CharacterUtils.appendToImportLog(log, "</br>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Sex counts:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexCounts").item(0);
		for(int i=0; i<element.getElementsByTagName("sexCount").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("sexCount").item(i));
			
			try {
				for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
					character.incrementSexCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
				CharacterUtils.appendToImportLog(log, "</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
			}catch(IllegalArgumentException ex){
			}
		}
		
		// Virginity losses:
		element = (Element) ((Element) nodes.item(0)).getElementsByTagName("virginityTakenBy").item(0);
		for(int i=0; i<element.getElementsByTagName("virginity").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("virginity").item(i));

			character.setVirginityLoss(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))), e.getAttribute("takenBy"));
			CharacterUtils.appendToImportLog(log, "</br>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
		}
		
		
		
		// Inventory:
		character.resetInventory();
		nodes = parentElement.getElementsByTagName("characterInventory");
		element = (Element) nodes.item(0);

		character.setMoney(Integer.valueOf(((Element)element.getElementsByTagName("money").item(0)).getAttribute("value")));
		character.setEssenceCount(TFEssence.ARCANE, Integer.valueOf(((Element)element.getElementsByTagName("essences").item(0)).getAttribute("value")));
		
		if(element.getElementsByTagName("mainWeapon").item(0)!=null) {
			character.equipMainWeaponFromNowhere(AbstractWeapon.loadFromXML(
					(Element) ((Element)element.getElementsByTagName("mainWeapon").item(0)).getElementsByTagName("weapon").item(0),
					doc));
		}

		if(element.getElementsByTagName("offhandWeapon").item(0)!=null) {
			character.equipOffhandWeaponFromNowhere(AbstractWeapon.loadFromXML(
					(Element) ((Element)element.getElementsByTagName("offhandWeapon").item(0)).getElementsByTagName("weapon").item(0),
					doc));
		}
		
		Element clothingEquipped = (Element) element.getElementsByTagName("clothingEquipped").item(0);
		for(int i=0; i<clothingEquipped.getElementsByTagName("clothing").getLength(); i++){
			Element e = ((Element)clothingEquipped.getElementsByTagName("clothing").item(i));
			
			character.equipClothingOverride(AbstractClothing.loadFromXML(e, doc));
		}
		//TODO count
		Element itemsInInventory = (Element) element.getElementsByTagName("itemsInInventory").item(0);
		for(int i=0; i<itemsInInventory.getElementsByTagName("item").getLength(); i++){
			Element e = ((Element)itemsInInventory.getElementsByTagName("item").item(i));
			
			for(int itemCount = 0 ; itemCount < Integer.valueOf(e.getAttribute("count")); itemCount++) {
				character.addItem(AbstractItem.loadFromXML(e, doc), false);
			}
		}
		
		Element clothingInInventory = (Element) element.getElementsByTagName("clothingInInventory").item(0);
		for(int i=0; i<clothingInInventory.getElementsByTagName("clothing").getLength(); i++){
			Element e = ((Element)clothingInInventory.getElementsByTagName("clothing").item(i));

			for(int clothingCount = 0 ; clothingCount < Integer.valueOf(e.getAttribute("count")); clothingCount++) {
				character.addClothing(AbstractClothing.loadFromXML(e, doc), false);
			}
		}
		
		Element weaponsInInventory = (Element) element.getElementsByTagName("weaponsInInventory").item(0);
		for(int i=0; i<weaponsInInventory.getElementsByTagName("weapon").getLength(); i++){
			Element e = ((Element)weaponsInInventory.getElementsByTagName("weapon").item(i));

			for(int weaponCount = 0 ; weaponCount < Integer.valueOf(e.getAttribute("count")); weaponCount++) {
				character.addWeapon(AbstractWeapon.loadFromXML(e, doc), false);
			}
		}
		
		
		character.setLocation(new Vector2i(0, 0));
		
		return character;
	}
	
	private void setBodyFromPreferences(int i, Gender gender, Race race) {
		int choice = Util.random.nextInt(i)+1;
		RaceStage raceStage = RaceStage.PARTIAL;
		
		if (choice == 1) {
			raceStage = RaceStage.PARTIAL;
		} else if (choice == 2) {
			raceStage = RaceStage.LESSER;
		} else {
			raceStage = RaceStage.GREATER;
		}
		
		setBody(gender, RacialBody.valueOfRace(race), raceStage);
	}
	
	@Override
	public String getDescription() {
		return (UtilText.parse(this,
				"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			if(!isSlave()) {
				setPendingClothingDressing(true);
			}
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		if(Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType()==Dominion.CITY_BACK_ALLEYS) {
			return DominionAlleywayAttackerDialogue.ALLEY_ATTACK;
		} else {
			return DominionAlleywayAttackerDialogue.STORM_ATTACK;
		}
	}

	// Combat:

	@Override
	public Attack attackType() {
		if(!getSpecialAttacks().isEmpty()) {
			if (Math.random() < 0.6) {
				return Attack.MAIN;
			} else if (Math.random() < 0.8) {
				return Attack.SEDUCTION;
			} else {
				return Attack.SPECIAL_ATTACK;
			}
			
		} else {
			if (Math.random() < 0.7) {
				return Attack.MAIN;
			} else {
				return Attack.SEDUCTION;
			}
		}
	}

	@Override
	public String getCombatDescription() {
		if(this.isPregnant()) {
			return "The consequence of your refusal to pull out of [npc.name] is standing right before you."
					+ " Visibly pregnant, your one-time sexual partner has a devious grin on [npc.her] face, and you're not quite sure if you want to know what [npc.she]'s planning for [npc.her] revenge...";
		} else {
			if(this.isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(this, "[npc.Name] is quite clearly turned on by your strong aura. [npc.She]'s willing to fight you in order to claim your body.");
				
			} else {
				return UtilText.parse(this, "Although your strong aura is having an effect on [npc.name], [npc.she]'s only really interested in robbing you of your possessions.");
				
			}
		}
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {

		if (attackType == Attack.MAIN) {
			switch (Util.random.nextInt(3)) {
			case 0:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] feints a punch, and as you dodge away, [npc.she] tries to deliver a kick aimed at your legs."
							+ (isHit ? "" : " You see [npc.her] kick coming and jump backwards out of harm's way.")
						+ "</p>");
			case 1:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] jumps forwards, trying to deliver a punch to your stomach."
							+ (isHit ? "" : " You manage to twist to one side, narrowly avoiding [npc.her] attack.")
						+ "</p>");
			default:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] darts forwards, throwing a punch at your torso."
							+ (isHit ? "" : " You manage to dodge [npc.her] attack by leaping to one side.")
						+ "</p>");
			}
		} else {
			if(isFeminine()) {
				switch (Util.random.nextInt(3)) {
					case 0:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] erotically runs [npc.her] hands down [npc.her] legs and bends forwards as [npc.she] teases you, "
									+ "[npc.speech(Come on baby, I can show you a good time!)]"
								+ "</p>");
					case 1:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] pushes out [npc.her] chest and lets out an erotic moan, "
									+ "[npc.speech(Come play with me!)]"
								+ "</p>");
					default:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] slowly runs [npc.her] hands down between [npc.her] thighs, "
									+ "[npc.speech(You know you want it!)]"
								+ "</p>");
				}
			} else {
				switch (Util.random.nextInt(3)) {
					case 0:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] winks at you and flexes [npc.his] muscles, "
									+ "[npc.speech(My body's aching for your touch!)]"
								+ "</p>");
					case 1:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] strikes a heroic pose before blowing a kiss your way, "
									+ "[npc.speech(Come on, I can show you a good time!)]"
								+ "</p>");
					default:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] grins at you as [npc.he] reaches down and grabs [npc.his] crotch, "
									+ "[npc.speech(You know you want a taste of this!)]"
								+ "</p>");
				}

			}
		}
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", DominionAlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", DominionAlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
		}
	}

	@Override
	public String getLostVirginityDescriptor() {
		return "in the streets of Dominion";
	}
	
	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			}else{
				if(item.getItemType().equals(ItemType.PROMISCUITY_PILL)) {
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isPlayerDom()) {
							return "<p>"
									+ "Holding out a 'Promiscuity pill' to [npc.name], you tell [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Promiscuity pill' to [npc.name], you ask [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
	
				} else if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {
					
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isPlayerDom()) {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you tell [npc.her] to swallow it."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you ask [npc.her] to swallow it."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.POTION) || item.getItemType().equals(ItemType.ELIXIR) || item.getItemType().equals(ItemType.FETISH_UNREFINED) || item.getItemType().equals(ItemType.FETISH_REFINED)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink some rando~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and, rather unceremoniously, shove the neck down [npc.her] throat."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.MOTHERS_MILK)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the bottle of "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you unceremoniously shove the bottle's teat into [npc.her] mouth."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.RACE_INGREDIENT_CAT_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_DOG_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HARPY)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HORSE_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_SQUIRREL_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_WOLF_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_COW_MORPH)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to eat tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you unceremoniously shove the "+item.getName()+" into [npc.her] mouth."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to gulp down the entire meal before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the food's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to eat that?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.SEX_INGREDIENT_HARPY_PERFUME)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to use tha~Hey!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you squirt the "+item.getName()+" onto [npc.her] neck."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the perfume's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to use that?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.COR_INGREDIENT_LILITHS_GIFT)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HUMAN)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_DEMON)
						|| item.getItemType().equals(ItemType.FIT_INGREDIENT_CANINE_CRUSH)
						|| item.getItemType().equals(ItemType.FIT_INGREDIENT_SQUIRREL_JAVA)
						|| item.getItemType().equals(ItemType.INT_INGREDIENT_FELINE_FANCY)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_EQUINE_CIDER)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_WOLF_WHISKEY)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_BUBBLE_MILK)) {
					
						if(Sex.isPlayerDom()) {
							return "<p>"
										+ "Taking the bottle of "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's cap, and, rather unceremoniously, shove the neck down [npc.her] throat."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
	
				} else if(item.getItemType().equals(ItemType.EGGPLANT)) {
					
					if(Sex.isPlayerDom()) {
						return "<p>"
									+ "Taking the eggplant from your inventory, you hold it out to [npc.name]."
									+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
									+ " [npc.speech(W-What are you going to do with th-~Mrph!~)]"
								+ "</p>"
								+ "<p>"
									+ "Not liking the start of [npc.her] response, you quickly shove the eggplant into [npc.her] mouth, grinning as you force [npc.herHim] to eat the purple fruit..."
								+ "</p>"
								+Main.game.getPlayer().useItem(item, target, false, true);
					} else {
						return "<p>"
									+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
									+ " [npc.speech(Hah! Did you really think I was going to eat that?!)]</br>"
									+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
								+ "</p>";
					}
					
				} else {
					return "<p>"
								+ "You try to give [npc.name] "+item.getItemType().getDeterminer()+" "+item.getName()+", but [npc.she] refuses to take it. You put the "+item.getName()+" back in your inventory."
							+ "</p>";
				}
			}
			
		// NPC is using an item:
		}else{
			return Sex.getPartner().useItem(item, target, false);
		}
	}
	
}
