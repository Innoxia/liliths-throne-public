package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
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
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
 * @author Innoxia
 */
public class FortressDemonLeader extends NPC {

	public FortressDemonLeader() {
		this(false);
	}
	
	public FortressDemonLeader(boolean isImported) {
		super(isImported,
				new NameTriplet("Mhyralyss"),
				"The ruler of Submission's central imp citadel, 'The Dark Siren' is an incredibly powerful arcane user...",
				26, Month.OCTOBER, 13,
				25, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL, new CharacterInventory(10), WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP, true);

		if(!isImported || Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.5")) {
			this.setPlayerKnowsName(false);
			this.setGenericName("dark siren");
			this.addTrait(Perk.CHUUNI);
			this.setEssenceCount(TFEssence.ARCANE, 10000);
			
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
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.5")) {
			setName(new NameTriplet("Mhyralyss"));
			this.setBody(Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.PARTIAL_FULL);
			setStartingBody(true);
			equipClothing(true, true, true, true);
			this.setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP, true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12.6")) {
			equipClothing(true, true, true, true);
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 40);
			this.setAttribute(Attribute.MAJOR_ARCANE, 50);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 100);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);
			
			this.clearFetishes();
			
			this.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
			this.addFetish(Fetish.FETISH_VOYEURIST);
		}
		
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(18);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setHornType(HornType.CURLED);

		// Core:
		this.setHeight(153);
		this.setFemininity(80);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.EYE_IRISES_HETEROCHROMATIC, Colour.EYE_GREEN, false, Colour.EYE_PURPLE, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_RED), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.LOOSE);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.TWO_MANICURED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_PURPLE_DARK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_PURPLE_DARK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
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
		this.setNippleSize(NippleSize.ONE_SMALL);
		this.setAreolaeSize(AreolaeSize.ONE_SMALL);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.THREE_GIRLY);
		// Anus settings and modifiers
		
		// Penis:
//		this.setPenisVirgin(false);
//		this.setPenisGirth(PenisGirth.TWO_AVERAGE);
//		this.setPenisSize(8);
//		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
//		this.setPenisCumStorage(100);
//		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.TWO_MOIST);
		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setVaginaPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean addWeapons, boolean addScarsAndTattoos, boolean addAccessories) {
		this.unequipAllClothingIntoVoid(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_THIGHHIGH_SOCKS_STRIPED, Colour.CLOTHING_PURPLE_VERY_DARK, Colour.CLOTHING_BLACK, null, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_hand_striped_gloves"), Colour.CLOTHING_PURPLE_VERY_DARK, Colour.CLOTHING_BLACK, null, false), true, this);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_witch_witch_boots", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_witch_witch_dress", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_witch_witch_hat_wide", Colour.CLOTHING_BLACK, Colour.CLOTHING_GOLD, Colour.CLOTHING_PURPLE_VERY_DARK, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_darkSiren_siren_amulet", false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_darkSiren_siren_cloak", false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_darkSiren_siren_seal", false), true, this);
		
		this.setPiercedEar(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_BLACK_STEEL, false), true, this);
		
		if(addWeapons) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_scythe_scythe"), DamageType.POISON));
		}
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
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public Attack attackType() {
		//TODO need to limit scythe spell
		if(!getWeightedSpellsAvailable(Combat.getTargetedCombatant(this)).isEmpty() && Math.random()<0.75f) {
			return Attack.SPELL;
		}

		return Attack.MAIN;
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

}