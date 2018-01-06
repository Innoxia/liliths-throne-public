package com.lilithsthrone.game.sex.sexActions.orgasms;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;

/**
 * Orgasms for the generic position of two characters standing face-to-face.
 * 
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class OrgasmPositionBackToWall {
	
	private static StringBuilder orgasmSB = new StringBuilder();
	
	@SuppressWarnings("unused") //TODO
	private static String getPenetrationOrgasmDescriptions(boolean playerOrgasm, boolean partnerOrgasm) {
		orgasmSB.setLength(0);
		
		// Start:
		if(Sex.getPosition()==SexPosition.BACK_TO_WALL_PARTNER) {
			if(partnerOrgasm) {
				orgasmSB.append("<p>");
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						orgasmSB.append("[npc.Name] reaches up and slowly wraps [npc.her] [npc.arms] around you, leaning back against the wall as [npc.she] gently pulls you forwards and lets out a soft [npc.moan],"
								+ " [npc.speech(Yes... I'm going to cum!)]");
						break;
					case DOM_NORMAL:
						orgasmSB.append("[npc.Name] suddenly reaches up and wraps [npc.her] [npc.arms] around you, leaning back against the wall as [npc.she] frantically pulls you forwards and lets out [npc.a_moan+],"
								+ " [npc.speech(Yes! I'm going to cum!)]");
						break;
					case DOM_ROUGH:
						orgasmSB.append("[npc.Name] suddenly reaches up and wraps [npc.her] [npc.arms] around you, leaning back against the wall as [npc.she] roughly pulls you forwards and lets out [npc.a_moan+],"
								+ " [npc.speech(Fuck! I'm going to cum!)]");
						break;
					case SUB_EAGER:
						orgasmSB.append("[npc.Name] suddenly reaches up and wraps [npc.her] [npc.arms] around you, leaning back against the wall as [npc.she] frantically pulls you forwards and lets out [npc.a_moan+],"
								+ " [npc.speech(Yes! I'm going to cum!)]");
						break;
					case SUB_NORMAL:
						orgasmSB.append("[npc.Name] suddenly reaches up and wraps [npc.her] [npc.arms] around you, leaning back against the wall as [npc.she] pulls you forwards and lets out [npc.a_moan+],"
								+ " [npc.speech(Yes! I'm going to cum!)]");
						break;
					case SUB_RESISTING:
						orgasmSB.append("[npc.Name] suddenly collapses back against the wall, breathing heavily as [npc.she] desperately tries to push you away, [npc.sobbing],"
								+ " [npc.speech(N-No! I-I don't want to cum from this!)]");
						break;
				}
				orgasmSB.append("/<p>");
			}
			if(playerOrgasm) {
				orgasmSB.append("<p>");
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						orgasmSB.append("You step forwards, gently pressing yourself up against [npc.name]'s body as you softly [pc.moanVerb] into [npc.her] [npc.ear],"
								+ " [pc.speech(I'm cumming!)]");
						break;
					case DOM_NORMAL:
						orgasmSB.append("You step forwards, desperately pressing yourself up against [npc.name]'s body as you [pc.moanVerb+] into [npc.her] [npc.ear],"
								+ " [pc.speech(I'm cumming!)]");
						break;
					case DOM_ROUGH:
						orgasmSB.append("You step forwards, dominantly grinding yourself up against [npc.name]'s body as you [pc.moanVerb+] into [npc.her] [npc.ear],"
								+ " [pc.speech(I'm cumming! Fuck!)]");
						break;
					case SUB_EAGER:
						orgasmSB.append("You step forwards, desperately pressing yourself up against [npc.name]'s body as you [pc.moanVerb+] into [npc.her] [npc.ear],"
								+ " [pc.speech(I'm cumming!)]");
						break;
					case SUB_NORMAL:
						orgasmSB.append("You step forwards, pressing yourself up against [npc.name]'s body as you [pc.moanVerb+] into [npc.her] [npc.ear],"
								+ " [pc.speech(I'm cumming!)]");
						break;
					case SUB_RESISTING:
						orgasmSB.append("You try to pull back, the overwhelming, burning arousal between your [pc.legs] bringing you to the brink of your orgasm as you [pc.sobVerb+],"
								+ " [pc.speech(N-No! I-I don't want to cum from this!)]");
						break;
				}
				orgasmSB.append("</p>");
			}
			
		} else {
			if(partnerOrgasm) {
				orgasmSB.append("<p>");
				switch(Sex.getSexPacePlayer()) {
					case DOM_GENTLE:
						orgasmSB.append("You reach up and slowly wrap your [pc.arms] around [npc.name], leaning back against the wall as you gently pull [npc.herHim] forwards and let out a soft [pc.moan],"
								+ " [pc.speech(Yes... I'm going to cum!)]");
						break;
					case DOM_NORMAL:
						orgasmSB.append("You reach up and wrap your [pc.arms] around [npc.name], leaning back against the wall as you frantically pull [npc.herHim] forwards and let out [pc.a_moan+],"
								+ " [pc.speech(Yes! I'm going to cum!)]");
						break;
					case DOM_ROUGH:
						orgasmSB.append("You reach up and wrap your [pc.arms] around [npc.name], leaning back against the wall as you roughly pull [npc.herHim] forwards and let out [pc.a_moan+],"
								+ " [pc.speech(Fuck! I'm going to cum!)]");
						break;
					case SUB_EAGER:
						orgasmSB.append("You reach up and wrap your [pc.arms] around [npc.name], leaning back against the wall as you frantically pull [npc.herHim] forwards and let out [pc.a_moan+],"
								+ " [pc.speech(Yes! I'm going to cum!)]");
						break;
					case SUB_NORMAL:
						orgasmSB.append("You reach up and wrap your [pc.arms] around [npc.name], leaning back against the wall as you pull [npc.herHim] forwards and let out [pc.a_moan+],"
								+ " [pc.speech(Yes! I'm going to cum!)]");
						break;
					case SUB_RESISTING:
						orgasmSB.append("The overwhelming, burning arousal between your [pc.legs] brings you to the brink of your orgasm as you collapse back against the wall, breathing heavily as you desperately try to push [npc.name] away, [pc.sobbing],"
								+ " [pc.speech(N-No! I-I don't want to cum from this!)]");
						break;
				}
				orgasmSB.append("/<p>");
			}
			if(playerOrgasm) {
				orgasmSB.append("<p>");
				switch(Sex.getSexPacePartner()) {
					case DOM_GENTLE:
						orgasmSB.append("[npc.Name] steps forwards, gently pressing [npc.herself] up against your body as [npc.she] softly [npc.moansVerb] into your [pc.ear],"
								+ " [npc.speech(I'm cumming!)]");
						break;
					case DOM_NORMAL:
						orgasmSB.append("[npc.Name] steps forwards, desperately pressing [npc.herself] up against your body as [npc.she] [npc.moansVerb+] into your [pc.ear],"
								+ " [npc.speech(I'm cumming!)]");
						break;
					case DOM_ROUGH:
						orgasmSB.append("[npc.Name] steps forwards, dominantly grinding [npc.herself] up against your body as [npc.she] [npc.moansVerb+] into your [pc.ear],"
								+ " [npc.speech(I'm cumming! Fuck!)]");
						break;
					case SUB_EAGER:
						orgasmSB.append("[npc.Name] steps forwards, desperately pressing [npc.herself] up against your body as [npc.she] [npc.moansVerb+] into your [pc.ear],"
								+ " [npc.speech(I'm cumming!)]");
						break;
					case SUB_NORMAL:
						orgasmSB.append("[npc.Name] steps forwards, pressing [npc.herself] up against your body as [npc.she] [npc.moansVerb+] into your [pc.ear],"
								+ " [npc.speech(I'm cumming!)]");
						break;
					case SUB_RESISTING:
						orgasmSB.append("[npc.Name] tries to pull back, the overwhelming, burning arousal between [npc.her] [npc.legs] bringing [npc.herHim] to the brink of [npc.her] orgasm as [npc.she] [pc.sobsVerb+],"
								+ " [npc.speech(N-No! I-I don't want to cum from this!)]");
						break;
				}
				orgasmSB.append("</p>");
			}
		}
		
		for(PenetrationType penetrationType : PenetrationType.values()) {
			if(Sex.getOngoingPenetrationMap().get(penetrationType)!=null && !Sex.getOngoingPenetrationMap().get(penetrationType).isEmpty()) {
				for(OrificeType orifice : Sex.getOngoingPenetrationMap().get(penetrationType)) {
					switch(penetrationType) {
						case FINGER_PARTNER:
							switch(orifice) {
								case ANUS_PARTNER:
									break;
								case ANUS_PLAYER:
									break;
								case MOUTH_PARTNER:
									break;
								case MOUTH_PLAYER:
									break;
								case NIPPLE_PARTNER:
									break;
								case NIPPLE_PLAYER:
									break;
								case VAGINA_PARTNER:
									break;
								case VAGINA_PLAYER:
									break;
								case URETHRA_PARTNER: case URETHRA_PLAYER:
									// TODO
									break;
								case ASS_PARTNER: case ASS_PLAYER: case BREAST_PARTNER: case BREAST_PLAYER: case THIGHS_PARTNER: case THIGHS_PLAYER:
									// Not needed
									break;
							}
							break;
						case FINGER_PLAYER:
							break;
						case PENIS_PARTNER:
							break;
						case PENIS_PLAYER:
							break;
						case TAIL_PARTNER:
							break;
						case TAIL_PLAYER:
							break;
						case TENTACLE_PARTNER:
							break;
						case TENTACLE_PLAYER:
							break;
						case TONGUE_PARTNER:
							break;
						case TONGUE_PLAYER:
							break;
					}
				}
			}
			
			
		}
		
		return orgasmSB.toString();
	}
	
	public static final SexAction STANDING_PLAYER_PREPARE = new SexAction(
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
			
			return !takingCock && Sex.getSexPacePlayer()!=SexPace.SUB_RESISTING;
		}
		
		@Override
		public String getActionTitle() {
			return "Encourage Orgasm";
		}

		@Override
		public String getActionDescription() {
			return "You can feel that [npc.name] is fast approaching [npc.her] orgasm. Encourage [npc.herHim] to climax.";
		}
		
		@Override
		public String getDescription() {
			switch(Sex.getSexPacePlayer()) {
				case DOM_GENTLE:
					return "Realising that [npc.name] is about to reach [npc.her] orgasm, you gently taking hold of [npc.her] [npc.hips], before pulling [npc.herHim] up against you and softly [pc.moaning], [pc.speech(Go on, cum for me!)]";
				case DOM_NORMAL:
					return "Realising that [npc.name] is about to reach [npc.her] orgasm, you eagerly grip [npc.her] [npc.hips+] and pull [npc.herHim] up against you, [pc.moaning+] into [npc.her] [npc.ear], [pc.speech(Yes! Cum for me!)]";
				case DOM_ROUGH:
					return "Realising that [npc.name] is about to reach [npc.her] orgasm, you roughly grip [npc.her] [npc.hips+] and force [npc.herHim] up against you, [pc.moaning+] into [npc.her] [npc.ear], [pc.speech(Go on bitch! I'll let you cum!)]";
				case SUB_EAGER:
					return "Realising that [npc.name] is about to reach [npc.her] orgasm, you eagerly grip [npc.her] [npc.hips+] and grind up against [npc.herHim], [pc.moaning+] into [npc.her] [npc.ear], [pc.speech(Yes! Go on! Cum already!)]";
				case SUB_NORMAL:
					return "Realising that [npc.name] is about to reach [npc.her] orgasm, you rest your [pc.hands] on [npc.her] [npc.hips+] and press up against [npc.herHim], [pc.moaning+] into [npc.her] [npc.ear], [pc.speech(Yes! Cum for me!)]";
				case SUB_RESISTING:
					return "";
			}
			return "";
		}
	};
	
}
