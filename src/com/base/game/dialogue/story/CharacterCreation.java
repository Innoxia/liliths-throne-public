package com.base.game.dialogue.story;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.base.game.Weather;
import com.base.game.character.CharacterUtils;
import com.base.game.character.History;
import com.base.game.character.Name;
import com.base.game.character.NameTriplet;
import com.base.game.character.QuestLine;
import com.base.game.character.QuestType;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.AssSize;
import com.base.game.character.body.valueEnums.BodySize;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Muscle;
import com.base.game.character.body.valueEnums.OrificeModifier;
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
import com.base.game.dialogue.utils.CharacterModificationUtils;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeaponType;
import com.base.game.inventory.weapon.WeaponType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class CharacterCreation {

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
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_SHORTS, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_HOODIE, false), true, Main.game.getPlayer());
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("Hero", "Heroine", "Heroine"));
						setSkin();
						setHair();
						setEyes();
						setBodyType();
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.getProperties().setNewWeaponDiscovered(false);
						Main.getProperties().setNewClothingDiscovered(false);
						Main.getProperties().setNewItemDiscovered(false);
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
			return "Body<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'> &gt Attributes &gt Name &gt Confirm</span>";
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
					+ "<i>Hint: Hover over the part that says 'Level 1 [pc.Race]' on the left of the screen to see your body's details.</i>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Gender</b></br>"
					+ getGenderOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Femininity</b></br>"
					+ getBodyOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Skin colour</b></br>"
					+ getSkinOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Muscle</b></br>"
					+ getMuscleOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Body size</b></br>"
					+ getBodySizeOption()
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "Your muscle and body size values result in your appearance being:</br>"
						+ "<b style='color:"+Main.game.getPlayer().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false))+"</b>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Sexual Orientation</b></br>"
					+ getSexualOrientationOption()
					+"</br>"
					+ "<i style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>This can be changed in the 'Fetishes' screen at any time.</i>"
					+ "</p>";
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Continue to next stage of character creation.", CHOOSE_BODY_ADVANCED){
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
						if (skinColourIndex >= RacialBody.HUMAN.getSkinType().getBodyCoveringType().getNaturalColoursPrimary().size()) {
							skinColourIndex = 0;
						}
						
						setSkin();
					}
				};
				
			} else if (index == 5) {
				return new Response("Muscle", "Cycle your muscle.", CHOOSE_BODY){
					@Override
					public void effects() {
						incrementMuscle();
					}
				};
				
			} else if (index == 6) {
				return new Response("Body Size", "Cycle your body size.", CHOOSE_BODY){
					@Override
					public void effects() {
						incrementBodySize();
					}
				};
				
			}
			else if (index == 7) {
				return new Response("Orientation", "Cycle your sexual orientation. (Hover over the status effect on the left of the screen to see what each orientation means.)", CHOOSE_BODY){
					@Override
					public void effects() {
						incrementOrientation();
					}
				};
				
			}
			else {
				return null;
			}
		}
	};

	private static StringBuilder contentSB = new StringBuilder();
	private static List<String> stringsList = new ArrayList<>();
	
	private static String stringsToSelection(List<String> strings) {
		contentSB = new StringBuilder();
		int i = 0;
		for(String s : strings) {
			
			contentSB.append(s);
			
			if (i + 1 != strings.size()) {
				contentSB.append(" | ");
			}
			
			i++;
		}
		
		return contentSB.toString();
	}
	

	private static String getGenderOption() {
		return (Main.game.getPlayer().getGender() == Gender.FEMALE ? ("<span class='option-disabled'>"
				+ Util.capitaliseSentence(Gender.MALE.getName())
				+ "</span> | "
				+ "<b style='color:"
				+ Colour.FEMININE.toWebHexString()
				+ ";'>"
				+ Util.capitaliseSentence(Gender.FEMALE.getName())
				+ "</b>")
				: ("<b style='color:"
						+ Colour.MASCULINE.toWebHexString()
						+ ";'>"
						+ Util.capitaliseSentence(Gender.MALE.getName())
						+ "</b> | "
						+ "<span class='option-disabled'>"
						+ Util.capitaliseSentence(Gender.FEMALE.getName())
						+ "</span>"));
	}

	private static String getSkinOption() {
		stringsList.clear();
		for (Colour cs : RacialBody.HUMAN.getSkinType().getBodyCoveringType().getNaturalColoursPrimary()) {
			if (Main.game.getPlayer().getCovering(RacialBody.HUMAN.getSkinType().getBodyCoveringType()).getPrimaryColour() == cs) {
				stringsList.add("<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	private static String getMuscleOption() {
		stringsList.clear();
		for (Muscle m : Muscle.values()) {
			if (Muscle.valueOf(Main.game.getPlayer().getMuscleValue()) == m) {
				stringsList.add("<b style='color:" + m.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(m.getName(false)) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(m.getName(false)) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	private static String getBodySizeOption() {
		stringsList.clear();
		for (BodySize bs : BodySize.values()) {
			if (BodySize.valueOf(Main.game.getPlayer().getBodySizeValue()) == bs) {
				stringsList.add("<b style='color:" + bs.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(bs.getName(false)) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(bs.getName(false)) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}

	private static String getBodyOption() {
		contentSB = new StringBuilder();
		for (int j = 0; j < bodyTypeMale.length; j++) {
			if (Main.game.getPlayer().getGender() == Gender.FEMALE) {
				if (bodyTypeIndex == j)
					contentSB.append("<b style='color:" + bodyColourFemale[j].toWebHexString() + ";'>" + Util.capitaliseSentence(bodyTypeFemale[j]) + "</b>");
				else
					contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(bodyTypeFemale[j]) + "</span>");
			} else {
				if (bodyTypeIndex == j)
					contentSB.append("<b style='color:" + bodyColourMale[j].toWebHexString() + ";'>" + Util.capitaliseSentence(bodyTypeMale[j]) + "</b>");
				else
					contentSB.append("<span class='option-disabled'>" + Util.capitaliseSentence(bodyTypeMale[j]) + "</span>");
			}

			if (j + 1 != bodyTypeMale.length)
				contentSB.append(" | ");
		}
		return contentSB.toString();
	}
	
	public static String getSexualOrientationOption() {
		stringsList.clear();
		for (SexualOrientation value : SexualOrientation.values()) {
			if (Main.game.getPlayer().getSexualOrientation() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}

	private static void setSkin() {
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HUMAN, RacialBody.HUMAN.getSkinType().getBodyCoveringType().getNaturalColoursPrimary().get(skinColourIndex)), true);
		Main.game.getLilaya().setSkinCovering(new Covering(BodyCoveringType.HUMAN, RacialBody.HUMAN.getSkinType().getBodyCoveringType().getNaturalColoursPrimary().get(skinColourIndex)), true);
	}
	
	private static void incrementMuscle() {
		switch(Main.game.getPlayer().getMuscle()) {
			case ZERO_SOFT:
				Main.game.getPlayer().setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
				break;
			case ONE_LIGHTLY_MUSCLED:
				Main.game.getPlayer().setMuscle(Muscle.TWO_TONED.getMedianValue());
				break;
			case TWO_TONED:
				Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
				break;
			case THREE_MUSCULAR:
				Main.game.getPlayer().setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
				break;
			case FOUR_RIPPED:
				Main.game.getPlayer().setMuscle(Muscle.ZERO_SOFT.getMedianValue());
				break;
		}
	}
	
	private static void incrementBodySize() {
		switch(Main.game.getPlayer().getBodySize()) {
			case ZERO_SKINNY:
				Main.game.getPlayer().setBodySize(BodySize.ONE_SLENDER.getMedianValue());
				break;
			case ONE_SLENDER:
				Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
				break;
			case TWO_AVERAGE:
				Main.game.getPlayer().setBodySize(BodySize.THREE_LARGE.getMedianValue());
				break;
			case THREE_LARGE:
				Main.game.getPlayer().setBodySize(BodySize.FOUR_HUGE.getMedianValue());
				break;
			case FOUR_HUGE:
				Main.game.getPlayer().setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
				break;
		}
	}
	
	private static void incrementOrientation() {
		switch(Main.game.getPlayer().getSexualOrientation()) {
			case AMBIPHILIC:
				Main.game.getPlayer().setSexualOrientation(SexualOrientation.ANDROPHILIC);
				break;
			case ANDROPHILIC:
				Main.game.getPlayer().setSexualOrientation(SexualOrientation.GYNEPHILIC);
				break;
			case GYNEPHILIC:
				Main.game.getPlayer().setSexualOrientation(SexualOrientation.AMBIPHILIC);
				break;
		}
	}
	
	
	private static void setHair() {
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, BodyCoveringType.HAIR_HUMAN.getNaturalColoursPrimary().get(hairColourIndex)), true);
	}

	private static void setEyes() {
		Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, BodyCoveringType.EYE_HUMAN.getNaturalColoursPrimary().get(eyeColourIndex)));
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
				Main.game.getPlayer().setBreastSize(CupSize.FLAT.getMeasurement());
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
	
	
	
	
	
	
	
	
	
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Body &gt Advanced <span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>&gt Attributes &gt Name &gt Confirm</span>";
		}

		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "<i>Choose any of the categories at the bottom of the screen to further customise your character.</i>"
					+ "</p>"
					+ "</br>"
					+ "<h4 style='text-align:center;'>"
						+ "<b>Your current appearance</b>"
					+ "</h4>"
					+ Main.game.getPlayer().getBodyDescription();
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
				return new Response("Face", "Enter the customisation menu for aspects related to your face.", CHOOSE_BODY_ADVANCED_FACE);
				
			} else if (index == 3) {
				return new Response("Hair", "Enter the customisation menu for your hair.", CHOOSE_BODY_ADVANCED_HAIR);
				
			} else if (index == 4) {
				return new Response("Breasts", "Enter the customisation menu for your breasts.", CHOOSE_BODY_ADVANCED_BREASTS);
				
			} else if (index == 5) {
				return new Response("Ass & Hips", "Enter the customisation menu for aspects related to your ass, hips, and anus.", CHOOSE_BODY_ADVANCED_ASS_HIPS);
				
			} else if (index == 6) {
				return new Response((Main.game.getPlayer().hasPenis()?"Penis":"Vagina"), "Enter the customisation menu for aspects related to your penis or vagina.", CHOOSE_BODY_ADVANCED_GENITALS);
				
			} else if (index == 7) {
				return new Response("Piercings", "Enter the customisation menu for body piercings.", CHOOSE_BODY_ADVANCED_PIERCINGS);
				
			} else if (index == 8) {
				return new Response("Makeup", "Enter the customisation menu for makeup.", CHOOSE_BODY_ADVANCED_COSMETICS);
				
			} else if (index == 9) {
				return new Response("Extra hair", "Enter the customisation menu for facial, pubic, and body hair.", CHOOSE_BODY_ADVANCED_BODY_HAIR);
				
			} else if (index == 0) {
				return new Response("Back", "Return to the body selection page.", CHOOSE_BODY);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_FACE = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Face";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'>"
						+ "<i>All of these options can be changed later on in the game by visiting Kate's shop.</i>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Lip size</b></br>"
						+ CharacterModificationUtils.getLipSizeOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Puffy lips</b></br>"
						+ CharacterModificationUtils.getLipPuffyOption(Main.game.getPlayer().hasFaceOrificeModifier(OrificeModifier.PUFFY))
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eye colour</b></br>"
						+ CharacterModificationUtils.getNaturalPrimaryCoveringOptions(BodyCoveringType.EYE_HUMAN)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eye pattern</b></br>"
						+ CharacterModificationUtils.getAllPatternOptions(BodyCoveringType.EYE_HUMAN)
					+ "</p>"
					+ (Main.game.getPlayer().getCovering(BodyCoveringType.EYE_HUMAN).getPattern()!=CoveringPattern.EYE_IRISES_HETEROCHROMATIC
						?"<p style='text-align:center;'>[style.boldDisabled(Heterochromatic eye colour)]</br>"
						:"<p style='text-align:center;'><b>Heterochromatic eye colour</b></br>")
						+ CharacterModificationUtils.getNaturalSecondaryCoveringOptions(BodyCoveringType.EYE_HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.EYE_HUMAN).getPattern()!=CoveringPattern.EYE_IRISES_HETEROCHROMATIC)
					+ "</p>";
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Lip size", "Cycle lip size.", CHOOSE_BODY_ADVANCED_FACE) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementLipSize();
					}
				};
				
			} else if (index == 2) {
				return new Response("Puffy lips", "Cycle your lips between being extra puffy or not.", CHOOSE_BODY_ADVANCED_FACE) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
							Main.game.getPlayer().removeFaceOrificeModifier(OrificeModifier.PUFFY);
						} else {
							Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY);
						}
					}
				};
				
			} else if (index == 3) {
				return new Response("Iris colour", "Cycle iris colour.", CHOOSE_BODY_ADVANCED_FACE) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementNaturalPrimaryCovering(BodyCoveringType.EYE_HUMAN);
					}
				};
				
			} else if (index == 4) {
				return new Response("Eye pattern", "Cycle eye pattern.", CHOOSE_BODY_ADVANCED_FACE) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementPatternFromAll(BodyCoveringType.EYE_HUMAN);
					}
				};
				
			} else if (index == 5) {
				if(Main.game.getPlayer().getCovering(BodyCoveringType.EYE_HUMAN).getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
					return new Response("Second iris colour", "Cycle the colour of your other eye.", CHOOSE_BODY_ADVANCED_FACE) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementNaturalSecondaryCovering(BodyCoveringType.EYE_HUMAN);
						}
					};
				} else {
					return new Response("Second iris colour", "You can only choose your second eye colour if you are heterochromatic.", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_HAIR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Hair";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'>"
						+ "<i>All of these options can be changed later on in the game by visiting Kate's shop.</i>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Length</b></br>"
						+ CharacterModificationUtils.getHairLengthOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Style</b></br>"
						+ CharacterModificationUtils.getHairStyleOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Colour</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.HAIR_HUMAN)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Pattern</b></br>"
						+ CharacterModificationUtils.getAllPatternOptions(BodyCoveringType.HAIR_HUMAN)
					+ "</p>"
					+ (Main.game.getPlayer().getCovering(BodyCoveringType.HAIR_HUMAN).getPattern()!=CoveringPattern.NONE
						?"<p style='text-align:center;'>[style.boldDisabled(Secondary colour)]</br>"
						:"<p style='text-align:center;'><b>Secondary colour</b></br>")
						+ CharacterModificationUtils.getAllSecondaryCoveringOptions(BodyCoveringType.HAIR_HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HAIR_HUMAN).getPattern()==CoveringPattern.NONE)
					+ "</p>";
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Hair length", "Cycle hair length.", CHOOSE_BODY_ADVANCED_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementHairLength();
					}
				};
				
			} else if (index == 2) {
				return new Response("Hair style", "Cycle your hair style.", CHOOSE_BODY_ADVANCED_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementHairStyle(Main.game.getPlayer().getHairStyle());
					}
				};
				
			} if (index == 3) {
				return new Response("Hair colour", "Cycle hair colour.", CHOOSE_BODY_ADVANCED_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.HAIR_HUMAN);
					}
				};
				
			} else if (index == 4) {
				return new Response("Hair pattern", "Cycle hair pattern.", CHOOSE_BODY_ADVANCED_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementPatternFromAll(BodyCoveringType.HAIR_HUMAN);
					}
				};
				
			} else if (index == 5) {
				if(Main.game.getPlayer().getCovering(BodyCoveringType.HAIR_HUMAN).getPattern() != CoveringPattern.NONE) {
					return new Response("Secondary hair colour", "Cycle the secondary colour of your hair.", CHOOSE_BODY_ADVANCED_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAllSecondaryCovering(BodyCoveringType.HAIR_HUMAN);
						}
					};
				} else {
					return new Response("Secondary hair colour", "You can only choose your second hair colour if you have an appropriate pattern.", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_BREASTS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Breasts";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'>"
						+ "<i>All of these options can be changed later on in the game.</i>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Breast size</b></br>"
						+ CharacterModificationUtils.getBreastSizeOption()
						+ (!Main.game.getPlayer().isFeminine()?"</br>[style.italicsDisabled(Masculine characters cannot start with breasts larger than 'training'.)]":"")
					+ "</p>"
					+ "<p style='text-align:center;'><b>Nipple size</b></br>"
						+ CharacterModificationUtils.getNippleSizeOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Areolae size</b></br>"
						+ CharacterModificationUtils.getAreolaeSizeOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Puffy nipples</b></br>"
						+ CharacterModificationUtils.getNipplePuffyOption(Main.game.getPlayer().hasNippleOrificeModifier(OrificeModifier.PUFFY))
					+ "</p>"
					+ "<p style='text-align:center;'><b>Lactation</b></br>"
						+ CharacterModificationUtils.getLactationOption()
						+ (!Main.game.getPlayer().isFeminine()?"</br>[style.italicsDisabled(Masculine characters cannot start with lactating breasts.)]":"")
					+ "</p>";
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Breast size", "Cycle breast size.", CHOOSE_BODY_ADVANCED_BREASTS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementBreastSize();
					}
				};
				
			} else if (index == 2) {
				return new Response("Nipple size", "Cycle your nipple size.", CHOOSE_BODY_ADVANCED_BREASTS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementNippleSize();
					}
				};
				
			} if (index == 3) {
				return new Response("Areolae size", "Cycle the size of your areolae.", CHOOSE_BODY_ADVANCED_BREASTS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAreolaeSize();
					}
				};
				
			} if (index == 4) {
				return new Response("Puffy nipples", "Cycle whether your nipples are extra puffy.", CHOOSE_BODY_ADVANCED_BREASTS) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
							Main.game.getPlayer().removeNippleOrificeModifier(OrificeModifier.PUFFY);
						} else {
							Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY);
						}
					}
				};
				
			} else if (index == 5) {
				return new Response("Lactation", "Cycle your starting lactation.", CHOOSE_BODY_ADVANCED_BREASTS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementLactation();
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_ASS_HIPS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Ass & Hips";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'>"
						+ "<i>All of these options can be changed later on in the game.</i>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Ass size</b></br>"
						+ CharacterModificationUtils.getAssSizeOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Hip size</b></br>"
						+ CharacterModificationUtils.getHipSizeOption()
					+ "</p>"
					+ "<p style='text-align:center;'><b>Bleached</b></br>"
						+ CharacterModificationUtils.getAnusBleachedOption()
					+ "</p>"; //TODO plasticity & elasticity
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Ass size", "Cycle ass size.", CHOOSE_BODY_ADVANCED_ASS_HIPS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAssSize();
					}
				};
				
			} else if (index == 2) {
				return new Response("Hip size", "Cycle your hip size.", CHOOSE_BODY_ADVANCED_ASS_HIPS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementHipSize();
					}
				};
				
			} else if (index == 3) {
				return new Response("Anus bleach", "Cycle whether your anus is bleached or not.", CHOOSE_BODY_ADVANCED_ASS_HIPS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setAssBleached(!Main.game.getPlayer().isAssBleached());
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_GENITALS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			if(Main.game.getPlayer().hasPenis()) {
				return "Penis";
			} else {
				return "Vagina";
			}
		}

		@Override
		public String getHeaderContent() {
			if(Main.game.getPlayer().hasPenis()) {
				return "<p style='text-align:center;'>"
							+ "<i>All of these options can be changed later on in the game.</i>"
						+ "</p>"
						
						+ "<p style='text-align:center;'><b>Penis size</b></br>"
							+ CharacterModificationUtils.getPenisSizeOption()
						+ "</p>"
						+ "<p style='text-align:center;'><b>Testicle size</b></br>"
							+ CharacterModificationUtils.getTesticleSizeOption()
						+ "</p>"
						+ "<p style='text-align:center;'><b>Cum production</b></br>"
							+ CharacterModificationUtils.getCumProductionOption()
						+ "</p>";
				
			} else {
				return "<p style='text-align:center;'>"
							+ "<i>All of these options can be changed later on in the game.</i>"
						+ "</p>"
						
						+ "<p style='text-align:center;'><b>Clit size</b></br>"
							+ CharacterModificationUtils.getClitSizeOption()
						+ "</p>"
						+ "<p style='text-align:center;'><b>Capacity</b></br>"
							+ CharacterModificationUtils.getVaginaCapacityOption()
						+ "</p>";//TODO plasticity & elasticity
				
			}
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if(Main.game.getPlayer().hasPenis()) {
				if (index == 1) {
					return new Response("Penis size", "Cycle penis size.", CHOOSE_BODY_ADVANCED_GENITALS) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementPenisSize();
						}
					};
					
				} else if (index == 2) {
					return new Response("Testicle size", "Cycle your testicle size.", CHOOSE_BODY_ADVANCED_GENITALS) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementTesticleSize();
						}
					};
					
				} else if (index == 3) {
					return new Response("Cum production", "Cycle your cum production.", CHOOSE_BODY_ADVANCED_GENITALS) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementCumProduction();
						}
					};
					
				} else if (index == 0) {
					return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
					
				} else {
					return null;
				}
			} else {
				if (index == 1) {
					return new Response("Clit size", "Cycle clit size.", CHOOSE_BODY_ADVANCED_GENITALS) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementClitSize();
						}
					};
					
				} else if (index == 2) {
					return new Response("Capacity", "Cycle your vagina's capacity.", CHOOSE_BODY_ADVANCED_GENITALS) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementVaginaCapacity();
						}
					};
					
				} else if (index == 0) {
					return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_BODY_HAIR = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Hair";
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
							+ "<i>All of these options can be changed later on in the game by visiting Kate's shop.</i>"
					+ "</p>");
			
			if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(
						"<p style='text-align:center;'><b>Body hair colour</b></br>"
							+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.BODY_HAIR_HUMAN)
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
						"<p style='text-align:center;'><b>Body hair colour</b></br>"
							+ "[style.colourDisabled(All extra body hair options are disabled. You will not see any extra hair content.)]"
						+ "</p>");
			}
			
			if(Main.game.isFacialHairEnabled()) {
				UtilText.nodeContentSB.append(
						"</br>"
						+ "<p style='text-align:center;'><b>Facial hair</b></br>"
							+ CharacterModificationUtils.getFacialHairOption()
							+ (Main.game.getPlayer().isFeminine()?"</br>[style.italicsDisabled(Feminine characters cannot grow facial hair.)]":"")
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"</br>"
						+ "<p style='text-align:center;'><b>Facial hair</b></br>"
							+ "[style.colourDisabled(Facial hair is currently disabled in the options. You will not see any facial hair content while it is disabled.)]"
						+ "</p>");
			}
			
			if(Main.game.isPubicHairEnabled()) {
				UtilText.nodeContentSB.append(
						"</br>"
						+ "<p style='text-align:center;'><b>Pubic hair</b></br>"
							+ CharacterModificationUtils.getPubicHairOption()
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"</br>"
						+ "<p style='text-align:center;'><b>Pubic hair</b></br>"
							+ "[style.colourDisabled(Pubic hair is currently disabled in the options. You will not see any pubic hair content while it is disabled.)]"
						+ "</p>");
			}
			
			if(Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(
						"</br>"
						+ "<p style='text-align:center;'><b>Underarm hair</b></br>"
							+ CharacterModificationUtils.getUnderarmHairOption()
						+ "</p>"
						+ "<p style='text-align:center;'><b>Ass hair</b></br>"
							+ CharacterModificationUtils.getAssHairOption()
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"</br>"
						+ "<p style='text-align:center;'><b>Pubic hair</b></br>"
							+ "[style.colourDisabled(Pubic hair is currently disabled in the options. You will not see any pubic hair content while it is disabled.)]"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
					return new Response("Body hair colour", "Cycle body hair colour.", CHOOSE_BODY_ADVANCED_BODY_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.BODY_HAIR_HUMAN);
						}
					};
				} else {
					return new Response("Body hair colour", "All extra body hair options are disabled.", null);
				}
				
			} else if (index == 2) {
				if(Main.game.isFacialHairEnabled()) {
					return new Response("Facial hair", "Cycle your facial hair quantity.", CHOOSE_BODY_ADVANCED_BODY_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementFacialHair(Main.game.getPlayer().getFacialHair());
						}
					};
				} else {
					return new Response("Facial hair", "Facial hair content is disabled in the options.", null);
				}
				
			} if (index == 3) {
				if(Main.game.isPubicHairEnabled()) {
					return new Response("Pubic hair", "Cycle your pubic hair quantity.", CHOOSE_BODY_ADVANCED_BODY_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementPubicHair();
						}
					};
				} else {
					return new Response("Pubic hair", "Pubic hair content is disabled in the options.", null);
				}
				
			} else if (index == 4) {
				if(Main.game.isBodyHairEnabled()) {
					return new Response("Underarm hair", "Cycle your underarm hair quantity.", CHOOSE_BODY_ADVANCED_BODY_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementUnderarmHair();
						}
					};
				} else {
					return new Response("Underarm hair", "Body hair content is disabled in the options.", null);
				}
				
			} else if (index == 5) {
				if(Main.game.isBodyHairEnabled()) {
					return new Response("Ass hair", "Cycle your ass hair quantity.", CHOOSE_BODY_ADVANCED_BODY_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAssHair();
						}
					};
				} else {
					return new Response("Ass hair", "Body hair content is disabled in the options.", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_PIERCINGS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Piercings";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'>"
					+ "<i>All of these options can be changed later on in the game, by visiting Kate's shop.</i>"
				+ "</p>"
				
				+ "<p style='text-align:center;'><b>Ear piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedEar())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Nose piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedNose())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Lip piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedLip())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Navel piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedNavel())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Tongue piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedTongue())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Nipple piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedNipple())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Penis piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedPenis())
				+ "</p>"
				+ "<p style='text-align:center;'><b>Vagina piercing</b></br>"
					+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedVagina())
				+ "</p>";
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Ear Piercing", "Cycle ear piercing.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedEar(!Main.game.getPlayer().isPiercedEar());
					}
				};
				
			} else if (index == 2) {
				return new Response("Nose Piercing", "Cycle nose piercing.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedNose(!Main.game.getPlayer().isPiercedNose());
					}
				};
				
			} else if (index == 3) {
				return new Response("Lip Piercing", "Cycle lip piercing.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedLip(!Main.game.getPlayer().isPiercedLip());
					}
				};
				
			} else if (index == 4) {
				return new Response("Navel Piercing", "Cycle navel piercing.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedNavel(!Main.game.getPlayer().isPiercedNavel());
					}
				};
				
			} else if (index == 5) {
				return new Response("Tongue Piercing", "Cycle tongue piercing.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedTongue(!Main.game.getPlayer().isPiercedTongue());
					}
				};
				
			} else if (index == 6) {
				return new Response("Nipple Piercings", "Cycle nipple piercings.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedNipples(!Main.game.getPlayer().isPiercedNipple());
					}
				};
				
			} else if (index == 7) {
				if(Main.game.getPlayer().hasPenis()) {
					return new Response("Penis Piercing", "Cycle penis piercings.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setPiercedPenis(!Main.game.getPlayer().isPiercedPenis());
						}
					};
				} else {
					return new Response("Penis Piercing", "You don't have a penis to pierce.", null);
				}
				
			} else if (index == 8) {
				if(Main.game.getPlayer().hasVagina()) {
					return new Response("Vagina Piercing", "Cycle vagina piercings.", CHOOSE_BODY_ADVANCED_PIERCINGS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setPiercedVagina(!Main.game.getPlayer().isPiercedVagina());
						}
					};
				} else {
					return new Response("Vagina Piercing", "You don't have a vagina to pierce.", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CHOOSE_BODY_ADVANCED_COSMETICS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Cosmetics";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'>"
						+ "<i>All of these options can be changed later on in the game, by visiting Kate's shop.</i>"
					+ "</p>"
					
					+ "<p style='text-align:center;'><b>Blusher</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_BLUSHER)
						+ "</br>"
						+ getGlowOptions(Main.game.getPlayer().getBlusher().isPrimaryGlowing())
					+ "</p>"
					+ "<p style='text-align:center;'><b>Lipstick</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_LIPSTICK)
						+ "</br>"
						+ getGlowOptions(Main.game.getPlayer().getLipstick().isPrimaryGlowing())
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eyeliner</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_EYE_LINER)
						+ "</br>"
						+ getGlowOptions(Main.game.getPlayer().getEyeLiner().isPrimaryGlowing())
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eyeshadow</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_EYE_SHADOW)
						+ "</br>"
						+ getGlowOptions(Main.game.getPlayer().getEyeShadow().isPrimaryGlowing())
					+ "</p>"
					+ "<p style='text-align:center;'><b>Nail polish</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS)
						+ "</br>"
						+ getGlowOptions(Main.game.getPlayer().getHandNailPolish().isPrimaryGlowing())
					+ "</p>"
					+ "<p style='text-align:center;'><b>Toenail polish</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET)
						+ "</br>"
						+ getGlowOptions(Main.game.getPlayer().getFootNailPolish().isPrimaryGlowing())
					+ "</p>"
					;
			
			
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Blusher colour", "Cycle blusher colour.", CHOOSE_BODY_ADVANCED_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_BLUSHER);
					}
				};
				
			} if (index == 2) {
				if(Main.game.getPlayer().getBlusher().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Blusher glow", "Cycle blusher glow.", CHOOSE_BODY_ADVANCED_COSMETICS) {
						@Override
						public void effects() {
							Main.game.getPlayer().getBlusher().setPrimaryGlowing(!Main.game.getPlayer().getBlusher().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Blusher glow", "You can't make your blusher glow if you aren't wearing any!", null);
				}
				
			} if (index == 3) {
				return new Response("Lipstick colour", "Cycle lipstick colour.", CHOOSE_BODY_ADVANCED_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				};
				
			} if (index == 4) {
				if(Main.game.getPlayer().getLipstick().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Lipstick glow", "Cycle lipstick glow.", CHOOSE_BODY_ADVANCED_COSMETICS) {
						@Override
						public void effects() {
							Main.game.getPlayer().getLipstick().setPrimaryGlowing(!Main.game.getPlayer().getLipstick().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Lipstick glow", "You can't make your lipstick glow if you aren't wearing any!", null);
				}
				
			} if (index == 5) {
				return new Response("Eyeliner colour", "Cycle eyeliner colour.", CHOOSE_BODY_ADVANCED_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_EYE_LINER);
					}
				};
				
			} if (index == 6) {
				if(Main.game.getPlayer().getEyeLiner().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Eyeliner glow", "Cycle eyeliner glow.", CHOOSE_BODY_ADVANCED_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getEyeLiner().setPrimaryGlowing(!Main.game.getPlayer().getEyeLiner().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Eyeliner glow", "You can't make your eyeliner glow if you aren't wearing any!", null);
				}
				
			} if (index == 7) {
				return new Response("Eyeshadow colour", "Cycle eyeshadow colour.", CHOOSE_BODY_ADVANCED_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_EYE_SHADOW);
					}
				};
				
			} if (index == 8) {
				if(Main.game.getPlayer().getEyeShadow().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Eyeshadow glow", "Cycle eyeshadow glow.", CHOOSE_BODY_ADVANCED_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getEyeShadow().setPrimaryGlowing(!Main.game.getPlayer().getEyeShadow().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Eyeshadow glow", "You can't make your eyeshadow glow if you aren't wearing any!", null);
				}
				
			} if (index == 9) {
				return new Response("Nail polish colour", "Cycle nail polish colour.", CHOOSE_BODY_ADVANCED_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS);
					}
				};
				
			} if (index == 10) {
				if(Main.game.getPlayer().getHandNailPolish().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Nail polish glow", "Cycle nail polish glow.", CHOOSE_BODY_ADVANCED_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getHandNailPolish().setPrimaryGlowing(!Main.game.getPlayer().getHandNailPolish().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Nail polish glow", "You can't make your nail polish glow if you aren't wearing any!", null);
				}
				
			} if (index == 11) {
				return new Response("Toenail polish colour", "Cycle toenail polish colour.", CHOOSE_BODY_ADVANCED_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET);
					}
				};
				
			} if (index == 12) {
				if(Main.game.getPlayer().getFootNailPolish().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Toenail polish glow", "Cycle toenail polish glow.", CHOOSE_BODY_ADVANCED_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getFootNailPolish().setPrimaryGlowing(!Main.game.getPlayer().getFootNailPolish().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Toenail polish glow", "You can't make your toenail polish glow if you aren't wearing any!", null);
				}
				
			}else if (index == 0) {
				return new Response("Back", "Return to the main character creation screen.", CHOOSE_BODY_ADVANCED);
				
			} else {
				return null;
			}
		}
	};
	
	
	
	private static String getGlowOptions(boolean glow) {
		stringsList.clear();
		if(!glow) {
			stringsList.add("[style.boldDisabled(Regular)]");
		} else {
			stringsList.add("[style.colourDisabled(Regular)]");
		}	
		if(glow) {
			stringsList.add("[style.boldGood(Glowing)]");
		} else {
			stringsList.add("[style.colourDisabled(Glowing)]");
		}
		return stringsToSelection(stringsList);
	}
	
	
	
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

					+ "Attribute-based perks can be earnt through core attribute values, plus any bonus modifiers you have."
					+ " For example, if your items boost your strength to 100, you will get the maximum strength status effect.</br></br>"

					+ "You can hover your mouse over the attributes panel on the left of the screen for information relating to your attributes."
					+ " There are also many secondary attributes, which you can check at any time by hovering over your name at the top-left (or by accessing your phone after the game starts).";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to body selection.", CHOOSE_BODY_ADVANCED);
				
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
	};

	private static boolean unsuitableName = false;
	public static final DialogueNodeOld CHOOSE_NAME = new DialogueNodeOld("Name", "Back to name selection.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Body &gt Advanced &gt Attributes &gt Name <span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>&gt Confirm</span>";
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
							+ Colour.GENERIC_BAD.toWebHexString()
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

						Main.game.setRenderMapSection(true);
						applyGameStart();

						Main.game.getPlayer().resetInventory();
						Main.game.getPlayer().setMoney(40);
						Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 5);

						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE)
							Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_HOODIE, Colour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK, false), true, Main.game.getPlayer());

						Main.game.getPlayer().equipMainWeapon(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE), false);

						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();

						Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
						Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());
						
						Main.getProperties().addRaceDiscovered(Main.game.getLilaya().getRace());
						Main.getProperties().addRaceDiscovered(Main.game.getRose().getRace());

						Main.game.setWeather(Weather.MAGIC_STORM, 300);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
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
						Main.mainController.setMapViewContent("");
						Main.mainController.setMapTitleContent("");
						
						Main.startNewGame(CharacterCreation.CHARACTER_CREATION_START);
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
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Skip prologue", "Start the game and skip the prologue.</br></br><i style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Not recommended for first time playing!</i>"){
					@Override
					public void effects() {

						Main.game.setRenderMapSection(true);
						applyGameStart();

						Main.game.getPlayer().resetInventory();

						Main.game.getPlayer().setMoney(40);
						Main.game.getPlayer().incrementEssenceCount(TFEssence.ARCANE, 5);
						
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE)
							Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_HOODIE, Colour.CLOTHING_BLACK, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, Main.game.getPlayer());
						Main.game.getPlayer().equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK, false), true, Main.game.getPlayer());

						Main.game.getPlayer().equipMainWeapon(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE, DamageType.FIRE), false);

						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();

						Main.game.getPlayer().addCharacterEncountered(Main.game.getLilaya());
						Main.game.getPlayer().addCharacterEncountered(Main.game.getRose());
						
						Main.getProperties().addRaceDiscovered(Main.game.getLilaya().getRace());
						Main.getProperties().addRaceDiscovered(Main.game.getRose().getRace());
						
						Main.game.setWeather(Weather.MAGIC_STORM, 300);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						
						Main.game.setActiveWorld(
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR),
								Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getPlacesOfInterest().get(LilayasHome.LILAYA_HOME_ROOM_PLAYER),
								true);
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
						
						Main.startNewGame(CharacterCreation.CHARACTER_CREATION_START);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
