package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.PregnancyDescriptor;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.2.11
 * @author Innoxia
 */
public class Elemental extends NPC {
	private String summonerID;

	public Elemental(boolean isImported) {
		this(Gender.F_V_B_FEMALE, null, isImported);
	}
	
	public Elemental(Gender gender, GameCharacter summoner, boolean isImported) {
		super(isImported, null, "", summoner==null?18:summoner.getAgeValue(), summoner==null?Month.JANUARY:summoner.getBirthMonth(), summoner==null?1:summoner.getDayOfBirth(), 20, gender, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, false);

		if(!isImported) {
			this.setWorldLocation(summoner.getWorldLocation());
			this.setLocation(summoner.getLocation());
			
			setLevel(summoner.getLevel());
			
			this.setSummoner(summoner);
			
			this.setLegType(LegType.DEMON_COMMON);
			
			this.setHistory(Occupation.ELEMENTAL_ARCANE);
			
			// RACE & NAME:
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(Race.DEMON));
			this.setPlayerKnowsName(true);
			
			// INVENTORY:
			
			resetInventory(true);
			
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);

			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 0);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element npcSpecific = doc.createElement("elementalSpecial");
		properties.appendChild(npcSpecific);

		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "summoner", this.getSummoner().getId());
		
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		Element npcSpecificElement = (Element) parentElement.getElementsByTagName("elementalSpecial").item(0);
		this.setSummoner(((Element)npcSpecificElement.getElementsByTagName("summoner").item(0)).getAttribute("value"));
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.6")) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 0);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			this.resetPerksMap();
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		// Not needed
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
	public int getLevel() {
		if(this.getSummoner()==null) {
			return level;
		}
		return getSummoner().getLevel();
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	
	@Override
	public String rollForPregnancy(GameCharacter partner, int cum) {
		return PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner)
				+"<p style='text-align:center;'>[style.italicsMinorBad(Elementals cannot get pregnant!)]<br/>[style.italicsDisabled(I will add support for impregnating/being impregnated by elementals soon!)]</p>";
	}

	@Override
	public String incrementExperience(int increment, boolean withExtaModifiers) {
		return ""; // Elementals don't gain experience, but instead automatically level up alongside their summoner.
	}
	
	@Override
	public boolean addPerk(int row, Perk perk) {
		perks.putIfAbsent(row, new HashSet<>());
		
		if (perks.get(row).contains(perk)) {
			return false;
		}
		
		perks.get(row).add(perk);
		
		if(!perk.isEquippableTrait()) {
			applyPerkGainEffects(perk);
		}

		calculateSpells(getCurrentSchool());
		
		return true;
	}
	
	private void calculateSpells(SpellSchool school) {
		this.resetSpells();
		
		// Add spells:
		for(Set<Perk> perkSet : this.getPerksMap().values()) {
			for(Perk p : perkSet) {
				if(p.getSchool()==school) {
					if(p.getSpellUpgrade()!=null) {
						this.addSpellUpgrade(p.getSpellUpgrade());
					} else {
						this.addSpell(p.getSpell());
						
					}
				}
			}
		}
	}
	
	public SpellSchool getCurrentSchool() {
		switch(this.getBodyMaterial()) {
			case AIR:
				return SpellSchool.AIR;
			case ARCANE:
				return SpellSchool.ARCANE;
			case FIRE:
				return SpellSchool.FIRE;
			case FLESH:
			case SLIME:
				break;
			case RUBBER:
			case STONE:
				return SpellSchool.EARTH;
			case ICE:
			case WATER:
				return SpellSchool.WATER;
		}
		return SpellSchool.ARCANE;
	}
	
	public void setElementalSchool(SpellSchool school) {
		setElementalSchool(school, null);
	}
	
	public void setElementalSchool(SpellSchool school, BodyMaterial preferredMaterial) {
		
		switch(school) {
			case AIR:
				this.setBodyMaterial(BodyMaterial.AIR);
				this.setHistory(Occupation.ELEMENTAL_AIR);
				break;
				
			case ARCANE:
				this.setBodyMaterial(BodyMaterial.ARCANE);
				this.setHistory(Occupation.ELEMENTAL_ARCANE);
				break;
				
			case EARTH:
				if(preferredMaterial==BodyMaterial.RUBBER) {
					this.setBodyMaterial(BodyMaterial.RUBBER);
				} else {
					this.setBodyMaterial(BodyMaterial.STONE);
				}
				this.setHistory(Occupation.ELEMENTAL_EARTH);
				break;
				
			case FIRE:
				this.setBodyMaterial(BodyMaterial.FIRE);
				this.setHistory(Occupation.ELEMENTAL_FIRE);
				break;
				
			case WATER:
				if(preferredMaterial==BodyMaterial.ICE) {
					this.setBodyMaterial(BodyMaterial.ICE);
				} else {
					this.setBodyMaterial(BodyMaterial.WATER);
				}
				this.setHistory(Occupation.ELEMENTAL_WATER);
				break;
		}
		calculateSpells(school);
	}
	
	public GameCharacter getSummoner() {
		try {
			return Main.game.getNPCById(summonerID);
		} catch (Exception e) {
//			System.err.println("Main.game.getNPCById("+id+") returning null in method: getSummoner()");
			return null;
//			throw new NullPointerException();
		}
	}

	public void setSummoner(String summonerID) {
		this.summonerID = summonerID;
	}
	
	public void setSummoner(GameCharacter summoner) {
		this.summonerID = summoner.getId();
	}
	

}