package com.base.game.character.npc.dominion;

import java.util.List;
import java.util.Map;

import com.base.game.character.NameTriplet;
import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.character.SexualOrientation;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.attributes.CorruptionLevel;
import com.base.game.character.body.types.ArmType;
import com.base.game.character.body.types.AssType;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.BreastType;
import com.base.game.character.body.types.EarType;
import com.base.game.character.body.types.FaceType;
import com.base.game.character.body.types.HornType;
import com.base.game.character.body.types.LegType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.TailType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.body.types.WingType;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Femininity;
import com.base.game.character.body.valueEnums.HairLength;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.combat.DamageType;
import com.base.game.combat.Spell;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.places.dominion.EnforcerHQDialogue;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.responses.ResponseEffectsOnly;
import com.base.game.dialogue.responses.ResponseSex;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.weapon.WeaponType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.game.sex.SexPace;
import com.base.game.sex.managers.dominion.brax.SMBraxDom;
import com.base.game.sex.managers.dominion.brax.SMBraxSubCowgirl;
import com.base.game.sex.managers.dominion.brax.SMBraxSubStart;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.utils.Util.Value;
import com.base.world.WorldType;
import com.base.world.places.Dominion;
import com.base.world.places.EnforcerHQ;

/**
 * @since 0.1.5
 * @version 0.1.8
 * @author Innoxia
 */
public class Brax extends NPC {

	private static final long serialVersionUID = 1L;

	private static StringBuilder descriptionSB;

