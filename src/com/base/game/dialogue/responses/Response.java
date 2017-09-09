package com.base.game.dialogue.responses;

import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.Perk;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.race.Race;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.SexPace;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.69
 * @version 0.1.82
 * @author Innoxia
 */
public class Response {
	
	protected String title, tooltipText;
	protected DialogueNodeOld nextDialogue;
	private QuestLine questLine;
	
	protected List<Fetish> fetishesRequired;
	protected CorruptionLevel corruptionBypass;
	private List<Perk> perksRequired;
	private Femininity femininityRequired;
	private Race raceRequired;

	private PenetrationType penetrationTypeAccessRequired;
	private OrificeType orificeTypeAccessRequired;
	private GameCharacter partner;
	
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
				null, null, null);
	}
	
	public Response(String title,
			String tooltipText,
			DialogueNodeOld nextDialogue, 
			List<Fetish> fetishesForUnlock,
			CorruptionLevel corruptionBypass,
			List<Perk> perksRequired,
			Femininity femininityRequired,
			Race raceRequired,
			PenetrationType penetrationTypeAccessRequired,
			OrificeType orificeTypeAccessRequired,
			GameCharacter partner) {
		
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
		this.partner=partner;
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
	
	public QuestLine getQuestLine() {
		return null;
	}
	
	public SexPace getSexPace() {
		return null;
	}
	
	public SexActionType getSexActionType() {
		return null;
	}
	
	public final void applyEffects() {
		if(questLine != null) {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(questLine));
		}
		
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
				if(Main.game.getPlayer().hasPerk(p)) {
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
		
		if(penetrationTypeAccessRequired!=null) {
			if(penetrationTypeAccessRequired.isPlayer()) {
				
				boolean penetrationAccess = Main.game.getPlayer().isPenetrationTypeExposed(penetrationTypeAccessRequired),
						penetrationFree = penetrationTypeAccessRequired.isFree();
				String penetrationName = Util.capitaliseSentence(penetrationTypeAccessRequired.getName()),
						accessText = (penetrationAccess?"access":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>access</span>"),
						freeText = (penetrationFree?"free":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>free</span>");
				
				if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED) {
					if(penetrationAccess && penetrationFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Your "+ penetrationName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Your "+ penetrationName);
					}
					
				} else if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION) {
					if(penetrationFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Your "+ penetrationName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Your "+ penetrationName);
					}
					
				} else {
					if(penetrationAccess) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Your "+ penetrationName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Your "+ penetrationName);
					}
				}
				
			} else {
				
				boolean penetrationAccess = partner.isPenetrationTypeExposed(penetrationTypeAccessRequired),
						penetrationFree = penetrationTypeAccessRequired.isFree();
				String penetrationName = Util.capitaliseSentence(penetrationTypeAccessRequired.getName()),
						accessText = (penetrationAccess?"access":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>access</span>"),
						freeText = (penetrationFree?"free":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>free</span>");
				
				if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED) {
					if(penetrationAccess && penetrationFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Partner's "+ penetrationName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Partner's "+ penetrationName);
					}
					
				} else if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION) {
					if(penetrationFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Partner's "+ penetrationName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Partner's "+ penetrationName);
					}
					
				} else {
					if(penetrationAccess) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Partner's "+ penetrationName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Partner's "+ penetrationName);
					}
				}
			}
		}
		
		if(orificeTypeAccessRequired!=null) {
			if(orificeTypeAccessRequired.isPlayer()) {
				
				boolean orificeAccess = Main.game.getPlayer().isOrificeTypeExposed(orificeTypeAccessRequired),
						orificeFree = orificeTypeAccessRequired.isFree();
				String orificeName = Util.capitaliseSentence(orificeTypeAccessRequired.getName()),
						accessText = (orificeAccess?"access":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>access</span>"),
						freeText = (orificeFree?"free":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>free</span>");
				
				if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED) {
					if(orificeAccess && orificeFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Your "+ orificeName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Your "+ orificeName);
					}
					
				} else if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION) {
					if(orificeFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Your "+ orificeName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Your "+ orificeName);
					}
					
				} else {
					if(orificeAccess) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Your "+ orificeName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Your "+ orificeName);
					}
				}
				
			} else {
				
				boolean orificeAccess = partner.isOrificeTypeExposed(orificeTypeAccessRequired),
						orificeFree = orificeTypeAccessRequired.isFree();
				String orificeName = Util.capitaliseSentence(orificeTypeAccessRequired.getName()),
						accessText = (orificeAccess?"access":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>access</span>"),
						freeText = (orificeFree?"free":"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>free</span>");
				
				if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED) {
					if(orificeAccess && orificeFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Partner's "+ orificeName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+" & "+freeText+"): Partner's "+ orificeName);
					}
					
				} else if(getSexActionType()==SexActionType.PLAYER_REQUIRES_NO_PENETRATION) {
					if(orificeFree) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Partner's "+ orificeName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+freeText+"): Partner's "+ orificeName);
					}
					
				} else {
					if(orificeAccess) {
						SB.append("</br><b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Partner's "+ orificeName);
					} else {
						SB.append("</br><b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b> ("+accessText+"): Partner's "+ orificeName);
					}
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
							+ " (<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>owned</span>): "
							+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
					
				} else {
					SB.append("</br>"
							+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
							+ " (<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>not owned</span>): "
							+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
				}
			}
		}
		
		if(corruptionBypass!=null) {
			if(isCorruptionWithinRange()) {
				SB.append("</br>"
						+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
						+ " (<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>within range</span>): "
						+ Util.capitaliseSentence(corruptionBypass.getName()));
			} else {
				SB.append("</br>"
						+"<span style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>Associated Corruption</span>"
						+ " (<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>out of range</span>): "
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
		return corruptionBypass != null && corruptionBypass.getMinimumValue() <= Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION);
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
			if(!Main.game.getPlayer().hasPerk(p)) {
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
				return Femininity.valueOf(Main.game.getPlayer().getFemininity()) == Femininity.ANDROGYNOUS;
			case FEMININE:
				return Main.game.getPlayer().getFemininity() >= Femininity.FEMININE.getMinimumFemininity();
			case FEMININE_STRONG:
				return Main.game.getPlayer().getFemininity() >= Femininity.FEMININE_STRONG.getMinimumFemininity();
			case MASCULINE:
				return Main.game.getPlayer().getFemininity() <= Femininity.MASCULINE.getMaximumFemininity();
			case MASCULINE_STRONG:
				return Main.game.getPlayer().getFemininity() <= Femininity.MASCULINE_STRONG.getMaximumFemininity();
			default:
				return true;
		}
	}
	
	public boolean isRequiredRace() {
		return (raceRequired==null
					? true
					: Main.game.getPlayer().getRace() == raceRequired);
	}
	
	public boolean isPenetrationTypeAvailable() {
		if(penetrationTypeAccessRequired==null)
			return true;
		
		// Don't care if exposed or not:
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION:
					if(penetrationTypeAccessRequired.isFree())
						return true;
					break;
				case PLAYER_REQUIRES_NO_PENETRATION:
					if(penetrationTypeAccessRequired.isFree())
						return true;
					break;
				default:
					break;
			}
		}
		
		// Check to make sure penetrationType is exposed:
		if(penetrationTypeAccessRequired.isPlayer()) {
			if(!Main.game.getPlayer().isPenetrationTypeExposed(penetrationTypeAccessRequired)) {
				return false;
			}
		} else {
			if(!partner.isPenetrationTypeExposed(penetrationTypeAccessRequired)) {
				return false;
			}
		}
		
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED:
					if(!penetrationTypeAccessRequired.isFree())
						return false;
					break;
				case PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED:
					if(!penetrationTypeAccessRequired.isFree())
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
				case PARTNER_REQUIRES_NO_PENETRATION:
					if(orificeTypeAccessRequired.isFree())
						return true;
					break;
				case PLAYER_REQUIRES_NO_PENETRATION:
					if(orificeTypeAccessRequired.isFree())
						return true;
					break;
				default:
					break;
			}
		}

		// Check to make sure orifice is exposed:
		if(orificeTypeAccessRequired.isPlayer()) {
			if(!Main.game.getPlayer().isOrificeTypeExposed(orificeTypeAccessRequired)) {
				return false;
			}
		} else {
			if(!partner.isOrificeTypeExposed(orificeTypeAccessRequired)) {
				return false;
			}
		}
		
		if(getSexActionType()!=null) {
			switch(getSexActionType()){
				case PARTNER_REQUIRES_NO_PENETRATION_AND_EXPOSED:
					if(!orificeTypeAccessRequired.isFree())
						return false;
					break;
				case PLAYER_REQUIRES_NO_PENETRATION_AND_EXPOSED:
					if(!orificeTypeAccessRequired.isFree())
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
