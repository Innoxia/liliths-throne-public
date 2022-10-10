package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.53
 * @version 0.3.1
 * @author Innoxia
 */
public class SexType implements XMLSaving {
	
	private SexParticipantType asParticipant;
	private SexAreaInterface performingSexArea;
	private SexAreaInterface targetedSexArea;

	/**
	 * Sets the SexParticipantType as NORMAL.
	 * @param performingSexArea
	 * @param targetedSexArea
	 */
	public SexType(SexAreaInterface performingSexArea, SexAreaInterface targetedSexArea) {
		this (SexParticipantType.NORMAL, performingSexArea, targetedSexArea);
	}
	
	public SexType(SexParticipantType asParticipant, SexAreaInterface performingSexArea, SexAreaInterface targetedSexArea) {
		this.asParticipant = asParticipant;
		this.performingSexArea = performingSexArea;
		this.targetedSexArea = targetedSexArea;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof SexType){
			if(((SexType)o).getAsParticipant() == getAsParticipant()
				&& ((SexType)o).getPerformingSexArea() == getPerformingSexArea()
				&& ((SexType)o).getTargetedSexArea() == getTargetedSexArea()){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getPerformingSexArea().hashCode();
		result = 31 * result + getTargetedSexArea().hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		return "Participant: "+asParticipant.toString()+"  Performing/Target: "+(performingSexArea==null?"null":performingSexArea.toString())+"/"+(targetedSexArea==null?"null":targetedSexArea.toString());
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element sexTypeElement = doc.createElement("sexType");
		parentElement.appendChild(sexTypeElement);
		
		XMLUtil.addAttribute(doc, sexTypeElement, "participant", asParticipant.toString());
		XMLUtil.addAttribute(doc, sexTypeElement, "self", performingSexArea.toString());
		XMLUtil.addAttribute(doc, sexTypeElement, "other", targetedSexArea.toString());
		
		return sexTypeElement;
	}
	
	public static SexType loadFromXML(Element parentElement, Document doc) {
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.6")) {
			return new SexType(
					SexParticipantType.valueOf(parentElement.getAttribute("SexParticipantType")),
					SexAreaPenetration.valueOf(parentElement.getAttribute("penetrationType")),
					SexAreaOrifice.valueOf(parentElement.getAttribute("orificeType")));
		}
		SexAreaInterface selfArea;
		SexAreaInterface otherArea;
		try {
			selfArea = SexAreaPenetration.valueOf(parentElement.getAttribute("self"));
		} catch(Exception ex) {
			selfArea = SexAreaOrifice.valueOf(parentElement.getAttribute("self"));
		}
		try {
			otherArea = SexAreaPenetration.valueOf(parentElement.getAttribute("other"));
		} catch(Exception ex) {
			otherArea = SexAreaOrifice.valueOf(parentElement.getAttribute("other"));
		}
		return new SexType(
				SexParticipantType.valueOf(parentElement.getAttribute("participant")),
				selfArea,
				otherArea);
	}
	
	public String getName() {
		return "";
	}

	/**
	 * @return A crude description of the sex that took place while using this SexType.
	 */
	public String getPerformanceDescription(boolean pastTense, boolean extendedDescription, GameCharacter performer, GameCharacter target) {
		SexAreaInterface areaPerforming = this.getPerformingSexArea();
		SexAreaInterface areaTargeted = this.getTargetedSexArea();
		
		if(extendedDescription) {
			SexPace performerPace = SexPace.DOM_NORMAL;
			SexPace targetPace = SexPace.DOM_NORMAL;
			if(Main.game.isInSex()) {
				performerPace = Main.sex.getSexPace(performer);
				targetPace = Main.sex.getSexPace(target);
			}
			
			String description = areaPerforming.getSexDescription(pastTense, performer, performerPace, target, targetPace, areaTargeted);
			if(!description.isEmpty()) {
				return description;
			}
			
			System.err.println("SexType.getPerformanceDescription() error: No description found for "+areaPerforming+" targeting "+areaTargeted+"!");
		}
		
		if(areaPerforming.isPenetration()) {
			if(areaTargeted.isPenetration()) {
				return UtilText.parse(performer, target,
						"[npc.Name] rubbed [npc.her] "+areaPerforming.getName(performer)+" against [npc2.namePos] "+areaTargeted.getName(target)+".");
			} else {
				return UtilText.parse(performer, target,
						"[npc.Name] used [npc.her] "+areaPerforming.getName(performer)+" "+(((SexAreaPenetration)areaPerforming).isTakesVirginity()?"to fuck":"on")+" [npc2.namePos] "+areaTargeted.getName(target)+".");
			}
			
		} else {
			if(areaTargeted.isPenetration()) {
				return UtilText.parse(performer, target,
						"[npc.Name] had [npc.her] "+areaPerforming.getName(performer)+" "+(((SexAreaPenetration)areaTargeted).isTakesVirginity()?"fucked by":"used by")+" [npc2.namePos] "+areaTargeted.getName(target)+".");
			} else {
				return UtilText.parse(performer, target,
						"[npc.Name] rubbed [npc.her] "+areaPerforming.getName(performer)+" against [npc2.namePos] "+areaTargeted.getName(target)+".");
			}
		}
	}
	
