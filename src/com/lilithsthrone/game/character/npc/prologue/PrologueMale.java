package com.lilithsthrone.game.character.npc.prologue;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.Height;
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
public class PrologueMale extends NPC {

	private static final long serialVersionUID = 1L;

	public PrologueMale() {
		super(new NameTriplet("Alexander", "Alex", "Alexandria"),
				"One of the guests at the museum's opening exhibit. He's tall, handsome, and muscular, and, even better, he seems to have taken an instant liking towards you...",
				3,
				Gender.M_P_MALE,
				RacialBody.HUMAN,
				RaceStage.HUMAN,
				new CharacterInventory(10),
				WorldType.JUNGLE,
				Jungle.JUNGLE_CLUB,
				false);
		
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setFemininity(Femininity.MASCULINE_STRONG.getMedianFemininity());
		this.setHeight(Height.THREE_TALL.getMedianValue());
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HUMAN, Colour.EYE_BROWN));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, Colour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

		this.setPenisSize(PenisSize.THREE_LARGE.getMedianValue());

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OXFORD_SHIRT, Colour.CLOTHING_BLUE_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_BLACK_STEEL, false), true, this);
		
		this.addFetish(Fetish.FETISH_CUM_STUD);
		this.addFetish(Fetish.FETISH_BREASTS_OTHERS);
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
			return "#2D3795";
			
		} else {
			return "#6B86FF";
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
