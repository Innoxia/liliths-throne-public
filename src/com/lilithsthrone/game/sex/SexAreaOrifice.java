package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.78
 * @version 0.4
 * @author Innoxia
 */
public enum SexAreaOrifice implements SexAreaInterface {
	
	MOUTH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			2/60f, 15/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "mouth";
			}
			return owner.getMouthName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this) && Main.sex.isPenetrationTypeFree(owner, SexAreaPenetration.TONGUE);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.MOUTH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getFaceStretchedCapacity();
			}
			return owner.getFaceRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getFaceMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getFaceMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.MOUTH getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] kept [npc.her] [npc.lips+] wrapped around [npc2.namePos] [npc2.clit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Bringing [npc.her] [npc.face] up to [npc2.namePos] groin, [npc.name] [npc.sexPaceVerb] wrapped [npc.her] [npc.lips+] around [npc2.her] [npc2.clit+] and performed oral on it.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.clit+] into [npc.her] mouth and forcing [npc.herHim] to perform oral on it.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")+", and didn't react at all as [npc2.she] had [npc2.her] [npc2.clit] sucked.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] had [npc2.her] [npc2.clit] sucked.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.clit] out of [npc.namePos] mouth, and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.clit] sucked against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] sucking [npc2.namePos] [npc2.clit+].");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep":"Without making a move")+", [npc.name] kept [npc.her] [npc.lips+] pressed against [npc2.namePos] [npc2.feet] in order to orally worship them.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Bringing [npc.her] [npc.face] down to the ends of [npc2.namePos] [npc2.legs], [npc.name] [npc.sexPaceVerb] pressed [npc.her] [npc.lips+] against [npc2.her] [npc2.feet] and orally worshipped them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.feet] into [npc.her] mouth and forcing [npc.herHim] to orally worship them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.feet] pushed into [npc.namePos] [npc.face].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] pushing [npc2.her] [npc2.feet] into [npc.namePos] [npc.face], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] had them orally worshipped.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.feet] away from [npc.namePos] mouth, but could do nothing but cry as [npc2.she] had them orally worshipped against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing and licking [npc2.namePos] [npc2.feet].");
						}
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"deeply asleep":"as still as a statue")+", [npc.name] kept [npc.her] [npc.lips+] wrapped around [npc2.namePos] [npc2.cock].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Bringing [npc.her] [npc.face] up to [npc2.namePos] groin, [npc.name] [npc.sexPaceVerb] wrapped [npc.her] [npc.lips+] around [npc2.her] [npc2.cock] and gave [npc2.herHim] a blowjob.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] mouth and forcing [npc.herHim] to give [npc2.herHim] a blowjob.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"continued to sleep":"didn't react or move at all")+" as [npc2.she] kept [npc2.her] [npc2.cock+] sheathed down [npc.namePos] throat.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] had [npc2.her] [npc2.cock] sucked.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] mouth, and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] sucked against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] giving [npc2.name] a blowjob.");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"deeply asleep":"as still as a statue")+", [npc.name] kept [npc.her] [npc.lips+] wrapped around [npc2.namePos] [npc2.tail].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] wrapped [npc.her] [npc.lips+] around [npc2.namePos] [npc2.tail] and started sucking on it.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail] into [npc.her] mouth and forcing [npc.herHim] to suck on it.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"continued to sleep":"didn't react or move at all")+" as [npc2.she] kept [npc2.her] [npc2.tail+] thrust deep down [npc.namePos] throat.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.tail] down [npc.namePos] throat.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] mouth, and could do nothing but cry as it was sucked against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] sucking [npc2.namePos] [npc2.tail].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"deeply asleep":"as still as a statue")+", [npc.name] kept [npc.her] [npc.lips+] wrapped around [npc2.namePos] [npc2.tentacle].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] wrapped [npc.her] [npc.lips+] around [npc2.namePos] [npc2.tentacle] and started sucking on it.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle] into [npc.her] mouth and forcing [npc.herHim] to suck on it.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"continued to sleep":"didn't react or move at all")+" as [npc2.she] kept [npc2.her] [npc2.tentacle+] thrust deep down [npc.namePos] throat.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.tentacle] down [npc.namePos] throat.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] mouth, and could do nothing but cry as it was sucked against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] sucking [npc2.namePos] [npc2.tentacle].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while pressing [npc.her] [npc.lips+] against [npc2.namePos] mouth.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.mouth], before pulling [npc2.herHim] into a kiss and starting to [npc.sexPaceVerb] make out with [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into a kiss and starting to [npc2.sexPaceVerb] make out with [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.tongue] thrust into [npc.namePos] [npc.mouth].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] leant into [npc.name] and [npc2.sexPaceVerb] started thrusting [npc2.her] [npc2.tongue] into [npc.her] [npc.mouth].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from thrusting [npc.her] [npc.tongue] into [npc2.her] [npc2.mouth].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.name].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	NIPPLE(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "nipple";
			}
			return owner.getNippleName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NIPPLES;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.NIPPLE;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getNippleStretchedCapacity();
			}
			return owner.getNippleRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getNippleMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getNippleMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.NIPPLE getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.name] pinched and squeezed [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] made [npc2.name] pinch and squeeze [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pinching and squeezing [npc.her] [npc.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")+", and kept [npc.namePos] [npc.nipples+] pinched between [npc2.her] [npc2.fingers] without moving.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc2.hands] into [npc.namePos] [npc.breasts] and [npc2.sexPaceVerb] played with [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] pinching and playing with [npc.namePos] [npc.nipples+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] guided [npc2.namePos] [npc2.cock+] up to [npc.her] [npc.nipples+] and got [npc2.herHim] to start fucking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.cock] against [npc.her] [npc.nipples+] and proceeding to fuck them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] bucking [npc2.her] [npc2.hips] into [npc.namePos] torso, [npc2.moaning] in delight as [npc2.she] fucked [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to fuck [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.nipples+].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] guided [npc2.namePos] [npc2.tail+] up to [npc.her] [npc.nipples+] and got [npc2.herHim] to start tail-fucking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.tail] against [npc.her] [npc.nipples+] and proceeding to tail-fuck them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc.breasts+], [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to tail-fuck [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.nipples+].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] guided [npc2.namePos] [npc2.tentacle+] up to [npc.her] [npc.nipples+] and got [npc2.herHim] to start tentacle-fucking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.tentacle] against [npc.her] [npc.nipples+] and proceeding to tentacle-fuck them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.breasts+], [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to tentacle-fuck [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tentacle-fucking [npc.namePos] [npc.nipples+].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] sucked on [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.breasts+] into [npc2.namePos] [npc2.face], before getting [npc2.herHim] to start sucking on [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.breasts+] and starting to suck on [npc.her] [npc.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.lips+] wrapped around [npc.namePos] [npc.nipples].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.breasts+] and [npc2.sexPaceVerb] continued to suck and kiss [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from pushing [npc.her] [npc.nipples+] against [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] sucking on [npc.namePos] [npc.nipples+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	BREAST(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				if(owner.hasBreasts()) {
					return "breasts";
				} else {
					return "pecs";
				}
			}
			return owner.getBreastName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.CHEST;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.BREAST getSexDescription() error: Does not support self actions!");
				return "";
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.name] groped and squeezed [npc.her] [npc.breasts+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] made [npc2.name] grope and squeeze them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from groping and squeezing [npc.her] [npc.breasts+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")+", and kept [npc.namePos] [npc.breasts+] gripped in [npc2.her] [npc2.hands] without moving.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc2.hands] into [npc.namePos] [npc.breasts] and [npc2.sexPaceVerb] continued playing with them.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.breasts+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] groping and squeezing [npc.namePos] [npc.breasts+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = performer.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] slid up and down between [npc.her] [npc.breasts+].");
								} else {
									sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] slid up and down over [npc.her] [npc.breasts+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("Pushing [npc.her] [npc.breasts+] together, [npc.name] [npc.was] able to slide [npc2.namePos] [npc2.cock+] up and down between them.");
										} else {
											sb.append("[npc.NamePos] chest was too flat to perform paizuri on [npc2.name], but that didn't stop [npc.herHim] from grinding [npc.her] [npc.breasts+] up and down against [npc2.her] [npc2.cock+].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("Pushing [npc.her] [npc.breasts+] together, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to perform paizuri on [npc2.herHim].");
										} else {
											sb.append("Not put off by the fact that [npc.her] chest is flat, [npc2.name] ignored [npc.namePos] protesting cries and stared grinding [npc.her] [npc.cock+] up and down over [npc2.her] [npc2.breasts+].");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								if(paizuri) {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] thrust between [npc.namePos] [npc.breasts+].");
								} else {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] pressed against [npc.namePos] [npc.breasts+].");
								}
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily received paizuri from [npc.name].");
										} else {
											sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily received naizuri from [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist receiving paizuri from [npc.name].");
										} else {
											sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist receiving naizuri from [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] performing paizuri on [npc2.name].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] performing naizuri on [npc2.name].");
							}
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] kissed and licked [npc.her] [npc.breasts+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.breasts+] into [npc2.namePos] [npc2.face], before getting [npc2.herHim] to start kissing and licking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.breasts+] and starting to kiss and lick them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.lips+] pressed against [npc.namePos] [npc.breasts].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.breasts+] and continued to [npc2.sexPaceVerb] kiss them.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from pushing [npc.her] [npc.breasts+] against [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] kissing [npc.namePos] [npc.breasts+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	NIPPLE_CROTCH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "teat";
			}
			return owner.getNippleCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NIPPLES_CROTCH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.STOMACH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getNippleCrotchStretchedCapacity();
			}
			return owner.getNippleCrotchRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getNippleCrotchMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getNippleCrotchMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.NIPPLE_CROTCH getSexDescription() error: Does not support self actions!");
				return "";
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.name] pinched and squeezed [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] made [npc2.name] pinch and squeeze [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pinching and squeezing [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and kept [npc.namePos] [npc.crotchNipples+] pinched between [npc2.her] [npc2.fingers] without moving.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc2.hands] into [npc.namePos] [npc.crotchBoobs] and [npc2.sexPaceVerb] played with [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] pinching and playing with [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] guided [npc2.namePos] [npc2.cock+] up to [npc.her] [npc.crotchNipples+] and got [npc2.herHim] to start fucking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.cock] against [npc.her] [npc.crotchNipples+] and proceeding to fuck them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] bucking [npc2.her] [npc2.hips] into [npc.namePos] groin, [npc2.moaning] in delight as [npc2.she] fucked [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to fuck [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] guided [npc2.namePos] [npc2.tail+] up to [npc.her] [npc.crotchNipples+] and got [npc2.herHim] to start tail-fucking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.tail] against [npc.her] [npc.crotchNipples+] and proceeding to tail-fuck them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc.crotchBoobs+], [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to tail-fuck [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] guided [npc2.namePos] [npc2.tentacle+] up to [npc.her] [npc.crotchNipples+] and got [npc2.herHim] to start tentacle-fucking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.tentacle] against [npc.her] [npc.crotchNipples+] and proceeding to tentacle-fuck them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.crotchBoobs+],"
												+ " [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to tentacle-fuck [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tentacle-fucking [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] sucked on [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.crotchBoobs+] into [npc2.namePos] [npc2.face], before getting [npc2.herHim] to start sucking on [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.crotchBoobs+] and starting to suck on [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.lips+] wrapped around [npc.namePos] [npc.crotchNipples].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoobs+] and [npc2.sexPaceVerb] continued to suck and kiss [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from pushing [npc.her] [npc.crotchNipples+] against [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] sucking on [npc.namePos] [npc.crotchNipples+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	BREAST_CROTCH(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "crotch-boobs";
			}
			return owner.getBreastCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS_CROTCH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.STOMACH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.BREAST_CROTCH getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.name] groped and squeezed [npc.her] [npc.crotchBoobs+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] made [npc2.name] grope and squeeze them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from groping and squeezing [npc.her] [npc.crotchBoobs+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")+", and kept [npc.namePos] [npc.crotchBoobs+] gripped in [npc2.her] [npc2.hands] without moving.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc2.hands] into [npc.namePos] [npc.crotchBoobs] and [npc2.sexPaceVerb] continued playing with them.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.crotchBoobs+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] groping and squeezing [npc.namePos] [npc.crotchBoobs+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = performer.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] slid up and down between [npc.her] [npc.crotchBoobs+].");
								} else {
									sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] slid up and down over [npc.her] [npc.crotchBoobs+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("Pushing [npc.her] [npc.crotchBoobs+] together, [npc.name] [npc.was] able to slide [npc2.namePos] [npc2.cock+] up and down between them.");
										} else {
											sb.append("[npc.NamePos] [npc.crotchBoobs] were too flat to perform crotch-paizuri on [npc2.name],"
													+ " but that didn't stop [npc.herHim] from grinding [npc.her] [npc.crotchBoobs+] up and down against [npc2.her] [npc2.cock+].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("Pushing [npc.her] [npc.crotchBoobs+] together, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to perform crotch-paizuri on [npc2.herHim].");
										} else {
											sb.append("Not put off by the fact that [npc.her] [npc.crotchBoobs] are flat,"
													+ " [npc2.name] ignored [npc.namePos] protesting cries and stared grinding [npc.her] [npc.cock+] up and down over [npc2.her] [npc2.crotchBoobs+].");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								if(paizuri) {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] thrust between [npc.namePos] [npc.crotchBoobs+].");
								} else {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] pressed against [npc.namePos] [npc.crotchBoobs+].");
								}
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily received crotch-paizuri from [npc.name].");
										} else {
											sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily received crotch-naizuri from [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist receiving crotch-paizuri from [npc.name].");
										} else {
											sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist receiving crotch-naizuri from [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] performing crotch-paizuri on [npc2.name].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] performing crotch-naizuri on [npc2.name].");
							}
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] kissed and licked [npc.her] [npc.crotchBoobs+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.crotchBoobs+] into [npc2.namePos] [npc2.face], before getting [npc2.herHim] to start kissing and licking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.crotchBoobs+] and starting to kiss and lick them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.lips+] pressed against [npc.namePos] [npc.crotchBoobs].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.crotchBoobs+] and continued to [npc2.sexPaceVerb] kiss them.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from pushing [npc.her] [npc.crotchBoobs+] against [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] kissing [npc.namePos] [npc.crotchBoobs+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	ASS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "ass cheeks";
			}
			return "ass cheeks";
