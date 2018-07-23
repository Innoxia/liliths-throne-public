package com.lilithsthrone.game.dialogue.utils;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7?
 * @version 0.2.8
 * @author Innoxia
 */
public class CharacterModificationUtils {

	private static StringBuilder contentSB = new StringBuilder();
	
	public static String getStartDateDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
						+"<div class='cosmetics-inner-container full'>"
						+ "<h5 style='text-align:center;'>"
							+"Start Date"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Select the month in which the game starts."
						+ "</p>");
		
		for(Month month : Month.values()) {
			if(Main.game.getStartingDate().getMonth() == month) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='STARTING_MONTH_"+month+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_MINOR_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH))+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	// Basics:
	
	public static String getGenderChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Gender"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Your gender is used to determine what genitals you start the game with."
						+ "</p>");
		
		if(Main.game.getPlayer().getGender().getGenderName().isHasVagina()) {
			contentSB.append(
					"<div id='CHOOSE_GENDER_MALE' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.MASCULINE.getShades()[0]+";'>Male</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldFeminine(Female)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.colourMasculine(Male)]"
					+ "</div>"
					+ "<div id='CHOOSE_GENDER_FEMALE' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.FEMININE.getShades()[0]+";'>Female</span>"
					+ "</div>");
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getFemininityChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Femininity"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Femininity is a measure of how masculine or feminine your face and body are."
						+ "</p>");
		
		if(Main.game.getPlayer().getGender().getGenderName().isHasVagina()) {
			if(Main.game.getPlayer().getFemininity()==Femininity.ANDROGYNOUS) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourAndrogynous(Androgynous)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.ANDROGYNOUS.getShades()[0]+";'>Androgynous</span>"
						+ "</div>");
			}
			if(BodyChanging.getTarget().getFemininity()==Femininity.FEMININE) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourFeminine(Feminine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHOOSE_FEM_FEMININE' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.FEMININE.getShades()[0]+";'>Feminine</span>"
						+ "</div>");
			}
			if(BodyChanging.getTarget().getFemininity()==Femininity.FEMININE_STRONG) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourFeminineStrong(Very Feminine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHOOSE_FEM_FEMININE_STRONG' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.FEMININE_PLUS.getShades()[0]+";'>Very Feminine</span>"
						+ "</div>");
			}
			
		} else {
			if(BodyChanging.getTarget().getFemininity()==Femininity.ANDROGYNOUS) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourAndrogynous(Androgynous)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.ANDROGYNOUS.getShades()[0]+";'>Androgynous</span>"
						+ "</div>");
			}
			if(BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourMasculine(Masculine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHOOSE_FEM_MASCULINE' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.MASCULINE.getShades()[0]+";'>Masculine</span>"
						+ "</div>");
			}
			if(BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE_STRONG) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourMasculineStrong(Very Masculine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHOOSE_FEM_MASCULINE_STRONG' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.MASCULINE_PLUS.getShades()[0]+";'>Very Masculine</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getPersonalityChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>"
							+"Personality"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Your personality will have a minor influence in some situations."
							+ " It will not lock out any options during the game, and is more for roleplaying purposes."
						+ "</p>");
		
		int i=0;
		for(PersonalityTrait trait : PersonalityTrait.values()) {
			contentSB.append("<div class='container-full-width' style='"+(i==4?"width:50%; margin:0 25%;":"width:calc(50% - 16px);")+" text-align:center; padding:0;'>"
					+ "<div class='title-button no-select' id='PERSONALITY_INFO_"+trait+"' style='position:absolute; left:auto; right:5%; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
					+ "<p style='text-align:center; margin-bottom:0; padding-bottom:0;'>"
						+ "<b style='color:"+trait.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(trait.getName())+"</b><br/>"
						+ "<i>"+trait.getNameFromWeight(BodyChanging.getTarget(), BodyChanging.getTarget().getPersonality().get(trait))+"</i>"
					+ "</p>");

			for(PersonalityWeight weight : PersonalityWeight.values()) {
				if(BodyChanging.getTarget().getPersonality().get(trait) == weight) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+trait.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(weight.getName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='PERSONALITY_"+trait+"_"+weight+"' class='cosmetics-button'>"
									+ "[style.colourDisabled("+Util.capitaliseSentence(weight.getName())+")]"
							+ "</div>");
				}
			}
			
			contentSB.append("</div>");
			i++;
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static void performPlayerAgeCheck(int ageTarget) {
		if(Main.game.getPlayer().getAge()>ageTarget) {
			Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().plusYears(1));
		} else if(Main.game.getPlayer().getAge()<ageTarget) {
			Main.game.getPlayer().setBirthday(Main.game.getPlayer().getBirthday().minusYears(1));
		}
	}
	
	public static String getBirthdayChoiceDiv() {
		contentSB.setLength(0);

		contentSB.append(
				"<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>"
							+"Birthday"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "You were born on the "
								+Util.intToDate(Main.game.getPlayer().getBirthday().getDayOfMonth())
								+" "+Main.game.getPlayer().getBirthday().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
								+", "+(Main.game.getPlayer().getBirthday().getYear())+", making you "+Util.intToString(Main.game.getPlayer().getAge())+" years old."
						+ "</p>");

			contentSB.append("<div class='container-full-width' style='margin:0;padding;0;width:100%;'>");
			
				contentSB.append(applyDateWrapper("Day", "BIRTH_DAY", "", "", String.valueOf(Main.game.getPlayer().getBirthday().getDayOfMonth()), false, false));
				
				contentSB.append(applyDateWrapper("Month", "BIRTH_MONTH", "", "", Main.game.getPlayer().getBirthday().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false, false));
				
				contentSB.append(applyDateWrapper("Age", "AGE", "", "",
						String.valueOf(Main.game.getPlayer().getAge()),
						Main.game.getPlayer().getAge()<=18,
						Main.game.getPlayer().getAge()>=50));
			contentSB.append("</div>");
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String applyDateWrapper(String title, String id, String measurement, String measurementPlural, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return "<div class='container-full-width' style='width:calc(33.3% - 16px);'>"
					+ "<p style='width:100%; text-align:center;'>"
						+ "<b>"+title+"</b>"
					+ "</p>"
					+ "<div class='container-half-width' style='width:30%; margin:0; padding:0; text-align:center;'>"
						+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:90%;'>"
							+ (decreaseDisabled?"[style.boldDisabled(-1"+measurement+")]":"[style.boldBadMinor(-1"+measurement+")]")
						+ "</div>"
						+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:90%;'>"
							+ (decreaseDisabled?"[style.boldDisabled(-5"+measurementPlural+")]":"[style.boldBad(-5"+measurementPlural+")]")
						+ "</div>"
					+ "</div>"
					+ "<div class='container-half-width' style='width:40%; margin:0; padding:0; text-align:center;'>"
						+ value
					+ "</div>"
					+ "<div class='container-half-width' style='width:30%; margin:0; padding:0; text-align:center;'>"
						+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:90%;'>"
							+ (increaseDisabled?"[style.boldDisabled(+1"+measurement+")]":"[style.boldGoodMinor(+1"+measurement+")]")
						+ "</div>"
						+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:90%;'>"
							+ (increaseDisabled?"[style.boldDisabled(+5"+measurementPlural+")]":"[style.boldGood(+5"+measurementPlural+")]")
						+ "</div>"
					+ "</div>"
				+ "</div>";
	}
	
	public static String getOrientationChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
						+"<div class='cosmetics-inner-container full'>"
						+ "<h5 style='text-align:center;'>"
							+"Sexual Orientation"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Sexual orientation in Lilith's Throne is determined by your attraction towards femininity or masculinity."
							+ " Each orientation has a related status effect (hover over the icon in the left-hand panel to see the effects)."
						+ "</p>");
		
		for(SexualOrientation orientation : SexualOrientation.values()) {
			if(BodyChanging.getTarget().getSexualOrientation() == orientation) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+orientation.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(orientation.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SEXUAL_ORIENTATION_"+orientation+"' class='cosmetics-button'>"
							+ "<span style='color:"+orientation.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(orientation.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	
	public static int[] soSilly = new int[] {0, 1, 2, 3, 4}; // Apparently just using normalValues.length isn't allowed (in MainController) :s
	public static int[] normalSexExperienceValues = new int[] {0, 5, 25, 50, 100};
	private static Colour[] sexColours = new Colour[] {Colour.GENERIC_EXCELLENT, Colour.BASE_PINK_LIGHT, Colour.BASE_PINK, Colour.BASE_PINK_DEEP, Colour.ATTRIBUTE_CORRUPTION};
	public static String[] feminineNames = new String[] {"Virgin", "Inexperienced", "Experienced", "Expert", "Slut"};
	public static String[] masculineNames = new String[] {"Virgin", "Inexperienced", "Experienced", "Expert", "Stud"};
	
//	public static String[] virginityLossesGynephilic = new String[] {"your girlfriend", "", "some girl in your apartment", "some girl in a club's restroom"};
//	public static String[] virginityLossesAmbiphilic = new String[] {"your girlfriend in her apartment", "your girlfriend in your apartment", "some girl in her apartment", "some girl in your apartment", "some girl in a club's restroom",
//			"your boyfriend in his apartment", "your boyfriend in your apartment", "some guy in his apartment", "some guy in your apartment", "some guy in a club's restroom"};
//	public static String[] virginityLossesAndrophilic = new String[] {"your boyfriend in his apartment", "your boyfriend in your apartment", "some guy in his apartment", "some guy in your apartment", "some guy in a club's restroom"};
//	
	
	
	public static String getSexualExperienceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>"
							+ "<div class='container-full-width' style='text-align:center;'><h6>Sex Actions Performed</h6></div>");
		
			contentSB.append(
							getSexExperienceEntry("HANDJOBS_GIVEN", "Handjobs Given",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.URETHRA_PENIS),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
							
							+ getSexExperienceEntry("FINGERINGS_GIVEN", "Fingerings Performed",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
							
							+ getSexExperienceEntry("BLOWJOBS_GIVEN", "Blowjobs Given",
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
							
							+ getSexExperienceEntry("CUNNILINGUS_GIVEN", "Cunnilingus Performed",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
							
							+ getSexExperienceEntry("VAGINAL_GIVEN", "Vaginal Sex Performed",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
							
							+ getSexExperienceEntry("ANAL_GIVEN", "Anal Sex Performed",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames));
		contentSB.append("</div>");

		contentSB.append("<div class='container-full-width'>"
							+ "<div class='container-full-width' style='text-align:center;'><h6>Sex Actions Received</h6></div>");
			contentSB.append(
							(Main.game.getPlayer().hasVagina()
									?""
									:getSexExperienceEntry("HANDJOBS_TAKEN", "Handjobs Received",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.FINGER),
										normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames))
							
							+ (Main.game.getPlayer().hasVagina()
									?getSexExperienceEntry("FINGERINGS_TAKEN", "Fingerings Received",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
										normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
									:"")
							
							+ (Main.game.getPlayer().hasVagina()
									?""
									:getSexExperienceEntry("BLOWJOBS_TAKEN", "Blowjobs Received",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames))
							
							+ (Main.game.getPlayer().hasVagina()
									?getSexExperienceEntry("CUNNILINGUS_TAKEN", "Cunnilingus Received",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
									:"")
							
							+ (Main.game.getPlayer().hasVagina()
									?getSexExperienceEntry("VAGINAL_TAKEN", "Vaginal Sex Received",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames)
									:"")
							
							+ getSexExperienceEntry("ANAL_TAKEN", "Anal Sex Received",
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
									normalSexExperienceValues, Main.game.getPlayer().isFeminine()?feminineNames:masculineNames));
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static void setSexExperience(SexType type, int index) {
		int count = Main.game.getPlayer().getSexCount(type);
		
		for(int i =0; i<normalSexExperienceValues.length; i++) {
			if(count == normalSexExperienceValues[i]) {
				Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, -i);
				break;
			}
		}

		Main.game.getPlayer().incrementAttribute(Attribute.MAJOR_CORRUPTION, index);
		
		Main.game.getPlayer().setSexCount(type, CharacterModificationUtils.normalSexExperienceValues[index]);
		
		if(index!=0) {
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC
					|| (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && !Main.game.getPlayer().isFeminine())) {
				Main.game.getPlayer().setVirginityLoss(type, "your girlfriend");
			} else {
				Main.game.getPlayer().setVirginityLoss(type, "your boyfriend");
			}
		} else {
			Main.game.getPlayer().setVirginityLoss(type, null);
		}
		
		if(type.getPerformingSexArea()==SexAreaPenetration.PENIS) {
			if(index==0) {
				Main.game.getPlayer().setPenisVirgin(true);
			} else {
				Main.game.getPlayer().setPenisVirgin(false);
			}
		}
		
		if(type.getTargetedSexArea()==SexAreaPenetration.PENIS) {
			if(type.getPerformingSexArea().isOrifice()) {
				switch((SexAreaOrifice)type.getPerformingSexArea()) {
					case ANUS:
						if(index==0) {
							Main.game.getPlayer().setAssVirgin(true);
						} else {
							Main.game.getPlayer().setAssVirgin(false);
						}
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case MOUTH:
						if(index==0) {
							Main.game.getPlayer().setFaceVirgin(true);
						} else {
							Main.game.getPlayer().setFaceVirgin(false);
						}
						break;
					case NIPPLE:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(index==0) {
							Main.game.getPlayer().setVaginaVirgin(true);
						} else {
							Main.game.getPlayer().setVaginaVirgin(false);
						}
						break;
				}
			}
		}
	}
	
	private static String getSexExperienceEntry(String id, String title, SexType associatedSexType, int[] values, String[] names) {
		int index = 0;
		for(int i=0; i<5; i++ ) {
			if(values[i] == Main.game.getPlayer().getSexCount(associatedSexType)) {
				index = i;
				break;
			}
		}
		
		return "<div class='container-full-width inner'>"
					+ "<div class='container-full-width inner' style='width:calc(50%);margin:0;padding:0;'>"
						+ title+": <b style='color:"+sexColours[index].toWebHexString()+";'>"+names[index]+"</b>"
					+ "</div>"
					+ "<div class='container-full-width inner' style='width:calc(50%);margin:0;padding:0;'>"
						+ "<div class='normal-button"+(index==0?" selected":"")+"' id='"+id+"_0' style='width:18%; margin-right:2%; text-align:center;"+(index==0?" color:"+sexColours[index].toWebHexString()+";":"")+"'>"+values[0]+"</div>"
						+ "<div class='normal-button"+(index==1?" selected":"")+"' id='"+id+"_1' style='width:18%; margin-right:2%; text-align:center;"+(index==1?" color:"+sexColours[index].toWebHexString()+";":"")+"'>"+values[1]+"</div>"
						+ "<div class='normal-button"+(index==2?" selected":"")+"' id='"+id+"_2' style='width:18%; margin-right:2%; text-align:center;"+(index==2?" color:"+sexColours[index].toWebHexString()+";":"")+"'>"+values[2]+"</div>"
						+ "<div class='normal-button"+(index==3?" selected":"")+"' id='"+id+"_3' style='width:18%; margin-right:2%; text-align:center;"+(index==3?" color:"+sexColours[index].toWebHexString()+";":"")+"'>"+values[3]+"</div>"
						+ "<div class='normal-button"+(index==4?" selected":"")+"' id='"+id+"_4' style='width:18%; margin-right:2%; text-align:center;"+(index==4?" color:"+sexColours[index].toWebHexString()+";":"")+"'>"+values[4]+"</div>"
					+ "</div>"
						//TODO
//					+ "<div class='container-full-width inner' style='width:calc(100%);margin:0;padding:0;'>"
//						+ "Virginity lost: "
//						+ (Main.game.getPlayer().getVirginityLoss(associatedSexType)==null?"[style.boldDisabled(N/A)]":Main.game.getPlayer().getVirginityLoss(associatedSexType))
//					+ "</div>"
				+ "</div>";
	}
	
	
	
	// Advanced:
	
	public static String getHeightChoiceDiv() {
		return applyFullVariableWrapper("Height",
				(BodyChanging.getTarget().isPlayer()
			?"Change how tall you are."+(!Main.game.isInNewWorld()?" This will affect some descriptions and scenes later on in the game.":"")
			:UtilText.parse(BodyChanging.getTarget(), "Change how tall [npc.name] is.")),
			"HEIGHT",
			"cm",
			"cm",
			BodyChanging.getTarget().getHeightValue()+"cm<br/>("+Util.inchesToFeetAndInches(Util.conversionCentimetresToInches(BodyChanging.getTarget().getHeightValue()))+")",
			BodyChanging.getTarget().getHeightValue()<=BodyChanging.getTarget().getMinimumHeight(),
			BodyChanging.getTarget().getHeightValue()>=BodyChanging.getTarget().getMaximumHeight());
	}
	
	public static String getFullFemininityChoiceDiv() {
		contentSB.setLength(0);
		
		if( BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE_STRONG) {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.colourMasculineStrong(Very Masculine)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='CHOOSE_FEM_MASCULINE_STRONG' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.MASCULINE_PLUS.getShades()[0]+";'>Very Masculine</span>"
					+ "</div>");
		}
		if( BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE) {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.colourMasculine(Masculine)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='CHOOSE_FEM_MASCULINE' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.MASCULINE.getShades()[0]+";'>Masculine</span>"
					+ "</div>");
		}
		if( BodyChanging.getTarget().getFemininity()==Femininity.ANDROGYNOUS) {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.colourAndrogynous(Androgynous)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.ANDROGYNOUS.getShades()[0]+";'>Androgynous</span>"
					+ "</div>");
		}
		if( BodyChanging.getTarget().getFemininity()==Femininity.FEMININE) {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.colourFeminine(Feminine)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='CHOOSE_FEM_FEMININE' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.FEMININE.getShades()[0]+";'>Feminine</span>"
					+ "</div>");
		}
		if( BodyChanging.getTarget().getFemininity()==Femininity.FEMININE_STRONG) {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.colourFeminineStrong(Very Feminine)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='CHOOSE_FEM_FEMININE_STRONG' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.FEMININE_PLUS.getShades()[0]+";'>Very Feminine</span>"
					+ "</div>");
		}
		

		return applyWrapper("Femininity",
				(BodyChanging.getTarget().isPlayer()
					?"Change how masculine or feminine your body and face are."
					:UtilText.parse(BodyChanging.getTarget(), "Change how masculine or feminine [npc.namePos] body and face are.")),
				contentSB.toString(), false);
	}
	
	private static String applyWrapper(String title, String description, String input, boolean halfWidth) {
		if(halfWidth) {
			return "<div class='cosmetics-inner-container'>"
					+ "<h5 style='text-align:center;'>"
						+ title
					+"</h5>"
					+ "<p style='text-align:center;'>"
						+ description
					+ "</p>"
					+ input
					+ "</div>";
			
		} else {
			return "<div class='container-full-width'>"
						+"<div class='cosmetics-inner-container left'>"
							+ "<h5 style='text-align:center;'>"
								+ title
							+"</h5>"
							+ "<p style='text-align:center;'>"
								+ description
							+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>"
							+ input
						+ "</div>"
					+ "</div>";
		}
	}
	
	private static String applyFullVariableWrapper(String title, String description, String id, String measurement, String measurementPlural, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return "<div class='container-full-width'>"
					+"<div class='container-half-width'>"
						+ "<h5 style='text-align:center;'>"
							+ title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='container-half-width'>"
						+ "<div class='container-half-width' style='width:calc(33.3% - 16px); text-align:center;'>"
							+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-1"+measurement+")]":"[style.boldBadMinor(-1"+measurement+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-5"+measurementPlural+")]":"[style.boldBad(-5"+measurementPlural+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(33.3% - 16px); text-align:center;'>"
							+ value
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(33.3% - 16px); text-align:center;'>"
							+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (increaseDisabled?"[style.boldDisabled(+1"+measurement+")]":"[style.boldGoodMinor(+1"+measurement+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (increaseDisabled?"[style.boldDisabled(+5"+measurementPlural+")]":"[style.boldGood(+5"+measurementPlural+")]")
							+ "</div>"
						+ "</div>"
					+ "</div>"
				+ "</div>";
	}
	
	private static String applyFullVariableWrapperFluids(String title, String description, String id, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return "<div class='container-full-width'>"
					+"<div class='container-half-width'>"
						+ "<h5 style='text-align:center;'>"
							+ title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='container-half-width'>"
						+ "<div class='container-half-width' style='width:calc(33.3% - 16px); text-align:center;'>"
							+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-1ml)]":"[style.boldBadMinor(-1ml)]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-25ml)]":"[style.boldBad(-25ml)]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE_HUGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-500ml)]":"[style.boldBad(-500ml)]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(33.3% - 16px); text-align:center;'>"
							+ value
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(33.3% - 16px); text-align:center;'>"
							+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (increaseDisabled?"[style.boldDisabled(+1ml)]":"[style.boldGoodMinor(+1ml)]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (increaseDisabled?"[style.boldDisabled(+25ml)]":"[style.boldGood(+25ml)]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_HUGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:100%;'>"
								+ (increaseDisabled?"[style.boldDisabled(+500ml)]":"[style.boldGood(+500ml)]")
							+ "</div>"
						+ "</div>"
					+ "</div>"
				+ "</div>";
	}
	
	public static String getSelfTransformTailChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(TailType tail : TailType.values()) {
			if((tail.getRace() !=null && availableRaces.contains(tail.getRace()))
					|| tail==TailType.NONE) {

				Colour c = Colour.TEXT_GREY;
				
				if(tail.getRace() != null) {
					c = tail.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getTailType() == tail) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(tail.getRace()==null? "None": tail.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_TAIL_"+tail+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(tail.getRace()==null? "None": tail.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("Tail",
				(BodyChanging.getTarget().isPlayer()
					?"Change your tail type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] tail type.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformWingSizeDiv() {
		contentSB.setLength(0);
		
		for(WingSize wingSize : WingSize.values()) {
			if(BodyChanging.getTarget().getWingSize() == wingSize) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(wingSize.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_WING_SIZE_"+wingSize+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(wingSize.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Wing Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your wings."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] wings.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformWingChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(WingType wing : WingType.values()) {
			if((wing.getRace() !=null && availableRaces.contains(wing.getRace()))
					|| wing==WingType.NONE) {
				
				Colour c = Colour.TEXT_GREY;
				
				if(wing.getRace() != null) {
					c = wing.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getWingType() == wing) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(wing.getRace()==null? "None": wing.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_WING_"+wing+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(wing.getRace()==null? "None": wing.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Wings",
				(BodyChanging.getTarget().isPlayer()
					?"Change your wing type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] wing type.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformHornChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(HornType horn : HornType.values()) {
			if((horn.getRace() !=null && availableRaces.contains(horn.getRace()))
					|| horn==HornType.NONE) {
				
				Colour c = Colour.TEXT_GREY;
				
				if(horn.getRace() != null) {
					c = horn.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getHornType() == horn) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(horn.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_HORN_"+horn+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(horn.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Horns",
				(BodyChanging.getTarget().isPlayer()
					?"Change your horn type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] horn type.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformHornSizeDiv() {
		contentSB.setLength(0);
		
		for(HornLength hornLength : HornLength.values()) {
			if(HornLength.getHornLengthFromInt(BodyChanging.getTarget().getHornLength()) == hornLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(hornLength.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_HORN_LENGTH_"+hornLength+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(hornLength.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Horn Length",
				(BodyChanging.getTarget().isPlayer()
					?"Change the length of your horns."
					:UtilText.parse(BodyChanging.getTarget(), "Change the length of [npc.namePos] horns.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAntennaChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(AntennaType antenna : AntennaType.values()) {
			if((antenna.getRace() !=null && availableRaces.contains(antenna.getRace()))
					|| antenna==AntennaType.NONE) {
				
				Colour c = Colour.TEXT_GREY;
				
				if(antenna.getRace() != null) {
					c = antenna.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getAntennaType() == antenna) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(antenna.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_ANTENNA_"+antenna+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(antenna.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Antennae",
				(BodyChanging.getTarget().isPlayer()
					?"Change your antenna type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] antenna type.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformHairChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(HairType hair : HairType.values()) {
			if((hair.getRace() !=null && availableRaces.contains(hair.getRace()))) {
				
				if(BodyChanging.getTarget().getHairType() == hair) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+hair.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hair.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_HAIR_"+hair+"' class='cosmetics-button'>"
								+ "<span style='color:"+hair.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hair.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Hair",
				(BodyChanging.getTarget().isPlayer()
					?"Change your hair type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair type.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfDivHairStyles(String title, String description) {
		contentSB.setLength(0);

		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		for (HairStyle hairStyle : HairStyle.values()) {
			if (BodyChanging.getTarget().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName()) + "</b>"
						+ "</div>");
			} else {
				if(BodyChanging.getTarget().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'>"
								+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(hairStyle.getName()) + "</span>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName()) + ")]"
							+ "</div>");
				}
			}
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getSelfTransformAssChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(AssType ass : AssType.values()) {
			if((ass.getRace() !=null && availableRaces.contains(ass.getRace()))) {
				
				if(BodyChanging.getTarget().getAssType() == ass) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+ass.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ass.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_ASS_"+ass+"' class='cosmetics-button'>"
								+ "<span style='color:"+ass.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ass.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Ass",
				(BodyChanging.getTarget().isPlayer()
					?"Change your ass type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] ass type.")),
				contentSB.toString(), false);
	}
	
	public static String getSelfTransformBreastChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(BreastType breast : BreastType.values()) {
			if((breast.getRace() !=null && availableRaces.contains(breast.getRace()))) {
				
				if(BodyChanging.getTarget().getBreastType() == breast) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+breast.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_BREAST_"+breast+"' class='cosmetics-button'>"
								+ "<span style='color:"+breast.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Breast",
				(BodyChanging.getTarget().isPlayer()
					?"Change your breast type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] breast type.")),
				contentSB.toString(), false);
	}
	
	public static String getSelfTransformArmChoiceDiv(List<Race> availableRaces) {
		
		if(BodyChanging.getTarget().getRace()==Race.SLIME) {
			contentSB.setLength(0);
			
			for(ArmType arm : ArmType.values()) {
				if(arm.getRace() !=null && availableRaces.contains(arm.getRace())) {
					if(BodyChanging.getTarget().getArmType() == arm) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<b style='color:"+arm.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</b>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='CHANGE_ARM_"+arm+"' class='cosmetics-button'>"
									+ "<span style='color:"+arm.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</span>"
								+ "</div>");
					}
				}
			}

			return applyWrapper("Arms",
					(BodyChanging.getTarget().isPlayer()
						?"Change your arm type."
						:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] arm type.")),
					contentSB.toString(), true);
			
		} else {
			if(BodyChanging.getTarget().getArmType().getRace()==Race.DEMON) {
				contentSB.setLength(0);
				
				for(ArmType arm : ArmType.values()) {
					if(arm.getRace() !=null && arm.getRace() == Race.DEMON) {
						if(BodyChanging.getTarget().getArmType() == arm) {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</b>"
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div id='CHANGE_ARM_"+arm+"' class='cosmetics-button'>"
										+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</span>"
									+ "</div>");
						}
					}
				}
	
				return applyWrapper("Arms",
						(BodyChanging.getTarget().isPlayer()
							?"Change your arm type."
							:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] arm type.")),
						contentSB.toString(), true);
				
			} else {
				return ("<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Arms"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ (BodyChanging.getTarget().isPlayer()
								?"You can only change your arm type if your arms are already demonic in nature."
								:UtilText.parse(BodyChanging.getTarget(), "You can only change [npc.namePos] arm type if [npc.her] arms are already demonic in nature."))
						+ "</p>"
						+ "</div>");
			}
		}
	}
	
	public static String getSelfTransformLegChoiceDiv(List<Race> availableRaces) {

		if(BodyChanging.getTarget().getRace()==Race.SLIME) {
			contentSB.setLength(0);
			
			for(LegType leg : LegType.values()) {
				if(leg.getRace() !=null && availableRaces.contains(leg.getRace())) {
					if(BodyChanging.getTarget().getLegType() == leg) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<b style='color:"+leg.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</b>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='CHANGE_LEG_"+leg+"' class='cosmetics-button'>"
									+ "<span style='color:"+leg.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</span>"
								+ "</div>");
					}
				}
			}

			return applyWrapper("Legs",
					(BodyChanging.getTarget().isPlayer()
						?"Change your leg type."
						:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] leg type.")),
					contentSB.toString(), true);
			
			
		} else {
			if(BodyChanging.getTarget().getLegType().getRace()==Race.DEMON) {
				contentSB.setLength(0);
				
				for(LegType leg : LegType.values()) {
					if(leg.getRace() !=null && leg.getRace() == Race.DEMON) {
						if(BodyChanging.getTarget().getLegType() == leg) {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</b>"
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div id='CHANGE_LEG_"+leg+"' class='cosmetics-button'>"
										+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</span>"
									+ "</div>");
						}
					}
				}
	
				return applyWrapper("Legs",
						(BodyChanging.getTarget().isPlayer()
							?"Change your leg type."
							:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] leg type.")),
						contentSB.toString(), true);
				
			} else {
				return ("<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Legs"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ (BodyChanging.getTarget().isPlayer()
								?"You can only change your leg type if your legs are already demonic in nature."
								:UtilText.parse(BodyChanging.getTarget(), "You can only change [npc.namePos] leg type if [npc.her] legs are already demonic in nature."))
						+ "</p>"
						+ "</div>");
			}
		}
	}
	
	public static String getSelfTransformFaceChoiceDiv(List<Race> availableRaces) {
		if(BodyChanging.getTarget().getRace()==Race.SLIME) {
			contentSB.setLength(0);
			
			for(FaceType face : FaceType.values()) {
				if(face.getRace() !=null && availableRaces.contains(face.getRace())) {
					if(BodyChanging.getTarget().getFaceType() == face) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<b style='color:"+face.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(face.getTransformName())+"</b>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='CHANGE_FACE_"+face+"' class='cosmetics-button'>"
									+ "<span style='color:"+face.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(face.getTransformName())+"</span>"
								+ "</div>");
					}
				}
			}

			return applyWrapper("Face",
					(BodyChanging.getTarget().isPlayer()
						?"Change your face type."
						:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] face type.")),
					contentSB.toString(), true);
			
		} else {
			if(BodyChanging.getTarget().getFaceType().getRace()==Race.DEMON) {
				contentSB.setLength(0);
				
				for(FaceType face : FaceType.values()) {
					if(face.getRace() !=null && face.getRace() == Race.DEMON) {
						if(BodyChanging.getTarget().getFaceType() == face) {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(face.getTransformName())+"</b>"
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div id='CHANGE_FACE_"+face+"' class='cosmetics-button'>"
										+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(face.getTransformName())+"</span>"
									+ "</div>");
						}
					}
				}
	
				return applyWrapper("Face",
						(BodyChanging.getTarget().isPlayer()
							?"Change your face type."
							:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] face type.")),
						contentSB.toString(), true);
				
			} else {
				return ("<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Face"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ (BodyChanging.getTarget().isPlayer()
								?"You can only change your face type if your face is already demonic in nature."
								:UtilText.parse(BodyChanging.getTarget(), "You can only change [npc.namePos] face type if [npc.her] face is already demonic in nature."))
						+ "</p>"
						+ "</div>");
			}
		}
	}
	
	public static String getSelfTransformBodyChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(SkinType skin : SkinType.values()) {
			if(availableRaces.contains(skin.getRace())) {
				if(BodyChanging.getTarget().getSkinType() == skin) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+skin.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(skin.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_SKIN_"+skin+"' class='cosmetics-button'>"
								+ "<span style='color:"+skin.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(skin.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Body",
				(BodyChanging.getTarget().isPlayer()
					?"Change your body type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] body type.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformEarChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(EarType ear : EarType.values()) {
			if(availableRaces.contains(ear.getRace())) {
				if(BodyChanging.getTarget().getEarType() == ear) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+ear.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ear.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_EAR_"+ear+"' class='cosmetics-button'>"
								+ "<span style='color:"+ear.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ear.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Ears",
				(BodyChanging.getTarget().isPlayer()
					?"Change your ear type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] ear type.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformEyeChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(EyeType eye : EyeType.values()) {
			if(availableRaces.contains(eye.getRace())) {
				if(BodyChanging.getTarget().getEyeType() == eye) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+eye.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(eye.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_EYE_"+eye+"' class='cosmetics-button'>"
								+ "<span style='color:"+eye.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(eye.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Eyes",
				(BodyChanging.getTarget().isPlayer()
					?"Change your eye type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] eye type.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformIrisChoiceDiv() {
		contentSB.setLength(0);
		
		for(EyeShape eyeShape : EyeShape.values()) {
			if(BodyChanging.getTarget().getIrisShape() == eyeShape) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_IRIS_SHAPE_"+eyeShape+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Iris Shape",
				(BodyChanging.getTarget().isPlayer()
					?"Change the shape of your irises."
					:UtilText.parse(BodyChanging.getTarget(), "Change the shape of [npc.namePos] irises.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformPupilChoiceDiv() {
		contentSB.setLength(0);
		
		for(EyeShape eyeShape : EyeShape.values()) {
			if(BodyChanging.getTarget().getPupilShape() == eyeShape) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_PUPIL_SHAPE_"+eyeShape+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Pupil Shape",
				(BodyChanging.getTarget().isPlayer()
					?"Change the shape of your pupils."
					:UtilText.parse(BodyChanging.getTarget(), "Change the shape of [npc.namePos] pupils.")),
				contentSB.toString(), true);
	}
	
	
	public static String getSelfTransformLipSizeDiv() {
		contentSB.setLength(0);
		
		for(LipSize lipSize : LipSize.values()) {
			if(BodyChanging.getTarget().getLipSize() == lipSize) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(lipSize.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_LIP_SIZE_"+lipSize+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(lipSize.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Lip Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your lips."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] lips.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformThroatModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div  id='CHANGE_MOUTH_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_MOUTH_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Lip & Throat Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your lips & throat."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] lips & throat.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformTongueModifiersDiv() {
		contentSB.setLength(0);
		
		for(TongueModifier tongueMod : TongueModifier.values()) {
			if(BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
				contentSB.append(
						"<div  id='CHANGE_TONGUE_MOD_"+tongueMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(tongueMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_TONGUE_MOD_"+tongueMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(tongueMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Tongue Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your tongue."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] tongue.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformTongueSizeDiv() {
		contentSB.setLength(0);
		
		for(TongueLength tongueLength : TongueLength.values()) {
			if(BodyChanging.getTarget().getTongueLength() == tongueLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(tongueLength.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_TONGUE_LENGTH_"+tongueLength+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(tongueLength.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Tongue Length",
				(BodyChanging.getTarget().isPlayer()
					?"Change the length of your tongue."
					:UtilText.parse(BodyChanging.getTarget(), "Change the length of [npc.namePos] tongue.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAssSizeDiv() {
		contentSB.setLength(0);
		
		for(AssSize as : AssSize.values()) {
			if(BodyChanging.getTarget().getAssSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_ASS_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Ass Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your ass."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] ass.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformHipSizeDiv() {
		contentSB.setLength(0);
		
		for(HipSize hs : HipSize.values()) {
			if(BodyChanging.getTarget().getHipSize() == hs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+hs.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_HIP_SIZE_"+hs+"' class='cosmetics-button'>"
							+ "<span style='color:"+hs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Hip Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your hips."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] hips.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAnusCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.values()) {
			if(BodyChanging.getTarget().getAssCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Anus Capacity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the capacity of your asshole."
					:UtilText.parse(BodyChanging.getTarget(), "Change the capacity of [npc.namePos] asshole.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAnusWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getAssWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_WETNESS_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Anus Wetness",
				(BodyChanging.getTarget().isPlayer()
					?"Change the wetness of your asshole."
					:UtilText.parse(BodyChanging.getTarget(), "Change the wetness of [npc.namePos] asshole.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAnusElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getAssElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Anus Elasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the elasticity of your asshole."
					:UtilText.parse(BodyChanging.getTarget(), "Change the elasticity of [npc.namePos] asshole.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAnusPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getAssPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Anus Plasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the plasticity of your asshole."
					:UtilText.parse(BodyChanging.getTarget(), "Change the plasticity of [npc.namePos] asshole.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAnusModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div  id='CHANGE_ANUS_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_ANUS_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Anus Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your anus."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] anus.")),
				contentSB.toString(), false);
	}
	
	public static String getSelfTransformBreastSizeDiv() {
		contentSB.setLength(0);
		
		for(CupSize breastSize : CupSize.values()) {
			if(BodyChanging.getTarget().getBreastSize() == breastSize) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(breastSize.getCupSizeName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_SIZE_"+breastSize+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(breastSize.getCupSizeName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Breast Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your breasts."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] breasts.")),
				contentSB.toString(), false);
	}
	
	public static String getSelfTransformBreastShapeDiv() {
		contentSB.setLength(0);
		
		for(BreastShape bs : BreastShape.values()) {
			if(BodyChanging.getTarget().getBreastShape() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_SHAPE_"+bs+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Breast Shape",
				(BodyChanging.getTarget().isPlayer()
					?"Change the shape of your breasts."
					:UtilText.parse(BodyChanging.getTarget(), "Change the shape of [npc.namePos] breasts.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformBreastRowsDiv() {
		contentSB.setLength(0);
		
		for(int i=1; i <= Breast.MAXIMUM_BREAST_ROWS; i++) {
			if(BodyChanging.getTarget().getBreastRows() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Breast Rows",
				(BodyChanging.getTarget().isPlayer()
					?"Change the number of breast rows you have."
					:UtilText.parse(BodyChanging.getTarget(), "Change the number of breast rows [npc.name] has.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformNippleCountDiv() {
		contentSB.setLength(0);
		
		for(int i=1; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			if(BodyChanging.getTarget().getNippleCountPerBreast() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Nipple Count",
				(BodyChanging.getTarget().isPlayer()
					?"Change the number of nipples you have on each breast."
					:UtilText.parse(BodyChanging.getTarget(), "Change the number of nipples [npc.name] has on each breast.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformNippleSizeDiv() {
		contentSB.setLength(0);
		
		for(NippleSize ns : NippleSize.values()) {
			if(BodyChanging.getTarget().getNippleSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_SIZE_"+ns+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Nipple Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your nipples."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] nipples.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformAreolaeSizeDiv() {
		contentSB.setLength(0);
		
		for(AreolaeSize as : AreolaeSize.values()) {
			if(BodyChanging.getTarget().getAreolaeSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Areolae Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your areolae."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] areolae.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformNippleCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.values()) {
			if(BodyChanging.getTarget().getNippleCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Nipple Capacity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the capacity of your nipples."
					:UtilText.parse(BodyChanging.getTarget(), "Change the capacity of [npc.namePos] nipples.")),
				contentSB.toString(), true);
	}
	
	public static int[] demonLactationValues = new int[] {0, 5, 20, 50, 250, 500, 750, 1000, 1500, 2500, 5000, 10000};//, 20000, 50000, 100000};
	
	public static String getSelfTransformLactationDiv() {
		contentSB.setLength(0);
		
		for(int i : demonLactationValues) {
			if(BodyChanging.getTarget().getBreastRawMilkStorageValue() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+i+"mL</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LACTATION_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>"+i+"mL</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Lactation",
				(BodyChanging.getTarget().isPlayer()
					?"Change how much milk you produce."
					:UtilText.parse(BodyChanging.getTarget(), "Change how much milk [npc.name] produces.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformNippleElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getNippleElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Nipple Elasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the elasticity of your nipples."
					:UtilText.parse(BodyChanging.getTarget(), "Change the elasticity of [npc.namePos] nipples.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformNipplePlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getNipplePlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Nipple Plasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the plasticity of your nipples."
					:UtilText.parse(BodyChanging.getTarget(), "Change the plasticity of [npc.namePos] nipples.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformNippleModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='CHANGE_NIPPLE_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_NIPPLE_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Nipple Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your nipples."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] nipples.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaChoiceDiv(List<Race> availableRaces) {
		contentSB.setLength(0);
		
		for(VaginaType vagina : VaginaType.values()) {
			if((vagina.getRace() !=null && availableRaces.contains(vagina.getRace()))
					|| vagina==VaginaType.NONE) {
				
				Colour c = Colour.TEXT_GREY;
				
				if(vagina.getRace() != null) {
					c = vagina.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getVaginaType() == vagina) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(vagina.getRace()==null?"None":vagina.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_VAGINA_"+vagina+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(vagina.getRace()==null?"None":vagina.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Vagina",
				(BodyChanging.getTarget().isPlayer()
					?"Change your vagina type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] vagina type.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformVaginaCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.values()) {
			if(BodyChanging.getTarget().getVaginaCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Vagina Capacity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the capacity of your vagina."
					:UtilText.parse(BodyChanging.getTarget(), "Change the capacity of [npc.namePos] vagina.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getVaginaWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_WETNESS_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Vagina Wetness",
				(BodyChanging.getTarget().isPlayer()
					?"Change the wetness of your vagina."
					:UtilText.parse(BodyChanging.getTarget(), "Change the wetness of [npc.namePos] vagina.")),
				contentSB.toString(), true);
	}

	public static String getSelfTransformVaginaElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getVaginaElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Vagina Elasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the elasticity of your vagina."
					:UtilText.parse(BodyChanging.getTarget(), "Change the elasticity of [npc.namePos] vagina.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getVaginaPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Vagina Plasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the plasticity of your vagina."
					:UtilText.parse(BodyChanging.getTarget(), "Change the plasticity of [npc.namePos] vagina.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformClitorisSizeDiv() {
		contentSB.setLength(0);
		
		for(ClitorisSize size : ClitorisSize.values()) {
			if(BodyChanging.getTarget().getVaginaClitorisSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Clitoris Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your clitoris."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] clitoris.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformLabiaSizeDiv() {
		contentSB.setLength(0);
		
		for(LabiaSize size : LabiaSize.values()) {
			if(BodyChanging.getTarget().getVaginaLabiaSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LABIA_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Labia Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your labia."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] labia.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='CHANGE_VAGINA_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_VAGINA_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Vagina Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your vagina."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] vagina.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaUrethraCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.values()) {
			if(BodyChanging.getTarget().getVaginaUrethraCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_URETHRA_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Capacity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the capacity of your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the capacity of [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaUrethraElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getVaginaUrethraElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_URETHRA_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Elasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the elasticity of your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the elasticity of [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaUrethraPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getVaginaUrethraPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_URETHRA_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Plasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the plasticity of your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the plasticity of [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformVaginaUrethraModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasVaginaUrethraOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='CHANGE_VAGINA_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_VAGINA_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static String getSelfTransformPenisChoiceDiv(List<Race> availableRaces, boolean halfWidth) {
		contentSB.setLength(0);
		
		for(PenisType penis : PenisType.values()) {
			if(((penis.getRace() !=null && availableRaces.contains(penis.getRace()))
					|| penis==PenisType.NONE)
					&& penis!=PenisType.DILDO) {
				
				Colour c = Colour.TEXT_GREY;
				
				if(penis.getRace() != null) {
					c = penis.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getPenisType() == penis) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(penis.getRace()==null?"None":penis.getTransformName())+"</b>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='CHANGE_PENIS_"+penis+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(penis.getRace()==null?"None":penis.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("Penis",
				(BodyChanging.getTarget().isPlayer()
					?"Change your penis type."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] penis type.")),
				contentSB.toString(), halfWidth);
	}
	
	public static String getSelfTransformPenisSizeDiv() {
		return applyFullVariableWrapper("Penis Size",
				(BodyChanging.getTarget().isPlayer()
			?"Change the size of your penis."
			:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] penis.")),
			"PENIS_SIZE",
			" inch",
			" inches",
			Util.inchesToFeetAndInches(BodyChanging.getTarget().getPenisRawSizeValue())+"<br/>("+Util.conversionInchesToCentimetres(BodyChanging.getTarget().getPenisRawSizeValue())+"cm)",
			BodyChanging.getTarget().getPenisRawSizeValue()<=0,
			BodyChanging.getTarget().getPenisRawSizeValue()>=PenisSize.SEVEN_STALLION.getMaximumValue());
	}
	
	public static String getSelfTransformPenisGirthDiv() {
		contentSB.setLength(0);
		
		for(PenisGirth girth : PenisGirth.values()) {
			if(BodyChanging.getTarget().getPenisGirth() == girth) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(girth.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_GIRTH_"+girth+"' class='cosmetics-button'>"
							+ "<span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Penis Girth",
				(BodyChanging.getTarget().isPlayer()
					?"Change the girth of your penis."
					:UtilText.parse(BodyChanging.getTarget(), "Change the girth of [npc.namePos] penis.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformTesticleSizeDiv() {
		contentSB.setLength(0);
		
		for(TesticleSize size : TesticleSize.values()) {
			if(BodyChanging.getTarget().getTesticleSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Testicle Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change the size of your testicles."
					:UtilText.parse(BodyChanging.getTarget(), "Change the size of [npc.namePos] testicles.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformTesticleCountDiv() {
		contentSB.setLength(0);
		
		for(int i=Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i+=2) {
			if(BodyChanging.getTarget().getTesticleCount() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Testicle Count",
				(BodyChanging.getTarget().isPlayer()
					?"Change how many testicles you have."
					:UtilText.parse(BodyChanging.getTarget(), "Change how many testicles [npc.name] has.")),
				contentSB.toString(), true);
	}
	

	public static String getInternalTesticleDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isInternalTesticles()) {
			contentSB.append(
					"<div id='TESTICLES_INTERNAL_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>External</span>"
					+ "</div>"
					+"<div class='cosmetics-button active'>"
						+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>Internal</b>"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>External</span>"
					+ "</div>"
					+"<div id='TESTICLES_INTERNAL_ON' class='cosmetics-button'>"
						+ "<b style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>Internal</b>"
					+ "</div>");
		}
		

		return applyWrapper("Internal Testicles",
				(BodyChanging.getTarget().isPlayer()
					?"Set whether your testicles are internal or not."
					:UtilText.parse(BodyChanging.getTarget(), "Set whether [npc.namePos] testicles are internal or not.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformUrethraCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.values()) {
			if(BodyChanging.getTarget().getPenisCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='URETHRA_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Capacity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the capacity of your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the capacity of [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformCumProductionDiv() {
		return applyFullVariableWrapperFluids("Cum Storage",
				(BodyChanging.getTarget().isPlayer()
			?"Change your maximum cum storage."
			:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] maximum cum storage.")),
			"CUM_PRODUCTION",
			BodyChanging.getTarget().getPenisRawCumStorageValue()+"ml",
			BodyChanging.getTarget().getPenisRawCumStorageValue()<=0,
			BodyChanging.getTarget().getPenisRawCumStorageValue()>=CumProduction.SEVEN_MONSTROUS.getMaximumValue());
	}
	
	public static String getSelfTransformUrethraElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getUrethraElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='URETHRA_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Elasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the elasticity of your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the elasticity of [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformUrethraPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getUrethraPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='URETHRA_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Plasticity",
				(BodyChanging.getTarget().isPlayer()
					?"Change the plasticity of your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the plasticity of [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformPenisModifiersDiv() {
		contentSB.setLength(0);
		
		for(PenetrationModifier orificeMod : PenetrationModifier.values()) {
			if(BodyChanging.getTarget().hasPenisModifier(orificeMod)) {
				contentSB.append(
						"<div id='CHANGE_PENIS_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_PENIS_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Penis Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your penis."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] penis.")),
				contentSB.toString(), true);
	}
	
	public static String getSelfTransformUrethraModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='CHANGE_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.RACE_DEMON.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CHANGE_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.RACE_DEMON.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Urethra Modifiers",
				(BodyChanging.getTarget().isPlayer()
					?"Change the modifiers for your urethra."
					:UtilText.parse(BodyChanging.getTarget(), "Change the modifiers for [npc.namePos] urethra.")),
				contentSB.toString(), true);
	}
	
	
	
	
	
	
	public static String getBodySizeChoiceDiv() {
		contentSB.setLength(0);
		
		for(BodySize bs : BodySize.values()) {
			if( BodyChanging.getTarget().getBodySize() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+ BodyChanging.getTarget().getBodySize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(bs.getName(false))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BODY_SIZE_"+bs+"' class='cosmetics-button'>"
							+ "<span style='color:"+bs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(bs.getName(false))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Body Size",
				(BodyChanging.getTarget().isPlayer()
					?"Change your body size."
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] body size.")),
				contentSB.toString(), true);
	}
	
	public static String getMuscleChoiceDiv() {
		contentSB.setLength(0);
		
		for(Muscle muscle : Muscle.values()) {
			if( BodyChanging.getTarget().getMuscle() == muscle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+ BodyChanging.getTarget().getMuscle().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='MUSCLE_"+muscle+"' class='cosmetics-button'>"
							+ "<span style='color:"+muscle.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("Muscle Definition",
				(BodyChanging.getTarget().isPlayer()
					?"Change your muscle definition."+(!Main.game.isInNewWorld()?" This does not affect the physique attribute of your character.":"")
					:UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] muscle definition.")),
				contentSB.toString(), true);
	}
	
	public static String getLipSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Lip Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your lips are."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(LipSize ls : LipSize.values()) {
			if(BodyChanging.getTarget().getLipSize() == ls) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+ls.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ls.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LIP_SIZE_"+ls+"' class='cosmetics-button'>"
							+ "<span style='color:"+ls.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ls.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getLipPuffynessDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Puffy Lips"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose whether your lips are extra puffy or not."
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
			contentSB.append(
					"<div id='LIP_PUFFY_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>Normal</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(Puffy)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(Normal)]"
					+ "</div>"
					+ "<div id='LIP_PUFFY_ON' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>Puffy</span>"
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static CupSize[] getBreastSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new CupSize[] {CupSize.FLAT, CupSize.TRAINING_AAA, CupSize.TRAINING_AA, CupSize.TRAINING_A};
		} else {
			return new CupSize[] {CupSize.AA, CupSize.A, CupSize.B, CupSize.C, CupSize.D, CupSize.DD, CupSize.E};
		}
	}
	
	public static String getBreastSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Breast Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your breasts are, in cup size."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		CupSize[] sizesAvailable = getBreastSizesAvailable();
		
		for(CupSize cs : sizesAvailable) {
			if(BodyChanging.getTarget().getBreastSize() == cs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(cs.getCupSizeName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_SIZE_"+cs+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(cs.getCupSizeName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBreastShapeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Breast Shape"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose the shape of your breasts."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(BreastShape bs : BreastShape.values()) {
			if(BodyChanging.getTarget().getBreastShape() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_SHAPE_"+bs+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static NippleSize[] getNippleSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new NippleSize[] {NippleSize.ZERO_TINY, NippleSize.ONE_SMALL, NippleSize.TWO_BIG};
		} else {
			return new NippleSize[] {NippleSize.ZERO_TINY, NippleSize.ONE_SMALL, NippleSize.TWO_BIG, NippleSize.THREE_LARGE, NippleSize.FOUR_MASSIVE};
		}
	}
	
	public static String getNippleSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Nipple Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your nipples are."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		NippleSize[] sizesAvailable = getNippleSizesAvailable();
		
		for(NippleSize ns : sizesAvailable) {
			if(BodyChanging.getTarget().getNippleSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+ns.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_SIZE_"+ns+"' class='cosmetics-button'>"
							+ "<span style='color:"+ns.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static AreolaeSize[] getAreolaeSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new AreolaeSize[] {AreolaeSize.ZERO_TINY, AreolaeSize.ONE_SMALL, AreolaeSize.TWO_BIG};
		} else {
			return new AreolaeSize[] {AreolaeSize.ZERO_TINY, AreolaeSize.ONE_SMALL, AreolaeSize.TWO_BIG, AreolaeSize.THREE_LARGE, AreolaeSize.FOUR_MASSIVE};
		}
	}
	
	public static String getAreolaeSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Areolae Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your areolae are."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		AreolaeSize[] sizesAvailable = getAreolaeSizesAvailable();
		
		for(AreolaeSize as : sizesAvailable) {
			if(BodyChanging.getTarget().getAreolaeSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getNipplePuffynessDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Puffy Nipples"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose whether your nipples are extra puffy or not."
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
			contentSB.append(
					"<div id='NIPPLE_PUFFY_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>Normal</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(Puffy)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(Normal)]"
					+ "</div>"
					+ "<div id='NIPPLE_PUFFY_ON' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>Puffy</span>"
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static int[] getLactationQuantitiesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new int[] {0};
		} else {
			return new int[] {0, 5, 10, 15, 30, 50, 75, 100, 150};
		}
	}
	
	public static String getLactationDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Natural Lactation"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how much you're currently lactating."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		int[] sizesAvailable = getLactationQuantitiesAvailable();
		
		for(int i : sizesAvailable) {
			if(BodyChanging.getTarget().getBreastRawMilkStorageValue() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+i+"mL</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LACTATION_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>"+i+"mL</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static AssSize[] getAssSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new AssSize[] {AssSize.ZERO_FLAT, AssSize.ONE_TINY, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, AssSize.FOUR_LARGE};
		} else {
			return AssSize.values();
		}
	}
	
	public static String getAssSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Ass Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your ass is."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		AssSize[] sizesAvailable = getAssSizesAvailable();
		
		for(AssSize as : sizesAvailable) {
			if(BodyChanging.getTarget().getAssSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ASS_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static HipSize[] getHipSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new HipSize[] {HipSize.ZERO_NO_HIPS, HipSize.ONE_VERY_NARROW, HipSize.TWO_NARROW, HipSize.THREE_GIRLY};
		} else {
			return HipSize.values();
		}
	}
	
	public static String getHipSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Hip Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your hips are."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		HipSize[] sizesAvailable = getHipSizesAvailable();
		
		for(HipSize hs : sizesAvailable) {
			if(BodyChanging.getTarget().getHipSize() == hs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+hs.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HIP_SIZE_"+hs+"' class='cosmetics-button'>"
							+ "<span style='color:"+hs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBleachedAnusDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Bleached Anus"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose whether your anus has been bleached to have the same skin tone as the rest of your body."
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().isAssBleached()) {
			contentSB.append(
					"<div id='ANUS_BLEACHED_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>Normal</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(Bleached)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(Normal)]"
					+ "</div>"
					+ "<div id='ANUS_BLEACHED_ON' class='cosmetics-button'>"
						+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>Bleached</span>"
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static int[] getPenisSizesAvailable() {
		return new int[] {1, 2, 3, 4, 5, 6, 7, 8};
	}
	
	public static String getPenisSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Penis Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your penis is."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		int[] sizesAvailable = getPenisSizesAvailable();
		
		for(int size : sizesAvailable) {
			if(BodyChanging.getTarget().getPenisRawSizeValue() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+size+"&quot;</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>"+size+"&quot;</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static TesticleSize[] getTesticleSizesAvailable() {
		return new TesticleSize[] {TesticleSize.ZERO_VESTIGIAL, TesticleSize.ONE_TINY, TesticleSize.TWO_AVERAGE, TesticleSize.THREE_LARGE};
	}
	
	public static String getTesticleSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Testicle Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your testicles are."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		TesticleSize[] sizesAvailable = getTesticleSizesAvailable();
		
		for(TesticleSize size : sizesAvailable) {
			if(BodyChanging.getTarget().getTesticleSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static CumProduction[] getCumProductionAvailable() {
		return new CumProduction[] {CumProduction.ZERO_NONE, CumProduction.ONE_TRICKLE, CumProduction.TWO_SMALL_AMOUNT, CumProduction.THREE_AVERAGE, CumProduction.FOUR_LARGE};
	}
	
	public static String getCumProductionDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Cum Storage"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how much cum your balls are able to hold."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		CumProduction[] sizesAvailable = new CumProduction[] {CumProduction.ZERO_NONE, CumProduction.ONE_TRICKLE, CumProduction.TWO_SMALL_AMOUNT, CumProduction.THREE_AVERAGE, CumProduction.FOUR_LARGE};
		
		for(CumProduction value : sizesAvailable) {
			if(BodyChanging.getTarget().getPenisCumStorage() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+value.getMedianValue()+"mL</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CUM_PRODUCTION_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+value.getMedianValue()+"mL</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getVaginaCapacityDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Vagina Capacity"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Select the capacity of your vagina. A higher capacity means that you'll be able to take larger insertions easier, but if it's too loose, it won't be very pleasurable for partners with small cocks."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(Capacity value : Capacity.values()) {
			if(BodyChanging.getTarget().getVaginaCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getClitorisSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Clitoris Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your clitoris is."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		ClitorisSize[] sizesAvailable = new ClitorisSize[] {ClitorisSize.ZERO_AVERAGE, ClitorisSize.ONE_BIG};
		
		for(ClitorisSize size : sizesAvailable) {
			if(BodyChanging.getTarget().getVaginaClitorisSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getLabiaSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Labia Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how large your labia are."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(LabiaSize size : LabiaSize.values()) {
			if(BodyChanging.getTarget().getVaginaLabiaSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LABIA_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	
	// ---------------------- Kate's Shop: ---------------------- //
	
	public static String getKatesDivHairLengths(boolean withCost, String title, String description) {
		contentSB.setLength(0);
		
		boolean noCost = !withCost;
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
							+(noCost
									?""
									:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST
											? UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_LENGTH_COST, "b")
											: UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_LENGTH_COST, "b", Colour.GENERIC_BAD)))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		for (HairLength hairLength : HairLength.values()) {
			if (BodyChanging.getTarget().getHairLength() == hairLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:" + hairLength.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(hairLength.getDescriptor()) + "</b>"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='HAIR_LENGTH_"+hairLength+"' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST || noCost
									? "<span style='color:"+hairLength.getColour().getShades()[0]+";'>" + Util.capitaliseSentence(hairLength.getDescriptor()) + "</span>"
									: "[style.colourDisabled(" + Util.capitaliseSentence(hairLength.getDescriptor()) + ")]")
						+ "</div>");
			}
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivHairStyles(boolean withCost, String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !withCost;
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
							+(noCost
									?""
									:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST
										? UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_STYLE_COST, "b")
										: UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_STYLE_COST, "b", Colour.GENERIC_BAD)))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		for (HairStyle hairStyle : HairStyle.values()) {
			if (BodyChanging.getTarget().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName()) + "</b>"
						+ "</div>");
			} else {
				if(BodyChanging.getTarget().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST || noCost
										? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(hairStyle.getName()) + "</span>"
										: "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName()) + ")]")
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName()) + ")]"
							+ "</div>");
				}
			}
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivAssHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, BodyChanging.getTarget().getAssHair(), "ASS_HAIR_", false);
	}
	
	public static String getKatesDivUnderarmHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, BodyChanging.getTarget().getUnderarmHair(), "UNDERARM_HAIR_", false);
	}
	
	public static String getKatesDivFacialHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, BodyChanging.getTarget().getFacialHair(), "FACIAL_HAIR_", BodyChanging.getTarget().isFeminine() && !Main.game.isFemaleFacialHairEnabled());
	}
	
	public static String getKatesDivPubicHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, BodyChanging.getTarget().getPubicHair(), "PUBIC_HAIR_", false);
	}
	
	private static String getKatesDivGenericBodyHair(String title, String description, BodyHair activeHair, String id, boolean blockAllButNoneOptions) {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
							+(noCost
								?""
								:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST
									? UtilText.formatAsMoney(SuccubisSecrets.BASE_BODY_HAIR_COST, "b")
									: UtilText.formatAsMoney(SuccubisSecrets.BASE_BODY_HAIR_COST, "b", Colour.GENERIC_BAD)))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		for (BodyHair bodyHair : BodyHair.values()) {
			if (activeHair == bodyHair) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(bodyHair.getName()) + "</b>"
						+ "</div>");
			} else {
				if(blockAllButNoneOptions) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(bodyHair.getName()) + ")]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+id+bodyHair+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || noCost
										? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(bodyHair.getName()) + "</span>"
										: "[style.colourDisabled(" + Util.capitaliseSentence(bodyHair.getName()) + ")]")
							+ "</div>");
				}
			}
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivGenericBodyHairDisabled(String title, String description, String disabledDescription) {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>"
					+ "<p style='text-align:center; color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
						+ disabledDescription
					+ "</p>"
				+ "</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivAnalBleaching(String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+ title
							+ (noCost
									?""
									:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
										? UtilText.formatAsMoney(SuccubisSecrets.BASE_ANAL_BLEACHING_COST, "b")
										: UtilText.formatAsMoney(SuccubisSecrets.BASE_ANAL_BLEACHING_COST, "b", Colour.GENERIC_BAD)))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().isAssBleached()) {
			contentSB.append(
					"<div id='BLEACHING_OFF' class='cosmetics-button'>"
						+ "[style.colourDisabled(Normal)]"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldArcane(Bleached)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "Normal"
					+ "</div>"
					+ "<div id='BLEACHING_ON' class='cosmetics-button'>"
						+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
							?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Bleached</span>"
							:"[style.colourDisabled(Bleached)]")
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	//TODO reset on open
	private static Map<BodyCoveringType, Covering> coveringsToBeApplied = new HashMap<>();
	
	public static Map<BodyCoveringType, Covering> getCoveringsToBeApplied() {
		return coveringsToBeApplied;
	}

	public static String getKatesDivCoveringsNew(boolean withCost, BodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow) {
		return getKatesDivCoveringsNew(withCost, coveringType, title, description, withSecondary, withGlow, true);
	}
	
	public static String getKatesDivCoveringsNew(boolean withCost, BodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow, boolean withDyeAndExtraPatterns) {
		boolean disabledButton = !coveringsToBeApplied.containsKey(coveringType) || coveringsToBeApplied.get(coveringType).equals(BodyChanging.getTarget().getCovering(coveringType));
		
		contentSB = new StringBuilder(
				"<div class='container-full-width' style='text-align:center;'>"
					+ "<div class='container-quarter-width'>"
						+ "<p>"
							+ "<b>"
								+ Util.capitaliseSentence(title)
							+ "</b>"
						+ "</p>"
						+ "<p>"
							+ "Currently:<br/><i>"+BodyChanging.getTarget().getCovering(coveringType).getFullDescription(BodyChanging.getTarget(), true)+"</i>"
						+ "</p>"
						
							+ (disabledButton
									?"<div class='normal-button disabled' style='width:90%; margin:2% auto; padding:0; text-align:center; bottom:0;'>"
										+"<span style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>Apply Changes"
											+ (withCost
												?"<br/>("+UtilText.formatAsMoneyUncoloured(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b")+")"
												:"")
										+"</span>"
									+ "</div>"
									:"<div class='normal-button' style='width:90%; margin:2% auto; padding:0; text-align:center; bottom:0;' id='APPLY_COVERING_"+coveringType+"'>"
										+ "Apply Changes"
										+ (withCost
											?"<br/>("
												+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
													? UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b")
													: UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b", Colour.GENERIC_BAD))+")"
											:"")
										+ "</div>")
						
						
					+ "</div>"
					+ "<div class='container-quarter-width'>"
					+ "Pattern:<br/>");

		Covering activeCovering = !coveringsToBeApplied.containsKey(coveringType)
										?BodyChanging.getTarget().getCovering(coveringType)
										:coveringsToBeApplied.get(coveringType);
		
		List<CoveringPattern> availablePatterns = withDyeAndExtraPatterns
														?coveringType.getAllPatterns()
														:coveringType.getNaturalPatterns();
		for (CoveringPattern pattern : availablePatterns) {
			if (activeCovering.getPattern() == pattern) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getName()) + "</b>"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='"+coveringType+"_PATTERN_"+pattern+"' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
									? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
									: "[style.colourDisabled(" + Util.capitaliseSentence(pattern.getName()) + ")]")
						+ "</div>");
			}
		}
		contentSB.append("</div>");
		contentSB.append("<div class='container-half-width''>");
		contentSB.append( "<div class='container-half-width'>"
					+ "Primary Colour:<br/>");
	
	
			List<Colour> availablePrimaryColours = new ArrayList<>(withDyeAndExtraPatterns
															?coveringType.getAllPrimaryColours()
															:coveringType.getNaturalColoursPrimary());
			int rainbowIncrement=3;
			Collections.sort(availablePrimaryColours, (c1, c2)->c2.isMetallic()?(c1.isMetallic()?0:-1):(c1.isMetallic()?1:0));
			for (Colour c : availablePrimaryColours) {
				contentSB.append("<div class='normal-button"+(activeCovering.getPrimaryColour()==c?" selected":"")+"' id='"+coveringType+"_PRIMARY_"+c+"'"
										+ " style='width:auto; margin-right:4px;"+(activeCovering.getPrimaryColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ (c.isMetallic()
											?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:(c.isRainbow()
													?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg,"
															+ " #c4e17f 0px, #c4e17f "+rainbowIncrement+"px,"
															+ "#f7fdca "+rainbowIncrement+"px, #f7fdca "+2*rainbowIncrement+"px,"
															+ "#fad071 "+2*rainbowIncrement+"px, #fad071 "+3*rainbowIncrement+"px,"
															+ "#f0766b "+3*rainbowIncrement+"px, #f0766b "+4*rainbowIncrement+"px,"
															+ "#db9dbe "+4*rainbowIncrement+"px, #db9dbe "+5*rainbowIncrement+"px,"
															+ "#c49cdf "+5*rainbowIncrement+"px, #c49cdf "+6*rainbowIncrement+"px,"
															+ "#6599e2 "+6*rainbowIncrement+"px, #6599e2 "+7*rainbowIncrement+"px,"
															+ "#61c2e4 "+7*rainbowIncrement+"px, #61c2e4 "+8*rainbowIncrement+"px);"
													:"<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"))
										+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")
									+"</div>"
								+ "</div>");
			}
			contentSB.append("<br/>");
			if(activeCovering.getPrimaryColour() == Colour.COVERING_NONE || !withGlow) { // Disable glow:
				
			} else {
				if(activeCovering.isPrimaryGlowing()) {
					contentSB.append(
							"<div class='normal-button active' id='"+coveringType+"_PRIMARY_GLOW_OFF' style='width:50%; margin:2% auto; padding:0; text-align:center;'>"
								+ "[style.boldArcane(Glow)]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+coveringType+"_PRIMARY_GLOW_ON' class='normal-button' style='width:50%; margin:2% auto; padding:0; text-align:center;'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
									?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glow</span>"
									:"[style.colourDisabled(Glowing)]")
							+ "</div>");
				}
			}
			contentSB.append("<p>"
						+ "<b style='color:"+activeCovering.getPrimaryColour().toWebHexString()+";"
								+(activeCovering.isPrimaryGlowing()
										?"text-shadow: 0px 0px 4px "+activeCovering.getPrimaryColour().getShades()[4]+";"
										:"")+"'>"
							+Util.capitaliseSentence(activeCovering.getPrimaryColour().getName())
						+"</b>"
					+ "</p>");
			
			contentSB.append("</div>"
					+ "<div class='container-half-width'>"
					+ "Secondary Colour:<br/>");
	
			List<Colour> availableSecondaryColours = new ArrayList<>(withDyeAndExtraPatterns
														?coveringType.getAllSecondaryColours()
														:coveringType.getNaturalColoursSecondary());
			Collections.sort(availableSecondaryColours, (c1, c2)->c2.isMetallic()?(c1.isMetallic()?0:-1):(c1.isMetallic()?1:0));
			for (Colour c : availableSecondaryColours) {
				if(activeCovering.getPattern()==CoveringPattern.NONE
						|| activeCovering.getPattern()==CoveringPattern.EYE_IRISES
						|| activeCovering.getPattern()==CoveringPattern.EYE_PUPILS
						|| activeCovering.getPattern()==CoveringPattern.EYE_SCLERA
						|| !withSecondary) {
					contentSB.append("<div class='normal-button disabled' style='width:auto; margin-right:4px;'>"
						+ "<div class='phone-item-colour' style='background-color:" + c.getShades()[0] + ";'></div>"
					+ "</div>");
					
				} else {
					contentSB.append("<div class='normal-button"+(activeCovering.getSecondaryColour()==c?" selected":"")+"' id='"+coveringType+"_SECONDARY_"+c+"'"
											+ " style='width:auto; margin-right:4px;"+(activeCovering.getSecondaryColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
											+ (c.isMetallic()
													?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:(c.isRainbow()
														?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg,"
																+ " #c4e17f 0px, #c4e17f "+rainbowIncrement+"px,"
																+ "#f7fdca "+rainbowIncrement+"px, #f7fdca "+2*rainbowIncrement+"px,"
																+ "#fad071 "+2*rainbowIncrement+"px, #fad071 "+3*rainbowIncrement+"px,"
																+ "#f0766b "+3*rainbowIncrement+"px, #f0766b "+4*rainbowIncrement+"px,"
																+ "#db9dbe "+4*rainbowIncrement+"px, #db9dbe "+5*rainbowIncrement+"px,"
																+ "#c49cdf "+5*rainbowIncrement+"px, #c49cdf "+6*rainbowIncrement+"px,"
																+ "#6599e2 "+6*rainbowIncrement+"px, #6599e2 "+7*rainbowIncrement+"px,"
																+ "#61c2e4 "+7*rainbowIncrement+"px, #61c2e4 "+8*rainbowIncrement+"px);"
														:"<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"))
												+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")
											+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("<br/>");
			if(activeCovering.getSecondaryColour() == Colour.COVERING_NONE || !withGlow || activeCovering.getPattern()==CoveringPattern.NONE || !withSecondary) { // Disable glow:
				
			} else {
				if(activeCovering.isSecondaryGlowing()) {
					contentSB.append(
							"<div class='normal-button active' id='"+coveringType+"_SECONDARY_GLOW_OFF' style='width:50%; margin:2% auto; padding:0; text-align:center;'>"
								+ "[style.boldArcane(Glow)]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+coveringType+"_SECONDARY_GLOW_ON' class='normal-button' style='width:50%; margin:2% auto; padding:0; text-align:center;'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
									?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glow</span>"
									:"[style.colourDisabled(Glowing)]")
							+ "</div>");
				}
			}
			if(activeCovering.getPattern()==CoveringPattern.NONE
					|| activeCovering.getPattern()==CoveringPattern.EYE_IRISES
					|| activeCovering.getPattern()==CoveringPattern.EYE_PUPILS
					|| activeCovering.getPattern()==CoveringPattern.EYE_SCLERA
					|| !withSecondary) {
				contentSB.append("<p>"
						+ "[style.boldDisabled(Disabled)]"
					+ "</p>");
				
			} else {
				contentSB.append("<p>"
						+ "<b style='color:"+activeCovering.getSecondaryColour().toWebHexString()+";"
								+(activeCovering.isSecondaryGlowing()?"text-shadow: 0px 0px 4px "+activeCovering.getSecondaryColour().getShades()[4]+";":"")+"'>"
							+Util.capitaliseSentence(activeCovering.getSecondaryColour().getName())
						+"</b>"
					+ "</p>");
			}
			contentSB.append("</div>");
			
			if(activeCovering.getType().getNaturalModifiers().size() + activeCovering.getType().getExtraModifiers().size()>1) {
				contentSB.append("<div class='container-full-width'>");
				for(CoveringModifier mod : activeCovering.getType().getNaturalModifiers()) {
					if (activeCovering.getModifier() == mod) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</b>"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='"+coveringType+"_MODIFIER_"+mod+"' class='cosmetics-button'>"
										+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
											? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
											: "[style.colourDisabled(" + Util.capitaliseSentence(mod.getName()) + ")]")
								+ "</div>");
					}
				}
				for(CoveringModifier mod : activeCovering.getType().getExtraModifiers()) {
					if (activeCovering.getModifier() == mod) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</b>"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='"+coveringType+"_MODIFIER_"+mod+"' class='cosmetics-button'>"
										+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
											? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
											: "[style.colourDisabled(" + Util.capitaliseSentence(mod.getName()) + ")]")
								+ "</div>");
					}
				}
				contentSB.append("</div>");
			}
		contentSB.append("</div>");
		
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivPiercings(PiercingType type, String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();
		
		boolean isPierced = false, canPierce = true;
		
		switch(type) {
			case EAR:
				isPierced = BodyChanging.getTarget().isPiercedEar();
				break;
			case LIP:
				isPierced = BodyChanging.getTarget().isPiercedLip();
				break;
			case NAVEL:
				isPierced = BodyChanging.getTarget().isPiercedNavel();
				break;
			case NIPPLE:
				isPierced = BodyChanging.getTarget().isPiercedNipple();
				break;
			case NOSE:
				isPierced = BodyChanging.getTarget().isPiercedNose();
				break;
			case PENIS:
				canPierce = BodyChanging.getTarget().hasPenis();
				isPierced = BodyChanging.getTarget().isPiercedPenis();
				break;
			case TONGUE:
				isPierced = BodyChanging.getTarget().isPiercedTongue();
				break;
			case VAGINA:
				canPierce = BodyChanging.getTarget().hasVagina();
				isPierced = BodyChanging.getTarget().isPiercedVagina();
				break;
		}
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+(canPierce
								? title
									+(noCost
											?""
													:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(type)
														? UtilText.formatAsMoney(SuccubisSecrets.getPiercingCost(type), "b")
														: UtilText.formatAsMoney(SuccubisSecrets.getPiercingCost(type), "b", Colour.GENERIC_BAD)))
								:"[style.colourDisabled("+title + ")] " + UtilText.formatAsMoneyUncoloured(SuccubisSecrets.getPiercingCost(type), "b"))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		
		
		if(isPierced) {
			contentSB.append(
					"<div id='"+type+"_PIERCE_REMOVE' class='cosmetics-button'>"
						+ "[style.colourDisabled(Unpierced)]"
					+ "</div>");
			
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldArcane(Pierced)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "Unpierced"
					+ "</div>");
			
			if(canPierce) {
				contentSB.append(
						"<div id='"+type+"_PIERCE' class='cosmetics-button'>"
							+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(type) || noCost
								?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Pierced</span>"
								:"[style.colourDisabled(Pierced)]")
						+ "</div>");
			} else {
				contentSB.append(
						"<div class='cosmetics-button disabled'>"
							+ "[style.colourDisabled(Pierced)]"
						+ "</div>");
			}
		}

		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}

	public static String getKatesDivTattoos() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>"
				+ "<h5 style='width:100%; text-align:center;'>Main Areas</h5>");
		
		for(InventorySlot invSlot : RenderingEngine.mainInventorySlots) {
			contentSB.append(getTattooDiv(invSlot));
		}
		
		contentSB.append("</div>");
		
		contentSB.append("<div class='container-full-width'>"
				+ "<h5 style='width:100%; text-align:center;'>Secondary Areas</h5>");
		
		for(InventorySlot invSlot : RenderingEngine.secondaryInventorySlots) {
			contentSB.append(getTattooDiv(invSlot));
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getTattooDiv(InventorySlot invSlot) {
		Tattoo tattooInSlot = BodyChanging.getTarget().getTattooInSlot(invSlot);
		boolean disabled = false;
		
		switch(invSlot) {
			case HORNS:
				if(BodyChanging.getTarget().getHornType()==HornType.NONE) {
					disabled = true;
				}
				break;
			case PENIS:
				if(!BodyChanging.getTarget().hasPenisIgnoreDildo()) {
					disabled = true;
				}
				break;
			case TAIL:
				if(BodyChanging.getTarget().getTailType()==TailType.NONE) {
					disabled = true;
				}
				break;
			case VAGINA:
				if(!BodyChanging.getTarget().hasVagina()) {
					disabled = true;
				}
				break;
			case WINGS:
				if(BodyChanging.getTarget().getWingType()==WingType.NONE) {
					disabled = true;
				}
				break;
			default:
				break;
		}
		
		return "<div class='container-half-width inner' style='width:23%;margin:1%;'>"
				+ "<div class='container-full-width inner' style='width:100%; margin:0; text-align:center;'>"+Util.capitaliseSentence(invSlot.getTattooSlotName())+"</div>"
				+(disabled
					?"<div class='inventory-item-slot disabled' style='width:48%;margin:0 1%'></div>"
					:"<div class='modifier-icon' style='width:48%;margin:0 1%'>"
						+ (tattooInSlot==null
							?"<div class='modifier-icon-content'></div>"
							:"<div class='modifier-icon-content "+tattooInSlot.getRarity().getName()+"'>"+tattooInSlot.getSVGImage(BodyChanging.getTarget())+"</div>")
						+ "<div class='overlay no-pointer' id='TATTOO_INFO_"+invSlot.toString()+"'></div>"
					+ "</div>")
				
				+ "<div class='container-half-width inner' style='width:48%;margin:1%;'>"
					+ "<div style='float:left; width:98%; margin:0 1%; padding:0;'>"
						+ "<div class='normal-button"+(disabled?" disabled":"")+"' "+(!disabled?"id='TATTOO_ADD_REMOVE_"+invSlot.toString()+"'":"")+" style='width:100%;'>"
							+(tattooInSlot==null
								?"Add"
								:(SuccubisSecrets.invSlotTattooToRemove==invSlot || !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?"[style.colourBad(Remove)]":"Remove"))
						+"</div>"
					+ "</div>"
					+ (Main.game.isInNewWorld()
						?"<div style='float:left; width:98%; margin:0 1%; padding:0;'>"
								+ "<div class='normal-button"+(disabled || tattooInSlot==null?" disabled":"")+"' "+(!disabled?"id='TATTOO_ENCHANT_"+invSlot.toString()+"'":"")+" style='width:100%;'>Enchant</div>"
							+ "</div>"
						:"")
				+ "</div>"
			+ "</div>";
	}
	
	public static InventorySlot tattooInventorySlot = null;
	public static Tattoo tattoo = null;
	
	public static void resetTattooVariables(InventorySlot slot) {
		tattooInventorySlot = slot;

		tattoo = new Tattoo(
				TattooType.TRIBAL,
				Colour.CLOTHING_GREY,
				null,
				null,
				false,
				new TattooWriting(
						"",
						Colour.BASE_GREY,
						false),
				new TattooCounter(
						TattooCounterType.NONE,
						TattooCountType.NUMBERS,
						Colour.BASE_GREY,
						false));
	}
	
	public static void resetTattooColours() {
		if(!tattoo.getType().getAvailablePrimaryColours().contains(tattoo.getPrimaryColour())) {
			tattoo.setPrimaryColour(tattoo.getType().getAvailablePrimaryColours().get(0));
		}
		
		if(!tattoo.getType().getAvailableSecondaryColours().contains(tattoo.getSecondaryColour())) {
			if(tattoo.getType().getAvailableSecondaryColours().isEmpty()) {
				tattoo.setSecondaryColour(null);
			} else {
				tattoo.setSecondaryColour(tattoo.getType().getAvailableSecondaryColours().get(0));
			}
		}

		if(!tattoo.getType().getAvailableTertiaryColours().contains(tattoo.getTertiaryColour())) {
			if(tattoo.getType().getAvailableTertiaryColours().isEmpty()) {
				tattoo.setTertiaryColour(null);
			} else {
				tattoo.setTertiaryColour(tattoo.getType().getAvailableTertiaryColours().get(0));
			}
		}
		
		tattoo.setGlowing(false);
	}
	
	public static String getKatesDivTattoosAdd() {
		contentSB.setLength(0);
		
		// Type:
	
		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<div class='container-full-width' style='width:75%; margin:0; position:relative; text-align:center;'>");
				contentSB.append("<h5 style='width:100%; text-align:center;'>Select Type</h5>");
		
				for(AbstractTattooType type : TattooType.getAllTattooTypes()) {
					if(type.getSlotAvailability().contains(tattooInventorySlot)) {
						contentSB.append("<div style='width:18%; margin:1%; padding:0; display:inline-block;'>"
											+ "<div class='normal-button"+(tattoo.getType()==type?" selected":"")+"' id='TATTOO_TYPE_"+type.getId()+"'"
													+ " style='width:100%; margin:0; color:"+(tattoo.getType()==type?Colour.GENERIC_GOOD:Colour.TEXT_HALF_GREY).toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</div>"
										+ "</div>");
						
					} else {
						contentSB.append("<div style='width:18%; margin:1%; padding:0; display:inline-block;'>"
								+ "<div class='normal-button disabled' id='TATTOO_TYPE_"+type.getId()+"'"
										+ " style='width:100%; margin:0;'>"+Util.capitaliseSentence(type.getName())+"</div>"
							+ "</div>");
					}
				}
				contentSB.append("</div>"
						+ "<div class='container-full-width' style='width:25%; margin:0;'>");
				
				contentSB.append("<div class='modifier-icon' style='float:left; width:100%; margin:0; text-align:center;'>"
									+ "<div class='modifier-icon-content'>"+tattoo.getSVGImage(BodyChanging.getTarget())+"</div>"
									+ "<div class='overlay no-pointer' id='NEW_TATTOO_INFO'></div>"
								+ "</div>");
			
			contentSB.append("</div>");
		contentSB.append("</div>");
	
		// Colours:

		contentSB.append("<div class='container-full-width'>"
				+ "<h5 style='width:100%; text-align:center;'>Select Colours</h5>");
			
			// Primary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
				for (Colour c : tattoo.getType().getAvailablePrimaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getPrimaryColour()==c?" selected":"")+"' id='TATTOO_COLOUR_PRIMARY_"+c+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getPrimaryColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			contentSB.append("</div>");

			// Secondary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
			if(tattoo.getType().getAvailableSecondaryColours().isEmpty()) {
				contentSB.append(
						"<p style='text-align:center;'>[style.italicsDisabled(No secondary colours available...)]</p>");
				
			} else {
				for (Colour c : tattoo.getType().getAvailableSecondaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getSecondaryColour()==c?" selected":"")+"' id='TATTOO_COLOUR_SECONDARY_"+c+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getSecondaryColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("</div>");

			// Tertiary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
			if(tattoo.getType().getAvailableTertiaryColours().isEmpty()) {
				contentSB.append(
						"<p style='text-align:center;'>[style.italicsDisabled(No tertiary colours available...)]</p>");
				
			} else {
				for (Colour c : tattoo.getType().getAvailableTertiaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getTertiaryColour()==c?" selected":"")+"' id='TATTOO_COLOUR_TERTIARY_"+c+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getTertiaryColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("</div>");
			
			if(Main.game.isInNewWorld()) {
				contentSB.append("<div class='container-full-width'>");
					if(tattoo.getType().equals(TattooType.NONE)) {
						contentSB.append(
								"<div class='normal-button disabled' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "Glow"
								+ "</div>");
						
					} else if(tattoo.isGlowing()) {
						contentSB.append(
								"<div class='normal-button active' id='TATTOO_GLOW' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "[style.boldArcane(Glow)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_GLOW' class='normal-button' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glow</span>"
								+ "</div>");
					}
				contentSB.append("</div>");
			}

		contentSB.append("</div>");
	
		// Writing:

		contentSB.append("<div class='container-full-width'>"
				+ "<h5 style='width:100%; text-align:center;'>Select Writing</h5>");
		
			contentSB.append("<div class='container-full-width' style='width:66.6%; margin:0; position:relative; text-align:center;'>"
					+"<form style='padding:0; margin:0 0 4px 0; text-align:center;'>"
						+ "<input type='text' id='tattoo_name' value='" +UtilText.parseForHTMLDisplay(tattoo.getWriting().getText())+"' style='padding:0;margin:0;width:80%;'>"// "+(tattoo.getWriting().getStyle()==TattooWritingStyle.NONE?"disabled":"")+">"
					+ "</form>"
					+ "<p style='width:100%; text-align:center;'>Writing Style</p>");
			for(TattooWritingStyle style : TattooWritingStyle.values()) {
				contentSB.append("<div style='width:18%; margin:0 1%; padding:0; display:inline-block;'>"
									+ "<div class='normal-button"+(tattoo.getWriting().getStyles().contains(style)?" selected":"")+"' id='TATTOO_WRITING_STYLE_"+style.toString()+"'"
											+ " style='width:100%; margin:0; color:"+(tattoo.getWriting().getStyles().contains(style)?Colour.GENERIC_GOOD:Colour.TEXT_HALF_GREY).toWebHexString()+";'>"+Util.capitaliseSentence(style.getName())+"</div>"
								+ "</div>");
			}
			contentSB.append("<div class='container-full-width'>"
					+ "Output: "+tattoo.getFormattedWritingOutput()
					+ "</div>");
			contentSB.append("</div>");
			
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
				for (Colour c : TattooWriting.getAvailableColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getWriting().getColour()==c?" selected":"")+"' id='TATTOO_WRITING_COLOUR_"+c+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getWriting().getColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
				if(Main.game.isInNewWorld()) {
					contentSB.append("<br/>");
					if(tattoo.getWriting().isGlow()) {
						contentSB.append(
								"<div class='normal-button selected' id='TATTOO_WRITING_GLOW' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "[style.boldArcane(Glow)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_WRITING_GLOW' class='normal-button' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glow</span>"
								+ "</div>");
					}
				}
			contentSB.append("</div>");
		contentSB.append("</div>");

		// Counter:
		if(Main.game.isInNewWorld()) {
			contentSB.append("<div class='container-full-width'>"
					+ "<h5 style='width:100%; text-align:center;'>Select Counter</h5>");
			
				contentSB.append("<div class='container-full-width' style='width:66.6%; margin:0;'>");
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>Counter Type</p>");
						for(TattooCounterType counterType : TattooCounterType.values()) {
							contentSB.append("<div style='width:31%; margin:1%; padding:0; display:inline-block;'>"
												+ "<div class='normal-button"+(tattoo.getCounter().getType()==counterType?" selected":"")+"' id='TATTOO_COUNTER_TYPE_"+counterType.toString()+"'"
														+ " style='width:100%; margin:0; color:"+(tattoo.getCounter().getType()==counterType?Colour.GENERIC_GOOD:Colour.TEXT_HALF_GREY).toWebHexString()+";'>"
													+Util.capitaliseSentence(counterType.getName())+"</div>"
											+ "</div>");
						}
					contentSB.append("</div>");
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>Counter Style</p>");
						for(TattooCountType countType : TattooCountType.values()) {
							contentSB.append("<div style='width:31%; margin:1%; padding:0; display:inline-block;'>"
												+ "<div class='normal-button"+(tattoo.getCounter().getCountType()==countType?" selected":"")+"' id='TATTOO_COUNT_TYPE_"+countType.toString()+"'"
														+ " style='width:100%; margin:0; color:"+(tattoo.getCounter().getCountType()==countType?Colour.GENERIC_GOOD:Colour.TEXT_HALF_GREY).toWebHexString()+";'>"
													+Util.capitaliseSentence(countType.getName())+"</div>"
											+ "</div>");
						}
					contentSB.append("</div>");
				contentSB.append("</div>");
				
				contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
					for (Colour c : TattooCounter.getAvailableColours()) {
						contentSB.append("<div class='normal-button"+(tattoo.getCounter().getColour()==c?" selected":"")+"' id='TATTOO_COUNTER_COLOUR_"+c+"'"
												+ " style='width:auto; margin-right:4px;"+(tattoo.getCounter().getColour()==c?" background-color:"+Colour.BASE_GREEN.getShades()[4]+";":"")+"'>"
											+ "<div class='phone-item-colour' style='background-color:" + c.toWebHexString() + ";"+(c==Colour.COVERING_NONE?" color:"+Colour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
										+ "</div>");
					}
					contentSB.append("<br/>");
					if(tattoo.getCounter().isGlow()) {
						contentSB.append(
								"<div class='normal-button selected' id='TATTOO_COUNTER_GLOW' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "[style.boldArcane(Glow)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_COUNTER_GLOW' class='normal-button' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glow</span>"
								+ "</div>");
					}
					contentSB.append("<div class='container-full-width'>"
							+ "Output: "+tattoo.getFormattedCounterOutput(BodyChanging.getTarget())
							+ "</div>");
				contentSB.append("</div>");
				
			contentSB.append("</div>");
		}
		
		contentSB.append("<p id='hiddenPField' style='display:none;'></p>");
		
		return contentSB.toString();
	}
	
}
