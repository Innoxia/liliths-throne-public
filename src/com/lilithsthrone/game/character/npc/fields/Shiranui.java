package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Body;
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
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.11.6
 * @version 0.4.11.6
 * @author Innoxia
 */
public class Shiranui extends NPC {

	public Shiranui() {
		this(false);
	}
	
	public Shiranui(boolean isImported) {
		super(isImported, new NameTriplet("Aya"), "Shiranui",
				"",
				47, Month.JANUARY, 1,
				40, Gender.F_P_V_B_FUTANARI, Subspecies.FOX_ASCENDANT, RaceStage.PARTIAL_FULL,
				new CharacterInventory(false, 0),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				true);
		
		if(!isImported) {
			this.setPlayerKnowsName(true);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			//TODO illusion magic
			this.addSpell(Spell.FIREBALL);
			this.addSpellUpgrade(SpellUpgrade.FIREBALL_1);
			this.addSpellUpgrade(SpellUpgrade.FIREBALL_2);
			this.addSpellUpgrade(SpellUpgrade.FIREBALL_3);
			
			this.addSpell(Spell.FLASH);
			this.addSpellUpgrade(SpellUpgrade.FLASH_1);
			this.addSpellUpgrade(SpellUpgrade.FLASH_2);
			this.addSpellUpgrade(SpellUpgrade.FLASH_3);
			
			this.addSpell(Spell.CLOAK_OF_FLAMES);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2);
			this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3);

			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
			
