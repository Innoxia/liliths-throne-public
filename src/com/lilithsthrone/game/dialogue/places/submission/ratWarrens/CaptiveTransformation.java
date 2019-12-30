package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.LinkedHashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum CaptiveTransformation {
	
	MASCULINE_FETISH(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			if(!target.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Let's get yer likin' the fact that yer gonna be naked from now on!",
						target.addFetish(Fetish.FETISH_EXHIBITIONIST, true));
			}

			if(!target.hasFetish(Fetish.FETISH_SUBMISSIVE)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We don't want yer thinkin' yer anythin' but a submissive milker now, do we?",
						target.addFetish(Fetish.FETISH_SUBMISSIVE, true));
			}

			if(!target.hasFetish(Fetish.FETISH_PENIS_GIVING)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put(target.hasPenisIgnoreDildo()
							?"We're gonna want yer desperate ta be having yer cock used..."
							:"Once we've got yer ta grow a nice big cock, we're gonna want yer ta be desperate ta use it...",
						target.addFetish(Fetish.FETISH_PENIS_GIVING, true));
			}

			if(!target.hasFetish(Fetish.FETISH_CUM_STUD)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("This'll make yer love nothin' more than ta be cummin' into the milkin' machine all day long!",
						target.addFetish(Fetish.FETISH_CUM_STUD, true));
			}
			
			if(Main.game.isAnalContentEnabled() && !target.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("It's betta fer everyone involved if yer love havin' yer ass fucked!",
						target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			}

			if(!target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We're gonna want yer ta be desperate ta 'ave us transform yer into one o' our cum milkers!",
						target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			}
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 1);
			return map;
		}
	},
	
	MASCULINE_FEMININITY(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a sissy:
			boolean sissy = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
			
			if(target.hasBreasts()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Yer ain't gonna be needin' them tits o' yours anymore!"
							+(!selfTransform?"":" Shrink 'em right down so taht yer got a flat chest!"),
						target.setBreastSize(0));
			}
			
			if(target.hasBreastsCrotch()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We don't need them hangin' udders yer got!"
						+(!selfTransform?"":" Shrink 'em down an' get rid o' 'em!"),
						target.setBreastCrotchType(BreastType.NONE));
			}
			
			if(sissy) {
				if(target.getFemininity() != Femininity.ANDROGYNOUS) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("I want yer lookin' so that noone's gonna be able ta guess whether yer a guy or girl! Hah!"
							+(!selfTransform?"":" Make yerself androgynous!"),
							target.setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity()));
				}
				
			} else {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				if(target.getFemininityValue() > Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
					map.put("It's only fittin' fer a cum milker like you ta be nice an' manly!"
							+(!selfTransform?"":" Make yerself more masculine!"),
							target.setFemininity(5));
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 2);
			return map;
		}
	},
	
	MASCULINE_GENITALS(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a sissy:
			boolean sissy = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
			
			if(target.hasVagina() && !target.isPregnant()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("As all yer gonna be is a cum milker, so yer ain't gonna be needin' that cunt o' yours anymore!"
						+(!selfTransform?"":" Get rid o' it!"),
						target.setVaginaType(VaginaType.NONE));
			}
			
			if(!target.hasPenisIgnoreDildo()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Let's get yer a brand new cock grown in! Yer gonna be makin' us a lotta cash with all the cum this thing's gonna be spurtin' out! Heh-heh-heh!"
						+(!selfTransform?"":" So go on, grow yerself one!"),
						target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType()));
			}
			
			if(sissy) {
				if(target.getPenisRawSizeValue()>PenisSize.ONE_TINY.getMedianValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("I want yer cock nice an' small, so that yer feel ashamed o' it when yer look at 'ow big mine is!"
							+(!selfTransform?"":" Go on, make it tiny!"),
							target.setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue()));
				}

				if(target.getPenisRawGirthValue()>PenisGirth.TWO_AVERAGE.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("I don't want no sissy cock bein' as thick as mine!"
							+(!selfTransform?"":" Go on, I wanna see yer slim yer cock down!"),
							target.setPenisGirth(PenisGirth.THREE_THICK));
				}
				
			} else {
				if(target.getPenisRawSizeValue()<PenisSize.FIVE_ENORMOUS.getMedianValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer cock's gonna need ta be bigger'n that, otherwise the milkin' tube might slip right off while we're milkin' ya!"
							+(!selfTransform?"":" Go on, make it bigger!"),
							target.setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue()));
				}

				if(target.getPenisRawGirthValue()<PenisGirth.THREE_THICK.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Let's make yer cock nice an' thick!"
							+(!selfTransform?"":" Go on, I wanna see yer fatten it up!"),
							target.setPenisGirth(PenisGirth.THREE_THICK));
				}
			}
			
			if(target.isInternalTesticles()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put(sissy
							?"I wanna see yer tiny balls tightin' up while the machine milks yer!"
							:"We wanna see yer balls swayin' while the machine milks yer!"
						+(!selfTransform?"":" So what yer waitin' for?! Grow yerself some balls!"),
						target.setInternalTesticles(false));
			}
			
			if(sissy) {
				if(target.getTesticleSize().getValue()>TesticleSize.ONE_TINY.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer balls are gonna need ta be a lot smaller'n that; we want yer feelin' proper embarrassed ta be havin' them on display!"
							+(!selfTransform?"":" Make 'em nice an' tiny!"),
							target.setTesticleSize(TesticleSize.FOUR_HUGE));
				}
				
			} else {
				if(target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer balls are gonna need ta be bigger'n that, wot with all the cum they're gonna need ta be pumpin' out!"
							+(!selfTransform?"":" Make 'em nice an' round!"),
							target.setTesticleSize(TesticleSize.FOUR_HUGE));
				}
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Let's give yer an' invintin', puffy asshole, so anyone who's payin' the milkers a visit can't resist fuckin' it!"
							+(!selfTransform?"":" Go on; puff it up!"),
							target.addAssOrificeModifier(OrificeModifier.PUFFY));
				}
				if(target.getAssWetness().getValue()<Wetness.FIVE_SLOPPY.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("We're gonna want yer asshole all nice an' wet, so them big fat cocks slip right on in!"
							+(!selfTransform?"":" I know ya can make yerself nice an' wet back 'ere, so do it already!"),
							target.setAssWetness(Wetness.FIVE_SLOPPY));
				}
				if(Main.game.isGapeContentEnabled()) {
					if(target.getAssRawCapacityValue()>1) {
						if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
						map.put("We like ta play a game around 'ere, where new milkers get <i>real</i> tight 'oles to start off with, then we see how long it takes fer us ta stretch 'em out an' ruin 'em! Heh-heh-heh!"
								+(!selfTransform?"":" So make yer asshole tight as a vice!"),
								target.setAssCapacity(1, true));
					}
					if(target.getAssPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
						if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
						map.put("Now we just need yer ass ta be transformed so that it moulds to the shape o' the biggest cock that fucks you! It's gonna be nothin' but a ruined fuck-hole 'fore long, mark me words!"
								+(!selfTransform?"":" Make yer ass nice an' pliable!"),
								target.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
					}
					if(target.getAssElasticity().getValue()>OrificeElasticity.ZERO_UNYIELDING.getValue()) {
						if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
						map.put("I want yer ta spend a good long time feelin' yer asshole stretch out!"
								+(!selfTransform?"":" Make yer ass real resistant ta bein' stretched out!"),
								target.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
					}
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 3);
			return map;
		}
	},
	
	MASCULINE_FLUIDS(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a sissy:
			boolean sissy = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
			
			if(target.getCurrentPenisRawCumStorageValue()<(sissy?100:1000)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put(sissy
							?"We're gonna need yer ta be packin' as much cum in them tiny balls o' yers as possible!"
							:"We're gonna need yer ta be storin' lots o' valuable cum in them fat balls o' yers!"
						+(!selfTransform?"":" Get them filled with cum already!"),
						target.setPenisCumStorage(sissy?100:1000));
			}

			if(target.getPenisRawCumProductionRegenerationValue()<FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("The most important thing 'bout cum milkers is that yer able ta keep on makin' more cum real quick like!"
						+(!selfTransform?"":" Make yer cum regen' real quick, y'hear?!"),
						target.setPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
			}
			
			if(!sissy) {
				if(target.getTesticleSize().getValue()<TesticleSize.SIX_GIGANTIC.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("With all that cum yer gonna be pumpin' out, I'm thinkin' yer gonna need some bigger balls!"
							+(!selfTransform?"":" Swell 'em up so that they're nice an' fat!"),
							target.setTesticleSize(TesticleSize.SIX_GIGANTIC));
				}
			}
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
			return map;
		}
	},
	
	MASCULINE_FLAVOUR(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			FluidFlavour flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());
			
			if(target.getCumFlavour()==FluidFlavour.CUM) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("It ain't so easy ta shift fluids wot 'ave the usual flavours, so let's make yer cum a nice an' yummy "+flavour.getName()+" flavour!"
						+(!selfTransform?"":" Get to it!"),
						target.setCumFlavour(flavour));
			}
			
			if(target.hasCumModifier(FluidModifier.ADDICTIVE)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Tryin' ta sell addictive cum ain't easy, so we're gonna get rid o' that!"
						+(!selfTransform?"":" What yer waitin' for? Do it already!"),
						target.removeCumModifier(FluidModifier.ADDICTIVE));
			}
			
