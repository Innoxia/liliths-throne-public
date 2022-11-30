package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.dominion.SlaveInStocks;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.misc.SlaveForSale;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class SlaverAlleyDialogue {

	private static NPC biddingNPC = null;
	private static int biddingPrice = 0;
	private static int biddingRounds = 0;
	private static int biddingRoundsTotal = 1;
	private static boolean playerBidLeader = false;
	private static SlaveAuctionBidder currentRivalBidder = null;
	
	private static NPC stocksSlaveTargeted = null;

	private static List<GameCharacter> randomSexPartners = null;
	
	public static void dailyReset() {
		for(String id : new ArrayList<>(Main.game.getNpc(Finch.class).getSlavesOwned())) {
			if(Main.game.isCharacterExisting(id)) {
				Main.game.banishNPC(id);
			}
		}
		Main.game.getNpc(Finch.class).removeAllSlaves();
		
		// Female stall:
		Gender[] genders = new Gender[] {Gender.F_V_B_FEMALE, Gender.F_V_B_FEMALE, Gender.F_P_V_B_FUTANARI};
		for (Gender gender : genders) {
			NPC slave = new SlaveForSale(gender, false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_FEMALES, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_GOLD, false), true, Main.game.getNpc(Finch.class));
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsFemale(slave);
		}

		// Male stall:
		genders = new Gender[] {Gender.M_P_MALE, Gender.M_P_MALE, Gender.M_P_MALE};
		for (Gender gender : genders) {
			NPC slave = new SlaveForSale(gender, false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_MALES, true);
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsMale(slave);
		}

		// Anal stall:
		for (int i=0; i<3; i++) {
			NPC slave = new SlaveForSale(Gender.getGenderFromUserPreferences(false, false), false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_ANAL, true);
			if (i==0) {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug"), false), true, Main.game.getNpc(Finch.class));
			} else if(i==1) {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_jewel"), false), true, Main.game.getNpc(Finch.class));
			} else {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"), false), true, Main.game.getNpc(Finch.class));
			}
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsAnal(slave);
			Main.game.getPlayer().setKnowsCharacterArea(CoverableArea.ANUS, slave, true);
		}

		// Vaginal stall:
		for (int i=0; i<3; i++) {
			NPC slave = new SlaveForSale(Gender.getGenderFromUserPreferences(true, false), false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_VAGINAL, true);
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsVaginal(slave);
		}

		// Oral stall:
		for (int i=0; i<3; i++) {
			NPC slave = new SlaveForSale(Gender.getGenderFromUserPreferences(false, false), false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_ORAL, true);
			if (Math.random()<0.5f) {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", false), true, Main.game.getNpc(Finch.class));
			}
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsOral(slave);
		}
	}
	
	private static void applySlaveEffectsFemale(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_SUBMISSIVE);
		slave.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		slave.addFetish(Fetish.FETISH_ORAL_GIVING);
		slave.removePersonalityTraits(PersonalityCategory.SPEECH);
		slave.removePersonalityTrait(PersonalityTrait.SHY);
		if (Math.random() < 0.5f) {
			slave.addPersonalityTrait(PersonalityTrait.LEWD);
		}
		slave.setObedience(100);
	}
	
	private static void applySlaveEffectsMale(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_DOMINANT);
		slave.addFetish(Fetish.FETISH_CUM_STUD);
		slave.removePersonalityTrait(PersonalityTrait.COWARDLY);
		if (Math.random() < 0.5f) {
			slave.addPersonalityTrait(PersonalityTrait.BRAVE);
		}
		slave.setObedience(75);
	}
	
	private static void applySlaveEffectsAnal(GameCharacter slave) {
		slave.setAssWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
		slave.setAssBleached(true);
		slave.setAssCapacity(Util.random.nextInt((int) Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue(false)), true);
		slave.setAssVirgin(false);
		
		slave.addFetish(Fetish.FETISH_ANAL_GIVING);
		slave.addFetish(Fetish.FETISH_ANAL_RECEIVING);
		slave.setObedience(75);
	}

	private static void applySlaveEffectsVaginal(GameCharacter slave) {
		slave.setVaginaWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
		slave.setVaginaCapacity(Util.random.nextInt((int) Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue(false)), true);
		slave.setVaginaVirgin(true);
		
		slave.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		slave.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		slave.setObedience(75);
	}
	
	private static void applySlaveEffectsOral(GameCharacter slave) {
		slave.setFaceWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
		slave.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), true);
		slave.setFaceElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		slave.setLipSize(LipSize.FOUR_HUGE.getValue());
		slave.setFaceVirgin(false);

		slave.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		slave.addFetish(Fetish.FETISH_ORAL_GIVING);
		slave.setObedience(75);
	}
	
	private static boolean slavesInStocksPresent() {
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if((npc instanceof SlaveInStocks) && !Main.game.getPlayer().getCompanions().contains(npc)) {
				return true;
			}
		}
		return false;
	}
	
	private static void banishSlavesInStocks() {
		List<NPC> npcsToBanish = new ArrayList<>();
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if((npc instanceof SlaveInStocks) && !Main.game.getPlayer().getCompanions().contains(npc)) {
				npcsToBanish.add(npc);
			}
		}
		for(NPC npc : npcsToBanish) {
			Main.game.banishNPC(npc);
		}
	}
	
	
	public static void stocksUpdate() {
		float chanceToBeUsed = (12 - Main.game.getHourOfDay()%12)/12f;
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if((npc instanceof SlaveInStocks) && !Main.game.getPlayer().getCompanions().contains(npc)) {
				if (Math.random()<chanceToBeUsed) {
					if (!Main.game.getCharactersPresent().contains(npc)) {
						Gender gender = Gender.getGenderFromUserPreferences(false, true);
						
						Map<AbstractSubspecies, Integer> availableRaces = AbstractSubspecies.getGenericSexPartnerSubspeciesMap(gender);
						
						AbstractSubspecies subspecies = Subspecies.HUMAN;
						AbstractSubspecies halfDemonSubspecies = null;
						if (!availableRaces.isEmpty()) {
							subspecies = Util.getRandomObjectFromWeightedMap(availableRaces);
						}
						if (Math.random()<Main.getProperties().halfDemonSpawnRate) {
							halfDemonSubspecies = subspecies;
							subspecies = Subspecies.HALF_DEMON;
						}
						
						if (npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL)) {
							npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
						}
						if (npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL)) {
							npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
						}
						if (npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL) && npc.hasVagina()) {
							npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
						}
					}
				}
			}
		}
	}
	
	public static void stocksReset(){
		banishSlavesInStocks();
		
		for(int i=0; i<4; i++) {
			SlaveInStocks slave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(Math.random()>0.5f) {
				Main.game.getNpc(GenericFemaleNPC.class).addSlave(slave);
			} else {
				Main.game.getNpc(GenericMaleNPC.class).addSlave(slave);	
			}
			slave.initSlavePermissions();
		}
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleySlavesFreed, false);
	}
	
	public static void setupBidding(NPC slaveToBidOn) {
		biddingNPC = slaveToBidOn;
		biddingPrice = (int) (biddingNPC.getValueAsSlave(true)*0.5f);
		biddingRoundsTotal = Util.random.nextInt(3)+1;
		biddingRounds = 0;
		playerBidLeader = false;
		currentRivalBidder = SlaveAuctionBidder.generateNewSlaveAuctionBidder(biddingNPC);
	}
	
	private static void increaseBid() {
		biddingRounds++;
		if(biddingRounds!=biddingRoundsTotal) {
			biddingPrice = (int) (biddingPrice * (1+(0.8f*Math.random())));
			playerBidLeader = false;
		}
	}
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_SLAVE_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Import</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static List<GameCharacter> generateRandomStocksPartners(GameCharacter target, boolean forceTwoPartners) {
		List<GameCharacter> partners = new ArrayList<>();

		Gender g = Gender.getGenderFromUserPreferences(false, false);
		if(!g.getGenderName().isHasPenis() && !g.getGenderName().isHasVagina()) {
			if(g.isFeminine()) {
				g = Gender.getGenderFromUserPreferences(true, false);
			} else {
				g = Gender.getGenderFromUserPreferences(false, true);
			}
		}
		NPC partner = new GenericSexualPartner(g, WorldType.SLAVER_ALLEY, Main.game.getPlayer().getLocation(), false);
		setupPartner(partner);
		try {
			Main.game.addNPC(partner, false);
			partners.add(partner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(Math.random()<0.25f || forceTwoPartners) { // Second partner:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTwoPartners, true);
			partner = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, true), WorldType.SLAVER_ALLEY, Main.game.getPlayer().getLocation(), false);
			setupPartner(partner);
			try {
				Main.game.addNPC(partner, false);
				partners.add(0, partner); // Add at index 0, as this one will always be the one fucking from behind (as they have a penis).
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTwoPartners, false);
		}
		
		partners.get(0).removeFetish(Fetish.FETISH_ORAL_RECEIVING);
		partners.get(0).removeFetish(Fetish.FETISH_VAGINAL_GIVING);
		partners.get(0).removeFetish(Fetish.FETISH_ANAL_GIVING);
		List<AbstractFetish> fetishes = Util.newArrayListOfValues(
				target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
				&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
					?Fetish.FETISH_ORAL_RECEIVING
					:null,
				target.hasVagina()
				&& target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				&& partners.get(0).hasPenis()
					?Fetish.FETISH_VAGINAL_GIVING
					:null,
				Main.game.isAnalContentEnabled()
				&& target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
				&& partners.get(0).hasPenis()
					?Fetish.FETISH_ANAL_GIVING
					:null);
		if(!fetishes.isEmpty()) {
			partners.get(0).addFetish(Util.randomItemFrom(fetishes));
		}
		
		return partners;
	}
	
	private static void setupPartner(GameCharacter partner) {
		partner.setFetishDesire(Fetish.FETISH_EXHIBITIONIST, FetishDesire.THREE_LIKE);
		if(partner.hasPenis()) {
			partner.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			partner.setPenisVirgin(false);
		}
		if(partner.hasVagina()) {
			partner.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			partner.setVaginaVirgin(false);
		}
		partner.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		partner.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		partner.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		partner.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	}

	private static void banishRandomSexPartners() {
		if(randomSexPartners!=null) {
			for(GameCharacter npc : randomSexPartners) {
				Main.game.banishNPC((NPC) npc);
			}
		}
	}
	
	private static void updateSeanPregnancyReactions() {
		Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Sean.class), true);
		if(isCompanionDialogue()) {
			getMainCompanion().setCharacterReactedToPregnancy(Main.game.getNpc(Sean.class), true);
		}
	}
	
	private static boolean isSeanOfferingDeal(GameCharacter target) {
		return target.isFeminine()
				&& target.hasVagina()
				&& target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				&& !target.isPregnant()
				&& !target.hasIncubationLitter(SexAreaOrifice.VAGINA);
	}
	
	private static void applyLockedUpEffects(boolean includeCompanion, boolean willingCompanion) {
		if(isCompanionDialogue() && includeCompanion && (willingCompanion || Main.game.isInvoluntaryNTREnabled())) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionInStocks, true);
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal,
					getMainCompanion().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
						&& getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))
						&& isSeanOfferingDeal(getMainCompanion()));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
				Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), getMainCompanion(), false);
			}
			
			getMainCompanion().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
			
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionInStocks, false);
		}
		
		Main.game.getPlayer().setCaptive(true);
		Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
	}
	
	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static SexManagerInterface getRandomPartnerSexManager() {
		boolean twoPartners = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners);
		return new SexManagerDefault(
				SexPosition.STOCKS,
				!twoPartners
					?Util.newHashMapOfValues(new Value<>(randomSexPartners.get(0), randomSexPartners.get(0).hasFetish(Fetish.FETISH_ORAL_RECEIVING)?SexSlotStocks.RECEIVING_ORAL:SexSlotStocks.BEHIND_STOCKS))
					:Util.newHashMapOfValues(
								new Value<>(randomSexPartners.get(0), SexSlotStocks.BEHIND_STOCKS),
								new Value<>(randomSexPartners.get(1), SexSlotStocks.RECEIVING_ORAL)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				return getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!twoPartners) {
					if(!character.isPlayer()) {
						if(character.hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
							if(character.hasPenis()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
							}
						} else if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
						} else if(character.hasFetish(Fetish.FETISH_VAGINAL_GIVING)) {
							return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
						} else {
							if(character.hasPenis()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
							}
						}
					}
				}
				return super.getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.NONE;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
				if(character.isPlayer()) {
					return false;
				}
				return super.isAbleToRemoveOthersClothing(character, clothing);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return !equippingCharacter.isPlayer();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return !character.isPlayer();
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				if(twoPartners) {
					return Util.newHashMapOfValues(
							new Value<>(randomSexPartners.get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
							new Value<>(randomSexPartners.get(1), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)));
				} else {
					return Util.newHashMapOfValues(
							new Value<>(randomSexPartners.get(0), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)));
				}
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
		};
	}
	
	private static void generateCompanionSexParsingDescriptions() {
		SexType sexType;
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
			GameCharacter secondCharacter = randomSexPartners.get(1);
			if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(secondCharacter.hasPenis()) {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
				} else {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
				}
				
			} else {
				if(secondCharacter.hasPenis()) {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
				} else {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
				}
			}
			
			UtilText.addSpecialParsingString(
					secondCharacter.calculateGenericSexEffects(true, true, getMainCompanion(), sexType, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED),
					true);
		}
		
		GameCharacter character = randomSexPartners.get(0);
		if(character.hasFetish(Fetish.FETISH_ORAL_RECEIVING) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
			if(character.hasPenis()) {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
			} else {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
			}
			
		} else if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			
		} else if(character.hasFetish(Fetish.FETISH_VAGINAL_GIVING)) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			
		} else {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ASS);
				
			} else {
				if(character.hasPenis()) {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
				} else {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
				}
			}
		}
		
		UtilText.addSpecialParsingString(
				character.calculateGenericSexEffects(true, true, getMainCompanion(), sexType, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED),
				!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners));
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("Slaver Alley", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Slaver Alley", "Step through the gate and enter Slaver Alley.", PlaceType.SLAVER_ALLEY_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY = new DialogueNode("Gateway", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY"));

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_6_ADVERTISING) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTERS"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Step back out into Dominion's alleyways.", PlaceType.DOMINION_SLAVER_ALLEY.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_SLAVER_ALLEY, false);
					}
				};
				
			} else if(index==2 && Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_6_ADVERTISING) {
				return new Response("Posters", "Ask the guards for permission to put up the posters which Helena gave to you.", GATEWAY_POSTER_PERMISSION);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION = new DialogueNode("Gateway", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
//				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Pay ("+UtilText.formatAsMoney(100, "span")+")", "Pay the guards the hundred flames they're asking for.", GATEWAY_POSTER_PERMISSION_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_PAID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_END"));
							Main.game.getPlayer().removeItemByType(ItemType.ROLLED_UP_POSTERS);
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-100));
						}
					};
					
