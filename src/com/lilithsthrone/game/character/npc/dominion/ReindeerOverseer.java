package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.Name;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class ReindeerOverseer extends NPC {

	private static final long serialVersionUID = 1L;
	
	private AbstractItemType[] itemsForSale = new AbstractItemType[] {
			ItemType.SEX_INGREDIENT_MINCE_PIE,
			ItemType.FIT_INGREDIENT_EGG_NOG,
			ItemType.RACE_INGREDIENT_REINDEER_MORPH};
	
	private AbstractClothingType[] clothingForSale = new AbstractClothingType[] {
			ClothingType.TORSO_OVER_CHRISTMAS_SWEATER,
			ClothingType.NECK_SNOWFLAKE_NECKLACE,
			ClothingType.PIERCING_EAR_SNOW_FLAKES,
			ClothingType.PIERCING_NOSE_SNOWFLAKE_STUD,
			ClothingType.JOLNIR_BOOTS,
			ClothingType.JOLNIR_BOOTS_FEMININE,
			ClothingType.JOLNIR_COAT,
			ClothingType.JOLNIR_DRESS,
			ClothingType.JOLNIR_HAT,
			ClothingType.KIMONO_DRESS,
			ClothingType.KIMONO_GETA,
			ClothingType.KIMONO_HAIR_KANZASHI};
	
	public ReindeerOverseer() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public ReindeerOverseer(Gender gender) {
		this(gender, false);
	}
	
	public ReindeerOverseer(Gender gender, boolean isImported) {
		super(null, "", 10, gender, RacialBody.REINDEER_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_STREET, false);

		if(!isImported) {
			setAttribute(Attribute.STRENGTH, (int)(this.getAttributeValue(Attribute.STRENGTH) * (0.5f+Math.random())));
			setAttribute(Attribute.INTELLIGENCE, (int)(this.getAttributeValue(Attribute.INTELLIGENCE) * (0.5f+Math.random())));
			setAttribute(Attribute.FITNESS, (int)(this.getAttributeValue(Attribute.FITNESS) * (0.5f+Math.random())));
			setAttribute(Attribute.CORRUPTION, (int)(20 * (0.5f+Math.random())));
			
			this.setRandomLocation(WorldType.DOMINION, PlaceType.DOMINION_STREET, true);
			
			// RACE & NAME:
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			Race race = Race.REINDEER_MORPH;
				
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

			setName(Name.getRandomTriplet(race));
			this.setPlayerKnowsName(false);
			
			// PERSONALITY & BACKGROUND:
			
			this.setHistory(History.REINDEER_OVERSEER);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			dailyReset(); // Give items for sale.
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
			setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
		}
	}
	
	@Override
	public ReindeerOverseer loadFromXML(Element parentElement, Document doc) {
		ReindeerOverseer npc = new ReindeerOverseer(Gender.F_V_B_FEMALE, true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}
	
	@Override
	public boolean isUnique() {
		return false;
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
				"[npc.Name] is an overseer of one of the many groups of reindeer-morphs which are working throughout Dominion to keep the streets shovelled clear of snow."));
	}
	
	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		for (int i = 0; i < 10 + (Util.random.nextInt(6)); i++) {
			this.addItem(AbstractItemType.generateItem(ItemType.PRESENT), false);
		}
		
		for (AbstractItemType item : itemsForSale) {
			for (int i = 0; i < 3 + (Util.random.nextInt(6)); i++) {
				this.addItem(AbstractItemType.generateItem(item), false);
			}
		}
		
		for (AbstractClothingType clothing : clothingForSale) {
			for (int i = 0; i < 1 + (Util.random.nextInt(2)); i++) {
				this.addClothing(AbstractClothingType.generateClothing(clothing), false);
			}
		}
		
	}
	
	// Trading:
	
	@Override
	public String getTraderDescription() {
		return UtilText.parse(this,
				"<p>"
					+ "[npc.speech(I'm not really interested in buying anything from you,)]"
					+ " [npc.name] explains, leading you over to a nearby cart which is stacked high with boxes,"
					+ " [npc.speech(but everything here is for sale."
						+ " We passed through the Kitsune's forest on our way to Dominion this year, so I've got some of their traditional clothes here too!)]"
				+ "</p>");
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
	
	
	// Sex:
	
	@Override
	public void endSex(boolean applyEffects) {
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
		return ReindeerOverseerDialogue.ENCOUNTER_START;
	}

	// Combat (you never fight):
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}
	
}
