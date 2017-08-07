package com.base.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.body.BodyPartInterface;
import com.base.game.character.body.Hair;
import com.base.game.character.body.Eye;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.npc.dominion.Kate;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.responses.ResponseTrade;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.InventorySlot;
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
						+ "</p>";
				
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
						+ "</p>";
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
						+ "</p>");
			
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
				return new Response("Hair length", "The brochure states that Kate is able to use the arcane in order to lengthen a client's hair. She can also just use regular scissors if your hair needs a cut.", SHOP_BEAUTY_SALON_HAIR_LENGTH){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};

			} else if (index == 3) {
				return new Response("Hair colour", "There's a double-page spread of all the different dyes Kate has in stock, allowing her to recolour your hair in a wide variety of shades.", SHOP_BEAUTY_SALON_HAIR_COLOUR){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};

			} else if (index == 4) {
				return new Response("Skin/fur colour", "There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
						+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_SKIN_COLOUR){
					@Override
					public void effects() {
						skins = new ArrayList<>();
						for(BodyPartInterface bp : Main.game.getPlayer().getAllBodyParts()){
							if(bp.getType().getSkinType()!=null
									&& bp.getType().getSkinType().getRace()!=null
									&& !skins.contains(bp.getType().getSkinType())
									&& !(bp instanceof Hair)
									&& !(bp instanceof Eye))
								skins.add(bp.getType().getSkinType());
						}

						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};

			} else if (index == 5) {
				return new Response("Eye colour", "There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
						+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_EYE_COLOUR){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};

			} else if (index == 6) {
				return new Response("Piercings", "Kate offers a wide range of different piercings.", SHOP_BEAUTY_SALON_PIERCINGS){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().reactedToKatePregnancy)
							Main.game.getDialogueFlags().reactedToKatePregnancy = true;
					}
				};

			} else if (index == 7) {
				return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
						+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...</br>"
						+ "<b>Will be done as soon as possible!</b>", null);

			} else if (index == 8) {
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
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_HAIR_LENGTH = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
									+ "The front half of the brochure is filled with images of different hair styles and colourings."
									+ " A picture of a surprisingly energetic Kate adorns one of the double-page spreads."
									+ " She's holding her arms out to one side, drawing attention to a series of pictures, each showing a different length of hair."
									+ " A little speech bubble, drawn coming out of her mouth, reads; "
									+ UtilText.parseSpeech("Just let me know how long you want it!", Main.game.getKate())
								+ "</p>"
								
								+ "<h6 style='text-align:center;'>"
									+ "Hair lengths"
								+"</h6>"
								+ "<p style='text-align:center;'>"
									+ "Your current length is <b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>highlighted</b>."
								+ "</p>"
								+ "<p style='text-align:center;'>");
								
			for(HairLength length : HairLength.values()){
				if(Main.game.getPlayer().getHairLength()==length)
					descriptionSB.append("<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(length.getDescriptor())+"</b></br>");
				else
					descriptionSB.append(Util.capitaliseSentence(length.getDescriptor())+"</br>");
			}
									
			descriptionSB.append("</p>"
								+ "<p style='text-align:center;'>"
								+ "A small paragraph at the bottom of the page informs you that Kate needs to use more arcane power the longer your hair is."
								+ " As a result, the price of hair lengthening is more expensive the longer your hair gets."
								+ " At its current length, Kate will charge you:</br>"
								+ "<b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>30</b> for a hair cut.</br>"
								+ "<b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>" +(Main.game.getPlayer().getHairRawLengthValue()*5) +"</b> for a permanent hair extension."
								+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<30) {
					return new Response("Cut (" + Main.game.getCurrencySymbol() + " 30)", "You don't have enough money to get your hair cut!", null);
				
				}else if(Main.game.getPlayer().getHairLength()==HairLength.ZERO_BALD) {
					return new Response("Cut (" + Main.game.getCurrencySymbol() + " 30)", "You are already bald, so there's nothing for Kate to cut!", null);
				
				} else {
					return new Response("Cut (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 30)",
							"Ask Kate to cut your hair a little shorter. This will shorten your hair from <b>"+Main.game.getPlayer().getHairLength().getDescriptor()+"</b> to <b>"
								+HairLength.getShorter(Main.game.getPlayer().getHairRawLengthValue()).getDescriptor()+"</b>", SHOP_BEAUTY_SALON_HAIR_LENGTH){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-30);
							Main.game.getPlayer().setHairLength(HairLength.getShorter(Main.game.getPlayer().getHairRawLengthValue()).getMedianValue());
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
									+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>30</b>."
									+ "</p>"
									+ "<p style='text-align:center;'>"
									+ "With a fair amount of skill, she snips away at your "+Main.game.getPlayer().getHairName()+" with a pair of scissors, leaving you with:</br>"
										+"<b>"+Main.game.getPlayer().getHairLength().getDescriptor()+" "+Main.game.getPlayer().getHairName()+"</b>."
									+ "</p>");
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<(Main.game.getPlayer().getHairRawLengthValue()*5)) {
					return new Response("Lengthen (" + Main.game.getCurrencySymbol() + " " +(Main.game.getPlayer().getHairRawLengthValue()*5) +")",
							"You don't have enough money to get your hair lengthened!", null);
				
				} else if(Main.game.getPlayer().getHairLength()==HairLength.SEVEN_TO_FLOOR) {
					return new Response("Lengthen (" + Main.game.getCurrencySymbol() + " " +(Main.game.getPlayer().getHairRawLengthValue()*5) +")",
							"You don't have enough money to get your hair cut!", null);
				
				} else {
					return new Response("Lengthen (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> " +(Main.game.getPlayer().getHairRawLengthValue()*5) +")",
							"Ask Kate to lengthen your hair. This will lengthen your hair from <b>"+Main.game.getPlayer().getHairLength().getDescriptor()+"</b> to <b>"
									+HairLength.getLonger(Main.game.getPlayer().getHairRawLengthValue()).getDescriptor()+"</b>",
							SHOP_BEAUTY_SALON_HAIR_LENGTH){
						@Override
						public void effects() {
							int cost = (Main.game.getPlayer().getHairRawLengthValue()*5);
							Main.game.getPlayer().incrementMoney(-cost);
							Main.game.getPlayer().setHairLength(HairLength.getLonger(Main.game.getPlayer().getHairRawLengthValue()).getMedianValue());
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
									+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+cost+"</b>."
									+ "</p>"
									+ "<p style='text-align:center;'>"
									+ "With a bright pink flash, Kate harnesses her arcane aura, channeling it's power into your "+Main.game.getPlayer().getHairName()+". As you open your eyes, you see that she's left you with:</br>"
										+"<b>"+Main.game.getPlayer().getHairLength().getDescriptor()+" "+Main.game.getPlayer().getHairName()+"</b>."
									+ "</p>");
						}
					};
				}
				
			} else if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_HAIR_COLOUR = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
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
			
			descriptionSB.append("<h6 style='text-align:center;'>"
					+ Util.capitaliseSentence(Main.game.getPlayer().getHairName())+" colours for "
					+ (Main.game.getPlayer().isFeminine()?Main.game.getPlayer().getHairType().getRace().getPluralFemaleName():Main.game.getPlayer().getHairType().getRace().getPluralMaleName())
				+"</h6>"
				+ "<p style='text-align:center;'>"
					+ "Your current colour is <b>*</b> <b>highlighted</b> <b>*</b>."
				+ "</p>"
				+ "<p style='text-align:center;'>");
				
				for(Colour colour : Main.game.getPlayer().getHairType().getAllColours()){
					if(Main.game.getPlayer().getHairColour()==colour)
						descriptionSB.append(
								"<b>*</b>"
								+" <b style='color:"+colour.toWebHexString()+";'>"+Util.capitaliseSentence(colour.getName())+"</b> "
								+"<b>*</b></br>");
					else
						descriptionSB.append("<span style='color:"+colour.toWebHexString()+";'>"+Util.capitaliseSentence(colour.getName())+"</span></br>");
				};
				
			descriptionSB.append("</p>"
					+ "<p>"
					+ "A small paragraph at the bottom of the page informs you that Kate uses a special arcane power to change your hair colour."
					+ " It seems to be trying to emphasise the fact that this isn't a temporary dye, and will permanently change your natural colouring.</p>"
					+ "<p style='text-align:center;'>"
					+ "All hair recolouring costs <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>30</b>."
					+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else if (index-1 < Main.game.getPlayer().getHairType().getAllColours().size()) {
				if(Main.game.getPlayer().getMoney()<30) {
					return new Response(Util.capitaliseSentence(Main.game.getPlayer().getHairType().getAllColours().get(index-1).getName())+" ("+ Main.game.getCurrencySymbol() + " 30)",
							"You don't have enough money to get your hair coloured!", null);
				
				}else if(Main.game.getPlayer().getHairColour()==Main.game.getPlayer().getHairType().getAllColours().get(index-1)) {
					return new Response(Util.capitaliseSentence(Main.game.getPlayer().getHairType().getAllColours().get(index-1).getName())+" ("+ Main.game.getCurrencySymbol() + " 30)",
							"Your "+Main.game.getPlayer().getHairName()+" is already "+Main.game.getPlayer().getHairType().getAllColours().get(index-1).getName()+"!", null);
				
				} else {
					return new Response(
							Util.capitaliseSentence(Main.game.getPlayer().getHairType().getAllColours().get(index-1).getName())
								+" (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 30)",
							"Ask Kate to change your natural hair colour to "+Main.game.getPlayer().getHairType().getAllColours().get(index-1).getName()+".",
							SHOP_BEAUTY_SALON_HAIR_COLOUR){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-30);
							Main.game.getPlayer().setHairColour(Main.game.getPlayer().getHairType().getAllColours().get(index-1));
							
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
									+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>30</b>."
									+ "</p>"
									+ "<p style='text-align:center;'>"
									+ "She runs her delicate fingertips down your scalp as she starts to massage your head."
									+ " A bright crackle of pink arcane energy suddenly leaps down from her hand, and your eyes open wide as you see your "+Main.game.getPlayer().getHairName()+" changing colour."
									+ " Within seconds, you're left with:</br>"
									+"<b style='color:"+Main.game.getPlayer().getHairColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getHairColour().getName())+"</b> <b>"+Main.game.getPlayer().getHairName()+"</b>."
									+ "</p>");
						}
					};
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
			
			for(BodyCoveringType st : skins){
				descriptionSB.append("<b style='color:"+Main.game.getPlayer().getSkinColour(st).toWebHexString()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getSkinColour(st).getName())
										+"</b> <b>"+st.getName(Main.game.getPlayer())+" ("+st.getRace().getName()+")</b>"
										+ "</br>");
			};
			
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
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
									+ "Turning the page to where all the different colours of "+Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+" are displayed, you wonder if you should wake Kate and ask her for her services..."
								+ "</p>");
			
			descriptionSB.append("<h6 style='text-align:center;'>"
					+Util.capitaliseSentence(Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer()))+" colours"
				+"</h6>"
				+ "<p style='text-align:center;'>"
					+ "Your current colour is <b>*</b> <b>highlighted</b> <b>*</b>."
				+ "</p>"
				+ "<p style='text-align:center;'>");
				
				for(Colour colour : Main.game.getDialogueFlags().skinTypeSelected.getAllColours()){
					if(Main.game.getPlayer().getSkinColour(Main.game.getDialogueFlags().skinTypeSelected)==colour)
						descriptionSB.append(
								"<b>*</b>"
								+" <b style='color:"+colour.toWebHexString()+";'>"+Util.capitaliseSentence(colour.getName())+"</b> "
								+"<b>*</b></br>");
					else
						descriptionSB.append("<span style='color:"+colour.toWebHexString()+";'>"+Util.capitaliseSentence(colour.getName())+"</span></br>");
				};
				
			descriptionSB.append("</p>"
					+ "<p style='text-align:center;'>"
					+ "All skin and fur recolouring costs <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>200</b>."
					+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Return to the previous page.", SHOP_BEAUTY_SALON_SKIN_COLOUR);
				
			} else if(index-1 < Main.game.getDialogueFlags().skinTypeSelected.getAllColours().size()) {
				if(Main.game.getPlayer().getMoney()<200) {
					return new Response(Util.capitaliseSentence(Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1).getName())+" ("+ Main.game.getCurrencySymbol() + " 200)",
							"You don't have enough money to get your "+ Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+" recoloured!", null);
				
				}else if(Main.game.getPlayer().getSkinColour(Main.game.getDialogueFlags().skinTypeSelected)==Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1)) {
					return new Response(Util.capitaliseSentence(Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1).getName())+" ("+ Main.game.getCurrencySymbol() + " 200)",
							"Your "+Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+" is already "+Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1).getName()+"!", null);
				
				} else {
					return new Response(
							Util.capitaliseSentence(Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1).getName())
								+" (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 200)",
							"Ask Kate to change your natural "+Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+" colour to "+Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1).getName()+".",
							SHOP_BEAUTY_SALON_SKIN_COLOUR_CHOICES){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-200);
							Main.game.getPlayer().setSkinColour(Main.game.getDialogueFlags().skinTypeSelected, Main.game.getDialogueFlags().skinTypeSelected.getAllColours().get(index-1));
							
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
									+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>200</b>."
									+ "</p>"
									+ "<p style='text-align:center;'>"
									+ "She runs her delicate fingertips all over your body, and you notice that her touch lingers longer at your groin and chest than elsewhere."
									+ " Before you get the chance to point this out to her, a bright crackle of pink arcane energy suddenly leaps from her hands, and your eyes open wide as you see your "
										+Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+" changing colour."
									+ " Within seconds, you're left with:</br>"
									+"<b style='color:"+Main.game.getPlayer().getSkinColour(Main.game.getDialogueFlags().skinTypeSelected).toWebHexString()+";'>"
										+Util.capitaliseSentence(Main.game.getPlayer().getSkinColour(Main.game.getDialogueFlags().skinTypeSelected).getName())
										+"</b> <b>"+Main.game.getDialogueFlags().skinTypeSelected.getName(Main.game.getPlayer())+"</b>."
									+ "</p>");
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld SHOP_BEAUTY_SALON_EYE_COLOUR = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
									+ "There's a page near the front of the brochure which displays all the different eye colourings that Kate has on offer."
									+ " There's separate section for each race, but the only one that applies to you is the page for ");
			
			if(Main.game.getPlayer().isFeminine()) {
				descriptionSB.append(Main.game.getPlayer().getEyeType().getRace().getPluralFemaleName()+"."
									+ "</p>");
				
			} else {
				descriptionSB.append(Main.game.getPlayer().getEyeType().getRace().getPluralMaleName()+"."
						+ "</p>");
			}
			
			descriptionSB.append("<p>"
					+ "Looking through all the different colours on offer, you wonder if you should wake Kate and ask to have your eye colour changed..."
					+ "</p>");
			
			descriptionSB.append("<h6 style='text-align:center;'>"
					+ Util.capitaliseSentence(Main.game.getPlayer().getEyeName())+" colours for "
					+ (Main.game.getPlayer().isFeminine()?Main.game.getPlayer().getEyeType().getRace().getPluralFemaleName():Main.game.getPlayer().getEyeType().getRace().getPluralMaleName())
				+"</h6>"
				+ "<p style='text-align:center;'>"
					+ "Your current colour is <b>*</b> <b>highlighted</b> <b>*</b>."
				+ "</p>"
				+ "<p style='text-align:center;'>");
				
				for(Colour colour : Main.game.getPlayer().getEyeType().getAllColours()){
					if(Main.game.getPlayer().getEyeColour()==colour)
						descriptionSB.append(
								"<b>*</b>"
								+" <b style='color:"+colour.toWebHexString()+";'>"+Util.capitaliseSentence(colour.getName())+"</b> "
								+"<b>*</b></br>");
					else
						descriptionSB.append("<span style='color:"+colour.toWebHexString()+";'>"+Util.capitaliseSentence(colour.getName())+"</span></br>");
				};
				
			descriptionSB.append("</p>"
					+ "<p>"
					+ "A small paragraph at the bottom of the page informs you that Kate uses a special arcane power to change your eye colour."
					+ " Apparently, it's extremely draining on her aura, so eye colouring is just as expensive as skin colouring.</p>"
					+ "<p style='text-align:center;'>"
					+ "All eye recolouring costs <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>200</b>."
					+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Back", "Tell Kate that you want a different service.", SHOP_BEAUTY_SALON_MAIN);
				
			} else if(index-1 < Main.game.getPlayer().getEyeType().getAllColours().size()) {
				if(Main.game.getPlayer().getMoney()<200) {
					return new Response(Util.capitaliseSentence(Main.game.getPlayer().getEyeType().getAllColours().get(index-1).getName())+" ("+ Main.game.getCurrencySymbol() + " 200)</span>",
							"You don't have enough money to get your [pc.eyes] recoloured!", null);
				
				}else if(Main.game.getPlayer().getEyeType().getAllColours().get(index-1) == Main.game.getPlayer().getEyeColour()) {
					return new Response(Util.capitaliseSentence(Main.game.getPlayer().getEyeType().getAllColours().get(index-1).getName())+" ("+ Main.game.getCurrencySymbol() + " 200)</span>",
							"Your [pc.eyes] are already "+Main.game.getPlayer().getEyeType().getAllColours().get(index-1).getName()+"!", null);
				
				} else {
					return new Response(
							Util.capitaliseSentence(Main.game.getPlayer().getEyeType().getAllColours().get(index-1).getName())+" (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 200)",
							"Ask Kate to change your natural eye colour to "+Main.game.getPlayer().getEyeType().getAllColours().get(index-1).getName()+".",
							SHOP_BEAUTY_SALON_EYE_COLOUR){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-200);
							Main.game.getPlayer().setEyeColour(Main.game.getPlayer().getEyeType().getAllColours().get(index-1));
							
							Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>"
									+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>200</b>."
									+ "</p>"
									+ "<p style='text-align:center;'>"
									+ "She runs her delicate fingertips over the contours of your cheeks, slowly moving up to gently rub at your lower eyelids."
									+ " A bright crackle of pink arcane energy suddenly flashes across your vision, and as Kate steps back, you look into the mirror in front of you to see that your "+Main.game.getPlayer().getEyeName()+" have changed colour."
									+ " You now have:</br>"
									+"<b style='color:"+Main.game.getPlayer().getEyeColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getEyeColour().getName())+"</b> <b>"+Main.game.getPlayer().getEyeName()+"</b>."
									+ "</p>");
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	
	private static int
		earPiercingCost=15, earRemovalCost=30,
		nosePiercingCost=15, noseRemovalCost=30,
		lipsPiercingCost=25, lipsRemovalCost=50,
		tonguePiercingCost=25, tongueRemovalCost=50,
		navelPiercingCost=40, navelRemovalCost=80,
		nipplePiercingCost=60, nippleRemovalCost=120,
		vaginaPiercingCost=100, vaginaRemovalCost=200,
		penisPiercingCost=100, penisRemovalCost=200;
	
	private static String pierceTableRow(String partName, int price, int priceRemoval){
		return "<tr>"
				+ "<td>"+partName+"</td>"
				+ "<td><span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+"</td>"
				+ "<td><span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+priceRemoval+"</td>"
			+ "</tr>";
	}
	private static String pierceReponseTitle(String partName, int price){
		if(Main.game.getPlayer().getMoney()<price)
			return "<span class='option-disabled'>"+partName +" (<b>P</b>: " + Main.game.getCurrencySymbol() +" "+price+")</span>";
		else
			return partName +" (<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>P</b>: "+"<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+")";
	}
	private static String removalReponseTitle(String partName, int price){
		if(Main.game.getPlayer().getMoney()<price)
			return "<span class='option-disabled'>"+partName +" (<b>R</b>: " + Main.game.getCurrencySymbol() +" "+price+")</span>";
		else
			return partName +" (<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>R</b>: "+"<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+")";
	}
	private static String pierceReponseDescription(String partName, int price){
		if(Main.game.getPlayer().getMoney()<price)
			return "You don't have enough money to get your "+partName+" pierced!</br></br>"
					+ "It costs <span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+", but you only have"
							+ " <span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> <span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+Main.game.getPlayer().getMoney()+"</span>!";
		else
			return "Ask Kate to pierce your "+partName +". This will cost <span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+".";
	}
	private static String removalReponseDescription(String partName, int price){
		if(Main.game.getPlayer().getMoney()<price)
			return "You don't have enough money to remove the piercing from your "+partName+"!</br></br>"
					+ "It costs <span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+", but you only have"
							+ " <span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> <span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"+Main.game.getPlayer().getMoney()+"</span>!";
		else
			return "Ask Kate to remove the piercing from your "+partName +". This will cost <span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> "+price+".";
	}
	
	

	
	//TODO finish all normal and masochistic piercing descriptions
	/*
	 * If masochist, you ask her to make it hurt.
	 * She says she understands, she likes rough stuff as well
	 * Painfully twists your part (to test your pain threshold)
	 * Then doesn't use any anesthetic and it really fucking hurts
	 * Afterwards, she says it'll only be sore for a few minutes, so she won't get another opportunity to do this
	 * Then twists and pinches your new piercing (but you like that... right? Why else would you have taken masochism...)
	 */
	
	private static StringBuilder piercingSB;
	private static String getPiercingDescription(InventorySlot slot, int payment){
		Main.game.getPlayer().incrementMoney(-payment);
		
		piercingSB = new StringBuilder();
		
		piercingSB.append("<p>"
								+ "Putting the brochure down to one side, you turn to Kate and ask if she's awake."
								+ " She lets out a huge yawn, and with blinking eyes, looks around for the source of the noise."
								+ " Seeing that you want something, she sits up in her chair, "
								+ UtilText.parseSpeech("Oh heya! So, you've decided on something you want?", Main.game.getKate())
							+ "</p>");
		
//		if(!Main.game.getPlayer().isMasochist()) {
			switch(slot){
				case PIERCING_EAR:
					return "<span style='text-align:center;'>"
							+ "<p>"
					+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
					+ "</p>"
					+Main.game.getPlayer().setPiercedEar(true)
					+"</span>";
					// Removed for now to keep consistency with descriptions (I didn't have time to write them all!!!)
//					piercingSB.append("<p>"
//											+ "You tell Kate that you'd like to get your ears pierced."
//											+ " With a happy cry, she jumps up out of her chair and steps over to you."
//											+ " You look up at her reflection in the mirror in front of you, and as she reaches down to stroke your "+Main.game.getPlayer().getEarDescriptor()
//											+" ears, you see a pink flicker of arcane energy crackling around her hand."
//										+ "</p>"
//										+ "<p>"
//										+ GenericSentence.parsePlayerSpeech("Is this going to hurt?")
//										+" you ask, shuffling nervously in your seat."
//										+ "</p>"
//										+"<p>"
//										+ "Kate carries on softly stroking your ears as she reassures you, "
//											+ GenericSentence.parseSpeech("No, no, no! I'm very, <i>very</i> good at this. You won't feel a thing, I promise!", Main.game.getBeautySalonOwner())
//										+ "</p>"
//										+ "<p>"
//										+ "Kate steps back, and a look of deep concentration settles on her face."
//										+ " As you watch, a sharp needle of arcane energy suddenly shoots out from her outstretched index finger, and she lets out a satisfied sigh as she visibly relaxes, "
//										+ GenericSentence.parseSpeech("All done!", Main.game.getBeautySalonOwner())
//										+ "</p>"
//										+ "<p>"
//										+ "You reach up to feel your ears, and, sure enough, you discover that they've been pierced!"
//										+ " Turning back to Kate, you see that she's already collapsed back into her chair, and her low snores start to fill the room once more."
//										+ " Letting out a disapproving sigh at how lazy she is, you end up having to leave her payment on the little table beside you."
//										+ "</p>"
//										+ "<p>"
//										+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
//										+ "</p>"
//										+Main.game.getPlayer().setPiercedEar(true));
//					break;
				case PIERCING_LIP:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedLip(true)
							+"</span>";
				case PIERCING_NIPPLE:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedNipple(true)
							+"</span>";
				case PIERCING_NOSE:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedNose(true)
							+"</span>";
				case PIERCING_PENIS:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedPenis(true)
							+"</span>";
				case PIERCING_STOMACH:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedNavel(true)
							+"</span>";
				case PIERCING_TONGUE:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedTongue(true)
							+"</span>";
				case PIERCING_VAGINA:
					return "<span style='text-align:center;'>"
							+ "<p>"
							+ "You pay Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
							+ "</p>"
							+Main.game.getPlayer().setPiercedVagina(true)
							+"</span>";
				default:
					return "";
			}
//		}
		
//		return piercingSB.toString();
	}
	private static String getRemovalDescription(InventorySlot slot, int payment){
		Main.game.getPlayer().incrementMoney(-payment);
		
		switch(slot){
			case PIERCING_EAR:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_EAR)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_EAR), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedEar(false)
						+"</span>";
			case PIERCING_LIP:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_LIP)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_LIP), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedLip(false)
						+"</span>";
			case PIERCING_NIPPLE:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NIPPLE)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NIPPLE), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedNipple(false)
						+"</span>";
			case PIERCING_NOSE:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NOSE)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NOSE), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedNose(false)
						+"</span>";
			case PIERCING_PENIS:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_PENIS)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_PENIS), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedPenis(false)
						+"</span>";
			case PIERCING_STOMACH:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_STOMACH)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_STOMACH), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedNavel(false)
						+"</span>";
			case PIERCING_TONGUE:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_TONGUE)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_TONGUE), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedTongue(false)
						+"</span>";
			case PIERCING_VAGINA:
				return "<span style='text-align:center;'>"
						+ "<p>"
						+ "You paid Kate <b style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</b> <b>"+payment+"</b>."
						+ "</p>"
						+(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_VAGINA)==null?""
								:Main.game.getPlayer().unequipClothingIntoInventory(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_VAGINA), true, Main.game.getKate()))
						+Main.game.getPlayer().setPiercedVagina(false)
						+"</span>";
			default:
				return "";
		}
	}
	
	private static Response getPiercingRemovalResponse(String bodypartName, int removalCost, InventorySlot slot){
		if(Main.game.getPlayer().getMoney()<removalCost)
			return new Response(removalReponseTitle(bodypartName, removalCost), removalReponseDescription(bodypartName, removalCost), null);
		else
			return new Response(removalReponseTitle(bodypartName, removalCost), removalReponseDescription(bodypartName, removalCost), SHOP_BEAUTY_SALON_PIERCINGS){
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(getRemovalDescription(slot, removalCost));
				}
			};
	}
	private static Response getPiercingPierceResponse(String bodypartName, int removalCost, InventorySlot slot){
		if(Main.game.getPlayer().getMoney()<removalCost)
			return new Response(pierceReponseTitle(bodypartName, removalCost), pierceReponseDescription(bodypartName, removalCost), null);
		else
			return new Response(pierceReponseTitle(bodypartName, removalCost), pierceReponseDescription(bodypartName, removalCost), SHOP_BEAUTY_SALON_PIERCINGS){
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(getPiercingDescription(slot, removalCost));
				}
			};
	}
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_PIERCINGS = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
									+ "You flip back to the piercings section of the brochure."
									+ " There's a very professional-looking picture of Kate on one side of the double-page spread, bending down over a faceless client as she pierces their navel."
									+ " On the opposite side of the page, there's a table of all the available piercings that Kate's able to provide."
								+ "</p>"
								+ "<p style='text-align:center;'>"
	
									+ "<b>Standard piercings:</b>"
									+"<table style='font-size:1em; text-align:center; margin: 0 auto;'>"
									+ "<tr>"
										+ "<th style='padding:0 8px 0 8px;'>Part</th>"
										+ "<th style='padding:0 8px 0 8px; color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Pierce</th>"
										+ "<th style='padding:0 8px 0 8px; color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Removal</th>"
									+ "</tr>"
									+ pierceTableRow("Ears", earPiercingCost, earRemovalCost)
									+ pierceTableRow("Nose", nosePiercingCost, noseRemovalCost)
									+ pierceTableRow("Lips", lipsPiercingCost, lipsRemovalCost)
									+ pierceTableRow("Tongue", tonguePiercingCost, tongueRemovalCost)
									+ pierceTableRow("Navel", navelPiercingCost, navelRemovalCost)
									+ "</table>"
									
									+ "</br></br><b>Special piercings:</b>"
									+"<table style='font-size:1em; text-align:center; margin: 0 auto;'>"
									+ "<tr>"
										+ "<th style='padding:0 8px 0 8px;'>Part</th>"
										+ "<th style='padding:0 8px 0 8px; color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Pierce</th>"
										+ "<th style='padding:0 8px 0 8px; color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Removal</th>"
									+ "</tr>"
									+ pierceTableRow("Nipples", nipplePiercingCost, nippleRemovalCost)
									+ pierceTableRow("Vagina", vaginaPiercingCost, vaginaRemovalCost)
									+ pierceTableRow("Penis", penisPiercingCost, penisRemovalCost)
									+ "</table>"
								+ "</p>");
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isPiercedEar()) {
					return getPiercingRemovalResponse("Ears", earRemovalCost, InventorySlot.PIERCING_EAR);
				} else {
					return getPiercingPierceResponse("Ears", earPiercingCost, InventorySlot.PIERCING_EAR);
				}

			} else if (index == 2) {
				if(Main.game.getPlayer().isPiercedNose()) {
					return getPiercingRemovalResponse("Nose", noseRemovalCost, InventorySlot.PIERCING_NOSE);
				} else {
					return getPiercingPierceResponse("Nose", nosePiercingCost, InventorySlot.PIERCING_NOSE);
				}

			} else if (index == 3) {
				if(Main.game.getPlayer().isPiercedLip()) {
					return getPiercingRemovalResponse("Lips", lipsRemovalCost, InventorySlot.PIERCING_LIP);
				} else {
					return getPiercingPierceResponse("Lips", lipsPiercingCost, InventorySlot.PIERCING_LIP);
				}

			} else if (index == 4) {
				if(Main.game.getPlayer().isPiercedTongue()) {
					return getPiercingRemovalResponse("Tongue", tongueRemovalCost, InventorySlot.PIERCING_TONGUE);
				} else {
					return getPiercingPierceResponse("Tongue", tonguePiercingCost, InventorySlot.PIERCING_TONGUE);
				}

			} else if (index == 5) {
				if(Main.game.getPlayer().isPiercedNavel()) {
					return getPiercingRemovalResponse("Navel", navelRemovalCost, InventorySlot.PIERCING_STOMACH);
				} else {
					return getPiercingPierceResponse("Navel", navelPiercingCost, InventorySlot.PIERCING_STOMACH);
				}

			} else if (index == 6) {
				if(Main.game.getPlayer().isPiercedNipple()) {
					return getPiercingRemovalResponse("Nipples", nippleRemovalCost, InventorySlot.PIERCING_NIPPLE);
				} else {
					return getPiercingPierceResponse("Nipples", nipplePiercingCost, InventorySlot.PIERCING_NIPPLE);
				}

			} else if (index == 7) {
				if(Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
					return new Response("Vagina (<b>P</b>: " + Main.game.getCurrencySymbol() +" "+vaginaPiercingCost+")", "You don't have a vagina, so it would be pretty hard for Kate to do this!", null);
				} else if(Main.game.getPlayer().isPiercedVagina()) {
					return getPiercingRemovalResponse("Vagina", vaginaRemovalCost, InventorySlot.PIERCING_VAGINA);
				} else {
					return getPiercingPierceResponse("Vagina", vaginaPiercingCost, InventorySlot.PIERCING_VAGINA);
				}

			} else if (index == 8) {
				if(Main.game.getPlayer().getPenisType()==PenisType.NONE) {
					return new Response("Penis (<b>P</b>: " + Main.game.getCurrencySymbol() +" "+penisPiercingCost+")", "You don't have a penis, so it would be pretty hard for Kate to do this!", null);
				} else if(Main.game.getPlayer().isPiercedPenis()) {
					return getPiercingRemovalResponse("Penis", penisRemovalCost, InventorySlot.PIERCING_PENIS);
				} else {
					return getPiercingPierceResponse("Penis", penisPiercingCost, InventorySlot.PIERCING_PENIS);
				}

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
