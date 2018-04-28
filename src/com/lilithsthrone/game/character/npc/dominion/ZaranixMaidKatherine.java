package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public class ZaranixMaidKatherine extends NPC {

	private static final long serialVersionUID = 1L;

	public ZaranixMaidKatherine() {
		this(false);
	}
	
	public ZaranixMaidKatherine(boolean isImported) {
		super(new NameTriplet("Katherine"),
				"One of Zaranix's succubi maid twins, Katherine is assigned by her master to keep the ground floor clean.",
				10, Gender.F_P_V_B_FUTANARI, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_MAID, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_BLUE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
			this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			this.setHairStyle(HairStyle.PONYTAIL);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_IVORY), true);
			
			this.setHornType(HornType.CURLED);
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);

			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MAIN_FEATHER_DUSTER));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		this.setPlayerKnowsName(true);
		
		if(this.getMainWeapon()==null) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MAIN_FEATHER_DUSTER));
		}
		this.addFetish(Fetish.FETISH_MASOCHIST);
		this.setDescription("One of Zaranix's succubi maid twins, Katherine is assigned by her master to keep the ground floor clean.");
	}
	
	public void resetBody() {
		this.setBody(Gender.F_P_V_B_FUTANARI, RacialBody.DEMON, RaceStage.GREATER);

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_BLUE));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.PONYTAIL);
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_IVORY), true);
		
		this.setHornType(HornType.CURLED);
		
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
	}
	
	@Override
	public String getName() {
		if(!playerKnowsName) {
			return "Zaranix's Maid";
			
		} else {
			return "Katherine";
		}
	}

	@Override
	public void hourlyUpdate() {
		if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			this.moveToAdjacentMatchingCellType(PlaceType.ZARANIX_GF_STAIRS, PlaceType.ZARANIX_GF_MAID);
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
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex(boolean applyEffects) {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getCombatDescription() {
		return "Despite her commitment to defending her master's home, Katherine's choice of weapon - the feather duster that she was using to clean the corridor - doesn't strike you as being the most effective of combat implements...";
	}

	@Override
	public String getMainAttackDescription(boolean isHit) {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Letting out a desperate cry, Katherine swings her little feather duster at you, brushing the feathers over your face!",
						"With a little shout, Katherine tickles her feather duster over your torso!",
						"Brushing her feather duster over your torso, Katherine lets out a little cry!") 
			+ "</p>";
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Katherine grips her feather duster to her chest and cries out as she casts a spell!",
						"Katherine thrusts her feather duster up into the air as she casts a spell!") 
			+ "</p>";
	}

	@Override
	public String getSeductionDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Katherine pulls the bottom of her maid's dress up a little, moaning, [katherine.speech(If you beat me, there'd be nothing stopping you from ravishing me!)]",
						"With a lewd moan, Katherine snakes a hand under her dress, moaning [katherine.speech(~Ah!~ You're so powerful! I'm getting wet already!)]",
						"Biting her lip, Katherine looks up into your eyes, and you hear a lewd moan echoing around inside of your head, [katherine.speech(I hope you beat me! Show me no mercy!)]",
						"With an incredibly lewd moan, Katherine runs her hands up the length of her body to cup her [katherine.cupSize]-cup breasts, [katherine.speech(~Mmm!~ If I fail, there'd be nothing stopping you from fucking me!)]",
						"Katherine makes a show of staggering back, before desperately pressing her hands down between her legs, [katherine.speech(~Aah!~ I can just imagine all the lewd things you'd do to me if I fall here!)]",
						"Katherine's [npc.breasts+] starts to heave up and down as she pants, [katherine.speech(Oh no! I'm feeling weak already! Y-You're sure to take advantage of me if I lose!)]") 
			+ "</p>";
	}
	
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKatherineSubdued, true);
				}
			};
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Katherine staggers back, gripping the edge of a nearby cabinet as she collapses against the wall."
						+ " She doesn't seem all that upset at having failed to defend her master's home, as she looks up at you and moans,"
						+ " [katherine.speech(~Ah!~ Oh no! I'm totally defenceless! ~Mmm!~ Nothing can stop you from having your way with me now!)]"
					+ "</p>"
					+ "<p>"
						+ "It's quite obvious that she wants to have sex with you, and she continues to moan and pant with desire as she leans back against the wall."
						+ " Unable to wait even two seconds to hear your reply, her hands slip under her dress, and she starts shamelessly masturbating right there in front of you."
					+ "</p>"
					+ "<p>"
						+ "In her current lust-filled state, Katherine isn't going to pose much of a threat from now on, so you could either do what she obviously want you t do, and have sex with her, or simply ignore her and continue on your way."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_MAID.getDialogue(false));
				
			} if(index==2) {
				return new ResponseSex("Use Katherine", "Have some fun with the horny maid.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "It doesn't look like any of the other maids of the household will interrupt you, so you decide to take this opportunity to have a little fun with Katherine."
							+ " Stepping over to where she's leaning back against the wall, you reach forwards and take hold of her arm, before pulling her hand away from her groin."
							+ " Denied the freedom to get herself off, the horny maid looks up into your eyes, and you see them filled with a desperate, burning lust."
						+ "</p>"
						+ "<p>"
							+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [katherine.lips+] against yours."
							+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with Katherine. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						ZaranixMaidKatherine.AFTER_SEX_VICTORY,
						"<p>"
							+ "Not willing to take the dominant role, but with a deep desire to have sex with the horny succubus, you walk up to where Katherine's collapsed against the wall, and sigh,"
							+ " [pc.speech(Katherine... Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you're a stranger in her master's house, Katherine looks up at you with an intense, burning passion in her eyes."
							+ " Sliding her hand out from under her dress, she immediately interrupts your sentence as she grabs your head and pulls you into a desperate kiss."
						+ "</p>"
						+ "<p>"
							+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Katherine pulls back, moaning,"
							+ " [katherine.speech(Oh yes! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("Transformations",
						"Get Katherine to use [katherine.her] demonic powers to transform [katherine.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getKatherine());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Continue", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "With a satisfied sigh, Katherine slumps back against the wall,"
							+ " [katherine.speech(~Ah!~ That was good...)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you only just brought her to an orgasm, her hands slip down between her legs, and, with an exceptionally lewd moan, she starts fingering herself."
							+ " It's quite clear from her actions that she's still overwhelmed by the arousing power of your aura, so it would be safe to leave her behind and continue in your exploration of Zaranix's home."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "With a desperate moan, Katherine slumps back against the wall,"
							+ " [katherine.speech(~Ah!~ I didn't even get to cum!)]"
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
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_MAID.getDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeated", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Katherine proves to be too powerful for you to handle, and, letting out a defeated cry, you stagger across the corridor and lean against the wall for support."
						+ " Looking up, you see the ivory-skinned maid pouting at you as she steps forwards,"
						+ " [katherine.speech(Aww... I was sort of hoping you'd put up a bit more of a fight... That was no fun at all!)]"
					+ "</p>"
					+ "<p>"
						+ "You're powerless to resist as she moves right up beside you, wrapping her arms around your back and pulling you into her [katherine.breasts+] as she giggles,"
						+ " [katherine.speech(Looks like I'll have to get my fun in a different way...)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she suddenly presses her [katherine.lips+] against yours, pulling you into a passionate kiss as her demonic tail snakes up to rub at your groin..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Katherine uses you.",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getKatherine(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_DEFEAT);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Completely exhausted, you collapse to the floor, panting."
						+ " Towering over you, Katherine speaks down in a very patronising tone,"
						+ " [katherine.speech(Awww! Was that too much fun for you? In that case, I think it's about time I showed you the exit!)]"
					+ "</p>"
					+ "<p>"
						+ "Too tired to resist, you're left totally helpless as Katherine drags you along the corridor to the front entrance."
						+ " Opening the door, she unceremoniously shoves you out into the street, sighing,"
						+ " [katherine.speech(I guess you picked the wrong house to try and rob, huh?)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she slams the door shut, leaving you to pick yourself up and carry on your way..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Katherine throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
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
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

}