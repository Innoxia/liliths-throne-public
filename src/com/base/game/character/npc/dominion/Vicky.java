package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.QuestLine;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.EnchantingUtils;
import com.base.game.inventory.enchanting.TFModifier;
import com.base.game.inventory.enchanting.TFPotency;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.AbstractItemType;
import com.base.game.inventory.item.ItemEffect;
import com.base.game.inventory.item.ItemType;
import com.base.game.inventory.weapon.AbstractWeapon;
import com.base.game.inventory.weapon.WeaponType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.ListValue;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public class Vicky extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing panties = AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_PINK_LIGHT, false),
			skirt = AbstractClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_PINK, false),
			bra = AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_PINK_LIGHT, false),
			torso = AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_WHITE, false),
			socks = AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false),
			shoes = AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false);

	private AbstractItemType[] availableIngredients = new AbstractItemType[] {
			ItemType.RACE_INGREDIENT_CAT_MORPH,
			ItemType.RACE_INGREDIENT_DOG_MORPH,
			ItemType.RACE_INGREDIENT_HARPY,
			ItemType.RACE_INGREDIENT_HORSE_MORPH,
			ItemType.RACE_INGREDIENT_WOLF_MORPH,
			ItemType.RACE_INGREDIENT_HUMAN,
			ItemType.RACE_INGREDIENT_DEMON};
	
	public Vicky() {
		super(new NameTriplet("Vicky"),
				"Vicky is the owner of the shop 'Arcane Arts'. Her manner of staring at anyone who enters her shop is quite unsettling, and you feel as though she's ready to pounce on you at any moment...", 10, Gender.FEMALE,
				RacialBody.WOLF_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.SHOPPING_ARCADE, ShoppingArcade.VICKYS_SHOP_WEAPONS, true);
		
		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_BLACK), true);

		this.setVaginaVirgin(false);
		this.setVaginaCapacity(Capacity.FIVE_ROOMY.getMedianValue());
		this.setBreastSize(CupSize.C.getMeasurement());

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

		for(int i=0;i<3;i++){
			this.addWeapon(WeaponType.generateWeapon(WeaponType.OFFHAND_CHAOS_RARE), false);
			this.addWeapon(WeaponType.generateWeapon(WeaponType.MELEE_CHAOS_RARE), false);
		}
		this.addWeapon(WeaponType.generateWeapon(WeaponType.OFFHAND_CHAOS_EPIC), false);
		this.addWeapon(WeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC), false);
		
		AbstractCoreItem ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
		TFModifier primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		for(int i=0; i<8;i++) {
			if(ingredient.getEnchantmentEffect().getEffectsDescription(primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0, Main.game.getPlayer(), Main.game.getPlayer())!=null) {
				this.addItem(EnchantingUtils.craftItem(ingredient, Util.newArrayListOfValues(new ListValue<>(new ItemEffect(ingredient.getEnchantmentEffect(), primaryMod, TFModifier.NONE, TFPotency.MINOR_BOOST, 0)))), false);
				
			}
			
			ingredient = AbstractItemType.generateItem(availableIngredients[Util.random.nextInt(availableIngredients.length)]);
			primaryMod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
		}
		
		if(Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_ARCANE), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_CAT_MORPH), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_DEMON), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_DOG_MORPH), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_HARPY), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_HORSE_MORPH), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_HUMAN), false);
			for(int i=0; i<25+Util.random.nextInt(20);i++)
				this.addItem(AbstractItemType.generateItem(ItemType.BOTTLED_ESSENCE_WOLF_MORPH), false);
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
					+ "You walk up to the counter, trying to ignore Vicky's piercing gaze and wolfish grin."
					+ " As you start to ask her about what sort of things she has in stock, she suddenly crouches down slightly, and you brace yourself as you half-expect her to leap over the counter and attack."
					+ " Instead, thankfully, she bends down and retrieves a few items, placing them before you on the countertop."
				+ "</p>"
				+ "<p>"
					+ UtilText.parseSpeech("This is what I've got today... I might have some new stuff tomorrow... You interested?", this) + " she asks, never taking her eyes off of you for a second."
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item instanceof AbstractWeapon)
			return true;
		
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_ARCANE
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_CAT_MORPH
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_DEMON
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_DOG_MORPH
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_HARPY
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_HORSE_MORPH
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_HUMAN
					|| ((AbstractItem)item).getItemType()==ItemType.BOTTLED_ESSENCE_WOLF_MORPH
					|| ((AbstractItem)item).getItemType()==ItemType.POTION
					|| ((AbstractItem)item).getItemType()==ItemType.ELIXIR) {
				return true;
			}
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