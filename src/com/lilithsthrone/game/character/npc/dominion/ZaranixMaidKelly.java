package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5.5
 * @author Innoxia
 */
public class ZaranixMaidKelly extends NPC {

	public ZaranixMaidKelly() {
		this(false);
	}
	
	public ZaranixMaidKelly(boolean isImported) {
		super(isImported, new NameTriplet("Kelly"), "Lasiellemartu",
				"One of Zaranix's succubi maid twins, Kelly is assigned by her master to keep the first floor clean.",
				26, Month.SEPTEMBER, 20,
				10,
				null, null, null,
				new CharacterInventory(10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_MAID, true);

		this.setPlayerKnowsName(true);
		
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceDifferenceToAppearAsAge(18);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setDescription("One of Zaranix's succubi maid twins, Kelly is assigned by her master to keep the first floor clean.");
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MAID);
	
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
		}
		
		
		// Body:
		// Add full body reset as this method is called after leaving Zaranix's house:
		this.setAgeAppearanceDifferenceToAppearAsAge(18);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.CURLED);

		// Core:
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_PURPLE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_IVORY), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_WHITE), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.HIME_CUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.F.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.THREE_AVERAGE);
		this.setPenisSize(17);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(100);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		
		this.unequipAllClothingIntoVoid(true, true);

		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_cleaning_feather_duster"));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLACK, false), true, this);

	}
	
	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(!playerKnowsName) {
			return "Zaranix's Maid";
			
		} else {
			return "Kelly";
		}
	}
	
	@Override
	public void hourlyUpdate() {
		if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			this.moveToAdjacentMatchingCellType(true, PlaceType.ZARANIX_FF_CORRIDOR, PlaceType.ZARANIX_FF_STAIRS, PlaceType.ZARANIX_FF_MAID);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#E48AFF";
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character instanceof ZaranixMaidKatherine) {
			Set<Relationship> result = new LinkedHashSet<>();
			result.add(Relationship.SiblingTwin);
			return result;
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}


	// Combat:
	
	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		return "<p>"
				+ UtilText.parse(target,
						UtilText.returnStringAtRandom(
						"Letting out a desperate cry, Kelly swings her little feather duster at [npc.name], brushing the feathers over [npc.her] face!",
						"With a little shout, Kelly tickles her feather duster over [npc.namePos] torso!",
						"Kelly lets out a little cry as she brushes her feather duster over [npc.namePos] torso!"))
			+ "</p>";
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Kelly grips her feather duster to her chest and cries out as she casts a spell!",
						"Kelly thrusts her feather duster up into the air as she casts a spell!") 
			+ "</p>";
	}

	@Override
	public String getSeductionDescription(GameCharacter target) {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Kelly pulls the bottom of her maid's dress up a little, moaning, [kelly.speech(If you beat me, there'd be nothing stopping you from ravishing me!)]",
						"With a lewd moan, Kelly snakes a hand under her dress, moaning [kelly.speech(~Ah!~ You're so powerful! I'm getting wet already!)]",
						"Biting her lip, Kelly looks up into your eyes, and you hear a lewd moan echoing around inside of your head, [kelly.speech(I hope you beat me! Show me no mercy!)]",
						"With an incredibly lewd moan, Kelly runs her hands up the length of her body to cup her [kelly.cupSize]-cup breasts, [kelly.speech(~Mmm!~ If I fail, there'd be nothing stopping you from fucking me!)]",
						"Kelly makes a show of staggering back, before desperately pressing her hands down between her legs, [kelly.speech(~Aah!~ I can just imagine all the lewd things you'd do to me if I fall here!)]",
						"Kelly's [npc.breasts+] starts to heave up and down as she pants, [kelly.speech(Oh no! I'm feeling weak already! Y-You're sure to take advantage of me if I lose!)]") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKellySubdued, true);
				}
			};
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "Kelly staggers back, gripping the edge of a nearby cabinet as she collapses against the wall."
						+ " She doesn't seem all that upset at having failed to defend her master's home, as she looks up at you and moans,"
						+ " [kelly.speech(~Ah!~ Oh no! I'm totally defenceless! ~Mmm!~ Nothing can stop you from having your way with me now!)]"
					+ "</p>"
					+ "<p>"
						+ "It's quite obvious that she wants to have sex with you, and she continues to moan and pant with desire as she leans back against the wall."
						+ " Unable to wait even two seconds to hear your reply, her hands slip under her dress, and she starts shamelessly masturbating right there in front of you."
					+ "</p>"
					+ "<p>"
						+ "In her current lust-filled state, Kelly isn't going to pose much of a threat from now on, so you could either do what she obviously wants you to do, and have sex with her,"
							+ " or simply ignore her and continue on your way."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_FF_MAID.getDialogue(false));
				
			} if(index==2) {
				return new ResponseSex("Use Kelly", "Have some fun with the horny maid.",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
						null,
						null), AFTER_SEX_VICTORY, "<p>"
							+ "It doesn't look like any of the other maids of the household will interrupt you, so you decide to take this opportunity to have a little fun with Kelly."
							+ " Stepping over to where she's leaning back against the wall, you reach forwards and take hold of her arm, before pulling her hand away from her groin."
							+ " Denied the freedom to get herself off, the horny maid looks up into your eyes, and you see them filled with a desperate, burning lust."
						+ "</p>"
						+ "<p>"
							+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [kelly.lips+] against yours."
							+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Kelly. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), AFTER_SEX_VICTORY, "<p>"
							+ "Not willing to take the dominant role, but with a deep desire to have sex with the horny succubus, you walk up to where Kelly's collapsed against the wall, and sigh,"
							+ " [pc.speech(Kelly... Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you're a stranger in her master's house, Kelly looks up at you with an intense, burning passion in her eyes."
							+ " Sliding her hand out from under her dress, she immediately interrupts your sentence as she grabs your head and pulls you into a desperate kiss."
						+ "</p>"
						+ "<p>"
							+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Kelly pulls back, moaning,"
							+ " [kelly.speech(Oh yes! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("Transformations",
						"Get Kelly to use [kelly.her] demonic powers to transform [kelly.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(ZaranixMaidKelly.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Continue", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(ZaranixMaidKelly.class)) >= Main.game.getNpc(ZaranixMaidKelly.class).getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "With a satisfied sigh, Kelly slumps back against the wall,"
							+ " [kelly.speech(~Ah!~ That was good...)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you only just brought her to an orgasm, her hands slip down between her legs, and, with an exceptionally lewd moan, she starts fingering herself."
							+ " It's quite clear from her actions that she's still overwhelmed by the arousing power of your aura, so it would be safe to leave her behind and continue in your exploration of Zaranix's home."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "With a desperate moan, Kelly slumps back against the wall,"
							+ " [kelly.speech(~Ah!~ I need more!)]"
						+ "</p>"
						+ "<p>"
							+ "Her hands instantly slip down between her legs, and, with an exceptionally lewd moan, she starts fingering herself."
							+ " It's quite clear from her actions that she's still overwhelmed by the arousing power of your aura, so it would be safe to leave her behind and continue in your exploration of Zaranix's home."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Conscious of the fact that there are other maids to look out for, you prepare to set off further into the house..."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_FF_MAID.getDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeated", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "Kelly proves to be too powerful for you to handle, and, letting out a defeated cry, you stagger across the corridor and lean against the wall for support."
						+ " Looking up, you see the ivory-skinned maid pouting at you as she steps forwards,"
						+ " [kelly.speech(Aww... I was sort of hoping you'd put up a bit more of a fight... That was no fun at all!)]"
					+ "</p>"
					+ "<p>"
						+ "You're powerless to resist as she moves right up beside you, wrapping her arms around your back and pulling you into her [kelly.breasts+] as she giggles,"
						+ " [kelly.speech(Looks like I'll have to get my fun in a different way...)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she suddenly presses her [kelly.lips+] against yours, pulling you into a passionate kiss as her demonic tail snakes up to rub at your groin..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Kelly uses you.",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(ZaranixMaidKelly.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						AFTER_SEX_DEFEAT,
						"");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Used", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "Completely exhausted, you collapse to the floor, panting."
						+ " Towering over you, Kelly speaks down in a very patronising tone,"
						+ " [kelly.speech(Awww! Was that too much fun for you? In that case, I think it's about time I showed you the exit!)]"
					+ "</p>"
					+ "<p>"
						+ "Too tired to resist, you're left totally helpless as Kelly drags you along the corridor to the front entrance."
						+ " Opening the door, she unceremoniously shoves you out into the street, sighing,"
						+ " [kelly.speech(I guess you picked the wrong house to try and rob, huh?)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she slams the door shut, leaving you to pick yourself up and carry on your way..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Kelly throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

}