//				} else {
//					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(100, "span")+")", "You cannot afford to pay the guards the hundred flames they're asking for!", null);
//				}

			} else if(index==2) {
				if(Main.game.getPlayer().hasBreasts()) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
						if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
							return new Response("Groped",
									"Let the guards grope your exposed breasts in exchange for them letting you put up the posters.",
									GATEWAY_POSTER_PERMISSION_GROPED,
									Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF, Fetish.FETISH_EXHIBITIONIST),
									CorruptionLevel.THREE_DIRTY,
									null,
									null,
									null) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_GROPED"));
									Main.game.getPlayer().incrementLust(15, false);
								}
							};
							
						} else {
							return new Response("Flash breasts",
									"Fully expose your breasts to the guards in exchange for them letting you put up the posters.",
									GATEWAY_POSTER_PERMISSION_GROPED,
									Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF, Fetish.FETISH_EXHIBITIONIST),
									CorruptionLevel.THREE_DIRTY,
									null,
									null,
									null) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_FLASH_BREASTS"));
									Main.game.getPlayer().incrementLust(5, false);
								}
							};
						}
						
					} else {
						return new Response("Flash breasts", "You are unable to fully expose your breasts, so can't flash them at the guards in exchange for them letting you put up the posters...", null);
					}
				}
				
			}
