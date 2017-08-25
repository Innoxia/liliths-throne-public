package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.LilayasHome;

/**
 * @since 0.1.3
 * @version 0.1.78
 * @author Innoxia
 */
public class Rose extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing underwear = ClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_BLACK, false),
			bra = ClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_BLACK, false),
			dress = ClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false),
			headpiece = ClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_BLACK, false),
			heels = ClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_BLACK, false),
			sleeves = ClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_BLACK, false),
			stockings = ClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_BLACK, false);

	public Rose() {
		super(new NameTriplet("Rose"),
				"Rose is Lilaya's slave, and is the only other member of her household that you've ever seen."
						+ " A partial cat-girl, Rose is treated with extreme fondness by Lilaya, and appears to be the only other person Lilaya has any regular contact with."
						+ " Their relationship strays into something more than a master-slave arrangement, and Rose and Lilaya can often be seen hugging and whispering to one another.",
				10, Gender.FEMALE, RacialBody.CAT_MORPH, RaceStage.PARTIAL_FULL,
				new CharacterInventory(10), WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_LAB, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_GREEN));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLACK));
		this.setCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BLACK));
		this.setCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT));

		this.setVaginaVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());

		this.equipClothingFromNowhere(underwear, true, this);
		this.equipClothingFromNowhere(bra, true, this);
		this.equipClothingFromNowhere(dress, true, this);
		this.equipClothingFromNowhere(headpiece, true, this);
		this.equipClothingFromNowhere(sleeves, true, this);
		this.equipClothingFromNowhere(stockings, true, this);
		this.equipClothingFromNowhere(heels, true, this);
	}

	@Override
	public void applyReset() {
		for (AbstractClothing c : this.getClothingCurrentlyEquipped())
			c.getDisplacedList().clear();

		if (this.getClothingInSlot(underwear.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(underwear, true, this);
		if (this.getClothingInSlot(bra.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(bra, true, this);
		if (this.getClothingInSlot(dress.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(dress, true, this);
		if (this.getClothingInSlot(headpiece.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(headpiece, true, this);
		if (this.getClothingInSlot(sleeves.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(sleeves, true, this);
		if (this.getClothingInSlot(stockings.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(stockings, true, this);
		if (this.getClothingInSlot(heels.getClothingType().getSlot()) == null)
			this.equipClothingFromNowhere(heels, true, this);
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

	// Combat (you never fight Rose):
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
	
	public static final DialogueNodeOld END_HAND_SEX = new DialogueNodeOld("Recover", "Both you and Rose and exhausted from your hand-holding session.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Rose staggers over and retrieves her little feather-duster, casting a sultry look back your way before biting her lip and hurrying off to another part of the house, no doubt to recover from your extreme hand-holding session."
					+ "</p>"
					+ "<p>"
						+ "With an exhausted sigh, you collapse down onto the room's bed, your thoughts dwelling on the amazing experience you've just had."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "You've finally recovered from your intense hand-holding session with Rose.", RoomPlayer.ROOM){
					@Override
					public void effects() {
						Main.game.getRose().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, LilayasHome.LILAYA_HOME_LAB);
					}
					
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(true);
					}
				};
			} else {
				return null;
			}
		}
	};

}