package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
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
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
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
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.submission.FortressAlphaLeaderSA;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressAlphaLeader extends NPC {

	public FortressAlphaLeader() {
		this(false);
	}
	
	public FortressAlphaLeader(boolean isImported) {
		super(isImported,
				new NameTriplet("Fyrsia", "Fyrsia", "Fyrsia"), "Lilyshamartu",
				"The leader of one of Submission's imp fortresses, [npc.name] rules over [npc.her] followers with an iron fist, and shows very little respect towards anyone but [npc.her] boss, 'The Dark Siren'...",
				27, Month.JANUARY, 28,
				20, Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);

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
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12")) {
			AbstractClothing jacket = this.getClothingInSlot(InventorySlot.TORSO_OVER);
			if(jacket!=null) {
				try {
					this.isAbleToBeDisplaced(jacket, DisplacementType.UNZIPS, true, true, this);
				} catch(Exception ex) {
				}
			}
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.5")) {
			this.setCombatBehaviour(CombatBehaviour.ATTACK);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(Perk.UNARMED_DAMAGE),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 5),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setCombatBehaviour(CombatBehaviour.ATTACK);
			
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);
			
			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			
			this.setFetishDesire(Fetish.FETISH_NON_CON_SUB, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.THREE_LIKE);
		}
		
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(20);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.CURVED);

		// Core:
		this.setHeight(176);
		this.setFemininity(75);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_PURPLE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PALE), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_WHITE), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMinimumValue());
		this.setHairStyle(HairStyle.SIDECUT);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);
//
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_BLACK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_BLACK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_BLACK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.THREE_THICK);
		this.setPenisSize(25);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(250);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_SARASHI, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_socks", Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_leg_distressed_jeans"), Colour.CLOTHING_BLACK, Colour.CLOTHING_GREY, Colour.CLOTHING_BRASS, false), true, this);
		AbstractClothing jacket = AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_WOMENS_LEATHER_JACKET, Colour.CLOTHING_BLACK, false);
		this.equipClothingFromNowhere(jacket, true, this);
		this.isAbleToBeDisplaced(this.getClothingInSlot(ClothingType.TORSO_OVER_WOMENS_LEATHER_JACKET.getEquipSlots().get(0)), DisplacementType.UNZIPS, true, true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_foot_goth_boots_fem"), false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_hand_fishnet_gloves", Colour.CLOTHING_BLACK, false), true, this);
		
		if(settings.contains(EquipClothingSetting.ADD_ACCESSORIES)) {
			this.setPiercedEar(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedLip(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_LIP_RINGS, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedNavel(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedNipples(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NIPPLE_BARS, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedPenis(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_PENIS_RING, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedTongue(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_TONGUE_BAR, Colour.CLOTHING_SILVER, false), true, this);
			this.setPiercedVagina(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_VAGINA_BARBELL_RING, Colour.CLOTHING_SILVER, false), true, this);
		}
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_knuckleDusters_knuckle_dusters"), DamageType.PHYSICAL));
			this.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_knuckleDusters_knuckle_dusters"), DamageType.POISON));
		}
		
		if(settings.contains(EquipClothingSetting.ADD_TATTOOS)) {
			this.clearTattoosAndScars();
			
			Tattoo tat = new Tattoo(
					TattooType.TRIBAL,
					Colour.CLOTHING_BLACK,
					Colour.CLOTHING_BLACK,
					Colour.CLOTHING_BLACK,
					false,
					new TattooWriting("Choke on it, bitch!", Colour.CLOTHING_BLACK, false, TattooWritingStyle.ITALICISED),
					null);
			this.addTattoo(InventorySlot.GROIN, tat);
			
			tat = new Tattoo(
					TattooType.TRIBAL,
					Colour.CLOTHING_BLACK,
					Colour.CLOTHING_BLACK,
					Colour.CLOTHING_BLACK,
					false,
					null,
					null);
			this.addTattoo(InventorySlot.TORSO_OVER, tat);

			tat = new Tattoo(
					TattooType.TRIBAL,
					Colour.CLOTHING_BLACK,
					Colour.CLOTHING_BLACK,
					Colour.CLOTHING_BLACK,
					false,
					null,
					null);
			this.addTattoo(InventorySlot.HORNS, tat);
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
			return (UtilText.parse(this, description));
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	public boolean isAbleToEquipGag(GameCharacter target) {
		AbstractClothing ringGag = AbstractClothingType.generateClothing(ClothingType.BDSM_RINGGAG, Colour.CLOTHING_GOLD, Colour.CLOTHING_WHITE, Colour.CLOTHING_GOLD, null);
		return target.isAbleToEquip(ringGag, true, this) && (target.getClothingInSlot(InventorySlot.MOUTH)==null || !target.getClothingInSlot(InventorySlot.MOUTH).getName().contains(UtilText.parse(this,"[npc.Name]")));	
	}
	
	@Override
	public void endSex() {
		
		if(Sex.getPostSexDialogue().equals(ImpFortressDialogue.KEEP_AFTER_SEX_DEFEAT)) {
			if(ImpFortressDialogue.getMainCompanion()!=null && Sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_SEX_DEFEAT_WITH_COMPANION", ImpFortressDialogue.getAllCharacters()));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_SEX_DEFEAT", ImpFortressDialogue.getAllCharacters()));
			}
			if(ImpFortressDialogue.isGuardsDefeated()) {
				ImpFortressDialogue.resetGuards(Main.game.getPlayer().getWorldLocation());
			}
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
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
	public SexPace getSexPaceDomPreference(){
		if(Sex.isDom(this)) {
			return SexPace.DOM_ROUGH;
		}
		return null;
	}

	@Override
	public GameCharacter getPreferredSexTarget() {
		if(Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class))!=null
				&& !FortressAlphaLeaderSA.isBothTargetsUsed()
				&& (Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.ORGASM
				|| Sex.getLastUsedSexAction(Main.game.getNpc(FortressAlphaLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)) {
			return FortressAlphaLeaderSA.getBlowjobTarget();
		}
		return null;
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Sex.getSexPositionSlot(this)==SexSlotStanding.STANDING_DOMINANT && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Sex.getSexPositionSlot(this)==SexSlotStanding.STANDING_DOMINANT && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}

		return super.getMainSexPreference(target);
	}

	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(FortressAlphaLeaderSA.class);
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	public Attack attackType() {
		Map<Attack, Integer> attackWeightingMap = new HashMap<>();

		attackWeightingMap.put(Attack.MAIN, 100);
		attackWeightingMap.put(Attack.OFFHAND, 25);
		
		int total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
		}
		
		int index = Util.random.nextInt(total);
		total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
			if(index<total) {
				return entry.getKey();
			}
		}
		
		return Attack.MAIN;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", ImpFortressDialogue.KEEP_AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
						Main.game.getTextEndStringBuilder().append(
								UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_KEY", ImpFortressDialogue.getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
						
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