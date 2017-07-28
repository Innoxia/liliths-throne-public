package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.effects.Fetish;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.npc.RomanceProgress;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.6x
 * @version 0.1.69.9
 * @author Innoxia
 */
public class Pix extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			groin = ClothingType.generateClothing(ClothingType.GROIN_BOYSHORTS, Colour.CLOTHING_WHITE, false),
			leg = ClothingType.generateClothing(ClothingType.LEG_YOGA_PANTS, Colour.CLOTHING_PINK_LIGHT, false),
			chest = ClothingType.generateClothing(ClothingType.CHEST_SPORTS_BRA, Colour.CLOTHING_PINK_LIGHT, false),
			socks = ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false),
			shoes = ClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_PINK_LIGHT, false);

	public Pix() {
		super(new NameTriplet("Pix"), "An extremely energetic dog-girl. While you're at the gym, Pix is never far away.",
				10, Gender.FEMALE, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, ShoppingArcade.PIXS_GYM, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeColour(Colour.EYE_BROWN);
		this.setHairColour(Colour.COVERING_BROWN_DARK);
		this.setSkinColour(BodyCoveringType.CANINE_FUR, Colour.COVERING_BROWN);
		this.setSkinColour(BodyCoveringType.HUMAN, Colour.HUMAN_SKIN_OLIVE);

		this.setVaginaVirgin(false);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue());
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastRows(3);

		this.equipClothingFromNowhere(groin, true, this);
		this.equipClothingFromNowhere(chest, true, this);
		this.equipClothingFromNowhere(leg, true, this);
		this.equipClothingFromNowhere(socks, true, this);
		this.equipClothingFromNowhere(shoes, true, this);
		
		this.addFetish(Fetish.FETISH_DENIAL);
		this.addFetish(Fetish.FETISH_DOMINANT);
	}

	@Override
	public void applyReset() {
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
		if (romanceProgress <= RomanceProgress.TWO_FRIENDLY.getProgressMax()) {
			romanceProgress += 10;
			if (romanceProgress > RomanceProgress.TWO_FRIENDLY.getProgressMax())
				romanceProgress = RomanceProgress.TWO_FRIENDLY.getProgressMax();
		}
	}
	
	public static final DialogueNodeOld PIX_POST_SEX = new DialogueNodeOld("Pix dresses you", "You're too tired to complain as Pix starts dressing you.", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 60;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Pix hooks her arms under yours and drags your almost-comatose body out from the showers."
					+ " You worry for a moment that she's going to continue with her 'fun', but instead, she quickly darts of to fetch a towel, before starting to gently dry your body off."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("You're, like, the best, y'know! Thanks for letting me have some fun, I hope you enjoyed it too!", Main.game.getPix())
					+ "</p>"
					+ "<p>"
					+ "You're far too tired to respond, and instead simply let out a satisfied groan as the dog-girl quickly dresses you."
					+ " After making sure that you're lying down comfortably on one of the benches, she gives you one last passionate kiss before giggling and running off."
					+ "</p>"
					+ "<p>"
					+ "It takes some time before you finally recover enough energy to get up and leave the gym."
					+ " Unusually, Pix is nowhere to be seen, and you wonder if she's avoiding you until you've had some time to fully recover from her 'one-to-one cooldown exercise'..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Exit gym", "Leave the gym and carry on your way.", ShoppingArcadeDialogue.ENTRY);
			} else {
				return null;
			}
		}
	};

	// Combat (you never fight Pix):
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
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}

}
