package com.base.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.body.BodyPartInterface;
import com.base.game.character.body.Hair;
import com.base.game.character.body.Eye;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.character.npc.dominion.Kate;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.responses.ResponseTrade;
import com.base.game.dialogue.utils.CharacterModificationUtils;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.managers.universal.consensual.SMChairTop;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.66
 * @version 0.1.83
 * @author Innoxia
 */
public class SuccubisSecrets {
	
	private static StringBuilder descriptionSB;
	
	
	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Succubi's Secrets (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().kateIntroduced) {
				return "<p>"
							+ "You find yourself standing, once again, in the quiet corner of the Shopping Arcade, where the dark, shut-up beauty salon 'Succubi's Secrets' is located."
							+ " You notice that everyone around you is completely ignoring the uninviting facade, and you smile to yourself as you realise that's exactly what its lazy owner wants."
							+ " Now that you're here, you wonder if you should pay Kate another visit..."
						+ "</p>";
				
			} else {
				return "<p>"
						+ "You find yourself standing in a quiet corner of the Shopping Arcade, and before you, there's a dark, shut-up store front."
						+ " Taking a closer look at this peculiarity in amongst all the other well-kept frontages, you notice some faded lettering above the doorway, which reads 'Succubi's Secrets'."
						+ " Curious, you walk towards the entrance, where you see that there's a little sign hanging in the door's narrow window which reads 'Open for business'."
					+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().kateIntroduced) {
					return new Response("Enter", "Step inside Succubi's Secrets.", SHOP_BEAUTY_SALON_ENTER);
					
				} else {
					return new Response("Enter", "Step inside Succubi's Secrets.", SHOP_BEAUTY_SALON);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Encouraged by the sign, you push open the door and step inside, somewhat apprehensive of what the interior will look like."
						+ " You're pleasantly surprised to find that it's actually the complete opposite of what you expected."
						+ " Clean, white marble flooring reflects the bright arcane illumination being cast from multiple delicate crystal wall-lights."
						+ " Luxurious leather chairs line the right-hand side of the room, and in front of each one stands a ceiling-height mirror."
						+ " All-in-all, the shop floor looks like an extremely up-market hairdressers, and a series of shelves, filled with all sorts of beauty products, runs alongside the left-hand wall,"
						+ " finishing the look and convincing you that you're in the right place."
					+ "</p>"
					+ "<p>"
					+ "As you stand in the doorway, taking in your new surroundings, you realise that the most striking feature of all is that the place is completely deserted."
					+ " Or, at least, that's how it appears at first glance."
					+ " As you look around for any sign of life, you notice that the furthest leather chair has been turned so that its back is to you."
					+ " A pair of slender, feminine legs, propped up on a low table, can just about be seen, and you decide to go and greet what must be this shop's owner."
					+ " Walking over to the other side of the room, you start to hear a faint snoring sound, and as you step around to one side of the chair, you see the source of the noise."
					+ "</p>"
					+ "<p>"
					+ " A stunningly beautiful woman is slumped down in the seat, her large E-cup breasts heaving up and down as she twitches and mumbles in her sleep."
					+ " Her skin is a light shade of pink, which, combined with her pair of low, swept-back horns, her little bat-like wings, and a cute spaded tail, mark her as a demon."
					+ " As you stand there, wondering what to do, she rolls over on one side, and as a thin stream of drool trickles down out the corner of her mouth, she whines, "
					+ UtilText.parseSpeech("Mmrph... A little deeper...",
							Main.game.getKate())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Wake her", "Wake the sleeping demon.", SHOP_BEAUTY_SALON_WAKE);
				
			} else if (index == 2) {
				return new Response("Watch", "Wait for the sleeping demon to wake up.", SHOP_BEAUTY_SALON_WATCH);

			} else if (index == 0) {
				return new Response("Back", "Head back out to the Shopping Promenade.", EXTERIOR);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_WAKE = new DialogueNodeOld("Succubi's Secrets", "-", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You decide that it'd probably be best to try and wake her up."
						+ " After all, you're not quite sure what she's dreaming about, and things could get out of control quite quickly."
						+ " You step to one side, clearing your throat before gently speaking down to her, "
						+ UtilText.parsePlayerSpeech("Excuse me?")
					+ "</p>"
					+ "<p>"
					+ "Her eyes instantly snap open, and as she lets out an annoyed huff, you realise that she was merely pretending to be asleep this whole time, "
					+ UtilText.parseSpeech("Am I really that rusty?! You didn't want to fuck me at all, not one little bit?"
							+ " Y'know, that's one downside of not having any customers. I haven't had sex in, like, three whole days! And then, when someone finally arrives, they don't even want to have any fun!",
							Main.game.getKate())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Sex", "You can't resist the horny succubus's request...", null,
						Main.game.getKate(), new SMChairTop(), Kate.AFTER_SEX,
							 "<p>"
							+ "As the horny demon finishes speaking, she sits up, spreading her legs and pulling up her skirt as she gives you a clear view of her spaded tail pushing deep into her hungry pussy."
							+ " You notice little vibrations running down her tail's length, and you realise that her demonic pussy is eagerly massaging and squeezing down on the intruding object."
							+ " She glances up at you with big, innocent eyes, and begs, "
							+ UtilText.parseSpeech("Pleeeease! It's just not the same when I have to do it myself...",
								Main.game.getKate())
							+ "</p>"
							+ "<p>"
							+ "She is, without doubt, one of the most attractive women you've ever seen."
							+ " Her face, with its full, plump lips, high cheekbones and immaculate skin, is framed by long, wavy locks of sleek black hair."
							+ " Her body is equally as impressive, and beneath her three pairs of "+Main.game.getKate().getBreastSize().getDescriptor()
							+" breasts, her wide hips and long, perfectly-formed legs provide the final visual stimulus that's needed to send you over the edge."
							+ "</p>"
							+ "<p>"
							+ "You step forwards, and as you do, the demon lets out a delighted squeal."
							+ " She spreads her legs even further apart, and you see her tail slide out of her warmed-up slit as she prepares herself for your entrance."
							+ " Leaning down, the scent of strawberries and sex overwhelms your senses, and as your lips press against hers, you feel her tail wrapping around one of your legs."
							+ "</p>"
							+ "<p>"
							+ "She briefly pushes you back for a moment, panting, "
							+ UtilText.parseSpeech("Oh, if you need a name to call out, it's Kate by the way.",
								Main.game.getKate())
							+ "</p>"
							+ "<p>"
							+ "With that, she reaches around and grabs the back of your head, pulling you into a desperate, passionate kiss as you press yourself down against her."
							+ "</p>");
				
			} else if (index == 2) {
				return new Response("No thanks", "Tell her that you're not the sort of person who just has sex with random shopkeepers.", SHOP_BEAUTY_SALON_NO_THANKS);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_WATCH = new DialogueNodeOld("Succubi's Secrets", "-", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You decide that there's no harm in watching her a little longer."
						+ " After all, she's fast asleep, and you really want to find out what she's dreaming about."
						+ " You don't have to wait long to discover the answer to your question, as it turns out that she's a very vocal sleep-talker."
					+ "</p>"
					+ "<p>"
					+ "She rolls around and lets out a little moan, "
					+ UtilText.parseSpeech("Aaah! Y-yeah... Give my little pussy a turn!",
							Main.game.getKate())
					+ "</p>"
					+ "<p>"
						+ "You see her tail snaking around beneath her, and as you watch, the spaded tip slips up beneath the fabric of her pink mini-skirt and thrusts forwards between her legs."
						+ " Your eyes widen as you watch it start to pump back and forth, and, not being able to resist, you tilt your head down to see that the horny demon isn't wearing any underwear."
						+ " Her spaded tail is buried deep in her drooling slit, and as it enthusiastically fucks its owner's eager cunt, she starts alternately squealing and sighing in delight. "
						+ UtilText.parseSpeech("Deeper... Aaah yeah, like that!",
								Main.game.getKate())
					+ "</p>"
					+ "<p>"
					+ "You can't take your eyes off of the lewd display before you, and you start to wonder how much longer she's going to keep this up."
					+ " Just as you're deciding on what to do next, the demon suddenly opens her eyes, letting out an annoyed little huff as she stares right at you, "
					+ UtilText.parseSpeech("How much longer do I have to do this for before you come over here and fuck me?! Y'know, that's one downside of not having any customers, I haven't had sex in, like, three whole days!",
							Main.game.getKate())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Fuck her", "Do as she says and start having sex with her.", null,
						Main.game.getKate(), new SMChairTop(), Kate.AFTER_SEX,
								"<p>"
								+ "As the horny demon finishes speaking, she sits up, spreading her legs and pulling up her skirt as she gives you a clear view of her spaded tail pushing deep into her hungry pussy."
								+ " You notice little vibrations running down her tail's length, and you realise that her demonic pussy is eagerly massaging and squeezing down on the intruding object."
								+ " She glances up at you with big, innocent eyes, and begs, "
								+ UtilText.parseSpeech("Pleeeease! It's just not the same when I have to do it myself...",
									Main.game.getKate())
								+ "</p>"
								+ "<p>"
								+ "She is, without doubt, one of the most attractive women you've ever seen."
								+ " Her face, with its full, plump lips, high cheekbones and immaculate skin, is framed by long, wavy locks of sleek black hair."
								+ " Her body is equally as impressive, and beneath her three pairs of "+Main.game.getKate().getBreastSize().getDescriptor()
								+" breasts, her wide hips and long, perfectly-formed legs provide the final visual stimulus that's needed to send you over the edge."
								+ "</p>"
								+ "<p>"
								+ "You step forwards, and as you do, the demon lets out a delighted squeal."
								+ " She spreads her legs even further apart, and you see her tail slide out of her warmed-up slit as she prepares herself for your entrance."
								+ " Leaning down, the scent of strawberries and sex overwhelms your senses, and as your lips press against hers, you feel her tail wrapping around one of your legs."
								+ "</p>"
								+ "<p>"
								+ "She briefly pushes you back for a moment, panting, "
								+ UtilText.parseSpeech("Oh, if you need a name to call out, it's Kate by the way.",
									Main.game.getKate())
								+ "</p>"
								+ "<p>"
								+ "With that, she reaches around and grabs the back of your head, pulling you into a desperate, passionate kiss as you press yourself down against her."
								+ "</p>");
				
			} else if (index == 2) {
				return new Response("No thanks", "Tell her that you're not the sort of person who just has sex with random shopkeepers.", SHOP_BEAUTY_SALON_NO_THANKS);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_NO_THANKS = new DialogueNodeOld("Succubi's Secrets", "-", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return ("<p>"
						+ "You're a little bit taken aback at how thirsty this horny demon is, and as she finishes speaking, she sits up,"
							+ " spreading her legs and pulling up her skirt as she gives you a clear view of her spaded tail pushing deep into her hungry pussy."
						+ " You notice little vibrations running down her tail's length, and you realise that her demonic pussy is eagerly massaging and squeezing down on the intruding object."
						+ " Before you're tempted to do something that you may regret, you turn your head and avert your eyes from the lewd display."
						+ "</p>"
						+"<p>"
						+ UtilText.parseSpeech("Pleeeease! It's just not the same when I have to do it myself...",
							Main.game.getKate())
						+ " you hear her beg, but you've already made up your mind."
						+ "</p>"
						+ "<p>"
						+ UtilText.parsePlayerSpeech("Can you please cover yourself up?! I though this was a beauty salon, not a brothel...")
						+" you say, your words coming out a little stronger than they might normally have done as you struggle to keep your lust under control."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Fine... Trust me to get the only prude in all of Dominion...",
							Main.game.getKate())
						+" she mumbles as you hear her covering herself back up."
						+ "</p>"
						+ "<p>"
						+ "You turn around to see that she's stood up, and as she flattens down her mini skirt and wipes her tail on a tissue she's produced from somewhere,"
						+ " you see that she is, without doubt, one of the most attractive women you've ever seen."
						+ " Her face, with its full, plump lips, high cheekbones and immaculate skin, is framed by long, wavy locks of sleek black hair."
						+ " Her body is equally as impressive, and beneath her three pairs of "+Main.game.getKate().getBreastSize().getDescriptor()
						+" breasts, her wide hips and long, perfectly-formed legs finish her look as a top-class super-model."
						+ "</p>"
						+ "<p>"
						+ "She seems to have got her own lust under control by now, and as she looks up, she seems rather ashamed of what she just did, "
						+ UtilText.parseSpeech("Ehhh, sorry about that... You know, it's pretty hard for us demons sometimes... Anyway! What are you even doing in here?"
								+ " Weren't you deterred by the boarded-up windows and stuff?",
							Main.game.getKate())
						+ "</p>"
						+ "<p>"
						+ UtilText.parsePlayerSpeech("So you're aware of how it appears to customers?")
						+ " you ask, wondering why she seems to actively keep the shop front looking so uninviting."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Well, yeah I'm aware! You know, the owners of this whole promenade keep threatening me with legal action, saying I have a 'responsibility' to keep the area looking nice."
								+ " As if! As long as I display an 'open for business' sign, I'm following all the terms of my contract! You know what happened when I opened this place?! Thirty. Six. Customers. All in one day. Eugh!"
								+ " As the last one of those demanding know-it-alls left, I followed them outside, boarded up the windows, and threw paint stripper all over the sign. One day's hard work is enough for anyone...",
								Main.game.getKate())
						+ "</p>"
						+ "<p>"
						+ "As she's been speaking, she's started gathering items from the shelves on the other side of the room, stacking them up on a little metal trolley that's been sitting nearby."
						+ " Looking back at you, she makes a satisfied little humming noise before making her way over to one of the leather chairs, pulling the trolley behind her."
						+ "</p>"
						+ "<p>"
						+ UtilText.parseSpeech("Well, I suppose I don't mind one customer every now and then. I could use the cash after all,",
							Main.game.getKate())
						+" she says, motioning for you to come and sit down, "
						+ UtilText.parseSpeech("Oh, and I'm Kate by the way. Now come over here and take a seat.",
							Main.game.getKate())
						+ "</p>"
						+ "<p>"
						+ "You do as she instructs, and as you sink down into the chair next to her, she hands you a little brochure of all the services she's capable of."
						+ " As you look through the little pamphlet, Kate sinks down onto one of the chairs next to you, and within a few seconds, her snores start to fill the empty shop once more..."
						+ "</p>");
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().kateIntroduced = true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_ENTER = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			
			if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
				return "<p>"
						+ "With the knowledge that appearances can be deceiving, you stride over to the door and push it open."
						+ " As a big flake of peeling paint breaks off and falls to the floor, you step inside, marvelling once again at how radically different the interior is."
						+ "</p>"
						+ "<p>"
						+ "Letting your vision wander down the line of modern, comfortable leather chairs, you spot Kate sleeping in her favourite spot."
						+ " You feel your eyes going wide as you see her [kate.hands] resting on her swollen belly, and you realise that [style.boldSex(you've ended up getting Kate pregnant!)]"
						+ "</p>"
						+ "<p>"
						+ "Despite trying your best to stay quiet, the echo of your footfalls on the clean marble floor causes her to wake from her slumber."
						+ " As you approach and sit down next to her, she opens one eye, smiling as she recognises you as the father of her children."
						+ " With a yawn, she slowly sits up, still cradling her round tummy as she grins at you."
						+ "</p>"
						+ "<p>"
						+ "[kate.speech(Hehe, look what you did!)] she laughs, [kate.speech(Mmm, it sure is nice being pregnant...)]"
						+ "</p>"
						+ "<p>"
						+ "She hands you a nearby brochure, and as you thank her, you can't help but feel quite taken aback at how casual she's acting about this."
						+ "</p>"
						+ "<p>"
						+ "[pc.speech(Is there anything I can get for you?)] you ask, unsure of quite how to react to Kate's blasé attitude, [pc.speech(You don't seem very concerned about being pregnant...)]"
						+ "</p>"
						+ "<p>"
						+ "[kate.speech(Aww that's sweet of you, but I'm fine thanks. I love being pregnant!)] she laughs again, [kate.speech(It gives me an excuse to just lie around all day. Anyway, just let me know if you need something.)]"
						+ "</p>"
						+ "<p>"
						+ "With that, she sinks back down into her chair, and before you can respond, her snores start to fill the empty shop once more..."
						+ "</p>"
						+ "<p>"
						+ "You find yourself sitting on a comfortable leather chair in the beauty salon 'Succubi's Secrets'."
						+ " The only other person to be seen is sitting in a chair right next to you, and takes the form of Kate; the shop's lazy, perpetually-horny, demonic owner."
						+ " She's currently fast asleep, and her snores are echoing around the empty shop floor."
						+ "</p>"
						+ "<p>"
						+ "You look through the brochure of all the services Kate's capable of providing."
						+ " Out of the corner of your eye, you see her tail snaking its way up beneath her skirt, and her snores start being punctuated by lewd moans..."
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "[style.boldBad(Customisation options are still being worked on! They will be improved for the next version!)]"
						+ "</p>";//TODO;
				
			} else {
				return "<p>"
						+ "You make your way over to the boarded-up beauty salon; 'Succubi's Secrets'."
						+ " You notice that everyone around you is completely ignoring the uninviting facade, and you smile to yourself as you realise that's exactly what its lazy owner wants."
						+ "</p>"
						+ "<p>"
						+ "With the knowledge that appearances can be deceiving, you stride over to the door and push it open."
						+ " As a big flake of peeling paint breaks off and falls to the floor, you step inside, marvelling once again at how radically different the interior is."
						+ "</p>"
						+ "<p>"
						+ "Letting your vision wander down the line of modern, comfortable leather chairs, you spot Kate sleeping in her favourite spot."
						+ " Despite trying your best to stay quiet, the echo of your footfalls on the clean marble floor causes her to wake from her slumber."
						+ " As you sit down next to her, she opens one eye, smiling as she recognises you."
						+ " With a yawn, she hands you a nearby brochure."
						+ " As you thank her, she mutters something about being happy to see you again, but before you can respond, her snores start to fill the empty shop once more..."
						+ "</p>"
						+ "<p>"
						+ "You find yourself sitting on a comfortable leather chair in the beauty salon 'Succubi's Secrets'."
						+ " The only other person to be seen is sitting in a chair right next to you, and takes the form of Kate; the shop's lazy, perpetually-horny, demonic owner."
						+ " She's currently fast asleep, and her snores are echoing around the empty shop floor."
						+ "</p>"
						+ "<p>"
						+ "You look through the brochure of all the services Kate's capable of providing."
						+ " Out of the corner of your eye, you see her tail snaking its way up beneath her skirt, and her snores start being punctuated by lewd moans..."
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "[style.boldBad(Customisation options are still being worked on! They will be improved for the next version!)]"
						+ "</p>";//TODO;
			}
		}
		
		@Override
		public Response getResponse(int index) {
			return SHOP_BEAUTY_SALON_MAIN.getResponse(index);
		}
	};
	
	private static List<BodyCoveringType> skins;
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_MAIN = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
						+ "You find yourself sitting on a comfortable leather chair in the beauty salon 'Succubi's Secrets'."
						+ " The only other person to be seen is sitting in a chair right next to you, and takes the form of Kate; the shop's lazy, perpetually-horny, demonic owner."
						+ " She's currently fast asleep, and her snores are echoing around the empty shop floor."
						+ "</p>"
						+ "<p>"
						+ "You look through the brochure of all the services Kate's capable of providing."
						+ " Out of the corner of your eye, you see her tail snaking its way up beneath her skirt, and her snores start being punctuated by lewd moans..."
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "[style.boldBad(Customisation options are still being worked on! They will be improved for the next version!)]"
						+ "</p>");//TODO
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if(index == 1){
				return new ResponseTrade("Trade with Kate", "There's a separate leaflet tucked into the back of the brochure. It informs you that Kate is a registered distributor for a large jewellery firm.", Main.game.getKate()){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()>=50) {
					return new Response("Makeup (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 50)",
							"Kate offers a wide range of different cosmetic services.", SHOP_BEAUTY_SALON_COSMETICS){
						@Override
						public void effects() {
							if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
								Main.game.getDialogueFlags().reactedToKatePregnancy = true;
							}
							Main.game.getPlayer().incrementMoney(-50);
						}
					};
				} else {
					return new Response("Piercings ("+ Main.game.getCurrencySymbol() + " 50)", "You don't have enough money!", null);
				}

			} else if (index == 3) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Hair (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 100)",
							"There's a double-page spread of all the different dyes, styles, and lengths of hair that Kate's able to work with.", SHOP_BEAUTY_SALON_HAIR){
						@Override
						public void effects() {
							if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
								Main.game.getDialogueFlags().reactedToKatePregnancy = true;
							}
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Hair ("+ Main.game.getCurrencySymbol() + " 100)", "You don't have enough money!", null);
				}

			} else if (index == 4) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Piercings (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 100)",
							"Kate offers a wide range of different piercings.", SHOP_BEAUTY_SALON_PIERCINGS){
						@Override
						public void effects() {
							if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
								Main.game.getDialogueFlags().reactedToKatePregnancy = true;
							}
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Piercings ("+ Main.game.getCurrencySymbol() + " 100)", "You don't have enough money!", null);
				}

			} else if (index == 5) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Irises (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 100)",
							"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
							+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_IRISES){
						@Override
						public void effects() {
							if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
								Main.game.getDialogueFlags().reactedToKatePregnancy = true;
							}
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Irises ("+ Main.game.getCurrencySymbol() + " 100)", "You don't have enough money!", null);
				}

			} else if (index == 6) {
				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("Pupils (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 100)",
							"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
							+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_PUPILS){
						@Override
						public void effects() {
							if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
								Main.game.getDialogueFlags().reactedToKatePregnancy = true;
							}
							Main.game.getPlayer().incrementMoney(-100);
						}
					};
				} else {
					return new Response("Pupils ("+ Main.game.getCurrencySymbol() + " 100)", "You don't have enough money!", null);
				}

			} else if (index == 7) {
				if(Main.game.getPlayer().getMoney()>=500) {
				return new Response("Coverings (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 500)",
						"There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
						+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_SKIN_COLOUR){
					@Override
					public void effects() {
						skins = new ArrayList<>();
						for(BodyPartInterface bp : Main.game.getPlayer().getAllBodyParts()){
							if(bp.getType().getBodyCoveringType()!=null
									&& bp.getType().getBodyCoveringType().getRace()!=null
									&& !skins.contains(bp.getType().getBodyCoveringType())
									&& !(bp instanceof Hair)
									&& !(bp instanceof Eye))
								skins.add(bp.getType().getBodyCoveringType());
						}
						skins.add(BodyCoveringType.ANUS);
						skins.add(BodyCoveringType.MOUTH);
						skins.add(BodyCoveringType.NIPPLES);
						skins.add(BodyCoveringType.TONGUE);
						if(Main.game.isBodyHairEnabled() || Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled()) {
							skins.add(Main.game.getPlayer().getUnderarmHairType().getType());
						}

						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy) {
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
						}
						Main.game.getPlayer().incrementMoney(-500);
					}
				};
				} else {
					return new Response("Coverings ("+ Main.game.getCurrencySymbol() + " 500)", "You don't have enough money!", null);
				}

			} else if (index == 8) {
				return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
						+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...</br>"
						+ "<b>Will be done as soon as possible!</b>", null);

			} else if (index == 9) {
				return new ResponseSex("Sex", "You roll your eyes as you reach the end of the brochure. On a double-page spread, there's an extremely lewd collection of pictures of Kate inserting her tail into her various orifices,"
						+ " with the suggestive caption 'Don't make me do it myself...'", null,
						Main.game.getKate(), new SMChairTop(), Kate.AFTER_SEX_REPEATED,
								"<p>"
								+ "Turning to the back of the brochure, you find a double-page spread that's filled with extremely explicit pictures of Kate inserting her tail into her various orifices."
								+ " Looking over at the sleeping demon next to you, the words of the caption 'Don't make me do it myself...' echo through your mind."
								+ "</p>"
								+ "<p>"
								+ "Not being able to resist, you put the brochure down and stand up, and in one quick movement, you're standing in front of the snoring form of Kate."
								+ " Before you know what you're doing, you're leaning down into her face."
								+ " The soft, hot breath of her relaxed snores gently blows against your lips, and with a little sigh, you pull her into a kiss."
								+ "</p>"
								+ "<p>"
								+ "Woken by your bold move, the demon slowly opens her eyes, before letting out a delighted squeal as she discovers what you're doing."
								+ " You feel her spreading her legs beneath you, and her tail bumps sleepily against your thighs as she uses it to pull up her mini-skirt, preparing herself for your entrance."
								+ "</p>"
								+ "<p>"
								+ "Breaking off the kiss for a moment, she slurs, "
								+ UtilText.parseSpeech("Mmm... Now this's a good dream!",
										Main.game.getKate())
								+"</p>"
								+ "<p>"
								+ "With that, she reaches around and grabs the back of your head, pulling you into a desperate, passionate kiss as you press yourself down against her."
								+ "</p>"){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};
				
			} else if (index == 0) {
				return new Response("Leave", "Leave Kate's shop, heading back out into the shopping promenade.", EXTERIOR){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_HAIR = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append(
					"<p style='text-align:center;'><b>Length</b></br>"
							+ CharacterModificationUtils.getHairLengthOption()
						+ "</p>"
						+ "<p style='text-align:center;'><b>Style</b></br>"
							+ CharacterModificationUtils.getHairStyleOption()
						+ "</p>"
						+ "<p style='text-align:center;'><b>Colour</b></br>"
							+ CharacterModificationUtils.getAllPrimaryCoveringOptions(Main.game.getPlayer().getHairCovering().getType())
							+ "</br><b>Glow:</b></br>"
							+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getHairCovering().isPrimaryGlowing(), false)
						+ "</p>"
						+ "<p style='text-align:center;'><b>Pattern</b></br>"
							+ CharacterModificationUtils.getAllPatternOptions(Main.game.getPlayer().getHairCovering().getType())
						+ "</p>"
						+ (Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering().getType()).getPattern()!=CoveringPattern.NONE
							?"<p style='text-align:center;'>[style.boldDisabled(Secondary colour)]</br>"
							:"<p style='text-align:center;'><b>Secondary colour</b></br>")
							+ CharacterModificationUtils.getAllSecondaryCoveringOptions(
									Main.game.getPlayer().getHairCovering().getType(),
									Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering().getType()).getPattern()==CoveringPattern.NONE)
							+ "</br><b>Glow:</b></br>"
							+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getHairCovering().isSecondaryGlowing(),
									Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering().getType()).getPattern()==CoveringPattern.NONE)
						+ "</p>");

			descriptionSB.append("<p>"
									+ "There's a large section near the front of the brochure which shows all the different hair colours that Kate has on offer."
									+ " It appears as though there's a separate page for each race, but the only one that applies to you is the page for ");
			
			if(Main.game.getPlayer().isFeminine()) {
				descriptionSB.append(Main.game.getPlayer().getHairType().getRace().getPluralFemaleName()+"."
									+ "</p>"
									+ "<p>"
									+ "A stunning "+Main.game.getPlayer().getHairType().getRace().getSingularFemaleName()+" smiles at you from the centre of the page,"
											+ " and all around the edge are pictures of her sporting different colours of "+Main.game.getPlayer().getHairName()+"."
											+ " You find yourself oohing and aahing as you imagine yourself in her place, showing off your beautiful new "+Main.game.getPlayer().getHairName()+"."
									+ "</p>");
				
			} else {
				descriptionSB.append(Main.game.getPlayer().getHairType().getRace().getPluralMaleName()+"."
						+ "</p>"
						+ "<p>"
						+ "A handsome "+Main.game.getPlayer().getHairType().getRace().getSingularMaleName()+" smiles at you from the centre of the page,"
								+ " and all around the edge are pictures of him sporting different colours of "+Main.game.getPlayer().getHairName()+"."
								+ " You find yourself humming and wondering if you should get your "+Main.game.getPlayer().getHairName()+" colour changed."
						+ "</p>");
			}
			
			descriptionSB.append(
					"<p>"
						+ " A picture of a surprisingly energetic Kate is located in the bottom-right of the double-page spread."
						+ " She's holding her arms out to one side, drawing attention to a series of pictures, each showing a different length of hair."
						+ " A little speech bubble, drawn coming out of her mouth, reads; "
						+ UtilText.parseSpeech("Just let me know how long you want it!", Main.game.getKate())
						+ "</br>Just beneath this, a small paragraph informs you that Kate uses a special arcane power to change her client's hair colouring."
						+ " It seems to be trying to emphasise the fact that this isn't a temporary dye, and will permanently change your natural colouring."
					+ "</p>");
				
			return descriptionSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} if (index == 1) {
				return new Response("[pc.Hair] length", "Cycle [pc.hair] length.", SHOP_BEAUTY_SALON_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementHairLength();
					}
				};
				
			} else if (index == 2) {
				return new Response("[pc.Hair] style", "Cycle your [pc.hair] style.", SHOP_BEAUTY_SALON_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementHairStyle(Main.game.getPlayer().getHairStyle());
					}
				};
				
			} if (index == 3) {
				return new Response("[pc.Hair] colour", "Cycle [pc.hair] colour.", SHOP_BEAUTY_SALON_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(Main.game.getPlayer().getHairCovering().getType());
					}
				};
				
			} else if (index == 4) {
				return new Response("Primary colour glow", "Cycle whether your primary [pc.hair] colour is glowing.", SHOP_BEAUTY_SALON_HAIR) {
					@Override
					public void effects() {
						Main.game.getPlayer().getHairCovering().setPrimaryGlowing(!Main.game.getPlayer().getHairCovering().isPrimaryGlowing());
					}
				};
				
			} else if (index == 5) {
				return new Response("[pc.Hair] pattern", "Cycle [pc.hair] pattern.", SHOP_BEAUTY_SALON_HAIR) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementPatternFromAll(Main.game.getPlayer().getHairCovering().getType());
					}
				};
				
			} else if (index == 6) {
				if(Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering().getType()).getPattern() != CoveringPattern.NONE) {
					return new Response("Secondary [pc.hair] colour", "Cycle the secondary colour of your [pc.hair].", SHOP_BEAUTY_SALON_HAIR) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAllSecondaryCovering(Main.game.getPlayer().getHairCovering().getType());
						}
					};
				} else {
					return new Response("Secondary [pc.hair] colour", "You can only choose your second [pc.hair] colour if you have an appropriate pattern.", null);
				}
				
			} else if (index == 7) {
				if(Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering().getType()).getPattern() != CoveringPattern.NONE) {
					return new Response("Secondary colour glow", "Cycle whether your secondary [pc.hair] colour is glowing.", SHOP_BEAUTY_SALON_HAIR) {
						@Override
						public void effects() {
							Main.game.getPlayer().getHairCovering().setSecondaryGlowing(!Main.game.getPlayer().getHairCovering().isSecondaryGlowing());
						}
					};
				} else {
					return new Response("Secondary colour glow", "You can only choose your secondary colour glow if you have an appropriate pattern.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_SKIN_COLOUR = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			descriptionSB.append("<p>"
									+ "In the middle of the brochure, there's a double-page spread advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
									+ " Your eyes widen as you see how much she charges for it, but as you read a paragraph describing how it's extremely demanding on her aura, you understand the high price."
								+ "</p>");
			
			descriptionSB.append("<h6 style='text-align:center;'>"
				+ "Your current body coverings"
			+"</h6>"
			+ "<p style='text-align:center;'>");
			
			//TODO
			int i = 1;
			for(BodyCoveringType st : skins){
				descriptionSB.append("<b>"+i+".</b> "+Main.game.getPlayer().getCovering(st).getFullDescription(Main.game.getPlayer(), true)+ "</br>");
				i++;
			}
			
			descriptionSB.append("</p>"
				+ "<p style='text-align:center;'>"
				+ "All skin and fur recolouring costs <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>200</b>."
				+ "</p>"
				+ "<p style='text-align:center;'>"
				+ "Choose a skin or fur type to see the different colours available."
				+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else if (index-1 < skins.size()) {
				return new Response(Util.capitaliseSentence(skins.get(index-1).getName(Main.game.getPlayer())) + " ("+Util.capitaliseSentence(skins.get(index-1).getRace().getName())+")",
						"Take a look at the different colours you can get your "+skins.get(index-1).getName(Main.game.getPlayer())+" changed to.", SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().skinTypeSelected = skins.get(index-1);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			BodyCoveringType st = Main.game.getDialogueFlags().skinTypeSelected;
			
			return "<p>"
						+ "Turning the page to where all the different colours of "+Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+" are displayed, you wonder if you should wake Kate and ask her for her services..."
					+ "</p>"
					+ "<h6 style='text-align:center;'>"
						+Util.capitaliseSentence(Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer()))+" colours"
					+"</h6>"
					+ "<p style='text-align:center;'><b>"+Util.capitaliseSentence(st.getName(Main.game.getPlayer()))+" colour</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(st)
						+ "</br><b>Glow:</b></br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getCovering(st).isPrimaryGlowing(), false)
					+ "</p>"
					+ "<p style='text-align:center;'><b>"+Util.capitaliseSentence(st.getName(Main.game.getPlayer()))+" pattern</b></br>"
						+ CharacterModificationUtils.getAllPatternOptions(st)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Secondary "+st.getName(Main.game.getPlayer())+" colour</b></br>"
						+ CharacterModificationUtils.getAllSecondaryCoveringOptions(st, Main.game.getDialogueFlags().skinTypeSelected.getAllSecondaryColours().isEmpty())
						+ "</br><b>Glow:</b></br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getCovering(st).isSecondaryGlowing(), Main.game.getDialogueFlags().skinTypeSelected.getAllSecondaryColours().isEmpty())
					+ "</p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous page.", SHOP_BEAUTY_SALON_SKIN_COLOUR);
				
			} else if (index == 1) {
				return new Response("Primary colour", "Cycle primary colour.", SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(Main.game.getDialogueFlags().skinTypeSelected);
					}
				};
				
			} else if (index == 2) {
				return new Response("Primary glow", "Cycle whether your primary colour is glowing.", SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES) {
					@Override
					public void effects() {
						Main.game.getPlayer().getCovering(Main.game.getDialogueFlags().skinTypeSelected).setPrimaryGlowing(!Main.game.getPlayer().getCovering(Main.game.getDialogueFlags().skinTypeSelected).isPrimaryGlowing());
					}
				};
				
			} else if (index == 3) {
				return new Response("Pattern", "Cycle covering pattern.", SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementPatternFromAll(Main.game.getDialogueFlags().skinTypeSelected);
					}
				};
				
			} else if (index == 4) {
				if(!Main.game.getDialogueFlags().skinTypeSelected.getAllSecondaryColours().isEmpty()) {
					return new Response("Secondary colour", "Cycle the secondary colour.", SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAllSecondaryCovering(Main.game.getDialogueFlags().skinTypeSelected);
						}
					};
				} else {
					return new Response("Secondary colour", "There are no secondary colour options.", null);
				}
				
			} else if (index == 5) {
				if(!Main.game.getDialogueFlags().skinTypeSelected.getAllSecondaryColours().isEmpty()) {
					return new Response("Secondary glow", "Cycle whether your secondary colour is glowing.", SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES) {
						@Override
						public void effects() {
							Main.game.getPlayer().getCovering(Main.game.getDialogueFlags().skinTypeSelected).setSecondaryGlowing(
									!Main.game.getPlayer().getCovering(Main.game.getDialogueFlags().skinTypeSelected).isSecondaryGlowing());
						}
					};
				} else {
					return new Response("Secondary colour", "There are no secondary colour options.", null);
				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld SHOP_BEAUTY_SALON_IRISES = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			BodyCoveringType eyeType = Main.game.getPlayer().getEyeType().getBodyCoveringType();
			
			return "<p>"
						+ "There's a page near the front of the brochure which displays all the different eye colourings that Kate has on offer."
						+ " There's separate section for each race, but the only one that applies to you is the page for [pc.eyeRaces]."
					+ "</p>"
					+ "<p style='text-align:center;'><b>Iris colour</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(eyeType)
						+ "</br><b>Glow:</b></br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).isPrimaryGlowing(), false)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Iris pattern</b></br>"
						+ CharacterModificationUtils.getAllPatternOptions(eyeType)
					+ "</p>"
					+ (Main.game.getPlayer().getCovering(eyeType).getPattern()!=CoveringPattern.EYE_IRISES_HETEROCHROMATIC
						?"<p style='text-align:center;'>[style.boldDisabled(Heterochromatic iris colour)]</br>"
						:"<p style='text-align:center;'><b>Heterochromatic iris colour</b></br>")
						+ CharacterModificationUtils.getAllSecondaryCoveringOptions(eyeType, Main.game.getPlayer().getCovering(eyeType).getPattern()!=CoveringPattern.EYE_IRISES_HETEROCHROMATIC)
						+ "</br><b>Glow:</b></br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).isSecondaryGlowing(),
								Main.game.getPlayer().getCovering(eyeType).getPattern()!=CoveringPattern.EYE_IRISES_HETEROCHROMATIC)
					+ "</p>"
					
					+ "<p>"
						+ "A small paragraph at the bottom of the page informs you that Kate uses a special arcane power to change your eye colour."
					+ "</p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else if (index == 1) {
				return new Response("Iris colour", "Cycle iris colour.", SHOP_BEAUTY_SALON_IRISES) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType());
					}
				};
				
			} else if (index == 2) {
				return new Response("Primary iris glow", "Cycle whether your primary iris colour is glowing.", SHOP_BEAUTY_SALON_IRISES) {
					@Override
					public void effects() {
						Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).setPrimaryGlowing(!Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).isPrimaryGlowing());
					}
				};
				
			} else if (index == 3) {
				return new Response("Iris pattern", "Cycle iris pattern.", SHOP_BEAUTY_SALON_IRISES) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementPatternFromAll(Main.game.getPlayer().getEyeType().getBodyCoveringType());
					}
				};
				
			} else if (index == 4) {
				if(Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
					return new Response("Second iris colour", "Cycle the colour of your other iris.", SHOP_BEAUTY_SALON_IRISES) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAllSecondaryCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType());
						}
					};
				} else {
					return new Response("Second iris colour", "You can only choose your second iris colour if you are heterochromatic.", null);
				}
				
			} else if (index == 5) {
				if(Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).getPattern() == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
					return new Response("Secondary iris glow", "Cycle whether your secondary iris colour is glowing.", SHOP_BEAUTY_SALON_IRISES) {
						@Override
						public void effects() {
							Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).setSecondaryGlowing(
									!Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).isSecondaryGlowing());
						}
					};
				} else {
					return new Response("Secondary iris glow", "You can only cycle your secondary iris glow if you are heterochromatic.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_PUPILS = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			BodyCoveringType pupils = BodyCoveringType.EYE_PUPILS;
			
			return "<p>"
						+ "There's a page near the front of the brochure which displays all the different eye colourings that Kate has on offer."
						+ " There's separate section for each race, but the only one that applies to you is the page for [pc.eyeRaces]."
					+ "</p>"
						
					+ "<p style='text-align:center;'><b>Pupil colour</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(pupils)
						+ "</br><b>Glow:</b></br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).isPrimaryGlowing(), false)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Pupil pattern</b></br>"
						+ CharacterModificationUtils.getAllPatternOptions(pupils)
					+ "</p>"
					+ (Main.game.getPlayer().getCovering(pupils).getPattern()!=CoveringPattern.EYE_PUPILS_HETEROCHROMATIC
						?"<p style='text-align:center;'>[style.boldDisabled(Heterochromatic pupil colour)]</br>"
						:"<p style='text-align:center;'><b>Heterochromatic pupil colour</b></br>")
						+ CharacterModificationUtils.getAllSecondaryCoveringOptions(pupils, Main.game.getPlayer().getCovering(pupils).getPattern()!=CoveringPattern.EYE_PUPILS_HETEROCHROMATIC)
						+ "</br><b>Glow:</b></br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getCovering(Main.game.getPlayer().getEyeType().getBodyCoveringType()).isSecondaryGlowing(),
								Main.game.getPlayer().getCovering(pupils).getPattern()!=CoveringPattern.EYE_PUPILS_HETEROCHROMATIC)
					+ "</p>"
					
					+ "<p>"
						+ "A small paragraph at the bottom of the page informs you that Kate uses a special arcane power to change your eye colour."
					+ "</p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else if (index == 1) {
				return new Response("Pupil colour", "Cycle pupil colour.", SHOP_BEAUTY_SALON_PUPILS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.EYE_PUPILS);
					}
				};
				
			} else if (index == 2) {
				return new Response("Primary pupil glow", "Cycle whether your primary pupil colour is glowing.", SHOP_BEAUTY_SALON_PUPILS) {
					@Override
					public void effects() {
						Main.game.getPlayer().getCovering(BodyCoveringType.EYE_PUPILS).setPrimaryGlowing(!Main.game.getPlayer().getCovering(BodyCoveringType.EYE_PUPILS).isPrimaryGlowing());
					}
				};
				
			} else if (index == 3) {
				return new Response("Pupil pattern", "Cycle pupil pattern.", SHOP_BEAUTY_SALON_PUPILS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementPatternFromAll(BodyCoveringType.EYE_PUPILS);
					}
				};
				
			} else if (index == 4) {
				if(Main.game.getPlayer().getCovering(BodyCoveringType.EYE_PUPILS).getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
					return new Response("Second pupil colour", "Cycle the colour of your other pupil.", SHOP_BEAUTY_SALON_PUPILS) {
						@Override
						public void effects() {
							CharacterModificationUtils.incrementAllSecondaryCovering(BodyCoveringType.EYE_PUPILS);
						}
					};
				} else {
					return new Response("Second pupil colour", "You can only choose your second pupil colour if you are heterochromatic.", null);
				}
				
			} else if (index == 5) {
				if(Main.game.getPlayer().getCovering(BodyCoveringType.EYE_PUPILS).getPattern() == CoveringPattern.EYE_PUPILS_HETEROCHROMATIC) {
					return new Response("Secondary pupil glow", "Cycle whether your secondary pupil colour is glowing.", SHOP_BEAUTY_SALON_PUPILS) {
						@Override
						public void effects() {
							Main.game.getPlayer().getCovering(BodyCoveringType.EYE_PUPILS).setSecondaryGlowing(
									!Main.game.getPlayer().getCovering(BodyCoveringType.EYE_PUPILS).isSecondaryGlowing());
						}
					};
				} else {
					return new Response("Secondary pupil glow", "You can only cycle your secondary pupil glow if you are heterochromatic.", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_PIERCINGS = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p style='text-align:center;'><b>Ear piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedEar())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Nose piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedNose())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Lip piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedLip())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Navel piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedNavel())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Tongue piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedTongue())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Nipple piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedNipple())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Penis piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedPenis())
								+ "</p>"
								+ "<p style='text-align:center;'><b>Vagina piercing</b></br>"
									+ CharacterModificationUtils.getPiercingsOptions(Main.game.getPlayer().isPiercedVagina())
								+ "</p>"
								+"<p>"
									+ "You flip back to the piercings section of the brochure."
									+ " There's a very professional-looking picture of Kate on one side of the double-page spread, bending down over a faceless client as she pierces their navel."
									+ " On the opposite side of the page, there's a table of all the available piercings that Kate's able to provide."
								+ "</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Ear Piercing", "Cycle ear piercing.", SHOP_BEAUTY_SALON_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedEar(!Main.game.getPlayer().isPiercedEar());
					}
				};
				
			} else if (index == 2) {
				return new Response("Nose Piercing", "Cycle nose piercing.", SHOP_BEAUTY_SALON_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedNose(!Main.game.getPlayer().isPiercedNose());
					}
				};
				
			} else if (index == 3) {
				return new Response("Lip Piercing", "Cycle lip piercing.", SHOP_BEAUTY_SALON_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedLip(!Main.game.getPlayer().isPiercedLip());
					}
				};
				
			} else if (index == 4) {
				return new Response("Navel Piercing", "Cycle navel piercing.", SHOP_BEAUTY_SALON_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedNavel(!Main.game.getPlayer().isPiercedNavel());
					}
				};
				
			} else if (index == 5) {
				return new Response("Tongue Piercing", "Cycle tongue piercing.", SHOP_BEAUTY_SALON_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedTongue(!Main.game.getPlayer().isPiercedTongue());
					}
				};
				
			} else if (index == 6) {
				return new Response("Nipple Piercings", "Cycle nipple piercings.", SHOP_BEAUTY_SALON_PIERCINGS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setPiercedNipples(!Main.game.getPlayer().isPiercedNipple());
					}
				};
				
			} else if (index == 7) {
				if(Main.game.getPlayer().hasPenis()) {
					return new Response("Penis Piercing", "Cycle penis piercings.", SHOP_BEAUTY_SALON_PIERCINGS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setPiercedPenis(!Main.game.getPlayer().isPiercedPenis());
						}
					};
				} else {
					return new Response("Penis Piercing", "You don't have a penis to pierce.", null);
				}
				
			} else if (index == 8) {
				if(Main.game.getPlayer().hasVagina()) {
					return new Response("Vagina Piercing", "Cycle vagina piercings.", SHOP_BEAUTY_SALON_PIERCINGS) {
						@Override
						public void effects() {
							Main.game.getPlayer().setPiercedVagina(!Main.game.getPlayer().isPiercedVagina());
						}
					};
				} else {
					return new Response("Vagina Piercing", "You don't have a vagina to pierce.", null);
				}
				
			} else if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_COSMETICS = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Cosmetics";
		}

		@Override
		public String getHeaderContent() {
			return "<p style='text-align:center;'><b>Blusher</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_BLUSHER)
						+ "</br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getBlusher().isPrimaryGlowing(), Main.game.getPlayer().getBlusher().getPrimaryColour()!=Colour.COVERING_NONE)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Lipstick</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_LIPSTICK)
						+ "</br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getLipstick().isPrimaryGlowing(), Main.game.getPlayer().getLipstick().getPrimaryColour()!=Colour.COVERING_NONE)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eyeliner</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_EYE_LINER)
						+ "</br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getEyeLiner().isPrimaryGlowing(), Main.game.getPlayer().getEyeLiner().getPrimaryColour()!=Colour.COVERING_NONE)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Eyeshadow</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_EYE_SHADOW)
						+ "</br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getEyeShadow().isPrimaryGlowing(), Main.game.getPlayer().getEyeShadow().getPrimaryColour()!=Colour.COVERING_NONE)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Nail polish</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS)
						+ "</br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getHandNailPolish().isPrimaryGlowing(), Main.game.getPlayer().getHandNailPolish().getPrimaryColour()!=Colour.COVERING_NONE)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Toenail polish</b></br>"
						+ CharacterModificationUtils.getAllPrimaryCoveringOptions(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET)
						+ "</br>"
						+ CharacterModificationUtils.getGlowOptions(Main.game.getPlayer().getFootNailPolish().isPrimaryGlowing(), Main.game.getPlayer().getFootNailPolish().getPrimaryColour()!=Colour.COVERING_NONE)
					+ "</p>"
					+ "<p style='text-align:center;'><b>Anal Bleaching</b></br>"
						+ CharacterModificationUtils.getAnusBleachedOption()
					+ "</p>";
			
			
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Blusher colour", "Cycle blusher colour.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_BLUSHER);
					}
				};
				
			} if (index == 2) {
				if(Main.game.getPlayer().getBlusher().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Blusher glow", "Cycle blusher glow.", SHOP_BEAUTY_SALON_COSMETICS) {
						@Override
						public void effects() {
							Main.game.getPlayer().getBlusher().setPrimaryGlowing(!Main.game.getPlayer().getBlusher().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Blusher glow", "You can't make your blusher glow if you aren't wearing any!", null);
				}
				
			} if (index == 3) {
				return new Response("Lipstick colour", "Cycle lipstick colour.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				};
				
			} if (index == 4) {
				if(Main.game.getPlayer().getLipstick().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Lipstick glow", "Cycle lipstick glow.", SHOP_BEAUTY_SALON_COSMETICS) {
						@Override
						public void effects() {
							Main.game.getPlayer().getLipstick().setPrimaryGlowing(!Main.game.getPlayer().getLipstick().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Lipstick glow", "You can't make your lipstick glow if you aren't wearing any!", null);
				}
				
			} if (index == 5) {
				return new Response("Eyeliner colour", "Cycle eyeliner colour.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_EYE_LINER);
					}
				};
				
			} if (index == 6) {
				if(Main.game.getPlayer().getEyeLiner().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Eyeliner glow", "Cycle eyeliner glow.", SHOP_BEAUTY_SALON_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getEyeLiner().setPrimaryGlowing(!Main.game.getPlayer().getEyeLiner().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Eyeliner glow", "You can't make your eyeliner glow if you aren't wearing any!", null);
				}
				
			} if (index == 7) {
				return new Response("Eyeshadow colour", "Cycle eyeshadow colour.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_EYE_SHADOW);
					}
				};
				
			} if (index == 8) {
				if(Main.game.getPlayer().getEyeShadow().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Eyeshadow glow", "Cycle eyeshadow glow.", SHOP_BEAUTY_SALON_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getEyeShadow().setPrimaryGlowing(!Main.game.getPlayer().getEyeShadow().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Eyeshadow glow", "You can't make your eyeshadow glow if you aren't wearing any!", null);
				}
				
			} if (index == 9) {
				return new Response("Nail polish colour", "Cycle nail polish colour.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS);
					}
				};
				
			} if (index == 10) {
				if(Main.game.getPlayer().getHandNailPolish().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Nail polish glow", "Cycle nail polish glow.", SHOP_BEAUTY_SALON_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getHandNailPolish().setPrimaryGlowing(!Main.game.getPlayer().getHandNailPolish().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Nail polish glow", "You can't make your nail polish glow if you aren't wearing any!", null);
				}
				
			} if (index == 11) {
				return new Response("Toenail polish colour", "Cycle toenail polish colour.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						CharacterModificationUtils.incrementAllPrimaryCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET);
					}
				};
				
			} if (index == 12) {
				if(Main.game.getPlayer().getFootNailPolish().getPrimaryColour()!=Colour.COVERING_NONE) {
					return new Response("Toenail polish glow", "Cycle toenail polish glow.", SHOP_BEAUTY_SALON_COSMETICS) {
						@Override
						public void effects() {
								Main.game.getPlayer().getFootNailPolish().setPrimaryGlowing(!Main.game.getPlayer().getFootNailPolish().isPrimaryGlowing());
						}
					};
				} else {
					return new Response("Toenail polish glow", "You can't make your toenail polish glow if you aren't wearing any!", null);
				}
				
			} else if (index == 13) {
				return new Response("Anal bleach", "Cycle whether your anus is bleached or not.", SHOP_BEAUTY_SALON_COSMETICS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setAssBleached(!Main.game.getPlayer().isAssBleached());
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_TATTOOS = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
					+ "Standard Tattoos cost 100 each."
					+ " Arcane tattoos cost 250."
						+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else {
				return null;
			}
		}
	};
}