//			return owner.getAssName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ASS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.ASS getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.name] groped [npc.her] [npc.ass+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing out [npc.her] [npc.ass+], [npc.name] [npc.sexPaceVerb] made [npc2.name] grope and squeeze it.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from groping and squeezing [npc.her] [npc.ass+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")+", and kept [npc.namePos] [npc.ass+] gripped in [npc2.her] [npc2.hands] without moving.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc2.hands] into [npc.namePos] [npc.ass] and [npc2.sexPaceVerb] continued playing with it.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.ass+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] groping and squeezing [npc.namePos] [npc.ass+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] slid up and down between [npc.her] ass cheeks.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing [npc.her] ass cheeks together, [npc.name] got [npc2.name] to slide [npc2.her] [npc2.cock+] up and down between the crevice that was formed.");
										break;
									case SUB_RESISTING:
										sb.append("Although [npc.she] tried to resist, [npc.name] had [npc.her] ass cheeks pushed together and was then hotdogged by [npc2.namePos] [npc2.cock+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] thrust between [npc.namePos] ass cheeks.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.cock+] up and over [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] hotdogging [npc.namePos] ass.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] kissed and licked [npc.her] [npc.ass+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.ass+] into [npc2.namePos] [npc2.face], before getting [npc2.herHim] to start kissing and licking it.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.ass+] and starting to kiss and lick it.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.lips+] pressed against [npc.namePos] [npc.ass].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.ass+] and continued to [npc2.sexPaceVerb] kiss it.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from pushing [npc.her] [npc.ass+] against [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] kissing [npc.namePos] [npc.ass+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	ANUS(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "asshole";
			}
			return owner.getAnusName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ANUS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.ANUS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getAssStretchedCapacity();
			}
			return owner.getAssRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getAssMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getAssMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.ANUS getSexDescription() error: Does not support self actions!");
				return "";
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.clit+] thrust into [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Lining [npc.her] [npc.ass] up to [npc2.namePos] groin, [npc.name] [npc.sexPaceVerb] pushed back against [npc2.her] [npc2.clit+] and made [npc2.herHim] penetrate [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.clit+] into [npc.her] [npc.asshole+] and start anally fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.clit] hilted in [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.ass], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] clit-fucked [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.clit] out of [npc.namePos] [npc.asshole], and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.clit] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] being [npc.sexPaceVerb] clit-fucked in the ass by [npc2.name].");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] pushed [npc2.her] [npc2.fingers+] deep into [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Grabbing [npc2.namePos] [npc2.hand], [npc.name] [npc.sexPaceVerb] pushed [npc2.her] [npc2.fingers] into [npc.her] [npc.asshole] and made [npc2.herHim] anally finger [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.fingers] into [npc.her] [npc.asshole+] and start anally fingering [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.fingers] shoved deep in [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] pushing [npc2.her] [npc2.fingers] into [npc.namePos] [npc.asshole], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fingered [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.fingers] out of [npc.namePos] [npc.asshole], and could do nothing but cry as [npc2.she] was forced to finger [npc.namePos] [npc.asshole+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] being [npc.sexPaceVerb] anally fingered by [npc2.name].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Lining [npc.her] [npc.ass] up to [npc2.namePos] groin, [npc.name] [npc.sexPaceVerb] pushed back against [npc2.her] [npc2.cock] and made [npc2.herHim] penetrate [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] [npc.asshole+] and start anally fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] into [npc.namePos] [npc.ass], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] [npc.asshole], and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] being [npc.sexPaceVerb] fucked in the ass by [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tail+], [npc.name] lined it up to [npc.her] [npc.ass+] and made [npc2.herHim] penetrate [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail+] into [npc.her] [npc.asshole+] and start anally tail-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc.asshole], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] anally tail-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] [npc.asshole], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being anally tail-fucked by [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tentacle+], [npc.name] lined it up to [npc.her] [npc.ass+] and made [npc2.herHim] penetrate [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle+] into [npc.her] [npc.asshole+] and start anally tentacle-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.asshole], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] anally tentacle-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] [npc.asshole], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being anally tentacle-fucked by [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] tongued [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] pushed [npc.her] [npc.ass+] back against [npc2.namePos] [npc2.face], before [npc.sexPaceVerb] making [npc2.herHim] start tonguing [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist,"
												+ " but [npc.was] unable to stop [npc2.name] from pressing [npc2.her] [npc2.face] against [npc.her] [npc.ass] and then tonguing [npc.her] [npc.asshole+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.tongue] thrust into [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out a series of [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.asshole+] and continued performing anilingus on [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name] tried to resist,"
												+ " but [npc2.was] unable to stop [npc.name] from planting [npc.her] [npc.ass] over [npc2.her] [npc2.face] and [npc.sexPaceVerb] forcing [npc2.herHim] to perform anilingus on [npc.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] performing anilingus on [npc.name].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	VAGINA(4,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "pussy";
			}
			return owner.getVaginaName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.VAGINA;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.VAGINA;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getVaginaStretchedCapacity();
			}
			return owner.getVaginaRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getVaginaMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getVaginaMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.VAGINA getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.clit+] thrust into [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Rubbing [npc.her] [npc.labia+] over the [npc2.clitTip+] of [npc2.namePos] [npc2.clit],"
												+ " [npc.name] [npc.sexPaceVerb] bucked [npc.her] [npc.hips] and forced [npc2.herHim] to penetrate [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.clit+] into [npc.her] [npc.pussy+] and start fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.clit] hilted in [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.clit+] into [npc.namePos] [npc.pussy+], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.clit] out of [npc.namePos] [npc.pussy], and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.clit] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being clit-fucked by [npc2.name].");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] pushed [npc2.her] [npc2.fingers+] deep into [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.hand+],"
												+ " [npc.name] rubbed [npc2.her] [npc2.fingers+] up and down over [npc.her] [npc.labia+] before making [npc2.herHim] slip them inside and start fingering [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.fingers+] into [npc.her] [npc.pussy+] and start fingering [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.fingers] shoved deep in [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] curling [npc2.her] [npc2.fingers] up inside [npc.namePos] [npc.pussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fingered [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.fingers] out of [npc.namePos] [npc.pussy],"
												+ " and could do nothing but cry as [npc2.she] was forced to finger [npc.herHim] against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fingering [npc.name].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Rubbing [npc.her] [npc.labia+] over the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
												+ " [npc.name] [npc.sexPaceVerb] bucked [npc.her] [npc.hips] and forced [npc2.herHim] to penetrate [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] [npc.pussy+] and start fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.cock+] into [npc.namePos] [npc.pussy+], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] [npc.pussy], and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being fucked by [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tail+], [npc.name] rubbed it up and down over [npc.her] [npc.labia+] before making [npc2.herHim] penetrate and start tail-fucking [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail+] into [npc.her] [npc.pussy+] and start tail-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc.pussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] [npc.pussy], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being tail-fucked by [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tentacle+], [npc.name] rubbed it up and down over [npc.her] [npc.labia+] before making [npc2.herHim] penetrate and start tentacle-fucking [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle+] into [npc.her] [npc.pussy+] and start tentacle-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.pussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] [npc.pussy], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being tentacle-fucked by [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] performed cunnilingus on [npc.herHim].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] pushed [npc.her] [npc.labia+] against [npc2.namePos] [npc2.face], before [npc.sexPaceVerb] making [npc2.herHim] start eating [npc.herHim] out.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist,"
												+ " but [npc.was] unable to stop [npc2.name] from pressing [npc2.her] [npc2.face] against [npc.her] [npc.pussy] and then start eating [npc.herHim] out.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.tongue] thrust into [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out a series of [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.pussy+] and continued performing cunnilingus on [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist,"
												+ " but [npc2.was] unable to stop [npc.name] from planting [npc.her] [npc.pussy+] over [npc2.her] [npc2.face] and [npc.sexPaceVerb] forcing [npc2.herHim] to perform cunnilingus on [npc.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] performing cunnilingus on [npc.name].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	THIGHS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "thighs";
			}
			return "thighs";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.THIGHS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.THIGHS getSexDescription() error: Does not support self actions!");
				return "";
			}
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep":"Acting like a lifeless sex toy")
										+", [npc.name] kept [npc.her] [npc.legs] pressed together while [npc2.namePos] slid [npc.her] [npc2.cock+] in and out of the crevice that was formed.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Pushing [npc.her] [npc.legs] together, [npc.name] got [npc2.name] to slide [npc2.her] [npc2.cock+] in and out of the crevice that was formed.");
										break;
									case SUB_RESISTING:
										sb.append("Although [npc.she] tried to resist, [npc.name] had [npc.her] [npc.legs] pushed together and was then thigh-fucked by [npc2.namePos] [npc2.cock+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] thrust between [npc.namePos] thighs.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.cock+] into the cleft between [npc.namePos] thighs.");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] thigh-fucking [npc.name].");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	ARMPITS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "armpits";
			}
			return "armpits";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ARMPITS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TORSO_UNDER;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.ARMPITS getSexDescription() error: Does not support self actions!");
				return "";
			}
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Acting like a lifeless sex toy, [npc.name] remained totally motionless")
											+" while [npc2.namePos] [npc2.cock+] slid up and down over [npc.her] [npc.armpit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Lifting [npc.her] [npc.arm(true)], [npc.name] got [npc2.name] to slide [npc2.her] [npc2.cock+] up and down over [npc.her] [npc.armpit+].");
										break;
									case SUB_RESISTING:
										sb.append("Although [npc.she] tried to resist, [npc.name] had [npc.her] [npc.arm(true)] lifted and [npc2.namePos] [npc2.cock+] rubbed up and down over [npc.her] [npc.armpit+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"showed no sign of waking up":"remained totally inanimate")+" as [npc2.she] kept [npc2.her] [npc2.cock] pressed against [npc.namePos] armpit.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out [npc2.a_moan+], [npc2.name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.cock+] against [npc.namePos] armpit.");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] armpit.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] kissed and licked [npc.her] [npc.armpits+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.armpits+] into [npc2.namePos] [npc2.face], before getting [npc2.herHim] to start kissing and licking them.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.armpits+] and starting to kiss and lick them.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.lips+] pressed against [npc.namePos] [npc.armpit].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out muffled [npc2.moans], [npc2.name] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc.armpits+] and continued to [npc2.sexPaceVerb] kiss them.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from pushing [npc.her] [npc.armpits+] against [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] kissing [npc.namePos] [npc.armpits+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	URETHRA_VAGINA(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "vaginal urethra";
			}
			return "urethra";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.VAGINA;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.VAGINA;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getVaginaUrethraStretchedCapacity();
			}
			return owner.getVaginaUrethraRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getVaginaUrethraMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getVaginaUrethraMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.URETHRA_VAGINA getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Rubbing [npc.her] [npc.labia+] over the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
												+ " [npc.name] [npc.sexPaceVerb] bucked [npc.her] [npc.hips] and forced [npc2.herHim] to penetrate [npc.her] [npc.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] pussy's [npc.vaginaUrethra+] and start fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.cock+] into [npc.namePos] [npc.vaginaUrethra+], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] [npc.vaginaUrethra],"
												+ " and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tail+], [npc.name] rubbed it up and down over [npc.her] [npc.labia+] before making [npc2.herHim] penetrate and start tail-fucking [npc.her] [npc.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail+] into [npc.her] pussy's [npc.vaginaUrethra+] and start tail-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc.vaginaUrethra], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] [npc.vaginaUrethra], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tentacle+], [npc.name] rubbed it up and down over [npc.her] [npc.labia+] before making [npc2.herHim] penetrate and start tentacle-fucking [npc.her] [npc.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle+] into [npc.her] pussy's [npc.vaginaUrethra+] and start tentacle-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.vaginaUrethra], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] [npc.vaginaUrethra], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tentacle-fucking [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	URETHRA_PENIS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "penile urethra";
			}
			return "urethra";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this) && Main.sex.isPenetrationTypeFree(owner, SexAreaPenetration.PENIS);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.PENIS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.PENIS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getPenisStretchedCapacity();
			}
			return owner.getPenisRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getUrethraMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getUrethraMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.URETHRA_PENIS getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Rubbing the [npc2.cockHead+] of [npc2.namePos] [npc2.cock] over the tip of [npc.her] own,"
												+ " [npc.name] [npc.sexPaceVerb] bucked [npc.her] [npc.hips] forwards and forced [npc2.herHim] to penetrate [npc.her] [npc.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] cock's [npc.penisUrethra+] and start fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.cock+] into [npc.namePos] [npc.penisUrethra+], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] [npc.penisUrethra],"
												+ " and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tail+],"
												+ " [npc.name] rubbed it up and down over the [npc.cockHead+] of [npc.her] [npc.cock] before making [npc2.herHim] penetrate and start tail-fucking [npc.her] [npc.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail+] into [npc.her] cock's [npc.penisUrethra+] and start tail-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc.penisUrethra], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] [npc.penisUrethra], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tentacle+],"
												+ " [npc.name] rubbed it up and down over the [npc.cockHead+] of [npc.her] [npc.cock] before making [npc2.herHim] penetrate and start tentacle-fucking [npc.her] [npc.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle+] into [npc.her] cock's [npc.penisUrethra+] and start tentacle-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.penisUrethra], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] [npc.penisUrethra], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tentacle-fucking [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	SPINNERET(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			return "spinneret";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner!=null && owner.hasTailSpinneret()) {
				return CoverableArea.TAIL;
			}
			return CoverableArea.ASS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			if(owner!=null && owner.hasTailSpinneret()) {
				return InventorySlot.TAIL;
			}
			return InventorySlot.ANUS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getSpinneretStretchedCapacity();
			}
			return owner.getSpinneretRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getSpinneretMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getSpinneretMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaOrifice.SPINNERET getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] pushed [npc2.her] [npc2.fingers+] deep into [npc.her] spinneret.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.hand+],"
												+ " [npc.name] rubbed [npc2.her] [npc2.fingers+] up and down over [npc.her] spinneret before making [npc2.herHim] slip them inside and start fingering [npc.her] web-spinning orifice.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.fingers+] into [npc.her] spinneret and start fingering [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.fingers] shoved deep in [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] curling [npc2.her] [npc2.fingers] up inside [npc.namePos] spinneret, [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fingered [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.fingers] out of [npc.namePos] spinneret,"
												+ " and could do nothing but cry as [npc2.she] was forced to finger [npc.herHim] against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fingering [npc.namePos] spinneret.");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Rubbing [npc.her] spinneret over the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
												+ " [npc.name] [npc.sexPaceVerb] bucked [npc.her] [npc.hips] and forced [npc2.herHim] to penetrate [npc.her] web-spinning orifice.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] spinneret and start fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.cock+] into [npc.namePos] spinneret, [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] spinneret, and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being fucked by [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tail+], [npc.name] rubbed it up and down over [npc.her] spinneret before making [npc2.herHim] penetrate and start tail-fucking [npc.her] web-spinning orifice.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail+] into [npc.her] spinneret and start tail-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] spinneret, [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] spinneret, and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] having [npc.her] spinneret tail-fucked by [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tentacle+], [npc.name] rubbed it up and down over [npc.her] spinneret before making [npc2.herHim] penetrate and start tentacle-fucking [npc.her] web-spinning orifice.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle+] into [npc.her] spinneret and start tentacle-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] spinneret, [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] spinneret, and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] having [npc.her] spinneret tentacle-fucked by [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+" while [npc2.name] performed oral on [npc.her] web-spinning orifice.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] pushed [npc.her] spinneret against [npc2.namePos] [npc2.face], before [npc.sexPaceVerb] making [npc2.herHim] start performing oral on [npc.her] web-spinning orifice.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist,"
												+ " but [npc.was] unable to stop [npc2.name] from pressing [npc2.her] [npc2.face] against [npc.her] spinneret and then start performing oral on [npc.her] web-spinning orifice.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.tongue] thrust into [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out a series of [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pressed [npc2.her] [npc2.lips+] against [npc.namePos] spinneret and continued performing oral on [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist,"
												+ " but [npc2.was] unable to stop [npc.name] from planting [npc.her] spinneret over [npc2.her] [npc2.face] and [npc.sexPaceVerb] forcing [npc2.herHim] to perform oral on [npc.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] performing oral on [npc.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	};

	private float baseArousalWhenPenetrated;
	private float arousalChangePenetratedStretching;
	private float arousalChangePenetratedTooLoose;
	private float arousalChangePenetratedDry;
	private float arousalChangePenetratingStretching;
	private float arousalChangePenetratingTooLoose;
	private float arousalChangePenetratingDry;
	private float cumLossPerSecond;
	private float cumAbsorptionPerSecond;
	private boolean takesPenisVirginity;

	/**
	 * @param baseArousalWhenPenetrated
	 * @param arousalChangePenetratedStretching
	 * @param arousalChangePenetratedTooLoose
	 * @param arousalChangePenetratedDry
	 * @param arousalChangePenetratingStretching
	 * @param arousalChangePenetratingTooLoose
	 * @param arousalChangePenetratingDry
	 * @param cumLossPerSecond The amount of cum or other fluids that leak out of this orifice every second.
	 * @param cumAbsorptionPerSecond The amount of cum or other fluids that are absorbed into the character's body through this orifice every second.
	 * @param takesPenisVirginity
	 */
	private SexAreaOrifice(float baseArousalWhenPenetrated,
			float arousalChangePenetratedStretching,
			float arousalChangePenetratedTooLoose,
			float arousalChangePenetratedDry,
			float arousalChangePenetratingStretching,
			float arousalChangePenetratingTooLoose,
			float arousalChangePenetratingDry,
			float cumLossPerSecond,
			float cumAbsorptionPerSecond,
			boolean takesPenisVirginity) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.arousalChangePenetratedStretching = arousalChangePenetratedStretching;
		this.arousalChangePenetratedTooLoose = arousalChangePenetratedTooLoose;
		this.arousalChangePenetratedDry = arousalChangePenetratedDry;
		this.arousalChangePenetratingStretching = arousalChangePenetratingStretching;
		this.arousalChangePenetratingTooLoose = arousalChangePenetratingTooLoose;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.cumLossPerSecond = cumLossPerSecond;
		this.cumAbsorptionPerSecond = cumAbsorptionPerSecond;
		this.takesPenisVirginity = takesPenisVirginity;
	}

	@Override
	public boolean isOrifice() {
		return true;
	}
	
	public float getBaseArousalWhenPenetrated() {
		return baseArousalWhenPenetrated;
	}
	
	public float getArousalChangePenetratedStretching() {
		return arousalChangePenetratedStretching;
	}

	public float getArousalChangePenetratedTooLoose() {
		return arousalChangePenetratedTooLoose;
	}

	public float getArousalChangePenetratedDry() {
		return arousalChangePenetratedDry;
	}

	public float getArousalChangePenetratingStretching() {
		return arousalChangePenetratingStretching;
	}

	public float getArousalChangePenetratingTooLoose() {
		return arousalChangePenetratingTooLoose;
	}

	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}

	public float getCumLossPerSecond() {
		return cumLossPerSecond;
	}
	
	public float getCumAbsorptionPerSecond() {
		return cumAbsorptionPerSecond;
	}
	
	/**
	 * @return true If this orifice is a fully internal orifice, capable of taking penile virginity.<br/>
	 * Mouth, vagina, anus, urethras, and nipple are considered internal orifices.<br/>
	 * Ass, breasts, and thighs are not.
	 */
	public boolean isInternalOrifice() {
		return takesPenisVirginity;
	}
	
	public abstract float getCapacity(GameCharacter owner, boolean currentlyStretchedValue);
	
	public float getCharactersCumLossPerSecond(GameCharacter target) {
		if(target.hasCreampieRetentionArea(this)) {
			return 0;
		}
		
		float cumLost = this.getCumAbsorptionPerSecond();
		float fluidInArea = target.getTotalFluidInArea(this);
		// The rate obviously decreases as the fluid drains out, but assuming if the drain was applied all at once, it would take about 5.5 hours to all drain out (not factoring in absorption or natural loss):
		float secondPercentageLoss = fluidInArea/20_000;
		
		if(!target.isOrificePlugged(this)) {
			cumLost += this.getCumLossPerSecond() + secondPercentageLoss;
		}
		return cumLost;
	}
	
	public abstract int getMaximumPenetrationDepthComfortable(GameCharacter target);
	
	public abstract int getMaximumPenetrationDepthUncomfortable(GameCharacter target);
}