//			if(!target.hasCumModifier(FluidModifier.ADDICTIVE)) {
//				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
//				map.put("We're gonna' want the customers ta keep on cummin' back fer anotha taste o' yer cum, so let's make it nice an' addictive for 'em! Heh-heh-heh!",
//						target.addCumModifier(FluidModifier.ADDICTIVE));
//			}
//
//			if(!target.hasCumModifier(FluidModifier.ALCOHOLIC)) {
//				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
//				map.put("It's so much easier ta sell milked fluids if we can market 'em as alcoholic drinks, so let's turn yer balls into a brewery!",
//						target.addCumModifier(FluidModifier.ALCOHOLIC));
//			}
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 5);
			return map;
		}
	},
	
	
	
	FEMININE_FETISH(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			if(!target.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Let's get yer likin' the fact that yer gonna be naked from now on!",
						target.addFetish(Fetish.FETISH_EXHIBITIONIST, true));
			}

			if(!target.hasFetish(Fetish.FETISH_SUBMISSIVE)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We don't want yer thinkin' yer anythin' but a submissive milker now, do we?",
						target.addFetish(Fetish.FETISH_SUBMISSIVE, true));
			}
			
			if(!target.hasFetish(Fetish.FETISH_BREASTS_SELF)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("When we plug them suction cups over yer tits 'an turn 'em on, yer never gonna want 'em taken off!",
						target.addFetish(Fetish.FETISH_BREASTS_SELF, true));
			}
			
			if(!target.hasFetish(Fetish.FETISH_LACTATION_SELF)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Yer gonna love the feelin' o' havin' yer tits milked all day!",
						target.addFetish(Fetish.FETISH_LACTATION_SELF, true));
			}
			
			if(!target.hasFetish(Fetish.FETISH_VAGINAL_RECEIVING)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put(target.hasVagina()
							?"With yer cunt on display like this, yer gonna be takin' a lot o' cock; it'll be betta fer everyone if yer love it!"
							:"Once we've given yer a drippin' cunt between yer legs, we're gonna want yer ta love people just walkin' up behind yer an' fuckin' it!",
						target.addFetish(Fetish.FETISH_VAGINAL_RECEIVING, true));
			}
			
			if(Main.game.isAnalContentEnabled() && !target.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("It's betta fer everyone involved if yer love havin' yer ass fucked!",
						target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			}

			if(!target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We're gonna want yer ta be desperate ta 'ave us transform yer into one o' our milkers!",
						target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 1);
			return map;
		}
	},
	
	FEMININE_FEMININITY(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			if(target.getBreastSize().getMeasurement()<CupSize.DD.getMeasurement()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Let's get a start on growin' yer tits! We'll make 'em even bigger later on, but fer now, let's just make 'em a bit bigga!"
						+(!selfTransform?"":" Go on, make 'em into some nice double-D's!"),
						target.setBreastSize(CupSize.DD));
			}

			if(target.getNippleSize().getValue()<NippleSize.THREE_LARGE.getValue()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We're gonna need yer nipples ta be nice an' big!"
						+(!selfTransform?"":" Plump 'em up; don't be shy!"),
						target.setNippleSize(NippleSize.THREE_LARGE.getValue()));
			}
			
			if((target.isTaur() && Main.getProperties().udders>=1) || (target.getRaceStage()==RaceStage.GREATER && Main.getProperties().udders==2)) {
				if (RacialBody.valueOfRace(target.getRace()).getBreastCrotchType()==BreastType.NONE) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Let's give yer some udders as well; gotta get that milk production up as 'igh as we can!"
						+(!selfTransform?"":" Just get 'em teats grown in first; we can make 'em bigger another time!"),
						target.setBreastCrotchType(RacialBody.valueOfRace(target.getRace()).getBreastCrotchType()));
				}
				
				if(target.getNippleCrotchSize().getValue()<NippleSize.THREE_LARGE.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer teats are gonna need ta be bigger'n that!"
							+(!selfTransform?"":" Make 'em nice and plump!"),
							target.setNippleCrotchSize(NippleSize.THREE_LARGE.getValue()));
				}
			}
			
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				if(target.getFemininityValue() < Femininity.FEMININE_STRONG.getMinimumFemininity()) {
					map.put("It's only fittin' fer a milker ta be nice an' womanly!"
							+(!selfTransform?"":" Go on, make yerself more feminine!"),
							target.setFemininity(90));
				}
			} else {
				if(target.getFemininityValue() < Femininity.FEMININE.getMedianFemininity()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("It's only fittin' fer a milker ta be nice an' womanly!"
							+(!selfTransform?"":" Go on, make yerself more feminine!"),
							target.setFemininity(Femininity.FEMININE.getMedianFemininity()));
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 2);
			return map;
		}
	},
	
	FEMININE_GENITALS(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			// Flag for character becoming a futa:
			boolean futa = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuta);
			
			if(!futa) {
				if(target.hasPenisIgnoreDildo()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("We ain't gonna be needin' that cock o' yours; say goodbye to it!"
							+(!selfTransform?"":" That's it; shrink it down an' make it disappear!"),
							target.setPenisType(PenisType.NONE));
				}
				
			} else {
				if(!target.hasPenisIgnoreDildo()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Let's get yer a brand new cock grown in! Yer gonna be makin' us a lotta cash with all the cum this thing's gonna be spurtin' out! Heh-heh-heh!"
							+(!selfTransform?"":" So go on, grow yerself one!"),
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType()));
				}
				
				if(target.getPenisRawSizeValue()<PenisSize.FIVE_ENORMOUS.getMedianValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer cock's gonna need ta be bigger'n that, otherwise the milkin' tube might slip right off while we're milkin' ya!"
							+(!selfTransform?"":" Go on, make it bigger!"),
							target.setPenisSize(PenisSize.FIVE_ENORMOUS.getMedianValue()));
				}
				
				if(target.getPenisRawGirthValue()<PenisGirth.THREE_THICK.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Let's make yer cock nice an' thick!"
							+(!selfTransform?"":" Go on, I wanna see yer fatten it up!"),
							target.setPenisGirth(PenisGirth.THREE_THICK));
				}
				
				if(Main.game.isFutanariTesticlesEnabled() && target.isInternalTesticles()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("We wanna see yer balls swayin' while the machine milks yer!"
							+(!selfTransform?"":" So what yer waitin' for?! Grow yerself some balls!"),
							target.setInternalTesticles(false));
				}
				
				if(Main.game.isFutanariTesticlesEnabled() && target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer balls are gonna need ta be bigger'n than that, wot with all the cum they're gonna need ta be pumpin' out!"
							+(!selfTransform?"":" Make 'em nice an' round!"),
							target.setTesticleSize(TesticleSize.FOUR_HUGE));
				}
			}
			
			if(!target.hasVagina()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Givin' ya a cunt an' then fuckin' yer pregnant is sure ta help with yer milk production!"
						+(!selfTransform?"":" Come on; I wanna see yer growin' yerself a twat fer us ta fuck!"),
						target.setVaginaType(RacialBody.valueOfRace(target.getRace()).getVaginaType()));
			}