//			else if(index==0) {
//				return new Response("Leave", "Tell the guards that you'll back with the money later...", GATEWAY) {
//					@Override
//					public void effects() {
//						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_LEAVE"));
//					}
//				};
//			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION_GROPED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Posters", "Now that you've got permission from the guards, you can put up the posters.", GATEWAY_POSTER_PERMISSION_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_END"));
						Main.game.getPlayer().removeItemByType(ItemType.ROLLED_UP_POSTERS);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION_END = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Scarlett", "Find out what it is Scarlett wants.", GATEWAY_POSTER_PERMISSION_END_RETURN);
			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Return", "Follow Scarlett and return to Helena's Boutique.", ScarlettsShop.ROMANCE_RETURN_AFTER_POSTERS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ALLEYWAY = new DialogueNode("Alleyway", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "ALLEYWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DESERTED_ALLEYWAY = new DialogueNode("Deserted alleyway", "", false) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "DESERTED_ALLEYWAY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_FEMALE = new DialogueNode("A Woman's Touch", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_FEMALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_MALE = new DialogueNode("Iron & Steel", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_MALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_ANAL = new DialogueNode("The Rear Entrance", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_ANAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_VAGINAL = new DialogueNode("White Lilies", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_VAGINAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_ORAL = new DialogueNode("Viva Voce", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_ORAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Slave Manager", "You don't have a slaver license, so can't buy or sell any slaves...", null);
				}
				return new Response("Slave Manager", "Enter the slave management screen.", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_STATUE = new DialogueNode("Statue of the Fallen Angel", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.statueTruthRevealed)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE_TRUTH"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE_IGNORANCE"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_EXCLUSIVE = new DialogueNode("Slave Rental Store", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_EXCLUSIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_BULK = new DialogueNode("Zaibatsu Exchange", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_BULK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_CAFE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "Enter the cafe and sit down at one of the tables.", MARKET_STALL_CAFE_INTERIOR);
			}
			return null;
		}
	};

	private static Map<Integer, AbstractItemType> getCafeItems() {
		return Util.newHashMapOfValues(
				new Value<>(1, ItemType.getItemTypeFromId("innoxia_race_human_vanilla_water")),
				new Value<>(2, ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_juice_box")),
				new Value<>(3, ItemType.getItemTypeFromId("innoxia_race_rabbit_bunny_juice")),
				new Value<>(4, ItemType.getItemTypeFromId("innoxia_race_squirrel_squirrel_java")),
				new Value<>(6, ItemType.getItemTypeFromId("innoxia_race_rabbit_bunny_carrot_cake")),
				new Value<>(7, ItemType.getItemTypeFromId("innoxia_race_rat_brown_rats_burger")),
				new Value<>(8, ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_salad")));
	}
	
	public static final DialogueNode MARKET_STALL_CAFE_INTERIOR = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNonCompanionCharactersPresent().isEmpty()) {
				NPC slave = new SlaveForSale(
						Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2 || Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3
							?Gender.M_P_MALE
							:Gender.F_V_B_FEMALE,
						false,
						false);
				try {
					Main.game.addNPC(slave, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				slave.setLocation(Main.game.getPlayer(), true);
				slave.removePersonalityTrait(PersonalityTrait.MUTE);
				slave.removePersonalityTrait(PersonalityTrait.STUTTER);
				slave.removePersonalityTrait(PersonalityTrait.SLOVENLY);
				slave.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				for(AbstractFetish fetish : Fetish.allFetishes) {
					if(slave.getFetishDesire(fetish).isNegative()) {
						slave.setFetishDesire(fetish, FetishDesire.TWO_NEUTRAL); // Remove all negative fetishes to make sure they don't start hating sex scenes
					}
				}
				
				if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE) { //Oral:
					applySlaveEffectsOral(slave);
					slave.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.METALLIC, PresetColour.COVERING_GOLD, false, PresetColour.COVERING_GOLD, false));
					slave.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					slave.addPersonalityTrait(PersonalityTrait.SHY);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_PURPLE_DARK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_FULLCUP_BRA, PresetColour.CLOTHING_PURPLE_DARK, false), true, slave);
					
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					
					slave.setPiercedEar(true);
					slave.setPiercedTongue(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), true, slave);
					slave.setPiercedLip(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", PresetColour.CLOTHING_GOLD, false), true, slave);
					
				} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2) { //Masculine:
					applySlaveEffectsMale(slave);
					if(slave.getPenisSize().getMedianValue()<PenisLength.FOUR_HUGE.getMedianValue()) {
						slave.setPenisSize(PenisLength.FOUR_HUGE);
					}
					slave.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
					slave.addPersonalityTrait(PersonalityTrait.CONFIDENT);
					slave.addFetish(Fetish.FETISH_IMPREGNATION);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_crotchless_chaps", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_NAVY, false), true, slave);
					
				} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3) { //Anal:
					applySlaveEffectsAnal(slave);
					slave.addFetish(Fetish.FETISH_SUBMISSIVE);
					slave.removePersonalityTrait(PersonalityTrait.CONFIDENT);
					
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, slave);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_waistcoat_shirt", PresetColour.CLOTHING_GREY, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_jacket", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_trousers", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_gloves", PresetColour.CLOTHING_WHITE, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_shoes", PresetColour.CLOTHING_BLACK, false), true, slave);

					slave.addStatusEffect(StatusEffect.CHASTITY_4, -1);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_chastity_cage", PresetColour.CLOTHING_PINK_LIGHT, false), true, slave);
					
				} else { //Feminine:
					applySlaveEffectsFemale(slave);
					if(slave.getBreastSize().getMeasurement()<CupSize.F.getMeasurement()) {
						slave.setBreastSize(CupSize.F);
					}
					slave.addPersonalityTrait(PersonalityTrait.CONFIDENT);
					slave.addPersonalityTrait(PersonalityTrait.LEWD);
					slave.setVaginaSquirter(true);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_VIRGIN_KILLER_SWEATER, PresetColour.CLOTHING_RED_BURGUNDY, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_elbow_length_gloves", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_platform_boots", PresetColour.CLOTHING_RED_BURGUNDY, false), true, slave);
					
					slave.setPiercedEar(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_PLATINUM, false), true, slave);
					slave.setPiercedNose(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", PresetColour.CLOTHING_PLATINUM, false), true, slave);
				}
			}
			NPC slave = Main.game.getNonCompanionCharactersPresent().get(0);
			Main.game.setActiveNPC(slave);
			if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE) { //Oral:
				slave.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
			}
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE_INTERIOR"));
			if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE) { //Oral:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe1Visited, true);
			} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2) { //Masculine:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe2Visited, true);
			} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3) { //Anal:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe3Visited, true);
			} else { // Feminine:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe4Visited, true);
			}
			if(slave.isVisiblyPregnant()) {
				slave.setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			}
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(slave, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().hasCompanions()) {
				if(index==0) {
					return "Self";
				} else if(index==1) {
					return "[com.Name]";
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "Exit the cafe and head back out into Slaver Alley.", MARKET_STALL_CAFE);
			}
			
			if(getCafeItems().containsKey(index)) {
				AbstractItemType itemType = getCafeItems().get(index);
				int itemValue = (int) (itemType.getValue()*1.8f);
				if(responseTab==0) {
					if(Main.game.getPlayer().getMoney()<itemValue) {
						return new Response(itemType.getName(false)+" ("+UtilText.formatAsMoneyUncoloured(itemValue, "span")+")",
								"You don't have enough money to order "+itemType.getDeterminer()+" "+itemType.getName(false)+"...",
								null);
						
					} else {
						return new Response(itemType.getName(false)+" ("+UtilText.formatAsMoney(itemValue, "span")+")",
										Main.game.getPlayer().hasCompanions()
											?"Order "+itemType.getDeterminer()+" "+itemType.getName(false)+" for yourself."
											:"Order "+itemType.getDeterminer()+" "+itemType.getName(false)+".",
											MARKET_STALL_CAFE_INTERIOR_NO_CONTENT) {
									@Override
									public void effects() {
										UtilText.addSpecialParsingString(itemType.getDeterminer(), true);
										UtilText.addSpecialParsingString(itemType.getName(false), false);
										UtilText.addSpecialParsingString(Util.intToString(itemValue), false);
										UtilText.addSpecialParsingString(itemType.getUseName(), false);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE_INTERIOR_ORDER"));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(itemType), Main.game.getPlayer(), false, true));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-itemValue));
									}
								};
					}
					
				} else if(responseTab==1) {
					if(Main.game.getPlayer().getMoney()<itemValue) {
						return new Response(itemType.getName(false), "You don't have enough money to order "+itemType.getDeterminer()+" "+itemType.getName(false)+"...", null);
						
					} else {
						return new Response(itemType.getName(false),
										"Order "+itemType.getDeterminer()+" "+itemType.getName(false)+" for [com.name].",
										MARKET_STALL_CAFE_INTERIOR_NO_CONTENT) {
									@Override
									public void effects() {
										UtilText.addSpecialParsingString(itemType.getDeterminer(), true);
										UtilText.addSpecialParsingString(itemType.getName(false), false);
										UtilText.addSpecialParsingString(Util.intToString(itemValue), false);
										UtilText.addSpecialParsingString(UtilText.parse("[com.verb("+itemType.getUseName()+")]"), false);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE_INTERIOR_ORDER_COMPANION"));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().getMainCompanion().useItem(Main.game.getItemGen().generateItem(itemType), Main.game.getPlayer().getMainCompanion(), false, true));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-itemValue));
									}
								};
					}
				}
			}
			
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_slaver_alley_cafe_interior").getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MARKET_STALL_CAFE_INTERIOR_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MARKET_STALL_CAFE_INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BOUNTY_HUNTERS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Enter", "Enter the establishment and take a look around inside...", BountyHunterLodge.ENTRANCE_INITITAL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AUCTION_BLOCK = new DialogueNode("Auctioning block", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_BLOCK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Approach", "Approach the auction block.", AUCTION_BLOCK_LIST);
				} else {
					return new Response("Approach", "You don't have a slaver license, so you're unable to participate in any slave auctions.", null);
				}
			}
			return null;
		}
	};
	
	
	public static final DialogueNode AUCTION_BLOCK_LIST = new DialogueNode("Auctioning block", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_BLOCK_LIST_START"));
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						+ "<b>Upcoming Public Auctions</b>"
						+ "<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
							+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slave"
							+ "</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>Starting Bid</b>"
							+"</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "Bid"
							+ "</div>"
						+ "</div>");
			
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			Collections.sort(charactersPresent, (e1, e2) -> e1.getName(true).compareTo(e2.getName(true)));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
								+ "<b>There are no upcoming auctions...</b>"
						+ "</div>");
				
			} else {
				int i=0;
				for(NPC slave : charactersPresent){
					boolean alternateBackground = i%2==0;
					
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
								+ "<div style='width:40%; float:left; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</b> - "
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span> "
									+ "<span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"
										+Util.capitaliseSentence((slave.isFeminine()?slave.getSubspecies().getSingularFemaleName(slave.getBody()):slave.getSubspecies().getSingularMaleName(slave.getBody())))
									+"</span>"
								+ "</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getObedience().getColour().toWebHexString()+";'>"+slave.getObedienceValue()+ "</b>"
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney(slave.getValueAsSlave(true), "span")
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*0.5f), "span")
								+"</div>"
								+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
									+ "<div id='"+slave.getId()+"_BID' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBid()+"</div></div>"
								+ "</div>"
							+ "</div>"
							));
					i++;
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Import", "View the character import screen.", AUCTION_IMPORT);
				
			} else if(index==0) {
				return new Response("Back", "Walk away from the auction block.", AUCTION_BLOCK);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUCTION_IMPORT = new DialogueNode("Auctioning block", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_START"));
			saveLoadSB.append(
					"<p>"
						+ "<table align='center'>");
			
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index==0) {
				return new Response("Back", "Return to the previous screen.", AUCTION_BLOCK_LIST);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUCTION_BIDDING = new DialogueNode("Auctioning block", "", true) {
		
		@Override
		public boolean isContinuesDialogue() {
			return biddingRounds!=0;
		}
		
		@Override
		public String getContent() {
			if(biddingRounds==0) {
				UtilText.addSpecialParsingString(currentRivalBidder.getName(true), true);
				UtilText.addSpecialParsingString(UtilText.parseNPCSpeech(currentRivalBidder.getRandomBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG)), false);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice, "span"), false);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice+100, "span"), false);

				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_START", biddingNPC);
				
			} if(biddingRounds==biddingRoundsTotal) {
				if(playerBidLeader) {
					UtilText.addSpecialParsingString(currentRivalBidder.getName(false), true);
					UtilText.addSpecialParsingString(UtilText.parseNPCSpeech(currentRivalBidder.getRandomFailedBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG)), false);
					UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice, "span"), false);
					
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_VICTORY", biddingNPC);
					
				} else {
					UtilText.addSpecialParsingString(currentRivalBidder.getName(false), true);
					UtilText.addSpecialParsingString(UtilText.parseNPCSpeech(currentRivalBidder.getRandomSuccessfulBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG)), false);

					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_DEFEAT", biddingNPC);
				}
				
			} else {
				UtilText.addSpecialParsingString(currentRivalBidder.getName(false), true);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice, "span"), false);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice+100, "span"), false);
				
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_CONTINUE", biddingNPC);
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(biddingRounds==biddingRoundsTotal) {
				if(index==1) {
					if(playerBidLeader) {
						return new Response("Continue", UtilText.parse(biddingNPC, "You won the bidding! [npc.Name] is now ready for collection from Slavery Administration."), AUCTION_BLOCK) {
							@Override
							public void effects() {
							}
						};
					} else {
						return new Response(UtilText.parse(biddingNPC, "[npc.Name] sold"), "You didn't win the auction, but there's always next time, right?", AUCTION_BLOCK) {
							@Override
							public void effects() {
								Main.game.getNpc(Finch.class).removeSlave(biddingNPC);
								Main.game.banishNPC(biddingNPC);
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=biddingPrice+100) {
						return new Response("Bid "+UtilText.formatAsMoney(biddingPrice+100, "span"), UtilText.parse(biddingNPC, "Place a bid of "+(biddingPrice+100)+" flames for [npc.name]."), AUCTION_BIDDING) {
							@Override
							public void effects() {
								biddingPrice += 100;
								playerBidLeader = true;
								increaseBid();
								if(biddingRounds==biddingRoundsTotal) {
									Main.game.getPlayer().addSlave(biddingNPC);
									biddingNPC.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
									Main.game.getPlayer().incrementMoney(-biddingPrice);
								}
							}
						};
					} else {
						return new Response("Bid "+UtilText.formatAsMoneyUncoloured(biddingPrice+100, "span"), "You can't afford a bid of "+(biddingPrice+100)+" flames, so you'll have to let this slave go to someone else.", null);
					}
					
				} else if(index==2) {
					return new Response("Stop bidding", UtilText.parse(biddingNPC, "Stop bidding, which will allow someone else to buy [npc.name]."), AUCTION_BIDDING) {
						@Override
						public void effects() {
							playerBidLeader = false;
							biddingRounds=biddingRoundsTotal;
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS = new DialogueNode("Public Stocks", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleySlavesFreed)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_FREED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS"));

				List<String> sexAvailability = new ArrayList<>();

				List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
				charactersPresent.removeIf((npc) -> npc instanceof Sean);
				for(NPC npc : charactersPresent) {
					UtilText.nodeContentSB.append(UtilText.parse(npc, 
							"<p>"
								+ "[npc.Name]," + (npc.getOwner().isPlayer()?" [style.boldArcane(who is your slave)], and is":"")
								+ " <span style='color:"+npc.getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span>"
										+ " <span style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</span>, has been marked as available for"));
					
					sexAvailability.clear();
					if(npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL)) {
						sexAvailability.add(" <b style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>oral</b>");
					}
					if(npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL)) {
						sexAvailability.add(" <b style='color:"+PresetColour.BASE_PINK.toWebHexString()+";'>vaginal</b>");
					}
					if(npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL)) {
						sexAvailability.add(" <b style='color:"+PresetColour.BASE_PINK_DEEP.toWebHexString()+";'>anal</b>");
					}
					
					if(!sexAvailability.isEmpty()) {
						UtilText.nodeContentSB.append(
								Util.stringsToStringList(sexAvailability, false)
								+" use.</p>");
					} else {
						UtilText.nodeContentSB.append(
								" [style.boldBad(no penetration at all)].</p>");
					}
				}
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_ENFORCER"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Solo";
				
			} else if(index==1) {
				return "Threesome";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
			charactersPresent.removeIf((npc) -> npc instanceof Sean);
			GameCharacter companion = getMainCompanion();

			if((responseTab==0 || isCompanionDialogue()) && index==1) {
				return new Response("[sean.Name]",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyComplained)
							?"You see [sean.name] watching over the area, so perhaps you could go and talk to [sean.herHim] again..."
							:"There appears to be an Enforcer watching over the area. Perhaps you could go and talk to him...",
							PUBLIC_STOCKS_SEAN);
			}
			
			if(index>1) {
				if(responseTab==0) {
					if(index-1 <= charactersPresent.size()) {
						GameCharacter slave = charactersPresent.get(index-2);
						boolean ownedByPlayer = slave.isSlave() && slave.getOwner().isPlayer();
						return new ResponseSex(
								UtilText.parse(slave,"Use [npc.name]"),
								UtilText.parse(slave, "Walk up to [npc.name] and start fucking [npc.herHim] right here in public..."),
								false,
								false,
								new SMStocks(
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS)),
										Util.newHashMapOfValues(new Value<>(slave, SexSlotStocks.LOCKED_IN_STOCKS))),
								Util.newArrayListOfValues(companion),
								null,
								AFTER_STOCKS_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEX_SOLO", slave, companion)) {
							@Override
							public void effects() {
								stocksSlaveTargeted = ((NPC) slave);
							}
						};
					}
					
				} else if(isCompanionDialogue()) {
					if(index-1 <= charactersPresent.size()) {
						GameCharacter slave = charactersPresent.get(index-2);
						boolean ownedByPlayer = slave.isSlave() && slave.getOwner().isPlayer();
						
						if(companion.isAbleToRefuseSexAsCompanion()) {
							if(!companion.isAttractedTo(Main.game.getPlayer())) {
								return new Response(UtilText.parse(slave, "Use [npc.name]"),
										UtilText.parse(companion, "You can tell that [npc.name] isn't at all interested in having sex with you, and as [npc.she] isn't your slave, you can't make [npc.herHim]..."),
										null);
							}
							if(!companion.isAttractedTo(slave)) {
								return new Response(UtilText.parse(slave, "Use [npc.name]"),
										UtilText.parse(slave, companion, "You can tell that [npc2.name] isn't at all interested in having sex with [npc.name], and as [npc2.she] isn't your slave, you can't make [npc.herHim]..."),
										null);
							}
							if(companion.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								return new Response(UtilText.parse(slave, "Use [npc.name]"),
										UtilText.parse(companion, "[npc.Name] has a negative attitude towards the '"+Fetish.FETISH_EXHIBITIONIST.getName(companion)+"' fetish, and as such, is unwilling to have sex in public."
												+ " As [npc.she] isn't your slave, you can't make [npc.herHim] do it, either..."),
										null);
							}
						}
						return new ResponseSex(
								UtilText.parse(slave, "Use [npc.name]"),
								UtilText.parse(slave, companion, "Walk up to [npc.name] and have [npc2.name] join you in fucking [npc.herHim] right here in public..."),
								false,
								false,
								new SMStocks(
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS),
												new Value<>(companion, SexSlotStocks.RECEIVING_ORAL)),
										Util.newHashMapOfValues(new Value<>(slave, SexSlotStocks.LOCKED_IN_STOCKS))),
								null,
								null,
								AFTER_STOCKS_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEX_THREESOME", slave, companion)) {
							@Override
							public void effects() {
								stocksSlaveTargeted = ((NPC) slave);
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_STOCKS_SEX = new DialogueNode("Public Stocks", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(stocksSlaveTargeted, "Having finished with [npc.name], you step away from [npc.herHim] and prepare to continue on your way.");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_STOCKS_SEX", stocksSlaveTargeted);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						stocksSlaveTargeted = null;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_SEAN = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Sean.class).setGenericName(UtilText.parse("Constable [sean.surname]"));
			Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Sean.class));
			
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentDialogueNode()==PUBLIC_STOCKS_TALK) {
					return new Response("Talk", "You are already talking to [sean.name].", null);
				}
				return new Response("Talk",
						"Ask [sean.name] why he's guarding this area, and whether anything interesting has happened recently.",
						PUBLIC_STOCKS_TALK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_TALK"));
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanIntroduced, true);
						if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTalkedBraxReveal, true);
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTalked, true);
						updateSeanPregnancyReactions();
					}
				};
				
			} if(index==2) {
				if(Main.game.getCurrentDialogueNode()==PUBLIC_STOCKS_TALK_FREED_SLAVES) {
					return new Response("Freed slaves", "You are already asking [sean.name] about the situation regarding the freed slaves.", null);
				}
				if(!slavesInStocksPresent()) {
					return new Response("Freed slaves",
							"Ask [sean.name] if he's going to get in trouble for letting you free the slaves in the stocks.",
							PUBLIC_STOCKS_TALK_FREED_SLAVES) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_TALK_FREED_SLAVES"));
							
							if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTalkedFreedSlaves, true);
							updateSeanPregnancyReactions();
						}
					};
				}
				return new Response("Complain",
						"You don't like the idea of slaves being publicly used. Complain to [sean.name] about it...",
						PUBLIC_STOCKS_COMPLAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanIntroduced, true);
						if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
						}
						updateSeanPregnancyReactions();
					}
				};
				
			} else if(index==3 && Main.game.getNpc(Sean.class).getTotalTimesHadSex(Main.game.getPlayer())>0) {
				if(!Main.game.getNpc(Sean.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response("Seduce", "[sean.Name] is not attracted to you, and so attempting to seduce him would be pointless.", null);
				}
				return new Response("Seduce",
						"Playfully tease [sean.name] and ask him if he wants to have some fun with you again...",
						PUBLIC_STOCKS_SEAN_SEDUCE) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						updateSeanPregnancyReactions();
					}
				};
				
				
			} else if(index==0) {
				return new Response("Step back", "Decide against asking anything of [sean.name] and instead step back.", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanIntroduced, true);
						if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
						}
						updateSeanPregnancyReactions();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PUBLIC_STOCKS_SEAN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_TALK_FREED_SLAVES = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PUBLIC_STOCKS_SEAN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_SEAN_SEDUCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
