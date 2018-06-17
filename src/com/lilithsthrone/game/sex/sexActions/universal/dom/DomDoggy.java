package com.lilithsthrone.game.sex.sexActions.universal.dom;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.69
 * @version 0.2.5
 * @author Innoxia
 */
public class DomDoggy {
	
	public static final SexAction PARTNER_LOOK_BACK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.MISC,
			null,
			SexPace.SUB_EAGER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			SexPositionSlot targetedSlot = Sex.getSexPositionSlot(Sex.getTargetedPartner(Sex.getActivePartner()));
			return Sex.isDom(Main.game.getPlayer())
					&& (targetedSlot==SexPositionSlot.DOGGY_BEHIND
					|| targetedSlot==SexPositionSlot.DOGGY_BEHIND_ORAL);
		}
		
		@Override
		public String getActionTitle() {
			return "Seductive look";
		}
		@Override
		public String getActionDescription() {
			return "Turn your head back and give [pc.name] a seductive look.";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"Turning [npc.her] head back, [npc.name] looks up at you and bites [npc.her] [npc.lip], putting on [npc.her] most seductive look as [npc.she] pushes [npc.her] [npc.ass+] back against you.",
					"Looking back at you as you tower over [npc.her] [npc.ass+], [npc.name] puts on a seductive look, smiling happily as [npc.she] sees you grinning hungrily down at [npc.herHim].",
					"[npc.Name] turns [npc.her] head and bites [npc.her] lip at you, trying to look as seductive as possible as [npc.she] pushes [npc.her] [npc.ass+] back into your groin.",
					"Looking back, [npc.name] puts on a seductive look, letting out a pleading whine as [npc.she] gazes up with big, innocent eyes.");
		}
	};
	
	// Player's methods:
	
	public static final SexAction PLAYER_SLAP_ASS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.MISC,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& (Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_BEHIND);
		}
		
		@Override
		public String getActionTitle() {
			return "Slap ass";
		}

		@Override
		public String getActionDescription() {
			return "Start slapping [npc.name]'s ass.";
		}

		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				switch(Sex.getActivePartner().getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting your [pc.cock+] deep inside [npc.name]'s [npc.pussy+], you reach down and roughly grope [npc.her] [npc.ass+], before starting to deliver a series of stinging slaps to [npc.her] exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting your [pc.cock+] deep inside [npc.name]'s [npc.pussy+], you roughly grab the base of [npc.her] [npc.tail+] and yank upwards,"
											+ " raising [npc.her] [npc.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc.her] exposed cheeks.";
						break;
				}
				switch(Sex.getActivePartner().getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at [npc.her] [npc.pussy+], you growl down that you're going to put [npc.name] in [npc.her] place before starting to aggressively slap [npc.her] exposed ass cheeks.";
						break;
					default:
						tailSpecial2 = "Still ploughing away at [npc.her] [npc.pussy+], you grab the base of [npc.name]'s [npc.tail+] in one [pc.hand],"
											+ " roughly yanking [npc.her] [npc.ass+] up high in the air before starting to aggressively slap [npc.her] exposed cheeks.";
						break;
				}
			
				return UtilText.parse(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name] lets out [npc.a_moan+] as you start slapping [npc.her] [npc.ass+] in time with your powerful thrusts into [npc.her] [npc.pussy+].",
							"Hilting your [pc.cock+] deep inside [npc.name]'s [npc.pussy], you use one [pc.hand] to hold [npc.herHim] still, while using your other to deliver a series of stinging slaps to [npc.her] exposed ass cheeks.",
							"While you continue pounding away at [npc.name]'s [npc.pussy+], you reach down and start to roughly slap [npc.her] [npc.ass+], growling in glee as [npc.she] squirms and squeals under your stinging blows."));
				
			} else if (Sex.getAllContactingSexAreas(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				switch(Sex.getActivePartner().getTailType()) {
					case NONE:
						tailSpecial1 = "Hilting your [pc.cock+] deep inside [npc.name]'s [npc.asshole+], you reach down and roughly grope [npc.her] [npc.ass+], before starting to deliver a series of stinging slaps to [npc.her] exposed cheeks.";
						break;
					default:
						tailSpecial1 = "Hilting your [pc.cock+] deep inside [npc.name]'s [npc.asshole+], you roughly grab the base of [npc.her] [npc.tail+] and yank upwards,"
											+ " raising [npc.her] [npc.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc.her] exposed cheeks.";
						break;
				}
				switch(Sex.getActivePartner().getTailType()) {
					case NONE:
						tailSpecial2 = "Still ploughing away at [npc.her] [npc.asshole+], you growl down that you're going to put [npc.name] in [npc.her] place before starting to aggressively slap [npc.her] exposed ass cheeks.";
						break;
					default:
						tailSpecial2 = "Still ploughing away at [npc.her] [npc.asshole+], you grab the base of [npc.name]'s [npc.tail+] in one [pc.hand],"
											+ " roughly yanking [npc.her] [npc.ass+] up high in the air before starting to aggressively slap [npc.her] exposed cheeks.";
						break;
				}
			
				return UtilText.parse(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name] lets out [npc.a_moan+] as you start slapping [npc.her] [npc.ass+] in time with your powerful thrusts into [npc.her] [npc.asshole+].",
							"Hilting your [pc.cock+] deep inside [npc.name]'s [npc.asshole], you use one [pc.hand] to hold [npc.herHim] still, while using your other to deliver a series of stinging slaps to [npc.her] exposed ass cheeks.",
							"While you continue pounding away at [npc.name]'s [npc.asshole+], you reach down and start to roughly slap [npc.her] [npc.ass+], growling in glee as [npc.she] squirms and squeals under your stinging blows."));
				
			} else {
				switch(Sex.getActivePartner().getTailType()) {
					case NONE:
						tailSpecial1 = "Growling down into [npc.name]'s [npc.ear+], you reach down and grab [npc.her] waist, using one hand to hold [npc.herHim] still,"
											+ " while using your other to deliver a series of stinging slaps to [npc.her] [npc.ass+].";
						break;
					default:
						tailSpecial1 = "Growling down into [npc.name]'s ear, you roughly grab the base of [npc.her] [npc.tail+] and yank upwards,"
									+ " raising [npc.her] [npc.ass+] up high in the air before starting to deliver a series of stinging slaps to [npc.her] exposed cheeks.";
						break;
				}
				switch(Sex.getActivePartner().getTailType()) {
					case NONE:
						tailSpecial2 = "You reach down and grab [npc.name]'s waist with one hand, holding [npc.herHim] firmly in your grip as you start to aggressively slap [npc.her] exposed cheeks.";
						break;
					default:
						tailSpecial2 = "You reach down and grab the base of [npc.name]'s [npc.tail+], causing [npc.herHim] to let out a surprised yelp as you roughly yank upwards,"
											+ " forcing [npc.herHim] to push [npc.her] [npc.ass+] up high in the air as you start to aggressively slap [npc.her] exposed cheeks.";
						break;
				}
			
				return UtilText.parse(Sex.getActivePartner(),
					UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name] lets out [npc.a_moan+] as you start roughly slapping [npc.her] [npc.ass+], and you find yourself grinning in glee as you watch [npc.herHim] squirm and cry out beneath your stinging blows.",
							"[npc.Name] lets out a startled wail as you start to roughly slap [npc.her] [npc.ass+], and you quickly find yourself grinning in glee as you watch [npc.herHim] squirm and wail beneath your relentless blows.",
							"You growl down that you're going to put [npc.name] in [npc.her] place, before starting to aggressively slap [npc.her] [npc.ass+],"
									+ " smirking down at [npc.her] submissive form as [npc.she] squeals and cries out beneath your relentless blows."));
			}
		}
		
	};
	
	
	// Player's orgasms:
	
	public static final SexAction PLAYER_DOGGY_OVER_BACK_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, null)),
			SexParticipantType.PITCHER) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "Cum on back";
		}

		@Override
		public String getActionDescription() {
			return "Cum over [npc.name]'s ass and back.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Sex.isPenetrationTypeFree(Main.game.getPlayer(), SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you decide that you're going to shoot your sticky seed all over [npc.name]'s ass."
						+ " Grabbing [npc.her] hips, you slide backwards, slipping your cock free from [npc.her] well-used hole, and brace for your orgasm. ");
				
			} else {
				UtilText.nodeContentSB.append("As you feel yourself reaching your climax, you decide that you're going to shoot your sticky seed all over [npc.name]'s ass."
						+ " Grabbing [npc.her] hips, you shuffle forwards, lining your [pc.cock+] up to [npc.her] [npc.ass+], and brace for your orgasm. ");
			}
			
			String flaredSpecial = "", knottedSpecial = "", barbedSpecial = "";
			
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.KNOTTED)) {
				knottedSpecial = " Your [pc.cock+] suddenly starts violently throbbing, and you know that you're about to cum."
										+ " Grabbing your [pc.cock] in one [pc.hand], you point it at [npc.name]'s ass, furiously masturbating as your thick knot swells up.";
			}
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.BARBED)) {
				barbedSpecial = " Your barbed [pc.cock] suddenly starts violently throbbing, and you know that you're about to cum."
						+ " Grabbing your [pc.cock] in one hand, you point it at [npc.name]'s ass, furiously masturbating as your hand slides up and down over your sensitive little barbs.";
			}
			if(Main.game.getPlayer().hasPenisModifier(PenisModifier.FLARED)) {
				flaredSpecial = " Your flared [pc.cock] suddenly starts violently throbbing, and you know that you're about to cum."
						+ " Grabbing your [pc.cock] in one [pc.hand], you point it at [npc.name]'s ass, furiously masturbating as your flared head swells up.";
			}
			
			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					flaredSpecial,
					knottedSpecial,
					barbedSpecial,
					"Your [pc.cock+] suddenly starts violently throbbing, and you know that you're about to cum."
							+ " Grabbing your [pc.cock] in one hand, you point it at [npc.name]'s ass, furiously masturbating as you let out [pc.a_moan+]."));
			
			if(Main.game.getPlayer().isWearingCondom()) {
				UtilText.nodeContentSB.append(" As your [pc.balls+] tense up");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(", you feel a small trickle of [pc.cum+] squirting");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(", you feel a small amount of [pc.cum+] squirting");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] squirting");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] shooting");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(", you feel your [pc.cum+] pouring");
						break;
					default:
						break;
				}
				UtilText.nodeContentSB.append(" out into the condom that you're wearing.");
				
			} else {
				UtilText.nodeContentSB.append(" You feel your [pc.balls+] tense up as your orgasm washes over you.");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append(" Unfortunately, you aren't able to produce even one drop of cum, somewhat diminishing the pleasure of your climax.");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append(" A small trickle of [pc.cum+] squirts onto [npc.name]'s ass, and you groan in satisfaction as you see your [pc.cum] sliding down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append(" A small amount of [pc.cum+] squirts onto [npc.name]'s ass, and you groan in satisfaction as you see your sticky seed sliding down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append(" Your [pc.cum+] shoots out as your cock throbs, and you groan in satisfaction as you see your sticky seed splatter over [npc.name]'s ass and lower back, before dripping down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append(" Your [pc.cum+] shoots out as your cock throbs, and you groan in satisfaction as you see your sticky seed splatter over [npc.name]'s ass and lower back, before dripping down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append(" Your large load pours out as your cock throbs, and you groan in satisfaction as you see your [pc.cum+] splatter all over [npc.name]'s ass and lower back, before sliding down over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append(" Your huge load pours out as your cock throbs, and you groan in satisfaction as you see your [pc.cum+] completely cover [npc.name]'s ass and back, before sliding down all over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+".");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append(" Your enormous load pours out as your cock throbs, and you groan in satisfaction as you see your [pc.cum+] completely drenching [npc.name]'s ass and back,"
								+ " before pouring down all over [npc.her] "
								+(Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.ANUS)?"exposed asshole":Sex.getActivePartner().getHighestZLayerCoverableArea(CoverableArea.ANUS).getName())+" to form a puddle around [npc.her] knees.");
						break;
					default:
						break;
				}
			}

			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/>"
						+ "As you grasp your softening member in your [pc.hand], you feel a second orgasm building deep in your groin."
						+ " Grabbing [npc.name]'s [npc.hips] to brace yourself, you go weak at the knees and clench your thighs together as your [pc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you moan and squeal in delight as your feminine sex joins in on the fun."
						+ " Panting heavily, you soon come down from your second climax and start to recover.");
			} else {
				UtilText.nodeContentSB.append("<br/>"
						+ "You pant heavily as you pull your softening member out of [npc.name]'s used hole, before giving [npc.her] [npc.ass] a slap.");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.ANUS);
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.VAGINA);
		}
		
	};
	
	public static final SexAction PLAYER_DOGGY_DOMINANT_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.PITCHER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Sex.isDom(Main.game.getPlayer())
					&& Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "Ass to mouth";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name] into the floor before filling [npc.her] [npc.ass] with your cum. Then use [npc.her] mouth to clean yourself off.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("As you feel [npc.name]'s [npc.asshole+] squeezing down around your [pc.cock+], you decide that you're going to show [npc.herHim] how an alpha treats their submissive little beta."
					+ " Letting out [pc.a_moan+], you slam your [pc.cock] deep into [npc.her] ass, grinning devilishly as [npc.she] lets out [npc.a_moan+]."
					+ "<br/><br/>"
					+ "Reaching down, you grab the [npc.race]'s shoulders, pushing your weight down onto [npc.her] back as you roughly mount [npc.herHim], which causes [npc.her] torso to collapse down to the floor."
					+ " Bending down, with your [pc.cock] still hilted in [npc.her] [npc.asshole], you growl menacingly in [npc.her] [npc.ear], "
							+ "[pc.speech(You little bitch! All you're good for is taking my load!)]"
					+"<br/><br/>"
					+ "[npc.Name] lets out another [npc.moan], which is enough to send you over the edge."
					+ " As you grind [npc.her] [npc.face+] into the floor, you reach your climax, and as your [pc.balls+] tense up");

			switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you realise that you aren't able to produce even one drop of cum, somewhat lessening the impact of your dominant display.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [pc.cum+] squirts deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [pc.cum+] squirts deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", your load pours out deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", your huge load pours out deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", your enormous load pours out deep into [npc.name]'s ass, and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				default:
					break;
			}

			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.name]'s well-used ass, you feel a second orgasm building deep in your groin."
						+ " Grabbing [npc.her] hips to brace yourself, you go weak at the knees and clench your thighs together as your [pc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you [pc.moanVerb] in delight as your feminine sex joins in on the fun."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.name]'s face is still collapsed down against the floor.");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.name]'s well-used ass, you look down, grinning, at the mess you've made of [npc.herHim]."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.name]'s face is still collapsed down against the floor.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Reaching down, you grab a fistful of [npc.name]'s [npc.hair+], and before [npc.she] has a chance to react, you shove [npc.her] [npc.face+] down onto your [pc.cock+]."
					+ " [npc.She] [npc.moansVerb] and squirms as you give [npc.herHim] a taste of [npc.her] ass, but you hold [npc.herHim] tightly in position, [pc.moaning] softly as [npc.her] frantic [npc.tongue] cleans you off."
					+ "<br/>"
					+ "After a minute of using the unfortunate [npc.race] in this manner, you finally release [npc.herHim], and, with a deep gasp, [npc.she] collapses to the floor, completely exhausted from your dominant treatment.");

			return UtilText.nodeContentSB.toString();
		}
		

		@Override
		public List<SexAreaOrifice> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.game.getPlayer()) && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
			} else {
				return null;
			}
		}

		@Override
		public void applyEffects() {
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.ANUS);
		}
		
	};
	
	public static final SexAction PLAYER_DOGGY_DOMINANT_ORGASM_PUSSY = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.PITCHER,
			SexPace.DOM_ROUGH,
			null) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getActivePartner().isCoverableAreaExposed(CoverableArea.MOUTH)
					&& Sex.isDom(Main.game.getPlayer())
					&& Main.game.getPlayer().hasPenisIgnoreDildo();
		}
		
		@Override
		public String getActionTitle() {
			return "Pussy to mouth";
		}

		@Override
		public String getActionDescription() {
			return "Roughly fuck [npc.name] into the floor before filling [npc.her] [npc.pussy] with your cum. Then use [npc.her] mouth to clean yourself off.";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("As you feel [npc.name]'s [npc.pussy+] squeezing down around your [pc.cock+], you decide that you're going to show [npc.herHim] how an alpha treats their submissive little beta."
					+ " Letting out [pc.a_moan+], you slam your [pc.cock] deep into [npc.her] [npc.pussy], grinning devilishly as [npc.she] lets out [npc.a_moan+]."
					+ "<br/><br/>"
					+ "Reaching down, you grab the [npc.race]'s shoulders, pushing your weight down onto [npc.her] back as you roughly mount [npc.herHim], which causes [npc.her] torso to collapse down to the floor."
					+ " Bending down, with your [pc.cock] still hilted in [npc.her] [npc.pussy+], you growl menacingly in [npc.her] [npc.ear], "
							+ "[pc.speech(You little bitch! All you're good for is taking my load!)]"
					+"<br/><br/>"
					+ "[npc.Name] lets out another [npc.moan], which is enough to send you over the edge."
					+ " As you grind [npc.her] [npc.face+] into the floor, you reach your climax, and as your [pc.balls+] tense up");

			switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append(", you realise that you aren't able to produce even one drop of cum, somewhat lessening the impact of your dominant display.");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append(", a small trickle of [pc.cum+] squirts deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append(", a small amount of [pc.cum+] squirts deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append(", your [pc.cum+] shoots deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append(", your load pours out deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append(", your huge load pours out deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append(", your enormous load pours out deep into [npc.name]'s [npc.pussy], and you let out [pc.a_moan+] as you tell [npc.herHim] what a bitch [npc.she] is.");
					break;
				default:
					break;
			}

			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.name]'s well-used [npc.pussy], you feel a second orgasm building deep in your groin."
						+ " Grabbing [npc.her] hips to brace yourself, you go weak at the knees and clench your thighs together as your [pc.pussy+] shudders and quivers."
						+ " A mind-splitting orgasm washes through you, and you [pc.moanVerb] in delight as your feminine sex joins in on the fun."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.name]'s face is still collapsed down against the floor.");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "As you slide your softening member out from [npc.name]'s well-used [npc.pussy], you look down, grinning, at the mess you've made of [npc.herHim]."
						+ " Panting heavily, you suddenly remember what you had planned, and shuffle around to where [npc.name]'s face is still collapsed down against the floor.");
			}
			
			UtilText.nodeContentSB.append("<br/><br/>"
					+ "Reaching down, you grab a fistful of [npc.name]'s [npc.hair+], and before [npc.she] has a chance to react, you shove [npc.her] [npc.face+] down onto your [pc.cock+]."
					+ " [npc.She] [npc.moansVerb] and squirms as you give [npc.herHim] a taste of [npc.her] [npc.pussy], but you hold [npc.herHim] tightly in position, [pc.moaning] softly as [npc.her] frantic [npc.tongue] cleans you off."
					+ "<br/>"
					+ "After a minute of using the unfortunate [npc.race] in this manner, you finally release [npc.herHim], and, with a deep gasp, [npc.she] collapses to the floor, completely exhausted from your dominant treatment.");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public List<SexAreaOrifice> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.game.getPlayer()) && cumTarget.equals(Sex.getTargetedPartner(Main.game.getPlayer()))) {
				return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
			} else {
				return null;
			}
		}

		@Override
		public void applyEffects() {
			Sex.stopOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Sex.getActivePartner(), SexAreaOrifice.VAGINA);
		}
		
	};
	
}
