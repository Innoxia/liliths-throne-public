package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.?
 * @version 0.2.2
 * @author Innoxia
 */
public class DominionSuccubusDialogue {
	
	public static final DialogueNodeOld ALLEY_DEMON_ATTACK = new DialogueNodeOld("", "You've ended up walking right into a trap!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
				return "An obvious trap";
		}

		@Override
		public String getContent() {
			
			if(Main.game.getActiveNPC().isVisiblyPregnant()){
				if(Main.game.getActiveNPC().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					return("<p>"
								+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
								+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
								+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
							+ "</p>"
							+ "<p>"
								+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
								+ " As you turn to leave, a familiar, [npc.feminine] laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
							+ "</p>"
							+ "<p>"
								+ "Looking in the direction that you were going to be travelling in, you see the [npc.feminine] figure of a [npc.race] suddenly materialise out of thin air."
								+ " A huge web, glistening pink with arcane energy, shimmers into life all around [npc.herHim], and with a snap of [npc.her] fingers, it falls harmlessly to the ground."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] steps over the fallen webbing, you recognise your tormentor as the [npc.fullRace(true)] you've run into before."
							+ "</p>"
							+ "<p>"
								+ UtilText.parse(Main.game.getActiveNPC(),
										UtilText.parseSpeech("It's <i>you</i> again?!", Main.game.getActiveNPC())
										+ " [npc.she] growls, failing to conceal the angry look in [npc.her] eyes. "
										+ UtilText.parseSpeech("Look what you fucking did!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] walks towards you, [npc.she] points down to [npc.her] stomach, and you feel your eyes going wide as you see a clearly pregnant bump in [npc.her] belly."
							+ "</p>"
							+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseThought("Do you have any idea how hard it is to ambush people when I've got to carry your little brood of imps around?", Main.game.getActiveNPC())
									+ " [npc.she] shouts, but this time, [npc.she] doesn't speak out loud, and you realise that you're hearing [npc.her] angry voice in your head. "
									+ UtilText.parseThought("So get down on your knees, <b>right now</b>, and beg to let me fuck you! Maybe then I won't be quite so mad at you!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
								+ "Although [npc.she] obviously still has a couple of tricks up [npc.her] sleeve, you haven't seen any arcane elements materialising around [npc.her] arms."
								+ " Either due to spending too much energy on setting up that trap, or perhaps because [npc.sheIs] become the mother of your children, this demon's arcane ability doesn't seem quite as potent as it should be."
								+ " Perhaps you can beat [npc.herHim] if you were to choose to fight?"
							+ "</p>");
				
				} else {
					return("<p>"
								+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
								+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
								+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
							+ "</p>"
							+ "<p>"
								+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
								+ " As you turn to leave, a familiar, [npc.feminine] laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
							+ "</p>"
							+ "<p>"
								+ "Looking in the direction that you were going to be travelling in, you see the [npc.feminine] figure of a [npc.race] suddenly materialise out of thin air."
								+ " A huge web, glistening pink with arcane energy, shimmers into life all around [npc.herHim], and with a snap of [npc.her] fingers, it falls harmlessly to the ground."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] steps over the fallen webbing, you recognise your tormentor as the [npc.fullRace(true)] you've run into before."
							+ "</p>"
							+ "<p>"
								+ UtilText.parse(Main.game.getActiveNPC(),
										UtilText.parseSpeech("It's <i>you</i> again?!", Main.game.getActiveNPC())
										+ " [npc.she] growls, failing to conceal the angry look in [npc.her] eyes. "
										+ UtilText.parseSpeech("I'm still carrying around your children, you fucking asshole!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "As [npc.she] walks towards you, [npc.she] points down to [npc.her] stomach, and you see that the pregnant bump in [npc.her] belly has only gotten bigger since the last time you saw [npc.herHim]."
							+ "</p>"
							+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseThought("Do you have any idea how hard it is to ambush people when I've got to carry your little brood of imps around?", Main.game.getActiveNPC())
									+ " [npc.she] shouts, but this time, [npc.she] doesn't speak out loud, and you realise that you're hearing [npc.her] angry voice in your head. "
									+ UtilText.parseThought("So get down on your knees, <b>right now</b>, and beg to let me fuck you! Maybe then I won't be quite so mad at you!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
								+ "Although [npc.she] obviously still has a couple of tricks up [npc.her] sleeve, you haven't seen any arcane elements materialising around [npc.her] arms."
								+ " Either due to spending too much energy on setting up that trap, or perhaps because [npc.sheIs] become the mother of your children, this demon's arcane ability doesn't seem quite as potent as it should be."
								+ " Perhaps you can beat [npc.herHim] if you were to choose to fight?"
							+ "</p>");
				}
				
			}else{
				// You've encountered this demon before:
				if(Main.game.getActiveNPC().getLastTimeEncountered() != -1) {
					return("<p>"
							+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
							+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
							+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
						+ "</p>"
						+ "<p>"
							+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
							+ " As you turn to leave, a familiar, [npc.feminine] laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
						+ "</p>"
						+ "<p>"
							+ "Looking in the direction that you were going to be travelling in, you see the [npc.feminine] figure of a [npc.race] suddenly materialise out of thin air."
							+ " A huge web, glistening pink with arcane energy, shimmers into life all around [npc.herHim], and with a snap of [npc.her] fingers, it falls harmlessly to the ground."
						+ "</p>"
						+ "<p>"
							+ "As [npc.she] steps over the fallen webbing, you recognise your tormentor as the [npc.fullRace(true)] you've run into before."
						+ "</p>"
						+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseSpeech("It's you again?!", Main.game.getActiveNPC())
									+ " [npc.she] laughs, and you see the same hungry look in [npc.her] eyes that [npc.she] had the last time you saw [npc.herHim]. "
									+ UtilText.parseSpeech("You're just asking to get fucked!", Main.game.getActiveNPC()))
						+ "</p>"
						+ "<p>"
							+ "It doesn't look like [npc.sheIs] any more capable than [npc.she] was the last time you fought [npc.herHim], and you still don't see any arcane elements materialising around [npc.her] arms."
							+ " Either due to spending too much energy on setting up that trap, or perhaps because [npc.sheIs] too horny to focus on using the arcane in combat, this demon's arcane ability doesn't seem quite as potent as it should be."
							+ " Perhaps you can beat [npc.herHim] if you were to choose to fight? But then again, you could always kneel and submit..."
						+ "</p>");
					
				} else {
					// This demon hasn't seen you before:
					return("<p>"
								+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
								+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
								+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
							+ "</p>"
							+ "<p>"
								+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
								+ " As you turn to leave, a high-pitched, [npc.feminine] laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
								+ " For a brief instant, you're reminded of that fateful night back at the museum, but just as you remind yourself that Lilith sounded nothing like this, you catch a glimpse of your elusive tormentor."
							+ "</p>"
							+ "<p>"
								+ "Looking in the direction that you were going to be travelling in, you see a [npc.feminine] figure suddenly materialise out of thin air."
								+ " A huge web, glistening pink with arcane energy, shimmers into life all around [npc.herHim], and with a snap of [npc.her] fingers, it falls harmlessly to the ground."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] steps over the fallen webbing, you see that your tormentor is, in fact, [npc.a_fullRace(true)]."
								+ " Unlike Lilaya"+(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)?" or Kate":"")+", this demon has a hungry, primal look in [npc.her] eyes, and as [npc.she] moves closer, [npc.she] starts to talk."
							+ "</p>"
							+ "<p>"
								+ UtilText.parse(Main.game.getActiveNPC(),
										UtilText.parseSpeech("Aww, how did you see my little surprise?", Main.game.getActiveNPC())
										+ " [npc.she] asks, failing to conceal the desperate look in [npc.her] eyes. "
										+ UtilText.parseSpeech("You must know how to use the arcane!"
												+ " Y'know, I'm not angry, but that little trick takes me quite a while to set up, so when someone comes along and ruins it..."
												+ " Actually, no, I <i>am</i> pretty angry!"
												+ " Do you know how long it's been since I fucked someone?! Huh?!"
												+ " <i>Five fucking hours!</i>", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "Although [npc.sheIs] been steadily walking towards you this whole time, [npc.she] suddenly stops, about three metres in front of you, and you realise that [npc.sheIs] a little unsure of what you're capable of."
							+ "</p>"
							+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseThought("So you're going to make it up to me for ruining my trap, understand?", Main.game.getActiveNPC())
									+ " [npc.she] demands, but this time, [npc.she] doesn't speak out loud, and you realise that you're hearing [npc.her] voice in your head. "
									+ UtilText.parseThought("So get down on your knees, <b>right now</b>, and beg for me to fuck you!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
								+ "Although [npc.she] obviously still has a couple of tricks up [npc.her] sleeve, you haven't seen any arcane elements materialising around [npc.her] arms."
								+ " Either due to spending too much energy on setting up that trap, or perhaps because all [npc.she] can think about is sex, this demon's arcane ability doesn't seem quite as potent as it should be."
								+ " Perhaps you can beat [npc.herHim] if you were to choose to fight?"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "This demon doesn't look so tough! You're pretty sure you can beat [npc.herHim].", Main.game.getActiveNPC()){
					@Override
					public void effects() {
						if(Main.game.getActiveNPC().isVisiblyPregnant())
							Main.game.getActiveNPC().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Kneel", "Maybe [npc.she]'ll go easy on you if you were to do as [npc.she] says?", SUBMITTING){
					@Override
					public void effects() {
						if(Main.game.getActiveNPC().isVisiblyPregnant())
							Main.game.getActiveNPC().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() { //TODO
			if(Main.game.getActiveNPC().isFeminine()) {
				return "<p>"
							+ "[npc.Name] lets out a manic, passionate moan as [npc.she] collapses back against a nearby wall."
							+ " Much to your surprise, the hungry look in [npc.her] eyes only seems to grow even fiercer in defeat, and [npc.she] pouts at you before starting to desperately plead for sex."
						+ "</p>"
						+ "<p>"
							+ UtilText.parseSpeech("Please! Let's just fuck already! I'll let you use my pussy! Or my mouth! Or even my ass! I just need to get off so badly!", Main.game.getActiveNPC())
							+" [npc.she] whines, letting out a little cry as [npc.she] hungrily looks up and down your body. "
							+ UtilText.parseSpeech("You know how demons can transform themselves, right? You want me to grow a cock? I can do that too! Anything! Please!", Main.game.getActiveNPC())
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] reaches down between [npc.her] legs with both hands, clenching [npc.her] thighs down around [npc.her] exploratory [npc.fingers] as [npc.she] carries on whining and pleading for you to use [npc.herHim]."
							+ " You wonder if you should help [npc.herHim] out, or just walk away and leave [npc.herHim] to get some release from the next person [npc.she] bumps in to."
						+ "</p>";
			} else {
				return "<p>"
						+ "[npc.Name] lets out a manic, frustrated groan as [npc.she] collapses back against a nearby wall."
						+ " Much to your surprise, the lustful look in [npc.her] eyes only seems to grow even fiercer in defeat, and [npc.she] gazes hungrily at your body before starting to desperately plead for sex."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("Please! Let's just fuck already! I'll let you use my cock! Or my mouth! Or even my ass! I just need to get off so badly!", Main.game.getActiveNPC())
						+" [npc.she] whines, letting out a little cry as [npc.she] continues, "
						+ UtilText.parseSpeech("You know how demons can transform themselves, right? You want me to grow a pussy? I can do that too! Anything! Please!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] reaches down between [npc.her] legs with both hands, clenching [npc.her] thighs down around [npc.her] exploratory [npc.fingers] as [npc.she] carries on whining and pleading for you to use [npc.herHim]."
						+ " You wonder if you should help [npc.herHim] out, or just walk away and leave [npc.herHim] to get some release from the next person [npc.she] bumps in to."
					+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave [npc.herHim]", "You don't really want to get involved with someone like this. Turn around and carry on your way.", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Sex",
						"Tell the [npc.race] that you want to fuck [npc.herHim] just as [npc.she] is.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						AFTER_SEX_VICTORY, "<p>"
							+ "Satisfied with how [npc.name] looks, you decide to give [npc.herHim] what [npc.she] wants, and, stepping forwards, you allow [npc.herHim] to move towards you, grinning as [npc.she] starts grinding [npc.herself] up against you."
							+ " Pressing your [pc.lips] to [npc.hers] and delivering a passionate kiss, you reach around and grope [npc.namePos] ass as [npc.she] moans hotly into your mouth..."
						+ "</p>");
				
			} else if (index == 3) {
				return new Response("Quick transformations",
						"If all [npc.she] wants is sex, then you're more than happy to oblige. Besides, if [npc.sheIs] able to transform [npc.herself], you have a few ideas in mind... (Proceed to sex with a few choices.)",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
				
			} else if (index == 4) {
				return new Response("Full transformations",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getActiveNPC());
					}
				};
				
			} else if (index == 5) {
				return new ResponseSex("Submit",
						"Seeing the desperate, whining form of the horny [npc.race] is proving to be too much for you to bear."
								+ " Perhaps you could cheer [npc.herHim] up by <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submitting to [npc.herHim]</b> and letting [npc.herHim] use your body?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null, AFTER_SEX_DEFEAT, "<p>"
							+ "As "+Main.game.getActiveNPC().getName("the")+" carries on whining and desperately touching [npc.herself], you start to feel incredibly sorry for [npc.herHim]."
							+ " Stepping forwards, you lean down and look up into [npc.her] "+Main.game.getActiveNPC().getEyeName()+". "
							+UtilText.parsePlayerSpeech("I'm sorry! I didn't mean to upset you!")
						+ "</p>"
						+ "<p>"
						+ "Your efforts at calming "+Main.game.getActiveNPC().getName("the")+" down only seem to make [npc.herHim] whine out even more, and,"
								+ " feeling extremely sorry for the poor, horny demon, you make an offer that you might soon regret..."
						+ "</p>"
						+ "<p>"
						+UtilText.parsePlayerSpeech("So, you need me to help you get some release?")
						+ "</p>"
						+ "<p>"
						+ Main.game.getActiveNPC().getName("The")+"'s eyes flick up as [npc.she] stops whining for a moment. "
						+ UtilText.parseSpeech("Yes... But I can't get off unless I'm the one in charge...", Main.game.getActiveNPC())
						+ "</p>"
						+ "<p>"
						+UtilText.parsePlayerSpeech("Well, in that case, I'm happy to let yo- ~Mrph!~")
						+ "</p>"
						+ "<p>"
						+ Main.game.getActiveNPC().getName("The")+" suddenly rocks forwards, pressing [npc.her] lips against yours as [npc.she] pulls you into a passionate kiss."
							+ " [npc.Her] whining instantly stopped as you started to offer your body to [npc.herHim], and as [npc.she] grinds up against you, the only noises that come from [npc.her] mouth are deep, pleasurable moans."
						+ "</p>"
						+ "<p>"
							+ "Before you really know what's going on, [npc.sheIs] pulled you up to your feet, and as [npc.her] body presses up tightly against yours, you feel a strange bulge digging into your leg."
							+ " Breaking off the kiss for a moment, you look down, and your eyes go wide as you see the distinctive shape of a massive erection between [npc.namePos] legs."
							+ " You gulp as you remember what [npc.she] said just moments ago, and you realise that [npc.sheIs] growing [npc.herself] a cock to fuck you with."
						+ "</p>"
						+ "<p>"
							+ "Seeing what you're looking at, the [npc.race] lets out a playful giggle. "
							+ UtilText.parseSpeech("What? You didn't seriously think I'd let you use my pussy, did you?"
									+ " You're the one who's offering to help here, remember?! If you didn't want to be my little fuck-toy, perhaps you should have just walked away!", Main.game.getActiveNPC())
						+ "</p>"
						+ "<p>"
							+ "Before you can respond, [npc.she] leans forwards and presses [npc.her] lips against yours once more, thrusting [npc.her] forked tongue into your mouth as [npc.she] lets out a happy moan."
							+ " You're far too submissive to consider putting a stop to this, and you find yourself desperately kissing [npc.herHim] back as you resign yourself to your fate..."
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare "+Main.game.getActiveNPC().getName("the")+" away. <b>This will remove "+Main.game.getActiveNPC().getName("the")+" from this area, allowing another NPC to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_PENIS = new DialogueNodeOld("Transformations", "", true, true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You can't pass up an opportunity like this, and as you step towards the horny [npc.race], [npc.she] lets out a delighted squeal, "
						+ UtilText.parseSpeech("Yes! Come fuck me!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
					+ "Jumping up onto [npc.her] feet, [npc.she] leaps forwards and tries to pull you into a kiss."
					+ " Wanting to take [npc.herHim] up on [npc.her] offer of transforming [npc.herself], you grab [npc.her] shoulders and prevent [npc.herHim] from getting too close just yet."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No cock",
						"Tell the [npc.race] that you don't want [npc.herHim] to have a cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.NONE);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Not really liking the idea of [npc.name] having a cock, you tell [npc.herHim] as much, "
								+	UtilText.parsePlayerSpeech("I don't want you having a cock, ok?")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the [npc.race] moans, "
								+ UtilText.parseSpeech("Aww! You're no fun... Let's just get started already!", Main.game.getActiveNPC())
								+"</p>");
					}
				};
				
			} else if (index == 2) {
				return new Response("Tiny cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a tiny little 1-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(1);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.ONE_TRICKLE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that [npc.name] should have a cute little sissy-cock, you order [npc.herHim] to transform, "
								+	UtilText.parsePlayerSpeech("I think a little bit of humiliation would do you good! You're going to give yourself a cute little cock for me, understood? And keep it tiny!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the [npc.race] moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at [npc.her] crotch, you don't see any sign of a bulge beneath [npc.her] clothes, but then again, if [npc.sheIs] kept it tiny like you asked, you wouldn't expect to see anything there anyway."
								+ "</p>");
					}
				};
				
			} else if (index == 3) {
				return new Response("Average-sized cock",
						"Tell the [npc.race] that you want [npc.herHim] to have an average, 6-inch, cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(6);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.THREE_AVERAGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that [npc.she] should have an average-sized cock, you order [npc.name] to grow one, "
								+	UtilText.parsePlayerSpeech("You're going to give yourself a nice demonic cock for me, understood? And keep it a reasonable size! I don't want to see anything too big!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the [npc.race] moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at [npc.her] crotch, you see a small bulge beneath [npc.her] clothes, and you find yourself smiling in delight as you realise that the [npc.race] is going to do anything you command."
								+ "</p>");
					}
				};
				
			} if (index == 4) {
				return new Response("Large cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a large, 11-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(11);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that [npc.she] should have a nice, big cock, you order the [npc.race] to transform, "
								+	UtilText.parsePlayerSpeech("You're going to give yourself a nice big demonic cock, understood? And I'm serious about it being big!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the [npc.race] moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at [npc.her] crotch, you see a large bulge beneath [npc.her] clothes, and you find yourself smiling in delight as you realise that the [npc.race] is going to do anything you command."
								+ "</p>");
					}
				};
				
			} if (index == 5) {
				return new Response("Enormous cock",
						"Tell the [npc.race] that you want [npc.herHim] to have a massive 19-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that [npc.she] should have the biggest cock [npc.sheIs] able to grow, you order the [npc.race] to get started, "
								+	UtilText.parsePlayerSpeech("You're going to give yourself a huge demonic cock, understood? And make it as big as possible! If you aren't hung like a horse, I'm not interested!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the [npc.race] moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at [npc.her] crotch, you see a massive bulge beneath [npc.her] clothing, and you find yourself smiling in delight as you realise that the [npc.race] is going to do anything you command."
								+ "</p>");
					}
				};
				
			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_VAGINA = new DialogueNodeOld("Transformations", "", true, true) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Panting and moaning, the [npc.race] pleads with you, "
						+ UtilText.parseSpeech("Come on! There isn't anything else is there? Let's just get going already!", Main.game.getActiveNPC())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Vagina",
						"Tell the [npc.race] that you want [npc.herHim] to have a demonic pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setVaginaType(VaginaType.DEMON_COMMON);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Wanting to make sure that [npc.sheIs] got a nice demonic pussy, you order [npc.herHim], "
								+	UtilText.parsePlayerSpeech("Make sure you've got a tight little demonic pussy! I've got plans for it!")
								+ "</p>"
								+ "<p>"
								+ "By now [npc.name] has stopped trying to get out of your grasp, and just obediently sighs, "
								+ UtilText.parseSpeech("Ok, ok! I was planning on having one for this anyway! Now let's start! Come on!", Main.game.getActiveNPC())
								+"</p>");
					}
				};
				
			} else if (index == 2) {
				return new Response("No Vagina",
						"Tell the [npc.race] that [npc.she] won't be needing a pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer(), true);
						Main.game.getActiveNPC().setVaginaType(VaginaType.NONE);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Not having any plans to use [npc.her] pussy, you bark out your second order, "
								+	UtilText.parsePlayerSpeech("I don't want to see a pussy between your legs, understood? You're not getting off like that with me!")
								+ "</p>"
								+ "<p>"
								+ "By now the [npc.race] has stopped trying to get out of your grasp, but [npc.she] shuffles around uncomfortably and lets out a mortified groan as [npc.she] hears your order, "
								+ UtilText.parseSpeech("Aww! But it's not a real orgasm if I don't have a pussy!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Unimpressed by [npc.her] reluctance to do as [npc.sheIs] told, you give [npc.herHim] a final ultimatum, "
								+	UtilText.parsePlayerSpeech("If you don't do as you're told, I'll just walk away, and you can wait for another five hours before someone else comes walking by.")
								+ "</p>"
								+ "<p>"
								+ UtilText.parseSpeech("Fine! Fine... Just... Hold on... Ok... It's gone...", Main.game.getActiveNPC())
								+"</p>");
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_BREASTS = new DialogueNodeOld("Transformations", "", true, true) { //TODO reactions
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isFeminine()) {
				return "<p>"
							+ "Letting out a pitiful whine, the [npc.race] stares up into your eyes. "
							+ UtilText.parseSpeech("You can't drag this out any more! Come on already!", Main.game.getActiveNPC())
						+ "</p>"
						+ "<p>"
							+ "Ignoring [npc.her] request, you wonder if you should get [npc.herHim] to perform one final transformation."
							+ " [npc.Her] huge, [npc.cupSize]-cup breasts might need some work, after all..."
						+ "</p>";
			} else {
				return "<p>"
						+ "Letting out a pitiful whine, the [npc.race] stares up into your eyes. "
						+ UtilText.parseSpeech("You can't drag this out any more! Come on already!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "Ignoring [npc.her] request, you wonder if you should get [npc.herHim] to perform one final transformation."
						+ " You could always tell [npc.herHim] to transform into a feminine succubus, after all..."
					+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isFeminine()) {
				if (index == 1) {
					return new ResponseSex("AA-cup",
							"Tell the [npc.race] to make [npc.her] breasts tiny little AA-cups.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to humiliate [npc.herHim] a little, you order [npc.herHim] to get rid of [npc.her] breasts, "
								+	UtilText.parsePlayerSpeech("These massive tits of yours are far too big! You're going to shrink them right down, understand? If they're anything bigger than little double-A's, I'm walking away!")
							+ "</p>"
							+ "<p>"
								+ "With a desperate sob, the [npc.race] does as you command. "
								+ UtilText.parseSpeech("Really?! Aah... I just need to get fucked so bad... Fine!", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "Looking down at [npc.her] chest, you see [npc.her] huge, E-cup breasts rapidly shrink down, and after just a couple of seconds, [npc.sheIs] almost completely flat-chested."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setBreastSize(CupSize.AA.getMeasurement());
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("C-cup",
							"Tell the [npc.race] to make [npc.her] breasts a more reasonable, C-cup size.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to reduce the size of [npc.her] huge breasts a little, you order [npc.herHim] to make the change, "
								+	UtilText.parsePlayerSpeech("These massive tits of yours are far too big! Make them a more reasonable size! C-cups should do just fine.")
							+ "</p>"
							+ "<p>"
								+ "With a desperate moan, the [npc.race] does as you command. "
								+ UtilText.parseSpeech("Aww... Fine!", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "Looking down at [npc.her] chest, you see [npc.her] huge, E-cup breasts rapidly shrink down, and after just a couple of seconds, [npc.sheIs] left with a much more reasonable bust."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setBreastSize(CupSize.C.getMeasurement());
						}
					};
					
				} else if (index == 3) {
					return new ResponseSex("E-cup",
							"Tell the [npc.race] to keep [npc.her] breasts as E-cups.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Looking down at [npc.her] chest, you find yourself happy enough already as you see [npc.her] huge, E-cup breasts."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setBreastSize(CupSize.E.getMeasurement());
						}
					};
					
				} else if (index == 4) {
					return new ResponseSex("H-cup",
							"Tell the [npc.race] to grow [npc.her] breasts into huge H-cups.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to make [npc.her] breasts a little bigger, you order [npc.herHim] to make the change, "
								+	UtilText.parsePlayerSpeech("I thought succubi liked having big breasts? I don't know what you'd call those things, but they're definitely not big by my standards."
										+ " Make them at least an H-cup, and we can finally get started.")
							+ "</p>"
							+ "<p>"
								+ "You see the [npc.race]'s cheeks flash red, and you realise that you've obviously touched a nerve. "
								+ UtilText.parseSpeech("W-What?! But everyone always says that these are big enough! F-Fine... I can make them bigger for you...", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "Looking down at [npc.her] chest, you see [npc.her] huge, E-cup breasts start to expand, and after just a couple of seconds, [npc.sheIs] left with a massive, H-cup bust."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setBreastSize(CupSize.H.getMeasurement());
						}
					};
					
				} else if (index == 5) {
					return new ResponseSex("N-cup",
							"Tell the [npc.race] that you want [npc.herHim] to make [npc.her] breasts as large as possible, which will leave [npc.herHim] with massive N-cup tits.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to make [npc.her] breasts as big as possible, you order [npc.herHim] to make the change, "
								+	UtilText.parsePlayerSpeech("I thought succubi liked having big breasts? I don't know what you'd call those things, but they're definitely not big by my standards."
										+ " I want to see just how big you can make them!")
							+ "</p>"
							+ "<p>"
								+ "You see the [npc.race]'s cheeks flash red, and you realise that you've obviously touched a nerve. "
								+ UtilText.parseSpeech("W-What?! But everyone always says that these are big enough! F-Fine... I can make them bigger...", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "Looking down at [npc.her] chest, you see [npc.her] huge, E-cup breasts start to expand, and after just a couple of seconds, [npc.sheIs] left with an absolutely massive, N-cup bust."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setBreastSize(CupSize.N.getMeasurement());
						}
					};
					
				} else {
					return null;
				}
			
			} else {
				if (index == 1) {
					return new ResponseSex("Incubus",
							"Tell the [npc.race] that you're ready to use [npc.herHim] now.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("AA-cup Succubus",
							"Tell the [npc.race] to transform into a succubus with tiny little AA-cup breasts.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to humiliate him a little, you order [npc.name] to transform into a succubus, "
								+	UtilText.parsePlayerSpeech("I think you'd look far better as a succubus! Transform yourself into one right now, and if your tits are anything bigger than little double-A's, I'm walking away!")
							+ "</p>"
							+ "<p>"
								+ "With a desperate sob, he does as you command. "
								+ UtilText.parseSpeech("Really?! Aah... I just need to get fucked so bad... Fine!", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "As you look on, the incubus' form shifts and changes, and after just a couple of seconds, an almost completely flat-chested succubus stands before you."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setFemininity(90);
							Main.game.getActiveNPC().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
							Main.game.getActiveNPC().setBreastSize(CupSize.AA.getMeasurement());
						}
					};
					
				} else if (index == 3) {
					return new ResponseSex("C-cup Succubus",
							"Tell the [npc.race] to transform into a succubus with C-cup breasts.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to humiliate him a little, you order [npc.name] to transform into a succubus, "
								+	UtilText.parsePlayerSpeech("I think you'd look far better as a succubus! Transform yourself into one right now, and make sure your tits are a more reasonable size! C-cups should do just fine.")
							+ "</p>"
							+ "<p>"
								+ "With a desperate moan, the [npc.race] does as you command. "
								+ UtilText.parseSpeech("Aww... Fine!", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
							+ "As you look on, the incubus' form shifts and changes, and after just a couple of seconds, a smoking hot succubus stands before you."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setFemininity(90);
							Main.game.getActiveNPC().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
							Main.game.getActiveNPC().setBreastSize(CupSize.C.getMeasurement());
						}
					};
					
				} else if (index == 4) {
					return new ResponseSex("E-cup Succubus",
							"Tell the [npc.race] to transform into a succubus with E-cup breasts.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to humiliate him a little, you order [npc.name] to transform into a succubus, "
								+	UtilText.parsePlayerSpeech("I think you'd look far better as a succubus! Transform yourself into one right now, and make sure your tits are nice and big! E-cups should do just fine.")
							+ "</p>"
							+ "<p>"
								+ "With a desperate moan, the [npc.race] does as you command. "
								+ UtilText.parseSpeech("Aww... Fine!", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "As you look on, the incubus' form shifts and changes, and after just a couple of seconds, a smoking hot succubus with large, E-cup breasts stands before you."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setFemininity(90);
							Main.game.getActiveNPC().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
							Main.game.getActiveNPC().setBreastSize(CupSize.E.getMeasurement());
						}
					};
					
				} else if (index == 5) {
					return new ResponseSex("H-cup Succubus",
							"Tell the [npc.race] to transform into a succubus with huge, H-cup breasts.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
								+ "Wanting to humiliate him a little, you order [npc.name] to transform into a succubus, "
								+	UtilText.parsePlayerSpeech("I think you'd look far better as a succubus! Transform yourself into one right now, and make sure your tits are huge! H-cups at least!")
							+ "</p>"
							+ "<p>"
								+ "With a desperate moan, the [npc.race] does as you command. "
								+ UtilText.parseSpeech("Aww... Fine!", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "As you look on, the incubus' form shifts and changes, and after just a couple of seconds, a smoking hot succubus with huge, H-cup breasts stands before you."
								+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
							+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setFemininity(90);
							Main.game.getActiveNPC().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
							Main.game.getActiveNPC().setBreastSize(CupSize.H.getMeasurement());
						}
					};
					
				} else if (index == 6) {
					return new ResponseSex("N-cup Succubus",
							"Tell the [npc.race] to transform into a succubus with breasts as large as possible, which will leave [npc.herHim] with massive N-cup tits.",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY, "<p>"
									+ "Wanting to humiliate him a little, you order [npc.name] to transform into a succubus, "
									+	UtilText.parsePlayerSpeech("I think you'd look far better as a succubus! Transform yourself into one right now, and make sure your tits are as large as possible!")
								+ "</p>"
								+ "<p>"
									+ "With a desperate moan, the [npc.race] does as you command. "
									+ UtilText.parseSpeech("Aww... Fine!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
									+ "As you look on, the incubus' form shifts and changes, and after just a couple of seconds, a smoking hot succubus with enormous, N-cup breasts stands before you."
									+ " Satisfied with your changes, you decide to finally give the [npc.race] what [npc.she] wants, and, releasing [npc.her] shoulders, you allow [npc.herHim] to step forwards and grind [npc.herself] up against you."
									+ " Leaning down to deliver a passionate kiss, you reach around and grope the [npc.race]'s ass as [npc.she] moans hotly into your mouth."
								+ "</p>"){
						@Override
						public void effects() {
							Main.game.getActiveNPC().setFemininity(90);
							Main.game.getActiveNPC().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
							Main.game.getActiveNPC().setBreastSize(CupSize.N.getMeasurement());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by " + Main.game.getActiveNPC().getName("the") + "!";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You simply can't bring yourself to carry on fighting, and you stumble back against a nearby wall, panting heavily from exertion."
						+ " Glancing up, you see a delighted smile flash across the [npc.race]'s face as [npc.she] steps towards you."
					+ "</p>"
					+ "<p>"
						+ "Moving up close before starting to grind [npc.herself] up against your leg, [npc.she] moans in your ear, "
						+ UtilText.parseSpeech("~Aah!~ Yes! Finally, I can get some release!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "As [npc.her] body presses tightly against yours, you feel a bulge pressing against your leg."
						+ " Looking down, your eyes go wide as you see the distinctive shape of a massive erection forming beneath the fabric of [npc.her] clothes."
						+ " You didn't see any signs of a bulge between [npc.her] legs while you were fighting [npc.herHim], and you gulp as you suddenly realise that [npc.sheIs] using [npc.her] demonic powers to grow [npc.herself] a huge cock to fuck you with."
					+ "</p>"
					+ "<p>"
						+ "Seeing what you're looking at, the [npc.race] lets out a playful laugh, "
						+ UtilText.parseSpeech("What? You didn't seriously think I'd grow a pussy for you to fuck, did you? Perhaps if you didn't want to be my little fuck-toy, you should have fought harder, hmm?", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "Before you can respond, [npc.she] leans forwards and presses [npc.her] lips against yours, thrusting [npc.her] [npc.tongue+] into your mouth as [npc.she] lets out a happy moan."
						+ " You're far too weak to resist [npc.her] advances, and you find yourself desperately kissing [npc.herHim] back as you resign yourself to your fate..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("[npc.Race]'s toy",
						"The [npc.race] is ready to use you as [npc.her] little fuck-toy...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null, AFTER_SEX_DEFEAT){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SUBMITTING = new DialogueNodeOld("Submit", "", true) {
		
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You kneel before the [npc.race].";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You don't really want to fight " + Main.game.getActiveNPC().getName("the") + ", and decide to just do as [npc.she] asks."
					+ " Sinking to your knees, you look up at [npc.herHim] and beg, "
					+ UtilText.parsePlayerSpeech("Please! Can you fuck me?")
				+ "</p>"
				+ "<p>"
					+ "Looking a little surprised for a moment, the [npc.race] quickly regains [npc.her] composure and strides towards you. "
					+ UtilText.parseSpeech("Hah! Well, that was easy! What a good little submissive thing you are!", Main.game.getActiveNPC())
				+ "</p>"
				+ "<p>"
					+ "Quickly coming to a halt just in front of you, [npc.she] leans down and grabs your arm, pulling you back up to your feet before pressing [npc.her] body up tightly against yours."
					+ " As [npc.she] starts to affectionately grind up against you, you feel a bulge pressing against your leg."
					+ " Looking down, your eyes go wide as you see the distinctive shape of a massive erection forming beneath the fabric of [npc.her] clothes."
					+ " You didn't see any signs of a bulge between [npc.her] legs a moment ago, and you gulp as you suddenly realise that [npc.sheIs] using [npc.her] demonic powers to grow [npc.herself] a huge cock to fuck you with."
				+ "</p>"
				+ "<p>"
					+ "Seeing what you're looking at, the [npc.race] lets out a playful laugh, "
					+ UtilText.parseSpeech("What? You didn't seriously think I'd grow a pussy for a submissive little bitch like you to use, did you?"
							+ " But you're an obedient little thing, I just know that you're going to love my cock!", Main.game.getActiveNPC())
				+ "</p>"
				+ "<p>"
					+ "Before you can respond, [npc.she] leans forwards and presses [npc.her] lips against yours, thrusting [npc.her] [npc.tongue+] into your mouth as [npc.she] lets out a happy moan."
					+ " Having already made the conscious decision to submit to this horny demon, you find yourself desperately kissing [npc.herHim] back as you resign yourself to your fate..."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("[npc.Race]'s toy",
						"The [npc.race] is ready to use you as [npc.her] little fuck-toy...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null, AFTER_SEX_DEFEAT){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setPenisCumStorage(CumProduction.FIVE_HUGE.getMedianValue());
						Main.game.getActiveNPC().setPenisStoredCum(CumProduction.FIVE_HUGE.getMedianValue());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave "+Main.game.getActiveNPC().getName("the")+" to recover.";
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner())==0) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, whining and desperately touching [npc.herself] as [npc.she] tries to get [npc.herself] off."
							+ " Looking up at you, [npc.she] moans, "
							+ UtilText.parseSpeech("Aaah! Why did you stop?! I was so close!", Main.game.getActiveNPC())
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a deeply satisfied sigh as [npc.she] slowly starts touching [npc.herself]."
							+ " Despite the fact that you just brought [npc.herHim] to a climax, [npc.sheIs] obviously still incredibly horny, and as [npc.her] sighs turn into lewd moans,"
								+ " you wonder what it must be like to live in a perpetual state of uncontrollable lust..."
						+ "</p>");
				
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from "+Main.game.getActiveNPC().getName("the")+"'s dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
					+ "As [npc.name] steps back, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
					+ " Glancing up at the horny demon, you see [npc.her] demonic cock start to shrink, and within moments, it's completely disappeared back into the flesh of [npc.her] groin."
					+ " Leaning down, [npc.she] grins devilishly at you as [npc.she] sighs, "
					+ UtilText.parseSpeech("Good little fuck toy! Perhaps I'll use you again some time!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
					+ "Turning [npc.her] back on you, [npc.she] walks off down a nearby alleyway, leaving you to recover on the floor."
					+ " It takes a little while for you to catch your breath and get your clothing in order, but eventually you feel strong enough to carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENSLAVEMENT_DIALOGUE = new DialogueNodeOld("New Slave", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return ".";
		}

		@Override
		public String getContent() {//TODO
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "TODO<br/>"
						+ "You clasp the collar around [npc.namePos] neck.<br/>"
						+ "The arcane enchantment recognises [npc.herHim] as being a criminal, and, with a purple flash, <b>they're teleported to the 'Slave Administration' building in Slaver Alley, where they'll be waiting for you to pick them up</b>."
					+ "</p>"
					+ "<p>"
						+ "Just before they disappear, glowing purple lettering appears on the collar's surface, which reads:<br/>"
						+ "Slave identification: [style.boldArcane("+Main.game.getActiveNPC().getNameIgnoresPlayerKnowledge()+")]"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", ENSLAVEMENT_DIALOGUE){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPlayerKnowsName(true);
						Main.game.getActiveNPC().setAffection(Main.game.getPlayer(), -100+Util.random.nextInt(10));
						Main.game.getActiveNPC().setObedience(-100+Util.random.nextInt(10));
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