			this.addSpell(Spell.ARCANE_CLOUD);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1);

			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.setPlayerKnowsName(true);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.11.7")) {
			this.setMoney(0);
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN)==Quest.MAIN_3_K_WEAPONS_CACHE && this.getRace()!=Race.FOX_MORPH) {
				this.setBodyToMeraxis(false, true);
			}
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SEVEN_TAILED_YOUKO);
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.HYPERMOBILITY),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 5)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_YOUKO);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_BONDAGE_APPLIER);
			
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.FOUR_LOVE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.FOUR_LOVE);
			this.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.FOUR_LOVE);
			
			this.setFetishDesire(Fetish.FETISH_KINK_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_DEFLOWERING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SIZE_QUEEN, FetishDesire.THREE_LIKE);
			
		}
		
		// Body:
		this.setSubspeciesOverride(null);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.FOX_ASCENDANT, RaceStage.PARTIAL_FULL, false);
		this.setAgeAppearanceAbsolute(25);
		this.setTailCount(7, true);

		// Core:
		this.setHeight(180);
		this.setFemininity(95);
		this.setMuscle(Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FOX_MORPH, PresetColour.EYE_RED));
		this.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, PresetColour.COVERING_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PORCELAIN), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FOX_FUR, PresetColour.COVERING_PINK_LIGHT), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMinimumValue());
		this.setHairStyle(HairStyle.SIDECUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.SIX_STRETCHED_OPEN, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ONE_LONG.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastRows(1);
		this.setBreastSize(CupSize.FF.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setAssSize(AssSize.FIVE_HUGE);
		this.setHipSize(HipSize.FIVE_VERY_WIDE);
		this.clearAssOrificeModifiers();
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(35);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(800);
		this.setPenisCumExpulsion(90);
		this.setPenisCumProductionRegeneration(FluidRegeneration.FOUR_VERY_RAPID.getMedianRegenerationValuePerDay());
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		this.clearVaginaOrificeModifiers();
		
		// Feet:
		// Foot shape
	}

	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(applyNameAlteringEffects && this.nameTriplet.getFeminine().equals("Aya")) {
			return this.getSurname();
		}
		return super.getName(applyNameAlteringEffects);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crystal_legendary"), DamageType.FIRE));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crystal_legendary"), DamageType.FIRE));
		}
		
		AbstractClothing kimono = Main.game.getItemGen().generateClothing("innoxia_japanese_kimono_short", PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_WHITE, false);
		kimono.setSticker("kimono_pattern", "none");
		this.equipClothingFromNowhere(kimono, true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_japanese_geta", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLACK, null, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_elemental_sun_necklace", PresetColour.CLOTHING_GOLD, false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", PresetColour.CLOTHING_GOLD, false), true, this);
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this,
				"[npc.Name] is a trickster youko, who took over Meraxis's hideout and the surrounding area.");
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getName().equals("Meraxis")) {
			if(this.getTorsoType().getRace()==Race.HUMAN) {
				return "Meraxis";
				
			} else {
				return "MeraxisDemon";
			}
		}
		return super.getArtworkFolderName();
	}
	

	@Override
	public String getSpeechColour() {
		if(this.getName().equals("Meraxis")) {
			return ((DarkSiren)Main.game.getNpc(DarkSiren.class)).getSpeechColour();
		}
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#ffccff";
		}
		return "#ff99ff";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void endSex() {
		this.replaceAllClothing();
	}
	
	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// TF potion:
	
	@Override
	public TransformativePotion generateTransformativePotion(GameCharacter target) {
		AbstractItemType itemType = ItemType.getItemTypeFromId("innoxia_race_fox_chicken_pot_pie");
		
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		List<PossibleItemEffect> minimumEffects = new ArrayList<>();
		List<PossibleItemEffect> reducedEffects = new ArrayList<>();
		List<PossibleItemEffect> maximumEffects = new ArrayList<>();
		
		if(!target.isAbleToHaveRaceTransformed()) {
			if(Main.getProperties().multiBreasts>0) {
				if(target.getBreastRows()<3) {
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, TFPotency.MINOR_BOOST, 1), ""));
				}
				if(target.getBreastRows()<2) {
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, TFPotency.MINOR_BOOST, 1), ""));
				}
			}
			
		} else {
			switch(Main.getProperties().getForcedTFPreference()) {
				case MAXIMUM:
				case NORMAL:
					maximumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_SKIN, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					if(Main.getProperties().multiBreasts>0) {
						if(target.getBreastRows()<3) {
							maximumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, TFPotency.MINOR_BOOST, 1), ""));
						}
						if(target.getBreastRows()<2) {
							maximumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, TFPotency.MINOR_BOOST, 1), ""));
						}
					}
					maximumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					
				case REDUCED:
					reducedEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					reducedEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					reducedEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_LEGS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					reducedEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ARMS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					
				case MINIMUM:
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_EARS, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_EYES, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_TAIL, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HORNS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), ""));
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ANTENNA, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
					minimumEffects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_WINGS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), ""));
					break;
					
				case HUMAN:
					itemType = ItemType.getItemTypeFromId("innoxia_race_human_bread_roll");
					break;
			}
		}
		
		effects.addAll(minimumEffects);
		effects.addAll(getFeminineEffects(target, itemType));

		// Add crotch-boobs:
		if(Main.game.isUdderContentEnabled()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
			for(int i=target.getBreastCrotchRows(); i<RacialBody.FOX_MORPH.getBreastCrotchCount(); i+=1) {
				effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_COUNT, TFPotency.BOOST, 1), ""));
			}
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		
		// Remove penis:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), ""));
		
		// Add wet vagina:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		
		// Make vagina deep:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_DEPTH, TFPotency.MAJOR_BOOST, 6), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_DEPTH, TFPotency.MAJOR_BOOST, 6), ""));
		
		effects.addAll(reducedEffects);
		effects.addAll(maximumEffects);
		
		return new TransformativePotion(itemType, effects);
	}
	
	private static List<PossibleItemEffect> getFeminineEffects(GameCharacter target, AbstractItemType itemType) {
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		for(int i=target.getFemininityValue(); i<Femininity.FEMININE_STRONG.getMinimumFemininity(); i+=15) { // Turn feminine:
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getMuscleValue()>Muscle.TWO_TONED.getMedianValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1), ""));
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1), ""));
		}
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.FF.getMeasurement(); i+=3) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		for(int i=target.getNippleSize().getValue(); i<NippleSize.THREE_LARGE.getValue(); i+=1) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1), ""));
		}
		for(int i=target.getAreolaeSize().getValue(); i<AreolaeSize.THREE_LARGE.getValue(); i+=1) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.BOOST, 1), ""));
		}
		if(target.getHipSize().getValue()<HipSize.SIX_EXTREMELY_WIDE.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getAssSize().getValue()<AssSize.SIX_MASSIVE.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i<HairLength.FOUR_MID_BACK.getMaximumValue(); i+=15) {
				effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.FOUR_HUGE.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), ""));
		}
		
		return effects;
	}
	
	public String applyTransformationPotion(GameCharacter target) {
		StringBuilder sb =  new StringBuilder();
		
		TransformativePotion potion = generateTransformativePotion(target);

//		if(target.isAbleToHaveRaceTransformed()) {
//			target.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, PresetColour.COVERING_PINK), false);
//		}
		
		for(PossibleItemEffect pe : potion.getEffects()) {
			sb.append(pe.getEffect().applyEffect(this, Main.game.getPlayer(), 1));
		}
		
		return sb.toString();
	}
	
	public void setBodyToMeraxis(boolean meraxis, boolean nameChange) {
		if(meraxis) {
			setBody(new Body(Main.game.getNpc(DarkSiren.class).getBody()), true);
			this.setAgeAppearanceAbsolute(18);
			if(nameChange) {
				this.setName("Meraxis");
				this.setSurname("Lyssiethmartuilani");
			}
			
		} else {
			this.setStartingBody(true);
			this.setAgeAppearanceAbsolute(25);
			if(nameChange) {
				this.setName("Aya");
				this.setSurname("Shiranui");
			}
			
			// Reset pregnancy as otherwise it will be demon or half-demon due to Meraxis form:
			if(this.isPregnant()) {
				FertilisationType ft = this.pregnantLitter.getFertilisationType();
				this.endPregnancy(false);
				this.guaranteePregnancyOnNextRoll();
				// GameCharacter partner, Body partnerBody, float cumQuantity, boolean directSexInsemination, FertilisationType fertilisationType, AbstractAttribute partnerVirilityAttribute
				this.rollForPregnancy(Main.game.getPlayer(), Main.game.getPlayer().getBody(), 100, true, ft, Attribute.VIRILITY);
			}
		}
		this.loadImages(true);
	}
	
	public void dressTargetInFetishGear(GameCharacter target, Colour colour) {
		if(target.isBipedal()) {
			AbstractClothing stockings = Main.game.getItemGen().generateClothing("sage_latex_stockings", colour, false);
			stockings.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
			stockings.setName("Shiranui's Stockings");
			target.equipClothingFromNowhere(stockings, true, this);
			
			AbstractClothing heels = Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", colour, false);
			heels.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
			heels.setName("Shiranui's Heels");
			target.equipClothingFromNowhere(heels, true, this);
		}

		AbstractClothing croptop = Main.game.getItemGen().generateClothing("sage_latex_croptop", colour, false);
		croptop.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		croptop.setName("Shiranui's Croptop");
		target.equipClothingFromNowhere(croptop, true, this);

		AbstractClothing microskirt = Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", colour, false);
		microskirt.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		microskirt.setName("Shiranui's Microskirt");
		target.equipClothingFromNowhere(microskirt, true, this);
		
		dressTargetInFetishGearChoker(target, colour);
	}
	
	
	public void dressTargetInFetishGearChoker(GameCharacter target, Colour colour) {
		AbstractClothing choker = Main.game.getItemGen().generateClothing("innoxia_bdsm_choker", colour, PresetColour.CLOTHING_SILVER, null, false);
		choker.setSticker("top_txt", "dumb");
		choker.setSticker("btm_txt", "slut");
		
		choker.clearEffects();
		
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SERVITUDE, TFPotency.MINOR_BOOST, 0));

		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_BONDAGE_VICTIM, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_VAGINAL_RECEIVING, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, TFPotency.MAJOR_BOOST, 0));

		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_VAGINA, TFModifier.TF_TYPE_1, TFPotency.MAJOR_BOOST, 0));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 7));
		choker.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_VAGINA, TFModifier.TF_MOD_DEPTH, TFPotency.MAJOR_BOOST, 6));
		
		choker.setName("Shiranui's Choker");
		
		target.equipClothingFromNowhere(choker, true, this);
	}
	
	public void addGifts(GameCharacter recipient, boolean appendToDialogue) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(recipient.addItem(Main.game.getItemGen().generateItem("innoxia_potions_youko_potion"), 10, false, appendToDialogue));
		sb.append(recipient.addItem(Main.game.getItemGen().generateItem("innoxia_race_fox_vulpines_vineyard"), 3, false, appendToDialogue));
		sb.append(recipient.addItem(Main.game.getItemGen().generateItem(ItemType.REJUVENATION_POTION), 1, false, appendToDialogue));
		
		TransformativePotion tfPotion = this.generateTransformativePotion(recipient);
		AbstractItem potion = EnchantingUtils.craftItem(
			Main.game.getItemGen().generateItem(tfPotion.getItemType()),
			tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
		potion.setName("Shiranui's Gift");
		sb.append(recipient.addItem(potion, 1, false, true));
		
		if(recipient.isPlayer() && appendToDialogue) {
			Main.game.getTextEndStringBuilder().append(sb.toString());
		}
	}
}
