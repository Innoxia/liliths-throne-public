package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.2.4
 * @author Innoxia
 */
public class Finch extends NPC {

	public Finch() {
		this(false);
	}
	
	public Finch(boolean isImported) {
		super(new NameTriplet("Finch"),
				"Finch is the manager of Slaver Alley's 'Slave Administration' building."
						+ " Although he acts friendly enough, you can't help but wonder if his disarming disposition is just for show."
						+ " After all, would the manager of Dominion's 'Slave Administration' really have got to that position just by being nice?",
				27, Month.SEPTEMBER, 29,
				10,
				Gender.M_P_MALE,
				RacialBody.CAT_MORPH, RaceStage.PARTIAL_FULL, new CharacterInventory(10),
				WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
			
			this.setPenisSize(PenisSize.ONE_TINY.getMedianValue());
			this.setTesticleSize(TesticleSize.ONE_TINY.getValue());
			this.setFemininity(25);
			this.setPenisVirgin(false);
			
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
	
			this.setMoney(10);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_CROTCHLESS_CHAPS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
			
			dailyReset();
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
	public void dailyReset() {
		clearNonEquippedInventory();
		
		// Always at least 4 slave collars:
		for(int i = 0; i<4; i++) {
			this.addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, false), false);
		}
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing!=null && clothing.getItemTags().contains(ItemTag.SOLD_BY_FINCH)) {
				for(int i = 0; i<Util.random.nextInt(3)+1; i++) {
					this.addClothing(AbstractClothingType.generateClothing(clothing, false), false);
				}
				if(clothing.getRarity()==Rarity.COMMON) {
					for(int i = 0; i<Util.random.nextInt(2); i++) {
						if(Math.random()<0.66f) {
							this.addClothing(AbstractClothingType.generateRareClothing(clothing), false);
						} else {
							this.addClothing(AbstractClothingType.generateClothingWithEnchantment(clothing), false);
						}
					}
				}
			}
		}
		
		for(AbstractClothing clothing : this.getAllClothingInInventory()) {
			clothing.setEnchantmentKnown(true);
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
	public String getTraderDescription() {
		return "<p>"
					+ "[finch.speech(Looking for the good stuff, huh?)] [finch.name] says, winking at you as he hands you a 'slaver-exclusive' sales brochure,"
					+ " [finch.speech(Let me know what you fancy!)]"
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return item instanceof AbstractClothing;
	}

	@Override
	public void endSex() {
	}

}