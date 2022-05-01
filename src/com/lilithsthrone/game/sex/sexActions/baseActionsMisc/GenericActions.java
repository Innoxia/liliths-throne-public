package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.*;
import java.util.Map.Entry;

/**
 * @since 0.1.79
 * @version 0.3.6.2
 * @author Innoxia
 */
public class GenericActions {
	
	private static String quickSexDescription = "";

	private static SexType getPlayerOngoingMainSex(GameCharacter partner) {
		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry1 : Main.sex.getOngoingActionsMap(Main.game.getPlayer()).entrySet()) {
			// If penetrating an internal orifice, prefer that:
			if(entry1.getValue().containsKey(partner)
					&& entry1.getKey().isPenetration()
					&& ((SexAreaPenetration)entry1.getKey()).isTakesVirginity()
					&& entry1.getValue().get(partner).stream().anyMatch(orifice -> orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice())) {
				return new SexType(SexParticipantType.NORMAL, entry1.getKey(), entry1.getValue().get(partner).stream().filter(orifice -> orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice()).findFirst().get());

			// If being penetrated, prefer that:
			} else if(entry1.getValue().containsKey(partner)
					&& entry1.getKey().isOrifice()
					&& ((SexAreaOrifice)entry1.getKey()).isInternalOrifice()
					&& entry1.getValue().get(partner).stream().anyMatch(penetration -> penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity())) {
				return new SexType(SexParticipantType.NORMAL, entry1.getKey(), entry1.getValue().get(partner).stream().filter(penetration -> penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity()).findFirst().get());
			}
		}
		return null;
	}
	
	private static SexType getForeplayPreference(GameCharacter dom, GameCharacter sub) {
		if(dom.isPlayer()) {
			SexType playerMainSexType = getPlayerOngoingMainSex(sub);
			if(playerMainSexType!=null) {
				return playerMainSexType;	
			}
		}
		
		SexType preference = Main.sex.getForeplayPreference(dom, sub);
		List<SexAreaInterface> domBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(dom);
		if(domBanned==null) {
			domBanned = new ArrayList<>();
		}
		List<SexAreaInterface> subBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(sub);
		if(subBanned==null) {
			subBanned = new ArrayList<>();
		}
		
		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom)!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		if(preference!=null) {
			if(domBanned.contains(preference.getPerformingSexArea())
					|| subBanned.contains(preference.getTargetedSexArea())
					|| !dom.isAbleToAccessCoverableArea(preference.getPerformingSexArea().getRelatedCoverableArea(dom), true)
					|| !sub.isAbleToAccessCoverableArea(preference.getTargetedSexArea().getRelatedCoverableArea(sub), true)) {
				preference = null;
			}
		}
		if(preference==null && dom.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !domBanned.contains(SexAreaOrifice.MOUTH)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
				
			} else if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
			}
		}
		
		if(sexTypesBanned.contains(preference)) {
			preference = null;
		}
		
		if(preference==null) {
			preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH); // At least give them something...
		}
		return preference;
	}
	
	private static SexType getMainSexPreference(GameCharacter dom, GameCharacter sub) {
		if(dom.isPlayer()) {
			SexType playerMainSexType = getPlayerOngoingMainSex(sub);
			if(playerMainSexType!=null) {
				return playerMainSexType;	
			}
		}
		
		SexType preference = Main.sex.getMainSexPreference(dom, sub);
		List<SexAreaInterface> domBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(dom);
		if(domBanned==null) {
			domBanned = new ArrayList<>();
		}
		List<SexAreaInterface> subBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(sub);
		if(subBanned==null) {
			subBanned = new ArrayList<>();
		}

		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom)!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		if(preference!=null) {
			if(domBanned.contains(preference.getPerformingSexArea())
					|| subBanned.contains(preference.getTargetedSexArea())
					|| !dom.isAbleToAccessCoverableArea(preference.getPerformingSexArea().getRelatedCoverableArea(dom), true)
					|| !sub.isAbleToAccessCoverableArea(preference.getTargetedSexArea().getRelatedCoverableArea(sub), true)) {
				preference = null;
			}
		}
		if(preference==null && dom.hasPenis() && !domBanned.contains(SexAreaPenetration.PENIS) && dom.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
				
			} else if(Main.game.isAnalContentEnabled() && !subBanned.contains(SexAreaOrifice.ANUS) && sub.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			}
		}
		if(preference==null && dom.hasVagina() && !domBanned.contains(SexAreaOrifice.VAGINA) && dom.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
				
			} else if(sub.hasTail() && !subBanned.contains(SexAreaPenetration.TAIL) && sub.isTailSuitableForPenetration()){
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL);
				
			} else {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
			}
		}
		if(preference==null && dom.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !domBanned.contains(SexAreaOrifice.MOUTH)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
				
			} else if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
				
			} else if(sub.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !subBanned.contains(SexAreaOrifice.MOUTH)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
			}
		}
		
		if(sexTypesBanned.contains(preference)) {
			preference = null;
		}
		
		if(preference==null) {
			preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH); // At least give them something...
		}
		
		return preference;
	}
	
	private static boolean preventCreampie(SexType sexType, GameCharacter dom, GameCharacter sub) {
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))
				&& ((Main.sex.isConsensual() && sub.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(dom)!=OrgasmBehaviour.CREAMPIE)
						|| Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(dom)==OrgasmBehaviour.PULL_OUT)) {
			return true;
		}
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))
				&& ((dom.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(sub)!=OrgasmBehaviour.CREAMPIE)
						|| Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(sub)==OrgasmBehaviour.PULL_OUT)) {
			return true;
		}
		return false;
	}

	private static int calculateNeededOrgasms (final GameCharacter dom, final GameCharacter sub, final boolean consenual, final boolean extracting) {
		final int currentDomOrgasms = Main.sex.getNumberOfOrgasms(dom);
		final int currentSubOrgasms = Main.sex.getNumberOfOrgasms(sub);

		int orgasmsNeeded = 0;
		if (consenual) {
			// Make sure to satisfy subs in a consensual encounter.
			orgasmsNeeded = Math.max((sub.getOrgasmsBeforeSatisfied() - currentSubOrgasms), (dom.getOrgasmsBeforeSatisfied() - currentDomOrgasms));

		} else {
			// Only the Doms matter.
			orgasmsNeeded = dom.getOrgasmsBeforeSatisfied() - currentDomOrgasms;
		}

		// In the case of extracting sex, we pull as much essence as we can.
		if (extracting) {
			orgasmsNeeded = Math.max(
					Math.max(6 - currentDomOrgasms, 6 - currentSubOrgasms),
					orgasmsNeeded);
		}

		return orgasmsNeeded;
	}
	
	private static String generateQuickSexDescription(final boolean extracting) {

		StringBuilder sb = new StringBuilder();
		
		HashMap<GameCharacter, GameCharacter> targetedCharacters = new HashMap<>();
		List<GameCharacter> availableSubs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		boolean allDomsAssigned = false;
		boolean allSubsAssigned = false;
		List<GameCharacter> domsNotSatisfied = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		
		// All characters in sex should know of each others' parts:
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			for(GameCharacter partner : Main.sex.getAllParticipants()) {
				if(!character.equals(partner)) {
					partner.setAllAreasKnownByCharacter(character, true);
					character.setAllAreasKnownByCharacter(partner, true);
				}
			}
		}
		
		while(!allSubsAssigned) {
			for(GameCharacter dom : domsNotSatisfied) {
				GameCharacter target = dom.isPlayer()?Main.sex.getTargetedPartner(dom):((NPC) dom).getPreferredSexTarget();
				if(target==null || (dom.isPlayer() && allDomsAssigned && Main.sex.isConsensual())) { // If second time through loop, and equal control, give player another target if available
					if(availableSubs.isEmpty()) { // If run out of subs, re-populate sub list.
						availableSubs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
					}
					float topWeight = -10_000;
					for(GameCharacter sub : availableSubs) {
						float weight = dom.isAttractedTo(sub)?dom.getAffection(sub):-1_000;
						if(weight>topWeight) {
							topWeight = weight;
							target = sub;
						}
					}
				}
				availableSubs.remove(target);
				if(availableSubs.isEmpty()) {
					allSubsAssigned = true;
				}
				targetedCharacters.put(dom, target);
				if(allDomsAssigned && allSubsAssigned) { // If this is the second+ time going through the loop, break as soon as all subs are accounted for
					break;
				}
			}
			allDomsAssigned = true;
			
			// Apply all generic sex effects:
			for(Entry<GameCharacter, GameCharacter> entry : targetedCharacters.entrySet()) {
				GameCharacter dom = entry.getKey();
				GameCharacter sub = entry.getValue();
				sb.append(UtilText.parse(dom, sub,
						"<p style='text-align:center;'>"
//							+ "<b>[style.boldSexDom([npc.Name])] dominantly "+(Main.sex.isConsensual()?"having sex with":"fucking")+" [style.boldSexSub([npc2.name])]</b>"
							+ "<b><b style='color:"+dom.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"
								+ " dominantly "+(Main.sex.isConsensual()?"having sex with ":"fucking ")
								+"<b style='color:"+sub.getFemininity().getColour().toWebHexString()+";'>[npc2.name]</b></b>"
						+ "</p>"));
				
				boolean preventCreampie = false;
				
				// Foreplay:
				SexType preference;
				if(Main.sex.isInForeplay(dom)) {
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						while(clothingValue!=null) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						}
					}
					preference = getForeplayPreference(dom, sub);
					preventCreampie = preventCreampie(preference, dom, sub);
					sb.append("<p style='margin:0; padding:0; text-align:center;'>");
					sb.append("[style.boldPurpleLight(Foreplay)] ([style.colourSexDom("+Util.capitaliseSentence(preference.getPerformingSexArea().getName(dom, true))+")]-[style.colourSexSub("+preference.getTargetedSexArea().getName(sub, true)+")]): ");
					sb.append(dom.calculateGenericSexEffects(true, false, sub, preference, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null)));
					sb.append("</p>");
				}
				
				// Main sex:
				preference = getMainSexPreference(dom, sub);
				preventCreampie = preventCreampie(preference, dom, sub);
				// If equal sex control, dom should satisfy subs:
				int orgamsNeeded = calculateNeededOrgasms(dom, sub, Main.sex.isConsensual(), extracting);
				for(int i=0; i<orgamsNeeded; i++) {
					// Regenerate cum by 5 minutes' worth of cum, so that there's cum for the next orgasm:
					// Moved before orgasm so the first quick sex orgasm isn't dry
					dom.incrementPenisStoredCum((5*60) * dom.getCumRegenerationPerSecond());
					sub.incrementPenisStoredCum((5*60) * sub.getCumRegenerationPerSecond());

					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						while(clothingValue!=null) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						}
					}
					sb.append("<p style='margin:0; padding:0; text-align:center;'>");
					sb.append("[style.boldPurple(Sex)] ([style.colourSexDom("+Util.capitaliseSentence(preference.getPerformingSexArea().getName(dom, true))+")]-[style.colourSexSub("+preference.getTargetedSexArea().getName(sub, true)+")]): ");
					sb.append(dom.calculateGenericSexEffects(true, true, sub, preference, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null))); // This increments orgasms
					sb.append("</p>");
					
					if(orgamsNeeded>1) {
						dom.generateSexChoices(false, sub);
						preference = getMainSexPreference(dom, sub);
						preventCreampie = preventCreampie(preference, dom, sub);
					}
				}
			}
			
			for(GameCharacter dom : new ArrayList<>(domsNotSatisfied)) {
				if(Main.sex.getNumberOfOrgasms(dom)>=dom.getOrgasmsBeforeSatisfied()) {
					domsNotSatisfied.remove(dom);
				}
			}
			
			if(!Main.sex.isConsensual()) { // If the doms don't care about satisfying all the subs, treat all subs assigned as being true
				if(domsNotSatisfied.isEmpty()) {
					allSubsAssigned = true;
				}
			} else {
				if(domsNotSatisfied.isEmpty() && !allSubsAssigned) {
					domsNotSatisfied = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
				}
			}
		}
		
		return sb.toString();
	}
	
	public static final SexAction PLAYER_SKIP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.UNIQUE_MAX; // So that this action is available with the 'Cocooned!' action.