//			if(!target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
//				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
//				map.put("Now ta give yer some extra muscles in yer cunt; I'll make sure ta let everyone know yer got these, so they can't resist fuckin' ya to see how they feel!"
//						+(!selfTransform?"":" This'll be one fun hole ta fuck!"),
//						target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
//			}
			if(target.getVaginaWetness().getValue()<Wetness.SEVEN_DROOLING.getValue()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Yer twat need's ta always be drippin' wet, so any rat wandering past can't resist steppin' up and plunging their cock in yer droolin' fuck-hole!"
						+(!selfTransform?"":" That's it; this'll make yer irresistible ta any rat wanderin' past!"),
						target.setVaginaWetness(Wetness.SEVEN_DROOLING));
			}
			boolean vaginaTightened = false;
			boolean vaginaPlasticised = false;
			boolean vaginaElasticised = false;
			if(Main.game.isGapeContentEnabled()) {
				if(target.getVaginaRawCapacityValue()>1) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					vaginaTightened = true;
					map.put("We like ta play a game around 'ere, where new milkers get <i>real</i> tight 'oles to start off with, then we see how long it takes fer us ta stretch 'em out an' ruin 'em! Heh-heh-heh!"
							+(!selfTransform?"":" So make yer pretty little cunt tight as a vice!"),
							target.setVaginaCapacity(1, true));
				}
				if(target.getVaginaLabiaSize()!=LabiaSize.ZERO_TINY) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Havin' yer cunt lookin' all fresh an' unused makes it even better when I gets ta ruin it!"
							+(!selfTransform?"":" Get rid o' yer big fuckin' labia!"),
							target.setVaginaLabiaSize(LabiaSize.ZERO_TINY));
				}
				if(target.getVaginaPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					vaginaPlasticised = true;
					map.put("Now we just need yer cunt ta be transformed so that it moulds to the shape o' the biggest cock that fucks you! It's gonna be nothin' but a ruined fuck-hole 'fore long, mark me words!"
							+(!selfTransform?"":" Make yer twat nice an' pliable!"),
							target.setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
				}
				if(target.getVaginaElasticity().getValue()>OrificeElasticity.ZERO_UNYIELDING.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					vaginaElasticised = true;
					map.put("I want yer ta spend a good long time feelin' yer cunt stretch out!"
							+(!selfTransform?"":" Make yer twat real resistant ta bein' stretched out!"),
							target.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
				}
			}

			if(Main.game.isAnalContentEnabled()) {
				if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Let's give yer an' invintin', puffy asshole, so anyone who's payin' the milkers a visit can't resist fuckin' it!"
							+(!selfTransform?"":" Go on; puff it up!"),
							target.addAssOrificeModifier(OrificeModifier.PUFFY));
				}
				if(target.getAssWetness().getValue()<Wetness.FIVE_SLOPPY.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("We're gonna want yer asshole all nice an' wet, so them big fat cocks slip right on in!"
							+(!selfTransform?"":" I know ya can make yerself nice an' wet back 'ere, so do it already!"),
							target.setAssWetness(Wetness.FIVE_SLOPPY));
				}
				if(Main.game.isGapeContentEnabled()) {
					if(target.getAssRawCapacityValue()>1) {
						if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
						map.put(vaginaTightened
									?"Yer asshole's gonna be just like yer cunt; nice an' tight so's we can all play the game o' seein' 'ow long it takes ta ruin it!"
											+(!selfTransform?"":" Do the same as what yer did with yer cunt!")
									:"We like ta play a game around 'ere, where new milkers get <i>real</i> tight 'oles to start off with, then we see how long it takes fer us ta stretch 'em out an' ruin 'em! Heh-heh-heh!"
											+(!selfTransform?"":" So make yer ass tight as a vice!"),
								target.setAssCapacity(1, true));
					}
					if(target.getAssPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
						if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
						map.put(vaginaPlasticised
									?"Like we did with yer twat, we're gonna make it so that yer ass takes the shape o' the biggest cock that fucks it!"
										+(!selfTransform?"":" Make yer butt like putty fer our cocks ta mould inta shape!")
									:"Now we just need yer ass ta be transformed so that it moulds to the shape o' the biggest cock that fucks you! It's gonna be nothin' but a ruined fuck-hole 'fore long, mark me words!"
										+(!selfTransform?"":" Make yer butt like putty fer our cocks ta mould inta shape!"),
								target.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
					}
					if(target.getAssElasticity().getValue()>OrificeElasticity.ZERO_UNYIELDING.getValue()) {
						if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
						map.put(vaginaElasticised
									?"Make yer ass just as slow ta stretch out as yer cunt!"
									:"I want yer ta spend a good long time feelin' yer asshole stretch out!"
								+(!selfTransform?"":" Do what I say!"),
								target.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
					}
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 3);
			return map;
		}
	},
	
	FEMININE_FLUIDS(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a futa:
			boolean futa = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuta);

			if(target.getBreastSize().getMeasurement()<CupSize.HH.getMeasurement()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("It's time ta make yer tits even bigger, so the milkin' cups can easily suck right onto 'em!"
							+(!selfTransform?"":" Make 'em into double-H cups! I wanna see 'em swayin' beneath yer while yer get fucked from behind!"),
						target.setBreastSize(CupSize.HH));
			}

			if(target.getNippleSize().getValue()<NippleSize.FOUR_MASSIVE.getValue()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("We're gonna need yer nipples ta be nice an' fat!"
						+(!selfTransform?"":" Plup 'em up as big as yer can!"),
						target.setNippleSize(NippleSize.FOUR_MASSIVE.getValue()));
			}

			if(!target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Now ta puff up those fat nipples of yours; the machines seem ta work better when they've got big puffy teats ta be suckin' on!"
						+(!selfTransform?"":" That's it; make 'em all puffy and squeezable!"),
						target.addNippleOrificeModifier(OrificeModifier.PUFFY));
			}

			if(target.getBreastRawMilkStorageValue()<2500) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("Now fer the main event; gettin' yer tits ta store loads o' milk!"
						+(!selfTransform?"":" Fill 'em up with milk! That's it!"),
						target.setBreastMilkStorage(2500));
			}
			
			if(target.getBreastRawLactationRegenerationValue()<FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				map.put("If yer fat titties ain't regeneratin' milk fast enough, then all that storage they've got'd be pointless!"
						+(!selfTransform?"":" Make sure yer gonna be constantly leakin' milk!"),
						target.setBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
			}
			
			if(target.hasBreastsCrotch()) {
				if(target.getBreastCrotchSize().getMeasurement()<CupSize.FF.getMeasurement()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("We'll make yer udders nice an' big! Ooh, I can't wait ta see how much milk they're gonna be producin'!"
							+(!selfTransform?"":" Make 'em nice an' big fer us!"),
							target.setBreastCrotchSize(CupSize.FF));
				}

				if(target.getNippleCrotchSize().getValue()<NippleSize.FOUR_MASSIVE.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Yer teats need ta be plump an' juicy!"
							+(!selfTransform?"":" Just like yer nipples, make 'em as big as yer can!"),
							target.setNippleCrotchSize(NippleSize.FOUR_MASSIVE.getValue()));
				}

				if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("let's get them teats o' yours all puffed up and ready ta be sucked on by the machines!"
							+(!selfTransform?"":" Go on, make 'em all puffy and beggin' ta be sucked on!"),
							target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
				}
				
				if(target.getBreastCrotchRawMilkStorageValue()<1500) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("We've gotta get yer udders storin' loads o' milk as well!"
							+(!selfTransform?"":" Fill 'em up!"),
							target.setBreastCrotchMilkStorage(1500));
				}
				
				if(target.getBreastCrotchRawLactationRegenerationValue()<FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("Let's get them udders o' yours regeneratin' milk real quick like!"
							+(!selfTransform?"":" Make 'em so they're alsways leakin' milk!"),
							target.setBreastCrotchLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
				}
				
			}
			
			if(futa) {
				if(target.getCurrentPenisRawCumStorageValue()<500) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put(Main.game.isFutanariTesticlesEnabled()
								?"We're gonna need yer ta be storin' lots o' valuable cum in them fat balls o' yers!"
										+(!selfTransform?"":" Get them filled with cum already!")
								:"We're gonna need yer ta be storin' more o' that valuable cum!"
										+(!selfTransform?"":" Make it so yer can produce enough ta keep fillin' up the machine!"),
							target.setPenisCumStorage(500));
				}
	
				if(target.getPenisRawCumProductionRegenerationValue()<FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("The most important thing is that yer able ta keep on makin' more cum real quick like!"
								+(!selfTransform?"":" Make yer cum regen' real quick, y'hear?!"),
							target.setPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
				}
				
				if(Main.game.isFutanariTesticlesEnabled() && target.getTesticleSize().getValue()<TesticleSize.SIX_GIGANTIC.getValue()) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put("With all that cum yer gonna be pumpin' out, I'm thinkin' yer gonna need some bigger balls!"
								+(!selfTransform?"":" Swell 'em up so that they're nice an' fat!"),
							target.setTesticleSize(TesticleSize.SIX_GIGANTIC));
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
			return map;
		}
	},
	
	FEMININE_FLAVOUR(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a futa:
			boolean futa = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuta);
			
			boolean addictionRemoved = false;
			FluidFlavour flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());

			boolean milkFlavoured = false;
			if(target.getMilkFlavour()==FluidFlavour.MILK) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				milkFlavoured = false;
				map.put("We're gonna want ta get a good price fer ya milk, so yer flavourin' is gonna 'ave ta be changed!"
							+(!selfTransform?"":" Get to it!"),
						target.setMilkFlavour(flavour));
			} else {
				flavour = target.getMilkFlavour();
			}
			
			if(target.hasMilkModifier(FluidModifier.ADDICTIVE)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				addictionRemoved = true;
				map.put(addictionRemoved
						?"Yer udder milk is gonna have ta be made non-addictive as well!"
						:"Tryin' ta sell addictive milk ain't easy, so we're gonna get rid o' that!"
							+(!selfTransform?"":" What yer waitin' for? Do it already!"),
						target.removeMilkModifier(FluidModifier.ADDICTIVE));
			}

			if(target.hasBreastsCrotch()) {
				if(target.getMilkCrotchFlavour()!=flavour) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put(milkFlavoured
								?"Let's make yer udder milk taste o' "+flavour.getName()+" as well!"
										+(!selfTransform?"":" Come on, get it the same flavour as yer titties!")
								:"We're gonna want ta get a good price fer ya milk, so yer flavourin' is gonna 'ave ta be changed!"
										+(!selfTransform?"":" Get to it!"),
							target.setMilkCrotchFlavour(flavour));
				}
				
				if(target.hasMilkCrotchModifier(FluidModifier.ADDICTIVE)) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					addictionRemoved = true;
					map.put(addictionRemoved
								?"Yer udder milk is gonna have ta be made non-addictive as well!"
										+(!selfTransform?"":" Come on, we ain't got all day!")
								:"Tryin' ta sell addictive milk ain't easy, so we're gonna get rid o' that!"
										+(!selfTransform?"":" Come on, we ain't got all day!"),
							target.removeMilkCrotchModifier(FluidModifier.ADDICTIVE));
				}
			}

			
			if(futa) {
				if(target.getCumFlavour()==FluidFlavour.CUM
						|| target.getGirlcumFlavour()==FluidFlavour.GIRL_CUM) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());
					map.put(milkFlavoured
							?"Let's make yer cum an' girlcum taste o' "+flavour.getName()+" as well!"
								+(!selfTransform?"":" Come on, get 'em the same flavour as yer titties!")
							:"It ain't so easy ta shift fluids wot 'ave the usual flavours, so let's make yer cum an' girlcum a nice an' yummy "+flavour.getName()+" flavour!"
								+(!selfTransform?"":" Make it quick! I wanna start milkin' yer already!"),
							target.setCumFlavour(flavour)
							+target.setGirlcumFlavour(flavour));
				}

				if(target.hasCumModifier(FluidModifier.ADDICTIVE)) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					map.put(addictionRemoved
								?"We'll get rid o' that addictive nature o' yer cum as well!"
										+(!selfTransform?"":" Get it done!")
								:"Tryin' ta sell addictive cum ain't easy, so we're gonna get rid o' that!"
										+(!selfTransform?"":" Get it done!"),
							target.removeCumModifier(FluidModifier.ADDICTIVE));
				}
				
			} else {
				if(target.getGirlcumFlavour()==FluidFlavour.GIRL_CUM) {
					if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
					flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());
					map.put(milkFlavoured
						?"Let's make yer girlcum taste o' "+flavour.getName()+" as well!"
							+(!selfTransform?"":" Come on, get it the same flavour as yer titties!")
						:"It ain't so easy ta shift fluids wot 'ave the usual flavours, so let's make yer girlcum a nice an' yummy "+flavour.getName()+" flavour!"
								+(!selfTransform?"":" Make it quick! I wanna start milkin' yer already!"),
							target.setGirlcumFlavour(flavour));
				}
			}
			
			if(target.hasGirlcumModifier(FluidModifier.ADDICTIVE)) {
				if(!applyEffects) { return Util.newHashMapOfValues(new Value<>("", "")); }
				addictionRemoved = true;
				map.put(addictionRemoved
						?"We'll get rid o' that addictive nature o' yer girlcum as well!"
								+(!selfTransform?"":" Get it done!")
						:"Tryin' ta sell addictive girlcum ain't easy, so we're gonna get rid o' that!"
								+(!selfTransform?"":" Get it done!"),
						target.removeGirlcumModifier(FluidModifier.ADDICTIVE));
			}
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 5);
			return map;
		}
	};
	
	
	private boolean feminine;
	
	private CaptiveTransformation(boolean feminine) {
		this.feminine = feminine;
	}

	public boolean isFeminine() {
		return feminine;
	}

	public boolean isConditionsMet(GameCharacter target) {
		return !getEffects(target, false, false).isEmpty();
	}

	public Map<String, String> getEffects(GameCharacter target) {
		return getEffects(target, !target.isAbleToHaveRaceTransformed(), true);
	}
	
	protected abstract Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects);
	
	/**
	 * @param target The target to be transformed.
	 * @return The CaptiveTransformation stage that this target needs to be subjected to next. Returns null if transformation is complete.
	 */
	public static CaptiveTransformation getTransformationType(GameCharacter target) {
		boolean masculineTF = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMasculine) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
		
		int stage = Main.game.getDialogueFlags().getMurkTfStage(target);
		
		if(masculineTF) {
			if(MASCULINE_FETISH.isConditionsMet(target) && stage==0) {
				return MASCULINE_FETISH;
				
			} else if(MASCULINE_FEMININITY.isConditionsMet(target) && stage==1) {
				return MASCULINE_FEMININITY;
				
			} else if(MASCULINE_GENITALS.isConditionsMet(target) && stage==2) {
				return MASCULINE_GENITALS;
				
			} else if(MASCULINE_FLUIDS.isConditionsMet(target) && stage==3) {
				return MASCULINE_FLUIDS;
				
			} else if(MASCULINE_FLAVOUR.isConditionsMet(target) && stage==4) {
				return MASCULINE_FLAVOUR;
			}
			
		} else {
			if(FEMININE_FETISH.isConditionsMet(target) && stage==0) {
				return FEMININE_FETISH;
				
			} else if(FEMININE_FEMININITY.isConditionsMet(target) && stage==1) {
				return FEMININE_FEMININITY;
				
			} else if(FEMININE_GENITALS.isConditionsMet(target) && stage==2) {
				return FEMININE_GENITALS;
				
			} else if(FEMININE_FLUIDS.isConditionsMet(target) && stage==3) {
				return FEMININE_FLUIDS;
				
			} else if(FEMININE_FLAVOUR.isConditionsMet(target) && stage==4) {
				return FEMININE_FLAVOUR;
			}
			
		}
		
		return null;
	}
}
