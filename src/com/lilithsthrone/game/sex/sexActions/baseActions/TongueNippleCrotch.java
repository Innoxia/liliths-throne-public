package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.3.7.8
 * @author Innoxia
 */
public class TongueNippleCrotch {
	
	private static String getBaseSucklingActionName(GameCharacter crotchBoobCharacter) {
		switch(crotchBoobCharacter.getNippleCrotchShape()) {
			case LIPS:
				if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
					return "lipple suckle";
				} else {
					return "lipple kiss";
				}
			case INVERTED:
			case NORMAL:
				if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
					return "nipple suckle";
				} else {
					return "nipple kiss";
				}
			case VAGINA:
				if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
					return "nipple-cunt suckle";
				} else {
					return "nipple-cunt kiss";
				}
		}
		return "";
	}

	private static String getBaseBreastFeedingActionName(GameCharacter crotchBoobCharacter) {
		if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
			return UtilText.parse(crotchBoobCharacter, "breastfeed ([npc.crotchBoobs])");
		}
		return UtilText.parse(crotchBoobCharacter, "[npc.crotchNipples] sucked");
	}
	
	public static final SexAction SUCKLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch();
		}
		@Override
		public boolean isPhysicallyPossible() {
			return true; // Need this to override detection of whether nipples are penetrable or not.
		}
		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
						return "Suckle crotch-lipples";
					} else {
						return "Kiss crotch-lipples";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
						return "Suckle crotch-nipples";
					} else {
						return "Kiss crotch-nipples";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
						return "Suckle crotch-nipple-cunts";
					} else {
						return "Kiss crotch-nipple-cunts";
					}
			}
			return "";
		}
		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Press your [npc.lips] up to [npc2.namePos] [npc2.crotchBoob+] and start kissing [npc2.her] lip-like nipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Press your [npc.lips] up to [npc2.namePos] [npc2.crotchBoob+] and start sucking on [npc2.her] [npc2.crotchNipple+].");
					break;
				case VAGINA:
					sb.append("Press your [npc.lips] up to [npc2.namePos] [npc2.crotchBoob+] and start kissing [npc2.her] pussy-like nipples.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?" As [npc2.sheIsFull] [style.colourMinorGood(lactating)], you will get to drink [npc2.her] [npc2.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			Map<SexPace, List<String>> descriptors = Util.newHashMapOfValues(
					new Value<>(SexPace.DOM_GENTLE, Util.newArrayListOfValues("slowly", "gently")),
					new Value<>(SexPace.SUB_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.SUB_EAGER, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_ROUGH, Util.newArrayListOfValues("forcefully", "roughly")));
			
			List<String> desList = descriptors.get(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()));
			int index = Util.random.nextInt(desList.size());
			String[] desc = new String[] {desList.get(index), desList.get((index+1)%desList.size())};
			
			String oneOfBoobsDescriptor = Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRows()==0?"":" one of";
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] "+desc[0]+" [npc.verb(lean)] in towards [npc2.namePos] [npc2.crotchBoobs+], before pressing [npc.her] [npc.lips+] against [npc2.her] lipples and "+desc[1]+" starting to make out with them.",
							Util.capitaliseSentence(desc[0])+" pressing [npc.her] [npc.lips+] against ones of [npc2.namePos] [npc2.crotchBoobs+], [npc.name] [npc.verb(start)] "+desc[1]+" to making out with [npc2.her] lipples."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Just like a real mouth, [npc2.namePos] lip-like nipples part to reveal a throat-like orifice, allowing [npc.name] to thrust [npc.her] tongue into [npc2.her] [npc2.crotchBoob].");
					}
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] "+desc[0]+" [npc.verb(press)] [npc.her] [npc.lips+] against "+oneOfBoobsDescriptor+"[npc2.namePos] [npc2.crotchBoobs+], before starting to "+desc[1]+" suck and kiss [npc2.her] [npc2.crotchNipple+].",
							Util.capitaliseSentence(desc[0])+" pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob], [npc.name] [npc.verb(start)] "+desc[1]+" sucking and kissing [npc2.her] [npc2.crotchNipple+]."));

					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" [npc.Name] [npc.verb(feel)] that the centre of [npc2.namePos] nipple opens up to reveal an orifice, allowing [npc.herHim] to thrust [npc.her] tongue into [npc2.her] [npc2.crotchBoob].");
					}
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] "+desc[0]+" [npc.verb(lean)] in towards [npc2.namePos] [npc2.crotchBoobs+], before pressing [npc.her] [npc.lips+] against one of [npc2.her] pussy-like nipples and "+desc[1]+" starting to kiss and lick it.",
							Util.capitaliseSentence(desc[0])+" pressing [npc.her] [npc.lips+] against "+oneOfBoobsDescriptor+"[npc2.namePos] [npc2.crotchBoobs], [npc.name] [npc.verb(start)] "+desc[1]+" sucking and kissing [npc2.her] pussy-like nipple."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Just like a real vagina, the centre of [npc2.namePos] cunt-like nipple houses an orifice, allowing [npc.name] to thrust [npc.her] tongue into [npc2.her] [npc2.crotchBoob].");
					}
					break;
			}
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) { // Milk:
				switch(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
					case ZERO_NONE:
						break;
					case ONE_TRICKLE:
					case TWO_SMALL_AMOUNT:
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" It only takes a moment before [npc2.her] [npc2.milk+] is trickling out into [npc.her] mouth, and",
								" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.milk+] as it squirts out into [npc.her] mouth, and"));
						break;
					case FOUR_LARGE_AMOUNT:
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" It only takes a moment before [npc2.her] [npc2.milk+] is flowing out into [npc.her] mouth, and",
								" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.milk+] as it streams out into [npc.her] mouth, and"));
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" It only takes a moment before [npc2.her] [npc2.milk+] is pouring out into [npc.her] mouth, and",
								" [npc.Name] quickly [npc.verb(get)] a taste of [npc2.her] [npc2.milk+] as it gushes out into [npc.her] mouth, and"));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] can't help but let out a soft sigh in response to the deeply satisfying feeling.",
									", gently pulling [npc.her] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] softly [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] can't help but let out [npc2.a_moan+] in response to the deeply satisfying feeling.",
									", happily pulling [npc.her] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] readily [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] can't help but let out [npc2.a_moan+] in response to the deeply satisfying feeling.",
									", roughly forcing [npc.her] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] [npc2.verb(order)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.name] can't help but let out [npc2.a_sob+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.herHim] away from [npc2.herHim].",
									", desperately trying to push [npc.namePos] head away from [npc2.her] [npc2.crotchBoob+], [npc2.name] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
							break;
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc.she] [npc.verb(let)] out [npc.a_moan] as [npc.she] [npc.verb(gulp)] it down.",
							" [npc.she] [npc.verb(let)] out a delighted [npc.moan] as [npc.she] [npc.verb(swallow)] it down."));
				}
				
			} else { // No milk:
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Pushing [npc2.her] chest out, [npc2.name] can't help but let out a happy little sigh in response to the delightful feeling of having [npc2.her] [npc2.crotchNipple] sucked on.",
									" Gently pulling [npc.namePos] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] softly [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Pushing [npc2.her] chest out, [npc2.name] can't help but let out [npc2.a_moan+] in response to the delightful feeling of having [npc2.her] [npc2.crotchNipple] sucked on.",
									" Happily pulling [npc.namePos] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] readily [npc2.verb(encourage)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" Pushing [npc2.her] chest out, [npc2.name] can't help but let out [npc2.a_moan+] in response to the delightful feeling of having [npc2.her] [npc2.crotchNipple] sucked on.",
									" Roughly forcing [npc.namePos] head into [npc2.her] [npc2.crotchBoob+], [npc2.name] [npc2.verb(order)] [npc.herHim] to keep on suckling on [npc2.her] [npc2.crotchNipple+]."));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] can't help but let out [npc2.a_sob+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.herHim] away from [npc2.herHim].",
									" Desperately trying to push [npc.namePos] head away from [npc2.her] [npc2.crotchBoob+], [npc2.name] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
							break;
					}
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
				float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawMilkStorageValue()/5));
				
				if(suckleAmount>Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()) {
					suckleAmount = Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue();
				}
				
				String rs = Main.sex.getCharacterPerformingAction().ingestFluid(
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterTargetedForSexAction(this).getMilkCrotch(),
							SexAreaOrifice.MOUTH,
							suckleAmount);
				Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-suckleAmount);
				return rs;
			}
			return "";
		}
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			String actionDescription = "";
			switch(Main.sex.getCharacterTargetedForSexAction(action).getNippleCrotchShape()) {
				case LIPS:
					actionDescription = UtilText.returnStringAtRandom(
							"making out with [npc2.her] mouth-like lipple",
							"kissing [npc2.her] lipple");
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(action).getBreastCrotchRawStoredMilkValue()>0) {
						actionDescription = UtilText.returnStringAtRandom(
								"suckling [npc2.her] [npc2.crotchNipple+]",
								"suckling the [npc2.milk] from [npc2.her] nipple");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"sucking on [npc2.her] [npc2.crotchNipple+]",
								"sucking and kissing [npc2.her] [npc2.crotchNipple]");
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(action).isBreastCrotchFuckableNipplePenetration()) {
						actionDescription = UtilText.returnStringAtRandom(
								"eating out [npc2.her] pussy-like nipple-cunt",
								"tongue-fucking [npc2.her] nipple-cunt");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"licking [npc2.her] pussy-like nipple-cunt",
								"kissing and licking [npc2.her] nipple-cunt");
					}
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] eagerly [npc2.verb(push)] [npc2.her] [npc2.crotchBoob+] out against [npc.namePos] [npc.lips+],"
									+ " releasing [npc2.a_moan+] as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep on "+actionDescription+".",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, eagerly pushing [npc2.her] [npc2.crotchBoob+] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.herHim] to continue "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(grind)] [npc2.her] [npc2.crotchBoob+] down against [npc.namePos] [npc.face],"
									+ " before eagerly begging [npc.herHim] to continue "+actionDescription+"."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to recoil [npc2.her] [npc2.crotchBoobs] away from [npc.namePos] unwelcome [npc.tongue],"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] "+actionDescription+".",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.tongue],"
									+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] "+actionDescription+"."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(push)] [npc2.her] [npc2.crotchBoob+] out against [npc.namePos] [npc.lips+],"
									+ " letting out a soft [npc2.moan] as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep on "+actionDescription+".",
							" A soft [npc2.moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, gently bucking [npc2.her] [npc2.crotchBoob+] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] gently [npc2.verb(press)] [npc2.her] [npc2.crotchBoob+] down against [npc.namePos] [npc.face],"
									+ " before eagerly begging [npc.herHim] to continue "+actionDescription+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] violently [npc2.verb(thrust)] [npc2.her] [npc2.crotchBoob+] out against [npc.namePos] [npc.lips+],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(order)] [npc.herHim] to keep on "+actionDescription+".",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, roughly slamming [npc2.her] [npc2.crotchBoob+] into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(order)] [npc.name] to continue "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] forcefully [npc2.verb(press)] [npc2.her] [npc2.crotchBoob+] down against [npc.namePos] [npc.face],"
									+ " before aggressively commanding [npc.herHim] to continue "+actionDescription+"."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(push)] [npc2.her] [npc2.crotchBoob+] out against [npc.namePos] [npc.lips+],"
									+ " letting out [npc2.a_moan] as [npc2.she] [npc2.verb(encourage)] [npc.herHim] to keep on "+actionDescription+".",
							" [npc2.A_moan] drifts out from between [npc2.namePos] [npc2.lips+],"
									+ " and, bucking [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], [npc2.she] [npc2.verb(beg)] for [npc.name] to continue "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(grind)] [npc2.her] [npc2.crotchBoob+] down against [npc.namePos] [npc.face],"
									+ " before begging [npc.herHim] to continue "+actionDescription+"."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle "+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Gently kiss and make-out with [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Gently kiss and suck [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Gently kiss and lick [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?" As [npc2.sheIsFull] [style.colourMinorGood(lactating)], you will get to drink [npc2.her] [npc2.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on gently making out with [npc2.namePos] mouth-like lipple.",
								"Gently running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple, before starting to slowly kiss and make out with it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on gently making out with [npc2.namePos] mouth-like lipple, drinking down the [npc2.milk] that flows out in the process.",
								"Gently running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple,"
										+ " before starting to make out with it and drink down the [npc2.milk] which flows forth."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on gently kissing and licking [npc2.namePos] [npc2.crotchNipple+].",
								"Gently running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+], before starting to slowly lick and kiss it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on gently kissing and licking [npc2.namePos] [npc2.crotchNipple+], drinking down the [npc2.milk] that flows out in the process.",
								"Gently running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+],"
										+ " before starting to suckle it and drink down the [npc2.milk] which flows out into [npc.her] mouth."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on gently "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")+" [npc2.namePos] pussy-like nipple-cunt.",
								"Gently running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt, before starting to slowly kiss and lick it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Gently pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on gently "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")
									+" [npc2.namePos] pussy-like nipple-cunt, drinking down the [npc2.milk] that flows out in the process.",
								"Gently running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt,"
										+ " before starting to lick it and drink down the [npc2.milk] which flows forth."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Kiss and make-out with [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Kiss and suck [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Kiss and lick [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?" As [npc2.sheIsFull] [style.colourMinorGood(lactating)], you will get to drink [npc2.her] [npc2.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily making out with [npc2.namePos] mouth-like lipple.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple, before starting to desperately kiss and make out with it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily making out with [npc2.namePos] mouth-like lipple, drinking down the [npc2.milk] that flows out in the process.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple,"
										+ " before starting to make out with it and drink down the [npc2.milk] which flows forth."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily kissing and licking [npc2.namePos] [npc2.crotchNipple+].",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+], before starting to desperately lick and kiss it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily kissing and licking [npc2.namePos] [npc2.crotchNipple+], drinking down the [npc2.milk] that flows out in the process.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+],"
										+ " before starting to suckle it and drink down the [npc2.milk] which flows out into [npc.her] mouth."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")+" [npc2.namePos] pussy-like nipple-cunt.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt, before starting to desperately kiss and lick it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")
										+" [npc2.namePos] pussy-like nipple-cunt, drinking down the [npc2.milk] that flows out in the process.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt,"
										+ " before starting to lick it and drink down the [npc2.milk] which flows forth."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "Rough "+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Roughly kiss and make-out with [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Roughly kiss and suck [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Roughly kiss and lick [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?" As [npc2.sheIsFull] [style.colourMinorGood(lactating)], you will get to drink [npc2.her] [npc2.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on roughly making out with [npc2.namePos] mouth-like lipple.",
								"Roughly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple, before starting to forcefully kiss and make out with it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on roughly making out with [npc2.namePos] mouth-like lipple, drinking down the [npc2.milk] that flows out in the process.",
								"Roughly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple,"
										+ " before starting to make out with it and drink down the [npc2.milk] which flows forth."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on roughly kissing and licking [npc2.namePos] [npc2.crotchNipple+].",
								"Roughly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+], before starting to forcefully lick and kiss it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on roughly kissing and licking [npc2.namePos] [npc2.crotchNipple+], drinking down the [npc2.milk] that flows out in the process.",
								"Roughly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+],"
										+ " before starting to suckle it and drink down the [npc2.milk] which flows out into [npc.her] mouth."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on roughly "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")+" [npc2.namePos] pussy-like nipple-cunt.",
								"Roughly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt, before starting to forcefully kiss and lick it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Roughly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on roughly "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")
										+" [npc2.namePos] pussy-like nipple-cunt, drinking down the [npc2.milk] that flows out in the process.",
								"Roughly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt,"
										+ " before starting to lick it and drink down the [npc2.milk] which flows forth."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist "+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Try to pull away from [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Try to pull away from [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Try to pull away from [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			return sb.toString();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc2.verb(continue)] gently pressing [npc2.her] [npc2.crotchNipple+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.lips] right back against [npc2.her] [npc2.crotchNipple+],"
									+ " gently grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.crotchBoobs+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.crotchNipple+] against [npc.her] [npc.lips+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] roughly [npc2.verb(slam)] [npc2.her] [npc2.crotchNipple+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] violently [npc2.verb(force)] [npc2.herself] on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] violently [npc2.verb(force)] [npc.her] [npc.lips] right back against [npc2.her] [npc2.crotchNipple+],"
									+ " roughly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.crotchBoobs+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] roughly [npc2.verb(grind)] [npc2.her] [npc2.crotchNipple+] against [npc.her] [npc.lips+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(try)] to pull [npc.her] [npc.face] back, but [npc2.name] [npc2.verb(continue)] eagerly pressing [npc2.her] [npc2.crotchNipple+] down against [npc.her] [npc.lips+],"
									+ " holding [npc.herHim] in place as [npc2.she] [npc2.verb(force)] [npc2.herself] on [npc.herHim].",
							"With [npc.a_sob+], [npc.name] [npc.verb(try)] to pull away from [npc2.name], but [npc2.she] quickly [npc2.verb(force)] [npc.her] [npc.lips] right back against [npc2.her] [npc2.crotchNipple+],"
									+ " eagerly grinding [npc2.herself] against [npc.herHim] as [npc2.she] [npc2.verb(ignore)] [npc.her] struggles.",
							"Tears start to well up in [npc.namePos] [npc.eyes], and with [npc.a_sob+], [npc.she] [npc.verb(try)] to pull [npc.her] mouth away from [npc2.namePos] [npc2.crotchBoobs+],"
									+ " but [npc2.name] quickly [npc2.verb(shift)] position, ignoring [npc.her] protests as [npc2.she] eagerly [npc2.verb(press)] [npc2.her] [npc2.crotchNipple+] against [npc.her] [npc.lips+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Kiss and make-out with [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Kiss and suck [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Kiss and lick [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?" As [npc2.sheIsFull] [style.colourMinorGood(lactating)], you will get to drink [npc2.her] [npc2.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on making out with [npc2.namePos] mouth-like lipple.",
								"Running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple, before starting to desperately kiss and make out with it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on making out with [npc2.namePos] mouth-like lipple, drinking down the [npc2.milk] that flows out in the process.",
								"Running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple,"
										+ " before starting to make out with it and drink down the [npc2.milk] which flows forth."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on kissing and licking [npc2.namePos] [npc2.crotchNipple+].",
								"Running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+], before starting to desperately lick and kiss it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on kissing and licking [npc2.namePos] [npc2.crotchNipple+], drinking down the [npc2.milk] that flows out in the process.",
								"Running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+],"
										+ " before starting to suckle it and drink down the [npc2.milk] which flows out into [npc.her] mouth."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")+" [npc2.namePos] pussy-like nipple-cunt.",
								"Running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt, before starting to desperately kiss and lick it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")
									+" [npc2.namePos] pussy-like nipple-cunt, drinking down the [npc2.milk] that flows out in the process.",
								"Running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt,"
										+ " before starting to lick it and drink down the [npc2.milk] which flows forth."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "Eager "+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Eagerly kiss and make-out with [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Eagerly kiss and suck [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Eagerly kiss and lick [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?" As [npc2.sheIsFull] [style.colourMinorGood(lactating)], you will get to drink [npc2.her] [npc2.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily making out with [npc2.namePos] mouth-like lipple.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple, before starting to desperately kiss and make out with it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] mouth-like lipple, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily making out with [npc2.namePos] mouth-like lipple, drinking down the [npc2.milk] that flows out in the process.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] lipple,"
										+ " before starting to make out with it and drink down the [npc2.milk] which flows forth."));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily kissing and licking [npc2.namePos] [npc2.crotchNipple+].",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+], before starting to desperately lick and kiss it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(suck)] and [npc.verb(kiss)] [npc2.her] [npc2.crotchNipple+], letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily kissing and licking [npc2.namePos] [npc2.crotchNipple+], drinking down the [npc2.milk] that flows out in the process.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] [npc2.crotchNipple+],"
										+ " before starting to suckle it and drink down the [npc2.milk] which flows out into [npc.her] mouth."));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.do] so.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily "
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")+" [npc2.namePos] pussy-like nipple-cunt.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt, before starting to desperately kiss and lick it."));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"Eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoob+],"
										+ " [npc.name] [npc.verb(kiss)] [npc2.her] pussy-like nipple-cunt, letting out a muffled [npc.moan] as [npc.she] [npc.verb(drink)] down the [npc2.milk] that flows forth.",
								"With a muffled [npc.moan], [npc.name] [npc.verb(focus)] on greedily "+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"eating out":"kissing and licking")
									+" [npc2.namePos] pussy-like nipple-cunt, drinking down the [npc2.milk] that flows out in the process.",
								"Eagerly running the tip of [npc.her] [npc.tongue+] over [npc2.namePos] [npc2.crotchBoob+], [npc.name] [npc.verb(home)] in on [npc2.her] nipple-cunt,"
										+ " before starting to lick it and drink down the [npc2.milk] which flows forth."));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop "+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("Pull away from [npc2.namePos] mouth-like lipples.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Pull away from [npc2.namePos] nipples.");
					break;
				case VAGINA:
					sb.append("Pull away from [npc2.namePos] pussy-like nipple-cunts.");
					break;
			}
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last rough lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.crotchNipple+].",
							"Giving [npc2.namePos] [npc2.crotchNipple+] a final, rough kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] [npc2.crotchBoobs+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With one last lick, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.namePos] [npc2.crotchNipple+].",
							"Giving [npc2.namePos] [npc2.crotchNipple+] a final, wet kiss, [npc.name] [npc.verb(pull)] [npc.her] [npc.face] away from [npc2.her] [npc2.crotchBoobs+]."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(continue)] to struggle against [npc.name], [npc2.sobbing] and squirming in discomfort as [npc2.she] realise that [npc.name] isn't completely finished with [npc2.herHim] just yet.",
								" Realising that [npc.sheHasFull]n't completely finished with [npc2.herHim] just yet, [npc2.name] [npc2.verb(continue)] struggling and [npc2.sobbing],"
										+ " tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(plead)] for [npc.name] to let [npc2.herHim] go."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] takes [npc.her] [npc.tongue+] away from [npc2.her] [npc2.nipples+].",
								" [npc2.A_moan+] drifts out from between [npc2.namePos] [npc2.lips+] as [npc.name] [npc.verb(stop)] pleasuring [npc2.her] [npc2.nipples+], betraying [npc2.her] desire for more of [npc.her] attention."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction BREASTFEED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch();
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) {
				return "Breastfeed ([npc.crotchBoobs])";
			}
			return "Get [npc.crotchNipples] sucked";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) {
				return "Pull [npc2.namePos] face into your [npc.crotchBoobs] and get [npc2.herHim] to start drinking your [npc.milk].";
			}
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					return "Pull [npc2.namePos] face into your [npc.crotchBoobs] and get [npc2.herHim] to start kissing your lipples.";
				case INVERTED:
				case NORMAL:
					return "Pull [npc2.namePos] face into your [npc.crotchBoobs] and get [npc2.herHim] to start kissing and sucking your nipples.";
				case VAGINA:
					return "Pull [npc2.namePos] face into your [npc.crotchBoobs] and get [npc2.herHim] to start kissing and licking your pussy-like nipple-cunts.";
			}
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			

			UtilText.nodeContentSB.setLength(0);
			Map<SexPace, List<String>> descriptors = Util.newHashMapOfValues(
					new Value<>(SexPace.DOM_GENTLE, Util.newArrayListOfValues("slowly", "gently")),
					new Value<>(SexPace.SUB_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.SUB_EAGER, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_NORMAL, Util.newArrayListOfValues("eagerly", "greedily")),
					new Value<>(SexPace.DOM_ROUGH, Util.newArrayListOfValues("forcefully", "roughly")));
			
			List<String> desList = descriptors.get(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()));
			int index = Util.random.nextInt(desList.size());
			String[] desc = new String[] {desList.get(index), desList.get((index+1)%desList.size())};

			String oneOfBoobsDescriptor = Main.sex.getCharacterPerformingAction().getBreastCrotchRows()==0?"":" one of";
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] "+desc[0]+" [npc.verb(guide)] [npc2.her] [npc2.lips+] up to "+oneOfBoobsDescriptor+"[npc.her] [npc.crotchBoobs+],"
									+ " before "+desc[1]+" getting [npc2.herHim] to kiss and make out with [npc.her] lipple.",
							"Taking hold of [npc2.namePos] head, [npc.name] "+desc[0]+" [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.crotchBoobs+], before "+desc[1]+" getting [npc2.herHim] to kiss [npc.her] mouth-like lipple."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Just like a real mouth, [npc.namePos] lip-like nipples part to reveal a throat-like orifice, allowing [npc2.name] to thrust [npc2.her] tongue into [npc.her] [npc.crotchBoob].");
					}
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] "+desc[0]+" [npc.verb(guide)] [npc2.her] [npc2.lips+] up to "+oneOfBoobsDescriptor+"[npc.her] [npc.crotchBoobs+], before "+desc[1]+" getting [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+].",
							"Taking hold of [npc2.namePos] head, [npc.name] "+desc[0]+" [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.crotchBoobs+], before "+desc[1]+" getting [npc2.herHim] to suck and kiss [npc.her] [npc.crotchNipple+]."));

					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" [npc2.Name] [npc2.verb(feel)] that the centre of [npc.namePos] nipple opens up to reveal an orifice, allowing [npc2.herHim] to thrust [npc2.her] tongue into [npc.her] [npc.crotchBoob].");
					}
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Taking hold of [npc2.namePos] head, [npc.name] "+desc[0]+" [npc.verb(guide)] [npc2.her] [npc2.lips+] up to "+oneOfBoobsDescriptor+"[npc.her] [npc.crotchBoobs+], before "+desc[1]+" getting [npc2.herHim] to lick and kiss [npc.her] nipple-cunt.",
							"Taking hold of [npc2.namePos] head, [npc.name] "+desc[0]+" [npc.verb(pull)] [npc2.herHim] into [npc.her] [npc.crotchBoobs+], before "+desc[1]+" getting [npc2.herHim] to lick and kiss [npc.her] nipple-cunt."));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" Just like a real vagina, the centre of [npc.namePos] pussy-like nipple houses an orifice, allowing [npc2.name] to thrust [npc2.her] tongue into [npc.her] [npc.crotchBoob].");
					}
					break;
			}

			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) { // Milk:
				switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
					case ZERO_NONE:
						break;
					case ONE_TRICKLE:
					case TWO_SMALL_AMOUNT:
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" It only takes a moment before [npc.namePos] [npc.milk+] is trickling out into [npc2.her] mouth,",
								" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.milk+] as it squirts out into [npc2.her] mouth,"));
						break;
					case FOUR_LARGE_AMOUNT:
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" It only takes a moment before [npc.namePos] [npc.milk+] is flowing out into [npc2.her] mouth,",
								" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.milk+] as it streams out into [npc2.her] mouth,"));
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" It only takes a moment before [npc.namePos] [npc.milk+] is pouring out into [npc2.her] mouth,",
								" [npc2.Name] quickly [npc2.verb(get)] a taste of [npc.her] [npc.milk+] as it gushes out into [npc2.her] mouth,"));
						break;
				}
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" and, with a soft, muffled sigh, [npc2.she] [npc2.verb(bury)] [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and [npc2.verb(continue)] drinking [npc.her] [npc.milk].",
								" and, gently pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] softly suckling on [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" and, with a muffled, [npc2.moan+], [npc2.she] [npc2.verb(bury)] [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and [npc2.verb(continue)] drinking [npc.her] [npc.milk].",
								" and, happily pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] eagerly suckling on [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" and, with a muffled, [npc2.moan+], [npc2.she] roughly [npc2.verb(bury)] [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and [npc2.verb(continue)] greedily drinking [npc.her] [npc.milk].",
								" and, forcefully pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] roughly suckling on [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" and [npc2.she] can't [npc.verb(help)] but [npc.verb(let)] out [npc2.a_moan+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.name] away from [npc2.herHim].",
								" and, desperately trying to pull away from [npc.namePos] [npc.crotchBoobs+], [npc2.she] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
						break;
				}
			
			} else { // No milk:
				String suckleDesc = "";
				switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
					case LIPS:
						suckleDesc = "[npc2.verb(kiss)]";
						break;
					case INVERTED:
					case NORMAL:
						suckleDesc = "[npc2.verb(suck)] and [npc2.verb(kiss)]";
						break;
					case VAGINA:
						suckleDesc = "[npc2.verb(kiss)] and [npc2.verb(lick)]";
						break;
				}
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" With a soft, muffled sigh, [npc2.she] [npc2.verb(bury)] [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and gently "+suckleDesc+" [npc.her] [npc.crotchNipple+].",
								" Gently pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] softly "+suckleDesc+" [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" With a muffled, [npc2.moan+], [npc2.she] [npc2.verb(bury)] [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and greedily "+suckleDesc+" [npc.her] [npc.crotchNipple+].",
								" Happily pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] eagerly "+suckleDesc+" [npc.her] [npc.crotchNipple+]."));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" With a muffled, [npc2.moan+], [npc2.she] roughly [npc2.verb(bury)] [npc2.her] face in [npc.namePos] [npc.crotchBoobs+] and forcefully "+suckleDesc+" [npc.her] [npc.crotchNipple+].",
								" Forcefully pressing [npc2.her] [npc2.face] into [npc.namePos] [npc.crotchBoob+], [npc2.she] roughly "+suckleDesc+" [npc.her] [npc.crotchNipple+]."));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] can't [npc.verb(help)] but [npc.verb(let)] out [npc2.a_moan+] as [npc2.she] frantically [npc2.verb(try)] to push [npc.name] away from [npc2.herHim].",
								" Desperately trying to pull away from [npc.namePos] [npc.crotchBoobs+], [npc2.she] [npc2.verb(plead)] with [npc.herHim] to leave [npc2.herHim] alone."));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) {
				float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterPerformingAction().getBreastCrotchRawMilkStorageValue()/5));
				
				if(suckleAmount>Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()) {
					suckleAmount = Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue();
				}
				
				String rs = Main.sex.getCharacterTargetedForSexAction(this).ingestFluid(
						Main.sex.getCharacterPerformingAction(),
						Main.sex.getCharacterPerformingAction().getMilkCrotch(),
						SexAreaOrifice.MOUTH,
						suckleAmount);
				Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-suckleAmount);
				return rs;
			}
			return "";
		}
	};
	

	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			String actionDescription = "";
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					actionDescription = UtilText.returnStringAtRandom(
							"making out with [npc.her] mouth-like lipple",
							"kissing [npc.her] lipple");
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(action).getBreastCrotchRawStoredMilkValue()>0) {
						actionDescription = UtilText.returnStringAtRandom(
								"suckling [npc.her] [npc.crotchNipple+]",
								"suckling the [npc.milk] from [npc.her] nipple");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"sucking on [npc.her] [npc.crotchNipple+]",
								"sucking and kissing [npc.her] [npc.crotchNipple]");
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(action).isBreastCrotchFuckableNipplePenetration()) {
						actionDescription = UtilText.returnStringAtRandom(
								"eating out [npc.her] pussy-like nipple-cunt",
								"tongue-fucking [npc.her] nipple-cunt");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"licking [npc.her] pussy-like nipple-cunt",
								"kissing and licking [npc.her] nipple-cunt");
					}
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] greedily [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" A muffled [npc2.moan] bursts out from [npc2.namePos] mouth, and, eagerly pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] eagerly [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob], before greedily "+actionDescription+"."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.face] away from [npc.namePos] [npc.crotchBoobs], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.name] away,"
									+ " squirming and protesting as [npc.she] [npc.verb(continue)] to force [npc.her] [npc.crotchNipple+] down against [npc2.her] [npc2.lips+].",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to recoil away from [npc.namePos] [npc.crotchBoobs],"
									+ " struggling against [npc.herHim] as [npc.she] [npc.verb(continue)] to press [npc.her] [npc.crotchNipple+] against [npc2.her] [npc2.lips+]."));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] gently [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, and, gently pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] softly [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob], before gently "+actionDescription+"."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] roughly [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, and, roughly pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] roughly [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob], before forcefully "+actionDescription+"."));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], letting out a muffled [npc2.moan] as [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" A muffled [npc2.moan] drifts out from [npc2.namePos] mouth, and, pressing [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob+], [npc2.she] [npc2.verb(continue)] "+actionDescription+".",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoob], before "+actionDescription+"."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction BREASTFEED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "Gentle "+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("Gently push your lipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and make-out with them.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Gently push your nipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and suck on them.");
					break;
				case VAGINA:
					sb.append("Gently push your nipple-cunts down against [npc2.namePos] mouth and get [npc2.herHim] to lick them.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?" As you are [style.colourMinorGood(lactating)], you will get [npc2.herHim] to drink your [npc.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(push)] [npc.her] mouth-like lipple against [npc2.namePos] [npc2.lips+], before using [npc.her] control over it to gently kiss [npc2.herHim].",
							"With [npc.a_moan+], [npc.name] gently [npc.verb(press)] [npc.her] lipple against [npc2.namePos] mouth, before kissing [npc2.herHim] fully on the [npc2.lips+].",
							"Letting out a soft [npc.moan], [npc.name] [npc.verb(use)] [npc.her] control over [npc.her] lipple to kiss [npc2.name] fully on the [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] gently [npc.verb(push)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] gently [npc.verb(press)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"Letting out a soft [npc.moan], [npc.name] gently [npc.verb(grind)] [npc.her] [npc.crotchNipple+] down against [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] gently [npc.verb(push)] [npc.her] pussy-like nipple-cunt down against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] gently [npc.verb(press)] [npc.her] nipple-cunt against [npc2.namePos] [npc2.lips+].",
							"Letting out a soft [npc.moan], [npc.name] gently [npc.verb(grind)] [npc.her] nipple-cunt down against [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("Push your lipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and make-out with them.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Push your nipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and suck on them.");
					break;
				case VAGINA:
					sb.append("Push your nipple-cunts down against [npc2.namePos] mouth and get [npc2.herHim] to lick them.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?" As you are [style.colourMinorGood(lactating)], you will get [npc2.herHim] to drink your [npc.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] mouth-like lipple against [npc2.namePos] [npc2.lips+], before using [npc.her] control over it to passionately kiss [npc2.herHim].",
							"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] lipple against [npc2.namePos] mouth, before passionately kissing [npc2.herHim] fully on the [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] control over [npc.her] lipple to passionately kiss [npc2.name] fully on the [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(push)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.crotchNipple+] down against [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(push)] [npc.her] pussy-like nipple-cunt down against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] nipple-cunt against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] nipple-cunt down against [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "Rough "+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("Roughly push your lipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and make-out with them.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Roughly push your nipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and suck on them.");
					break;
				case VAGINA:
					sb.append("Roughly push your nipple-cunts down against [npc2.namePos] mouth and get [npc2.herHim] to lick them.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?" As you are [style.colourMinorGood(lactating)], you will get [npc2.herHim] to drink your [npc.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(push)] [npc.her] mouth-like lipple against [npc2.namePos] [npc2.lips+], before using [npc.her] control over it to forcefully kiss [npc2.herHim].",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(press)] [npc.her] lipple against [npc2.namePos] mouth, before forcefully kissing [npc2.herHim] fully on the [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] control over [npc.her] lipple to forcefully kiss [npc2.name] fully on the [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(push)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(press)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] [npc.crotchNipple+] down against [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] roughly [npc.verb(push)] [npc.her] pussy-like nipple-cunt down against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(press)] [npc.her] nipple-cunt against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] nipple-cunt down against [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "Resist "+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.nipples+] away from [npc2.namePos] mouth.";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] gently [npc2.verb(slide)] [npc2.her] [npc2.tongue] over [npc.her] [npc.crotchNipple+].",
							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a soft kiss on [npc.her] [npc.crotchNipple+].",
							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] gently kissing [npc.her] [npc.crotchNipple+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] roughly [npc2.verb(slide)] [npc2.her] [npc2.tongue] over [npc.her] [npc.crotchNipple+].",
							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] forcefully [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a wet kiss on [npc.her] [npc.crotchNipple+].",
							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] firmly holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] roughly kissing [npc.her] [npc.crotchNipple+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] desperately [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs] away from [npc2.namePos] [npc2.face],"
									+ " letting out [npc.a_sob+] as [npc2.name] greedily [npc2.verb(slide)] [npc2.her] [npc2.tongue] over [npc.her] [npc.crotchNipple+].",
							"Letting out [npc.a_sob+], [npc.name] desperately [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.lips+]."
									+ " Ignoring [npc.her] protests, [npc2.name] [npc2.verb(hold)] [npc.name] in place as [npc2.she] [npc2.verb(plant)] a passionate kiss on [npc.her] [npc.crotchNipple+].",
							"With [npc.a_sob+], [npc.name] frantically [npc.verb(try)] to pull [npc.her] [npc.crotchBoobs+] away from [npc2.namePos] [npc2.lips+], but [npc2.she] holds [npc.herHim] in place,"
									+ " ignoring [npc.her] protests as [npc2.she] [npc2.verb(continue)] eagerly kissing [npc.her] [npc.crotchNipple+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BREASTFEED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("Push your lipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and make-out with them.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Push your nipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and suck on them.");
					break;
				case VAGINA:
					sb.append("Push your nipple-cunts down against [npc2.namePos] mouth and get [npc2.herHim] to lick them.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?" As you are [style.colourMinorGood(lactating)], you will get [npc2.herHim] to drink your [npc.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] mouth-like lipple against [npc2.namePos] [npc2.lips+], before using [npc.her] control over it to passionately kiss [npc2.herHim].",
							"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] lipple against [npc2.namePos] mouth, before passionately kissing [npc2.herHim] fully on the [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] control over [npc.her] lipple to passionately kiss [npc2.name] fully on the [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] [npc.crotchNipple+] down against [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] pussy-like nipple-cunt down against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] nipple-cunt against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(grind)] [npc.her] nipple-cunt down against [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "Eager "+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("Eagerly push your lipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and make-out with them.");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("Eagerly push your nipples down against [npc2.namePos] mouth and get [npc2.herHim] to kiss and suck on them.");
					break;
				case VAGINA:
					sb.append("Eagerly push your nipple-cunts down against [npc2.namePos] mouth and get [npc2.herHim] to lick them.");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?" As you are [style.colourMinorGood(lactating)], you will get [npc2.herHim] to drink your [npc.milk] while performing this action."
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(push)] [npc.her] mouth-like lipple against [npc2.namePos] [npc2.lips+], before using [npc.her] control over it to passionately kiss [npc2.herHim].",
							"With [npc.a_moan+], [npc.name] [npc.verb(press)] [npc.her] lipple against [npc2.namePos] mouth, before passionately kissing [npc2.herHim] fully on the [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] [npc.verb(use)] [npc.her] control over [npc.her] lipple to passionately kiss [npc2.name] fully on the [npc2.lips]."));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(push)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] [npc.crotchNipple+] against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] [npc.crotchNipple+] down against [npc2.namePos] [npc2.lips]."));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] eagerly [npc.verb(push)] [npc.her] pussy-like nipple-cunt down against [npc2.namePos] [npc2.lips+].",
							"With [npc.a_moan+], [npc.name] eagerly [npc.verb(press)] [npc.her] nipple-cunt against [npc2.namePos] [npc2.lips+].",
							"Letting out [npc.a_moan+], [npc.name] eagerly [npc.verb(grind)] [npc.her] nipple-cunt down against [npc2.namePos] [npc2.lips]."));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop "+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to pull away from your [npc.crotchNipple+].";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Roughly yanking [npc2.namePos] head away from [npc.her] [npc.crotchBoobs+], [npc.name] [npc.verb(order)] [npc2.herHim] to stop kissing [npc.her] [npc.crotchNipple].",
							"Roughly grinding [npc.her] [npc.crotchNipple+] into [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(push)] [npc.herHim] away."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"Pulling [npc2.namePos] head away from [npc.her] [npc.crotchBoobs+], [npc.name] [npc.verb(tell)] [npc2.herHim] to stop kissing [npc.her] [npc.crotchNipple].",
							"Pressing [npc.her] [npc.crotchNipple+] into [npc2.namePos] [npc2.face] one last time, [npc.name] then [npc.verb(push)] [npc.herHim] away."));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" With tears streaming down [npc2.her] [npc2.face], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.nameIsFull]n't finished with [npc2.herHim] just yet.",
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] [npc2.verb(continue)] struggling against [npc.name], begging for [npc.herHim] to let [npc2.herHim] go."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to give [npc.namePos] [npc.nipples+] more of [npc2.her] oral attention.",
								" Still hungry for more, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(move)] away from [npc2.herHim]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
