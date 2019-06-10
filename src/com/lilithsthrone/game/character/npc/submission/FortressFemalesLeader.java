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
import com.lilithsthrone.game.character.body.CoverableArea;
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
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
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
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class FortressFemalesLeader extends NPC {

	public FortressFemalesLeader() {
		this(false);
	}
	
	public FortressFemalesLeader(boolean isImported) {
		super(isImported,
				new NameTriplet("Hyorlyix", "Hyorlyss", "Hyorlyss"), "Loviennemartu",
				"The leader of one of Submission's imp fortresses, [npc.name] has a loyal following of female imps, who love nothing more than to join [npc.herHim] in having dominant sex with those who oppose their ruler, 'The Dark Siren'...",
				22, Month.JANUARY, 11,
				20, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
		
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
			this.resetPerksMap();
		}
	}

	@Override
	public void setupPerks() {
		this.addSpecialPerk(Perk.SLUT);
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 1)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:
		
		if(setPersona) {
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MUGGER);

			this.clearFetishes();
			this.clearFetishDesires();
			
			this.addFetish(Fetish.FETISH_BIMBO);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
		}
		
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(18);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.SWEPT_BACK);
		
		// Core:
		this.setHeight(168);
		this.setFemininity(85);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_BLUE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.COVERING_PURPLE_LIGHT), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLONDE), true);
		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLONDE), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_PINK));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_PINK));
		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_PINK_LIGHT));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_PINK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK_LIGHT));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.FOUR_HUGE);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.H.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.THREE_LARGE);
		this.setAreolaeSize(AreolaeSize.THREE_LARGE);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		// n/a
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.SIX_STRETCHED_OPEN, true);
		this.setVaginaWetness(Wetness.SEVEN_DROOLING);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_TUBE_TOP, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_PLEATED, Colour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_fishnets", Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_FISHNET_GLOVES, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_foot_stiletto_heels", Colour.CLOTHING_PINK, false), true, this);
		
		if(settings.contains(EquipClothingSetting.ADD_ACCESSORIES)) {
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_ankle_anklet", Colour.CLOTHING_GOLD, false), true, this);
			
			this.setPiercedEar(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
			this.setPiercedNose(true);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
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
	
	public void equipStrapon() {
		try {
			if(this.getClothingInSlot(InventorySlot.GROIN)!=null) {
				this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.GROIN), true, this);
			}
			this.displaceClothingForAccess(CoverableArea.PENIS);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.BDSM_PENIS_STRAPON, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		} catch(Exception ex) {
		}
	}

	public boolean isAbleToEquipButtPlug(GameCharacter target) {
		AbstractClothing buttPlug = AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"), Colour.CLOTHING_SILVER, Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_PINK_LIGHT, null);
		return target.isAbleToEquip(buttPlug, true, this) && (target.getClothingInSlot(InventorySlot.ANUS)==null || !target.getClothingInSlot(InventorySlot.ANUS).getName().contains(UtilText.parse(this,"[npc.Name]")));	
	}
	
	@Override
	public void endSex() {
		if(this.getClothingInSlot(InventorySlot.PENIS)!=null) {
			this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.PENIS), true, this);
			if(this.getClothingInSlot(InventorySlot.GROIN)==null) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_LACY_PANTIES, Colour.CLOTHING_BLACK, false), true, this);
			}
			this.replaceAllClothing();
		}
		
		if(Sex.getPostSexDialogue().equals(ImpFortressDialogue.KEEP_AFTER_SEX_DEFEAT)) {
			if(ImpFortressDialogue.getMainCompanion()!=null && Sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_SEX_DEFEAT_WITH_COMPANION", ImpFortressDialogue.getAllCharacters()));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_SEX_DEFEAT", ImpFortressDialogue.getAllCharacters()));
			}
			if(ImpFortressDialogue.isGuardsDefeated()) {
				ImpFortressDialogue.resetGuards(Main.game.getPlayer().getWorldLocation());
			}
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES);
		}
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
		
		Map<Attack, Integer> attackWeightingMap = new HashMap<>();
		boolean canCastASpecialAttack = false;//!getSpecialAttacksAbleToUse().isEmpty();

		attackWeightingMap.put(Attack.SEDUCTION, 100);
		attackWeightingMap.put(Attack.SPECIAL_ATTACK, canCastASpecialAttack?25:0);
		
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
		
		return Attack.SEDUCTION;
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", ImpFortressDialogue.KEEP_AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
						Main.game.getTextEndStringBuilder().append(
								UtilText.parseFromXMLFile("places/submission/fortress"+ImpFortressDialogue.getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_KEY", ImpFortressDialogue.getAllCharacters()));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3), false));
						
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