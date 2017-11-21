package com.lilithsthrone.game.dialogue.npcDialogue;

import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMDomStanding;
import com.lilithsthrone.game.sex.managers.universal.SMSubStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

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
				if(Main.game.getActiveNPC().isReactedToPregnancy()) {
					return("<p>"
								+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
								+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
								+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
							+ "</p>"
							+ "<p>"
								+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
								+ " As you turn to leave, a familiar, feminine laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
							+ "</p>"
							+ "<p>"
								+ "Looking in the direction that you were going to be travelling in, you see the feminine figure of a succubus suddenly materialise out of thin air."
								+ " A huge web, glistening pink with arcane energy, shimmers into life all around her, and with a snap of her fingers, it falls harmlessly to the ground."
							+ "</p>"
							+ "<p>"
								+ "As she steps over the fallen webbing, you recognise your tormentor as the"
								+ " <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"+Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), false)+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>" + Main.game.getActiveNPC().getName() + "</b>"
								+ " you've run into before."
							+ "</p>"
							+ "<p>"
								+ UtilText.parse(Main.game.getActiveNPC(),
										UtilText.parseSpeech("It's <i>you</i> again?!", Main.game.getActiveNPC())
										+ " [npc.she] growls, failing to conceal the angry look in [npc.her] eyes, "
										+ UtilText.parseSpeech("Look what you fucking did!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "As she walks towards you, she points down to her stomach, and you feel your eyes going wide as you see a clearly pregnant bump in her belly."
							+ "</p>"
							+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseThought("Do you have any idea how hard it is to ambush people when I've got to carry your little brood of imps around?", Main.game.getActiveNPC())
									+ " [npc.she] shouts, but this time, [npc.she] doesn't speak out loud, and you realise that you're hearing [npc.her] angry voice in your head, "
									+ UtilText.parseThought("So get down on your knees, <b>right now</b>, and beg to let me fuck you! Maybe then I won't be quite so mad at you!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "Although she obviously still has a couple of tricks up her sleeve, you haven't seen any arcane elements materialising around her arms."
							+ " Either due to spending too much energy on setting up that trap, or perhaps because she's become the mother of your children, this demon's arcane ability doesn't seem quite as potent as it should be."
							+ " Perhaps you can beat her if you were to choose to fight?"
							+ "</p>");
				
				} else {
					return("<p>"
								+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
								+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
								+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
							+ "</p>"
							+ "<p>"
								+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
								+ " As you turn to leave, a familiar, feminine laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
							+ "</p>"
							+ "<p>"
								+ "Looking in the direction that you were going to be travelling in, you see the feminine figure of a succubus suddenly materialise out of thin air."
								+ " A huge web, glistening pink with arcane energy, shimmers into life all around her, and with a snap of her fingers, it falls harmlessly to the ground."
							+ "</p>"
							+ "<p>"
								+ "As she steps over the fallen webbing, you recognise your tormentor as the"
								+ " <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"+Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), false)+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>" + Main.game.getActiveNPC().getName() + "</b>"
								+ " you've run into before."
							+ "</p>"
							+ "<p>"
								+ UtilText.parse(Main.game.getActiveNPC(),
										UtilText.parseSpeech("It's <i>you</i> again?!", Main.game.getActiveNPC())
										+ " [npc.she] growls, failing to conceal the angry look in her eyes, "
										+ UtilText.parseSpeech("I'm still carrying around your children, you fucking asshole!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "As she walks towards you, she points down to her stomach, and you see that the pregnant bump in her belly has only gotten bigger since the last time you saw her."
							+ "</p>"
							+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseThought("Do you have any idea how hard it is to ambush people when I've got to carry your little brood of imps around?", Main.game.getActiveNPC())
									+ " [npc.she] shouts, but this time, [npc.she] doesn't speak out loud, and you realise that you're hearing [npc.her] angry voice in your head, "
									+ UtilText.parseThought("So get down on your knees, <b>right now</b>, and beg to let me fuck you! Maybe then I won't be quite so mad at you!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "Although she obviously still has a couple of tricks up her sleeve, you haven't seen any arcane elements materialising around her arms."
							+ " Either due to spending too much energy on setting up that trap, or perhaps because she's become the mother of your children, this demon's arcane ability doesn't seem quite as potent as it should be."
							+ " Perhaps you can beat her if you were to choose to fight?"
							+ "</p>");
				}
				
			}else{
				// You've encountered this demon before:
				if(Main.game.getActiveNPC().getLastTimeEncountered()!=-1) {
					return("<p>"
							+ "Although Dominion's alleyways are eerily quiet for most of the time, you suddenly start to feel a deep, unsettling stillness descending upon you."
							+ " Stopping in your tracks, you glance back the way you came, before looking all around for any sign of danger."
							+ " The passageway that you're currently standing in looks to be clear, but you can't shake the feeling that you're about to walk right into a trap."
						+ "</p>"
						+ "<p>"
							+ "Deciding that it's better to be safe than sorry, you start to take a step backwards, with the intention of doubling back on yourself and finding another route through the maze-like lanes."
							+ " As you turn to leave, a familiar, feminine laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
						+ "</p>"
						+ "<p>"
							+ "Looking in the direction that you were going to be travelling in, you see the feminine figure of a succubus suddenly materialise out of thin air."
							+ " A huge web, glistening pink with arcane energy, shimmers into life all around her, and with a snap of her fingers, it falls harmlessly to the ground."
						+ "</p>"
						+ "<p>"
							+ "As she steps over the fallen webbing, you recognise your tormentor as the"
							+ " <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"+Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), false)+"</b>"
							+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
							+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>" + Main.game.getActiveNPC().getName() + "</b>"
							+ " you've run into before."
						+ "</p>"
						+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseSpeech("It's you again?!", Main.game.getActiveNPC())
									+ " [npc.she] laughs, and you see the same hungry look in her eyes that [npc.she] had the last time you saw [npc.herHim], "
									+ UtilText.parseSpeech("You're just asking to get fucked!", Main.game.getActiveNPC()))
						+ "</p>"
						+ "<p>"
							+ "It doesn't look like [npc.she]'s any more capable than [npc.she] was the last time you fought [npc.herHim], and you still don't see any arcane elements materialising around her arms."
							+ " Either due to spending too much energy on setting up that trap, or perhaps because she's too horny to focus on using the arcane in combat, this demon's arcane ability doesn't seem quite as potent as it should be."
							+ " Perhaps you can beat her if you were to choose to fight? But then again, you could always kneel and submit..."
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
								+ " As you turn to leave, a high-pitched, feminine laughter suddenly rings out, echoing off the walls and leaving you turning this way and that as you try to locate the source of the sound."
								+ " For a brief instant, you're reminded of that fateful night back at the museum, but just as you remind yourself that Lilith sounded nothing like this, you catch a glimpse of your elusive tormentor."
							+ "</p>"
							+ "<p>"
								+ "Looking in the direction that you were going to be travelling in, you see a feminine figure suddenly materialise out of thin air."
								+ " A huge web, glistening pink with arcane energy, shimmers into life all around her, and with a snap of her fingers, it falls harmlessly to the ground."
							+ "</p>"
							+ "<p>"
								+ "As she steps over the fallen webbing, you see that your tormentor is, in fact, "
								+ " <b style='color:"+Femininity.valueOf(Main.game.getActiveNPC().getFemininityValue()).getColour().toWebHexString()+";'>"+Femininity.getFemininityName(Main.game.getActiveNPC().getFemininityValue(), true)+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRaceStage().getColour().toWebHexString()+";'>" +Main.game.getActiveNPC().getRaceStage().getName()+"</b>"
								+ " <b style='color:"+Main.game.getActiveNPC().getRace().getColour().toWebHexString()+";'>" + Main.game.getActiveNPC().getName() + "</b>"
								+ ". Unlike Lilaya"+(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)?" or Kate":"")+", this demon has a hungry, primal look in her eyes, and as she moves closer, she starts to talk."
							+ "</p>"
							+ "<p>"
								+ UtilText.parse(Main.game.getActiveNPC(),
										UtilText.parseSpeech("Aww, how did you see my little surprise?", Main.game.getActiveNPC())
										+ " [npc.she] asks, failing to conceal the desperate look in [npc.her] eyes, "
										+ UtilText.parseSpeech("You must know how to use the arcane!"
												+ " Y'know, I'm not angry, but that little trick takes me quite a while to set up, so when someone comes along and ruins it..."
												+ " Actually, no, I <i>am</i> pretty angry!"
												+ " Do you know how long it's been since I fucked someone?! Huh?!"
												+ " <i>Five fucking hours!</i>", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "Although she's been steadily walking towards you this whole time, she suddenly stops, about three metres in front of you, and you realise that she's a little unsure of what you're capable of."
							+ "</p>"
							+ "<p>"
							+ UtilText.parse(Main.game.getActiveNPC(),
									UtilText.parseThought("So you're going to make it up to me for ruining my trap, understand?", Main.game.getActiveNPC())
									+ " [npc.she] demands, but this time, [npc.she] doesn't speak out loud, and you realise that you're hearing [npc.her] voice in your head, "
									+ UtilText.parseThought("So get down on your knees, <b>right now</b>, and beg to let me fuck you!", Main.game.getActiveNPC()))
							+ "</p>"
							+ "<p>"
							+ "Although she obviously still has a couple of tricks up her sleeve, you haven't seen any arcane elements materialising around her arms."
							+ " Either due to spending too much energy on setting up that trap, or perhaps because all she can think about is sex, this demon's arcane ability doesn't seem quite as potent as it should be."
							+ " Perhaps you can beat her if you were to choose to fight?"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "This demon doesn't look so tough! You're pretty sure you can beat her.", ALLEY_DEMON_ATTACK, Main.game.getActiveNPC()){
					@Override
					public void effects() {
						if(Main.game.getActiveNPC().isVisiblyPregnant())
							Main.game.getActiveNPC().setReactedToPregnancy(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("Kneel", "Maybe she'll go easy on you if you were to do as she says?", SUBMITTING){
					@Override
					public void effects() {
						if(Main.game.getActiveNPC().isVisiblyPregnant())
							Main.game.getActiveNPC().setReactedToPregnancy(true);
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
			return "You have defeated " + Main.game.getActiveNPC().getName("the") + "!";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ Main.game.getActiveNPC().getName("The") + " lets out a manic, passionate moan as she collapses back against a nearby wall."
						+ " Much to your surprise, the hungry look in her eyes only seems to grow even fiercer in defeat, and she pouts at you before starting to desperately plead for sex."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeech("Please! Let's just fuck already! I'll let you use my pussy! Or my mouth! Or even my ass! I just need to get off so badly!", Main.game.getActiveNPC())
						+" she whines, letting out a little cry as she hungrily looks up and down your body, "
						+ UtilText.parseSpeech("You know how demons can transform themselves, right? You want me to grow a cock? I can do that too! Anything! Please!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
					+ "With that, she reaches down between her legs and starts pressing her dress up against her concealed pussy, clenching her thighs down around her hand as she carries on whining and pleading for you to use her."
					+ " You wonder if you should help her out, or just walk away and leave her to get some release from the next person she bumps in to."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave her", "You don't really want to get involved with someone like this. Turn around and carry on your way.", null){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 2) {
				return new Response("Transformations",
						"If all she wants is sex, then you're more than happy to oblige. Besides, if she's able to transform herself, you have a few ideas in mind...",
						AFTER_COMBAT_TRANSFORMATIONS_PENIS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Submit",
						"Seeing the desperate, whining form of the horny succubus is proving to be too much for you to bear."
								+ " Perhaps you could cheer her up by <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submitting to her</b> and letting her use your body?",
						AFTER_SEX_DEFEAT,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true, Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT,
						"<p>"
							+ "As "+Main.game.getActiveNPC().getName("the")+" carries on whining and desperately touching herself, you start to feel incredibly sorry for her."
							+ " Stepping forwards, you lean down and look up into her "+Main.game.getActiveNPC().getEyeName()+", "
							+UtilText.parsePlayerSpeech("I'm sorry! I didn't mean to upset you!")
						+ "</p>"
						+ "<p>"
						+ "Your efforts at calming "+Main.game.getActiveNPC().getName("the")+" down only seem to make her whine out even more, and,"
								+ " feeling extremely sorry for the poor, horny demon, you make an offer that you might soon regret..."
						+ "</p>"
						+ "<p>"
						+UtilText.parsePlayerSpeech("So, you need me to help you get some release?")
						+ "</p>"
						+ "<p>"
						+ Main.game.getActiveNPC().getName("The")+"'s eyes flick up as she stops whining for a moment, "
						+ UtilText.parseSpeech("Yes... But I can't get off unless I'm the one in charge...", Main.game.getActiveNPC())
						+ "</p>"
						+ "<p>"
						+UtilText.parsePlayerSpeech("Well, in that case, I'm happy to let yo- ~Mrph!~")
						+ "</p>"
						+ "<p>"
						+ Main.game.getActiveNPC().getName("The")+" suddenly rocks forwards, pressing her lips against yours as she pulls you into a passionate kiss."
							+ " Her whining instantly stopped as you started to offer your body to her, and as she grinds up against you, the only noises that come from her mouth are deep, pleasurable moans."
						+ "</p>"
						+ "<p>"
							+ "Before you really know what's going on, she's pulled you up to your feet, and as her body presses up tightly against yours, you feel a strange bulge digging into your leg."
							+ " Breaking off the kiss for a moment, you look down, and your eyes go wide as you see the distinctive shape of a massive erection forming beneath the fabric of her dress."
							+ " You gulp as you remember what she said just moments ago, and you realise that she's growing herself a cock to fuck you with."
						+ "</p>"
						+ "<p>"
							+ "Seeing what you're looking at, the succubus lets out a playful giggle, "
							+ UtilText.parseSpeech("What? You didn't seriously think I'd let you use my pussy, did you?"
									+ " You're the one who's offering to help here, remember?! If you didn't want to be my little fuck-toy, perhaps you should have just walked away!", Main.game.getActiveNPC())
						+ "</p>"
						+ "<p>"
							+ "Before you can respond, she leans forwards and presses her lips against yours once more, thrusting her forked tongue into your mouth as she lets out a happy moan."
							+ " You're far too submissive to consider putting a stop to this, and you find yourself desperately kissing her back as you resign yourself to your fate..."
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
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
						return DebugDialogue.getDefaultDialogueNoEncounter();
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
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You can't pass up an opportunity like this, and as you step towards the horny succubus, she lets out a delighted squeal, "
						+ UtilText.parseSpeech("Yes! Come fuck me!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
					+ "Jumping up onto her feet, she leaps forwards and tries to pull you into a kiss."
					+ " Wanting to take her up on her offer of transforming herself, you grab her shoulders and prevent her from getting too close just yet."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No cock",
						"Tell the succubus that you don't want her to grow a cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Not really liking the sound of having her grow a cock, you tell her as much, "
								+	UtilText.parsePlayerSpeech("I don't want to see you trying to grow a cock, ok?")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the succubus moans, "
								+ UtilText.parseSpeech("Aww! You're no fun... Let's just get started already!", Main.game.getActiveNPC())
								+"</p>");
					}
				};
				
			} else if (index == 2) {
				return new Response("Tiny cock",
						"Tell the succubus that you want her to grow a tiny little 1-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(1);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that she should have a cute little sissy-cock, you order the succubus to grow one, "
								+	UtilText.parsePlayerSpeech("I think a little bit of humiliation would do you good! You're going to grow a cute little cock for me, understood? And keep it tiny!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the succubus moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at her crotch, you don't see any sign of a bulge beneath her dress, but then again, if she's kept it tiny like you asked, you wouldn't expect to see anything there anyway."
								+ "</p>");
					}
				};
				
			} else if (index == 3) {
				return new Response("Average-sized cock",
						"Tell the succubus that you want her to grow an average, 6-inch, cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(6);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that she should have an average-sized cock, you order the succubus to grow one, "
								+	UtilText.parsePlayerSpeech("You're going to grow a cock for me, understood? And keep it a reasonable size! I don't want to see anything too big!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the succubus moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at her crotch, you see a small bulge beneath her dress, and you find yourself smiling in delight as you realise that the succubus is going to do anything you command."
								+ "</p>");
					}
				};
				
			} if (index == 4) {
				return new Response("Large cock",
						"Tell the succubus that you want her to grow a large, 11-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(11);
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that she should have a nice, big cock, you order the succubus to grow one, "
								+	UtilText.parsePlayerSpeech("You're going to grow a cock for me, understood? And make it a big one!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the succubus moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at her crotch, you see a large bulge beneath her dress, and you find yourself smiling in delight as you realise that the succubus is going to do anything you command."
								+ "</p>");
					}
				};
				
			} if (index == 5) {
				return new Response("Enormous cock",
						"Tell the succubus that you want her to grow a massive 19-inch cock.",
						AFTER_COMBAT_TRANSFORMATIONS_VAGINA){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.PENIS, true);
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Deciding that she should have the biggest cock she's able to grow, you order the succubus to get started, "
								+	UtilText.parsePlayerSpeech("You're going to grow a cock for me, understood? And make it as big as possible! If you aren't hung like a horse, I'm not interested!")
								+ "</p>"
								+ "<p>"
								+ "Trying to wriggle out of your grasp, the succubus moans, "
								+ UtilText.parseSpeech("Fine! Just... Aah... Hold on... Ok, all done, can we start now?!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Looking down at her crotch, you see a massive bulge beneath her dress, and you find yourself smiling in delight as you realise that the succubus is going to do anything you command."
								+ "</p>");
					}
				};
				
			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATIONS_VAGINA = new DialogueNodeOld("Transformations", "", true, true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Panting and moaning, the succubus pleads with you, "
						+ UtilText.parseSpeech("Come on! There isn't anything else is there? Let's just get going already!", Main.game.getActiveNPC())
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Keep vagina",
						"Tell the succubus that you want her to keep her pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Wanting to make sure that she keeps her pussy, you order her once more, "
								+	UtilText.parsePlayerSpeech("Don't try and get rid of your pussy! I've got plans for it!")
								+ "</p>"
								+ "<p>"
								+ "By now the succubus has stopped trying to get out of your grasp, and just obediently sighs, "
								+ UtilText.parseSpeech("Ok, ok! I wasn't planning on getting rid of it anyway! Now let's start! Come on!", Main.game.getActiveNPC())
								+"</p>");
					}
				};
				
			} else if (index == 2) {
				return new Response("Remove vagina",
						"Tell the succubus that she won't be needing her pussy.",
						AFTER_COMBAT_TRANSFORMATIONS_BREASTS){
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().getPlayerKnowsAreasMap().put(CoverableArea.VAGINA, true);
						Main.game.getActiveNPC().setVaginaType(VaginaType.NONE);
						Main.game.getTextStartStringBuilder().append(
								"<p>"
								+ "Not having any plans to use her pussy, you decide to humiliate her a little, and bark out your second order, "
								+	UtilText.parsePlayerSpeech("I don't want to see a pussy between your legs, understood? You're not getting off like that with me!")
								+ "</p>"
								+ "<p>"
								+ "By now the succubus has stopped trying to get out of your grasp, but as you order her to remove her pussy, she shuffles around uncomfortably and lets out a mortified groan, "
								+ UtilText.parseSpeech("No! Please, not my pussy! It's not a real orgasm if I don't have one!", Main.game.getActiveNPC())
								+"</p>"
								+ "<p>"
								+ "Unimpressed by her reluctance to do as she's told, you give her a final ultimatum, "
								+	UtilText.parsePlayerSpeech("If you don't do as you're told, I'll just walk away, and you can wait for another five hours before someone else comes walking by.")
								+ "</p>"
								+ "<p>"
								+ UtilText.parseSpeech("Fine! Fine... Just... Hold on... Aww, it feels so weird down there now... It's gone...", Main.game.getActiveNPC())
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
			return "<p>"
						+ "Letting out a pitiful whine, the succubus stares up into your eyes, "
						+ UtilText.parseSpeech("You can't drag this out any more! Come on already!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "Ignoring her request, you wonder if you should get her to perform one final transformation."
						+ " Her huge, E-cup breasts might need some work, after all..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("AA-cup",
						"Tell the succubus to make her breasts tiny little AA-cups.",
						AFTER_SEX_VICTORY,
						true, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
						"<p>"
							+ "Wanting to humiliate her a little, you order her to get rid of her breasts, "
							+	UtilText.parsePlayerSpeech("These massive tits of yours are far too big! You're going to shrink them right down, understand? If they're anything bigger than little double-A's, I'm walking away!")
						+ "</p>"
						+ "<p>"
							+ "With a desperate sob, the succubus does as you command, "
							+ UtilText.parseSpeech("Really?! Aah... I just need to get fucked so bad... Fine!", Main.game.getActiveNPC())
						+"</p>"
						+ "<p>"
							+ "Looking down at her chest, you see her huge, E-cup breasts rapidly shrink down, and after just a couple of seconds, she's almost completely flat-chested."
							+ " Satisfied with your changes, you decide to finally give the succubus what she wants, and, releasing her shoulders, you allow her to step forwards and grind herself up against you."
							+ " Leaning down to deliver a passionate kiss, you reach around and grope the succubus's ass as she moans hotly into your mouth."
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setBreastSize(CupSize.AA.getMeasurement());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("C-cup",
						"Tell the succubus to make her breasts a more reasonable, C-cup size.",
						AFTER_SEX_VICTORY,
						true, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
						"<p>"
							+ "Wanting to reduce the size of her huge breasts a little, you order her to make the change, "
							+	UtilText.parsePlayerSpeech("These massive tits of yours are far too big! Make them a more reasonable size! C-cups should do just fine.")
						+ "</p>"
						+ "<p>"
							+ "With a desperate moan, the succubus does as you command, "
							+ UtilText.parseSpeech("Aww... Fine!", Main.game.getActiveNPC())
						+"</p>"
						+ "<p>"
							+ "Looking down at her chest, you see her huge, E-cup breasts rapidly shrink down, and after just a couple of seconds, she's left with a much more reasonable bust."
							+ " Satisfied with your changes, you decide to finally give the succubus what she wants, and, releasing her shoulders, you allow her to step forwards and grind herself up against you."
							+ " Leaning down to deliver a passionate kiss, you reach around and grope the succubus's ass as she moans hotly into your mouth."
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setBreastSize(CupSize.C.getMeasurement());
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("E-cup",
						"Tell the succubus to keep her breasts as E-cups.",
						AFTER_SEX_VICTORY,
						true, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
						"<p>"
							+ "Looking down at her chest, you find yourself happy enough already as you see her huge, E-cup breasts."
							+ " Satisfied with your changes, you decide to finally give the succubus what she wants, and, releasing her shoulders, you allow her to step forwards and grind herself up against you."
							+ " Leaning down to deliver a passionate kiss, you reach around and grope the succubus's ass as she moans hotly into your mouth."
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setBreastSize(CupSize.E.getMeasurement());
					}
				};
				
			} else if (index == 4) {
				return new ResponseSex("H-cup",
						"Tell the succubus to grow her breasts into huge H-cups.",
						AFTER_SEX_VICTORY,
						true, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
						"<p>"
							+ "Wanting to make her breasts a little bigger, you order her to make the change, "
							+	UtilText.parsePlayerSpeech("I thought succubi liked having big breasts? I don't know what you'd call those things, but they're definitely not big by my standards."
									+ " Make them at least an H-cup, and we can finally get started.")
						+ "</p>"
						+ "<p>"
							+ "You see the succubus's cheeks flash red, and you realise that you've obviously touched a nerve, "
							+ UtilText.parseSpeech("W-What?! But everyone always says that these are big enough! F-Fine... I can make them bigger for you...", Main.game.getActiveNPC())
						+"</p>"
						+ "<p>"
							+ "Looking down at her chest, you see her huge, E-cup breasts start to expand, and after just a couple of seconds, she's left with a massive, H-cup bust."
							+ " Satisfied with your changes, you decide to finally give the succubus what she wants, and, releasing her shoulders, you allow her to step forwards and grind herself up against you."
							+ " Leaning down to deliver a passionate kiss, you reach around and grope the succubus's ass as she moans hotly into your mouth."
						+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setBreastSize(CupSize.H.getMeasurement());
					}
				};
				
			} else if (index == 5) {
				return new ResponseSex("N-cup",
						"Tell the succubus that you want her to make her breasts as large as possible, which will leave her with massive N-cup tits.",
						AFTER_SEX_VICTORY,
						true, false, Main.game.getActiveNPC(), new SMDomStanding(), AFTER_SEX_VICTORY,
						"<p>"
								+ "Wanting to make her breasts as big as possible, you order her to make the change, "
								+	UtilText.parsePlayerSpeech("I thought succubi liked having big breasts? I don't know what you'd call those things, but they're definitely not big by my standards."
										+ " I want to see just how big you can make them!")
							+ "</p>"
							+ "<p>"
								+ "You see the succubus's cheeks flash red, and you realise that you've obviously touched a nerve, "
								+ UtilText.parseSpeech("W-What?! But everyone always says that these are big enough! F-Fine... I can make them bigger...", Main.game.getActiveNPC())
							+"</p>"
							+ "<p>"
								+ "Looking down at her chest, you see her huge, E-cup breasts start to expand, and after just a couple of seconds, she's left with an absolutely massive, N-cup bust."
								+ " Satisfied with your changes, you decide to finally give the succubus what she wants, and, releasing her shoulders, you allow her to step forwards and grind herself up against you."
								+ " Leaning down to deliver a passionate kiss, you reach around and grope the succubus's ass as she moans hotly into your mouth."
							+ "</p>"){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setBreastSize(CupSize.N.getMeasurement());
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by " + Main.game.getActiveNPC().getName("the") + "!";
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You simply can't bring yourself to carry on fighting, and you stumble back against a nearby wall, panting heavily from exertion."
						+ " Glancing up, you see a delighted smile flash across the succubus's face as she steps towards you."
					+ "</p>"
					+ "<p>"
						+ "Moving up close before starting to grind herself up against your leg, she moans in your ear, "
						+ UtilText.parseSpeech("~Aah!~ Yes! Finally, I can get some release!", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "As her body presses tightly against yours, you feel a strange bulge pressing against your leg."
						+ " Looking down, your eyes go wide as you see the distinctive shape of a massive erection forming beneath the fabric of her dress."
						+ " You didn't see any signs of a bulge between her legs while you were fighting her, and you gulp as you suddenly realise that she's using her demonic powers to grow herself a cock to fuck you with."
					+ "</p>"
					+ "<p>"
						+ "Seeing what you're looking at, the succubus lets out a playful giggle, "
						+ UtilText.parseSpeech("What? You didn't seriously think I'd let you use my pussy, did you? Perhaps if you didn't want to be my little fuck-toy, you should have fought harder, hmm?", Main.game.getActiveNPC())
					+ "</p>"
					+ "<p>"
						+ "Before you can respond, she leans forwards and presses her lips against yours, thrusting her forked tongue into your mouth as she lets out a happy moan."
						+ " You're far too weak to resist her advances, and you find yourself desperately kissing her back as you resign yourself to your fate..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Succubus's toy",
						"The succubus is ready to use you as her little fuck-toy...",
						AFTER_SEX_DEFEAT,
						false, false, Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SUBMITTING = new DialogueNodeOld("Submit", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You kneel before the succubus.";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "You don't really want to fight " + Main.game.getActiveNPC().getName("the") + ", and decide to just do as she asks."
					+ " Sinking to your knees, you look up at her and beg, "
					+ UtilText.parsePlayerSpeech("Please! Can you fuck me?")
				+ "</p>"
				+ "<p>"
					+ "Looking a little surprised for a moment, the succubus quickly regains her composure and strides towards you, "
					+ UtilText.parseSpeech("Hah! Well, that was easy! What a good little submissive thing you are!", Main.game.getActiveNPC())
				+ "</p>"
				+ "<p>"
					+ "Quickly coming to a halt just in front of you, she leans down and grabs your arm, pulling you back up to your feet before pressing her body up tightly against yours."
					+ " As she starts to affectionately grind up against you, you feel a strange bulge pressing against your leg."
					+ " Looking down, your eyes go wide as you see the distinctive shape of a massive erection forming beneath the fabric of her dress."
					+ " You didn't see any signs of a bulge between her legs a moment ago, and you gulp as you suddenly realise that she's using her demonic powers to grow herself a cock to fuck you with."
				+ "</p>"
				+ "<p>"
					+ "Seeing what you're looking at, the succubus lets out a playful giggle, "
					+ UtilText.parseSpeech("What? You didn't seriously think I'd let a submissive little bitch like you use my pussy, did you?"
							+ " But you're an obedient little thing, I just know that you're going to love my cock!", Main.game.getActiveNPC())
				+ "</p>"
				+ "<p>"
					+ "Before you can respond, she leans forwards and presses her lips against yours, thrusting her forked tongue into your mouth as she lets out a happy moan."
					+ " Having already made the conscious decision to submit to this horny demon, you find yourself desperately kissing her back as you resign yourself to your fate..."
				+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Succubus's toy",
						"The succubus is ready to use you as her little fuck-toy...",
						AFTER_SEX_DEFEAT,
						false, false, Main.game.getActiveNPC(), new SMSubStanding(), AFTER_SEX_DEFEAT){
					@Override
					public void effects() {
						Main.game.getActiveNPC().setPenisType(PenisType.DEMON_COMMON);
						Main.game.getActiveNPC().setPenisSize(PenisSize.FIVE_ENORMOUS.getMaximumValue());
						Main.game.getActiveNPC().setTesticleSize(TesticleSize.ZERO_VESTIGIAL.getValue());
						Main.game.getActiveNPC().setCumProduction(CumProduction.FIVE_HUGE.getMaximumValue());
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
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave "+Main.game.getActiveNPC().getName("the")+" to recover.";
		}

		@Override
		public String getContent() {
			if(Sex.getNumberOfPartnerOrgasms()==0) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, whining and desperately touching [npc.herself] as [npc.she] tries to get [npc.herself] off."
							+ " Looking up at you, [npc.she] moans, "
							+ UtilText.parseSpeech("Aaah! Why did you stop?! I was so close!", Main.game.getActiveNPC())
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a deeply satisfied sigh as [npc.she] slowly starts touching herself."
							+ " Despite the fact that you just brought [npc.herHim] to a climax, [npc.she]'s obviously still incredibly horny, and as [npc.her] sighs turn into lewd moans,"
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
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.name]'s clothing and items..."){
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
						return DebugDialogue.getDefaultDialogueNoEncounter();
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
			return 30;
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
						+ "TODO</br>"
						+ "You clasp the collar around [npc.name]'s neck.</br>"
						+ "The arcane enchantment recognises [npc.herHim] as being a criminal, and, with a purple flash, <b>they're teleported to the 'Slave Administration' building in Slaver Alley, where they'll be waiting for you to pick them up</b>."
					+ "</p>"
					+ "<p>"
						+ "Just before they disappear, glowing purple lettering appears on the collar's surface, which reads:</br>"
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
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