//				if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
//					return new Response(
//							isCompanionDialogue()
//								?"Alleyway (solo)"
//								:"Alleyway",
//							"You have not yet discovered the hidden alleyway, so cannot suggest going there to [sean.name]!"
//									+ "<br/>[style.italics(You will discover the hidden alleyway if you accept his challenge (you are not forced to fight him when choosing that option).)]",
//							null);
//				}
				return new ResponseSex(
						isCompanionDialogue()
							?"Alleyway (solo)"
							:"Alleyway",
						"Tell [sean.name] that you want to go to the hidden alleyway and have sex with him there...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.TWO_HORNY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Sean.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								null),
						AFTER_SEAN_SEDUCE_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_ALLEYWAY")) {
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
							Cell c = Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getX()+2,
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getY());
							c.getPlace().setPlaceType(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						}
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasVagina()) {
					return new Response("Stocks breeding", "You do not have a vagina, so cannot be bred by [sean.name] in the stocks.", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Stocks breeding", "[sean.Name] cannot gain access to your vagina, so you cannot be bred by him in the stocks.", null);
				}
				if(Main.game.getPlayer().isPregnant()) {
					return new Response("Stocks breeding", "You are already pregnant, so cannot be bred by [sean.name] in the stocks.", null);
				}
				if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("Stocks breeding", "Your womb is full of eggs, so you cannot be bred by [sean.name] in the stocks.", null);
				}
				return new ResponseSex("Stocks breeding",
						"Ask [sean.name] if he'd like to lock you in the stocks and breed you in front of members of the public.",
						Util.newArrayListOfValues(Fetish.FETISH_EXHIBITIONIST, Fetish.FETISH_PREGNANCY, Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.FOUR_LUSTFUL,
						null,
						null,
						null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_SEAN_SEDUCE_STOCKS_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_BREEDING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
						Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal, false);
						Main.game.getPlayer().setCaptive(true);
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
					}
				};
				
			} else if(index==6 && isCompanionDialogue()) {
//				if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
//					return new Response(
//							"Alleyway (threesome",
//							"You have not yet discovered the hidden alleyway, so cannot suggest going there to [sean.name]!"
//									+ "<br/>[style.italics(You will discover the hidden alleyway if you accept his challenge (you are not forced to fight him when choosing that option).)]",
//							null);
//				}
				if(!Main.game.getNpc(Sean.class).isAttractedTo(getMainCompanion())) {
					return new Response("Alleyway (threesome)", "[sean.Name] is not attracted to [com.name], so is unwilling to have a threesome with [com.herHim].", null);
				}
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Alleyway (threesome)", "[com.Name] is not attracted to you, so is unwilling to have a threesome with you and [sean.name].", null);
					}
					if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))) {
						return new Response("Alleyway (threesome)", "[com.Name] is not attracted to [sean.name], so is unwilling to have a threesome with you and him.", null);
					}
