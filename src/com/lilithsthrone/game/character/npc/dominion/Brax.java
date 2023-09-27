package com.lilithsthrone.game.character.npc.dominion;

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
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
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
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.5
 * @version 0.3.5
 * @author Innoxia
 */
public class Brax extends NPC {
	
	public Brax() {
		this(false);
	}
	
	public Brax(boolean isImported) {
		super(isImported, new NameTriplet("Brax", "Bree", "Brandi"), "Volkov",
				"Holding the rank of Inspector, Brax is a high-ranking Enforcer. Muscular, handsome, and with an incredibly dominant personality, he's the focus of every female Enforcer's attention.",
				30, Month.NOVEMBER, 27,
				10, Gender.M_P_MALE,
				Subspecies.WOLF_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_BRAXS_OFFICE, true);
		
		if(!isImported) {
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 25);

			TransformativePotion tfPotion = this.generateTransformativePotion(this); // Generate effects based on Brax's body
			AbstractItem potion = EnchantingUtils.craftItem(
				Main.game.getItemGen().generateItem(tfPotion.getItemType()),
				tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
			potion.setName("Brax's Surprise");
			this.addItem(potion, false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(!this.isSlave() && Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		
		this.setPenisVirgin(false);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.8")) {
			this.setLevel(10);
			this.setHistory(Occupation.NPC_ENFORCER_PATROL_INSPECTOR);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 25);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.7") && !this.isSlave()) {
			TransformativePotion tfPotion = this.generateTransformativePotion(this); // Generate effects based on Brax's body
			AbstractItem potion = EnchantingUtils.craftItem(
				Main.game.getItemGen().generateItem(tfPotion.getItemType()),
				tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
			potion.setName("Brax's Surprise");
			this.addItem(potion, false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.17") && !this.isSlave()) {
			equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		if(this.isSlave()) {
			super.setupPerks(autoSelectPerks);
			return;
		}
		
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.ENCHANTMENT_STABILITY,
						Perk.UNARMED_DAMAGE,
						Perk.ARCANE_BOOST),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 2),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
	
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ENFORCER_PATROL_INSPECTOR);
	
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.addFetish(Fetish.FETISH_DOMINANT);
		}
		
		// Body:

		// Core:
		this.setHeight(183);
		this.setFemininity(25);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, PresetColour.COVERING_GREY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, PresetColour.COVERING_GREY), true);
		this.setHairLength(HairLength.ZERO_BALD);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(20);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);
		
		if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
			AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", false);
			collar.setSealed(true);
			collar.setColour(0, PresetColour.CLOTHING_SILVER);
			this.equipClothingFromNowhere(collar, true, this);
		}
		
		if(isFeminine()) {
			if(hasFetish(Fetish.FETISH_BIMBO)) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_panties", PresetColour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_nipple_tape_crosses", PresetColour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_FISHNET_TOP, PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_pleated", PresetColour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_fishnets", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_fishnet_gloves", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_GOLD, false), true, this);

				this.setPiercedEar(true);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNose(true);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNavel(true);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_STOMACH, true, this);
				
			} else {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE, false), true, this);

				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
			}
			
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_STEEL, false), true, this);

			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_ssldshirt", PresetColour.CLOTHING_BLUE, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
			
			if(!Main.game.isStarted() || (Main.game.getPlayer().hasQuest(QuestLine.MAIN) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN))) {
				if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
					this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_epic", DamageType.FIRE));
					this.setEssenceCount(150);
					this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("dsg_eep_pbweap_pbpistol")));
				}
			}
		}
	}

	@Override
	public String getArtworkFolderName() {
		return this.getNameIgnoresPlayerKnowledge();
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave() && this.getOwner().isPlayer()) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bimbofiedBrax)) {
				return "At one time being an Enforcer holding the rank of Inspector, [brax.name] ended up becoming Candi's slave, and under her ownership, [brax.she] was transformed into a sex-obsessed bimbo wolf-girl."
				+ " Eventually, after performing several tasks for the bimbo cat-girl Enforcer, you became [brax.namePos] new owner.";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feminisedBrax)) {
				return "At one time being an Enforcer holding the rank of Inspector, [brax.name] ended up becoming Candi's slave, and under her ownership, [brax.she] was transformed into a wolf-girl."
						+ " Eventually, after performing several tasks for the bimbo cat-girl Enforcer, you became [brax.namePos] new owner.";
				
			} else {
				return "At one time being an Enforcer holding the rank of Inspector, [brax.name] ended up becoming Candi's slave, and after you performed several tasks for the bimbo cat-girl Enforcer, you became [brax.namePos] new owner.";
			}
		}
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.bimbofiedBrax)) {
			return "At one time being an Enforcer holding the rank of Inspector, [brax.name] is now completely unrecognisable from [brax.her] former self."
					+ " With some help from Candi, she's been transformed into a brain-dead bimbo, who can only think about where the next cock is coming from.";
			
		} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.feminisedBrax)) {
			return "At one time being an Enforcer holding the rank of Inspector, [brax.name] is almost unrecognisable from [brax.her] former self."
					+ " With some help from Candi, you've transformed [brax.herHim] into a wolf-girl."
					+ " Where once [brax.she] was muscular and dominant, [brax.sheIs] now feminine and submissive, and meekly agrees to do anything that's asked of [brax.herHim].";
			
		} else {
			return "Holding the rank of Inspector, Brax is a high-ranking Enforcer. Muscular, handsome, and with an incredibly dominant personality, he's the focus of every female Enforcer's attention.";
		}
		
	}
	
	@Override
	public void turnUpdate() {
		if(!this.isSlave() && !Main.game.getCharactersPresent().contains(this)) {
			this.setEssenceCount(150);
		}
		if(this.isSlave() && !this.getOwner().isPlayer() && !Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isWorkTime()) {
				this.setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK, true);
				
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void endSex() {
		if(this.isSlave() && this.getOwner().isPlayer()) {
			return;
		}
		setPendingClothingDressing(true);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public boolean isImmuneToLevelDrain() {
		return !this.isSlave();
	}
	
	@Override
	public SexPace getSexPaceDomPreference(){
		if(this.isSlave() && this.getOwner().isPlayer()) {
			return super.getSexPaceDomPreference();
		}
		return SexPace.DOM_ROUGH;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		if(this.isSlave() && this.getOwner().isPlayer()) {
			return super.getSexPaceSubPreference(character);
		}
		if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
			return SexPace.SUB_EAGER;
			
		} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
			return SexPace.SUB_NORMAL;
			
		} else {
			return SexPace.SUB_NORMAL;
		}
	}

	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			if(this.isFeminine()) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
					return "#FF0AA5";
				} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
					return "#C60AFF";
				}
			}
			return "#1F35FF";
			
		} else {
			if(this.isFeminine()) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
					return "#E36DE1";
				} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
					return "#D79EFF";
				}
			}
			return "#ADB4FF";
		}
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Combat:
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", BraxOffice.AFTER_COMBAT_VICTORY){
				@Override
				public void effects() {
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				}
			};
			
		} else {
			return new Response("", "", BraxOffice.AFTER_COMBAT_DEFEAT);
		}
	}

	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public int getLootMoney() {
		return 2500;
	}
	
	public List<AbstractCoreItem> getLootItems() {
		return null;
	}
	
	@Override
	public int getLootEssenceDrops() {
		return 8;
	}

	@Override
	public TransformativePotion generateTransformativePotion(GameCharacter target) {
		AbstractItemType itemType = ItemType.getItemTypeFromId("innoxia_race_wolf_meat_and_marrow");
		
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		List<PossibleItemEffect> minimumEffects = new ArrayList<>();
		List<PossibleItemEffect> reducedEffects = new ArrayList<>();
		List<PossibleItemEffect> maximumEffects = new ArrayList<>();
		
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
			case HUMAN:
				break;
		}
		
		effects.addAll(minimumEffects);
		effects.addAll(getFeminineEffects(target, itemType));
		
		// Remove crotch-boobs:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS_CROTCH, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), ""));
		
		// Remove penis:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1), ""));
		
		// Add wet vagina:
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_TYPE_1, TFPotency.MINOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));
		effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1), ""));

		effects.addAll(reducedEffects);
		effects.addAll(maximumEffects);
		
		return new TransformativePotion(itemType, effects);
	}
	
	private static List<PossibleItemEffect> getFeminineEffects(GameCharacter target, AbstractItemType itemType) {
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		for(int i=target.getFemininityValue(); i<Femininity.FEMININE_STRONG.getMinimumFemininity(); i+=15) { // Turn feminine:
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1), ""));
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1), ""));
		}
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.E.getMeasurement(); i+=3) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i<HairLength.THREE_SHOULDER_LENGTH.getMaximumValue(); i+=15) {
				effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1), ""));
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.TWO_FULL.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1), ""));
		}
		
		return effects;
	}
	
}
