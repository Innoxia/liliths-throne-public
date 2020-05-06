package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7.5
 * @author Innoxia
 */
public class DominionExpress {
	
	public static Map<Colour, Integer> COLLAR_POINTS_MAP = Util.newHashMapOfValues(
			new Value<>(PresetColour.CLOTHING_STEEL, 0),
			new Value<>(PresetColour.CLOTHING_BRONZE, 50),
			new Value<>(PresetColour.CLOTHING_SILVER, 100),
			new Value<>(PresetColour.CLOTHING_GOLD, 200));

	public static Map<Colour, Integer> COLLAR_DAILY_WAGE_MAP = Util.newHashMapOfValues(
			new Value<>(PresetColour.CLOTHING_STEEL, 0),
			new Value<>(PresetColour.CLOTHING_BRONZE, 100),
			new Value<>(PresetColour.CLOTHING_SILVER, 250),
			new Value<>(PresetColour.CLOTHING_GOLD, 500));
	
	private static SexAreaInterface sleepSexAreaWanted = null;
	private static int slavePointsReward = 1;
	
	private static boolean isPlayerBodyCorrect() {
		return Main.game.getPlayer().getLegConfiguration()==LegConfiguration.TAUR
				&& Main.game.getPlayer().isFeminine()
				&& Main.game.getPlayer().hasPenis()
				&& !Main.game.getPlayer().hasVagina();
	}

	private static String applyTransformation(boolean centaurLowerBody) {
		StringBuilder sb =  new StringBuilder();
		
		if(!Main.game.getPlayer().isFeminine()) {
			sb.append(Main.game.getPlayer().setFemininity(75));
		}
		if(Main.game.getPlayer().getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
			sb.append(Main.game.getPlayer().setBreastSize(CupSize.C));
		}
		if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
			AbstractRacialBody rb = RacialBody.valueOfRace(Main.game.getPlayer().getRace());
			sb.append(Main.game.getPlayer().setPenisType(rb.getPenisType()));
		}
		if(Main.game.getPlayer().hasVagina()) {
			sb.append(Main.game.getPlayer().setVaginaType(VaginaType.NONE));
		}
		if((centaurLowerBody || !Main.game.getPlayer().getLegType().isLegConfigurationAvailable(LegConfiguration.TAUR)) && Main.game.getPlayer().getLegType()!=LegType.HORSE_MORPH) {
			sb.append(Main.game.getPlayer().setLegType(LegType.HORSE_MORPH));
		}
		if(Main.game.getPlayer().getLegConfiguration()!=LegConfiguration.TAUR) {
			sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.TAUR, true));
		}
		
		return sb.toString();
	}
	
	public static Colour getColourFromPoints() {
		int points = Main.game.getDialogueFlags().getNatalyaPoints();
		
		if(points>=COLLAR_POINTS_MAP.get(PresetColour.CLOTHING_GOLD)) {
			return PresetColour.CLOTHING_GOLD;
			
		} else if(points>=COLLAR_POINTS_MAP.get(PresetColour.CLOTHING_SILVER)) {
			return PresetColour.CLOTHING_SILVER;
			
		} else if(points>=COLLAR_POINTS_MAP.get(PresetColour.CLOTHING_BRONZE)) {
			return PresetColour.CLOTHING_BRONZE;
		}
		
		return PresetColour.CLOTHING_STEEL;
	}
	
	private static AbstractClothing getPlayerCollar() {
		return Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
	}
	
	private static boolean wearingFillyCollar() {
		return getPlayerCollar()!=null && getPlayerCollar().getClothingType()==ClothingType.getClothingTypeFromId("innoxia_neck_filly_choker");
	}
	
	private static AbstractClothing generateCollar() {
		return AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_filly_choker"), getColourFromPoints(), false);
	}
	
	private static void spawnSlaves(int count, boolean feminine) {
		spawnSlaves(count, feminine, null);
	}
	
	private static void spawnSlaves(int count, boolean feminine, Colour collarColour) {
		slavePointsReward = 2+Util.random.nextInt(4); // Slaves give the player 2-5 points to service them
				
		List<String> names;
		if(feminine) {
			names = Util.newArrayListOfValues("horny centauress", "lustful centauress", "desperate centauress");
		} else {
			names = Util.newArrayListOfValues("horny centaur", "lustful centaur", "desperate centaur");
		}
		Collections.shuffle(names);
		
		for(int i=0; i<count; i++) {
			NPC npc;
			if(collarColour==null) {
				collarColour = PresetColour.CLOTHING_GOLD;
				double rnd = Math.random();
				if(rnd<0.3f) {
					collarColour = PresetColour.CLOTHING_STEEL;
				} else if(rnd<0.8f) {
					collarColour = PresetColour.CLOTHING_BRONZE;
				} else if(rnd<0.95f) {
					collarColour = PresetColour.CLOTHING_SILVER;
				}
			}
			if(feminine) {
				npc = new GenericSexualPartner(Gender.F_P_B_SHEMALE, WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getCell(PlaceType.GENERIC_HOLDING_CELL).getLocation(), false, (s)->s!=Subspecies.CENTAUR);
				npc.unequipAllClothing(npc, true, true);
				npc.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), collarColour, false), true, npc);
				if(Math.random()<0.5f) {
					npc.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_TUBE_TOP, false), true, npc);
				} else {
					npc.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, false), true, npc);
				}
				
			} else {
				npc = new GenericSexualPartner(Gender.M_P_MALE, WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getCell(PlaceType.GENERIC_HOLDING_CELL).getLocation(), false, (s)->s!=Subspecies.CENTAUR);
				npc.unequipAllClothing(npc, true, true);
				npc.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), collarColour, false), true, npc);
			}
			
			if(Main.game.getPlayer().isFeminine() && Math.random()<0.75f) {
				npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			} else {
				npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			}
			
			npc.clearFetishes();
			npc.clearFetishDesires();
			
			npc.addFetish(Fetish.FETISH_ANAL_GIVING);
			npc.addFetish(Fetish.FETISH_ORAL_RECEIVING);

			npc.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.ZERO_HATE);
			npc.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.ZERO_HATE);
			
			npc.setAssVirgin(false);
			npc.setPenisVirgin(false);
			npc.setFaceVirgin(false);
			
			npc.setGenericName(names.get(i));
			
			npc.setLocation(Main.game.getPlayer(), false);
			try {
				Main.game.addNPC(npc, false);
				Main.game.setActiveNPC(npc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->npc.isUnique());
		return list;
	}

	private static void banishSlaves() {
		for(GameCharacter npc : getSlaves()) {
			Main.game.banishNPC((NPC) npc);
		}
	}

