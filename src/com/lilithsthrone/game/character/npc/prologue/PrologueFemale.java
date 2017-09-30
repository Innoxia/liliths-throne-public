package com.lilithsthrone.game.character.npc.prologue;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;
import com.lilithsthrone.world.places.Jungle;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public class PrologueFemale extends NPC {

	private static final long serialVersionUID = 1L;

	public PrologueFemale() {
		super(new NameTriplet("Alexander", "Alex", "Alexandria"),
				"One of the guests at the museum's opening exhibit. She's got a beautiful face and figure, and, even better, she seems to have taken an instant liking towards you...",
				3,
				Gender.F_V_B_FEMALE,
				RacialBody.HUMAN,
				RaceStage.HUMAN,
				new CharacterInventory(10),
				WorldType.JUNGLE,
				Jungle.JUNGLE_CLUB,
				false);
		
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
		this.setHeight(Height.TWO_AVERAGE.getMedianValue());
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		this.setBreastSize(CupSize.D.getMeasurement());
		this.setAssSize(AssSize.FOUR_LARGE.getValue());
		this.setHipSize(HipSize.FOUR_WOMANLY.getValue());
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_BLUE));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BLONDE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setPiercedEar(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_RED, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_RED, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_TIGHTS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_PLUNGE_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_STILETTO_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_ELBOWLENGTH_GLOVES, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_ROSE_GOLD, false), true, this);
		
		this.addFetish(Fetish.FETISH_MASOCHIST);
		this.addFetish(Fetish.FETISH_ORAL_GIVING);
	}
	
	@Override
	public void endSex(boolean applyEffects) {
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_NORMAL;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(){
		return SexPace.SUB_EAGER;
	}

	@Override
	public String getSpeechColour() {
		if(Main.getProperties().lightTheme) {
			return "#D60092";
			
		} else {
			return "#FF80D7";
		}
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Brax is done", "Brax has finished having his fun with you.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "[brax.speech(You're a pretty good fuck, slut!)]"
					+ "</p>";
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
	
	@Override
	public String getLostVirginityDescriptor() {
		return "in the museum's restrooms";
	}
	
	// CoverableArea reveals:
	
	@Override
	public String getPlayerPenisRevealReaction(boolean isPlayerDom) {
		if (Main.game.getPlayer().isFeminine()) {
			if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.ONE_TINY.getMaximumValue()) {
				return "<p>"
						+ "[brax.name] lets out a surprised grunt as your tiny "
						+ Main.game.getPlayer().getPenisName(false)
						+ " is revealed, "
						+ UtilText.parseSpeech("Wait, what?! I thought you were a girl!", Sex.getPartner())
						+ "</p>";

			} else if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.TWO_AVERAGE.getMaximumValue()) {
				return "<p>"
						+ "[brax.name] lets out a surprised grunt as your "
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
						+ "[brax.name] lets out a surprised grunt as your "
						+ Main.game.getPlayer().getPenisSize().getDescriptor()
						+ " "
						+ Main.game.getPlayer().getPenisName(false)
						+ " is revealed, "
						+ UtilText.parseSpeech("I should have guessed from that bulge...", Sex.getPartner())
						+ "</p>";

			} else if (Main.game.getPlayer().getPenisRawSizeValue() <= PenisSize.SIX_GIGANTIC.getMaximumValue()) {
				return "<p>"
						+ "[brax.name] lets out a surprised grunt as your "
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
	
	@Override
	public String getPlayerVaginaRevealReaction(boolean isPlayerDom) {
		return "<p>"
				+ "[brax.name] lets out an amused grunt as he sees your doll-like crotch, "
				+ UtilText.parseSpeech("Hah! Guess I'll have to be using your ass then...", Sex.getPartner())
				+ "</p>";
	}

	@Override
	public String getCombatDescription() {
		return null;
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null;
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}

	@Override
	public Attack attackType() {
		return null;
	}

	
	
}
