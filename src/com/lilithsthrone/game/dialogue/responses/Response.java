package com.lilithsthrone.game.dialogue.responses;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69
 * @version 0.1.99
 * @author Innoxia
 */
public class Response {
	
	protected String title, tooltipText;
	protected DialogueNodeOld nextDialogue;
	
	protected List<Fetish> fetishesRequired;
	protected CorruptionLevel corruptionBypass;
	private List<Perk> perksRequired;
	private Femininity femininityRequired;
	private Race raceRequired;

	private PenetrationType penetrationTypeAccessRequired;
	private OrificeType orificeTypeAccessRequired;

	private GameCharacter characterPenetrating;
	private GameCharacter characterPenetrated;
	
	public Response(String title,
			String tooltipText,
			DialogueNodeOld nextDialogue) {
		
		this(title, tooltipText, nextDialogue,
				null, null,
				null, null, null);
	}
	
	public Response(String title,
			String tooltipText,
			DialogueNodeOld nextDialogue,
			List<Fetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired) {
		
		this(title, tooltipText, nextDialogue,
				fetishesForUnlock, corruptionBypass,
				perksRequired, femininityRequired, raceRequired,
				null, null, null, null);
	}
	
	public Response(String title,
			String tooltipText,
			DialogueNodeOld nextDialogue, 
			List<Fetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired,
			GameCharacter characterPenetrating, PenetrationType penetrationTypeAccessRequired,
			GameCharacter characterPenetrated, OrificeType orificeTypeAccessRequired) {
		
		this.title = UtilText.parse(title);
		this.tooltipText = UtilText.parse(tooltipText);
		this.nextDialogue = nextDialogue;
		
		this.fetishesRequired = fetishesForUnlock;
		this.corruptionBypass = corruptionBypass;
		this.perksRequired = perksRequired;
		this.femininityRequired = femininityRequired;
		this.raceRequired = raceRequired;
		
		this.penetrationTypeAccessRequired = penetrationTypeAccessRequired;
		this.orificeTypeAccessRequired = orificeTypeAccessRequired;

		this.characterPenetrating=characterPenetrating;
		this.characterPenetrated=characterPenetrated;
	}

	public String getTitle() {
		return title;
	}

	public String getTooltipText() {
		return tooltipText;
	}

	public DialogueNodeOld getNextDialogue() {
		if(isAvailable() || isAbleToBypass()) {
			return nextDialogue;
		} else {
			return null;
		}
	}

	public boolean disabledOnNullDialogue(){
		return true;
	}
	
	public boolean isSexHighlight() {
		return false;
	}
	
	public boolean isSexPenetrationHighlight() {
		return false;
	}
	
	public boolean isSexPositioningHighlight() {
		return false;
	}
	
	public boolean isCombatHighlight() {
		return false;
	}
	
	public boolean isCorruptionHighlight() {
		return false;
	}
	
	public boolean isTradeHighlight() {
		return false;
	}
	
	public Colour getHighlightColour() {
		if(isSexHighlight()) {
			return Colour.GENERIC_SEX;
			
		} else if(isSexPenetrationHighlight()) {
			return Colour.GENERIC_SEX;
			
		} else if(isSexPositioningHighlight()) {
			return Colour.GENERIC_ARCANE;
			
		} else if(isCombatHighlight()) {
			return Colour.GENERIC_COMBAT;
			
		} else if(isCorruptionHighlight()) {
			return Colour.ATTRIBUTE_CORRUPTION;
			
		} else if(isTradeHighlight()) {
			return Colour.BASE_YELLOW_LIGHT;
			
		} else {
			return Colour.TEXT;
		}
	}
	
	public SexPace getSexPace() {
		return null;
	}
	
	public SexActionType getSexActionType() {
		return null;
	}
	
	public final void applyEffects() {
		effects();
	}
	
	public void effects() {
	}
	
	public boolean hasRequirements() {
		return fetishesRequired != null
				|| corruptionBypass != null
				|| perksRequired != null
				|| femininityRequired != null
				|| raceRequired != null
				|| penetrationTypeAccessRequired != null
				|| orificeTypeAccessRequired != null;
	}
	
