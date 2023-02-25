package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.misc.BasicSlave;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMScarlettShopOral;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.9.1
 * @author Innoxia
 */
public class ScarlettsShop {
	
	private static void spawnDeliveryNpcs() {
		Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
		
		String[] names = new String[] {"obedient centaur", "loyal centaur"};
		for(int i=0; i<2; i++) {
			NPC npc = new GenericSexualPartner(Gender.M_P_MALE, WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getCell(PlaceType.GENERIC_HOLDING_CELL).getLocation(), false);
			npc.setBody(Gender.M_P_MALE, Subspecies.CENTAUR, Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(Subspecies.CENTAUR), Gender.M_P_MALE, Subspecies.CENTAUR),false);
			
			npc.unequipAllClothing(npc, true, true);
			npc.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), PresetColour.CLOTHING_GOLD, false), true, npc);
			
			npc.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			npc.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
			npc.setHeight(230+Util.random.nextInt(11));
			
			npc.setTesticleSize(TesticleSize.FIVE_MASSIVE);
			npc.setPenisCumStorage(750);
			npc.setPenisCumExpulsion(80);
			
			npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			npc.clearFetishes();
			npc.clearFetishDesires();
			
			npc.addFetish(Fetish.FETISH_ANAL_GIVING);
			
			npc.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.ZERO_HATE);
			npc.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.ZERO_HATE);
			
			npc.setAssVirgin(false);
			npc.setPenisVirgin(false);
			npc.setFaceVirgin(false);
			
			npc.setGenericName(names[i]);
			
			npc.setLocation(Main.game.getPlayer(), false);
			try {
				Main.game.addNPC(npc, false);
				Main.game.setActiveNPC(npc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<GameCharacter> getDeliveryNpcs() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->npc.isUnique());
		return list;
	}
	
	private static int getScarlettPrice() {
		return (int) Units.roundTo(Main.game.getNpc(Scarlett.class).getValueAsSlave(false) * 2, 1000);
	}
	
	private static SexManagerInterface getScarlettSleepoverSexManager(AbstractSexPosition position, SexSlot scarlettSlot, SexSlot playerSlot, SexType scarlettPreference, Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				return SexControl.ONGOING_ONLY; // So Scarlett doesn't start anything else.
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return true;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return false;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return scarlettPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getPlayer(), new HashMap<>());
					map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Scarlett.class), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				return super.getStartingWetAreas();
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettSleepoverSex)) { // If this is the morning oral scene, Scarlett stops after cumming.
					return Main.sex.isSatisfiedFromOrgasms(partner, true);
				}
				return Main.sex.isSatisfiedFromOrgasms(partner, true) && (Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true) || Main.sex.getNumberOfOrgasms(partner)>=3);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {// pull out if Scarlett receiving blowjob
						return OrgasmBehaviour.PULL_OUT;
					}
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
				if(!character.isPlayer()) {
					if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {// pull out onto face if Scarlett receiving blowjob
						return OrgasmCumTarget.FACE;
					}
				}
				return null;
			}
		};
	}
	
	private static SexManagerInterface getScarlettCafeSexManager(AbstractSexPosition position, SexSlot scarlettSlot, SexSlot playerSlot, SexType scarlettPreference, Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				return SexControl.ONGOING_ONLY; // So Scarlett doesn't start anything else.
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return false;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return scarlettPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				return Main.sex.isSatisfiedFromOrgasms(partner, true);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}

	private static GameCharacter getSlaveForCustomisation() {
		if(Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters().isEmpty()) {
			return null;
		}
		return Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters().get(0);
	}
	
	private static int getSlaveValue(boolean asSlime) {
		if(getSlaveForCustomisation()==null) {
			return 0;
		}
		
		int value = 25_000;
		for(Entry<AbstractRace, Integer> entry : getSlaveForCustomisation().getBody().getRaceWeightMap().entrySet()) { // Add value for non-human parts:
			if(entry.getKey()!=Race.HUMAN) {
				value += Math.min(5_000, 1_000*entry.getValue());
			}
		}
		
		if(asSlime) {
			value += 5_000;
		}
		return value;
	}
	
	public static boolean isSlaveCustomisationMenu() {
		return Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS;
	}
	
	private static void generateStartingSlave(Gender gender) {
		NPC slave = new BasicSlave(gender, false);
		
		try {
			Main.game.addNPC(slave, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.getNpc(Helena.class).addSlave(slave);
		
		BodyChanging.setTarget(slave);
		
		SuccubisSecrets.initCoveringsMap(slave);
	}
	
	private static boolean isFilly() {
		return Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) || Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD);
	}
	
	public static final DialogueNode SCARLETTS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR_CLOSED");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("Enter", "Scarlett's Shop is currently closed, and will re-open at six in the morning. You'll have to come back some time after then.", null);
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
					return new Response("Enter", "You should go and find Helena before entering Scarlett's Shop again.", null);
					
				} else {
					return new Response("Enter", "Enter the shop.", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETTS_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_INTRO");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
					return new Response("Ask for Arthur", "Ask Scarlett if she's got a slave named Arthur for sale.", SCARLETT_IS_A_BITCH);

				} else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_BITCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to help Scarlett.", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_SUPER_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_SUPER_BITCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_HELENA_RETURNS");
				
			} else if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_ROMANCE_FAILED");
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_CLOSED"));
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)
					&& Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_8_FINISH)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_CLOSED"));
				}
				
			} else {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_OPEN"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_CLOSED"));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR"));
				}
				
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_CLOSED"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return null;
			}
			
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Enter", "Enter the shop.", HELENAS_SHOP) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getNpc(Scarlett.class).resetInventory(true);
							AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
							collar.setSealed(true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
						}
					};
					
				} else if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					return new Response("Enter",
							"Helena's shop is currently closed, and will re-open at nine in the morning. You'll have to come back some time after then.",
							null);
				}
				return new Response("Enter",
						"Enter the shop.",
						Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)
							?ROMANCE_SHOP_CORE
							:HELENAS_SHOP);

			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_INTRODUCTION");
					
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_OFFER_SCARLETT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP", Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return new Response("Leave", "Say goodbye to Helena and exit her shop.", HELENAS_SHOP_EXTERIOR);
				
			} else if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Offer to buy", "Offer to buy Scarlett from Helena.", HELENAS_SHOP_SCARLETT_FOR_SALE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY));
							if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.punishedByHelena)) {
								Main.game.getDialogueFlags().scarlettPrice = 10000;
							}
						}
					};
					
				} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
					if(!Main.game.getPlayer().isHasSlaverLicense()) {
						return new Response("Buy Scarlett (" + UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "span")+")",
								"You need to obtain a slaver license from the Slavery Administration before you can buy Scarlett!", null);
						
					} else if(Main.game.getPlayer().getMoney() < Main.game.getDialogueFlags().scarlettPrice) {
						return new Response("Buy Scarlett (" +UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "span")+")", "You don't have enough money to buy Scarlett.", null);
						
					} else {
						return new Response("Buy Scarlett ("+UtilText.formatAsMoney(Main.game.getDialogueFlags().scarlettPrice, "span")+")",
								"Buy Scarlett for "+Main.game.getDialogueFlags().scarlettPrice+" flames.", HELENAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().scarlettPrice));
								
								AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
								if (ballgag != null) {
									ballgag.setSealed(false);
									Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
								}
								
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
							}
						};
					}
					
				} else {
					return new Response("Slave Manager", "Enter the slave management screen.", HELENAS_SHOP) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(HELENAS_SHOP, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
				if(index==2) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)) {
						return new Response("Business",
								"Ask Helena why she's chosen to remain here and run this business herself."
										+ "<br/>[style.italicsQuestRomance(This will start Helena's romance quest!)]",
								ROMANCE_BUSINESS) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_FOR_SALE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_FOR_SALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().scarlettPrice), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Calm her down", "Gently reassure Scarlett to get her to calm down.", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_GENTLE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("Shout at her", "Shout at Scarlett and remind her that she's now your property.", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SHOUT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(5));
					}
				};
				
			} else if (index == 3) {
				return new Response("Slap her", "Slap Scarlett and remind her that she's now your property.", HELENAS_SHOP_SCARLETT_PURCHASED,
						Util.newArrayListOfValues(Fetish.FETISH_SADIST),
						CorruptionLevel.FOUR_LUSTFUL,
						null, null, null) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SLAP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -15));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(10));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_PURCHASED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Keep her", "You decide to keep Scarlett as your slave.", HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};

			} else if (index == 2) {
				return new Response("Free her", "You decide to grant Scarlett her freedom.", HELENAS_SHOP_BUYING_SCARLETT_FREE_HER) {
					@Override
					public void effects() {
						
						AbstractClothing collar = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK);
						if(collar!=null) {
							collar.setSealed(false);
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(collar, true, Main.game.getNpc(Helena.class));
						}
						
						((Scarlett) Main.game.getNpc(Scarlett.class)).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.REMOVE_SEALS, EquipClothingSetting.ADD_ACCESSORIES));
						
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
						Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Exit the shop.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_FREE_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Exit the shop.", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Helena romance quest:
	
	public static final DialogueNode ROMANCE_SHOP_CORE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				// If Scarlett has a saved inventory, then give items to player:
				CharacterInventory scarlettSavedInventory = Main.game.getSavedInventories().get(Main.game.getNpc(Scarlett.class).getId());
				if(scarlettSavedInventory!=null) {
					for(AbstractWeapon weapon : scarlettSavedInventory.getMainWeaponArray()) {
						if(weapon!=null) {
							Main.game.getPlayer().addWeapon(weapon, 1, false, true);
						}
					}
					for(AbstractWeapon weapon : scarlettSavedInventory.getOffhandWeaponArray()) {
						if(weapon!=null) {
							Main.game.getPlayer().addWeapon(weapon, 1, false, true);
						}
					}
					for(AbstractClothing clothing : scarlettSavedInventory.getClothingCurrentlyEquipped()) {
						Main.game.getPlayer().addClothing(clothing, 1, false, true);
					}
					for(Entry<AbstractWeapon, Integer> entry : scarlettSavedInventory.getAllWeaponsInInventory().entrySet()) {
						Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), false, true);
					}
					for(Entry<AbstractClothing, Integer> entry : scarlettSavedInventory.getAllClothingInInventory().entrySet()) {
						Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), false, true);
					}
					for(Entry<AbstractItem, Integer> entry : scarlettSavedInventory.getAllItemsInInventory().entrySet()) {
						Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), false, true);
					}
					Main.game.getPlayer().incrementMoney(scarlettSavedInventory.getMoney());
				}
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP")
						+ UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_1");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2"));
				if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
						|| Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_PAINT"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_NO_PAINT"));
				}
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_EXTERIOR");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_SIGN");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT_OWNED_PRESENT"); // Helena demands you sell Scarlett to her
						
					} else {
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT_OWNED_NOT_PRESENT"); // Helena demands you fetch Scarlett
					}
					
				} else {
					if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT"); // Helena tells you to come back tomorrow/Monday
						
					} else {
						if(Main.game.getNpc(Scarlett.class).isSlave()) {
							return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_NO_SCARLETT_SLAVE"); // Helena tells you to come back once Scarlett has been returned
							
						} else {
							return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_NO_SCARLETT"); // Helena tells you to come back once Scarlett is here
						}
					}
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5"));
				CharacterInventory scarlettSavedInventory = Main.game.getSavedInventories().get(Main.game.getNpc(Scarlett.class).getId());
				if(scarlettSavedInventory!=null) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_ITEMS_RESTORED"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_NO_ITEMS"));
				}
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_END"));
				
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_6_ADVERTISING) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_6"); // Helena asks you if you've put up the posters yet
			}
			
			
			// Romance quest completed:
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE"));
			
			if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY && Main.game.getHourOfDay()>=17) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_DATE"));
				
			} else if(getSlaveForCustomisation()!=null) {
				int daysToGo = 7-(Main.game.getDayNumber()-Main.game.getDialogueFlags().helenaSlaveOrderDay);
				if(daysToGo>0) {
					UtilText.addSpecialParsingString(Util.intToString(daysToGo), true);
					LocalDateTime timeReady = Main.game.getDateNow().plusDays(daysToGo);
					UtilText.addSpecialParsingString(timeReady.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false);
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_CUSTOM_SLAVE_PROGRESS"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_CUSTOM_SLAVE_READY"));
				}
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				if(index==0) {
					return new Response("Leave", "Say goodbye to Helena and exit her shop.", HELENAS_SHOP_EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
							}
						}
					};
					
				} else if(index==1) {
					if(getSlaveForCustomisation()!=null) {
						int daysToGo = 7-(Main.game.getDayNumber()-Main.game.getDialogueFlags().helenaSlaveOrderDay);
						if(daysToGo>0) {
							return new Response("Collect slave",
									"Your custom slave is in the process of being trained and transformed, and won't be ready for collection for another "+Util.intToString(daysToGo)+" days.",
									null);
							
						} else {
							return new Response("Collect slave", "Tell Helena that you're ready to take delivery of your custom slave.", HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY) {
								@Override
								public void effects() {
									NPC slave = (NPC) getSlaveForCustomisation();
									slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
								}
							};
						}
						
					} else {
						return new Response("Custom slave", "Ask Helena about ordering a custom slave.", HELENAS_SHOP_CUSTOM_SLAVE_START);
					}
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopTalkedTo)) {
						return new Response("Talk", "You've already spent some time talking to Helena today...", null);
					}
					return new Response("Talk", "Ask Helena how her business is going.", HELENAS_SHOP_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopTalkedTo, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 2));
						}
					};
					
				} else if(index==3 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopFucked)) {
						return new Response("Back room", "You've already had sex with Helena in the back room today, and she doesn't have time to do it again...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						return new Response("Back room", "As you are unable to gain access to your mouth or genitals, you can't have sex with Helena...", null);
					}
					return new Response("Back room", "Accept Helena's offer of joining her in the back room.<br/>[style.italicsSex(This will lead into having sex with her...)]", HELENAS_SHOP_BACK_ROOM) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopFucked, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				}
				//TODO
