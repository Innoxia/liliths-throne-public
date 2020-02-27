package com.lilithsthrone.game.sex.positions.slots;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * All SexSlots that are used in the ALL_FOURS position.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Innoxia
 */
public class SexSlotAllFours {

	public static final SexSlot ALL_FOURS = new SexSlot(
			"Down on all fours",
			"all fours",
			"With trembling [npc.legs], [npc.name] [npc.do] [npc.her] best to steady [npc.herself], and with [npc.a_moan+], [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false,
			SexSlotTag.ALL_FOURS);
	public static final SexSlot ALL_FOURS_TWO = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "all fours (2nd)";
		}
	};
	public static final SexSlot ALL_FOURS_THREE = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "all fours (3rd)";
		}
	};
	public static final SexSlot ALL_FOURS_FOUR = new SexSlot(ALL_FOURS){
		@Override
		public String getDescription() {
			return "all fours (4th)";
		}
	};
	

	public static final SexSlot BEHIND = new SexSlot(
			"Mounting/Fucking",
			"behind",
			"[npc.Name] [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_TWO = new SexSlot(
			"Mounting/Fucking",
			"behind (2nd)",
			"[npc.Name] [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_THREE = new SexSlot(
			"Mounting/Fucking",
			"behind (3rd)",
			"[npc.Name] [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	public static final SexSlot BEHIND_FOUR = new SexSlot(
			"Mounting/Fucking",
			"behind (4th)",
			"[npc.Name] [npc.verb(press)] [npc.her] weight down on top of [npc2.namePos] body, letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true,
			SexSlotTag.BEHIND_ALL_FOURS) {
		@Override
		public String getName(GameCharacter target) {
			boolean standing = isStanding(target);
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (target.getLegConfiguration()==LegConfiguration.TAUR
						?"Mounting"
						:standing?"Standing behind":"Kneeling behind")
					+" "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			return partner!=null && (partner.isSizeDifferenceTallerThan(target) || partner.isTaur());
		}
	};
	
	public static final SexSlot HUMPING = new SexSlot(
			"Humping",
			"humping",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] against [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Humping "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot HUMPING_TWO = new SexSlot(
			"Humping",
			"humping (2nd)",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] against [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Humping "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot HUMPING_THREE = new SexSlot(
			"Humping",
			"humping (3rd)",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] against [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Humping "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot HUMPING_FOUR = new SexSlot(
			"Humping",
			"humping (4th)",
			"[npc.Name] wildly [npc.verb(buck)] [npc.her] [npc.hips] against [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Humping "+UtilText.parse(partner, "[npc.name]");
		}
	};
	
	
	public static final SexSlot BEHIND_ORAL = new SexSlot(
			"Performing oral",
			"performing oral",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Performing oral on "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot BEHIND_ORAL_TWO = new SexSlot(
			"Performing oral",
			"performing oral (2nd)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Performing oral on "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot BEHIND_ORAL_THREE = new SexSlot(
			"Performing oral",
			"performing oral (3rd)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Performing oral on "+UtilText.parse(partner, "[npc.name]");
		}
	};
	public static final SexSlot BEHIND_ORAL_FOUR = new SexSlot(
			"Performing oral",
			"performing oral (4th)",
			"[npc.Name] [npc.verb(reach)] up and [npc.verb(place)] a [npc.hand] on one of [npc2.namePos] [npc2.legs], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Performing oral on "+UtilText.parse(partner, "[npc.name]");
		}
	};

	public static final SexSlot USING_FEET = new SexSlot(
			"Using feet",
			"using feet",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Using "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_TWO = new SexSlot(
			"Using feet",
			"using feet (2nd)",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Using "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_THREE = new SexSlot(
			"Using feet",
			"using feet (3rd)",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Using "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};
	public static final SexSlot USING_FEET_FOUR = new SexSlot(
			"Using feet",
			"using feet (4th)",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			false) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return "Using "+UtilText.parse(partner, "[npc.namePos] [npc.feet]");
		}
	};

	
	public static final SexSlot IN_FRONT = new SexSlot(
			"Standing/kneeling",
			"in front",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_TWO = new SexSlot(
			"Standing/kneeling",
			"in front (2nd)",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_THREE = new SexSlot(
			"Standing/kneeling",
			"in front (3rd)",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_FOUR = new SexSlot(
			"Standing/kneeling",
			"in front (4th)",
			"[npc.Name] [npc.verb(shuffle)] forwards in order to get closer to [npc2.name], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return (this.isStanding(target)?"Standing":"Kneeling")+" before "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {partner = getCharacterInSlot(ALL_FOURS);}
			return partner.isSizeDifferenceTallerThan(target);
		}
	};


	public static final SexSlot IN_FRONT_ANAL = new SexSlot(
			"Standing/kneeling",
			"in front (anal)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return "Presenting ass to "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_TWO = new SexSlot(
			"Standing/kneeling",
			"in front (2nd anal)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "Presenting ass to "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_TWO);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_THREE = new SexSlot(
			"Standing/kneeling",
			"in front (3rd anal)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "Presenting ass to "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_THREE);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
	public static final SexSlot IN_FRONT_ANAL_FOUR = new SexSlot(
			"Standing/kneeling",
			"in front (4th anal)",
			"[npc.Name] [npc.verb(push)] [npc.her] [npc.hips+] back into [npc2.namePos] [npc2.face], before letting out [npc.a_moan+] as [npc.she] [npc.verb(prepare)] to reach [npc.her] climax.",
			true) {
		@Override
		public String getName(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			if(partner==null) {
				partner = getCharacterInSlot(ALL_FOURS);
			}
			return "Presenting ass to "+UtilText.parse(partner, "[npc.name]");
		}
		@Override
		public boolean isStanding(GameCharacter target) {
			GameCharacter partner = getCharacterInSlot(ALL_FOURS_FOUR);
			return partner.isSizeDifferenceTallerThan(target);
		}
	};
}
