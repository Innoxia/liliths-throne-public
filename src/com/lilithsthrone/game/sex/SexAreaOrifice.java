package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.78
 * @version 0.3.5.5
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.MOUTH;
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
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] giving [npc2.name] a blowjob.");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] sucking [npc2.namePos] [npc2.tail].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] sucking [npc2.namePos] [npc2.tentacle].");
						}
						break;
					case TONGUE:
						if(pastTense) {
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NIPPLES;
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
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc.hands] into [npc.namePos] [npc.breasts] and [npc2.sexPaceVerb] played with [npc.her] [npc.nipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.nipples+].");
									break;
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] pinching and playing with [npc.namePos] [npc.nipples+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.nipples+].");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.nipples+].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tentacle-fucking [npc.namePos] [npc.nipples+].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.breasts+] into [npc2.namePos] [npc.face], before getting [npc2.herHim] to start sucking on [npc.her] [npc.nipples+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.breasts+] and starting to suck on [npc.her] [npc.nipples+].");
									break;
							}
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.BREASTS;
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
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc.hands] into [npc.namePos] [npc.breasts] and [npc2.sexPaceVerb] continued playing with them.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.breasts+].");
									break;
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] groping and squeezing [npc.namePos] [npc.breasts+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
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
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.breasts+] into [npc2.namePos] [npc.face], before getting [npc2.herHim] to start kissing and licking them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.breasts+] and starting to kiss and lick them.");
									break;
							}
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.NIPPLES_CROTCH;
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
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc.hands] into [npc.namePos] [npc.crotchBoobs] and [npc2.sexPaceVerb] played with [npc.her] [npc.crotchNipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] pinching and playing with [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] soon started [npc2.sexpaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc.crotchBoobs+], [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.her] [npc.crotchNipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to tentacle-fuck [npc.her] [npc.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tentacle-fucking [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.crotchBoobs+] into [npc2.namePos] [npc.face], before getting [npc2.herHim] to start sucking on [npc.her] [npc.crotchNipples+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.crotchBoobs+] and starting to suck on [npc.her] [npc.crotchNipples+].");
									break;
							}
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.BREASTS_CROTCH;
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
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc.hands] into [npc.namePos] [npc.crotchBoobs] and [npc2.sexPaceVerb] continued playing with them.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.crotchBoobs+].");
									break;
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] groping and squeezing [npc.namePos] [npc.crotchBoobs+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
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
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.crotchBoobs+] into [npc2.namePos] [npc.face], before getting [npc2.herHim] to start kissing and licking them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.crotchBoobs+] and starting to kiss and lick them.");
									break;
							}
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.ASS;
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
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.a_moan+], [npc2.name] pressed [npc2.her] [npc.hands] into [npc.namePos] [npc.ass] and [npc2.sexPaceVerb] continued playing with it.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried, and failed, to pull away from [npc.name], and could do nothing but cry as [npc2.she] [npc2.was] forced to play with [npc.her] [npc.ass+].");
									break;
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] groping and squeezing [npc.namePos] [npc.ass+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
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
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.ass+] into [npc2.namePos] [npc.face], before getting [npc2.herHim] to start kissing and licking it.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but was unable to stop [npc2.name] from pressing [npc2.her] [npc2.lips] against [npc.her] [npc.ass+] and starting to kiss and lick it.");
									break;
							}
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.ANUS;
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
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being anally fucked by [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being anally tail-fucked by [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being anally tentacle-fucked by [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.VAGINA;
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
						break;
					case FINGER:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fingering [npc.name].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being fucked by [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being tail-fucked by [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being tentacle-fucked by [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.THIGHS;
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.VAGINA;
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
		public CoverableArea getRelatedCoverableArea() {
			return CoverableArea.PENIS;
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fucking [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TAIL:
						if(pastTense) {
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
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] tail-fucking [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
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
		return cumAbsorptionPerSecond/60f;
	}
	
	/**
	 * @return true If this orifice is a fully internal orifice, capable of taking penile virginity.<br/>
	 * Mouth, vagina, anus, urethras, and nipple are considered internal orifices.<br/>
	 * Ass, breasts, and thighs are not.
	 */
	public boolean isInternalOrifice() {
		return takesPenisVirginity;
	}
	
	public float getCharactersCumLossPerSecond(GameCharacter target) {
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
