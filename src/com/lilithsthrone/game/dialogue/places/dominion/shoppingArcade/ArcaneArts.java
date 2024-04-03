package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Vicky;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMVicky;
import com.lilithsthrone.game.sex.managers.dominion.SMVickyOverDesk;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.dominion.VickySpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.4.7.8
 * @author Innoxia
 */
public class ArcaneArts {
	
	private static AbstractItemType mealItemType;
	private static int mealResponseIndex = 0;
	private static List<Value<String, String>> mealResponses;
	private static int helpResponseIndex = 0;
	private static List<Value<String, String>> helpResponses;
	
	private static boolean vickyRefused = false;
	private static boolean vickyHadSex = false;
	
	static {
		mealResponses = new ArrayList<>();
		mealResponses.add(new Value<>("Feed her", "Do as Vicky commands and cut up the meat for her, before feeding it to her bite-by-bite."));
		mealResponses.add(new Value<>("Petted", "Do as Vicky commands and kneel down beside her so that she can pet you while eating."));
		mealResponses.add(new Value<>("Give massage", "Do as Vicky commands and massage her shoulders while she eats."));

		helpResponses = new ArrayList<>();
		helpResponses.add(new Value<>("Stock shelves", "Do as Vicky commands and stock the shelves for her."));
		helpResponses.add(new Value<>("Sweep floor", "Do as Vicky commands and sweep the floor of the shop for her."));
		helpResponses.add(new Value<>("Organise weapons", "Do as Vicky commands and organise the weapons she has for sale."));
	}
	
	private static Vicky getVicky() {
		return (Vicky) Main.game.getNpc(Vicky.class);
	}
	
	private static boolean isInApartment() {
		return Main.game.getDialogueFlags().hasFlag("innoxia_vicky_apartment");
	}
	
	private static DialogueNode getPostSexScene() {
		if(isInApartment()) {
			return VICKY_APARTMENT_POST_SEX;
		}
		return VICKY_POST_SEX;
	}
	
	private static boolean isMuskMarked() {
		return Main.game.getPlayer().getMuskMarkerCharacter()==getVicky();
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Arcane Arts (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(Main.game.isWorkTime()) {
						return new Response("Enter", "Step inside Arcane Arts.", SHOP_WEAPONS) {
							@Override
							public void effects() {
								vickyRefused = false;
								vickyHadSex = false;
							}
						};
					} else {
						return new Response("Enter", "Arcane Arts is currently closed. You'll have to come back later if you want to do some shopping here.", null);
					}
				}
			}
			
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_WEAPONS = new DialogueNode("Arcane Arts", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!vickyRefused
					&& getVicky().getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>=3
					&& (Main.game.isMuskContentEnabled()
							?!isMuskMarked()
							:!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed"))) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_DOMINATE"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS"));
			}
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				getVicky().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			}
			if(getVicky().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(getVicky(), true);
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
			if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed") && (!Main.game.isMuskContentEnabled() || isMuskMarked()) && !vickyRefused) {
				if(index==0) {
					return "Business";
				} else if(index==1) {
					return "Pleasure";
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			
			// Domination responses:
			
			if(!vickyHadSex // So that this doesn't trigger immediately after having sex (which calls SHOP_WEAPONS.getResponse())
					&& !vickyRefused // So that this doesn't repeatedly trigger when refusing
					&& getVicky().getSexCount(Main.game.getPlayer()).getTotalTimesHadSex()>=3
					&& (Main.game.isMuskContentEnabled()
							?!isMuskMarked()
							:!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed"))) {
				if(index==1) {
					if(Main.game.isMuskContentEnabled()) {
						return new ResponseSex("Get marked", "Submit to Vicky and allow her to cum all over you in order to mark you with her musk.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
										new SexType(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
										new SexType(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
										null,
										Util.newArrayListOfValues(CoverableArea.PENIS)) {
									@Override
									public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
										return OrgasmBehaviour.PULL_OUT;
									}
									@Override
									public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
										if(!character.isPlayer()) {
											return OrgasmCumTarget.FACE;
										}
										return super.getCharacterPullOutOrgasmCumTarget(character, target);
									}
									@Override
									public boolean isPartnerWantingToStopSex(GameCharacter partner) {
										if(partner instanceof Vicky && Main.sex.isSatisfiedFromOrgasms(partner, true)) {
											return true;
										}
										return super.isPartnerWantingToStopSex(partner);
									}
									@Override
									public List<SexActionInterface> getUniqueSexClasses(GameCharacter character) {
										return Util.newArrayListOfValues(VickySpecials.VICKY_MARKING_ORGASM);
									}
								},
								null,
								null,
								VICKY_CLAIMED_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_MARKING_SEX")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getVicky(), FingerPenis.COCK_MASTURBATING_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex("Submit", "Submit to Vicky and let her dominantly fuck you.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE)),
										null, null, null, null),
								null,
								null,
								VICKY_CLAIMED_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_SUBMIT_SEX"));
					}
					
				} else if(index==2) {
					return new Response("Refuse", "Refuse to submit to Vicky.", VICKY_REFUSED) {
						@Override
						public void effects() {
							vickyRefused = true;
						}
					};
				}
				return null;
			}
			
