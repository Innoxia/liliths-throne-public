package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SexManagerRalphDiscount;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.1.82
 * @author Innoxia
 */
public class RalphsSnacks {
	
	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Ralph's Snacks (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing before the only place in the Shopping Arcade that sells food to go."
						+ " From the outside, it looks like an old-fashioned sweet shop, with large glass windows displaying all manner of exotic-looking food and drink."
						+ " The words 'Ralph's Snacks' are painted in cursive gold lettering above the entrance, and a little sign reading 'Open 24/7!' hangs in the door's window."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step inside Ralph's Snacks.", INTERIOR){
					@Override
					public void effects() {
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTERIOR = new DialogueNodeOld("Ralph's Snacks", "-", true) {
		private static final long serialVersionUID = 1L;

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
						+ (Main.game.getDialogueFlags().ralphDiscountStartTime>0
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
					
				if(((Ralph)Main.game.getRalph()).isDiscountActive()){
					UtilText.nodeContentSB.append("<p>"
									+ "<b>Ralph is giving you a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>discount!</b>"
								+ "</p>");
				}
				
				return UtilText.nodeContentSB.toString();
				
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Ralph", "Go and ask Ralph about the special consumables on display.", Main.game.getRalph()){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
					}
				};
				
			} else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Discount", "Ask Ralph if there's anything you can do to get a discount.", INTERIOR_ASK_FOR_DISCOUNT){
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						}
					};
					
				} else {
					return new Response("Discount", "You can only get a discount from Ralph if he's able to gain access to your mouth!", null);
					
				}

			} else if (index == 0) {
				return new Response("Leave", "Leave Ralph's shop.", EXTERIOR){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld INTERIOR_ASK_FOR_DISCOUNT = new DialogueNodeOld("Ralph's Snacks", "-", true, true) {
		private static final long serialVersionUID = 1L;


		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().ralphDiscountStartTime>0){
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
					 	+ "You were so engrossed in your fantasy that you didn't notice Ralph walking up to you, and you're taken completely off-guard as you blurt out a reply, " 
						+ "[pc.speech(Oh! Yeah... Thanks! I was just thinking... Erm...)]"
					+ "</p>"
					+ "<p>"
						+ "You notice that his eyes have started to roam up and down your body, and you get the feeling that he knows exactly what you were imagining."
						+ " Glancing down, you see a distinctive bulge forming between his legs."
						+ " As you struggle to contain a happy gasp at the sight of his clear erection, he steps forwards, and you find yourself with your back to the wall as you feel his hot breath on your face once again." 
					+ "</p>"
					+ "<p>"
						+ "[ralph.speech(Want to have another taste?)] he asks, grinning deviously, "
						+ "[ralph.speech(Let me blow a load down that pretty little throat of yours, and I'll give you twenty-five percent off everything I have in stock for a few days.)]"
					+ "</p>"
					+ "<p>"
						+ "As he moves even closer, you struggle to get the image of his massive, throbbing black horse-cock out of your mind, and as the tent in his trousers comes into contact with your leg, you blurt out your reply."
					+ "</p>";
				
			}else{
				return "<p>"
							+ "You get the feeling that some of these items may be out of your price range."
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
							+ "You notice that his smile has suddenly lost its friendly appearance, and as his eyes roam up and down your body, you get the feeling that your arcane aura is giving him some dirty thoughts."
							+ " Glancing down, you see a distinctive bulge forming between his legs."
							+ " As your eyes open wide at the sight of his clear erection, he steps forwards, and you find yourself with your back to the wall as you feel his hot breath on your face." 
						+ "</p>"
						+ "<p>"
							+ "[ralph.speech(Like what you see?)] he asks, grinning deviously, "
							+ "[ralph.speech(Let me blow a load down that pretty little throat of yours, and I'll give you twenty-five percent off everything I have in stock for a few days.)]"
						+ "</p>"
						+ "<p>"
							+ "You gulp as he moves even closer, and as the tent in his trousers comes into contact with your leg, you blurt out your reply."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Agree", "Agree to do as Ralph says and suck his cock.", Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL_GIVING)),
						null, CorruptionLevel.TWO_HORNY, null, null, null,
						true, true,
						new SexManagerRalphDiscount(
								Util.newHashMapOfValues(new Value<>(Main.game.getRalph(), SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH))),
						Ralph.AFTER_SEX,
						"<p>"
							+ UtilText.parsePlayerSpeech("Ok, I'll do it,") + " you say, looking up at Ralph to see his smile grow even wider." + "</p>" + "<p>"
							+ "He leans in, and you half-expect him to try and kiss you, but instead, he simply grabs your " + Main.game.getPlayer().getArmNameSingular() + " and starts to drag you back to his desk."
							+ " As he walks, he starts instructing you on what's about to happen."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("You're going to kneel under my desk over here, and I don't expect to have to do any of the work, understood?", Main.game.getRalph())
							+ " he asks, and as you answer in the affirmative, he continues, "
							+ UtilText.parseSpeech("This is a respectable shop, so if anyone comes in, you're to keep quiet! For each customer that hears you, I'm going to knock five percent off our deal.", Main.game.getRalph())
						+ "</p>"
						+ "<p>"
							+ "By this time, Ralph's led you behind the shop's front desk, and you see that there's a hollow space beneath the counter-top, large enough for you to kneel inside quite comfortably."
							+ " The desk's solid front conceals you from the rest of the shop, and you realise that if you keep quiet, any customers will be completely oblivious as to what's going on."
							+ " Ralph places his hands on your shoulders, and, feeling that it's too late to back out now, you allow him to push you to your knees."
							+ " Shuffling back, you occupy the space under his desk, and Ralph steps forwards, bringing the massive bulge in his trousers right up to your face."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Make sure you give my balls some attention as well,", Main.game.getRalph()) + " you hear him command."
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
							+ "<b>There are</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
							+ " <b>You will earn a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>25%</b> <b>discount.</b>"
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getRalph().getPlayerKnowsAreas().add(CoverableArea.PENIS);
						if(Main.game.getRalph().getHighestZLayerCoverableArea(CoverableArea.PENIS)!=null) {
							Main.game.getRalph().isAbleToBeDisplaced(Main.game.getRalph().getHighestZLayerCoverableArea(CoverableArea.PENIS), DisplacementType.PULLS_DOWN, true, true, Main.game.getRalph());
							Main.game.getRalph().isAbleToBeDisplaced(Main.game.getRalph().getHighestZLayerCoverableArea(CoverableArea.PENIS), DisplacementType.SHIFTS_ASIDE, true, true, Main.game.getRalph());
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Refuse", "Tell him that you're not willing to do that.", INTERIOR_REFUSE_DISCOUNT_CONDITIONS);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld INTERIOR_REFUSE_DISCOUNT_CONDITIONS = new DialogueNodeOld("Ralph's Snacks", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().ralphDiscountStartTime>0){
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
							+ "[ralph.speech(Well, no harm in either of us asking!)] he laughs, turning around and heading back to his counter, "
							+ "[ralph.speech(If you need anything else, just let me know.)]" 
						+ "</p>"
						+ "<p>"
							+ "You go back to browsing the shop's wares, but you find it hard to shake the image of Ralph's massive bulge from your head."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseTrade("Trade with Ralph", "Go and ask Ralph about the special transformative consumables on display.", Main.game.getRalph()){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else if (index == 2) {
				return new Response("Discount", "Ask Ralph if there's anything you can do to get a discount.", INTERIOR_ASK_FOR_DISCOUNT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else if (index == 0) {
				return new Response("Leave", "Head back outside to the shopping arcade.", EXTERIOR){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.ralphIntroduced);
						// if 3 days have passed, reset discount:
						if((Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) >= (60*24*3)){
							Main.game.getDialogueFlags().ralphDiscount=0;
							Main.game.getRalph().setSellModifier(1.5f);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
}
