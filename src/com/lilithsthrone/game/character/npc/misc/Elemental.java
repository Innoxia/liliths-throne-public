package com.lilithsthrone.game.character.npc.misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public class Elemental extends NPC {

	private static final long serialVersionUID = 1L;
	private String summonerID;

	public Elemental(boolean isImported) {
		this(Gender.F_V_B_FEMALE, null, isImported);
	}
	
	public Elemental(Gender gender, GameCharacter summoner, boolean isImported) {
		super(null, "", 20, gender, RacialBody.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, false);

		if(!isImported) {
			this.setWorldLocation(summoner.getWorldLocation());
			this.setLocation(summoner.getLocation());
			
			setLevel(summoner.getLevel());
			
			// RACE & NAME:
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(Race.DEMON));
			this.setPlayerKnowsName(true);
			
//			// PERSONALITY & BACKGROUND:
//			
//			CharacterUtils.setHistoryAndPersonality(this);
//			
//			// ADDING FETISHES:
//			
//			CharacterUtils.addFetishes(this);
//			
//			// BODY RANDOMISATION:
//			
//			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.unequipAllClothingIntoVoid();
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element npcSpecific = doc.createElement("elementalSpecial");
		properties.appendChild(npcSpecific);

		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "summoner", this.getOwnerId());
		
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		Element npcSpecificElement = (Element) parentElement.getElementsByTagName("npcSpecific").item(0);
		this.setSummoner(npcSpecificElement.getAttribute("summoner"));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, getSummoner(), "");
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

	public GameCharacter getSummoner() {
		return Main.game.getNPCById(summonerID);
	}

	public void setSummoner(String summonerID) {
		this.summonerID = summonerID;
	}
	
	public void setSummoner(GameCharacter summoner) {
		this.summonerID = summoner.getId();
	}
	

}