//			}
//			return super.getPriority();
//		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_ORANGE;
		}
		@Override
		public String getActionTitle() {
			return "Quick sex";
		}
		@Override
		public String getActionDescription() {
			return "Skips this sex scene, but still [style.boldSex(applies all applicable effects)] as though the scene had taken place, based on your partner's preferences."
					+ " A description of the resulting sex scene will be displayed before the scene ends.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isAbleToSkipSexScene()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String applyEndEffects(){
			quickSexDescription = "";
			return "";
		}
		@Override
		public String getDescription() {
			if(quickSexDescription.isEmpty()) {
				quickSexDescription = generateQuickSexDescription(false);
			}
			return quickSexDescription;
		}
		@Override
		public boolean endsSex() {
			return true;
		}

		@Override
		public boolean isSkip() {
			return true;
		}

		@Override
		public int getSecondsPassed(final Sex sex) {
			int seconds = 0;
			// 3 minutes per character orgasm (always limited to 1 orgasm for unequal control subs) if 'Quick sex' is used:
			for(GameCharacter participant : sex.getAllParticipants(true)) {
				if(Main.sex.isDom(participant) || Main.sex.isSubHasEqualControl()) {
					seconds+=Math.max(1, participant.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(participant))*3*60;
				} else {
					seconds+=3*60;
				}
			}
			return seconds;
		}
	};

	public static final SexAction PLAYER_EXTRACTING_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL
	) {
		private String extractingSexDescription = "";
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_PINK;
		}
		@Override
		public String getActionTitle() {
			return "Extracting sex";
		}
		@Override
		public String getActionDescription() {
			return "Skips this sex scene, but still [style.boldSex(applies all applicable effects)] as though the scene had taken place, attempting to extract the maximum essence possible."
					+ " A description of the resulting sex scene will be displayed before the scene ends.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			PlayerCharacter player = Main.game.getPlayer();

			return Main.sex.getInitialSexManager().isAbleToSkipSexScene()
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isSexLeader(player)
					&& !player.isAbleToOrgasm();
		}

		@Override
		public String applyEndEffects() {

			extractingSexDescription = "";
			return "";
		}
		@Override
		public String getDescription() {
			if(extractingSexDescription.isEmpty()) {
				extractingSexDescription = generateQuickSexDescription(true);
			}
			return extractingSexDescription;
		}
		@Override
		public boolean endsSex() {
			return true;
		}

		@Override
		public int getSecondsPassed(Sex sex) {
			int seconds = 0;
			// 3 minutes per character orgasm (always limited to 1 orgasm for unequal control subs) if 'Quick sex' is used:
			for(GameCharacter participant : sex.getAllParticipants(true)) {
				// Spectator orgams don't count.
				if (sex.isSpectator(participant)) {
					continue;
				}

				seconds += Math.max(1, Main.sex.getNumberOfOrgasms(participant))*3*60;
			}

			return super.getSecondsPassed(sex);
		}

		@Override
		public boolean isSkip() {
			return true;
		}
	};
	
	public static final SexAction GENERIC_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && !Main.sex.isConsensual());
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [npc2.name].";
		}

		@Override
		public String getDescription() {
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], and [npc.verb(kick)] [npc2.name] as [npc.she] desperately [npc.verb(try)] to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] easily [npc2.verb(keep)] [npc.herHim] pinned back against the wall.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out of [npc2.her] grasp,"
								+ " but [npc2.her] grip is too strong for [npc.herHim], and [npc2.name] easily [npc2.verb(keep)] [npc.herHim] pushed back against the wall.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(push)] [npc.herHim] back against the wall.");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] [npc.verb(try)] to crawl away from [npc2.name],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] [npc.her] [npc.hips] and [npc2.verb(pull)] [npc.her] [npc.ass] back into [npc2.her] groin.",
						
						"Trying to crawl away from [npc2.name] on all fours, [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc2.she] [npc2.verb(grasp)] [npc.her] [npc.hips], before pulling [npc.herHim] back into position.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(try)] to crawl away from [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(take)] hold of [npc.her] [npc.hips] and [npc2.verb(pull)] [npc.herHim] back into [npc2.herHim].");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], and [npc.verb(kick)] [npc2.name] as [npc.she] desperately [npc.verb(try)] to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] easily [npc2.verb(keep)] [npc.herHim] pinned up against the wall.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out of [npc2.her] grasp,"
								+ " but [npc2.her] grip is too strong for [npc.herHim], and [npc2.she] easily [npc2.verb(keep)] [npc.herHim] pushed up against the wall.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(push)] [npc.herHim] up against the wall.");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to push [npc2.namePos] groin away from [npc.her] [npc.face],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] hold of [npc.her] head and [npc2.verb(pull)] [npc.herHim] back into [npc2.her] crotch.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to pull [npc.her] [npc.face] away from [npc2.her] groin,"
								+ " but [npc2.namePos] grasp on [npc.her] head is too strong, and [npc2.she] quickly [npc2.verb(force)] [npc.herHim] back into position.",
								
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(pull)] [npc.her] [npc.face] back into [npc2.her] groin.");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to push [npc2.name] off of [npc.herHim] as [npc.she] desperately [npc.verb(try)] to wriggle out from under [npc2.herHim],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] easily [npc2.verb(pin)] [npc.herHim] to the floor.",
						
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out from under [npc2.herHim],"
								+ " but [npc2.name] [npc2.verb(press)] [npc2.her] body down onto [npc.hers], preventing [npc.herHim] from getting away.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(use)] [npc2.her] body to pin [npc.herHim] to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slap)], [npc.verb(hit)], and [npc.verb(kick)] [npc2.name] as [npc.she] desperately [npc.verb(try)] to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] easily [npc2.verb(continue)] restraining [npc.herHim].",
								
						"Struggling against [npc2.name], [npc.name] [npc.verb(let)] out [npc.a_sob+] as [npc.she] weakly [npc.verb(try)] to wriggle out of [npc2.her] grasp.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately [npc.verb(struggle)] against [npc2.herHim], [npc.sobbing] in distress as [npc2.she] easily [npc2.verb(hold)] [npc.herHim] in place.");
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
		}
	};
	
	public static final SexAction PLAYER_SELF_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "Grow cock (self)";
		}

		@Override
		public String getActionDescription() {
			return "Use your transformative powers to grow a cock for yourself. <b>You will automatically transform the grown cock away when sex ends.</b>";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isAbleToSelfTransform()
					&& !Main.game.getPlayer().hasPenis()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {//TODO resisting variations
			if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
				return "Deciding to use your transformative powers to give yourself a thick demonic cock, you start grinning as you [npc.moanVerb],"
						+ " [npc.speech(You're going to love this!)]";
			} else if (Main.game.getPlayer().isYouko()){
				return "Deciding to use your transformative powers to give yourself a thick knotted fox cock, you start grinning as you [npc.moanVerb],"
						+ " [npc.speech(You're going to love this!)]";
			} else {
				return "Deciding to use your transformative powers to give yourself a thick slimy cock, you start grinning as you [npc.moanVerb],"
						+ " [npc.speech(You're going to love this!)]";
			}
		}

		@Override
		public String applyEffectsString() {
			Main.sex.getCharactersGrewCock().add(Main.game.getPlayer());
			
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
				sb.append(Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON));
			} else if (Main.game.getPlayer().isYouko()){
				sb.append(Main.game.getPlayer().setPenisType(PenisType.FOX_MORPH));
			} else {
				sb.append(Main.game.getPlayer().setPenisType(RacialBody.valueOfRace(Main.game.getPlayer().getBody().getFleshSubspecies().getRace()).getPenisType()));
			}
			if(Main.game.getPlayer().getPenisRawCumStorageValue() < 150) {
				sb.append(Main.game.getPlayer().setPenisCumStorage(150));
			}
			Main.game.getPlayer().fillCumToMaxStorage();
			int size = Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isLargeGenitals()?40:20;
			if(Main.game.getPlayer().getPenisRawSizeValue() < size) {
				sb.append(Main.game.getPlayer().setPenisSize(size));
			}
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character instanceof NPC) {
					for(GameCharacter target : Main.sex.getAllParticipants()) {
						if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
							((NPC)character).generateSexChoices(false, target, null);
						}
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction PLAYER_GET_PARTNER_TO_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Grow cock";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				return "Get [npc2.name] to use [npc2.her] demonic self-transformative powers to grow [npc2.herself] a demonic cock.";
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				return "Get [npc2.name] to use [npc2.her] innate self-transformative powers to grow [npc2.herself] a fox cock.";
			} else {
				return "Get [npc2.name] to use [npc2.her] slimy body's self-transformative powers to grow [npc2.herself] a slimy cock.";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isMasturbation()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToSelfTransform()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {//TODO resisting variations
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				return "Grinning down at [npc2.name], you tease, [npc.speech(How about you use your transformative powers to grow a nice thick demonic cock, so that we can have even more fun!)]"
						+ "<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] naked groin, you see a large bump start to form beneath [npc2.her] [npc2.skin]."
									+ " Before you have any time to change your mind, it quickly grows out into a fat demonic cock, and as you stare down at the little wriggling bumps that press out all along its shaft,"
									+ " a bead of precum seeps out and drips from the tip."
							:"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] groin, you see a huge bulge quickly forming in [npc2.her] "
									+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
									+ " Before you have any time to change your mind, [npc2.she] lets out [npc2.a_moan+], and you realise that [npc2.sheHas] now got a huge demonic cock hiding beneath [npc2.her] clothing.");
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				return "Grinning down at [npc2.name], you tease, [npc.speech(How about you use your transformative powers to grow a nice thick fox cock, so that we can have even more fun!)]"
						+ "<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
						?"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] naked groin, you see a large bump start to form beneath [npc2.her] [npc2.skin]."
						+ " Before you have any time to change your mind, it quickly grows out into a fox cock, and as you stare down at its throbbing length,"
						+ " a bead of precum seeps out and drips from the tip."
						:"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] groin, you see a huge bulge quickly forming in [npc2.her] "
						+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
						+ " Before you have any time to change your mind, [npc2.she] lets out [npc2.a_moan+], and you realise that [npc2.sheHas] now got a huge fox cock hiding beneath [npc2.her] clothing.");
			} else {
				return "Grinning down at [npc2.name], you tease, [npc.speech(How about you use your transformative powers to grow a nice thick slimy cock, so that we can have even more fun!)]"
						+ "<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] naked groin, you see a large bump start to form beneath [npc2.her] [npc2.skin]."
									+ " Before you have any time to change your mind, it quickly grows out into a fat slimy cock, and as you stare down at its throbbing length, a bead of precum seeps out and drips from the tip."
							:"[npc2.Name] lets out a little giggle, and as you look down at [npc2.her] groin, you see a huge bulge quickly forming in [npc2.her] "
									+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
									+ " Before you have any time to change your mind, [npc2.she] lets out [npc2.a_moan+], and you realise that [npc2.sheHas] now got a huge slimy cock hiding beneath [npc2.her] clothing.");
			}
		}
		
		@Override
		public String applyEffectsString() {
			Main.sex.getCharactersGrewCock().add(Main.sex.getCharacterTargetedForSexAction(this));
			
			StringBuilder sb = new StringBuilder();
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.DEMON_COMMON));
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.FOX_MORPH));
			} else {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(RacialBody.valueOfRace(Main.sex.getCharacterTargetedForSexAction(this).getBody().getFleshSubspecies().getRace()).getPenisType()));
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).getPenisRawCumStorageValue() < 150) {
				Main.sex.getCharacterTargetedForSexAction(this).setPenisCumStorage(150);
			}
			Main.sex.getCharacterTargetedForSexAction(this).fillCumToMaxStorage();
			if(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()) {
				Main.sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.ZERO_VESTIGIAL);
			} else {
				Main.sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.THREE_LARGE);
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).getPenisGirth().getValue() < PenetrationGirth.FOUR_GIRTHY.getValue()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisGirth(PenetrationGirth.FOUR_GIRTHY));
			}
			int size = Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isLargeGenitals()?40:20;
			if(Main.sex.getCharacterTargetedForSexAction(this).getPenisRawSizeValue() < size) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisSize(size));
			}
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character instanceof NPC) {
					for(GameCharacter target : Main.sex.getAllParticipants()) {
						if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
							((NPC)character).generateSexChoices(false, target, null);
						}
					}
				}
			}
			
			return sb.toString();
		}
	};

	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public Colour getHighlightColour() {
			return PresetColour.PSYCHOACTIVE;
		}
		@Override
		public String getActionTitle() {
			return "Calming suggestion";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] doesn't like having sex with you.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || (Main.sex.getCharacterTargetedForSexAction(this).getLust()>25 && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>"
					+ "Wanting to take advantage of the fact that [npc2.nameIsFull] under the strong effect of a psychoactive substance, [npc.name] [npc.verb(lean)] towards [npc2.herHim] and [npc.moansVerb],"
						+ " [npc.speech(You aren't really interested in having sex with me, are you?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc2.Name] can't help but agree with what [npc.sheIs] saying, and [npc2.name] haltingly [npc2.verb(answer)],"
						+ " [npc2.speech(Yes... I... I don't know why I'm having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further,"+(!Main.sex.getCharacterPerformingAction().isPlayer()?" and driven on by [npc.her] fetish for having non-consensual sex,":"")+" [npc.name] [npc.verb(continue)],"
						+ " [npc.speech(You'd rather I wasn't fucking you right now, isn't that right?)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] [npc2.verb(let)] out a disappointed sigh,"
							+ " [npc2.speech(This isn't really all that fun...)]");
					
				} else {
					if(LustLevel.getLustLevelFromValue(Main.sex.getCharacterTargetedForSexAction(this).getLust()-50).getSexPaceSubmissive()==SexPace.SUB_RESISTING) {
						sb.append("As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] [npc2.verb(let)] out a distressed cry,"
								+ " [npc2.speech(Wait, w-why is this happening?! Please, stop it! Get away from me!)]");
					} else {
						sb.append("As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] [npc2.verb(let)] out a disappointed sigh,"
								+ " [npc2.speech(This isn't really all that fun...)]");
					}
				}
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterTargetedForSexAction(this).incrementLust(-50, false);
		}
	};
	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public Colour getHighlightColour() {
			return PresetColour.PSYCHOACTIVE;
		}
		@Override
		public String getActionTitle() {
			return "Lustful suggestion";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] loves to have sex with you.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || (Main.sex.getCharacterTargetedForSexAction(this).getLust()<75 && !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Wanting to take advantage of the fact that [npc2.nameIsFull] under the mind-altering effects of a psychoactive substance, [npc.name] [npc.verb(lean)] in towards [npc2.herHim] and [npc.moanVerb],"
						+ " [npc.speech(You love having sex with me, don't you?)]"
					+ "</p>");
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>"
							+ "As [npc.name] says this, you suddenly feel a fuzzy warmth clouding your mind, and you're vaguely aware of your [npc2.eyes] glazing over as you answer,"
							+ " [npc2.speech(Yes... I... I love having sex with you...)]"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "[npc2.NamePos] [npc2.eyes] glaze over a little as [npc2.she] answers,"
						+ " [npc2.speech(Yes... I... I love having sex with you...)]"
					+ "</p>");
			}
			
			sb.append( "<p>"
						+ "Pushing a little further, [npc.name] [npc.verb(continue)],"
						+ " [npc.speech(You love begging for me to fuck you, isn't that right?)]"
					+ "</p>");

			if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>"
							+ "As your mind well and truly gives in to the hypnotic suggestion, you find yourself wanting nothing more than to be fucked by [npc.name], and you eagerly [npc2.moansVerb],"
							+ " [npc2.speech(Yes... Yes! Please, fuck me! I <i>need</i> you to fuck me!)]"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] starts to sound a lot more eager, and [npc2.moansVerb],"
						+ " [npc2.speech(Yes... Yes, [npc.name]! Please, fuck me! I <i>need</i> you to fuck me!)]"
					+ "</p>");
			}
			
			sb.append( "<p>"
						+ "[npc.speech(That's a good [npc2.girl],)] [npc.name] [npc.verb(say)], pleased to hear that [npc2.namePos] mind is [npc.hers] to twist as [npc.she] [npc.verb(see)] fit. [npc.speech(I'll give you what you want!)]"
					+ "</p>");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterTargetedForSexAction(this).incrementLust(50, false);
		}
	};
	
	public static final SexAction GENERIC_DENY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Deny";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to stay perfectly still, holding them in position until they've lost a good portion of their arousal.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isMasturbation()
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction()) // can't deny when hidden
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) { // can't deny hidden characters
				if(Main.sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Main.sex.getCharacterTargetedForSexAction(this).getArousal()>=50 // They will not deny if less than 50 arousal.
							&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_DENIAL).isPositive()
							&& !Main.sex.getLastUsedSexAction(Main.sex.getCharacterPerformingAction()).equals(this); // Do not deny twice in a row
				}
			}
			return false;
		}

		@Override
		public String getDescription() {//TODO BDSM gear. penetrations.
			UtilText.nodeContentSB.setLength(0);
			
			boolean alreadyOrgasmed = Main.sex.getNumberOfOrgasms(Main.sex.getCharacterTargetedForSexAction(this))>0;
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"Taking a gentle, yet firm, grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] quite still, softly [npc.moaning] as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, ",
							"Firmly gripping [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] in place, letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to calm down, ",
							"Softly [npc.moaning] as [npc.she] [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(tease)] [npc.her] partner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, "));

					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc.speech(That's a good [npc2.girl]... We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"just yet")+" now, would we?)]",
							"[npc.speech(Be a good [npc2.girl] now, and take a moment to calm down. You didn't think you'd get to orgasm "+(alreadyOrgasmed?"again":"")+" so soon with me, did you?)]",
							"[npc.speech(Good [npc2.girl]... You just take a moment to calm down. We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"so soon")+" now, would we?)]"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"Taking a firm, grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] quite still, [npc.moaning+] as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, ",
								"Firmly gripping [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(hold)] [npc2.herHim] in place, letting out [npc.a_moan+] as [npc.she] [npc.verb(force)] [npc2.herHim] to calm down, ",
								"[npc.Moaning+] as [npc.she] [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(tease)] [npc.her] partner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, "));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(That's a good [npc2.girl]! We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"just yet")+" now, would we?!)]",
								"[npc.speech(Be a good [npc2.girl] now, and take a moment to calm down. You didn't think you'd get to orgasm "+(alreadyOrgasmed?"again":"")+" so soon with me, did you?!)]",
								"[npc.speech(Good [npc2.girl]! You just take a moment to calm down! We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"so soon")+" now, would we?!)]"));
						break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"Taking a rough, firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(force)] [npc2.herHim] to remain quite still, growling in a threatening manner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, ",
								"Roughly gripping [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(force)] [npc2.herHim] to remain in place, letting out a threatening growl as [npc.she] [npc.verb(make)] [npc2.herHim] calm down, ",
								"Letting out a menacing growl as [npc.she] [npc.verb(take)] a firm grip of [npc2.namePos] [npc2.arms], [npc.name] [npc.verb(threaten)] [npc.her] partner as [npc.she] [npc.verb(wait)] for [npc2.herHim] to calm down, "));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(Stay still, you dumb [npc2.bitch]! You're not getting to climax "+(alreadyOrgasmed?"again already":"just yet")+" with me, understood?!)]",
								"[npc.speech(Stay still, [npc2.bitch], and calm down! You didn't think you'd get to orgasm "+(alreadyOrgasmed?"again":"")+" so soon with me, did you?!)]",
								"[npc.speech(That's it, [npc2.bitch]! You stay still and calm down! We wouldn't want you to climax "+(alreadyOrgasmed?"again already":"so soon")+" now, would we?!)]"));
						break;
				default:
					break;
			}
			UtilText.nodeContentSB.append("<br/><br/>");
			boolean nameKnown = Main.sex.getCharacterPerformingAction().isPlayerKnowsName();
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
								"[npc2.speechNoExtraEffects("+(nameKnown?"No, [npc.name],":"No...")+")] [npc2.name] [npc2.verb(reply)], trying to contain the excitement in [npc2.her] voice, [npc2.speechNoExtraEffects(I'll try to endure...)]",
								"[npc2.speechNoExtraEffects("+(nameKnown?"No, [npc.name],":"No...")+")] [npc2.name] [npc2.moansVerb], failing to subdue the intense arousal in [npc2.her] voice, [npc2.speechNoExtraEffects(I'll do my best to hold back...)]",
								"[npc2.speechNoExtraEffects("+(nameKnown?"No, [npc.name]...":"No...")+")] [npc2.name] [npc2.verb(answer)], before trying to stifle a desperate [npc2.moan], [npc2.speechNoExtraEffects(~Mmm!~ I'll try to hold back!)]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
									"[npc2.speechNoExtraEffects("+(nameKnown?"No, [npc.name]...":"No...")+")] [npc2.name] [npc2.verb(reply)], [npc2.speechNoExtraEffects(I'll try to endure...)]",
									"[npc2.speechNoExtraEffects("+(nameKnown?"No, [npc.name]...":"No...")+")] [npc2.name] [npc2.moansVerb], [npc2.speechNoExtraEffects(I'll do my best to hold back...)]",
									"[npc2.speechNoExtraEffects("+(nameKnown?"No, [npc.name]...":"No...")+")] [npc2.name] [npc2.verb(answer)], [npc2.speechNoExtraEffects(I'll try to hold back!)]"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
									"[npc2.speechNoExtraEffects(Just get away from me!)] [npc2.name] frantically [npc2.sobsVerb], [npc2.speechNoExtraEffects(I hate this!)]",
									"[npc2.speechNoExtraEffects(Let go of me!)] [npc2.name] desperately [npc2.sobsVerb], trying to pull [npc2.herself] out of [npc.namePos] grasp, [npc2.speechNoExtraEffects(Stop it! Get away from me!)]",
									"[npc2.speechNoExtraEffects(Why won't you just let me go?!)] [npc2.name] [npc2.sobsVerb] as [npc2.she] weakly [npc2.verb(try)] to pull away from [npc2.name], [npc2.speechNoExtraEffects(I don't want this!)]"));
					break;
				default:
					break;
			}
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Once it's clear that [npc2.nameHasFull] calmed down, [npc.name] [npc.verb(loosen)] [npc.her] grip, before continuing as [npc.she] [npc.was] before...");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop penetrations";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions that [npc2.name] is involved with.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening()
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Main.sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.asshole+]."));
							}
							break;
						case ASS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] using [npc2.her] [npc2.ass+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] using [npc2.her] [npc2.ass+]."));
							}
							break;
						case ARMPITS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] fucking [npc2.her] [npc2.armpit+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] fucking [npc2.her] [npc2.armpit+]."));
							}
							break;
						case BREAST:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.breasts+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.breasts+]."));
							}
							break;
						case BREAST_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.crotchBoobs+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(stop)] playing with [npc2.her] [npc2.crotchBoobs+]."));
							}
							break;
						case MOUTH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] mouth."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] mouth."));
							}
							break;
						case NIPPLE:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.nipple+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.nipple+]."));
							}
							break;
						case NIPPLE_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.crotchNipple+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.crotchNipple+]."));
							}
							break;
						case URETHRA_PENIS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.penisUrethra+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.penisUrethra+]."));
							}
							break;
						case URETHRA_VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.vaginaUrethra+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.vaginaUrethra+]."));
							}
							break;
						case VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.pussy+]."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] [npc2.pussy+]."));
							}
							break;
						case THIGHS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out from between [npc2.her] thighs."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out from between [npc2.her] thighs."));
							}
							break;
						case SPINNERET:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] spinneret."));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] out of [npc2.her] spinneret."));
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), character);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop penetrations (self)";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions that you are involved with.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterEngagedInOngoingAction(Main.game.getPlayer())
					&& Main.sex.getAllParticipants(false).size()>2
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Main.sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.asshole+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.asshole+].");
							}
							break;
						case ASS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop using [npc2.her] [npc2.ass+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.ass+].");
							}
							break;
						case ARMPITS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop fucking [npc2.her] [npc2.armpit+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.armpits+].");
							}
							break;
						case BREAST:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop playing with [npc2.her] [npc2.breasts+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.breasts+].");
							}
							break;
						case BREAST_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop playing with [npc2.her] [npc2.crotchBoobs+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.crotchBoobs+].");
							}
							break;
						case MOUTH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] mouth.");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you pull out of your mouth.");
							}
							break;
						case NIPPLE:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.nipple+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.nipple+].");
							}
							break;
						case NIPPLE_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.crotchNipple+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.crotchNipple+].");
							}
							break;
						case URETHRA_PENIS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.penisUrethra+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.penisUrethra+].");
							}
							break;
						case URETHRA_VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.vaginaUrethra+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.vaginaUrethra+].");
							}
							break;
						case VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.pussy+].");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.pussy+].");
							}
							break;
						case THIGHS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out from between [npc2.her] thighs.");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop playing with your thighs.");
							}
							break;
						case SPINNERET:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] spinneret.");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your spinneret.");
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				Main.sex.stopAllOngoingActions(Main.game.getPlayer(), character);
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid self actions";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(Main.sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc2.she] lets out a pathetic whine.");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] pouts at you as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop using [npc2.her] mouth.");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0) {
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[npc.speech(I don't want to see you trying to get yourself off,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will no longer use any self-penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.setCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit self actions";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to perform all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(Main.sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(You can touch yourself all you want,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to use any self-penetrative actions.</i>";
		}

		@Override
		public void applyEffects() {
			Main.sex.setCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction PLAYER_FORBID_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Restrict control";
		}

		@Override
		public String getActionDescription() {
			return "Restrict [npc2.namePos] level of control, preventing [npc2.herHim] from initiating any non-self penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Main.sex.isDom(target)
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(target).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(You're not to do anything without being told,)] you order [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] now has a restricted level of control, and cannot initiate any non-self penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.setForcedSexControl(target, SexControl.ONGOING_ONLY);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Unrestrict control";
		}

		@Override
		public String getActionDescription() {
			return "Unrestrict [npc2.namePos] level of control, allowing [npc2.herHim] to initiate non-self penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Main.sex.isDom(target)
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(target).getValue()<SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(You can do what you like,)] you say to [npc2.name], freeing [npc2.herHim] to do as [npc2.she] pleases.<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] now has an unrestricted level of control, and can initiate non-self penetrative actions at will.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.setForcedSexControl(target, SexControl.FULL);
			for(GameCharacter participant : Main.sex.getAllParticipants()) {
				if(!participant.equals(target) && Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid positioning";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from using any positioning actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(target);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(I don't want to see you attempting to switch positions,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will no longer use any positioning actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.addCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit positioning";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to use positioning actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(target);
		}

		@Override
		public String getDescription() {
			return "[npc.speech(If you'd like, you can switch to whatever position you're most comfortable with,)] you [npc.moanVerb] at [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to use positioning actions.</i>";
		}

		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.removeCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterTargetedForSexAction(this));
			for(GameCharacter participant : Main.sex.getAllParticipants()) {
				if(!participant.equals(target) && Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from managing any of your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(Main.sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), null)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(Don't you <i>dare</i> try and touch any of my clothes!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will not attempt to remove or displace any of your clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to take off and displace your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(Main.sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), null)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(How about you help me take off some of these clothes?)] you [npc.moan].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to manage your clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from managing any of [npc2.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(Main.sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(Don't you <i>dare</i> try and touch your clothes!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will not attempt to remove or displace any of [npc2.her] clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to take off and displace [npc2.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (Main.sex.getSexControl(Main.sex.getCharacterTargetedForSexAction(this))!=SexControl.FULL || !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(How about you start taking off some of your clothes?)] you [npc.moan].<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to manage [npc2.her] clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};
	
	
	public static final SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop partner";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc2.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isMasturbation()
					&& Main.sex.isCharacterSelfOngoingActionHappening(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc2.she] lets out a pathetic whine.");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] pouts at you as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop using [npc2.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop player";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterSelfOngoingActionHappening(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isAnyNonSelfOngoingActionHappening()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc.Name] lets out an angry growl as [npc.she] forces [npc2.name] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As [npc.name] puts an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc.she] growls menacingly at [npc2.herHim].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] frowns at [npc2.name] as [npc.she] forces [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] makes a disapproving clicking noise with [npc.her] [npc.tongue] as [npc.she] forces [npc2.name] to stop using [npc2.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	/**
	 * Special end action for submissive NPCs who end up resisting, and who also have the power to stop sex.
	 */
	public static final SexAction PARTNER_STOP_SEX_NOT_HAVING_FUN = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "End sex";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& (!Main.sex.getCharacterPerformingAction().isSlave() || !Main.sex.getAllParticipants().contains(Main.sex.getCharacterPerformingAction().getOwner()))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
					&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB);
			
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With an annoyed sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches,"
					+ " [npc.speechNoExtraEffects(Eugh... I'm not really feeling this right now, ok?)]";
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return Main.sex.isMasturbation()
					?"Stop masturbating"
					:"Stop sex";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterWantingToStopSex(Main.sex.getCharacterPerformingAction())
					&& (!Main.sex.getAllParticipants(false).contains(Main.game.getPlayer()) || !Main.sex.isReadyToOrgasm(Main.game.getPlayer()))
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).getGenericEndSexDescription(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "Stop watching";
			}
			return Main.sex.isMasturbation()
					?"Stop masturbating"
					:"Stop sex";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "Back out and stop watching the sex scene unfold before you."
						+ "<br/>This will still [style.boldSex(apply all applicable effects)] to the other sex participants as though the sex scene had fully taken place.";
			}
			return Main.sex.isMasturbation()
					?"Put an end to your masturbation session."
					:"Stop having sex with [npc2.name].";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isPlayerAbleToStopSex()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getDescription() {
			return Main.sex.isMasturbation()
					?"Having had enough of the show, you turn away and stop watching the sex scene unfold before you..."
					:"Having had enough, you [pc.step] back and stop having sex...";
		}
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation() || Main.sex.isSpectator(Main.game.getPlayer())
					?SexParticipantType.SELF
					:SexParticipantType.NORMAL;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
		@Override
		public String applyEndEffects(){
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) { // Generate effects when ending sex as hidden spectator
				quickSexDescription = generateQuickSexDescription(false);
			}
			return "";
		}
	};

	public static final SexAction ROPE_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Bound!)]";
		}
		@Override
		public String getActionDescription() {
			return "The ropes tied around your body are preventing you from making a move!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return value!=null && value.getKey()==ImmobilisationType.ROPE;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)] to make a move, but the ropes binding [npc.herHim] in place are too strong, and [npc.she] [npc.verb(collapse)] back down, stunned.");
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction CHAINS_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Bound!)]";
		}
		@Override
		public String getActionDescription() {
			return "The chains tied around your body are preventing you from making a move!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return value!=null && value.getKey()==ImmobilisationType.CHAINS;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)] to make a move, but the chains binding [npc.herHim] in place are too strong, and [npc.she] [npc.verb(collapse)] back down, stunned.");
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Spinneret:
	
	public static final SexAction SPINNERET_SPIN_CONDOM_SELF = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Spin condom (self)";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to wrap your [npc.penis] in a condom-like binding of webbing.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| ((NPC) Main.sex.getCharacterPerformingAction()).isWantingToEquipCondom(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())))
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterPerformingAction(), InventorySlot.PENIS, null)
					&& Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterPerformingAction().equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
	};
	
	public static final SexAction SPINNERET_SPIN_CONDOM_PARTNER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Spin condom";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to wrap [npc2.namePos] [npc2.penis] in a condom-like binding of webbing.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| ((NPC) Main.sex.getCharacterPerformingAction()).isWantingToEquipCondomOnPartner(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.PENIS, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.PENIS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
	};
	
	public static final SexAction SPINNERET_SPIN_SEAL_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Spin pussy-seal";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to apply a thick binding of webbing over [npc2.namePos] [npc2.pussy].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.VAGINA, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.VAGINA)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_vagina"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Spin ass-seal";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to apply a thick binding of webbing over [npc2.namePos] [npc2.asshole].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.ANUS, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.ANUS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_anus"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Spin nipple-seal";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to apply a thick binding of webbing over [npc2.namePos] [npc2.nipples].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.NIPPLE, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.NIPPLE)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_nipples"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_MOUTH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "Spin mouth-seal";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to apply a thick binding of webbing over [npc2.namePos] [npc2.mouth].";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.MOUTH, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.MOUTH)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_mouth"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Cocooning:
	
	public static final SexAction SPINNERET_COCOON_PARTNER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Cocoon [npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "Use your spinneret to wrap [npc2.name] up in a cocoon.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Not wanting [npc2.name] to try and make any move of [npc2.her] own, [npc.name] [npc.verb(point)] [npc.her] spinneret at [npc2.herHim], before starting to shoot out a continuous strand of thick, sticky webbing."
					+ " Before [npc2.she] [npc2.verb(know)] what's happening, [npc2.namePos] [npc2.arms] and [npc2.legs] are completely restrained by the strong binding,"
						+ " and after just a few moments more, [npc.name] [npc.has] completely wrapped [npc2.herHim] up in a cocoon."
					+ " Smirking at [npc.her] handiwork, [npc.name] [npc.verb(prepare)] to make good use of the fact that [style.boldBad([npc2.name] [npc2.is] no longer able to move)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.COCOON, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_COCOON_PARTNER_REMOVE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Remove webbing";
		}
		@Override
		public String getActionDescription() {
			return "Tear off the webbing that's binding [npc2.name] into [npc2.her] cocoon.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& (value!=null && value.getKey()==ImmobilisationType.COCOON)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc2.name] [npc2.has] had enough of being restrained, [npc.name] [npc.verb(set)] about tearing off the webbing that's binding [npc2.herHim] into [npc2.her] cocoon. "
					+ " After just a few moments, [npc.name] [npc.has] managed to destroy all of the webbing, and as a result, [style.boldGood([npc2.name] [npc2.is] now able to move again)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction COCOONED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Cocooned!)]";
		}
		@Override
		public String getActionDescription() {
			return "The cocoon of thick spider-webbing is preventing you from making a move!";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return value!=null && value.getKey()==ImmobilisationType.COCOON;
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name] [npc.verb(try)] to make a move, but the cocoon binding [npc.herHim] in place is too strong, and [npc.she] [npc.verb(collapse)] back down, stunned.");
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Tentacles:
	
	public static final SexAction TENTACLES_RESTRICT_PARTNER = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Tentacle restraint";
		}
		@Override
		public String getActionDescription() {
			return "Use your tentacles to hold [npc2.name] still and prevent [npc2.herHim] from moving.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Not wanting [npc2.name] to try and make any move of [npc2.her] own, [npc.name] [npc.verb(grab)] [npc2.herHim] with [npc.her] [npc.tentacles] and [npc.verb(hold)] [npc2.herHim] firmly in place."
					+ "  Smirking as [npc2.name] [npc2.verb(make)] a few futile attempts to struggle against [npc.her] tight embrace,"
						+ " [npc.name] [npc.verb(prepare)] to make good use of the fact that [style.boldBad([npc2.name] [npc2.is] no longer able to move)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.TENTACLE_RESTRICTION, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TENTACLES_RELEASE_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Release tentacles";
		}
		@Override
		public String getActionDescription() {
			return "Release your grip on [npc2.name] and allow [npc2.herHim] to freely move again.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& (value!=null && value.getKey()==ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc2.name] [npc2.has] had enough of being restrained, [npc.name] [npc.verb(begin)] to relax the grip of [npc.her] [npc.tentacles]. "
					+ " Smirking in amusement at the relieved look on [npc2.namePos] face, [npc.name] completely [npc.verb(release)] [npc2.herHim] from [npc.her] tentacles' embrace,"
						+ " and as a result, [style.boldGood([npc2.name] [npc2.is] now able to move again)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction TENTACLE_SQUEEZE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Tentacle-squeeze";
		}
		@Override
		public String getActionDescription() {
			return "Take advantage of the fact that you have [npc2.name] fully restrained in your tentacles to firmly squeeze and constrict [npc2.her] body.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return (value!=null && value.getKey()==ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Taking full advantage of the fact that [npc.she] [npc.has] [npc2.name] completely restrained in [npc.her] tentacles, [npc.name] [npc.verb(squeeze)] down and firmly [npc.verb(constrict)] [npc2.her] body,"
							+ " causing [npc2.herHim] to let out a pathetic, choking gasp.",
					"Wanting to show [npc2.name] that [npc2.sheIs] completely at [npc.her] mercy, [npc.name] firmly [npc.verb(squeeze)] [npc.her] tentacles down around [npc2.her] body,"
							+ " which results in [npc2.herHim] making a series of progressively weaker and more desperate gasping noises.",
					"In a display of complete dominance, [npc.name] firmly [npc.verb(squeeze)] [npc.her] tentacles down around [npc2.namePos] body,"
							+ " delighting in the desperate, frantic gasps and struggles that [npc.sheIs] able to elicit from "+(targetPlayer?"you.":"[npc.her] partner."),
					"Greatly enjoying [npc.her] position of dominance, [npc.name] slowly increases the strength of [npc.her] tentacles' grip around [npc2.namePos] body, until "+(targetPlayer?"you're":"[npc.her] partner is")
						+" able to do nothing but weakly struggle and desperately gasp for breath.",
					"Firmly tightening [npc.her] tentacles' grip around [npc2.namePos] body, [npc.name] [npc.verb(delight)] in squeezing the breath out of "+(targetPlayer?"your":"[npc.her] partner's")
						+" lungs, and can't help but let out a dominant [npc.moan] as [npc.she] [npc.verb(feel)] [npc2.name] weakly struggling to break free."));

			sb.append(UtilText.returnStringAtRandom(
					" Only once [npc2.nameIsFull] starting to go limp [npc.do] [npc.name] finally relent, and by relaxing [npc.her] tentacles somewhat,"
							+ " [npc.she] [npc.verb(allow)] [npc2.herHim] to catch [npc2.her] breath and come back to [npc2.her] senses.",
					" Not wanting to go so far as to make [npc2.name] lose consciousness, [npc.name] only [npc.verb(choose)] to relax [npc.her] tentacles at the last moment,"
							+ " allowing [npc2.name] to take a deep breath and come back from the brink of fainting.",
					" After some time constricting [npc2.name] in this manner, [npc.name] [npc.verb(feel)] as though [npc.sheHas] pushed [npc2.herHim] far enough, and so after one final, tight squeeze,"
							+ " [npc.she] [npc.verb(relax)] [npc.her] tentacles and [npc.verb(allow)] [npc2.name] to breathe freely once again.",
					" Waiting until [npc.she] [npc.verb(sense)] that [npc2.nameIsFull] is about to faint, [npc.name] [npc.verb(let)] out an amused [npc.moan],"
							+ " before relaxing [npc.her] tentacles and granting [npc2.name] the ability to fill [npc2.her] lungs with oxygen.",
					" Waiting until [npc2.nameIsFull] about to pass out, [npc.name] [npc.verb(let)] out an amused [npc.moan], before relaxing [npc.her] tentacles and allowing [npc2.name] to take a deep breath and recover from [npc2.her] asphyxiation."));
			
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SADIST, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TENTACLE_MASSAGE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Tentacle-massage";
		}
		@Override
		public String getActionDescription() {
			return "Take advantage of the fact that you have [npc2.name] fully restrained in your tentacles to squeeze and massage [npc2.her] body.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return (value!=null && value.getKey()==ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Taking full advantage of the fact that [npc.she] [npc.has] [npc2.name] completely restrained in [npc.her] tentacles, [npc.name] [npc.verb(squeeze)] down and [npc.verb(massage)] [npc2.her] body.",
					"Wanting to show [npc2.name] that [npc2.sheIs] completely at [npc.her] mercy, [npc.name] [npc.verb(squeeze)] [npc.her] tentacles down around [npc2.her] body and [npc.verb(start)] massaging [npc2.herHim].",
					"In a display of dominance, [npc.name] firmly [npc.verb(squeeze)] [npc.her] tentacles down around [npc2.namePos] body, using [npc.her] grip to massage "+(targetPlayer?"your":"[npc.her] partner's")+" body.",
					"Greatly enjoying [npc.her] position of dominance, [npc.name] [npc.verb(make)] full use of [npc.her] tentacles' grip around [npc2.namePos] body by squeezing down and massaging [npc2.herHim].",
					"Firmly tightening [npc.her] tentacles' grip around [npc2.namePos] body, [npc.name] [npc.verb(delight)] in squeezing and massaging [npc2.herHim]."));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" Although it's a complete impossibility, [npc2.name] [npc2.verb(continue)] to try and struggle free from [npc.namePos] grip, letting out a distressed cry as [npc2.she] [npc2.verb(realise)] that it's futile.",
						" [npc2.Name] [npc2.verb(let)] out a frantic cry in response, before trying, and failing, to struggle free from [npc.namePos] embrace.",
						" Refusing to accept [npc2.her] fate, [npc2.name] [npc2.verb(try)] to pull free from [npc.namePos] tight embrace, but [npc2.her] efforts prove to be in vain."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(relax)] and [npc2.verb(enjoy)] the sensual feeling of [npc.namePos] [npc.tentacles+] sliding over and pressing down against [npc.her] body.",
						" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, making it very clear that [npc2.sheIs] enjoying the feeling of being wrapped up in [npc.namePos] [npc.tentacles+].",
						" Relaxing and enjoying the feeling of being wrapped up in [npc.namePos] [npc.tentacles+], [npc2.name] [npc2.verb(let)] [npc2.a_moan+] and [npc2.verb(encourage)] [npc.herHim] to continue the massage."));
			}
			
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction TENTACLE_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Tentacle-bound!)]";
		}
		@Override
		public String getActionDescription() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return UtilText.parse(value.getValue(), "[npc.Name] is using [npc.her] tentacles to hold you in place, and as a result, you are unable to make a move!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return value!=null && value.getKey()==ImmobilisationType.TENTACLE_RESTRICTION;
		}
		@Override
		public String getDescription() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), value.getValue(),
					"[npc.Name] [npc.verb(try)] to make a move, but [npc2.namePos] tentacle embrace is far too strong, and all [npc.she] can manage is to make a few pathetic squirming motions.");
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Witch's seal:

	public static final SexAction WITCH_SEAL_CAST = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.SPELL_SCHOOL_ARCANE;
		}
		@Override
		public String getActionTitle() {
			return "Cast '"+Spell.WITCH_SEAL.getName()+"'";
		}
		@Override
		public String getActionDescription() {
			return "Cast the spell '"+Spell.WITCH_SEAL.getName()+"' on [npc2.name] to immobilise [npc2.herHim] and completely prevent [npc2.herHim] from moving.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer() // Only allow player to use this
					&& Main.sex.getCharacterPerformingAction().hasSpell(Spell.WITCH_SEAL, true)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Not wanting [npc2.name] to try and make any move of [npc2.her] own, [npc.name] [npc.verb(prepare)] to cast the spell '"+Spell.WITCH_SEAL.getName()+"' on [npc2.herHim]."
					+ " Concentrating on the arcane power within [npc.her] broomstick, [npc.name] [npc.verb(summon)] forth a powerful seal, which traps [npc2.name] in place!"
						+ " Having successfully cast [npc.her] spell, [npc.name] [npc.verb(prepare)] to make good use of the fact that [style.boldBad([npc2.name] [npc2.is] no longer able to move)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.WITCH_SEAL, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction WITCH_SEALED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Sealed!)]";
		}
		@Override
		public String getActionDescription() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return UtilText.parse(value.getValue(), "The Witch's Seal that [npc.name] cast on you is preventing you from making a move!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return value!=null && value.getKey()==ImmobilisationType.WITCH_SEAL;
		}
		@Override
		public String getDescription() {
			if(Main.sex.getInitialSexManager() instanceof SMAltarMissionarySealed) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to make a move, but the Witch's Seal is too strong, and [npc.she] [npc.verb(collapse)] back onto the altar, stunned.",
						"The purple glow of a pentagram materialises beneath [npc.namePos] body as [npc.she] [npc.verb(try)] to make a move; proof that the Witch's Seal is still keeping [npc.herHim] bound in place.",
						"[npc.Name] [npc.verb(try)] to sit up on the altar, but [npc.sheIs] only able to squirm about a little under the immobilising effects of the Witch's Seal.",
						"The soft purple glow of the Witch's Seal can be seen all around [npc.name] as [npc.she] [npc.verb(struggle)] to make a move.",
						"[npc.speech(~Mmm!~)] [npc.name] [npc.verb(moan)], struggling in vain against the Witch's Seal.",
						"[npc.speech(~Aah!~)] [npc.name] [npc.verb(whimper)], squirming about on the altar as the With's Seal keeps [npc.herHim] locked in place.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(try)] to make a move, but the Witch's Seal is too strong, and [npc.she] [npc.verb(collapse)] back into position, stunned.",
						"The purple glow of a pentagram materialises beneath [npc.namePos] body as [npc.she] [npc.verb(try)] to make a move; proof that the Witch's Seal is still keeping [npc.herHim] bound in place.",
						"[npc.Name] [npc.verb(try)] to move, but [npc.sheIs] only able to squirm about a little under the immobilising effects of the Witch's Seal.",
						"The soft purple glow of the Witch's Seal can be seen all around [npc.name] as [npc.she] [npc.verb(struggle)] to make a move.",
						"[npc.speech(~Mmm!~)] [npc.name] [npc.verb(moan)], struggling in vain against the Witch's Seal.",
						"[npc.speech(~Aah!~)] [npc.name] [npc.verb(whimper)], squirming about as the With's Seal keeps [npc.herHim] locked in place.");
			}
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction WITCH_SEAL_BREAK = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.SPELL_SCHOOL_ARCANE;
		}
		@Override
		public String getActionTitle() {
			return "Dispel '"+Spell.WITCH_SEAL.getName()+"'";
		}
		@Override
		public String getActionDescription() {
			return "Cancel the spell '"+Spell.WITCH_SEAL.getName()+"' that you've cast on [npc2.name], allowing [npc2.herHim] to freely move again.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return Main.sex.getCharacterPerformingAction().isPlayer() // Only allow player to end this
					&& (value!=null && value.getKey()==ImmobilisationType.WITCH_SEAL)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc2.name] [npc2.has] had enough of being immobilised, [npc.name] [npc.verb(prepare)] to dispel the effect of the spell, '"+Spell.WITCH_SEAL.getName()+"'. "
					+ " Taking a deep breath and concentrating on [npc.her] arcane power, [npc.name] [npc.verb(break)] the spell, and as a result, [style.boldGood([npc2.name] [npc2.is] now able to move again)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	// Tentacles:
	
	public static final SexAction TAIL_CONSTRICTION_RESTRICT_PARTNER = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "Constrict";
		}
		@Override
		public String getActionDescription() {
			return "Wrap your long tail around [npc2.name] and constrict [npc2.herHim] in order to prevent [npc2.herHim] from moving.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.getCharactersImmobilised().containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getCharactersImmobilised().get(ImmobilisationType.TAIL_CONSTRICTION).containsKey(Main.sex.getCharacterPerformingAction())) {
				return false; // If performing character is engaged in ongoing long-tail constriction, return false (as can only restrict one at a time).
			}
			return Main.sex.getCharacterPerformingAction().getLegConfiguration()==LegConfiguration.TAIL_LONG
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "Not wanting [npc2.name] to try and make any move of [npc2.her] own, [npc.name] [npc.verb(wrap)] [npc.her] long tail around [npc2.herHim] and [npc.verb(squeeze)] tight, locking [npc2.herHim] firmly in place."
					+ "  Smirking as [npc2.name] [npc2.verb(make)] a few futile attempts to struggle against [npc.her] constricting coils,"
						+ " [npc.name] [npc.verb(prepare)] to make good use of the fact that [style.boldBad([npc2.name] [npc2.is] no longer able to move)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.TAIL_CONSTRICTION, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_CONSTRICTION_RELEASE_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "Release constriction";
		}
		@Override
		public String getActionDescription() {
			return "Release your grip on [npc2.name] and allow [npc2.herHim] to freely move again.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& (value!=null && value.getKey()==ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "Deciding that [npc2.name] [npc2.has] had enough of being restrained, [npc.name] [npc.verb(begin)] to relax the tight coils of [npc.her] long tail. "
					+ " Smirking in amusement at the relieved look on [npc2.namePos] face, [npc.name] completely [npc.verb(release)] [npc2.herHim] from [npc.her] embrace,"
						+ " and as a result, [style.boldGood([npc2.name] [npc2.is] now able to move again)].";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction TAIL_SQUEEZE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "Tail-squeeze";
		}
		@Override
		public String getActionDescription() {
			return "Take advantage of the fact that you have [npc2.name] fully restrained in your tail to firmly squeeze and constrict [npc2.her] body.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return (value!=null && value.getKey()==ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Taking full advantage of the fact that [npc.she] [npc.has] [npc2.name] completely restrained in [npc.her] tail, [npc.name] [npc.verb(squeeze)] down and firmly [npc.verb(constrict)] [npc2.her] body,"
							+ " causing [npc2.herHim] to let out a pathetic, choking gasp.",
					"Wanting to show [npc2.name] that [npc2.sheIs] completely at [npc.her] mercy, [npc.name] firmly [npc.verb(squeeze)] [npc.her] tail down around [npc2.her] body,"
							+ " which results in [npc2.herHim] making a series of progressively weaker and more desperate gasping noises.",
					"In a display of complete dominance, [npc.name] firmly [npc.verb(squeeze)] [npc.her] tail down around [npc2.namePos] body,"
							+ " delighting in the desperate, frantic gasps and struggles that [npc.sheIs] able to elicit from "+(targetPlayer?"you.":"[npc.her] partner."),
					"Greatly enjoying [npc.her] position of dominance, [npc.name] slowly increases the strength of [npc.her] tail's grip around [npc2.namePos] body, until "+(targetPlayer?"you're":"[npc.her] partner is")
						+" able to do nothing but weakly struggle and desperately gasp for breath.",
					"Firmly tightening [npc.her] tail's grip around [npc2.namePos] body, [npc.name] [npc.verb(delight)] in squeezing the breath out of "+(targetPlayer?"your":"[npc.her] partner's")
						+" lungs, and can't help but let out a dominant [npc.moan] as [npc.she] [npc.verb(feel)] [npc2.name] weakly struggling to break free."));
			
			sb.append(UtilText.returnStringAtRandom(
					" Only once [npc2.nameIsFull] starting to go limp [npc.do] [npc.name] finally relent, and by relaxing [npc.her] tail somewhat,"
							+ " [npc.she] [npc.verb(allow)] [npc2.herHim] to catch [npc2.her] breath and come back to [npc2.her] senses.",
					" Not wanting to go so far as to make [npc2.name] lose consciousness, [npc.name] only [npc.verb(choose)] to relax [npc.her] tail at the last moment,"
							+ " allowing [npc2.name] to take a deep breath and come back from the brink of fainting.",
					" After some time constricting [npc2.name] in this manner, [npc.name] [npc.verb(feel)] as though [npc.sheHas] pushed [npc2.herHim] far enough, and so after one final, tight squeeze,"
							+ " [npc.she] [npc.verb(relax)] [npc.her] tail and [npc.verb(allow)] [npc2.name] to breathe freely once again.",
					" Waiting until [npc.she] [npc.verb(sense)] that [npc2.nameIsFull] is about to faint, [npc.name] [npc.verb(let)] out an amused [npc.moan],"
							+ " before relaxing [npc.her] tail and granting [npc2.name] the ability to fill [npc2.her] lungs with oxygen.",
					" Waiting until [npc2.nameIsFull] about to pass out, [npc.name] [npc.verb(let)] out an amused [npc.moan], before relaxing [npc.her] tail and allowing [npc2.name] to take a deep breath and recover from [npc2.her] asphyxiation."));
			
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SADIST, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_MASSAGE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Tail-massage";
		}
		@Override
		public String getActionDescription() {
			return "Take advantage of the fact that you have [npc2.name] fully restrained in your tail to squeeze and massage [npc2.her] body.";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterTargetedForSexAction(this));
			return (value!=null && value.getKey()==ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"Taking full advantage of the fact that [npc.she] [npc.has] [npc2.name] completely restrained in [npc.her] tail, [npc.name] [npc.verb(squeeze)] down and [npc.verb(massage)] [npc2.her] body.",
					"Wanting to show [npc2.name] that [npc2.sheIs] completely at [npc.her] mercy, [npc.name] [npc.verb(squeeze)] [npc.her] tail down around [npc2.her] body and [npc.verb(start)] massaging [npc2.herHim].",
					"In a display of dominance, [npc.name] firmly [npc.verb(squeeze)] [npc.her] tail down around [npc2.namePos] body, using [npc.her] grip to massage "+(targetPlayer?"your":"[npc.her] partner's")+" body.",
					"Greatly enjoying [npc.her] position of dominance, [npc.name] [npc.verb(make)] full use of [npc.her] tail' grip around [npc2.namePos] body by squeezing down and massaging [npc2.herHim].",
					"Firmly tightening [npc.her] tail' grip around [npc2.namePos] body, [npc.name] [npc.verb(delight)] in squeezing and massaging [npc2.herHim]."));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						" Although it's a complete impossibility, [npc2.name] [npc2.verb(continue)] to try and struggle free from [npc.namePos] grip, letting out a distressed cry as [npc2.she] [npc2.verb(realise)] that it's futile.",
						" [npc2.Name] [npc2.verb(let)] out a frantic cry in response, before trying, and failing, to struggle free from [npc.namePos] embrace.",
						" Refusing to accept [npc2.her] fate, [npc2.name] [npc2.verb(try)] to pull free from [npc.namePos] tight embrace, but [npc2.her] efforts prove to be in vain."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						" Letting out [npc2.a_moan+], [npc2.name] [npc2.verb(relax)] and [npc2.verb(enjoy)] the sensual feeling of [npc.namePos] tail sliding over and pressing down against [npc.her] body.",
						" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] in response, making it very clear that [npc2.sheIs] enjoying the feeling of being wrapped up in [npc.namePos] tail.",
						" Relaxing and enjoying the feeling of being wrapped up in [npc.namePos] tail, [npc2.name] [npc2.verb(let)] [npc2.a_moan+] and [npc2.verb(encourage)] [npc.herHim] to continue the massage."));
			}
			
			return sb.toString();
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_CONSTRICTED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(Constricted!)]";
		}
		@Override
		public String getActionDescription() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return UtilText.parse(value.getValue(), "[npc.Name] is using [npc.her] long tail to hold you in place, and as a result, you are unable to make a move!");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return value!=null && value.getKey()==ImmobilisationType.TAIL_CONSTRICTION;
		}
		@Override
		public String getDescription() {
			Value<ImmobilisationType, GameCharacter> value = Main.sex.getImmobilisationType(Main.sex.getCharacterPerformingAction());
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), value.getValue(),
					"[npc.Name] [npc.verb(try)] to make a move, but [npc2.namePos] constricting tail is far too strong, and all [npc.she] can manage is to make a few pathetic squirming motions.");
		}
		@Override
		public List<Fetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
}
