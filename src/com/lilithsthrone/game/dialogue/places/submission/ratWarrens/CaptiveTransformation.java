package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.LinkedHashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.5
 * @version 0.3.8.9
 * @author Innoxia
 */
public enum CaptiveTransformation {
	
	MASCULINE_FETISH(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			map.put("First o' all, yer gonna start lovin' the idea o' bein' transformed; yer gonna be beggin' me fer the rest o' yer transformations!",
					target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			
			map.put("Next, yer gonna be gettin' real turned on by the fact that yer a submissive milker 'ose private parts are on display fer anyone 'o enters this room!",
					target.removeFetish(Fetish.FETISH_DOMINANT, true)
					+ target.addFetish(Fetish.FETISH_SUBMISSIVE, true)
					+ target.addFetish(Fetish.FETISH_EXHIBITIONIST, true));
			
			map.put(target.hasPenisIgnoreDildo()
						?"Yer gonna' love nothin' more than ta be cummin' into the milkin' machine all day long!"
						:"Once I's got yer ta grow a nice big cock, yer gonna' love nothin' more than ta be cummin' into the milkin' machine all day long!",
					target.addFetish(Fetish.FETISH_PENIS_GIVING, true)
					+ target.addFetish(Fetish.FETISH_CUM_STUD, true));

			map.put("'Course, the most important part 'bout bein' me milker is fallin' in love with me fat cock! You's gonna be beggin' ta be stuffed full o' me stinkin' cum after this!",
					target.addFetish(Fetish.FETISH_PENIS_RECEIVING, true)
					+ target.addFetish(Fetish.FETISH_CUM_ADDICT, true));
			
			if(Main.game.isAnalContentEnabled()) {
				map.put("We's can't be forgettin' 'bout yer ass now, can we? All me milkers are filthy butt-sluts, an' you's gonna be no exception!",
						target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			}

			map.put("Finally, let's get ya lovin' ta perform oral! I's gonna be puttin' this ta the test in just a minute...",
					target.addFetish(Fetish.FETISH_ORAL_GIVING, true));
			
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
				map.put("Yer ain't gonna be needin' them tits o' yours anymore!",
						target.setBreastSize(0));
			}
			
			if(target.hasBreastsCrotch()) {
				map.put("We don't need them hangin' udders yer got!",
						target.setBreastCrotchType(BreastType.NONE));
			}
			
			if(sissy) {
				map.put("I want yer lookin' so that noone's gonna be able ta guess whether yer a guy or girl! Hah!",
						target.setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity())
						+ ((!target.isFaceBaldnessNatural() && target.getHairRawLengthValue()<HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								?target.incrementHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								:""));
				
				map.put("Let's give yer some big girly nipples!",
						target.incrementNippleSize(3)
						+ target.incrementAreolaeSize(1));

				map.put("Lets give yer a nice fat ass an' a pair o' big swayin' hips!",
						target.incrementAssSize(2)
						+target.incrementHipSize(2));
				
			} else {
				map.put("It's only fittin' fer a cum milker like you ta be nice an' manly!",
						target.setFemininity(5));
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
			
			if(target.hasVagina()) {
				map.put("As all yer gonna be is a cum milker, yer ain't gonna be needin' that cunt o' yours anymore!",
						target.setVaginaType(VaginaType.NONE));
			}

			if(!target.hasPenisIgnoreDildo()) {
				if(sissy) {
					map.put("You's gonna be growin' a tiny little sissy cock! Yer gonna feel real embarrassed to 'ave this thing tiny thing droolin' out cum! Heh-heh-heh!",
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType())
							+ target.setPenisSize(PenisLength.ONE_TINY.getMedianValue())
							+ (target.getPenisRawGirthValue()>PenetrationGirth.TWO_NARROW.getValue()
									?target.setPenisGirth(PenetrationGirth.TWO_NARROW)
									:""));
					
				} else {
					map.put("You's gonna be growin' a nice fat cock! Yer gonna be makin' us a lotta cash with all the cum this thing's gonna be spurtin' out! Heh-heh-heh!",
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType())
							+ target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_THICK.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_THICK)
									:""));
				}
			} else {
				if(sissy) {
					map.put("Yer cock's gonna be nice an' small, so that yer feel like a real pathetic sissy!",
							target.setPenisSize(PenisLength.ONE_TINY.getMedianValue())
							+(target.getPenisRawGirthValue()>PenetrationGirth.TWO_NARROW.getValue()
									?target.setPenisGirth(PenetrationGirth.TWO_NARROW)
									:""));
					
				} else {
					map.put("Yer cock's gonna need ta be bigger'n that, otherwise the milkin' tube might slip right off while we're milkin' ya!",
							target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_THICK.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_THICK)
									:""));
				}
			}
			
			if(target.isInternalTesticles()) {
				map.put(sissy
							?"I wanna see yer tiny balls tightin' up while the machine milks yer!"
							:"We wanna see yer balls swayin' while the machine milks yer!",
						target.setInternalTesticles(false));
			}
			
			if(sissy) {
				if(target.getTesticleSize().getValue()>TesticleSize.ONE_TINY.getValue()) {
					map.put("Yer balls are gonna need ta be a lot smaller'n that; we want yer feelin' proper embarrassed ta be havin' them on display!",
							target.setTesticleSize(TesticleSize.ONE_TINY));
				}
				
			} else {
				if(target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()) {
					map.put("Yer balls are gonna need ta be bigger'n that, wot with all the cum they're gonna need ta be pumpin' out!",
							target.setTesticleSize(TesticleSize.FOUR_HUGE));
				}
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
					map.put("Let's give yer an' invintin', puffy asshole, so anyone who's payin' the milkers a visit can't resist fuckin' it!",
							target.addAssOrificeModifier(OrificeModifier.PUFFY));
				}
				if(target.getAssWetness().getValue()<Wetness.FIVE_SLOPPY.getValue()) {
					map.put("We're gonna want yer asshole all nice an' wet, so them big fat cocks slip right on in!",
							target.setAssWetness(Wetness.FIVE_SLOPPY));
				}
				map.put("It ain't gonna be fun fer me if I can't hilt me fat cock deep in yer ass!",
						target.incrementAssDepth(3));
				
				if(Main.game.isGapeContentEnabled()) {
					map.put("I's love havin' me milkers start off with<i>real</i> tight 'oles, an' then takin' me time ta' mould 'em to the shape o' me big fat cock! I's gonna enjoy turnin' ya tight little ass inta a ruined fuck-hole!",
							target.setAssCapacity(1, true)
							+ target.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue())
							+ target.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
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
			
			map.put(sissy
						?"We're gonna need yer ta be packin' as much cum in them tiny balls o' yers as possible!"
						:"We're gonna need yer ta be storin' lots o' valuable cum in them fat balls o' yers!",
					target.incrementPenisCumStorage(500));

			map.put("The most important thing 'bout cum milkers is that yer able ta keep on makin' more cum real quick like!",
					target.incrementPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
			
			if(!sissy) {
				if(target.getTesticleSize().getValue()<TesticleSize.SIX_GIGANTIC.getValue()) {
					map.put("With all that cum yer gonna be pumpin' out, I'm thinkin' yer gonna need some bigger balls!",
							target.setTesticleSize(TesticleSize.SIX_GIGANTIC));
				}
			}
			
			FluidFlavour flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());
			
			map.put("It ain't so easy ta shift fluids wot 'ave the usual flavours, so let's make yer cum a nice an' yummy "+flavour.getName()+" flavour!",
					target.setCumFlavour(flavour));
			
			map.put("Tryin' ta sell addictive cum ain't easy, so we's gonna make sure ya ain't got none o' that goin' on!",
					target.removeCumModifier(FluidModifier.ADDICTIVE));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
			
			return map;
		}
	},
	
	
	
	FEMININE_FETISH(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			map.put("First o' all, yer gonna start lovin' the idea o' bein' transformed; yer gonna be beggin' me fer the rest o' yer transformations!",
					target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			
			map.put("Next, yer gonna be gettin' real turned on by the fact that yer a submissive milker 'ose private parts are on display fer anyone 'o enters this room!",
					target.removeFetish(Fetish.FETISH_DOMINANT, true)
					+ target.addFetish(Fetish.FETISH_SUBMISSIVE, true)
					+ target.addFetish(Fetish.FETISH_EXHIBITIONIST, true));
			
			if(Main.game.isLactationContentEnabled()) {
				map.put("Now yer gonna be made ta love the idea o' havin' yer tits milked! When we plug them suction cups over yer fat teats 'an turn 'em on, yer never gonna want 'em taken off!",
						target.addFetish(Fetish.FETISH_BREASTS_SELF, true)
						+ target.addFetish(Fetish.FETISH_LACTATION_SELF, true));
			}
			
			map.put("'Course, the most important part 'bout bein' me milker is fallin' in love with me fat cock! You's gonna be beggin' ta be stuffed full o' me stinkin' cum after this!",
					target.addFetish(Fetish.FETISH_PENIS_RECEIVING, true)
					+ target.addFetish(Fetish.FETISH_CUM_ADDICT, true));
			
			map.put(target.hasVagina()
						?"You's gonna be desperate ta 'ave yer droolin' cunt stuffed full o' cock!"
						:"Once I's given ya a juicy, droolin' cunt, you's gonna be desperate ta 'ave it stuffed full o' cock!",
					target.addFetish(Fetish.FETISH_VAGINAL_RECEIVING, true));
			
			if(Main.game.isAnalContentEnabled()) {
				map.put("We's can't be forgettin' 'bout yer ass now, can we? All me milkers are filthy butt-sluts, an' you's gonna be no exception!",
						target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			}

			map.put("Finally, let's get ya lovin' ta perform oral! I's gonna be puttin' this ta the test in just a minute...",
					target.addFetish(Fetish.FETISH_ORAL_GIVING, true));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 1);
			
			return map;
		}
	},
	
	FEMININE_FEMININITY(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				map.put("It's only fittin' fer a milker ta be nice an' womanly!",
						target.setFemininity(95)
						+ ((!target.isFaceBaldnessNatural() && target.getHairRawLengthValue()<HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								?target.incrementHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								:""));
					
			} else if(target.getFemininityValue()<Femininity.FEMININE.getMedianFemininity()) {
				map.put("It's only fittin' fer a milker ta be nice an' girly!",
						target.setFemininity(Femininity.FEMININE.getMedianFemininity())
						+ ((!target.isFaceBaldnessNatural() && target.getHairRawLengthValue()<HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								?target.incrementHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								:""));
			}
			
			map.put("Let's get a start on growin' yer tits! We'll make 'em even bigger later on, but fer now, let's just plump 'em up a bit!",
					target.incrementBreastSize(6));

			map.put("We're gonna need yer nipples ta be nice an' big!",
					target.incrementNippleSize(2)
					+ target.incrementAreolaeSize(1));

			map.put("Lets give yer a fuckin' fat ass an' a pair o' big swayin' hips!",
					target.incrementAssSize(2)
					+ target.incrementHipSize(2));
			
			if((target.isTaur() && Main.getProperties().udders>=1) || (target.getRaceStage()==RaceStage.GREATER && Main.getProperties().udders==2)
					&& Main.game.isLactationContentEnabled()
					&& RacialBody.valueOfRace(target.getRace()).getBreastCrotchType()!=BreastType.NONE) {
				map.put("Let's give yer some fuckin' udders with nice big teats; gotta get that milk production up as 'igh as we can!",
						target.setBreastCrotchType(RacialBody.valueOfRace(target.getRace()).getBreastCrotchType())
						+ target.incrementNippleCrotchSize(2));
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
					map.put("We ain't gonna be needin' that dirty cock o' yours!",
							target.setPenisType(PenisType.NONE));
				}
				
			} else {
				if(!target.hasPenisIgnoreDildo()) {
					map.put("You's gonna be growin' a nice fat cock! Yer gonna be makin' us a lotta cash with all the cum this thing's gonna be spurtin' out! Heh-heh-heh!",
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType())
							+ target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_THICK.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_THICK)
									:""));
				} else {
					map.put("Yer cock needs ta be big an' fat!",
							target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_THICK.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_THICK)
									:""));
				}
				
				if(Main.game.isFutanariTesticlesEnabled() && target.isInternalTesticles()) {
					map.put("I's wantin' tas see yer big fat balls swayin' while the machine milks yer!",
							target.setInternalTesticles(false)
							+ (target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()
									?target.setTesticleSize(TesticleSize.FOUR_HUGE)
									:""));
				}
				
			}
			
			if(!target.hasVagina()) {
				map.put("Givin' ya a cunt an' then fuckin' yer pregnant is sure ta help with yer milk production!",
						target.setVaginaType(RacialBody.valueOfRace(target.getRace()).getVaginaType()));
			}
			map.put("You's need ta 'ave a permanently-droolin' fuck-hole so's that me fat cock can slip right on in! Oh, an' it needs ta be real deep, so's I can hilt me massive cock in it!",
					target.setVaginaWetness(Wetness.SEVEN_DROOLING)
					+ target.incrementVaginaDepth(3));
			
			if(Main.game.isGapeContentEnabled()) {
				map.put("I's love havin' me milkers start off with<i>real</i> tight 'oles, an' then takin' me time ta' mould 'em to the shape o' me big fat cock! I's gonna enjoy turnin' ya tight little cunt inta a ruined fuck-hole!",
						target.setVaginaCapacity(1, true)
						+ target.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue())
						+ target.setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
			}
			
			map.put("I's likin' me milkers ta 'ave big fat dirty cunts; we's wouldn't want yer thinkin' that you's got a pretty little pussy now, would we?",
					target.addVaginaOrificeModifier(OrificeModifier.PUFFY)
					+ target.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE));
			
			if(Main.game.isAnalContentEnabled()) {
				if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
					map.put("Let's give yer an' invintin', puffy asshole, so anyone who's payin' the milkers a visit can't resist fuckin' it!",
							target.addAssOrificeModifier(OrificeModifier.PUFFY));
				}
				if(target.getAssWetness().getValue()<Wetness.FIVE_SLOPPY.getValue()) {
					map.put("We're gonna want yer asshole all nice an' wet, so them big fat cocks slip right on in!",
							target.setAssWetness(Wetness.FIVE_SLOPPY));
				}
				map.put("It ain't gonna be fun fer me if I can't hilt me fat cock deep in yer ass!",
						target.incrementAssDepth(3));
				
				if(Main.game.isGapeContentEnabled()) {
					map.put("Yer asshole's gonna be just like yer cunt; nice an' tight so's I can take me time stretchin' it out inta a gapin', ruined 'ole!",
							target.setAssCapacity(1, true)
							+ target.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue())
							+ target.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
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

			map.put(Main.game.isLactationContentEnabled()
						?"Now yer tits are gonna grow even bigger, so the milkin' cups can easily suck right onto 'em!"
						:"Now yer tits are gonna grow even bigger!",
					target.incrementBreastSize(6));
			
			map.put(Main.game.isLactationContentEnabled()
						?"You's nipples are gonna swell up 'til they's big, fat, an' puffy; I loves watchin' milk srayin' out o' dirty fat teats!"
						:"You's nipples are gonna swell up 'til they's big, fat, an' puffy!",
					target.incrementNippleSize(3)
					+ target.incrementAreolaeSize(1)
					+ target.addNippleOrificeModifier(OrificeModifier.PUFFY));
			
			if(Main.game.isLactationContentEnabled()) {
				map.put("Now fer the main event; gettin' yer tits ta store loads o' milk!",
						target.incrementBreastMilkStorage(2500));
				
				map.put("If yer fat titties ain't regeneratin' milk fast enough, then all that storage they've got'd be pointless!",
						target.incrementBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
			
				if(target.hasBreastsCrotch()) {
						map.put("We'll make yer udders nice an' big! Ooh, I can't wait ta see how much milk they're gonna be producin'!",
								target.incrementBreastCrotchSize(10));
	
					map.put("Yer teats need ta be plump an' juicy!",
							target.incrementNippleCrotchSize(4));
	
					if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
						map.put("let's get them teats o' yours all puffed up and ready ta be sucked on by the machines!",
								target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
					}
					
					map.put("We've gotta get yer udders storin' loads o' milk as well!",
							target.incrementBreastCrotchMilkStorage(2000));
					
					map.put("Let's get them udders o' yours regeneratin' milk real quick like!",
							target.incrementBreastCrotchLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
					
				}
			}
			
			map.put("You's gonna be havin' a vibratin' dildo shoved up yer cunt so I's can milk yer girlcum! You's better be squirtin' out loads o' juices whenever ya orgasm!",
					target.setVaginaSquirter(true));
			
			if(futa) {
				map.put(Main.game.isFutanariTesticlesEnabled()
							?"We're gonna need yer ta be storin' lots o' valuable cum in them fat balls o' yers!"
							:"We're gonna need yer ta be storin' more o' that valuable cum!",
						target.incrementPenisCumStorage(500));
	
				map.put("The most important thing is that yer able ta keep on makin' more cum real quick like!",
						target.incrementPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
				
				if(Main.game.isFutanariTesticlesEnabled() && target.getTesticleSize().getValue()<TesticleSize.SIX_GIGANTIC.getValue()) {
					map.put("With all that cum yer gonna be pumpin' out, I'm thinkin' yer gonna need some bigger balls!",
							target.setTesticleSize(TesticleSize.SIX_GIGANTIC));
				}
			}
			
			FluidFlavour flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());

			map.put("We're gonna want ta get a good price fer ya fluids, so yer flavourin' is gonna 'ave ta be changed!",
					target.setGirlcumFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()))
					+ (futa
						?target.setCumFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()))
						:"")
					+ target.setMilkFlavour(flavour)
					+ (target.hasBreastsCrotch()
						?target.setMilkCrotchFlavour(flavour)
						:""));
			
			map.put("Tryin' ta sell addictive fluids ain't easy, so we're gonna get rid o' that!",
					target.removeGirlcumModifier(FluidModifier.ADDICTIVE)
					+ (futa
						?target.removeCumModifier(FluidModifier.ADDICTIVE)
						:"")
					+ target.removeMilkModifier(FluidModifier.ADDICTIVE)
					+ (target.hasBreastsCrotch()
						?target.removeMilkCrotchModifier(FluidModifier.ADDICTIVE)
						:""));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
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
		return true;
	}

	public Map<String, String> getEffects(GameCharacter target) {
		return getEffects(target, false, true);
	}
	
	protected abstract Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects); // selfTransform is never used as no racial transformations take place which would require it.
	
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
				
			}
			
		}
		
		return null;
	}
}
