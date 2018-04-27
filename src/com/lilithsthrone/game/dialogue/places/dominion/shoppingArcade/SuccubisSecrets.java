package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.1.84
 * @author Innoxia
 */
public class SuccubisSecrets {
	
	private static StringBuilder descriptionSB;
	
	public static final int BASE_COSMETICS_COST = 200;
	public static final int BASE_PIERCINGS_COST = 25;
	public static final int BASE_HAIR_LENGTH_COST = 25;
	public static final int BASE_HAIR_STYLE_COST = 50;
	public static final int BASE_ANAL_BLEACHING_COST = 100;
	public static final int BASE_BODY_HAIR_COST = 50;
	
	public static final HashMap<BodyCoveringType, Integer> cosmeticCostsMap = Util.newHashMapOfValues(
			new Value<>(BodyCoveringType.MAKEUP_BLUSHER, 25),
			new Value<>(BodyCoveringType.MAKEUP_EYE_LINER, 25),
			new Value<>(BodyCoveringType.MAKEUP_EYE_SHADOW, 25),
			new Value<>(BodyCoveringType.MAKEUP_LIPSTICK, 25),
			new Value<>(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, 25),
			new Value<>(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, 25));

	public static final HashMap<PiercingType, Integer> piercingCostsMap = Util.newHashMapOfValues(
			new Value<>(PiercingType.EAR, 10),
			new Value<>(PiercingType.LIP, 25),
			new Value<>(PiercingType.NAVEL, 25),
			new Value<>(PiercingType.NIPPLE, 50),
			new Value<>(PiercingType.NOSE, 25),
			new Value<>(PiercingType.PENIS, 100),
			new Value<>(PiercingType.TONGUE, 50),
			new Value<>(PiercingType.VAGINA, 100));
	

	public static int getBodyCoveringTypeCost(BodyCoveringType type) {
		if(cosmeticCostsMap.containsKey(type)) {
			return cosmeticCostsMap.get(type);
		}
		
		return BASE_COSMETICS_COST;
	}

