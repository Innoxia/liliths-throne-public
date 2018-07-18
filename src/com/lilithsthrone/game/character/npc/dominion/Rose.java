package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
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
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
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
 * @since 0.1.3
 * @version 0.1.89
 * @author Innoxia
 */
public class Rose extends NPC {

	private static final long serialVersionUID = 1L;

	public Rose() {
		this(false);
	}
	
	public Rose(boolean isImported) {
		super(new NameTriplet("Rose"),
				"Rose is Lilaya's slave, and is the only other member of her household that you've ever seen."
						+ " A partial cat-girl, Rose is treated with extreme fondness by Lilaya, and appears to be the only other person Lilaya has any regular contact with."
						+ " Their relationship strays into something more than a master-slave arrangement, and Rose and Lilaya can often be seen hugging and whispering to one another.",
				18, Month.MARCH, 5,
				10, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.PARTIAL_FULL,
				new CharacterInventory(10), WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.LOW)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_RED), true);
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
			this.setHairStyle(HairStyle.BOB_CUT);
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	
			this.setMuscle(Muscle.TWO_TONED.getMedianValue());
			this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
			
			this.setVaginaVirgin(true);
			this.setBreastSize(CupSize.C.getMeasurement());
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_BELL_COLLAR, Colour.CLOTHING_BLACK, false), true, this);
			
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MAIN_FEATHER_DUSTER));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.addFetish(Fetish.FETISH_SUBMISSIVE);
		this.addFetish(Fetish.FETISH_DOMINANT);
		this.addFetish(Fetish.FETISH_SADIST);
		
		if(this.getClothingInSlot(InventorySlot.NECK)==null) {
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_BELL_COLLAR, Colour.CLOTHING_BLACK, false), true, this);
		}
		if(this.getMainWeapon()==null) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MAIN_FEATHER_DUSTER));
		}
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	}

	@Override
	public boolean isUnique() {
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
		if(this.getClothingInSlot(InventorySlot.PENIS)!=null) {
			this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.PENIS), true, this);
			if(this.getClothingInSlot(InventorySlot.GROIN)==null) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_BLACK, false), true, this);
			}
			this.replaceAllClothing();
		}
	}
	
	public static final DialogueNodeOld END_HAND_SEX = new DialogueNodeOld("Recover", "Both you and Rose and exhausted from your hand-holding session.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Rose staggers over and retrieves her little feather-duster, casting a sultry look back your way before biting her lip and hurrying off to another part of the house, no doubt to recover from your extreme hand-holding session."
					+ "</p>"
					+ "<p>"
						+ "With an exhausted sigh, you collapse down onto the room's bed, your thoughts dwelling on the amazing experience you've just had."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "You've finally recovered from your intense hand-holding session with Rose.", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
					
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
					}
				};
			} else {
				return null;
			}
		}
	};

}