package com.lilithsthrone.game.sex;

import java.util.Map;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Enum values that determine what actions are available for each slot.</br></br>
 * 
 * Each value holds a map, <i>slotTargets</i>, which maps SexPositionSlots to a map of SexPositionSlots, which in turn maps to positions available.
 *  By providing a character's position in sex, along with the position of the partner they're targeting, this map is used to fetch available actions.</br></br>
 *  
 *  <b>Example:</b></br>
 *  <i>getSlotTargets().get(character1SexPositionSlot).get(character2SexPositionSlot)</i></br>returns the <i>SexActionPresetPair</i> which holds all available actions.</br></br>
 *  
 *  If character1SexPositionSlot is SexPositionSlot.DOGGY_ON_ALL_FOURS, and character2SexPositionSlot is SexPositionSlot.DOGGY_BEHIND, then the returned actions would be those that
 *   are available for the character on all fours, in relation to a character kneeling behind them.
 * 
 * @since 0.1.97
 * @version 0.2.0
 * @author Innoxia
 */
public enum SexPositionType {
	
	BACK_TO_WALL("Back-to-wall",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
									new SexActionPresetPair(
											SexActionPresets.playerBackToWallAgainstWall,
											SexActionPresets.partnerBackToWallAgainstWall)))),
					new Value<>(
							SexPositionSlot.BACK_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.BACK_TO_WALL_AGAINST_WALL,
									new SexActionPresetPair(
											SexActionPresets.playerBackToWallFacingTarget,
											SexActionPresets.partnerBackToWallFacingTarget)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
									new SexActionPresetPair(
											SexActionPresets.playerFacingWallAgainstWall,
											SexActionPresets.partnerFacingWallAgainstWall)))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL,
									new SexActionPresetPair(
											SexActionPresets.playerFacingWallFacingTarget,
											SexActionPresets.partnerFacingWallFacingTarget)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.COWGIRL_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.COWGIRL_RIDING,
									new SexActionPresetPair(
											SexActionPresets.playerCowgirlOnBack,
											SexActionPresets.partnerCowgirlOnBack)))),
					new Value<>(
							SexPositionSlot.COWGIRL_RIDING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.COWGIRL_ON_BACK,
									new SexActionPresetPair(
											SexActionPresets.playerCowgirlRiding,
											SexActionPresets.partnerCowgirlRiding)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFoursSideBySide,
											SexActionPresets.partnerDoggyOnAllFoursSideBySide)),
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)),
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND_ORAL,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT_ANAL,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)))),
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFoursSideBySide,
											SexActionPresets.partnerDoggyOnAllFoursSideBySide)),
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)),
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND_ORAL,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)),
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT_ANAL,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFours,
											SexActionPresets.partnerDoggyOnAllFours)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_INFRONT,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyBehindInteractingCharacterInfront,
											SexActionPresets.partnerDoggyBehindInteractingCharacterInfront)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyBehind,
											SexActionPresets.partnerDoggyBehind)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyBehind,
											SexActionPresets.partnerDoggyBehind)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyBehindOral,
											SexActionPresets.partnerDoggyBehindOral)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyBehindOral,
											SexActionPresets.partnerDoggyBehindOral)))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyBehindInteractingCharacterInfront,
											SexActionPresets.partnerDoggyBehindInteractingCharacterInfront)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyInfront,
											SexActionPresets.partnerDoggyInfront)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyInfront,
											SexActionPresets.partnerDoggyInfront)))),
					new Value<>(
							SexPositionSlot.DOGGY_INFRONT_ANAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyInfrontAnal,
											SexActionPresets.partnerDoggyInfrontAnal)),
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyInfrontAnal,
											SexActionPresets.partnerDoggyInfrontAnal)))))) {
		@Override
		public String getDescription() { //TODO
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS || Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND) {
				return "You're down on all fours, presenting yourself to [npc.name], who's kneeling down behind you, eager to take advantage of your submissive position.";
			} else if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_BEHIND) {
				return "[npc.Name] is down on all fours, presenting [npc.herself] to you. You're kneeling down behind [npc.herHim], ready to take advantage of [npc.her] submissive position.";
			} else {
				return "[npc.Name] is down on all fours, presenting [npc.herself] to you. You're also down on all fours behind [npc.herHim], ready to perform oral on [npc.herHim].";
			}
		}
	},
	
	SIXTY_NINE("Sixty-nine",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.SIXTY_NINE_TOP,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.SIXTY_NINE_BOTTOM,
									new SexActionPresetPair(
											SexActionPresets.playerSixtyNineOnTop,
											SexActionPresets.partnerSixtyNineOnTop)))),
					new Value<>(
							SexPositionSlot.SIXTY_NINE_BOTTOM,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.SIXTY_NINE_TOP,
									new SexActionPresetPair(
											SexActionPresets.playerSixtyNineOnBottom,
											SexActionPresets.partnerSixtyNineOnBottom)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL,
									new SexActionPresetPair(
											SexActionPresets.playerKneelingReceivingOral,
											SexActionPresets.partnerKneelingReceivingOral)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL,
									new SexActionPresetPair(
											SexActionPresets.playerKneelingPerformingOral,
											SexActionPresets.partnerKneelingPerformingOral)))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_RECEIVING_ORAL) {
				return "[npc.Name] is kneeling on the floor in front of you, with [npc.her] [npc.face+] hovering just inches away from your groin.";
			} else {
				return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
			}
		}
	},
	
	MISSIONARY("Missionary",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ON_BACK,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryKneelingBetweenLegs,
											SexActionPresets.partnerMissionaryKneelingBetweenLegs)))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ON_BACK,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryOnBack,
											SexActionPresets.partnerMissionaryOnBack)))))) {
		@Override
		public String getDescription() {
			if(Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS) {
				return "You're kneeling between [npc.name]'s [npc.legs], looking down at [npc.herHim] as you prepare to have sex in the missionary position.";
			} else {
				return "You're lying down on your back, looking up at [npc.name] as [npc.she] kneels down between your [npc.legs], ready to have sex with you in the missionary position.";
			}
		}
	},
	
	STANDING("Standing",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.STANDING_DOMINANT,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_SUBMISSIVE,
									new SexActionPresetPair(
											SexActionPresets.playerStandingDom,
											SexActionPresets.partnerStandingDom)))),
					new Value<>(
							SexPositionSlot.STANDING_SUBMISSIVE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STANDING_DOMINANT,
									new SexActionPresetPair(
											SexActionPresets.playerStandingSub,
											SexActionPresets.partnerStandingSub)))))) {
		@Override
		public String getDescription() {
			return "You're standing face-to-face with [npc.name].";
		}
	},
	
	CHAIR_SEX("Chair sex",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.CHAIR_TOP,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_BOTTOM,
									new SexActionPresetPair(
											SexActionPresets.playerChairTop,
											SexActionPresets.partnerChairTop)))),
					new Value<>(
							SexPositionSlot.CHAIR_BOTTOM,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_TOP,
									new SexActionPresetPair(
											SexActionPresets.playerChairBottom,
											SexActionPresets.partnerChairBottom)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STOCKS_RECEIVING_ORAL,
									new SexActionPresetPair(
											SexActionPresets.playerStocksLockedInStocks,
											SexActionPresets.partnerStocksLockedInStocks)),
							new Value<>(
									SexPositionSlot.STOCKS_PERFORMING_ORAL,
									new SexActionPresetPair(
											SexActionPresets.playerStocksLockedInStocks,
											SexActionPresets.partnerStocksLockedInStocks)),
							new Value<>(
									SexPositionSlot.STOCKS_FUCKING,
									new SexActionPresetPair(
											SexActionPresets.playerStocksLockedInStocks,
											SexActionPresets.partnerStocksLockedInStocks)))),
					new Value<>(
							SexPositionSlot.STOCKS_RECEIVING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
									new SexActionPresetPair(
											SexActionPresets.playerStocksReceivingOral,
											SexActionPresets.partnerStocksReceivingOral)))),
					new Value<>(
							SexPositionSlot.STOCKS_PERFORMING_ORAL,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
									new SexActionPresetPair(
											SexActionPresets.playerStocksPerformingOral,
											SexActionPresets.partnerStocksPerformingOral)))),
					new Value<>(
							SexPositionSlot.STOCKS_FUCKING,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.STOCKS_LOCKED_IN_STOCKS,
									new SexActionPresetPair(
											SexActionPresets.playerStocksStandingBehind,
											SexActionPresets.partnerStocksStandingBehind)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.CHAIR_TOP_LILAYA,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_BOTTOM_LILAYA,
									new SexActionPresetPair(
											SexActionPresets.playerChairTopLilaya,
											SexActionPresets.partnerChairTopLilaya)))),
					new Value<>(
							SexPositionSlot.CHAIR_BOTTOM_LILAYA,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.CHAIR_TOP_LILAYA,
									new SexActionPresetPair(
											SexActionPresets.playerChairBottomLilaya,
											SexActionPresets.partnerChairBottomLilaya)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_BEHIND_AMBER,
									new SexActionPresetPair(
											SexActionPresets.playerDoggyOnAllFoursAmber,
											SexActionPresets.empty)))),
					new Value<>(
							SexPositionSlot.DOGGY_BEHIND_AMBER,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER,
									new SexActionPresetPair(
											SexActionPresets.empty,
											SexActionPresets.partnerDoggyBehindAmber)))))) {
		@Override
		public String getDescription() {
			return "You're down on all fours, presenting yourself to [npc.name], who's kneeling down behind you, eager to take advantage of your submissive position.";
		}
	},
	
	KNEELING_ORAL_ZARANIX("Kneeling",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL_ZARANIX,
									new SexActionPresetPair(
											SexActionPresets.empty,
											SexActionPresets.partnerKneelingReceivingOralZaranix)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_ZARANIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX,
									new SexActionPresetPair(
											SexActionPresets.playerKneelingPerformingOralZaranix,
											SexActionPresets.empty)))))) {
		@Override
		public String getDescription() {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
		}
	},
	
	UNDER_DESK_RALPH("Under desk",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH,
									new SexActionPresetPair(
											SexActionPresets.empty,
											SexActionPresets.partnerKneelingReceivingOralRalph)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_RALPH,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_RALPH,
									new SexActionPresetPair(
											SexActionPresets.playerKneelingPerformingOralRalph,
											SexActionPresets.empty)))))) {
		@Override
		public String getDescription() {
			return "You're kneeling under Ralph's desk, with your face just inches away from his crotch.";
		}
	},
	
	SHOWER_TIME_PIX("Shower sex",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX,
									new SexActionPresetPair(
											SexActionPresets.partnerFacingWallFacingTargetPix,
											SexActionPresets.empty)))),
					new Value<>(
							SexPositionSlot.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX,
									new SexActionPresetPair(
											SexActionPresets.empty,
											SexActionPresets.playerFacingWallAgainstWallPix)))))) {
		@Override
		public String getDescription() {
			return "You're standing with your face pressed up against one wall of the shower, and behind you, Pix is growling hungrily into your ear.";
		}
	},
	
	HANDS_ROSE("Hand sex",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.HAND_SEX_DOM_ROSE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.HAND_SEX_SUB_ROSE,
									new SexActionPresetPair(
											SexActionPresets.playerDomHandsRose,
											SexActionPresets.partnerDomHandsRose)))),
					new Value<>(
							SexPositionSlot.HAND_SEX_SUB_ROSE,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.HAND_SEX_DOM_ROSE,
									new SexActionPresetPair(
											SexActionPresets.playerSubHandsRose,
											SexActionPresets.playerSubHandsRose)))))) {
		@Override
		public String getDescription() {
			return "You're standing in one of the many empty bedrooms in Lilaya's home. Before you, the cat-girl maid, Rose, is displaying her hands for your benefit.";
		}
	},
	
	MISSIONARY_DESK_VICKY("Missionary on counter",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_DESK_DOM_VICKY,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_DESK_SUB_VICKY,
									new SexActionPresetPair(
											SexActionPresets.empty,
											SexActionPresets.partnerMissionaryDeskDomVicky)))),
					new Value<>(
							SexPositionSlot.MISSIONARY_DESK_SUB_VICKY,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_DESK_DOM_VICKY,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryDeskSubVicky,
											SexActionPresets.empty)))))) {
		@Override
		public String getDescription() {
			return "You're lying back on top of Arcane Arts' front desk, and Vicky's standing between your [pc.legs], growling down at you as she prepares to fuck you in the missionary position.";
		}
	},
	
	KNEELING_ORAL_CULTIST("Kneeling",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST,
									new SexActionPresetPair(
											SexActionPresets.empty,
											SexActionPresets.partnerKneelingReceivingOralCultist)))),
					new Value<>(
							SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST,
									new SexActionPresetPair(
											SexActionPresets.playerKneelingPerformingOralCultist,
											SexActionPresets.empty)))))) {
		@Override
		public String getDescription() {
			return "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin.";
		}
	},
	
	MISSIONARY_ALTAR_CULTIST("Missionary on altar",
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarSubCultist,
											SexActionPresets.partnerMissionaryAltarSubCultist)),
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarSubCultist,
											SexActionPresets.partnerMissionaryAltarSubCultist)))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarDomCultist,
											SexActionPresets.partnerMissionaryAltarDomCultist)))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarDomCultistOral,
											SexActionPresets.partnerMissionaryAltarDomCultistOral)))))) {
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
			Util.newHashMapOfValues(
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarSealedSubCultist,
											SexActionPresets.partnerMissionaryAltarSealedSubCultist)),
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarSealedSubCultist,
											SexActionPresets.partnerMissionaryAltarSealedSubCultist)))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarSealedDomCultist,
											SexActionPresets.partnerMissionaryAltarSealedDomCultist)))),
					new Value<>(
							SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS,
							Util.newHashMapOfValues(
							new Value<>(
									SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR,
									new SexActionPresetPair(
											SexActionPresets.playerMissionaryAltarSealedDomCultistOral,
											SexActionPresets.partnerMissionaryAltarSealedDomCultistOral)))))) {
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
	private Map<SexPositionSlot, Map<SexPositionSlot, SexActionPresetPair>> slotTargets;
	
	private SexPositionType(String name, Map<SexPositionSlot, Map<SexPositionSlot, SexActionPresetPair>> slotTargets) {
		this.name = name;
		this.slotTargets = slotTargets;
	}
	
	public String getName() {
		return name;
	}

	public abstract String getDescription();
	
	public int getMaximumSlots() {
		return slotTargets.size();
	}

	public Map<SexPositionSlot, Map<SexPositionSlot, SexActionPresetPair>> getSlotTargets() {
		return slotTargets;
	}

}
