package com.lilithsthrone.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public class Nyan extends NPC {

	private static final long serialVersionUID = 1L;

	private List<AbstractClothing> commonFemaleClothing, commonFemaleUnderwear, commonFemaleOtherLingerie, commonFemaleAccessories,
									commonMaleClothing, commonMaleLingerie, commonMaleAccessories,
									commonAndrogynousClothing, commonAndrogynousLingerie, commonAndrogynousAccessories,
									specials;

	public Nyan() {
		this(false);
	}
	
	private Nyan(boolean isImported) {
		super(new NameTriplet("Nyan"), "Nyan is the owner of the store 'Nyan's Clothing Emporium', found in Dominion's shopping arcade."
				+ " She's extremely shy, and gets very nervous when having to talk to people.",
				10, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_NYANS_SHOP, true);

		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_BLUE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BLONDE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	
			this.setBreastSize(CupSize.B.getMeasurement());
	
			this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
			this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
			
			this.setMoney(10);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_PENCIL_SKIRT, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_BLOUSE, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		}
		
		//TODO import these values
		commonFemaleClothing = new ArrayList<>();
		commonFemaleUnderwear = new ArrayList<>();
		commonFemaleOtherLingerie = new ArrayList<>();
		commonFemaleAccessories = new ArrayList<>();
		commonMaleClothing = new ArrayList<>();
		commonMaleLingerie = new ArrayList<>();
		commonMaleAccessories = new ArrayList<>();
		commonAndrogynousClothing = new ArrayList<>();
		commonAndrogynousLingerie = new ArrayList<>();
		commonAndrogynousAccessories = new ArrayList<>();
		specials = new ArrayList<>();
		dailyReset();
	}
	
	@Override
	public Nyan loadFromXML(Element parentElement, Document doc) {
		Nyan npc = new Nyan(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		commonFemaleClothing.clear();
		commonFemaleUnderwear.clear();
		commonFemaleOtherLingerie.clear();
		commonFemaleAccessories.clear();
		
		commonMaleClothing.clear();
		commonMaleLingerie.clear();
		commonMaleAccessories.clear();
		
		commonAndrogynousClothing.clear();
		commonAndrogynousLingerie.clear();
		commonAndrogynousAccessories.clear();
		
		specials.clear();

		// Female:
		for(AbstractClothingType ct : ClothingType.getCommonFemaleClothing()) {
			commonFemaleClothing.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonFemaleClothing.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleClothing().get(Util.random.nextInt(ClothingType.getCommonFemaleClothing().size()))));
		
		for(AbstractClothingType ct : ClothingType.getCommonFemaleLingerie()) {
			if(ct.getSlot() == InventorySlot.GROIN) {
				commonFemaleUnderwear.add(AbstractClothingType.generateClothing(ct, false));
			} else {
				commonFemaleOtherLingerie.add(AbstractClothingType.generateClothing(ct, false));
			}
		}
		for (int i = 0; i < 4; i++) {
			commonFemaleUnderwear.add(AbstractClothingType.generateClothingWithEnchantment(commonFemaleUnderwear.get(Util.random.nextInt(commonFemaleUnderwear.size())).getClothingType()));
			commonFemaleOtherLingerie.add(AbstractClothingType.generateClothingWithEnchantment(commonFemaleOtherLingerie.get(Util.random.nextInt(commonFemaleOtherLingerie.size())).getClothingType()));
		}
		
		for(AbstractClothingType ct : ClothingType.getCommonFemaleAccessories()) {
			commonFemaleAccessories.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonFemaleAccessories.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonFemaleAccessories().get(Util.random.nextInt(ClothingType.getCommonFemaleAccessories().size()))));
		

		// Male:
		for(AbstractClothingType ct : ClothingType.getCommonMaleClothing()) {
			commonMaleClothing.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonMaleClothing.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleClothing().get(Util.random.nextInt(ClothingType.getCommonMaleClothing().size()))));
		
		for(AbstractClothingType ct : ClothingType.getCommonMaleLingerie()) {
			commonMaleLingerie.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonMaleLingerie.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleLingerie().get(Util.random.nextInt(ClothingType.getCommonMaleLingerie().size()))));
		
		for(AbstractClothingType ct : ClothingType.getCommonMaleAccessories()) {
			commonMaleAccessories.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonMaleAccessories.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonMaleAccessories().get(Util.random.nextInt(ClothingType.getCommonMaleAccessories().size()))));
		

		// Androgynous:
		for(AbstractClothingType ct : ClothingType.getCommonAndrogynousClothing()) {
			commonAndrogynousClothing.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonAndrogynousClothing.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousClothing().get(Util.random.nextInt(ClothingType.getCommonAndrogynousClothing().size()))));
		
		for(AbstractClothingType ct : ClothingType.getCommonAndrogynousLingerie()) {
			commonAndrogynousLingerie.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++)
			commonAndrogynousLingerie.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousLingerie().get(Util.random.nextInt(ClothingType.getCommonAndrogynousLingerie().size()))));
		
		for(AbstractClothingType ct : ClothingType.getCommonAndrogynousAccessories()) {
			commonAndrogynousAccessories.add(AbstractClothingType.generateClothing(ct, false));
		}
		for (int i = 0; i < 4; i++) {
			commonAndrogynousAccessories.add(AbstractClothingType.generateClothingWithEnchantment(ClothingType.getCommonAndrogynousAccessories().get(Util.random.nextInt(ClothingType.getCommonAndrogynousAccessories().size()))));
		}
		
		// Specials:
		specials.add(AbstractClothingType.generateClothing(ClothingType.SOCK_RAINBOW_STOCKINGS));
		specials.add(AbstractClothingType.generateClothing(ClothingType.HAND_RAINBOW_FINGERLESS_GLOVES));

		specials.add(AbstractClothingType.generateClothing(ClothingType.MILK_MAID_KERCHIEF));
		specials.add(AbstractClothingType.generateClothing(ClothingType.MILK_MAID_HEADBAND));
		specials.add(AbstractClothingType.generateClothing(ClothingType.MILK_MAID_TORSO_DRESS));

		specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS));
		specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE));
		specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS));
		specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES));
		specials.add(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS));
		
		specials.add(AbstractClothingType.generateClothing(ClothingType.CATTLE_PIERCING_NOSE_BOVINE_RING));
		specials.add(AbstractClothingType.generateClothing(ClothingType.CATTLE_NECK_COWBELL_COLLAR));
		specials.add(AbstractClothingType.generateClothing(ClothingType.CATTLE_PIERCING_EAR_TAGS));

		specials.add(AbstractClothingType.generateClothing(ClothingType.MEGA_MILK));

		specials.add(AbstractClothingType.generateClothing(ClothingType.NECK_BREEDER_COLLAR));
		
		for(AbstractClothing c : commonFemaleClothing) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleUnderwear) {
			c.setEnchantmentKnown(true);
		}
		for(AbstractClothing c : commonFemaleOtherLingerie) {
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
	
	@Override
	public void handleSellingEffects(AbstractCoreItem item, int count, int itemPrice){
		commonFemaleClothing.remove(item);
		commonFemaleUnderwear.remove(item);
		commonFemaleOtherLingerie.remove(item);
		commonFemaleAccessories.remove(item);
		
		commonMaleClothing.remove(item);
		commonMaleLingerie.remove(item);
		commonMaleAccessories.remove(item);
		
		commonAndrogynousClothing.remove(item);
		commonAndrogynousLingerie.remove(item);
		commonAndrogynousAccessories.remove(item);
		
		specials.remove(item);
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
		return item instanceof AbstractClothing;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}
	
	public List<AbstractClothing> getCommonFemaleClothing() {
		return commonFemaleClothing;
	}

	public List<AbstractClothing> getCommonFemaleUnderwear() {
		return commonFemaleUnderwear;
	}
	
	public List<AbstractClothing> getCommonFemaleOtherLingerie() {
		return commonFemaleOtherLingerie;
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

	public List<AbstractClothing> getSpecials() {
		return specials;
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