//				}
				return new ResponseSex(
						"Alleyway (threesome)",
						"Tell [sean.name] that you and [com.name] want to go to the hidden alleyway and have sex with him there...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.TWO_HORNY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Sean.class)),
								Util.newArrayListOfValues(Main.game.getPlayer(), getMainCompanion()),
								null,
								null),
						AFTER_SEAN_SEDUCE_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_ALLEYWAY_THREESOME")){
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
							Cell c = Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getX()+2,
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getY());
							c.getPlace().setPlaceType(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						}
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
					}
				};
				
			} else if(index==7 && isCompanionDialogue()) {
				if(!Main.game.getPlayer().hasVagina()) {
					return new Response("Double breeding", "You do not have a vagina, so cannot be bred by [sean.name] in the stocks.", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Double breeding", "[sean.Name] cannot gain access to your vagina, so you cannot be bred by him in the stocks.", null);
				}
				if(Main.game.getPlayer().isPregnant()) {
					return new Response("Double breeding", "You are already pregnant, so cannot be bred by [sean.name] in the stocks.", null);
				}
				if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("Double breeding", "Your womb is full of eggs, so you cannot be bred by [sean.name] in the stocks.", null);
				}
				if(!getMainCompanion().hasVagina()) {
					return new Response("Double breeding", "[com.Name] does not have a vagina, so cannot be bred by [sean.name] in the stocks.", null);
				}
				if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Double breeding", "[sean.Name] cannot gain access to [com.namePos] vagina, so [com.she] cannot be bred by him in the stocks.", null);
				}
				if(getMainCompanion().isPregnant()) {
					return new Response("Double breeding", "[com.Name] is already pregnant, so cannot be bred by [sean.name] in the stocks.", null);
				}
				if(getMainCompanion().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("Double breeding", "[com.NamePos] womb is full of eggs, so [com.she] cannot be bred by [sean.name] in the stocks.", null);
				}
				if(!Main.game.getNpc(Sean.class).isAttractedTo(getMainCompanion())) {
					return new Response("Double breeding", "[sean.Name] is not attracted to [com.name], so is unwilling to breed [com.herHim].", null);
				}
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Double breeding", "[com.Name] is not attracted to you, and so is unwilling to participate in the breeding.", null);
					}
					if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))) {
						return new Response("Double breeding", "[com.Name] is not attracted to [sean.name], and so is unwilling to participate in the breeding.", null);
					}
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
						return new Response("Double breeding",
								"As [com.name] does not have a positive view of the '"+Fetish.FETISH_EXHIBITIONIST.getName(getMainCompanion())+"' fetish, [com.she] will not agree to do this."
										+ " [sean.Name] will also not want to force [com.herHim] into anything [com.she] doesn't want to do.",
								null);
					}
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
						return new Response("Double breeding",
								"As [com.name] does not have a positive view of the '"+Fetish.FETISH_PREGNANCY.getName(getMainCompanion())+"' fetish, [com.she] will not agree to do this."
										+ " [sean.Name] will also not want to force [com.herHim] into anything [com.she] doesn't want to do.",
								null);
					}
