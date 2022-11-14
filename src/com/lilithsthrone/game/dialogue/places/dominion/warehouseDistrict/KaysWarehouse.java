package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FootMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.4
 * @author Innoxia
 */
public class KaysWarehouse {
	
	private static final int PAY_OFF_PRICE = 50_000;
	private static final int PAY_OFF_PRICE_WOLFGANG_SHARE = 30_000;
	private static final int PAY_OFF_PRICE_KARL_SHARE = 20_000;

	/**
	 * Sets quest progress to RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN and appends to the TextEndStringBuilder.
	 * <br/>Moves Wolfgang and Karl to the bounty hunter lodge.
	 */
	public static void applySuppliersBeatenEffects() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN));
		((SupplierLeader)Main.game.getNpc(SupplierLeader.class)).moveToBountyHunterLodge();
		((SupplierPartner)Main.game.getNpc(SupplierPartner.class)).moveToBountyHunterLodge();
	}
	
	/** The amount of flames the dobermanns give to you if you demonically intimidate them. */
	private static final int DEMONIC_PAYOFF = 6_000;
	
	public static boolean isPlayerMouthFree() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	public static boolean isPlayerAssFree() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true);
	}
	
	public static boolean isPlayerPenisFree() {
		return Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
	}
	
	public static boolean isPlayerVaginaFree() {
		return Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}
	
	public static boolean isPlayerNippleFuckFree() {
		return Main.game.getPlayer().hasBreasts() && Main.game.getPlayer().isBreastFuckableNipplePenetration() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true);
	}
	
	public static boolean isSexAvailable() {
		return isPlayerMouthFree() || isPlayerAssFree() || isPlayerVaginaFree();
	}
	
	public static ResponseSex getDobermannsSexResponse(AbstractSexPosition position,
			SexSlot slotWolfgang, SexAreaInterface sexAreaWolfgang,
			SexSlot slotKarl, SexAreaInterface sexAreaKarl,
			SexSlot slotPlayer,
			String title, String description, DialogueNode postSexDialogue, String startingText) {
		
		SexType sexTypeWolfgang = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, sexAreaWolfgang);
		SexType sexTypeKarl = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, sexAreaKarl);
		
		String fuckingText = "Wolfgang fucking your " +sexTypeWolfgang.getTargetedSexArea().getName(Main.game.getPlayer())+" while Karl fucks your " +sexTypeKarl.getTargetedSexArea().getName(Main.game.getPlayer());
		if(sexTypeWolfgang.getTargetedSexArea()==sexTypeKarl.getTargetedSexArea()) {
			fuckingText = "both Wolfgang and Karl fucking your " + sexTypeWolfgang.getTargetedSexArea().getName(Main.game.getPlayer());
		}
		if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
			fuckingText = "Wolfgang fucking your face while Karl makes you give him a handjob";
		}
		
		return new ResponseSex(title,
				description
					+"<br/>[style.italicsSex(This will result in "+fuckingText+"!)]",
				true, false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(SupplierLeader.class), slotWolfgang),
								new Value<>(Main.game.getNpc(SupplierPartner.class), slotKarl)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), slotPlayer))) {
						@Override
						public boolean isPositionChangingAllowed(GameCharacter character) {
							return false;
						}
						@Override
						public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
							return new ArrayList<>();
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(
									new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
									new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
									new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.NIPPLE
												?CoverableArea.NIPPLES
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.MOUTH
												?CoverableArea.MOUTH
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.ANUS
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.ANUS
												?CoverableArea.ANUS
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.VAGINA
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.VAGINA
												?CoverableArea.VAGINA
												:null)));
						}
						@Override
						public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(!character.isPlayer()) {
								return character.getMainSexPreference(targetedCharacter);
							}
							return super.getForeplayPreference(character, targetedCharacter);
						}
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.equals(Main.game.getNpc(SupplierLeader.class))) {
								return sexTypeWolfgang;
							}
							if(character.equals(Main.game.getNpc(SupplierPartner.class))) {
								return sexTypeKarl;
							}
							return super.getMainSexPreference(character, targetedCharacter);
						}
					},
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				startingText) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				List<InitialSexActionInformation> initialActions = new ArrayList<>();
				
				boolean sameTarget = sexTypeWolfgang.getTargetedSexArea()==sexTypeKarl.getTargetedSexArea();
				
				if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisNipple.PENIS_FUCKING_START, false, true));
				}
				
				if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), sameTarget?PenisVagina.PENIS_FUCKING_START_ADDITIONAL:PenisVagina.PENIS_FUCKING_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), sameTarget?PenisAnus.PENIS_FUCKING_START_ADDITIONAL:PenisAnus.PENIS_FUCKING_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaPenetration.FINGER) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), PenisNipple.PENIS_FUCKING_START, false, true));
				}
				
				return initialActions;
			}
		};
	}
	
	private static Response getDobermannsRewardSexResponse(int startIndex, int index, String title) {
		if(index == startIndex) {
			title = title.replaceAll("sex_type_replacement", "oral");
			
			if(!isPlayerMouthFree()) {
				return new Response(title,
						"As you cannot gain access to your mouth, you cannot offer to perform oral on the dobermanns...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.SITTING,
						SexSlotSitting.SITTING, SexAreaOrifice.MOUTH,
						SexSlotSitting.SITTING, SexAreaPenetration.FINGER,
						SexSlotSitting.PERFORMING_ORAL,
						title,
						"Offer to suck the dobermanns' cocks...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_ORAL"));
			}
			
		} else if(index == startIndex+1) {
			title = title.replaceAll("sex_type_replacement", "spit-roast");
			
			if(!isPlayerMouthFree()) {
				return new Response(title,
						"As you cannot gain access to your mouth, you cannot offer to be spit-roasted by the dobermanns...",
						null);
				
			} else if(!isPlayerAssFree() && !isPlayerVaginaFree()) {
				return new Response(title,
						"As you cannot gain access to your ass"+(Main.game.getPlayer().hasVagina()?" or pussy":"")+", you cannot offer to be spit-roasted by the dobermanns...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.ALL_FOURS,
						SexSlotAllFours.BEHIND, isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotAllFours.IN_FRONT, SexAreaOrifice.MOUTH,
						SexSlotAllFours.ALL_FOURS,
						title,
						"Offer to let the two dobermanns spit-roast you...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_SPITROAST"));
			}
			
		} else if(index == startIndex+2) {
			title = title.replaceAll("sex_type_replacement", "ride");
			
			if(!isPlayerAssFree() && !isPlayerVaginaFree()) {
				return new Response(title,
						"As you cannot gain access to your ass"+(Main.game.getPlayer().hasVagina()?" or pussy":"")+", you cannot offer to ride the dobermanns...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.LYING_DOWN,
						SexSlotLyingDown.LYING_DOWN, isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotLyingDown.MISSIONARY, isPlayerAssFree()?SexAreaOrifice.ANUS:SexAreaOrifice.VAGINA,
						SexSlotLyingDown.COWGIRL,
						title,
						"Offer to give the two dobermanns a ride...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_RIDE"));
			}
			
		} else if(index == startIndex+3 && Main.game.isNipplePenEnabled()) {
			title = title.replaceAll("sex_type_replacement", "nipple-fuck");
			
			if(!isPlayerNippleFuckFree()) {
				return new Response(title,
						Main.game.getPlayer().isBreastFuckableNipplePenetration()
							?"As you cannot gain access to your fuckable nipples, you cannot offer them to the dobermanns..."
							:"As you do not have fuckable nipples, you cannot offer them to the dobermanns...",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT, SexAreaOrifice.NIPPLE,
						SexSlotStanding.STANDING_DOMINANT_TWO, SexAreaOrifice.NIPPLE,
						SexSlotStanding.PERFORMING_ORAL,
						title,
						"Offer to let the two dobermanns fuck your nipples...",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_NIPPLES"));
			}
		}
		return null;
	}
	
	private static ResponseSex getKaySexResponse(String title,
			String description,
			DialogueNode postSexDialogue,
			String startingText,
			AbstractSexPosition position,
			SexSlot slotKay, SexType sexTypeKay,
			SexSlot slotPlayer,
			List<CoverableArea> kayExposedParts,
			List<CoverableArea> playerExposedParts,
			List<InitialSexActionInformation> initialActions) {
		return getKaySexResponse(title,
				description,
				postSexDialogue,
				startingText,
				position,
				slotKay,
				sexTypeKay,
				slotPlayer,
				kayExposedParts,
				playerExposedParts,
				initialActions,
				null);
	}

	/** @param startingWetAreas Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	private static ResponseSex getKaySexResponse(String title,
			String description,
			DialogueNode postSexDialogue,
			String startingText,
			AbstractSexPosition position,
			SexSlot slotKay, SexType sexTypeKay,
			SexSlot slotPlayer,
			List<CoverableArea> kayExposedParts,
			List<CoverableArea> playerExposedParts,
			List<InitialSexActionInformation> initialActions,
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> startingWetAreas) {
		return new ResponseSex(title,
				description,
				true, false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), slotPlayer)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Kay.class), slotKay))) {
						@Override
						public SexPace getForcedSexPace(GameCharacter character) {
							if(character==Main.game.getNpc(Kay.class)) {
								return SexPace.SUB_EAGER; // Lock Kay into being eager, as if they were to fall into resisting, the scenes don't account for that
							}
							return null;
						}
						@Override
						public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
							return clothingToEquip.isCondom(); // Do not let sex clothing be equipped onto Kay, as they have special actions for this and it would otherwise break the flow of their scenes
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(
									new Value<>(Main.game.getNpc(Kay.class), kayExposedParts),
									new Value<>(Main.game.getPlayer(), playerExposedParts));
						}
						@Override
						public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(sexTypeKay!=null) {
								return character.getMainSexPreference(targetedCharacter);
							}
							return super.getForeplayPreference(character, targetedCharacter);
						}
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(sexTypeKay==null) {
								return super.getMainSexPreference(character, targetedCharacter);
							}
							if(character.equals(Main.game.getNpc(Kay.class))) {
								return sexTypeKay;
							} else {
								return sexTypeKay.getReversedSexType();
							}
						}
						@Override
						public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
							if(startingWetAreas==null) {
								return super.getStartingWetAreas();
							}
							return startingWetAreas;
						}
					},
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				startingText) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				return initialActions;
			}
		};
	}
	
	
	public static final DialogueNode INITIAL_ENTRY = new DialogueNode("Reception Area", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "INITIAL_ENTRY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
					return new Response("Exit", "Decide to leave the warehouse for now.", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
						}
					};
					
				} else {
					return new Response("Receptionist", "Approach the receptionist and tell her that you're here to see Kay.", RECEPTIONIST_UNLOCKING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode RECEPTIONIST_UNLOCKING = new DialogueNode("Reception Area", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "RECEPTIONIST_UNLOCKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return INITIAL_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode RECEPTION = new DialogueNode("Reception Area", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
				return INITIAL_ENTRY.getContent();
			}
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "RECEPTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return INITIAL_ENTRY.getResponse(responseTab, index);}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("Corridor", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STORAGE_AREA = new DialogueNode("Storage Area", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
					return new Response("Surplus", "You don't think it's a good idea to help yourself to items from the 'Surplus Disposal' crate without first receiving permission to do so...", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)) {
					return new Response("Surplus", "You've already taken today's surplus stock!", null);
					
				} else {
					return new Response("Surplus", "Help yourself to today's surplus stock.", STORAGE_AREA_SEARCHING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayCratesSearched, true);
							
							List<AbstractClothingType> clothingToGenerate = new ArrayList<>(ClothingType.getAllClothing());
							clothingToGenerate.removeIf((clothing) -> clothing.getRarity()!=Rarity.COMMON || !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN) || clothing.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN));
							
							Main.game.getTextEndStringBuilder().append(
									Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), false), false)
									+ Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false)
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false):"")
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false):"")
							        + UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA_SEARCHED"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STORAGE_AREA_SEARCHING = new DialogueNode("Storage Room", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA_SEARCHING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STORAGE_AREA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WEAVING_MACHINES = new DialogueNode("Weaving Machines", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "WEAVING_MACHINES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode OVERSEER_STATION = new DialogueNode("Overseer's Station", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OVERSEER_STATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
					return new Response("[kay.NamePos] office", "Head up the stairs, through the overseer's station, and pay [kay.name] a visit.", OFFICE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OFFICE, false);
							Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else {
					return new Response("Upstairs", "Head up the stairs and enter the overseer's station.", DOBERMANNS) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP)==Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS));
							}
						}
					};
				}
				
			} else if(index==2) {
				return new Response("Turn around", "[pc.Step] away from the staircase and head back down the corridor.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS = new DialogueNode("Overseer's Station", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(SupplierLeader.class).setPlayerKnowsName(true);
			Main.game.getNpc(SupplierPartner.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Leave", "Now that the dobermanns have mistaken you for an Enforcer, it would be best to resolve this conflict before attempting to leave...", null);
				}
				if(Main.game.getPlayer().getRace()==Race.DEMON) {
					return new Response("Leave", "Now that the dobermanns have seen you in your demonic form, it would be best to resolve this conflict before attempting to leave...", null);
				}
				return new Response("Leave", "[pc.Step] back from the dobermanns and head back out the way you came.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_LEAVE"));
					}
				};
				
			} else if(index == 1) {
				if(Main.game.getPlayer().getMoney()<PAY_OFF_PRICE) {
					return new Response("Pay off ("+UtilText.formatAsMoneyUncoloured(PAY_OFF_PRICE, "span")+")",
							"You don't have enough flames to offer to pay off the dobermanns' contract...",
							null);
					
				} else {
					return new Response("Pay off ("+UtilText.formatAsMoney(PAY_OFF_PRICE, "span")+")",
							"Offer to pay off the remainder of the dobermanns' contract, which, along with the pointed reminder that what they're doing is illegal, should be enough to make them leave.",
							DOBERMANNS_PAID_OFF) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatPaid, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-PAY_OFF_PRICE));
							Main.game.getNpc(SupplierLeader.class).incrementMoney(PAY_OFF_PRICE_WOLFGANG_SHARE);
							Main.game.getNpc(SupplierPartner.class).incrementMoney(PAY_OFF_PRICE_KARL_SHARE);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Enforcer bluff", "Use the fact that the dobermanns have mistaken you for an Enforcer to convince them to leave.", DOBERMANNS_ENFORCER_BLUFF) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatEnforcer, true);
						}
					};
					
				} else {
					return new Response("Enforcer Bluff", "You'd need to be wearing an Enforcer's uniform in order to attempt this!", null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().getRace()!=Race.DEMON
						&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
					if(!Main.game.getPlayer().isAbleToSelfTransform()) {
						return new Response("Demonic reveal", "As your self-transformative powers are current inhibited, you're not able to turn into a demon in front of the dobermanns in order to intimidate them into leaving!", null);
					} else {
						return new Response("Demonic reveal",
								"Transform into a demon in front of the dobermanns and intimidate them into leaving.",
								DOBERMANNS_DEMON_REVEAL) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatDemon, true);
							}
						};
					}
					
				} else {
					return new Response("Demonic intimidation",
							"Use the fact that you're a demon to intimidate the dobermanns into leaving.",
							DOBERMANNS_DEMONIC_INTIMIDATION,
							null,
							null,
							null,
							null,
							Util.newArrayListOfValues(Subspecies.DEMON)) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatDemon, true);
						}
					};
				}
				
			} else if(index==4) {
				if(!isSexAvailable()) {
					return new Response("Seduce", "As you're unable to access any of your orifices, you're unable to seduce the dobermanns...", null);
				}
				return new Response("Seduce",
						"Seduce the dobermanns and offer them your body in exchange for leaving Kay alone.",
						DOBERMANNS_SEDUCE,
						null,
						null,
						Util.newArrayListOfValues(Perk.MALE_ATTRACTION, Perk.OBJECT_OF_DESIRE),
						null,
						null) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatSeduced, true);
					}
				};
				
			} else if(index==5) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("Fight", "It'd be better to try and play on the fact that the dobermanns have mistaken you for an Enforcer...", null);
					
				} else {
					return new ResponseCombat("Fight", "Immediately launch into combat!",
							Main.game.getNpc(SupplierLeader.class),
							Util.newArrayListOfValues(
									Main.game.getNpc(SupplierLeader.class),
									Main.game.getNpc(SupplierPartner.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(I'll soon have the two of you cleared out from here,)]"
											+ " you declare, readying yourself for a fight,"
											+ " [pc.speech(but I know that people like you only respect force, so I'm left with no choice but to do this!)]"),
									new Value<>(Main.game.getNpc(SupplierLeader.class), "[wolfgang.speech(Hah!)] Wolfgang shouts, [wolfgang.speech(If it's a fight you want, we'll give you one!)]"),
									new Value<>(Main.game.getNpc(SupplierPartner.class), "[karl.speech(You're gonna pay for this, bitch!)] Karl snarls."))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS_PAID_OFF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(PAY_OFF_PRICE), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_PAID_OFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Let them go", "Stand aside and let the two dobermanns leave.", DOBERMANNS_BANISHED);
				
			} else {
				return getDobermannsRewardSexResponse(2, index, "\"Thank\" them (sex_type_replacement)");
			}
		}
	};
	
	public static final DialogueNode DOBERMANNS_ENFORCER_BLUFF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_ENFORCER_BLUFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DOBERMANNS_DEMON_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(DEMONIC_PAYOFF));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(DEMONIC_PAYOFF), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_DEMON_REVEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOBERMANNS_DEMONIC_INTIMIDATION = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(DEMONIC_PAYOFF));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(DEMONIC_PAYOFF), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_DEMONIC_INTIMIDATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOBERMANNS_SEDUCE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEDUCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDobermannsRewardSexResponse(1, index, "Offer sex_type_replacement");
		}
	};
	
	public static final DialogueNode DOBERMANNS_COMBAT_PLAYER_VICTORY = new DialogueNode("Victory", "The two dobermanns have been defeated!", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatCombat, true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Let them go", "Stand aside and let the two dobermanns leave.", DOBERMANNS_BANISHED);
				
			} else if (index == 2) {
				return new ResponseSex("Fuck Them",
						"Push Wolfgang and Karl down side-by-side in the doggy-style position, ready to have some fun with them...",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_VICTORY_FUCK_THEM"));
				
			} else {
				return getDobermannsRewardSexResponse(3, index, "Submit (sex_type_replacement)");
			}
		}
	};
	
	public static final DialogueNode DOBERMANNS_COMBAT_PLAYER_LOSS = new DialogueNode("Defeated", "The dobermanns have proven to be too much for you to handle...", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Fucked",
						"Completely exhausted and utterly defeated, you're not able to offer any resistance as the two dobermanns prepare to fuck you...",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
						},
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED"));
				
			} else if(index == 2) {
				return new ResponseSex("Eagerly fucked",
						"Turned on from being so dominantly put in your place, you can't help but eagerly beg for the two dobermanns to fuck you...",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY, ResponseTag.START_PACE_PLAYER_SUB_EAGER) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
						},
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED_EAGER"));
				
			} else if(index==3) {
				if(!Main.game.isNonConEnabled()) {
					return new Response("Refuse", "You might be defeated, but there's no way you're going to let these two brutes have their way with you!", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_THROWN_OUT"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
						}
					};
					
				} else {
					return new ResponseSex("Resist",
							"Despite being completely exhausted and utterly defeated, you do your best to resist as the two dobermanns prepare to rape you...",
							null, null, null, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.PREFER_DOGGY, ResponseTag.START_PACE_PLAYER_SUB_RESISTING) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
							},
							AFTER_SEX_FUCKED,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED_RESIST"));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_WILLING = new DialogueNode("Finished", "The two dobermanns have had enough...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_WILLING_DOMMED_THEM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_WILLING");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Let them go", "Stand aside and let the two dobermanns leave.", DOBERMANNS_BANISHED);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_FUCKED = new DialogueNode("Finished", "The two dobermanns have had their fun with you...", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_FUCKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Thrown out", "Karl unceremoniously throws you out of the warehouse.", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_THROWN_OUT"));
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS_BANISHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applySuppliersBeatenEffects();
			Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).setAffection(Main.game.getPlayer(), 25));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_BANISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Follow", "Follow the excitable Kay into his office.", KAY_SAVED_OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OFFICE, false);
						Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_SAVED_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_SAVED_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE"));
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Kay.class), true);
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
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "Exit [kay.namePos] office and head back down to the warehouse corridor.", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_LEAVE"));
					}
				};
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayTalkedTo)) {
					return new Response("Small talk", "You've already spent time talking to [kay.name] today, and as such, you don't have much more to say until tomorrow.", null);
					
				} else {
					return new Response("Small talk", "Spend some time talking to [kay.name].", KAY_OFFICE_UTIL_EMPTY) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK"));
							
							List<String> topics = Util.newArrayListOfValues(
									"KAY_MACHINES",
									"KAY_INTERCOM",
									"KAY_BUSINESS",
									"KAY_BOUNTY_HUNTERS");
							long lowestValue = 1_000_000;
							for(String topic : topics) {
								if(Main.game.getDialogueFlags().getSavedLong(topic)<lowestValue) {
									lowestValue = Main.game.getDialogueFlags().getSavedLong(topic);
								}
							}
							long thanksJava = lowestValue;
							topics.removeIf(s -> Main.game.getDialogueFlags().getSavedLong(s)>thanksJava);
							String topicSelected = Util.randomItemFrom(topics);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK_"+topicSelected));
							Main.game.getDialogueFlags().incrementSavedLong(topicSelected, 1);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK_END"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayTalkedTo, true);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFlirtedWith)) {
					return new Response("Flirt", "You've already spent time flirting with [kay.name] today. You should return tomorrow if you wanted to flirt with him some more.", null);
					
				} else {
					return new Response("Flirt", "Spend some time flirting with [kay.name].", KAY_OFFICE_UTIL_EMPTY) {
						@Override
						public int getSecondsPassed() {
							return 20*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_FLIRT"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFlirtedWith, true);
						}
					};
				}
				
			} else if(index==3) {
				if(Main.game.getNpc(Kay.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_FOUR_LOVE)) {
					return new Response("Dominate",
							"Although he clearly likes you, you can tell that [kay.name] wouldn't be comfortable with letting you sexually dominate him."
							+ "<br/>[style.italicsMinorBad(Requires Kay to have an affection level of at least '"+AffectionLevel.POSITIVE_FOUR_LOVE.getName()+"' towards you.)]",
							null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayDommed)) {
					return new Response("Dominate", "You've already dominated [kay.name] today, and as such he'll be far too exhausted to be of much fun until tomorrow.", null);
					
				} else {
					return new Response("Dominate",
							"Dominate [kay.name] and make [kay.herHim] submit to you.",
							KAY_OFFICE_DOMINATE,
							Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
							Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(Main.game.getNpc(Kay.class).getAffection(Main.game.getPlayer())<100) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).setAffection(Main.game.getPlayer(), 100));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayDommed, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_UTIL_EMPTY = new DialogueNode("", "", true, true) {
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
			return OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kaySubmitted)) {
				if(index==1) {
					return new Response("[pc.Name]", "Don't give [kay.name] any special name to call you by.<br/>[style.colourMinorGood(This can be changed at any time after this scene.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NO_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==2) {
					return new Response("[pc.Mistress]", "Get [kay.name] to call you '[pc.Mistress]'.<br/>[style.colourMinorGood(This can be changed at any time after this scene.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Mistress]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==3) {
					return new Response("[pc.Maam]", "Get [kay.name] to call you '[pc.Maam]'.<br/>[style.colourMinorGood(This can be changed at any time after this scene.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Maam]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==4) {
					String surname = (Main.game.getPlayer().getSurname()!=null && !Main.game.getPlayer().getSurname().isEmpty()?"[pc.Surname]":"[pc.Name]");
					return new Response("[pc.Miss] "+surname,"Get [kay.name] to call you '[pc.Miss] "+surname+"'.<br/>[style.colourMinorGood(This can be changed at any time after this scene.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Miss] "+surname);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==5) {
					return new Response("[pc.Mommy]", "Get [kay.name] to call you '[pc.Mommy]'.<br/>[style.colourMinorGood(This can be changed at any time after this scene.)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Mommy]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
				}
				
			} else {
				boolean buttplugActionAvailable = Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)!=null || Main.game.isAnalContentEnabled();
				
				if(index==0) {
					return new Response("Leave",
							"Having had your fun with [kay.name], it's time to leave his office.<br/>[style.italicsMinorBad(If you leave, you'll have to wait until tomorrow before being able to dominate him again!)]",
							CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_LEAVE"));
						}
					};
				}
				// Standard actions:
				if(index==1) {
					return new Response("Pet [kay.herHim]", "Have [kay.name] kneel down beside you so that you can pet [kay.her] soft ears and hair for a while.", KAY_OFFICE_DOMINATE_PETTING);
					
				} else if(index==2) {
					return new Response("Get massage", "Get [kay.name] to massage your shoulders and back.", KAY_OFFICE_DOMINATE_MASSAGE);
					
				} else if(index==3 && Main.game.isFootContentEnabled()) {
					if(!Main.game.getPlayer().hasLegs()) {
						return new Response("Get foot massage", "You do not have any feet for [kay.name] to massage...", null);
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.FEET, true)) {
						return new Response("Get foot massage", "As you are unable to gain access to your [pc.feet], [kay.name] cannot give you a foot massage!", null);
					} else {
						return new Response("Get foot massage", "Get [kay.name] to give you a foot massage.", KAY_OFFICE_DOMINATE_MASSAGE_FEET);
					}
					
				} else if(Main.game.isFootContentEnabled()?index==4:index==3) {
					return getKaySexResponse("Fuck [kay.herHim]",
							"Pull [kay.name] into your embrace and tell [kay.herHim] that you're going to fuck [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_FUCK"),
							SexPosition.STANDING,
							SexSlotStanding.STANDING_SUBMISSIVE,
							null,
							SexSlotStanding.STANDING_DOMINANT,
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues());
				}
				
				// Toys and behaviour changes:
				if(index==6) {
					if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) {
						return new Response("Cage: [style.colourMinorBad(Unequipped)]",
								"[kay.Name] is currently not wearing [kay.her] chastity cage. You could change that, if you wanted to..."
										+ "<br/>[style.italicsMinorGood(You can freely equip and remove [kay.namePos] cage at any time.)]",
								KAY_OFFICE_DOMINATE_CAGE);
					} else {
						return new Response("Cage: [style.colourMinorGood(Equipped)]",
								"[kay.Name] is currently wearing [kay.her] chastity cage. You could take it off of [kay.herHim], if you wanted to..."
									+ "<br/>[style.italicsMinorGood(You can freely equip and remove [kay.namePos] cage at any time.)]",
								KAY_OFFICE_DOMINATE_CAGE);
					}
					
				} else if(index==7 && buttplugActionAvailable) {
					if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
						return new Response("Buttplug: [style.colourMinorBad(Removed)]", "[kay.Name] is currently not wearing [kay.her] buttplug. You could change that, if you wanted to...", KAY_OFFICE_DOMINATE_BUTTPLUG);
					} else {
						return new Response("Buttplug: [style.colourMinorGood(Inserted)]", "[kay.Name] is currently wearing [kay.her] buttplug. You could remove it, if you wanted to...", KAY_OFFICE_DOMINATE_BUTTPLUG);
					}
					
				} else if(buttplugActionAvailable?index==8:index==7) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
						return new Response("Clothing: [style.colourFeminine(Feminine)]",
								"Tell [kay.name] to switch back to wearing [kay.her] old masculine suit."
										+ "<br/>[style.italicsMinorBad([kay.Name] will revert to having [kay.her] old hairstyle and will wipe off [kay.her] makeup as part of this.)]",
								KAY_OFFICE_DOMINATE_CLOTHING);
					} else {
						return new Response("Clothing: [style.colourMasculine(Masculine)]",
								"Tell [kay.name] that [kay.she] should find a feminine suit to wear.",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayPreviouslyFeminised)
									?KAY_OFFICE_DOMINATE_CLOTHING
									:KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME);
					}
					
				} else if(buttplugActionAvailable?index==9:index==8) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
						return new Response("Makeover", "[kay.Name] won't feel comfortable with you giving [kay.herHim] a makeover unless you've already got [kay.herHim] to wear feminine clothing!", null);
						
					} else if(!Main.game.getPlayer().hasItemType(ItemType.MAKEUP_SET)) {
						return new Response("Makeover", "You require "+ItemType.MAKEUP_SET.getDeterminer()+" "+ItemType.MAKEUP_SET.getName(false)+" in order to do this!", null);
						
					} else {
						return new Response("Makeover", "Get [kay.name] to sit in [kay.her] chair while you change [kay.her] hairstyle and apply makeup to [kay.herHim].", KAY_OFFICE_DOMINATE_MAKEUP);
					}
					
				} else if(index==10) {
					return new Response("Naming", "Choose what name [kay.name] calls you by.", KAY_OFFICE_DOMINATE_NAMING);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_UTIL_EMPTY = new DialogueNode("", "", true, true) {
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
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_POST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_PETTING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();

			responses.add(new Response("Stop petting", "Having had enough of petting [kay.name], you sit back in [kay.her] chair and wonder what to do next.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_END"));
				}
			});
			
			if(isPlayerPenisFree()) {
				responses.add(getKaySexResponse("Receive blowjob",
						Main.game.getPlayer().isTaur()
							?"Stand up and get [kay.name] to kneel beneath your lower [pc.legRace]'s body so that [kay.she] can suck your cock."
							:"Pull [kay.namePos] head into your lap and get [kay.herHim] to suck your cock.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_BLOWJOB"),
						Main.game.getPlayer().isTaur()
							?SexPosition.STANDING
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.PERFORMING_ORAL
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.STANDING_DOMINANT
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisMouth.BLOWJOB_START, false, true))));
				
			} else {
				responses.add(new Response("Receive blowjob", "You require a penis and access to it in able to receive a blowjob from [kay.name].", null));
			}
			
			if(isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("Receive cunnilingus",
						Main.game.getPlayer().isTaur()
							?"Stand up and get [kay.name] to kneel behind your lower [pc.legRace]'s body so that [kay.she] can eat you out."
							:"Pull [kay.namePos] head into your lap and get [kay.herHim] to eat you out.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_CUNNILINGUS"),
						Main.game.getPlayer().isTaur()
							?SexPosition.STANDING
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.PERFORMING_ORAL_BEHIND
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.STANDING_DOMINANT
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true))));
				
				if(Main.game.getPlayer().isClitorisPseudoPenis()) {
					responses.add(getKaySexResponse("Clit sucked",
							Main.game.getPlayer().isTaur()
								?"Stand up and get [kay.name] to kneel behind your lower [pc.legRace]'s body so that [kay.she] can suck your [pc.clitSize] clit."
								:"Pull [kay.namePos] head into your lap and get [kay.herHim] to suck your [pc.clitSize] clit.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_CLIT_SUCK"),
							Main.game.getPlayer().isTaur()
								?SexPosition.STANDING
								:SexPosition.SITTING,
							Main.game.getPlayer().isTaur()
								?SexSlotStanding.PERFORMING_ORAL
								:SexSlotSitting.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT),
							Main.game.getPlayer().isTaur()
								?SexSlotStanding.STANDING_DOMINANT
								:SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(CoverableArea.VAGINA),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), ClitMouth.CLIT_ORAL_START, false, true))));
				} else {
					responses.add(new Response("Clit sucked", "You require a clit of at least size '"+ClitorisSize.getMinimumClitorisSizeForPseudoPenis()+"' before you are able to get it sucked by [kay.name].", null));
				}
				
			} else {
				responses.add(new Response("Receive cunnilingus", "You require a pussy and access to it before you are able to receive cunnilingus from [kay.name].", null));
				responses.add(new Response("Clit sucked", "You require a pussy, a clit of at least size '"+ClitorisSize.getMinimumClitorisSizeForPseudoPenis()+"', and access to it before you are able to get it sucked by [kay.name].", null));
			}
			
			for(int i=0;i<responses.size();i++) {
				if(i==index-1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+15)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();

			responses.add(new Response("Stop massage", "Tell [kay.name] that [kay.sheHas] done an excellent job, but that you've had enough of [kay.her] massaging for now.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_END"));
				}
			});
			
			if(isPlayerPenisFree() || isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("Groin 'massage'",
						"Tell [kay.name] to move [kay.her] attention down to your groin and "
						+(isPlayerPenisFree() && isPlayerVaginaFree()
							?"finger you while also giving you a handjob."
							:(isPlayerPenisFree()
								?"give you a handjob."
								:"finger you.")),
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_GROIN"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.BEHIND
							:SexSlotDesk.BETWEEN_LEGS,
						isPlayerPenisFree()
							?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)
							:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(),
						Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA),
						Util.newArrayListOfValues(
								(isPlayerPenisFree()
									?new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true)
									:null),
								(isPlayerVaginaFree()
									?new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true)
									:null))));
				
			} else {
				responses.add(new Response("Groin 'massage'", "You require a penis or a vagina, as well as access to them, before you're able to receive a groin 'massage' from [kay.name].", null));
			}

			if(isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("Cunnilingus",
						"Get [kay.name] to drop down behind you and eat you out.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_CUNNILINGUS"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.BEHIND_ORAL
							:SexSlotDesk.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true))));
				
			} else {
				responses.add(new Response("Cunnilingus", "You require a vagina, as well as access to it, before you're able to receive cunnilingus from [kay.name].", null));
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(isPlayerAssFree()) {
					responses.add(getKaySexResponse("Anilingus",
							"Get [kay.name] to drop down behind you and perform anilingus.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_ANILINGUS"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.OVER_DESK,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.BEHIND_ORAL
								:SexSlotDesk.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotDesk.OVER_DESK_ON_FRONT,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true))));
					
				} else {
					responses.add(new Response("Anilingus", "You require access to your anus in order to receive cunnilingus from [kay.name].", null));
				}
			}
			
			if(!isPlayerVaginaFree()) {
				responses.add(new Response("Fucked", "You require a vagina, as well as access to it, in order to get fucked by [kay.name].", null));
				
			} else if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				responses.add(new Response("Fucked", "As [kay.namePos] cock is locked away in [kay.her] chastity cage, [kay.she] is unable to fuck you!", null));
				
			} else {
				responses.add(getKaySexResponse("Fucked",
						"Get [kay.name] to move up behind you and fuck you.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_VAGINAL"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.HUMPING
							:SexSlotDesk.HUMPING,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)),
						Util.newHashMapOfValues(
								new Value<>(
										Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(
													SexAreaOrifice.VAGINA,
													Util.newHashMapOfValues(
														new Value<>(
															Main.game.getNpc(Kay.class),
															Util.newHashSetOfValues(LubricationType.PRECUM)))))))));
				
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!isPlayerAssFree()) {
					responses.add(new Response("Fucked (anal)", "You require access to your anus in order to receive anal from [kay.name].", null));
					
				} else if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					responses.add(new Response("Fucked (anal)", "As [kay.namePos] cock is locked away in [kay.her] chastity cage, [kay.she] is unable to fuck your ass!", null));
					
				} else {
					responses.add(getKaySexResponse("Fucked (anal)",
							"Get [kay.name] to move up behind you and fuck your ass.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_ANAL"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.OVER_DESK,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.HUMPING
								:SexSlotDesk.HUMPING,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotDesk.OVER_DESK_ON_FRONT,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)),
							Util.newHashMapOfValues(
									new Value<>(
											Main.game.getPlayer(),
											Util.newHashMapOfValues(
													new Value<>(
														SexAreaOrifice.ANUS,
														Util.newHashMapOfValues(
															new Value<>(
																Main.game.getNpc(Kay.class),
																Util.newHashSetOfValues(LubricationType.PRECUM)))))))));
					
				}
			}
			
			for(int i=0;i<responses.size();i++) {
				if(i==index-1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_MASSAGE_FEET = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+15)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Stop massage", "Tell [kay.name] that [kay.sheHas] done an excellent job, but that you've had enough of [kay.her] massaging for now.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_END"));
					}
				};
				
			} else if(index==2) {
				return getKaySexResponse("[pc.Foot(true)] worship",
						"Get [kay.name] to orally worship your [pc.feet+(true)].",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_FOOT_WORSHIP"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.USING_FEET
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.FEET),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), FootMouth.FOOT_ORAL_RECEIVING_START, false, true)));
				
			} else if(index==3) {
				if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("[pc.Footjob]", "As [kay.namePos] cock is locked away in [kay.her] chastity cage, you are unable to give [kay.herHim] a [pc.footjob]!", null);
					
				} else {
					return getKaySexResponse("[pc.Footjob]",
							"Get [kay.name] to present you with [kay.her] cock so that you can reward [kay.herHim] with a [pc.footjob].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_FOOTJOB"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.SITTING,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.USING_FEET
								:SexSlotSitting.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisFeet.FOOT_JOB_DOUBLE_GIVING_START, false, true)));
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).equipClothingFromNowhere(
								Main.game.getItemGen().generateClothing("innoxia_bdsm_chastity_cage", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLUE_LIGHT, PresetColour.CLOTHING_STEEL, false), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_EQUIP"));
				
			} else {
				Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS).setSealed(false);
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).unequipClothingIntoVoid(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_UNEQUIP"));
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
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) { // Taken cage off:
				List<Response> responses = new ArrayList<>();

				responses.add(new Response("Sit back", "Sit back and let [kay.name] pull [kay.her] trousers back up.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_SIT_BACK"));
					}
				});
				
				if(!Main.game.getPlayer().isTaur()) {
					responses.add(getKaySexResponse("Pull into lap",
							"Pull [kay.name] into your lap and tell [kay.herHim] that you're going to fuck [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_LAP"),
							SexPosition.SITTING,
							SexSlotSitting.SITTING_IN_LAP,
							null,
							SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				} else {
					responses.add(new Response("Pull into lap", "As you have the lower body of [pc.a_legRace], you cannot pull [kay.name] into your lap!", null));
				}

				if(Main.game.isAnalContentEnabled()) {
					responses.add(getKaySexResponse("Push over desk",
							"Push [kay.name] over [kay.her] desk and tell [kay.herHim] that you're going to fuck [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_DESK"),
							SexPosition.OVER_DESK,
							SexSlotDesk.OVER_DESK_ON_FRONT,
							null,
							SexSlotDesk.BETWEEN_LEGS,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				}

				responses.add(getKaySexResponse("Handjob",
						"Reward [kay.name] by taking hold of [kay.her] cock and jerking [kay.herHim] off.",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_HANDJOB"),
						SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
						SexSlotStanding.PERFORMING_ORAL,
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), FingerPenis.COCK_MASTURBATING_START, false, true))));

				if(isPlayerMouthFree()) {
					responses.add(getKaySexResponse("Suck cock",
							"Reward [kay.name] by leaning forwards and sucking [kay.her] cock.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_BLOWJOB"),
							SexPosition.STANDING,
							SexSlotStanding.STANDING_DOMINANT,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
							SexSlotStanding.PERFORMING_ORAL,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisMouth.GIVING_BLOWJOB_START, false, true))));
					
				} else {
					responses.add(new Response("Suck cock", "You cannot gain access to your mouth, and so are not able to suck [kay.namePos] cock.", null));
				}

				if(!isPlayerVaginaFree()) {
					responses.add(new Response("Humped", "You require a vagina, as well as access to it, in order to get fucked by [kay.name].", null));
					
				} else {
					responses.add(getKaySexResponse("Humped",
							"Present yourself to [kay.name] and let [kay.herHim] fuck your pussy.",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_VAGINAL"),
							SexPosition.ALL_FOURS,
							SexSlotAllFours.HUMPING,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
							SexSlotAllFours.ALL_FOURS,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.VAGINA),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true))));
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!isPlayerAssFree()) {
						responses.add(new Response("Humped (anal)", "You require access to your anus in order to receive anal from [kay.name].", null));
						
					} else {
						responses.add(getKaySexResponse("Humped (anal)",
								"Present yourself to [kay.name] and let [kay.herHim] fuck your ass.",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_ANAL"),
								SexPosition.ALL_FOURS,
								SexSlotAllFours.HUMPING,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
								SexSlotAllFours.ALL_FOURS,
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true))));
					}
				}

				for(int i=0;i<responses.size();i++) {
					if(i==index-1) {
						return responses.get(i);
					}
				}
				
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_BUTTPLUG = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).equipClothingFromNowhere(
								Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_jewel", PresetColour.CLOTHING_SILVER, PresetColour.COVERING_BLUE_LIGHT, null, false), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_EQUIP"));
				
			} else {
				Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS).setSealed(false); // Just in case
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).unequipClothingIntoVoid(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_UNEQUIP"));
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
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
				List<Response> responses = new ArrayList<>();

				responses.add(new Response("Sit back", "Sit back and let [kay.name] pull [kay.her] trousers back up.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_SIT_BACK"));
					}
				});
				
				if(!Main.game.getPlayer().isTaur()) {
					responses.add(getKaySexResponse("Pull into lap",
							"Pull [kay.name] into your lap and tell [kay.herHim] that you're going to fuck [kay.herHim].",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_LAP"),
							SexPosition.SITTING,
							SexSlotSitting.SITTING_IN_LAP,
							null,
							SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				} else {
					responses.add(new Response("Pull into lap", "As you have the lower body of [pc.a_legRace], you cannot pull [kay.name] into your lap!", null));
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(isPlayerMouthFree()) {
						responses.add(getKaySexResponse("Desk anilingus",
								"Push [kay.name] over [kay.her] desk and start eating [kay.her] ass.",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_DESK_ANILINGUS"),
								SexPosition.OVER_DESK,
								SexSlotDesk.OVER_DESK_ON_FRONT,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
								SexSlotDesk.PERFORMING_ORAL,
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.MOUTH),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), TongueAnus.ANILINGUS_START, false, true))));
						
					} else {
						responses.add(new Response("Desk anilingus", "You cannot gain access to your mouth, and so are not able to perform anilingus on [kay.name].", null));
					}
					
					if(isPlayerPenisFree()) {
						responses.add(getKaySexResponse("Desk anal",
								"Push [kay.name] over [kay.her] desk and fuck [kay.her] ass.",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_DESK_FUCK"),
								SexPosition.OVER_DESK,
								SexSlotDesk.OVER_DESK_ON_FRONT,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								SexSlotDesk.BETWEEN_LEGS,
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisAnus.PENIS_FUCKING_START, false, true))));
						
					} else {
						responses.add(new Response("Desk fuck", "You require a penis, as well as access to it, in order to anally fuck [kay.name].", null));
					}
				}

				for(int i=0;i<responses.size();i++) {
					if(i==index-1) {
						return responses.get(i);
					}
				}
				
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Tell Kay to get changed into 'her' new set of clothing...", KAY_OFFICE_DOMINATE_CLOTHING);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public boolean isContinuesDialogue() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayPreviouslyFeminised);
		}
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_MASCULINE"));
				Main.game.getNpc(Kay.class).removeAllMakeup();
				Main.game.getNpc(Kay.class).setName(new NameTriplet("Jack", "Jack", "Jackie"));
				Main.game.getNpc(Kay.class).setHairStyle(HairStyle.LOOSE);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFeminised, false);
				Main.game.getNpc(Kay.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_FEMININE"));
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFeminised, true);
				Main.game.getNpc(Kay.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getNpc(Kay.class).loadImages(true); // reload images to use correct artwork
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
				if(index==1) {
					return new Response("Kay", "Keep calling the cute cat-girl by her surname and tell her that she looks very pretty.", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Jack", "Jack", "Jackie"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==2) {
					return new Response("Jackie",
							"Tell the cute [kay.race] that she looks very pretty, and that the name of 'Jackie' is more suitable for [kay.herHim] when [kay.she] looks like this."
							+ "<br/>[style.colourFeminine(Kay will be renamed to 'Jackie' until (if ever) you tell [kay.herHim] to start wearing masculine clothing again.)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Jackie", "Jackie", "Jackie"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_JACKIE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==3) {
					return new Response("Kaytie",
							"Tell the cute [kay.race] that she looks very pretty, and that the name of 'Kaytie' is more suitable for [kay.herHim] when [kay.she] looks like this."
							+ "<br/>[style.colourFeminine(Kay will be renamed to 'Kaytie' until (if ever) you tell [kay.herHim] to start wearing masculine clothing again.)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Kaytie", "Kaytie", "Kaytie"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_KAYTIE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==4) {
					return new Response("Mikayla",
							"Tell the cute [kay.race] that she looks very pretty, and that the name of 'Mikayla' is more suitable for [kay.herHim] when [kay.she] looks like this."
							+ "<br/>[style.colourFeminine(Kay will be renamed to 'Mikayla' until (if ever) you tell [kay.herHim] to start wearing masculine clothing again.)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("Mikayla", "Mikayla", "Mikayla"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MIKAYLA"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				}
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_MAKEUP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget( Main.game.getNpc(Kay.class));
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MAKEUP"));
					
			sb.append(CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true));
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Finished", "You've finished giving [kay.name] a makeover.", KAY_OFFICE_DOMINATE_MAKEUP_END);
			}
			return null;
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_MAKEUP_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MAKEUP_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_NAMING = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			NPC kay = Main.game.getNpc(Kay.class);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NAMING"));
			
			UtilText.nodeContentSB.append(UtilText.parse(kay,
					"<p>"
						+ "At the moment, [npc.nameIsFull] calling you '[npc.pcName]', and you wonder if you should get [npc.herHim] to call you by a different name or title."
						+ " As [npc.sheIs] not your slave, you can't get [npc.herHim] to change [npc.her] name."
					+ "</p>"));
			
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='padding:8px 16px;'>"
					+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
						+ "Name"
					+ "</div>"
					+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
						+ "Surname"
					+ "</div>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
						+ UtilText.parse(kay, "What [npc.she] calls you")
					+ "</div>"
					
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(kay.getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
						
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(kay.getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
					
					+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(kay.getPetName(Main.game.getPlayer()))
						+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+kay.getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ " <div class='normal-button disabled' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "All Slaves"
					+ "</div>");
			
			UtilText.nodeContentSB.append(UtilText.parse(kay,
						"<p style='text-align:center; margin-top:4px;'>"
							+ "<i>If [npc.name] is told to call you 'Mom' or 'Dad', 'Mommy' or 'Daddy', 'Mistress' or 'Master', or 'Ma'am' or 'Sir',"
							+ " then [npc.she] will automatically switch to the appropriate paired name depending on the femininity of your character.</i>"
						+ "</p>"
					+ "</div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Finished", "You've finished choosing what [kay.name] calls you.", KAY_OFFICE_DOMINATE_NAMING_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_NAMING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NAMING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
}
