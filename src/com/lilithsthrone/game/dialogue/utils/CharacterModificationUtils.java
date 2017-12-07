package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.Personality;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.7?
 * @version 0.1.88
 * @author Innoxia
 */
public class CharacterModificationUtils {

	private static StringBuilder contentSB = new StringBuilder();
	
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
					"<div id='CHAR_CREATION_GENDER_MALE' class='cosmetics-button'>"
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
					+ "<div id='CHAR_CREATION_GENDER_FEMALE' class='cosmetics-button'>"
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
						"<div id='CHAR_CREATION_FEM_ANDROGYNOUS' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.ANDROGYNOUS.getShades()[0]+";'>Androgynous</span>"
						+ "</div>");
			}
			if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourFeminine(Feminine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHAR_CREATION_FEM_FEMININE' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.FEMININE.getShades()[0]+";'>Feminine</span>"
						+ "</div>");
			}
			if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE_STRONG) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourFeminineStrong(Very Feminine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHAR_CREATION_FEM_FEMININE_STRONG' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.FEMININE_PLUS.getShades()[0]+";'>Very Feminine</span>"
						+ "</div>");
			}
			
		} else {
			if(Main.game.getPlayer().getFemininity()==Femininity.ANDROGYNOUS) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourAndrogynous(Androgynous)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHAR_CREATION_FEM_ANDROGYNOUS' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.ANDROGYNOUS.getShades()[0]+";'>Androgynous</span>"
						+ "</div>");
			}
			if(Main.game.getPlayer().getFemininity()==Femininity.MASCULINE) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourMasculine(Masculine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHAR_CREATION_FEM_MASCULINE' class='cosmetics-button'>"
							+ "<span style='color:"+Colour.MASCULINE.getShades()[0]+";'>Masculine</span>"
						+ "</div>");
			}
			if(Main.game.getPlayer().getFemininity()==Femininity.MASCULINE_STRONG) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourMasculineStrong(Very Masculine)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div id='CHAR_CREATION_FEM_MASCULINE_STRONG' class='cosmetics-button'>"
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
				"<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Personality"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Your personality will have a minor influence on how your character speaks or reacts."
							+ " It will not lock out any options during the game, and is more for role-playing purposes."
						+ "</p>");
		
		for(Personality personality : Personality.values()) {
			if(Main.game.getPlayer().getPersonality() == personality) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+personality.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(personality.getName())+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PERSONALITY_"+personality+"' class='cosmetics-button'>"
							+ "<span style='color:"+personality.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(personality.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
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
			if(Main.game.getPlayer().getSexualOrientation() == orientation) {
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
	
	
	
	// Advanced:
	
	private static int numberOfChoices = 18;
	public static int[] heightChoices = new int[numberOfChoices];
	static {
		float height = 152;
		for(int i=0; i<numberOfChoices; i++) {
			heightChoices[i] = Math.round(height);
			height += 2.54f;
		}
	}
	
	public static String getHeightChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"Height"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Your height will influence dialogue descriptions and some events later on in the game."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(int i : heightChoices) {
			if(Main.game.getPlayer().getHeightValue() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Main.game.getPlayer().getHeight().getColour().toWebHexString()+";'>"+(Util.inchesToFeetAndInches(Util.conversionCentimetresToInches(i)))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HEIGHT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+Height.getHeightFromInt(i).getColour().getShades()[0]+";'>"+(Util.inchesToFeetAndInches(Util.conversionCentimetresToInches(i)))+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBodySizeChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Body Size"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Your body size is a measure of how large you are."
						+ "</p>");
		
		for(BodySize bs : BodySize.values()) {
			if(Main.game.getPlayer().getBodySize() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Main.game.getPlayer().getBodySize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(bs.getName(false))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BODY_SIZE_"+bs+"' class='cosmetics-button'>"
							+ "<span style='color:"+bs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(bs.getName(false))+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getMuscleChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='cosmetics-inner-container'>"
						+ "<h5 style='text-align:center;'>"
							+"Muscle Definition"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Your muscle definition is solely for aesthetics."
						+ "</p>");
		
		for(Muscle muscle : Muscle.values()) {
			if(Main.game.getPlayer().getMuscle() == muscle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Main.game.getPlayer().getMuscle().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</b>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='MUSCLE_"+muscle+"' class='cosmetics-button'>"
							+ "<span style='color:"+muscle.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
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
			if(Main.game.getPlayer().getLipSize() == ls) {
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
		
		if(Main.game.getPlayer().hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
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
		if(Main.game.getPlayer().hasPenis()) {
			return new CupSize[] {CupSize.FLAT, CupSize.TRAINING};
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
			if(Main.game.getPlayer().getBreastSize() == cs) {
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
			if(Main.game.getPlayer().getBreastShape() == bs) {
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
		if(Main.game.getPlayer().hasPenis()) {
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
			if(Main.game.getPlayer().getNippleSize() == ns) {
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
		if(Main.game.getPlayer().hasPenis()) {
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
			if(Main.game.getPlayer().getAreolaeSize() == as) {
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
		
		if(Main.game.getPlayer().hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
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
		if(Main.game.getPlayer().hasPenis()) {
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
			if(Main.game.getPlayer().getBreastRawLactationValue() == i) {
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
		if(Main.game.getPlayer().hasPenis()) {
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
			if(Main.game.getPlayer().getAssSize() == as) {
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
		if(Main.game.getPlayer().hasPenis()) {
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
			if(Main.game.getPlayer().getHipSize() == hs) {
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
		
		if(Main.game.getPlayer().isAssBleached()) {
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
			if(Main.game.getPlayer().getPenisRawSizeValue() == size) {
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
			if(Main.game.getPlayer().getTesticleSize() == size) {
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
							+"Cum Production"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "Choose how much cum you produce at each orgasm."
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		CumProduction[] sizesAvailable = new CumProduction[] {CumProduction.ZERO_NONE, CumProduction.ONE_TRICKLE, CumProduction.TWO_SMALL_AMOUNT, CumProduction.THREE_AVERAGE, CumProduction.FOUR_LARGE};
		
		for(CumProduction value : sizesAvailable) {
			if(Main.game.getPlayer().getPenisCumProduction() == value) {
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
			if(Main.game.getPlayer().getVaginaCapacity() == value) {
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
			if(Main.game.getPlayer().getVaginaClitorisSize() == size) {
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
			if(Main.game.getPlayer().getVaginaLabiaSize() == size) {
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
	
	public static String getKatesDivHairLengths(String title, String description) {
		contentSB.setLength(0);
		
		boolean noCost = !Main.game.isInNewWorld();
		
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
			if (Main.game.getPlayer().getHairLength() == hairLength) {
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
	
	public static String getKatesDivHairStyles(String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();
		
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
			if (Main.game.getPlayer().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName()) + "</b>"
						+ "</div>");
			} else {
				if(Main.game.getPlayer().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST | noCost
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
		return getKatesDivGenericBodyHair(title, description, Main.game.getPlayer().getAssHair(), "ASS_HAIR_", false);
	}
	
	public static String getKatesDivUnderarmHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, Main.game.getPlayer().getUnderarmHair(), "UNDERARM_HAIR_", false);
	}
	
	public static String getKatesDivFacialHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, Main.game.getPlayer().getFacialHair(), "FACIAL_HAIR_", Main.game.getPlayer().isFeminine());
	}
	
	public static String getKatesDivPubicHair(String title, String description) {
		return getKatesDivGenericBodyHair(title, description, Main.game.getPlayer().getPubicHair(), "PUBIC_HAIR_", false);
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
		
		if(Main.game.getPlayer().isAssBleached()) {
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
	
	public static String getKatesDivNaturalCoverings(BodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow) {
		contentSB.setLength(0);
		
		boolean noCost = !Main.game.isInNewWorld();
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
							+(noCost
									?""
									:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
										? UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b")
										: UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b", Colour.GENERIC_BAD)))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		for (Colour cs : coveringType.getNaturalColoursPrimary()) {
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				if(Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing()) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
							+ "</div>");
				}
			} else {
				contentSB.append(
						"<div id='"+coveringType+"_PRIMARY_"+cs+"' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
									? "<span style='color:"+cs.getShades()[0]+";'>" + Util.capitaliseSentence(cs.getName()) + "</span>"
									: "[style.colourDisabled(" + Util.capitaliseSentence(cs.getName()) + ")]")
						+ "</div>");
			}
		}
		
		if(withGlow) {
			contentSB.append(
					"</div>"
					+ "<div class='cosmetics-inner-container right'>");
			
			if(Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == Colour.COVERING_NONE) { // Disable glow:
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "No glow"
							+ "</div>"
							+ "<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(Glowing)]"
							+ "</div>");
				
			} else {
				if(Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing()) {
					contentSB.append(
							"<div id='"+coveringType+"_PRIMARY_GLOW_OFF' class='cosmetics-button'>"
								+ "[style.colourDisabled(No glow)]"
							+ "</div>"
							+ "<div class='cosmetics-button active'>"
								+ "[style.boldArcane(Glowing)]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "No glow"
							+ "</div>"
							+ "<div id='"+coveringType+"_PRIMARY_GLOW_ON' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
									?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glowing</span>"
									:"[style.colourDisabled(Glowing)]")
							+ "</div>");
				}
		
			}
		}
		
		if(withSecondary
				&& Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() != Colour.COVERING_NONE
				&& !coveringType.getAllPatterns().isEmpty()
				&& (coveringType.getAllPatterns().size()==1
					?!coveringType.getAllPatterns().contains(CoveringPattern.NONE)&&!coveringType.getAllPatterns().contains(CoveringPattern.EYE_IRISES)&&!coveringType.getAllPatterns().contains(CoveringPattern.EYE_PUPILS)
					:true)
				&& !coveringType.getAllSecondaryColours().isEmpty()) {
			contentSB.append(
					"</div>"
					+ "<div class='cosmetics-inner-container right'>");
			
			for (CoveringPattern pattern : coveringType.getNaturalPatterns()) {
				if (Main.game.getPlayer().getCovering(coveringType).getPattern() == pattern) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getName()) + "</b>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+coveringType+"_PATTERN_"+pattern+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
										? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
										: "[style.colourDisabled(" + Util.capitaliseSentence(pattern.getName()) + ")]")
							+ "</div>");
				}
			}
			
			contentSB.append(
					"</div>"
					+ "<div class='cosmetics-inner-container right'>");
			
			for (Colour cs : coveringType.getNaturalColoursSecondary()) {
				
				if(Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.NONE
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_IRISES
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_PUPILS) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(cs.getName()) + ")]"
							+ "</div>");
					
					
				} else {
					if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs) {
						if(Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()) {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
									+ "</div>");
						} else {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
									+ "</div>");
						}
					} else {
						contentSB.append(
								"<div id='"+coveringType+"_SECONDARY_"+cs+"' class='cosmetics-button'>"
										+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
											? "<span style='color:"+cs.getShades()[0]+";'>" + Util.capitaliseSentence(cs.getName()) + "</span>"
											: "[style.colourDisabled(" + Util.capitaliseSentence(cs.getName()) + ")]")
								+ "</div>");
					}
				}
			}
			
			if(withGlow) {
				contentSB.append(
						"</div>"
						+ "<div class='cosmetics-inner-container right'>");
				
				if(Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == Colour.COVERING_NONE
						|| Main.game.getPlayer().getCovering(coveringType).getPattern() == CoveringPattern.NONE
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_IRISES
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_PUPILS) { // Disable glow:
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
									+ "[style.colourDisabled(No Glow)]"
								+ "</div>"
								+ "<div class='cosmetics-button disabled'>"
									+ "[style.colourDisabled(Glowing)]"
								+ "</div>");
					
				} else {
					if(Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()) {
						contentSB.append(
								"<div id='"+coveringType+"_SECONDARY_GLOW_OFF' class='cosmetics-button'>"
									+ "[style.colourDisabled(No glow)]"
								+ "</div>"
								+ "<div class='cosmetics-button active'>"
									+ "[style.boldArcane(Glowing)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "No glow"
								+ "</div>"
								+ "<div id='"+coveringType+"_SECONDARY_GLOW_ON' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
										?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glowing</span>"
										:"[style.colourDisabled(Glowing)]")
								+ "</div>");
					}
			
				}
			}
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivCoverings(BodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow) {
		contentSB.setLength(0);
		
		boolean noCost = !Main.game.isInNewWorld();
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
							+(noCost
									?""
									:" "+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
										? UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b")
										: UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "b", Colour.GENERIC_BAD)))
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		for (Colour cs : coveringType.getAllPrimaryColours()) {
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				if(Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing()) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
							+ "</div>");
				}
			} else {
				contentSB.append(
						"<div id='"+coveringType+"_PRIMARY_"+cs+"' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
									? "<span style='color:"+cs.getShades()[0]+";'>" + Util.capitaliseSentence(cs.getName()) + "</span>"
									: "[style.colourDisabled(" + Util.capitaliseSentence(cs.getName()) + ")]")
						+ "</div>");
			}
		}
		
		if(withGlow) {
			contentSB.append(
					"</div>"
					+ "<div class='cosmetics-inner-container right'>");
			
			if(Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == Colour.COVERING_NONE) { // Disable glow:
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "No glow"
							+ "</div>"
							+ "<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(Glowing)]"
							+ "</div>");
				
			} else {
				if(Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing()) {
					contentSB.append(
							"<div id='"+coveringType+"_PRIMARY_GLOW_OFF' class='cosmetics-button'>"
								+ "[style.colourDisabled(No glow)]"
							+ "</div>"
							+ "<div class='cosmetics-button active'>"
								+ "[style.boldArcane(Glowing)]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "No glow"
							+ "</div>"
							+ "<div id='"+coveringType+"_PRIMARY_GLOW_ON' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
									?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glowing</span>"
									:"[style.colourDisabled(Glowing)]")
							+ "</div>");
				}
		
			}
		}
		
		if(withSecondary
				&& Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() != Colour.COVERING_NONE
				&& !coveringType.getAllPatterns().isEmpty()
				&& (coveringType.getAllPatterns().size()==1
					?!coveringType.getAllPatterns().contains(CoveringPattern.NONE)&&!coveringType.getAllPatterns().contains(CoveringPattern.EYE_IRISES)&&!coveringType.getAllPatterns().contains(CoveringPattern.EYE_PUPILS)
					:true)
				&& !coveringType.getAllSecondaryColours().isEmpty()) {
			contentSB.append(
					"</div>"
					+ "<div class='cosmetics-inner-container right'>");
			
			for (CoveringPattern pattern : coveringType.getAllPatterns()) {
				if (Main.game.getPlayer().getCovering(coveringType).getPattern() == pattern) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getName()) + "</b>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+coveringType+"_PATTERN_"+pattern+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
										? "<span style='color:"+Colour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
										: "[style.colourDisabled(" + Util.capitaliseSentence(pattern.getName()) + ")]")
							+ "</div>");
				}
			}
			
			contentSB.append(
					"</div>"
					+ "<div class='cosmetics-inner-container right'>");
			
			for (Colour cs : coveringType.getAllSecondaryColours()) {
				
				if(Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.NONE
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_IRISES
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_PUPILS) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(cs.getName()) + ")]"
							+ "</div>");
					
					
				} else {
					if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs) {
						if(Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()) {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
									+ "</div>");
						} else {
							contentSB.append(
									"<div class='cosmetics-button active'>"
										+ "<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>"
									+ "</div>");
						}
					} else {
						contentSB.append(
								"<div id='"+coveringType+"_SECONDARY_"+cs+"' class='cosmetics-button'>"
										+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
											? "<span style='color:"+cs.getShades()[0]+";'>" + Util.capitaliseSentence(cs.getName()) + "</span>"
											: "[style.colourDisabled(" + Util.capitaliseSentence(cs.getName()) + ")]")
								+ "</div>");
					}
				}
			}
			
			if(withGlow) {
				contentSB.append(
						"</div>"
						+ "<div class='cosmetics-inner-container right'>");
				
				if(Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == Colour.COVERING_NONE
						|| Main.game.getPlayer().getCovering(coveringType).getPattern() == CoveringPattern.NONE
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_IRISES
						|| Main.game.getPlayer().getCovering(coveringType).getPattern()==CoveringPattern.EYE_PUPILS) { // Disable glow:
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
									+ "[style.colourDisabled(No Glow)]"
								+ "</div>"
								+ "<div class='cosmetics-button disabled'>"
									+ "[style.colourDisabled(Glowing)]"
								+ "</div>");
					
				} else {
					if(Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()) {
						contentSB.append(
								"<div id='"+coveringType+"_SECONDARY_GLOW_OFF' class='cosmetics-button'>"
									+ "[style.colourDisabled(No glow)]"
								+ "</div>"
								+ "<div class='cosmetics-button active'>"
									+ "[style.boldArcane(Glowing)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "No glow"
								+ "</div>"
								+ "<div id='"+coveringType+"_SECONDARY_GLOW_ON' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || noCost
										?"<span style='color:"+Colour.GENERIC_ARCANE.getShades()[0]+";'>Glowing</span>"
										:"[style.colourDisabled(Glowing)]")
								+ "</div>");
					}
			
				}
			}
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivPiercings(PiercingType type, String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();
		
		boolean isPierced = false, canPierce = true;
		
		switch(type) {
			case EAR:
				isPierced = Main.game.getPlayer().isPiercedEar();
				break;
			case LIP:
				isPierced = Main.game.getPlayer().isPiercedLip();
				break;
			case NAVEL:
				isPierced = Main.game.getPlayer().isPiercedNavel();
				break;
			case NIPPLE:
				isPierced = Main.game.getPlayer().isPiercedNipple();
				break;
			case NOSE:
				isPierced = Main.game.getPlayer().isPiercedNose();
				break;
			case PENIS:
				canPierce = Main.game.getPlayer().hasPenis();
				isPierced = Main.game.getPlayer().isPiercedPenis();
				break;
			case TONGUE:
				isPierced = Main.game.getPlayer().isPiercedTongue();
				break;
			case VAGINA:
				canPierce = Main.game.getPlayer().hasVagina();
				isPierced = Main.game.getPlayer().isPiercedVagina();
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
	
}
