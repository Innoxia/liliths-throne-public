package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
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
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Silence extends NPC {

	public Silence() {
		this(false);
	}
	
	public Silence(boolean isImported) {
		super(isImported, new NameTriplet("Silence"), "Wildeyes",
				"",
				21, Month.MAY, 16,
				15, Gender.F_V_B_FEMALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, true);
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) { // Reset character
			setupPerks(true);
			setStartingBody(true);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			setStartingCombatMoves();
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TRAINING);
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_LIGHTNING);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipMove("offhand-strike");
		this.equipMove("twin-strike");
		this.equipAllSpellMoves();
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.clearPersonalityTraits();
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.setPersonalityTraits(
					PersonalityTrait.KIND,
					PersonalityTrait.MUTE,
					PersonalityTrait.LEWD);
			
			this.addSpell(Spell.POISON_VAPOURS);
			this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1);
			this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2);
			this.addSpellUpgrade(SpellUpgrade.POISON_VAPOURS_3);
			
			this.addSpell(Spell.VACUUM);
			this.addSpellUpgrade(SpellUpgrade.VACUUM_1);
			
			this.addSpell(Spell.PROTECTIVE_GUSTS);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2);
			this.addSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3);
			
			this.addSpell(Spell.ELEMENTAL_AIR);
			this.addSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_1);
			this.addSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_2);
			this.addSpellUpgrade(SpellUpgrade.ELEMENTAL_AIR_3A);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_GANG_BODY_GUARD);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.setFetishDesire(Fetish.FETISH_BREASTS_SELF, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		// Core:
		this.setHeight(167);
		this.setFemininity(90);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_RAT, CoveringPattern.EYE_IRISES, PresetColour.EYE_PINK, true, PresetColour.EYE_PINK, false));
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_WHITE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_PALE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_WHITE), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.PONYTAIL);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_WHITE), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK_LIGHT));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK_LIGHT));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK_LIGHT));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK), true);
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK), true);
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.DD.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.THREE_LARGE.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE.getValue());
		this.setHipSize(HipSize.FOUR_WOMANLY.getValue());
		this.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FIVE_ROOMY, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FIVE_YIELDING.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			AbstractWeapon weapon = Main.game.getItemGen().generateWeapon("innoxia_feather_epic", DamageType.POISON);
			weapon.setEffects(new ArrayList<>());
			this.equipMainWeaponFromNowhere(weapon);
			
			weapon = Main.game.getItemGen().generateWeapon("innoxia_feather_epic", DamageType.POISON);
			weapon.setEffects(new ArrayList<>());
			this.equipOffhandWeaponFromNowhere(weapon);
		}

		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_BLACK_JET, PresetColour.CLOTHING_BLACK_JET, false, null, null));
		}
		
		this.setPiercedEar(true);
		this.setPiercedVagina(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_piercings_piercing_vertical_hood", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_HOT, null, false), true, this);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_lacy_thong", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_skirt", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SLEEVELESS_TURTLENECK, PresetColour.CLOTHING_WHITE, false), true, this);

		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_tail_ribbon_tail_ribbon", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		AbstractClothing scrunchie = Main.game.getItemGen().generateClothing("norin_hair_accessories_hair_scrunchie", PresetColour.CLOTHING_PINK_LIGHT, false);
		scrunchie.setPattern("none");
		this.equipClothingFromNowhere(scrunchie, true, this);
		
		
		AbstractClothing demonStoneNecklace = Main.game.getItemGen().generateClothing("innoxia_neck_demonstone_necklace", PresetColour.CLOTHING_PINK_DARK, PresetColour.CLOTHING_SILVER, null, false);
		
		demonStoneNecklace.clearEffects();
		
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.INTELLIGENCE, TFPotency.MINOR_BOOST, 0));
		
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_MAJOR_ATTRIBUTE, TFModifier.STRENGTH, TFPotency.MINOR_BOOST, 0));

		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0));
		demonStoneNecklace.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.BOOST, 0));
		
		demonStoneNecklace.setName("Shard of Lyxias");
		
		this.equipClothingFromNowhere(demonStoneNecklace, true, this);
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Having fur as white as snow, and skin as pale as a ghost, this albino rat-girl would strike an eye-catching figure even if not for the fact that her pink eyes emit a fierce luminescent glow."
				+ " While her unusual eyes allude to the fact that she possesses exceptional arcane abilities, nobody has much of a chance of getting her to reveal the secrets of her past, for she is completely mute.");

		sb.append("<br/>"
				+ "Thanks to this inability of hers to talk, she's been given the name 'Silence', but what she lacks in verbal communication she more than compensates for in her sexual appetite."
				+ " Seemingly in a perpetual state of horniness, this lewd rat-girl can often be found riding a stranger's cock, or even more commonly, forcing them to perform oral on her.");

		if(this.getHomeWorldLocation()==WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS) {
			sb.append("<br/>"
					+ "No longer a personal bodyguard for Vengar, Silence is now a professional bounty hunter."
					+ " Joined by her long-time companion, Shadow, she can be found in Slaver Alley's 'Bounty Hunter Lodge'.");
		} else {
			sb.append("<br/>"
					+"Silence is a personal bodyguard for the dangerous gang leader, 'Vengar'."
					+ " Joined by her long-time companion, Shadow, she is sworn to protect the rat-boy from any harm that might befall him.");
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getArtworkFolderName() {
		if(this.getBreastRows()>1) {
			if(this.isVisiblyPregnant()) {
				return "SilenceMultiBoobPregnant";
			}
			return "SilenceMultiBoob";
		} else {
			if(this.isVisiblyPregnant()) {
				return "SilencePregnant";
			}
			return "Silence";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public void hourlyUpdate(int hour) {
		this.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), this, false);
	}

	public void moveToBountyHunterLodge() {
		this.setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_SHADOW_SILENCE, true);
		if(Main.game.getHourOfDay()<2 || Main.game.getHourOfDay()>=10) {
			this.setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_STAIRS, false);
			this.setNearestLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_SEATING, false);
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
			if(!Main.game.getCharactersPresent().contains(this)
					&& Main.game.getPlayer().isCaptive()
					&& !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
				if(!Main.game.isExtendedWorkTime() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					this.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS);
				} else {
					this.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
				}
			}

		} else {
			if(!Main.game.getCharactersPresent().contains(this)) {
				this.moveToBountyHunterLodge();
			}
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated(){
		return Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR);
	}

	@Override
	public void endSex() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarCaptiveSilenceSatisfied, true);
	}
	
	// Combat:

	@Override
	public Response interruptCombatSpecialCase() {
		return Main.game.getNpc(Shadow.class).interruptCombatSpecialCase();
	};
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public CombatBehaviour getCombatBehaviour() {
		if(Main.game.isInCombat()) {
			boolean spellsAvailable = false;
			for(GameCharacter character : Main.combat.getAllCombatants(true)) {
				if(!getWeightedSpellsAvailable(character).keySet().stream().filter(s->s!=Spell.ELEMENTAL_AIR).collect(Collectors.toList()).isEmpty()) {
					spellsAvailable = true;
					break;
				}
			}
			if(spellsAvailable) {
				return CombatBehaviour.SPELLS;
			}
		}
		return CombatBehaviour.ATTACK;
	}
	
	@Override
	public float getMoveWeight(AbstractCombatMove move, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(move.getAssociatedSpell()==Spell.ELEMENTAL_AIR) {
			return 0;
		}
		return super.getMoveWeight(move, enemies, allies);
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(this.isElementalSummoned()) {
			NPC elemental = this.getElemental();
			this.removeCompanion(elemental);
			elemental.returnToHome();
		}
		return Main.game.getNpc(Shadow.class).endCombat(applyEffects, victory);
	}
}