//				else if(index==3 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
//					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettThreesome)) {
//						return new Response("Threesome", "Helena will be unwilling to have a threesome until after you've convinced her to do it in her bedroom.", null);
//					}
//					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopFucked)) {
//						return new Response("Threesome", "You've already had sex with Helena in the back room today, and she doesn't have time to do it again...", null);
//					}
//					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
//							&& (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
//							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
//						return new Response("Threesome", "As you are unable to gain access to your mouth or genitals, you can't have sex with Helena...", null);
//					}
//					return new Response("Threesome",
//							"Ask Helena if she'd like to have a threesome with you and Scarlett in the back room...",
//							HELENAS_SHOP_BACK_ROOM_THREESOME) {
//						@Override
//						public boolean isSexHighlight() {
//							return true;
//						}
//						@Override
//						public void effects() {
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopFucked, true);
//							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
//						}
//					};
//					
//				}
				else if(index==5) {
					if(Main.game.getDayOfWeek()!=DayOfWeek.FRIDAY || Main.game.getHourOfDay()<17) {
						return new Response("Date", "You can only ask Helena out on a date on Fridays after [units.time(17)].", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Date", "As it will involve eating dinner, you [style.colourBad(require access to your mouth)] to ask Helena out on a date!", null);
					}
					return new Response("Date", "Ask Helena out on a date.", HelenaHotel.DATE_START);
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafe)) {
						return new Response("Scarlett", "As she's already spent her lunch break with you, Helena will not agree to giving Scarlett any more time off work in which to talk with you...", null);
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCounterOral)) {
						return new Response("Scarlett", "As you've already spent time with Scarlett today, Helena will not agree to giving her any more time off work in which to talk with you...", null);
					}
					return new Response("Scarlett", "Ask Helena if you could spend some time with Scarlett.", HELENAS_SHOP_SCARLETT);
				}
				return null;
			}
			
			if (index==0
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				return new Response("Leave", "Say goodbye to Helena and exit her shop.", HELENAS_SHOP_EXTERIOR);
			}
			
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
				if(index == 1) {
					return new Response("Slave Manager", "Enter the slave management screen.", ROMANCE_SHOP_CORE) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(ROMANCE_SHOP_CORE, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				if(index==2) {
					return new Response("Offer help", "Tell Helena that you'd be willing to provide whatever help she needs in order to improve her business.", ROMANCE_OFFER_HELP);
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(index==1) {
					if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
						return new Response("Paint", "Show Helena the 'Purple-star' can of golden paint which she asked for.", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_PREMIUM"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN_PREMIUM);
							}
						};
						
					} else if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
						return new Response("Paint", "Show Helena the can of 'Bronze-star' golden paint, and hope that she doesn't notice that it's not the exact one she asked for...", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaCheapPaint, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_STANDARD"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN);
							}
						};
						
					} else {
						return new Response("Paint", "You haven't yet purchased the golden paint which Helena asked for...", null);
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("Strip paint", "Do as Helena ordered and strip all the old, peeling paint from the store's frontage.", ROMANCE_PAINTING_A) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR));
						}
					};
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("Paint frontage", "Do as Helena ordered and paint the entire frontage in a fresh new coat of white paint.", ROMANCE_PAINTING_B);
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("Take paint", "Do as Helena instructs and take the golden paint outside.", ROMANCE_PAINTING_C);
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					if(!Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						if(index==1) {
							return new Response("Leave", "Do as Helena says and leave her shop.", SlaverAlleyDialogue.ALLEYWAY) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								}
							};
						}
						
					} else {
						if(index==1) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 1); // Sell
							
						} else if(index==2) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 2); // Give
							
						} else if(index==5) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 5); // Refuse
						}
					}
					
				} else {
					if(index==1) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) { // Returning after recovering Scarlett from nest or antiques shop:
							return new Response("Agree",
									Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
										?"Tell Helena that you'll return on Monday."
										:"Tell Helena that you'll return tomorrow.",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
								}
							};
							
						} else { // Returning before Scarlett is recovered:
							return new Response("Leave", "Do as Helena says and leave her shop.", SlaverAlleyDialogue.ALLEYWAY) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								}
							};
						}
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				if(index==1) {
					return new Response("Follow",
							"Do as Helena asks and follow her into the back room.",
							ROMANCE_5_POTIONS);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thank her", "Perhaps she's waiting for you to thank her?", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_THANK_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
				
			} else if(index==2) {
				return new Response("Prompt her", "Tell her to get on with it then.", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_PROMPT_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS_FOLLOWUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_FOLLOWUP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(0, index);
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=10_000) {
					return new Response("Pay ("+UtilText.formatAsMoney(10_000, "span")+")", "Pay Helena the ten thousand flames which she's asking for.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getNpc(Helena.class).incrementMoney(10_000);
						}
					};
					
				} else if(Main.game.getPlayer().getMoney()>0) {
					return new Response("Pay ("+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")+")", "Tell Helena that you don't have ten thousand flames, so cannot pay her.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(Main.game.getPlayer().getMoney()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY_REDUCED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getPlayer().getMoney()));
							Main.game.getNpc(Helena.class).incrementMoney(Main.game.getPlayer().getMoney());
						}
					};
					
				} else {
					return new Response("Cannot pay", "Tell Helena that you don't have any flames on you at all, so cannot pay her.", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_CANNOT_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Refuse", "Tell Helena that she's being extremely unreasonable; <i>she's</i> the one who should be paying <i>you</i>!", ROMANCE_OFFER_HELP_PAYMENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_REFUSE_TO_PAY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_PAYMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAYMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Supplies", "Do as Helena commands and fetch the box of supplies.", ROMANCE_OFFER_HELP_FETCH_SUPPLIES);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_FETCH_SUPPLIES = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_FETCH_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Helena to find what it is she's looking for.", ROMANCE_OFFER_HELP_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_2_PURCHASE_PAINT));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), false);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_A = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
							?"You'll have to wait until Monday before continuing with your work..."
							:"You'll have to wait until tomorrow before continuing with your work...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_B = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			spawnDeliveryNpcs();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_2", getDeliveryNpcs());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						isFilly()
							?"Greet Natalya"
							:"Introduction",
						isFilly()
							?"Greet your Mistress and tell her that you're here to take delivery of the furniture which must be in the back of the cart."
							:"Introduce yourself as the person this [natalya.race] is looking for, and then proceed to take delivery of the furniture which must be in the back of the cart.",
						ROMANCE_PAINTING_FURNITURE_DELIVERY) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setPlayerKnowsName(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Don't offer any help, and instead stand back and wait for the centaurs to be done with their task.", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_NO_HELP"));
					}
				};
				
			} else if(index==2) {
				return new Response("Offer help", "Ask Natalya if there's anything you can do to help.", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_HELP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isFilly()) {
				if(index==1) {
					return new Response("Follow",
							"Follow Mistress Natalya down the alleyway to see what she requires of you.",
							ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
						@Override
						public void effects() {
							Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Remain", "Wait next to the cart for Natalya to return.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
						@Override
						public int getSecondsPassed() {
							return 15*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_WAIT"));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isAnalContentEnabled()) {
						return new Response("Follow",
								"You get the feeling that following Natalya down the alleyway would lead to something you'd rather not see..."
										+ "<br/>[style.italicsMinorBad(Natalya's scenes involve anal content, and as such will be disabled for as long as your 'Anal Content' setting is turned off.)]",
								null);
					}
					return new Response("Follow",
							"Follow Natalya down the alleyway and see what she's up to."
									+ "<br/>[style.italicsSex(You get the feeling that you might see something quite lewd...)]",
							ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW) {
						@Override
						public void effects() {
							Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : getDeliveryNpcs()) {
				Main.game.banishNPC((NPC) npc);
			}
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR));
			if(!Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD)) {
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false));
			}
		}
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
				return new Response("Lock up",
						"Lock up the store and prepare to leave.",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						"Continue on your way out into Slaver Alley.",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and leave Natalya to get herself off without your help.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 15*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_LEAVE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==2) {
				return new Response("Submit",
						"Do as the dominant [natalya.race] commands, and after calling her 'Mistress', kneel down before her and submit to whatever plans she has in store for you...",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalya, true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Start stroking", "Do as Mistress Natalya commands and start stroking her thick horse-like cock.",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return getForeplayPreference(character, targetedCharacter);
								}
								return character.getMainSexPreference(targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaOrifice.NIPPLE, SexAreaOrifice.BREAST));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaPenetration.FOOT));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return super.getCharacterPullOutOrgasmCumTarget(character, target);
							}
						},
						null,
						null,
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX = new DialogueNode("Finished", "Mistress Natalya has finished with you for now.", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Mistress Natalya to see if the two centaurs have finished unpacking the furniture yet.", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX_FOLLOW"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Paint sign", "Under the close supervision of Helena, use the golden paint you purchased to paint the words 'Helena's Boutique' over the shop's entrance.", ROMANCE_PAINTING_C_PAINT_SIGN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_PAINT_SIGN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 10*60;
			}
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_PAINT_SIGN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow", "Follow Helena back into her store.", ROMANCE_PAINTING_C_FINISHED) {
					@Override
					public void effects() {
						// If Scarlett has been sold but not 'banished' yet, 'banish' her now:
						if(Main.game.getNpc(Scarlett.class).isSlave() && !Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
							Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
							Main.game.banishNPC(Main.game.getNpc(Scarlett.class));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Make tea",
						"Do as Helena says and make some tea for the two of you.",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_TEA"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse",
						"Refuse to make Helena some tea, and instead bluntly ask her if there's anything else she requires of you.",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_NO_TEA"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Read", "Read the letter which Helena just handed to you.", ROMANCE_PAINTING_C_FINISHED_LETTER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_LETTER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_LETTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
				if(!Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(index==1) {
						return new Response("Agree", "Agree to go and fetch Scarlett and bring [scarlett.herHim] back here.", ROMANCE_SCARLETT_OWNED_FETCH);
					}
				} else {
					if(index==1) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
							return new Response("Sell Scarlett ("+UtilText.formatAsMoney(getScarlettPrice(), "span", PresetColour.GENERIC_GOOD)+")",
									"Sell Scarlett back to Helena for "+UtilText.formatAsMoney(getScarlettPrice())+".",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
									
									Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
									Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
									Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD"));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_REACTION"));
									if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || !Main.game.getNpc(Scarlett.class).isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NEEDING_TF"));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NO_TF_NEEDED"));
									}
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(getScarlettPrice()));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
									
									Main.game.addSavedInventory(Main.game.getNpc(Scarlett.class));
									int essences = Main.game.getNpc(Scarlett.class).getEssenceCount();
									Main.game.getNpc(Scarlett.class).setInventory(new CharacterInventory(0));
									Main.game.getNpc(Scarlett.class).setEssenceCount(essences);
								}
							};
							
						} else {
							return new Response("Sell Scarlett ("+UtilText.formatAsMoneyUncoloured(getScarlettPrice(), "span")+")",
									"You need to bring Scarlett here as your companion before being able to sell [scarlett.herHim] to Helena!",
									null);
						}
						
					} else if(index==2) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
							return new Response("Give Scarlett",
									"Give Scarlett back to Helena and do not accept the "+UtilText.formatAsMoney(getScarlettPrice())+" she's offering you.",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
									
									Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
									Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
									Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_GIVEN_AWAY"));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_REACTION"));
									if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || !Main.game.getNpc(Scarlett.class).isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NEEDING_TF"));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NO_TF_NEEDED"));
									}
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 15));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
									
									Main.game.addSavedInventory(Main.game.getNpc(Scarlett.class));
									int essences = Main.game.getNpc(Scarlett.class).getEssenceCount();
									Main.game.getNpc(Scarlett.class).setInventory(new CharacterInventory(0));
									Main.game.getNpc(Scarlett.class).setEssenceCount(essences);
								}
							};
							
						} else {
							return new Response("Give Scarlett", "You need to bring Scarlett here as your companion before being able to give [scarlett.herHim] away to Helena!", null);
						}
					}
				}
				
				if(index==5) {
					return new Response("Refuse",
								"Refuse to sell Scarlett to Helena."
									+ "<br/>[style.italicsBad(This will fail the quest, and without your help, Helena will have to close down the store and return to the Harpy Nests!)]",
								ROMANCE_QUEST_FAILURE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setAffection(Main.game.getPlayer(), -100));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_FAILED));
							Main.game.getNpc(Helena.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
							if(!Main.game.getPlayer().hasCompanion(Main.game.getNpc(Scarlett.class))) {
								Main.game.getNpc(Scarlett.class).returnToHome();
								Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'><i>You order [scarlett.name] to return to [scarlett.her] room...</i></p>");
							}
						}
					};
				}
				return null;
			}
			
			if(index==1) {
				return new Response("Continue",
						"Continue on your way out into Slaver Alley.",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						if(Main.game.getNpc(Scarlett.class).getHomeWorldLocation()!=WorldType.HARPY_NEST) {
							Main.game.getNpc(GenericMaleNPC.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ANTIQUES);
						}
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_OWNED_FETCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_OWNED_FETCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						"As you've promised to go and fetch Scarlett, you'd better head off and do so...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_QUEST_FAILURE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_REFUSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						"With Helena having abandoned her store, there's nothing left for you to do here except leave...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_DELIVERED_EMPTY = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			
			if(Main.game.getNpc(Helena.class).getAffection(Main.game.getNpc(Scarlett.class))<0) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), 0);
			}
			
			((Scarlett)Main.game.getNpc(Scarlett.class)).resetName();
			
			((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
		}
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
				return new Response("Leave",
						"Now that Helena and Scarlett have departed, there's nothing for you to do except leave...",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_5_POTIONS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getSavedInventories().remove(Main.game.getNpc(Scarlett.class).getId()); // Removed Scarlett saved inventory, as it was restored to the player in the preceding scene
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave her", "Tell Helena that it would be best not to transform Scarlett.", ROMANCE_5_POTIONS_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS_SCARLETT_NO_TF"));
					}
				};
				
			} else if(index==2) {
				return new Response("Transform her", "Tell Helena that transforming Scarlett into a female harpy would be best for her.", ROMANCE_5_POTIONS_NEXT) {
					@Override
					public void effects() {
						((Scarlett)Main.game.getNpc(Scarlett.class)).applyFeminisation();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS_SCARLETT_FEMALE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_5_POTIONS_NEXT = new DialogueNode("", "", true, true) {
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
				return new Response("Follow", "Follow Helena back out into the store.", ROMANCE_ADVERTISING_POSTERS);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Compliment", "Compliment Helena on how beautiful she looks on the posters.", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_COMPLIMENT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Question", "Agree with Scarlett and question how these posters are supposed to advertise the business.", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_QUESTION"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ROLLED_UP_POSTERS), false));
			if(Main.getProperties().addItemDiscovered(ItemType.ROLLED_UP_POSTERS)) {
				Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(ItemType.ROLLED_UP_POSTERS.getName(false), ItemType.ROLLED_UP_POSTERS.getRarity().getColour()), true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(100));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						"Having received your orders from Helena, you're ready to set out into Slaver Alley and put up these posters.",
						SlaverAlleyDialogue.GATEWAY_POSTER_PERMISSION) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE);
					}
				};
