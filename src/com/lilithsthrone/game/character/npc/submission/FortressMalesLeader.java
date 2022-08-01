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
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.submission.FortressMalesLeaderSA;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressMalesLeader extends NPC {

	public FortressMalesLeader() {
		this(false);
	}
	
	public FortressMalesLeader(boolean isImported) {
		super(isImported,
				new NameTriplet("Jhortrax", "Jhortriss", "Jhortriss"), "Liannamartu",
				"The leader of one of Submission's imp fortresses, [npc.name] and [npc.her] all-male gang of imps take great pleasure in breeding anyone foolish enough to oppose their ruler, 'The Dark Siren'...",
				65, Month.SEPTEMBER, 19,
				20, Gender.M_P_MALE, Subspecies.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);

		if(!isImported || Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) {
			this.setPlayerKnowsName(false);
		}
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.8")) {
			setStartingBody(true);
			equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.5")) {
			this.setCombatBehaviour(CombatBehaviour.ATTACK);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.BRAVE);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.FETISH_SEEDER,
						Perk.MELEE_DAMAGE),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 5),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 2)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setCombatBehaviour(CombatBehaviour.ATTACK);

			this.setPersonalityTraits(
					PersonalityTrait.BRAVE);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_CUM_STUD);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(35);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.STRAIGHT);
		this.setHornLength(HornLength.TWO_LONG.getMedianValue());

		// Core:
		this.setHeight(205);
		this.setFemininity(15);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:

		// Allow to be randomised:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_RED), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_RED), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.MESSY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PURPLE));
//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PURPLE));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(true);
//		this.setLipSize(LipSize.TWO_FULL);
//		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
//		this.setNippleVirgin(false);
//		this.setBreastSize(CupSize.E.getMeasurement());
//		this.setBreastShape(BreastShape.POINTY);
//		this.setNippleSize(NippleSize.THREE_LARGE);
//		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(30);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(1000);
		this.setPenisCumExpulsion(FluidExpulsion.FOUR_HUGE.getMedianValue());
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
//		this.setVaginaVirgin(false);
//		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
//		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
//		this.setVaginaSquirter(true);
//		this.setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN, true);
//		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
//		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
//		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_stomach_sarashi", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_wraps", PresetColour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_leg_mens_hakama"), PresetColour.CLOTHING_BLACK, false), true, this);

		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_japaneseSwords_katana"), DamageType.PHYSICAL));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_japaneseSwords_wakizashi"), DamageType.FIRE));
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
					"[npc.NamePos] days of ruling over [npc.her] imp fortress are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this, "The leader of one of Submission's imp fortresses, [npc.name] and [npc.her] all-male gang of imps take great pleasure in breeding anyone foolish enough to oppose their ruler, 'The Dark Siren'..."));
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	public boolean isAbleToEquipThong(GameCharacter target) {
		AbstractClothing thong = Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_RED_DARK, null);
		return target.isAbleToEquip(thong, true, this)
				&& Main.sex.getSexTypeCount(this, target, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0
				&& (target.getClothingInSlot(InventorySlot.GROIN)==null || !target.getClothingInSlot(InventorySlot.GROIN).getName().contains(UtilText.parse(this,"[npc.Name]")));	
	}
	
	public boolean isAbleToEquipDildo(GameCharacter target) {
		AbstractClothing dildo = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_vagina_insertable_dildo"), PresetColour.CLOTHING_PURPLE_DARK, null);
		return target.isAbleToEquip(dildo, true, this)
				&& Main.sex.getSexTypeCount(this, target, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
	}
	
	@Override
	public void endSex() {
		if(Main.sex.getPostSexDialogue().equals(ImpFortressDialogue.KEEP_AFTER_SEX_DEFEAT)) {
			if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_SEX_DEFEAT_WITH_COMPANION", ImpFortressDialogue.getAllCharacters()));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_SEX_DEFEAT", ImpFortressDialogue.getAllCharacters()));
			}
			if(ImpFortressDialogue.isGuardsDefeated()) {
				ImpFortressDialogue.resetGuards(Main.game.getPlayer().getWorldLocation());
			}
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_MALES);
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public int getOrgasmsBeforeSatisfied() {
		return 2;
	}
	
	@Override
	public GameCharacter getPreferredSexTarget() {
		if(Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class))!=null
				&& !FortressMalesLeaderSA.isBothTargetsUsed()
				&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
				|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)) {
			return FortressMalesLeaderSA.getBreedingTarget();
		}
		return null;
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.MISSIONARY && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(this)==SexSlotLyingDown.MISSIONARY && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}

		return super.getMainSexPreference(target);
	}

	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(FortressMalesLeaderSA.class);
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public CombatBehaviour getCombatBehaviour() {
		return CombatBehaviour.ATTACK;
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		
		//This is the fight at the citadel:
		if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if (victory) {
				return new Response("", "", ImpCitadelDialogue.KEEP_DEMONS_DEFEATED);
				
			} else {
				return new Response("", "", ImpCitadelDialogue.KEEP_AFTER_COMBAT_DEFEAT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/submission/impCitadel"+ImpCitadelDialogue.getDialogueEncounterId(), "KEEP_CHALLENGE_LEADER_DEFEAT", ImpCitadelDialogue.getAllCharacters()));
					}
				};
			}
		}
		
		// This is the fight in his fortress:
		if (victory) {
			return new Response("", "", ImpFortressDialogue.KEEP_AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
						Main.game.getTextEndStringBuilder().append(
								UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_KEY", ImpFortressDialogue.getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2), false));
						
					} else if(!ImpFortressDialogue.isDarkSirenDefeated()) {
						Main.game.getTextEndStringBuilder().append(
								UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY", ImpFortressDialogue.getAllCharacters()));
						
					} else {
						Main.game.getTextEndStringBuilder().append(
								UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_DS_DEFEATED", ImpFortressDialogue.getAllCharacters()));
					}
				}
			};
		} else {
			return new Response("", "", ImpFortressDialogue.KEEP_AFTER_COMBAT_DEFEAT);
		}
	}

}