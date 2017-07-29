package com.base.game.sex.sexActions.dominion.ralph;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.effects.Fetish;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.ArousalIncrease;
import com.base.game.sex.LubricationType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexFlags;
import com.base.game.sex.sexActions.SexAction;
import com.base.game.sex.sexActions.SexActionPriority;
import com.base.game.sex.sexActions.SexActionType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;

/**
 * @since 0.1.64
 * @version 0.1.78
 * @author Innoxia
 */
public class SexActionRalphDiscount {

	// Player actions:

	public static SexAction PLAYER_START_ORAL = new SexAction(
			SexActionType.PLAYER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
			return !Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("With Ralph's cock being as large as it is, you don't even need to lean forwards in order to start earning your discount."
					+ " Opening your mouth, you simply move your lips down to kiss the flared head of his horse-like shaft.");
			
			if(Sex.getWetPenetrationTypes().get(PenetrationType.PENIS_PARTNER).contains(LubricationType.PARTNER_PRECUM))
				UtilText.nodeContentSB.append(" As the tip of your tongue slides over his bumpy equine urethra, a dollop of salty pre-cum leaks into your mouth."
						+ " You let out a surprised moan as the salty liquid drips past your lips");
			else
				UtilText.nodeContentSB.append(" As the tip of your tongue slides over his bumpy equine urethra, you feel the heat of his bestial cock radiating across your face."
						+ " You let out a little moan as you anticipate sucking his delicious cock");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append(", only too late remembering that there's a customer on the other side of the counter!</br>"
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
			
	public static SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
					+ " A slimy stream of saliva and pre-cum drools down from your stretched lips, and you silently slide forwards a little more as you try to use Ralph's cock to push the liquid back down your throat.";
		}
	};
	
