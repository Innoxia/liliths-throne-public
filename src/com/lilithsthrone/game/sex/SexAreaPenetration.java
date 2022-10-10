package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public enum SexAreaPenetration implements SexAreaInterface {
	
	PENIS(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "cock";
			}
			return owner.getPenisName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getPenisRawSizeValue();
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			return owner.getPenisDiameter();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, SexAreaOrifice.URETHRA_PENIS) && Main.sex.isPenetrationTypeFree(owner, this);
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
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.PENIS getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("[npc.Name] [npc.sexPaceVerb] rubbed the [npc.cockHead] of [npc.her] [npc.cock+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit],"
											+ " teasing [npc2.herHim] with the promise of penetration.");
									break;
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] forced the [npc.cockHead] of [npc.her] [npc.cock+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit],"
											+ " cruelly teasing [npc2.herHim] with the promise of penetration.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist as [npc.she] [npc.was] forced to rub [npc.her] [npc.cock+] over [npc2.namePos] [npc2.pussy] and [npc2.clit].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Before too long, [npc2.name] found [npc2.herself] desperately turned-on and [npc2.was] [npc2.moaning] for [npc.name] to start fucking [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append(" Before too long,"
											+ " [npc2.name] found [npc2.herself] desperately turned-on and [npc2.was] ordering [npc.name] to hold still as [npc2.she] ground [npc2.her] [npc2.clit+] against [npc.namePos] [npc.cock+].");
									break;
								case SUB_RESISTING:
									sb.append(" Crying and pleading to be left alone, [npc2.name] desperately tried to struggle free, but to no avail.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] rubbing the [npc.cockHead] of [npc.her] [npc.cock+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit].");
						}
						break;
					case FINGER:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] received a handjob from [npc2.name].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to pull away as [npc2.name] gave [npc.herHim] a handjob.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.SexPaceVerb] wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.cock], [npc2.name] took great pleasure in jerking [npc.herHim] off.");
									break;
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] wrapping [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.cock], [npc2.name] dominantly ordered [npc.herHim] to remain still while [npc2.she] jerked [npc.herHim] off.");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out pitiful sobs, [npc2.name] tried to plead with [npc.name] to leave [npc2.herHim] alone, but [npc.she] simply ignored [npc2.herHim] and continued jerking [npc.herHim] off.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] receiving a handjob from [npc2.name].");
						}
						break;
					case FOOT:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] took great pleasure in [npc.sexPaceVerb] rubbing [npc.her] [npc.cock] over [npc2.namePos] [npc2.feet+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist and pull away as [npc2.name] rubbed [npc2.her] [npc2.feet+] up and down over [npc.her] [npc.cock].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+], [npc2.name] greatly enjoyed the feeling of using [npc2.her] [npc2.feet+] to pleasure [npc.name].");
									break;
								case SUB_RESISTING:
									sb.append(" Doing [npc2.her] best to try and resist, [npc2.name] ultimately proved unable to stop [npc.name] from using [npc2.her] [npc2.feet] in such a lewd manner.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] receiving a [npc2.footjob] from [npc2.name].");
						}
						break;
					case PENIS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] rubbed [npc.her] [npc.cock+] up and down against [npc2.namePos] own [npc2.cock].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist as [npc.she] [npc.was] forced to rub [npc.her] [npc.cock+] against [npc2.namePos] own [npc2.cock].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] couldn't help but let out a series of lewd [npc2.moans] as [npc2.her] [npc2.cock+] was used in such a manner.");
									break;
								case SUB_RESISTING:
									sb.append(" Crying and pleading to be left alone, [npc2.name] desperately tried, and failed, to pull away from [npc.name].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] rubbing [npc.her] [npc.cock+] up and down against [npc2.namePos] own [npc2.cock].");
						}
						break;
					case TAIL:
						boolean multipleTails = target.getTailCount()>1;
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] received a tailjob from [npc2.name].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to pull away as [npc2.name] gave [npc.herHim] a tailjob.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.SexPaceVerb] wrapping [npc2.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" around [npc.namePos] [npc.cock],"
												+ " [npc2.name] took great pleasure in using "+(multipleTails?"them":"it")+" to jerk [npc.herHim] off.");
									break;
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] wrapping [npc2.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" around [npc.namePos] [npc.cock],"
												+ " [npc2.name] dominantly ordered [npc.herHim] to remain still while [npc2.she] used "+(multipleTails?"them":"it")+" to jerk [npc.herHim] off.");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out pitiful sobs, [npc2.name] tried to plead with [npc.name] to leave [npc2.herHim] alone,"
												+ " but [npc.she] simply ignored [npc2.herHim] and continued using [npc.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" to jerk [npc.herHim] off.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] receiving a tailjob from [npc2.name].");
						}
						break;
					case TENTACLE:
						boolean multipleTentacles = target.getTentacleCount()>1;
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] received a tentaclejob from [npc2.name].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to pull away as [npc2.name] gave [npc.herHim] a tentaclejob.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.SexPaceVerb] wrapping [npc2.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" around [npc.namePos] [npc.cock],"
												+ " [npc2.name] took great pleasure in using "+(multipleTentacles?"them":"it")+" to jerk [npc.herHim] off.");
									break;
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] wrapping [npc2.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" around [npc.namePos] [npc.cock],"
												+ " [npc2.name] dominantly ordered [npc.herHim] to remain still while [npc2.she] used "+(multipleTentacles?"them":"it")+" to jerk [npc.herHim] off.");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out pitiful sobs, [npc2.name] tried to plead with [npc.name] to leave [npc2.herHim] alone,"
												+ " but [npc.she] simply ignored [npc2.herHim] and continued using [npc.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" to jerk [npc.herHim] off.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] receiving a tentaclejob from [npc2.name].");
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
									sb.append("[npc.Name] soon found [npc.herself] [npc.moaning] in delight as [npc2.namePos] [npc2.tongue+] licked and lapped at [npc.her] [npc.cock+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] cried and struggled against [npc2.name] as [npc2.she] used [npc2.her] [npc2.tongue+] to lick and lap at [npc.her] [npc.cock+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Oftentimes with [npc2.her] mouth full of cock, [npc2.name] let it be known that [npc2.she] [npc2.was] having a good time by letting out a series of muffled [npc2.moans].");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] tried to resist, [npc2.name] ended up being forced to service [npc.namePos] [npc.cock] in this manner.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] having [npc.her] [npc.cock] licked by [npc2.name].");
						}
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ANUS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed the [npc.cockHead] of [npc.her] [npc.cock+] against [npc2.namePos] [npc2.asshole+], before thrusting forwards and starting to [npc.sexPaceVerb] fuck [npc2.her] [npc2.ass+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] pressed [npc2.her] [npc2.asshole+] back against the [npc.cockHead] of [npc.her] [npc.cock+],"
											+ " and cried out in despair as [npc2.she] thrust back and forced [npc.herHim] to start fucking [npc2.her] [npc2.ass+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.asshole].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.cock+] thrust in and out of [npc2.her] [npc2.asshole+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pushing [npc2.namePos] ass cheeks together, [npc.name] [npc.was] able to slide [npc.her] [npc.cock+] up and down between them.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] forced to slide [npc.her] [npc.cock+] up and down between [npc2.namePos] ass cheeks.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Lifting and dropping [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] soon [npc2.moaning] as [npc2.she] [npc2.sexPaceVerb] forced [npc.namePos] [npc.cock] over [npc2.her] [npc2.asshole+].");
									break;
								case SUB_RESISTING:
									sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.ass] used against [npc2.her] will.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] hotdogging [npc2.namePos] ass.");
						}
						break;
					case ARMPITS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Getting [npc2.name] to hold [npc2.her] [npc2.arm] up, [npc.name] [npc.was] able to slide [npc.her] [npc.cock+] up and down over [npc2.her] armpit.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] forced to slide [npc.her] [npc.cock+] up and down over [npc2.namePos] armpit.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Holding [npc2.her] [npc2.arm] up, [npc2.name] [npc2.was] soon [npc2.moaning] as [npc2.she] [npc2.sexPaceVerb] forced [npc.namePos] [npc.cock] over [npc2.her] [npc2.armpit+].");
									break;
								case SUB_RESISTING:
									sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.armpits] fucked against [npc2.her] will.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] sliding [npc.her] [npc.cock+] over [npc2.namePos] armpit.");
						}
						break;
					case BREAST:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									if(paizuri) {
										sb.append("Pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.was] able to slide [npc.her] [npc.cock+] up and down between them.");
									} else {
										sb.append("[npc2.NamePos] chest was too flat for [npc.name] to fuck, but that didn't stop [npc.herHim] from sliding [npc.her] [npc.cock+] up and down over [npc2.her] [npc2.breasts+].");
									}
									break;
								case SUB_RESISTING:
									if(paizuri) {
										sb.append("Pushing [npc2.her] [npc2.breasts+] together, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.cock+] up and down between them.");
									} else {
										sb.append("Not put off by the fact that [npc2.her] chest is flat,"
												+ " [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.cock+] up and down over [npc2.her] [npc2.breasts+].");
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
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed paizuri on [npc.name].");
									} else {
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed naizuri on [npc.name].");
									}
									break;
								case SUB_RESISTING:
									if(paizuri) {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing paizuri on [npc.name].");
									} else {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing naizuri on [npc.name].");
									}
									break;
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.breasts+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] grinding [npc.her] [npc.cock] against [npc2.namePos] flat chest.");
							}
						}
						break;
					case BREAST_CROTCH:
						boolean crotchPaizuri = target.isBreastCrotchFuckablePaizuri();
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									if(crotchPaizuri) {
										sb.append("Pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.was] able to slide [npc.her] [npc.cock+] up and down between them.");
									} else {
										sb.append("[npc2.NamePos] [npc2.crotchBoobs] were too flat for [npc.name] to fuck, but that didn't stop [npc.herHim] from sliding [npc.her] [npc.cock+] up and down over them.");
									}
									break;
								case SUB_RESISTING:
									if(crotchPaizuri) {
										sb.append("Pushing [npc2.her] [npc2.crotchBoobs+] together, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.cock+] up and down between them.");
									} else {
										sb.append("Not put off by the fact that [npc2.her] [npc2.crotchBoobs] are flat, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.cock+] up and down over them.");
									}
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									if(crotchPaizuri) {
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed crotch-paizuri on [npc.name].");
									} else {
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed crotch-naizuri on [npc.name].");
									}
									break;
								case SUB_RESISTING:
									if(crotchPaizuri) {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing crotch-paizuri on [npc.name].");
									} else {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing crotch-naizuri on [npc.name].");
									}
									break;
							}
							
						} else {
							if(crotchPaizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.crotchBoobs+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] grinding [npc.her] [npc.cock] against [npc2.namePos] flat [npc2.crotchBoobs].");
							}
						}
						break;
					case MOUTH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed the [npc.cockHead] of [npc.her] [npc.cock+] against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing forwards into [npc2.her] mouth.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from wrapping [npc2.her] [npc2.lips+] around [npc.her] [npc.cock] and taking it into [npc2.her] mouth.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] sucking and licking the [npc.cock+] in front of [npc2.herHim], [npc2.name] kept on letting out muffled [npc2.moans] as [npc2.she] gave [npc.name] a blowjob.");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried to pull away from the [npc.cock+] in front of [npc2.herHim], but ended up being forced to give [npc.name] a blowjob.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] face.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the [npc.cockHead+] of [npc.her] [npc.cock] against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and fucking [npc2.her] [npc2.breast+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and fuck [npc2.her] [npc2.nipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.nipple(true)].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.cock+] thrust in and out of [npc2.her] [npc2.nipple+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the [npc.cockHead+] of [npc.her] [npc.cock] against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and fucking [npc2.her] [npc2.crotchBoob+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and fuck [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.crotchNipple].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.cock+] thrust in and out of [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.was] able to slide [npc.her] [npc.cock+] in and out between [npc2.her] thighs.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] forced to slide [npc.her] [npc.cock+] in and out between [npc2.namePos] thighs.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Lifting and dropping [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] soon [npc2.moaning] as [npc2.she] [npc2.sexPaceVerb] forced [npc.namePos] [npc.cock] between [npc2.her] [npc2.legs+].");
									break;
								case SUB_RESISTING:
									sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] thighs fucked against [npc2.her] will.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] thighs.");
						}
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] own [npc2.cock], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.penisUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] cock's [npc2.penisUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.penisUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.cock+] thrust in and out of [npc2.her] [npc2.penisUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.vaginaUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.cock+] deep into [npc2.her] [npc2.vaginaUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.cock+] thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.pussy+] before starting to fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.pussy+] before starting to forcefully fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.cock+] from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.cock+].");
									break;
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] bucked [npc2.her] hips to help drive [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.pussy+].");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] [npc2.pussy+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.cock+] into [npc2.her] web-spinning orifice before starting to fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.cock+] into [npc2.her] web-spinning orifice before starting to forcefully fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.cock+] from being grabbed by [npc2.name] and guided into [npc2.her] spinneret.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in using [npc.her] spinneret to [npc2.sexPaceVerb] ride [npc.namePos] [npc.cock+].");
									break;
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] drove [npc.namePos] [npc.cock+] deep into [npc2.her] spinneret.");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] spinneret.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	CLIT(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "clit";
			}
			return owner.getClitorisName(false);
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getVaginaRawClitorisSizeValue();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
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
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.CLIT getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaseVerb] rubbed [npc.her] [npc.pussy+] against [npc2.namePos], [npc.moaning+] as [npc.her] [npc.clit+] bumped against [npc2.hers].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to pull away as [npc2.name] started rubbing [npc2.her] [npc2.pussy+] against [npc.hers], but [npc.her] efforts proved to be futile.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] soon started letting out [npc2.moans+] as [npc2.she] [npc2.sexPaceVerb] reciprocated [npc.namePos] movements and tribbed with [npc.herHim].");
									break;
								case SUB_RESISTING:
									sb.append(" Begging to be left alone, [npc2.name] desperately tried to struggle free, but [npc2.was] unable to stop [npc.name] from tribbing with [npc2.herHim].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tribbing with [npc2.name].");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.clit+] against [npc2.namePos] [npc2.asshole+], before thrusting forwards and starting to [npc.sexPaceVerb] fuck [npc2.her] [npc2.ass+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] pressed [npc2.her] [npc2.asshole+] back against [npc.her] [npc.clit+],"
											+ " and cried out in despair as [npc2.she] thrust back and forced [npc.herHim] to start fucking [npc2.her] [npc2.ass+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink [npc.namePos] [npc.clit+] deep into [npc2.her] [npc2.asshole].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.clit+] thrust in and out of [npc2.her] [npc2.asshole+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pushing [npc2.namePos] ass cheeks together, [npc.name] [npc.was] able to slide [npc.her] [npc.clit+] up and down between them.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] forced to slide [npc.her] [npc.clit+] up and down between [npc2.namePos] ass cheeks.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Lifting and dropping [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] soon [npc2.moaning] as [npc2.she] [npc2.sexPaceVerb] forced [npc.namePos] [npc.clit] over [npc2.her] [npc2.asshole+].");
									break;
								case SUB_RESISTING:
									sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.ass] used against [npc2.her] will.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-hotdogging [npc2.namePos] ass.");
						}
						break;
					case BREAST:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									if(paizuri) {
										sb.append("Pushing [npc2.namePos] [npc2.breasts+] together, [npc.name] [npc.was] able to slide [npc.her] [npc.clit+] up and down between them.");
									} else {
										sb.append("[npc2.NamePos] chest was too flat for [npc.name] to fuck, but that didn't stop [npc.herHim] from sliding [npc.her] [npc.clit+] up and down over [npc2.her] [npc2.breasts+].");
									}
									break;
								case SUB_RESISTING:
									if(paizuri) {
										sb.append("Pushing [npc2.her] [npc2.breasts+] together, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.clit+] up and down between them.");
									} else {
										sb.append("Not put off by the fact that [npc2.her] chest is flat,"
												+ " [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.clit+] up and down over [npc2.her] [npc2.breasts+].");
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
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed paizuri on [npc.name].");
									} else {
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed naizuri on [npc.name].");
									}
									break;
								case SUB_RESISTING:
									if(paizuri) {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing paizuri on [npc.name].");
									} else {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing naizuri on [npc.name].");
									}
									break;
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.breasts+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] grinding [npc.her] [npc.clit] against [npc2.namePos] flat chest.");
							}
						}
						break;
					case BREAST_CROTCH:
						boolean crotchPaizuri = target.isBreastCrotchFuckablePaizuri();
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									if(crotchPaizuri) {
										sb.append("Pushing [npc2.namePos] [npc2.crotchBoobs+] together, [npc.name] [npc.was] able to slide [npc.her] [npc.clit+] up and down between them.");
									} else {
										sb.append("[npc2.NamePos] [npc2.crotchBoobs] were too flat for [npc.name] to fuck, but that didn't stop [npc.herHim] from sliding [npc.her] [npc.clit+] up and down over them.");
									}
									break;
								case SUB_RESISTING:
									if(crotchPaizuri) {
										sb.append("Pushing [npc2.her] [npc2.crotchBoobs+] together, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.clit+] up and down between them.");
									} else {
										sb.append("Not put off by the fact that [npc2.her] [npc2.crotchBoobs] are flat, [npc2.name] ignored [npc.namePos] protesting cries and forced [npc.herHim] to slide [npc.her] [npc.clit+] up and down over them.");
									}
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									if(crotchPaizuri) {
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed crotch-paizuri on [npc.name].");
									} else {
										sb.append(" Letting out a series of [npc2.moans], [npc2.name] happily performed crotch-naizuri on [npc.name].");
									}
									break;
								case SUB_RESISTING:
									if(crotchPaizuri) {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing crotch-paizuri on [npc.name].");
									} else {
										sb.append(" Sobbing and crying, [npc2.name] tried, and failed, to resist performing crotch-naizuri on [npc.name].");
									}
									break;
							}
							
						} else {
							if(crotchPaizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.crotchBoobs+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] grinding [npc.her] [npc.clit] against [npc2.namePos] flat [npc2.crotchBoobs].");
							}
						}
						break;
					case MOUTH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.clit+] against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing forwards into [npc2.her] mouth.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from wrapping [npc2.her] [npc2.lips+] around [npc.her] [npc.clit] and taking it into [npc2.her] mouth.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] sucking and licking the [npc.clit+] in front of [npc2.herHim], [npc2.name] kept on letting out muffled [npc2.moans] as [npc2.she] gave [npc.name] a blowjob.");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried to pull away from the [npc.clit+] in front of [npc2.herHim], but ended up being forced to give [npc.name] a blowjob.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] face.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.clit] against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and fucking [npc2.her] [npc2.breast+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and fuck [npc2.her] [npc2.nipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.clit+] deep into [npc2.her] [npc2.nipple(true)].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.clit+] thrust in and out of [npc2.her] [npc2.nipple+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.clit] against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and fucking [npc2.her] [npc2.crotchBoob+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and fuck [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.clit+] deep into [npc2.her] [npc2.crotchNipple].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.clit+] thrust in and out of [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pushing [npc2.namePos] [npc2.legs+] together, [npc.name] [npc.was] able to slide [npc.her] [npc.clit+] in and out between [npc2.her] thighs.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] forced to slide [npc.her] [npc.clit+] in and out between [npc2.namePos] thighs.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Lifting and dropping [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] soon [npc2.moaning] as [npc2.she] [npc2.sexPaceVerb] forced [npc.namePos] [npc.clit] between [npc2.her] [npc2.legs+].");
									break;
								case SUB_RESISTING:
									sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] thighs fucked against [npc2.her] will.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] thighs.");
						}
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing [npc.her] [npc.clit] against [npc2.namePos] own [npc2.clit], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.penisUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] clit's [npc2.penisUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.clit+] deep into [npc2.her] [npc2.penisUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.clit+] thrust in and out of [npc2.her] [npc2.penisUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing [npc.her] [npc.clit] against [npc2.namePos] own [npc2.clit], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.vaginaUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.clit+] deep into [npc2.her] [npc2.vaginaUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.clit+] thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding [npc.her] [npc.clit] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.clit+] into [npc2.her] [npc2.pussy+] before starting to fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding [npc.her] [npc.clit] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.clit+] into [npc2.her] [npc2.pussy+] before starting to forcefully fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.clit+] from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.clit+].");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] [npc2.pussy+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding [npc.her] [npc.clit] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.clit+] into [npc2.her] web-spinning orifice before starting to fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding [npc.her] [npc.clit] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.clit+] into [npc2.her] web-spinning orifice before starting to forcefully fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.clit+] from being grabbed by [npc2.name] and guided into [npc2.her] spinneret.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in using [npc.her] spinneret to [npc2.sexPaceVerb] ride [npc.namePos] [npc.clit+].");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] spinneret.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TONGUE(2, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "tongue";
			}
			return owner.getTongueName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getTongueLengthValue();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, SexAreaOrifice.MOUTH) && Main.sex.isPenetrationTypeFree(owner, this);
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
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.TONGUE getSexDescription() error: Does not support self actions!");
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
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.armpits+], before starting to [npc.sexPaceVerb] kiss and lick them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.armpits+] and forcing [npc.herHim] to kiss and lick them.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out muffled [npc2.moans], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.armpits] into [npc.namePos] [npc.face] and encouraged [npc.herHim] to continue orally pleasuring them.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc2.was] unable to stop [npc.name] from pressing [npc.her] [npc.face] into [npc2.her] [npc2.armpits].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] [npc2.armpits].");
						}
						break;
					case ANUS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.ass], planting a kiss on [npc2.her] [npc2.asshole+] before starting to [npc.sexPaceVerb] perform anilingus on [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried as [npc2.name] planted [npc2.her] [npc2.ass+] on [npc.her] [npc.face] and forced [npc.herHim] to perform anilingus on [npc2.herHim].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out a series of [npc2.moans+], [npc2.name] pushed [npc2.her] [npc2.hips] back into [npc.namePos] [npc.face], helping to drive [npc.her] [npc.tongue] deep into [npc2.her] [npc2.asshole].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull [npc2.her] [npc2.ass] away from [npc.namePos] unwelcome [npc.tongue], but [npc2.her] efforts proved to be in vain.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] performing anilingus on [npc2.name].");
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.breasts+], before starting to [npc.sexPaceVerb] kiss and lick them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.breasts+] and forcing [npc.herHim] to kiss and lick them.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out muffled [npc2.moans], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.breasts] into [npc.namePos] [npc.face] and encouraged [npc.herHim] to continue orally pleasuring them.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc2.was] unable to stop [npc.name] from pressing [npc.her] [npc.face] into [npc2.her] [npc2.breasts].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] [npc2.breasts].");
						}
						break;
					case BREAST_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoobs+], before starting to [npc.sexPaceVerb] kiss and lick them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.crotchBoobs+] and forcing [npc.herHim] to kiss and lick them.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out muffled [npc2.moans], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.crotchBoobs] into [npc.namePos] [npc.face] and encouraged [npc.herHim] to continue orally pleasuring them.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc2.was] unable to stop [npc.name] from pressing [npc.her] [npc.face] into [npc2.her] [npc2.crotchBoobs].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] [npc2.crotchBoobs].");
						}
						break;
					case MOUTH:
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
					case NIPPLE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.breasts], before starting to [npc.sexPaceVerb] kiss and suck on [npc2.her] [npc2.nipples+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.breasts] and [npc2.sexPaceVerb] making [npc.herHim] kiss [npc2.her] [npc2.nipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] chest out into [npc.namePos] [npc.face] and begged [npc.herHim] to continue playing with [npc2.her] [npc2.nipples+].");
									break;
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] chest out into [npc.namePos] [npc.face] and ordered [npc.herHim] to continue playing with [npc2.her] [npc2.nipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from continuing to play with [npc2.her] [npc2.nipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] [npc2.nipples].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.crotchBoobs], before starting to [npc.sexPaceVerb] kiss and suck on [npc2.her] [npc2.crotchNipples+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist,"
											+ " but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.crotchBoobs] and [npc2.sexPaceVerb] making [npc.herHim] kiss [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Letting out [npc2.moans+],"
											+ " [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.crotchBoobs] out into [npc.namePos] [npc.face] and begged [npc.herHim] to continue playing with [npc2.her] [npc2.crotchNipples+].");
									break;
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+],"
											+ " [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.crotchBoobs] out into [npc.namePos] [npc.face] and ordered [npc.herHim] to continue playing with [npc2.her] [npc2.crotchNipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from continuing to play with [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] [npc2.crotchNipples].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] [npc2.labia+], before [npc.sexPaceVerb] licking [npc2.her] [npc2.clit] and starting to eat [npc2.herHim] out.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from planting [npc2.her] [npc2.labia+] over [npc.her] [npc.lips] and making [npc.herHim] eat [npc2.hreHim] out.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.Moaning+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], making [npc.herHim] drive [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+].");
									break;
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+], [npc2.name] roughly slammed [npc2.her] [npc2.hips] into [npc.namePos] [npc.face], forcing [npc.herHim] drive [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away from [npc.name], but [npc2.was] unable to stop [npc.herHim] from driving [npc.her] [npc.tongue] deep into [npc2.her] [npc2.pussy+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] [npc2.vagina].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.lips+] against [npc2.namePos] spinneret, before [npc.sexPaceVerb] licking it and starting to eat [npc2.herHim] out.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from planting [npc2.her] spinneret over [npc.her] [npc.lips] and making [npc.herHim] eat [npc2.hreHim] out.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.Moaning+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] spinneret into [npc.namePos] [npc.face], making [npc.herHim] drive [npc.her] [npc.tongue] deep into [npc2.her] web-spinning orifice.");
									break;
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+], [npc2.name] roughly slammed [npc2.her] spinneret into [npc.namePos] [npc.face], forcing [npc.herHim] drive [npc.her] [npc.tongue] deep into [npc2.her] web-spinning orifice.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away from [npc.name], but [npc2.was] unable to stop [npc.herHim] from driving [npc.her] [npc.tongue] deep into [npc2.her] spinneret.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] kissing [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	FINGER(1, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "fingers";
			}
			return owner.getArmType().getFingersNamePlural(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			System.err.println("Warning: Finger length is being called!");
			return 8;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.FINGER;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.FINGER getSexDescription() error: Does not support self actions!");
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
									sb.append("[npc.SexPaceVerb] wrapping [npc.her] [npc.fingers] around [npc2.namePos] [npc2.hand+], [npc.name] started holding [npc2.her] hand.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] unable to stop [npc2.name] from holding [npc.her] [npc.hand].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] squeezing [npc2.her] [npc2.fingers+] around [npc.namePos] [npc.hand], [npc2.name] started letting out [npc2.moans+] as [npc2.she] enjoyed the hand holding.");
									break;
								case SUB_RESISTING:
									sb.append(" Desperately trying to pull away, [npc2.name] begged for [npc.name] to leave [npc2.herHim] alone,"
											+ " but [npc2.her] efforts proved to be in vain, and [npc2.her] [npc2.hand] continued to be held.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] holding [npc2.namePos] [npc2.hand].");
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
									sb.append("[npc.SexPaceVerb] wrapping [npc.her] [npc.fingers] around [npc2.namePos] [npc2.cock+], [npc.name] started giving [npc2.herHim] a handjob.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] unable to stop [npc2.name] from forcing [npc.herHim] to give [npc2.herHim] a handjob.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.hips] in time with [npc.namePos] movements, [npc2.name] started letting out [npc2.moans+] as [npc2.her] [npc2.cock] was stroked and played with.");
									break;
								case SUB_RESISTING:
									sb.append(" Desperately trying to pull away, [npc2.name] begged for [npc.name] to leave [npc2.herHim] alone,"
											+ " but [npc2.her] efforts proved to be in vain, and [npc2.her] [npc2.cock] continued to be stroked and played with.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] giving a handjob to [npc2.name].");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Grabbing [npc2.namePos] [npc2.ass], [npc.name] ran [npc.her] [npc.fingers+] down to [npc2.her] [npc2.asshole+] and, slipping them inside, started to [npc.sexPaceVerb] finger [npc2.her] [npc2.ass].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried as [npc2.name] grabbed hold of [npc.her] [npc.hand] and forced [npc.her] [npc.fingers] into [npc2.her] [npc2.asshole+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out a series of [npc2.moans+], [npc2.name] pushed [npc2.her] [npc2.hips] back against [npc.namePos] touch, helping to drive [npc.her] [npc.fingers] deep into [npc2.her] [npc2.asshole].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull [npc2.her] [npc2.ass] away from [npc.namePos] unwelcome [npc.fingers], but [npc2.her] efforts proved to be in vain.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fingering [npc2.namePos] [npc2.asshole].");
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.hands+] into [npc2.namePos] [npc2.breasts], before starting to [npc.sexPaceVerb] grope and squeeze them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist,"
											+ " but [npc.was] unable to stop [npc2.name] from pulling [npc.her] [npc.hands] into [npc2.her] [npc2.breasts] and [npc2.sexPaceVerb] making [npc.herHim] grope and squeeze them.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] chest out against [npc.namePos] touch and begged [npc.herHim] to continue playing with [npc2.her] [npc2.breasts+].");
									break;
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] chest out against [npc.namePos] touch and ordered [npc.herHim] to continue playing with [npc2.her] [npc2.breasts+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from continuing to play with [npc2.her] [npc2.breasts+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] groping and squeezing [npc2.namePos] [npc2.breasts].");
						}
						break;
					case BREAST_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.hands+] into [npc2.namePos] [npc2.crotchBoobs], before starting to [npc.sexPaceVerb] grope and squeeze them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist,"
											+ " but [npc.was] unable to stop [npc2.name] from pulling [npc.her] [npc.hands] into [npc2.her] [npc2.crotchBoobs] and [npc2.sexPaceVerb] making [npc.herHim] grope and squeeze them.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] groin out against [npc.namePos] touch and begged [npc.herHim] to continue playing with [npc2.her] [npc2.crotchBoobs+].");
									break;
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] groin out against [npc.namePos] touch and ordered [npc.herHim] to continue playing with [npc2.her] [npc2.crotchBoobs+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from continuing to play with [npc2.her] [npc2.crotchBoobs+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] groping and squeezing [npc2.namePos] [npc2.crotchBoobs].");
						}
						break;
					case MOUTH:
						break;
					case NIPPLE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.hands+] into [npc2.namePos] [npc2.breasts], before homing in on [npc2.her] [npc2.nipples+] and starting to [npc.sexPaceVerb] pinch and squeeze them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist,"
											+ " but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.breasts] and [npc2.sexPaceVerb] making [npc.herHim] pinch and play with [npc2.her] [npc2.nipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] chest out against [npc.namePos] touch and begged [npc.herHim] to continue playing with [npc2.her] [npc2.nipples+].");
									break;
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] chest out against [npc.namePos] touch and ordered [npc.herHim] to continue playing with [npc2.her] [npc2.nipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from continuing to play with [npc2.her] [npc2.nipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] playfully pinching with [npc2.namePos] [npc2.nipples].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.hands+] into [npc2.namePos] [npc2.crotchBoobs], before homing in on [npc2.her] [npc2.crotchNipples+] and starting to [npc.sexPaceVerb] pinch and squeeze them.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist,"
											+ " but [npc.was] unable to stop [npc2.name] from pulling [npc.herHim] into [npc2.her] [npc2.crotchBoobs] and [npc2.sexPaceVerb] making [npc.herHim] pinch and play with [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Letting out [npc2.moans+],"
											+ " [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.crotchBoobs] out against [npc.namePos] touch and begged [npc.herHim] to continue playing with [npc2.her] [npc2.crotchNipples+].");
									break;
								case DOM_ROUGH:
									sb.append(" Letting out [npc2.moans+],"
											+ " [npc2.name] [npc2.sexPaceVerb] pushed [npc2.her] [npc2.crotchBoobs] out against [npc.namePos] touch and ordered [npc.herHim] to continue playing with [npc2.her] [npc2.crotchNipples+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to resist, but [npc2.was] unable to stop [npc.name] from continuing to play with [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] playfully pinching with [npc2.namePos] [npc2.crotchNipples].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Dropping [npc.her] hands down between [npc2.namePos] [npc2.legs], [npc.name] ran [npc.her] [npc.fingers+] over [npc2.her] [npc2.labia+] and,"
											+ " slipping them inside, started to [npc.sexPaceVerb] finger [npc2.her] [npc2.pussy+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried as [npc2.name] grabbed hold of [npc.her] [npc.hand] and forced [npc.her] [npc.fingers] into [npc2.her] [npc2.pussy+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out a series of [npc2.moans+], [npc2.name] pushed [npc2.her] [npc2.hips] back against [npc.namePos] touch, helping to drive [npc.her] [npc.fingers] deep into [npc2.her] [npc2.pussy+].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull [npc2.her] [npc2.hips] away from [npc.namePos] unwelcome [npc.fingers], but [npc2.her] efforts proved to be in vain.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fingering [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Dropping [npc.her] hands down towards [npc2.namePos] spinneret, [npc.name] ran [npc.her] [npc.fingers+] over [npc2.her] web-spinning orifice and,"
											+ " slipping them inside, started to [npc.sexPaceVerb] finger [npc2.herHim]");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried as [npc2.name] grabbed hold of [npc.her] [npc.hand] and forced [npc.her] [npc.fingers] into [npc2.her] spinneret.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" Letting out a series of [npc2.moans+], [npc2.name] pushed [npc2.her] spinneret back against [npc.namePos] touch, helping to drive [npc.her] [npc.fingers] deep into [npc2.her] web-spinning orifice.");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull [npc2.her] spinneret away from [npc.namePos] unwelcome [npc.fingers], but [npc2.her] efforts proved to be in vain.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fingering [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	FOOT(1, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "foot";
			}
			return owner.getLegType().getFootNameSingular(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			System.err.println("Warning: Foot length is being called!");
			return 8;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.FOOT;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.FOOT getSexDescription() error: Does not support self actions!");
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
									sb.append("[npc.SexPaceVerb] pressing [npc.her] [npc.feet+] against [npc2.namePos] [npc2.cock+], [npc.name] started giving [npc2.herHim] [npc.a_footjob].");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] unable to stop [npc2.name] from forcing [npc.herHim] to give [npc2.herHim] [npc.a_footjob].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.hips] in time with [npc.namePos] movements, [npc2.name] started letting out [npc2.moans+] as [npc2.she] fucked [npc.her] [npc.feet].");
									break;
								case SUB_RESISTING:
									sb.append(" Desperately trying to pull away from [npc.namePos] [npc.feet], [npc2.name] begged to be left alone, but [npc2.her] efforts proved to be in vain.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] giving [npc.a_footjob] to [npc2.name].");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.SexPaceVerb] pressing [npc.her] [npc.feet+] against [npc2.namePos] face, [npc.name] got [npc2.herHim] to orally worship them.");
									break;
								case SUB_RESISTING:
									sb.append("Although [npc.she] tried to resist, [npc.name] [npc.was] unable to stop [npc2.name] from orally worshipping [npc.her] [npc.feet+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] soon started letting out [npc2.moans+] as [npc2.she] [npc2.sexPaceVerb] licked and kissed [npc.namePos] [npc.feet+].");
									break;
								case SUB_RESISTING:
									sb.append(" Desperately trying to pull away from [npc.name], [npc2.name] tried, and failed, to stop orally servicing [npc.her] [npc.feet].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] getting [npc2.name] to orally worship [npc.her] [npc.feet+].");
						}
						break;
					case NIPPLE:
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						break;
					case SPINNERET:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TAIL(2, -1f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName || owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return "tail";
			}
			return owner.getTailName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return owner.getLegTailLength(penetrationLength);
			}
			return owner.getTailLength(penetrationLength);
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return owner.getLegTailDiameter(atLength);
			}
			return owner.getTailDiameter(atLength);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TAIL;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.TAIL getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("[npc.Name] [npc.sexPaceVerb] rubbed [npc.her] [npc.tail+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit],"
											+ " teasing [npc2.herHim] with the promise of penetration.");
									break;
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] forced [npc.her] [npc.tail+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit],"
											+ " cruelly teasing [npc2.herHim] with the promise of penetration.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist as [npc.she] [npc.was] forced to rub [npc.her] [npc.tail+] over [npc2.namePos] [npc2.pussy] and [npc2.clit].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Before too long, [npc2.name] found [npc2.herself] desperately turned-on and [npc2.was] [npc2.moaning] for [npc.name] to start tail-fucking [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append(" Before too long,"
											+ " [npc2.name] found [npc2.herself] desperately turned-on and [npc2.was] ordering [npc.name] to hold still as [npc2.she] ground [npc2.her] [npc2.clit+] against [npc.namePos] [npc.tail+].");
									break;
								case SUB_RESISTING:
									sb.append(" Crying and pleading to be left alone, [npc2.name] desperately tried to struggle free, but to no avail.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] rubbing [npc.her] [npc.tail+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit].");
						}
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
									sb.append("[npc.Name] [npc.sexPaceVerb] wrapped [npc.her] [npc.tail+] around [npc2.namePos] [npc2.cock], before starting to give [npc2.herHim] a tailjob.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist as [npc.she] [npc.was] forced wrap [npc.her] [npc.tail+] around [npc2.namePos] [npc2.cock] and give [npc2.herHim] a tailjob.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] couldn't help but let out a series of lewd [npc2.moans] as [npc2.her] [npc2.cock+] was used in such a manner.");
									break;
								case SUB_RESISTING:
									sb.append(" Crying and pleading to be left alone, [npc2.name] desperately tried, and failed, to pull away from [npc.name].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] giving [npc2.name] a tailjob.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.tail+] against [npc2.namePos] [npc2.asshole+], before pushing forwards and starting to [npc.sexPaceVerb] tail-fuck [npc2.her] [npc2.ass+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] grabbed [npc.her] [npc.tail] and forced [npc.herHim] to start tail-fucking [npc2.her] [npc2.ass+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink [npc.namePos] [npc.tail+] deep into [npc2.her] [npc2.asshole].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tail+] thrust in and out of [npc2.her] [npc2.asshole+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.tail+] against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing forwards and thrusting it into [npc2.her] mouth.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from wrapping [npc2.her] [npc2.lips+] around [npc.her] [npc.tail] and taking it into [npc2.her] mouth.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] kept on letting out muffled [npc2.moans] as [npc2.she] [npc2.sexPaceVerb] sucked and licked the [npc.tail+] in front of [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried, and failed, to pull away from the [npc.tail+] in front of [npc2.herHim].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] face.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.tail] against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and tail-fucking [npc2.her] [npc2.breast+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and tail-fuck [npc2.her] [npc2.nipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.tail+] deep into [npc2.her] [npc2.nipple(true)].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tail+] thrust in and out of [npc2.her] [npc2.nipple+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.tail] against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and tail-fucking [npc2.her] [npc2.crotchBoob+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and tail-fuck [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.tail+] deep into [npc2.her] [npc2.crotchNipple].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tail+] thrust in and out of [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing [npc.her] [npc.tail] against the [npc2.cockHead] of [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] thrust forwards and started tail-fucking [npc2.her] [npc2.penisUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start tail-fucking [npc2.her] cock's [npc2.penisUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.her] [npc2.penisUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tail+] thrust in and out of [npc2.her] [npc2.penisUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing [npc.her] [npc.tail] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] thrust forwards and started tail-fucking [npc2.her] [npc2.vaginaUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start tail-fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.tail+] deep into [npc2.her] [npc2.vaginaUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tail+] thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding [npc.her] [npc.tail] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.tail+] into [npc2.her] [npc2.pussy+] before starting to tail-fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding [npc.her] [npc.tail] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.tail+] into [npc2.her] [npc2.pussy+] before starting to forcefully tail-fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.tail+] from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.tail+].");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from tail-fucking [npc2.her] [npc2.pussy+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding [npc.her] [npc.tail] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.tail+] into [npc2.her] web-spinning orifice before starting to tail-fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding [npc.her] [npc.tail] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.tail+] into [npc2.her] web-spinning orifice before starting to forcefully tail-fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.tail+] from being grabbed by [npc2.name] and guided into [npc2.her] spinneret.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in using [npc.her] spinneret to [npc2.sexPaceVerb] ride [npc.namePos] [npc.tail+].");
									break;
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] drove [npc.namePos] [npc.tail+] deep into [npc2.her] spinneret.");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from tail-fucking [npc2.her] spinneret.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tail-fucking [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TENTACLE(3, -1.5f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "tentacle";
			}
			return owner.getTentacleName(false);
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getTentacleLength(penetrationLength);
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			return owner.getTentacleDiameter(atLength);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				System.err.println("SexAreaPenetration.TENTACLE getSexDescription() error: Does not support self actions!");
				return "";
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("[npc.Name] [npc.sexPaceVerb] rubbed [npc.her] [npc.tentacle+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit],"
											+ " teasing [npc2.herHim] with the promise of penetration.");
									break;
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] forced [npc.her] [npc.tentacle+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit],"
											+ " cruelly teasing [npc2.herHim] with the promise of penetration.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist as [npc.she] [npc.was] forced to rub [npc.her] [npc.tentacle+] over [npc2.namePos] [npc2.pussy] and [npc2.clit].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" Before too long, [npc2.name] found [npc2.herself] desperately turned-on and [npc2.was] [npc2.moaning] for [npc.name] to start tentacle-fucking [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append(" Before too long,"
											+ " [npc2.name] found [npc2.herself] desperately turned-on and [npc2.was] ordering [npc.name] to hold still as [npc2.she] ground [npc2.her] [npc2.clit+] against [npc.namePos] [npc.tentacle+].");
									break;
								case SUB_RESISTING:
									sb.append(" Crying and pleading to be left alone, [npc2.name] desperately tried to struggle free, but to no avail.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] rubbing [npc.her] [npc.tentacle+] between [npc2.namePos] [npc2.labia] and over [npc2.her] [npc2.clit].");
						}
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
									sb.append("[npc.Name] [npc.sexPaceVerb] wrapped [npc.her] [npc.tentacle+] around [npc2.namePos] [npc2.cock], before starting to give [npc2.herHim] a tentaclejob.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist as [npc.she] [npc.was] forced wrap [npc.her] [npc.tentacle+] around [npc2.namePos] [npc2.cock] and give [npc2.herHim] a tentaclejob.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] couldn't help but let out a series of lewd [npc2.moans] as [npc2.her] [npc2.cock+] was used in such a manner.");
									break;
								case SUB_RESISTING:
									sb.append(" Crying and pleading to be left alone, [npc2.name] desperately tried, and failed, to pull away from [npc.name].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] giving [npc2.name] a tentaclejob.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.tentacle+] against [npc2.namePos] [npc2.asshole+], before pushing forwards and starting to [npc.sexPaceVerb] tentacle-fuck [npc2.her] [npc2.ass+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] grabbed [npc.her] [npc.tentacle] and forced [npc.herHim] to start tentacle-fucking [npc2.her] [npc2.ass+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink [npc.namePos] [npc.tentacle+] deep into [npc2.her] [npc2.asshole].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tentacle+] thrust in and out of [npc2.her] [npc2.asshole+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] pressed [npc.her] [npc.tentacle+] against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing forwards and thrusting it into [npc2.her] mouth.");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from wrapping [npc2.her] [npc2.lips+] around [npc.her] [npc.tentacle] and taking it into [npc2.her] mouth.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] kept on letting out muffled [npc2.moans] as [npc2.she] [npc2.sexPaceVerb] sucked and licked the [npc.tentacle+] in front of [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried, and failed, to pull away from the [npc.tentacle+] in front of [npc2.herHim].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] face.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.tentacle] against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and tentacle-fucking [npc2.her] [npc2.breast+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and tentacle-fuck [npc2.her] [npc2.nipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.tentacle+] deep into [npc2.her] [npc2.nipple(true)].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tentacle+] thrust in and out of [npc2.her] [npc2.nipple+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("[npc.Name] [npc.sexPaceVerb] pushed [npc.her] [npc.tentacle] against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and tentacle-fucking [npc2.her] [npc2.crotchBoob+].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and tentacle-fuck [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink [npc.namePos] [npc.tentacle+] deep into [npc2.her] [npc2.crotchNipple].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tentacle+] thrust in and out of [npc2.her] [npc2.crotchNipples+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing [npc.her] [npc.tentacle] against the [npc2.cockHead] of [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] thrust forwards and started tentacle-fucking [npc2.her] [npc2.penisUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start tentacle-fucking [npc2.her] cock's [npc2.penisUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.tentacle+] deep into [npc2.her] [npc2.penisUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tentacle+] thrust in and out of [npc2.her] [npc2.penisUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append("Pressing [npc.her] [npc.tentacle] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] thrust forwards and started tentacle-fucking [npc2.her] [npc2.vaginaUrethra+].");
									break;
								case SUB_RESISTING:
									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start tentacle-fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink [npc.her] [npc.tentacle+] deep into [npc2.her] [npc2.vaginaUrethra].");
									break;
								case SUB_RESISTING:
									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while [npc.her] [npc.tentacle+] thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding [npc.her] [npc.tentacle] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.tentacle+] into [npc2.her] [npc2.pussy+] before starting to tentacle-fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding [npc.her] [npc.tentacle] up and down between [npc2.namePos] [npc2.labia+],"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.tentacle+] into [npc2.her] [npc2.pussy+] before starting to forcefully tentacle-fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.tentacle+] from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.tentacle+].");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from tentacle-fucking [npc2.her] [npc2.pussy+].");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							switch(performerPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append("Sliding [npc.her] [npc.tentacle] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.tentacle+] into [npc2.her] web-spinning orifice before starting to tentacle-fuck [npc2.herHim].");
									break;
								case DOM_ROUGH:
									sb.append("Roughly grinding [npc.her] [npc.tentacle] up and down over [npc2.namePos] spinneret,"
											+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.tentacle+] into [npc2.her] web-spinning orifice before starting to forcefully tentacle-fuck [npc2.herHim].");
									break;
								case SUB_RESISTING:
									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.tentacle+] from being grabbed by [npc2.name] and guided into [npc2.her] spinneret.");
									break;
							}
							switch(targetPace) {
								case DOM_GENTLE:
								case DOM_NORMAL:
								case DOM_ROUGH:
									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in using [npc.her] spinneret to [npc2.sexPaceVerb] ride [npc.namePos] [npc.tentacle+].");
									break;
								case SUB_EAGER:
								case SUB_NORMAL:
									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] drove [npc.namePos] [npc.tentacle+] deep into [npc2.her] spinneret.");
									break;
								case SUB_RESISTING:
									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from tentacle-fucking [npc2.her] spinneret.");
									break;
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] tentacle-fucking [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	};
	
