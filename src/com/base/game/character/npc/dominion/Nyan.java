package com.base.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;

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
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public class Nyan extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing panties = ClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false),
			skirt = ClothingType.generateClothing(ClothingType.LEG_PENCIL_SKIRT, Colour.CLOTHING_BLACK, false),
			bra = ClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false),
			torso = ClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_WHITE, false),
			socks = ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false),
			shoes = ClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false);

	private List<AbstractClothing> commonFemaleClothing, commonFemaleLingerie, commonFemaleAccessories,
									commonMaleClothing, commonMaleLingerie, commonMaleAccessories,
									commonAndrogynousClothing, commonAndrogynousLingerie, commonAndrogynousAccessories;

	public Nyan() {
		super(new NameTriplet("Nyan"), "Nyan is the owner of the store 'Nyan's Clothing Emporium', found in Dominion's shopping arcade."
				+ " She's extremely shy, and gets very nervous when having to talk to people.",
				10, Gender.FEMALE, RacialBody.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, ShoppingArcade.NYANS_SHOP_CLOTHING, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_BLUE));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLONDE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);

		this.setBreastSize(CupSize.B.getMeasurement());
		
		commonFemaleClothing = new ArrayList<>();
		commonFemaleLingerie = new ArrayList<>();
		commonFemaleAccessories = new ArrayList<>();
		commonMaleClothing = new ArrayList<>();
		commonMaleLingerie = new ArrayList<>();
		commonMaleAccessories = new ArrayList<>();
		commonAndrogynousClothing = new ArrayList<>();
		commonAndrogynousLingerie = new ArrayList<>();
		commonAndrogynousAccessories = new ArrayList<>();

		applyReset();
	}

	@Override
	public void applyReset() {
		resetInventory();
		
		this.setMoney(10);
		
		this.equipClothingFromNowhere(panties, true, this);
		this.equipClothingFromNowhere(bra, true, this);
		this.equipClothingFromNowhere(skirt, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(socks, true, this);
		this.equipClothingFromNowhere(shoes, true, this);
		
		commonFemaleClothing.clear();
		commonFemaleLingerie.clear();
		commonFemaleAccessories.clear();
		
		commonMaleClothing.clear();
		commonMaleLingerie.clear();
		commonMaleAccessories.clear();
		
		commonAndrogynousClothing.clear();
		commonAndrogynousLingerie.clear();
		commonAndrogynousAccessories.clear();

		// Female:
		for(ClothingType ct : ClothingType.getCommonFemaleClothing()) {
			commonFemaleClothing.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonFemaleClothing.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleClothing().get(Util.random.nextInt(ClothingType.getCommonFemaleClothing().size()))));
		
		for(ClothingType ct : ClothingType.getCommonFemaleLingerie()) {
			commonFemaleLingerie.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 3; i++)
			commonFemaleLingerie.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleLingerie().get(Util.random.nextInt(ClothingType.getCommonFemaleLingerie().size()))));
		
		for(ClothingType ct : ClothingType.getCommonFemaleAccessories()) {
			commonFemaleAccessories.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonFemaleAccessories.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleAccessories().get(Util.random.nextInt(ClothingType.getCommonFemaleAccessories().size()))));
		

		// Male:
		for(ClothingType ct : ClothingType.getCommonMaleClothing()) {
			commonMaleClothing.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonMaleClothing.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleClothing().get(Util.random.nextInt(ClothingType.getCommonMaleClothing().size()))));
		
		for(ClothingType ct : ClothingType.getCommonMaleLingerie()) {
			commonMaleLingerie.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonMaleLingerie.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleLingerie().get(Util.random.nextInt(ClothingType.getCommonMaleLingerie().size()))));
		
		for(ClothingType ct : ClothingType.getCommonMaleAccessories()) {
			commonMaleAccessories.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonMaleAccessories.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleAccessories().get(Util.random.nextInt(ClothingType.getCommonMaleAccessories().size()))));
		

		// Androgynous:
		for(ClothingType ct : ClothingType.getCommonAndrogynousClothing()) {
			commonAndrogynousClothing.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonAndrogynousClothing.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousClothing().get(Util.random.nextInt(ClothingType.getCommonAndrogynousClothing().size()))));
		
		for(ClothingType ct : ClothingType.getCommonAndrogynousLingerie()) {
			commonAndrogynousLingerie.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonAndrogynousLingerie.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousLingerie().get(Util.random.nextInt(ClothingType.getCommonAndrogynousLingerie().size()))));
		
		for(ClothingType ct : ClothingType.getCommonAndrogynousAccessories()) {
			commonAndrogynousAccessories.add(ClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonAndrogynousAccessories.add(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousAccessories().get(Util.random.nextInt(ClothingType.getCommonAndrogynousAccessories().size()))));
		
		
		
		for(AbstractClothing c : commonFemaleClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleLingerie) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleAccessories) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonMaleClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonMaleLingerie) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonMaleAccessories) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonAndrogynousClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonAndrogynousLingerie) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonAndrogynousAccessories) {
			c.setEnchantmentKnown(true);
		}
	}
	
	public void removeClothingFromLists(AbstractClothing clothing){
		commonFemaleClothing.remove(clothing);
		commonFemaleLingerie.remove(clothing);
		commonFemaleAccessories.remove(clothing);
		
		commonMaleClothing.remove(clothing);
		commonMaleLingerie.remove(clothing);
		commonMaleAccessories.remove(clothing);
		
		commonAndrogynousClothing.remove(clothing);
		commonAndrogynousLingerie.remove(clothing);
		commonAndrogynousAccessories.remove(clothing);
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
					+ "Nyan nervously leafs through her little notebook, before guiding you over to some shelves that stock what you're looking for, "
					+ "[nyan.speech(E-erm, j-just remember, I get new stock in every day! S-so if you don't like what I've got today, y-you can come back again tomorrow! I-if you want to, that is...)]"
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item instanceof AbstractClothing)
			return true;
		
		return false;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects)
			applyReset();
	}
	
	public List<AbstractClothing> getCommonFemaleClothing() {
		return commonFemaleClothing;
	}

	public List<AbstractClothing> getCommonFemaleLingerie() {
		return commonFemaleLingerie;
	}

	public List<AbstractClothing> getCommonFemaleAccessories() {
		return commonFemaleAccessories;
	}

	public List<AbstractClothing> getCommonMaleClothing() {
		return commonMaleClothing;
	}

	public List<AbstractClothing> getCommonAndrogynousClothing() {
		return commonAndrogynousClothing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AbstractClothing> getCommonMaleLingerie() {
		return commonMaleLingerie;
	}

	public List<AbstractClothing> getCommonMaleAccessories() {
		return commonMaleAccessories;
	}

	public List<AbstractClothing> getCommonAndrogynousLingerie() {
		return commonAndrogynousLingerie;
	}

	public List<AbstractClothing> getCommonAndrogynousAccessories() {
		return commonAndrogynousAccessories;
	}

	// Combat (you never fight Nyan):
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