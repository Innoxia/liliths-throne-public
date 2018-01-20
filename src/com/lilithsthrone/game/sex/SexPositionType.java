package com.lilithsthrone.game.sex;

import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.97
 * @version 0.1.97
 * @author Innoxia
 */
public enum SexPositionType {
	
	BACK_TO_WALL("Back-to-wall",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.BACK_TO_WALL_AGAINST_WALL))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.BACK_TO_WALL_FACING_TARGET))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return "You're standing with your back to the wall, and in front of you, [npc.name] is eyeing you up with a hungry glint in [npc.her] [npc.eyes+].";
			} else {
				return "You're standing face-to-face with [npc.name] as you push [npc.herHim] back against the wall.";
			}
		}
	},
	
	FACING_WALL("Facing wall",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.FACE_TO_WALL_AGAINST_WALL))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.FACE_TO_WALL_FACING_TARGET))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return "You're standing with your face to the wall, and behind you, [npc.name] is pressing into your back as [npc.she] breathes down your neck.";
			} else {
				return "You're standing behind [npc.name] as you push [npc.herHim] into the wall.";
			}
		}
	},
	
	COWGIRL("Cowgirl",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.COWGIRL_ON_BACK))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.COWGIRL_RIDING))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.COWGIRL_ON_BACK) {
				return "You're lying down on your back as [npc.name] straddles your stomach in the cowgirl position.";
			} else {
				return "You're straddling [npc.name]'s stomach in the cowgirl position.";
			}
		}
	},
	
	DOGGY_STYLE("Doggy-style",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_ON_ALL_FOURS))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_BEHIND), new ListValue<>(SexPositionSlot.DOGGY_BEHIND_ORAL))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_INFRONT), new ListValue<>(SexPositionSlot.DOGGY_INFRONT_ANAL))))) {
		@Override
		public String getDescription() { //TODO
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return "You're down on all fours, presenting yourself to [npc.name], who's kneeling down behind you, eager to take advantage of your submissive position.";
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_BEHIND) {
				return "[npc.Name] is down on all fours, presenting [npc.herself] to you. You're kneeling down behind [npc.herHim], ready to take advantage of [npc.her] submissive position.";
			} else {
				return "[npc.Name] is down on all fours, presenting [npc.herself] to you. You're also down on all fours behind [npc.herHim], ready to perform oral on [npc.herHim].";
			}
		}
	},
	
	SIXTY_NINE("Sixty-nine",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.SIXTY_NINE_TOP))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.SIXTY_NINE_BOTTOM))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_TOP) {
				return "You're on all fours over the top of [npc.name]. Your crotch is positioned over [npc.her] [npc.face+], while you're looking down at [npc.her] groin.";
			} else {
				return "You're lying beneath [npc.name], who's down on all fours over the top of you. [npc.Her] crotch is positioned over your face, while [npc.her] own head is similarly positioned over your groin.";
			}
		}
	},
	
	KNEELING_ORAL("Kneeling",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_RECEIVING_ORAL))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_PERFORMING_ORAL))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_RECEIVING_ORAL) {
				return "[npc.Name] is kneeling on the floor in front of you, with [npc.her] [npc.face+] hovering just inches away from your groin.";
			} else {
				return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
			}
		}
	},
	
	STANDING("Standing",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.STANDING_DOMINANT))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.STANDING_SUBMISSIVE))))) {
		@Override
		public String getDescription() {
			return "You're standing face-to-face with [npc.name].";
		}
	},
	
	CHAIR_SEX("Chair sex",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.CHAIR_TOP))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.CHAIR_BOTTOM))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.CHAIR_TOP) {
				return "[npc.Name] is sitting down in front of you, and as [npc.she] looks up into your [pc.eyes+], [npc.she] flashes you a quick smile.";
			} else {
				return "You're sitting down, looking up at [npc.name] as [npc.she] smiles down at you.";
			}
		}
	},
	
	STOCKS_SEX("Stocks sex",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.STOCKS_LOCKED_IN_STOCKS))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.STOCKS_RECEIVING_ORAL), new ListValue<>(SexPositionSlot.STOCKS_PERFORMING_ORAL),  new ListValue<>(SexPositionSlot.STOCKS_FUCKING))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.STOCKS_RECEIVING_ORAL) {
				return "[npc.Name] is locked into the stocks, ready for public use. You're standing in front of [npc.her] [npc.face], ready to put [npc.her] mouth to good use.";
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.STOCKS_PERFORMING_ORAL) {
				return "[npc.Name] is locked into the stocks, ready for public use. You're kneeling behind [npc.herHim], ready to perform oral on [npc.herHim].";
			} else {
				return "[npc.Name] is locked into the stocks, ready for public use. You're standing behind [npc.herHim], ready to take advantage of [npc.her] compromising position.";
			}
		}
	},
	
	/* UNIQUE */
	
	CHAIR_SEX_LILAYA("Chair sex",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.CHAIR_TOP_LILAYA))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.CHAIR_BOTTOM_LILAYA))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.CHAIR_TOP_LILAYA) {
				return "[npc.Name] is sitting down in front of you, and as [npc.she] looks up into your [pc.eyes+], [npc.she] flashes you a quick smile.";
			} else {
				return "You're sitting down, looking up at [npc.name] as [npc.she] smiles down at you.";
			}
		}
	},
	
	DOGGY_AMBER("Doggy-style",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.DOGGY_BEHIND_AMBER))))) {
		@Override
		public String getDescription() {
			return "You're down on all fours, presenting yourself to [npc.name], who's kneeling down behind you, eager to take advantage of your submissive position.";
		}
	},
	
	KNEELING_ORAL_ZARANIX("Kneeling",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_PERFORMING_ORAL_ZARANIX))))) {
		@Override
		public String getDescription() {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
		}
	},
	
	UNDER_DESK_RALPH("Under desk",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH))))) {
		@Override
		public String getDescription() {
			return "You're kneeling under Ralph's desk, with your face just inches away from his crotch.";
		}
	},
	
	SHOWER_TIME_PIX("Shower sex",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX))))) {
		@Override
		public String getDescription() {
			return "You're standing with your face pressed up against one wall of the shower, and behind you, Pix is growling hungrily into your ear.";
		}
	},
	
	HANDS_ROSE("Hand sex",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.HAND_SEX_DOM_ROSE))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.HAND_SEX_SUB_ROSE))))) {
		@Override
		public String getDescription() {
			return "You're standing in one of the many empty bedrooms in Lilaya's home. Before you, the cat-girl maid, Rose, is displaying her hands for your benefit.";
		}
	},
	
	MISSIONARY_DESK_VICKY("Missionary on counter",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.MISSIONARY_DESK_DOM_VICKY))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.MISSIONARY_DESK_SUB_VICKY))))) {
		@Override
		public String getDescription() {
			return "You're lying back on top of Arcane Arts' front desk, and Vicky's standing between your [pc.legs], growling down at you as she prepares to fuck you in the missionary position.";
		}
	},
	
	KNEELING_ORAL_CULTIST("Kneeling",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST))))) {
		@Override
		public String getDescription() {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
		}
	},
	
	MISSIONARY_ALTAR_CULTIST("Missionary on altar",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS), new ListValue<>(SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS))))) {
		@Override
		public String getDescription() {//TODO
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
				return "You're lying back on top of the chapel's altar, and [npc.name]'s standing between your [pc.legs], ready to have some fun with you in the missionary position.";
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're standing between [npc.her] [npc.legs], ready to have some fun in the missionary position.";
			} else {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're kneeling down between [npc.her] [npc.legs], ready to have some oral fun in the missionary position.";
			}
		}
	},
	
	MISSIONARY_ALTAR_SEALED_CULTIST("Missionary on altar",
			Util.newArrayListOfValues(
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))),
					new ListValue<>(Util.newArrayListOfValues(new ListValue<>(SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS), new ListValue<>(SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS))))) {
		@Override
		public String getDescription() {//TODO
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
				return "You're lying back on top of the chapel's altar, and [npc.name]'s standing between your [pc.legs], ready to have some fun with you in the missionary position.";
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're standing between [npc.her] [npc.legs], ready to have some fun in the missionary position.";
			} else {
				return "[npc.Name] is lying back on top of the chapel's altar, and you're kneeling down between [npc.her] [npc.legs], ready to have some oral fun in the missionary position.";
			}
		}
	},
	
	;
	
	private String name;
	/**Key is role position. Value is list of all slots that this slot can switch to.*/
	private List<List<SexPositionSlot>> availableSlots;
	
	private SexPositionType(String name, List<List<SexPositionSlot>> availableSlots) {
		this.name = name;
		this.availableSlots = availableSlots;
	}
	
	public String getName() {
		return name;
	}

	public List<List<SexPositionSlot>> getAvailableSlots() {
		return availableSlots;
	}

	public abstract String getDescription();
	
	public int getMaximumSlots() {
		return availableSlots.size();
	}
}
