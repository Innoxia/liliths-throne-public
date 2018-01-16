package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
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
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionNew;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.1.97
 * @author Innoxia
 */
public class Amber extends NPC {

	private static final long serialVersionUID = 1L;

	public Amber() {
		this(false);
	}
	
	public Amber(boolean isImported) {
		super(new NameTriplet("Amber"),
				"The highest-ranking of Zaranix's maids, Amber is clearly outraged by the fact that you're wandering around her master's house unsupervised.",
				12, Gender.F_P_V_B_FUTANARI, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);

		if(!isImported) {
			this.setPlayerKnowsName(false);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHeight(180);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, Colour.EYE_AMBER, true, Colour.EYE_AMBER, true));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, Colour.COVERING_AMBER, true, Colour.COVERING_AMBER, true), true);
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
			this.setHairStyle(HairStyle.SIDECUT);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_EBONY), true);
			
			this.setBreastSize(CupSize.DD.getMeasurement());
			
			this.setHornType(HornType.SWEPT_BACK);
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DEFLOWERING);

			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.FIRE));
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_UNDERBUST_CORSET, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc) {
		loadNPCVariablesFromXML(this, null, parentElement, doc);
	}
	
	public void resetBody() {
		this.setBody(Gender.F_P_V_B_FUTANARI, RacialBody.DEMON, RaceStage.GREATER);
		
		this.setHeight(180);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, Colour.EYE_AMBER, true, Colour.EYE_AMBER, true));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, Colour.COVERING_AMBER, true, Colour.COVERING_AMBER, true), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.SIDECUT);
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_EBONY), true);
		
		this.setBreastSize(CupSize.DD.getMeasurement());
		
		this.setHornType(HornType.SWEPT_BACK);
		
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
	}

	@Override
	public String getName() {
		if(!playerKnowsName) {
			return "Fiery Maid";
			
		} else {
			return "Amber";
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#FFB38A";
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
		if (applyEffects) {
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getCombatDescription() {
		return "The maid's fiery amber hair and eyes both glow with an intense fury as she fiercely launches her attack!";
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		if (attackType == Attack.MAIN) {
			return "<p>"
						+ UtilText.returnStringAtRandom(
								"Amber's eyes burn with an incandescent fury as she delivers a kick straight into your side!",
								"With a furious cry, Amber punches you square in the chest!",
								"Spitting curses, Amber furiously kicks at your shins!",
								"Amber's hair, burning with the same fiery fury as her eyes, swishes through the air as she spins to one side and delivers a solid punch to your [pc.arm]!") 
					+ "</p>";
		} else {
			return "<p>"
					+ UtilText.returnStringAtRandom(
							"Letting out a wild scream, Amber thrusts her arm into mid air as she casts a spell!",
							"Spitting curses, Amber locks her blazing eyes onto yours, before casting a spell!",
							"With an angry curse, Amber steps forwards and casts a spell!") 
				+ "</p>";
		}
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, true);
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
						+ "Amber staggers back, bracing herself against the wall as she lets out one last hateful curse,"
						+ " [amber.speech(You... "+(Main.game.getPlayer().isFeminine()?"bitch...":"bastard...")+")]"
					+ "</p>"
					+ "<p>"
						+ "It's quite clear that she's unable to continue fighting any longer, and after just a moment, the effects of your powerful arcane aura make themselves known."
						+ " Amber's angry scowl trails off into a exceptionally lewd moan, and, averting the gaze of her luminescent eyes from you for the first time, she looks down at her groin and slips a hand under her dress."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(~Aaah!~ Fuck...)]"
						+ " she moans, the movement of her arm making it quite clear that she's fingering herself."
					+ "</p>"
					+ "<p>"
						+ "Despite the noise of your fight, there's no sign of the other maid whose voice you heard upon entering the house."
						+ " Given the chance to have some fun with this angry, and now very horny, maid, you wonder if you should take it, or continue on your way to find Zaranix."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false)) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getPlayer().getLocationPlace().getDialogue(false);
					}
				};
				
			} if(index==2) {
				return new ResponseSex("Use Amber", "Have some fun with this fiery maid.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "It doesn't look like any of the other maids of the household are coming to help her, so you decide to take this opportunity to have a little fun with Amber."
							+ " Stepping over to where she's leaning against the wall, you reach forwards and take hold of her arm, before pulling her hand out from under her dress."
							+ " Denied the freedom to get herself off, Amber pitifully looks up into your eyes, and instead of fury, you see them filled with burning lust."
						+ "</p>"
						+ "<p>"
							+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [amber.lips+] against yours."
							+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "Despite her currently-defeated state, you find yourself incredibly turned on by Amber's dominant, fiery personality."
							+ " Not willing to take the dominant role, but with a deep desire to have sex with the now-very-horny succubus, you walk up to where she's leaning against the wall, and sigh,"
							+ " [pc.speech('Miss Amber' was it? Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you're a stranger in her master's house, Amber looks up at you with an intense, burning passion in her eyes."
							+ " Sliding her hand out from under her dress, she pushes herself off of the wall, interrupting your sentence as she grabs your head and pulls you into a desperate kiss."
						+ "</p>"
						+ "<p>"
							+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Amber pulls back, moaning,"
							+ " [amber.speech(Good bitch! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("Transformations",
						"Get Amber to use [npc.her] demonic powers to transform [npc.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getAmber());
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
							+ "Amber lets out a deeply satisfied sigh, before sinking to the floor in total exhaustion."
							+ " Despite her fatigue, you see one of her hands slip down between her legs, and, quite clearly still overwhelmed by lust, she starts masturbating in front of you."
							+ " She's obviously not going to pose much of a threat like this, so you turn your attention back towards the task at hand; that of finding Zaranix and rescuing Arthur."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Amber lets out a desperate whine, before sinking to the floor and pressing both of her hands to her groin."
							+ " Having not orgasmed during sex, she's quite clearly still overcome by her intense lust, and starts frantically masturbating right there on the floor."
							+ " She's obviously not going to pose much of a threat like this, so you turn your attention back towards the task at hand; that of finding Zaranix and rescuing Arthur."
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
				return new Response("Continue", "Continue exploring Zaranix's house.", Main.game.getPlayer().getLocationPlace().getDialogue(false)) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getPlayer().getLocationPlace().getDialogue(false);
					}
				};
				
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
						+ "The fiery-haired maid proves to be too much for you to handle, and, unable to continue fighting, you stumble back against a nearby wall."
						+ " Amber's mocking laughter rings out as she sees that you're defeated, and, stepping towards you, she growls,"
						+ " [amber.speech(You stupid fucking bitch! Now you're going to <i>really</i> pay!)]"
					+ "</p>"
					+ "<p>"
						+ "You look up to see the burning amber eyes of the angry succubus glaring into yours, and before you can do or say anything, she reaches up with one hand and firmly grabs you by the neck."
						+ " You let out a coughing splutter as her grip tightens, which only serves to make her let out another mocking laugh,"
						+ " [amber.speech(Hahaha! Pathetic!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she suddenly hurls you to the floor, and you let out a cry as you fall down onto all fours."
						+ " Amber steps around behind you, and as you try to crawl away, she delivers a stingingly-sharp slap to your rear end,"
						+ " [amber.speech(Stupid bitch! You've got me all worked up now! Time to teach you a lesson!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Amber starts fucking you.",
						false, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getAmber(), SexPositionSlot.DOGGY_BEHIND_AMBER)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS_AMBER))),
						AFTER_SEX_DEFEAT,
						"<p>"
							+ "Amber takes a firm grasp of your hips, before roughly lifting your ass a little higher."
							+ " The sharp slap of her hand across your right cheek causes you to let out a little cry, which is met by the maid's aggressive growl,"
							+ " [amber.speech(Squeal all you want bitch, <i>you're mine now!</i>)]"
						+ "</p>");
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
						+ "You collapse to the floor, totally exhausted from Amber's rough treatment of you."
						+ " With a final, scornful sneer, Amber stands up,"
						+ " [amber.speech(That's all bitches like you are good for! Now get the fuck out of this house!)]"
					+ "</p>"
					+ "<p>"
						+ "Reaching down to grab "+(Main.game.getPlayer().getHairRawLengthValue()>4?"a fistful of your [pc.hair]":"you by the back of the neck")+", Amber mercilessly drags you to the front door."
						+ " Yanking it open, she spits one last hateful remark your way, before literally kicking you out into the street and slamming the door behind you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Amber throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
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
	public Attack attackType() {
		if (Math.random() < 0.6
				|| this.getManaPercentage() <= 0.4f
				|| (Main.game.getPlayer().getStatusEffects().contains(StatusEffect.DAZED)
						&& this.getStatusEffects().contains(StatusEffect.ARCANE_SHIELD))) {
			return Attack.MAIN;
		} else {
			return Attack.SPELL;
		}
	}
	
	@Override
	public Spell getSpell() {
		if (!Main.game.getPlayer().getStatusEffects().contains(StatusEffect.DAZED)) {
			return Spell.SLAM_1;
		} else {
			return Spell.ARCANE_SHIELD;
		}
	}
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public int getExperienceFromVictory() {
		return 50;
	}
	
	public int getLootMoney() {
		return 100;
	}

	// Sex:
	
	public SexType getForeplayPreference() {
		if(Sex.getSexManager().getPosition() == SexPositionNew.DOGGY_AMBER) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER);
			} else {
				return new SexType(PenetrationType.FINGER_PARTNER, OrificeType.ANUS_PLAYER);
			}
		}
		
		return super.getForeplayPreference();
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexManager().getPosition() == SexPositionNew.DOGGY_AMBER) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER);
			} else {
				return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER);
			}
		}

		return super.getMainSexPreference();
	}
	
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
}