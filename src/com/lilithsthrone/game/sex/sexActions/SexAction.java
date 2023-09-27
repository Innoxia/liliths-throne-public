package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.4.1
 * @author Innoxia
 */
public abstract class SexAction implements SexActionInterface {

	protected SexActionType sexActionType;
	
	protected ArousalIncrease selfArousalGain;
	protected ArousalIncrease targetArousalGain;
	
	protected CorruptionLevel minimumCorruptionNeeded;
	
	/**A map of all the SexAreaInterfaces that are interacting with one another in this SexAction.
	 *  The keys are representing ownership of the character who performs the action, while the values are owned by the character upon which this is being performed.*/
	protected Map<SexAreaInterface, SexAreaInterface> sexAreaInteractions;

	protected SexParticipantType participantType;
	protected SexPace associatedSexPace;
	protected Map<GameCharacter, Set<AbstractFetish>> characterFetishes;
	protected Map<GameCharacter, Set<AbstractFetish>> characterFetishesForPartner;
	
	// External file variables:

	protected boolean mod = false;
	protected boolean fromExternalFile = false;
	protected String author = "Innoxia";
	
	public SexAction(SexActionInterface sexActionToCopy) {
		this.sexActionType = sexActionToCopy.getActionType();
		this.selfArousalGain = sexActionToCopy.getArousalGainSelf();
		this.targetArousalGain = sexActionToCopy.getArousalGainTarget();
		this.minimumCorruptionNeeded = sexActionToCopy.getCorruptionNeeded();
		this.sexAreaInteractions = sexActionToCopy.getSexAreaInteractions();
		this.participantType = sexActionToCopy.getParticipantType();
		this.associatedSexPace = sexActionToCopy.getSexPace();
	}
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease selfArousalGain,
			ArousalIncrease targetArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			Map<SexAreaInterface, SexAreaInterface> sexAreaInteractions,
			SexParticipantType participantType) {
		this(sexActionType,
				selfArousalGain,
				targetArousalGain,
				minimumCorruptionNeeded,
				sexAreaInteractions,
				participantType,
				null);
	}
	
	public SexAction(
			SexActionType sexActionType,
			ArousalIncrease selfArousalGain,
			ArousalIncrease targetArousalGain,
			CorruptionLevel minimumCorruptionNeeded,
			Map<SexAreaInterface, SexAreaInterface> sexAreaInteractions,
			SexParticipantType participantType,
			SexPace associatedSexPace) {
		
		this.sexActionType = sexActionType;
		this.selfArousalGain = selfArousalGain;
		this.targetArousalGain = targetArousalGain;
		this.minimumCorruptionNeeded = minimumCorruptionNeeded;
		
		if(sexAreaInteractions==null) {
			this.sexAreaInteractions = new HashMap<>();
		} else {
			this.sexAreaInteractions = sexAreaInteractions;
		}
		
		this.participantType = participantType;
		this.associatedSexPace = associatedSexPace;
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public SexPace getSexPace(){
		return associatedSexPace;
	}
	
	public Map<SexAreaInterface, SexAreaInterface> getSexAreaInteractions() {
		return sexAreaInteractions;
	}

	@Override
	public SexParticipantType getParticipantType() {
		return participantType;
	}
	
	@Override
	public SexActionType getActionType(){
		return sexActionType;
	}

	@Override
	public ArousalIncrease getArousalGainSelf() {
		if(Main.game.isInSex()) {
			if(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
					&& this.getActionType()!=SexActionType.PREPARE_FOR_PARTNER_ORGASM
					&& this.getActionType()!=SexActionType.SPECIAL) {
				if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
					return ArousalIncrease.FOUR_HIGH;
					
				} else {
					// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
					for(SexAreaInterface sArea : this.getSexAreaInteractions().keySet()) {
						if((sArea.isOrifice() && ((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated()>1)
								|| (sArea.isPenetration() && ((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating()>1)) {
							return ArousalIncrease.TWO_LOW;
						}
					}
					return ArousalIncrease.ZERO_NONE;
				}
			}
		}
		
		return selfArousalGain;
	}

	@Override
	public ArousalIncrease getArousalGainTarget() {
		if(Main.game.isInSex()) {
			if(this.isSadisticAction() && !Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()) {
				return ArousalIncrease.ZERO_NONE;
			}
			
			if(!Main.sex.isMasturbation()) {
				if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING
						&& this.getActionType()!=SexActionType.PREPARE_FOR_PARTNER_ORGASM
						&& this.getActionType()!=SexActionType.SPECIAL) {
					if(Main.sex.getCharacterTargetedForSexAction(this).hasFetish(Fetish.FETISH_NON_CON_SUB)) {
						return ArousalIncrease.FOUR_HIGH;
						
					} else {
						// If it's an erogenous zone, they gain arousal. If not, arousal gain is 0.
						for(SexAreaInterface sArea : this.getSexAreaInteractions().values()) {
							if(sArea!=null
									&& ((sArea.isOrifice() && ((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated()>1)
											|| (sArea.isPenetration() && ((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating()>1))) {
								return ArousalIncrease.TWO_LOW;
							}
						}
						return ArousalIncrease.ZERO_NONE;
					}
				}
			}
		}
		
		return targetArousalGain;
	}

	@Override
	public CorruptionLevel getCorruptionNeeded(){
		return minimumCorruptionNeeded;
	}

	@Override
	public abstract String getActionTitle();

	@Override
	public abstract String getDescription();

	private static String formatFlavour(String input) {
		return "<p style='text-align:center; margin:0; padding:0;'><i>"
				+ input
			+ "</i></p>";
	}
	
	@Override
	public String getFluidFlavourDescription(GameCharacter performing, GameCharacter receiving) {
		if((this.getPerformingCharacterAreas().contains(SexAreaPenetration.TONGUE) || this.getPerformingCharacterAreas().contains(SexAreaOrifice.MOUTH)) && performing.isPlayer()) {
			
			if(this.getTargetedCharacterAreas().contains(SexAreaOrifice.VAGINA)) {
				FluidFlavour flavour = receiving.getGirlcumFlavour();
				if(flavour!=FluidFlavour.GIRL_CUM && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(receiving,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.pussy] tastes like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
				
			} else if(this.getTargetedCharacterAreas().contains(SexAreaPenetration.PENIS)) {
				FluidFlavour flavour = receiving.getCumFlavour();
				if(flavour!=FluidFlavour.CUM && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(receiving,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.cock] tastes like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
				
			} else if(this.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE)) {
				FluidFlavour flavour = receiving.getMilkFlavour();
				if(flavour!=FluidFlavour.MILK && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(receiving,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.nipples] taste like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
				
			} else if(this.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE_CROTCH)) {
				FluidFlavour flavour = receiving.getMilkCrotchFlavour();
				if(flavour!=FluidFlavour.MILK && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(receiving,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.crotchNipples] taste like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
			}
			
		} else if((this.getTargetedCharacterAreas().contains(SexAreaPenetration.TONGUE) || this.getTargetedCharacterAreas().contains(SexAreaOrifice.MOUTH)) && receiving.isPlayer()) {
			
			if(this.getPerformingCharacterAreas().contains(SexAreaOrifice.VAGINA)) {
				FluidFlavour flavour = performing.getGirlcumFlavour();
				if(flavour!=FluidFlavour.GIRL_CUM && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(performing,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.pussy] tastes like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
				
			} else if(this.getPerformingCharacterAreas().contains(SexAreaPenetration.PENIS)) {
				FluidFlavour flavour = performing.getCumFlavour();
				if(flavour!=FluidFlavour.CUM && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(performing,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.cock] tastes like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
				
			} else if(this.getPerformingCharacterAreas().contains(SexAreaOrifice.NIPPLE)) {
				FluidFlavour flavour = performing.getMilkFlavour();
				if(flavour!=FluidFlavour.MILK && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(performing,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.nipples] taste like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
				
			} else if(this.getPerformingCharacterAreas().contains(SexAreaOrifice.NIPPLE_CROTCH)) {
				FluidFlavour flavour = performing.getMilkCrotchFlavour();
				if(flavour!=FluidFlavour.MILK && flavour!=FluidFlavour.FLAVOURLESS) {
					return formatFlavour(UtilText.parse(performing,
										UtilText.returnStringAtRandom(
												"[npc.NamePos] [npc.crotchNipples] taste like <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span>!",
												"The taste of <span style='color:"+flavour.getColour().toWebHexString()+";'> "+flavour.getName()+"</span> fills your mouth!")));
				}
			}
		}
		
		return "";
	}
	
	@Override
	public List<AbstractFetish> getFetishesForTargetedPartner(GameCharacter characterPerformingAction) {
		return getFetishesForEitherPartner(characterPerformingAction, false);
	}

	@Override
	public List<AbstractFetish> getFetishes(GameCharacter characterPerformingAction) {
		return getFetishesForEitherPartner(characterPerformingAction, true);
	}
	
	/**
	 * To be overridden to add extra fetishes on top of the automatically-generated ones in getFetishes() and getFetishesForTargetedPartner().
	 */
	public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
		return null;
	}
	
	public List<AbstractFetish> getFetishesForEitherPartner(GameCharacter characterPerformingAction, boolean characterPerformingActionFetishes) {
//		if(characterFetishes==null || characterFetishes.get(characterPerformingAction)==null) {
			GameCharacter characterTarget = Main.sex.getTargetedPartner(characterPerformingAction);
			
			characterFetishes = new HashMap<>();
			characterFetishesForPartner = new HashMap<>();
			
			characterFetishes.putIfAbsent(characterPerformingAction, new HashSet<>());
			characterFetishesForPartner.putIfAbsent(characterPerformingAction, new HashSet<>());
			
			if(getExtraFetishes(characterPerformingAction)!=null) {
				for(AbstractFetish f : getExtraFetishes(characterPerformingAction)) {
					characterFetishes.get(characterPerformingAction).add(f);
				}
			}
			if(getExtraFetishes(characterTarget)!=null) {
				for(AbstractFetish f : getExtraFetishes(characterTarget)) {
					characterFetishesForPartner.get(characterPerformingAction).add(f);
				}
			}
			
//			if(this.getParticipantType()==SexParticipantType.SELF && !characterPerformingActionFetishes) { // If this is a self action, do not apply fetishes to other partner.
//				return new ArrayList<>(characterFetishesForPartner.get(characterPerformingAction));
//			}
			
			if(this.getParticipantType()!=SexParticipantType.SELF && characterPerformingAction.isRelatedTo(characterTarget)) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_INCEST);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_INCEST);
			}
			
			if(Main.sex.isPublicSex()) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_EXHIBITIONIST);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_EXHIBITIONIST);
			}
			
			if(this.getSexPace()!=null) {
				switch(this.getSexPace()) {
					case DOM_GENTLE:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_NORMAL:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						break;
					case DOM_ROUGH:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SADIST);
						if(this.getParticipantType()==SexParticipantType.SELF) {
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_MASOCHIST);
						}
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_MASOCHIST);
						break;
					case SUB_EAGER:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						break;
					case SUB_NORMAL:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						break;
					case SUB_RESISTING:
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_SUBMISSIVE);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_DOMINANT);
						characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_NON_CON_SUB);
						characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_NON_CON_DOM);
						break;
				}
			}
			
			List<CoverableArea> cummedOnList = null;
			try { // Wrap in try/catch block as some sex actions may make calls to ongoing actions that aren't ongoing yet
				cummedOnList = this.getAreasCummedOn(characterPerformingAction, characterTarget);
			} catch(Exception ex) {
			}
			
			if(cummedOnList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				for(CoverableArea area : cummedOnList) {
					switch(area) {
						case NONE:
						case TAIL:
							break;
						case ARMPITS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_RECEIVING);
							break;
						case ANUS: case ASS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
							break;
						case BACK:
							break;
						case BREASTS: case NIPPLES:
							if(characterTarget.getBreastRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							break;
						case BREASTS_CROTCH: case NIPPLES_CROTCH:
							if(characterTarget.getBreastCrotchRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							break;
						case HAIR:
							break;
						case EYES:
							break;
						case HANDS:
							break;
						case FEET:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_FOOT_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_FOOT_RECEIVING);
							break;
						case LEGS: case THIGHS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
							break;
						case MOUND:
							break;
						case MOUTH:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
							break;
						case PENIS: case TESTICLES:
							break;
						case STOMACH:
							break;
						case VAGINA:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							break;
					}
				}
			}
			
			try { // Wrap in try/catch block as some sex actions may make calls to ongoing actions that aren't ongoing yet
				cummedOnList = this.getAreasCummedOn(characterTarget, characterPerformingAction);
			} catch(Exception ex) {
			}
			if(cummedOnList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				
				for(CoverableArea area : cummedOnList) {
					switch(area) {
						case NONE:
						case TAIL:
							break;
						case ARMPITS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_GIVING);
							break;
						case ANUS: case ASS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
							break;
						case BACK:
							break;
						case BREASTS: case NIPPLES:
							if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case BREASTS_CROTCH: case NIPPLES_CROTCH:
							if(characterPerformingAction.getBreastCrotchRawMilkStorageValue()>0) {
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
							}
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
							break;
						case HAIR:
							break;
						case EYES:
							break;
						case HANDS:
							break;
						case FEET:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_FOOT_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_FOOT_GIVING);
							break;
						case LEGS: case THIGHS:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
							break;
						case MOUND:
							break;
						case MOUTH:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
							break;
						case PENIS: case TESTICLES:
							break;
						case STOMACH:
							break;
						case VAGINA:
							characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
							characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
							break;
					}
				}
			}
			
			List<SexAreaInterface> cummedInList = this.getAreasCummedIn(characterPerformingAction, characterTarget);
			if(cummedInList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				
				for(SexAreaInterface area : cummedInList) {
					if(area.isOrifice()) {
						switch((SexAreaOrifice)area) {
							case ANUS: case ASS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
								break;
							case BREAST: case NIPPLE:
								if(characterTarget.getBreastRawMilkStorageValue()>0) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
									characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
								}
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
								break;
							case BREAST_CROTCH: case NIPPLE_CROTCH:
								if(characterTarget.getBreastCrotchRawMilkStorageValue()>0) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
									characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
								}
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
								break;
							case THIGHS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
								break;
							case MOUTH:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
								break;
							case VAGINA:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								if(this.getParticipantType()==SexParticipantType.SELF) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								}
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								break;
							case ARMPITS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_RECEIVING);
								break;
							case SPINNERET:
							case URETHRA_PENIS:
							case URETHRA_VAGINA:
								break;
						}
					} else {
						switch((SexAreaPenetration)area) {
							case CLIT:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								if(this.getParticipantType()==SexParticipantType.SELF) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								}
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								break;
							case FINGER:
								break;
							case FOOT:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_FOOT_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_FOOT_GIVING);
								break;
							case PENIS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PENIS_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_PENIS_GIVING);
								break;
							case TAIL:
								break;
							case TENTACLE:
								break;
							case TONGUE:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
								break;
						}
					}
				}
			}
			
			cummedInList = this.getAreasCummedIn(characterTarget, characterPerformingAction);
			if(cummedInList != null) {
				characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_CUM_ADDICT);
				characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_CUM_STUD);
				
				for(SexAreaInterface area : cummedInList) {
					if(area.isOrifice()) {
						switch((SexAreaOrifice)area) {
							case ANUS: case ASS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ANAL_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ANAL_GIVING);
								break;
							case BREAST: case NIPPLE:
								if(characterPerformingAction.getBreastRawMilkStorageValue()>0) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
									characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
								}
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case BREAST_CROTCH: case NIPPLE_CROTCH:
								if(characterPerformingAction.getBreastCrotchRawMilkStorageValue()>0) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_SELF);
									characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LACTATION_OTHERS);
								}
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_SELF);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_BREASTS_OTHERS);
								break;
							case THIGHS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_STRUTTER);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_LEG_LOVER);
								break;
							case MOUTH:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
								break;
							case VAGINA:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								if(this.getParticipantType()==SexParticipantType.SELF) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								}
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								break;
							case ARMPITS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ARMPIT_GIVING);
								break;
							case SPINNERET:
							case URETHRA_PENIS:
							case URETHRA_VAGINA:
								break;
						}
					} else {
						switch((SexAreaPenetration)area) {
							case CLIT:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_RECEIVING);
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PREGNANCY);
								if(this.getParticipantType()==SexParticipantType.SELF) {
									characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								}
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_VAGINAL_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_IMPREGNATION);
								break;
							case FINGER:
								break;
							case FOOT:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_FOOT_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_FOOT_RECEIVING);
								break;
							case PENIS:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_PENIS_GIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_PENIS_RECEIVING);
								break;
							case TAIL:
								break;
							case TENTACLE:
								break;
							case TONGUE:
								characterFetishes.get(characterPerformingAction).add(Fetish.FETISH_ORAL_RECEIVING);
								characterFetishesForPartner.get(characterPerformingAction).add(Fetish.FETISH_ORAL_GIVING);
								break;
						}
					}
				}
			}
			
			for(Entry<SexAreaInterface, SexAreaInterface> entry : this.getSexAreaInteractions().entrySet()) {
				if(this.getActionType()!=SexActionType.STOP_ONGOING) {
					if(characterPerformingActionFetishes) {
						if(this.getParticipantType()==SexParticipantType.SELF) {
							characterFetishes.get(characterPerformingAction).addAll(
									getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), characterPerformingAction, entry.getValue(), !characterPerformingActionFetishes));
						}
						characterFetishes.get(characterPerformingAction).addAll(
								getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), Main.sex.getTargetedPartner(characterPerformingAction), entry.getValue(), characterPerformingActionFetishes));
						
					} else {
						if(this.getParticipantType()==SexParticipantType.SELF) {
							characterFetishesForPartner.get(characterPerformingAction).addAll(
									getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), characterPerformingAction, entry.getValue(), !characterPerformingActionFetishes));
						}
						characterFetishesForPartner.get(characterPerformingAction).addAll(
								getFetishesFromPenetrationAndOrificeTypes(characterPerformingAction, entry.getKey(), Main.sex.getTargetedPartner(characterPerformingAction), entry.getValue(), characterPerformingActionFetishes));
					}
				}
			}
			
			characterFetishes.get(characterPerformingAction).removeIf(f -> !characterTarget.hasVagina() && (f==Fetish.FETISH_VAGINAL_GIVING || f==Fetish.FETISH_IMPREGNATION));
			characterFetishesForPartner.get(characterPerformingAction).removeIf(f -> !characterTarget.hasVagina() && (f==Fetish.FETISH_VAGINAL_GIVING || f==Fetish.FETISH_IMPREGNATION));
//		}
		
		
		if(characterPerformingActionFetishes) {
			return new ArrayList<>(characterFetishes.get(characterPerformingAction));
			
		} else {
			return new ArrayList<>(characterFetishesForPartner.get(characterPerformingAction));
		}
	}
}