	public SexParticipantType getAsParticipant() {
		return asParticipant;
	}

	public SexAreaInterface getPerformingSexArea() {
		return performingSexArea;
	}

	public SexAreaInterface getTargetedSexArea() {
		return targetedSexArea;
	}
	
	/**
	 * @return true if this SexType has the potential to take virginity of an internal orifice.
	 */
	public boolean isTakesVirginity() {
		if(performingSexArea!=null && performingSexArea.isPenetration() && ((SexAreaPenetration)performingSexArea).isTakesVirginity()) {
			return targetedSexArea!=null && targetedSexArea.isOrifice() && ((SexAreaOrifice)targetedSexArea).isInternalOrifice();
		}
		if(targetedSexArea!=null && targetedSexArea.isPenetration() && ((SexAreaPenetration)targetedSexArea).isTakesVirginity()) {
			return performingSexArea!=null && performingSexArea.isOrifice() && ((SexAreaOrifice)performingSexArea).isInternalOrifice();
		}
		return false;
	}

	public boolean isPenetrating() {
		return getPerformingSexArea().isPenetration() && getTargetedSexArea().isOrifice();
	}
	
	public boolean isBeingPenetrated() {
		return getPerformingSexArea().isOrifice() && getTargetedSexArea().isPenetration();
	}
	
	public SexType getReversedSexType() {
		return new SexType(getAsParticipant(), getTargetedSexArea(), getPerformingSexArea());
	}
	
