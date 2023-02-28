package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.List;

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
import com.lilithsthrone.game.combat.moves.CMBasicAttack;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemType;
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
public class Shadow extends NPC {

	public Shadow() {
		this(false);
	}
	
	public Shadow(boolean isImported) {
		super(isImported, new NameTriplet("Shadow"), "Werlock",
				"",
				23, Month.DECEMBER, 29,
				15, Gender.F_V_B_FEMALE, Subspecies.RAT_MORPH, RaceStage.GREATER,
				new CharacterInventory(30), WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, true);
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.7")) { // Reset character
			setupPerks(true);
			setStartingBody(true);
			setStartingCombatMoves();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6") && !this.hasItemType(ItemType.RESONANCE_STONE)) {
			this.addItem(Main.game.getItemGen().generateItem(ItemType.RESONANCE_STONE), false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.1")) {
			this.setupPerks(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.BESERK,
						Perk.UNARMED_TRAINING),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		equipMove("strike");
		equipMove("offhand-strike");
		equipMove("twin-strike");
		equipMove("block");
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
					PersonalityTrait.BRAVE,
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD,
					PersonalityTrait.SLOVENLY);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_GANG_BODY_GUARD);

			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.setFetishDesire(Fetish.FETISH_BREASTS_OTHERS, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
		}
		
		// Body:
		// Core:
		this.setHeight(173);
		this.setFemininity(70);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_RAT, PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_FUR, PresetColour.COVERING_JET_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.RAT_SKIN, PresetColour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_RAT_FUR, PresetColour.COVERING_JET_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMinimumValue()+5);
		this.setHairStyle(HairStyle.PIXIE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_RAT_FUR, PresetColour.COVERING_JET_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_GREEN_DARK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_GREEN_DARK));
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
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.TWO_BIG.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL.getValue());
		this.setHipSize(HipSize.THREE_GIRLY.getValue());
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
		this.setVaginaLabiaSize(LabiaSize.THREE_LARGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.THREE_FLEXIBLE.getValue());
		this.setVaginaPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		if(settings!=null && settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_kerambit_kerambit", DamageType.POISON, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL)));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_kerambit_kerambit", DamageType.POISON, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL)));
		}
		
		if(settings!=null && settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.addTattoo(InventorySlot.WRIST, new Tattoo(TattooType.getTattooTypeFromId("innoxia_gang_rat_skull"), PresetColour.CLOTHING_GREEN, PresetColour.CLOTHING_GREEN, PresetColour.CLOTHING_GREEN, true, null, null));
		}

		this.setPiercedEar(true);
		this.setPiercedNose(true);
		this.setPiercedVagina(true);
		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", PresetColour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_VAGINA, true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_SILVER, false), InventorySlot.PIERCING_STOMACH, true, this);
		
		AbstractClothing ring = Main.game.getItemGen().generateClothing("innoxia_finger_gemstone_ring", PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GREEN_DARK, null, false);
		
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_MELEE_WEAPON, TFPotency.MINOR_BOOST, 0));
		
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.DAMAGE_POISON, TFPotency.MINOR_BOOST, 0));

		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.MAJOR_BOOST, 0));
		ring.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.CRITICAL_DAMAGE, TFPotency.BOOST, 0));
		
		ring.setName("Shadow's Kiss");
		
		this.equipClothingFromNowhere(ring, true, this);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_belted", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_tube_top", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_stomach_sarashi", PresetColour.CLOTHING_BLACK, false), true, this);

		this.addItem(Main.game.getItemGen().generateItem(ItemType.RESONANCE_STONE), false);
		
//		this.equipClothingFromNowhere(Main.game.getItemGeneration().generateClothing("innoxia_hand_fishnet_gloves", PresetColour.CLOTHING_GREEN_VERY_DARK, false), true, this);
//		this.equipClothingFromNowhere(Main.game.getItemGeneration().generateClothing("innoxia_sock_fishnets", PresetColour.CLOTHING_GREEN_VERY_DARK, false), true, this);
		
	}

	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("As her adopted name, 'Shadow', suggests, this sneaky rat-girl is an unrivalled expert at sneaking around and remaining unseen when moving by cover of darkness."
				+ " Her considerable stealth skills are greatly enhanced by the fact that she has pitch-black fur and ebony skin, making her nigh impossible to spot when in a poorly-illuminated area.");

		sb.append("<br/>"
				+ "While her lust is not quite as insatiable as her companion, Silence, Shadow does often find herself feeling extremely horny,"
					+ " and thinks nothing of using her finely-honed stealth abilities to ambush innocent people and force herself upon them at blade-point."
				+ " From this indiscriminate targeting of those around her, combined with rumours of her previous employers being stabbed in the back, Shadow has a reputation for being underhanded and treacherous.");
		
		if(this.getHomeWorldLocation()==WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS) {
			sb.append("<br/>"
					+ "No longer a personal bodyguard for Vengar, Shadow is now a professional bounty hunter."
					+ " Joined by her long-time companion, Silence, she can be found in Slaver Alley's 'Bounty Hunter Lodge'.");
		} else {
			sb.append("<br/>"
					+"Despite her ill repute, she has earned a place as a personal bodyguard for the dangerous gang leader, 'Vengar'."
					+ " Joined by Silence, the one person she is unshakably loyal to, she is sworn to protect Vengar from any harm that might befall him.");
		}
		
		return sb.toString();
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
	public void hourlyUpdate() {
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
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarCaptiveShadowSatisfied, true);
	}
	
	// Combat:

	@Override
	public Response interruptCombatSpecialCase() {
		if(Main.combat.getAllCombatants(false).contains(this)
				&& Main.combat.getAllCombatants(false).contains(Main.game.getNpc(Silence.class))
				&& Main.combat.isCombatantDefeated(this)
				&& !Main.combat.isCombatantDefeated(Main.game.getNpc(Silence.class))) {
			return new Response("Silence",
					"As she sees Shadow fall to the floor, Silence stumbles back, looking as though she's about to faint.",
					RatWarrensDialogue.BODYGUARDS_COMBAT_SHADOW_DEFEATED){
				@Override
				public void effects() {
					Main.game.setInCombat(false);
				}
			};
			
		} else if(Main.combat.getAllCombatants(false).contains(this)
				&& Main.combat.getAllCombatants(false).contains(Main.game.getNpc(Silence.class))
				&& !Main.combat.isCombatantDefeated(this)
				&& Main.combat.isCombatantDefeated(Main.game.getNpc(Silence.class))) {
			return new Response("Shadow",
					"As she sees Silence fall to the floor, Shadow lets out a furious scream, looking as though she's about to completely lose her mind.",
					RatWarrensDialogue.BODYGUARDS_COMBAT_SILENCE_DEFEATED){
				@Override
				public void effects() {
					Main.game.setInCombat(false);
				}
			};
		}
		return null;
	};
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}
	
	@Override
	public float getMoveWeight(AbstractCombatMove move, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if((move==CMBasicAttack.BASIC_BLOCK || move==CMBasicAttack.BASIC_TEASE_BLOCK)
				&& !Main.combat.getAllCombatants(false).contains(Main.game.getNpc(Silence.class))) { // Shadow does not block when beserk
			return 0;
		}
		return super.getMoveWeight(move, enemies, allies);
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(victory) {
			return new Response("", "", RatWarrensDialogue.BODYGUARDS_COMBAT_VICTORY);
			
		} else {
			return new Response("", "", RatWarrensDialogue.BODYGUARDS_COMBAT_DEFEAT) {
				@Override
				public void effects() {
					RatWarrensDialogue.applyCombatDefeatFlagsReset();
				}
			};
		}
	}
}