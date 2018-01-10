package com.lilithsthrone.game.sex.sexActions.universal.sub;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.69
 * @version 0.1.79
 * @author Innoxia
 */
public class SubDoggy {
	
	public static final SexAction PLAYER_LOOK_BACK = new SexAction(
			SexActionType.PLAYER,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			null,
			SexPace.SUB_EAGER,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Seductive look";
		}
		@Override
		public String getActionDescription() {
			return "Turn your head back and give [npc.name] a seductive look.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Turning your head back, you look up at [npc.name] and bite your [pc.lip], putting on your most seductive look as [npc.she] grins down at you.",
					"Looking back at [npc.name] as [npc.she] towers over your [pc.ass+], you put on a seductive look, smiling happily as you see [npc.herHim] gazing hungrily down at your body.",
					"You turn your head and bite your [pc.lip] at [npc.name], trying to look as seductive as possible as [npc.she] grins down you.",
					"Looking back, you put on a seductive look for [npc.name], feeling extremely pleased with yourself as you see [npc.herHim] gazing hungrily down at you in return.");
		}
	};

	// Partner's methods:
	public static final SexAction PARTNER_SLAP_ASS = new SexAction(
			SexActionType.PARTNER,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			null,
			null,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer());
		}
		
		@Override
		public String getActionTitle() {
			return "Slap ass";
		}

		@Override
		public String getActionDescription() {
			return "Roughly start spanking [pc.name].";
		}

		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)==PenetrationType.PENIS_PARTNER) {
				switch(Main.game.getPlayer().getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside your [pc.pussy+], [npc.name] reaches down and roughly gropes your [pc.ass+], before starting to deliver a series of stinging slaps to your exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside your [pc.pussy+], [npc.name] roughly grabs your [pc.tail+] and yanks it upwards,"
											+ " raising your[pc.ass+] up high in the air before starting to deliver a series of stinging slaps to your exposed cheeks.";
						break;
				}
				switch(Main.game.getPlayer().getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at your [pc.pussy+], [npc.name] growls down that [npc.she] going to put you in your place before starting to deliver an aggressive series of powerful slaps to your [pc.ass+].";
						break;
					default:
						tailSpecial2 = "Still ploughing away at your [pc.pussy+], [npc.name] grabs your [pc.tail+] in one [npc.hand], roughly yanking your [pc.ass+] up high in the air before starting to aggressively slap your exposed cheeks.";
						break;
				}
			
				return UtilText.genderParsing(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"You let out [pc.a_moan+] as [npc.name] starts slapping your [pc.ass+] in time with [npc.her] powerful thrusts into your [pc.pussy+].",
							"Hilting [npc.her] [npc.cock+] deep inside your [pc.pussy+], [npc.name] uses one [npc.hand] to hold you still, while the other starts to deliver a series of stinging slaps to your [pc.ass+].",
							"While [npc.name] continues to fuck your [pc.pussy+], [npc.she] starts to roughly slap your [pc.ass+], growling in glee as you squirm and squeal under [npc.her] stinging blows."));
				
			} else if (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)==PenetrationType.PENIS_PARTNER) {
				switch(Main.game.getPlayer().getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside your [pc.asshole+], [npc.name] reaches down and roughly gropes your [pc.ass+], before starting to deliver a series of stinging slaps to your exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting [npc.her] [npc.cock+] deep inside your [pc.asshole+], [npc.name] roughly grabs your [pc.tail+] and yanks it upwards,"
											+ " raising your[pc.ass+] up high in the air before starting to deliver a series of stinging slaps to your exposed cheeks.";
						break;
				}
				switch(Main.game.getPlayer().getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at your [pc.asshole+], [npc.name] growls down that [npc.she] going to put you in your place before starting to deliver an aggressive series of powerful slaps to your [pc.ass+].";
						break;
					default:
						tailSpecial2 = "Still ploughing away at your [pc.asshole+], [npc.name] grabs your [pc.tail+] in one [npc.hand], roughly yanking your [pc.ass+] up high in the air before starting to aggressively slap your exposed cheeks.";
						break;
				}
			
				return UtilText.genderParsing(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"You let out [pc.a_moan+] as [npc.name] starts slapping your [pc.ass+] in time with [npc.her] powerful thrusts into your [pc.asshole+].",
							"Hilting [npc.her] [npc.cock+] deep inside your [pc.asshole+], [npc.name] uses one [npc.hand] to hold you still, while the other starts to deliver a series of stinging slaps to your [pc.ass+].",
							"While [npc.name] continues to fuck your [pc.asshole+], [npc.she] starts to roughly slap your [pc.ass+], growling in glee as you squirm and squeal under [npc.her] stinging blows."));
			
			} else {
				switch(Main.game.getPlayer().getTailType()) {
					case NONE:
						tailSpecial1 = "With a menacing growl, [npc.name] suddenly reaches down and grabs your waist,"
								+ " using one [npc.hand] to hold you still while the other starts to deliver a series of stinging slaps to your [pc.ass+].";
						break;
					default:
						tailSpecial1 = "With a menacing growl, [npc.name] roughly grabs your [pc.tail+] and yanks upwards, raising your [pc.ass+] up high in the air before starting to deliver a series of stinging slaps to your exposed cheeks.";
						break;
				}
				switch(Main.game.getPlayer().getTailType()) {
					case NONE:
						tailSpecial2 = "You feel [npc.name] suddenly grab your waist with one [npc.hand], holding you firmly in [npc.her] grip as [npc.she] starts to aggressively slap your exposed cheeks.";
						break;
					default:
						tailSpecial2 = "You feel [npc.name] suddenly grab your [pc.tail+], and you let out a surprised yelp as [npc.she] roughly yanks upwards,"
								+ " forcing you to push your [pc.ass+] up high in the air before [npc.she] starts to aggressively slap your exposed cheeks.";
						break;
				}
			
				return UtilText.genderParsing(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"You let out [pc.a_moan+] as [npc.name] starts roughly slapping your [pc.ass+], and you hear [npc.herHim] growling in glee as [npc.she] watches you squirm beneath [npc.her] stinging blows.",
							"You let out a startled cry as [npc.name] starts to roughly slap your [pc.ass+], growling in glee as you squirm and squeal under [npc.her] relentless blows.",
							"[npc.Name] growls down that [npc.she]'s going to put you in your place, before starting to aggressively slap your [pc.ass+], causing you to squeal and cry out as you squirm beneath [npc.her] stinging blows."));
			}
		}
	};
}
