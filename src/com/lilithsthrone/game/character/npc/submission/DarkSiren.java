package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
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
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.9
 * @author Innoxia
 */
public class DarkSiren extends NPC {

	public DarkSiren() {
		this(false);
	}
	
	public DarkSiren(boolean isImported) {
		super(isImported,
				new NameTriplet("Meraxis"), "Lyssiethmartuilani",
				"The ruler of Submission's central imp citadel, 'The Dark Siren' is an incredibly powerful arcane user...",
				26, Month.OCTOBER, 13,
				30, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL, new CharacterInventory(10), WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP, true);

		if(!isImported) {
			this.setPlayerKnowsName(false);
			this.setGenericName("dark siren");
			
			this.addTrait(Perk.CHUUNI);
			
			this.setEssenceCount(10000);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		this.setGenericName("dark siren");
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.5")) {
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL, false);
			setStartingBody(true);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP, true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.6")) {
			equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6")) {
			this.setGenericName("dark siren");
			
			this.setEssenceCount(10000);
			
			this.addSpells();
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.6")) {
			this.setWingSize(WingSize.ONE_SMALL.getValue());
		}
		setName(new NameTriplet("Meraxis"));
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.7")) {
			this.setLevel(30);
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.9")) {
			if(this.getSubspecies()==Subspecies.DEMON) {
				this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_RED_DARK, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
				this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_RED, PresetColour.SKIN_RED_DARK), false);
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setHistory(Occupation.NPC_ARCANE_RESEARCHER);
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE,
					PersonalityTrait.INNOCENT);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) {
			if(this.getSubspecies()==Subspecies.HALF_DEMON) {
				this.setStartingBody(false);
			}
			this.removeVaginaOrificeModifier(OrificeModifier.TENTACLED);
			this.removeAssOrificeModifier(OrificeModifier.TENTACLED);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.ONE_DISLIKE);
			setStartingCombatMoves();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.0.5")) {
			if(this.getSubspecies()==Subspecies.HALF_DEMON) {
				this.setStartingBody(false);
			} else {
				this.setStartingBody(false);
				Lyssieth.setDaughterDemonicBodyParts(this);
			}