	public boolean isAvailable(){
		if(hasRequirements()) {
			return (isCorruptionWithinRange() || isAvailableFromFetishes() || (corruptionBypass==null && fetishesRequired==null))
					&& !isBlockedFromPerks()
					&& isFemininityInRange()
					&& isRequiredRace()
					&& isPenetrationTypeAvailable()
					&& isOrificeTypeAvailable();
		} else {
			return true;
		}
	}
	
	public boolean isAbleToBypass(){
		if(!isAvailable()) {
			return !(isBlockedFromPerks()
					|| !isFemininityInRange()
					|| !isRequiredRace()
					|| !isPenetrationTypeAvailable()
					|| !isOrificeTypeAvailable()
					|| (corruptionBypass==null && fetishesRequired!=null));
		}
		
		return false;
	}

	private StringBuilder SB;
	public String getTooltipCorruptionBypassText() {
		SB = new StringBuilder();
		
		if(!isAvailable() && !isAbleToBypass()) {
			SB.append("This action is being blocked, due to not meeting certain <span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>requirements</span>.");
		} else {
			if(isAvailableFromFetishes()) {
				SB.append("Your <span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fetish</span> bypasses this action's"
						+ " <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>corruption</span> requirements!");
				return SB.toString();
			}
			
			if(corruptionBypass != null) {
				if(isCorruptionWithinRange())
					SB.append("Your <span style='color:"+Main.game.getPlayer().getCorruptionLevel().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getCorruptionLevel().getName())+"</span>"
							+ " <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>corruption</span> has unlocked this action!");
				else
					SB.append("You will gain <b>+"+corruptionBypass.getCorruptionBypass()+"</b> <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>corruption</b>, as"
							+ " you don't meet the <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>corruption</span> or <span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fetish</span> requirements!");
			} else {
				SB.append("This action cannot be unlocked with <span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>corruption</span>.");
			}
		}
		
