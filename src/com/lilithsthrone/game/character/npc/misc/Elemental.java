package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
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
			
			// RACE & NAME:
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(Race.DEMON));
			this.setPlayerKnowsName(true);
			
			// INVENTORY:
			
			resetInventory(true);
			
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
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
	public void endSex() {
	}
	
	@Override
	public String rollForPregnancy(GameCharacter partner, int cum) {
		return PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner)
				+"<p style='text-align:center;'>[style.italicsMinorBad(Elementals cannot get pregnant!)]<br/>[style.italicsDisabled(I will add support for impregnating/being impregnated by elementals soon!)]</p>";
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
		this.setAttribute(Attribute.MAJOR_ARCANE, 60);
		this.resetSpells();
		
		switch(school) {
			case AIR:
				this.setAttribute(Attribute.MAJOR_PHYSIQUE, 30);
				this.setAttribute(Attribute.MAJOR_ARCANE, 70);
				this.setBodyMaterial(BodyMaterial.AIR);
				
				this.addSpell(Spell.POISON_VAPOURS);
				this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1);
				this.addSpell(Spell.PROTECTIVE_GUSTS);
				this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1);
				this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2);
				this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3);
				this.addSpell(Spell.VACUUM);
				break;
				
			case ARCANE:
				this.setAttribute(Attribute.MAJOR_PHYSIQUE, 50);
				this.setAttribute(Attribute.MAJOR_ARCANE, 90);
				this.setBodyMaterial(BodyMaterial.ARCANE);

				this.addSpell(Spell.ARCANE_AROUSAL);
				this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
				this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
				this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);
				this.addSpell(Spell.ARCANE_CLOUD);
				this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1);
				this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
				this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
				this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2);
				break;
				
			case EARTH:
				this.setAttribute(Attribute.MAJOR_ARCANE, 40);
				if(preferredMaterial==BodyMaterial.RUBBER) {
					this.setAttribute(Attribute.MAJOR_PHYSIQUE, 50);
					this.setBodyMaterial(BodyMaterial.RUBBER);
				} else {
					this.setAttribute(Attribute.MAJOR_PHYSIQUE, 80);
					this.setBodyMaterial(BodyMaterial.STONE);
				}
				
				this.addSpell(Spell.SLAM);
				this.addSpellUpgrade(SpellUpgrade.SLAM_1);
				this.addSpellUpgrade(SpellUpgrade.SLAM_2);
				this.addSpell(Spell.TELEKENETIC_SHOWER);
				this.addSpell(Spell.STONE_SHELL);
				this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_1);
				this.addSpellUpgrade(SpellUpgrade.STONE_SHELL_2);
				break;
				
			case FIRE:
				this.setAttribute(Attribute.MAJOR_ARCANE, 50);
				this.setAttribute(Attribute.MAJOR_PHYSIQUE, 60);
				this.setBodyMaterial(BodyMaterial.FIRE);
				
				this.addSpell(Spell.FIREBALL);
				this.addSpellUpgrade(SpellUpgrade.FIREBALL_1);
				this.addSpell(Spell.CLOAK_OF_FLAMES);
				this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1);
				this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2);
				this.addSpell(Spell.FLASH);
				break;
				
			case WATER:
				this.setAttribute(Attribute.MAJOR_ARCANE, 60);
				this.setAttribute(Attribute.MAJOR_PHYSIQUE, 40);
				if(preferredMaterial==BodyMaterial.ICE) {
					this.setBodyMaterial(BodyMaterial.ICE);
				} else {
					this.setBodyMaterial(BodyMaterial.WATER);
				}
				
				this.addSpell(Spell.ICE_SHARD);
				this.addSpellUpgrade(SpellUpgrade.ICE_SHARD_1);
				this.addSpellUpgrade(SpellUpgrade.ICE_SHARD_2);
				this.addSpell(Spell.RAIN_CLOUD);
				this.addSpellUpgrade(SpellUpgrade.RAIN_CLOUD_1);
				this.addSpellUpgrade(SpellUpgrade.RAIN_CLOUD_2);
				this.addSpellUpgrade(SpellUpgrade.RAIN_CLOUD_3);
				this.addSpell(Spell.SOOTHING_WATERS);
				break;
		}
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