//				return new Response("Leave",
//						"Having received your orders from Helena, you're ready to set out into Slaver Alley and put up these posters.",
//						SlaverAlleyDialogue.ALLEYWAY) {
//					@Override
//					public void effects() {
//						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
//					}
//				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_RETURN_AFTER_POSTERS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0; // 0 as otherwise the time in the dialogue doesn't match up
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_RETURN_AFTER_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Follow",
						"Follow Helena throughout the store to see what she needs you and Scarlett to have done by tomorrow.",
						ROMANCE_7_FOLLOW) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_FOLLOW"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION));
						Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
						Main.game.getPlayer().removeAllCompanions(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_FOLLOW = new DialogueNode("", "", true, true) {
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
				return new Response("Start work",
						"There's not much to do except get to work...",
						ROMANCE_7_START);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ask nicely",
						"Kindly ask Scarlett to help clean the shop with you.",
						ROMANCE_7_WORKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_ASK_NICELY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==2) {
				return new Response("Scold her",
						"Scold Scarlett for always causing a nuisance and order her to help.",
						ROMANCE_7_WORKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_SCOLDING"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_WORKING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Put up decorations",
						"Get started on putting up the decorations.",
						ROMANCE_7_DECORATIONS) {
					@Override
					public void effects() {
						AbstractClothing dress = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.TORSO_UNDER);
						if(dress!=null) {
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(dress, true, Main.game.getNpc(Scarlett.class));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_7_DECORATIONS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(1 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_DECORATIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Decline",
						"Turn down the offer of whiskey from Scarlett.",
						ROMANCE_7_WORKING_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_NO_DRINK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAlcoholLevel(0.3f));
					}
				};
				
			} else if(index==2) {
				return new Response("Drink",
						"Drink the whiskey which Scarlett is offering to you.",
						ROMANCE_7_WORKING_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_DRINK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAlcoholLevel(0.3f));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.3f));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_WORKING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
				AbstractClothing bra = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.CHEST);
				if(bra!=null) {
					Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(bra, true, Main.game.getNpc(Scarlett.class));
				}
				AbstractClothing underwear = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.GROIN);
				if(underwear!=null) {
					Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(underwear, true, Main.game.getNpc(Scarlett.class));
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("Sleep", "Fall asleep on the sofa.", ROMANCE_7_MORNING);
				}
				
			} else {
				if(Main.game.getNpc(Scarlett.class).hasVagina()) {
					if(index==1) {
						return new Response("Decline", "Tell Scarlett that you're not interested in servicing her pussy.", ROMANCE_7_SEX_DECLINED);
						
					} else if(index==2) {
						if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							return new ResponseSex(
									"Ridden",
									"Do as Scarlett asks and let her ride your cock.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_RIDDEN_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							};
							
						} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex(
									"Eat out",
									"Do as Scarlett asks and eat her out.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_CUNNILINGUS_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							};
							
						} else {
							return new ResponseSex(
									"Finger her",
									"Do as Scarlett asks and finger her.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_FINGERING_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERED_START, false, true));
								}
							};
						}
					}
					
				} else {
					 if(index==1) {
						return new Response("Decline", "Tell Scarlett that you're not interested in servicing her cock.", ROMANCE_7_SEX_DECLINED);
						
					} else if(index==2) {
						if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							return new ResponseSex(
									"Offer ass",
									"Do as Scarlett asks and offer her the use of your ass.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS, CoverableArea.VAGINA)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_ANAL_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> initialActions = new ArrayList<>();
									
									initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
									
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
										
									} else if(Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
										initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
										
									}
									
									return initialActions;
								}
							};
							
						} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex(
									"Suck cock",
									"Do as Scarlett asks and suck her cock.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_ORAL_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
								}
							};
							
						} else {
							return new ResponseSex(
									"Give handjob",
									"Do as Scarlett asks and give her a handjob.",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_HANDJOB_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
								}
							};
						}
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_AFTER_SEX = new DialogueNode("Finished", "Scarlett is satisfied with your performance.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Fall asleep on the sofa.", ROMANCE_7_MORNING) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettSleepoverSex, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_SEX_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_SEX_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Fall asleep on the sofa.", ROMANCE_7_MORNING);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettSleepoverSex) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("Wake her", "Shake Scarlett to wake her up.", ROMANCE_7_MORNING_TIDY_UP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_WAKE_SCARLETT_NO_SEX"));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getNpc(Scarlett.class).hasVagina()) {
						return new ResponseSex(
								"Morning cunnilingus",
								"Do as Scarlett told you the night before and wake her up by eating her out.",
								true,
								false,
								getScarlettSleepoverSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								ROMANCE_7_MORNING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_CUNNILINGUS_START")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 20));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex(
								"Morning blowjob",
								"Do as Scarlett told you the night before and wake her up by giving her a blowjob.",
								true,
								false,
								getScarlettSleepoverSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								ROMANCE_7_MORNING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_BLOWJOB_START")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 20));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						};
					}
					
				}
				
			} else {
				if(index==1) {
					return new Response("Wake her", "Shake Scarlett to wake her up.", ROMANCE_7_MORNING_TIDY_UP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_WAKE_SCARLETT"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING_AFTER_SEX = new DialogueNode("Finished", "Now that she's orgasmed, Scarlett has had enough.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Tidy up", "Tidy up the back room before Helena arrives.", ROMANCE_7_MORNING_TIDY_UP);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING_TIDY_UP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getHourOfDay()<9) {
				return (Main.game.getMinutesUntilTimeInMinutes(9 * 60) * 60) + 10*60;
			}
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_TIDY_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Make drinks", "Work alongside Scarlett to make drinks for the guests.", ROMANCE_7_MAKING_DRINKS);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_7_MAKING_DRINKS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY) {
				return (Main.game.getMinutesUntilTimeInMinutes(21 * 60) * 60) + 10*60;
			}
			return (Main.game.getMinutesUntilTimeInMinutes(17 * 60) * 60) + 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MAKING_DRINKS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Close eyes", "Do as Helena orders and close your eyes.", ROMANCE_END_KISSED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_CLOSE_EYES"));
					}
				};
				
			} else if(index==2) {
				return new Response("Peek", "Pretend to close your eyes, but secretly peek to see what it is Helena is planning.", ROMANCE_END_KISSED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_PEEK"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_END_KISSED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.SIDE_UTIL_COMPLETE));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 25));
			
			if(Main.game.getNpc(Helena.class).getAffection(Main.game.getNpc(Scarlett.class))<50) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), 50);
			}
			Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_COMPLETED"));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way out into Slaver Alley...", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setHomeLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Dialogue for when romance quest is completed:
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back out", "Decide against ordering a custom slave from Helena.", HELENAS_SHOP_CUSTOM_SLAVE_DECLINED);
				
			} else if(index==1) {
				return new Response("[style.colourFeminine(Female template)]", "Start designing your custom slave, using a human female as a starting point.", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public void effects() {
						generateStartingSlave(Gender.F_V_B_FEMALE);
					}
				};
				
			} else if(index==2) {
				return new Response("[style.colourMasculine(Male template)]", "Start designing your custom slave, using a human male as a starting point.", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public void effects() {
						generateStartingSlave(Gender.M_P_MALE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY"));
			
			sb.append(
					"<div class='container-full-width' style='padding:8px;'>"
						+ "<div style='width:22%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "Name"
						+ "</div>"
						+ "<div style='width:22%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "Surname"
						+ "</div>"
						+ "<div style='width:24%; float:left; font-weight:bold; margin:0 6% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(getSlaveForCustomisation(), "What [npc.she] calls you")
						+ "</div>"
						
						+ "<form style='float:left; width:22%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
							
						+ "<form style='float:left; width:22%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
						
						+ "<form style='float:left; width:24%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
					+ "</div>");
			
			sb.append("<div class='cosmetics-container' style='background:transparent;'>"
						+ CharacterModificationUtils.getAgeChoiceDiv()
						+ CharacterModificationUtils.getOrientationChoiceDiv()
						+ CharacterModificationUtils.getPersonalityChoiceDiv(true)
						+ CharacterModificationUtils.getObedienceChoiceDiv()
						+ CharacterModificationUtils.getAffectionChoiceDiv()
						+ CharacterModificationUtils.getFetishChoiceDiv()
					+"</div>");
			
			sb.append("<p id='hiddenFieldName' style='display:none;'></p>");
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Cancel", "Decide against ordering a custom slave from Helena.", ROMANCE_SHOP_CORE) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getSlaveForCustomisation());
					}
				};
				
			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					return new Response("Personality", "You are already customising your slave's personality!", null);
				}
				return new Response("Personality", "Customise aspects of your slave's personality.", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY);
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE) {
					return new Response("Body", "You are already customising core aspects of your slave's body!", null);
				}
				return new Response("Body", "Customise core aspects of your slave's body.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE);
				
			} else if(index==3) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES) {
					return new Response("Eyes", "You are already customising the aspects of your slave's eyes!", null);
				}
				return new Response("Eyes", "Customise aspects of your slave's eyes.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES);
				
			} else if(index==4) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR) {
					return new Response("Hair", "You are already customising the aspects of your slave's hair!", null);
				}
				return new Response("Hair", "Customise aspects of your slave's hair.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR);
				
			} else if(index==5) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD) {
					return new Response("Head", "You are already customising the aspects of your slave's head and face!", null);
				}
				return new Response("Head", "Customise aspects of your slave's head and face.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD);
				
			} else if(index==6) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS) {
					return new Response("Ass", "You are already customising the aspects of your slave's hips and ass!", null);
				}
				return new Response("Ass", "Customise aspects of your slave's hips and ass.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS);
				
			} else if(index==7) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS) {
					return new Response("Breasts", "You are already customising the aspects of your slave's breasts!", null);
				}
				return new Response("Breasts", "Customise aspects of your slave's breasts.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS);
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA) {
					return new Response("Vagina", "You are already customising the aspects of your slave's vagina!", null);
				}
				return new Response("Vagina", "Customise aspects of your slave's vagina.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA);
				
			} else if(index==9) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS) {
					return new Response("Penis", "You are already customising the aspects of your slave's penis!", null);
				}
				return new Response("Penis", "Customise aspects of your slave's penis.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS);
				
			} else if(index==10) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET) {
					return new Response("Spinneret", "You are already customising the aspects of your slave's spinneret!", null);
				}
				if(!BodyChanging.getTarget().hasSpinneret()) {
					return new Response("Spinneret",
							"Your slave does not have a spinneret!<br/><i>Spinnerets are gained via certain tail or leg types.</i>",
							null);
				}
				return new Response("Spinneret", "Customise aspects of your slave's penis.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET);
				
			} else if(index==11) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH) {
					return new Response("Crotch-boobs", "You are already customising the aspects of your slave's crotch-boobs!", null);
				}
				
				return new Response(
						BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs",
						UtilText.parse(BodyChanging.getTarget(), "Change aspects of your slave's [npc.crotchBoobs]."),
						HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH);
				
			} else if(index==12) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP) {
					return new Response("Makeup", "You are already customising your slave's makeup!", null);
				}
				return new Response("Makeup", "Customise your slave's makeup.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP);
				
			} else if(index==13) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS) {
					return new Response("Piercings", "You are already customising your slave's piercings!", null);
				}
				return new Response("Piercings", "Customise your slave's piercings.", HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS);
				
			} else if(index==14) {
				return new Response("[style.colourMinorGood(Finalise order)]",
						"Tell Helena that you've completed the ordering forms, and see how much this is going to cost you...",
						HELENAS_SHOP_CUSTOM_SLAVE_FINISH) {
					@Override
					public void effects() {
						BodyChanging.getTarget().setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} 
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE"));
			sb.append(BodyChanging.BODY_CHANGING_CORE.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES"));
			sb.append(BodyChanging.BODY_CHANGING_EYES.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR"));
			sb.append(BodyChanging.BODY_CHANGING_HAIR.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD"));
			sb.append(BodyChanging.BODY_CHANGING_HEAD.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS"));
			sb.append(BodyChanging.BODY_CHANGING_ASS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS"));
			sb.append(BodyChanging.BODY_CHANGING_BREASTS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA"));
			sb.append(BodyChanging.BODY_CHANGING_VAGINA.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS"));
			sb.append(BodyChanging.BODY_CHANGING_PENIS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH"));
			sb.append(BodyChanging.BODY_CHANGING_BREASTS_CROTCH.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET"));
			sb.append(BodyChanging.BODY_CHANGING_SPINNERET.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP"));

			sb.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on a person's hands.", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on person's feet.", true, true));
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS = new DialogueNode("Customise Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS"));
			sb.append(CharacterModificationUtils.getKatesDivPiercings(true));
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_FINISH = new DialogueNode("Order Slave", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(false)), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_FINISH"));
			
			sb.append("<div class='container-full-width' style='text-align:center;'>"
							+ "<i>"
								+ "More sexual experience will result in your slave gaining more corruption."
							+ "</i>"
						+ "</div>"
						+CharacterModificationUtils.getSexualExperienceDiv());
			
			sb.append("<div class='container-full-width'>"
						+ UtilText.parse(BodyChanging.getTarget(), "<p style='text-align:center;'><b>[npc.NamePos] Appearance</b></p>")
						+ BodyChanging.getTarget().getBodyDescription()
					+ "</div>");
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Go back and make some changes...", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY);
				
			} else if(index==1) {
				if(Main.game.getPlayer().getMoney()<getSlaveValue(false)) {
					return new Response("Order ("+UtilText.formatAsMoneyUncoloured(getSlaveValue(false), "span")+")",
							"You cannot afford to order the slave, as you only have "+Util.intToString(Main.game.getPlayer().getMoney())+" flames.",
							null);
				}
				return new Response("Order ("+UtilText.formatAsMoney(getSlaveValue(false), "span")+")",
						"Tell Helena that you'd like to order the slave for "+Util.intToString(getSlaveValue(false))+" flames.",
						HELENAS_SHOP_CUSTOM_SLAVE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(false)), true);
						UtilText.addSpecialParsingString(Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getSlaveValue(false)));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<getSlaveValue(true)) {
					return new Response("Slime special ("+UtilText.formatAsMoneyUncoloured(getSlaveValue(true), "span")+")",
							"You cannot afford to order the slime special, as you only have "+Util.intToString(Main.game.getPlayer().getMoney())+" flames.",
							null);
				}
				return new Response("Slime special ("+UtilText.formatAsMoney(getSlaveValue(true), "span")+")",
						"Tell Helena that you'd like to order the slave, with the 'slime special' treatment, for "+Util.intToString(getSlaveValue(true))+" flames.",
						HELENAS_SHOP_CUSTOM_SLAVE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(true)), true);
						UtilText.addSpecialParsingString(Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_SLIME"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getSlaveValue(true)));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_ORDER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().helenaSlaveOrderDay = Main.game.getDayNumber();
			CharacterModificationUtils.resetImpossibeSexExperience(); // If no vagina/penis, reset experience.
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
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			NPC slave = (NPC) getSlaveForCustomisation();
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC slave = (NPC) getSlaveForCustomisation();
			if(index==1) {
				return new Response("Respond", UtilText.parse(slave, "Respond in the positive to [npc.namePos] question."), HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END", slave));
						Main.game.getPlayer().addSlave(slave);
						slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END = new DialogueNode("", "", true, true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			AbstractClothing underwear = Main.game.getNpc(Helena.class).getClothingInSlot(InventorySlot.GROIN);
			if(underwear!=null) {
				Main.game.getNpc(Helena.class).unequipClothingIntoVoid(underwear, true, Main.game.getNpc(Helena.class));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				responses.add(new Response("Cunnilingus", "As you cannot gain access to your mouth, you cannot eat Helena out!", null));
			} else {
				responses.add(new ResponseSex(
						"Cunnilingus",
						"Drop down in front of Helena and start eating her out.",
						true, true,
						HelenaHotel.getHelenaSexManager(false,
								SexPosition.AGAINST_WALL, SexSlotAgainstWall.BACK_TO_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_PERFORM_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				});
			}

			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Anilingus", "As you cannot gain access to your mouth, you cannot perform anilingus on Helena!", null));
				} else {
					responses.add(new ResponseSex(
							"Anilingus",
							"Ask Helena if you can perform anilingus on her instead.",
							true, true,
							HelenaHotel.getHelenaSexManager(false,
									SexPosition.AGAINST_WALL, SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_PERFORM_ANILINGUS")) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
						}
					});
				}
			}
			
			if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) { // If Helena is dominant, she wants to be on the receiving end of oral
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Get blowjob", "As you cannot gain access to your penis, Helena cannot suck your cock.", null));
					} else {
						responses.add(new ResponseSex(
								"Get blowjob",
								"Ask Helena to suck your cock.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.AGAINST_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL
											:SexSlotAgainstWall.PERFORMING_ORAL_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotAgainstWall.BACK_TO_WALL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_BLOWJOB_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
						});
					}
				}
	
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("Eaten out", "As you cannot gain access to your pussy, Helena cannot eat you out.", null));
					} else {
						responses.add(new ResponseSex(
								"Eaten out",
								"Ask Helena to eat you out.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.AGAINST_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL_BEHIND
											:SexSlotAgainstWall.PERFORMING_ORAL_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotAgainstWall.BACK_TO_WALL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_CUNNILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						});
					}
				}
			}
			
			// Penetrative sex:
			
			if(Main.game.getPlayer().hasPenis()) {
				if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
						responses.add(new Response("Ridden", "Helena is unwilling to lose her virginity in a situation like this!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Ridden", "As you cannot gain access to your penis, you cannot fuck Helena.", null));
						
					} else {
						responses.add(new ResponseSex(
								"Ridden",
								"Ask Helena to ride your cock.",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RIDDEN")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
							}
						});
					}
					if(Main.game.isAnalContentEnabled()) {
						if(Main.game.getNpc(Helena.class).isAssVirgin()) {
							responses.add(new Response("Ridden (anal)", "Helena is unwilling to lose her anal virginity in a situation like this!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Ridden (anal)", "As you cannot gain access to your penis, you cannot fuck Helena's ass.", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							responses.add(new Response("Ridden (anal)", "Helena will want you to lubricate her ass before fucking it, and as you cannot gain access to your mouth, you cannot do this...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Ridden (anal)",
									"Ask Helena to anally ride your cock.<br/>[style.italicsSex(She will get you to lubricate her ass with your saliva before letting you penetrate her...)]",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RIDDEN_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
								}
							});
						}
					}
					
				} else { // Submissive:
					if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
						responses.add(new Response("Fuck her", "Helena is unwilling to lose her virginity in a situation like this!", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("Fuck her", "As you cannot gain access to your penis, you cannot fuck Helena.", null));
						
					} else {
						responses.add(new ResponseSex(
								"Fuck her",
								"Bend Helena over a nearby desk and fuck her pussy.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										SexPosition.OVER_DESK, Main.game.getNpc(Helena.class).isVisiblyPregnant()?SexSlotDesk.OVER_DESK_ON_BACK:SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_FUCK_HER")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
					if(Main.game.isAnalContentEnabled()) {
						if(Main.game.getNpc(Helena.class).isAssVirgin()) {
							responses.add(new Response("Fuck her (anal)", "Helena is unwilling to lose her anal virginity in a situation like this!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Fuck her (anal)", "As you cannot gain access to your penis, you cannot fuck Helena's ass.", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							responses.add(new Response("Fuck her (anal)", "Helena will want you to lubricate her ass before fucking it, and as you cannot gain access to your mouth, you cannot do this...", null));
							
						} else {
							responses.add(new ResponseSex(
									"Fuck her (anal)",
									"Bend Helena over a nearby desk and fuck her ass.<br/>[style.italicsSex(She will get you to lubricate her ass with your saliva before letting you penetrate her...)]",
									true, true,
									HelenaHotel.getHelenaSexManager(false,
											SexPosition.OVER_DESK, Main.game.getNpc(Helena.class).isVisiblyPregnant()?SexSlotDesk.OVER_DESK_ON_BACK:SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_FUCK_HER_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_AFTER_SEX = new DialogueNode("Finished", "Helena is done and need to return to work.", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	//TODO
	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_THREESOME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Helena focus";
			} else if(index==1) {
				return "Scarlett focus";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(responseTab==0) { // Helena focus:
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("Cunnilingus", "As you cannot gain access to your mouth, you cannot eat Helena out!", null));
				} else {
					responses.add(new ResponseSex(
							"Cunnilingus",
							"Get Helena to lie down so that you can drop your head between her legs and eat her out while Scarlett sits on her face.",
							true, true,
							HelenaHotel.getHelenaSexManager(false,
									true,
									true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.MISSIONARY_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Main.game.getNpc(Scarlett.class).hasVagina()
										?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
										:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
									null,
									Main.game.getNpc(Scarlett.class).hasVagina()
										?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
										:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
												:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_PERFORM_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
							} else {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
							return list;
						}
					});
				}

				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Anilingus", "As you cannot gain access to your mouth, you cannot perform anilingus on Helena!", null));
					} else {
						responses.add(new ResponseSex(
								"Anilingus",
								"Get Helena to sit on your face so that you can perform anilingus on her while she performs oral on Scarlett.",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										true,
										false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.BESIDE, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Main.game.getNpc(Scarlett.class).hasVagina()
											?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
											:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										null,
										Main.game.getNpc(Scarlett.class).hasVagina()
											?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
											:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
													:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_PERFORM_ANILINGUS")
								+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
								if(Main.game.getNpc(Scarlett.class).hasVagina()) {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
								} else {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
								}
								return list;
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) { // If Helena is dominant, she wants to be on the receiving end of oral
					if(Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Get blowjob", "As you cannot gain access to your penis, Helena cannot suck your cock.", null));
						} else {
							if(Main.game.getNpc(Scarlett.class).hasPenis()
									&& (!Main.game.getNpc(Helena.class).isAssVirgin() || !Main.game.getNpc(Helena.class).isVaginaVirgin())) {
								if(!Main.game.getNpc(Helena.class).isAssVirgin()) {
									responses.add(new ResponseSex(
											"Get blowjob",
											"Get Helena to give you a blowjob while she gets anally fucked by Scarlett from behind.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB_SCARLETT_ANAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
									
								} else {
									responses.add(new ResponseSex(
											"Get blowjob",
											"Get Helena to give you a blowjob while she gets fucked by Scarlett from behind.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB_SCARLETT_VAGINAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
								}
								
							} else {
								responses.add(new ResponseSex(
										"Get blowjob",
										"Get Helena to give you a blowjob while she sits on Scarlett's face.",
										true, true,
										HelenaHotel.getHelenaSexManager(true,
												false,
												true,
												SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.BESIDE,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
												null,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
														new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB")
										+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
											?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_BLOWJOB_FIRST_TIME")
											:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_BLOWJOB_EXPERIENCED"))) {
									@Override
									public void effects() {
										if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
										}
									}
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										return list;
									}
								});
							}
						}
					}
		
					if(Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							responses.add(new Response("Eaten out", "As you cannot gain access to your pussy, Helena cannot eat you out.", null));
						} else {
							if(Main.game.getNpc(Scarlett.class).hasPenis()
									&& (!Main.game.getNpc(Helena.class).isAssVirgin() || !Main.game.getNpc(Helena.class).isVaginaVirgin())) {
								if(!Main.game.getNpc(Helena.class).isAssVirgin()) {
									responses.add(new ResponseSex(
											"Eaten out",
											"Get Helena to eat you out while she gets anally fucked by Scarlett from behind.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS_SCARLETT_ANAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
									
								} else {
									responses.add(new ResponseSex(
											"Eaten out",
											"Get Helena to eat you out while she gets fucked by Scarlett from behind.",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS_SCARLETT_VAGINAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
								}
								
							} else {
								responses.add(new ResponseSex(
										"Eaten out",
										"Sit on Helena's face and have her eat you out while Scarlett drops down between her legs and performs cunnilingus on her.",
										true, true,
										HelenaHotel.getHelenaSexManager(false,
												true,
												true,
												SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.FACE_SITTING,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
												null,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
														new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS")
										+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
											?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "GIVING_CUNNILINGUS_FIRST_TIME")
											:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "GIVING_CUNNILINGUS_EXPERIENCED"))) {
									@Override
									public void effects() {
										if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
										}
									}
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										return list;
									}
								});
							}
						}
					}
				}
				
				// Penetrative sex:
				
				if(Main.game.getPlayer().hasPenis()) {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
							responses.add(new Response("Ridden", "Helena is unwilling to lose her virginity in a situation like this!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Ridden", "As you cannot gain access to your penis, you cannot fuck Helena.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Ridden",
									"Get Helena to ride your cock while Scarlett sits on your face.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
												:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													Main.game.getNpc(Scarlett.class).hasVagina()
														?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH))
														:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									if(Main.game.getNpc(Scarlett.class).hasVagina()) {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									} else {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
									}
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getNpc(Helena.class).isAssVirgin()) {
								responses.add(new Response("Ridden (anal)", "Helena is unwilling to lose her anal virginity in a situation like this!", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								responses.add(new Response("Ridden (anal)", "As you cannot gain access to your penis, you cannot fuck Helena's ass.", null));
								
							} else {
								responses.add(new ResponseSex(
										"Ridden (anal)",
										"Get Helena to anally ride your cock while Scarlett sits on your face.",
										true, true,
										HelenaHotel.getHelenaSexManager(true,
												true,
												false,
												SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
													:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)),
														Main.game.getNpc(Scarlett.class).hasVagina()
															?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH))
															:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RIDDEN_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
										if(Main.game.getNpc(Scarlett.class).hasVagina()) {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										} else {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
										}
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
										return list;
									}
								});
							}
						}
						
					} else { // Submissive:
						if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
							responses.add(new Response("Fuck her", "Helena is unwilling to lose her virginity in a situation like this!", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Fuck her", "As you cannot gain access to your penis, you cannot fuck Helena.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Fuck her",
									"Push Helena down onto all fours and fuck her from behind while she performs oral on Scarlett.",
									true, true,
									HelenaHotel.getHelenaSexManager(false,
											true,
											true,
											SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
												:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
											null,
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
												:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													Main.game.getNpc(Scarlett.class).hasVagina()
														?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
														:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_FUCK_HER")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
									if(Main.game.getNpc(Scarlett.class).hasVagina()) {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									} else {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
									}
									return list;
								}
							});
						}
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getNpc(Helena.class).isAssVirgin()) {
								responses.add(new Response("Fuck her (anal)", "Helena is unwilling to lose her anal virginity in a situation like this!", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								responses.add(new Response("Fuck her (anal)", "As you cannot gain access to your penis, you cannot fuck Helena's ass.", null));
								
							} else {
								responses.add(new ResponseSex(
										"Fuck her (anal)",
										"Push Helena down onto all fours and fuck her ass while she performs oral on Scarlett.",
										true, true,
										HelenaHotel.getHelenaSexManager(false,
												true,
												true,
												SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
													:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
												null,
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
													:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)),
														Main.game.getNpc(Scarlett.class).hasVagina()
															?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
															:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_FUCK_HER_ANAL")) { // Player anilingus first
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
										if(Main.game.getNpc(Scarlett.class).hasVagina()) {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										} else {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
										}
										return list;
									}
								});
							}
						}
					}
				}
				
			} else if(responseTab==1) { // Scarlett focus:
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Blowjob", "As you cannot gain access to your mouth, you cannot give Scarlett a blowjob!", null));
					} else {
						responses.add(new ResponseSex(
								"Blowjob",
								"Join Helena in kneeling down and giving Scarlett a blowjob.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										true,
										false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL_TWO,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_DOUBLE_BLOWJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								return list;
							}
						});
					}
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Cunnilingus", "As you cannot gain access to your mouth, you cannot eat Scarlett out!", null));
					} else {
						responses.add(new ResponseSex(
								"Cunnilingus",
								"Join Helena in dropping down before Scarlett and orally servicing her pussy.",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										true,
										false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL_TWO,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_DOUBLE_CUNNILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START_ADDITIONAL, false, true));
								return list;
							}
						});
					}
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("Anilingus", "As you cannot gain access to your mouth, you cannot perform anilingus on Scarlett!", null));
					} else {
						responses.add(new ResponseSex(
								"Anilingus",
								"Get Scarlett to sit on your face so that you can perform anilingus on her while she performs oral on Helena.",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										true,
										false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.BESIDE, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueAnus.ANILINGUS_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
								return list;
							}
						});
					}
				}
				
				// Penetrative sex:

				if(Main.game.getPlayer().hasPenis()) {
					if(Main.game.getNpc(Scarlett.class).hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Ridden", "As you cannot gain access to your penis, you cannot fuck Scarlett.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Ridden",
									"Get Scarlett to ride your cock while Helena sits on your face.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}

				if(Main.game.getNpc(Scarlett.class).hasVagina()) {
					if(Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("Ridden", "As you cannot gain access to your penis, you cannot fuck Scarlett.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Ridden",
									"Get Scarlett to ride your cock while Helena sits on your face.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}

				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							responses.add(new Response("Fucked", "As you cannot gain access to your pussy, Scarlett cannot fuck you.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Fucked",
									"Get Scarlett to fuck your pussy while you perform cunnilingus on Helena.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_FUCKED")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							responses.add(new Response("Fucked (anal)", "As you cannot gain access to your ass, Scarlett cannot fuck you.", null));
							
						} else {
							responses.add(new ResponseSex(
									"Fucked (anal)",
									"Get Scarlett to fuck your ass while you perform cunnilingus on Helena.",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_FUCKED_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME = new DialogueNode("Finished", "Helena is done and need to return to work.", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			Main.game.getNpc(Scarlett.class).cleanAllClothing(true, false);
			Main.game.getNpc(Scarlett.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	
	// Scarlett:
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Tell Scarlett that she can get back to work now.", HELENAS_SHOP_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_END"));
					}
				};
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettTalkedTo)) {
					return new Response("Talk", "You've already spent some time talking to Scarlett...", null);
				}
				return new Response("Talk", "Ask Scarlett how she's been recently.", HELENAS_SHOP_SCARLETT_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettTalkedTo, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCounterOral)) {
					return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"Blowjob":"Cunnilingus",
							"You've already given Scarlett oral today...",
							null);
				}
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"Blowjob":"Cunnilingus",
							"Scarlett isn't attracted to you, so she'd be unwilling to let you give her "+(Main.game.getNpc(Scarlett.class).hasPenis()?"a quick blowjob":"some quick cunnilingus")+".",
							null);
				}
				return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"Blowjob":"Cunnilingus",
						"Kneel down beneath the shop's counter and give Scarlett "+(Main.game.getNpc(Scarlett.class).hasPenis()?"a quick blowjob":"some quick cunnilingus")+".",
						HELENAS_SHOP_SCARLETT_COUNTER_ORAL) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCounterOral, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==5) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Cafe"
								:"Lunch break",
							"Scarlett is only willing to spend her lunch break with people she's attracted to, and as you're not feminine enough for her liking, she's unwilling to spend it with you...",
							null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafe)) {
					return new Response("Cafe", "You've already been out to the cafe with Scarlett today...", null);
					
				} else if(Main.game.getHourOfDay()<11) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Cafe"
								:"Lunch break",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"It's too early for Scarlett to take a lunch break, and so she can't go out to the cafe with you. Try again between [units.time(11)] and [units.time(15)]."
								:"It's too early for Scarlett to take a lunch break. Try again between [units.time(11)] and [units.time(15)].",
							null);
					
				} else if(Main.game.getHourOfDay()>15) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Cafe"
								:"Lunch break",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"Scarlett has already taken her lunch break, and so she can't go out to a cafe with you. Try again another day between [units.time(11)] and [units.time(15)]."
								:"Scarlett has already taken her lunch break, and so can't spend it with you. Try again another day between [units.time(11)] and [units.time(15)].",
							null);
					
				}
				
				return new Response(
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
							?"Cafe"
							:"Lunch break",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
							?"Ask Scarlett if she'd like to spend her lunch break with you at the cafe again."
							:"Ask Scarlett if she'd like to spend her lunch break with you.",
						HELENAS_SHOP_SCARLETT_CAFE);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_COUNTER_ORAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"Suck cock",
							"Do as Scarlett says and suck her cock...",
							true,
							false,
							new SMScarlettShopOral(),
							null,
							null,
							HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"Eat her out",
							"Do as Scarlett says and eat her out...",
							true,
							false,
							new SMScarlettShopOral(),
							null,
							null,
							HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL = new DialogueNode("Finished", "Scarlett is satisfied, and steps back from the counter...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_END = new DialogueNode("", "", true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				if(index==1) {
					return new Response("Cafe", "Let Scarlett lead you to the cafe she wants to visit.", HELENAS_SHOP_SCARLETT_CAFE_ARRIVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_CORE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<150) {
						return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(150, "span")+")", "You don't have enough money to pay for Scarlett's lunch...", null);
					}
					return new Response("Pay ("+UtilText.formatAsMoney(150, "span")+")", "Tell Scarlett that you're willing to pay for her lunch.", HELENAS_SHOP_SCARLETT_CAFE_ARRIVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_PAY"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_CORE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-150));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else if(index==2) {
					return new Response("Refuse", "Refuse to pay for Scarlett's lunch.", HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY);
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_ARRIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_CAFE, false);
			Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCafe, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed, true);
		}
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
				if(Main.game.getPlayer().getMoney()<150) {
					return new Response("Order ("+UtilText.formatAsMoneyUncoloured(150, "span")+")", "You don't have enough money to order lunch...", null);
				}
				return new Response("Order ("+UtilText.formatAsMoney(150, "span")+")", "Order the same sandwich selection that Scarlett's choosing.", HELENAS_SHOP_SCARLETT_CAFE_EATING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_ARRIVE_EATING_LUNCH"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-150));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Don't order", "Don't order anything and instead just sit and talk with Scarlett while she eats.", HELENAS_SHOP_SCARLETT_CAFE_EATING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_ARRIVE_NO_LUNCH"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_EATING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_DISCUSSION");
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_DISCUSSION_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_APPLIED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
				return HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Discourage",
							"Discourage Scarlett from drinking the potion for the time being.<br/>[style.italics(You can get her to drink it another time, if you change your mind later on.)]",
							HELENAS_SHOP_SCARLETT_CAFE_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_NO_TRANSFORMATION"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
						}
					};
					
				} else if(index==2) {
					return new Response("Drink",
							"Encourage Scarlett to drink the potion.<br/>"
							+ (Main.game.getNpc(Scarlett.class).hasVagina()
								?"[style.italicsTfSex(This will grow Scarlett's breasts by one cup size (to "+CupSize.getCupSizeFromInt(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+1)+"-cups),"
										+ " and will also make her appear more feminine!)]"
								:"[style.italicsTfSex(This will increase Scarlett's cum production, as well as thickening her cock and expanding it to [style.sizes(20)] in length!)]"),
							HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED"));
							
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								Main.game.getNpc(Scarlett.class).setFemininity(90);
								Main.game.getNpc(Scarlett.class).setBreastSize(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+1);
								Main.game.getNpc(Scarlett.class).setVaginaWetness(Wetness.FIVE_SLOPPY);
								
							} else {
								Main.game.getNpc(Scarlett.class).setPenisSize(20);
								Main.game.getNpc(Scarlett.class).setPenisGirth(PenetrationGirth.THREE_AVERAGE);
								Main.game.getNpc(Scarlett.class).setPenisCumStorage(50);
							}

							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_CORE"));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getPlayer().getEssenceCount()<10) {
						return new Response("Boost (10 essences)", "You need at least ten arcane essences to boost the effects of the transformation potion!", null);
					}
					return new Response("Boost ([style.colourArcane(10 essences)])",
							"Use ten of your arcane essences to boost the effects of Scarlett's potion.<br/>"
							+ (Main.game.getNpc(Scarlett.class).hasVagina()
								?"[style.italicsTfSex(This will grow Scarlett's breasts by two cup sizes (to "+CupSize.getCupSizeFromInt(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+2)+"-cups),"
										+ " and will also make her appear far more feminine!)]"
								:"[style.italicsTfSex(This will increase Scarlett's cum production, as well as thickening her cock and expanding it to [style.sizes(30)] in length!)]"),
							HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_EXTRA"));
							
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								Main.game.getNpc(Scarlett.class).setFemininity(95);
								Main.game.getNpc(Scarlett.class).setBreastSize(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+2);
								Main.game.getNpc(Scarlett.class).setVaginaWetness(Wetness.FIVE_SLOPPY);
								
							} else {
								Main.game.getNpc(Scarlett.class).setPenisSize(30);
								Main.game.getNpc(Scarlett.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Scarlett.class).setPenisCumStorage(150);
							}

							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(-10, false));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED = new DialogueNode("", "", true, true) {
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
				return new Response("Refuse", "Tell Scarlett that you don't want to do something so lewd with her.", HELENAS_SHOP_SCARLETT_CAFE_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_NO_SEX"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"Handjob",
							"Move around to sit next to Scarlett and give her a handjob...",
							true,
							false,
							getScarlettCafeSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_MASTURBATION_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else {
					return new ResponseSex(
							"Finger her",
							"Move around to sit next to Scarlett and start fingering her...",
							true,
							false,
							getScarlettCafeSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
							null,
							null,
							HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_MASTURBATION_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERED_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX = new DialogueNode("Finished", "Scarlett is satisfied, and tells you to move away from her...", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT_CAFE_END.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_END = new DialogueNode("", "", true, true) {
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
				return new Response("Return", "Accompany Scarlett back to Helena's Boutique.", HELENAS_SHOP_SCARLETT_CAFE_RETURNED) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false); // Just in case time passed closing time during sex.
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_RETURNED"));
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationHelenaReacted, true);
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_RETURNED = new DialogueNode("", "", true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	
}
