package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FootType;
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
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class FootMouth {

	// Actions related to someone worshipping feet:
	
	public static final SexAction FOOT_ORAL_GIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Worship [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Press your [npc.lips] against [npc2.namePos] [npc2.feet] and start kissing and licking them.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			boolean foundFootType = false;
			if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.HOOFS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Eagerly(bringing)] [npc.her] head down to [npc2.namePos] hard hoofs, [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against them and [npc.verb(start)] passionately kissing and licking them.",
							"[npc.Name] [npc.verb(drop)] [npc.her] head down to [npc2.namePos] [npc2.feet], before eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] hard hoofs and starting to [npc.gently] kiss and lick them."));
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TALONS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Eagerly(bringing)] [npc.her] head down to [npc2.namePos] bird-like feet, [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.her] talons and [npc.verb(start)] passionately kissing and licking them.",
							"[npc.Name] [npc.verb(drop)] [npc.her] head down to [npc2.namePos] bird-like feet, before eagerly pressing [npc.her] [npc.lips+] against [npc2.namePos] talons and starting to [npc.gently] kiss and lick them."));
					
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getLegType().getFootType().equals(FootType.TENTACLE)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Eagerly(bringing)] [npc.her] head down to [npc2.namePos] tentacles, [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against them and [npc.verb(start)] passionately kissing and licking them.",
							"[npc.Name] [npc.verb(drop)] [npc.her] head down to [npc2.namePos] tentacles, before eagerly pressing [npc.her] [npc.lips+] against them and starting to [npc.gently] kiss and lick them."));
					
				}
			}
			if(!foundFootType) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.Eagerly(bringing)] [npc.her] head down to [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(press)] [npc.her] [npc.lips+] against them and [npc.verb(start)] passionately kissing and licking them.",
						"[npc.Name] [npc.verb(drop)] [npc.her] head down to [npc2.namePos] [npc2.feet+], before eagerly pressing [npc.her] [npc.lips+] against them and starting to [npc.gently] kiss and lick them."));
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.do] this, and with tears running down [npc2.her] [npc2.face], [npc2.she] desperately [npc2.verb(beg)] for [npc.herHim] to stop.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name], but, being unable to do so, all [npc2.she] can do is sob as [npc.name] [npc.verb(continue)] worshipping [npc2.her] [npc2.feet]."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(start)] worshipping [npc2.her] [npc2.feet],"
									+ " before [npc2.eagerly] pushing them into [npc.her] [npc.face] in order to encourage [npc.herHim] to keep on going.",
							" In response to this, [npc2.name] [npc2.verb(start)] [npc2.eagerly] pushing [npc2.her] [npc2.feet] into [npc.namePos] [npc.face],"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(focus)] on the delightful feeling of having [npc2.her] [npc2.feet(true)] worshipped."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
				case SUB_NORMAL:
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.feet+] up and down over [npc.namePos] [npc.face] in response,"
									+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(get)] [npc.herHim] to worship [npc2.her] [npc2.feet(true)].",
							" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.eagerly] [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down over [npc.namePos] [npc.face+].",
							" [npc2.Moaning] in delight, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] [npc2.feet+] against [npc.namePos] mouth as [npc2.she] [npc2.has] them worshipped."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Desperately trying, and failing, to pull away from [npc.name], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] and weakly [npc2.verb(beg)] for [npc.herHim] to stop worshipping [npc2.her] [npc2.feet(true)].",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.namePos] [npc.face] away from [npc2.her] [npc2.feet].",
							" [npc2.Sobbing] in distress, and with tears running down [npc2.her] [npc2.face], [npc2.name] weakly [npc2.verb(try)] to pull [npc2.her] [npc2.feet] away from [npc.namePos] [npc.face]."));
					break;
				case DOM_ROUGH:
					boolean foundFootType = false;
					if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
						if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.HOOFS)) {
							foundFootType = true;
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(start)] [npc2.roughly] grinding [npc2.her] [npc2.feet+] up and down over [npc.namePos] [npc.face] in response,"
											+ " not caring about the fact that using [npc2.her] hard hoofs in such a violent manner is extremely uncomfortable for [npc.herHim].",
									" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.roughly] [npc2.verb(rub)] [npc2.her] hard hoofs up and down over [npc.namePos] [npc.face+],"
											+ " not caring in the least about how uncomfortable it must be for [npc.herHim].",
									" [npc2.Moaning] in delight, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] hard hoofs against [npc.namePos] mouth,"
											+ " laughing derisively at [npc.herHim] as [npc2.she] [npc2.has] [npc2.her] [npc2.feet] worshipped."));
							
						} else if(Main.sex.getCharacterTargetedForSexAction(action).getLegType().getFootType().equals(FootType.TALONS)) {
							foundFootType = true;
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(start)] [npc2.roughly] grinding [npc2.her] [npc2.feet+] up and down over [npc.namePos] [npc.face] in response,"
											+ " not caring about the fact that [npc2.her] sharp talons keep on coming dangerously close to cutting [npc.herHim].",
									" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.roughly] [npc2.verb(rub)] [npc2.her] bird-like claws up and down over [npc.namePos] [npc.face+],"
											+ " not caring in the least about how close [npc2.her] sharp talons come to cutting [npc.herHim].",
									" [npc2.Moaning] in delight, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] clawed, bird-like feet against [npc.namePos] mouth,"
											+ " laughing derisively at [npc.herHim] as [npc2.she] [npc2.verb(draw)] dangerously close to cutting [npc.herHim] with [npc2.her] sharp talons."));
							
						}
					}
					if(!foundFootType) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(start)] [npc2.eagerly] sliding [npc2.her] [npc2.feet+] up and down over [npc.namePos] [npc.face] in response,"
										+ " letting out [npc2.a_moan+] as [npc2.she] [npc2.verb(get)] [npc.herHim] to worship [npc2.her] [npc2.feet(true)].",
								" [npc2.A_moan+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] [npc2.eagerly] [npc2.verb(slide)] [npc2.her] [npc2.feet+] up and down over [npc.namePos] [npc.face+].",
								" [npc2.Moaning] in delight, [npc2.name] [npc2.eagerly] [npc2.verb(push)] [npc2.her] [npc2.feet+] against [npc.namePos] mouth as [npc2.she] [npc2.has] them worshipped."));
					}
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gently worship [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Gently kiss and lick [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"After planting a series of gentle kisses on [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(proceed)] to start lovingly licking them, letting out a series of soft [npc.moans] in the process.",
					"Gently kissing and licking [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] out a little [npc.moan] as [npc.she] lovingly [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] out a little [npc.moan] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.feet], before starting to lovingly kiss and lick them."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Worship [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Continue licking and kissing [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"After planting a series of passionate kisses on [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(proceed)] to start enthusiastically licking them, letting out a series of [npc.moans+] in the process.",
					"Passionately kissing and licking [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] happily [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.feet], before starting to eagerly kiss and lick them."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Roughly worship [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Roughly kiss and lick [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"After planting a series of forceful kisses on [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(proceed)] to start roughly licking them, letting out a series of [npc.moans+] in the process.",
					"Roughly kissing and licking [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] dominantly [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.feet], before starting to forcefully kiss and lick them."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "Worship [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Continue kissing and licking [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"After planting a series of kisses on [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(proceed)] to start licking them, letting out a series of [npc.moans+] in the process.",
					"Kissing and licking [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.feet], before starting to kiss and lick them."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eagerly worship [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly kiss and lick [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"After planting a series of passionate kisses on [npc2.namePos] [npc2.feet], [npc.name] [npc.verb(proceed)] to start enthusiastically licking them, letting out a series of [npc.moans+] in the process.",
					"Passionately kissing and licking [npc2.namePos] [npc2.toes+], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] happily [npc.verb(worship)] [npc2.namePos] [npc2.feet(true)].",
					"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(press)] [npc.her] [npc.lips+] against [npc2.namePos] [npc2.feet], before starting to eagerly kiss and lick them."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist worshipping [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Try to pull your [npc.face] away from [npc2.namePos] [npc2.feet+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] desperately [npc.verb(try)], and [npc.verb(fail)], to pull [npc.her] [npc.face+] away from [npc2.namePos] [npc2.feet+], letting out [npc.a_sob+] in the process.",
					"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.namePos] [npc2.feet+] away from [npc.her] [npc.face].",
					"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to take [npc2.her] [npc2.feet] away from [npc.her] [npc.face]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Totally ignoring [npc.namePos] protests, [npc2.name] gently, yet firmly, [npc2.verb(continue)] to push [npc2.her] [npc2.feet] against [npc.her] mouth, forcing [npc.herHim] to worship [npc2.her] [npc2.feet+].",
							" Softly [npc2.moaning] in delight, [npc2.name] completely [npc2.verb(ignore)] [npc.namePos] protests and [npc2.verb(continue)] to push [npc2.her] [npc2.feet+] into [npc.her] [npc.face].",
							" Not paying any attention to [npc.namePos] struggles, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.feet+] against [npc.namePos] mouth."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Totally ignoring [npc.namePos] protests, [npc2.name] dominantly [npc2.verb(push)] [npc2.her] [npc2.feet] against [npc.her] mouth, roughly forcing [npc.herHim] to worship [npc2.her] [npc2.feet+].",
							" [npc2.Moaning+] in delight, [npc2.name] completely [npc2.verb(ignore)] [npc.namePos] protests and [npc2.verb(continue)] to roughly grind [npc2.her] [npc2.feet+] into [npc.her] [npc.face].",
							" Not paying any attention to [npc.namePos] struggles, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] forcefully [npc2.verb(push)] [npc2.her] [npc2.feet+] against [npc.namePos] mouth."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Totally ignoring [npc.namePos] protests, [npc2.name] firmly [npc2.verb(continue)] to push [npc2.her] [npc2.feet] against [npc.her] mouth, forcing [npc.herHim] to worship [npc2.her] [npc2.feet+].",
							" [npc2.Moaning+] in delight, [npc2.name] completely [npc2.verb(ignore)] [npc.namePos] protests and [npc2.verb(continue)] to firmly push [npc2.her] [npc2.feet+] into [npc.her] [npc.face].",
							" Not paying any attention to [npc.namePos] struggles, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(push)] [npc2.her] [npc2.feet+] against [npc.namePos] mouth."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_GIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop worshipping [npc2.feet(true)]";
		}
		@Override
		public String getActionDescription() {
			return "Take your [npc.face] away from [npc2.namePos] [npc2.feet] and stop worshipping them.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"After planting one last, rough kiss on [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(lift)] [npc.her] head and [npc.verb(bring)] an end to [npc.her] worshipping.",
							"Forcefully licking [npc2.namePos] [npc2.toes+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to [npc.her] worship of [npc2.namePos] [npc2.feet+]."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"After planting one last kiss on [npc2.namePos] [npc2.feet+], [npc.name] [npc.verb(lift)] [npc.her] head and [npc.verb(bring)] an end to [npc.her] worshipping.",
							"Greedily licking [npc2.namePos] [npc2.toes+] one last time, [npc.name] then [npc2.verb(pull)] back, putting an end to [npc.her] worship of [npc2.namePos] [npc2.feet+]."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] can't help but let out a relieved sob as [npc.name] moves away, before hopefully asking if [npc2.her] ordeal is now over.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle against [npc.name], tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(beg)] to be left alone."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] back, eager for more of [npc.her] attention.",
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] lust for [npc.namePos] [npc.tongue+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	// Actions related to someone receiving foot worship:
	
	public static final SexAction FOOT_ORAL_RECEIVING_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Have [npc.feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Get [npc2.name] to drop [npc2.her] head down to your [npc.feet] and start orally worshipping them.";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			boolean foundFootType = false;
			if(Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.FEET, false)) {
				if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.HOOFS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] head down to [npc.her] hard hoofs, before getting [npc2.herHim] to kiss them and start orally worshipping [npc.her] [npc.feet+].",
							"Pushing [npc2.namePos] head down to [npc.her] hard hoofs, [npc.name] can't help but [npc.moan+] as [npc.she] [npc.verb(make)] [npc2.herHim] kiss and orally worship [npc.her] [npc.feet+]."));
					
				} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TALONS)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] head down to [npc.her] bird-like feet, before getting [npc2.herHim] to kiss [npc.her] talons and start orally worshipping [npc.her] [npc.feet+].",
							"Pushing [npc2.namePos] head down to [npc.her] bird-like feet, [npc.name] can't help but [npc.moan+] as [npc.she] [npc.verb(make)] [npc2.herHim] kiss [npc.her] talons and orally worship [npc.her] [npc.feet+]."));
					
				} else if(Main.sex.getCharacterPerformingAction().getLegType().getFootType().equals(FootType.TENTACLE)) {
					foundFootType = true;
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] head down to [npc.her] tentacles, before getting [npc2.herHim] to kiss and start orally worshipping them.",
							"Pushing [npc2.namePos] head down to [npc.her] tentacles, [npc.name] can't help but [npc.moan+] as [npc.she] [npc.verb(make)] [npc2.herHim] kiss and orally worship them."));
				}
			}
			if(!foundFootType) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc2.namePos] head down to [npc.her] [npc.feet], before getting [npc2.herHim] to kiss them and start orally worshipping them.",
						"Pushing [npc2.namePos] head down to [npc.her] [npc.feet], [npc.name] can't help but [npc.moan+] as [npc.she] [npc.verb(make)] [npc2.herHim] kiss and orally worship them."));
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_sob+] as [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet] against [npc2.her] mouth, and desperately [npc2.verb(try)] to pull away.",
								" With [npc2.a_sob+], [npc2.name] [npc2.verb(struggle)] against [npc.name] as [npc.she] [npc.eagerly] [npc.verb(press)] [npc.her] [npc.feet+] against [npc2.her] mouth."));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.eagerly] [npc2.verb(plant)] a series of kisses and licks on [npc.namePos] [npc.feet].",
								" With [npc2.a_moan+], [npc2.name] [npc2.eagerly] [npc2.verb(start)] kissing and licking [npc.namePos] [npc.feet+]."));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
				case SUB_NORMAL:
				case DOM_GENTLE:
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc.eagerly] [npc2.verb(lick)] and [npc2.verb(suck)] on [npc.namePos] [npc.toes+], letting out [npc2.a_moan+] as [npc2.she] [npc2.eagerly] [npc2.verb(worship)] [npc.her] [npc.feet+].",
							" [npc2.A_moan+] bursts out from [npc2.namePos] mouth as [npc2.she] [npc2.verb(continue)] [npc.eagerly] licking and kissing [npc.namePos] [npc.feet+].",
							" [npc2.Moaning] in delight, [npc2.name] [npc.eagerly] [npc2.verb(worship)] [npc.namePos] [npc.feet+] by planting a series of passionate kisses on them."));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Failing to pull [npc2.her] [npc2.face] away from [npc.namePos] [npc.feet], [npc2.name] [npc2.verb(let)] out [npc2.a_sob+] as [npc2.she] weakly [npc2.verb(try)] to struggle free.",
							" [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips] as [npc2.she] weakly [npc2.verb(try)] to push [npc.namePos] [npc.feet] away from [npc2.her] face.",
							" [npc2.Sobbing] in distress, [npc2.name] [npc2.verb(try)], in vain, to pull [npc2.her] [npc2.face] away from [npc.namePos] [npc.feet+]."));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "Gentle [npc.feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Gently continue to push your [npc.feet+] into [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Gently pushing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.has] them orally worshipped.",
					"With a soft [npc.moan], [npc.name] gently [npc.verb(push)] [npc.her] [npc.feet+] against [npc2.namePos] [npc2.lips+], giving [npc2.herHim] no option but to continue worshipping them.",
					"Slowly pressing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] can't help but let out a soft [npc.moan] as [npc.she] [npc.has] them worshipped."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "[npc.Feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Continue to push your [npc.feet+] into [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly] pushing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.has] them orally worshipped.",
					"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet+] against [npc2.namePos] [npc2.lips+], giving [npc2.herHim] no option but to continue worshipping them.",
					"[npc.Eagerly] pressing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] can't help but let out [npc.a_moan+] as [npc.she] [npc.has] them worshipped."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "Rough [npc.feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Roughly continue to push your [npc.feet+] into [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly] pushing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.has] them orally worshipped.",
					"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet+] against [npc2.namePos] [npc2.lips+], giving [npc2.herHim] no option but to continue worshipping them.",
					"[npc.Eagerly] pressing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] can't help but let out [npc.a_moan+] as [npc.she] [npc.has] them worshipped."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		@Override
		public String getActionTitle() {
			return "[npc.Feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Continue to push your [npc.feet+] into [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"Pushing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.has] them orally worshipped.",
					"With [npc.a_moan+], [npc.name] [npc.verb(push)] [npc.her] [npc.feet+] against [npc2.namePos] [npc2.lips+], giving [npc2.herHim] no option but to continue worshipping them.",
					"Pressing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] can't help but let out [npc.a_moan+] as [npc.she] [npc.has] them worshipped."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		@Override
		public String getActionTitle() {
			return "Eager [npc.feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Eagerly continue to push your [npc.feet+] into [npc2.namePos] [npc2.face].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Eagerly] pushing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.has] them orally worshipped.",
					"With [npc.a_moan+], [npc.name] [npc.eagerly] [npc.verb(push)] [npc.her] [npc.feet+] against [npc2.namePos] [npc2.lips+], giving [npc2.herHim] no option but to continue worshipping them.",
					"[npc.Eagerly] pressing [npc.her] [npc.feet+] into [npc2.namePos] [npc2.face], [npc.name] can't help but let out [npc.a_moan+] as [npc.she] [npc.has] them worshipped."));
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public String getActionTitle() {
			return "Resist [npc.feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Try and pull your [npc.feet+] away from [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.Name] desperately [npc.verb(try)], and [npc.verb(fail)], to pull [npc.her] [npc.feet] away from [npc2.namePos] [npc2.face], letting out [npc.a_sob+] in the process.",
					"[npc.A_sob+] bursts out from between [npc.namePos] [npc.lips] as [npc.she] weakly tries to push [npc2.namePos] [npc2.face+] away from [npc.her] [npc.feet].",
					"[npc.Sobbing] in distress, [npc.name] weakly [npc.verb(struggle)] against [npc2.name] as [npc.she] [npc.verb(plead)] for [npc2.herHim] to take [npc2.her] mouth away from [npc.her] [npc.feet]."));
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Totally ignoring [npc.namePos] protests, [npc2.name] gently, yet firmly, [npc2.verb(continue)] to worship [npc.her] [npc.feet+] by planting a series of licks and kisses on them.",
							" Softly [npc2.moaning] in delight, [npc2.name] completely [npc2.verb(ignore)] [npc.namePos] protests and [npc2.verb(continue)] to orally worship [npc.her] [npc.feet+].",
							" Not paying any attention to [npc.namePos] struggles, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.feet+]."));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Totally ignoring [npc.namePos] protests, [npc2.name] dominantly [npc2.verb(continue)] to worship [npc.her] [npc.feet+] by planting a series of rough licks and kisses on them.",
							" [npc2.Moaning+] in delight, [npc2.name] completely [npc2.verb(ignore)] [npc.namePos] protests and [npc2.verb(continue)] to forcefully worship [npc.her] [npc.feet+].",
							" Not paying any attention to [npc.namePos] struggles, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] forcefully [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.feet+]."));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" Totally ignoring [npc.namePos] protests, [npc2.name] [npc.eagerly] [npc2.verb(continue)] to worship [npc.her] [npc.feet+] by planting a series of licks and kisses on them.",
							" [npc2.Moaning+] in delight, [npc2.name] completely [npc2.verb(ignore)] [npc.namePos] protests and [npc2.verb(continue)] to orally worship [npc.her] [npc.feet+].",
							" Not paying any attention to [npc.namePos] struggles, [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(press)] [npc2.her] [npc2.lips+] against [npc.namePos] [npc.feet+]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction FOOT_ORAL_RECEIVING_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FOOT, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "Stop [npc.feet(true)] worshipped";
		}
		@Override
		public String getActionDescription() {
			return "Pull your [npc.feet+] away from [npc2.namePos] [npc2.face+].";
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"After roughly pushing [npc.her] [npc.feet+] into [npc2.namePos] face one last time, [npc.name] [npc.verb(pull)] them away and [npc.verb(put)] an end to [npc2.her] oral worshipping.",
						"[npc.Name] [npc.verb(grind)] [npc.her] [npc.feet+] into [npc2.namePos] face one last time, before letting out an amused [npc.moan] and suddenly pulling them away from [npc2.her] mouth."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"After [npc.eagerly] pushing [npc.her] [npc.feet+] into [npc2.namePos] face one last time, [npc.name] [npc.verb(pull)] them away and [npc.verb(put)] an end to [npc2.her] oral worshipping.",
						"[npc.Name] [npc.verb(push)] [npc.her] [npc.feet+] into [npc2.namePos] face one last time, before letting out [npc.a_moan+] and firmly pulling them away from [npc2.her] mouth."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.Name] [npc2.verb(let)] out a relieved sigh, which soon turns into [npc2.a_sob+] as [npc2.she] [npc2.verb(realise)] that [npc.name] [npc.has]n't finished with [npc2.herHim] just yet.",
						" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to protest and struggle against [npc.name] as [npc.she] [npc.verb(hold)] [npc2.herHim] firmly in place."));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desire to continue licking and kissing [npc.her] [npc.feet]."));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
