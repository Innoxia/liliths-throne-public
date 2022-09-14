package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.68
 * @version 0.1.8
 * @author Innoxia
 */
public class SABraxSubCowgirl {

	// TODO: This class isn't being used anymore, but I need the facesitting actions for reference for when I add that position.
	
	// Player's actions:

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "Tell Brax that he's your bitch now.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You cry out as you bounce up and down on Brax's [npc.penis+], ",
						"In amongst your moans, you cry down to Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah! I love your cock!",

							"Fuck! ~Aah!~ Your cock's so big!",

							"~Mmm!~ I love riding little betas like you!",

							"Good boy! ~Aah!~ Let your alpha ride you like a little bitch!")));
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"You cry out as you bounce up and down on Brax's [npc.penis+], ",
						"In amongst your moans, you cry down to Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah! I love your cock!",

							"Fuck! ~Aah!~ Your cock's so big!",

							"~Mmm!~ I love riding little betas like you!",

							"Good boy! ~Aah!~ Let your alpha ride you like a little bitch!")));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking down at the top of Brax's face between your legs, you speak down to him, ",
						"With a grin, you speak down to Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah, good little beta!",

							"~Mmm!~ Keep going! Yeah, like that!",

							"Good boy Brax! Keep licking! Don't stop!",

							"~Aah!~ Yes! Keep doing it like that!")));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking down at the top of Brax's face between your legs, you speak down to him, ",
						"With a grin, you speak down to Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah, good little beta!",

							"~Mmm!~ Keep going! Yeah, like that!",

							"Good boy Brax! Keep licking! Don't stop!",

							"~Aah!~ Yes! Keep doing it like that!")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking down at Brax's submissive form beneath you, you speak down to him, ",
						"With a grin, you speak down to Brax, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"You're going to be a good little beta, aren't you Brax?",

							"That's right, stay down like a good little beta!",

							"What an obedient beta you are!",

							"You want me to ride your little beta cock?")));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			}
		}
	};
	

	public static final SexAction PLAYER_COWGIRL_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Kiss";
		}

		@Override
		public String getActionDescription() {
			return "Lean down and kiss Brax.";
		}

		@Override
		public String getDescription() {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
				switch(Util.random.nextInt(5)){
					case 0:
						return "You sink down onto Brax's [npc.penis+], letting out a happy squeal as you feel him deep inside your [pc.asshole+]."
								+ " Bottoming out on his cock, you lean down, grabbing Brax's head in both hands and pulling him up into a passionate kiss.";
					case 1:
						return "With a lewd moan, you slide down onto Brax's [npc.penis+], leaning forwards into his chest and breathing in his masculine scent before pressing your lips against his.";
					case 2:
						return "You let Brax's [npc.penis+] slip out of your ass for a moment as you lean down and pull him into a desperate kiss."
						+ " After a few moments of tongue-fucking one another's mouths, you sit back up, and as you lower yourself back down onto Brax's massive cock,"
						+ " you see a strand of saliva briefly linking your lips before breaking and falling onto his furry chest.";
					case 3:
						return "As you lean forwards, Brax props himself up on his elbows, eagerly meeting your lips with his. Your moans are muffled in each other's mouths as Brax's [npc.penis+] buries itself deep in your [pc.asshole+].";
					default:
						return "Leaning down, you let out a desperate groan as you bury yourself on Brax's [npc.penis+], before pressing your lips against his and starting to eagerly kiss the masculine wolf-boy.";
				}

			} else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
				switch(Util.random.nextInt(5)){
					case 0:
						return "You sink down onto Brax's [npc.penis+], letting out a happy squeal as you feel him deep inside your "+Main.game.getPlayer().getVaginaName(true)+"."
								+ " Bottoming out on his cock, you lean down, grabbing Brax's head in both hands and pulling him up into a passionate kiss.";
					case 1:
						return "With a lewd moan, you slide down onto Brax's [npc.penis+], leaning forwards into his chest and breathing in his masculine scent before pressing your lips against his.";
					case 2:
						return "You let Brax's [npc.penis+] slip out of your pussy for a moment as you lean down and pull him into a desperate kiss."
								+ " After a few moments of tongue-fucking one another's mouths, you sit back up, and as you lower yourself back down onto Brax's massive cock,"
								+ " you see a strand of saliva briefly linking your lips before breaking and falling onto his furry chest.";
					case 3:
						return "As you lean forwards, Brax props himself up on his elbows, eagerly meeting your lips with his. Your moans are muffled in each other's mouths as Brax's [npc.penis+] buries itself deep in your "
								+Main.game.getPlayer().getVaginaName(true)+".";
					default:
						return "Leaning down, you let out a desperate groan as you bury yourself on Brax's [npc.penis+], before pressing your lips against his and starting to eagerly kiss the masculine wolf-boy.";
				}

			} else {
				switch(Util.random.nextInt(5)){
					case 0:
						return "You lean down, grabbing Brax's head in both hands and pulling him up into a passionate kiss. You let out a muffled giggle into the wolf-boy's mouth as you feel his "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
								+" bump up against your lower back.";
					case 1:
						return "With a grin, you lean down onto Brax's powerful chest, breathing in his masculine scent before pressing your lips against his.";
					case 2:
						return "You feel Brax's [npc.penis+] bumping uselessly against your lower back as you pull him into a desperate kiss."
						+ " After a few moments of tongue-fucking one another's mouths, you sit back up, noticing a strand of saliva briefly linking your lips before breaking and falling onto his furry chest.";
					case 3:
						return "As you lean forwards, Brax props himself on on his elbows, eagerly meeting your lips with his. Your moans are muffled in each other's mouths as Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
								+" bumps fruitlessly against your lower back.";
					default:
						return "Leaning down, you take a moment to admire Brax's masculine form, letting out a little moan before pressing your lips against his and starting to eagerly kiss him.";
				}
			}
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PUSSY = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return CorruptionLevel.THREE_DIRTY;
			else
				return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return "Clean pussy";
			else
				return "Facesit (pussy)";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return "Shuffle forwards and sit on Brax's face, getting his tongue to clean the cum out of your "+Main.game.getPlayer().getVaginaName(true)+".";
			else
				return "Shuffle forwards and sit on Brax's face, putting his tongue to use on your "+Main.game.getPlayer().getVaginaName(true)+".";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()
					&& !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& Main.game.getPlayer().getVaginaType() != VaginaType.NONE;
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
				UtilText.nodeContentSB.setLength(0);
				
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
					UtilText.nodeContentSB.append("Deciding that you've had enough of Brax's tongue in your [pc.asshole+], you lift yourself up and shuffle backwards until your "
								+Main.game.getPlayer().getVaginaName(true)+" is positioned over his face.");
					
				} else {
					UtilText.nodeContentSB.append("You decide to put Brax's tongue to use, and shuffle forwards until your crotch is positioned over his face.");
				}
				
				UtilText.nodeContentSB.append(" You feel a big dollop of slimy cum drip out of your "+Main.game.getPlayer().getVaginaName(true)+", and you let out a giggle as you smush the slimy creampie down onto Brax's wolf-like muzzle."
						+ " You hold the squirming wolf-boy's head still as you press your cum-filled cunt down over his mouth, giving him no choice but to start cleaning you out."
						+ " After a moment, Brax stops squirming as he realises that it's useless to resist, and you let out a contented sigh as he starts licking out the cum from your creampied "+Main.game.getPlayer().getVaginaName(false)+".");
				
				return UtilText.nodeContentSB.toString();
						
			} else {
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
					return "Deciding that you've had enough of Brax's tongue in your [pc.asshole+], you lift yourself up and shuffle backwards until your "
								+Main.game.getPlayer().getVaginaName(true)+" is positioned over his wolf-like muzzle."
							+ " Lowering yourself down, you let out a pleased moan as you feel Brax's flat tongue greedily start to lick at your sensitive folds, and as your "
								+Main.game.getPlayer().getVaginaName(true)+" comes to rest over his mouth, Brax starts eagerly eating you out.";
					
				} else {
					return "You decide to put Brax's tongue to use, and shuffle forwards until your crotch is positioned over his wolf-like muzzle."
							+ " Lowering yourself down, you let out a pleased moan as you feel Brax's flat tongue greedily start to lick at your sensitive folds, and as your "
								+Main.game.getPlayer().getVaginaName(true)+" comes to rest over his mouth, Brax starts eagerly eating you out.";
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_CUM_STUD);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
				}
				
			} else {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_ASS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return CorruptionLevel.FOUR_LUSTFUL;
			else
				return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return "Clean ass";
			else
				return "Facesit (ass)";
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return "Shuffle forwards and sit on Brax's face, getting his tongue to clean the cum out of your [pc.asshole+].";
			else
				return "Shuffle forwards and sit on Brax's face, putting his tongue to use on your [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
				UtilText.nodeContentSB.setLength(0);
				
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
					UtilText.nodeContentSB.append("Deciding that you've had enough of Brax's tongue in your "+Main.game.getPlayer().getVaginaName(true)+", you lift yourself up and shuffle forwards until your [pc.asshole+] is positioned over his face.");
					
				} else {
					UtilText.nodeContentSB.append("You decide to put Brax's tongue to use, and shuffle forwards until your ass is positioned over his face.");
				}
				
				UtilText.nodeContentSB.append(" You feel a big dollop of slimy cum drip out of your [pc.asshole+], and you let out a giggle as you smush the slimy creampie down onto Brax's wolf-like muzzle."
						+ " You hold the squirming wolf-boy's head still as you press your cum-filled asshole down over his mouth, giving him no choice but to start cleaning you out."
						+ " After a moment, Brax stops squirming as he realises that it's useless to resist, and you let out a contented sigh as he starts licking out the cum from your creampied [pc.asshole].");
				
				return UtilText.nodeContentSB.toString();
						
			} else {
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
					return "Deciding that you've had enough of Brax's tongue in your "+Main.game.getPlayer().getVaginaName(true)+", you lift yourself up and shuffle forwards until your [pc.asshole+] is positioned over his wolf-like muzzle."
							+ " Lowering yourself down, you let out a pleased moan as you feel Brax's flat tongue obediently start to lick at your [pc.asshole+].";
					
				} else {
					return "You decide to put Brax's tongue to use, and shuffle forwards until your [pc.asshole+] is positioned over his wolf-like muzzle."
							+ " Lowering yourself down, you let out a pleased moan as you feel Brax's flat tongue obediently start to lick at your [pc.asshole+].";
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_CUM_STUD);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
				}
				
			} else {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_ANAL_GIVING);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PASSIVE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Passive";
		}

		@Override
		public String getActionDescription() {
			return "Sit still and let Brax do all the work.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You hold yourself over Brax's mouth, letting out soft little moans as his wolf-like tongue eagerly laps at your "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"You absent-mindedly stroke the top of Brax's head, moaning happily as he eats you out.",
					
					"Reaching down to playfully stroke Brax's furry wolf-like ears, you let out a contented moan as he buries his tongue deep in your "+Main.game.getPlayer().getVaginaName(true)+".",
						
					"Holding yourself over Brax's face, you let out a series of lewd moans as his wide, wolf-like tongue pushes its way eagerly into your "+Main.game.getPlayer().getVaginaName(true)+".");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PASSIVE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Passive";
		}

		@Override
		public String getActionDescription() {
			return "Sit still and let Brax do all the work.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"You hold yourself over Brax's mouth, letting out soft little moans as his wolf-like tongue obediently laps at your [pc.asshole+].",
					
					"You absent-mindedly stroke the top of Brax's head, moaning happily as he licks away at your [pc.asshole+].",
					
					"Reaching down to playfully stroke Brax's furry wolf-like ears, you let out a contented moan as he buries his tongue deep in your [pc.asshole+].",
						
					"Holding yourself over Brax's face, you let out a series of lewd moans as his wide, wolf-like tongue obediently pushes its way into your [pc.asshole+].");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_GRIND = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Grind";
		}

		@Override
		public String getActionDescription() {
			return "Roughly grind down on Brax's wolf-like muzzle.";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				return UtilText.returnStringAtRandom(
						"Feeling like taking it up a notch, you collapse down onto Brax's face, completely smothering his mouth with your "+Main.game.getPlayer().getVaginaName(true)+"."
								+ " As he starts to whine and squirm, you grind yourself down onto his wolf-like muzzle, letting out a desperate squeal as his tongue is forced deep into your "+Main.game.getPlayer().getVaginaName(true)+".",
						
						"Allowing your legs to give way, you collapse down onto Brax's face, grinding your "+Main.game.getPlayer().getVaginaName(true)+" down hard against his mouth as he squirms and whines beneath you.",
						
						"Reaching down to take hold of Brax's furry wolf-like ears, you collapse down onto his face, letting out a delighted squeal as his tongue is forced deep into your "+Main.game.getPlayer().getVaginaName(true)+".",
							
						"Spreading your legs out a little further, you sink down onto Brax's wolf-like muzzle, letting out a desperate moan as you grind your "+Main.game.getPlayer().getVaginaName(true)+" down hard against his eager tongue.");
			} else {
				return UtilText.returnStringAtRandom(
						"Feeling like taking it up a notch, you collapse down onto Brax's face, completely smothering his mouth with your "+Main.game.getPlayer().getAssName(true)+"."
								+ " As he starts to whine and squirm, you grind yourself down onto his wolf-like muzzle, letting out a desperate squeal as his tongue is forced deep into your [pc.asshole+].",
						
						"Allowing your legs to give way, you collapse down onto Brax's face, grinding your [pc.asshole+] down hard against his mouth as he squirms and whines beneath you.",
						
						"Reaching down to take hold of Brax's furry wolf-like ears, you collapse down onto his face, letting out a delighted squeal as his tongue is forced deep into your [pc.asshole+].",
							
						"Spreading your legs out a little further, you sink down onto Brax's wolf-like muzzle, letting out a desperate moan as you grind your [pc.asshole+] down hard against his eager tongue.");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_GRIND_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Grind";
		}

		@Override
		public String getActionDescription() {
			return "Roughly grind down on Brax's wolf-like muzzle.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Feeling like taking it up a notch, you collapse down onto Brax's face, completely smothering his mouth with your "+Main.game.getPlayer().getAssName(true)+"."
							+ " As he starts to whine and squirm, you grind yourself down onto his wolf-like muzzle, letting out a desperate squeal as his tongue is forced deep into your [pc.asshole+].",
					
					"Allowing your legs to give way, you collapse down onto Brax's face, grinding your [pc.asshole+] down hard against his mouth as he squirms and whines beneath you.",
					
					"Reaching down to take hold of Brax's furry wolf-like ears, you collapse down onto his face, letting out a delighted squeal as his tongue is forced deep into your [pc.asshole+].",
						
					"Spreading your legs out a little further, you sink down onto Brax's wolf-like muzzle, letting out a desperate moan as you grind your [pc.asshole+] down hard against his eager tongue.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};

	public static final SexAction PLAYER_COWGIRL_PENETRATE_PUSSY = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Penetrate pussy";
		}

		@Override
		public String getActionDescription() {
			return "Slip Brax's [npc.penis+] into your "+Main.game.getPlayer().getVaginaName(true)+".";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE);
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty())
				UtilText.nodeContentSB.append("You lift yourself up, letting Brax's pointed dog-cock slip out of your [pc.asshole+]. ");

			UtilText.nodeContentSB.append("Reaching down and taking hold of the [npc.penis+] beneath you, you shuffle around until it's lined up to your "+Main.game.getPlayer().getVaginaName(true)+"."
					+ " Not wasting another moment, you quickly sink down, feeling the tapered end of Brax's [npc.penis+] easily slide into you."
					+ " With a desperate wail, you sink down, stuffing more and more of the readily widening shaft into your "+Main.game.getPlayer().getVaginaName(true)
					+", until at last, with a quiver of delight, you feel the swollen knot at the base bump against your pussy lips.");

			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_PENETRATE_ASS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Penetrate ass";
		}

		@Override
		public String getActionDescription() {
			return "Slip Brax's [npc.penis+] into your [pc.asshole+].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty())
				UtilText.nodeContentSB.append("You lift yourself up, letting Brax's pointed dog-cock slip out of your "+Main.game.getPlayer().getVaginaName(true)+". ");

			UtilText.nodeContentSB.append("Reaching down and taking hold of the [npc.penis+] beneath you, you shuffle around until it's lined up to your [pc.asshole+]."
					+ " Not wasting another moment, you quickly sink down, feeling the tapered end of Brax's [npc.penis+] slide into you."
					+ " With a desperate wail, you sink down, stuffing more and more of the readily widening shaft into your [pc.ass], until at last, with a quiver of delight, you feel the swollen knot at the base bump against your [pc.asshole+].");

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Return to sitting on Brax's stomach.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "You lift yourself up, and, with a disappointed whine from Brax, you allow his [npc.penis+] to slip out of your "+Main.game.getPlayer().getVaginaName(true)+"."
					+ " With a quick shuffle, you return to sitting on the wolf-boy's toned, fur-covered stomach.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_RIDING_ANAL = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Return to sitting on Brax's stomach.";
		}

		@Override
		public String getDescription() {
			return "You lift yourself up, and, with a disappointed whine from Brax, you allow his [npc.penis+] to slip out of your [pc.asshole+]."
					+ " With a quick shuffle, you return to sitting on the wolf-boy's toned, fur-covered stomach.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_TONGUE_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Return to sitting on Brax's stomach.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "You lift yourself up, and, with a disappointed whine from Brax, you allow his tongue to slip out of your "+Main.game.getPlayer().getVaginaName(true)+"."
					+ " With a quick shuffle, you return to sitting on the wolf-boy's toned, fur-covered stomach.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_TONGUE_RIDING_ANAL = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stop penetration";
		}

		@Override
		public String getActionDescription() {
			return "Return to sitting on Brax's stomach.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "You lift yourself up, and, with a slightly relieved sigh from Brax, you allow his tongue to slip out of your [pc.asshole+]."
					+ " With a quick shuffle, you return to sitting on the wolf-boy's toned, fur-covered stomach.";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_BRAXS_HANDS_ON_TITS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Brax on chest";
		}

		@Override
		public String getActionDescription() {
			return "Guide Brax's paw-like hands up to your chest, encouraging him to play with your breasts.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
				return UtilText.returnStringAtRandom(
						"Reaching down, you take hold of Brax's paw-like hands and guide them up to your chest, letting out a lewd moan as he eagerly starts groping and squeezing at the soft flesh of your "+Main.game.getPlayer().getBreastName(true)+".",
						
						"Guiding Brax's paw-like hands up to your exposed chest, you let out a lewd moan as you feel the tough little pads on the undersides of his fingers pinch down and squeeze your "+Main.game.getPlayer().getNippleName(true)+".",
						
						"Pushing your chest out towards Brax, you guide his hands up to your exposed breasts, letting out a little squeal as he starts to eagerly pinch and squeeze your "+Main.game.getPlayer().getNippleName(true)+".",
							
						"Guiding Brax's hands up to your exposed "+Main.game.getPlayer().getBreastName(true)+", you let out a little cry as he starts eagerly groping your chest, digging the tough little pads on his palms roughly into your soft flesh.");
			} else {
				return UtilText.returnStringAtRandom(
						"Reaching down, you take hold of Brax's paw-like hands and guide them up to your chest, letting out a lewd moan as he eagerly starts groping and squeezing at your "+Main.game.getPlayer().getBreastName(true)
								+ " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Guiding Brax's paw-like hands up to your chest, you let out a lewd moan as you feel his fingers trying to pinch down and squeeze at your "+Main.game.getPlayer().getNippleName(true)+""
								+ " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Pushing your chest out towards Brax, you guide his hands up to your breasts, letting out a little squeal as he starts to eagerly grope at your chest"
								+ " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
							
						"Guiding Brax's hands up to your "+Main.game.getPlayer().getBreastName(true)+", you let out a little cry as he starts eagerly groping your chest, digging the tough little pads on his palms roughly into your soft flesh"
								+ " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_SLOW = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Slow ride";
		}

		@Override
		public String getActionDescription() {
			return "Slowly slide up and down on Brax's hard shaft.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Using your legs, you slowly lift yourself up and down, sliding Brax's [npc.penis+] in and out of your "+Main.game.getPlayer().getVaginaName(true)+" as you both moan and groan in pleasure.",
					
					"Leaning forwards, you use your hands to support some of your weight as you slowly slide up and down on Brax's [npc.penis+]."
							+ " As you lower your face towards the submissive wolf-boy, you get a waft of his masculine musk, and you bite your lip as you eagerly breathe in the intoxicating scent.",
					
					"Lowering your hands to your knees, you use your legs to slide yourself up and down on Brax's [npc.penis+], letting out high-pitched moans as you impale yourself on the grinning wolf-boy.",
						
					"You slowly lift yourself up before sliding back down, spearing your "+Main.game.getPlayer().getVaginaName(true)+" on Brax's [npc.penis+] as you let out a lewd moan.");
			
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_SLOW_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Slow ride";
		}

		@Override
		public String getActionDescription() {
			return "Slowly slide up and down on Brax's hard shaft.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Using your legs, you slowly lift yourself up and down, sliding Brax's [npc.penis+] in and out of your [pc.asshole+] as you both moan and groan in pleasure.",
					
					"Leaning forwards, you use your hands to support some of your weight as you slowly slide up and down on Brax's [npc.penis+]."
							+ " As you lower your face towards the submissive wolf-boy, you get a waft of his masculine musk, and you bite your lip as you eagerly breathe in the intoxicating scent.",
					
					"Lowering your hands to your knees, you use your legs to slide yourself up and down on Brax's [npc.penis+], letting out high-pitched moans as you impale yourself on the grinning wolf-boy.",
						
					"You slowly lift yourself up before sliding back down, spearing your [pc.asshole+] on Brax's [npc.penis+] as you let out a lewd moan.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FAST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fast ride";
		}

		@Override
		public String getActionDescription() {
			return "Quickly bounce up and down on Brax's [npc.penis+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Using your legs, you start to rapidly bounce yourself up and down, slamming Brax's [npc.penis+] in and out of your "+Main.game.getPlayer().getVaginaName(true)
						+" as you squeal and moan in pleasure.",
					
					"Leaning forwards, you use Brax's furry, masculine chest to support most of your weight before starting to rapidly buck your hips up and down on his [npc.penis+]."
							+ " As your face comes closer to his body, you get a waft of his masculine musk, and you bite your lip as you eagerly breathe in the intoxicating scent.",
					
					"Placing your hands down on the floor behind you for support, you start rapidly bouncing yourself up and down on Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+", letting out shuddering high-pitched moans as you repeatedly impale yourself on the grinning wolf-boy.",
						
					"Grinning down at Brax, you start enthusiastically sliding up and down, repeatedly spearing your "+Main.game.getPlayer().getVaginaName(true)+" on Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+" as you let out a series of high-pitched moans.");
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FAST_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fast ride";
		}

		@Override
		public String getActionDescription() {
			return "Quickly bounce up and down on Brax's [npc.penis+].";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Using your legs, you start to rapidly bounce yourself up and down, slamming Brax's [npc.penis+] in and out of your [pc.asshole+] as you squeal and moan in pleasure.",
					
					"Leaning forwards, you use Brax's furry, masculine chest to support most of your weight before starting to rapidly buck your hips up and down on his [npc.penis+]."
							+ " As your face comes closer to his body, you get a waft of his masculine musk, and you bite your lip as you eagerly breathe in the intoxicating scent.",
					
					"Placing your hands down on the floor behind you for support, you start rapidly bouncing yourself up and down on Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+", letting out shuddering high-pitched moans as you repeatedly impale yourself on the grinning wolf-boy.",
						
					"Grinning down at Brax, you start enthusiastically sliding up and down, repeatedly spearing your [pc.asshole+] on Brax's "+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+" as you let out a series of desperate groans.");
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_CUM_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Cummy kiss";
		}

		@Override
		public String getActionDescription() {
			return "Show Brax how delicious his cum is...";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.braxCumOnChest;
		}

		@Override
		public String getDescription() {
			return "Seeing Brax's cum splattered all over his furry stomach and chest, you get the sudden urge to give him a taste of himself."
					+ " Leaning down, you start licking up all the sticky seed, breathing in the wolf-boy's powerful masculine musk as you take your time making sure not to miss a single drop."
					+ "<br/><br/>"
					+ "Once your mouth is full of delicious, salty cream, you slide your way up Brax's chest until you're gazing down into his rather worried-looking eyes."
					+ " Pressing your lips against his, he tries to jerk his head away, but, not taking no for an answer, you reach up and grab his wolf-like muzzle, squeezing his cheeks until you force his mouth open a little."
					+ "<br/><br/>"
					+ "Seeing your chance, you thrust your tongue past his lips, and start using the opening through which to transfer your mouthful of yummy cream."
					+ " Brax starts squirming about beneath you, but his unwillingness to share your love of cum only strengthens your resolve, and before long, you've succeeded in pushing a significant amount of his own seed into his mouth."
					+ "<br/><br/>"
					+ "Making sure to save a little for yourself, you break off the kiss, rapidly closing your hand around Brax's muzzle to prevent him from wasting his precious jizz."
					+ " Not being able to resist any longer, you make a show of swallowing the small amount of delicious cum that you kept in your mouth, before telling Brax to do the same."
					+ "<br/><br/>"
					+ "You hold Brax's muzzle closed until you see him reluctantly mimicking your behaviour, and, as you let go, he obediently opens his mouth to show you that he's swallowed it all."
					+ " Delighted with the wolf-boy's performance, you lean down and share a passionate, cummy kiss with him.";
		}

		@Override
		public void applyEffects() {
			if (SexFlags.braxCumOnChest)
				SexFlags.braxCumOnChest = false;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FEED_CUM = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Feed Brax";
		}

		@Override
		public String getActionDescription() {
			return "Use your fingers to feed Brax his own cum.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.braxCumOnChest;
		}

		@Override
		public String getDescription() {
			return "Seeing Brax's cum splattered all over his furry stomach and chest, you get the sudden urge to give him a taste of himself."
					+ " Leaning down, you start scooping up all the sticky seed in your fingers, breathing in the wolf-boy's powerful masculine musk as you take your time making sure not to miss a single drop."
					+ "<br/><br/>"
					+ "Once your hand is full of delicious, salty cream, you lean down over Brax's chest to gaze down into his rather worried-looking eyes."
					+ " Bringing your cum-covered digits up to his mouth, Brax tries to jerk his head away, but, not taking no for an answer, you reach up and grab his wolf-like muzzle with your free hand,"
					+ " squeezing his cheeks until you force his mouth open a little."
					+ "<br/><br/>"
					+ "Seeing your chance, you thrust your cummy fingers past his lips, and start using the opening through which to pour your handful of yummy cream."
					+ " Brax starts squirming about beneath you, but his unwillingness to share your love of cum only strengthens your resolve, and before long, you've succeeded in pushing a significant amount of his own seed into his mouth."
					+ "<br/><br/>"
					+ "Happy now that his mouth is full, you draw your fingers back, rapidly closing your other hand around Brax's muzzle to prevent him from wasting his precious jizz."
					+ " Giggling down at the distressed-looking wolf-boy, you tell him to swallow it all down."
					+ "<br/><br/>"
					+ "You hold Brax's muzzle closed until you see him reluctantly obeying your command, and, as you let go, he obediently opens his mouth to show you that he's swallowed it all."
					+ " Delighted with the wolf-boy's performance, you hold out your cummy hand, and, resigned to the fact that he doesn't have much choice in the matter, proceeds to lick your hand clean.";

		}

		@Override
		public void applyEffects() {
			if (SexFlags.braxCumOnChest)
				SexFlags.braxCumOnChest = false;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			}
		}
	};

	// Partner's actions:

	public static final SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brax cries out as you bounce up and down on his [npc.penis+], ",
						"In amongst his groans, Brax cries up to you, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah! Keep riding me!",

							"Fuck! ~Aah!~ I'm your little beta!",

							"~Aah!~ I'm your little toy!",

							"I'm your good little beta! ~Aah!~ Use me!"), Main.sex.getCharacterPerformingAction()));
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brax cries out as you bounce up and down on his [npc.penis+], ",
						"In amongst his groans, Brax cries up to you, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Aah!~ Yeah! Keep riding me!",

							"Fuck! ~Aah!~ I'm your little beta!",

							"~Aah!~ I'm your little toy!",

							"I'm your good little beta! ~Aah!~ Use me!"), Main.sex.getCharacterPerformingAction()));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking up from between your legs, Brax mumbles into your pussy, ",
						"Brax mumbles up to you, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Mrph!~ Love... ~Mrph!~ Pussy!",

							"~Mmm!~ I'm... ~Mrph!~ Beta!",

							"~Mrph!~ You're... ~Mrph!~ Alpha!",

							"~Mrph!~ Taste... ~Mmm!~ Good!"), Main.sex.getCharacterPerformingAction()));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Brax mumbles into your ass, ",
						"Brax mumbles up to you, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~Mrph!~ How... ~Mrph!~ Much longer...",

							"~Mmm!~ I'm... ~Mrph!~ Beta!",

							"~Mrph!~ You're... ~Mrph!~ Alpha!",

							"~Mrph!~ Hope... ~Mmm!~ Happy!"), Main.sex.getCharacterPerformingAction()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"Looking up at you, Brax mumbles, ",
						"Brax mumbles up to you, "));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"I'll be a good little beta...",

							"I'll do what you say...",

							"I-I'd like you to ride me... please...",

							"You're my alpha..."), Main.sex.getCharacterPerformingAction()));
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_CUNNILINGUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax stays still, letting out little groans as he eagerly licks away at your "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"As you sit on Brax's face, he stays quite still, letting out little whining noises as he laps hungrily at your "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"Brax groans happily beneath you, seeming to be quite content with eating you out like a submissive bitch.",
						
					"As Brax licks happily away at your "+Main.game.getPlayer().getVaginaName(true)+", he remains motionless, looking up from between your legs with big, submissive eyes.");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_ANILINGUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax stays still, letting out little groans as he obediently licks away at your [pc.asshole+].",
					
					"As you sit on Brax's face, he stays quite still, letting out little whining noises as he laps dutifully away at your [pc.asshole+].",
					
					"Brax groans beneath you, seeming to be quite content to perform anilingus on you like a submissive bitch.",
						
					"As Brax licks away at your [pc.asshole+], he remains motionless, letting out low, submissive whines every now and then.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_RIDING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax stays still, letting out satisfied little groans as you impale your "+Main.game.getPlayer().getVaginaName(true)+" on his [npc.penis+].",
					
					"As you carry on riding Brax's [npc.penis+], he stays quite still, letting out little whining noises every time his knot bumps against your "+Main.game.getPlayer().getVaginaName(true)+".",
					
					"Brax groans beneath you, seeming to be quite content to just lie still and let you ride his [npc.penis+].",
						
					"As you carry on impaling your "+Main.game.getPlayer().getVaginaName(true)+" on Brax's [npc.penis+], he remains motionless, looking up at you with big, submissive eyes.");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax stays still, letting out satisfied little groans as you impale your [pc.asshole+] on his [npc.penis+].",
					
					"As you carry on riding Brax's [npc.penis+], he stays quite still, letting out little whining noises every time his knot bumps against your [pc.asshole+].",
					
					"Brax groans beneath you, seeming to be quite content to just lie still and let you ride his [npc.penis+].",
						
					"As you carry on impaling your [pc.asshole+] on Brax's [npc.penis+], he remains motionless, looking up at you with big, submissive eyes.");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
		
	};
	
	public static final SexAction PARTNER_COWGIRL_MASTURBATE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, null)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)
					|| Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				return UtilText.returnStringAtRandom(
						"As you carry on using his tongue, Brax reaches down to grab his [npc.penis+], letting out a series of pathetic whines as he starts jerking himself off.",

						"With his tongue still buried between your legs, Brax takes hold of his [npc.penis+], and with a low groan, starts masturbating beneath you.",
						
						"With his [npc.penis+] not receiving any attention, Brax reaches down between his legs and starts submissively jerking off.",
							
						"As you carry on sitting on his face, Brax reaches down to his groin, and, after spending a moment squeezing and stroking his fat knot, starts submissively stroking his [npc.penis+].");
			
			} else {
				return UtilText.returnStringAtRandom(
						"You feel Brax's [npc.penis+] prodding against your lower back as you sit on his stomach, and, with a pleading whining noise, he reaches around and starts submissively jerking himself off.",

						"Brax's level of arousal makes itself known as you feel his [npc.penis+] poking into your lower back."
								+ " With an amused smirk, you look down at the submissive wolf-boy as he reaches around and starts jerking off behind you.",
						
						"With his [npc.penis+] not receiving any attention, Brax reaches around between his legs and starts submissively masturbating.",
							
						"With a pathetic whine, Brax reaches around to his groin, and, after spending a moment squeezing and stroking his fat knot, starts submissively stroking his [npc.penis+].");
			}
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_BOUNCE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax gently bucks his hips in time with your bounces, helping to impale your "+Main.game.getPlayer().getVaginaName(true)+" on his [npc.penis+].",
					
					"As you carry on riding him, Brax starts bucking his hips, helping to fill your "+Main.game.getPlayer().getVaginaName(true)+" with his [npc.penis+].",
					
					"With a lewd groan, Brax starts bucking his hips up into you, helping you to ride his [npc.penis+].",
						
					"As you carry on impaling your "+Main.game.getPlayer().getVaginaName(true)+" on Brax's [npc.penis+], he starts gently bucking his hips up in time with your bounces.");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_BOUNCE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Brax gently bucks his hips in time with your bounces, helping to impale your [pc.asshole+] on his [npc.penis+].",
					
					"As you carry on riding him, Brax starts bucking his hips, helping to fill your [pc.asshole+] with his [npc.penis+].",
					
					"With a lewd groan, Brax starts bucking his hips up into you, helping you to ride his [npc.penis+].",
						
					"As you carry on impaling your [pc.asshole+] on Brax's [npc.penis+], he starts gently bucking his hips up in time with your bounces.");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_GROPE_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
				return UtilText.returnStringAtRandom(
						"You see Brax staring at your chest, and, letting out a playful giggle, you guide his hands up to your chest,"
								+ " where he immediately starts groping and squeezing at the soft flesh of your "+Main.game.getPlayer().getBreastName(true)+".",
						
						"Brax suggestively reaches his paw-like hands up to your exposed chest, and, deciding not to stop him,"
								+ " you let out a lewd moan as he starts to pinch and squeeze at your "+Main.game.getPlayer().getNippleName(true)+".",
						
						"Brax reaches up to your chest, and, letting him have some fun, you allow him to start eagerly pinching and squeezing your "+Main.game.getPlayer().getNippleName(true)+", causing you to let out a lewd moan.",
							
						"Letting Brax reach up to your exposed "+Main.game.getPlayer().getBreastName(true)+", you let out a little cry as he starts eagerly groping your chest, digging the tough little pads on his palms roughly into your soft flesh.");
			} else {
				return UtilText.returnStringAtRandom(
						"You see Brax staring at your chest, and, letting out a playful giggle, you guide his hands up to your chest, letting out a lewd moan as he eagerly starts groping and squeezing at your "+Main.game.getPlayer().getBreastName(true)
								+ " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Brax suggestively reaches his paw-like hands up to your exposed chest, and, deciding not to stop him,"
								+ " you let out a lewd moan as you feel his fingers trying to pinch down and squeeze at your "+Main.game.getPlayer().getNippleName(true)+""
								+ " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
						
						"Brax reaches up to your chest, and, letting him have some fun, you allow him to start eagerly groping at your chest" + " through your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".",
							
						"Letting Brax reach up to your exposed "+Main.game.getPlayer().getBreastName(true)+", you let out a little cry as he starts eagerly groping your chest, roughly pressing his paw-like hands down"
								+ " on your "+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+".");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
		}
	};
	
	// Player's orgasms:

	public static final SexAction PLAYER_COWGIRL_ORGASM_NO_PEN = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You feel yourself approaching your climax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you let out a desperate moan, and you brace for your orgasm. ");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
				
				if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
					UtilText.nodeContentSB.append(" Reaching down, you grab your "+Main.game.getPlayer().getPenisName(true)+", before starting to furiously masturbate as you rock gently back and forth on Brax's furry stomach."
							+ " Letting out a desperate groan, you feel your cock twitching as your orgasm washes over you");
					
				} else {
					UtilText.nodeContentSB.append(" Rocking gently back and forth on Brax's furry stomach, you reach down and start rubbing at your "+Main.game.getPlayer().getPenisName(true)+" through your "
							+ Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
							+ " Letting out a desperate groan, you feel your cock twitching as your orgasm washes over you");
				}

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", but you aren't able to produce even one drop of cum, somewhat diminishing the pleasure of your climax.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", and a small trickle of cum squirts"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", and a small amount of cum squirts"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", and your cum shoots"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", and your cum shoots"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", and your large load of cum pours"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", and your huge load of cum pours"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", and your enormous load of cum pours"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto Brax's muscular chest."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					default:
						break;
				}
				
				// Vagina:
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
					UtilText.nodeContentSB.append("<br/><br/>"
							+ "As your balls finish emptying themselves on Brax's stomach, you feel a second wave of intense heat building in your groin."
							+ " Grabbing Brax's chest to brace yourself, you go weak at the knees and clench your thighs together as your hot slit shudders and quivers."
							+ " Another mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex provides you with a wave of intense pleasure.");
			
			} else if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append(" Rocking gently back and forth on Brax's furry stomach, you feel an intense heat building in your groin."
						+ " Grabbing the wolf-boy's chest to brace yourself, you go weak at the knees and clench your thighs together as your hot slit shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex provides you with a wave of intense pleasure.");
				
			}else {
				UtilText.nodeContentSB.append(" Rocking gently back and forth on Brax's furry stomach, you feel an intense heat building in your genderless mound."
						+ " Grabbing the wolf-boy's chest to brace yourself, you go weak at the knees and clench your thighs together as your doll-like crotch sends jolts of pleasure up through your body."
						+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as you desperately stroke at the smooth and sensitive area between your legs.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};

	public static final SexAction PLAYER_COWGIRL_ORGASM_FACESITTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You feel yourself approaching your climax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE))
					|| (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE))
				UtilText.nodeContentSB.append("As you feel Brax's tongue lapping away at your [pc.asshole+], you realise that you're starting to climax, and with a desperate moan, you brace for your orgasm. ");
			else
				UtilText.nodeContentSB.append("As you feel Brax's tongue lapping away at your "+Main.game.getPlayer().getVaginaName(true)+", you realise that you're starting to climax, and with a desperate moan, you brace for your orgasm. ");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
				
				if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
					UtilText.nodeContentSB.append(" Reaching down, you grab your "+Main.game.getPlayer().getPenisName(true)+", furiously masturbating as you grind down on Brax's wolf-like muzzle."
							+ " Letting out a desperate groan, you feel your "+Main.game.getPlayer().getPenisName(true)+" twitching as your orgasm washes over you");
					
				} else {
					UtilText.nodeContentSB.append(" Grinding down on Brax's wolf-like muzzle, you reach down and start rubbing at your "+Main.game.getPlayer().getPenisName(true)+" through your "
							+ Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."
							+ " Letting out a desperate groan, you feel your "+Main.game.getPlayer().getPenisName(true)+" twitching as your orgasm washes over you");
				}

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", but you aren't able to produce even one drop of cum, somewhat diminishing the pleasure of your climax.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", and a small trickle of cum squirts"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", and a small amount of cum squirts"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", and your cum shoots"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", and your cum shoots"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", and your large load of cum pours"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", and your huge load of cum pours"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", and your enormous load of cum pours"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?" out onto the floor above Brax's head."
									:" out shamefully into your "+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"."));
						break;
					default:
						break;
				}
				
				// Vagina:
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
					UtilText.nodeContentSB.append("<br/><br/>"
							+ "As your balls finish emptying themselves, you feel a second wave of intense heat building in your groin."
							+ " You go weak at the knees, and, collapsing down completely on Brax's face, you clench your thighs together as your hot slit shudders and quivers."
							+ " Another mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex provides you with a wave of intense pleasure.");
			
			} else if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append( "Squealing out in pleasure, you feel an intense heat building in your groin."
						+ " You go weak at the knees, and, collapsing down completely on Brax's face, you clench your thighs together as your hot slit shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex provides you with a wave of intense pleasure.");
				
			}else {
				UtilText.nodeContentSB.append(" Rocking gently back and forth on Brax's furry stomach, you feel an intense heat building in your genderless mound."
						+ " Grabbing the wolf-boy's chest to brace yourself, you go weak at the knees and clench your thighs together as your doll-like crotch sends jolts of pleasure up through your body."
						+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as you desperately stroke at the smooth and sensitive area between your legs.");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_ORGASM_RIDING_COCK = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You feel yourself approaching your climax.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS))
					|| (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS))
				UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you increase the speed at which you're bouncing up and down on Brax's [npc.penis+]."
						+ " Letting out a desperate moan, your mind is overwhelmed by the feeling in your [pc.asshole+], and you brace for your orgasm. ");
			else
				UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you increase the speed at which you're bouncing up and down on Brax's [npc.penis+]."
						+ " Letting out a desperate moan, your mind is overwhelmed by the feeling in your "+Main.game.getPlayer().getVaginaName(true)+", and you brace for your orgasm. ");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
					UtilText.nodeContentSB.append(
							"Your [pc.cock+] suddenly starts violently throbbing, and you know that you're about to cum."
					+ " Grabbing your hard member in one hand, you point it towards Brax's face, furiously masturbating as you prepare to cum.");

				UtilText.nodeContentSB.append(" Letting out a desperate groan, you feel your cock twitching as your orgasm washes over you. ");

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("Unfortunately, you aren't able to produce even one drop of cum, somewhat diminishing the pleasure of your climax.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("A small trickle of cum squirts onto Brax's chest, and you groan in satisfaction as you spurt out your sticky seed.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("A small amount of cum squirts onto Brax's chest, and you groan in satisfaction as you spurt out your sticky seed.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append("Your cum shoots out as your cock throbs, and you groan in satisfaction as as you spurt your sticky seed all over Brax's chest.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append("Your cum shoots out as your cock throbs, and you groan in satisfaction as as you spurt your sticky seed all over Brax's chest."
							+ " A few drops even reach up to his face, and he flinches as your salty cum lands around his mouth.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append("Your large load pours out as your cock throbs, and you groan in satisfaction as as you spurt your sticky seed all over Brax's chest."
							+ " A splatter of cum manages to reach up to his face, and he flinches as your salty cum lands around his mouth.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append("Your huge load pours out as your cock throbs, and you groan in satisfaction as as you spurt your sticky seed all over Brax's chest."
							+ " A few thick ropes of cum manage to reach up to his face, and he flinches as your salty cum lands all around his mouth.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append("Your enormous load pours out as your cock throbs, and you groan in satisfaction as as you spurt your sticky seed all over Brax's chest."
							+ " Several thick ropes of cum manage to reach up to his face, and he flinches as your salty cum completely covers his torso and face.");
					break;
				default:
					break;
				}

				UtilText.nodeContentSB.append("<br/><br/>");
			}

			// Vagina:
			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
					UtilText.nodeContentSB.append("As you continue to impale your ass on Brax's knotted dog-cock, you feel an intense heat building in your pussy."
							+ " Grabbing Brax's chest to brace yourself, you go weak at the knees and clench your thighs together as your hot slit shudders and quivers."
							+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex provides you with a wave of intense pleasure.");
				} else {
					UtilText.nodeContentSB.append("As you continue to impale your pussy on Brax's knotted dog-cock, you feel an intense heat building in your groin."
							+ " Grabbing Brax's chest to brace yourself, you go weak at the knees and clench your thighs together as your hot slit shudders and quivers, clenching around the throbbing member lodged deep in your cunt."
							+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex provides you with a wave of intense pleasure.");
				}
			}

			// Mound:
			if (Main.game.getPlayer().getPenisType() == PenisType.NONE && Main.game.getPlayer().getVaginaType() == VaginaType.NONE) {
				if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
					UtilText.nodeContentSB.append("As you continue to impale your ass on Brax's knotted dog-cock, you feel an intense heat building in your genderless mound."
							+ " Grabbing Brax's chest to brace yourself, you go weak at the knees and clench your thighs together as your doll-like crotch sends jolts of pleasure up through your body."
							+ " A mind-splitting orgasm washes over you, and you moan and squeal in delight as you desperately stroke at the smooth and sensitive area between your legs.");
				} else {
					UtilText.nodeContentSB.append("As you slide back and forth over Brax's stomach, you feel an intense heat building in your genderless mound."
							+ " Grabbing Brax's chest to brace yourself, you go weak at the knees and clench your thighs together as your doll-like crotch sends jolts of pleasure up through your body."
							+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as you desperately stroke at the smooth and sensitive area between your legs.");
				}
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
//			Main.sex.removePenetration(PenetrationType.PENIS, OrificeType.VAGINA); TODO
//			Main.sex.removePenetration(PenetrationType.PENIS, OrificeType.ANUS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
		}
	};

	// Partner's orgasms:

	public static final SexAction PARTNER_COWGIRL_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
				return "Brax's groans start getting louder, and you realise that he's about to orgasm."
						+ " Feeling the fat cock in your ass throbbing wildly, you're suddenly overcome by the desperate need to have your ass filled with his sticky seed."
						+ " Lifting yourself up one last time, you slam yourself down on his swollen member, driving the fat knot at the base of his cock into your asshole."
						+ " You let out a startled cry as you feel the knot immediately swell up, locking Brax's twitching dog-cock deep in your ass."
						+ "<br/>"
						+ "Brax lets out a wild groan, and you squirm in delight as you feel his cock spurting it's potent load into your slutty backdoor."
						+ " After a few moments, you feel the knot deflating, and with a wet pop, it slips out, freeing you up again.";
				
			} else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
				return "Brax's groans start getting louder, and you realise that he's about to orgasm."
						+ " Feeling the fat cock in your pussy throbbing wildly, you're suddenly overcome by the desperate need to "+(Main.game.getPlayer().isVisiblyPregnant()?"be":"have your womb")+" filled with his sticky seed."
						+ " Lifting yourself up one last time, you slam yourself down on his swollen member, driving the fat knot at the base of his cock into your greedy cunt."
						+ " You let out a startled cry as you feel the knot immediately swell up, locking Brax's twitching dog-cock deep in your wet slit."
						+ "<br/>"
						+ "Brax lets out a wild groan, and you squirm in delight as you feel his cock spurting it's potent load straight into your "+(Main.game.getPlayer().isVisiblyPregnant()?"waiting pussy":"waiting womb")+"."
						+ " After a few moments, you feel the knot deflating, and with a wet pop, it slips out, freeing you up again.";
				
			} else {
				return "Brax's groans start getting louder, and you realise that he's about to orgasm."
						+ " Looking back at his [npc.penis+], you're suddenly overcome by the desperate need to start stroking it."
						+ " Shuffling back, so that you're sitting on his thighs, you reach down and grab his massive shaft in both hands."
						+ " Sliding your grip up and down the entire length, you press down on the fat knot at the base before lifting up to rub the tapered tip."
						+ " You let out a little cry as you feel the knot swelling up, and point the cock up to Brax's face as you feel it starting to twitch."
						+ "<br/>"
						+ "Brax lets out a wild groan, and you squirm in delight as you see his cock spurting its potent load all over Brax's chest."
						+ " After a few moments, Brax's orgasm ends, and as the knot deflates, he lets out a satisfied groan.";
			}
		}

		@Override
		public void applyEffects() {
//			if(Main.sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS)==null && Main.sex.getPenetrationTypeInOrifice(OrificeType.VAGINA)==null)
//				SexFlags.braxCumOnChest = true; TODO
		}
		

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Main.sex.getTargetedPartner(cumProvider))) {
				if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.VAGINA).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.ANUS).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.MOUTH).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.NIPPLE).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.NIPPLE);
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty())
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty())
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			else
				return null;
		}
	};
	
	
}
