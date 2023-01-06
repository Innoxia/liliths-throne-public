package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.SexManagerRalphDiscount;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.82
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RalphsSnacks {
	
	private static void resetDiscountCheck() {
		// if 3 days have passed, reset discount:
		if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)) >= (60*24*3)){
			Main.game.getDialogueFlags().ralphDiscount=0;
		}
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Ralph's Snacks (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Enter", "'Ralph's Snacks' is currently closed, so you'll have to come back during opening hours if you wanted to take a look inside.", null);
					}
					return new Response("Enter", "Step inside Ralph's Snacks.", INTERIOR){
						@Override
						public void effects() {
							resetDiscountCheck();
						}
					};
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode INTERIOR = new DialogueNode("Ralph's Snacks", "-", true) {

		@Override
		public String getContent() {
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ralphIntroduced)) {
				return "<p>"
							+ "You push the door open and step inside, looking up as a little bell rings to announce your arrival."
							+ " [ralph.speech(Hey there! If you need any help, just ask!)] a horse-boy shouts to you from behind the counter."
						+ "</p>"
						+ "<p>"
							+ "You thank the horse-boy, who you assume to be Ralph, and start to have a look around his shop."
							+ " Most of the goods aren't anything special, and are just the typical sorts of food and drink that you could pick up almost anywhere."
							+ " What sets this shop apart, however, is a special display of arcane-imbued consumables."
							+ " The prices aren't listed, and instead, a little label reads 'Please ask Ralph for assistance with these items'."
						+ "</p>";
				
			} else {
				UtilText.nodeContentSB.setLength(0);
				
				UtilText.nodeContentSB.append("<p>"
							+ "You make your way over, once again, to the only place in the shopping arcade that sells food to go."
							+ " From the outside, it looks like an old-fashioned sweet shop, with large glass windows displaying all manner of exotic-looking food and drink."
							+ " The words 'Ralph's Snacks' are painted in gold cursive letters above the entrance, and as you push the door open and step inside, a little bell rings to announce your arrival."
						+ "</p>"
						+ "<p>"
						+ (Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0
								?"As you enter the shop, Ralph winks at you and calls out from behind the very-familiar counter,"
									+" [ralph.speech(Ah, well if it isn't my favourite regular! If you need any help, you know how to ask!)]"
								:"[ralph.speech(Hello again! If you need any help, just ask!)] the familiar horse-boy shouts to you from behind the counter.")
						+ "</p>"
						+ "<p>"
							+ "You thank Ralph, and start to have a look around his shop."
							+ " Most of the goods aren't anything special, and are just the typical sorts of food and drink that you could pick up anywhere."
							+ " What sets this shop apart, however, is a special display of arcane-imbued consumables."
							+ " The prices aren't listed, and instead, a little label reads 'Please ask Ralph for assistance with these items'."
						+ "</p>");
					
				if(((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive()){
					UtilText.nodeContentSB.append("<p>"
									+ "<b>Ralph is giving you a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>discount!</b>"
								+ "</p>");
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Ralph", "Go and ask Ralph about the special consumables on display.", Main.game.getNpc(Ralph.class)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						resetDiscountCheck();
					}
				};
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Discount", "Ask Ralph if there's anything you can do to get a discount.", INTERIOR_ASK_FOR_DISCOUNT){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
							resetDiscountCheck();
						}
					};
					
				} else {
					return new Response("Discount", "You can only get a discount from Ralph if he's able to gain access to your mouth!", null);
					
				}

			} else if (index == 3
					&& Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)
					&& Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_LIPSTICK
					&& !Main.game.getPlayer().hasItemType(ItemType.CANDI_HUNDRED_KISSES)) {
					return new Response("Candi's lipstick",
							!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.ralphAskedAboutHundredKisses)
								?"Ask Ralph if he has a box of 'A Hundred Kisses', and if he'd be willing to sell it to you."
								:"Ask Ralph if he still has the box of 'A Hundred Kisses' for sale.",
							CANDI_LIPSTICK) {
						@Override
						public void effects() {
							resetDiscountCheck();
						}
					};

			} else if (index == 0) {
				return new Response("Leave", "Leave Ralph's shop.", EXTERIOR){
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						resetDiscountCheck();
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_ASK_FOR_DISCOUNT = new DialogueNode("Ralph's Snacks", "-", true, true) {


		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0){
				return "<p>"
						+"Glancing over to the other side of the shop, you see Ralph giving you a cheerful wave from behind the counter."
						+ " Images of his massive equine cock flash across your mind, and you let out a tiny moan as you remember it sliding deep down your throat..."
						+ " His wide, flared head ramming its way past your lips... And the taste of his salty precum as saliva drools from your mouth..."
					+ "</p>"
					+ "<p>"
						+"A familiar voice suddenly snaps you out of your daydream, "
						+ "[ralph.speech(Hey, are you alright?)]"
					+ "</p>"
					+ "<p>"
					 	+ "You were so engrossed in your fantasy that you didn't notice Ralph walking up to you, and, having been caught completely off-guard, you blurt out a reply in the affirmative."
					 	+ " As you turn to face the muscular horse-boy, you notice that his eyes have started to roam up and down your body, and you get the feeling that he knows exactly what you were imagining."
						+ " Glancing down, you see a distinctive bulge forming between his legs, but before you can react to the sight of Ralph's growing erection, he steps forwards, pinning you back against the wall."
					+ "</p>"
					+ "<p>"
						+ "[ralph.speech(Fancy another taste?)] he slyly asks, before grinning deviously at you and leaning in a little closer."
						+ " As you feel his hot breath on your face, the horse-boy continues, "
						+ "[ralph.speech(Let me blow a load down that pretty little throat of yours, and I'll give you twenty-five percent off everything I have in stock for a few days.)]"
					+ "</p>"
					+ "<p>"
						+ "As Ralph steps forwards and presses his hot body against yours, you struggle to get the image of his massive, throbbing black horse-cock out of your mind,"
							+ " and as the tent in his trousers comes into contact with your leg, you blurt out your reply."
					+ "</p>";
				
			}else{
				return "<p>"
							+ "You get the feeling that the price of many of these items is inflated past what would be considered a fair markup."
							+ " Glancing over to the other side of the shop, you see Ralph giving you a cheerful wave from behind the counter."
							+ " Encouraged by his disarming smile and friendly face, you decide to ask him if there's any way you can get a discount on some of these transformative items, and start to walk over to him."
						+ "</p>"
						+ "<p>"
							+ "As he sees you approaching, Ralph calls out to you, [ralph.speech(Hello! Is there anything I can help you with?)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Hi there, I was wondering if you could show me the prices of some of these items,)] you reply." 
						+ "</p>"
						+ "<p>"
							+ "Ralph cheerfully leads you back over to the special display, and as he informs you of some of the prices, your suspicions prove to be correct."
							+ " While some of the items are a reasonable price, the more exotic foodstuffs are quite expensive."
							+ " Looking across at the friendly shopkeeper, you ask him if there's any way he'd drop the prices a little."
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(Hmm,)] he says, glancing over at you, "
							+ "[ralph.speech(I suppose we could work something out...)]"
						+ "</p>"
						+ "<p>"
							+ "You notice that his smile has suddenly lost its friendly appearance, and as his eyes roam up and down your body, you get the feeling that this horse-boy is having some dirty thoughts."
							+ " Glancing down, you see a distinctive bulge forming between his legs, but before you can react to the sight of Ralph's growing erection, he steps forwards, pinning you back against the wall."
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(Fancy another taste?)] he slyly asks, before grinning deviously at you and leaning in a little closer."
							+ " As you feel his hot breath on your face, the horse-boy continues, "
							+ "[ralph.speech(Let me blow a load down that pretty little throat of yours, and I'll give you twenty-five percent off everything I have in stock for a few days.)]"
						+ "</p>"
						+ "<p>"
							+ "As Ralph steps forwards and presses his hot body against yours, you struggle to get the image of his massive, throbbing black horse-cock out of your mind,"
								+ " and as the tent in his trousers comes into contact with your leg, you blurt out your reply."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Agree", "Agree to do as Ralph says and suck his cock.", Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING),
						null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, true,
						new SexManagerRalphDiscount(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB))),
						null,
						null,
						AFTER_SEX,
						"<p>"
							+ UtilText.parsePlayerSpeech("Ok, I'll do it,") + " you say, looking up at Ralph to see his smile grow even wider." + "</p>" + "<p>"
							+ "He leans in, and you half-expect him to try and kiss you, but instead, he simply grabs your " + Main.game.getPlayer().getArmNameSingular() + " and starts to drag you back to his desk."
							+ " As he walks, he starts instructing you on what's about to happen."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("You're going to kneel under my desk over here, and I don't expect to have to do any of the work, understood?", Main.game.getNpc(Ralph.class))
							+ " he asks, and as you answer in the affirmative, he continues, "
							+ UtilText.parseSpeech("This is a respectable shop, so if anyone comes in, you're to keep quiet! For each customer that hears you, I'm going to knock five percent off our deal.", Main.game.getNpc(Ralph.class))
						+ "</p>"
						+ "<p>"
							+ "By this time, Ralph's led you behind the shop's front desk, and you see that there's a hollow space beneath the counter-top, large enough for you to kneel inside quite comfortably."
							+ " The desk's solid front conceals you from the rest of the shop, and you realise that if you keep quiet, any customers will be completely oblivious as to what's going on."
							+ " Ralph places his hands on your shoulders, and, feeling that it's too late to back out now, you allow him to push you to your knees."
							+ " Shuffling back, you occupy the space under his desk, and Ralph steps forwards, bringing the massive bulge in his trousers right up to your face."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Make sure you give my balls some attention as well,", Main.game.getNpc(Ralph.class)) + " you hear him command."
						+ "</p>"
						+ "<p>"
							+ "Just as you're about to answer him, you hear the little bell over the shop's front door ring out, announcing the arrival of a customer."
							+ " You hear Ralph calling out his friendly greeting, but as he does so, he pushes his hips forwards, making it quite clear that he wants you to get started."
							+ " There isn't much room for you to move around, and you realise that you're going to be totally restricted to using just your mouth in order to earn your discount."
						+ "</p>"
						+ "<p>"
							+ "As the customer walks off to another part of the shop, Ralph reaches down and unbuttons his trousers."
							+ " With a quick tug, he pulls them, along with his boxers, down to pool around his ankles."
							+ " You feel your eyes go wide as you see the gigantic length of Ralph's rapidly-hardening horse-cock rise up to bump against your chin."
							+ " His huge pair of black-skinned balls droop down loosely at the base of his bestial shaft, and you gulp at the thought of what's about to happen..."
						+ "</p>" 
						+ "<p>" 
							+ "<b>There are</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
							+ " <b>You will earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>25%</b> <b>discount.</b>"
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getNpc(Ralph.class).setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getNpc(Ralph.class).displaceClothingForAccess(CoverableArea.PENIS, null);
						SexFlags.customerAtCounter = false;
						SexFlags.customerTurnAppearance = 0;
					}
				};
				
			} else if (index == 2) {
				return new Response("Refuse", "Tell him that you're not willing to do that.", INTERIOR_REFUSE_DISCOUNT_CONDITIONS);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_REFUSE_DISCOUNT_CONDITIONS = new DialogueNode("Ralph's Snacks", "-", true) {

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().getSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID)>0){
				return "<p>"
						+ "[pc.speech(No thanks,)] you force yourself to say, stepping to one side and moving away from the horny shopkeeper."
					+ "</p>"
					+ "<p>"
						+ "[ralph.speech(Well, if you get a craving, I'm always ready to satisfy it!)] he laughs, turning around and heading back to his counter, "
						+ "[ralph.speech(If you need anything else, just let me know.)]" 
					+ "</p>"
					+ "<p>"
						+ "You go back to browsing the shop's wares, the images of Ralph's massive black horse-cock refusing to budge from your head."
					+ "</p>";
				
			}else{
				return "<p>"
							+ "[pc.speech(No thanks,)] you say, stepping to one side and moving away from the horny shopkeeper."
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(Well, no harm in either of us asking!)] he laughs, turning around and heading back to his counter. "
							+ "[ralph.speech(If you need anything else, just let me know.)]" 
						+ "</p>"
						+ "<p>"
							+ "You go back to browsing the shop's wares, but you find it hard to shake the image of Ralph's massive bulge from your head."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Shopping", "Return to browsing the wares in Ralph's shop.", true) {
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Ralph returns to running his shop, and you walk back over to the transformative consumables section, wondering if you should buy anything with your discount."
						+ " When he's sure that nobody else is watching, Ralph gazes lustfully at your body, and you're pretty sure that you could convince him to give you another \"discount\" any time you wanted it."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on browsing the wares in Ralph's shop.", RalphsSnacks.INTERIOR);
			} else {
				return null;
			}
		}
	};
	
	private static int getLipstickPrice() {
		int price = 50000;
		
		price *= (100-Main.game.getDialogueFlags().ralphDiscount)/100f;
		
		return price;
	}
	
	public static final DialogueNode CANDI_LIPSTICK = new DialogueNode("Ralph's Snacks", "-", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().ralphDiscount), true);
			UtilText.addSpecialParsingString(Util.intToString(getLipstickPrice()), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<getLipstickPrice()) {
					return new Response("Pay "+UtilText.formatAsMoneyUncoloured(getLipstickPrice(), "span"), "You cannot afford to pay "+UtilText.formatAsMoney(getLipstickPrice(), "span")+" in exchange for the box of 'A Hundred Kisses'!", null);
				}
				return new Response("Pay "+UtilText.formatAsMoney(getLipstickPrice(), "span"),
						"Pay Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "span")+" in exchange for the box of 'A Hundred Kisses'.",
						CANDI_LIPSTICK_PURCHASE) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getLipstickPrice()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_HUNDRED_KISSES), false));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LIPSTICK));
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
					}
				};

			} else if (index == 2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ralphDailyBred)) {
					return new Response("Accept breeding", "Ralph is too busy running his shop to keep on trying to get you knocked up. Perhaps you should come back and try to get bred again tomorrow.", null);
					
				} else if(!Main.game.getPlayer().hasVagina()) {
					return new Response("Accept breeding", "As you don't have a vagina, there's no other option but to pay Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "span")+" for the box of 'A Hundred Kisses'.", null);
					
				} else if(Main.game.getPlayer().isPregnant()) {
					return new Response("Accept breeding", "As you're already pregnant, there's no other option but to pay Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "span")+" for the box of 'A Hundred Kisses'.", null);
					
				} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("Accept breeding", "As your womb is full of eggs, there's no other option but to pay Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "span")+" for the box of 'A Hundred Kisses'.", null);
					
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("Accept breeding", "As you're unable to get access to your vagina, there's no other option but to pay Ralph "+UtilText.formatAsMoney(getLipstickPrice(), "span")+" for the box of 'A Hundred Kisses'.", null);
				}
				return new ResponseSex("Accept breeding",
						"Tell Ralph that you'll let him breed you in exchange for the box of 'A Hundred Kisses'.",
						Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.OVER_DESK,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotDesk.BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_FRONT))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Ralph.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(character.isPlayer()) {
									return super.getCharacterOrgasmBehaviour(character);
								}
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_START_BREEDING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Ralph.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getNpc(Ralph.class), false);
						Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ralphDailyBred, true);
					}
				};

			} else if (index == 0) {
				return new Response("Decline", "Tell Ralph that you'll think about it...", BACK_TO_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_PURCHASE_DECLINE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_INTERIOR"));
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphAskedAboutHundredKisses);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CANDI_LIPSTICK_PURCHASE = new DialogueNode("Ralph's Snacks", "-", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().ralphDiscount), true);
			UtilText.addSpecialParsingString(Util.intToString(getLipstickPrice()), false);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "CANDI_LIPSTICK_PURCHASE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_BREEDING = new DialogueNode("Finished", "Now that your womb has been filled with his potent cum, Ralph has had enough.", true) {

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Main.game.getNpc(Ralph.class).useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false), true);
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant() && Objects.equals(Main.game.getPlayer().getPregnantLitter().getFather(), Main.game.getNpc(Ralph.class))) {
					return new Response("Success",
							"Not only are you now the proud owner of the limited edition box of 'A Hundred Kisses', but you've also become the mother of Ralph's "
									+(Main.game.getPlayer().getPregnantLitter().getTotalLitterCount()==1?"child":"children")+"!",
									BACK_TO_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_SUCCESS"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_HUNDRED_KISSES), false));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_LIPSTICK));
						}
					};
					
				} else {
					return new Response("Failure...",
							"Ralph didn't manage to get you pregnant, and as such, he's unwilling to hand over the box of 'A Hundred Kisses'...",
							BACK_TO_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/ralphsSnacks", "AFTER_BREEDING_FAILURE"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode BACK_TO_INTERIOR = new DialogueNode("Ralph's Snacks", "-", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return INTERIOR.getResponse(responseTab, index);
		}
	};
}