//				}
				return new ResponseSex("Double breeding",
						"Ask [sean.name] if he'd like to lock you and [com.name] in the stocks and breed the two of you in front of members of the public.",
						Util.newArrayListOfValues(Fetish.FETISH_EXHIBITIONIST, Fetish.FETISH_PREGNANCY, Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.FOUR_LUSTFUL,
						null,
						null,
						null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_SEAN_SEDUCE_STOCKS_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_BREEDING_THREESOME")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
						Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
						Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), getMainCompanion(), false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal, true);
						Main.game.getPlayer().setCaptive(true);
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
						getMainCompanion().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
					}
				};
				
			} else if(index==0) {
				return new Response("Back out", "Decide against having sex with [sean.name]...", PUBLIC_STOCKS_SEAN_SEDUCE_BACK_OUT) {
					@Override
					public void effects() {
						if(Main.game.getNpc(Sean.class).getAffection(Main.game.getPlayer())>AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Sean.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_SEAN_SEDUCE_BACK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_BACK_OUT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PUBLIC_STOCKS_SEAN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEAN_SEDUCE_ALLEYWAY_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
				return "You, [com.name], and [sean.name] have finished having sex with one another...";
			}
			return "You and [sean.name] have finished having sex with one another...";
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_ALLEYWAY_THREESOME_SEX");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_ALLEYWAY_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						"Head back out into Slaver Alley.",
						PlaceType.SLAVER_ALLEY_PATH.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_ALLEYWAY_SEX_FINISHED"));
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PATH, false);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyVisitedHiddenAlleyway, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEAN_SEDUCE_STOCKS_BREEDING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				return 10*60;
			} else {
				return 2*60;
			}
		}
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Sean.class).calculateGenericSexEffects(
								true, true, getMainCompanion(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.LIMITED_DESCRIPTION_NEEDED),
						true);
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_THREESOME");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
					return new Response("Wait", "[sean.Name] leaves you and [com.name] locked up in the stocks for a while longer.", AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED) {
						@Override
						public void effects() {
							Main.game.getPlayer().performImpregnationCheck(true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
							getMainCompanion().performImpregnationCheck(true);
							getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
							updateSeanPregnancyReactions();
						}
					};
					
				} else {
					return new Response("Wait", "[sean.Name] leaves you locked up in the stocks a while longer.", AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED) {
						@Override
						public void effects() {
							Main.game.getPlayer().performImpregnationCheck(true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
							updateSeanPregnancyReactions();
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_THREESOME_FINISHED");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
					return new Response("Released", "[sean.Name] moves to release you and [com.name] from the stocks.", PUBLIC_STOCKS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_THREESOME_FINISHED_END"));
							Main.game.getPlayer().setCaptive(false);
							Main.game.getPlayer().equipAllClothingFromHoldingInventory();
							getMainCompanion().equipAllClothingFromHoldingInventory();
						}
					};
				} else {
					return new Response("Released", "[sean.Name] moves to release you from the stocks.", PUBLIC_STOCKS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED_END"));
							Main.game.getPlayer().setCaptive(false);
							Main.game.getPlayer().equipAllClothingFromHoldingInventory();
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
								getMainCompanion().equipAllClothingFromHoldingInventory();
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyComplained)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Step back", "Do as [sean.name] says and step back.", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_STEP_BACK"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Persist", "Ignore [sean.namePos] command and demand that the slaves be released.", PUBLIC_STOCKS_COMPLAIN_PERSIST);
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_PERSIST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyComplained)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_PERSIST_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_PERSIST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Decline", "Decide not to take up [sean.namePos] challenge and step back.", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_PERSIST_STEP_BACK"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Challenge", "Accept [sean.namePos] challenge.", PUBLIC_STOCKS_COMPLAIN_CHALLENGE) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Cell c = Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(
								Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getX()+2,
								Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getY());
						
						c.getPlace().setPlaceType(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);

						NPC sean = Main.game.getNpc(Sean.class);
						sean.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						// Sean takes his stabproof vest, utility belt, and beret off:
						sean.unequipClothingIntoInventory(sean.getClothingInSlot(InventorySlot.TORSO_OVER), true, sean);
						sean.unequipClothingIntoInventory(sean.getClothingInSlot(InventorySlot.HIPS), true, sean);
						sean.unequipClothingIntoInventory(sean.getClothingInSlot(InventorySlot.HEAD), true, sean);

						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyVisitedHiddenAlleyway)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_REPEAT"));
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyVisitedHiddenAlleyway, true);
						
						sean.setPlayerKnowsName(true);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getMoney()<5000) {
					return new Response("Bribe ("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"You don't have enough money to bribe [sean.name]...<br/>[style.italicsBad(Requires 5000 flames!)]",
							null);
				}
				return new Response("Bribe ("+UtilText.formatAsMoney(5000, "span")+")",
						"Offer [sean.name] a bribe to look the other way while you release the slaves.",
						PUBLIC_STOCKS_COMPLAIN_BRIBE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyBribed)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_BRIBE_REPEAT"));
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_BRIBE"));

						Main.game.getNpc(Sean.class).setPlayerKnowsName(true);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-5000));
					}
				};
				
			} else if(index==4) {
				return new Response(
						isCompanionDialogue()
							?"Take place (solo)"
							:"Take place",
						isCompanionDialogue()
							?"Do as [sean.name] suggests and take the place of one of the slaves, leaving [com.name] to wait and watch nearby..."
							:"Do as [sean.name] suggests and take the place of one of the slaves.",
						PUBLIC_STOCKS_LOCKED_UP) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
 					@Override
					public void effects() {
 						SlaveInStocks tempSlave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTookPlace)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE_REPEAT", tempSlave));
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE", tempSlave));

						Main.game.getNpc(Sean.class).setPlayerKnowsName(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						
						applyLockedUpEffects(false, false);
					}
				};
				
			} else if(index==5 && isCompanionDialogue()) {
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
						return new Response("Take place (both)",
								"As [com.name] does not have a positive view of the '"+Fetish.FETISH_EXHIBITIONIST.getName(getMainCompanion())+"' fetish, [com.she] will not agree to do this."
										+ " [sean.Name] will also not want to force [com.herHim] into anything [com.she] doesn't want to do.",
								null);
					}
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
						return new Response("Take place (both)",
								"As [com.name] does not have a positive view of the '"+Fetish.FETISH_NON_CON_SUB.getName(getMainCompanion())+"' fetish, [com.she] will not agree to do this."
										+ " [sean.Name] will also not want to force [com.herHim] into anything [com.she] doesn't want to do.",
								null);
					}
