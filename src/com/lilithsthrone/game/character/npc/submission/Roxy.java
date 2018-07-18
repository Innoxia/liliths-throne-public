package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.ScarType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.sexActions.submission.roxy.SARoxySpecials;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class Roxy extends NPC {

	private static final long serialVersionUID = 1L;

	private static List<AbstractItemType> itemsForSale = Util.newArrayListOfValues(
			ItemType.FETISH_UNREFINED,
			ItemType.MOO_MILKER_EMPTY,
			ItemType.VIXENS_VIRILITY,
			ItemType.PROMISCUITY_PILL,
			ItemType.MOTHERS_MILK,
			ItemType.PREGNANCY_TEST);

	static {
		for(AbstractItemType itemType : ItemType.allItems) {
			if(!itemType.getItemTags().contains(ItemTag.NOT_FOR_SALE)
					&& (itemType.getItemTags().contains(ItemTag.ATTRIBUTE_TF_ITEM) || itemType.getItemTags().contains(ItemTag.RACIAL_TF_ITEM))
					&& (itemType.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN)
							|| itemType.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN))) {
				itemsForSale.add(itemType);
			}
		}
	}
	
	public Roxy() {
		this(false);
	}
	
	public Roxy(boolean isImported) {
		super(new NameTriplet("Roxy"),
				"Roxy is the rat-girl owner of the Gambling Den's shop, 'Roxy's Box'."
					+ " With a patch over one eye, and visible scarring down one side of her face, Roxy is clearly no stranger to violence."
					+ " She has some particularly vulgar mannerisms, and has little patience for any of her customers.",
				33, Month.AUGUST, 2,
				10, Gender.F_V_B_FEMALE, RacialBody.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);

		buyModifier=0.4f;
		sellModifier=2.5f;
		
		if(!isImported) {
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

			this.setPlayerKnowsName(true);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_RAT, Colour.EYE_BROWN));
			this.setSkinCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, Colour.COVERING_BROWN), true);
			this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, Colour.COVERING_BROWN), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
			
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
			
			this.setFemininity(85);
			
			this.setScar(InventorySlot.EYES, new Scar(ScarType.CLAW_MARKS, true));
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
			this.setHairStyle(HairStyle.LOOSE);
			
			this.setAssVirgin(true);
			this.setAssSize(AssSize.THREE_NORMAL.getValue());
			this.setHipSize(HipSize.FOUR_WOMANLY.getValue());

			this.setBreastSize(CupSize.B.getMeasurement());
			this.setBreastShape(BreastShape.PERKY);
			
			this.setFaceVirgin(false);
			
			this.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue(), true);
			this.setVaginaWetness(Wetness.FOUR_SLIMY.getValue());
			this.setVaginaLabiaSize(LabiaSize.THREE_LARGE.getValue());
			this.setVaginaSquirter(true);
			this.addGirlcumModifier(FluidModifier.ADDICTIVE);
			this.setVaginaVirgin(false);
			
			this.setHeight(174);
			
			this.setPiercedEar(true);
			this.setPiercedLip(true);
			this.setPiercedNavel(true);
			this.setPiercedNipples(true);
			this.setPiercedNose(true);
			this.setPiercedTongue(true);
			this.setPiercedVagina(true);
			
			this.equipClothing(true, false);
			
			this.dailyReset();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		this.unequipAllClothingIntoVoid();

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_LIP_RINGS, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NIPPLE_BARS, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_TONGUE_BAR, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_VAGINA_BARBELL_RING, Colour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ANKLE_BRACELET, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_PATCH, Colour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SKATER_DRESS, Colour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_THIGH_HIGH_BOOTS, Colour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.getDisplacedList().clear();
		}
	}
	
	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		for(int i=0;i<25;i++) {
			this.addItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), false);
		}
		
		for (AbstractItemType item : itemsForSale) {
			for (int i = 0; i < 6 + (Util.random.nextInt(12)); i++) {
				this.addItem(AbstractItemType.generateItem(item), false);
			}
		}
		
		Colour condomColour1 = ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().get(Util.random.nextInt(ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().size()));
		Colour condomColour2 = ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().get(Util.random.nextInt(ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().size()));
		
		for (int i = 0; i < 6+(Util.random.nextInt(12)); i++) {
			this.addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, condomColour1, false), false);
		}
		for (int i = 0; i < 6+(Util.random.nextInt(12)); i++) {
			this.addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, condomColour2, false), false);
		}
		
		List<AbstractClothingType> clothingToAdd = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing!=null
					&& clothing.getRarity()==Rarity.COMMON
					&& (clothing.getItemTags().contains(ItemTag.SOLD_BY_FINCH) || clothing.getItemTags().contains(ItemTag.SOLD_BY_NYAN))) {
				clothingToAdd.add(clothing);
			}
		}
		
		Collections.shuffle(clothingToAdd);
		
		for(int i=0; i<6; i++) {
			AbstractClothing clothing = AbstractClothingType.generateClothingWithNegativeEnchantment(Util.randomItemFrom(clothingToAdd));
			this.addClothing(clothing, false);
			clothing.setEnchantmentKnown(false);
		}
		for(int i=0; i<3; i++) {
			AbstractClothing clothing = AbstractClothingType.generateClothingWithEnchantment(Util.randomItemFrom(clothingToAdd));
			this.addClothing(clothing, false);
			clothing.setEnchantmentKnown(false);
		}
		AbstractClothing clothing = AbstractClothingType.generateRareClothing(Util.randomItemFrom(clothingToAdd));
		this.addClothing(clothing, false);
		clothing.setEnchantmentKnown(false);
		
	}
	
	@Override
	public String getTraderDescription() {
		return "<p>"
					+ "Roxy grins as she points to the shelving behind you. [roxy.speech(I ain't gettin' up, so just grab whatever takes yer fancy and bring it over to me.)]"
				+ "</p>";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType().canBeSold()) {
				return true;
			}
		}
		
		return item instanceof AbstractClothing;
	}

	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SARoxySpecials.class);
	}
}