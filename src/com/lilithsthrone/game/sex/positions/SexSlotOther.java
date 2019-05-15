package com.lilithsthrone.game.sex.positions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class SexSlotOther {
	
	/* STANDING */
	
	public static final SexSlot STANDING_DOMINANT = new SexSlot(
			"Standing",
			"standing (dominant)",
			"[npc.Name] [npc.verb(reach)] around and [npc.verb(wrap)] [npc.her] [npc.arms] around [npc2.namePos] back, pulling [npc2.herHim] close and letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true);
	
	public static final SexSlot STANDING_DOMINANT_TWO = new SexSlot(STANDING_DOMINANT);

	public static final SexSlot STANDING_SUBMISSIVE = new SexSlot(
			"Standing",
			"standing (submissive)",
			"[npc.Name] [npc.verb(lean)] heavily into [npc2.namePos] [npc2.breasts] and [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true);

	public static final SexSlot STANDING_SUBMISSIVE_TWO = new SexSlot(STANDING_SUBMISSIVE);
	
	
	/* ORAL */
	
	public static final SexSlot RECEIVING_ORAL = new SexSlot(
			"Standing",
			"receiving oral",
			"With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			true);
	
	public static final SexSlot RECEIVING_ORAL_TWO = new SexSlot(
			"Standing",
			"receiving oral",
			"With a small thrust of [npc.her] [npc.hips], [npc.name] [npc.verb(push)] [npc.her] groin into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(reach)] [npc.her] climax.",
			true);

	public static final SexSlot PERFORMING_ORAL = new SexSlot(
			"Kneeling",
			"performing oral",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = Sex.getTargetedPartner(target);
			return (this.isStanding(target)?"Standing":"Kneeling")+(!partner.getLegConfiguration().isBipedalPositionedGenitals()?" beneath ":" before ")+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_TWO = new SexSlot(
			"Kneeling",
			"performing oral (second)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = Sex.getTargetedPartner(target);
			return (this.isStanding(target)?"Standing":"Kneeling")+(!partner.getLegConfiguration().isBipedalPositionedGenitals()?" beneath ":" before ")+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND = new SexSlot(
			"Kneeling behind",
			"performing oral from behind",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] rear [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return (this.isStanding(target)?"Standing":"Kneeling")+" behind "+UtilText.parse(Sex.getTargetedPartner(target), "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot PERFORMING_ORAL_BEHIND_TWO = new SexSlot(
			"Kneeling behind",
			"performing oral from behind (second)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] rear [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			return (this.isStanding(target)?"Standing":"Kneeling")+" behind "+UtilText.parse(Sex.getTargetedPartner(target), "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			return Sex.getTargetedPartner(target).isSizeDifferenceTallerThan(target);
		}
	};
	
	
	/* MOUNTING */
	
	public static final SexSlot ALL_FOURS_FUCKED = new SexSlot(
			"Standing/On all fours",
			"being fucked",
			"With trembling [npc.legs], [npc.name] [npc.do] [npc.her] best to [npc.verb(steady)] [npc2.herself], and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true){
		@Override
		public String getName(GameCharacter target) {
			return isStanding(target)
					?"Standing"
					:(target.getLegConfiguration()==LegConfiguration.TAUR
						?"Lying down"
						:"On all fours");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_MOUNTING);
			GameCharacter oralPartner = Sex.getCharacterInPosition(IN_FRONT_OF_ALL_FOURS_TARGET);
			if(oralPartner!=null || partner==null || target.getLegConfiguration()!=LegConfiguration.TAUR) {
				return false; // If nobody is fucking them, they are not a taur, or if they are performing oral, they kneel.
			}
			
			return target.getLegConfiguration()==LegConfiguration.TAUR && partner.getLegConfiguration()==LegConfiguration.TAUR; // Only standing if they are a taurs (so they can get easily mounted by the taur fucking them)
		}
	};

	public static final SexSlot ALL_FOURS_FUCKED_TWO = new SexSlot(
			"Standing/On all fours",
			"being fucked",
			"With trembling [npc.legs], [npc.name] [npc.do] [npc.her] best to [npc.verb(steady)] [npc2.herself], and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true){
		@Override
		public String getName(GameCharacter target) {
			return isStanding(target)
					?"Standing"
					:(target.getLegConfiguration()==LegConfiguration.TAUR
						?"Lying down"
						:"On all fours");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_MOUNTING);
			GameCharacter partner2 = Sex.getCharacterInPosition(ALL_FOURS_MOUNTING_TWO);
			GameCharacter oralPartner = Sex.getCharacterInPosition(IN_FRONT_OF_ALL_FOURS_TARGET);
			GameCharacter oralPartner2 = Sex.getCharacterInPosition(IN_FRONT_OF_ALL_FOURS_TARGET_TWO);
			if(oralPartner!=null || oralPartner2!=null || (partner==null && partner2==null) || target.getLegConfiguration()!=LegConfiguration.TAUR) {
				return false; // If nobody is fucking them, they are not a taur, or if they are performing oral, they kneel.
			}
			
			 // Only standing if they are a taurs (so they can get easily mounted by the taur fucking them):
			return target.getLegConfiguration()==LegConfiguration.TAUR
					&& ((partner!=null && partner.getLegConfiguration()==LegConfiguration.TAUR) || (partner2!=null && partner2.getLegConfiguration()==LegConfiguration.TAUR));
		}
	};

	public static final SexSlot ALL_FOURS_MOUNTING = new SexSlot(
			"Mounting/Kneeling behind",
			"mounting",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return partner!=null && (ALL_FOURS_FUCKED.isStanding(partner) || partner.isSizeDifferenceTallerThan(target));
		}
	};

	public static final SexSlot ALL_FOURS_MOUNTING_TWO = new SexSlot(
			"Mounting/Fucking",
			"mounting (second)",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] and [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			if(partner==null) {
				partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			}
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			return partner!=null && (ALL_FOURS_FUCKED_TWO.isStanding(partner) || partner.isSizeDifferenceTallerThan(target));
		}
	};

	public static final SexSlot IN_FRONT_OF_ALL_FOURS_TARGET = new SexSlot(
			"Standing/kneeling",
			"in front",
			"[npc.Name] [npc.verb(press)] [npc.herself] in against [npc2.namePos] [npc2.breasts], letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			return (target.getLegConfiguration().isBipedalPositionedGenitals() && partner!=null && !partner.getLegConfiguration().isBipedalPositionedGenitals())
					|| ALL_FOURS_FUCKED.isStanding(partner)
					|| partner.isSizeDifferenceTallerThan(target);
		}
	};

	public static final SexSlot IN_FRONT_OF_ALL_FOURS_TARGET_TWO = new SexSlot(
			"Standing/kneeling",
			"in front",
			"[npc.Name] [npc.verb(press)] [npc.herself] in against [npc2.namePos] [npc2.breasts], letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			if(partner==null) {
				partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED);
			}
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = Sex.getCharacterInPosition(ALL_FOURS_FUCKED_TWO);
			return (target.getLegConfiguration().isBipedalPositionedGenitals() && partner!=null && !partner.getLegConfiguration().isBipedalPositionedGenitals())
					|| ALL_FOURS_FUCKED_TWO.isStanding(partner)
					|| partner.isSizeDifferenceTallerThan(target);
		}
	};
	
}
