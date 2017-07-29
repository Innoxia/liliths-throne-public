package com.base.game.dialogue.story;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.base.game.Weather;
import com.base.game.character.CharacterUtils;
import com.base.game.character.History;
import com.base.game.character.Name;
import com.base.game.character.NameTriplet;
import com.base.game.character.QuestLine;
import com.base.game.character.QuestType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.gender.Gender;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.DamageType;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

/**
 * @since 0.1.0
 * @version 0.1.8
 * @author Innoxia
 */
public class CharacterCreationDialogue {

	public static final DialogueNodeOld CHARACTER_CREATION_START = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Agree", "You agree that you are the legal age to view pornographic material, and consent to being exposed to graphic content.", ALPHA_MESSAGE);
			} else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};

	public static final DialogueNodeOld ALPHA_MESSAGE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return Main.patchNotes;
			
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Start", "Proceed to character creation.", CHOOSE_BODY){
					@Override
					public void effects() {
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_HOODIE, false), true, Main.game.getPlayer());
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("Hero", "Heroine", "Heroine"));
						setSkin();
						setHair();
						setEyes();
						setBodyType();
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.game.getPlayer().setNewWeaponDiscovered(false);
						Main.game.getPlayer().setNewClothingDiscovered(false);
						Main.game.getPlayer().setNewItemDiscovered(false);
						Main.game.getPlayer().getItemsDiscovered().clear();
						Main.game.getPlayer().calculateStatusEffects(0);
					}
				};
				
			} else if (index == 2) {
				return new Response("Start (Import)", "Import a character from a previous version to use on game start.", IMPORT_CHOOSE);
				
			} else if (index == 3) {
				return new Response("Blog", "Opens the page:</br>" + "<i>https://lilithsthrone.blogspot.co.uk/</i></br>" + "<b>Externally</b> in your default browser.", ALPHA_MESSAGE){
					@Override
					public void effects() {
						try {
							Desktop.getDesktop().browse(new URI("https://lilithsthrone.blogspot.co.uk/"));
						} catch (IOException | URISyntaxException e) {
							e.printStackTrace();
						}
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};

	// Character creation:
	private static String[] bodyTypeMale = { "androgynous", "masculine", "very masculine" };
	private static Colour[] bodyColourMale = { Colour.ANDROGYNOUS, Colour.MASCULINE, Colour.MASCULINE_PLUS };
	private static String[] bodyTypeFemale = { "androgynous", "feminine", "very feminine" };
	private static Colour[] bodyColourFemale = { Colour.ANDROGYNOUS, Colour.FEMININE, Colour.FEMININE_PLUS };
	private static int bodyTypeIndex = 1, hairColourIndex = 0, eyeColourIndex = 0, skinColourIndex = 0;
	public static final DialogueNodeOld CHOOSE_BODY = new DialogueNodeOld("Choose your starting body", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Body<span style='color:"+Colour.TEXT_GREY+";'> &gt Attributes &gt Name &gt Confirm</span>";
		}

		@Override
		public String getHeaderContent() {
			return "<p>"
					+ "You're a young adult, in your mid-twenties. What sort of body do you have?"
					+ "</p>"
					+ "<p>"
					+ "<b>Note:</b> By default, androgynous bodies appear feminine or masculine based on their clothing. This can be changed in the options after the game starts (by accessing: Menu -> options -> Gender pronouns)."
					+ "</p>"
					+ "<p>"
					+ "<i>Hint: Hover over the part that says 'Level 1 Human' on the left of the screen to see your body's details.</i>"
					+ "</p>"

					+ "</br>"
					
					+ "<p style='text-align:center;'><b>Gender</b></br>"
					+ getGenderOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Femininity</b></br>"
					+ getBodyOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Skin colour</b></br>"
					+ getSkinOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Hair colour</b></br>"
					+ getHairOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eye colour</b></br>"
					+ getEyeOption()
					+ "</p>"
					
//					+ "</br>"
//					
//					+ "<p style='text-align:center;'><b>Sexual Orientation</b></br>"
//					+ getSexualOrientationOption()
//					+ "</p>"
					;
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Continue to next stage of character creation.", HISTORY_MENU){
					@Override
					public void effects() {
						unsuitableName = false;
					}
				};
				
			} else if (index == 2) {
				return new Response("Gender", "Cycle your starting gender.", CHOOSE_BODY){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getGender() == Gender.FEMALE)
							Main.game.getPlayer().setBody(Gender.MALE, RacialBody.HUMAN, RaceStage.HUMAN);
						else
							Main.game.getPlayer().setBody(Gender.FEMALE, RacialBody.HUMAN, RaceStage.HUMAN);

						setSkin();
						setHair();
						setEyes();
						setBodyType();
					}
				};
				
			} else if (index == 3) {
				return new Response("Femininity", "Cycle your femininity. If you appear feminine or androgynous, people will address you as a female. Otherwise, they will address you as a male.", CHOOSE_BODY){
					@Override
					public void effects() {
						bodyTypeIndex++;
						if (bodyTypeIndex > 2)
							bodyTypeIndex = 0;

						setBodyType();
					}
				};
				
			} else if (index == 4) {
				return new Response("Skin", "Cycle your skin colour.", CHOOSE_BODY){
					@Override
					public void effects() {
						skinColourIndex++;
						if (skinColourIndex >= RacialBody.HUMAN.getSkinType().getNaturalColours().size())
							skinColourIndex = 0;

						setSkin();
					}
				};
				
			} else if (index == 5) {
				return new Response("Hair", "Cycle your hair colour.", CHOOSE_BODY){
					@Override
					public void effects() {
						hairColourIndex++;
						if (hairColourIndex >= RacialBody.HUMAN.getHairType().getNaturalColours().size())
							hairColourIndex = 0;

						setHair();
					}
				};
				
			} else if (index == 6) {
				return new Response("Eyes", "Cycle your eye colour.", CHOOSE_BODY){
					@Override
					public void effects() {
						eyeColourIndex++;
						if (eyeColourIndex > 2)
							eyeColourIndex = 0;

						setEyes();
					}
				};
				
			}
