package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.Roxy;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMRoxyPussyLicker;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RoxysShop {

	private static boolean isAddictedToRoxy() {
		Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
		return ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getNpc(Roxy.class).getId());
	}
	
	public static final DialogueNode TRADER = new DialogueNode("Roxy's Fun Box", "", false) {
		@Override
		public void applyPreParsingEffects() {
			ItemEffectType.CIGARETTE.applyEffect(null, null, null, 0, Main.game.getNpc(Roxy.class), Main.game.getNpc(Roxy.class), null);
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced);
		}
		@Override
		public String getContent() {
			if(Main.game.getNpc(Vengar.class).isSlave() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyVengarOwnerIntroduced)) {
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_START"));
				if(isAddictedToRoxy()) {
					sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_ADDICTED"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_NOT_ADDICTED"));
				}
				sb.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_VENGAR_INTRO_END"));
				return sb.toString();
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				
				if(isAddictedToRoxy()) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPEAT_ADDICT");
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyAddicted)) {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPEAT_BEATEN_ADDICTION");
					
				} else {
					return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPEAT");
				}
				
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
					return new Response("Refuse", "Tell Roxy that you're only interested in having a look around her shop.", TRADER_REPLY_NO){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
					
				} else {
					return new ResponseTrade("Trade", "Trade with Roxy.", Main.game.getNpc(Roxy.class)){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
				}
				
			} else if (index == 2) {
				if(isAddictedToRoxy()) { // Repeat oral:
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Get fix ("+UtilText.formatAsMoneyUncoloured(1000, "span")+")",
								"You can only service Roxy if you're able to gain access to your mouth!",
								null);
						
					} else if(Main.game.getPlayer().getMoney()<1000) {
						return new Response("Get fix ("+UtilText.formatAsMoneyUncoloured(1000, "span")+")",
								"You don't have the one thousand flames that Roxy is asking for!",
								null);
						
					} else {
						return new ResponseSex("Get fix ("+UtilText.formatAsMoney(1000, "span")+")",
								"Desperate to get another fix of her addictive girl cum, you agree to <b>pay Roxy 1000 flames</b> to get her to sit on your face for an hour.",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Roxy.class), SexSlotLyingDown.FACE_SITTING)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								null,
								AFTER_ROXY_SEX_ADDICT,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "ROXY_SEX_START_ADDICT")){
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-1000));
								Main.game.getNpc(Roxy.class).incrementMoney(1000);
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Roxy.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
					}
					
				} else { // Perform oral:
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex("Lick for item", "Agree to let Roxy sit on your face and eat her out until she cums in exchange for a random item from her shop.",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true, false,
								new SMRoxyPussyLicker(
										Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Roxy.class), SexSlotLyingDown.FACE_SITTING)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								null,
								AFTER_ROXY_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "ROXY_SEX_START")){
							@Override
							public void effects() {
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Roxy.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
						
					} else {
						return new Response("Lick for item", "You can only service Roxy if you're able to gain access to your mouth!", null);
					}
				}
				
			} else if(index==3 && Main.game.getNpc(Vengar.class).isSlave()) {
				return new Response("Vengar", "Ask Roxy if you can talk to Vengar.", VENGAR);
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode TRADER_REPLY_NO = new DialogueNode("Roxy's Fun Box", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "TRADER_REPLY_NO");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_ROXY_SEX = new DialogueNode("Roxy stands up", "Roxy is finished, and, not caring whether you've had any fun or not, starts to stand up.", true) {
		
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_ROXY_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Roll", "Watch Roxy roll her dice.", AFTER_ROXY_SEX_ITEM_OBTAINED) {
					@Override
					public void effects() {
						Dice d = new Dice(Util.newHashMapOfValues(new Value<>(DiceFace.ONE, 8f), new Value<>(DiceFace.TWO, 4f), new Value<>(DiceFace.THREE, 2f)));
						d.roll();
						int d1 = d.getFace().getValue();
						d.roll();
						int d2 = d.getFace().getValue();
						AbstractItem item;
						
						int dTotal = d1 + d2;
						if(dTotal<=3) {
							item = AbstractItemType.generateItem(ItemType.INT_INGREDIENT_VANILLA_WATER);
						} else if(dTotal<=5) {
							item = AbstractItemType.generateItem(ItemType.INT_INGREDIENT_FRUIT_BAT_SQUASH);
						} else if(dTotal<=7) {
							item = AbstractItemType.generateItem(ItemType.MOTHERS_MILK);
						} else if(dTotal<=9) {
							item = AbstractItemType.generateItem(ItemType.STR_INGREDIENT_BLACK_RATS_RUM);
						} else if(dTotal<=11) {
							item = AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_RAT_MORPH);
						} else {
							item = AbstractItemType.generateItem(ItemType.RACE_INGREDIENT_HUMAN);
						}
						
						UtilText.addSpecialParsingString(Util.intToString(d1), true);
						UtilText.addSpecialParsingString(Util.intToString(d2), false);
						UtilText.addSpecialParsingString(Util.intToString(dTotal), false);
						UtilText.addSpecialParsingString(item.getName(true, false), false);
						UtilText.addSpecialParsingString(item.getName(), false);
						
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_ROXY_SEX_DICE_ROLL"));
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(item, false));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROXY_SEX_ITEM_OBTAINED = new DialogueNode("Roxy's Fun Box", "", false, true) {
		
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
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_ROXY_SEX_ADDICT = new DialogueNode("Roxy stands up", "Roxy is finished, and, not caring whether you've had any fun or not, starts to stand up.", false) {

		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_ROXY_SEX_ADDICT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	
	private static int VENGAR_SUB_SEX_COST = 1000;
	private static int VENGAR_SUB_DOM_COST = 1500;
	
	public static final DialogueNode VENGAR = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<VENGAR_SUB_DOM_COST) {
					return new Response("Fuck ("+UtilText.formatAsMoney(VENGAR_SUB_DOM_COST, "span")+")", "You don't have enough money to buy a session of dominant sex with Vengar.", null);
				}
				return new ResponseSex(
						"Fuck ("+UtilText.formatAsMoney(VENGAR_SUB_DOM_COST, "span")+")",
						"Tell Roxy that you're willing to pay in order to fuck Vengar.",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								Util.newArrayListOfValues(
										Main.game.getNpc(Vengar.class)),
								null,
								null){
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasPenis()) {
										if(Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
										}
									}
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
						},
						AFTER_VENGAR_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_SEX_DOM")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-VENGAR_SUB_DOM_COST));
						Main.game.getNpc(Roxy.class).incrementMoney(VENGAR_SUB_DOM_COST);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<VENGAR_SUB_SEX_COST) {
					return new Response("Fucked ("+UtilText.formatAsMoney(VENGAR_SUB_SEX_COST, "span")+")", "You don't have enough money to buy a session of submissive sex with Vengar.", null);
				}
				return new ResponseSex(
						"Fucked ("+UtilText.formatAsMoney(VENGAR_SUB_SEX_COST, "span")+")",
						"Tell Roxy that you're willing to pay in order to get Vengar to fuck you.",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								null,
								null){
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Vengar.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(targetedCharacter.hasVagina()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
									} else if(Main.game.isAnalContentEnabled()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
									}
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
						},
						AFTER_VENGAR_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_SEX_SUB")) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-VENGAR_SUB_SEX_COST));
						Main.game.getNpc(Roxy.class).incrementMoney(VENGAR_SUB_SEX_COST);
					}
				};
				
			} else if(index==3 && !Main.game.getNpc(Vengar.class).isFeminine()) {
				return new Response("Sissify", "Tell Roxy that Vengar would behave a lot better if she were to turn him into a sissy.", VENGAR_SISSIFY) {
					@Override
					public void effects() {
						((Vengar)Main.game.getNpc(Vengar.class)).applySissification();
					}
				};
				
			} else if(index==0) {
				return new Response("Back", "Decide against doing anything with Vengar.", TRADER);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_VENGAR_SEX = new DialogueNode("Finished", "You and Vengar are finished...", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_VENGAR_SEX_AS_DOM");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "AFTER_VENGAR_SEX_AS_SUB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGAR_SISSIFY = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/roxysShop", "VENGAR_SISSIFY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return TRADER.getResponse(responseTab, index);
		}
	};
}
