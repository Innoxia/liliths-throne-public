package com.lilithsthrone.game.sex.sexActions.submission;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class SAMurkSpecials {

	/**
	 * Special action for Murk preparing for the player's orgasm.
	 */
	public static final SexAction GENERIC_PREPARATION_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getCharacterOrgasming()!=null
					&& Main.sex.getCharacterOrgasming().isPlayer()
					&& Main.game.getPlayer().isCaptive();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public String getActionTitle() {
			return "Encourage milker";
		}
		@Override
		public String getActionDescription() {
			return "You can feel that [npc2.name] is fast approaching [npc2.her] orgasm. Encourage [npc2.herHim] to cum for you.";
		}
		@Override
		public String getDescription() {
			if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.MOUTH)) {
				return UtilText.returnStringAtRandom(
						"Sensing that you're about to orgasm from having your face fucked by his fat, throbbing cock, Murk roughly slams his dark shaft fully down your throat and grunts,",
						"Realising that you're about to cum just from giving him a blowjob, Murk picks up the pace and violently pounds his hot cock deep down your throat as he grunts,",
						"Knowing that you're about to reach your climax, Murk continues relentlessly fucking your throat as he grunts,")
						+ " [murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"That's it, slut! ~Mmm!~ Cum from the feelin' of me fat, stinkin' cock fuckin' yer throat!",
								"Go on, ya 'orny slut! ~Aah!~ Let the taste o' me fat, stinkin' cock give yer an' orgasm!",
								"You's gonna cum just from suckin' me fat, stinkin' cock?! ~Mmm!~ Go on then, slut! Cum fer me!")
						+")]";
				
			} else if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.VAGINA)) {
				return UtilText.returnStringAtRandom(
						"Feeling your [pc.pussy+] start to uncontrollably squeeze down around his fat, throbbing cock, Murk roughly slams his dark shaft fully into your drooling hole and grunts,",
						"Realising from your cunt's wild spasms that you're about to cum, Murk picks up the pace and violently pounds his hot cock deep into your [pc.pussy+] as he grunts,",
						"As your [pc.pussy+] starts to wildly grip and squeeze down around Murk's fat, [murk.cockColour] cock, the chubby rat-boy realises that you're about to reach your climax and deeply grunts,")
						+ " [murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"That's it, slut! ~Mmm!~ Cum from the feelin' of me fat, stinkin' cock makin' love ta yer ugly cunt!",
								"Go on, ya 'orny slut! ~Aah!~ Let the feel o' me fat, stinkin' cock give yer ugly cunt an orgasm!",
								"Yer ugly cunt's gonna cum from havin' me fat, stinkin' cock pound it?! ~Mmm!~ Go on then, slut! Cum fer me!")
						+")]";
				
			} else if(Main.sex.getOrificesBeingPenetratedBy(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, Main.sex.getCharacterOrgasming()).contains(SexAreaOrifice.ANUS)) {
				return UtilText.returnStringAtRandom(
						"Feeling your [pc.asshole+] start to uncontrollably squeeze down around his fat, throbbing cock, Murk roughly slams his dark shaft fully into your wet hole and grunts,",
						"Realising from your asshole's wild spasms that you're about to cum, Murk picks up the pace and violently pounds his hot cock deep into your [pc.asshole+] as he grunts,",
						"As your [pc.asshole+] starts to wildly grip and squeeze down around Murk's fat, [murk.cockColour] cock, the chubby rat-boy realises that you're about to reach your climax and deeply grunts,")
						+ " [murk.speechNoEffects("
						+ UtilText.returnStringAtRandom(
								"That's it, slut! ~Mmm!~ Cum from the feelin' of me fat, stinkin' cock makin' love ta yer ass!",
								"Go on, ya 'orny butt-slut! ~Aah!~ Let the feel o' me fat, stinkin' cock give ya an' orgasm!",
								"Ya gonna cum from havin' me fat, stinkin' cock pound yer ass?! ~Mmm!~ Go on then, butt-slut! Cum fer me!")
						+")]";
				
			} else {
				return "As Murk realises that you're about to reach your climax, he roughly grinds himself against you and sneers, [murk.speechNoEffects(Come on then, slut! Fuckin' cum fer me!)]"; //This should never be reached.
			}
		}
	};
}
