package com.lilithsthrone.game.dialogue.story;

import java.io.File;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.History;
import com.lilithsthrone.game.character.Name;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.QuestType;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.VaginaType;
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
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.LilayasHome;

/**
 * @since 0.1.0
 * @version 0.1.86
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
		public Response getResponse(int index) {
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
			return "Version " + Main.VERSION_NUMBER + " | <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Very-early Alpha!</b>";
		}
		
		@Override
		public String getContent() {
			return Main.patchNotes;
			
		}

		@Override
		public Response getResponse(int index) {
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Start", "Proceed to character creation.", CHOOSE_APPEARANCE){
					@Override
					public void effects() {
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, false), true, Main.game.getPlayer());
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.getProperties().setNewWeaponDiscovered(false);
						Main.getProperties().setNewClothingDiscovered(false);
						Main.getProperties().setNewItemDiscovered(false);
						Main.game.getPlayer().calculateStatusEffects(0);
						resetBodyAppearance();
						
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("Unknown", "Unknown", "Unknown"));
						Main.game.getPlayer().setSurname("");
					}
				};
				
			} else if (index == 2) {
				return new Response("Start (Import)", "Import a character from a previous version to use on game start.", IMPORT_CHOOSE);
				
			} else {
				return null;
			}
		}
	};
	
	public static void resetBodyAppearance() {
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_BROWN), true);
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN), true);
		Main.game.getPlayer().setBreastShape(BreastShape.ROUND);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue());
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
		CharacterCreation.resetBodyAppearance();
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
						+ " It only takes a moment before you're standing at the museum's front doors, where, much to your dismay, you see that there are a couple of people queueing up to gain entry."
						+ " Having no choice but to step in line and wait your turn, you briefly glance over at the large glass windows of the building's modern facade to see your blurry reflection in the glass..."
					+ "</p>"
					+ "</br>"
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getGenderChoiceDiv()
						
						+ CharacterModificationUtils.getFemininityChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "You will be referred to as <span style='color:"+Main.game.getPlayer().getGender().getColour().toWebHexString()+";'>"
								+UtilText.generateSingularDeterminer(Main.game.getPlayer().getGender().getName())+ " " + Main.game.getPlayer().getGender().getName()+"</span>.</br>"
							+ "<i>You can change all gender names in the options menu.</i>"
						+ "</div>"
					
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Wait your turn, and hope that the event hasn't started yet.", CHOOSE_NAME);
				
			}
			else if (index == 2) {
				return new Response("Cycle Gender", "Cycle through the gender options.", CHOOSE_APPEARANCE) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().hasVagina()) {
							setGenderMale();
						} else {
							setGenderFemale();
						}
					}
				};
				
			} else if (index == 3) {
				return new Response("Cycle Femininity", "Cycle through the femininity options.", CHOOSE_APPEARANCE) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().hasVagina()) {
							switch(Main.game.getPlayer().getFemininity()) {
								case ANDROGYNOUS:
									Main.game.getPlayer().setFemininity(Femininity.FEMININE.getMedianFemininity());
									break;
								case FEMININE:
									Main.game.getPlayer().setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
									break;
								case FEMININE_STRONG:
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
									break;
								default:
									break;
							}
						} else {
							switch(Main.game.getPlayer().getFemininity()) {
								case ANDROGYNOUS:
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE.getMedianFemininity());
									break;
								case MASCULINE:
									Main.game.getPlayer().setFemininity(Femininity.MASCULINE_STRONG.getMedianFemininity());
									break;
								case MASCULINE_STRONG:
									Main.game.getPlayer().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
									break;
								default:
									break;
							}
						}
					}
				};
				
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
						+ "<i>Your name must be between 2 and 16 characters long. They can only include the standard letters a-z and their respective capitals. (Surname may be left blank.)</i>"
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
		public Response getResponse(int index) {
			if (index == 1) {
				
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
				
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
				if(Main.mainController.getWebEngine().getDocument()!=null) {
					if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
							&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 16
									|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[a-zA-Z]+")))
						unsuitableSurname = true;
					else {
						unsuitableSurname = false;
					}
				}
				
				
				if (unsuitableName || unsuitableSurname) {
					return new Response("Continue", "Use this name and continue to the final character creation screen.", CHOOSE_NAME);
					
				} else {
					return new Response("Continue", "Use this name and continue to the final character creation screen.", CHOOSE_ADVANCED_APPEARANCE){
						@Override
						public void effects() {
							Main.game.getPlayer().setName(new NameTriplet(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent()));
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
						}
					};
				}
				
			} else if (index == 2) {
				return new Response("Random", "Generate a random name based on your gender.", CHOOSE_NAME){
					@Override
					public void effects() {
						unsuitableName = false;

						Main.game.getPlayer().setName(new NameTriplet(Name.getRandomName(Main.game.getPlayer())));
					}
				};
				
			} else if (index == 3) {
				return new Response("Random Surname", "Generate a random surname.", CHOOSE_NAME){
					@Override
					public void effects() {
						unsuitableName = false;

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
	
	public static final DialogueNodeOld CHOOSE_ADVANCED_APPEARANCE = new DialogueNodeOld("A Night Out", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[pc.speech(It's [pc.name]"+(Main.game.getPlayer().getSurname().length()!=0?", [pc.name] [pc.surname]":"")+",)]"
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
						+ "As Lily's opening speech seems to be running just as late as you are, you decide to step over to a nearby mirror to make sure that you're looking presentable..."
					+ "</p>"
					+ "</br>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Appearance</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>"
					+ "</br>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>You can alter your appearance by entering each of the sub-menus below. Once you're happy with how you look, press the 'Approach Stage' option.</i>"
					+ "</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Approach Stage", "You're ready to approach the stage and wait for Lily to make her entrance.", HISTORY_MENU);
				
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>All of these options can be influenced later on in the game.</i>"
						+ "</div>"
						
						+ CharacterModificationUtils.getHeightChoiceDiv()
						
						+ CharacterModificationUtils.getKatesDivNaturalCoverings(BodyCoveringType.HUMAN, "Skin Colour", "The colour of the skin that's covering your body.", false, false)
						
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
		public Response getResponse(int index) {
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>All of these options can be influenced later on in the game.</i>"
						+ "</div>"

						+ CharacterModificationUtils.getLipSizeDiv()
						
						+ CharacterModificationUtils.getLipPuffynessDiv()

						+ CharacterModificationUtils.getKatesDivNaturalCoverings(BodyCoveringType.EYE_HUMAN, "Iris Colour", "The colour of your eye's irises.", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>All of these options can be influenced later on in the game.</i>"
						+ "</div>"

						+ CharacterModificationUtils.getKatesDivHairLengths("Hair Length", "Choose how long your hair is.")
						
						+ CharacterModificationUtils.getKatesDivHairStyles("Hair Style", "Choose your hair style. Certain hair styles are unavailable at shorter hair lengths.")

						+ CharacterModificationUtils.getKatesDivCoverings(BodyCoveringType.HAIR_HUMAN, "Hair Colour", "The colour of your hair.", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
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
		public Response getResponse(int index) {
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
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
		public Response getResponse(int index) {
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
				return "<div class='container-full-width' style='background:transparent;'>"
							+ "<div class='container-full-width' style='text-align:center;'>"
								+ "<i>All of these options can be influenced later on in the game.</i>"
							+ "</div>"
							
							+ CharacterModificationUtils.getPenisSizeDiv()
							
							+ CharacterModificationUtils.getTesticleSizeDiv()
							
							+ CharacterModificationUtils.getCumProductionDiv();
				
			} else {
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
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
		public Response getResponse(int index) {
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
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
		public Response getResponse(int index) {
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
			return "<div class='container-full-width' style='background:transparent;'>"
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>All of these options can be influenced later on in the game.</i>"
						+ "</div>"
							
						+CharacterModificationUtils.getKatesDivCoverings(
								BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, false)
						
						+CharacterModificationUtils.getKatesDivCoverings(
								BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, false)

						+CharacterModificationUtils.getKatesDivCoverings(
								BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, false)

						+CharacterModificationUtils.getKatesDivCoverings(
								BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, false)

						+CharacterModificationUtils.getKatesDivCoverings(
								BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, false)

						+CharacterModificationUtils.getKatesDivCoverings(
								BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
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
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='background:transparent;'>"
							+ "<div class='container-full-width' style='text-align:center;'>"
								+ "<i>All of these options can be influenced later on in the game.</i>"
							+ "</div>");
			
			if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivNaturalCoverings(
						Main.game.getPlayer().getBodyHairCoveringType(), "Body hair", "This is the hair that covers all areas other than the head.", false, false));
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
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	//TODO from here
	
	public static final DialogueNodeOld HISTORY_MENU = new DialogueNodeOld("Choose your background", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Body &gt Advanced &gt Attributes <span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>&gt Name &gt Confirm</span>";
		}
		
		@Override
		public String getContent() {
			return "What sort of person are you?</br></br>"

					+ "Your background will influence your starting core attributes. While attributes can be temporarily modified by many items in the game, core attributes are a lot harder to change.</br></br>"

					+ "Attribute-based perks can be earned through core attribute values, plus any bonus modifiers you have."
					+ " For example, if your items boost your strength to 100, you will get the maximum strength status effect.</br></br>"

					+ "You can hover your mouse over the attributes panel on the left of the screen for information relating to your attributes."
					+ " There are also many secondary attributes, which you can check at any time by hovering over your name at the top-left (or by accessing your phone after the game starts).";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to body selection.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else if (index <= History.getAvailableHistories(Main.game.getPlayer()).size()) {
				return new Response(History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getName(),
						History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getDescriptionPlayer()
						+ (History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getModifiersAsStringList().length() == 0
							? ""
							: "</br>" + History.getAvailableHistories(Main.game.getPlayer()).get(index - 1).getModifiersAsStringList()), START_GAME){
					@Override
					public void effects() {
						Main.game.getPlayer().setHistory(History.getAvailableHistories(Main.game.getPlayer()).get(index - 1));
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld START_GAME = new DialogueNodeOld("Start game", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Body &gt Advanced &gt Attributes &gt Name &gt Confirm";
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
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {
						applyGameStart();

						Main.game.getPlayer().resetInventory();
						Main.game.getPlayer().setMoney(40);
						Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 5);

						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE)
							Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK, false), true, Main.game.getPlayer());

						Main.game.getPlayer().equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE));

						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						
						Main.game.setRenderMap(true);
						Main.game.setInNewWorld(true);

						Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
						Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());
						
						Main.getProperties().addRaceDiscovered(Main.game.getLilaya().getRace());
						Main.getProperties().addRaceDiscovered(Main.game.getRose().getRace());

						Main.game.setWeather(Weather.MAGIC_STORM, 300);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(new GenericPlace(LilayasHome.LILAYA_HOME_ROOM_PLAYER)),
								true);
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
	};

	private static void applyGameStart() {
		Main.getProperties().addRaceDiscovered(Race.HUMAN);
		Main.getProperties().addItemDiscovered(ItemType.CONDOM);
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
			return Main.game.getPlayer().getBodyDescription()
					+"<details>"
					+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>Import Log</summary>"
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
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {

						applyGameStart();

						Main.game.getPlayer().resetInventory();

						Main.game.getPlayer().setMoney(40);
						Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 5);
						
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE)
							Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK, false), true, Main.game.getPlayer());

						Main.game.getPlayer().equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE));

						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						
						Main.game.setRenderMap(true);
						Main.game.setInNewWorld(true);

						Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
						Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());
						
						Main.getProperties().addRaceDiscovered(Main.game.getLilaya().getRace());
						Main.getProperties().addRaceDiscovered(Main.game.getRose().getRace());
						
						Main.game.setWeather(Weather.MAGIC_STORM, 300);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(new GenericPlace(LilayasHome.LILAYA_HOME_ROOM_PLAYER)),
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