//			else if (index == 7) {
//				return new Response("Orientation", "Cycle your sexual orientation. (Hover over the status effect on the left of the screen to see what each orientation means.)", CHOOSE_BODY){
//					@Override
//					public void effects() {
//						if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
//							Main.game.getPlayer().setSexualOrientation(SexualOrientation.GYNEPHILIC);
//							
//						} else if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC) {
//							Main.game.getPlayer().setSexualOrientation(SexualOrientation.AMBIPHILIC);
//							
//						} else {
//							Main.game.getPlayer().setSexualOrientation(SexualOrientation.ANDROPHILIC);
//							
//						}
//					}
//				};
//				
//			}
			else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};

	private static StringBuilder contentSB = new StringBuilder();

	private static String getGenderOption() {
		return (Main.game.getPlayer().getGender() == Gender.FEMALE ? ("<span class='option-disabled'>"
				+ Util.capitaliseSentence(Gender.MALE.getName())
				+ "</span> | "
				+ "<b style='color:"
				+ Colour.FEMININE
				+ ";'>"
				+ Util.capitaliseSentence(Gender.FEMALE.getName())
				+ "</b>")
				: ("<b style='color:"
						+ Colour.MASCULINE
						+ ";'>"
						+ Util.capitaliseSentence(Gender.MALE.getName())
						+ "</b> | "
						+ "<span class='option-disabled'>"
						+ Util.capitaliseSentence(Gender.FEMALE.getName())
						+ "</span>"));
	}

	private static String getSkinOption() {
		contentSB = new StringBuilder();
		int i = 0;
		for (Colour cs : RacialBody.HUMAN.getSkinType().getNaturalColours()) {
			if (Main.game.getPlayer().getSkinColour(RacialBody.HUMAN.getSkinType()) == cs)
				contentSB.append("<b style='color:" + cs + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
			else
				contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");

			if (i + 1 != RacialBody.HUMAN.getSkinType().getNaturalColours().size())
				contentSB.append(" | ");
			i++;
		}
		return contentSB.toString();
	}

	private static String getHairOption() {
		contentSB = new StringBuilder();
		int i = 0;
		for (Colour cs : RacialBody.HUMAN.getHairType().getNaturalColours()) {
			if (Main.game.getPlayer().getHairColour() == cs)
				contentSB.append("<b style='color:" + cs + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
			else
				contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");

			if (i + 1 != RacialBody.HUMAN.getHairType().getNaturalColours().size())
				contentSB.append(" | ");
			i++;
		}
		return contentSB.toString();
	}

	private static String getEyeOption() {
		contentSB = new StringBuilder();
		int i = 0;
		for (Colour cs : RacialBody.HUMAN.getEyeType().getNaturalColours()) {
			if (Main.game.getPlayer().getEyeColour() == cs)
				contentSB.append("<b style='color:" + cs + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
			else
				contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");

			if (i + 1 != RacialBody.HUMAN.getEyeType().getNaturalColours().size())
				contentSB.append(" | ");
			i++;
		}
		return contentSB.toString();
	}

	private static String getBodyOption() {
		contentSB = new StringBuilder();
		for (int j = 0; j < bodyTypeMale.length; j++) {
			if (Main.game.getPlayer().getGender() == Gender.FEMALE) {
				if (bodyTypeIndex == j)
					contentSB.append("<b style='color:" + bodyColourFemale[j] + ";'>" + Util.capitaliseSentence(bodyTypeFemale[j]) + "</b>");
				else
					contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(bodyTypeFemale[j]) + "</span>");
			} else {
				if (bodyTypeIndex == j)
					contentSB.append("<b style='color:" + bodyColourMale[j] + ";'>" + Util.capitaliseSentence(bodyTypeMale[j]) + "</b>");
				else
					contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(bodyTypeMale[j]) + "</span>");
			}

			if (j + 1 != bodyTypeMale.length)
				contentSB.append(" | ");
		}
		return contentSB.toString();
	}
	
//	public static String getSexualOrientationOption() {
//		if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
//			return "<span style='color:"+ SexualOrientation.ANDROPHILIC.getColour()+ ";'>"+ Util.capitaliseSentence(SexualOrientation.ANDROPHILIC.getName())+ "</span>"
//					+ " | <span class='option-disabled'>"+ Util.capitaliseSentence(SexualOrientation.GYNEPHILIC.getName())+ "</span>"
//					+ " | <span class='option-disabled'>"+ Util.capitaliseSentence(SexualOrientation.AMBIPHILIC.getName())+ "</span>";
//			
//		} else if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC) {
//			return "<span class='option-disabled'>"+ Util.capitaliseSentence(SexualOrientation.ANDROPHILIC.getName())+ "</span>"
//					+ " | <span style='color:"+ SexualOrientation.GYNEPHILIC.getColour()+ ";'>"+ Util.capitaliseSentence(SexualOrientation.GYNEPHILIC.getName())+ "</span>"
//					+ " | <span class='option-disabled'>"+ Util.capitaliseSentence(SexualOrientation.AMBIPHILIC.getName())+ "</span>";
//			
//		} else {
//			return "<span class='option-disabled'>"+ Util.capitaliseSentence(SexualOrientation.ANDROPHILIC.getName())+ "</span>"
//					+ " | <span class='option-disabled'>"+ Util.capitaliseSentence(SexualOrientation.GYNEPHILIC.getName())+ "</span>"
//					+ " | <span style='color:"+ SexualOrientation.AMBIPHILIC.getColour()+ ";'>"+ Util.capitaliseSentence(SexualOrientation.AMBIPHILIC.getName())+ "</span>";
//			
//		}
//	}

	private static void setSkin() {
		Main.game.getPlayer().setSkinColour(RacialBody.HUMAN.getSkinType(), RacialBody.HUMAN.getSkinType().getNaturalColours().get(skinColourIndex));
		Main.game.getLilaya().setSkinColour(RacialBody.HUMAN.getSkinType(), RacialBody.HUMAN.getSkinType().getNaturalColours().get(skinColourIndex));
	}

	private static void setHair() {
		Main.game.getPlayer().setHairColour(RacialBody.HUMAN.getHairType().getNaturalColours().get(hairColourIndex));
	}

	private static void setEyes() {
		Main.game.getPlayer().setEyeColour(RacialBody.HUMAN.getEyeType().getNaturalColours().get(eyeColourIndex));
	}

	private static void setBodyType() {
		if (Main.game.getPlayer().getGender() == Gender.FEMALE) {
			if (bodyTypeIndex == 0) {
				Main.game.getPlayer().setAssSize(AssSize.THREE_NORMAL.getValue());
				Main.game.getPlayer().setBreastSize(CupSize.AA.getMeasurement());
				Main.game.getPlayer().setFemininity(55);
			} else if (bodyTypeIndex == 1) {
				Main.game.getPlayer().setAssSize(AssSize.THREE_NORMAL.getValue());
				Main.game.getPlayer().setBreastSize(CupSize.C.getMeasurement());
				Main.game.getPlayer().setFemininity(70);
			} else {
				Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE.getValue());
				Main.game.getPlayer().setBreastSize(CupSize.DD.getMeasurement());
				Main.game.getPlayer().setFemininity(85);
			}
		} else {
			if (bodyTypeIndex == 0) {
				Main.game.getPlayer().setAssSize(AssSize.THREE_NORMAL.getValue());
				Main.game.getPlayer().setBreastSize(CupSize.AA.getMeasurement());
				Main.game.getPlayer().setFemininity(45);
				Main.game.getPlayer().setPenisSize(PenisSize.ONE_TINY.getMedianValue());
				Main.game.getPlayer().setTesticleSize(TesticleSize.ONE_TINY.getValue());
			} else if (bodyTypeIndex == 1) {
				Main.game.getPlayer().setAssSize(AssSize.TWO_SMALL.getValue());
				Main.game.getPlayer().setBreastSize(0);
				Main.game.getPlayer().setFemininity(30);
				Main.game.getPlayer().setPenisSize(PenisSize.TWO_AVERAGE.getMedianValue());
				Main.game.getPlayer().setTesticleSize(TesticleSize.TWO_AVERAGE.getValue());
			} else {
				Main.game.getPlayer().setAssSize(AssSize.TWO_SMALL.getValue());
				Main.game.getPlayer().setBreastSize(0);
				Main.game.getPlayer().setFemininity(15);
				Main.game.getPlayer().setPenisSize(PenisSize.THREE_LARGE.getMinimumValue());
				Main.game.getPlayer().setTesticleSize(TesticleSize.TWO_AVERAGE.getValue());
			}
		}
		Main.game.getPlayer().setMana(1000);
	}

	public static final DialogueNodeOld HISTORY_MENU = new DialogueNodeOld("Choose your background", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "<span style='color:"+Colour.TEXT_GREY+";'>Body &gt </span>Attributes <span style='color:"+Colour.TEXT_GREY+";'>&gt Name &gt Confirm</span>";
		}
		
		@Override
		public String getContent() {
			return "What sort of person are you?</br></br>"

					+ "Your background will influence your starting core attributes. While attributes can be temporarily modified by many items in the game, core attributes are a lot harder to change.</br></br>"

					+ "Attribute-based perks can be earnt through core attribute values, plus any bonus modifiers you have."
					+ " For example, if your items boost your strength to 100, you will get the maximum strength status effect.</br></br>"

					+ "You can hover your mouse over the attributes panel on the left of the screen for information relating to your attributes."
					+ " There are also many secondary attributes, which you can check at any time by hovering over your name at the top-left (or by accessing your phone after the game starts).";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to body selection.", CHOOSE_BODY);
				
			} else if (index <= History.getAvailableHistories(Main.game.getPlayer()).size()) {
				return new Response(History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getName(),
						History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getDescriptionPlayer()
						+ (History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getModifiersAsStringList().length() == 0
							? ""
							: "</br>" + History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getModifiersAsStringList()), CHOOSE_NAME){
					@Override
					public void effects() {
						Main.game.getPlayer().setHistory(History.getAvailableHistories(Main.game.getPlayer()).get(index - 1));
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};

	private static boolean unsuitableName = false;
	public static final DialogueNodeOld CHOOSE_NAME = new DialogueNodeOld("Name", "Back to name selection.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "<span style='color:"+Colour.TEXT_GREY+";'>Body &gt Attributes &gt</span> Name <span style='color:"+Colour.TEXT_GREY+";'>&gt Confirm</span>";
		}

		@Override
		public String getHeaderContent() {
			return ("<p>"
					+ "Your name must be between 2 and 16 characters long. It can only include the standard letters a-z and their respective capitals."
					+ "</p>"

					+ "<p style='padding:0;margin:0;text-align:center;'>Your name:</p>"
					+ "<form style='padding:0;margin:0;text-align:center;'><input type='text' id='nameInput' value='"
					+ Main.game.getPlayer().getName()
					+ "'>"
					+ "</form>"
					+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"
							+ Colour.GENERIC_BAD
							+ ";'>Invalid name.</b></p>" : "")

					+ "<p id='hiddenFieldName' style='display:none;'></p>");
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				
				if(Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')")!=null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");

					if(Main.mainController.getWebEngine().getDocument()!=null) {
						if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 16
								|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[a-zA-Z]+"))
							unsuitableName = true;
						else {
							unsuitableName = false;
						}
					}
					
				} else {
					return new Response("Continue", "Use this name and continue to the final character creation screen.", START_GAME);
				}

				if (unsuitableName)
					return new Response("Continue", "Use this name and continue to the final character creation screen.", CHOOSE_NAME);
				else
					return new Response("Continue", "Use this name and continue to the final character creation screen.", START_GAME){
					@Override
					public void effects() {
						Main.game.getPlayer().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
					}
				};
				
			} else if (index == 2) {
				return new Response("Random", "Generate a random name based on your gender.", CHOOSE_NAME){
					@Override
					public void effects() {
						unsuitableName = false;

						Main.game.getPlayer().setName(new NameTriplet(Name.getRandomName(Main.game.getPlayer())));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to background selection.", HISTORY_MENU);
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};

	public static final DialogueNodeOld START_GAME = new DialogueNodeOld("Start game", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "<span style='color:"+Colour.TEXT_GREY+";'>Body &gt Attributes &gt Name &gt</span> Confirm";
		}
		
		@Override
		public String getContent() {
			return Main.game.getPlayer().getBodyDescription();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Start", "Use this character and start the game at the very beginning.", PrologueDialogue.INTRO){
					@Override
					public void effects() {
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {

						Main.game.setRenderMapSection(true);
						applyGameStart();

						Main.game.getPlayer().resetInventory();

						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE)
							Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_HOODIE, Colour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK, false), true, Main.game.getPlayer());

						Main.game.getPlayer().equipMainWeapon(WeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE), false);

						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();

						Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
						Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());

						Main.game.setWeather(Weather.MAGIC_STORM, 300);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
								true);

						Main.game.getPlayer().setMoney(40);
					}
				};
				
			}
