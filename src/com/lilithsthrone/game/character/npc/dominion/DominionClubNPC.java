package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class DominionClubNPC extends NPC {

	private static final long serialVersionUID = 1L;

	public DominionClubNPC() {
		this(Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, false);
	}
	
	public DominionClubNPC(boolean isImported) {
		this(Gender.F_V_B_FEMALE, Subspecies.DOG_MORPH, isImported);
	}
	
	public DominionClubNPC(Gender gender, Subspecies subspecies, boolean isImported) {
		super(null, "",
				25, Month.JUNE, 15,
				3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_STREET, false);

		if(!isImported) {
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(Main.game.getPlayer().getLocation());
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:

			
			if(subspecies.getRace()==Race.HARPY) {
				setBody(gender, subspecies, RaceStage.LESSER);
				
			} else {
				FurryPreference preference;
				if(gender.isFeminine()) {
					preference = Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies);
				} else {
					preference = Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies);
				}
				
				switch(preference) {
					case HUMAN:
					case MINIMUM:
						setBodyFromPreferences(1, gender, subspecies);
						break;
					case REDUCED:
						setBodyFromPreferences(2, gender, subspecies);
						break;
					case NORMAL:
						setBodyFromPreferences(3, gender, subspecies);
						break;
					case MAXIMUM:
						setBody(gender, subspecies, RaceStage.GREATER);
						break;
				}
			}
			
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(true);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who you met in one of Nightlife's clubs."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public Vector2i getLocation() {
		if(this.getWorldLocation()==WorldType.NIGHTLIFE_CLUB
				&& Main.game.getPlayer().getWorldLocation()==WorldType.NIGHTLIFE_CLUB) {
			return Main.game.getPlayer().getLocation();
		}
		return location;
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
		return null;
	}

	@Override
	public void endSex() {
	}
	
}