//				}
				return new Response("Take place (both)",
						"Do as [sean.name] suggests and get [com.name] to join you in taking the place of two of the slaves.",
						PUBLIC_STOCKS_LOCKED_UP) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
 					@Override
					public void effects() {
 						SlaveInStocks tempSlave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
 						SlaveInStocks tempSlave2 = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
 						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTookPlace)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE_BOTH_REPEAT", tempSlave, tempSlave2));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE_BOTH", tempSlave, tempSlave2));
						}
						
						Main.game.getNpc(Sean.class).setPlayerKnowsName(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						
						applyLockedUpEffects(true, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_CHALLENGE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "Start your fight with [sean.name]!", Main.game.getNpc(Sean.class));
				
			} else if(index==0) {
				return new Response("Back out", "Decide against fighting [sean.name] after all...", 
						PlaceType.SLAVER_ALLEY_PATH.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_BACK_OUT"));
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PATH, false);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						if(Main.game.getNpc(Sean.class).getAffection(Main.game.getPlayer())>AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue()) {
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Sean.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Free slaves", "Head back into the square and unlock all of the stocks.", PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY_FREE_SLAVES"));
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Seduce",
							"[sean.Name] isn't attracted to you, so you can't seduce him. It would also be an extremely bad idea to force yourself upon him, as that would no doubt quickly lead to you being declared a wanted criminal.",
							null);
				}
				return new ResponseSex(
						"Seduce",
						"Tell [sean.name] that you were only really interested in getting some alone time with him..."
								+ "<br/>[style.italicsMinorBad(Having sex with [sean.name] will not leave you with enough time in which to also free the slaves.)]",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Sean.class)),
								Util.newArrayListOfValues(getMainCompanion()),
								null),
						AFTER_SEAN_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY_SEX"));
				
			} else if(index==3 && isCompanionDialogue()) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("Seduce (threesome)",
							"[sean.Name] isn't attracted to you, so you can't seduce him. It would also be an extremely bad idea to force yourself on him, as that would no doubt quickly lead to you being declared a wanted criminal.",
							null);
				}
				if(!getMainCompanion().isFeminine()) {
					return new Response("Seduce (threesome)",
							"[sean.Name] isn't attracted to [com.name], so you can't seduce him into having a threesome."
									+ " It would also be an extremely bad idea to force yourselves on him, as that would no doubt quickly lead to both you and [com.name] being declared wanted criminals.",
							null);
				}
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Seduce (threesome)",
								"You can tell that [com.name] isn't at all interested in having sex with you...",
								null);
					}
					if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))) {
						return new Response("Seduce (threesome)",
								"You can tell that [com.name] isn't at all interested in having sex with [sean.name]...",
								null);
					}