	public static SexAction PLAYER_STAY_QUIET_TEASE = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
					+ " A slimy stream of saliva and pre-cum drools down from your stretched lips, and you reach up to smear the natural lubricant up and down his shaft, gently stroking his balls as you carry on teasing his head with your tongue."
					+ " You grin mischievously as you hear Ralph struggling to maintain his composure in front of the customer, and as you hear him tactfully directing the cutomer to look at the other side of the shop,"
					+ " he slams his cock forwards down your throat, letting you know that he's clearly not amused by your little game...";
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_SUCK_COCK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_FONDLE_BALLS = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
				UtilText.nodeContentSB.append("</br>"
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PLAYER_DEEP_THROAT = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Deep throat";
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
				UtilText.nodeContentSB.append("</br>"
						+ "The loud squelching sounds, combined with the creaking of floorboards as you rocked back and forth deep-throating the huge horse-cock, ended up making quite a bit of noise."
						+ " You hear Ralph offering a part of your discount to the customer who just overheard your zealous display, and you let out a muffled sigh as you realise that you forgot they were there.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};

	public static SexAction PLAYER_BIG_DISCOUNT = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Big discount";
		}

		@Override
		public String getActionDescription() {
			return "Ask Ralph if there's a way to earn an even bigger discount. <i style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>This is an offer for Ralph to fuck your pussy!</i>";
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
					+ "</br>"
					+ UtilText.parseSpeechNoEffects("Perhaps there's a way I could earn even more of a discount?", Main.game.getPlayer())+" you ask in your most convincingly seductive tone.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("</br>"
						+ "You hear the customer on the other side of the counter let out an amused cry of surprise, and Ralph quickly offers him some of your discount to stay quiet.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
			
			SexFlags.askedForBigDiscount=true;
			
			while(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)){
				Main.game.getPlayer().isAbleToBeDisplaced(Main.game.getPlayer().getNextClothingToRemoveForCoverableAreaAccess(CoverableArea.VAGINA).getKey(),
						Main.game.getPlayer().getNextClothingToRemoveForCoverableAreaAccess(CoverableArea.VAGINA).getValue(), true, true, Main.game.getRalph());
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_PREGNANCY));
		}

		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
	};
	
	public static SexAction PLAYER_ANAL_BIG_DISCOUNT = new SexAction(
			SexActionType.PLAYER_STOP_PENETRATION,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
		@Override
		public String getActionTitle() {
			return "Big discount";
		}

		@Override
		public String getActionDescription() {
			return "Ask Ralph if there's a way to earn an even bigger discount. <i style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>This is an offer for Ralph to fuck your ass!</i>";
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
					+ "</br>"
					+ UtilText.parseSpeechNoEffects("Perhaps there's a way I could earn even more of a discount?", Main.game.getPlayer())+" you ask in your most convincingly seductive tone.");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("</br>"
						+ "You hear the customer on the other side of the counter let out an amused cry of surprise, and Ralph quickly offers him some of your discount to stay quiet.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
			
			SexFlags.askedForBigDiscount=true;
			
			while(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.ANUS)){
				Main.game.getPlayer().isAbleToBeDisplaced(Main.game.getPlayer().getNextClothingToRemoveForCoverableAreaAccess(CoverableArea.ANUS).getKey(),
						Main.game.getPlayer().getNextClothingToRemoveForCoverableAreaAccess(CoverableArea.ANUS).getValue(), true, true, Main.game.getRalph());
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}

		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL));
		}
	};

	public static SexAction PLAYER_TAKE_IT = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction PLAYER_TAKE_IT_ANAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
			return "Ralph's strong hands keep a firm grip on your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips as he pounds away at your ass, and you find yourself with little to do other than lie still and take it."
				+ " Letting out lewd groans, you fold your arms beneath your head, using them as a pillow as your backdoor earns you your extra discount.";
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}

		
		@Override
		public List<Fetish> getFetishesPartner() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_ANAL));
		}
	};

	public static SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PLAYER_DIRTY_TALK_ANAL = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};

	
	// Partner actions:

	public static SexAction PARTNER_CUSTOMER = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
			return "Ralph suddenly stops the gentle bucking of his hips, and before you have time to wonder what's wrong, you hear him greeting a customer."
					+ " You hear the customer's heavy steps as they move up to just the other side of the counter, and you realise that if you don't stay quiet, they're going to find out that you're under here!"
					+ "</br></br>"
					+ "<b>There is</b> <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>a customer</b> <b>on the other side of the counter!</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = true;
		}
	};
	
	public static SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
			return Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
			UtilText.nodeContentSB.append("You feel Ralph's cock slide back a little as he bends down towards you, ");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("That's right, keep earning your discount!", Main.game.getRalph()));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah... You're good at this!", Main.game.getRalph()));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Just remember to keep the noise down!", Main.game.getRalph()));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Keep going, you're ~mmm~ doing a great job!", Main.game.getRalph()));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
	};
	
	public static SexAction PARTNER_DIRTY_TALK_VAGINAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
			UtilText.nodeContentSB.append("You feel Ralph's grip move down to grope and squeeze your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass as he groans down to you, ");
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("I can't believe you asked for this!", Main.game.getRalph()));
						break;
					case 1:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah yeah! Fuck...", Main.game.getRalph()));
						break;
					case 2:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah, fuck! Your pussy's so fucking good!", Main.game.getRalph()));
						break;
					default: 
						UtilText.nodeContentSB.append(UtilText.parseSpeech("Fuck! You're really earning this discount, huh?", Main.game.getRalph()));
						break;
				}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
	};
	
	public static SexAction PARTNER_DIRTY_TALK_ANAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
			return Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
			UtilText.nodeContentSB.append("You feel Ralph's grip move down to grope and squeeze your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass as he groans down to you, ");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("I can't believe you talked me into this!", Main.game.getRalph()));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah yeah! Fuck...", Main.game.getRalph()));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("Ah, fuck! This is pretty fucking good!", Main.game.getRalph()));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("You're really earning this discount, huh?", Main.game.getRalph()));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
	};
	
	public static SexAction PARTNER_COMMAND_START_ORAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
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
			return !Sex.isAnyNonSelfPenetrationHappening();
		}

		@Override
		public SexActionPriority getPriority() {
			if(Sex.getLastUsedPartnerAction()!=PARTNER_COMMAND_START_ORAL)
				return SexActionPriority.HIGH;
			else
				return  SexActionPriority.NORMAL;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
			UtilText.nodeContentSB.append("You hear Ralph let out an impatient grunt as he bends down towards you, "
					+ UtilText.parseSpeech("What are you doing? You agreed to do this, so don't keep me waiting!", Main.game.getRalph()));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}

		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_START_ORAL = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Sex.getLastUsedPartnerAction()==PARTNER_COMMAND_START_ORAL)
				return SexActionPriority.HIGH;
			else
				return SexActionPriority.NORMAL;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
			UtilText.nodeContentSB.append("You hear Ralph let out a very annoyed grunt as he bends down towards you, "
					+ UtilText.parseSpeech("I'll show you how to get started!", Main.game.getRalph())
					+"</br>"
					+ "Before you have any time to respond, he suddenly pushes his hips forwards."
					+ " His hard shaft quickly pushes your head back against the counter behind you, and before you know what's happening, the flared head of his equine member is rubbing forcefully over your face."
					+ " With a quick reposition and another determined thrust, you feel your lips parting as Ralph rams his eager horse-cock past your lips and into your mouth."
					+ " As you realise that this is, in fact, happening, you finally start to do what you agreed to, and begin sucking Ralph's cock.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_PASSIVE = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
			UtilText.nodeContentSB.append("Ralph remains all but motionless, watching over the shop as you diligently continue sucking his cock."
						+ " Now and then, he gently pushes his hips forwards, helping you to take his massive shaft down your throat, but other than that, you're left with little assistance as you work on pleasing his cock.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_PASSIVE_VAGINAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
			
			UtilText.nodeContentSB.append("The constant pounding between your legs starts to slow down, and you turn your head slightly as you look back to see what's wrong."
					+ " As Ralph smiles back at you, you notice that his chest is heaving, and you realise that he's simply slowed down for a moment to catch his breath."
					+ " Sinking his cock fully into your greedy little cunt, he squeezes and gropes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, and you rest your head back in your arms,"
							+ " moaning and sighing as you both take a moment to recover."
					+ "</br></br>"
					+ "After less than a minute, you feel the wide, flared head of his horse-cock sliding back out of your pussy, and as you let out a pathetic little whine at the feeling of emptiness,"
					+ " Ralph tightens his grip on your hips before thrusting forwards, turning your whine into a high-pitched squeal of pleasure as he starts fucking you at full pace once more.");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PARTNER_PASSIVE_ANAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
			
			UtilText.nodeContentSB.append("The constant pounding of your rear entrance starts to slow down, and you turn your head slightly as you look back to see what's wrong."
					+ " As Ralph smiles back at you, you notice that his chest is heaving, and you realise that he's simply slowed down for a moment to catch his breath."
					+ " Sinking his cock fully into your greedy asshole, he squeezes and gropes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, and you rest your head back in your arms,"
							+ " groaning and sighing as you both take a moment to recover."
					+ "</br></br>"
					+ "After less than a minute, you feel the wide, flared head of his horse-cock sliding out of your back door, and as you let out a pathetic little whine at the feeling of emptiness,"
					+ " Ralph tightens his grip on your hips before thrusting forwards, turning your whine into a desperate cry of pleasure as he starts fucking you at full pace once more.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction PARTNER_DEEP_THROAT = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
						+ "</br></br>"
						+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>no customers</b> <b>near the counter.</b>"
								+ "</br></br>");
			
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
			SexFlags.customerAtCounter=false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ORAL));
		}
	};
	
	public static SexAction PARTNER_REDUCES_DISCOUNT = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.MOUTH_PLAYER) {
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
			return SexFlags.alertedCustomer;
		}

		@Override
		public String getDescription() {
			return "You feel Ralph's cock slide back a little as he bends down towards you, "
					+ UtilText.parseSpeech("What did I tell you about this being a respectable establishment?! I made it quite clear what would happen if you made too much noise!"
							+ " I'm knocking five percent off our deal, no complaints!", Main.game.getRalph())
					+"</br>"
					+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
					+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
					+ "</br></br>"
					+ "<b>You will now earn a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>discount.</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.alertedCustomer=false;
			SexFlags.customerAtCounter=false;
			if(SexFlags.ralphDiscount>0)
				SexFlags.ralphDiscount-=5;
		}
	};
	
	public static SexAction PARTNER_PENETRATES = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
								+ " I'm knocking five percent off our deal, no complaints!", Main.game.getRalph())
						+"</br>"
						+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
						+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
						+ "</br></br>"
						+ "<b>You will now earn a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>discount.</b>"
						+ "</br></br>");
			}
				
			UtilText.nodeContentSB.append("Ralph steps back, and, not even bothering to pull his trousers up, walks off to the front of the shop."
					+ " As you hear him locking the front door and pulling down the shop's blinds, you realise that the last customer must have left some time ago."
					+ " Wanting to see what's going on, you shuffle your way out from beneath the counter and try to stand up."
					+ "</br></br>"
					+ "Almost instantly, a huge jolt of cramp shoots up through your legs, and you let out a little cry as you bend over and grab the edge of the counter."
					+ " Suddenly, you feel a strong pair of hands grab your hips, and before you can react, Ralph pushes you down, face first, onto the counter-top."
					+ " You let out a little moan at his dominant treatment of you, and as he roughly gropes and squeezes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, he leans down and growls in your ear, "
					+ UtilText.parseSpeech(
						(Sex.getPartner().isWearingCondom()
							?"So you want a bigger discount, huh?! You know, it's a shame you insisted on the condom, but I'll still give you another twenty percent if you let me fuck you."
							:(Main.game.getPlayer().isVisiblyPregnant()
									?"So you want a bigger discount, huh?! You know, it's a shame you're already pregnant, but I'll still give you another twenty percent if you let me fuck you."
									:"So you want a bigger discount, huh?! Well, you let me put a few little foals in this belly of yours, and I'll give you another twenty five percent!")
						), Main.game.getRalph())
					+"</br></br>"
					+ "By now, you don't really care about the discount, all you want is to feel that delicious cock sliding in between your legs."
					+ " Even before Ralph's finished giving you his offer, you're moaning in agreement, and he lets out a little laugh as he reaches down to grope your "
					+ (Main.game.getPlayer().getVaginaType()==VaginaType.HORSE_MORPH?"needy horse-pussy.":"hot little pussy.")
					+"</br></br>"
					+(Main.game.getPlayer().isVisiblyPregnant()
						?"Being very careful not to bump your pregnant belly against the counter, Ralph pushes your upper-torso down onto the counter top."
							+ " Grabbing your hips, he wastes no time in lining his massive cock up to your waiting cunt, and as he starts to push forwards, you let out a desperate squeal."
						:"Pushing your top-half down onto the counter top, Ralph wastes no time in lining his massive cock up to your waiting cunt, and as he starts to push forwards, you let out a desperate squeal.")
					+ " His wide, flared head slowly pushes its way into you, and you pant and squirm as your folds lewdly spread around his animalistic horse-cock."
					+ " With a quick step forwards, Ralph suddenly rams his impatient member deep into your hungry snatch, and you gasp and moan as he starts fucking you on the shop's counter-top."
					+ "</br></br>"
					+ "<b>You will now earn a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
							+(SexFlags.ralphDiscount+(Sex.getPartner().isWearingCondom()||Main.game.getPlayer().isVisiblyPregnant()?20:25)-(SexFlags.alertedCustomer?5:0))
					+"%</b> <b>discount.</b>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
			if(SexFlags.alertedCustomer)
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			
			SexFlags.alertedCustomer=false;
			if(Sex.getPartner().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_PREGNANCY));
		}
	};
	
	public static SexAction PARTNER_PENETRATES_ANUS = new SexAction(
			SexActionType.PARTNER_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
								+ " I'm knocking five percent off our deal, no complaints!", Main.game.getRalph())
						+"</br>"
						+ "Despite his usual polite and friendly temperament, he sounds quite angry as he scolds you, and you feel a little bad for putting the reputation of his shop on the line."
						+ " Then again, he was the one to suggest this, so it's not as though you're entirely the one at fault here..."
						+ "</br></br>"
						+ "<b>You will now earn a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b> <b>discount.</b>"
						+ "</br></br>");
			}
			
			UtilText.nodeContentSB.append("Ralph steps back, and, not even bothering to pull his trousers up, walks off to the front of the shop."
					+ " As you hear him locking the front door and pulling down the shop's blinds, you realise that the last customer must have left some time ago."
					+ " Before you have time to get out from under the counter, however, the imposing figure of Ralph moves back into view, blocking your way."
					+ "</br></br>"
					+ UtilText.parseSpeech("So you want a bigger discount, huh?! And how exactly do you intend to earn it?", Main.game.getRalph())
					+" He asks, crossing his arms and frowning down at you."
					+ "</br></br>"
					+ "You realise that although he was perfectly happy with having you suck his cock, he's going to need some convincing if you're to get him to use your other hole."
					+ " Shifting aside your clothing, you awkwardly shuffle around on the spot, bending over to present your "+Main.game.getPlayer().getAssCapacity().getDescriptor()+" asshole."
					+ " You hear Ralph struggling to suppress an eager groan, and as you shake your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips from side to side, he breaks his composure."
					+ "</br></br>"
					+ "Suddenly, you feel a strong pair of hands grab your hips, and before you can react, Ralph pulls you back out from under the counter, and in one swift movement, pushes you down, face first, onto the counter-top."
					+ " You let out a happy little cry at his dominant treatment of you, and as he roughly gropes and squeezes your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass, he leans down and growls in your ear, "
					+ UtilText.parseSpeech(
							"You know, I don't usually do this sort of thing... But damn, you've got a nice-looking ass! I'll give you another twenty percent if you let me take it for a spin.", Main.game.getRalph())
					+"</br></br>"
					+ "By now, you don't really care about the discount, all you want is to feel that delicious cock ramming its way into your rear entrance."
					+ " Even before Ralph's finished giving you his offer, you're crying out in agreement, and he wastes no time in lining his massive cock up to your waiting asshole."
					+ " You feel the wide, flared head rubbing all over your slutty hole, and as he starts to slowly push his animalistic horse-cock into you, you begin to squirm and squeal."
					+ " With a quick step forwards, Ralph suddenly rams his impatient member deep into your hungry back door, and you let out an ear-splitting cry as he starts fucking you on the shop's counter-top."
					+ "</br></br>"
					+ "<b>You will now earn a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"
						+(SexFlags.ralphDiscount+20-(SexFlags.alertedCustomer?5:0))
						+"%</b> <b>discount.</b>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter=false;
			if(SexFlags.alertedCustomer)
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			
			SexFlags.alertedCustomer=false;
			if(Sex.getPartner().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE)
				SexFlags.ralphDiscount+=20;
			else
				SexFlags.ralphDiscount+=25;

		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction PARTNER_ROUGH_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
			return (Main.game.getPlayer().isVisiblyPregnant()
						?"Grabbing your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, Ralph pulls you back slightly, moving your pregnant belly safely away from the counter's edge."
								+ " Making sure that he's given you enough room, he suddenly slams his massive cock fully into you, causing you to rock forwards as you let out a desperate wail."
								+ " Encouraged by your lewd reaction, Ralph starts rapidly thrusting his hips forwards and back, relentlessly pounding away at your slutty cunt."
								+ " With his legs in position on either side of yours, he focuses his movements solely in his hips, and your screams of ecstasy come out in little bursts as he jackhammers his huge horse-cock into your slit."
						:"Grabbing your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, Ralph pushes you hard into the counter, leaning down heavily on top of you as he starts relentlessly pounding away at your slutty cunt."
							+ " Using your body as a cushion, he focuses his movements solely in his hips, and your screams of ecstasy come out in little bursts as he jackhammers his huge horse-cock between your legs.")
					+ (Sex.getPartner().isWearingCondom()
						?" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, oblivious to the fact that their contents are going to be caught in the condom you made their owner wear."
						:" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, giving you a little reminder as to the origin of your impending creampie's filling.");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PARTNER_ROUGH_FUCK_ANAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
			return "Grabbing your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, Ralph pushes you hard into the counter, leaning down heavily on top of you as he starts relentlessly pounding away at your slutty ass."
				+ " Using your body as a cushion, he focuses his movements solely in his hips, and your screams of ecstasy come out in little bursts as he jackhammers his huge horse-cock into your back door."
				+ (Sex.getPartner().isWearingCondom()
					?" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, oblivious to the fact that their contents are going to be caught in the condom you made their owner wear."
					:" Every time he bottoms out, the momentum in his heavy sack causes his balls to swing up and slap against you, giving you a little reminder as to the origin of your impending creampie's filling.");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};
	
	public static SexAction PARTNER_NORMAL_FUCK = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.VAGINA_PLAYER) {
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
			return (Main.game.getPlayer().isVisiblyPregnant()
						?"Ralph continues holding your hips tightly, rocking you forwards and back as he fills you with his huge, black horse-cock."
						:"Ralph continues holding your hips tightly, rocking you into the counter as he fills you with his huge, black horse-cock.")
					+ " His heavy balls swing to and fro, impatient to deposit their potent load "
					+ (Sex.getPartner().isWearingCondom()
						?"into the condom that you've provided."
						:(Main.game.getPlayer().isVisiblyPregnant()?"deep into your hungry cunt.":"deep in your waiting womb."));
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE));
		}
	};
	
	public static SexAction PARTNER_NORMAL_FUCK_ANAL = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			PenetrationType.PENIS_PARTNER,
			OrificeType.ANUS_PLAYER) {
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
				return "Ralph continues holding your hips tightly, rocking you into the counter as he fills you with his huge, black horse-cock."
					+ " His heavy balls swing to and fro, impatient to deposit their potent load "
					+ (Sex.getPartner().isWearingCondom()
						?"into the condom that you've provided."
						:"deep in your waiting ass.");
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE), new ListValue<>(Fetish.FETISH_ANAL));
		}
	};

}
