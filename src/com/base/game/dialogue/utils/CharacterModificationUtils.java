package com.base.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.AreolaeSize;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.HairStyle;
import com.base.game.character.body.valueEnums.Lactation;
import com.base.game.character.body.valueEnums.LipSize;
import com.base.game.character.body.valueEnums.NippleSize;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

public class CharacterModificationUtils {

	private static StringBuilder contentSB = new StringBuilder();
	private static List<String> stringsList = new ArrayList<>();
	
	public static String stringsToSelection(List<String> strings) {
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
				setFacialHairIfAvailable(BodyHair.MANICURED);
				break;
			case MANICURED:
				setFacialHairIfAvailable(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				setFacialHairIfAvailable(BodyHair.BUSHY);
				break;
			case BUSHY:
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
				Main.game.getPlayer().setPubicHair(BodyHair.MANICURED);
				break;
			case MANICURED:
				Main.game.getPlayer().setPubicHair(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				Main.game.getPlayer().setPubicHair(BodyHair.BUSHY);
				break;
			case BUSHY:
				Main.game.getPlayer().setPubicHair(BodyHair.NONE);
				break;
		}
	}
	public static void incrementUnderarmHair() {
		switch(Main.game.getPlayer().getUnderarmHair()) {
			case NONE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.MANICURED);
				break;
			case MANICURED:
				Main.game.getPlayer().setUnderarmHair(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				Main.game.getPlayer().setUnderarmHair(BodyHair.BUSHY);
				break;
			case BUSHY:
				Main.game.getPlayer().setUnderarmHair(BodyHair.NONE);
				break;
		}
	}
	public static void incrementAssHair() {
		switch(Main.game.getPlayer().getAssHair()) {
			case NONE:
				Main.game.getPlayer().setAssHair(BodyHair.MANICURED);
				break;
			case MANICURED:
				Main.game.getPlayer().setAssHair(BodyHair.TRIMMED);
				break;
			case TRIMMED:
				Main.game.getPlayer().setAssHair(BodyHair.BUSHY);
				break;
			case BUSHY:
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
				Main.game.getPlayer().setCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					coveringType.getAllPrimaryColours().get(0),
					coveringType.getAllPrimaryColours().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
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
				Main.game.getPlayer().setCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getPrimaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					coveringType.getNaturalColoursPrimary().get(0),
					coveringType.getNaturalColoursPrimary().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
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
				Main.game.getPlayer().setCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					coveringType.getAllSecondaryColours().get(0),
					coveringType.getAllSecondaryColours().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
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
				Main.game.getPlayer().setCovering(new Covering(
						coveringType,
						Main.game.getPlayer().getCovering(coveringType).getPattern(),
						Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						cs,
						cs==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getSecondaryColour() == cs) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setCovering(new Covering(
					coveringType,
					Main.game.getPlayer().getCovering(coveringType).getPattern(),
					Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					coveringType.getNaturalColoursSecondary().get(0),
					coveringType.getNaturalColoursSecondary().get(0)==Colour.COVERING_NONE?false:Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
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
				Main.game.getPlayer().setCovering(new Covering(
						coveringType,
						value,
						Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
						Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
						Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
				applied = true;
				break;
			}
			if (Main.game.getPlayer().getCovering(coveringType).getPattern() == value) {
				found = true;
			}
		}
		if(!applied) {
			Main.game.getPlayer().setCovering(new Covering(
					coveringType,
					coveringType.getAllPatterns().get(0),
					Main.game.getPlayer().getCovering(coveringType).getPrimaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isPrimaryGlowing(),
					Main.game.getPlayer().getCovering(coveringType).getSecondaryColour(),
					Main.game.getPlayer().getCovering(coveringType).isSecondaryGlowing()));
		}
	}
}
