package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DominionOffspringDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.HarpyNestOffspringDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.1.95
 * @author Innoxia
 */
public class NPCOffspring extends NPC {
	
	public NPCOffspring() {
		this(false);
	}
	
	public NPCOffspring(boolean isImported) {
		super(null, "",
				18, Month.JUNE, 15,
				3, Gender.F_V_B_FEMALE, RacialBody.DOG_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
		
		this.setEnslavementDialogue(DominionOffspringDialogue.ENSLAVEMENT_DIALOGUE);
	}
	
	public NPCOffspring(GameCharacter mother, GameCharacter father) {
		super(null, "",
				18, Main.game.getDateNow().minusMonths(1).getMonth(), 1+Util.random.nextInt(25),
				3, Gender.F_V_B_FEMALE, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
		
		this.setMother(mother);
		this.setFather(father);
		
		this.setAffection(mother, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		this.setAffection(father, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		
		// Set random level from 1 to 3:
		setLevel(Util.random.nextInt(3) + 1);
		
		// BODY GENERATION:
		
		Gender gender = GenderPreference.getGenderFromUserPreferences(false, false);
		
		setBody(gender, mother, father);
		
		setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(getGender()));

		setName(Name.getRandomTriplet(getRace()));

		// PERSONALITY & BACKGROUND:
		
		CharacterUtils.setHistoryAndPersonality(this, true);
		
		// ADDING FETISHES:
		
		CharacterUtils.addFetishes(this);
		
		// BODY RANDOMISATION:
		
		CharacterUtils.randomiseBody(this);
		
		// INVENTORY:
		
		resetInventory();
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
		
		CharacterUtils.equipClothing(this, true, false);
		
		CharacterUtils.applyMakeup(this, true);

		if(this.getWorldLocation()==WorldType.HARPY_NEST) {
			this.setEnslavementDialogue(HarpyNestOffspringDialogue.ENSLAVEMENT_DIALOGUE);
		} else {
			this.setEnslavementDialogue(DominionOffspringDialogue.ENSLAVEMENT_DIALOGUE);
		}
		
		setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
	}
	
	

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getWorldLocation()==WorldType.HARPY_NEST) {
			this.setEnslavementDialogue(HarpyNestOffspringDialogue.ENSLAVEMENT_DIALOGUE);
		} else {
			this.setEnslavementDialogue(DominionOffspringDialogue.ENSLAVEMENT_DIALOGUE);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getPlayerPetName() {
		if(playerPetName.length()==0 || playerPetName.equalsIgnoreCase("Mom") || playerPetName.equalsIgnoreCase("Dad")) {
			if(Main.game.getPlayer().isFeminine()) {
				return "Mom";
			} else {
				return "Dad";
			}
		} else if (playerPetName.equalsIgnoreCase("Mommy") || playerPetName.equalsIgnoreCase("Daddy")) {
			if(Main.game.getPlayer().isFeminine()) {
				return "Mommy";
			} else {
				return "Daddy";
			}
		} else {
			return playerPetName;
		}
	}
	
	@Override
	public String getDescription() {
		int timeToBirth = this.getDayOfBirth()-this.getDayOfConception();
		if(this.getMother()==null || this.getFather()==null) {
			return "";
		}
		return (UtilText.parse(this,
				"[npc.Name] is your [npc.daughter], who you "+(this.getMother().isPlayer()?"mothered with "+(this.getFather().getName("a")):"fathered with "+(this.getMother().getName("a")))+"."
						+ " [npc.She] was conceived on day "+this.getDayOfConception()+", and "
						+(timeToBirth==0
							?"later that same day"
							:timeToBirth>1?Util.intToString(timeToBirth)+" days later":Util.intToString(timeToBirth)+" day later")
						+(this.getMother().isPlayer()
							?" you gave birth to [npc.herHim]."
							:" [npc.she] was born.")
						+ " You first encountered [npc.herHim] prowling the alleyways of Dominion, and, through some arcane-influenced instinct, you both recognised your relationship at first sight."));
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
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
		if(this.getWorldLocation()==WorldType.HARPY_NEST) {
			return HarpyNestOffspringDialogue.OFFSPRING_ENCOUNTER;
		} else {
			return DominionOffspringDialogue.OFFSPRING_ENCOUNTER;
		}
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.getWorldLocation()==WorldType.HARPY_NEST) {
			if (victory) {
				return new Response("", "", HarpyNestOffspringDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", HarpyNestOffspringDialogue.AFTER_COMBAT_DEFEAT);
			}
			
		} else {
			if (victory) {
				return new Response("", "", DominionOffspringDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", DominionOffspringDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}

	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		return getItemUseEffectsAllowingUse(item, user, target);
	}
	
	
}
