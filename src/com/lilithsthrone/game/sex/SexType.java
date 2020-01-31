package com.lilithsthrone.game.sex;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
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
		Element effect = doc.createElement("sexType");
		parentElement.appendChild(effect);
		
		CharacterUtils.addAttribute(doc, effect, "SexParticipantType", asParticipant.toString());
		CharacterUtils.addAttribute(doc, effect, "penetrationType", performingSexArea.toString());
		CharacterUtils.addAttribute(doc, effect, "orificeType", targetedSexArea.toString());
		
		return effect;
	}
	
	public static SexType loadFromXML(Element parentElement, Document doc) {
		return new SexType(
				SexParticipantType.valueOf(parentElement.getAttribute("SexParticipantType")),
				SexAreaPenetration.valueOf(parentElement.getAttribute("penetrationType")),
				SexAreaOrifice.valueOf(parentElement.getAttribute("orificeType")));
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
				return UtilText.parse(performer, target, "[npc.Name] rubbed [npc.her] "+areaPerforming.getName(performer)+" against [npc2.namePos] "+areaTargeted.getName(target)+".");
			} else {
				return UtilText.parse(performer, target, "[npc.Name] used [npc.her] "+areaPerforming.getName(performer)+" to fuck [npc2.namePos] "+areaTargeted.getName(target)+".");
			}
			
		} else {
			if(areaTargeted.isPenetration()) {
				return UtilText.parse(performer, target, "[npc.Name] had [npc.her] "+areaPerforming.getName(performer)+" fucked by [npc2.namePos] "+areaTargeted.getName(target)+".");
			} else {
				return UtilText.parse(performer, target, "[npc.Name] rubbed [npc.her] "+areaPerforming.getName(performer)+" against [npc2.namePos] "+areaTargeted.getName(target)+".");
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
	
	public List<Fetish> getRelatedFetishes(GameCharacter characterPerforming, GameCharacter characterTargeted, boolean isPenetration, boolean isOrgasm) {
		List<Fetish> fetishes = new ArrayList<>();
		
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
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
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
					fetishes.add(Fetish.FETISH_ORAL_GIVING);
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
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
					break;
			}
		}
		if(getTargetedSexArea()!=null && getTargetedSexArea().isOrifice()) {
			switch((SexAreaOrifice)getTargetedSexArea()) {
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
					fetishes.add(Fetish.FETISH_ORAL_RECEIVING);
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
			}
		}
		
		// Check for masturbation:
		if(!fetishes.contains(Fetish.FETISH_MASTURBATION) && (characterPerforming.equals(characterTargeted) || this.asParticipant==SexParticipantType.SELF)) {
			fetishes.add(Fetish.FETISH_MASTURBATION);
		}
		
		return fetishes;
	}
	
	public List<Fetish> getOppositeFetishes(GameCharacter characterPerforming, GameCharacter characterTargeted, boolean isPenetration, boolean isOrgasm) {
		List<Fetish> oppositeFetishes = new ArrayList<>();
		for(Fetish f : getRelatedFetishes(characterPerforming, characterTargeted, isPenetration, isOrgasm)) {
			switch(f) {
				case FETISH_ANAL_GIVING:
					oppositeFetishes.add(Fetish.FETISH_ANAL_RECEIVING);
					break;
				case FETISH_ANAL_RECEIVING:
					oppositeFetishes.add(Fetish.FETISH_ANAL_GIVING);
					break;
				case FETISH_BIMBO:
					break;
				case FETISH_BREASTS_OTHERS:
					oppositeFetishes.add(Fetish.FETISH_BREASTS_SELF);
					break;
				case FETISH_BREASTS_SELF:
					oppositeFetishes.add(Fetish.FETISH_BREASTS_OTHERS);
					break;
				case FETISH_LACTATION_OTHERS:
					oppositeFetishes.add(Fetish.FETISH_LACTATION_SELF);
					break;
				case FETISH_LACTATION_SELF:
					oppositeFetishes.add(Fetish.FETISH_LACTATION_OTHERS);
					break;
				case FETISH_BREEDER:
					break;
				case FETISH_CROSS_DRESSER:
					break;
				case FETISH_CUM_ADDICT:
					oppositeFetishes.add(Fetish.FETISH_CUM_STUD);
					break;
				case FETISH_CUM_STUD:
					oppositeFetishes.add(Fetish.FETISH_CUM_ADDICT);
					break;
				case FETISH_DEFLOWERING:
					break;
				case FETISH_DENIAL:
					oppositeFetishes.add(Fetish.FETISH_DENIAL_SELF);
					break;
				case FETISH_DENIAL_SELF:
					oppositeFetishes.add(Fetish.FETISH_DENIAL);
					break;
				case FETISH_DOMINANT:
					oppositeFetishes.add(Fetish.FETISH_SUBMISSIVE);
					break;
				case FETISH_EXHIBITIONIST:
					break;
				case FETISH_IMPREGNATION:
					oppositeFetishes.add(Fetish.FETISH_PREGNANCY);
					break;
				case FETISH_INCEST:
					oppositeFetishes.add(Fetish.FETISH_INCEST);
					break;
				case FETISH_LEG_LOVER:
					oppositeFetishes.add(Fetish.FETISH_STRUTTER);
					break;
				case FETISH_LUSTY_MAIDEN:
					break;
				case FETISH_MASOCHIST:
					oppositeFetishes.add(Fetish.FETISH_SADIST);
					break;
				case FETISH_MASTURBATION:
					oppositeFetishes.add(Fetish.FETISH_MASTURBATION);
					break;
				case FETISH_NON_CON_DOM:
					oppositeFetishes.add(Fetish.FETISH_NON_CON_SUB);
					break;
				case FETISH_NON_CON_SUB:
					oppositeFetishes.add(Fetish.FETISH_NON_CON_DOM);
					break;
				case FETISH_ORAL_GIVING:
					oppositeFetishes.add(Fetish.FETISH_ORAL_RECEIVING);
					break;
				case FETISH_ORAL_RECEIVING:
					oppositeFetishes.add(Fetish.FETISH_ORAL_GIVING);
					break;
				case FETISH_PREGNANCY:
					oppositeFetishes.add(Fetish.FETISH_IMPREGNATION);
					break;
				case FETISH_PURE_VIRGIN:
					break;
				case FETISH_SADIST:
					oppositeFetishes.add(Fetish.FETISH_MASOCHIST);
					break;
				case FETISH_SADOMASOCHIST:
					break;
				case FETISH_STRUTTER:
					oppositeFetishes.add(Fetish.FETISH_LEG_LOVER);
					break;
				case FETISH_SUBMISSIVE:
					oppositeFetishes.add(Fetish.FETISH_DOMINANT);
					break;
				case FETISH_SWITCH:
					break;
				case FETISH_TRANSFORMATION_GIVING:
					oppositeFetishes.add(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					break;
				case FETISH_TRANSFORMATION_RECEIVING:
					oppositeFetishes.add(Fetish.FETISH_TRANSFORMATION_GIVING);
					break;
				case FETISH_VAGINAL_GIVING:
					oppositeFetishes.add(Fetish.FETISH_VAGINAL_RECEIVING);
					break;
				case FETISH_VAGINAL_RECEIVING:
					oppositeFetishes.add(Fetish.FETISH_VAGINAL_GIVING);
					break;
				case FETISH_VOYEURIST:
					break;
				case FETISH_KINK_GIVING:
					break;
				case FETISH_KINK_RECEIVING:
					break;
				case FETISH_PENIS_GIVING:
					oppositeFetishes.add(Fetish.FETISH_PENIS_RECEIVING);
					break;
				case FETISH_PENIS_RECEIVING:
					oppositeFetishes.add(Fetish.FETISH_PENIS_GIVING);
					break;
				case FETISH_FOOT_GIVING:
					oppositeFetishes.add(Fetish.FETISH_FOOT_RECEIVING);
					break;
				case FETISH_FOOT_RECEIVING:
					oppositeFetishes.add(Fetish.FETISH_FOOT_GIVING);
					break;
			}
		}
		return oppositeFetishes;
	}
}