	public Brax() {
		super(new NameTriplet("Brax", "Bree", "Brandi"),
				"The 'Chief of Dominion Operations', Brax is a high-ranking enforcer. Muscular, handsome, and with an incredibly dominant personality, he's the focus of every female enforcer's attention.", 3, Gender.MALE,
				RacialBody.WOLF_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.ENFORCER_HQ, EnforcerHQ.BRAXS_OFFICE, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeColour(Colour.EYE_YELLOW);
		this.setHairColour(Colour.COVERING_BLACK);
		this.setSkinColour(BodyCoveringType.LYCAN_FUR, Colour.COVERING_WHITE);

		this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());

		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, false), true, this);

		this.equipMainWeapon(WeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.FIRE), false);
		
		this.addFetish(Fetish.FETISH_DOMINANT);
	}
	
	@Override
	public String getDescription() {
		if(Main.game.getDialogueFlags().bimboifiedBrax) {
			return "The one-time 'Chief of Dominion Operations', [brax.name] is now completely unrecognisable from [brax.her] former self."
					+ " With some help from Candi, she's been transformed into a brain-dead bimbo, who can only think about where the next cock is coming from.";
			
		} else if(Main.game.getDialogueFlags().feminisedBrax) {
			return "The one-time 'Chief of Dominion Operations', [brax.name] is almost unrecognisable from [brax.her] former self."
					+ " With some help from Candi, you've transformed [brax.herHim] into a wolf-girl."
					+ " Where once [brax.she] was muscular and dominant, [brax.she]'s now feminine and submissive, and meekly agrees to do anything that's asked of [brax.herHim].";
			
		} else {
			return "The 'Chief of Dominion Operations', Brax is a high-ranking enforcer. Muscular, handsome, and with an incredibly dominant personality, he's the focus of every female enforcer's attention.";
		}
		
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			setPendingClothingDressing(true);
		}
	}
	
	@Override
	public void equipClothing() {
		deleteAllEquippedClothing();
		
		if(Main.game.getDialogueFlags().braxBeaten) {
			AbstractClothing collar = ClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, false);
			collar.setSealed(true);
			collar.setColour(Colour.CLOTHING_SILVER);
			this.equipClothingFromNowhere(collar, true, this);
		}
		
		if(isFeminine()) {
			if(hasFetish(Fetish.FETISH_BIMBO)) {
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_PANTIES, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.CHEST_TAPE_CROSSES, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_FISHNET_TOP, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_PLEATED, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.SOCK_FISHNET_STOCKINGS, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.HAND_FISHNET_GLOVES, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
				
				this.setPiercedEar(true);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNose(true);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNavel(true);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);
				
			} else {
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FINGER_RING,  Colour.CLOTHING_SILVER, false), true, this);
			}
			
		} else {
			this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, false), true, this);
			this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, false), true, this);
		}
		
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(){
		if(Main.game.getDialogueFlags().bimboifiedBrax) {
			return SexPace.SUB_EAGER;
			
		} else if(Main.game.getDialogueFlags().feminisedBrax) {
			return SexPace.SUB_NORMAL;
			
		} else {
			if(Main.game.isNonConEnabled()) {
				return SexPace.SUB_RESISTING;
				
			} else {
				return SexPace.SUB_NORMAL;
			}
		}
	}

	@Override
	public String getSpeechColour() {
		if(Main.getProperties().lightTheme) {
			if(Main.game.getDialogueFlags().bimboifiedBrax) {
				return "#FF0AA5";
			} else if(Main.game.getDialogueFlags().feminisedBrax) {
				return "#C60AFF";
			} else {
				return "#1F35FF";
			}
			
		} else {
			if(Main.game.getDialogueFlags().bimboifiedBrax) {
				return "#E36DE1";
			} else if(Main.game.getDialogueFlags().feminisedBrax) {
				return "#D79EFF";
			} else {
				return "#ADB4FF";
			}
		}
	}

	@Override
	public void applyReset() {
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	// Combat:
	@Override
	public String getCombatDescription() {
		return "Brax is crouched down in a fighting stance. His huge muscles and obvious knowledge of the arcane make him a formidable opponent."
				+ " An unsettling grin is plastered across his face, and you get the feeling that he's enjoying the chance to have a fight.";
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		if (attackType == Attack.MAIN) {
			switch (Util.random.nextInt(3)) {
			case 0:
				return "<p>"
						+ "Brax lunges forwards, attempting to deliver a punch to your torso."
						+ (isHit ? " The arcane flames swirling around his forearm dart forwards as his fist makes contact, enveloping you for a brief moment in a fiery vortex."
								+ " Although the flames don't cause any real pain, the arcane's strength-sapping effect still knocks you back, reeling." : " You manage to twist away to one side, allowing Brax's fist to sail harmlessly by.")
						+ "</p>";
			case 1:
				return "<p>"
						+ "With surprising swiftness, Brax darts forwards, lifting his leg as he attempts to land a kick."
						+ (isHit ? " As his wolf-like foot connects with your side, the arcane flames swirling around his forearm dart down the length of his body to strike out at the point of contact."
								+ " You're briefly enveloped in a fiery vortex, and although the flames don't cause any real pain, the arcane fire still adds considerable power to Brax's attack."
								: " You see his attack coming and step back to avoid the blow.")
						+ "</p>";
			default:
				return "<p>"
						+ "Brax steps forwards as he attempts to punch you."
						+ (isHit ? " The arcane flames swirling around his forearm dart forwards as his fist makes contact, enveloping you for a brief moment in a fiery vortex."
								+ " Although the flames don't cause any real pain, the arcane's strength-sapping effect still knocks you back, reeling." : " You jump out of the way, managing to dodge his clearly-telegraphed attack.")
						+ "</p>";
			}
		} else {
			switch (Util.random.nextInt(2)) {
			case 0:
				return "<p>"
						+ "With a grin, Brax focuses on the arcane fire swirling around his arm, and with a sudden thrust forwards, he casts a spell!"
						+ "</p>";
			default:
				return "<p>"
						+ "Brax focuses on the arcane fire swirling around his arm, and with a sudden thrust forwards, he casts a spell!"
						+ "</p>";
			}
		}

	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY){
				@Override
				public QuestLine getQuestLine() {
					if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_C_WOLFS_DEN)
						return QuestLine.MAIN;
					else
						return null;
				}
			};
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}

	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated Brax!";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Brax takes one last half-hearted swing at you, which you easily sidestep."
					+ " Stumbling past you, he ends up collapsing against his desk, completely and utterly defeated."
					+ "</p>"
					+ "<p>"
					+ "Walking over to his submissive form, you can't help but smirk as you hear him surrender, "
					+ "[brax.speech(P-Please... No more...)]"
					+ "</p>"
					+ "<p>"
					+ "Grinning down at the broken wolf-boy, you demand to know what's happened to Arthur."
					+ " Turning to one side, Brax pulls a piece of the paper out from a pile of freshly-signed documents, and as he hands it to you, you discover what's happened to Arthur:"
					+ "</p>"
					+ "<p>"
					+ "<h6 style='text-align:center;'>Dominion Enforcer Department</h6>"
					+ "<h5 style='text-align:center;'>RECORD OF SLAVE TRANSFER</h5>"
					+ "<p style='text-align:center;'>The person of <i>Arthur Fairbanks</i>, having being found guilty of <i>treason</i>, has, according to law, been enslaved."
					+ " Following standard procedure, the slave's ownership has been transferred from the Dominion Enforcer Department to a registered slave trader, who has been chosen by random lottery."
					+ "</br></br>"
					+ "Officer in charge of transferring slave ownership: <i>Brax</i>"
					+ "</br></br>"
					+ "Slave trader taking ownership: <i>Scarlett</i>"
					+ "</br></br>"
					+ "Contact address: <i>Scarlett's shop, Slaver Alley</i></p>"
					+ "</p>"
					+ "<p>"
					+ "As you read it over a second time, you let out an annoyed tutting sound, realising that Arthur is a hard person to track down."
					+ " It looks like you're going to have to make your way to Slaver Alley and see if you can find some way to buy Arthur's freedom from this 'Scarlett' person."
					+ "</p>"
					+ "<p>"
					+ "A little groan brings your attention back to Brax, and as you look down at him, you see a lustful glimmer in his eyes."
					+ " Surprisingly, it doesn't seem as though anyone outside the office has heard your fight, and you wonder if you should take advantage of Brax's weakened state..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Resist", "As tempting as it is, you don't really want to have sex with Brax...", AFTER_COMBAT_VICTORY_NO_SEX){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().braxBeaten=true;
						Main.game.getPlayer().addClothing(ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT), false);
						Main.game.getPlayer().addClothing(ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS), false);
					}
				};
				
			} else if (index == 2) {
				return new Response("Dominate Brax",
						"Brax's broken, horny form is too much for you to resist, and you can't help but smile down deviously at the wolf-boy as you prepare to make him your bitch.",
						AFTER_COMBAT_VICTORY_DOMINANT_SEX,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT)), CorruptionLevel.ONE_VANILLA, null, null, null){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().braxBeaten=true;
						Main.game.getPlayer().addClothing(ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT), false);
						Main.game.getPlayer().addClothing(ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS), false);
					}
				};
				
			} else if (index == 3) {
				return new Response("Submit to Brax",
						"Although you've defeated him, your submissive nature is causing you to consider letting Brax dominantly fuck you...",
						AFTER_COMBAT_VICTORY_SUBMISSIVE_SEX,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null){
					@Override
					public Femininity getFemininityRequired() {
						return Femininity.FEMININE;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().braxBeaten=true;
						Main.game.getPlayer().addClothing(ClothingType.generateClothing(ClothingType.ENFORCER_SHIRT), false);
						Main.game.getPlayer().addClothing(ClothingType.generateClothing(ClothingType.ENFORCER_SHORTS), false);
					}
				};
				
			} else {
				return null;
			}
		}

	};
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_NO_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You don't really feel like having sex with the guy who just tried to beat you up."
						+ " Instead, you push Brax back into his chair, tutting in disapproval as he lets out a desperate whine."
					+ "</p>"
					+ "<p>"
						+ "Taking one last look around Brax's office, you notice that there's a neatly-folded spare uniform lying on top of a cabinet."
						+ " Deciding to punish Brax a little more for trying to beat you up, <b>you take the spare uniform, and add it to your inventory</b>."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), Dominion.CITY_ENFORCER_HQ, true);
						Main.game.getBrax().setLocation(WorldType.ENFORCER_HQ, EnforcerHQ.RECEPTION_DESK);
						Main.game.getBrax().setPendingClothingDressing(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_DOMINANT_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You feel a devious smile spread across your face as you walk over to Brax. Leaning into him, you growl down into his ear, "
						+ UtilText.parsePlayerSpeech("I hope you like it rough!")
					+ "</p>"
					+ "<p>"
						+ "Brax lets out a surprised cry as you reach down and firmly grab his groin with one hand."
						+ " Pressing your lips against his to prevent him from making any more noise, you thrust your tongue into his mouth, squeezing down on his satisfyingly large package as he squirms and melts under your touch."
					+ "</p>"
					+ "<p>"
						+ "Breaking off the kiss, but making sure not to let go of his crotch, you growl down to him again, "
						+ UtilText.parsePlayerSpeech("So, you ready to be my little bitch?")
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(W-Wait I-)]"
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Wrong answer!")
						+" you cry, giving Brax's throbbing cock a hard squeeze as you interrupt his response."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Aah! Yes! Yes, I like it rough!)]"
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Mmm, that's right, ")
						+" you sigh, softening your grip before running your fingers up and down Brax's shorts, biting your lip as you get a good feel of the impressive length of his throbbing cock, "
						+ UtilText.parsePlayerSpeech("and who's going to be a good little bitch for their new alpha?")
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(I-I am...)]"
						+" Brax groans, admitting defeat."
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Good little beta!")
						+" you cry, happy now that you've asserted your dominance over the handsome wolf-boy."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Get started", "Start dominating your new bitch.", EnforcerHQDialogue.EXTERIOR,
						Main.game.getBrax(), new SMBraxSubStart(), AFTER_DOMINANT_SEX,
						"<p>"
							+ "With a forceful push, you shove Brax down onto his knees before you."
							+ " His meek, submissive look couldn't be further from the aggressive snarl that he greeted you with when you entered his office, and you grin down at him as you prepare to make him your bitch."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_SUBMISSIVE_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You reach down and run your fingertips over Brax's crotch, biting your lip and letting out a soft squeal as you feel that he's already hard."
						+ " Encouraged by your touch, Brax leans forwards, and you happily accept his tongue into your mouth as he pulls you down into a kiss."
					+ "</p>"
					+ "<p>"
						+ "Your submissive nature starts to take over, and after gently breaking off the kiss, you whisper down to Brax, "
						+ UtilText.parsePlayerSpeech("I bet you're pretty angry at being beaten like that, huh?")
					+ "</p>"
					+ "<p>"
						+ "As Brax grunts in agreement, you continue teasing him, "
						+ UtilText.parsePlayerSpeech("I wonder what you'd like to do to me to get your revenge... Perhaps you'd grab me with those big, strong hands...")
					+ "</p>"
					+"<p>"
						+ "You reach down, and, taking Brax's wolf-like hands in yours, guide them up to your waist."
						+ " As he starts to get the idea, you feel him roughly grab your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, causing you to let out a lewd little cry."
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("And then, pull me in against you as you ruthlessly tongue-fuck my mou- ~hrmp!~")
					+ "</p>"
					+"<p>"
						+ "Brax does exactly what you suggest, and eagerly jerks you forwards, slamming his lips against yours as he starts passionately kissing you."
						+ " He stands up, reaching down to grope and squeeze your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass as you grind yourself against him."
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Then mayb-")
						+" you try to continue, after breaking of the kiss, but Brax reaches up and presses his hand down firmly over your mouth."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Then maybe you shut up, get down on all fours, and present yourself like the submissive little slut you clearly are!)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Obey", "Do as Brax says and present yourself for him.", EnforcerHQDialogue.EXTERIOR,
						Main.game.getBrax(), new SMBraxDom(), AFTER_SUBMISSIVE_SEX,
						"<p>"
							+ "You obediently do as Brax commands and drop down on all fours right there in the middle of his office."
							+ (Main.game.getPlayer().getTailType() == TailType.LYCAN
								?" You can't help but shake your ass at the dominant wolf-boy, and you playfully flick your wolf-like tail back and forth, making pitiful little whining noises as you eagerly plead for Brax's thick cock."
								:" You can't help but shake your ass at the dominant wolf-boy, and you turn your head and make a show of biting your lip, making pitiful little whining noises as you eagerly plead for Brax's thick cock.")
						+ "</p>"
						+ "<p>"
							+ "Brax stands behind you, grinning, and you know that it's only going to be a matter of seconds before he fills you with his alpha cock."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};

	