//			else if (index == 5) {
//
//				return new Response("Non-con:", "Toggle non-consensual content. This can be enabled or disabled in the options menu at any point during the game.", START_GAME){
//					@Override
//					public String getTitle() {
//						return "Non-con: "+(Main.getProperties().nonConContent?"[style.boldArcane(ON)]":"[style.boldDisabled(OFF)]");
//					}
//					@Override
//					public void effects() {
//						Main.getProperties().nonConContent = !Main.getProperties().nonConContent;
//						Main.saveProperties();
//					}
//				};
//
//			}
			else if (index == 0) {
				return new Response("Back", "Return to name selection.", CHOOSE_NAME){
					@Override
					public void effects() {
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

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};

	private static void applyGameStart() {
		Main.game.getPlayer().addRaceDiscovered(Race.HUMAN);
		Main.game.getPlayer().getItemsDiscovered().add(ItemType.CONDOM);
		
		// Main.game.getPlayer().addSideQuest(QuestLine.SIDE_ITEM_DISCOVERY);
		// Main.game.getPlayer().addSideQuest(QuestLine.SIDE_RACE_DISCOVERY);
//		Main.game.getPlayer().setMainQuestUpdated(false);
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Refresh", "Refresh this page.", IMPORT_CHOOSE);
				
			} else if (index == 0) {
				return new ResponseEffectsOnly("Back", "Return to new game screen."){
					@Override
					public void effects() {
						Main.mainController.setAttributePanelContent("");
						Main.mainController.setButtonsContent("");
						Main.mainController.setMapViewContent("");
						Main.mainController.setMapTitleContent("");
						
						Main.startNewGame(CharacterCreationDialogue.CHARACTER_CREATION_START);
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
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
					+ "<div class='saveLoadButton' id='character_import_" + baseName + "' style='color:"+Colour.GENERIC_GOOD+";'>Load</div>"
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
			return Main.game.getPlayer().getBodyDescription()
					+"<details>"
					+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour() + ";'>Import Log</summary>"
					+ CharacterUtils.getCharacterImportLog()
					+ "</details>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Start", "Use this character and start the game at the very beginning.", PrologueDialogue.INTRO){
					@Override
					public void effects() {
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {

						Main.game.setRenderMapSection(true);
						applyGameStart();

						Main.game.getPlayer().resetInventory();

						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE)
							Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_HOODIE, Colour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK, false), true, Main.game.getPlayer());

						Main.game.getPlayer().equipMainWeapon(WeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE), false);

						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();

						Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
						Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());
						
						Main.game.setWeather(Weather.MAGIC_STORM, 300);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
								true);

						Main.game.getPlayer().setMoney(40);
					}
				};
				
			} else if (index == 0) {
				return new ResponseEffectsOnly("Back", "Return to new game screen."){
					@Override
					public void effects() {
						Main.mainController.setAttributePanelContent("");
						Main.mainController.setButtonsContent("");
						Main.mainController.setMapViewContent("");
						Main.mainController.setMapTitleContent("");
						
						Main.startNewGame(CharacterCreationDialogue.CHARACTER_CREATION_START);
					}
				};
				
			} else {
				return null;
			}
		}

		@Override
		public boolean isOptionsDisabled() {
			return true;
		}
	};
	
}