//	private static SexManagerInterface new SMDominionExpress(AbstractSexPosition position,
//			Map<GameCharacter, SexSlot> dominants,
//			Map<GameCharacter, SexSlot> submissives,
//			Map<GameCharacter, SexType> preferences,
//			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
//		return new SMDominionExpress(position,
//				dominants,
//				submissives,
//				preferences,
//				exposeAtStartOfSexMap,
//				null,
//				null);
//	}
//	
//	private static SexManagerInterface new SMDominionExpress(AbstractSexPosition position,
//			Map<GameCharacter, SexSlot> dominants,
//			Map<GameCharacter, SexSlot> submissives,
//			Map<GameCharacter, SexType> preferences,
//			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap,
//			Map<GameCharacter, OrgasmBehaviour> orgasmBehaviours,
//			Map<GameCharacter, OrgasmCumTarget> orgasmCumTargets) {
//		return new SexManagerDefault(
//				true,
//				position,
//				dominants,
//				submissives) {
//			@Override
//			public boolean isPublicSex() {
//				return false;
//			}
//			@Override
//			public SexControl getSexControl(GameCharacter character) {
//				return SexControl.ONGOING_ONLY; // So Natalya doesn't start anything else.
//			}
//			@Override
//			public boolean isAbleToEquipSexClothing(GameCharacter character){
//				return false;
//			}
//			@Override
//			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
//				return true;
//			}
//			@Override
//			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
//				return false;
//			}
//			@Override
//			public boolean isPositionChangingAllowed(GameCharacter character) {
//				return false;
//			}
//			@Override
//			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
//				return exposeAtStartOfSexMap;
//			}
//			@Override
//			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
//				return new ArrayList<>();
//			}
//			@Override
//			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
//				if(preferences.containsKey(character)) {
//					return preferences.get(character);
//				}
//				return super.getForeplayPreference(character, targetedCharacter);
//			}
//			@Override
//			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
//				if(!character.isPlayer()) {
//					return getForeplayPreference(character, targetedCharacter);
//				}
//				return character.getMainSexPreference(targetedCharacter);
//			}
//			@Override
//			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
//				if(orgasmBehaviours!=null && orgasmBehaviours.containsKey(character)) {
//					return orgasmBehaviours.get(character);
//				}
//				return super.getCharacterOrgasmBehaviour(character);
//			}
//			@Override
//			public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
//				if(orgasmCumTargets!=null && orgasmCumTargets.containsKey(character)) {
//					return orgasmCumTargets.get(character);
//				}
//				return super.getCharacterPullOutOrgasmCumTarget(character, target);
//			}
//		};
//	}
	
	private static String getFillyDetectionText() {
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
			UtilText.addSpecialParsingString(String.valueOf(Main.game.getDialogueFlags().getNatalyaPoints()), true);
			UtilText.addSpecialParsingString(UtilText.formatAsMoney(Main.game.getDialogueFlags().getNatalyaWages(), "span"), false);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_DETECTION"));
			
			if(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
				if(COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour())) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_DETECTION_PROMOTION"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_DETECTION_DEMOTION"));
				}
			}
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode INITIAL_ENTRANCE = new DialogueNode("", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
				return ENTRANCE.getResponse(responseTab, index);
				
			} else {
				if(index==0) {
					return new Response("Exit", "Tell the receptionist that you made a mistake and head back out into the warehouse district.", Warehouses.WAREHOUSE_DISTRICT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
						}
					};
				}
				if(index==1) {
					return new Response("Show card", "Show the receptionist the card you received from Natalya.", INITIAL_ENTRANCE_CARD_SHOWN);
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode INITIAL_ENTRANCE_CARD_SHOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE_CARD_SHOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Exit", "Head back out into the warehouse district.", Warehouses.WAREHOUSE_DISTRICT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
					}
				};
			}
			return null;
		}
	};

	//TODO Encounter if filly, can get dragged behind nearby crates and fucked
	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	//TODO Encounter if filly, can get dragged behind nearby crates and fucked
	public static final DialogueNode STORAGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STABLES = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(wearingFillyCollar()) {
					return new Response("Enter", "Enter the stables and look for centaur slaves to service...", STABLES_ENTERED);
					
				} else {
					return new Response("Enter", "As you are not a slave or qualified filly, you cannot enter the stables...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLES_ENTERED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTERED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Exit", "Exit the stables and head back out into the main warehouse...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_EXIT"));
					}
				};
				
			} else if(index==1) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) 
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Centauress", "You cannot gain access to your mouth or anus, and so cannot service any slaves...", null);
				}
				return new Response("Centauress", "Search for a centauress to service...", STABLE_SEX) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
					@Override
					public void effects() {
						spawnSlaves(1, true);
						UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", getSlaves()));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) 
						&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Centaur", "You cannot gain access to your mouth or anus, and so cannot service any slaves...", null);
				}
				return new Response("Centaur", "Search for a centaur to service...", STABLE_SEX) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
					@Override
					public void effects() {
						spawnSlaves(1, false);
						UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", getSlaves()));
					}
				};
				
			} else if(index==5) {
				if(Main.game.getCurrentDialogueNode()==STABLE_SHOWER) {
					return new Response("Shower", "You have just taken a shower!", null);
				}
				return new Response("Shower",
							"Use the stable's shower facilities to clean yourself."
								+ "<br/>[style.italicsGood(Cleans <b>a maximum of "+Units.fluid(500)+"</b> of fluids from all orifices.)]"
								+ "<br/>[style.italicsGood(This will clean <b>only</b> your currently equipped clothing.)]",
							STABLE_SHOWER);
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLE_SEX = new DialogueNode("", "", true, true) {
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
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Suck cock", UtilText.parse(getSlaves(), "You cannot gain access to your mouth, and so cannot suck [npc.namePos] cock..."), null);
				}
				return new ResponseSex(
						"Suck cock",
						UtilText.parse(getSlaves(), "Drop down onto your knees and suck [npc.namePos] cock."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(getSlaves().get(0), new HashMap<>());
								map.get(getSlaves().get(0)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSlaves().get(0)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), getSlaves().get(0), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Anilingus", UtilText.parse(getSlaves(), "You cannot gain access to your mouth, and so cannot perform anilingus on [npc.name]..."), null);
				}
				return new ResponseSex(
						"Anilingus",
						UtilText.parse(getSlaves(), "Drop down onto your knees behind [npc.name] and perform anilingus on [npc.herHim]."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getSlaves().get(0), TongueAnus.ANILINGUS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getSlaves().get(0), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Mounted", UtilText.parse(getSlaves(), "You cannot gain access to your anus, and so cannot get mounted by [npc.name]..."), null);
				}
				return new ResponseSex(
						"Mounted",
						UtilText.parse(getSlaves(), "Present your [pc.asshole+] to [npc.name] and let [npc.herHim] mount you."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(getSlaves().get(0), new HashMap<>());
								map.get(getSlaves().get(0)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSlaves().get(0)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_MOUNTED")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getSlaves().get(0), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(slavePointsReward));
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(getSlaves(), "Leave [npc.name] behind and continue on your way through the stables."), STABLES_ENTERED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", getSlaves()));
						banishSlaves();
					}
				};
				
			} else if(index==2) {
				if(Main.game.getHourOfDay()>4 && Main.game.getHourOfDay()<22) {
					return new Response("Sleep", UtilText.parse(getSlaves(), "You can only sleep with a slave between [style.time(22)] and [style.time(4)]."), null);
					
				} else {
					return new Response("Sleep", UtilText.parse(getSlaves(), "Accept [npc.namePos] offer to sleep with [npc.herHim] for the night."), AFTER_STABLE_SEX_SLEEP);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_STABLE_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			List<SexAreaInterface> list = Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				list.remove(SexAreaOrifice.MOUTH);
				list.remove(SexAreaPenetration.TONGUE);
			}
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				list.remove(SexAreaOrifice.ANUS);
			}
			if(list.isEmpty() || Math.random()<0.25f) {
				sleepSexAreaWanted = null;
			} else {
				sleepSexAreaWanted = Util.randomItemFrom(list);
			}
			if(sleepSexAreaWanted==null) {
				Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
			}
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(6)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP", getSlaves()));
			if(sleepSexAreaWanted==null) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_NO_SEX", getSlaves()));
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_BLOWJOB", getSlaves()));
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_ANILINGUS", getSlaves()));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_MOUNTED", getSlaves()));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(sleepSexAreaWanted==null) {
				if(index==1) {
					return new Response("Continue", UtilText.parse(getSlaves(), "Leave [npc.name] behind and continue on your way through the stables."), STABLES_ENTERED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", getSlaves()));
							banishSlaves();
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				if(index==1) {
					return new ResponseSex(
							"Suck cock",
							UtilText.parse(getSlaves().get(0), "You have little choice but to suck on [npc.namePos] fat, feral horse-cock."),
							true,
							false,
							new SMDominionExpress(SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
									Util.newHashMapOfValues(
											new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_BLOWJOB", getSlaves())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getSlaves().get(0), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				if(index==1) {
					return new ResponseSex(
							"Anilingus",
							UtilText.parse(getSlaves().get(0), "You have little choice but to give [npc.name] a rimjob."),
							true,
							false,
							new SMDominionExpress(SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotLyingDown.FACE_SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
									Util.newHashMapOfValues(
											new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_ANILINGUS", getSlaves())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getSlaves().get(0), Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"Fucked",
							UtilText.parse(getSlaves().get(0), "You have little choice but to let [npc.name] pound your asshole."),
							true,
							false,
							new SMDominionExpress(SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
									Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
									Util.newHashMapOfValues(
											new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_MOUNTED", getSlaves())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(getSlaves().get(0), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(getSlaves().get(0), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_BLOWJOB", getSlaves());
				
			} else if(Main.sex.getForeplayPreference(getSlaves().get(0), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_ANILINGUS", getSlaves());
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_MOUNTED", getSlaves());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", UtilText.parse(getSlaves(), "Leave [npc.name] behind and continue on your way through the stables."), STABLES_ENTERED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", getSlaves()));
						banishSlaves();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STABLE_SHOWER = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().cleanAllClothing(false);
			Main.game.getPlayer().cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 10 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SHOWER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STABLES_ENTERED.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE"));
			
			sb.append(getFillyDetectionText());
			
			if(Main.game.getPlayer().isPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaBusy)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_PREGNANT"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isAnalContentEnabled() && !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					return new Response("Enter",
							"You get the feeling that you don't want anything to do with Natalya..."
									+ "<br/>[style.italicsMinorBad(Natalya's scenes involve anal content, and as such will be disabled for as long as your 'Anal Content' setting is turned off.)]",
							null);
				}
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaBusy)) {
					return new Response("Enter", "Natalya has told you not to bother her for the rest of the day...", null);
					
				} else if(Main.game.getPlayer().isPregnant()) {
					return new Response("Enter", "You are not allowed into Natalya's office while pregnant!", null);
					
				} else if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_3_TRAINING_1)
						&& !isPlayerBodyCorrect()
						&& !Main.game.getPlayer().isAbleToHaveRaceTransformed()
						&& !Main.game.getPlayer().isAbleToSelfTransform()) {
					return new Response("Enter",
							"You should not return to Natalya until you're able to be transformed into [style.a_shemale] taur!"
									+ "<br/>[style.italicsBad(One or more items of equipped clothing are blocking your ability to self-transform!)]",
							null);
					
				} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Enter", "You need to be able to access your mouth in order to receive Natalya's training!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("Enter", "You need to be able to access your asshole in order to receive Natalya's training!", null);
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3
							&& !Main.game.getPlayer().isAbleToEquip(generateCollar(), true, Main.game.getNpc(Natalya.class))) {
						return new Response("Enter", "You need to be able to equip clothing in your neck slot in order to finish Natalya's training!", null);
					}
				}
				
				return new Response("Enter",
						"Knock on the door to the office and step inside."
						+ (!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
							?"<br/>[style.italicsQuestRomance(This will start Natalya's romance quest!)]"
							:""),
						OFFICE_STABLE_ENTRY) {
					@Override
					public Colour getHighlightColour() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
							return PresetColour.QUEST_RELATIONSHIP;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_NATALYA));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_ENTRY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if((Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_3_TRAINING_1) && !isPlayerBodyCorrect())
					|| (Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && !wearingFillyCollar())
					|| (Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && getPlayerCollar().getColour()!=Main.game.getDialogueFlags().getNatalyaCollarColour())) {
				Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -25));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY"));

			AbstractClothing collar = getPlayerCollar();
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_CONTRACT"));
				
			} else if(!isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WRONG_BODY"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_3_TRAINING_1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_1"));
				sb.append(OFFICE_STABLE_TRANSFORMED.getContent());
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_2"));
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_3"));
				
			} else { // Qualified filly:
				if(!wearingFillyCollar()) {
					if(!Main.game.getPlayer().isAbleToEquip(generateCollar(), true, Main.game.getNpc(Natalya.class))) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_NO_COLLAR_CANNOT_EQUIP"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_NO_COLLAR"));
					}
					
				} else if(collar.getColour()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_DYED"));
					
				} else if(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
					if(COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour())) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_UPGRADE"));
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_DOWNGRADE"));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WITH_COLLAR"));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				if(index==0) {
					return new Response("Leave", "Tell Natalya that you'll come back for an interview another time...", OFFICE_STABLE_EXIT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_LEAVE"));
						}
					};
					
				}
				if(index==1) {
					return new Response(
							!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaInterviewFailed)
								?"Interview"
								:"Repeat interview",
							!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaInterviewFailed)
								?"Accept Nataly's offer of an interview."
								:"Repeat Natalya's interview.",
							OFFICE_STABLE_INTERVIEW_1) {
						@Override
						public void effects() {
							if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaInterviewFailed)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED));
							}
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED) {
				if(index==1) {
					return new Response("Read contract", "Read the contract which Natalya has prepared for you.", OFFICE_STABLE_CONTRACT_OFFERED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_REPEAT"));
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_3_TRAINING_1) {
				if(!isPlayerBodyCorrect()) {
					return OFFICE_STABLE_CONTRACT_SIGNED.getResponse(responseTab, index);
				} else {
					return OFFICE_STABLE_TRANSFORMED.getResponse(responseTab, index);
				}
				
			} else {
				if(!isPlayerBodyCorrect()) {
					if(!Main.game.getPlayer().isAbleToHaveRaceTransformed()) {
						if(!Main.game.getPlayer().isAbleToSelfTransform()) {
							if(index==1) {
								return new Response("Leave", "As you're unable to be transformed, Natalya has no use for you...", OFFICE_STABLE_EXIT_NO_CONTENT){
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_SELF_TF_BLOCKED"));
									}
								};
							}
							
						} else {
							if(index==1) {
								return new Response("Transform",
										"Use your ability to self-transform to turn yourself into [style.a_shemale] taur.",
										OFFICE_STABLE_ENTRY_TRANSFORMED){
									@Override
									public void effects() {
										String transformationText = applyTransformation(false);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_SELF"));
										Main.game.getTextStartStringBuilder().append(transformationText);
									}
								};
							}
						}
						
					} else {
						if(index==1) {
							return new Response("Centauress",
									"Drink the potion and transform into [style.a_shemale] centauress.",
									OFFICE_STABLE_ENTRY_TRANSFORMED){
								@Override
								public void effects() {
									String transformationText = applyTransformation(true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_CENTAURESS"));
									Main.game.getTextStartStringBuilder().append(transformationText);
								}
							};
							
						} else if(index==2 && Main.game.getPlayer().getRace()!=Race.HORSE_MORPH && Main.game.getPlayer().getLegType().isLegConfigurationAvailable(LegConfiguration.TAUR)) {
							return new Response("Keep race",
									"Drink the potion and transform into [style.a_shemale] [pc.race]"+(Main.game.getPlayer().isTaur()?"":"-taur")+".",
									OFFICE_STABLE_ENTRY_TRANSFORMED){
								@Override
								public void effects() {
									String transformationText = applyTransformation(false);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_KEEP_RACE"));
									Main.game.getTextStartStringBuilder().append(transformationText);
								}
							};
						}
					}
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
						if(index==1) {
							return new Response("Kneel", "Do as Natalya says and step over to kneel down beside her...", OFFICE_STABLE_TRAINING_2);
							
						} else if(index==2) {
							return new Response("Leave", "Make an excuse to leave...", OFFICE_STABLE_EXIT_NO_CONTENT) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_LEAVE"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
								}
							};
						}
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
						if(index==1) {
							return new Response("Kneel", "Kneel before Natalya...", OFFICE_STABLE_TRAINING_3);
							
						} else if(index==2) {
							return new Response("Leave", "Make an excuse to leave...", OFFICE_STABLE_EXIT_NO_CONTENT) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_LEAVE"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
								}
							};
						}
							
					} else { // Qualified filly:
						AbstractClothing collar = getPlayerCollar();
						if(!wearingFillyCollar()) {
							if(index==1) {
								if(!Main.game.getPlayer().isAbleToEquip(generateCollar(), true, Main.game.getNpc(Natalya.class))) {
									return new Response("Thrown out", "Natalya is outraged that you're not only not wearing your filly collar, but that you're unable to equip a new one...", OFFICE_STABLE_EXIT_NO_CONTENT) {
										@Override
										public void effects() {
											Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
											Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_NO_COLLAR_THROWN_OUT"));
										}
									};
									
								} else {
									return new Response("Collared",
											"Natalya is outraged that you're not wearing your filly collar, and makes you equip a new one...",
										(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour())
											?((COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour()))
												?OFFICE_STABLE_COLLAR_UPGRADED
												:OFFICE_STABLE_COLLAR_DOWNGRADED)
											:OFFICE_STABLE_ENTRY_COLLAR_APPLIED) {
										@Override
										public void effects() {
											Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-25));
											UtilText.addSpecialParsingString(getColourFromPoints().getName(), true);
											
											if(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
												if(COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour())) {
													Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_NO_COLLAR_EQUIPPED_UPGRADE"));
												} else {
													Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_NO_COLLAR_EQUIPPED_DOWNGRADE"));
												}
											} else {
												Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_NO_COLLAR_EQUIPPED"));
											}
											Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_POST_NO_COLLAR"));
											
											Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(generateCollar(), true, Main.game.getNpc(Natalya.class)));
										}
									};
								}
							}
							
						} else if(collar.getColour()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
							if(index==1) {
								return new Response("Choker dyed",
										"Natalya is outraged that you're wearing an incorrectly-coloured filly collar, and dyes it to the proper colour...",
										(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour())
											?((COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour()))
												?OFFICE_STABLE_COLLAR_UPGRADED
												:OFFICE_STABLE_COLLAR_DOWNGRADED)
											:OFFICE_STABLE_ENTRY_COLLAR_APPLIED) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-25));
										collar.setColour(getColourFromPoints());
										UtilText.addSpecialParsingString(getColourFromPoints().getName(), true);
										if(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
											if(COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour())) {
												Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_DYED_UPGRADE"));
											} else {
												Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_DYED_DOWNGRADE"));
											}
										} else {
											Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_DYED"));
										}
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_POST_NO_COLLAR"));
									}
								};
							}
							
						} else if(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
							if(index==1) {
								if(COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour())) {
									return new Response("Filly promotion", "Have your filly choker upgraded to "+getColourFromPoints().getName()+".", OFFICE_STABLE_COLLAR_UPGRADED) {
										@Override
										public void effects() {
//											Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(10));
											collar.setColour(getColourFromPoints());
											UtilText.addSpecialParsingString(getColourFromPoints().getName(), true);
											Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_UPGRADE"));
										}
									};
								} else {
									return new Response("Filly demotion", "Have your filly choker downgraded to "+getColourFromPoints().getName()+".", OFFICE_STABLE_COLLAR_DOWNGRADED) {
										@Override
										public void effects() {
//											Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-10));
											collar.setColour(getColourFromPoints());
											UtilText.addSpecialParsingString(getColourFromPoints().getName(), true);
											Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_DOWNGRADE"));
										}
									};
								}
							}
							
						} else {
							if(index==0) {
								return new Response("Leave", "Tell Natalya that you'll get back to your duties and leave...", OFFICE_STABLE_EXIT_NO_CONTENT) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_FILLY_LEAVE"));
									}
								};
								
							} else if(index==1) {
								if(Main.game.getDialogueFlags().getNatalyaWages()==0) {
									return new Response("Payout ("+UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().getNatalyaWages(), "span")+")", "You do not have any wages for Natalya to payout to you!", null);
								}
								return new Response("Payout ("+UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().getNatalyaWages(), "span")+")", "Ask Natalya for your wages.", OFFICE_STABLE_PAYOUT) {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(Main.game.getDialogueFlags().getNatalyaWages()));
										UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().getNatalyaWages()), true);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_PAYOUT"));
										Main.game.getDialogueFlags().setNatalyaWages(0);
									}
								};
								
							} else if(index==2) { // Bronze (oral on Natalya):
								if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySex)) {
									return new Response("Oral", "You've already had sex with Natalya today, and as such she has no more time for you...", null);
									
								} else if(collar.getColour()!=PresetColour.CLOTHING_BRONZE
										&& collar.getColour()!=PresetColour.CLOTHING_SILVER
										&& collar.getColour()!=PresetColour.CLOTHING_GOLD) {
									return new Response("Oral",
											"You do not have a high enough filly ranking to perform oral on Natalya!<br/>[style.italics(Requires a ranking of at least 'bronze', and you are only '"+collar.getColour().getName()+"'...)]",
											null);
									
								} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
									return new Response("Oral", "As you cannot gain access to your mouth, you cannot perform oral on Natalya!", null);
									
								} else {
									return new Response("Oral", "Tell Natalya that you'd like to make use of your filly ranking's benefit and perform oral on her.", OFFICE_STABLE_FILLY_ORAL) {
										@Override
										public boolean isSexHighlight() {
											return true;
										}
										@Override
										public void effects() {
											Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySex, true);
										}
									};
								}
								
							} else if(index==3) { // Silver (fucked):
								if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySex)) {
									return new Response("Mounted", "You've already had sex with Natalya today, and as such she has no more time for you...", null);
									
								} else if(collar.getColour()!=PresetColour.CLOTHING_SILVER
										&& collar.getColour()!=PresetColour.CLOTHING_GOLD) {
									return new Response("Mounted",
											"You do not have a high enough filly ranking to be mounted by Natalya!<br/>[style.italics(Requires a ranking of at least 'silver', and you are only '"+collar.getColour().getName()+"'...)]",
											null);
									
								} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
									return new Response("Mounted", "As you cannot gain access to your asshole, Natalya cannot mount you!", null);
									
								} else {
									return new Response("Mounted", "Tell Natalya that you'd like to make use of your filly ranking's benefit and get mounted by her.", OFFICE_STABLE_FILLY_MOUNTED) {
										@Override
										public boolean isSexHighlight() {
											return true;
										}
										@Override
										public void effects() {
											Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySex, true);
										}
									};
								}
								
							} else if(index==4) { // Gold (fuck Natalya):
								if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySex)) {
									return new Response("Mount her", "You've already had sex with Natalya today, and as such she has no more time for you...", null);
									
								} else if(collar.getColour()!=PresetColour.CLOTHING_GOLD) {
									return new Response("Mount her",
											"You do not have a high enough filly ranking to mount Natalya!<br/>[style.italics(Requires a ranking of at least 'gold', and you are only '"+collar.getColour().getName()+"'...)]",
											null);
									
								} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
									return new Response("Mount her", "As you cannot gain access to your penis, you cannot mount Natalya!", null);
									
								} else {
									return new Response("Mount her", "Tell Natalya that you'd like to make use of your filly ranking's benefit and mount her.", OFFICE_STABLE_FILLY_MOUNT_HER) {
										@Override
										public boolean isSexHighlight() {
											return true;
										}
										@Override
										public void effects() {
											Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySex, true);
										}
									};
								}
							}
						}
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_ENTRY_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-25));
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)
					&& (!wearingFillyCollar() || getPlayerCollar().getColour()!=Main.game.getDialogueFlags().getNatalyaCollarColour())) {
				Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -25));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && !wearingFillyCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_COLLAR_MISSING"));
				
			} else if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && getPlayerCollar().getColour()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_COLLAR_DYED"));
				
			} else if(getColourFromPoints()!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
				if(COLLAR_POINTS_MAP.get(getColourFromPoints())>COLLAR_POINTS_MAP.get(Main.game.getDialogueFlags().getNatalyaCollarColour())) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_COLLAR_UPGRADE"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_COLLAR_DOWNGRADE"));
				}
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_ENTRY_COLLAR_APPLIED = new DialogueNode("", "", true, true) {
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
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kneel", "Tell Natalya that you'll be a good filly and kneel down before her.", OFFICE_STABLE_INTERVIEW_2) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to kneel before Natalya.<br/>[style.italicsMinorBad(This will bring the interview to an end!)]", OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewFailed, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_1"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_2 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lift skirt", "Do as Natalya commands and lift her skirt...", OFFICE_STABLE_INTERVIEW_3) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to lift Natalya's skirt.<br/>[style.italicsMinorBad(This will bring the interview to an end!)]", OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewFailed, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_2"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_3 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_3");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Reluctant kiss",
						"Hesitantly kiss Natalya's asshole and then get started on sucking her cock.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_RELUCTANT_KISS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Eager kiss",
						"Eagerly tongue Natalya's asshole and then get started on sucking her cock.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_EAGER_KISS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new Response("Refuse", "Refuse to kiss Natalya's asshole.<br/>[style.italicsMinorBad(This will bring the interview to an end!)]", OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewFailed, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_3"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -15));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
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
//			if(index==1) {
//				return new Response("Continue", "Continue on your way out of the warehouse...", CORRIDOR);
//			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Clean cock", "Clean Natalya's cock while she prepares your contract.", OFFICE_STABLE_CONTRACT_OFFERED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_AFTER_SEX_COCK_CLEAN"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_OFFERED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED"));
			if(isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_NO_TF"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_TF"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sign", "Do as Natalya says and sign the contract.", OFFICE_STABLE_CONTRACT_SIGNED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_3_TRAINING_1));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to sign the contract.", OFFICE_STABLE_EXIT_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_REFUSED"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -15));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_SIGNED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isPlayerBodyCorrect()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_NO_TF");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isPlayerBodyCorrect()) {
				return OFFICE_STABLE_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(!Main.game.getPlayer().isAbleToHaveRaceTransformed()) {
					if(!Main.game.getPlayer().isAbleToSelfTransform()) {
						if(index==1) {
							return new Response("Leave", "As you're unable to be transformed, Natalya has no use for you...", OFFICE_STABLE_EXIT_NO_CONTENT) {
								@Override
								public void effects() {
									Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_SELF_TF_BLOCKED"));
								}
							};
						}
						
					} else {
						if(index==1) {
							return new Response("Transform", "Use your ability to self-transform to turn yourself into [style.a_shemale] taur.", OFFICE_STABLE_TRANSFORMED){
								@Override
								public void effects() {
									String transformationText = applyTransformation(false);
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_TRANSFORMED_SELF"));
									Main.game.getTextStartStringBuilder().append(transformationText);
								}
							};
						}
					}
					
				} else {
					if(index==1) {
						return new Response("Centauress", "Drink the potion and transform into [style.a_shemale] centauress.", OFFICE_STABLE_TRANSFORMED){
							@Override
							public void effects() {
								String transformationText = applyTransformation(true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_TRANSFORMED_CENTAURESS"));
								Main.game.getTextStartStringBuilder().append(transformationText);
							}
						};
						
					} else if(index==2 && Main.game.getPlayer().getRace()!=Race.HORSE_MORPH && Main.game.getPlayer().getLegType().isLegConfigurationAvailable(LegConfiguration.TAUR)) {
						return new Response("Keep race", "Drink the potion and transform into [style.a_shemale] [pc.race]"+(Main.game.getPlayer().isTaur()?"":"-taur")+".", OFFICE_STABLE_TRANSFORMED){
							@Override
							public void effects() {
								String transformationText = applyTransformation(false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_TRANSFORMED_KEEP_RACE"));
								Main.game.getTextStartStringBuilder().append(transformationText);
							}
						};
					}
				}	
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRANSFORMED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Centauress", "Tell Natalya that you'd prefer to service a centauress.", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
					@Override
					public void effects() {
						spawnSlaves(1, true, PresetColour.CLOTHING_BRONZE);
						GameCharacter slave = getSlaves().get(0);
						slave.addFetish(Fetish.FETISH_SADIST);
						slave.setPlayerKnowsName(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Centaur", "Tell Natalya that you'd prefer to service a centaur.", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
					@Override
					public void effects() {
						spawnSlaves(1, false, PresetColour.CLOTHING_BRONZE);
						GameCharacter slave = getSlaves().get(0);
						slave.addFetish(Fetish.FETISH_SADIST);
						slave.setPlayerKnowsName(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reluctant lick", UtilText.parse(getSlaves(), "Hesitantly lick [npc.namePos] cock."), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_RELUCTANT_LICK", getSlaves()));
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.TWO_NEUTRAL));
						}
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.TWO_NEUTRAL));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Eager lick", UtilText.parse(getSlaves(), "Eagerly lick [npc.namePos] cock."), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_EAGER_LICK", getSlaves()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
						}
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_1_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_SEX", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Suck cock",
						UtilText.parse(getSlaves().get(0), "Suck [npc.namePos] fat, feral horse-cock."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(getSlaves().get(0), new HashMap<>());
								map.get(getSlaves().get(0)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSlaves().get(0)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_1_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_SEX_SUCK_COCK")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), getSlaves().get(0), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_4_TRAINING_2));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Natalya has no more time for you today, so you'll have to return tomorrow to continue your training.", OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX_LEAVE", getSlaves()));
						banishSlaves();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Matte dark blue", "Choose the matte dark blue lipstick.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_DARK_BLUE"));
						Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.MATTE, PresetColour.COVERING_BLUE_DARK, false, PresetColour.COVERING_NONE, false));
						Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				};
				
			} else if(index==2) {
				return new Response("Sparkly gold", "Choose the sparkly gold lipstick.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_SPARKLY_GOLD"));
						Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.SPARKLY, PresetColour.COVERING_GOLD, false, PresetColour.COVERING_NONE, false));
						Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				};
				
			} else if(index==3) {
				return new Response("Glowing pink", "Choose the glowing pink lipstick.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_GLOWING_PINK"));
						Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.GLOSSY, PresetColour.COVERING_PINK, true, PresetColour.COVERING_NONE, false));
						Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reluctant kisses", "Hesitantly start kissing Natalya's asshole.", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getFullDescription(Main.game.getPlayer(), false), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_RELUCTANT_KISSES"));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.TWO_NEUTRAL));
						}
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.TWO_NEUTRAL));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Eager kisses", "Eagerly start kissing Natalya's asshole.", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getFullDescription(Main.game.getPlayer(), false), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_EAGER_KISSES"));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE));
						}
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS = new DialogueNode("", "", true, true) {
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
				return new ResponseSex(
						"Anilingus",
						"Start using your tongue to perform anilingus on Natalya.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_2_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_5_TRAINING_3));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Natalya has no more time for you today, so you'll have to return tomorrow to continue your training.", OFFICE_STABLE_EXIT_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getFullDescription(Main.game.getPlayer(), false), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX_LEAVE"));
						banishSlaves();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Centauress", "Tell Natalya that you'd prefer to perform anilingus on a centauress while she fucks your ass.", OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
					@Override
					public void effects() {
						spawnSlaves(1, true, PresetColour.CLOTHING_BRONZE);
						GameCharacter slave = getSlaves().get(0);
						slave.setPlayerKnowsName(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Centaur", "Tell Natalya that you'd prefer to perform anilingus on a centaur while she fucks your ass.", OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
					@Override
					public void effects() {
						spawnSlaves(1, false, PresetColour.CLOTHING_BRONZE);
						GameCharacter slave = getSlaves().get(0);
						slave.setPlayerKnowsName(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reluctantly obey", "hesitantly present your asshole to Natalya and ask her to mount you.", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getFullDescription(Main.game.getPlayer(), false), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY", getSlaves()));
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.TWO_NEUTRAL));
						}
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.TWO_NEUTRAL));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Eagerly obey", "Present your asshole to Natalya and eagerly beg her to mount you.", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getFullDescription(Main.game.getPlayer(), false), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_EAGERLY_OBEY", getSlaves()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
						}
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE));
						}
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_FUCKED = new DialogueNode("", "", true, true) {
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
				return new ResponseSex(
						"Double-teamed",
						"Natalya fucks your ass while you perform anilingus on her slave...",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND),
										new Value<>(getSlaves().get(0), SexSlotAllFours.IN_FRONT_ANAL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
										new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(getSlaves().get(0), new HashMap<>());
								map.get(getSlaves().get(0)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSlaves().get(0)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_FUCKED_START", getSlaves())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getSlaves().get(0), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Round two",
						"Natalya returns to sit behind her desk while her slave mounts you...",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(getSlaves().get(0), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(getSlaves().get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								
								map.put(getSlaves().get(0), new HashMap<>());
								map.get(getSlaves().get(0)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSlaves().get(0)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA, LubricationType.PRECUM, LubricationType.CUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_ROUND_TWO", getSlaves())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getSlaves().get(0), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND", getSlaves());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(index==1) {
					return new Response("Filly choker", "Let Natalya strap your new choker around your neck...", OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED){
						@Override
						public void effects() {
							banishSlaves();
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(generateCollar(), true, Main.game.getNpc(Natalya.class)));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(10));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(index==1) {
					return new Response("Leave", "Do as Natalya says and exit her office...", OFFICE_STABLE_EXIT_NO_CONTENT){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_LEAVE"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_COLLAR_UPGRADED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getPlayerCollar().setColour(getColourFromPoints());
			Main.game.getDialogueFlags().setNatalyaCollarColour(getColourFromPoints());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(COLLAR_DAILY_WAGE_MAP.get(getColourFromPoints())), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_COLLAR_UPGRADED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_COLLAR_DOWNGRADED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getPlayerCollar().setColour(getColourFromPoints());
			Main.game.getDialogueFlags().setNatalyaCollarColour(getColourFromPoints());
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(COLLAR_DAILY_WAGE_MAP.get(getColourFromPoints())), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_COLLAR_DOWNGRADED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_PAYOUT = new DialogueNode("", "", true, true) {
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
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_ORAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Suck cock",
						"Kiss Natalya's asshole and then get started on sucking her cock.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_ORAL_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Anilingus",
						"Kiss Natalya's asshole and then start using your tongue to perform anilingus on her.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_ORAL_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_MOUNTED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNTED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Mounted",
						"Kiss Natalya's asshole and then get mounted by her.",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNTED_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_MOUNT_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Mount her",
						"Kiss Natalya's asshole and then rear up and mount her.",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_ANILINGUS");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_MOUNTED");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_MOUNT_HER");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
}