//	private static String fetishChanges = "";
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by Brax!";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().braxTransformedPlayer) {
				return "<p>"
						+ "You can't carry on fighting any longer, and your legs give out from beneath you as you sink down onto your knees."
						+ " As Brax lets out a deep laugh, you find yourself looking down at the floor, trying to avoid his powerful gaze."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Hah! Looks like you're still just a submissive little bitch! Didn't you learn anything from last time?!)]"
						+ " he growls, stepping forwards and grabbing you by the neck, "
						+ "[brax.speech(Now lets get you another drink!)]"
					+ "</p>"
					+ "<p>"
						+ "You cry out as Brax roughly drags you across the room to his desk."
						+ " Fishing about in one of the drawers, he quickly finds what he's looking for, and, with a light clink of glass, places a very familiar-looking bottle on the desk in front of you."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(I was so pleased with the last one, I got a few more of these potions made!)]"
						+ " Brax says, pulling out the glass stopper before roughly shoving the bottle's neck down your throat,"
						+ " [brax.speech(That's right, drink it all down... Good girl!)]"
					+ "</p>"
					+ "<p>"
						+ "With the bottle's contents already pouring out down your throat, you have little option but to do as Brax commands, and quickly start gulping down the sickly sweet liquid."
						+ " It only takes a matter of seconds before the bottle is empty, and Brax carelessly tosses it to one side before stepping back and grinning hungrily at you."
						+ " You suddenly start to feel uncontrollably turned on, but before you can make a move on Brax, you let out a desperate cry as the rest of the bottle's contents start to have an effect..."
					+ "</p>";
				
			} else {
				return "<p>"
							+ "You can't carry on fighting any longer, and your legs give out from beneath you as you sink down onto your knees."
							+ " As Brax lets out a deep laugh, you find yourself looking down at the floor, trying to avoid his powerful gaze."
						+ "</p>"
						+ "<p>"
							+ "[brax.speech(Hah! What a submissive little bitch! I was kinda hoping that you'd put up a better fight than that, but, whatever,)]"
							+ " he growls, stepping forwards and grabbing you by the neck, "
							+ "[brax.speech(Now let's talk more about that little punishment I mentioned.)]"
						+ "</p>"
						+ "<p>"
							+ "You cry out as Brax roughly drags you across the room to his desk."
							+ " Fishing about in one of the drawers, he quickly finds what he's looking for, and, with a light clink of glass, places a very delicate-looking bottle on the desk in front of you."
						+ "</p>"
						+ "<p>"
							+ "[brax.speech(I got this made up for that useless bimbo you saw downstairs, but the ungrateful slut said she didn't want to be a wolf-girl...)]"
							+ " Brax says, pulling out the glass stopper before roughly shoving the bottle's neck down your throat, "
							+ "[brax.speech(That's right, drink it all down... Oh fuck, this is gonna be good!)]"
						+ "</p>"
						+ "<p>"
							+ "With the bottle's contents already pouring out down your throat, you have little option but to do as Brax commands, and quickly start gulping down the sickly sweet liquid."
							+ " It only takes a matter of seconds before the bottle is empty, and Brax carelessly tosses it to one side before stepping back and grinning hungrily at you."
							+ " You suddenly start to feel uncontrollably turned on, but before you can make a move on Brax, you let out a desperate cry as the rest of the bottle's contents start to have an effect..."
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Transformation time", "There's no mistaking it, you're starting to transform!", AFTER_DEFEAT_TRANSFORMATION){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().braxTransformedPlayer = true;
						
						Main.game.getPlayer().setArmType(ArmType.LYCAN);
						Main.game.getPlayer().setAssType(AssType.WOLF_MORPH);
						Main.game.getPlayer().setBreastType(BreastType.WOLF_MORPH);
						Main.game.getPlayer().setEarType(EarType.LYCAN);
						Main.game.getPlayer().setEyeType(BodyCoveringType.EYE_LYCAN);
						Main.game.getPlayer().setFaceType(FaceType.LYCAN);
						Main.game.getPlayer().setHairType(BodyCoveringType.LYCAN_FUR);
						Main.game.getPlayer().setHornType(HornType.NONE);
						Main.game.getPlayer().setLegType(LegType.LYCAN);
						Main.game.getPlayer().setPenisType(PenisType.NONE);
						Main.game.getPlayer().setSkinType(BodyCoveringType.LYCAN_FUR);
						Main.game.getPlayer().setTailType(TailType.LYCAN);
						Main.game.getPlayer().setVaginaType(VaginaType.WOLF_MORPH);
						Main.game.getPlayer().setWingType(WingType.NONE);
						
						Main.game.getPlayer().setFemininity(Femininity.FEMININE_STRONG.getMinimumFemininity());
						Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						if(Main.game.getPlayer().getVaginaWetness().getValue()<Wetness.THREE_WET.getValue())
							Main.game.getPlayer().setVaginaWetness(Wetness.THREE_WET.getValue());
						
						Main.game.getPlayer().setSkinColour(BodyCoveringType.LYCAN_FUR, Colour.COVERING_WHITE);
						Main.game.getPlayer().setHairColour(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK);
						Main.game.getPlayer().setEyeColour(Colour.EYE_YELLOW);
						Main.game.getPlayer().setBreastSize(CupSize.E.getMeasurement());
						
						if(Main.getProperties().multiBreasts!=0) {
							Main.game.getPlayer().setBreastRows(3);
						}
						
						if(Main.game.getPlayer().getAttributeValue(Attribute.CORRUPTION)<CorruptionLevel.TWO_HORNY.getMinimumValue())
							Main.game.getPlayer().setAttribute(Attribute.CORRUPTION, CorruptionLevel.TWO_HORNY.getMinimumValue());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AFTER_DEFEAT_TRANSFORMATION = new DialogueNodeOld("Brax's Office", "-", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			descriptionSB.append(
					"<p>"
						+ "The liquid's transformative force is enough to knock to your knees, and you collapse to the floor, panting, as you feel your body start to change."
						+ " You can't stand the powerful rush of transformations that suddenly wash through your body, and you find yourself collapsing to the floor as you pass out..."
					+ "</p>"
					+ "<p>"
						+ "..."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Hrmph! She never said anything about them losing consciousness! Hey! Wake up!)]"
					+ "</p>"
					+ "<p>"
						+ "With Brax's worried voice sounding in your ears, you slowly start to come back to your senses."
						+ " Blinking your eyes open, you see a look of relief wash over the wolf-like face that's hovering over you."
						+ " Pushing yourself up on your elbows, you find yourself quickly recovering, and as Brax helps you to stand up on unfamiliar, shaking legs, the feeling of exhaustion is quickly replaced with an intense, burning lust."
					+ "</p>"
					+ "<p>"
						+ "Without thinking, you grab Brax's head and pull him into a manic, desperate kiss, grinding yourself against him as he tries to reel back in shock."
						+ " After a moment, you feel the wolf-boy start to return the kiss, and you let out a happy giggle as you start making out with the very guy who's just forced you to transform..."
					+ "</p>"
					+ "<p>"
						+ "As that thought crosses your mind, your eyes open wide, and you get your lust under control for a moment as you step back, looking down at your body to see what's changed."
						+ " You hear Brax let out a laugh as he notices that you're trying to find out what he's done to you, and, with a powerful grip, he grabs your wolf-like muzzle and turns your head to face a mirror hanging on one wall."
					+ "</p>"
					+ "<p>"
						+ "As you see your new reflection, you let out a little gasp."
						+ " Brax has transformed you into his perfect vision of a greater wolf-girl, and your yellow, predatory eyes open wide in shock as you feel the weight of three pairs of huge, E-cup tits that are now sitting on your chest."
						+ " Fur covers your entire body, and as Brax steps up behind you, you see that it's the exact same bright white colour as his is."
						+ " Your hands, feet and face have all transformed into anthropormophic wolf-like counterparts, and a pair of fluffy wolf-like ears and a long, shaggy tail finish off your new look."
					+ "</p>"
					+ "<p>"
						+ "You see Brax's hand reaching around in the mirror, and with a pathetic little yelp, you quiver as he roughly grabs your crotch, "
						+ "[brax.speech(This little cunt belongs to me, understood?)]"
					+ "</p>"
					+ "<p>"
						+ " The final effect of the transformation makes itself known, and as Brax roughly gropes your body, something unlocks in your mind, and you find yourself wanting nothing more than to be dominated by your new master."
						+ " You lean back into the masculine wolf-morph, feeling his hot breath on your neck as you let out a soft moan."
						+ " Reaching down, you eagerly guide his paw-like hands over your new breasts, letting out little squeals of joy as he sinks his greedy fingers into your pillowy mounds of flesh."
					+ "</p>"
//					+ fetishChanges
					+ "<p>"
						+ "You see Brax's reflection smiling greedily back at you, and he issues you a command as he steps back and starts to undress, "
						+ "[brax.speech(Get down and present yourself like the bitch you are.)]"
					+ "</p>");

			return descriptionSB.toString();
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseSex("Obey", "The arousing liquid you've just been forced to drink is forcing you to obey, and you eagerly fall down on all fours so that Brax can fuck you, doggy-style.", EnforcerHQDialogue.EXTERIOR,
						Main.game.getBrax(), new SMBraxDom(), AFTER_SUBMISSIVE_SEX,
						"<p>"
							+ "You obediently do as Brax commands and drop down on all fours right there in the middle of his office."
							+ (Main.game.getPlayer().getTailType() == TailType.LYCAN
								?" You can't help but shake your ass at the dominant wolf-boy, and you playfully flick your wolf-like tail back and forth, making pitiful little whining noises as you eagerly plead for Brax's thick cock."
								:" You can't help but shake your ass at the dominant wolf-boy, and you turn your head and make a show of biting your lip, making pitiful little whining noises as you eagerly plead for Brax's thick cock.")
						+ "</p>"
						+ "<p>"
							+ "Brax stands behind you, grinning, and you know that it's only going to be a matter of seconds before he fills you with his alpha cock."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_SUBMISSIVE_SEX = new DialogueNodeOld("Brax is done", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "[brax.speech(You're a pretty good fuck, slut!)]"
					+" Brax grunts, releasing his grip on your hips as you collapse to the floor, totally worn out from his dominant treatment of you."
					+ "</p>"
					+ "<p>"
					+ "With a satisfied sigh, you feel your eyelids closing, and the last thing you hear before passing out is Brax laughing down at the mess he's made of you."
					+ "</p>"
					+ "<p>"
					+ "..."
					+ "</p>"
					+ "<p>"
					+ "With a soft moan, you slowly open your eyes, the memory of Brax's rough fucking filling your groggy mind."
					+ " After a few moments, your head starts to clear, and you see that you've been unceremoniously dumped out the back of the Enforcer HQ."
					+ "</p>"
					+(Main.game.getDialogueFlags().braxBeaten
						?"<p>"
							+ "Although you've been thrown out without so much as a goodbye, you feel a big smile spreading across your face."
							+ " You found out where Arthur is, and what's more, you got to have a good fuck with that hunk of a wolf-boy."
							+ "</p>"
							+ "<p>"
							+ "As you walk out from behind the Enforcer HQ, you see that an open crate, full of spare uniforms, is sitting outside a side door."
							+ " The crate is too big to fit through the doorway, and the person in charge of bringing them inside isn't anywhere to be seen."
							+ " Deciding that you want a little memento of your time with Brax, <b>you take a spare uniform, and add it to your inventory as you continue on your way</b>."
							+ "</p>"
						:"<p>"
							+ "Although you've been transformed, fucked, and thrown out without so much as a goodbye, you consider that things could always be worse."
							+ " Setting off, you try to look on the bright side."
							+ " After all, at least you weren't enslaved or anything crazy like that..."
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Carry on", "Get up and carry on your way.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), Dominion.CITY_ENFORCER_HQ, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_DOMINANT_SEX = new DialogueNodeOld("Brax collapses", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Brax is completely exhausted, and collapses harmlessly to the floor as he passes out."
						+ " Letting out a triumphant laugh, you look down at your latest sexual conquest, smirking in satisfaction as you see the happy smile covering his unconscious face."
					+ "</p>"
					+ "<p>"
						+ "Taking one last look around Brax's office, you notice that there's a neatly-folded spare uniform lying on top of a cabinet."
						+ " Deciding that you want a little memento of your time with Brax, <b>you take the spare uniform, and add it to your inventory</b>, before heading downstairs and back outside."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), Dominion.CITY_ENFORCER_HQ, true);
						Main.game.getBrax().setLocation(WorldType.ENFORCER_HQ, EnforcerHQ.RECEPTION_DESK);
						Main.game.getBrax().setPendingClothingDressing(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	@Override
	public Attack attackType() {
		if (Math.random() < 0.7
				|| this.getManaPercentage() <= 0.6f
				|| (Main.game.getPlayer().getStatusEffects().contains(StatusEffect.BURN_WEAK)
						&& this.getStatusEffects().contains(StatusEffect.FIRE_SHIELD)))
			return Attack.MAIN;
		else
			return Attack.SPELL;
	}

	@Override
	public Spell getSpell() {
		if (!Main.game.getPlayer().getStatusEffects().contains(StatusEffect.BURN_WEAK))
			return Spell.FIREBALL_1;
		else
			return Spell.FIRE_SHIELD;
	}
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public int getExperienceFromVictory() {
		return 20;
	}
	
	public int getLootMoney() {
		return 50;
	}
	
	public List<AbstractCoreItem> getLootItems() {
		return null;
	}
	
	@Override
	public Map<TFEssence, Integer> getLootEssenceDrops() {
		return Util.newHashMapOfValues(new Value<>(TFEssence.WOLF_MORPH, Util.random.nextInt(5)+4), new Value<>(TFEssence.ARCANE, 3));
	}
	
	@Override
	public String getLostVirginityDescriptor() {
		return "on the floor of his office";
	}
	
	// CoverableArea reveals:
	
	@Override
	public String getPlayerPenisRevealReaction(boolean isPlayerDom) {
		if (isPlayerDom) {
			return super.getPlayerPenisRevealReaction(isPlayerDom);
		} else {
			if (Main.game.getPlayer().isFeminine()) {
				if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
					return "<p>"
							+ "Brax lets out a surprised grunt as your tiny "
							+ Main.game.getPlayer().getPenisName(false)
							+ " is revealed, "
							+ UtilText.parseSpeech("Wait, what?! I thought you were a girl!", Sex.getPartner())
							+ "</p>";

				} else if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
					return "<p>"
							+ "Brax lets out a surprised grunt as your "
							+ Main.game.getPlayer().getPenisSize().getDescriptor()
							+ " "
							+ Main.game.getPlayer().getPenisName(false)
							+ " is revealed, "
							+ UtilText.parseSpeech("Wait, what?! You're a "
									+ Main.game.getPlayer().getGender().getName()
									+ "?!", Sex.getPartner())
							+ "</p>";

				} else if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.FOUR_HUGE.getMaximumValue()) {
					return "<p>"
							+ "Brax lets out a surprised grunt as your "
							+ Main.game.getPlayer().getPenisSize().getDescriptor()
							+ " "
							+ Main.game.getPlayer().getPenisName(false)
							+ " is revealed, "
							+ UtilText.parseSpeech("I should have guessed from that bulge...", Sex.getPartner())
							+ "</p>";

				} else if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
					return "<p>"
							+ "Brax lets out a surprised grunt as your "
							+ Main.game.getPlayer().getPenisSize().getDescriptor()
							+ " "
							+ Main.game.getPlayer().getPenisName(false)
							+ " is revealed, "
							+ UtilText.parseSpeech("Really?! A "
									+ Main.game.getPlayer().getGender().getName()
									+ " has a bigger cock than <i>me</i>?!", Sex.getPartner())
							+ "</p>";

				} else {
					return "<p>"
							+ "The "
							+ Sex.getPartner().getName()
							+ "'s jaw drops as your stallion-sized "
							+ Main.game.getPlayer().getPenisName(false)
							+ " is revealed, "
							+ UtilText.parseSpeech("How does a "
									+ Main.game.getPlayer().getGender().getName()
									+ " get a cock that big?!", Sex.getPartner())
							+ "</p>";
				}

			}
			return super.getPlayerPenisRevealReaction(isPlayerDom);
		}
	}
	
	@Override
	public String getPlayerMoundRevealReaction(boolean isPlayerDom) {
		if (isPlayerDom) {
			return super.getPlayerPenisRevealReaction(isPlayerDom);
		} else {
			return "<p>"
					+ "Brax lets out an amused grunt as he sees your doll-like crotch, "
					+ UtilText.parseSpeech("Hah! Guess I'll have to be using your ass then...", Sex.getPartner())
					+ "</p>";
		}
	}

	
	// Penetrations
	@Override
	public String getPenetrationDescription(boolean initialPenetration, PenetrationType penetrationType, OrificeType orifice) {
		if(Math.random()>0.3) {
			if(Sex.getSexManager() instanceof SMBraxSubCowgirl){
				if(orifice == OrificeType.VAGINA_PLAYER) {
					if(penetrationType == PenetrationType.PENIS_PARTNER) {
						return UtilText.returnStringAtRandom(
								"You keep bouncing up and down, slamming Brax's "+Sex.getPartner().getPenisName(true)+" in and out of your "+Main.game.getPlayer().getVaginaName(true)+".",
								"With lewd little moans, you continue bouncing up and down on Brax's "+Sex.getPartner().getPenisName(true)+".",
								"You feel Brax's "+Sex.getPartner().getPenisName(true)+" lewdly spreading out your "+Main.game.getPlayer().getVaginaName(true)+" as you ride him.",
								"You let out a gasp as you carry on spearing your "+Main.game.getPlayer().getVaginaName(true)+" on Brax's "+Sex.getPartner().getPenisName(true)+".");
					} else if(penetrationType == PenetrationType.TONGUE_PARTNER) {
						return UtilText.returnStringAtRandom(
								"You hold the top of Brax's head, moaning softly as he carries on eating you out.",
								"With a little giggle, you grind your "+Main.game.getPlayer().getVaginaName(true)+" down on Brax's wolf-like muzzle.",
								"You feel Brax's tongue eagerly lapping away at your "+Main.game.getPlayer().getVaginaName(true)+".",
								"You sink down a little further onto Brax's face, letting out a delighted sigh as you feel his tongue spearing deep into your "+Main.game.getPlayer().getVaginaName(true)+".");
					}
				}
				
				if(orifice == OrificeType.ANUS_PLAYER) {
					if(penetrationType == PenetrationType.PENIS_PARTNER) {
						return UtilText.returnStringAtRandom(
								"You keep bouncing up and down, slamming Brax's "+Sex.getPartner().getPenisName(true)+" in and out of your "+Main.game.getPlayer().getAssholeName(true)+".",
								"With lewd little moans, you continue bouncing up and down on Brax's "+Sex.getPartner().getPenisName(true)+".",
								"You feel Brax's "+Sex.getPartner().getPenisName(true)+" lewdly spreading out your "+Main.game.getPlayer().getAssholeName(true)+" as you ride him.",
								"You let out a gasp as you carry on spearing your "+Main.game.getPlayer().getAssholeName(true)+" on Brax's "+Sex.getPartner().getPenisName(true)+".");
					} else {
						return UtilText.returnStringAtRandom(
								"You hold the top of Brax's head, moaning softly as he carries on licking your "+Main.game.getPlayer().getAssholeName(true)+".",
								"With a little giggle, you grind your "+Main.game.getPlayer().getAssholeName(true)+" down on Brax's wolf-like muzzle.",
								"You feel Brax's tongue eagerly lapping away at your "+Main.game.getPlayer().getAssholeName(true)+".",
								"You sink down a little further onto Brax's face, letting out a delighted sigh as you feel his tongue spearing deep into your "+Main.game.getPlayer().getAssholeName(true)+".");
					}
				}
			}
			
			if(penetrationType == PenetrationType.PENIS_PLAYER && orifice == OrificeType.ANUS_PARTNER) {
				return UtilText.returnStringAtRandom(
						"You carry on slamming your "+Main.game.getPlayer().getPenisName(true)+" into Brax's "+Sex.getPartner().getAssholeName(true)+".",
						"Holding his hips, you carry on fucking Brax's "+Sex.getPartner().getAssholeName(true)+".",
						"You feel Brax's "+Sex.getPartner().getAssholeName(true)+" lewdly spreading out around your "+Main.game.getPlayer().getPenisName(true)+" as you thrust into him.",
						"Brax gasps and groans as you carry on spearing your "+Main.game.getPlayer().getPenisName(true)+" into his "+Sex.getPartner().getAssholeName(true)+".");
			}
		}
		
		return super.getPenetrationDescription(initialPenetration, penetrationType, orifice);
	}
	
}
