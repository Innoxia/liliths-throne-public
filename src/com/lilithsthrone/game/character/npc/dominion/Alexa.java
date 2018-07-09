package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.1.83
 * @author Innoxia
 */
public class Alexa extends NPC {

	private static final long serialVersionUID = 1L;

	public Alexa() {
		this(false);
	}
	
	public Alexa(boolean isImported) {
		super(new NameTriplet("Alexa"),
				"Alexa is an extremely powerful harpy matriarch, and is in control of one of the largest harpy flocks in Dominion."
						+ " Her beauty rivals that of even the most gorgeous of succubi, which, combined with her sharp mind and regal personality, makes her somewhat of an idol in harpy society.",
				26, Month.MAY, 3,
				8, Gender.F_V_B_FEMALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_ALEXAS_NEST, true);

		this.setAttribute(Attribute.MAJOR_PHYSIQUE, 10f);
		this.setAttribute(Attribute.MAJOR_ARCANE, 0f);
		this.setAttribute(Attribute.MAJOR_CORRUPTION, 30f);
		
		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_BLUE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

			this.setFemininity(100);
			
			this.setVaginaVirgin(true);
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			this.setBreastSize(CupSize.D.getMeasurement());
			
			this.setMuscle(Muscle.TWO_TONED.getMedianValue());
			this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
			
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
			this.addFetish(Fetish.FETISH_DENIAL);
			
			this.setHeight(162);
			
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.PHYSICAL));
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BIKINI, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_BIKINI, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_TIARA, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_GOLD, false), true, this);
			
			this.setPiercedEar(true);
			this.setPiercedNavel(true);
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#59005C";
		} else {
			return "#FFDFB3";
		}
	}
	
	@Override
	public void dailyReset() {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
			for(String id : this.getSlavesOwned()) {
				if(Main.game.isCharacterExisting(id)) {
					Main.game.banishNPC(id);
				}
			}
			this.removeAllSlaves();
			
			for(int i=0; i<5; i++) {
				NPC newSlave = new DominionAlleywayAttacker(GenderPreference.getGenderFromUserPreferences());
				try {
					Main.game.addNPC(newSlave, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				newSlave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
				addSlave(newSlave);
				newSlave.resetInventory();
				newSlave.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getAlexa());
				newSlave.setPlayerKnowsName(true);
				
			}
		}
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