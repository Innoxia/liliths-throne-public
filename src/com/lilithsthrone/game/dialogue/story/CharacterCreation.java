package com.lilithsthrone.game.dialogue.story;

import java.io.File;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.Name;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.Personality;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.QuestType;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.87
 * @author Innoxia
 */
public class CharacterCreation {

	public static final DialogueNodeOld CHARACTER_CREATION_START = new DialogueNodeOld("Disclaimer", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "You agree that you are the legal age to view pornographic material, and consent to being exposed to graphic content.", ALPHA_MESSAGE);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld ALPHA_MESSAGE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Version " + Main.VERSION_NUMBER + " | <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Early Alpha!</b>";
		}
		
		@Override
		public String getContent() {
			return Main.patchNotes;
			
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue to the next screen.", CONTENT_PREFERENCES);
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld CONTENT_PREFERENCES = new DialogueNodeOld("Content Preferences", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "The following options determine what content is enabled in the game."
					+ "</p>"
					+ "<p>"
						+ "All of these options can be changed at any time by accessing the main menu (press Escape, or click on the cog icon in the bottom-left corner of the screen), and then navigating to 'options', then 'content preferences'."
					+ "</p>"
					+ OptionsDialogue.CONTENT_PREFERENCE.getHeaderContent();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Start", "Proceed to character creation.", CHOOSE_APPEARANCE){
					@Override
					public void effects() {
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.getProperties().setNewWeaponDiscovered(false);
						Main.getProperties().setNewClothingDiscovered(false);
						Main.getProperties().setNewItemDiscovered(false);
						Main.game.getPlayer().calculateStatusEffects(0);
						getDressed();
						resetBodyAppearance();
						
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("Unknown", "Unknown", "Unknown"));
						Main.game.getPlayer().setSurname("");
					}
				};
				
			} else if (index == 2) {
				return new Response("Start (Import)", "Import a character from a previous version to use on game start.", IMPORT_CHOOSE) {
					@Override
					public void effects() {
						Main.game.getPlayerCell().resetInventory();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static void resetBodyAppearance() {
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
		Main.game.getLilaya().setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_BROWN), true);
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN), true);
		Main.game.getPlayer().setBreastShape(BreastShape.ROUND);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue());
		Main.game.getPlayer().setPersonality(Personality.EARTH_CALM);

		Main.game.getPlayer().setFacialHair(BodyHair.ZERO_NONE);
		switch(Main.game.getPlayer().getFemininity()) {
			case MASCULINE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				break;
			case MASCULINE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				break;
			case ANDROGYNOUS:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				break;
			case FEMININE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.THREE_TRIMMED);
				break;
			case FEMININE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setPubicHair(BodyHair.ZERO_NONE);
				break;
		}
	}
	
	public static void setGenderFemale() {
		Femininity fem = Femininity.FEMININE;
		switch(Main.game.getPlayer().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case MASCULINE:
				fem = Femininity.FEMININE;
				break;
			case MASCULINE_STRONG:
				fem = Femininity.FEMININE_STRONG;
				break;
			default:
				break;
		}
		Main.game.getPlayer().setBody(Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN);
		Main.game.getPlayer().setFemininity(fem.getMedianFemininity());
		
		getDressed();
		
		CharacterCreation.resetBodyAppearance();
	}
	
	public static void setGenderMale() {
		Femininity fem = Femininity.MASCULINE;
		switch(Main.game.getPlayer().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case FEMININE:
				fem = Femininity.MASCULINE;
				break;
			case FEMININE_STRONG:
				fem = Femininity.MASCULINE_STRONG;
				break;
			default:
				break;
		}
		Main.game.getPlayer().setBody(Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN);
		Main.game.getPlayer().setFemininity(fem.getMedianFemininity());
		
		getDressed();
		
		CharacterCreation.resetBodyAppearance();
	}
	
	private static void equipPiercings() {
		Colour colour1 = Main.game.getPlayer().isFeminine()?Colour.CLOTHING_GOLD:Colour.CLOTHING_BLACK_STEEL;
		Colour colour2 = Main.game.getPlayer().isFeminine()?Colour.CLOTHING_ROSE_GOLD:Colour.CLOTHING_SILVER;
		
		// Ear piercings:
		if(Main.game.getPlayer().isPiercedEar()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_EAR)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_EAR), true, Main.game.getPlayer());
		}
		
		// Lip piercings:
		if(Main.game.getPlayer().isPiercedLip()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_LIP_RINGS, colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_LIP)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_LIP), true, Main.game.getPlayer());
		}
		
		// Navel piercings:
		if(Main.game.getPlayer().isPiercedNavel() && Main.game.getPlayer().isFeminine()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, colour2, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_STOMACH)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_STOMACH), true, Main.game.getPlayer());
		}

		// Nipples piercings:
		if(Main.game.getPlayer().isPiercedNipple()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NIPPLE_BARS, colour2, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NIPPLE)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NIPPLE), true, Main.game.getPlayer());
		}

		// Nose piercings:
		if(Main.game.getPlayer().isPiercedNose()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NOSE)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NOSE), true, Main.game.getPlayer());
		}

		// Penis piercings:
		if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isPiercedPenis()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_PENIS_RING, colour2, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_PENIS)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_PENIS), true, Main.game.getPlayer());
		}

		// Tongue piercings:
		if(Main.game.getPlayer().isPiercedTongue()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_TONGUE_BAR, colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_TONGUE)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_TONGUE), true, Main.game.getPlayer());
		}

		// Vagina piercings:
		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isPiercedVagina()) {
			Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_VAGINA_BARBELL_RING, colour2, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_VAGINA)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_VAGINA), true, Main.game.getPlayer());
		}
	}
	
	public static void getDressed() {
		getDressed(Main.game.getPlayer(), true);
	}
	
	public static void getDressed(GameCharacter character, boolean spawnClothingOnFloor) {
		character.resetInventory();
		Main.game.getPlayerCell().resetInventory();
		
		equipPiercings();
		
		switch(character.getFemininity()) {
			case MASCULINE_STRONG:
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OXFORD_SHIRT, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_TIE, Colour.CLOTHING_RED, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_SUIT_JACKET, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_GOLD, false), true, character);
				
				if(spawnClothingOnFloor) {
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_BLUE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_TAN, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_LOW_TOP_SKATER_SHOES, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_CARGO_TROUSERS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLUE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_BLACK_STEEL, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HAND_GLOVES, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_CAP, Colour.CLOTHING_BLUE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_SCARF, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_RIBBED_SWEATER, Colour.CLOTHING_GREY, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_TSHIRT, Colour.CLOTHING_BLUE_LIGHT, false));
				}
				break;
				
			case MASCULINE:
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_SILVER, false), true, character);

				if(spawnClothingOnFloor) {
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OXFORD_SHIRT, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_TIE, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_SUIT_JACKET, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_BLUE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_TAN, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_LOW_TOP_SKATER_SHOES, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_CARGO_TROUSERS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLUE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_BLACK_STEEL, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HAND_GLOVES, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_CAP, Colour.CLOTHING_BLUE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_SCARF, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_RIBBED_SWEATER, Colour.CLOTHING_GREY, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_TSHIRT, Colour.CLOTHING_BLUE_LIGHT, false));
				}
				break;
				
			case ANDROGYNOUS:
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, character);
				if(character.getBreastRawSizeValue()!=0) {
					character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_CROPTOP_BRA, Colour.CLOTHING_WHITE, false), true, character);
				} else {
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.CHEST_CROPTOP_BRA, Colour.CLOTHING_WHITE, false));
				}
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLUE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_LOW_TOP_SKATER_SHOES, Colour.CLOTHING_RED, false), true, character);
				
				if(spawnClothingOnFloor) {
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PURPLE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_WHITE, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_RED, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_WHITE, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_CARGO_TROUSERS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_SKIRT, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_SCARF, Colour.CLOTHING_RED, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_CAP, Colour.CLOTHING_BLUE, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.STOMACH_UNDERBUST_CORSET, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_TSHIRT, Colour.CLOTHING_BLUE_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_BLUE_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_GREEN, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_PINK_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_OPEN_CARDIGAN, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_SILVER, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_SILVER, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_SILVER, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.ANKLE_BRACELET, Colour.CLOTHING_SILVER, false));
				}
				break;
				
			case FEMININE:
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SKATER_DRESS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.BASE_PINK_LIGHT, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_SILVER, false), true, character);

				if(spawnClothingOnFloor) {
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_TIGHTS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_THIGHHIGH_SOCKS, Colour.CLOTHING_WHITE, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_ROSE_GOLD, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_ANKLE_BOOTS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_LOW_TOP_SKATER_SHOES, Colour.CLOTHING_PINK_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_TAN, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_STILETTO_HEELS, Colour.CLOTHING_RED, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HAND_ELBOWLENGTH_GLOVES, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND_BOW, Colour.CLOTHING_PINK_LIGHT, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_HOTPANTS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_SKIRT, Colour.CLOTHING_PINK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_SCARF, Colour.CLOTHING_RED, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.STOMACH_UNDERBUST_CORSET, Colour.CLOTHING_BLACK, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_BLUE_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_GREEN, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_LONG_SLEEVE_DRESS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_CROPTOP, Colour.CLOTHING_PINK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_VIRGIN_KILLER_SWEATER, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_SLIP_DRESS, Colour.CLOTHING_RED, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_OPEN_CARDIGAN, Colour.CLOTHING_BLACK, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.ANKLE_BRACELET, Colour.CLOTHING_GOLD, false));
				}
				break;
				
			case FEMININE_STRONG:
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SLIP_DRESS, Colour.CLOTHING_RED, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TIGHTS, Colour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_STILETTO_HEELS, Colour.CLOTHING_RED, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.BASE_BLACK, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_HEART_NECKLACE, Colour.CLOTHING_GOLD, false), true, character);

				if(spawnClothingOnFloor) {
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_RED, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_TRAINER_SOCKS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.SOCK_THIGHHIGH_SOCKS, Colour.CLOTHING_WHITE, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_ROSE_GOLD, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_ANKLE_BOOTS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_LOW_TOP_SKATER_SHOES, Colour.CLOTHING_PINK_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_TAN, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HAND_ELBOWLENGTH_GLOVES, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND_BOW, Colour.CLOTHING_PINK_LIGHT, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_HOTPANTS, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_SKIRT, Colour.CLOTHING_PINK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false));
	
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.NECK_SCARF, Colour.CLOTHING_RED, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.STOMACH_UNDERBUST_CORSET, Colour.CLOTHING_BLACK, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_BLUE_LIGHT, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_GREEN, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_LONG_SLEEVE_DRESS, Colour.CLOTHING_BLACK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_CROPTOP, Colour.CLOTHING_PINK, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_VIRGIN_KILLER_SWEATER, Colour.CLOTHING_WHITE, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_SKATER_DRESS, Colour.CLOTHING_BLACK, false));
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_OPEN_CARDIGAN, Colour.CLOTHING_BLACK, false));
					
					
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false));
					Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.ANKLE_BRACELET, Colour.CLOTHING_GOLD, false));
				}
				break;
			default:
				break;
		}
		
		if(character.isPlayer() && character.getName().equals("James") && character.getSurname().equals("Bond")) {
			character.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MAIN_WESTERN_KKP));
		}
	}
	
	public static final DialogueNodeOld CHOOSE_APPEARANCE = new DialogueNodeOld("A Night Out", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "By the time the taxi finally pulls up to the museum, you're already almost five minutes late."
						+ " You'd promised your aunt Lily that you'd be here in time for her speech, and as you hurriedly pay the driver his fare and step out of the car, you hope that the opening event hasn't started yet."
					+ "</p>"
					+ "<p>"
						+ "The street lights flicker into life as you rush over to the entrance, illuminating your surroundings with a dull orange glow."
						+ " It only takes a moment before you're standing at the museum's front doors, where, much to your dismay, you see that a small queue has formed."
						+ " Having no choice but to step in line and wait your turn, you briefly glance over at the large glass windows of the building's modern facade to see your blurry reflection in the glass..."
					+ "</p>"
					+ "</br>"
					
					+ CharacterModificationUtils.getStartDateDiv()
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getGenderChoiceDiv()
						
						+ CharacterModificationUtils.getFemininityChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "You will be referred to as <span style='color:"+Main.game.getPlayer().getGender().getColour().toWebHexString()+";'>"
								+UtilText.generateSingularDeterminer(Main.game.getPlayer().getGender().getName())+ " " + Main.game.getPlayer().getGender().getName()+"</span>.</br>"
							+ "<i>You can change all gender names in the options menu.</i>"
						+ "</div>"
						
						+ CharacterModificationUtils.getOrientationChoiceDiv()
					
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Wait your turn, and hope that the event hasn't started yet.", CHOOSE_NAME);
				
			}
			else if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CONTENT_PREFERENCES);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_NAME = new DialogueNodeOld("A Night Out", "", true) {
		private static final long serialVersionUID = 1L;

		boolean unsuitableName = false, unsuitableSurname = false;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[npcMale.speech("+(Main.game.getPlayer().isFeminine()?"Miss":"Sir")+"?)]"
						+ " the doorman calls out to you, evidently having finished with the other people in the queue,"
						+ " [npcMale.speech(Do you have an invitation?)]"
					+ "</p>"
					+ "<p>"
						+ "You turn away from the glass and step forwards, smiling,"
						+ " [pc.speech(Yes, I have it right here... erm... hold on....)]"
					+ "</p>"
					+ "<p>"
						+ "Reaching into your "+(Main.game.getPlayer().isFeminine()?"purse":"pocket")+", you feel your heart start to race as you discover that the invitation isn't in there,"
						+ " [pc.speech(No, no, no! I must have left it in the taxi!)]"
					+ "</p>"
					+ "<p>"
						+ "[npcMale.speech(Well, don't worry,)]"
						+ " the man replies,"
						+ " [npcMale.speech(if you give me your name, I can check to make sure that you're on the list.)]"
					+ "</p>"
					+ "<p>"
						+ "Breathing a sigh of relief, you tell the man your name..."
					+ "</p>"
					+"</br>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>First name: </p>"
							+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='nameInput' value='"+ Main.game.getPlayer().getName()+ "'></form>"
							+ "</br>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Surname: </p>"
							+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ Main.game.getPlayer().getSurname()+ "'></form>"
						+ "</div>"
						+ "</br>"
						+ "<i>Your name must be between 2 and 16 characters long. You cannot use the square bracket characters or full stops. (Surname may be left blank.)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Invalid name.</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Invalid Surname.</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				
				return new ResponseEffectsOnly("Continue", "Use this name and continue to the final character creation screen."){
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
									|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 16
									|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+"))
								unsuitableName = true;
							else {
								unsuitableName = false;
							}
						}
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 16
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+")))
								unsuitableSurname = true;
							else {
								unsuitableSurname = false;
							}
						}
						
						if (unsuitableName || unsuitableSurname)  {
							Main.game.setContent(new Response("" ,"", CHOOSE_NAME));
							
						} else {
						
							Main.game.getPlayer().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
							getDressed();
							Main.game.setContent(new Response("" ,"", CHOOSE_ADVANCED_APPEARANCE));
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Random", "Generate a random name based on your gender.", CHOOSE_NAME){
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 16
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+")))
								unsuitableSurname = true;
							else {
								unsuitableSurname = false;
							}
						}
						if(!unsuitableSurname) {
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
						}

						Main.game.getPlayer().setName(new NameTriplet(Name.getRandomName(Main.game.getPlayer())));
					}
				};
				
			} else if (index == 3) {
				return new Response("Random Surname", "Generate a random surname.", CHOOSE_NAME){
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
									|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 16
									|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+"))
								unsuitableName = true;
							else {
								unsuitableName = false;
							}
						}
						if(!unsuitableName) {
							Main.game.getPlayer().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
						}
						
						Main.game.getPlayer().setSurname(Name.getRandomSurname());
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to gender selection.", CHOOSE_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE = new DialogueNodeOld("In the Museum", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[pc.speech(It's "+(Main.game.getPlayer().getSurname().length()!=0?"[pc.surname], [pc.name] [pc.surname]":"[pc.name]")+",)]"
						+ " you say, impatiently looking down at the man's clipboard as he scans through his list."
					+ "</p>"
					+ "<p>"
						+ "Finally, you see his finger trace over your name, and with a smile, he steps to one side and beckons you forwards,"
						+ " [npcMale.speech(Have a good evening "+(Main.game.getPlayer().getSurname().length()!=0
								?(Main.game.getPlayer().isFeminine()?"Ms.":"Mr.")+" [pc.surname]"
								:(Main.game.getPlayer().isFeminine()?"Miss":"Sir"))+".)]"
					+ "</p>"
					+ "<p>"
						+ "Thanking him, you hurry through the entranceway, and within moments, find yourself stepping into the museum's enormous central lobby."
						+ " Large banners have been hung from the upper floor's balconies; their bold font proudly declaring this to be the 'Akkadian Empire Exhibit: Opening Evening'."
						+ " On the far side of the grand hall, you see throngs of people surrounding a large stage, and you breathe a sigh of relief as you notice that it's currently empty."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Phew... I made it in time after all...)]"
					+ "</p>"
					+ "<p>"
						+ "As Lily's opening speech seems to be running just as late as you are, you decide to step over to a nearby mirror to make sure that you're looking presentable..."
					+ "</p>"
					+ "</br>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Appearance</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>"
					+ "</br>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>You can modify your appearance by entering each of the sub-menus below. Once you're happy with how you look, press the 'Check clothes' option.</i>"
					+ "</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Check clothes", "Your clothes are a little messy after rushing here. Tidy yourself up before proceeding to the main stage.", InventoryDialogue.INVENTORY_MENU) {
					@Override
					public void effects() {
						equipPiercings();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
					}
				};
				
			} else if (index == 2) {
				return new Response("Core", "Enter the customisation menu for all of your body's core aspects.", CHOOSE_ADVANCED_APPEARANCE_CORE);
				
			} else if (index == 3) {
				return new Response("Face", "Enter the customisation menu for aspects related to your face.", CHOOSE_ADVANCED_APPEARANCE_FACE);
				
			} else if (index == 4) {
				return new Response("Hair", "Enter the customisation menu for your hair.", CHOOSE_ADVANCED_APPEARANCE_HAIR);
				
			} else if (index == 5) {
				return new Response("Breasts", "Enter the customisation menu for your breasts.", CHOOSE_ADVANCED_APPEARANCE_BREASTS);
				
			} else if (index == 6) {
				return new Response("Ass & Hips", "Enter the customisation menu for aspects related to your ass, hips, and anus.", CHOOSE_ADVANCED_APPEARANCE_ASS);
				
			} else if (index == 7) {
				return new Response((Main.game.getPlayer().hasPenis()?"Penis":"Vagina"), "Enter the customisation menu for aspects related to your "+(Main.game.getPlayer().hasPenis()?"penis":"vagina")+".", CHOOSE_ADVANCED_APPEARANCE_GENITALS);
				
			} else if (index == 8) {
				return new Response("Piercings", "Enter the customisation menu for body piercings.", CHOOSE_ADVANCED_APPEARANCE_PIERCINGS);
				
			} else if (index == 9) {
				return new Response("Makeup", "Enter the customisation menu for makeup.", CHOOSE_ADVANCED_APPEARANCE_COSMETICS);
				
			} else if (index == 10) {
				return new Response("Extra hair", "Enter the customisation menu for facial, pubic, and body hair.", CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR);
				
			} else if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_NAME);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_CORE = new DialogueNodeOld("Core Body Appearance", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getHeightChoiceDiv()
					
					+ CharacterModificationUtils.getKatesDivNaturalCoverings(false, BodyCoveringType.HUMAN, "Skin Colour", "The colour of the skin that's covering your body.", false, false)
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getBodySizeChoiceDiv()
						
						+ CharacterModificationUtils.getMuscleChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "Your muscle and body size values result in your appearance being:</br>"
							+ "<b style='color:"+Main.game.getPlayer().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false))+"</b>"
						+ "</div>"
					
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_FACE = new DialogueNodeOld("Face Appearance", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"

					+ CharacterModificationUtils.getLipSizeDiv()
					
					+ CharacterModificationUtils.getLipPuffynessDiv()

					+ CharacterModificationUtils.getKatesDivNaturalCoverings(false, BodyCoveringType.EYE_HUMAN, "Iris Colour", "The colour of your eye's irises.", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_HAIR = new DialogueNodeOld("Hair Appearance", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"

					+ CharacterModificationUtils.getKatesDivHairLengths(false, "Hair Length", "Choose how long your hair is.")
					
					+ CharacterModificationUtils.getKatesDivHairStyles(false, "Hair Style", "Choose your hair style. Certain hair styles are unavailable at shorter hair lengths.")

					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.HAIR_HUMAN, "Hair Colour", "The colour of your hair.", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_BREASTS = new DialogueNodeOld("Breasts Appearance", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getBreastSizeDiv()
					
					+ CharacterModificationUtils.getBreastShapeDiv()
					
					+ CharacterModificationUtils.getNippleSizeDiv()
					
					+ CharacterModificationUtils.getAreolaeSizeDiv()
					
					+ CharacterModificationUtils.getNipplePuffynessDiv()
					
					+ CharacterModificationUtils.getLactationDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_ASS = new DialogueNodeOld("Ass Appearance", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getAssSizeDiv()
					
					+ CharacterModificationUtils.getHipSizeDiv()
					
					+ CharacterModificationUtils.getBleachedAnusDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_GENITALS = new DialogueNodeOld("Genitals Appearance", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			if(Main.game.getPlayer().hasPenis()) {
				return "Penis Appearance";
			} else {
				return "Vagina Appearance";
			}
		}
		
		@Override
		public String getHeaderContent() {
			if(Main.game.getPlayer().hasPenis()) {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>All of these options can be influenced later on in the game.</i>"
						+ "</div>"
							
							+ CharacterModificationUtils.getPenisSizeDiv()
							
							+ CharacterModificationUtils.getTesticleSizeDiv()
							
							+ CharacterModificationUtils.getCumProductionDiv();
				
			} else {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>All of these options can be influenced later on in the game.</i>"
						+ "</div>"
	
							+ CharacterModificationUtils.getVaginaCapacityDiv()
							
							+ CharacterModificationUtils.getLabiaSizeDiv()
							
							+ CharacterModificationUtils.getClitorisSizeDiv();
				
			}
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_PIERCINGS = new DialogueNodeOld("Piercings", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"
						
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.EAR, "Ear Piercing", "Ears are the most common area of the body that are pierced, and enable the equipping of earrings and other ear-related jewellery.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NOSE, "Nose Piercing", "Having a nose piercing allows you to equip jewellery such as nose rings or studs.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.LIP, "Lip Piercing", "Lip piercings allow you to wear lip rings.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NAVEL, "Navel Piercing", "Getting your navel (belly button) pierced allows you to equip navel-related jewellery.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.TONGUE, "Tongue Piercing", "Getting a tongue piercing will allow you to equip tongue bars.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NIPPLE, "Nipple Piercing", "Nipple piercings will allow you to equip nipple bars.")
					
					+(Main.game.getPlayer().hasPenis()?CharacterModificationUtils.getKatesDivPiercings(PiercingType.PENIS, "Penis Piercing", "Having a penis piercing will allow you to equip penis-related jewellery."):"")
					
					+(Main.game.getPlayer().hasVagina()?CharacterModificationUtils.getKatesDivPiercings(PiercingType.VAGINA, "Vagina Piercing", "Having a vagina piercing will allow you to equip vagina-related jewellery."):"");
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_COSMETICS = new DialogueNodeOld("Cosmetics", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>All of these options can be influenced later on in the game.</i>"
					+ "</div>"
							
					+CharacterModificationUtils.getKatesDivCoverings(
							false, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, false)
					
					+CharacterModificationUtils.getKatesDivCoverings(
							false, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, false)

					+CharacterModificationUtils.getKatesDivCoverings(
							false, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, false)

					+CharacterModificationUtils.getKatesDivCoverings(
							false, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, false)

					+CharacterModificationUtils.getKatesDivCoverings(
							false, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, false)

					+CharacterModificationUtils.getKatesDivCoverings(
							false, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR = new DialogueNodeOld("Body Hair", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
												+ "<i>All of these options can be influenced later on in the game.</i>"
											+ "</div>");
			
			if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivNaturalCoverings(
						false, Main.game.getPlayer().getBodyHairCoveringType(), "Body hair", "This is the hair that covers all areas other than the head.", false, false));
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Body hair", "This is the hair that covers all areas other than the head.", "All extra body hair options are disabled. You will not see any extra hair content."));
				
				return UtilText.nodeContentSB.toString();
			}
			
			if(Main.game.isFacialHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair("Facial hair", "The body hair found on your face. Feminine characters cannot grow facial hair."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Facial hair", "The body hair found on your face. Feminine characters cannot grow facial hair.", "Facial hair is currently disabled in the options. You will not see any facial hair content while it is disabled."));
			}
			
			if(Main.game.isPubicHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair("Pubic hair", "The body hair found in the genital area; located on and around your sex organs and crotch."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Pubic hair", "The body hair found in the genital area; located on and around your sex organs and crotch.", "Pubic hair is currently disabled in the options. You will not see any pubic hair content while it is disabled."));
			}
			
			if(Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getKatesDivUnderarmHair("Underarm hair", "The body hair found in your armpits.")
						+ CharacterModificationUtils.getKatesDivAssHair("Ass hair", "The body hair found around your asshole."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Body hair", "The body hair found in your armpits and around your asshole.", "Body hair is currently disabled in the options. You will not see any underarm or ass hair content while it is disabled."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static String getCheckingClothingDescription() {
		return "<div class='container-full-width' style='background:transparent;'>"
					+ "<p>"
						+ "There doesn't seem to be any sign of activity on the main stage, so, afforded a few more minutes, you decide to smarten up your clothes a little."
						+ " After all, this is a big evening for Lily, and you want her to see that you've put some effort into your appearance."
					+ "</p>"
					+ "<p>"
						+ "Turning this way and that to get a better look at yourself in the mirror, you begin to notice just how "+(Main.game.getPlayer().isFeminine()?"hot":"handsome")+" you're looking tonight..."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Why am I feeling so horny all of a sudden?)]"
					+ "</p>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Choose what you decided to wear to the museum.</i></br>"
						+ "<i>You'll need to be wearing some kind of footwear, as well as clothing that conceals your genitals and chest, before being able to proceed.</i>"
					+ "</div>"
				+ "</div>";
	}
	
	public static void moveNPCIntoPlayerTile() {
		if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasVagina())) {
			Main.game.getPrologueMale().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			
		} else {
			Main.game.getPrologueFemale().setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		}
	}
	
	public static void moveNPCOutOfPlayerTile() {
		Main.game.getPrologueMale().setLocation(WorldType.JUNGLE, PlaceType.JUNGLE_CLUB, false);
		Main.game.getPrologueFemale().setLocation(WorldType.JUNGLE, PlaceType.JUNGLE_CLUB, false);
	}
	
	private static boolean femalePrologueNPC() {
		return Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasPenis());
	}
	
	public static final DialogueNodeOld CHOOSE_BACKGROUND = new DialogueNodeOld("In the Museum", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "Satisfied with your appearance, you turn away from the mirror and begin to walk towards the main stage."
						+ " With each step you take, you inexplicably find yourself getting more and more turned on, and by the time you've barely covered half the distance to the bustling crowd of visitors,"
							+(Main.game.getPlayer().hasPenis()
									?" you're struggling to keep yourself from getting an erection."
									:" you can feel your pussy getting wet from arousal.")+"."
					+ "</p>"
					+ "<p>"
						+ "Ducking behind a nearby pillar, you shake your head to try and dislodge the dirty thoughts that are starting to seep into your mind."
						+ " As you lean back against the cold stone and take a deep breath, a voice suddenly interrupts your thoughts,");
			
			if(!femalePrologueNPC()) {
				UtilText.nodeContentSB.append(" [prologueMale.speech(Taking a break from the crowds as well?)]"
						+ "</p>"
						+ "<p>"
							+ "Turning around, you see a tall, handsome-looking man, who must be only a couple of years older than you, giving you the most charming smile you've ever seen."
							+ " Before you know what you're doing, your eyes are travelling up and down every inch of his manly, muscular body, and you only just manage to stop yourself from letting out a desperate little whine."
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(Focus [pc.name], focus!)] you think, trying to act as casual as possible as you smile back at the stranger before you."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Actually,)] you say, [pc.speech(I've only just arrived. I thought I was going to be late, but it looks like nothing's started yet.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(Ah, you must have just missed the announcement,)] he replies, [prologueMale.speech(the opening speech is being delayed by half an hour."
								+ " I tried hanging around in that crowd back there, but I'm no historian, and most of the conversation is pretty dry...)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Haha,)]"
							+ " you laugh, desperately trying not to imagine how he looks naked,"
							+ " [pc.speech(I know <i>exactly</i> what you mean. My aunt is the lady giving the opening speech, and every time I meet her friends from the museum, I can never follow their conversations."
									+ " Well, apart from Arthur that is. He's closer to our age, and is really easy-going and fun to talk to.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(Hah! You know Arthur? I'm here by his invitation. He and I go way back,)]"
							+ " the man cheerly replies, his smile causing your heart to race,"
							+ " [prologueMale.speech(I'm [prologueMale.name] by the way, pleased you meet you "+(Main.game.getPlayer().isFeminine()?"Ms. ...?":"Mr. ...?")+")]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Likewise,)] you respond, shaking his offered hand while trying not to think of how powerful and dominant his grip is, [pc.speech(I'm [pc.Name].)]"
						+ "</p>"
						+ "<p>"
							+ "You and [prologueMale.name] continue talking with one another as you wait for the presentation to start."
							+ " Before long, the subject shifts to work, and you find out that he's an airline pilot, based out of the airport on the city's outskirts."
							+ " Conversation then moves on to what it is you do, and you end up talking about that for a little while..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(" [prologueFemale.speech(Taking a break from the crowds as well?)]"
						+ "</p>"
						+ "<p>"
							+ "Turning around, you see a beautiful woman, who looks to be about the same age as you, giving you the most stunning smile you've ever seen."
							+ " Before you know what you're doing, your eyes are travelling up and down every inch of her curvy, womanly body, and you only just manage to stop yourself from letting out a hungry groan."
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(Focus [pc.name], focus!)] you think, trying to act as casual as possible as you smile back at the stranger before you."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Actually,)] you say, [pc.speech(I've only just arrived. I thought I was going to be late, but it looks like nothing's started yet.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(Ah, you must have just missed the announcement,)] she replies, [prologueFemale.speech(the opening speech is being delayed by half an hour."
								+ " I tried hanging around in that crowd back there, but I'm no historian, and most of the conversation is pretty dry...)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Haha,)]"
							+ " you laugh, desperately trying not to imagine how she looks naked,"
							+ " [pc.speech(I know <i>exactly</i> what you mean. My aunt is the lady giving the opening speech, and every time I meet her friends from the museum, I can never follow their conversations."
									+ " Well, apart from Arthur that is. He's closer to our age, and is really easy-going and fun to talk to.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(Oh! You know Arthur? I'm here by his invitation, actually. He and I go way back,)]"
							+ " the woman cheerly replies, her smile causing your heart to race,"
							+ " [prologueFemale.speech(I'm [prologueFemale.name] by the way, pleased you meet you "+(Main.game.getPlayer().isFeminine()?"Ms. ...?":"Mr. ...?")+")]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Likewise,)] you respond, shaking her offered hand while trying not to think of how soft and delicate her skin is, [pc.speech(I'm [pc.Name].)]"
						+ "</p>"
						+ "<p>"
							+ "You and [prologueFemale.name] continue talking with one another as you wait for the presentation to start."
							+ " Before long, the subject shifts to work, and you find out that she's training to become a doctor, and is studying here at the city's university."
							+ " Conversation then moves on to what it is you do, and you end up talking about that for a little while..."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
												+ "<i>This is still a placeholder. I'm planning on adding a lot of backgrounds for you to choose from!</i>"
											+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Back", "Return to clothing selection.") {
					@Override
					public void effects() {
						moveNPCOutOfPlayerTile();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
					}
				};
				
			} else if (index <= History.getAvailableHistories(Main.game.getPlayer()).size()) {
				return new Response(Util.capitaliseSentence(History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getName()),
						History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getDescriptionPlayer()
						+ (History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getModifiersAsStringList().length() == 0
							? ""
							: "</br>" + History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getModifiersAsStringList()), CHOOSE_SEX_EXPERIENCE){
					@Override
					public void effects() {
						Main.game.getPlayer().setHistory(History.getAvailableHistories(Main.game.getPlayer()).get(index - 1));
						// Remove attribute gain sentences in the start game screen:
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	//TODO
	
	private static void resetVirginity() {
		if(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)>0) {
			Main.game.getPlayer().setAttribute(Attribute.CORRUPTION, 0);
		}
		
		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), null);

		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), null);

		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), null);

		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), null);

		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), null);
		
		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), null);

		Main.game.getPlayer().setSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 0);
		Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), null);
		
		Main.game.getPlayer().setVaginaVirgin(true);
		Main.game.getPlayer().setAssVirgin(true);
		Main.game.getPlayer().setFaceVirgin(true);
	}
	
	public static final DialogueNodeOld CHOOSE_SEX_EXPERIENCE = new DialogueNodeOld("Start", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "As the two of you continue to talk, you find yourself getting more and more turned on."
							+ " What's more, you begin to notice that [prologueFemale.name]'s cheeks are starting to flush red, and she keeps on glancing hungrily down at your body when she thinks that you aren't looking."
						+ "</p>"
						+ "<p>"
							+ "As final evidence that she's getting just as turned on as you are, she starts openly talking about her sex life."
							+ " To begin with, you're a little taken aback at her openness, but the more she talks, the more comfortable you find yourself with talking to this relative stranger about sex."
						+ "</p>"
						+ "<p>"
							+ "And so, after talking with [prologueFemale.name] for no longer than ten minutes, you're telling her every little detail about your sexual experiences..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "As the two of you continue to talk, you find yourself getting more and more turned on."
							+ " What's more, you begin to notice that [prologueMale.name]'s cheeks are starting to flush red, and he keeps on glancing hungrily down at your body when he thinks that you aren't looking."
						+ "</p>"
						+ "<p>"
							+ "As final evidence that he's getting just as turned on as you are, he starts openly talking about his sex life."
							+ " To begin with, you're a little taken aback at his openness, but the more he talks, the more comfortable you find yourself with talking to this relative stranger about sex."
						+ "</p>"
						+ "<p>"
							+ "And so, after talking with [prologueMale.name] for no longer than ten minutes, you're telling him every little detail about your sexual experiences..."
						+ "</p>");
			}

			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
												+ "<i>This is a <b>temporary placeholder</b>. I'm planning on adding a detailed menu for you to choose your sexual experiences from!</i>"
											+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Virgin", "You still have your virginity.", START_GAME){
					@Override
					public void effects() {
						resetVirginity();
					}
				};
				
			} else if (index == 2) {
				return new Response("Limited experience",
						(Main.game.getPlayer().hasVagina()
								?"You have lost your vaginal virginity (but not your anal virginity), and have only had sex with one person."
								:"You have lost your virginity (but not your anal virginity), and have only had sex with one person."),
						START_GAME){
					@Override
					public void effects() {
						resetVirginity();
						
						if(Main.game.getPlayer().hasVagina()) {
							
							String virginityLossText = Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC?"your girlfriend":"your boyfriend";
							
							Main.game.getPlayer().setVaginaVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 10 + Util.random.nextInt(20));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 5 + Util.random.nextInt(10));

							Main.game.getPlayer().setFaceVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 10 + Util.random.nextInt(20));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 5 + Util.random.nextInt(10));

							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 10 + Util.random.nextInt(20));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 5 + Util.random.nextInt(10));
							
						} else {
							String virginityLossText = Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC?"your boyfriend":"your girlfriend";
							
							if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 10 + Util.random.nextInt(20));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 5 + Util.random.nextInt(10));

								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 10 + Util.random.nextInt(20));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 5 + Util.random.nextInt(10));

								Main.game.getPlayer().setFaceVirgin(false);
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 10 + Util.random.nextInt(20));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 5 + Util.random.nextInt(10));
								
							} else {
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), 10 + Util.random.nextInt(20));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), 5 + Util.random.nextInt(10));

								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 10 + Util.random.nextInt(20));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 5 + Util.random.nextInt(10));
								
							}
						}
						Main.game.getPlayer().setAttribute(Attribute.CORRUPTION, 5);

					}
				};
				
			} else if (index == 3) {
				return new Response("Moderate experience",
						(Main.game.getPlayer().hasVagina()
								?"You have lost your vaginal virginity (but not your anal virginity), and have had sex with quite a few people."
								:"You have lost your virginity (but not your anal virginity), and have had sex with quite a few people."),
						START_GAME){
					@Override
					public void effects() {
						resetVirginity();
						
						if(Main.game.getPlayer().hasVagina()) {
							
							String virginityLossText = Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC?"your first girlfriend":"your first boyfriend";
							
							Main.game.getPlayer().setVaginaVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 50 + Util.random.nextInt(30));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 25 + Util.random.nextInt(15));

							Main.game.getPlayer().setFaceVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 50 + Util.random.nextInt(30));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 25 + Util.random.nextInt(15));

							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 50 + Util.random.nextInt(30));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 25 + Util.random.nextInt(15));
							
						} else {
							String virginityLossText = Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC?"your first boyfriend":"your first girlfriend";
							
							if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 25 + Util.random.nextInt(15));

								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 25 + Util.random.nextInt(15));

								Main.game.getPlayer().setFaceVirgin(false);
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 25 + Util.random.nextInt(15));
								
							} else {
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PLAYER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PLAYER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PLAYER), 25 + Util.random.nextInt(15));

								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 25 + Util.random.nextInt(15));
								
							}
						}
						Main.game.getPlayer().setAttribute(Attribute.CORRUPTION, 10);

					}
				};
				
			} else if (index == 4) {
				return new Response("Highly experienced",
						(Main.game.getPlayer().hasVagina()
								?"You have lost both your vaginal virginity and anal virginity, and have had sex with more people than you can count."
								:(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC
										?"You have lost your virginity (but not your anal virginity), and have had sex with more people than you can count"
										:"You have lost both your virginity and anal virginity, and have had sex with more people than you can count.")),
						START_GAME){
					@Override
					public void effects() {
						resetVirginity();
						
						if(Main.game.getPlayer().hasVagina()) {
							
							String virginityLossText = Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC?"some girl in her apartment":"some guy in a club's restroom";
							
							Main.game.getPlayer().setVaginaVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 50 + Util.random.nextInt(30));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER), 25 + Util.random.nextInt(15));

							Main.game.getPlayer().setFaceVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 50 + Util.random.nextInt(30));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 25 + Util.random.nextInt(15));

							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 50 + Util.random.nextInt(30));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 25 + Util.random.nextInt(15));

							Main.game.getPlayer().setAssVirgin(false);
							Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), virginityLossText);
							Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 20 + Util.random.nextInt(10));
							Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 10 + Util.random.nextInt(5));
							
						} else {
							String virginityLossText = Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC?"some guy in a club's restroom":"some girl in her apartment";
							
							if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC) {
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.VAGINA_PARTNER), 25 + Util.random.nextInt(15));

								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 25 + Util.random.nextInt(15));
								
							} else {
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.ANUS_PARTNER), 25 + Util.random.nextInt(15));

								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PLAYER, OrificeType.MOUTH_PARTNER), 25 + Util.random.nextInt(15));

								Main.game.getPlayer().setFaceVirgin(false);
								Main.game.getPlayer().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), virginityLossText);
								Main.game.getPlayer().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 50 + Util.random.nextInt(30));
								Main.game.getPlayer().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 25 + Util.random.nextInt(15));
								
							}
						}
						Main.game.getPlayer().setAttribute(Attribute.CORRUPTION, 15);

					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to background selection.", CHOOSE_BACKGROUND){
					@Override
					public void effects() {
						Main.game.getPlayer().setHistory(History.NONE);
						Main.game.getPlayer().incrementHealth(1000);
						Main.game.getPlayer().incrementMana(1000);
						
						// Remove attribute gain sentences in the start game screen:
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld START_GAME = new DialogueNodeOld("Start", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "As the two of you talk about your sexual backgrounds, you find yourself increasingly unable to control your arousal, and from the desperate look in [prologueFemale.name]'s eyes, she's having the same problem as you are."
							+ " Stepping forwards, she half-speaks, half-moans,"
							+ " [prologueFemale.speech(All this talk's got me pretty turned on... How about you and I-)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech([pc.Name]! There you are! I've been looking everywhere for you,)]"
							+ " a very familiar, and at this moment very unwanted, voice calls out,"
							+ " [lilaya.speech(sorry if I'm interrupting, but I really need your help!)]"
						+ "</p>"
						+ "<p>"
							+ "Turning towards the unmistakable voice of your aunt Lily, you suppress a frustrated sigh,"
							+ " [pc.speech(Is this about the opening being delayed?)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(I see you're going to be busy, perhaps I'll catch you later,)] [prologueFemale.name] says, before walking off and leaving you alone with your aunt."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "As the two of you talk about your sexual backgrounds, you find yourself increasingly unable to control your arousal, and from the desperate look in [prologueMale.name]'s eyes, he's having the same problem as you are."
							+ " Stepping forwards, he half-speaks, half-groans,"
							+ " [prologueMale.speech(All this talk's got me pretty turned on... How about you and I-)]"
						+ "</p>"
						+ "<p>"
							+ "[lilaya.speech([pc.Name]! There you are! I've been looking everywhere for you,)]"
							+ " a very familiar, and at this moment very unwanted, voice calls out,"
							+ " [lilaya.speech(sorry if I'm interrupting, but I really need your help!)]"
						+ "</p>"
						+ "<p>"
							+ "Turning towards the unmistakable voice of your aunt Lily, you suppress a frustrated sigh,"
							+ " [pc.speech(Is this about the opening being delayed?)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(I see you're going to be busy, perhaps I'll catch you later,)] [prologueMale.name] says, before walking off and leaving you alone with your aunt."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append("<p>"
					+ "[lilaya.speech(Erm... goodbye!)] Lily awkwardly calls out, completely oblivious as to what was just about to happen, before turning back to you,"
					+ " [lilaya.speech(Yes! This is to do with the delay! Arthur's gone missing, and I can't start without him! Everyone's crowding me asking questions, so I can't go out and look for him myself."
						+ " Please, please <i>please</i> can you go and find him for me?)]"
				+ "</p>"
				+ "<p>"
					+ "Lily's puppy-dog eyes always were too much for you to say no to, and you find yourself agreeing to do as she asks,"
					+ " [pc.speech(Ok Lily, I'll find him for you, don't worry.)]"
				+ "</p>"
				+ "<p>"
					+ "As Lily starts thanking you, someone calls out from behind her, asking her to return to the stage."
					+ " Thanking you once more, she then rushes off, leaving you to do as you said you would..."
				+ "</p>"
				+"<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Still a <b>temporary placeholder</b>. You'll be able to have sex with "+(femalePrologueNPC()?"[prologueFemale.name]":"[prologueMale.name]")+" soon!</i>"
				+ "</div>"
				+ "</br>"
				+ "<div class='container-full-width'>"
					+ "<h5 style='text-align:center;'>Appearance</h5>"
					+ Main.game.getPlayer().getBodyDescription()
				+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Start", "Use this character and start the game at the very beginning, with trying to find Arthur in the museum.", PrologueDialogue.INTRO_2){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {
						
						Main.game.setRenderMap(true);
						Main.game.setInNewWorld(true);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.getPlayer().setMoney(40);
						Main.game.getPlayer().equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE));
						
						applyGameStart();
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(new GenericPlace(PlaceType.LILAYA_HOME_ROOM_PLAYER)),
								true);

					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to background selection.", CHOOSE_SEX_EXPERIENCE){
					@Override
					public void effects() {
						resetVirginity();
						
						// Remove attribute gain sentences in the start game screen:
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	private static void applyGameStart() {
		Main.getProperties().addRaceDiscovered(Race.HUMAN);
		
		Main.game.getLilaya().setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);

		Main.game.clearTextStartStringBuilder();
		Main.game.clearTextEndStringBuilder();

		Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
		Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());
		
		Main.getProperties().addRaceDiscovered(Main.game.getLilaya().getRace());
		Main.getProperties().addRaceDiscovered(Main.game.getRose().getRace());

		Main.game.setWeather(Weather.MAGIC_STORM, 300);
		
		Main.game.getPlayerCell().resetInventory();
		moveNPCOutOfPlayerTile();
	}
	
	private static StringBuilder importSB;
	public static final DialogueNodeOld IMPORT_CHOOSE = new DialogueNodeOld("Import", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent(){
			importSB = new StringBuilder();

			importSB.append("<p style='text-align:center;'>"
					+ "These characters are being read from the 'data/characters' folder."
					+ " If you want to import a character from a previous version, follow these steps:</br></br>"
					+ "<b>1.</b> Open up the old game version, and export your old character (menu -> options -> export).</br>"
					+ "<b>2.</b> Copy the exported .xml file (in the old version's <i>data/characters</i> folder).</br>"
					+ "<b>3.</b> Paste it into this version's <i>data/characters</i> folder.</br>"
					+ "<b>4.</b> Press 'Refresh', and your old character file should show up in the list below!</br></br>"
					+ "(If it doesn't work, please let me know as a comment on my blog, and I'll get it fixed ASAP!)"
					+ "</p>"
					+ "<p>"
					+ "<table align='center'>"
					+ "<tr>"
					+ "<th></th>"
					+ "<th>Name</th>"
					+ "<th></th>"
					+ "</tr>");
			
			int i=1;
			for(File f : Main.getCharactersForImport()){
				importSB.append(getImportRow(i, f.getName()));
				i++;
			}

			importSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");

			return importSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Refresh", "Refresh this page.", IMPORT_CHOOSE);
				
			} else if (index == 0) {
				return new ResponseEffectsOnly("Back", "Return to new game screen."){
					@Override
					public void effects() {
						Main.mainController.setAttributePanelContent("");
						Main.mainController.setButtonsContent("");
						
						Main.startNewGame(CharacterCreation.CONTENT_PREFERENCES);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	private static String getImportRow(int i, String name) {
		String baseName = name.substring(0, name.lastIndexOf('.'));
		
		return "<tr>"
				+ "<td>"
					+ i+"."
				+ "</td>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='character_import_" + baseName + "' style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Load</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNodeOld START_GAME_WITH_IMPORT = new DialogueNodeOld("Start game", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Imported Character";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "<b>Still TODO!</b>"
					+ "</p>"
					+ "<p>"
						+ "Meet Lily, she asks you to go and find Arthur."
					+ "</p>"
					+ "</br>"
					+"<details>"
						+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>Import Log</summary>"
						+ CharacterUtils.getCharacterImportLog()
					+ "</details>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Appearance</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Start", "Use this character and start the game at the very beginning.", PrologueDialogue.INTRO_2){
					@Override
					public void effects() {
						Main.game.getPlayer().resetAllQuests();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {
						Main.game.setRenderMap(true);
						Main.game.setInNewWorld(true);

						Main.game.getPlayer().resetAllQuests();
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						applyGameStart();
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(new GenericPlace(PlaceType.LILAYA_HOME_ROOM_PLAYER)),
								true);
					}
				};
				
			} else if (index == 0) {
				return new ResponseEffectsOnly("Back", "Return to new game screen."){
					@Override
					public void effects() {
						Main.mainController.setAttributePanelContent("");
						Main.mainController.setButtonsContent("");
						
						Main.startNewGame(CharacterCreation.CONTENT_PREFERENCES);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
