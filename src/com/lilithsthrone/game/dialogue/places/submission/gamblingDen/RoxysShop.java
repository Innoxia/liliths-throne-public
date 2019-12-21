package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.Roxy;
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
import com.lilithsthrone.game.sex.managers.submission.SMRoxyPussyLicker;
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
				
				if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getNpc(Roxy.class).getId())) {
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
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.roxyIntroduced)) {
				if (index == 1) {
					return new Response("Refuse", "Tell Roxy that you're only interested in having a look around her shop.", TRADER_REPLY_NO){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new ResponseSex("Lick for item", "Agree to let Roxy sit on your face and eat her out until she cums in exchange for a random item from her shop.",
								Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING), null, CorruptionLevel.TWO_HORNY, null, null, null,
								true,
								false,
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
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseTrade("Trade", "Trade with Roxy.", Main.game.getNpc(Roxy.class)){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.roxyIntroduced);
						}
					};
					
				} else if (index == 2) {
					Addiction ratGCumAdd = Main.game.getPlayer().getAddiction(FluidType.GIRL_CUM_RAT_MORPH);
					
					if(ratGCumAdd!=null && ratGCumAdd.getProviderIDs().contains(Main.game.getNpc(Roxy.class).getId())) {
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
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Roxy.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							};
						}
						
					} else {
						if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex("Lick for item", "Agree to let Roxy sit on your face for an hour, in exchange for a random item from her shop.",
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
					
				} else {
					return null;
				}
			}
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
}
