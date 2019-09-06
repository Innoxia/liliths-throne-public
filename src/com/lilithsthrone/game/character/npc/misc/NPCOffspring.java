package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.offspring.GenericOffspringDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.3.2
 * @author Innoxia
 */
public class NPCOffspring extends NPC {
	
	public NPCOffspring() {
		this(false);
	}
	
	public NPCOffspring(boolean isImported) {
		super(isImported, null, null, "",
				18, Month.JUNE, 15,
				3, Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);
	}
	
	public NPCOffspring(GameCharacter mother, GameCharacter father) {
		super(false, null, null, "",
				0, Main.game.getDateNow().getMonth(), Main.game.getDateNow().getDayOfMonth(),
				3, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, true);


		if(mother.getSubspecies()==Subspecies.LILIN || mother.getSubspecies()==Subspecies.ELDER_LILIN) {
			this.setSurname(mother.getName(false)+"martuilani");
			
		} else if(father.getSubspecies()==Subspecies.LILIN || father.getSubspecies()==Subspecies.ELDER_LILIN) {
			this.setSurname(father.getName(false)+"martuilani");
				
		} else if(mother.getSurname()!=null && !mother.getSurname().isEmpty()) {
			this.setSurname(mother.getSurname());
		}
		
		this.setMother(mother);
		this.setFather(father);
		
		this.setAffection(mother, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		this.setAffection(father, AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		
		// Set random level from 1 to 3:
		setLevel(Util.random.nextInt(3) + 1);
		
		// BODY GENERATION:
		
		Gender gender = Gender.getGenderFromUserPreferences(false, false);
		
		Body preGeneratedBody = Subspecies.getPreGeneratedBody(this, gender, mother, father);
		if(preGeneratedBody!=null) {
			setBody(preGeneratedBody);
		} else {
			setBody(gender, mother, father);
		}
		
		setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(getGender()));

		setName(Name.getRandomTriplet(getRace()));
		
		// PERSONALITY & BACKGROUND:
		
		CharacterUtils.setHistoryAndPersonality(this, true);
		
		// ADDING FETISHES:
		
		CharacterUtils.addFetishes(this);

		// BODY RANDOMISATION:
		
		CharacterUtils.randomiseBody(this, true);
		
		// INVENTORY:
		
		resetInventory(true);
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
		
		equipClothing(EquipClothingSetting.getAllClothingSettings());
		
		CharacterUtils.applyMakeup(this, true);

		initHealthAndManaToMax();

		this.setEnslavementDialogue(GenericOffspringDialogue.ENSLAVEMENT_DIALOGUE, true);
	}
	
	

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(this.getConceptionDate().isAfter(this.getBirthday())) {
			this.setBirthday(this.getConceptionDate().plusMonths(2));
			
		} else if(Math.abs((int) ChronoUnit.DAYS.between(this.getConceptionDate(), this.getBirthday()))>300) {
			this.setConceptionDate(this.getBirthday().minusMonths(2));
		}
		this.setEnslavementDialogue(GenericOffspringDialogue.ENSLAVEMENT_DIALOGUE, true);
	}

//	@Override
//	public DialogueNode getEnslavementDialogue(AbstractClothing enslavementClothing) {
//		SlaveDialogue.setEnslavementTarget(this);
//		this.enslavementClothing = enslavementClothing;
//		
//		return GenericOffspringDialogue.ENSLAVEMENT_DIALOGUE;
//	}
	
//	@Override
//	public boolean isAbleToBeEnslaved() {
//		return this.getSubspecies()!=Subspecies.DEMON;
//	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			CharacterUtils.equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			CharacterUtils.equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public boolean isPlayerOnFirstNameTerms() {
		return true;
	}
	
	private String getRelationshipFromPlayer() {
		Set<Relationship> rel = Main.game.getPlayer().getRelationshipsTo(this);
		if(rel.isEmpty())
			return "";

		return UtilText.parse(this, " You are [npc.hisHer] ") + getRelationshipStr(rel, Main.game.getPlayer().getGender().getType()) + ".";
	}

	private static String getMatingDescription(GameCharacter self, GameCharacter partner, String what) {
		String result = what + " with " + partner.getName("a");
		String rel = partner.getRelationshipStrTo(self);
		if(!rel.isEmpty())
			return result + ", your " + rel;
		else
			return result;
	}
	
	@Override
	public String getDescription() {
		int daysToBirth = (int) ChronoUnit.DAYS.between(this.getConceptionDate(), this.getBirthday());
		
		if(this.getMother()==null || this.getFather()==null) {
			return "";
		}
		return (UtilText.parse(this,
				"[npc.Name] is your [npc.daughter], who you "+
						(this.getMother().isPlayer()
								? getMatingDescription(getMother(), getFather(), "mothered")
								: getMatingDescription(getFather(), getMother(), "fathered")
						)+"."
						+ getRelationshipFromPlayer()
						+ " [npc.She] was conceived on "+Units.date(this.getConceptionDate(), Units.DateType.LONG)+", and "
						+(daysToBirth==0
							?"later that same day"
							:daysToBirth>1?Util.intToString(daysToBirth)+" days later":Util.intToString(daysToBirth)+" day later")
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
	public DialogueNode getEncounterDialogue() {
		return GenericOffspringDialogue.OFFSPRING_ENCOUNTER;
	}
	
	@Override
	public boolean isAllowingPlayerToManageInventory() {
		return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_FIVE_WORSHIP.getMinimumValue() || (this.isSlave() && this.getOwner().isPlayer());
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", GenericOffspringDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", GenericOffspringDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
}