//	TOY(4, -2f, false) {
//		@Override
//		public String getName(GameCharacter owner, boolean standardName) {
//			// Cannot know which orifice is being penetrated, so always return "toy":
//			return "toy";
//		}
//		@Override
//		public int getLength(GameCharacter owner, boolean penetrationLength) {
//			System.err.println("Warning: Toy length is being called!");
//			return 8;
//		}
//		@Override
//		public boolean isFree(GameCharacter owner) {
//			return Main.sex.isPenetrationTypeFree(owner, this);
//		}
//		@Override
//		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
//			return CoverableArea.NONE;
//		}
//		@Override
//		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
//			StringBuilder sb = new StringBuilder();
//			if(performer==target) {
//				System.err.println("SexAreaPenetration.TOY getSexDescription() error: Does not support self actions!");
//				return "";
//			}
//			
//			if(targetArea.isPenetration()) {
//				switch((SexAreaPenetration)targetArea) {
//					case CLIT:
//						break;
//					case FINGER:
//						break;
//					case FOOT:
//						break;
//					case PENIS:
//						break;
//					case TAIL:
//						break;
//					case TENTACLE:
//						break;
//					case TONGUE:
//						break;
//					case TOY:
//						break;
//				}
//				
//			} else {
//				switch((SexAreaOrifice)targetArea) {
//					case ASS:
//						break;
//					case BREAST:
//						break;
//					case BREAST_CROTCH:
//						break;
//					case THIGHS:
//						break;
//					case ANUS:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] pressed the tip of a toy against [npc2.namePos] [npc2.asshole+], before thrusting forwards and starting to [npc.sexPaceVerb] fuck [npc2.her] [npc2.ass+] with it.");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.ass+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
//											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.asshole].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place while [npc.she] thrust the toy in and out of [npc2.her] [npc2.asshole+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.asshole+].");
//						}
//						break;
//					case MOUTH:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] pressed the tip of a toy against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing it forwards into [npc2.her] mouth.");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] throat using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] kept on letting out muffled [npc2.moans] as [npc2.she] sucked and licked the toy.");
//									break;
//								case SUB_RESISTING:
//									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried to pull away from the toy, but ended up being forced to take it deep down [npc2.her] throat.");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] face.");
//						}
//						break;
//					case NIPPLE:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the tip of a toy against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and using it to fuck [npc2.her] [npc2.breast+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.nipples+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.nipple(true)].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while the toy thrust in and out of [npc2.her] [npc2.nipple+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.nipple+].");
//						}
//						break;
//					case NIPPLE_CROTCH:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the tip of a toy against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and using it to fuck [npc2.her] [npc2.crotchBoob+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.crotchNipples+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.crotchNipple].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while the toy thrust in and out of [npc2.her] [npc2.crotchNipple+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.crotchNipple+].");
//						}
//						break;
//					case URETHRA_PENIS:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] own [npc2.cock], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.penisUrethra+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] cock's [npc2.penisUrethra+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink a toy deep into [npc2.her] [npc2.penisUrethra].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while a toy thrust in and out of [npc2.her] [npc2.penisUrethra+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.penisUrethra+].");
//						}
//						break;
//					case URETHRA_VAGINA:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.vaginaUrethra+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink a toy deep into [npc2.her] [npc2.vaginaUrethra].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while a toy thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.vaginaUrethra+].");
//						}
//						break;
//					case VAGINA:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//									sb.append("Sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
//											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking a toy into [npc2.namePos] [npc2.pussy+] before starting to fuck [npc2.herHim].");
//									break;
//								case DOM_ROUGH:
//									sb.append("Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
//											+ " [npc.name] violently thrust forwards, sinking a toy into [npc2.namePos] [npc2.pussy+] before starting to forcefully fuck [npc2.herHim].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent a toy from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.cock+].");
//									break;
//								case SUB_EAGER:
//								case SUB_NORMAL:
//									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] bucked [npc2.her] hips to help drive [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.pussy+].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] [npc2.pussy+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.pussy+].");
//						}
//						break;
//				}
//			}
//			return UtilText.parse(performer, target, sb.toString());
//		}
//	};

	
	private float baseArousalWhenPenetrating;
	private float arousalChangePenetratingDry;
	private boolean takesVirginity;

	private SexAreaPenetration(float baseArousalWhenPenetrating, float arousalChangePenetratingDry, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.takesVirginity = takesVirginity;
	}

	@Override
	public boolean isOrifice() {
		return false;
	}
	
	public boolean appliesStretchEffects(GameCharacter owner) {
		return getDiameter(owner, 0)!=-1;
	}

	public abstract int getLength(GameCharacter owner, boolean penetrationLength);

	/** The diameter of the owner's SexAreaPenetration at the length specified, measured from the base. Diameter is the unit of measurement for all Capacity values. */
	public float getDiameter(GameCharacter owner, int atLength) {
		return -1;
	};
	
	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
}
