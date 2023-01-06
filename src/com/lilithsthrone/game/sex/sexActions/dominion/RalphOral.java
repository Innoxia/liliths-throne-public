package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.64
 * @version 0.2.8
 * @author Innoxia
 */
public class RalphOral {

	// Player actions:

	public static final SexAction PLAYER_START_ORAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Start sucking";
		}

		@Override
		public String getActionDescription() {
			return "Take the head of Ralph's gigantic horse-cock into your mouth.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyOngoingActionHappening()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("With Ralph's cock being as large as it is, you don't even need to lean forwards in order to start earning your discount."
					+ " Opening your mouth, you simply move your lips down to kiss the flared head of his horse-like shaft.");
			
			if(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM))
				UtilText.nodeContentSB.append(" As the tip of your tongue slides over his bumpy equine urethra, a dollop of salty precum leaks into your mouth."
						+ " You let out a surprised moan as the salty liquid drips past your lips");
			else
				UtilText.nodeContentSB.append(" As the tip of your tongue slides over his bumpy equine urethra, you feel the heat of his bestial cock radiating across your face."
						+ " You let out a little moan as you anticipate sucking his delicious cock");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append(", only too late remembering that there's a customer on the other side of the counter!<br/>"
						+ "Eager to make up for your mistake, you quickly rock forwards, stretching your jaw open wide as you take the hot, throbbing cock into your mouth."
						+ " In your haste, you end up making even more noise, and you hear Ralph hurriedly offering the customer a portion of your discount to keep this a secret.");
			else
				UtilText.nodeContentSB.append(", which is quickly muffled as you stretch your jaw open wide and take the hot, throbbing cock into your mouth.");
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
			
	public static final SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Stay quiet";
		}

		@Override
		public String getActionDescription() {
			return "Sit perfectly still and don't make a sound. This will prevent the customer at the counter from knowing that you're here.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.customerAtCounter;
		}

		@Override
		public String getDescription() {
			return "You sit perfectly still, trying your best not to make a sound as Ralph finishes dealing with the customer above you."
					+ " A slimy stream of saliva and precum drools down from your stretched lips, and you silently slide forwards a little more as you try to use Ralph's cock to push the liquid back down your throat.";
		}
	};
	
	public static final SexAction PLAYER_STAY_QUIET_TEASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Silently tease";
		}

		@Override
		public String getActionDescription() {
			return "Tease Ralph's cock as you try to get him to break his composure in front of the customer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.customerAtCounter;
		}

		@Override
		public String getDescription() {
			return "As Ralph finishes dealing with the customer above you, you decide to have a little fun."
					+ " Pulling back, you gently run your tongue around the flared head of his equine cock, teasing the tip over his bumpy urethra."
					+ " A slimy stream of saliva and precum drools down from your stretched lips, and you reach up to smear the natural lubricant up and down his shaft, gently stroking his balls as you carry on teasing his head with your tongue."
					+ " You grin mischievously as you hear Ralph struggling to maintain his composure in front of the customer, and as you hear him tactfully directing the customer to look at the other side of the shop,"
					+ " he slams his cock forwards down your throat, letting you know that he's clearly not amused by your little game...";
		}
	};
	
	public static final SexAction PLAYER_SUCK_COCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Suck cock";
		}

		@Override
		public String getActionDescription() {
			return "Keep sucking Ralph's cock.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("You keep bobbing your head back and forth as you ram the wide, flared head of Ralph's equine member down your throat."
					+ " His thick black shaft shines and glistens under the shop's lighting as your lips spread a slimy mixture of precum and saliva up and down its length.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append(" A lewd squelching noise echoes around you in your little cramped box, which, combined with the creaking of the floorboards as you rock back and forth,"
						+ " is making a surprising amount of noise."
						+ " You got so carried away with sucking the delicious, fat cock in front of you that you completely forgot there was a customer above you, and you hear Ralph desperately offering him a portion of your discount"
						+ " as he bribes him to keep quiet.");
			else
				UtilText.nodeContentSB.append(" Every time you push forwards, you feel Ralph gently bucking his hips as he helps to drive his gigantic rod into your facial fuck-hole."
						+ " A lewd squelching noise echoes around you in your little cramped box, which, combined with the creaking of the floorboards as you rock back and forth,"
						+ " is making a surprising amount of noise, and you're glad that there are no customers nearby to hear you.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
	
	public static final SexAction PLAYER_FONDLE_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fondle balls";
		}

		@Override
		public String getActionDescription() {
			return "Pay some attention to Ralph's balls.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("As you suck Ralph's hot black horse-cock, you remember what he said earlier about giving his balls some attention."
					+ " Not wanting to disappoint him, you somewhat awkwardly, considering you have almost no room to move, reach up to the heavy sack hanging at the base of his throbbing shaft."
					+ " You gently rub and squeeze his huge, cum-filled testicles, and you find yourself letting out an involuntary muffled moan as you imagine them emptying themselves down your throat.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "The combination of your cramped, shuffling movements, and the not-so-quiet moan you just let out, is enough to alert the customer standing on just the other side of the counter."
						+ " You feel a bit deflated as you hear Ralph giving away a portion of your discount to keep them quiet.");
			else
				UtilText.nodeContentSB.append(" You hear Ralph start to groan encouragingly above you, and you carry on fondling and stroking his nice heavy balls as their accompanying, similarly-proportioned cock slides in and out of your mouth.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
	
	public static final SexAction PLAYER_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Deepthroat";
		}

		@Override
		public String getActionDescription() {
			return "See how good your throat is at taking horse-cock.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("Summoning up your nerve, you decide to try and take as much of Ralph's cock down your throat as possible."
					+ " Shuffling forwards, you lean into his groin, relaxing your throat and opening your esophagus as you try to gulp down the thick, hot length of Ralph's equine member."
					+ " With nerves of steel, you push your head forwards, slamming the entire length of his gigantic dick straight down your throat."
					+ " As you start to pull back, a thick layer of slimy saliva drools down your chin, and as you see over half of its length leaving your mouth, you decide to do it all over again, and push forwards once more into Ralph's crotch."
					+ " Thanks to your excessive saliva production, his bestial horse-like shaft slides easily into your mouth yet again, and you hilt it fully down your throat,"
					+ " keeping it there for as long as possible before you start to run out of breath and are forced to back up, recovering for only a moment before quickly returning to sucking it at a normal pace.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "The loud squelching sounds, combined with the creaking of floorboards as you rocked back and forth deep-throating the huge horse-cock, ended up making quite a bit of noise."
						+ " You hear Ralph offering a part of your discount to the customer who just overheard your zealous display, and you let out a muffled sigh as you realise that you forgot they were there.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};

	public static final SexAction PLAYER_BIG_DISCOUNT = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Big discount";
		}
		@Override
		public String getActionDescription() {
			return "Ask Ralph if there's a way to earn an even bigger discount. <i style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>This is an offer for Ralph to fuck your pussy!</i>";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !SexFlags.askedForBigDiscount;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("As you've been sucking Ralph's cock, you've started to feel a desperate craving deep in your loins."
					+ " You want to feel him inside of you. His fat, animalistic cock pumping away at your pussy, before filling you with his potent seed..."
					+ " You can't hold back any longer, and, sliding his cock fully out of your mouth, you shuffle forwards to look up at him with big, innocent eyes."
					+ "<br/>"
					+ UtilText.parseSpeechNoEffects("Perhaps there's a way I could earn even more of a discount?", Main.game.getPlayer())+" you ask in your most convincingly seductive tone.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "You hear the customer on the other side of the counter let out an amused cry of surprise, and Ralph quickly offers him some of your discount to stay quiet.");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
			
			SexFlags.askedForBigDiscount=true;

			Map<AbstractClothing, DisplacementType> clothingTouched = Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_PREGNANCY);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			}
		}
	};
	
	public static final SexAction PLAYER_ANAL_BIG_DISCOUNT = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Big discount";
		}
		@Override
		public String getActionDescription() {
			return "Ask Ralph if there's a way to earn an even bigger discount. <i style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>This is an offer for Ralph to fuck your ass!</i>";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !SexFlags.askedForBigDiscount;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("As you've been sucking Ralph's cock, you've started to feel a desperate craving deep in your loins."
					+ " You want to feel him inside of you. His fat, animalistic cock pumping away at your ass, before filling you with his potent seed..."
					+ " You can't hold back any longer, and, sliding his cock fully out of your mouth, you shuffle forwards to look up at him with big, innocent eyes."
					+ "<br/>"
					+ UtilText.parseSpeechNoEffects("Perhaps there's a way I could earn even more of a discount?", Main.game.getPlayer())+" you ask in your most convincingly seductive tone.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "You hear the customer on the other side of the counter let out an amused cry of surprise, and Ralph quickly offers him some of your discount to stay quiet.");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter) {
				SexFlags.alertedCustomer=true;
			}
			
			SexFlags.askedForBigDiscount=true;
			
			Map<AbstractClothing, DisplacementType> clothingTouched = Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ANAL_RECEIVING);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};

	public static final SexAction PLAYER_TAKE_IT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
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
			return "Take it";
		}

		@Override
		public String getActionDescription() {
			return "You're in no position to do anything other than lie there and let Ralph's massive horse-cock pound your pussy.";
		}

		@Override
		public String getDescription() {
				return "Ralph's strong hands keep a firm grip on your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips as he pounds away at your pussy, and you find yourself with little to do other than lie still and take it."
					+ " Letting out lewd moans, you fold your arms beneath your head, using them as a pillow as your cunt earns you your extra discount.";
		}
		
	};
	
	public static final SexAction PLAYER_TAKE_IT_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
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
			return "Take it";
		}

		@Override
		public String getActionDescription() {
			return "You're in no position to do anything other than lie there and let Ralph's massive horse-cock pound your ass.";
		}

		@Override
		public String getDescription() {
			return "Ralph's strong hands keep a firm grip on your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips as he pounds away at your ass, and you find yourself with little to do other than lie still and take it."
				+ " Letting out lewd groans, you fold your arms beneath your head, using them as a pillow as your backdoor earns you your extra discount.";
		}
		
	};

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "Now that Ralph's cock is using a different hole, you're able to give him some words of encouragement.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("With Ralph's dominant grip preventing you from making any bold moves, you're left with little option other than to give him some encouraging words."
					+ " Turning your head back and making a show of biting your lip, you plead, ");
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Mmm! My little pussy's all yours!"));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ah yeah! Breed me like your little mare!"));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ah! Ralph! Y-your cock's so big!"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("A-Ahh! Yeah! Fuck me!"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_DIRTY_TALK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "Now that Ralph's cock is using a different hole, you're able to give him some words of encouragement.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("With Ralph's dominant grip preventing you from making any bold moves, you're left with little option other than to give him some encouraging words."
					+ " Turning your head back and making a show of biting your lip, you plead, ");
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Mmm! My little ass is all yours!"));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ah yeah! I'm your little mare!"));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("Ah! Ralph! Y-your cock's so big!"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("A-Ahh! Yeah! Fuck me!"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	// Partner actions:

	public static final SexAction PARTNER_CUSTOMER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTurn() - SexFlags.customerTurnAppearance >= 3; // Have 2 turns of non-customer at minimum
		}
		@Override
		public String getActionTitle() {
			return "A customer!";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return "Ralph suddenly stops the gentle bucking of his hips, and before you have time to wonder what's wrong, you hear him greeting a customer."
					+ " You hear the customer's heavy steps as they move up to just the other side of the counter, and you realise that if you don't stay quiet, they're going to find out that you're under here!"
					+ "<br/><br/>"
					+ "<b>There is</b> <b style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>a customer</b> <b>on the other side of the counter!</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = true;
			SexFlags.customerTurnAppearance = Main.sex.getTurn();
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("You feel Ralph's cock slide back a little as he bends down towards you, ");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("That's right, keep earning your discount!", Main.game.getNpc(Ralph.class)));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah... You're good at this!", Main.game.getNpc(Ralph.class)));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Just remember to keep the noise down!", Main.game.getNpc(Ralph.class)));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Keep going, you're ~mmm~ doing a great job!", Main.game.getNpc(Ralph.class)));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK_VAGINAL = new SexAction(
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
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("You feel Ralph's grip move down to grope and squeeze your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass as he groans down to you, ");
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("I can't believe you asked for this!", Main.game.getNpc(Ralph.class)));
						break;
					case 1:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah yeah! Fuck...", Main.game.getNpc(Ralph.class)));
						break;
					case 2:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah, fuck! Your pussy's so fucking good!", Main.game.getNpc(Ralph.class)));
						break;
					default: 
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Fuck! You're really earning this discount, huh?", Main.game.getNpc(Ralph.class)));
						break;
				}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK_ANAL = new SexAction(
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
			return "Dirty talk";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("You feel Ralph's grip move down to grope and squeeze your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass as he groans down to you, ");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("I can't believe you talked me into this!", Main.game.getNpc(Ralph.class)));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah yeah! Fuck...", Main.game.getNpc(Ralph.class)));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah, fuck! This is pretty fucking good!", Main.game.getNpc(Ralph.class)));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("You're really earning this discount, huh?", Main.game.getNpc(Ralph.class)));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_COMMAND_START_ORAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
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
			return "Demand oral";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyNonSelfOngoingActionHappening();
		}

		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Ralph.class))!=PARTNER_COMMAND_START_ORAL) {
				return SexActionPriority.HIGH;
			} else {
				return  SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("You hear Ralph let out an impatient grunt as he bends down towards you, "
					+ UtilText.parseSpeech("What are you doing? You agreed to do this, so don't keep me waiting!", Main.game.getNpc(Ralph.class)));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}

	};
	
	public static final SexAction PARTNER_START_ORAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Start blowjob";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Ralph.class))==PARTNER_COMMAND_START_ORAL) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("You hear Ralph let out a very annoyed grunt as he bends down towards you, "
					+ UtilText.parseSpeech("I'll show you how to get started!", Main.game.getNpc(Ralph.class))
					+"<br/>"
					+ "Before you have any time to respond, he suddenly pushes his hips forwards."
					+ " His hard shaft quickly pushes your head back against the counter behind you, and before you know what's happening, the flared head of his equine member is rubbing forcefully over your face."
					+ " With a quick reposition and another determined thrust, you feel your lips parting as Ralph rams his eager horse-cock past your lips and into your mouth."
					+ " As you realise that this is, in fact, happening, you finally start to do what you agreed to, and begin sucking Ralph's cock.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(SexFlags.customerAtCounter) {
				return "Deal with customer (Receive blowjob)";
			}
			return "Receive blowjob";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("Ralph remains all but motionless, watching over the shop as you diligently continue sucking his cock."
						+ " Now and then, he gently pushes his hips forwards, helping you to take his massive shaft down your throat, but other than that, you're left with little assistance as you work on pleasing his cock.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE_VAGINAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Sheathe cock";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("The constant pounding between your legs starts to slow down, and you turn your head slightly as you look back to see what's wrong."
					+ " As Ralph smiles back at you, you notice that his chest is heaving, and you realise that he's simply slowed down for a moment to catch his breath."
					+ " Sinking his cock fully into your greedy little cunt, he squeezes and gropes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, and you rest your head back in your arms,"
							+ " moaning and sighing as you both take a moment to recover."
					+ "<br/><br/>"
					+ "After less than a minute, you feel the wide, flared head of his horse-cock sliding back out of your pussy, and as you let out a pathetic little whine at the feeling of emptiness,"
					+ " Ralph tightens his grip on your hips before thrusting forwards, turning your whine into a high-pitched squeal of pleasure as he starts fucking you at full pace once more.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Sheathe cock";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("The constant pounding of your rear entrance starts to slow down, and you turn your head slightly as you look back to see what's wrong."
					+ " As Ralph smiles back at you, you notice that his chest is heaving, and you realise that he's simply slowed down for a moment to catch his breath."
					+ " Sinking his cock fully into your greedy asshole, he squeezes and gropes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, and you rest your head back in your arms,"
							+ " groaning and sighing as you both take a moment to recover."
					+ "<br/><br/>"
					+ "After less than a minute, you feel the wide, flared head of his horse-cock sliding out of your back door, and as you let out a pathetic little whine at the feeling of emptiness,"
					+ " Ralph tightens his grip on your hips before thrusting forwards, turning your whine into a desperate cry of pleasure as he starts fucking you at full pace once more.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(SexFlags.customerAtCounter) {
				return "Deal with customer (Deepthroat)";
			}
			return "Deepthroat";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "<br/><br/>"
						+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("A little ringing noise echoes in your ears, signalling that the only customer in the shop has just left, leaving you alone with Ralph for the moment."
					+ " The consequences of this are immediately made clear as you suddenly feel a strong pressure on each side of your head."
					+ " Looking up, you see that Ralph has grabbed you with both hands, and before you can react, he steps forwards, pulling you into his crotch as he hilts his huge horse-cock deep down your throat."
					+ " Everything goes blurry as tears start streaming from your eyes, but just as you think that you're about to pass out from a lack of oxygen, Ralph releases you."
					+ " Sinking down onto your knees, you let the huge shaft slip out of your mouth for a moment as you're left coughing and spluttering,"
					+ " a thick stream of saliva and precum running down your chin as you take a moment to recover."
					+ " Just at that moment, the bell rings once more, and Ralph steps forwards, concealing his lower half behind the counter as he pushes his cock into your mouth once again.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_REDUCES_DISCOUNT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Reduce discount";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.alertedCustomer;
		}

		@Override
		public String getDescription() {
			return "You feel Ralph's cock slide back a little as he bends down towards you, "
					+ UtilText.parseSpeech("What did I tell you about this being a respectable establishment?! I made it quite clear what would happen if you made too much noise!"
							+ " I'm knocking five percent off our deal, no complaints!", Main.game.getNpc(Ralph.class))
					+"<br/>"
					+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
					+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
					+ "<br/><br/>"
					+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>discount.</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.alertedCustomer=false;
			SexFlags.customerAtCounter = false;
			if(SexFlags.ralphDiscount>0)
				SexFlags.ralphDiscount-=5;
		}
	};
	
	public static final SexAction PARTNER_PENETRATES = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
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
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				return "Fuck [pc.herHim]";
			}
			return "Breed [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.askedForBigDiscount && Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.alertedCustomer){
				UtilText.nodeContentSB.append("Ralph bends down towards you, "
						+ UtilText.parseSpeech("What did I tell you about this being a respectable establishment?! I made it quite clear what would happen if you made too much noise!"
								+ " I'm knocking five percent off our deal, no complaints!", Main.game.getNpc(Ralph.class))
						+"<br/>"
						+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
						+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
						+ "<br/><br/>"
						+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>discount.</b>"
						+ "<br/><br/>");
			}
				
			UtilText.nodeContentSB.append("Ralph steps back, and, not even bothering to pull his trousers up, walks off to the front of the shop."
					+ " As you hear him locking the front door and pulling down the shop's blinds, you realise that the last customer must have left some time ago."
					+ " Wanting to see what's going on, you shuffle your way out from beneath the counter and try to stand up."
					+ "<br/><br/>"
					+ "Almost instantly, a huge jolt of cramp shoots up through your legs, and you let out a little cry as you bend over and grab the edge of the counter."
					+ " Suddenly, you feel a strong pair of hands grab your hips, and before you can react, Ralph pushes you down, face first, onto the counter-top."
					+ " You let out a little moan at his dominant treatment of you, and as he roughly gropes and squeezes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, he leans down and growls in your ear, "
					+ UtilText.parseSpeech(
						(Main.sex.getCharacterPerformingAction().isWearingCondom()
							?"So you want a bigger discount, huh?! You know, it's a shame you insisted on the condom, but I'll still give you another twenty percent if you let me fuck you."
							:(Main.game.getPlayer().isVisiblyPregnant()
									?"So you want a bigger discount, huh?! You know, it's a shame you're already pregnant, but I'll still give you another twenty percent if you let me fuck you."
									:"So you want a bigger discount, huh?! Well, you let me put a few little foals in this belly of yours, and I'll give you another twenty five percent!")
						), Main.game.getNpc(Ralph.class))
					+"<br/><br/>"
					+ "By now, you don't really care about the discount, all you want is to feel that delicious cock sliding in between your legs."
					+ " Even before Ralph's finished giving you his offer, you're moaning in agreement, and he lets out a little laugh as he reaches down to grope your "
					+ (Main.game.getPlayer().getVaginaType()==VaginaType.HORSE_MORPH?"needy horse-pussy.":"hot little pussy.")
					+"<br/><br/>"
					+(Main.game.getPlayer().isVisiblyPregnant()
						?"Being very careful not to bump your pregnant belly against the counter, Ralph pushes your upper-torso down onto the counter top."
							+ " Grabbing your hips, he wastes no time in lining his massive cock up to your waiting cunt, and as he starts to push forwards, you let out a desperate squeal."
						:"Pushing your top-half down onto the counter top, Ralph wastes no time in lining his massive cock up to your waiting cunt, and as he starts to push forwards, you let out a desperate squeal.")
					+ " His wide, flared head slowly pushes its way into you, and you pant and squirm as your folds lewdly spread around his animalistic horse-cock."
					+ " With a quick step forwards, Ralph suddenly rams his impatient member deep into your hungry snatch, and you gasp and moan as he starts fucking you on the shop's counter-top."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
							+(SexFlags.ralphDiscount+(Main.sex.getCharacterPerformingAction().isWearingCondom()||Main.game.getPlayer().isVisiblyPregnant()?20:25)-(SexFlags.alertedCustomer?5:0))
					+"%</b> <b>discount.</b>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA, new ArrayList<>());
			
			SexFlags.customerAtCounter = false;
			if(SexFlags.alertedCustomer) {
				if(SexFlags.ralphDiscount>0) {
					SexFlags.ralphDiscount-=5;
				}
			}
			
			SexFlags.alertedCustomer=false;
			if(Main.sex.getCharacterPerformingAction().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}
		}
		
	};
	
	public static final SexAction PARTNER_PENETRATES_ANUS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
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
			return "Fuck [pc.her] ass";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.askedForBigDiscount && !Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.alertedCustomer){
				UtilText.nodeContentSB.append("Ralph bends down towards you, "
						+ UtilText.parseSpeech("What did I tell you about this being a respectable establishment?! I made it quite clear what would happen if you made too much noise!"
								+ " I'm knocking five percent off our deal, no complaints!", Main.game.getNpc(Ralph.class))
						+"<br/>"
						+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
						+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
						+ "<br/><br/>"
						+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>discount.</b>"
						+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("Ralph steps back, and, not even bothering to pull his trousers up, walks off to the front of the shop."
					+ " As you hear him locking the front door and pulling down the shop's blinds, you realise that the last customer must have left some time ago."
					+ " Before you have time to get out from under the counter, however, the imposing figure of Ralph moves back into view, blocking your way."
					+ "<br/><br/>"
					+ UtilText.parseSpeech("So you want a bigger discount, huh?! And how exactly do you intend to earn it?", Main.game.getNpc(Ralph.class))
					+" He asks, crossing his arms and frowning down at you."
					+ "<br/><br/>"
					+ "You realise that although he was perfectly happy with having you suck his cock, he's going to need some convincing if you're to get him to use your other hole."
					+ " Shifting aside your clothing, you awkwardly shuffle around on the spot, bending over to present your "+Main.game.getPlayer().getAssCapacity().getDescriptor()+" asshole."
					+ " You hear Ralph struggling to suppress an eager groan, and as you shake your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips from side to side, he breaks his composure."
					+ "<br/><br/>"
					+ "Suddenly, you feel a strong pair of hands grab your hips, and before you can react, Ralph pulls you back out from under the counter, and in one swift movement, pushes you down, face first, onto the counter-top."
					+ " You let out a happy little cry at his dominant treatment of you, and as he roughly gropes and squeezes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, he leans down and growls in your ear, "
					+ UtilText.parseSpeech(
							"You know, I don't usually do this sort of thing... But damn, you've got a nice-looking ass! I'll give you another twenty percent if you let me take it for a spin.", Main.game.getNpc(Ralph.class))
					+"<br/><br/>"
					+ "By now, you don't really care about the discount, all you want is to feel that delicious cock ramming its way into your rear entrance."
					+ " Even before Ralph's finished giving you his offer, you're crying out in agreement, and he wastes no time in lining his massive cock up to your waiting asshole."
					+ " You feel the wide, flared head rubbing all over your slutty hole, and as he starts to slowly push his animalistic horse-cock into you, you begin to squirm and squeal."
					+ " With a quick step forwards, Ralph suddenly rams his impatient member deep into your hungry back door, and you let out an ear-splitting cry as he starts fucking you on the shop's counter-top."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "<b>You will now earn a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
						+(SexFlags.ralphDiscount+20-(SexFlags.alertedCustomer?5:0))
						+"%</b> <b>discount.</b>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS, new ArrayList<>());
			
			SexFlags.customerAtCounter = false;
			if(SexFlags.alertedCustomer) {
				if(SexFlags.ralphDiscount>0) {
					SexFlags.ralphDiscount-=5;
				}
			}
			
			SexFlags.alertedCustomer=false;
			if(Main.sex.getCharacterPerformingAction().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}

		}
		
	};
	
	public static final SexAction PARTNER_ROUGH_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Pound [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return (Main.game.getPlayer().isVisiblyPregnant()
						?"Grabbing your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, Ralph pulls you back slightly, moving your pregnant belly safely away from the counter's edge."
								+ " Making sure that he's given you enough room, he suddenly slams his massive cock fully into you, causing you to rock forwards as you let out a desperate wail."
								+ " Encouraged by your lewd reaction, Ralph starts rapidly thrusting his hips forwards and back, relentlessly pounding away at your slutty cunt."
								+ " With his legs in position on either side of yours, he focuses his movements solely in his hips, and your screams of ecstasy come out in little bursts as he jackhammers his huge horse-cock into your slit."
						:"Grabbing your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, Ralph pushes you hard into the counter, leaning down heavily on top of you as he starts relentlessly pounding away at your slutty cunt."
							+ " Using your body as a cushion, he focuses his movements solely in his hips, and your screams of ecstasy come out in little bursts as he jackhammers his huge horse-cock between your legs.")
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, oblivious to the fact that their contents are going to be caught in the condom you made their owner wear."
						:" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, giving you a little reminder as to the origin of your impending creampie's filling.");
		}
		
	};
	
	public static final SexAction PARTNER_ROUGH_FUCK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Pound [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "Grabbing your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, Ralph pushes you hard into the counter, leaning down heavily on top of you as he starts relentlessly pounding away at your slutty ass."
				+ " Using your body as a cushion, he focuses his movements solely in his hips, and your screams of ecstasy come out in little bursts as he jackhammers his huge horse-cock into your back door."
				+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
					?" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, oblivious to the fact that their contents are going to be caught in the condom you made their owner wear."
					:" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, giving you a little reminder as to the origin of your impending creampie's filling.");
		}
		
	};
	
	public static final SexAction PARTNER_NORMAL_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fuck [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return (Main.game.getPlayer().isVisiblyPregnant()
						?"Ralph continues holding your hips tightly, rocking you forwards and back as he fills you with his huge, black horse-cock."
						:"Ralph continues holding your hips tightly, rocking you into the counter as he fills you with his huge, black horse-cock.")
					+ " His heavy balls swing to and fro, impatient to deposit their potent load "
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"into the condom that you've provided."
						:(Main.game.getPlayer().isVisiblyPregnant()?"deep into your hungry cunt.":"deep in your waiting womb."));
		}
		
	};
	
	public static final SexAction PARTNER_NORMAL_FUCK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "Fuck [pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
				return "Ralph continues holding your hips tightly, rocking you into the counter as he fills you with his huge, black horse-cock."
					+ " His heavy balls swing to and fro, impatient to deposit their potent load "
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"into the condom that you've provided."
						:"deep in your waiting ass.");
		}
		
	};
	

	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "You let out a soft [pc.moan] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case DOM_NORMAL:
					return "You let out [pc.a_moan+] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case DOM_ROUGH:
					return "You let out [pc.a_moan+] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case SUB_EAGER:
					return "You let out [pc.a_moan+] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case SUB_NORMAL:
					return "You let out [pc.a_moan] of encouragement as you prepare for [npc.name] to reach [npc.her] orgasm.";
				case SUB_RESISTING:
					return "You let out [pc.a_moan+] as you desperately try to pull away from [npc.name] before [npc.she] orgasms.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PLAYER_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
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
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("As Ralph's huge black cock thrusts deep down your throat, you feel yourself reaching your climax, and before you know what's happening, you're letting out a desperate, muffled [pc.moan].");
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("As Ralph's huge black cock thrusts deep into your [pc.pussy+], you feel yourself reaching your climax, and before you know what's happening, you're letting out [pc.a_moan+].");
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("As Ralph's huge black cock thrusts deep into your [pc.asshole+], you feel yourself reaching your climax, and before you know what's happening, you're letting out [pc.a_moan+].");
				
			} else {
				UtilText.nodeContentSB.append("You feel a desperate heat building in your groin, and with [pc.a_moan+], you reach your climax.");
			}
			
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()){

				UtilText.nodeContentSB.append("<br/><br/>");
				
				if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					UtilText.nodeContentSB.append("You let out a deep groan as you feel the knot at the base of your [pc.cock+] swelling up as you prepare to cum.");
					
				} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
					UtilText.nodeContentSB.append("You let out a deep groan as you feel the wide head of your [pc.cock+] swelling up as you prepare to cum.");
					
				} else {
					UtilText.nodeContentSB.append("You let out a deep groan as you feel your [pc.cock+] twitch and throb as you prepare to cum.");
					
				}
				
				// Describe cum amount:
				UtilText.nodeContentSB.append(" As your "+Main.game.getPlayer().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of your [pc.cum] squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of your [pc.cum] squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum] squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum] shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum] pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your [pc.cum] pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your [pc.cum] pouring");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE){
					if(Main.game.getPlayer().isWearingCondom()) {
						UtilText.nodeContentSB.append("out into the condom that you're wearing.");
						
					} else if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
						UtilText.nodeContentSB.append(" into your [pc.lowClothing(PENIS)].");
						
					} else {
						UtilText.nodeContentSB.append(" out all over the floor beneath you.");
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "A desperate, shuddering heat suddenly crashes up from your [pc.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
				
				if (!Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
					GameCharacter characterPenetrating = Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).get(0);
					switch(Main.sex.getFirstOngoingSexAreaPenetration(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
						case FINGER:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" You curl your fingers up deep inside your [pc.pussy+]"
										+", and, while desperately stroking in a 'come-hither' motion, you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your intruding digits.");
							} else {
								UtilText.nodeContentSB.append(" [npc.NamePos] fingers carry on pumping away at your [pc.pussy+]"
										+", and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding digits.");
							}
							break;
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" You carry on fucking yourself through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [pc.cock+].");
							} else {
								UtilText.nodeContentSB.append(" Ralph carries on fucking your [pc.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around his meaty equine shaft."
										+ " Encouraged by the squeezing, milking sensation of your vagina, Ralph buries his thick black horse-cock deep into your [pc.pussy+] and leans down heavily on top of you."
										+ " You feel the heat of his strong, masculine body pressing down on your back, and you let out a defeated moan as his equine shaft claims you as his most recent sexual conquest.");
							}
							break;
						case TAIL:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" You carry on using your tail to fuck yourself through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
							} else {
								UtilText.nodeContentSB.append(" [npc.NamePos] tail carries on fucking your [pc.pussy+]"
										+" through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
							}
							break;
						case TONGUE:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append(" You carry on thrusting your tongue deep into your [pc.pussy+]"
										+" as you orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding muscle.");
							} else {
								UtilText.nodeContentSB.append(" [npc.Name] carries on eating you out, licking and kissing at your [pc.pussy+]"
										+" while you orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding muscle.");
							}
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					UtilText.nodeContentSB.append(" Your [pc.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through your groin.");
				}

				UtilText.nodeContentSB.append(" With a final, ear-splitting scream of pure arousal, your climax crashes over you, and after a few moments, leaves you as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Main.game.getPlayer().getPenisType()==PenisType.NONE && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
									+ "Despite your lack of sexual organs, you feel a desperate warmth spreading quickly through your lower abdomen, and before you know what's happening,"
									+ " the feeling has concentrated itself in your groin, and you're suddenly hit by a blinding orgasm."
									+ " Your legs clench together and you let out a desperate cry as the climax deep within your horny doll-like mound takes you by surprise."
									+ " A wave of hot pleasure radiates up throughout your whole body, and you shudder and quiver as the overwhelming wave of ecstasy washes through you.");
			}
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you start to come down from your climax, you hear Ralph offering a part of your discount to the customer who just overheard your not-so-quiet orgasm"
						+ ", and you let out a defeated sigh as you realise that you forgot they were there.");
			}
			
			return UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), UtilText.nodeContentSB.toString());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& Main.game.getPlayer().getPenisType()!=PenisType.NONE
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE)
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			
			if(SexFlags.customerAtCounter) {
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			}
			
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [pc.name] is fast approaching [pc.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "[npc.Name] lets out a soft [npc.moan] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case DOM_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case DOM_ROUGH:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_EAGER:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_NORMAL:
					return "[npc.Name] lets out [npc.a_moan+] of encouragement as [npc.she] prepares for you to reach your orgasm.";
				case SUB_RESISTING:
					return "[npc.Name] lets out [npc.a_moan+] as [npc.she] desperately tries to pull away from you before you orgasm.";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ORGASM = new SexAction(
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
			return "Stay in position";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()){
				UtilText.nodeContentSB.append("Ralph starts letting out a series of low, heavy grunts, and you gasp as you feel his member start to twitch inside of you."
						+ " With a roar, he slams his massive horse-cock deep into your cunt, and you let out a squeal as you feel his powerful shaft ride up inside of you as it prepares for its master's orgasm."
						+ "<br/>"
						+ " Grinding the base of his cock against your outer folds, his massive balls tense up as Ralph starts to cum.");
				
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into the condom, and as you feel the hot seed ballooning out the rubber inside of you, you let out a satisfied sigh."
							+ " His cock continues to pump a few more times, the huge, black equine shaft quickly depositing its seed harmlessly into the protection you've provided."
							+ "<br/><br/>"
							+ "As Ralph's balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used pussy."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you roll over onto your back and playfully wrap your legs around Ralph,"
							+ " pulling him forwards and rubbing your pussy against his now-flaccid equine cock as he finishes tying up the used condom."
							+ " He places the little seed-filled package down next to you, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction,"
							+ " [ralph.speechNoEffects(Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...)]");
				} else {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load"
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?", and as you feel the hot cum filling you up, you let out a satisfied moan."
								:" directly into your womb, and as you feel the hot cum filling you up, you let out a satisfied moan.")
							+ " His cock continues to pump a few more times, the huge, black equine shaft making sure to deposit its seed deep into your hungry cunt."
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?" Once your pussy is completely saturated with sticky white horse-jizz, you feel Ralph's strong grip on your hips start to relax as he finishes his orgasm."
								:" Once your womb is completely saturated with sticky white horse-jizz, you feel Ralph's strong grip on your hips start to relax as he finishes his orgasm.")
							+ "<br/><br/>"
							+ "As Ralph's balls finish emptying themselves, he steps back, and with a wet sucking sound, slips his rapidly-softening member out of your well-used slit."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you feel a little trickle of his warm, wet seed escaping from your entrance."
							+ " Rolling over onto your back, you playfully wrap your legs around Ralph, pulling him forwards and rubbing your cum-filled pussy against his now-flaccid equine cock."
							+ " It seems as though he's not up for round two, however, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction,"
							+ " [ralph.speechNoEffects(Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...)]");
				}
				
			}else if(!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()){
				UtilText.nodeContentSB.append("Ralph starts letting out a series of low, heavy grunts, and you gasp as you feel his member start to twitch inside of you."
						+ " With a roar, he slams his massive horse-cock deep into your ass, and you let out a cry as you feel his powerful shaft ride up inside of you as it prepares for its master's orgasm."
						+ "<br/>"
						+ " Grinding the base of his cock against your asshole, his massive balls tense up as Ralph starts to cum.");
				 
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into the condom, and as you feel the hot seed ballooning out the rubber inside of you, you let out a satisfied sigh."
							+ " His cock continues to pump a few more times, the huge, black equine shaft quickly depositing its seed harmlessly into the protection you've provided."
							+ "<br/><br/>"
							+ "As Ralph's balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used back door."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you roll over onto your back and playfully wrap your legs around Ralph,"
							+ " pulling him forwards and rubbing your groin against his now-flaccid equine cock as he finishes tying up the used condom."
							+ " He places the little seed-filled package down next to you, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction,"
							+ " [ralph.speechNoEffects(Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...)]");
				} else {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into your ass, and as you feel the hot seed filling you up, you let out a satisfied groan."
							+ " His cock continues to pump a few more times, the huge, black equine shaft making sure to deposit its seed deep into your hungry back door."
							+ " Once your rear entrance is completely saturated with sticky white horse-jizz, you feel Ralph's strong grip on your hips start to relax as he finishes his orgasm."
							+ "<br/><br/>"
							+ "As Ralph's balls finish emptying themselves, he steps back, and with a wet sucking sound, slips his rapidly-softening member out of your well-used fuck hole."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you feel a little trickle of his warm, wet seed escaping from your rear entrance."
							+ " Rolling over onto your back, you playfully wrap your legs around Ralph, pulling him forwards and rubbing your cum-filled asshole against his now-flaccid equine cock."
							+ " It seems as though he's not up for round two, however, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction,"
							+ " [ralph.speechNoEffects(Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...)]");
				}
				
			} else {
				if(SexFlags.customerAtCounter){
					if(SexFlags.alertedCustomer)
						UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, who's still chuckling at the knowledge of what's going on under the counter."
								+ " Ralph just seems to want to get rid of them as soon as possible, and forgets to knock off the portion of your discount he just gave away!"
								+ " The reason for his oversight is soon made clear..."
								+ "<br/><br/>"
								+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
										+ "<br/><br/>");
					else
						UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
								+ "<br/><br/>"
								+ "<b>There are now</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
										+ "<br/><br/>");
				}
				
				UtilText.nodeContentSB.append("Ralph starts letting out a series of low, heavy grunts, and you gasp as you feel his member start to twitch in your mouth."
						+ " With a suppressed groan, he slams his massive horse-cock deep down your throat, and, squirming about under the counter, you feel his powerful shaft trying to ride up as it prepares for its master's orgasm."
						+ "<br/><br/>"
						+ "Grinding the base of his cock against your lips, his massive balls tense up as Ralph starts to cum.");
				
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into the condom, and as you feel the hot seed ballooning out the tip of the protection you've provided,"
								+ " you let out a very muffled moan."
							+ " The huge, black equine shaft continues to pump and twitch a few more times as it deposits its seed harmlessly into the protection you've provided."
							+ "<br/><br/>"
							+ "As Ralph's balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used throat."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you shuffle forwards and playfully bite your lip at Ralph, watching as he finishes tying up the used condom."
							+ " He places the little seed-filled package down next to you, and, after making sure that nobody's watching, he takes hold of one of your [pc.arms+] and pulls you to your feet,"
							+ " motioning for you to continue shopping as he fulfils his end of the bargain, "
							+ "[ralph.speechNoEffects(Well, I think you've more than earned that discount...)]");
				} else {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into your stomach, and as you feel the hot seed filling you up, you let out a very muffled moan."
							+ " The huge, black equine shaft continues to pump and twitch a few more times as it deposits its seed deep down your facial fuck hole."
							+ " Only once your stomach is completely full of sticky white horse-jizz, does Ralph finally come to the end of his orgasm."
							+ "<br/><br/>"
							+ "As his balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used throat."
							+ " A thick, slimy strand of cummy saliva drools down to form a little stream beneath your mouth, and you shuffle forwards, wiping yourself clean as you playfully smile up at Ralph."
							+ " After making sure that nobody's watching, he takes hold of one of your [pc.arms+] and pulls you up, motioning for you to continue shopping as he fulfils his end of the bargain, "
							+ "[ralph.speechNoEffects(Well, I think you've more than earned that discount...)]");
				}
			}
			
			
			
			if(((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive() && (SexFlags.ralphDiscount<Main.game.getDialogueFlags().ralphDiscount)){
				UtilText.nodeContentSB.append(
						"<br/><br/>"
						+ "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>You have earned a "+SexFlags.ralphDiscount+"% discount for the next three days!</b>"
						+ "<br/><br/>"
						+ "<b>Because Ralph was already giving you the bigger discount of </b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
							+Main.game.getDialogueFlags().ralphDiscount+"%</b><b>, he instead agrees to refresh that for the next three days!</b>");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/><b>You have earned a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+SexFlags.ralphDiscount+"%</b> <b>discount for the next three days!</b>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			// If your existing discount is bigger, just refresh the bigger discount:
			if(!((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive() || SexFlags.ralphDiscount>Main.game.getDialogueFlags().ralphDiscount){
				Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
			}
			
			Main.game.getDialogueFlags().setSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID, Main.game.getMinutesPassed());
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
		public boolean endsSex() {
			return true;
		}
	};

}
