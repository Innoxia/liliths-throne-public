package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.effects.Fetish;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.Jungle;

/**
 * @since 0.1.79
 * @version 0.1.79
 * @author Innoxia, Kumiko
 */
public class Pazu extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			shorts = ClothingType.generateClothing(ClothingType.LEG_SHORTS, Colour.CLOTHING_WHITE, false);

	public Pazu() {
		super(new NameTriplet("Pazu"),
				"Pazu is a shy male harpy. While very feminine looking by a human’s standards, for harpy standards he is considered too masculine."
						+ " His refusal to wear feminine clothing, unlike most male harpies, further accentuates his masculinity.",
				1, Gender.MALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(1), WorldType.JUNGLE, Jungle.JUNGLE_CLUB, true); //TODO He's in the jungle for now ^^

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeColour(Colour.EYE_PINK);
		this.setHairColour(Colour.FEATHERS_LILAC);
		this.setSkinColour(BodyCoveringType.HUMAN, Colour.HUMAN_SKIN_LIGHT);
		this.setSkinColour(BodyCoveringType.FEATHERS, Colour.FEATHERS_LILAC);

		this.setAssVirgin(true);
		this.setFaceVirgin(true);
		
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		
		this.setPenisSize(6);
		
		this.setHeight(156);
		
		this.setFemininity(39);
		
		this.setAttribute(Attribute.STRENGTH, 1);
		this.setAttribute(Attribute.INTELLIGENCE, 22);
		this.setAttribute(Attribute.FITNESS, 30);
		this.setAttribute(Attribute.CORRUPTION, 5);
		
		this.addFetish(Fetish.FETISH_ORAL);

		this.equipClothingFromNowhere(shorts, true, this);
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().lightTheme) {
			return "#7000FA";
		} else {
			return "#C18FFF";
		}
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
		if (applyEffects)
			applyReset();
	}

	// Combat (you never fight Pazu):
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