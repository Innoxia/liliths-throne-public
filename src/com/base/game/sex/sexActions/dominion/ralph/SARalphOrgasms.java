package com.base.game.sex.sexActions.dominion.ralph;

import java.util.List;

import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.npc.dominion.Ralph;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.clothing.CoverableArea;
import com.base.game.sex.ArousalIncrease;
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
public class SARalphOrgasms {

	public static SexAction PLAYER_ORGASM = new SexAction(
			SexActionType.PLAYER_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
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
			
			if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) == PenetrationType.PENIS_PARTNER) {
				UtilText.nodeContentSB.append("As Ralph's huge black cock thrusts deep down your throat, you feel yourself reaching your climax, and before you know what's happening, you're letting out a deperate, muffled [pc.moan].");
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) == PenetrationType.PENIS_PARTNER) {
				UtilText.nodeContentSB.append("As Ralph's huge black cock thrusts deep into your [pc.pussy+], you feel yourself reaching your climax, and before you know what's happening, you're letting out [pc.a_moan+].");
				
			} else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) == PenetrationType.PENIS_PARTNER) {
				UtilText.nodeContentSB.append("As Ralph's huge black cock thrusts deep into your [pc.asshole+], you feel yourself reaching your climax, and before you know what's happening, you're letting out [pc.a_moan+].");
				
			} else {
				UtilText.nodeContentSB.append("You feel a desperate heat building in your groin, and with [pc.a_moan+], you reach your climax.");
			}
			
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().getPenisType()!=PenisType.NONE){

				UtilText.nodeContentSB.append("</br></br>");
				
				switch(Main.game.getPlayer().getPenisType()){
					case CANINE:
						UtilText.nodeContentSB.append("You let out a deep groan as you feel the knot at the base of your [pc.cock+] swelling up as you prepare to cum.");
						break;
					case EQUINE:
						UtilText.nodeContentSB.append("You let out a deep groan as you feel the wide head of your [pc.cock+] swelling up as you prepare to cum.");
						break;
					case FELINE:
						UtilText.nodeContentSB.append("You let out a deep groan as you feel your [pc.cock+] twitch and throb as you prepare to cum.");
						break;
					default:
						UtilText.nodeContentSB.append("You let out a deep groan as you feel your [pc.cock+] twitch and throb as you prepare to cum.");
						break;
				}
				
				// Describe cum amount:
				UtilText.nodeContentSB.append(" As your "+Main.game.getPlayer().getTesticleSize().getDescriptor()+" balls tense up");
				switch (Main.game.getPlayer().getPenisCumProduction()) {
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
				if(Main.game.getPlayer().getPenisCumProduction()!=CumProduction.ZERO_NONE){
					if(Main.game.getPlayer().isWearingCondom()) {
						UtilText.nodeContentSB.append("out into the condom that you're wearing.");
						
					} else if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
						UtilText.nodeContentSB.append(" into your [pc.lowClothing(penis)].");
						
					} else {
						UtilText.nodeContentSB.append(" out all over the floor beneath you.");
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("</br></br>"
						+ "A desperate, shuddering heat suddenly crashes up from your [pc.pussy+], and you let out a manic squeal as a blinding wave of pure ecstasy washes over you.");
				
				if (Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) != null) {
					switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)) {
						case FINGER_PARTNER:
							UtilText.nodeContentSB.append(" [npc.Name]'s fingers carry on pumping away at your [pc.pussy+]"
									+", and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding digits.");
							break;
						case FINGER_PLAYER:
							UtilText.nodeContentSB.append(" You curl your fingers up deep inside your [pc.pussy+]"
									+", and, while desperately stroking in a 'come-hither' motion, you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your intruding digits.");
							break;
						case PENIS_PARTNER:
							UtilText.nodeContentSB.append(" Ralph carries on fucking your [pc.pussy+] through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around his meaty equine shaft."
									+ " Encouraged by the squeezing, milking sensation of your vagina, Ralph buries his thick black horse-cock deep into your [pc.pussy+] and leans down heavily on top of you."
									+ " You feel the heat of his strong, masculine body pressing down on your back, and you let out a defeated moan as his equine shaft claims you as his most recent sexual conquest.");
							break;
						case PENIS_PLAYER:
							UtilText.nodeContentSB.append(" You carry on fucking yourself through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around your [pc.cock+].");
							break;
						case TAIL_PARTNER:
							UtilText.nodeContentSB.append(" [npc.Name]'s tail carries on fucking your [pc.pussy+]"
									+" through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
							break;
						case TAIL_PLAYER:
							UtilText.nodeContentSB.append(" You carry on using your tail to fuck yourself through your orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding object.");
							break;
						case TONGUE_PARTNER:
							UtilText.nodeContentSB.append(" [npc.Name] carries on eating you out, licking and kissing at your [pc.pussy+]"
									+" while you orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding muscle.");
							break;
						case TONGUE_PLAYER:
							UtilText.nodeContentSB.append(" You carry on thrusting your tongue deep into your [pc.pussy+]"
									+" as you orgasm, and you let out a series of high-pitched moans as your vaginal muscles grip and squeeze around the intruding muscle.");
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
				UtilText.nodeContentSB.append("</br></br>"
									+ "Despite your lack of sexual organs, you feel a desperate warmth spreading quickly through your lower abdomen, and before you know what's happening,"
									+ " the feeling has concentrated itself in your groin, and you're suddenly hit by a blinding orgasm."
									+ " Your legs clench together and you let out a desperate cry as the climax deep within your horny doll-like mound takes you by surprise."
									+ " A wave of hot pleasure radiates up throughout your whole body, and you shudder and quiver as the overwhelming wave of ecstasy washes through you.");
			}
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("</br></br>"
						+ "As you start to come down from your climax, you hear Ralph offering a part of your discount to the customer who just overheard your not-so-quiet orgasm"
						+ ", and you let out a defeated sigh as you realise that you forgot they were there.");
			}
			
			return UtilText.genderParsing(Sex.getPartner(),
					UtilText.nodeContentSB.toString());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			
			if(SexFlags.customerAtCounter) {
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			}
			
			SexFlags.customerAtCounter=false;
		}
	};
	
	public static SexAction PARTNER_ORGASM = new SexAction(
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
			return SexActionPriority.HIGH;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null){
				UtilText.nodeContentSB.append("Ralph starts letting out a series of low, heavy grunts, and you gasp as you feel his member start to twitch inside of you."
						+ " With a roar, he slams his massive horse-cock deep into your cunt, and you let out a squeal as you feel his powerful shaft ride up inside of you as it prepares for its master's orgasm."
						+ "</br>"
						+ " Grinding the base of his cock against your outer folds, his massive balls tense up as Ralph starts to cum.");
				
				if(Sex.getPartner().isWearingCondom()) {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into the condom, and as you feel the hot seed ballooning out the rubber inside of you, you let out a satisfied sigh."
							+ " His cock continues to pump a few more times, the huge, black equine shaft quickly depositing its seed harmlessly into the protection you've provided."
							+ "</br></br>"
							+ "As Ralph's balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used pussy."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you roll over onto your back and playfully wrap your legs around Ralph,"
							+ " pulling him forwards and rubbing your pussy against his now-flaccid equine cock as he finishes tying up the used condom."
							+ " He places the little seed-filled package down next to you, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction, "
							+ UtilText.parseSpeech("Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...", Main.game.getRalph()));
				} else {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load"
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?", and as you feel the hot cum filling you up, you let out a satisfied moan."
								:" directly into your womb, and as you feel the hot cum filling you up, you let out a satisfied moan.")
							+ " His cock continues to pump a few more times, the huge, black equine shaft making sure to deposit its seed deep into your hungry cunt."
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?" Once your pussy is completely saturated with sticky white horse-jizz, you feel Ralph's strong grip on your hips start to relax as he finishes his orgasm."
								:" Once your womb is completely saturated with sticky white horse-jizz, you feel Ralph's strong grip on your hips start to relax as he finishes his orgasm.")
							+ "</br></br>"
							+ "As Ralph's balls finish emptying themselves, he steps back, and with a wet sucking sound, slips his rapidly-softening member out of your well-used slit."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you feel a little trickle of his warm, wet seed escaping from your entrance."
							+ " Rolling over onto your back, you playfully wrap your legs around Ralph, pulling him forwards and rubbing your cum-filled pussy against his now-flaccid equine cock."
							+ " It seems as though he's not up for round two, however, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction, "
							+ UtilText.parseSpeech("Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...", Main.game.getRalph()));
				}
				
			}else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null){
				UtilText.nodeContentSB.append("Ralph starts letting out a series of low, heavy grunts, and you gasp as you feel his member start to twitch inside of you."
						+ " With a roar, he slams his massive horse-cock deep into your ass, and you let out a cry as you feel his powerful shaft ride up inside of you as it prepares for its master's orgasm."
						+ "</br>"
						+ " Grinding the base of his cock against your asshole, his massive balls tense up as Ralph starts to cum.");
				 
				if(Sex.getPartner().isWearingCondom()) {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into the condom, and as you feel the hot seed ballooning out the rubber inside of you, you let out a satisfied sigh."
							+ " His cock continues to pump a few more times, the huge, black equine shaft quickly depositing its seed harmlessly into the protection you've provided."
							+ "</br></br>"
							+ "As Ralph's balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used back door."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you roll over onto your back and playfully wrap your legs around Ralph,"
							+ " pulling him forwards and rubbing your groin against his now-flaccid equine cock as he finishes tying up the used condom."
							+ " He places the little seed-filled package down next to you, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction, "
							+ UtilText.parseSpeech("Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...", Main.game.getRalph()));
				} else {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into your ass, and as you feel the hot seed filling you up, you let out a satisfied groan."
							+ " His cock continues to pump a few more times, the huge, black equine shaft making sure to deposit its seed deep into your hungry back door."
							+ " Once your rear entrance is completely saturated with sticky white horse-jizz, you feel Ralph's strong grip on your hips start to relax as he finishes his orgasm."
							+ "</br></br>"
							+ "As Ralph's balls finish emptying themselves, he steps back, and with a wet sucking sound, slips his rapidly-softening member out of your well-used fuck hole."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you feel a little trickle of his warm, wet seed escaping from your rear entrance."
							+ " Rolling over onto your back, you playfully wrap your legs around Ralph, pulling him forwards and rubbing your cum-filled asshole against his now-flaccid equine cock."
							+ " It seems as though he's not up for round two, however, and you pout at him as he takes hold of your legs and disentangles himself, laughing as he sees your reaction, "
							+ UtilText.parseSpeech("Hey! I've got a shop to run here, remember? Anyway, I think you've more than earned that discount...", Main.game.getRalph()));
				}
				
			} else {
				if(SexFlags.customerAtCounter){
					if(SexFlags.alertedCustomer)
						UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, who's still chuckling at the knowledge of what's going on under the counter."
								+ " Ralph just seems to want to get rid of them as soon as possible, and forgets to knock off the portion of your discount he just gave away!"
								+ " The reason for his oversight is soon made clear..."
								+ "</br></br>"
								+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD + ";'>no customers</b> <b>near the counter.</b>"
										+ "</br></br>");
					else
						UtilText.nodeContentSB.append("Ralph quickly finishes up with the customer, and as you hear them start to walk away, you realise that by staying quiet, they didn't find out that you were here!"
								+ "</br></br>"
								+ "<b>There are now</b> <b style='color:" + Colour.GENERIC_GOOD + ";'>no customers</b> <b>near the counter.</b>"
										+ "</br></br>");
				}
				
				UtilText.nodeContentSB.append("Ralph starts letting out a series of low, heavy grunts, and you gasp as you feel his member start to twitch in your mouth."
						+ " With a suppressed groan, he slams his massive horse-cock deep down your throat, and, squirming about under the counter, you feel his powerful shaft trying to ride up as it prepares for its master's orgasm."
						+ "</br></br>"
						+ "Grinding the base of his cock against your lips, his massive balls tense up as Ralph starts to cum.");
				
				if(Sex.getPartner().isWearingCondom()) {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into the condom, and as you feel the hot seed ballooning out the tip of the protection you've provided,"
								+ " you let out a very muffled moan."
							+ " The huge, black equine shaft continues to pump and twitch a few more times as it deposits its seed harmlessly into the protection you've provided."
							+ "</br></br>"
							+ "As Ralph's balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used throat."
							+ " You let out a little sigh as you're suddenly left feeling extremely empty, and you shuffle forwards and playfully bite your lip at Ralph, watching as he finishes tying up the used condom."
							+ " He places the little seed-filled package down next to you, and, after making sure that nobody's watching, he takes hold of one of your [pc.arms+] and pulls you to your feet,"
							+ " motioning for you to continue shopping as he fulfils his end of the bargain, "
							+ UtilText.parseSpeech("Well, I think you've more than earned that discount...", Main.game.getRalph()));
				} else {
					UtilText.nodeContentSB.append(" With a powerful rocking motion, his gigantic dick spurts out its potent load directly into your stomach, and as you feel the hot seed filling you up, you let out a very muffled moan."
							+ " The huge, black equine shaft continues to pump and twitch a few more times as it deposits its seed deep down your facial fuck hole."
							+ " Only once your stomach is completely filled with sticky white horse-jizz, does Ralph finally come to the end of his orgasm."
							+ "</br></br>"
							+ "As his balls finish emptying themselves, he steps back, sliding his rapidly-softening member from your well-used throat."
							+ " A thick, slimy strand of cummy saliva drools down to form a little stream beneath your mouth, and you shuffle forwards, wiping yourself clean as you playfully smile up at Ralph."
							+ " After making sure that nobody's watching, he takes hold of one of your [pc.arms+] and pulls you up, motioning for you to continue shopping as he fulfils his end of the bargain, "
							+ UtilText.parseSpeech("Well, I think you've more than earned that discount...", Main.game.getRalph()));
				}
			}
			
			
			
			if(((Ralph)Main.game.getRalph()).isDiscountActive() && (SexFlags.ralphDiscount<Main.game.getDialogueFlags().ralphDiscount)){
				UtilText.nodeContentSB.append(
						"</br></br>"
						+ "<b style='color:" + Colour.TEXT_GREY + ";'>You have earned a "+SexFlags.ralphDiscount+"% discount for the next three days!</b>"
						+ "</br></br>"
						+ "<b>Because Ralph was already giving you the bigger discount of </b> <b style='color:" + Colour.GENERIC_GOOD + ";'>"
							+Main.game.getDialogueFlags().ralphDiscount+"%</b><b>, he instead agrees to refresh that for the next three days!</b>");
				
			} else {
				UtilText.nodeContentSB.append("</br></br><b>You have earned a</b> <b style='color:" + Colour.GENERIC_GOOD + ";'>"+SexFlags.ralphDiscount+"%</b> <b>discount for the next three days!</b>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			// If your existing discount is bigger, just refresh the bigger discount:
			if(((Ralph)Main.game.getRalph()).isDiscountActive()){
				if(SexFlags.ralphDiscount>Main.game.getDialogueFlags().ralphDiscount){
					Main.game.getRalph().setSellModifier(1.5f*((100-SexFlags.ralphDiscount)/100f));
					Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
				}
			}else{
				Main.game.getRalph().setSellModifier(1.5f*((100-SexFlags.ralphDiscount)/100f));
				Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
			}
			
			Main.game.getDialogueFlags().ralphDiscountStartTime=Main.game.getMinutesPassed();
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
			return true;
		}
	};
	
	public static SexAction PLAYER_MUTUAL_ORGASM = new SexAction(
			SexActionType.MUTUAL_ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			null) {
		@Override
		public String getActionTitle() {
			return "Mutual orgasm";
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
			return(PLAYER_ORGASM.getDescription()
					+"</br></br>"
					+PARTNER_ORGASM.getDescription());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS) && Main.game.getPlayer().getPenisType()!=PenisType.NONE && !Main.game.getPlayer().isWearingCondom())
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
			
			if(SexFlags.customerAtCounter) {
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			}
			
			SexFlags.customerAtCounter=false;
			
			// If your existing discount is bigger, just refresh the bigger discount:
			if(((Ralph)Main.game.getRalph()).isDiscountActive()){
				if(SexFlags.ralphDiscount>Main.game.getDialogueFlags().ralphDiscount){
					Main.game.getRalph().setSellModifier(1.5f*((100-SexFlags.ralphDiscount)/100f));
					Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
				}
			}else{
				Main.game.getRalph().setSellModifier(1.5f*((100-SexFlags.ralphDiscount)/100f));
				Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
			}
			
			Main.game.getDialogueFlags().ralphDiscountStartTime=Main.game.getMinutesPassed();
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
			return true;
		}
	};
}