			// Standard responses:

			if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
					}
				};
			}
			
			if(!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed") || responseTab==0) {
				if(index == 1) {
					return new ResponseTrade("Weapons", "Walk over to the counter and see what weapons Vicky has in stock.", getVicky()) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
							
							getVicky().clearNonEquippedInventory(false);
							
							for (Entry<AbstractWeapon, Integer> weapon : (getVicky()).getWeaponsForSale().entrySet()) {
								if(getVicky().isInventoryFull()) {
									break;
								}
								getVicky().addWeapon(weapon.getKey(), weapon.getValue(), false, false);
							}
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("Potions & Spells", "Walk over to the counter and see what potions, essences, and spells Vicky has in stock.", getVicky()) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);

							getVicky().clearNonEquippedInventory(false);
							
							for (Entry<AbstractItem, Integer> item : (getVicky()).getItemsForSale().entrySet()) {
								if(getVicky().isInventoryFull()) {
									break;
								}
								getVicky().addItem(item.getKey(), item.getValue(), false, false);
							}
						}
					};
					
				} else if (index == 3) {
					if((getVicky()).getClothingForSale().isEmpty()) {
						return new Response("Clothing", "Vicky doesn't have any clothing in stock at the moment.", null);
					}
					return new ResponseTrade("Clothing", "Walk over to the counter and see what clothing Vicky has in stock.", getVicky()) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);

							getVicky().clearNonEquippedInventory(false);
							
							for (Entry<AbstractClothing, Integer> clothing : (getVicky()).getClothingForSale().entrySet()) {
								if(getVicky().isInventoryFull()) {
									break;
								}
								getVicky().addClothing(clothing.getKey(), clothing.getValue(), false, false);
							}
						}
					};
				}
				
				// After Vicky has claimed the player, sexual actions are under the 'Pleasure' tab
				if(!Main.game.getDialogueFlags().hasFlag("innoxia_vicky_claimed")) {
					if(index==5) {
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
							if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HYPNO_WATCH)==Quest.SIDE_HYPNO_WATCH_VICKY) {
								if(Main.game.getPlayer().isInventoryFull()) {
									return new Response("Arthur's package", "You don't have enough room in your inventory for the package!", null);
									
								} else {
									return new Response("Arthur's package", "Tell Vicky that you're here to collect Arthur's package.", ARTHURS_PACKAGE) {
										@Override
										public void effects() {
											Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
										}
									};
								}
								
							} else {
								return null;
							}
							
						} else {
							if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
									&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
								return new Response("Offer body",
										"Vicky needs to be able to access your "
											+ (Main.game.isAnalContentEnabled()?"anus":"")
											+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?" or ":"")+"vagina":"")+"!",
										null);
								
							} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
								return new Response("Offer body",
										"Vicky has already fucked you today, and doesn't have time to do so again...",
										null);
								
							} else {
								return new ResponseSex("Offer body", "Let Vicky use your body.",
										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
										true, false,
										new SMVickyOverDesk(
												Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
										null,
										null,
										getPostSexScene(),
										UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_BODY"));
							}
						}
						
					} else if (index == 10 && Main.getProperties().hasValue(PropertyValue.nonConContent) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.arthursPackageObtained)) {
						if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
								&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
							return new Response("Nervously leave",
									"Vicky needs to be able to access your "
										+ (Main.game.isAnalContentEnabled()?"anus":"")
										+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?" or ":"")+"vagina":"")+"!",
									null);
							
						} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
							return new Response("Nervously leave",
									"Vicky has already fucked you today, and doesn't have time to do so again...",
									null);
							
						} else {
							return new ResponseSex("Nervously leave",
									"Vicky is far too intimidating for you... Turn around and try to escape from her gaze."
											+ "<br/>[style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
									Util.newArrayListOfValues(
											Fetish.FETISH_SUBMISSIVE,
											Fetish.FETISH_NON_CON_SUB), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
									false, false,
									new SMVickyOverDesk(
											Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
										@Override
										public SexPace getStartingSexPaceModifier(GameCharacter character) {
											if(character.isPlayer()) {
												return SexPace.SUB_RESISTING;
											}
											return null;
										}
									},
									null,
									null,
									VICKY_POST_SEX_RAPE,
									UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_RAPE"));
						}
					}
				}
				
			} else if(responseTab==1) {
				if(index==1) { // Offer cunnilingus:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Offer cunnilingus",
								"You need to be able to access your mouth in order to eat Vicky out!",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("Offer cunnilingus",
								"Vicky has already fucked you today, and doesn't have time to do so again...",
								null);
						
					} else {
						return new ResponseSex("Offer cunnilingus", "Tell Vicky that you want to eat her out.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										isInApartment()
											?SexPosition.SITTING
											:SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL)),
										new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newArrayListOfValues(CoverableArea.MOUTH),
										Util.newArrayListOfValues(CoverableArea.VAGINA)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_CUNNILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getVicky(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						};
					}
				}
				if(index==2) { // Offer blowjob:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Offer blowjob",
								"You need to be able to access your mouth in order to give Vicky a blowjob!",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("Offer blowjob",
								"Vicky has already fucked you today, and doesn't have time to do so again...",
								null);
						
					} else {
						return new ResponseSex("Offer blowjob", "Tell Vicky that you want to give her a blowjob.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										isInApartment()
											?SexPosition.SITTING
											:SexPosition.STANDING,
										Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotStanding.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL)),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newArrayListOfValues(CoverableArea.MOUTH),
										Util.newArrayListOfValues(CoverableArea.PENIS)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_BLOWJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getVicky(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
						};
					}
				}
				if(index==3) { // Offer pussy:
					if(!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Offer pussy",
								!Main.game.getPlayer().hasVagina()
									?"You don't have a pussy..."
									:"Vicky needs to be able to access your pussy in order to fuck it!",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("Offer pussy",
								"Vicky has already fucked you today, and doesn't have time to do so again...",
								null);
						
					} else {
						return new ResponseSex("Offer pussy", "Let Vicky fuck your pussy.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.OVER_DESK,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT)),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
										Util.newArrayListOfValues(CoverableArea.VAGINA),
										Util.newArrayListOfValues(CoverableArea.PENIS)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_PUSSY")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						};
					}
				}
				if(index==4 && Main.game.isAnalContentEnabled()) { // Offer ass:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("Offer ass",
								"Vicky needs to be able to access your asshole in order to fuck it!",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("Offer ass",
								"Vicky has already fucked you today, and doesn't have time to do so again...",
								null);
						
					} else {
						return new ResponseSex("Offer ass", "Let Vicky fuck your ass.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMVicky(
										SexPosition.OVER_DESK,
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT)),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
										Util.newArrayListOfValues(CoverableArea.VAGINA),
										Util.newArrayListOfValues(CoverableArea.ANUS)),
								null,
								null,
								getPostSexScene(),
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "SHOP_WEAPONS_OFFER_ASS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
						};
					}
				}
				if(Main.game.isAnalContentEnabled()?index==5:index==4) {
					if(!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
						return new Response("Offer cock",
								!Main.game.getPlayer().hasPenis()
									?"You don't have a penis, so can't offer it to Vicky!"
									:"Vicky needs to be able to access your penis in order to use it!",
								null);
						
					} else if(!getVicky().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
						return new Response("Offer cock",
								"Vicky doesn't like you enough to allow you to use your cock on her..."
								+ "<br/>[style.italicsMinorBad(Requires Vicky's affection towards you to be at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()
									+", and it is currently "+((int)getVicky().getAffection(Main.game.getPlayer()))+"!)]",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("Offer cock",
								"Vicky has already fucked you today, and doesn't have time to do so again...",
								null);
						
					} else {
						return new Response("Offer cock",
								"Ask Vicky if she'll let you use your cock.",
								VICKY_PET_OFFER_COCK) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
				}
				// Non-sex:
				if(index==6) {
					if(Main.game.getHourOfDay()>15) {
						return new Response("Offer lunch",
								"It's too late in the day to be offering Vicky lunch..."
								+ "<br/>[style.italicsMinorBad(She will only want to eat if the time is before [style.time(16)].)]",
								null);
						
					} else if(!Main.game.getPlayer().hasItemType("innoxia_race_wolf_meat_and_marrow")
							&& !Main.game.getPlayer().hasItemType("innoxia_race_panther_panthers_delight")) {
						return new Response(
								Main.game.isMorning()
									?"Offer breakfast"
									:"Offer lunch",
								"Vicky only likes to eat meat, so you'll need to have either '"
									+ItemType.getItemTypeFromId("innoxia_race_wolf_meat_and_marrow").getName(false)
									+"' or '"
									+ItemType.getItemTypeFromId("innoxia_race_panther_panthers_delight").getName(false)
									+"' in your inventory to offer to her...",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_fed")) {
							return new Response(
								Main.game.isMorning()
									?"Offer breakfast"
									:"Offer lunch",
								"You've already given Vicky some food today, so if you wanted to offer her another meal then you'll have to do so tomorrow...",
								null);
							
					} else {
						AbstractItemType itemTypeBeingOffered = ItemType.getItemTypeFromId("innoxia_race_wolf_meat_and_marrow");
						mealItemType = itemTypeBeingOffered;
						if(!Main.game.getPlayer().hasItemType("innoxia_race_wolf_meat_and_marrow")) {
							itemTypeBeingOffered = ItemType.getItemTypeFromId("innoxia_race_panther_panthers_delight");
							mealItemType = itemTypeBeingOffered;
						}
						return new Response(
								Main.game.isMorning()
									?"Offer breakfast"
									:"Offer lunch",
								"Offer Vicky the '"+itemTypeBeingOffered.getName(false)+"' in your inventory.",
								VICKY_PET_OFFER_MEAL);
					}
				}
				if(index==7) {
					if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_helped")) {
						return new Response("Offer help",
								"You've already helped Vicky today, so if you wanted to offer her any more assistance then you'll have to do so tomorrow...",
								null);
						
					} else {
						return new Response("Offer help",
								"Ask Vicky if there's anything you can do to help her.",
								VICKY_PET_OFFER_HELP);
					}
				}
				if(index==8) {
					if(!getVicky().getAffectionLevel(Main.game.getPlayer()).isGreaterThan(AffectionLevel.POSITIVE_TWO_LIKE)) {
						return new Response("Evening company",
								"Vicky doesn't like you enough to want your company for the evening..."
								+ "<br/>[style.italicsMinorBad(Requires Vicky's affection towards you to be at least "+AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue()
									+", and it is currently "+((int)getVicky().getAffection(Main.game.getPlayer()))+"!)]",
								null);
						
					} else if(Main.game.getHourOfDay()<16) {
						return new Response("Evening company",
								"It's too early in the day to be offering Vicky your company for the evening..."
								+ "<br/>[style.italicsMinorBad(She will only consider this if the time is past [style.time(16)].)]",
								null);
						
					} else {
						return new Response("Evening company",
								"Ask Vicky if she'd like you to spend the evening with her to give her some company.",
								VICKY_PET_OFFER_COMPANY);
					}
				}
				if (index == 10 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
					if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						return new Response("Nervously leave",
								"Vicky needs to be able to access your "
									+ (Main.game.isAnalContentEnabled()?"anus":"")
									+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?" or ":"")+"vagina":"")+"!",
								null);
						
					} else if(Main.game.getDialogueFlags().hasFlag("innoxia_vicky_daily_sex")) {
						return new Response("Nervously leave",
								"Vicky has already fucked you today, and doesn't have time to do so again...",
								null);
						
					} else {
						return new ResponseSex("Nervously leave",
								"Vicky is far too intimidating for you... Turn around and try to escape from her gaze."
								+ "<br/>[style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
								Util.newArrayListOfValues(
										Fetish.FETISH_SUBMISSIVE,
										Fetish.FETISH_NON_CON_SUB), null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
								false, false,
								new SMVickyOverDesk(
										Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.isPlayer()) {
											return SexPace.SUB_RESISTING;
										}
										return null;
									}
								},
								null,
								null,
								VICKY_POST_SEX_RAPE,
								UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_RAPE"));
					}
				}
			}
			
			
			return null;
		}
	};
	
	public static final DialogueNode ARTHURS_PACKAGE = new DialogueNode("Arcane Arts", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Pay ("+UtilText.formatAsMoney(100, "span")+")", "Pay Vicky 100 flames.", ARTHURS_PACKAGE_BOUGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.arthursPackageObtained, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ARTHURS_PACKAGE), false, true));
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(100, "span")+")", "You don't have enough money to pay the fee!", null);	
				}
				
			} else if (index == 2) {
				if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
						&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
					return new Response("Offer body",
							"Vicky needs to be able to access your "
								+ (Main.game.isAnalContentEnabled()?"anus":"")
								+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?" or ":"")+"vagina":"")+"!",
							null);
					
				} else {
					return new ResponseSex("Offer body", "Let Vicky use your body as payment for the fee.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
							true, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))),
							null,
							null,
							VICKY_POST_SEX_PACKAGE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_SEX"));
					
				}
				
			} else if (index == 3 && Main.getProperties().hasValue(PropertyValue.nonConContent)) {
				if((!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
						&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
					return new Response("Weakly refuse",
							"Vicky needs to be able to access your "
								+ (Main.game.isAnalContentEnabled()?"anus":"")
								+ (Main.game.getPlayer().hasVagina()?(Main.game.isAnalContentEnabled()?" or ":"")+"vagina":"")+"!",
							null);
					
				} else {
					return new ResponseSex(
							"Weakly refuse",
							"You can't bring yourself to say no to such an intimidating person... Try to wriggle free and leave..."
									+ "<br/>[style.boldBad(You get the feeling that this will result in non-consensual sex...)]",
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE,
									Fetish.FETISH_NON_CON_SUB),
							null, CorruptionLevel.FOUR_LUSTFUL, null, null, null,
							false, false,
							new SMVickyOverDesk(
									Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotDesk.BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							null,
							VICKY_POST_SEX_RAPE_PACKAGE,
							UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_RAPE"));
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Arcane Arts and head back out into the arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vickyIntroduced, true);
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_LEAVE"));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ARTHURS_PACKAGE_BOUGHT = new DialogueNode("Arcane Arts", "-", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "ARTHURS_PACKAGE_BOUGHT");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	
	public static final DialogueNode VICKY_POST_SEX_PACKAGE = new DialogueNode("Arcane Arts", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_PACKAGE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX_RAPE_PACKAGE = new DialogueNode("Arcane Arts", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_RAPE_PACKAGE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX = new DialogueNode("Finished", "Vicky has had her fun...", true) {
		@Override
		public void applyPreParsingEffects() {
			vickyHadSex = true;
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VICKY_POST_SEX_RAPE = new DialogueNode("Finished", "Vicky has had her fun...", true) {
		@Override
		public void applyPreParsingEffects() {
			vickyHadSex = true;
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_POST_SEX_RAPE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_CLAIMED_AFTER_SEX = new DialogueNode("Finished", "Vicky has finished with you...", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_claimed", true);
			if(Main.game.isMuskContentEnabled()) {
				Main.game.getPlayer().setMuskMarker(getVicky().getId()); // Just to make sure that the player was marked
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_CLAIMED_AFTER_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_REFUSED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_REFUSED");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};
	
	// Vicky dominated player content:

	public static final DialogueNode VICKY_PET_OFFER_COCK = new DialogueNode("Arcane Arts", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Handjob", "Ask Vicky to give you a handjob.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								isInApartment()
									?SexPosition.SITTING
									:SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotAgainstWall.STANDING_WALL)), // TODO test positions interaction
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.SITTING_TWO:SexSlotAgainstWall.BACK_TO_WALL)),
								new SexType(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS),
								new SexType(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								null),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_HANDJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			if(index==2) {
				return new ResponseSex("Blowjob", "Ask Vicky to give you a blowjob.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								isInApartment()
									?(Main.game.getPlayer().isTaur()?SexPosition.STANDING:SexPosition.SITTING)
									:SexPosition.AGAINST_WALL,
								Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?(Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL):SexSlotAgainstWall.PERFORMING_ORAL_WALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?(Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING):SexSlotAgainstWall.BACK_TO_WALL)),
								new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
								new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								null),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			if(index==3) {
				return new ResponseSex("Pussy", "Tell Vicky that you want to fuck her pussy.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
								new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.VAGINA)),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_PUSSY")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
					}
				};
			}
			if(index==4 && Main.game.isAnalContentEnabled()) {
				return new ResponseSex("Anal", "Tell Vicky that you want to fuck her ass.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotLyingDown.COWGIRL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.ANUS)),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_ANAL")) { //TODO virginity loss handling
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
					}
				};
			}
			if(Main.game.isFootContentEnabled() && (Main.game.isAnalContentEnabled()?index==5:index==4)) {
				return new ResponseSex("Footjob", "Tell Vicky that you want her to give you a footjob.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, false,
						new SMVicky(
								isInApartment()
									?SexPosition.SITTING
									:SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getVicky(), isInApartment()?SexSlotSitting.SITTING:SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), isInApartment()?SexSlotSitting.PERFORMING_ORAL:SexSlotStanding.PERFORMING_ORAL)),//TODO test sitting position
								new SexType(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								new SexType(SexAreaPenetration.FOOT, SexAreaPenetration.PENIS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.FEET)),
						null,
						null,
						getPostSexScene(),
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COCK_FOOTJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getVicky(), Main.game.getPlayer(), PenisFoot.FOOT_JOB_SINGLE_GIVING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_PET_OFFER_MEAL = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeItemByType(mealItemType, 1, true);
			mealResponseIndex = Util.random.nextInt(mealResponses.size());
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_fed", true);
			if(mealResponseIndex==0) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_DEMAND_FEED_HER"));
			} else if(mealResponseIndex==1) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_DEMAND_PETTINGS"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_DEMAND_MASSAGE"));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(mealItemType.getName(false), true);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(mealResponses.get(mealResponseIndex).getKey(),
						mealResponses.get(mealResponseIndex).getValue(),
						VICKY_PET_OFFER_MEAL_END);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_MEAL_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(getVicky().incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(mealItemType.getName(false), true);
			if(mealResponseIndex==0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_FEED_HER"));
			} else if(mealResponseIndex==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_PETTINGS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_MASSAGE"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_MEAL_END"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			helpResponseIndex = Util.random.nextInt(helpResponses.size());
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_helped", true);
			if(helpResponseIndex==0) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_DEMAND_STOCK_SHLEVES"));
			} else if(helpResponseIndex==1) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_DEMAND_SWEEP_FLOOR"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_DEMAND_ORGANISE_WEAPONS"));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(helpResponses.get(helpResponseIndex).getKey(),
						helpResponses.get(helpResponseIndex).getValue(),
						VICKY_PET_OFFER_HELP_END);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_HELP_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(getVicky().incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(helpResponseIndex==0) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_STOCK_SHLEVES"));
			} else if(helpResponseIndex==1) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_SWEEP_FLOOR"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_ORGANISE_WEAPONS"));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_HELP_END"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_END = new DialogueNode("", "", true) {
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
			return SHOP_WEAPONS.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_WEAPONS.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_COMPANY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COMPANY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait",
						"Wait with Vicky until closing time...",
						VICKY_PET_OFFER_COMPANY_APARTMENT);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_PET_OFFER_COMPANY_APARTMENT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_apartment", true);
			Main.game.getDialogueFlags().setFlag("innoxia_vicky_daily_sex", false); // So that sex options are available after the shower
			Main.game.getTextEndStringBuilder().append(getVicky().incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(18 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COMPANY_APARTMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Shower time", "Join Vicky in the shower.",
						true, false,
						new SMVicky(
								SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getVicky(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE)),
								null, null, null, null) {
							@Override
							public boolean isWashingScene() {
								return true;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return true;
							}
							@Override
							public List<AbstractSexPosition> getAllowedSexPositions() {
								return Util.newArrayListOfValues(
										SexPosition.STANDING,
										SexPosition.AGAINST_WALL);
							}
						},
						null,
						null,
						VICKY_APARTMENT_POST_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_PET_OFFER_COMPANY_APARTMENT_SHOWER_SEX"));
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_POST_SHOWER_SEX = new DialogueNode("Finished", "Vicky has had her fun...", true) {
		@Override
		public void applyPreParsingEffects() {
			getVicky().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240);
			getVicky().equipUnderwear();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_POST_SHOWER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chat",
						"Relax with Vicky and chat with her for a while...",
						VICKY_APARTMENT_SLEEP);
			} else if(index<=6) {
				return SHOP_WEAPONS.getResponse(1, index-1);
			}
			return null;
		}
	};

	public static final DialogueNode VICKY_APARTMENT_POST_SEX = new DialogueNode("Finished", "Vicky has had her fun...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Chat",
						"Relax with Vicky and chat with her for a while...",
						VICKY_APARTMENT_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep",
						"It seems like Vicky isn't giving you a choice, so you'll have to sleep over at her place tonight...",
						VICKY_APARTMENT_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(7*60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave",
						"Say goodbye to Vicky and head back out through her store to return to the shopping arcade.",
						VICKY_APARTMENT_END_LEAVE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag("innoxia_vicky_apartment", false);
						Main.game.getPlayer().setNearestLocation(PlaceType.SHOPPING_ARCADE_PATH);
						getVicky().equipClothing();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VICKY_APARTMENT_END_LEAVE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/arcaneArts", "VICKY_APARTMENT_END_LEAVE");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return PlaceType.SHOPPING_ARCADE_PATH.getDialogue(false).getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PlaceType.SHOPPING_ARCADE_PATH.getDialogue(false).getResponse(responseTab, index);
		}
	};
}