		return SB.toString();
	}
	
	public String getTooltipBlockingList(){
		SB = new StringBuilder();
		
		if(perksRequired!=null) {
			for(Perk p : perksRequired){
				if(Main.game.getPlayer().hasTrait(p, true)) {
					SB.append("</br>"
							+"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
							+ " (<span style='color:"+Colour.PERK.toWebHexString()+";'>Perk</span>): "
							+Util.capitaliseSentence(p.getName(Main.game.getPlayer())));
				} else {
					SB.append("</br>"
							+"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
							+ " (<span style='color:"+Colour.PERK.toWebHexString()+";'>Perk</span>): "
							+Util.capitaliseSentence(p.getName(Main.game.getPlayer())));
				}
			}
		}
		
		if(femininityRequired!=null) {
			if(isFemininityInRange()) {
				SB.append("</br>"
						+"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (Femininity): "
						+ "<span style='color:"+femininityRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(femininityRequired.getName(false))+"</span>");
			} else {
				SB.append("</br>"
						+"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (Femininity): "
						+ "<span style='color:"+femininityRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(femininityRequired.getName(false))+"</span>");
			}
		}
		
		if(raceRequired!=null) {
			if(isRequiredRace()) {
				SB.append("</br>"
						+"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (Race): "
						+"<span style='color:"+raceRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceRequired.getName())+"</span>");
			} else {
				SB.append("</br>"
						+"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (Race): "
						+"<span style='color:"+raceRequired.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(raceRequired.getName())+"</span>");
			}
		}
		
		if(penetrationTypeAccessRequired!=null && characterPenetrating!=null) {
			boolean penetrationAccess = characterPenetrating.isPenetrationTypeExposed(penetrationTypeAccessRequired);
			boolean penetrationFree = penetrationTypeAccessRequired.isFree(characterPenetrating);
			
			String penetrationName = Util.capitaliseSentence(penetrationTypeAccessRequired.getName(characterPenetrating));
			String accessText = (penetrationAccess?"access":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>access</span>");
			String freeText = (penetrationFree?"free":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>free</span>");
			String targetName = (characterPenetrating.isPlayer()?"Your":UtilText.parse(characterPenetrating, "[npc.Name]'s"));
			
			if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED || getSexActionType()==SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED
					|| getSexActionType()==SexActionType.PLAYER_PENETRATION || getSexActionType()==SexActionType.PARTNER_PENETRATION) {
				if(penetrationAccess && penetrationFree) {
					SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ penetrationName);
				} else {
					SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ penetrationName);
				}
				
			} else if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION || getSexActionType()==SexActionType.PARTNER_REQUIRES_NO_PENETRATION) {
				if(penetrationFree) {
					SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+freeText+"): "+targetName+" "+ penetrationName);
				} else {
					SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+freeText+"): "+targetName+" "+ penetrationName);
				}
				
			} else {
				if(penetrationAccess) {
					SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ penetrationName);
				} else {
					SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ penetrationName);
				}
			}
		}
		
		if(orificeTypeAccessRequired!=null && characterPenetrated!=null) {
			boolean orificeAccess = characterPenetrated.isOrificeTypeExposed(orificeTypeAccessRequired);
			boolean orificeFree = orificeTypeAccessRequired.isFree(characterPenetrated);
			
			String orificeName = Util.capitaliseSentence(orificeTypeAccessRequired.getName(characterPenetrated));
			String accessText = (orificeAccess?"access":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>access</span>");
			String freeText = (orificeFree?"free":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>free</span>");
			String targetName = (characterPenetrated.isPlayer()?"Your":UtilText.parse(characterPenetrated, "[npc.Name]'s"));
			
			if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED || getSexActionType()==SexActionType.PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED
					|| getSexActionType()==SexActionType.PLAYER_PENETRATION || getSexActionType()==SexActionType.PARTNER_PENETRATION) {
				if(orificeAccess && orificeFree) {
					SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ orificeName);
				} else {
					SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): "+targetName+" "+ orificeName);
				}
				
			} else if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION || getSexActionType()==SexActionType.PARTNER_REQUIRES_NO_PENETRATION) {
				if(orificeFree) {
					SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+freeText+"): "+targetName+" "+ orificeName);
				} else {
					SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+freeText+"): "+targetName+" "+ orificeName);
				}
				
			} else {
				if(orificeAccess) {
					SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ orificeName);
				} else {
					SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): "+targetName+" "+ orificeName);
				}
			}
		}
		
		return SB.toString();
	}
	
	public String getTooltipRequiredList(){
		SB = new StringBuilder();
		
		if(fetishesRequired!=null) {
			for(Fetish f : fetishesRequired){
				if(Main.game.getPlayer().hasFetish(f)) {
					SB.append("</br>"
							+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
							+ " (<span style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>owned</span>): "
							+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
					
				} else {
					SB.append("</br>"
							+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
							+ " (<span style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>not owned</span>): "
							+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
				}
			}
		}
		
		if(corruptionBypass!=null) {
			if(isCorruptionWithinRange()) {
				SB.append("</br>"
						+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
						+ " (<span style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>within range</span>): "
						+ Util.capitaliseSentence(corruptionBypass.getName()));
			} else {
				SB.append("</br>"
						+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
						+ " (<span style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>out of range</span>): "
						+ Util.capitaliseSentence(corruptionBypass.getName()));
			}
		}
		
		return SB.toString();
	}
	
	public int lineHeight(){
		int heightLeft = 0;
		
		if(perksRequired!=null)
			heightLeft+=perksRequired.size();
		if(femininityRequired!=null)
			heightLeft++;
		if(raceRequired!=null)
			heightLeft++;
		if(penetrationTypeAccessRequired!=null)
			heightLeft ++;
		if(orificeTypeAccessRequired!=null)
			heightLeft ++;
		
		if(fetishesRequired!=null)
			heightLeft+=fetishesRequired.size();
		if(corruptionBypass!=null)
			heightLeft++;
		
		return heightLeft;
	}

	public boolean isCorruptionWithinRange() {
		return corruptionBypass != null && corruptionBypass.getMinimumValue() <= Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION);
	}
	
	public boolean isAvailableFromFetishes() {
		if(fetishesRequired==null)
			return false;
		
		for (Fetish f : fetishesRequired) {
			if(Main.game.getPlayer().hasFetish(f)) {
				if(f==Fetish.FETISH_PURE_VIRGIN) {
					if(Main.game.getPlayer().hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN)) // Virginity fetish only blocks if player is still a virgin.
						return true;
				} else
					return true;
			}
		}
		return false;
	}
	
	public boolean isBlockedFromPerks() {
		if(perksRequired==null)
			return false;
		
		for (Perk p : perksRequired) {
			if(!Main.game.getPlayer().hasTrait(p, true)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFemininityInRange() {
		if(femininityRequired==null)
			return true;
		
		switch(femininityRequired){
			case ANDROGYNOUS:
				return Femininity.valueOf(Main.game.getPlayer().getFemininityValue()) == Femininity.ANDROGYNOUS;
			case FEMININE:
				return Main.game.getPlayer().getFemininityValue() >= Femininity.FEMININE.getMinimumFemininity();
			case FEMININE_STRONG:
				return Main.game.getPlayer().getFemininityValue() >= Femininity.FEMININE_STRONG.getMinimumFemininity();
			case MASCULINE:
				return Main.game.getPlayer().getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity();
			case MASCULINE_STRONG:
				return Main.game.getPlayer().getFemininityValue() <= Femininity.MASCULINE_STRONG.getMaximumFemininity();
			default:
				return true;
		}
	}
	
	public boolean isRequiredRace() {
		return raceRequired == null || Main.game.getPlayer().getRace() == raceRequired;
	}
	
	public boolean isPenetrationTypeAvailable() {
		if(penetrationTypeAccessRequired==null)
			return true;
		
		// Don't care if exposed or not:
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION: case PLAYER_REQUIRES_NO_PENETRATION:
					if(penetrationTypeAccessRequired.isFree(characterPenetrating))
						return true;
					break;
				case PARTNER_PENETRATION: case PLAYER_PENETRATION:
					if(!penetrationTypeAccessRequired.isFree(characterPenetrating))
						return false;
					break;
				default:
					break;
			}
		}
		
		// Check to make sure penetrationType is exposed:
		if(!characterPenetrating.isPenetrationTypeExposed(penetrationTypeAccessRequired)) {
			return false;
		}
		
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED: case PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED:
					if(!penetrationTypeAccessRequired.isFree(characterPenetrating))
						return false;
					break;
				default:
					break;
			}
		}
		
		return true;
	}
	
	public boolean isOrificeTypeAvailable() {
		if(orificeTypeAccessRequired==null)
			return true;
		
		// Don't care if exposed or not:
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION: case PLAYER_REQUIRES_NO_PENETRATION:
					if(orificeTypeAccessRequired.isFree(characterPenetrated))
						return true;
					break;
				case PARTNER_PENETRATION: case PLAYER_PENETRATION:
					if(!orificeTypeAccessRequired.isFree(characterPenetrated))
						return false;
					break;
				default:
					break;
			}
		}

		// Check to make sure orifice is exposed:
		if(!characterPenetrated.isOrificeTypeExposed(orificeTypeAccessRequired)) {
			return false;
		}
		
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED: case PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED:
					if(!orificeTypeAccessRequired.isFree(characterPenetrated))
						return false;
					break;
				default:
					break;
			}
		}
		
		return true;
	}

	public List<Fetish> getFetishesForUnlock() {
		return fetishesRequired;
	}

	public CorruptionLevel getCorruptionNeeded() {
		return corruptionBypass;
	}

	public List<Perk> getPerksRequired() {
		return perksRequired;
	}

	public Femininity getFemininityRequired() {
		return femininityRequired;
	}

	public Race getRaceRequired() {
		return raceRequired;
	}
}