	public List<AbstractFetish> getRelatedFetishes(GameCharacter characterPerforming, GameCharacter characterTargeted, boolean isPenetration, boolean isOrgasm) {
		List<AbstractFetish> fetishes = new ArrayList<>();
		
		// Self areas:
		
		if(getPerformingSexArea()!=null && getPerformingSexArea().isPenetration()) {
			switch((SexAreaPenetration)getPerformingSexArea()) {
				case CLIT:
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case FINGER:
					break;
				case PENIS:
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case FOOT:
					fetishes.add(Fetish.FETISH_FOOT_GIVING);
					break;
				case TONGUE:
					if(getTargetedSexArea()!=SexAreaOrifice.MOUTH) { // Kissing should not be associated with oral fetish.
						fetishes.add(Fetish.FETISH_ORAL_GIVING);
					}
					break;
			}
		}
		if(getPerformingSexArea()!=null && getPerformingSexArea().isOrifice()) {
			switch((SexAreaOrifice)getPerformingSexArea()) {
				case ANUS:
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
					break;
				case ASS:
					fetishes.add(Fetish.FETISH_ANAL_RECEIVING);
					break;
				case BREAST: case BREAST_CROTCH:
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case MOUTH:
					if(getTargetedSexArea()!=SexAreaPenetration.TONGUE) { // Kissing should not be associated with oral fetish.
						fetishes.add(Fetish.FETISH_ORAL_GIVING);
					}
					break;
				case NIPPLE:
					if(characterPerforming.getBreastRawStoredMilkValue()>0) {
						fetishes.add(Fetish.FETISH_LACTATION_SELF);
					}
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case NIPPLE_CROTCH:
					if(characterPerforming.getBreastCrotchRawStoredMilkValue()>0) {
						fetishes.add(Fetish.FETISH_LACTATION_SELF);
					}
					fetishes.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case THIGHS:
					fetishes.add(Fetish.FETISH_STRUTTER);
					break;
				case URETHRA_PENIS:
					fetishes.add(Fetish.FETISH_PENIS_GIVING);
					break;
				case URETHRA_VAGINA:
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case VAGINA:
					if(characterTargeted.hasPenisIgnoreDildo()
							&& characterTargeted.getPenisRawStoredCumValue()>0
							&& getTargetedSexArea()==SexAreaPenetration.PENIS
							&& isOrgasm) {
						fetishes.add(Fetish.FETISH_PREGNANCY);
					}
					fetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case SPINNERET:
					break;
				case ARMPITS:
					fetishes.add(Fetish.FETISH_ARMPIT_RECEIVING);
					break;
			}
		}
		
		// Targeted areas:
		
		if(getTargetedSexArea()!=null && getTargetedSexArea().isPenetration()) {
			switch((SexAreaPenetration)getTargetedSexArea()) {
				case CLIT:
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					break;
				case FINGER:
					break;
				case PENIS:
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case FOOT:
					fetishes.add(Fetish.FETISH_FOOT_RECEIVING);
					break;
				case TONGUE:
					if(getPerformingSexArea()!=SexAreaOrifice.MOUTH) { // Kissing should not be associated with oral fetish.
						fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
					}
					break;
			}
		}
		if(getTargetedSexArea()!=null && getTargetedSexArea().isOrifice()) {
			switch((SexAreaOrifice)getTargetedSexArea()) {
				case ARMPITS:
					fetishes.add(Fetish.FETISH_ARMPIT_GIVING);
					break;
				case ANUS:
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isAssVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case ASS:
					fetishes.add(Fetish.FETISH_ANAL_GIVING);
					break;
				case BREAST:
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					break;
				case BREAST_CROTCH:
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					break;
				case MOUTH:
					if(getPerformingSexArea()!=SexAreaPenetration.TONGUE) { // Kissing should not be associated with oral fetish.
						fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
					}
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isFaceVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case NIPPLE:
					if(characterTargeted.getBreastRawStoredMilkValue()>0) {
						fetishes.add(Fetish.FETISH_LACTATION_OTHERS);
					}
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isNippleVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case NIPPLE_CROTCH:
					if(characterTargeted.getBreastCrotchRawStoredMilkValue()>0) {
						fetishes.add(Fetish.FETISH_LACTATION_OTHERS);
					}
					fetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isNippleCrotchVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case THIGHS:
					fetishes.add(Fetish.FETISH_LEG_LOVER);
					break;
				case URETHRA_PENIS:
					fetishes.add(Fetish.FETISH_PENIS_RECEIVING);
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isUrethraVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case URETHRA_VAGINA:
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isVaginaUrethraVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case VAGINA:
					if(characterPerforming.hasPenisIgnoreDildo() 
							&& characterPerforming.getPenisRawStoredCumValue()>0
							&& getPerformingSexArea()!=null
							&& getPerformingSexArea()==SexAreaPenetration.PENIS
							&& isOrgasm) {
						fetishes.add(Fetish.FETISH_IMPREGNATION);
					}
					fetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isVaginaVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
				case SPINNERET:
					if(getPerformingSexArea()!=null && isPenetration && getPerformingSexArea().isPenetration() && ((SexAreaPenetration)getPerformingSexArea()).isTakesVirginity() &&  characterTargeted.isSpinneretVirgin()) {
						fetishes.add(Fetish.FETISH_DEFLOWERING);
					}
					break;
			}
		}
		
		// Check for masturbation:
		if(!fetishes.contains(Fetish.FETISH_MASTURBATION) && (characterPerforming.equals(characterTargeted) || this.asParticipant==SexParticipantType.SELF)) {
			fetishes.add(Fetish.FETISH_MASTURBATION);
		}
		
		return fetishes;
	}
	
	public List<AbstractFetish> getOppositeFetishes(GameCharacter characterPerforming, GameCharacter characterTargeted, boolean isPenetration, boolean isOrgasm) {
		List<AbstractFetish> oppositeFetishes = new ArrayList<>();
		for(AbstractFetish f : getRelatedFetishes(characterPerforming, characterTargeted, isPenetration, isOrgasm)) {
			AbstractFetish opposite = f.getOpposite();
			if(opposite != null) {
				oppositeFetishes.add(f.getOpposite());
			}
		}
		return oppositeFetishes;
	}
}