	public static int getPiercingCost(PiercingType type) {
		if(piercingCostsMap.containsKey(type)) {
			return piercingCostsMap.get(type);
		}
		
		return BASE_PIERCINGS_COST;
	}
	
	
	
	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Succubi's Secrets (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)) {
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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)) {
					return new Response("Enter", "Step inside Succubi's Secrets.", SHOP_BEAUTY_SALON_ENTER) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
					
				} else {
					return new Response("Enter", "Step inside Succubi's Secrets.", SHOP_BEAUTY_SALON) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
				}
				
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
						+ " As you stand there, wondering what to do, she rolls over on one side, and as a thin stream of drool trickles down out the corner of her mouth, she whines,"
						+ " [kate.speech(Mmrph... A little deeper...)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wake her", "Wake the sleeping demon.", SHOP_BEAUTY_SALON_WAKE);
				
			} else if (index == 2) {
				return new Response("Watch", "Wait for the sleeping demon to wake up.", SHOP_BEAUTY_SALON_WATCH);

			} else if (index == 0) {
				return new Response("Back", "Head back out to the Shopping Arcade.", EXTERIOR);

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
						+ "[pc.speech(Excuse me?)]"
					+ "</p>"
					+ "<p>"
						+ "Her eyes instantly snap open, and as she lets out an annoyed huff, you realise that she was merely pretending to be asleep this whole time,"
						+ " [kate.speech(Am I really that rusty?! You didn't want to fuck me at all, not one little bit?"
							+ " Y'know, that's one downside of not having any customers. I haven't had sex in, like, three whole days! And then, when someone finally arrives, they don't even want to have any fun!)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex", "You can't resist the horny succubus's request...",
						true, true,
						new SMChair(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP)),
								Util.newHashMapOfValues(new Value<>(Main.game.getKate(), SexPositionSlot.CHAIR_BOTTOM))),
						Kate.AFTER_SEX,
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
						+ " Her body is equally as impressive, and beneath her pair of "+Main.game.getKate().getBreastSize().getDescriptor()
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
						+ "You see her tail snaking around beneath her, and as you watch, the spaded tip slips up between her legs, and, pulling her pink v-string panties to one side, thrusts up into her pussy."
						+ " Due to the fact that she's wearing nothing more than an impossibly-small micro-skirt around her waist, you're able to see everything as she starts to pump her tail back and forth."
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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Fuck her", "Do as she says and start having sex with her.",
						true, true,
						new SMChair(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP)),
								Util.newHashMapOfValues(new Value<>(Main.game.getKate(), SexPositionSlot.CHAIR_BOTTOM))),
						Kate.AFTER_SEX,
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
						+ " Her body is equally as impressive, and beneath her pair of "+Main.game.getKate().getBreastSize().getDescriptor()
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
						+ " Her body is equally as impressive, and beneath her pair of "+Main.game.getKate().getBreastSize().getDescriptor()
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
						+ UtilText.parseSpeech("Well, yeah I'm aware! You know, the owners of this whole arcade keep threatening me with legal action, saying I have a 'responsibility' to keep the area looking nice."
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
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.kateIntroduced);
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
			
			if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
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
						+ "[pc.speech(Is there anything I can get for you?)] you ask, unsure of quite how to react to Kate's blas&eacute; attitude, [pc.speech(You don't seem very concerned about being pregnant...)]"
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
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static Map<BodyCoveringType, List<String>> CoveringsNamesMap;
	
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
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static Response getMainResponse(int index) {
		if(index == 1){
			return new ResponseTrade("Trade with Kate", "There's a separate leaflet tucked into the back of the brochure. It informs you that Kate is a registered distributor for a large jewellery firm.", Main.game.getKate()){
				@Override
				public void effects() {
					if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy))
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
				}
			};
			
		} else if (index == 2) {
			if(!Main.game.getPlayer().getBodyMaterial().isAbleToWearMakeup()) {
				return new Response("Makeup", "As your body is made of "+Main.game.getPlayer().getBodyMaterial().getName()+", Kate is unable to apply any makeup!", null);
				
			} else {
				return new Response("Makeup",
						"Kate offers a wide range of different cosmetic services, and several pages of the brochure are devoted to images displaying different styles and colours of lipstick, nail polish, and other forms of makeup.",
						SHOP_BEAUTY_SALON_COSMETICS){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
						}
					}
				};
			}

		} else if (index == 3) {
			return new Response("Hair",
					"There's a double-page spread of all the different dyes, styles, and lengths of hair that Kate's able to work with.",
					SHOP_BEAUTY_SALON_HAIR){
				@Override
				public void effects() {
					if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};

		} else if (index == 4) {
				return new Response("Piercings",
						"Kate offers a wide range of different piercings.",
						SHOP_BEAUTY_SALON_PIERCINGS){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
						}
					}
				};

		}  else if (index == 5) {
				return new Response("Eyes",
						"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
						+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_EYES){
					@Override
					public void effects() {
						if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
						}
					}
				};

		} else if (index == 6) {
			return new Response("Coverings",
					"There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
					+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.",
					SHOP_BEAUTY_SALON_SKIN_COLOUR){
				@Override
				public void effects() {
					
					CoveringsNamesMap = new LinkedHashMap<>();
					
					if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
						CoveringsNamesMap.put(BodyCoveringType.SLIME, Util.newArrayListOfValues(new ListValue<>("SLIME")));
						
					} else {
						for(BodyPartInterface bp : Main.game.getPlayer().getAllBodyParts()){
							if(bp.getType().getBodyCoveringType(Main.game.getPlayer())!=null
									&& !(bp instanceof Hair)
									&& !(bp instanceof Eye)) {
								
								String name = bp.getName(Main.game.getPlayer());
								if(bp instanceof Skin) {
									name = "torso";
								} else if(bp instanceof Vagina) {
									name = "vagina";
								}
								
								if(CoveringsNamesMap.containsKey(bp.getType().getBodyCoveringType(Main.game.getPlayer()))) {
									CoveringsNamesMap.get(bp.getType().getBodyCoveringType(Main.game.getPlayer())).add(name);
								} else {
									CoveringsNamesMap.put(bp.getType().getBodyCoveringType(Main.game.getPlayer()), Util.newArrayListOfValues(new ListValue<>(name)));
								}
							}
						}

						if(Main.getProperties().hasValue(PropertyValue.pubicHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getPubicHairType().getType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getPubicHairType().getType()).add("growing around your pubic region");
						}
						if(Main.getProperties().hasValue(PropertyValue.facialHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getFacialHairType().getType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getFacialHairType().getType()).add("covering your face");
						}
						if(Main.getProperties().hasValue(PropertyValue.bodyHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getBodyHairCoveringType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getBodyHairCoveringType()).add("growing in your underarms");
						}
						if(Main.getProperties().hasValue(PropertyValue.assHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getAssHairType().getType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getAssHairType().getType()).add("growing around your anus");
						}
						
						CoveringsNamesMap.put(BodyCoveringType.ANUS, Util.newArrayListOfValues(new ListValue<>("anus")));
						CoveringsNamesMap.put(BodyCoveringType.MOUTH, Util.newArrayListOfValues(new ListValue<>("mouth")));
						CoveringsNamesMap.put(BodyCoveringType.NIPPLES, Util.newArrayListOfValues(new ListValue<>("nipples")));
						CoveringsNamesMap.put(BodyCoveringType.TONGUE, Util.newArrayListOfValues(new ListValue<>("tongue")));
					}

					if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};

		} else if (index == 7) {
			return new Response("Other", "Kate can offer other miscellaneous services, such as anal bleaching.", SHOP_BEAUTY_SALON_OTHER);

		} else if (index == 8) {
			return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
					+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...</br>"
					+ "<b>Will be done as soon as possible!</b>", null);

		} else if (index == 9) {
			return new ResponseSex("Sex",
					"You roll your eyes as you reach the end of the brochure."
							+ " On a double-page spread, there's an extremely lewd collection of pictures of Kate inserting her tail into her various orifices, with the suggestive caption 'Don't make me do it myself...'",
					true, true,
					new SMChair(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.CHAIR_TOP)),
							Util.newHashMapOfValues(new Value<>(Main.game.getKate(), SexPositionSlot.CHAIR_BOTTOM))),
					Kate.AFTER_SEX_REPEATED,
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
					if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};
			
		} else if (index == 0) {
			return new Response("Leave", "Leave Kate's shop, heading back out into the Shopping Arcade.", EXTERIOR){
				@Override
				public void effects() {
					if(Main.game.getKate().isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};
			
		} else {
			return null;
		}
	}
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_HAIR = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			
			return "<p>"
						+ "There's a large section near the front of the brochure which shows all the different hair colours that are on offer."
						+ " A picture of a surprisingly energetic Kate is located in the bottom-right of the double-page spread."
						+ " She's holding her arms out to one side, drawing attention to a series of pictures, each showing a different length of hair."
						+ " A little speech bubble, drawn coming out of her mouth, reads; [kate.speech(Just let me know how long you want it!)]"
					+ "</p>"
					+ "<p>"
						+ "Just beneath this, a small paragraph informs you that Kate uses a special arcane power to change her client's hair colouring."
						+ " It seems to be trying to emphasise the fact that this isn't a temporary dye, and will permanently change your natural colouring."
					+ "</p>"
					+ "<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles you're able to have. The longer the hair, the more styles are available.")

					+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by your hair length.")
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairType().getBodyCoveringType(Main.game.getPlayer())).getType(),
							"[pc.Hair] Colour", "All hair recolourings are permanent, so if you want to change your colour again at a later time, you'll have to visit Kate again.", true, true)
					;
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_SKIN_COLOUR = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
									+ "In the middle of the brochure, there's a double-page spread advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
									+ " Your eyes widen as you see how much she charges for it, but as you read a paragraph describing how it's extremely demanding on her aura, you understand the high price."
								+ "</p>"
								+ "<h6 style='text-align:center;'>"
									+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
								+ "</h6>");
			
			for(Entry<BodyCoveringType, List<String>> entry : CoveringsNamesMap.entrySet()){
				BodyCoveringType bct = entry.getKey();
				
				String title = Util.capitaliseSentence(bct.getName(Main.game.getPlayer()));
				String description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently covering your "+Util.stringsToStringList(entry.getValue(), false)+".";
				
				if(bct == BodyCoveringType.SLIME) {
					title = "Slime";
					description = "Your entire body is made of slime!";
					
				} else if(bct == BodyCoveringType.ANUS) {
					title = "Anus";
					description = "This is the skin that's currently covering your anal rim. The secondary colour determines what your anus's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.VAGINA) {
					title = "Vagina";
					description = "This is the skin that's currently covering your labia. The secondary colour determines what your vagina's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.PENIS) {
					title = "Penis";
					description = "This is the skin that's currently covering your penis. The secondary colour determines what the inside of your urethra looks like (if it's fuckable).";
					
				} else if(bct == BodyCoveringType.NIPPLES) {
					title = "Nipples";
					description = "This is the skin that's currently covering your nipples and areolae. The secondary colour determines what your nipples' inner-walls look like (if they are fuckable).";
					
				} else if(bct == BodyCoveringType.MOUTH) {
					title = "Lips & Throat";
					if(Main.game.getPlayer().getFaceType() == FaceType.HARPY) {
						description = "This is the colour of your beak. The secondary colour determines what the insides of your mouth and throat look like.";
					} else {
						description = "This is the skin that's currently covering your lips. The secondary colour determines what the insides of your mouth and throat look like.";
					}
					
				} else if(bct == BodyCoveringType.TONGUE) {
					title = "Tongue";
					description = "This is the skin that's currently covering your tongue.";
				
				} else if(Main.getProperties().hasValue(PropertyValue.pubicHairContent) && bct == Main.game.getPlayer().getPubicHairType().getType()) {
					title = "Pubic "+bct.getName(Main.game.getPlayer());
					description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
					
				} else if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && bct == Main.game.getPlayer().getFacialHairType().getType()) {
					title = "Facial "+bct.getName(Main.game.getPlayer());
					description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
					
				} else if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && bct == Main.game.getPlayer().getBodyHairCoveringType()) {
					title = "Body "+bct.getName(Main.game.getPlayer());
					description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
				}
					
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true, 
						bct,
						title,
						description,
						true,
						true));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_EYES = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			

			return "<p>"
						+ "There's a page near the front of the brochure which displays all the different eye colourings that Kate has on offer."
						+ " It looks like she's able to colour each iris and pupil individually."
					+ "</p>"
					+ "<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Main.game.getPlayer().getEyeType().getBodyCoveringType(Main.game.getPlayer()), "Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_PUPILS, "Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_SCLERA, "Sclerae", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_PIERCINGS = new DialogueNodeOld("Succubi's Secrets", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			
			return "<p>"
					+ "You ask Kate about the different piercings she's able to apply and remove, and, after sleepily responding that she's capable of anything, you wonder if you should take advantage of this opportunity to change your piercings..."
				+ "</p>"
				+ "<h6 style='text-align:center;'>"
					+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
				+ "</h6>"
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.EAR, "Ear Piercing", "Ears are the most common area of the body that are pierced, and enable the equipping of earrings and other ear-related jewellery.")

				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NOSE, "Nose Piercing", "Having a nose piercing allows you to equip jewellery such as nose rings or studs.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.LIP, "Lip Piercing", "Lip piercings allow you to wear lip rings.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NAVEL, "Navel Piercing", "Getting your navel (belly button) pierced allows you to equip navel-related jewellery.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.TONGUE, "Tongue Piercing", "Getting a tongue piercing will allow you to equip tongue bars.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NIPPLE, "Nipple Piercing", "Nipple piercings will allow you to equip nipple bars.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.PENIS, "Penis Piercing", "Having a penis piercing will allow you to equip penis-related jewellery.")
				
				+CharacterModificationUtils.getKatesDivPiercings(PiercingType.VAGINA, "Vagina Piercing", "Having a vagina piercing will allow you to equip vagina-related jewellery.");
		
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNodeOld SHOP_BEAUTY_SALON_OTHER = new DialogueNodeOld("", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return "Cosmetics";
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "Kate also offers some other miscellaneous services, such as anal bleaching and body hair colouring."
					+ "</p>"
					+ "<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivAnalBleaching("Anal bleaching", "Anal bleaching is the process of lightening the colour of the skin around the anus, to make it more uniform with the surrounding area.")

//					+(Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled() || Main.game.isPubicHairEnabled()
//							?CharacterModificationUtils.getKatesDivCoveringsNew(
//									true, Main.game.getPlayer().getBodyHairCoveringType(), "Body hair", "This is the hair that covers all areas other than the head.", true, true)
//							:"")
					
					+(Main.game.isFacialHairEnabled()
							?CharacterModificationUtils.getKatesDivFacialHair("Facial hair", "The body hair found on your face. Feminine characters cannot grow facial hair.")
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair("Pubic hair", "The body hair found in the genital area; located on and around your sex organs and crotch.")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair("Underarm hair", "The body hair found in your armpits.")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair("Ass hair", "The body hair found around your asshole.")
							:"")
					);
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, bct, "Body hair", "Your body hair.", true, true));
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
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
			
			return "<p>"
						+ "You ask Kate about the different types of makeup she's able to apply, and, after reluctantly getting up out of her chair, she informs you that all of her cosmetics are enchanted to be permanent,"
						+ " so if you want to remove or change them at a later time, you'll have to come back and use her services again. If you choose to set a cosmetic's colour to 'None', then glow will also be removed."
					+ "</p>"
					+ "<h6 style='text-align:center;'>"
						+ "You currently have "+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true);
			
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
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
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
}
