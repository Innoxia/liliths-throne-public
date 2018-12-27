package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.sexActions.SexActionOrgasmOverride;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.submission.SALyssiethSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.12
 * @version 0.3
 * @author Innoxia
 */
public class Lyssieth extends NPC {

	public Lyssieth() {
		this(false);
	}
	
	public Lyssieth(boolean isImported) {
		super(isImported,
				new NameTriplet("Lyssieth"), "Lilithmartuilani",
				"One of the seven elder Lilin, Lyssieth is one of the most powerful beings in existence.",
				7734, Month.OCTOBER, 13,
				1000,
				Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_OFFICE,
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(true);
			//TODO spells
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER);
			this.setStartingBody(true);
			this.equipClothing(true, true, true, true);
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 100);
			this.setAttribute(Attribute.MAJOR_ARCANE, 100);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_LILIN);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_INCEST);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);

			this.setFetishDesire(Fetish.FETISH_PREGNANCY, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);

			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ZERO_HATE);
		}
		
		this.setBody(Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.GREATER);
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceDifferenceToAppearAsAge(45);
//		this.setTailType(TailType.DEMON_COMMON);
//		this.setWingType(WingType.DEMON_COMMON);
//		this.setHornType(HornType.CURLED);

		// Core:
		this.setHeight(178);
		this.setFemininity(100);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, Colour.EYE_YELLOW, true, Colour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		// Anus settings and modifiers
		
		// Penis:
		// For if she grows one:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.FOUR_FAT);
		this.setPenisSize(12);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(250);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		this.unequipAllClothingIntoVoid(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_eye_half_rim_glasses", Colour.CLOTHING_BROWN_VERY_DARK, Colour.CLOTHING_BRASS, Colour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_torso_plunge_blouse", Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_asymmetrical_skirt", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_strappy_sandals", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_groin_lacy_thong", Colour.CLOTHING_RED_DARK, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#71009E";
		}
		return "#E194FF";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SALyssiethSpecials.class);
	}
	
	@Override
	public SexActionOrgasmOverride getSexActionOrgasmOverride(OrgasmCumTarget target, boolean applyExtraEffects) {
		if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_D_MEETING_A_LILIN)) { // Vision scene:
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(this, target));
			
			// Gaining Lyssieth's power:
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_2_D_MEETING_A_LILIN) {
				sb.append("<p>"
							+ "Lyssieth suddenly wraps her arms around you, and as she gazes into your eyes, you start to feel a strange warmth pulsating all around you."
						+ "</p>"
						+ "<p>"
							+ "[lyssieth.speech(Yes! Fuck... Take it! Let it in!)]"
								+ " Lyssieth pants, and before you can respond, you feel your head start to swim as your vision closes in. The last thing you remember before blacking out is collapsing into the elder lilin's arms..." 
						+ "</p>");
			}
			
			return new SexActionOrgasmOverride(true, sb.toString()) {
				@Override
				public void applyEffects() {
					if(applyExtraEffects) {
						Main.game.getPlayer().setArousal(50);
					}
				}
			};
			
		} else if(Sex.getSexManager() instanceof SMLyssiethDemonTF) { // Demon TF scene:
			StringBuilder sb = new StringBuilder();
			sb.append(GenericOrgasms.getGenericOrgasmDescription(this, target));
			
			if(Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
				sb.append("<p>"
							+ "Just as you think that Lyssieth has stopped, she grabs the back of your head, and, stepping forwards, she hilts her huge, demonic cock deep down your throat."
							+ " [lyssieth.speech(Come on, take it! Everyone knows demons can't get enough of sucking cock!)]"
						+ "</p>"
						+ "<p>"
							+ "You let out a muffled, gurgling noise in agreement, and, relaxing your throat, you feel tears streaming from your eyes as the elder lilin pumps yet more cum directly down into your stomach."
							+ " With each spurt of demonic seed that you're forced to swallow, you feel a deep, corruptive heat spreading out from your torso."
						+ "</p>"
						+ "<p>"
							+ "First, the strange feeling fans out into your back, before moving down to just above your ass."
							+ " Before you know what's happening, you feel a pair of large, demonic wings pressing out from the [pc.skin] around your shoulder blades,"
								+ " and, unable to turn around and view them just yet, due to Lyssieth's cock still being hilted down your throat, you have to make do with simply giving them an experimental flap."
							+ " As you do this, you feel a long, spaded tail taking form and pressing out from the base of your spine, and,"
								+ " realising that you have a significant amount of control over it, you curl it around one of Lyssieth's legs as you continue sucking on her fat cock."
						+ "</p>"
						+ "<p>"
							+ "[lyssieth.speech(That's my good little demon,)] Lyssieth gloats, sending yet another spurt of hot demonic cum down your throat,"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [lyssieth.speech(Show your new mommy how much you love her! Drink it all down, and you'll soon be my newest daughter!)]"
							+ "#ELSE [lyssieth.speech(Drink it all down, and let the corruption in!)]"
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "You do as you're told, and, pressing your lips up against the base of Lyssieth's cock, you obediently swallow down spurt after spurt of her corruptive cum."
							+ " This time, instead of flowing into your back, the heat starts to rise up into your head, and you let out yet more wet, gurgling noises as you try to moan around the fat cock in your mouth."
						+ "</p>"
						+ "<p>"
							+ "Although you can't see the changes that begin to take place, you can certainly feel them, and, as one, your eyes, ears, and scalp all start to tingle and itch."
							+ " Within just a few moments, you know that they have all been corrupted and transformed into their demonic counterparts, but instead of then stopping, the itchiness quickly moves up to concentrate in your forehead."
							+ " A deep, uncomfortable pressure starts building behind this tingling feeling, but thankfully, with one last spurt of demonic cum down your throat,"
								+ " it's suddenly released, and two horns rapidly grow out and sweep back around the upper sides of your head."
						+ "</p>"
						+ "<p>"
							+ "[lyssieth.speech(Good [pc.girl],)] Lyssieth says, finally withdrawing most of her cock from your throat, but still leaving the head in your mouth,"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [lyssieth.speech(Now, show your mommy how happy you are to have received her gift!)]"
							+ "#ELSE [lyssieth.speech(Now, show me how happy you are to have received my gift!)]"
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "As Lyssieth says this, a huge burst of arousal crashes over you, and you realise that you're about to orgasm!"
						+ "</p>");
				
				
				return new SexActionOrgasmOverride(false, sb.toString()) {
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							Main.game.getPlayer().setTailType(TailType.DEMON_COMMON);
							Main.game.getPlayer().setHornType(HornType.SWEPT_BACK);
							Main.game.getPlayer().setWingType(WingType.DEMON_COMMON);
							Main.game.getPlayer().setWingSize(WingSize.THREE_LARGE.getValue());
							Main.game.getPlayer().setEarType(EarType.DEMON_COMMON);
							Main.game.getPlayer().setEyeType(EyeType.DEMON_COMMON);
							Main.game.getPlayer().setHairType(HairType.DEMON_COMMON);
							Main.game.getPlayer().setArousal(100);
						}
					}
				};
				
			} else if(Sex.getContactingSexAreas(this, SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
				sb.append("<p>"
							+ "Just like before, Lyssieth's orgasm suddenly gains a second wind, and, grabbing your [pc.hips], she pulls you into her groin as she steps forwards,"
								+ " fully hilting her fat cock deep in your stretched pussy in the process."
							+ " [lyssieth.speech(That's right, squeeze your tight little demon cunt around my cock!)]"
						+ "</p>"
						+ "<p>"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [pc.speech(Yes mommy!)] you squeal, clenching your pussy down as hard as you can around her fat, ribbed shaft, [pc.speech(I'm your good girl!)]"
							+ "#ELSE [pc.speech(Fuck, yes!)] you squeal, clenching your pussy down as hard as you can around her fat, ribbed shaft, [pc.speech(Fuck me!)]"
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "As you say that, you suddenly feel Lyssieth's cock twitch and throb within you, and, as she lets out a frantic squeal, yet another massive spurt of her hot seed is pumped deep into your"
							+ "#IF pc.isVisiblyPregnant()"
							+ "#THEN already-saturated womb."
							+ "#ELSE cum-stuffed cunt."
							+ "#ENDIF"
							+ " Just like when you swallowed it, the corruptive cum sends a deep heat all throughout your body, but this time, you feel it moving into your arms and legs."
						+ "</p>"
						+ "<p>"
							+ "Before your eyes, you see the [pc.armSkin] covering your [pc.arms] start to shift and change,"
								+ " and with another pump of cum into your well-fucked pussy, they suddenly transform into a pair of smooth-skinned demonic arms."
							+ " You let out a shocked gasp, but as you do, Lyssieth rams the base of her cock up against your [pc.labiaSize] labia, turning your gasp into a pleasurable moan."
							+ " With another twitch and a throb, Lyssith's cum starts oozing out from your over-filled cunt around her thick shaft, causing her to pout and ask,"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [lyssieth.speech(Aww, are you struggling to take all of mommy's cum?)]"
							+ "#ELSE [lyssieth.speech(Aww, are you struggling to take all of my cum?)]"
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [pc.speech(No, mommy! Give me more!)] you squeal, spreading your legs for your new mother."
							+ "#ELSE [pc.speech(No! Please, give me more!)] you squeal, spreading your legs for the elder lilin."
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "Doing as you ask, Lyssieth sends another hot gush of demonic spunk deep into your cunt, and, although it once again causes more of her cum to leak out around her fat shaft,"
								+ " it's enough to finally trigger the second part of the transformation."
							+ " A deep, corruptive warmth runs down into your [pc.legs], and, just at that moment, Lyssieth grabs them and holds them up, spreading them wide as she makes quick, forceful thrusts into your stretched cunt."
							+ " Looking up at them as you pant and moan from the delightful feeling in your groin, you see the same process occurring as the one that took place in your arms, and within just a moment,"
								+ " your legs are fully demonic in nature."
						+ "</p>"
						+ "<p>"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [lyssieth.speech(That's my good [pc.girl],)] Lyssieth says, smiling down at you, [lyssieth.speech(Now, show mommy that you're having a good time!)]"
							+ "#ELSE [lyssieth.speech(Good little succubus,)] Lyssieth says, smiling down at you, [lyssieth.speech(Now, show me that you're having a good time!)]"
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "Once again, as Lyssieth says this, you feel a huge surge of arousal build up within you, and, with a frantic squeal, you reach yet another climax!"
						+ "</p>");
				
				return new SexActionOrgasmOverride(false, sb.toString()) {
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							Main.game.getPlayer().setArmType(ArmType.DEMON_COMMON);
							Main.game.getPlayer().setLegType(LegType.DEMON_COMMON);
							Main.game.getPlayer().setArousal(100);
						}
					}
				};
				
			} else if(Sex.getContactingSexAreas(this, SexAreaOrifice.VAGINA, Main.game.getPlayer()).contains(SexAreaPenetration.PENIS)) {
				sb.append("<p>"
							+ "As Lyssieth's pussy grips and squeezes around your cock, she once again seems to have her orgasm rebound and intensify."
							+ " Reaching up to grab you by the head, she wraps her legs around your lower back and pulls you down into a desperate, passioante kiss."
						+ "</p>"
						+ "<p>"
							+ "As the elder lilin moans and thrusts her tongue into your mouth, you feel the corruptive cum in your stomach and cunt unleash another wave of transformative power."
							+ " This time, your entire body and face starts to tingle, and as Lyssieth continues to desperately tongue-fuck your throat, you feel your body finishing the last of its transformations,"
								+ " turning you fully into a succubus."
						+ "</p>"
						+ "<p>"
							+ "Breaking off the kiss, Lyssieth smiles in delight upon your new, demonic form, and moans,"
							+ "#IF pc.hasFetish(FETISH_INCEST)"
							+ "#THEN [lyssieth.speech(Be a good daughter for mommy now, and give me a nice big creampie!)]"
							+ "#ELSE [lyssieth.speech(Be a good little succubus now, and give me a nice big creampie!)]"
							+ "#ENDIF"
						+ "</p>"
						+ "<p>"
							+ "Just as the two times before, as she says this, you feel a gigantic wave of arousal crash over you, and you realise that you're about to cum!"
						+ "</p>");
				
				return new SexActionOrgasmOverride(false, sb.toString()) {
					@Override
					public void applyEffects() {
						if(applyExtraEffects) {
							Main.game.getPlayer().setSkinType(SkinType.DEMON_COMMON);
							Main.game.getPlayer().setFaceType(FaceType.DEMON_COMMON);
							Main.game.getPlayer().setSubspeciesOverride(Subspecies.DEMON);
							Main.game.getPlayer().setArousal(100);
							Main.game.getPlayer().fillCumToMaxStorage();
						}
					}
				};
				
			}
		}

		return super.getSexActionOrgasmOverride(target, applyExtraEffects); // Normal scene
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Sex.getNumberOfOrgasms(this)==0) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		} else if(Sex.getNumberOfOrgasms(this)==1) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		}
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Sex.getNumberOfOrgasms(this)==0) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		} else if(Sex.getNumberOfOrgasms(this)==1) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		}
	}
	
	@Override
	public void endSex() {
		this.setPenisType(PenisType.NONE);
	}

	@Override
	public int getOrgasmsBeforeSatisfied() {
		return 3;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public void setLilinBody() {
		
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER);
		
		// Body:
		this.setSubspeciesOverride(Subspecies.ELDER_LILIN);
		this.setAgeAppearanceDifferenceToAppearAsAge(45);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.FOUR_HUGE.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());

		// Core:
		this.setHeight(196);
		this.setFemininity(100);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_GREEN));
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES, Colour.EYE_YELLOW, true, Colour.EYE_YELLOW, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN_DARK), true);
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMaximumValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED_DARK));
//			this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.THREE_PLUMP);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.GG.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		// Anus settings and modifiers
		
		// Penis:
		// For if she grows one:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.FOUR_FAT);
		this.setPenisSize(16);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(2500);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FIVE_SLOPPY);
		this.setVaginaElasticity(OrificeElasticity.FIVE_STRETCHY.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}

}