package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.PenisSize;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.character.effects.Fetish;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingSet;
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.SlaverAlley;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Finch extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing groin = ClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_BRIEFS, Colour.CLOTHING_BLACK, false),
			legs = ClothingType.generateClothing(ClothingType.LEG_CROTCHLESS_CHAPS, Colour.CLOTHING_BLACK, false),
			torso = ClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_BLACK, false),
			socks = ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false),
			shoes = ClothingType.generateClothing(ClothingType.FOOT_WORK_BOOTS, Colour.CLOTHING_BLACK, false);
	
	public Finch() {
		super(new NameTriplet("Finch"),
				"Finch is the manager of Slaver Alley's 'Slave Administration' building."
						+ " Although he acts friendly enough, you can't help but wonder if his disarming disposition is just for show."
						+ " After all, would the manager of Dominion's 'Slave Administration' really have got to that position just by being nice?",
				10,
				Gender.MALE,
				RacialBody.CAT_MORPH, RaceStage.PARTIAL_FULL, new CharacterInventory(10),
				WorldType.SLAVER_ALLEY, SlaverAlley.SLAVERY_ADMINISTRATION, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeColour(Colour.EYE_GREEN);
		this.setHairColour(Colour.COVERING_BLACK);
		this.setSkinColour(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK);
		this.setSkinColour(BodyCoveringType.HUMAN, Colour.HUMAN_SKIN_LIGHT);
		this.setPenisSize(PenisSize.ONE_TINY.getMedianValue());
		this.setTesticleSize(TesticleSize.ONE_TINY.getValue());
		this.setFemininity(25);
		
		this.addFetish(Fetish.FETISH_EXHIBITIONIST);
		this.addFetish(Fetish.FETISH_SADIST);
		this.addFetish(Fetish.FETISH_DEFLOWERING);

		applyReset();
	}

	@Override
	public void applyReset() {
		resetInventory();
		
		this.setMoney(10);
		
		this.equipClothingFromNowhere(groin, true, this);
		this.equipClothingFromNowhere(legs, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(socks, true, this);
		this.equipClothingFromNowhere(shoes, true, this);

		for(int i = 0; i<6; i++) {
			this.addClothing(ClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR), false);
		}
		
		for(ClothingType clothing : ClothingType.values()) {
			if(clothing.getClothingSet() == ClothingSet.BDSM) {
				for(int i = 0; i<2; i++) {
					this.addClothing(ClothingType.generateClothing(clothing), false);
				}
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public String getTraderDescription() {
		return "<p>"
					+ "[finch.speech(Looking for the good stuff, huh?)] [finch.name] says, winking at you as he hands you a 'slaver-exclusive' sales brochure,"
					+ " [finch.speech(Let me know what you fancy!)]"
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item instanceof AbstractClothing) {
			return true;
		}
		
		return false;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects)
			applyReset();
	}

	// Combat:
	@Override
	public String getCombatDescription() {
		return null;// You never fight
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null; // You never fight
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null; // You never fight
	}

	@Override
	public Attack attackType() {
		return null; // You never fight
	}

	@Override
	public int getExperienceFromVictory() {
		return 0;
	}

}