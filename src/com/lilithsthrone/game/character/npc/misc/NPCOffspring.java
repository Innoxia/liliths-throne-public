package com.lilithsthrone.game.character.npc.misc;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.offspring.GenericOffspringDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.3.5.5
 * @author Innoxia
 */
public class NPCOffspring extends NPC {
	
	public NPCOffspring() {
		this(false);
	}
	
	public NPCOffspring(boolean isImported) {
		super(isImported, null, null, "",
				18, Month.JUNE, 15,
				3, Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
	}
	
	public NPCOffspring(OffspringSeed os) {
		super(false, os.nameTriplet, os.surname, os.description,
				0, os.getBirthday().getMonth(), os.getBirthday().getDayOfMonth(),
				1, os.body.getGender(), os.subspecies, os.body.getRaceStage(), new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
		
		this.birthday = LocalDateTime.of(os.getBirthday().getYear(), this.getBirthday().getMonth(), this.getBirthday().getDayOfMonth(), this.getBirthday().getHour(), this.getBirthday().getMinute());
		this.conceptionDate = os.conceptionDate;
		this.motherName = os.getMotherName();
		this.motherFemininity = os.getMotherFemininity();
		this.motherSubspecies = os.getMotherSubspecies();
		this.fatherName = os.getFatherName();
		this.fatherFemininity = os.getFatherFemininity();
		this.fatherSubspecies = os.getFatherSubspecies();
		this.incubatorName = os.getIncubatorName();
		this.incubatorFemininity = os.getIncubatorFemininity();
		this.incubatorSubspecies = os.getIncubatorSubspecies();

        // The body should be copied from the one generated earlier
        this.body = os.getBody();
        this.body.calculateRace(this);

		// Set random level from 1 to 3:
		setLevel(Util.random.nextInt(3) + 1);
		
		setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(getGender()));
		
		if(os.getMother()!=null) {
			this.setMother(os.getMother());
			this.setAffection(os.getMother(), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		}
		
		if(os.getFather()!=null) {
			this.setFather(os.getFather());
			this.setAffection(os.getFather(), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		}
		
		if(os.getIncubator()!=null) {
			this.setIncubator(os.getIncubatorId());
			this.setAffection(os.getIncubator(), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
		}
		
		// PERSONALITY & BACKGROUND:
		
        Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
        // Undo any name change when spawned as prostitute:
        this.setName(os.nameTriplet);
		
		// ADDING FETISHES:
		
		Main.game.getCharacterUtils().addFetishes(this);
		
		// BODY RANDOMISATION:
		
		Main.game.getCharacterUtils().randomiseBody(this, false);
		
		// INVENTORY:
		
		// Offspring does not call equipClothing() until spawned in Encounter!
		
		resetInventory(true);
		inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
		
		Main.game.getCharacterUtils().applyMakeup(this, true);
		Main.game.getCharacterUtils().applyTattoos(this, true);
		
		initHealthAndManaToMax();
		
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
		
		String npcId = Main.game.safeAddNPC(this, false);
		if(os.getMother()!=null) {
			os.getMother().swapLitters(os.getId(), npcId);
		}
		if(os.getFather()!=null){
			os.getFather().swapLitters(os.getId(), npcId);
		}
		if(os.getIncubator()!=null){
			os.getIncubator().swapLitters(os.getId(), npcId);
		}
		Main.game.getOffspring().add(this);
		Main.game.removeOffspringSeed(os);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(this.getConceptionDate().isAfter(this.getBirthday())) {
			this.setBirthday(this.getConceptionDate().plusMonths(2));
			
		} else if(Math.abs((int) ChronoUnit.DAYS.between(this.getConceptionDate(), this.getBirthday()))>300) {
			this.setConceptionDate(this.getBirthday().minusMonths(2));
		}
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);

		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.PROSTITUTE, settings);
		} else {
			Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
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
						(this.getMother()!=null && this.getMother().isPlayer()
								? getMatingDescription(getMother(), getFather(), "mothered")
								: getMatingDescription(getFather(), getMother(), "fathered")
						)+"."
						+ getRelationshipFromPlayer()
						+ " [npc.She] was conceived on "+Units.date(this.getConceptionDate(), Units.DateType.LONG)+", and "
						+(daysToBirth==0
							?"later that same day "
							:daysToBirth>1?Util.intToString(daysToBirth)+" days later ":Util.intToString(daysToBirth)+" day later ")
						+(this.getMother()!=null && this.getMother().isPlayer()
							?(this.getIncubator()!=null && !this.getIncubator().isPlayer()?this.getIncubator().getName():"you")+" gave birth to [npc.herHim]."
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
