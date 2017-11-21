package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.69
 * @version 0.1.88
 * @author Innoxia
 */
public class GenericOrgasms {
	
	private static StringBuilder descriptionSB;
	
	/*
	 * Creampie
	 * Pull out
	 * Grind
	 */
	
	// PLAYER:
	
	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean takingCock = false;
			
			if((Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER)) {
				takingCock = true;
			}
			
			return !takingCock;
		}
		
		@Override
		public String getActionTitle() {
			return "Prepare";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Prepare yourself for it.";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPacePlayer()) {
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
	
	public static final SexAction PLAYER_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			null) {
		@Override
		public String getActionTitle() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER || Sex.getPenetrationTypeInOrifice(OrificeType.BREAST_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Request cum";
			} else {
				return "Request creampie";
			}
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Ask [npc.herHim] to fill you with [npc.her] cum.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean takingCock = false;

			if((Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER)) {
				takingCock = true;
			}
			
			return takingCock;
		}

		@Override
		public String getDescription() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+(Main.game.getPlayer().isVisiblyPregnant()
								?"[pc.speech(Fuck! Cum in me! I need your cum!)]"
								:"[pc.speech(Breed me! Cum in me! I need your cum!)]");
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum,"
						+ " [pc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+ " [pc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.BREAST_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+ " [pc.speech(Yes! Cum for me! Cover my tits with your cum!)]";
				
			} else {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out for [npc.name]'s cum, "
						+ " [pc.speech(Cum for me! I want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = true;
			SexFlags.playerRequestedPullOut = false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_PREGNANCY));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
				
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT));
			}
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_IMPREGNATION));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
				
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD));
			}
		}
	};
	
	public static final SexAction PLAYER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PLAYER_PREPARE_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		
		@Override
		public String getActionTitle() {
			return "Request pullout";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Ask [npc.herHim] to pull out before [npc.she] cums.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			boolean takingCock = false;

			if((Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER)) {
				takingCock = true;
			}
			
			return takingCock;
		}

		@Override
		public String getDescription() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out to [npc.name], "
						+(Main.game.getPlayer().isVisiblyPregnant()
								?"[pc.speech(Pull out! I don't want you to cum in me!)]"
								:"[pc.speech(Pull out! I don't want to get pregnant!)]");
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out to [npc.name], "
						+ "[pc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return "Through your desperate moans and lewd cries, you somehow manage to formulate a sentence as you cry out to [npc.name], "
						+ "[pc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else {
				return "Through your desperate moans and lewd cries, you somehow manage to cry out around [npc.name]'s [npc.cock], "
						+ "[pc.speech(Pull out! I don't want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.playerRequestedCreampie = false;
			SexFlags.playerRequestedPullOut = true;
		}
	};
	
	// TODO
	// Penis orgasm
	// Vagina orgasm
	
	public static final SexAction PLAYER_GENERIC_ORGASM_CUM_INSIDE = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stay in position";
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
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("You feel a desperate heat building in your groin, and with a lewd cry, you reach your orgasm.");
			
			descriptionSB.append("</br></br>");
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().getPenisType()!=PenisType.NONE){
				
				// Describe where penetration is occurring:
				if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Ramming the knot at the base of your [pc.cock+] into [npc.name]'s [npc.pussy+], you let out a deep groan as you feel it swelling up and locking you in place.");
							break;
						case EQUINE:
							descriptionSB.append("Ramming the wide head of your [pc.cock+] deep into [npc.name]'s [npc.pussy+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						case FELINE:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s [npc.pussy+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						default:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s [npc.pussy+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Ramming the knot at the base of your [pc.cock+] into [npc.name]'s [npc.asshole+], you let out a deep groan as you feel it swelling up and locking you in place.");
							break;
						case EQUINE:
							descriptionSB.append("Ramming the wide head of your [pc.cock+] deep into [npc.name]'s [npc.asshole+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						case FELINE:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s [npc.asshole+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						default:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s [npc.asshole+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Ramming the knot at the base of your [pc.cock+] past [npc.name]'s lips, you let out a deep groan as you feel it swelling up and locking you in place.");
							break;
						case EQUINE:
							descriptionSB.append("Ramming the wide head of your [pc.cock+] deep into [npc.name]'s throat, you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						case FELINE:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s throat, you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						default:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s throat, you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Ramming the knot at the base of your [pc.cock+] into [npc.name]'s [npc.nipple+], you let out a deep groan as you feel it swelling up and locking you in place.");
							break;
						case EQUINE:
							descriptionSB.append("Ramming the wide head of your [pc.cock+] deep into [npc.name]'s [npc.nipple+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						case FELINE:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s [npc.nipple+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
						default:
							descriptionSB.append("Ramming your [pc.cock+] deep into [npc.name]'s [npc.nipple+], you let out a deep groan as you feel your [pc.cock+] start to twitch.");
							break;
					}
					
				} else {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a deep groan as you feel the knot at the base of your [pc.cock+] swelling up as you prepare to cum.");
							break;
						case EQUINE:
							descriptionSB.append("You let out a deep groan as you feel the wide head of your [pc.cock+] swelling up as you prepare to cum.");
							break;
						case FELINE:
							descriptionSB.append("You let out a deep groan as you feel your [pc.cock+] start to twitch as you prepare to cum.");
							break;
						default:
							descriptionSB.append("You let out a deep groan as you feel your [pc.cock+] start to twitch as you prepare to cum.");
							break;
					}
				}
				
				// Describe cum amount:
				descriptionSB.append(" As your [pc.balls+] tense up");
				switch (Main.game.getPlayer().getPenisCumProduction()) {
					case ZERO_NONE:
						descriptionSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						descriptionSB.append(", you feel a small trickle of [pc.cum+] squirting");
						break;
					case TWO_SMALL_AMOUNT:
						descriptionSB.append(", you feel a small amount of [pc.cum+] squirting");
						break;
					case THREE_AVERAGE:
						descriptionSB.append(", you feel your [pc.cum+] squirting");
						break;
					case FOUR_LARGE:
						descriptionSB.append(", you feel your [pc.cum+] shooting");
						break;
					case FIVE_HUGE:
						descriptionSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SIX_EXTREME:
						descriptionSB.append(", you feel your [pc.cum+] pouring");
						break;
					case SEVEN_MONSTROUS:
						descriptionSB.append(", you feel your [pc.cum+] pouring");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Main.game.getPlayer().isWearingCondom()) {
						descriptionSB.append(" out into the condom that you're wearing.");
						
					} else if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
						descriptionSB.append(" into your [pc.lowClothing(penis)].");
						
					} else {
						if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
							if(Sex.getPartner().isVisiblyPregnant()) {
								descriptionSB.append(" deep into [npc.name]'s hungry [npc.pussy].");
							} else {
								descriptionSB.append(" deep into [npc.name]'s waiting womb.");
							}
							switch (Main.game.getPlayer().getPenisCumProduction()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									descriptionSB.append(" After a few seconds, [npc.name] realises that you're not even close to stopping, and as your [pc.cum+] backs up and starts drooling out of [npc.her] [npc.pussy],"
												+ " [npc.she] squirms about and lets out a series of [npc.moans+]."
													+ " It takes a while before you finally feel your balls running dry, by which time "
														+(Sex.getPartner().isVisiblyPregnant()
															?"your [pc.cum+] has formed a huge, slick puddle on the floor, and you smirk as you see that you've totally saturated [npc.name]'s [npc.pussy+] with your [pc.cum+]."
															:"[npc.name]'s stomach has quite visibly distended, and [npc.she] lets out a final, satisfied moan as [npc.she] feels the massive amount of [pc.cum+]"
																	+ " that you've deposited deep in [npc.her] womb."));
									break;
								default:
									break;
							}
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" deep into [npc.name]'s [npc.asshole+].");
							switch (Main.game.getPlayer().getPenisCumProduction()) {
								case SIX_EXTREME: case SEVEN_MONSTROUS:
									descriptionSB.append(" After a few seconds, [npc.name] realises that you're not even close to stopping, and as your "
											+"[pc.cum+] backs up and starts drooling out of [npc.her] [npc.asshole], [npc.she] lets out a desperate groan."
													+ " It takes a while before you finally feel your balls running dry, by which time "
														+"[npc.name]'s stomach has quite visibly distended, and [npc.she] lets out a final, satisfied moan as [npc.she] feels the massive amount of "
																+"[pc.cum+] that you've deposited deep in [npc.her] ass.");
									break;
								default:
									break;
							}
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" deep down [npc.name]'s throat.");
							switch (Main.game.getPlayer().getPenisCumProduction()) {
							case SIX_EXTREME: case SEVEN_MONSTROUS:
								descriptionSB.append(" After a few seconds, [npc.name] realises that you're not even close to stopping, and as your "
										+"[pc.cum+] backs up and starts drooling out the corners of [npc.her] mouth, [npc.she] lets out a desperate, muffled moan."
												+ " It takes a while before you finally feel your balls running dry, by which time [npc.name]'s stomach has quite visibly distended."
												+ " As you stop, [npc.she] finally gets a moment to draw in a deep, desperate breath, before looking down and groaning as [npc.she] feels the massive amount of "
															+"[pc.cum+] that you've deposited directly into [npc.her] stomach.");
								break;
							default:
								break;
						}
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" deep into [npc.name]'s [npc.breasts+].");
							
						} else {
							descriptionSB.append(" out all over the floor. ");
						}
					}
				}
				
				descriptionSB.append("</br></br>");
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				descriptionSB.append("A desperate, shuddering heat suddenly crashes up from your [pc.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
				
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) != null) {
					switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)) {
						case FINGER_PARTNER:
							descriptionSB.append(" [npc.Name]'s fingers carry on pumping away at your [pc.pussy+]"
									+", and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding digits.");
							break;
						case FINGER_PLAYER:
							descriptionSB.append(" You curl your fingers up deep inside your [pc.pussy+]"
									+", and, while desperately stroking in a 'come-hither' motion, you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your intruding digits.");
							break;
						case PENIS_PARTNER:
							descriptionSB.append(" [npc.Name] carries on fucking your [pc.pussy+]"
									+" through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around [npc.her] [npc.penis+].");
							break;
						case PENIS_PLAYER:
							descriptionSB.append(" You carry on fucking yourself through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [pc.cock+].");
							break;
						case TAIL_PARTNER:
							descriptionSB.append(" [npc.Name]'s tail carries on fucking your [pc.pussy+]"
									+" through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
							break;
						case TAIL_PLAYER:
							descriptionSB.append(" You carry on using your tail to fuck yourself through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
							break;
						case TONGUE_PARTNER:
							descriptionSB.append(" [npc.Name] carries on eating you out, licking and kissing at your [pc.pussy+]"
									+" while you orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding muscle.");
							break;
						case TONGUE_PLAYER:
							descriptionSB.append(" You carry on thrusting your tongue deep into your [pc.pussy+]"
									+" as you orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding muscle.");
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					descriptionSB.append(" Your [pc.pussy+]"
							+" clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through your groin.");
				}

				descriptionSB.append(" With a final, ear-splitting scream of pure arousal, your climax crashes over you, and after a few moments, leaves you as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Main.game.getPlayer().getPenisType()==PenisType.NONE && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				descriptionSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through you."
						+ " The muscles within your genderless mound start to spasm and contract, and you're soon left as a panting, moaning wreck as you come down from your surprisingly intense climax.");
			}

			return UtilText.parse(Sex.getPartner(),
					descriptionSB.toString());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
		}
		
		@Override
		public List<OrificeType> getPartnerAreasCummedIn() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA_PARTNER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS_PARTNER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH_PARTNER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE_PARTNER));
				
			} else {
				return null;
			}
		}
	};
		
	public static final SexAction PLAYER_GENERIC_ORGASM_PULL_OUT = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Pull out";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() || Sex.isSubHasEqualControl() || SexFlags.partnerRequestedPullOut) && Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("You feel a desperate heat building in your groin, and with a lewd cry, you reach your orgasm.");
			
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
				switch(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)){
					case FINGER_PARTNER:
						descriptionSB.append(" You pull [npc.name]'s fingers out of your [pc.ass+].");
						break;
					case FINGER_PLAYER:
						descriptionSB.append(" You pull your fingers out of your [pc.ass+].");
						break;
					case PENIS_PARTNER:
						descriptionSB.append(" Grabbing [npc.name]'s hips, you push back, causing [npc.her] [npc.penis+] to slip out of your [pc.ass+].");
						break;
					case PENIS_PLAYER:
						descriptionSB.append(" You pull your [pc.cock+] out of your [pc.ass+].");
						break;
					case TAIL_PARTNER:
						descriptionSB.append(" Grabbing [npc.name]'s tail, you pull it out of your [pc.ass+].");
						break;
					case TAIL_PLAYER:
						descriptionSB.append(" Grabbing your tail, you pull it out of your [pc.ass+].");
						break;
					case TONGUE_PARTNER:
						descriptionSB.append(" Grabbing [npc.name]'s head, you push back, causing [npc.her] tongue to slip out of your [pc.ass+].");
						break;
					case TONGUE_PLAYER:
						descriptionSB.append(" You slide your tongue out of your [pc.ass+].");
						break;
					default:
						break;
				}
			if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)!=null)
				switch(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)){
					case FINGER_PARTNER:
						descriptionSB.append(" You pull [npc.name]'s fingers out of your mouth.");
						break;
					case FINGER_PLAYER:
						descriptionSB.append(" You pull your fingers out of your mouth.");
						break;
					case PENIS_PARTNER:
						descriptionSB.append(" Grabbing [npc.name]'s hips, you push back, causing [npc.her] [npc.penis+] to slip out of your mouth.");
						break;
					case PENIS_PLAYER:
						descriptionSB.append(" You pull your [pc.cock+] out of your mouth.");
						break;
					case TAIL_PARTNER:
						descriptionSB.append(" Grabbing [npc.name]'s tail, you pull it out of your mouth.");
						break;
					case TAIL_PLAYER:
						descriptionSB.append(" Grabbing your tail, you pull it out of your mouth.");
						break;
					case TONGUE_PARTNER:
						descriptionSB.append(" Grabbing [npc.name]'s head, you push back, causing [npc.her] tongue to slip out of your mouth.");
						break;
					case TONGUE_PLAYER:
						descriptionSB.append(" You slide your tongue out of your throat.");
						break;
					default:
						break;
				}

			descriptionSB.append("</br></br>");
			
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().getPenisType()!=PenisType.NONE){
				
				// Describe where penetration is occurring:
				if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.pussy+] just as your fat knot starts to swell up.");
							break;
						case EQUINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.pussy+], letting out a deep groan as you feel your wide head pop free.");
							break;
						case FELINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.pussy+], eliciting a manic squeal as your barbs ruthlessly rake the inner walls of [npc.her] vagina.");
							break;
						default:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.pussy+], letting out a deep groan as you feel the head of your cock pop free.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.asshole+] just as your fat knot starts to swell up.");
							break;
						case EQUINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.asshole+], letting out a deep groan as you feel your wide head pop free.");
							break;
						case FELINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.ass+], eliciting a manic squeal as your barbs ruthlessly rake the inner walls of [npc.her] [npc.asshole+].");
							break;
						default:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.hips+], you pull your [pc.cock+] out of [npc.her] [npc.asshole+], letting out a deep groan as you feel the head of your cock pop free.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Taking hold of [npc.name]'s head, you pull your [pc.cock+] out of [npc.her] mouth just as your fat knot starts to swell up.");
							break;
						case EQUINE:
							descriptionSB.append("Taking hold of [npc.name]'s head, you pull your [pc.cock+] out of [npc.her] mouth, letting out a deep groan as you feel your wide head pop free.");
							break;
						case FELINE:
							descriptionSB.append("Taking hold of [npc.name]'s head, you pull your [pc.cock+] out of [npc.her] mouth, eliciting a manic squeal as your barbs ruthlessly rake the inner walls of [npc.her] vagina.");
							break;
						default:
							descriptionSB.append("Taking hold of [npc.name]'s head, you pull your [pc.cock+] out of [npc.her] mouth, letting out a deep groan as you feel the head of your cock pop free.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.breasts+], you pull your [pc.cock+] out of [npc.her] [npc.nipple+] just as your fat knot starts to swell up.");
							break;
						case EQUINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.breasts+], you pull your [pc.cock+] out of [npc.her] [npc.nipple+], letting out a deep groan as you feel your wide head pop free.");
							break;
						case FELINE:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.breasts+], you pull your [pc.cock+] out of [npc.her] [npc.nipple+], eliciting a manic squeal as your barbs ruthlessly rake the inner walls of [npc.her] [npc.nipple].");
							break;
						default:
							descriptionSB.append("Taking hold of [npc.name]'s [npc.breasts+], you pull your [pc.cock+] out of [npc.her] [npc.nipple+], letting out a deep groan as you feel the head of your cock pop free.");
							break;
					}
					
				} else {
					switch(Main.game.getPlayer().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a deep groan as you feel the knot at the base of your [pc.cock+] swelling up as you prepare to cum.");
							break;
						case EQUINE:
							descriptionSB.append("You let out a deep groan as you feel the wide head of your [pc.cock+] swelling up as you prepare to cum.");
							break;
						case FELINE:
							descriptionSB.append("You let out a deep groan as you feel your [pc.cock+] twitch and throb as you prepare to cum.");
							break;
						default:
							descriptionSB.append("You let out a deep groan as you feel your [pc.cock+] twitch and throb as you prepare to cum.");
							break;
					}
				}
				
				// Describe cum amount:
				descriptionSB.append(" As your "+Main.game.getPlayer().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Main.game.getPlayer().getPenisCumProduction()) {
					case ZERO_NONE:
						descriptionSB.append(", you realise that you're not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						descriptionSB.append(", you look down to see a small trickle of cum squirting");
						break;
					case TWO_SMALL_AMOUNT:
						descriptionSB.append(", you look down to see a small amount of cum squirting");
						break;
					case THREE_AVERAGE:
						descriptionSB.append(", you look down to see your cum squirting");
						break;
					case FOUR_LARGE:
						descriptionSB.append(", you look down to see your cum shooting");
						break;
					case FIVE_HUGE:
						descriptionSB.append(", you look down to see your cum pouring");
						break;
					case SIX_EXTREME:
						descriptionSB.append(", you look down to see your cum pouring");
						break;
					case SEVEN_MONSTROUS:
						descriptionSB.append(", you look down to see your cum pouring");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Main.game.getPlayer().isWearingCondom()) {
						descriptionSB.append("out into the condom that you're wearing.");
						
					} else if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
						descriptionSB.append(" into your [pc.lowClothing(penis)].");
						
					} else {
						if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" out all over the entrance to [npc.name]'s [npc.pussy+].");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" out all over [npc.name]'s [npc.asshole+].");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" out all over [npc.name]'s face.");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
							descriptionSB.append(" out all over [npc.name]'s [npc.breasts+].");
							
						} else {
							descriptionSB.append(" out all over the floor.");
						}
					}
				}
				descriptionSB.append("</br></br>");
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				descriptionSB.append("A desperate, shuddering heat suddenly crashes up from your [pc.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
				
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) != null) {
					switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)) {
						case FINGER_PARTNER:
							descriptionSB.append(" Grabbing [npc.name]'s hand, you pull [npc.her] fingers out of your [pc.pussy+],"
									+ " letting out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case FINGER_PLAYER:
							descriptionSB.append(" Pulling your fingers out of your [pc.pussy+],"
									+ " letting out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case PENIS_PARTNER:
							descriptionSB.append(" Grabbing [npc.name]'s hips, you pull back from [npc.her] groin, letting [npc.her] [npc.penis+] slide out of your [pc.pussy+]"
									+", and letting out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case PENIS_PLAYER:
							descriptionSB.append(" Sliding your [pc.cock+] out of your [pc.pussy+]"
									+", you let out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case TAIL_PARTNER:
							descriptionSB.append(" Grabbing [npc.name]'s tail, you slide it out of your [pc.pussy+]"
									+", letting out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case TAIL_PLAYER:
							descriptionSB.append(" Grabbing your tail, you slide it out of your [pc.pussy+]"
									+", letting out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case TONGUE_PARTNER:
							descriptionSB.append(" Grabbing [npc.name]'s head, you push [npc.her] back away from your groin, letting [npc.her] tongue slip out from your [pc.pussy+]"
									+", and you let out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						case TONGUE_PLAYER:
							descriptionSB.append(" Sliding your tongue out from your [pc.pussy+]"
									+", you let out a high-pitched moan as your vaginal muscles grip and squeeze down on the sudden emptiness between your legs.");
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					descriptionSB.append(" Your [pc.pussy+] clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through your groin.");
				}

				descriptionSB.append(" With a final, ear-splitting scream of pure arousal, your climax crashes over you, and after a few moments, leaves you as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Main.game.getPlayer().getPenisType()==PenisType.NONE && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				descriptionSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through you."
						+ " The muscles within your genderless mound start to spasm and contract, and you're soon left as a panting, moaning wreck as you come down from your surprisingly intense climax.");
			}
			
			return UtilText.parse(Sex.getPartner(),
					descriptionSB.toString());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
		}
	};
	
	// PARTNER
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean takingCock = false;
			
			if((Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER)) {
				takingCock = true;
			}
			
			return !takingCock;
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
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
			switch(Sex.getSexPacePartner()) {
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
	
	public static final SexAction PARTNER_ASK_FOR_CREAMPIE = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
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
			boolean takingCock = false;
			
			if((Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER)) {
				takingCock = true;
			}
			
			return takingCock && Sex.getSexPacePartner()!=SexPace.SUB_RESISTING;
		}

		@Override
		public SexActionPriority getPriority() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)==PenetrationType.PENIS_PARTNER && Sex.getPartner().hasFetish(Fetish.FETISH_PREGNANCY)) {
				return SexActionPriority.NORMAL;
			} else {
				return SexActionPriority.LOW;
			}
		}

		@Override
		public String getDescription() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum, "
						+(Sex.getPartner().isVisiblyPregnant()
								?"[npc.speech(Fuck! Cum in me! I need your cum!)]"
								:"[npc.speech(Breed me! Cum in me! I need your cum!)]");
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum,"
						+ " [npc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum, "
						+ " [npc.speech(Fuck! Cum in me! I need your cum!)]";
				
			} else {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out for your cum, "
						+ " [npc.speech(Cum for me! I want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedCreampie = true;
			SexFlags.partnerRequestedPullOut = false;
		}
		
		@Override
		public List<Fetish> getFetishesPlayer() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_IMPREGNATION));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_ANAL_GIVING));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD), new ListValue<>(Fetish.FETISH_BREASTS_OTHERS));
				
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_STUD));
			}
		}
		
		@Override
		public List<Fetish> getFetishesPartner() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_PREGNANCY));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_ANAL_RECEIVING));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT), new ListValue<>(Fetish.FETISH_BREASTS_SELF));
				
			} else {
				return Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_CUM_ADDICT));
			}
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PARTNER_PREPARE_PLAYER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
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
			boolean takingCock = false;
			
			if((Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER)
					|| (Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER)) {
				takingCock = true;
			}
			
			return takingCock
					&& (Sex.getSexPacePartner()==SexPace.SUB_RESISTING
						|| ((Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)==PenetrationType.PENIS_PLAYER
							?!Sex.getPartner().hasFetish(Fetish.FETISH_CUM_ADDICT)
							:true)
						&& (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)==PenetrationType.PENIS_PLAYER
							?(!Sex.getPartner().hasFetish(Fetish.FETISH_PREGNANCY) && !Sex.getPartner().hasFetish(Fetish.FETISH_BROODMOTHER))
							:true)));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW;
		}
		
		@Override
		public String getDescription() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+(Sex.getPartner().isVisiblyPregnant()
								?"[npc.speech(Pull out! I don't want you to cum in me!)]"
								:"[npc.speech(Pull out! I don't want to get pregnant!)]");
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+ "[npc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+ "[npc.speech(Pull out! I don't want you to cum in me!)]";
				
			} else {
				return "Through [npc.her] desperate moans and lewd cries, [npc.name] somehow manages to formulate a sentence as [npc.she] cries out to you, "
						+ "[npc.speech(Pull out! I don't want to taste your cum!)]";
			}
		}

		@Override
		public void applyEffects() {
			SexFlags.partnerRequestedCreampie = false;
			SexFlags.partnerRequestedPullOut = true;
		}
	};
	
	public static final SexAction PARTNER_GENERIC_ORGASM_CUM_INSIDE = new SexAction(
			SexActionType.PARTNER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
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
			return SexActionPriority.LOW; // Prefer to use scene-specific orgasms
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getPartner() instanceof Cultist) { //TODO 
				return true;
			}
			
			return SexFlags.playerRequestedCreampie
					|| (!SexFlags.playerRequestedPullOut && !SexFlags.playerRequestedCreampie);
		}

		@Override
		public String getDescription() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("[npc.Name] lets out a lewd cry as [npc.she] reaches [npc.her] orgasm.");
			
			// PENIS ORGASM:
			
			if(Sex.getPartner().getPenisType()!=PenisType.NONE){

				descriptionSB.append("</br></br>");
				
				// Describe where penetration is occurring:
				if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] into your [pc.pussy+]."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] deep into your [pc.pussy+]."
									+ " Your eyes go wide as you feel it lewdly spreading your inner walls out, and as the base bumps against your pussy lips, you let out a shuddering moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.pussy+]."
									+ " As the base bumps against your pussy lips, [npc.she] starts gently bucking back and forth, causing you to let out a series of pleasurable screams as [npc.her] barbs start ruthlessly raking your vaginal walls.");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.pussy+]."
									+ " Your eyes go wide as you feel the base bump against your pussy lips, and you can't help but let out a shuddering moan as you find yourself filled with cock.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] into your [pc.asshole+]."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] deep into your [pc.asshole+]."
									+ " Your eyes go wide as you feel it lewdly spreading you out, and as the base bumps against your [pc.ass+], you let out a shuddering moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.asshole+]."
									+ " As the base bumps against your [pc.ass], [npc.she] starts gently bucking back and forth,"
										+ " causing you to let out a series of pleasurable screams as [npc.her] barbs start ruthlessly raking the insides of your [pc.ass].");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.asshole+]."
									+ " Your eyes go wide as you feel the base bump against your [pc.ass+], and you can't help but let out a shuddering moan as you find yourself filled with cock.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] past your lips and into your mouth."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] down over your tongue."
									+ " Your eyes go wide as you feel it lewdly spreading your throat out, and as the base bumps against your lips, you let out a muffled moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your throat."
									+ " As the base bumps against your lips, [npc.she] starts gently bucking back and forth, causing you to let out a series of muffled cries as [npc.her] barbs"
									+ " start ruthlessly raking the insides of your throat.");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep down your throat."
									+ " Your eyes go wide as you feel the base bump against your lips, and you can't help but let out a muffled moan as you find yourself filled with cock.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the knot at the base of [npc.her] "
									+"[npc.cock+] into your [pc.nipple+]."
									+ " Your eyes go wide as you feel it swell up, and as you find yourself locked in position, you let out a shuddering moan.");
							break;
						case EQUINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming the wide, flared head of [npc.her] "
									+"[npc.penis] deep into your [pc.nipple+]."
									+ " Your eyes go wide as you feel it lewdly spreading you out, and as the base bumps against your [pc.breasts+], you let out a shuddering moan.");
							break;
						case FELINE:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] [npc.penis] deep into your [pc.nipple+]."
									+ " As the base bumps against your [pc.breast+], [npc.she] starts gently bucking back and forth, causing you to let out a series of pleasurable screams"
										+ " as [npc.her] barbs start ruthlessly raking the insides of your [pc.breast].");
							break;
						default:
							descriptionSB.append("You squeal and squirm about as [npc.name] suddenly pushes forwards, ramming [npc.her] "
									+"[npc.penis] deep into your [pc.nipple+]."
									+ " Your eyes go wide as you feel the base bump against your [pc.breasts+], and you can't help but let out a shuddering moan as you find yourself filled with cock.");
							break;
					}
					
				} else {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the knot at the base of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case EQUINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the wide head of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case FELINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
						default:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
					}
				}
				
				// Describe cum amount:
				descriptionSB.append(" As [npc.her] "+Sex.getPartner().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Sex.getPartner().getPenisCumProduction()) {
					case ZERO_NONE:
						descriptionSB.append(", you see that [npc.she]'s not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						descriptionSB.append(", a small trickle of cum squirts");
						break;
					case TWO_SMALL_AMOUNT:
						descriptionSB.append(", a small amount of cum squirts");
						break;
					case THREE_AVERAGE:
						descriptionSB.append(", [npc.her] cum squirts");
						break;
					case FOUR_LARGE:
						descriptionSB.append(", [npc.her] cum shoots");
						break;
					case FIVE_HUGE:
						descriptionSB.append(", [npc.her] cum pours");
						break;
					case SIX_EXTREME:
						descriptionSB.append(", [npc.her] cum pours");
						break;
					case SEVEN_MONSTROUS:
						descriptionSB.append(", [npc.her] cum pours");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Sex.getPartner().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Sex.getPartner().isWearingCondom()) {
						descriptionSB.append("out into the condom that [npc.she]'s wearing.");
						
					} else if (!Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
						descriptionSB.append(" into [npc.her] [npc.lowClothing(penis)].");
						
					} else {
						if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
							if(Main.game.getPlayer().isVisiblyPregnant())
								descriptionSB.append(" deep into your hungry [pc.pussy], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of you.");
							else
								descriptionSB.append(" deep into your waiting womb, and you find yourself whining and moaning as you wonder if the [npc.cum+] will get you pregnant.");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" deep into your [pc.asshole+], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of you.");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" deep down your throat, and you find yourself making muffled whining noises as you feel the [npc.cum+] sliding down into your stomach.");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" deep into your [pc.breasts+], and you find yourself whining and moaning as you feel the [npc.cum+] deep inside of your [pc.breasts+].");
							
						} else {
							descriptionSB.append(" out all over the floor.");
						}
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Sex.getPartner().getVaginaType()!=VaginaType.NONE) {

				descriptionSB.append("</br></br>");
				
				descriptionSB.append("[npc.She] suddenly lets out a manic squeal as [npc.her] orgasm starts rising up from [npc.her] [npc.pussy+].");
				
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) != null) {
					switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)) {
						case FINGER_PARTNER:
							descriptionSB.append(" [npc.She] curls [npc.her] fingers up deep inside [npc.her] [npc.pussy+]"
									+", and [npc.she] lets out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding digits.");
							break;
						case FINGER_PLAYER:
							descriptionSB.append(" You push your fingers deep inside [npc.her] [npc.pussy+]"
									+", and, while eagerly stroking in a 'come-hither' motion, you feel [npc.her] vaginal muscles grip and squeeze around your intruding digits as [npc.she] lets out a series of high-pitched moans.");
							break;
						case PENIS_PARTNER:
							descriptionSB.append(" [npc.She] carries on fucking [npc.herHim]self through [npc.her] orgasm, and [npc.she] starts letting out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around [npc.her] "
									+"[npc.cock+].");
							break;
						case PENIS_PLAYER:
							descriptionSB.append(" You carry on fucking [npc.her] [npc.pussy+]"
									+" through [npc.her] orgasm, causing [npc.herHim] to let out a series of desperate, high-pitched moans as [npc.her] vaginal muscles grip and squeeze around your [pc.cock+].");
							break;
						case TAIL_PARTNER:
							descriptionSB.append(" [npc.She] carries on using [npc.her] tail to fuck [npc.herHim]self through [npc.her] orgasm,"
									+ " and [npc.she] starts letting out a series of high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
							break;
						case TAIL_PLAYER:
							descriptionSB.append(" You carry on using your tail to fuck [npc.her] [npc.pussy+]"
									+" through [npc.her] orgasm, causing [npc.herHim] to let out a series of desperate, high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding object.");
							break;
						case TONGUE_PARTNER:
							descriptionSB.append(" [npc.She] carries on thrusting [npc.her] tongue deep into [npc.her] [npc.pussy+]"
									+" while [npc.she] orgasms, causing [npc.herHim] to let out a series of desperate, high-pitched moans as [npc.her] vaginal muscles grip and squeeze around the intruding muscle.");
							break;
						case TONGUE_PLAYER:
							descriptionSB.append(" You carry on eating [npc.herHim] out, licking and kissing at [npc.her] [npc.pussy+]"
									+" while [npc.she] orgasms, and you let out a muffled moan as you feel [npc.her] vaginal muscles grip and squeeze around your tongue.");
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					descriptionSB.append(" [npc.Her] [npc.pussy+]"
							+" clenches down hard, and although [npc.she] finds [npc.herHim]self empty, [npc.she] still lets out a desperate series of high-pitched moans as a deep pleasure radiates up through [npc.her] groin.");
				}

				descriptionSB.append(" With a final, ear-splitting scream of pure arousal, [npc.her] climax crashes over [npc.herHim], and after a few moments, leaves [npc.herHim] as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Sex.getPartner().getPenisType()==PenisType.NONE && Sex.getPartner().getVaginaType()==VaginaType.NONE) {
				
				descriptionSB.append("</br></br>");
				
				descriptionSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through [npc.herHim]."
						+ " The muscles within [npc.her] genderless mound start to spasm and contract, and [npc.she]'s soon left as a panting, moaning wreck as [npc.she] comes down from [npc.her] surprisingly intense climax.");
			}

			return UtilText.parse(Sex.getPartner(),
					descriptionSB.toString());
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<OrificeType> getPlayerAreasCummedIn() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA_PLAYER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS_PLAYER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH_PLAYER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE_PLAYER));
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isPlayerDom() && !Sex.isConsensual();
		}
	};
		
	public static final SexAction PARTNER_GENERIC_ORGASM_PULL_OUT = new SexAction(SexActionType.PARTNER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Pull out";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.LOW; // Prefer to use scene-specific orgasms
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isAnyPenetrationHappening()
					&& (SexFlags.playerRequestedPullOut || (!SexFlags.playerRequestedPullOut && !SexFlags.playerRequestedCreampie));
//			(!Sex.isPlayerDom() ||Sex.isConsensual())
		}

		@Override
		public String getDescription() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("[npc.Name] lets out a lewd cry as [npc.she] reaches [npc.her] orgasm.");
			
			if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)!=null)
				switch(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)){
					case FINGER_PARTNER:
						descriptionSB.append(" [npc.She] pulls [npc.her] fingers out of [npc.her] [npc.ass+].");
						break;
					case FINGER_PLAYER:
						descriptionSB.append(" [npc.She] grabs your hand and pulls your fingers out of [npc.her] [npc.ass+].");
						break;
					case PENIS_PARTNER:
						descriptionSB.append(" [npc.She] pulls [npc.her] [npc.penis+] out of [npc.her] [npc.ass+].");
						break;
					case PENIS_PLAYER:
						descriptionSB.append(" Grabbing your hips, [npc.she] pushes back, causing your [pc.cock+] to slip out of [npc.her] [npc.ass+].");
						break;
					case TAIL_PARTNER:
						descriptionSB.append(" Grabbing [npc.her] tail, [npc.she] pulls it out of [npc.her] [npc.ass+].");
						break;
					case TAIL_PLAYER:
						descriptionSB.append(" Grabbing your tail, [npc.she] pulls it out of [npc.her] [npc.ass+].");
						break;
					case TONGUE_PARTNER:
						descriptionSB.append(" [npc.She] slides [npc.her] tongue out of [npc.her] [npc.ass+].");
						break;
					case TONGUE_PLAYER:
						descriptionSB.append(" Grabbing your head, [npc.she] pushes back, causing your tongue to slip out of [npc.her] [npc.ass+].");
						break;
					default:
						break;
				}
			if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)!=null)
				switch(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)){
					case FINGER_PARTNER:
						descriptionSB.append(" [npc.She] pulls [npc.her] fingers out of [npc.her] mouth.");
						break;
					case FINGER_PLAYER:
						descriptionSB.append(" [npc.She] pulls your fingers out of [npc.her] mouth.");
						break;
					case PENIS_PARTNER:
						descriptionSB.append(" [npc.She] pulls [npc.her] [npc.penis+] out of [npc.her] mouth.");
						break;
					case PENIS_PLAYER:
						descriptionSB.append(" Grabbing your hips, [npc.she] pushes back, causing your [pc.cock+] to slip out of [npc.her] mouth.");
						break;
					case TAIL_PARTNER:
						descriptionSB.append(" Grabbing [npc.her] tail, [npc.she] pulls it out of [npc.her] mouth.");
						break;
					case TAIL_PLAYER:
						descriptionSB.append(" Grabbing your tail, [npc.she] pulls it out of [npc.her] mouth.");
						break;
					case TONGUE_PARTNER:
						descriptionSB.append(" [npc.She] slides [npc.her] tongue out of [npc.her] throat.");
						break;
					case TONGUE_PLAYER:
						descriptionSB.append(" Grabbing your head, [npc.she] pushes back, causing your tongue to slip out of [npc.her] mouth.");
						break;
					default:
						break;
				}
			
			descriptionSB.append("</br></br>");
			// PENIS ORGASM:
			
			if(Sex.getPartner().getPenisType()!=PenisType.NONE){
				
				// Describe where penetration is occurring:
				if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your [pc.pussy+].");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your [pc.pussy+].");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your [pc.pussy+]."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a desperate scream as you feel [npc.her] barbs roughly rake back along your vaginal walls.");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your [pc.pussy+].");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your [pc.asshole+].");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your [pc.asshole+].");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your [pc.asshole+]."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a desperate scream as you feel [npc.her] barbs roughly rake back along the inside of your [pc.ass+].");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your hips, and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your [pc.asshole+].");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your throat.");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your throat.");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your throat."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a muffled squeal as you feel [npc.her] barbs roughly rake up along the sides of your throat.");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your head, and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your throat.");
							break;
					}
					
				} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], and, just as [npc.her] knot starts to swell up, [npc.she] pulls back, sliding [npc.her] "
									+"[npc.cock+] out of your [pc.nipple+].");
							break;
						case EQUINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], and, as [npc.she] pulls back, you feel the wide, flared head of [npc.her] "
									+"[npc.cock+] slide out of your [pc.nipple+].");
							break;
						case FELINE:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], before pulling back and sliding [npc.her] "
									+"[npc.cock+] out of your [pc.nipple+]."
									+ " As [npc.she] withdraws [npc.her] spiny cock, you let out a desperate scream as you feel [npc.her] barbs roughly rake back along the insides of your [pc.nipple+].");
							break;
						default:
							descriptionSB.append("You let out a surprised cry as [npc.name] suddenly grabs your [pc.breasts+], and, as [npc.she] pulls back, you feel [npc.her] "
									+"[npc.cock+] slide out of your [pc.nipple+].");
							break;
					}
					
				} else {
					switch(Sex.getPartner().getPenisType()){
						case CANINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the knot at the base of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case EQUINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as the wide head of [npc.her] [npc.penis+] swells up as [npc.she] prepares to cum.");
							break;
						case FELINE:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
						default:
							descriptionSB.append("[npc.Name] lets out a deep groan as [npc.her] [npc.penis+] starts to twitch as [npc.she] prepares to cum.");
							break;
					}
				}
				
				// Describe cum amount:
				descriptionSB.append(" As [npc.her] "+Sex.getPartner().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Sex.getPartner().getPenisCumProduction()) {
					case ZERO_NONE:
						descriptionSB.append(", you see that [npc.she]'s not able to produce even one drop of cum.");
						break;
					case ONE_TRICKLE:
						descriptionSB.append(", a small trickle of [npc.cum+] squirts");
						break;
					case TWO_SMALL_AMOUNT:
						descriptionSB.append(", a small amount of [npc.cum+] squirts");
						break;
					case THREE_AVERAGE:
						descriptionSB.append(", [npc.her] [npc.cum+] squirts");
						break;
					case FOUR_LARGE:
						descriptionSB.append(", [npc.her] [npc.cum+] shoots");
						break;
					case FIVE_HUGE:
						descriptionSB.append(", [npc.her] [npc.cum+] pours");
						break;
					case SIX_EXTREME:
						descriptionSB.append(", [npc.her] [npc.cum+] pours");
						break;
					case SEVEN_MONSTROUS:
						descriptionSB.append(", [npc.her] [npc.cum+] pours");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Sex.getPartner().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Sex.getPartner().isWearingCondom()) {
						descriptionSB.append("out into the condom that [npc.she]'s wearing.");
						
					} else if (!Sex.getPartner().isCoverableAreaExposed(CoverableArea.PENIS)) {
						descriptionSB.append(" into [npc.her] [npc.lowClothing(penis)].");
						
					} else {
						if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" out all over the entrance to your [pc.pussy+].");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" out all over your [pc.asshole+].");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" out all over your face, causing you to blink and flinch.");
							
						} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
							descriptionSB.append(" out all over your [pc.breasts+].");
							
						} else {
							descriptionSB.append(" out all over the floor.");
						}
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Sex.getPartner().getVaginaType()!=VaginaType.NONE) {
				
				if(Sex.getPartner().getPenisType()!=PenisType.NONE) {
					descriptionSB.append(" ");
				}
				
				descriptionSB.append("[npc.She] suddenly lets out a manic squeal as [npc.her] orgasm starts rising up from [npc.her] [npc.pussy+].");
				
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) != null) {
					switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)) {
						case FINGER_PARTNER:
							descriptionSB.append(" Pulling [npc.her] fingers out of [npc.her] [npc.pussy+]"
									+", [npc.she] lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case FINGER_PLAYER:
							descriptionSB.append(" Grabbing your hand, [npc.she] pulls your fingers out of [npc.her] [npc.pussy+]"
									+", letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case PENIS_PARTNER:
							descriptionSB.append(" Sliding [npc.her] [npc.penis+] out of [npc.her] [npc.pussy+]"
									+", [npc.she] lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case PENIS_PLAYER:
							descriptionSB.append(" Grabbing your hips, [npc.she] pulls back from your groin, letting your [pc.cock+] slide out of [npc.her] [npc.pussy+]"
									+", and letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case TAIL_PARTNER:
							descriptionSB.append(" Grabbing [npc.her] tail, [npc.she] slides it out of [npc.her] [npc.pussy+]"
									+", letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case TAIL_PLAYER:
							descriptionSB.append(" Grabbing your tail, [npc.she] slides it out of [npc.her] [npc.pussy+]"
									+", letting out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case TONGUE_PARTNER:
							descriptionSB.append(" Sliding [npc.her] tongue out from [npc.her] [npc.pussy+]"
									+", [npc.she] lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						case TONGUE_PLAYER:
							descriptionSB.append(" Grabbing your head, [npc.she] pushes you back away from [npc.her] groin, causing your tongue to slip out from [npc.her] [npc.pussy+]."
									+ " [npc.She] then lets out a high-pitched moan as [npc.her] vaginal muscles grip and squeeze down on the sudden emptiness between [npc.her] legs.");
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					descriptionSB.append(" [npc.Her] [npc.pussy+]"
							+" clenches down hard, and the wave of disappointment upon finding itself empty almost overwhelms the pleasure that radiates up through [npc.her] groin.");
				}
				
				descriptionSB.append(" With a final, ear-splitting scream of pure arousal, [npc.her] climax crashes over [npc.herHim], and after a few moments, leaves [npc.herHim] as a panting, thoroughly-satisfied wreck.");
			}
			
			// MOUND ORGASM:
			if (Sex.getPartner().getPenisType()==PenisType.NONE && Sex.getPartner().getVaginaType()==VaginaType.NONE) {
				descriptionSB.append("With an ear-splitting scream and trembling legs, a crashing wave of pure ecstasy suddenly washes through [npc.herHim]."
						+ " The muscles within [npc.her] genderless mound start to spasm and contract, and [npc.she]'s soon left as a panting, moaning wreck as [npc.she] comes down from [npc.her] surprisingly intense climax.");
			}

			return UtilText.parse(Sex.getPartner(),
					descriptionSB.toString());
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isPlayerDom() && !Sex.isConsensual();
		}
	};
	
	
	// Mutual orgasms:
	
	public static final SexAction PLAYER_GENERIC_MUTUAL_ORGASM_CUM_INSIDE = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Stay in position";
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
			return(PLAYER_GENERIC_ORGASM_CUM_INSIDE.getDescription()
					+"</br></br>"
					+PARTNER_GENERIC_ORGASM_CUM_INSIDE.getDescription());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
		}
		
		@Override
		public List<OrificeType> getPlayerAreasCummedIn() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA_PLAYER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS_PLAYER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH_PLAYER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) == PenetrationType.PENIS_PARTNER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE_PLAYER));
				
			} else {
				return null;
			}
		}
		
		@Override
		public List<OrificeType> getPartnerAreasCummedIn() {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.VAGINA_PARTNER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.ANUS_PARTNER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.MOUTH_PARTNER));
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) == PenetrationType.PENIS_PLAYER) {
				return Util.newArrayListOfValues(new ListValue<>(OrificeType.NIPPLE_PARTNER));
				
			} else {
				return null;
			}
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isPlayerDom() && !Sex.isConsensual();
		}
	};
	
	public static final SexAction PLAYER_GENERIC_MUTUAL_ORGASM_PULL_OUT = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Pull out";
		}

		@Override
		public String getActionDescription() {
			return "You've reached your climax, and can't hold back your orgasm any longer.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isPlayerDom() ||Sex.isConsensual()) && Sex.isAnyPenetrationHappening();
		}

		@Override
		public String getDescription() {
			return(PLAYER_GENERIC_ORGASM_PULL_OUT.getDescription()
					+"</br></br>"
					+PARTNER_GENERIC_ORGASM_PULL_OUT.getDescription());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
		}
		
		@Override
		public boolean endsSex() {
			return !Sex.isPlayerDom() && !Sex.isConsensual();
		}
	};
}
