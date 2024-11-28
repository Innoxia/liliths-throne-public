package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Scar;
import com.lilithsthrone.game.character.markings.ScarType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.submission.SARoxySpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Roxy extends NPC {

	private static List<AbstractItemType> itemsForSale = Util.newArrayListOfValues(
			ItemType.CIGARETTE_PACK,
			ItemType.FETISH_UNREFINED,
			ItemType.MOO_MILKER_EMPTY,
			ItemType.getItemTypeFromId("innoxia_pills_fertility"),
			ItemType.getItemTypeFromId("innoxia_pills_broodmother"),
			ItemType.getItemTypeFromId("innoxia_pills_sterility"),
			ItemType.MOTHERS_MILK,
			ItemType.PREGNANCY_TEST);

	static {
		for(AbstractItemType itemType : ItemType.getAllItems()) {
			if(!itemType.getItemTags().contains(ItemTag.NOT_FOR_SALE)
					&& (itemType.getItemTags().contains(ItemTag.ATTRIBUTE_TF_ITEM) || itemType.getItemTags().contains(ItemTag.RACIAL_TF_ITEM))
					&& (itemType.getItemTags().contains(ItemTag.SUBMISSION_TUNNEL_SPAWN) || itemType.getItemTags().contains(ItemTag.BAT_CAVERNS_SPAWN))) {
				itemsForSale.add(itemType);
			}
		}               
	}
	
	public Roxy() {
		this(false);
	}
	
	public Roxy(boolean isImported) {
		super(isImported, new NameTriplet("Roxy"), "Yap",
				"Roxy is the rat-girl owner of the Gambling Den's shop, 'Roxy's Box'."
					+ " With a patch over one eye, and visible scarring down one side of her face, Roxy is clearly no stranger to violence."
					+ " She has some particularly vulgar mannerisms, and has little patience for any of her customers.",
				33, Month.AUGUST, 2,
				15, Gender.F_V_B_FEMALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);

		buyModifier=0.3f;
		sellModifier=2.5f;
		
		if(!isImported) {
			this.dailyUpdate();
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(15);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.COWARDLY,
					PersonalityTrait.LEWD);
			this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_NONE));
			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_NONE));
			this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_NONE));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.5")) {
			buyModifier=0.3f;
			sellModifier=2.5f;
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.COWARDLY,
					PersonalityTrait.LEWD,
					PersonalityTrait.SLOVENLY);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);
	
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
		}
		
		
		// Body:

		// Core:
		this.setHeight(174);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_BROWN), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_BROWN), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_CLEAR));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_CLEAR));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK_LIGHT));
//		this.setSkinCovering(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK), true);
//		this.setSkinCovering(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK), true);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL.getValue());
		this.setHipSize(HipSize.FOUR_WOMANLY.getValue());
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		this.addGirlcumModifier(FluidModifier.ADDICTIVE);
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setScar(InventorySlot.EYES, new Scar(ScarType.CLAW_MARKS, true));
		
		this.setPiercedEar(true);
		this.setPiercedLip(true);
		this.setPiercedNavel(true);
		this.setPiercedNipples(true);
		this.setPiercedNose(true);
		this.setPiercedTongue(true);
		this.setPiercedVagina(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_STOMACH, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_NIPPLE, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_TONGUE, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_VAGINA, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_wrist_bangle", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_head_headband", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_eye_patch", PresetColour.CLOTHING_BLACK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SKATER_DRESS, PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_BLACK, false), true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.getDisplacedList().clear();
		}
	}

	@Override
	public void turnUpdate() {
		if(!this.hasStatusEffect(StatusEffect.SMOKING) && !Main.game.isInSex()) {
			if(this.hasStatusEffect(StatusEffect.RECENTLY_SMOKED)) {
				if(this.getStatusEffectDuration(StatusEffect.RECENTLY_SMOKED)<60*60*2) {
					this.addStatusEffect(StatusEffect.SMOKING, 60*5);
				}
			} else {
				this.addStatusEffect(StatusEffect.SMOKING, 60*5);
			}
		}
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
		this.addItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), 25, false, false);
		this.addItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), 10, false, false);
		
		for (AbstractItemType item : itemsForSale) {
			if(Main.game.isSillyMode() || !item.getItemTags().contains(ItemTag.SILLY_MODE)) {
				for (int i = 0; i < 6 + (Util.random.nextInt(12)); i++) {
					this.addItem(Main.game.getItemGen().generateItem(item), false);
				}
			}
		}
                
	    // Add a special case for firebombs
        if (Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_REBEL_BASE_FIREBOMBS)) {
            this.addWeapon(Main.game.getItemGen().generateWeapon("dsg_hlf_weap_pbomb"), 10, false, false);
        }
		
		List<AbstractClothingType> clothingToAdd = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing!=null
					&& (clothing.getRarity()==Rarity.COMMON || clothing.isDefaultSlotCondom())
					&& (clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_FINCH)
							|| clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN)
							|| clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_RALPH))) {
				if(clothing.isDefaultSlotCondom()) {
					Colour condomColour = clothing.getColourReplacement(0).getRandomOfDefaultColours();
					Colour condomColourSec = PresetColour.CLOTHING_BLACK;
					Colour condomColourTer = PresetColour.CLOTHING_BLACK;
					
					if(clothing.getColourReplacement(1)!=null) {
						condomColourSec = clothing.getColourReplacement(1).getRandomOfDefaultColours();
					}
					if(clothing.getColourReplacement(2)!=null) {
						condomColourTer = clothing.getColourReplacement(2).getRandomOfDefaultColours();
					}
					for (int i = 0; i < (3+(Util.random.nextInt(4)))*(clothing.getRarity()==Rarity.COMMON?3:(clothing.getRarity()==Rarity.UNCOMMON?2:1)); i++) {
						this.addClothing(Main.game.getItemGen().generateClothing(clothing, condomColour, condomColourSec, condomColourTer, false), false);
					}
					
				} else {
					clothingToAdd.add(clothing);
				}
			}
		}
		
		Collections.shuffle(clothingToAdd);
		
		for(int i=0; i<6; i++) {
			AbstractClothing clothing = Main.game.getItemGen().generateClothingWithNegativeEnchantment(Util.randomItemFrom(clothingToAdd));
			clothing.setEnchantmentKnown(this, false);
			this.addClothing(clothing, false);
		}
		for(int i=0; i<3; i++) {
			AbstractClothing clothing = Main.game.getItemGen().generateClothingWithEnchantment(Util.randomItemFrom(clothingToAdd));
			clothing.setEnchantmentKnown(this, false);
			this.addClothing(clothing, false);
		}
		AbstractClothing clothing = Main.game.getItemGen().generateRareClothing(Util.randomItemFrom(clothingToAdd));
		clothing.setEnchantmentKnown(this, false);
		this.addClothing(clothing, false);
		
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
		return true;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_NORMAL;
	}
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(SARoxySpecials.class);
	}
}