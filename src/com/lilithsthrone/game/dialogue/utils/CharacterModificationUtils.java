package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.body.Covering;
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
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

public class CharacterModificationUtils {

	private static StringBuilder contentSB = new StringBuilder();
	private static List<String> stringsList = new ArrayList<>();
	
	public static String stringsToSelection(List<String> strings) {
		contentSB = new StringBuilder();
		int i = 0;
		for(String s : strings) {
			
			contentSB.append(s);
			
			if(i==3 && strings.size()>6) {
				contentSB.append("</br>");
				
			} else if (i + 1 != strings.size()) {
				contentSB.append(" | ");
			}
			
			i++;
		}
		
		return contentSB.toString();
	}
	
	
	
	// ---------------------- Hair: ---------------------- //
	
	public static String getHairLengthOption() {
		stringsList.clear();
		for (HairLength value : HairLength.values()) {
			if (Main.game.getPlayer().getHairLength() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementHairLength() {
		switch(Main.game.getPlayer().getHairLength()) {
			case ZERO_BALD:
				Main.game.getPlayer().setHairLength(HairLength.ONE_VERY_SHORT.getMedianValue());
				break;
			case ONE_VERY_SHORT:
				Main.game.getPlayer().setHairLength(HairLength.TWO_SHORT.getMedianValue());
				break;
			case TWO_SHORT:
				Main.game.getPlayer().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
				break;
			case THREE_SHOULDER_LENGTH:
				Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
				break;
			case FOUR_MID_BACK:
				Main.game.getPlayer().setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
				break;
			case FIVE_ABOVE_ASS:
				Main.game.getPlayer().setHairLength(HairLength.SIX_BELOW_ASS.getMedianValue());
				break;
			case SIX_BELOW_ASS:
				Main.game.getPlayer().setHairLength(HairLength.SEVEN_TO_FLOOR.getMedianValue());
				break;
			case SEVEN_TO_FLOOR:
				Main.game.getPlayer().setHairLength(HairLength.ZERO_BALD.getMedianValue());
				break;
		}
	}
	
	public static String getHairStyleOption() {
		stringsList.clear();
		for (HairStyle value : HairStyle.values()) {
			if (Main.game.getPlayer().getHairStyle() == value) {
				stringsList.add("[style.boldGood(" + Util.capitaliseSentence(value.getName()) + ")]");
			} else if (Main.game.getPlayer().getHairRawLengthValue() < value.getMinimumLengthRequired()) {
				stringsList.add("[style.colourBad(" + Util.capitaliseSentence(value.getName()) + ")]");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementHairStyle(HairStyle style) {
		switch(style) {
			case NONE:
				setHairStyleIfAvailable(HairStyle.LOOSE);
				break;
			case LOOSE:
				setHairStyleIfAvailable(HairStyle.CURLY);
				break;
			case CURLY:
				setHairStyleIfAvailable(HairStyle.STRAIGHT);
				break;
			case STRAIGHT:
				setHairStyleIfAvailable(HairStyle.AFRO);
				break;
			case AFRO:
				setHairStyleIfAvailable(HairStyle.SIDECUT);
				break;
			case SIDECUT:
				setHairStyleIfAvailable(HairStyle.MOHAWK);
				break;
			case MOHAWK:
				setHairStyleIfAvailable(HairStyle.WAVY);
				break;
			case WAVY:
				setHairStyleIfAvailable(HairStyle.PONYTAIL);
				break;
			case PONYTAIL:
				setHairStyleIfAvailable(HairStyle.TWIN_TAILS);
				break;
			case TWIN_TAILS:
				setHairStyleIfAvailable(HairStyle.BRAIDED);
				break;
			case BRAIDED:
				setHairStyleIfAvailable(HairStyle.NONE);
				break;
		}
	}
	private static void setHairStyleIfAvailable(HairStyle style) {
		if(Main.game.getPlayer().getHairRawLengthValue() >= style.getMinimumLengthRequired()) {
			Main.game.getPlayer().setHairStyle(style);
		} else {
			incrementHairStyle(style);
		}
	}
	
	
	
	// ---------------------- Body hair: ---------------------- //
	
	public static String getFacialHairOption() {
		stringsList.clear();
		for (BodyHair value : BodyHair.values()) {
			if (Main.game.getPlayer().getFacialHair() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else if (Main.game.getPlayer().isFeminine() && value != BodyHair.NONE) {
				stringsList.add("[style.colourBad(" + Util.capitaliseSentence(value.getName()) + ")]");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getPubicHairOption() {
		stringsList.clear();
		for (BodyHair value : BodyHair.values()) {
			if (Main.game.getPlayer().getPubicHair() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getUnderarmHairOption() {
		stringsList.clear();
		for (BodyHair value : BodyHair.values()) {
			if (Main.game.getPlayer().getUnderarmHair() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getAssHairOption() {
		stringsList.clear();
		for (BodyHair value : BodyHair.values()) {
			if (Main.game.getPlayer().getAssHair() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementFacialHair(BodyHair hair) {
		switch(hair) {
			case NONE:
				setFacialHairIfAvailable(BodyHair.STUBBLE);
				break;
			case STUBBLE:
				setFacialHairIfAvailable(BodyHair.MANICURED);
				break;
			case MANICURED:
				setFacialHairIfAvailable(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				setFacialHairIfAvailable(BodyHair.NATURAL);
				break;
			case NATURAL:
				setFacialHairIfAvailable(BodyHair.UNKEMPT);
				break;
			case UNKEMPT:
				setFacialHairIfAvailable(BodyHair.BUSHY);
				break;
			case BUSHY:
				setFacialHairIfAvailable(BodyHair.WILD);
				break;
			case WILD:
				setFacialHairIfAvailable(BodyHair.NONE);
				break;
		}
	}
	private static void setFacialHairIfAvailable(BodyHair hair) {
		if(Main.game.getPlayer().isFeminine() && hair != BodyHair.NONE) {
			Main.game.getPlayer().setFacialHair(BodyHair.NONE);
		} else {
			Main.game.getPlayer().setFacialHair(hair);
		}
	}
	public static void incrementPubicHair() {
		switch(Main.game.getPlayer().getPubicHair()) {
			case NONE:
				Main.game.getPlayer().setPubicHair(BodyHair.STUBBLE);
				break;
			case STUBBLE:
				Main.game.getPlayer().setPubicHair(BodyHair.MANICURED);
				break;
			case MANICURED:
				Main.game.getPlayer().setPubicHair(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				Main.game.getPlayer().setPubicHair(BodyHair.NATURAL);
				break;
			case NATURAL:
				Main.game.getPlayer().setPubicHair(BodyHair.UNKEMPT);
				break;
			case UNKEMPT:
				Main.game.getPlayer().setPubicHair(BodyHair.BUSHY);
				break;
			case BUSHY:
				Main.game.getPlayer().setPubicHair(BodyHair.WILD);
				break;
			case WILD:
				Main.game.getPlayer().setPubicHair(BodyHair.NONE);
				break;
		}
	}
	public static void incrementUnderarmHair() {
		switch(Main.game.getPlayer().getUnderarmHair()) {
			case NONE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.STUBBLE);
				break;
			case STUBBLE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.MANICURED);
				break;
			case MANICURED:
				Main.game.getPlayer().setUnderarmHair(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				Main.game.getPlayer().setUnderarmHair(BodyHair.NATURAL);
				break;
			case NATURAL:
				Main.game.getPlayer().setUnderarmHair(BodyHair.UNKEMPT);
				break;
			case UNKEMPT:
				Main.game.getPlayer().setUnderarmHair(BodyHair.BUSHY);
				break;
			case BUSHY:
				Main.game.getPlayer().setUnderarmHair(BodyHair.WILD);
				break;
			case WILD:
				Main.game.getPlayer().setUnderarmHair(BodyHair.NONE);
				break;
		}
	}
	public static void incrementAssHair() {
		switch(Main.game.getPlayer().getAssHair()) {
			case NONE:
				Main.game.getPlayer().setAssHair(BodyHair.STUBBLE);
				break;
			case STUBBLE:
				Main.game.getPlayer().setAssHair(BodyHair.MANICURED);
				break;
			case MANICURED:
				Main.game.getPlayer().setAssHair(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				Main.game.getPlayer().setAssHair(BodyHair.NATURAL);
				break;
			case NATURAL:
				Main.game.getPlayer().setAssHair(BodyHair.UNKEMPT);
				break;
			case UNKEMPT:
				Main.game.getPlayer().setAssHair(BodyHair.BUSHY);
				break;
			case BUSHY:
				Main.game.getPlayer().setAssHair(BodyHair.WILD);
				break;
			case WILD:
				Main.game.getPlayer().setAssHair(BodyHair.NONE);
				break;
		}
	}
	
	
	
	// ---------------------- Breasts: ---------------------- //

	public static String getBreastSizeOption() {
		stringsList.clear();
		for (CupSize value : CupSize.values()) {
			if(value.getMeasurement() <= CupSize.E.getMeasurement()) {
				if (Main.game.getPlayer().getBreastSize() == value) {
					stringsList.add("[style.boldGood(" + Util.capitaliseSentence(value.getCupSizeName()) + ")]");
				} else if (!Main.game.getPlayer().isFeminine() && value.getMeasurement() > CupSize.TRAINING.getMeasurement()) {
					stringsList.add("[style.colourBad(" + Util.capitaliseSentence(value.getCupSizeName()) + ")]");
				} else {
					stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getCupSizeName()) + "</span>");
				}
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getNippleSizeOption() {
		stringsList.clear();
		for (NippleSize value : NippleSize.values()) {
			if (Main.game.getPlayer().getNippleSize() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getAreolaeSizeOption() {
		stringsList.clear();
		for (AreolaeSize value : AreolaeSize.values()) {
			if (Main.game.getPlayer().getAreolaeSize() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getLactationOption() {
		stringsList.clear();
		for (Lactation value : Lactation.values()) {
			if(value.getMedianValue() < Lactation.THREE_DECENT_AMOUNT.getMinimumValue()) {
				if (Main.game.getPlayer().getBreastLactation() == value) {
					stringsList.add("[style.boldGood(" + value.getMedianValue() + "mL)]");
				} else if (!Main.game.getPlayer().isFeminine() && value != Lactation.ZERO_NONE) {
					stringsList.add("[style.colourBad(" + value.getMedianValue() + "mL)]");
				} else {
					stringsList.add("<span class='option-disabled'>" + value.getMedianValue() + "mL</span>");
				}
			}
		}
		return stringsToSelection(stringsList);
	}

	public static void incrementBreastSize() {
		switch(Main.game.getPlayer().getBreastSize()) {
			case FLAT:
				setBreastSizeIfAvailable(CupSize.TRAINING);
				break;
			case TRAINING:
				setBreastSizeIfAvailable(CupSize.AA);
				break;
			case AA:
				setBreastSizeIfAvailable(CupSize.A);
				break;
			case A:
				setBreastSizeIfAvailable(CupSize.B);
				break;
			case B:
				setBreastSizeIfAvailable(CupSize.C);
				break;
			case C:
				setBreastSizeIfAvailable(CupSize.D);
				break;
			case D:
				setBreastSizeIfAvailable(CupSize.DD);
				break;
			case DD:
				setBreastSizeIfAvailable(CupSize.E);
				break;
			case E:
				setBreastSizeIfAvailable(CupSize.FLAT);
				break;
			default:
				setBreastSizeIfAvailable(CupSize.FLAT);
				break;
		}
	}
	private static void setBreastSizeIfAvailable(CupSize cupSize) {
		if(!Main.game.getPlayer().isFeminine() && cupSize.getMeasurement()>CupSize.TRAINING.getMeasurement()) {
			Main.game.getPlayer().setBreastSize(CupSize.FLAT.getMeasurement());
		} else {
			Main.game.getPlayer().setBreastSize(cupSize.getMeasurement());
		}
	}

	public static void incrementNippleSize() {
		switch(Main.game.getPlayer().getNippleSize()) {
			case ZERO_TINY:
				Main.game.getPlayer().setNippleSize(NippleSize.ONE_SMALL.getValue());
				break;
			case ONE_SMALL:
				Main.game.getPlayer().setNippleSize(NippleSize.TWO_BIG.getValue());
				break;
			case TWO_BIG:
				Main.game.getPlayer().setNippleSize(NippleSize.THREE_LARGE.getValue());
				break;
			case THREE_LARGE:
				Main.game.getPlayer().setNippleSize(NippleSize.FOUR_MASSIVE.getValue());
				break;
			case FOUR_MASSIVE:
				Main.game.getPlayer().setNippleSize(NippleSize.ZERO_TINY.getValue());
				break;
		}
	}
	
	public static void incrementAreolaeSize() {
		switch(Main.game.getPlayer().getAreolaeSize()) {
			case ZERO_TINY:
				Main.game.getPlayer().setAreolaeSize(AreolaeSize.ONE_SMALL.getValue());
				break;
			case ONE_SMALL:
				Main.game.getPlayer().setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
				break;
			case TWO_BIG:
				Main.game.getPlayer().setAreolaeSize(AreolaeSize.THREE_LARGE.getValue());
				break;
			case THREE_LARGE:
				Main.game.getPlayer().setAreolaeSize(AreolaeSize.FOUR_MASSIVE.getValue());
				break;
			case FOUR_MASSIVE:
				Main.game.getPlayer().setAreolaeSize(AreolaeSize.ZERO_TINY.getValue());
				break;
		}
	}

	public static void incrementLactation() {
		switch(Main.game.getPlayer().getBreastLactation()) {
			case ZERO_NONE:
				if(Main.game.getPlayer().isFeminine()) {
					Main.game.getPlayer().setBreastLactation(Lactation.ONE_TRICKLE.getMedianValue());
				}
				break;
			case ONE_TRICKLE:
				Main.game.getPlayer().setBreastLactation(Lactation.TWO_SMALL_AMOUNT.getMedianValue());
				break;
			case TWO_SMALL_AMOUNT:
				Main.game.getPlayer().setBreastLactation(Lactation.ZERO_NONE.getMedianValue());
				break;
			default:
				break;
		}
	}
	
	public static String getNipplePuffyOption(boolean puffy) {
		stringsList.clear();
		if(!puffy) {
			stringsList.add("[style.boldDisabled(Regular)]");
		} else {
			stringsList.add("[style.colourDisabled(Regular)]");
		}	
		if(puffy) {
			stringsList.add("[style.boldGood(Puffy)]");
		} else {
			stringsList.add("[style.colourDisabled(Puffy)]");
		}
		return stringsToSelection(stringsList);
	}
	
	
	
	// ---------------------- Ass & Hips: ---------------------- //

	public static String getAssSizeOption() {
		stringsList.clear();
		for (AssSize value : AssSize.values()) {
			if (Main.game.getPlayer().getAssSize() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getHipSizeOption() {
		stringsList.clear();
		for (HipSize value : HipSize.values()) {
			if (Main.game.getPlayer().getHipSize() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getAnusBleachedOption() {
		stringsList.clear();
		if(!Main.game.getPlayer().isAssBleached()) {
			stringsList.add("[style.boldDisabled(Regular)]");
		} else {
			stringsList.add("[style.colourDisabled(Regular)]");
		}	
		if(Main.game.getPlayer().isAssBleached()) {
			stringsList.add("[style.boldGood(Bleached)]");
		} else {
			stringsList.add("[style.colourDisabled(Bleached)]");
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementAssSize() {
		switch(Main.game.getPlayer().getAssSize()) {
			case ZERO_FLAT:
				Main.game.getPlayer().setAssSize(AssSize.ONE_TINY.getValue());
				break;
			case ONE_TINY:
				Main.game.getPlayer().setAssSize(AssSize.TWO_SMALL.getValue());
				break;
			case TWO_SMALL:
				Main.game.getPlayer().setAssSize(AssSize.THREE_NORMAL.getValue());
				break;
			case THREE_NORMAL:
				Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE.getValue());
				break;
			case FOUR_LARGE:
				Main.game.getPlayer().setAssSize(AssSize.FIVE_HUGE.getValue());
				break;
			case FIVE_HUGE:
				Main.game.getPlayer().setAssSize(AssSize.SIX_MASSIVE.getValue());
				break;
			case SIX_MASSIVE:
				Main.game.getPlayer().setAssSize(AssSize.SEVEN_GIGANTIC.getValue());
				break;
			case SEVEN_GIGANTIC:
				Main.game.getPlayer().setAssSize(AssSize.ZERO_FLAT.getValue());
				break;
		}
	}
	public static void incrementHipSize() {
		switch(Main.game.getPlayer().getHipSize()) {
			case ZERO_NO_HIPS:
				Main.game.getPlayer().setHipSize(HipSize.ONE_VERY_NARROW.getValue());
				break;
			case ONE_VERY_NARROW:
				Main.game.getPlayer().setHipSize(HipSize.TWO_NARROW.getValue());
				break;
			case TWO_NARROW:
				Main.game.getPlayer().setHipSize(HipSize.THREE_GIRLY.getValue());
				break;
			case THREE_GIRLY:
				Main.game.getPlayer().setHipSize(HipSize.FOUR_WOMANLY.getValue());
				break;
			case FOUR_WOMANLY:
				Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE.getValue());
				break;
			case FIVE_VERY_WIDE:
				Main.game.getPlayer().setHipSize(HipSize.SIX_EXTREMELY_WIDE.getValue());
				break;
			case SIX_EXTREMELY_WIDE:
				Main.game.getPlayer().setHipSize(HipSize.SEVEN_ABSURDLY_WIDE.getValue());
				break;
			case SEVEN_ABSURDLY_WIDE:
				Main.game.getPlayer().setHipSize(HipSize.ZERO_NO_HIPS.getValue());
				break;
		}
	}
	
	
	
	// ---------------------- Penis: ---------------------- //
	
	public static String getPenisSizeOption() {
		stringsList.clear();
		for (PenisSize value : PenisSize.values()) {
			if(value.getMedianValue() < PenisSize.FOUR_HUGE.getMinimumValue() && value != PenisSize.NEGATIVE_UTILITY_VALUE) {
				if (Main.game.getPlayer().getPenisSize() == value) {
					stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
				} else {
					stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
				}
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getTesticleSizeOption() {
		stringsList.clear();
		for (TesticleSize value : TesticleSize.values()) {
			if(value.getValue() < TesticleSize.FOUR_HUGE.getValue()) {
				if (Main.game.getPlayer().getTesticleSize() == value) {
					stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
				} else {
					stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
				}
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getCumProductionOption() {
		stringsList.clear();
		for (CumProduction value : CumProduction.values()) {
			if(value.getMedianValue() < CumProduction.FIVE_HUGE.getMinimumValue()) {
				if (Main.game.getPlayer().getPenisCumProduction() == value) {
					stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + value.getMedianValue() + "mL</b>");
				} else {
					stringsList.add("<span class='option-disabled'>" + value.getMedianValue() + "mL</span>");
				}
			}
		}
		return stringsToSelection(stringsList);
	}

	public static void incrementPenisSize() {
		switch(Main.game.getPlayer().getPenisSize()) {
			case ZERO_MICROSCOPIC:
				Main.game.getPlayer().setPenisSize(PenisSize.ONE_TINY.getMedianValue());
				break;
			case ONE_TINY:
				Main.game.getPlayer().setPenisSize(PenisSize.TWO_AVERAGE.getMedianValue());
				break;
			case TWO_AVERAGE:
				Main.game.getPlayer().setPenisSize(PenisSize.THREE_LARGE.getMedianValue());
				break;
			case THREE_LARGE:
				Main.game.getPlayer().setPenisSize(PenisSize.ZERO_MICROSCOPIC.getMedianValue());
				break;
			default:
				Main.game.getPlayer().setPenisSize(PenisSize.ZERO_MICROSCOPIC.getMedianValue());
				break;
		}
	}
	public static void incrementTesticleSize() {
		switch(Main.game.getPlayer().getTesticleSize()) {
			case ZERO_VESTIGIAL:
				Main.game.getPlayer().setTesticleSize(TesticleSize.ONE_TINY.getValue());
				break;
			case ONE_TINY:
				Main.game.getPlayer().setTesticleSize(TesticleSize.TWO_AVERAGE.getValue());
				break;
			case TWO_AVERAGE:
				Main.game.getPlayer().setTesticleSize(TesticleSize.THREE_LARGE.getValue());
				break;
			case THREE_LARGE:
				Main.game.getPlayer().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
				break;
			default:
				Main.game.getPlayer().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
				break;
		}
	}
	public static void incrementCumProduction() {
		switch(Main.game.getPlayer().getPenisCumProduction()) {
			case ZERO_NONE:
				Main.game.getPlayer().setCumProduction(CumProduction.ONE_TRICKLE.getMedianValue());
				break;
			case ONE_TRICKLE:
				Main.game.getPlayer().setCumProduction(CumProduction.TWO_SMALL_AMOUNT.getMedianValue());
				break;
			case TWO_SMALL_AMOUNT:
				Main.game.getPlayer().setCumProduction(CumProduction.THREE_AVERAGE.getMedianValue());
				break;
			case THREE_AVERAGE:
				Main.game.getPlayer().setCumProduction(CumProduction.FOUR_LARGE.getMedianValue());
				break;
			case FOUR_LARGE:
				Main.game.getPlayer().setCumProduction(CumProduction.ZERO_NONE.getMedianValue());
				break;
			default:
				Main.game.getPlayer().setCumProduction(CumProduction.ZERO_NONE.getMedianValue());
				break;
		}
	}
	
	
	
	// ---------------------- Lips: ---------------------- //
	
	public static String getClitSizeOption() {
		stringsList.clear();
		for (ClitorisSize value : ClitorisSize.values()) {
			if(value.getMedianValue() < ClitorisSize.TWO_LARGE.getMinimumValue()) {
				if (Main.game.getPlayer().getVaginaClitorisSize() == value) {
					stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
				} else {
					stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
				}
			}
		}
		return stringsToSelection(stringsList);
	}
	public static String getVaginaCapacityOption() {
		stringsList.clear();
		for (Capacity value : Capacity.values()) {
			if (Main.game.getPlayer().getVaginaCapacity() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getDescriptor()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getDescriptor()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementClitSize() {
		switch(Main.game.getPlayer().getVaginaClitorisSize()) {
			case ZERO_AVERAGE:
				Main.game.getPlayer().setVaginaClitorisSize(ClitorisSize.ONE_BIG.getMedianValue());
				break;
			case ONE_BIG:
				Main.game.getPlayer().setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE.getMedianValue());
				break;
			default:
				Main.game.getPlayer().setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE.getMedianValue());
				break;
		}
	}
	public static void incrementVaginaCapacity() {
		switch(Main.game.getPlayer().getVaginaCapacity()) {
			case ZERO_IMPENETRABLE:
				Main.game.getPlayer().setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue());
				break;
			case ONE_EXTREMELY_TIGHT:
				Main.game.getPlayer().setVaginaCapacity(Capacity.TWO_TIGHT.getMedianValue());
				break;
			case TWO_TIGHT:
				Main.game.getPlayer().setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue());
				break;
			case THREE_SLIGHTLY_LOOSE:
				Main.game.getPlayer().setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue());
				break;
			case FOUR_LOOSE:
				Main.game.getPlayer().setVaginaCapacity(Capacity.FIVE_ROOMY.getMedianValue());
				break;
			case FIVE_ROOMY:
				Main.game.getPlayer().setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN.getMedianValue());
				break;
			case SIX_STRETCHED_OPEN:
				Main.game.getPlayer().setVaginaCapacity(Capacity.SEVEN_GAPING.getMedianValue());
				break;
			case SEVEN_GAPING:
				Main.game.getPlayer().setVaginaCapacity(Capacity.ZERO_IMPENETRABLE.getMedianValue());
				break;
		}
		Main.game.getPlayer().setVaginaStretchedCapacity(Main.game.getPlayer().getVaginaRawCapacityValue());
	}
	
	
	
	// ---------------------- Lips: ---------------------- //
	
	public static String getLipSizeOption() {
		stringsList.clear();
		for (LipSize value : LipSize.values()) {
			if (Main.game.getPlayer().getLipSize() == value) {
				stringsList.add("<b style='color:" + value.getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementLipSize() {
		switch(Main.game.getPlayer().getLipSize()) {
			case ZERO_THIN:
				Main.game.getPlayer().setLipSize(LipSize.ONE_AVERAGE.getValue());
				break;
			case ONE_AVERAGE:
				Main.game.getPlayer().setLipSize(LipSize.TWO_FULL.getValue());
				break;
			case TWO_FULL:
				Main.game.getPlayer().setLipSize(LipSize.THREE_PLUMP.getValue());
				break;
			case THREE_PLUMP:
				Main.game.getPlayer().setLipSize(LipSize.FOUR_HUGE.getValue());
				break;
			case FOUR_HUGE:
				Main.game.getPlayer().setLipSize(LipSize.ZERO_THIN.getValue());
				break;
		}
	}
	
	public static String getLipPuffyOption(boolean puffy) {
		stringsList.clear();
		if(!puffy) {
			stringsList.add("[style.boldDisabled(Regular)]");
		} else {
			stringsList.add("[style.colourDisabled(Regular)]");
		}	
		if(puffy) {
			stringsList.add("[style.boldGood(Puffy)]");
		} else {
			stringsList.add("[style.colourDisabled(Puffy)]");
		}
		return stringsToSelection(stringsList);
	}
	
	
	
	// ---------------------- Piercings: ---------------------- //
	
	public static String getPiercingsOptions(boolean pierced) {
		stringsList.clear();
		
		if(!pierced) {
			stringsList.add("[style.boldDisabled(Unpierced)]");
		} else {
			stringsList.add("[style.colourDisabled(Unpierced)]");
		}	
		if(pierced) {
			stringsList.add("[style.boldGood(Pierced)]");
		} else {
			stringsList.add("[style.colourDisabled(Pierced)]");
		}

		return stringsToSelection(stringsList);
	}
	
	
	
	// ---------------------- Coverings: ---------------------- //
	
	public static String getAllPrimaryCoveringOptions(BodyCoveringType coveringType) {
		stringsList.clear();
		for (Colour cs : coveringType.getAllPrimaryColours()) {
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				if(Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing()) {
					stringsList.add("<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				} else {
					stringsList.add("<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				}
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementAllPrimaryCovering(BodyCoveringType coveringType) {
		boolean found = false, applied = false;
		for (Colour cs : coveringType.getAllPrimaryColours()) {
			if(found) {
				Main.game.getPlayer().setSkinCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
						false);
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setSkinCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					coveringType.getAllPrimaryColours().get(0),
					coveringType.getAllPrimaryColours().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
					false);
		}
	}
	
	public static String getNaturalPrimaryCoveringOptions(BodyCoveringType coveringType) {
		stringsList.clear();
		for (Colour cs : coveringType.getNaturalColoursPrimary()) {
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				if(Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing()) {
					stringsList.add("<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				} else {
					stringsList.add("<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				}
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementNaturalPrimaryCovering(BodyCoveringType coveringType) {
		boolean found = false, applied = false;
		for (Colour cs : coveringType.getNaturalColoursPrimary()) {
			if(found) {
				Main.game.getPlayer().setSkinCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
						false);
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setSkinCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					coveringType.getNaturalColoursPrimary().get(0),
					coveringType.getNaturalColoursPrimary().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
					false);
		}
	}
	
	public static String getAllSecondaryCoveringOptions(BodyCoveringType coveringType, boolean disabled) {
		stringsList.clear();
		for (Colour cs : coveringType.getAllSecondaryColours()) {
			if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs && !disabled) {
				if(Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()) {
					stringsList.add("<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				} else {
					stringsList.add("<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				}
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementAllSecondaryCovering(BodyCoveringType coveringType) {
		boolean found = false, applied = false;
		for (Colour cs : coveringType.getAllSecondaryColours()) {
			if(found) {
				Main.game.getPlayer().setSkinCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
						false);
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setSkinCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					coveringType.getAllSecondaryColours().get(0),
					coveringType.getAllSecondaryColours().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
					false);
		}
	}
	
	public static String getNaturalSecondaryCoveringOptions(BodyCoveringType coveringType, boolean disabled) {
		stringsList.clear();
		for (Colour cs : coveringType.getNaturalColoursSecondary()) {
			if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs && !disabled) {
				if(Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()) {
					stringsList.add("<b style='color:"+cs.toWebHexString()+"; text-shadow: 0px 0px 4px "+cs.getShades()[4]+";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				} else {
					stringsList.add("<b style='color:" + cs.toWebHexString() + ";'>" + Util.capitaliseSentence(cs.getName()) + "</b>");
				}
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(cs.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementNaturalSecondaryCovering(BodyCoveringType coveringType) {
		boolean found = false, applied = false;
		for (Colour cs : coveringType.getNaturalColoursSecondary()) {
			if(found) {
				Main.game.getPlayer().setSkinCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
						false);
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setSkinCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					coveringType.getNaturalColoursSecondary().get(0),
					coveringType.getNaturalColoursSecondary().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
					false);
		}
	}
	
	public static String getAllPatternOptions(BodyCoveringType coveringType) {
		stringsList.clear();
		for (CoveringPattern value : coveringType.getAllPatterns()) {
			if (Main.game.getPlayer().getCovering(coveringType).getPattern() == value) {
				stringsList.add("<b>" + Util.capitaliseSentence(value.getName()) + "</b>");
			} else {
				stringsList.add("<span class='option-disabled'>" + Util.capitaliseSentence(value.getName()) + "</span>");
			}
		}
		return stringsToSelection(stringsList);
	}
	
	public static void incrementPatternFromAll(BodyCoveringType coveringType) {
		boolean found = false, applied = false;
		for (CoveringPattern value : coveringType.getAllPatterns()) {
			if(found) {
				Main.game.getPlayer().setSkinCovering(new Covering(
						coveringType,
						value,
						Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
						false);
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getPattern() == value) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setSkinCovering(new Covering(
					coveringType,
					coveringType.getAllPatterns().get(0),
					Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()),
					false);
		}
	}
	
	public static String getGlowOptions(boolean glow, boolean disabled) {
		stringsList.clear();
		
		if(disabled) {
			if(!glow) {
				stringsList.add("[style.colourDisabled(Regular)]");
			} else {
				stringsList.add("[style.colourDisabled(Regular)]");
			}	
			if(glow) {
				stringsList.add("[style.colourDisabled(Glowing)]");
			} else {
				stringsList.add("[style.colourDisabled(Glowing)]");
			}
		} else {
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
		}
		
		return stringsToSelection(stringsList);
	}
	
	
	
	

	// ---------------------- Character Creation: ---------------------- //
	
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
									:" "+UtilText.getColouredMoneySymbol("span")+" "
										+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST
											? SuccubisSecrets.BASE_HAIR_LENGTH_COST
											: "[style.colourBad("+SuccubisSecrets.BASE_HAIR_LENGTH_COST+")]"))
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
									:" "+UtilText.getColouredMoneySymbol("span")+" "
										+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST
											? SuccubisSecrets.BASE_HAIR_STYLE_COST
											: "[style.colourBad("+SuccubisSecrets.BASE_HAIR_STYLE_COST+")]"))
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
								:" "+UtilText.getColouredMoneySymbol("span")+" "
									+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST
										? SuccubisSecrets.BASE_BODY_HAIR_COST
										: "[style.colourBad("+SuccubisSecrets.BASE_BODY_HAIR_COST+")]"))
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
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title+" "+UtilText.getColouredMoneySymbol("span")+" "
								+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
									? SuccubisSecrets.BASE_ANAL_BLEACHING_COST
									: "[style.colourBad("+SuccubisSecrets.BASE_ANAL_BLEACHING_COST+")]")
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
									:" "+UtilText.getColouredMoneySymbol("span")+" "
										+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
											? SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
											: "[style.colourBad("+SuccubisSecrets.getBodyCoveringTypeCost(coveringType)+")]"))
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
									:" "+UtilText.getColouredMoneySymbol("span")+" "
										+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
											? SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
											: "[style.colourBad("+SuccubisSecrets.getBodyCoveringTypeCost(coveringType)+")]"))
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
								? title+
									(noCost
											?""
										:" "+UtilText.getColouredMoneySymbol("span")+" "
											+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(type)
												? SuccubisSecrets.getPiercingCost(type)
												: "[style.colourBad("+SuccubisSecrets.getPiercingCost(type)+")]"))
								:"[style.colourDisabled("+title+" "+Main.game.getCurrencySymbol()+" "+SuccubisSecrets.getPiercingCost(type)+")]")
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