//			// Reset sex stats:
//			this.sexCount = new HashMap<>();
//			this.virginityLossMap = new HashMap<>();
//			// Reset body knowledge and fluids:
//			this.areasKnownByCharactersMap = new HashMap<>();
//			this.fluidsStoredMap = new HashMap<>();
		}
		if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
			this.setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP, true);
			
		} else if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_3_ELIS)) {
			this.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MERAXIS);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.CHUUNI,
						Perk.CLOTHING_ENCHANTER,
						Perk.WEAPON_ENCHANTER
						),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 100)));
	}

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipAllSpellMoves();
	}
	
	private void addSpells() {
		this.resetSpells();
		
		this.addSpell(Spell.FIREBALL);
		this.addSpellUpgrade(SpellUpgrade.FIREBALL_1);
		this.addSpellUpgrade(SpellUpgrade.FIREBALL_2);
		
		this.addSpell(Spell.CLOAK_OF_FLAMES);
		this.addSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1);
		
		this.addSpell(Spell.ICE_SHARD);
		this.addSpellUpgrade(SpellUpgrade.ICE_SHARD_1);
		this.addSpellUpgrade(SpellUpgrade.ICE_SHARD_2);
		
		this.addSpell(Spell.POISON_VAPOURS);
		this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1);
		this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2);
		
		this.addSpell(Spell.VACUUM);
		this.addSpellUpgrade(SpellUpgrade.VACUUM_1);
		
		this.addSpell(Spell.SLAM);
		this.addSpellUpgrade(SpellUpgrade.SLAM_1);
		this.addSpellUpgrade(SpellUpgrade.SLAM_2);
		this.addSpellUpgrade(SpellUpgrade.SLAM_3);
		
		this.addSpell(Spell.TELEKENETIC_SHOWER);
		this.addSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_1);
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE,
					PersonalityTrait.INNOCENT);
			
			addSpells();
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ARCANE_RESEARCHER);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
			this.addFetish(Fetish.FETISH_VOYEURIST);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		this.setSubspeciesOverride(Subspecies.HALF_DEMON);
		this.setAgeAppearanceDifferenceToAppearAsAge(18);
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.ONE_SMALL.getValue());
		this.setHornType(HornType.SWEPT_BACK);
		this.setHornLength(8);
		
		// Core:
		this.setHeight(153);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES_HETEROCHROMATIC, PresetColour.EYE_GREEN, false, PresetColour.EYE_PURPLE, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);

		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_LIGHT), false);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_LIGHT), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_LIGHT), false);
		this.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_LIGHT), false);
		this.setSkinCovering(new Covering(BodyCoveringType.MOUTH, PresetColour.SKIN_LIGHT), false);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.AA.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		this.setNippleCapacity(0, true);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.removeAssOrificeModifier(OrificeModifier.TENTACLED);
		
		// Penis:
		// n/a
		this.setTesticleCount(2); // For if she grows one
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		this.removeVaginaOrificeModifier(OrificeModifier.TENTACLED);
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_thighhigh_socks_striped", PresetColour.CLOTHING_PURPLE_VERY_DARK, PresetColour.CLOTHING_BLACK, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_hand_striped_gloves"), PresetColour.CLOTHING_PURPLE_VERY_DARK, PresetColour.CLOTHING_BLACK, null, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_darkSiren_siren_amulet", false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_darkSiren_siren_cloak", false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_darkSiren_siren_seal", false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_scythe_scythe"), DamageType.POISON));
		}
	}
	
	public void applyScythe(boolean equip) {
		if(equip) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_scythe_scythe"), DamageType.POISON));
		} else {
			this.unequipAllWeaponsIntoVoid(true);
		}
	}
	
	public void postDefeatReset() {
		this.setObedience(ObedienceLevel.NEGATIVE_ONE_DISOBEDIENT.getMedianValue());
		this.setLocation(WorldType.LYSSIETH_PALACE, PlaceType.LYSSIETH_PALACE_SIREN_OFFICE, true);
		this.unequipAllClothingIntoVoid(true, true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_panties", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_trainer_socks", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_feminine_short_sleeve_shirt", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_feminine_blazer", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_BLACK_STEEL, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_darkSiren_siren_seal", false), true, this);
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#7E0094";
		}//
		return "#D397C5";//C374B1";
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of ruling over [npc.her] imp citadel as the 'Dark Siren' are now over."
					+ " Having been enslaved, she is now legally the property of her mother, Lyssieth, and had been expressly forbidden from leaving her room without supervision."));
		} else {
			return "The ruler of Submission's central imp citadel, this 'Dark Siren' is an incredibly powerful arcane user...";
		}
	}

	@Override
	public String getArtworkFolderName() {
		if(this.getTorsoType().getRace()==Race.HUMAN) {
			if(this.isVisiblyPregnant()) {
				return "MeraxisPregnant";
			}
			return "Meraxis";
			
		} else {
			if(this.isVisiblyPregnant()) {
				return "MeraxisDemonPregnant";
			}
			return "MeraxisDemon";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}

	@Override
	public void hourlyUpdate(int hour) {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_3_B_MEETING_MERAXIS)
				&& !Main.game.isBadEnd()
				&& Main.game.getPlayer().getWorldLocation()!=WorldType.getWorldTypeFromId("innoxia_fields_themiscyra")) {
			if(!Main.game.getCharactersPresent().contains(this)) {
				if(hour>=1 && hour<9) { // In room from 01:00 - 09:00
					this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_f1_room_meraxis"), true);
				} else {
					this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f0"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_f0_meraxis"));
				}
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character.isPlayer() && Main.game.getDialogueFlags().hasFlag("innoxia_child_of_lyssieth")) {
			return Util.newHashSetOfValues(Relationship.HalfSibling);
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}

	@Override
	public int getBreastRows() {
		return body.getBreast().getRows(); // Should not be affected by settings
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public CombatBehaviour getCombatBehaviour() {
		if(Main.game.isInCombat()) {
			boolean spellsAvailable = false;
			for(GameCharacter character : Main.combat.getAllCombatants(true)) {
				if(!getWeightedSpellsAvailable(character).isEmpty()) {
					spellsAvailable = true;
					break;
				}
			}
			if(spellsAvailable && Math.random()<0.75f) {
				return CombatBehaviour.SPELLS;
			}
		}
		return CombatBehaviour.ATTACK;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", ImpCitadelDialogue.KEEP_AFTER_COMBAT_VICTORY);
			
		} else {
			return new Response("", "", ImpCitadelDialogue.KEEP_AFTER_COMBAT_DEFEAT) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(
							UtilText.parseFromXMLFile("places/submission/impCitadel"+ImpCitadelDialogue.getDialogueEncounterId(), "KEEP_CHALLENGE_BOSS_DEFEAT", ImpCitadelDialogue.getAllCharacters()));
				}
			};
		}
	}
	
	// Extra methods for post-duelling options:
	
	public boolean isAssChanged() {
		return this.getAssSize().getValue()>AssSize.TWO_SMALL.getValue();
	}
	
	public String duelAssChange(boolean big) {
		StringBuilder sb = new StringBuilder();
		if(big) {
			sb.append(this.setAssSize(AssSize.FOUR_LARGE));
			sb.append(this.setHipSize(HipSize.FIVE_VERY_WIDE));
		} else {
			sb.append(this.setAssSize(AssSize.TWO_SMALL));
			sb.append(this.setHipSize(HipSize.THREE_GIRLY));
		}
		return sb.toString();
	}

	public boolean isBreastsChanged() {
		return this.getBreastSize().getMeasurement()>CupSize.AA.getMeasurement();
	}
	
	public String duelBreastChange(boolean big) {
		StringBuilder sb = new StringBuilder();
		if(big) {
			sb.append(this.setBreastSize(CupSize.DD));
			sb.append(this.setNippleSize(NippleSize.THREE_LARGE));
			sb.append(this.setAreolaeSize(AreolaeSize.THREE_LARGE));
		} else {
			sb.append(this.setBreastSize(CupSize.AA.getMeasurement()));
			sb.append(this.setNippleSize(NippleSize.TWO_BIG));
			sb.append(this.setAreolaeSize(AreolaeSize.TWO_BIG));
		}
		return sb.toString();
	}
	
	public String duelLactation(boolean lactate) {
		StringBuilder sb = new StringBuilder();
		if(lactate) {
			sb.append(this.setBreastMilkStorage(250));
			this.fillMilkToMaxStorage();
		} else {
			sb.append(this.setBreastMilkStorage(0));
			this.fillMilkToMaxStorage();
		}
		return sb.toString();
	}

	public boolean isMouthChanged() {
		return this.getLipSize().getValue()>LipSize.ONE_AVERAGE.getValue();
	}

	public String duelMouthChange(boolean big) {
		StringBuilder sb = new StringBuilder();
		if(big) {
			sb.append(this.setLipSize(LipSize.FOUR_HUGE));
			sb.append(this.addFaceOrificeModifier(OrificeModifier.PUFFY));
			sb.append(this.setFaceWetness(Wetness.SIX_SOPPING_WET.getValue()));
			sb.append(this.setTongueLength(TongueLength.ONE_LONG.getMedianValue()));
		} else {
			sb.append(this.setLipSize(LipSize.ONE_AVERAGE));
			sb.append(this.removeFaceOrificeModifier(OrificeModifier.PUFFY));
			sb.append(this.setFaceWetness(Wetness.THREE_WET.getValue()));
			sb.append(this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue()));
		}
		return sb.toString();
	}

	public boolean isPussyChanged() {
		return this.getVaginaLabiaSize().getValue()>LabiaSize.ZERO_TINY.getValue();
	}

	public String duelPussyChange(boolean big) {
		StringBuilder sb = new StringBuilder();
		if(big) {
			sb.append(this.setVaginaLabiaSize(LabiaSize.THREE_LARGE));
			sb.append(this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true));
			sb.append(this.setVaginaWetness(Wetness.SIX_SOPPING_WET));
		} else {
			sb.append(this.setVaginaLabiaSize(LabiaSize.ZERO_TINY));
			sb.append(this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true));
			sb.append(this.setVaginaWetness(Wetness.THREE_WET));
		}
		return sb.toString();
	}
	
	public String duelSquirter(boolean squirter) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.setVaginaSquirter(squirter));
		return sb.toString();
	}
}