//				}
				return new ResponseSex(
						"Seduce (threesome)",
						"Tell [sean.name] that you and [com.name] were only really interested in getting some alone time with him..."
								+ "<br/>[style.italicsMinorBad(Having a threesome with [sean.name] and [com.name] will not leave you with enough time in which to also free the slaves.)]",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										getMainCompanion()),
								Util.newArrayListOfValues(
										Main.game.getNpc(Sean.class)),
								null,
								null),
						AFTER_SEAN_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY_SEX_BOTH"));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEAN_ALLEYWAY_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
				return "You, [com.name], and [sean.name] have finished having sex with one another...";
			}
			return "You and [sean.name] have finished having sex with one another...";
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_ALLEYWAY_SEX_THREESOME");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_ALLEYWAY_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						"Head back out into Slaver Alley",
						PlaceType.SLAVER_ALLEY_PATH.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_ALLEYWAY_SEX_FINISHED"));
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PATH, false);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_CHALLENGE_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
			Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
			Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Locked up",
						isCompanionDialogue()
							?"You and [com.name] are locked up in the stocks..."
							:"You are locked up in the stocks...",
						PUBLIC_STOCKS_LOCKED_UP) {
					@Override
					public void effects() {
						applyLockedUpEffects(true, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_BRIBE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Free slaves", "Head back into the square and unlock all of the stocks.", PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES) {
					@Override
					public void effects() {
						Main.game.getNpc(Sean.class).setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_CAFE, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyBribed, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlavesInStocks();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Step back",
						"Do as the guards say and step back while they try to figure out what happened.",
						PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES_END) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleySlavesFreed, true);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES_END = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getPlayerCell().getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(isSeanOfferingDeal(Main.game.getPlayer())) {
				if(isCompanionDialogue() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks) && isSeanOfferingDeal(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_DEAL_BOTH");
					
				} else {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_DEAL");
				}
				
			} else {
				if(isCompanionDialogue() && isSeanOfferingDeal(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_DEAL_COMPANION");
					
				} else {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP");
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isSeanOfferingDeal(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("Accept",
							"Swallow the "+ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false)+" that [sean.name] is offering you, thereby accepting his deal of protection in exchange for letting him try to impregnate you...",
							PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_ACCEPTED_DEAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyAcceptedDeal, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTookPlace, true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
							Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==2) {
					return new Response("Refuse",
							"Refuse to swallow the "+ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false)+" and instead accept the fact that you're going to be used by members of the public.",
							PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_REFUSED_DEAL"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_APPROACH", randomSexPartners));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyAcceptedDeal, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTookPlace, true);
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new Response("Continue",
							"All you can do is wait and see if you're going to be used by members of the public...",
							PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_APPROACH", randomSexPartners));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyAcceptedDeal, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTookPlace, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				if(index==1) {
					return new ResponseSex("Get bred",
							"[sean.Name] steps up behind you and prepares to start breeding you.",
							true,
							false,
							new SexManagerDefault(
									SexPosition.STOCKS,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.NONE;
									}
									return super.getSexControl(character);
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
									if(character.isPlayer()) {
										return false;
									}
									return super.isAbleToRemoveOthersClothing(character, clothing);
								}
								@Override
								public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
									return !equippingCharacter.isPlayer();
								}
								@Override
								public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
									return !character.isPlayer();
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
								@Override
								public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
									return new ArrayList<>();
								}
								@Override
								public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
									return OrgasmBehaviour.CREAMPIE;
								}
							},
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX_START_BREEDING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex("Used",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
								?"The two strangers prepare to start using you..."
								:"The stranger prepares to start using you...",
							false,
							false,
							getRandomPartnerSexManager(),
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX", randomSexPartners));
				
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return "Having filled your [pc.pussy+] with his cum, [sean.name] is finished with you and steps back.";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				return "Having had their fun, the strangers step back...";
				
			} else {
				return UtilText.parse(randomSexPartners.get(0), "Having had [npc.her] fun, the stranger steps back...");
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX_BREEDING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX_RANDOMS", randomSexPartners);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
						return new Response("[com.NamePos] turn",
								"[sean.Name] prepares to start fucking [com.name]...",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_FIRST_SEX) {
							@Override
							public void effects() {
								UtilText.addSpecialParsingString(
										Main.game.getNpc(Sean.class).calculateGenericSexEffects(
												true, true, getMainCompanion(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.LIMITED_DESCRIPTION_NEEDED),
										true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX_COMPANION_BREEDING"));
							}
						};
						
					} else {
						return new Response("[com.NamePos] turn",
								"A stranger moves up to [com.name] and prepares to start fucking [com.herHim]...",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_FIRST_SEX) {
							@Override
							public void effects() {
								banishRandomSexPartners();
								randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(getMainCompanion(), false);
								generateCompanionSexParsingDescriptions();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX_COMPANION", randomSexPartners));
							}
						};
					}
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
						return new Response("Wait",
								"You can't do anything but wait until [sean.name] is ready to fuck you again...",
								PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_BREEDING"));
							}
						};
						
					} else {
						return new Response("Wait",
								"You can't do anything but wait and see if you're going to get used again...",
								PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
							@Override
							public void effects() {
								banishRandomSexPartners();
								randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_RANDOMS", randomSexPartners));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_COMPANION_FIRST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
					return new Response("Wait",
							"You can't do anything but wait until [sean.name] is ready to fuck you again...",
							PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_BREEDING"));
						}
					};
					
				} else {
					return new Response("Wait",
							"You can't do anything but wait and see if you're going to get used again...",
							PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_RANDOMS", randomSexPartners));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				if(index==1) {
					return new ResponseSex("Bred again",
							"[sean.Name] steps up behind you and prepares to start breeding you.",
							true,
							false,
							new SexManagerDefault(
									SexPosition.STOCKS,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.NONE;
									}
									return super.getSexControl(character);
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
									if(character.isPlayer()) {
										return false;
									}
									return super.isAbleToRemoveOthersClothing(character, clothing);
								}
								@Override
								public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
									return !equippingCharacter.isPlayer();
								}
								@Override
								public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
									return !character.isPlayer();
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
								@Override
								public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
									return new ArrayList<>();
								}
								@Override
								public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
									return OrgasmBehaviour.CREAMPIE;
								}
							},
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_START_BREEDING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex("Used again",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
								?"The two strangers prepare to start using you..."
								:"The stranger prepares to start using you...",
							false,
							false,
							getRandomPartnerSexManager(),
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX", randomSexPartners));
				
				}
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return "Having filled your [pc.pussy+] with his cum, [sean.name] is finished with you and steps back.";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				return "Having had their fun, the strangers step back...";
				
			} else {
				return UtilText.parse(randomSexPartners.get(0), "Having had [npc.her] fun, the stranger steps back...");
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX_BREEDING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX_RANDOMS", randomSexPartners);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
						return new Response("[com.NamePos] turn",
								"[sean.Name] moves over to [com.name] and prepares to start fucking [com.herHim]...",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_SECOND_SEX) {
							@Override
							public void effects() {
								Main.game.getNpc(Sean.class).calculateGenericSexEffects(
										true, true, getMainCompanion(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.LIMITED_DESCRIPTION_NEEDED);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX_COMPANION_BREEDING"));
							}
						};
						
					} else {
						return new Response("[com.NamePos] turn",
								"A pair of strangers move up to [com.name] and prepare to start fucking [com.herHim]...",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_SECOND_SEX) {
							@Override
							public void effects() {
								banishRandomSexPartners();
								randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(getMainCompanion(), false);
								generateCompanionSexParsingDescriptions();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX_COMPANION", randomSexPartners));
							}
						};
					}
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
						return new Response("Wait",
								"There's nothing for you to do but wait until [sean.name] returns and releases you from the stocks...",
								PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
							@Override
							public void effects() {
								Main.game.getPlayer().performImpregnationCheck(true);
								Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_BREEDING"));
								updateSeanPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Wait",
								"There's nothing for you to do but wait until [sean.name] returns and releases you from the stocks...",
								PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED"));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_COMPANION_SECOND_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
					return new Response("Wait",
							"There's nothing for you to do but wait until [sean.name] returns and releases you from the stocks...",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
						@Override
						public void effects() {
							Main.game.getPlayer().performImpregnationCheck(true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);

							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
								getMainCompanion().performImpregnationCheck(true);
								getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
							}
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_BREEDING_WITH_COMPANION"));
							updateSeanPregnancyReactions();
						}
					};
					
				} else {
					return new Response("Wait",
							"There's nothing for you to do but wait until [sean.name] returns and releases you from the stocks...",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
						@Override
						public void effects() {
							banishRandomSexPartners();
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
								getMainCompanion().performImpregnationCheck(true);
								getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_WITH_COMPANION"));
							updateSeanPregnancyReactions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						"Now that you're free, you can continue on your way once again...",
						PUBLIC_STOCKS) {
					@Override
					public void effects() {
						banishRandomSexPartners();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
							getMainCompanion().equipAllClothingFromHoldingInventory();
						}
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_END"));
						}
					}
				};
			}
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				if(index==2) {
					return new Response("More",
							"Tell [sean.name] that you want to stay in the stocks for a little longer...",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT,
							Util.newArrayListOfValues(
									Fetish.FETISH_EXHIBITIONIST,
									Fetish.FETISH_SUBMISSIVE,
									Fetish.FETISH_MASOCHIST),
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
							?"The two strangers prepare to start using you..."
							:"The stranger prepares to start using you...",
						false,
						false,
						getRandomPartnerSexManager(),
						null,
						null,
						PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX", randomSexPartners));
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return "Having filled your [pc.pussy+] with his cum, [sean.name] is finished with you and steps back.";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				return "Having had their fun, the strangers step back...";
				
			} else {
				return UtilText.parse(randomSexPartners.get(0), "Having had [npc.her] fun, the stranger steps back...");
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				if(index==1) {
					return new Response("[com.NamePos] turn",
							"A stranger moves up to [com.name] and prepares to start fucking [com.herHim]...",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_COMPANION_SEX) {
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(getMainCompanion(), false);
							generateCompanionSexParsingDescriptions();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX_START_SEX_COMPANION", randomSexPartners));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Wait",
							"There's nothing for you to do but wait until [sean.name] returns and releases you from the stocks...",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_COMPANION_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait",
						"There's nothing for you to do but wait until [sean.name] returns and releases you from the stocks...",
						PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED"));
					}
				};
			}
			return null;
		}
	};
}
