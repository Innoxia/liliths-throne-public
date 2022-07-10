package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
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
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8
 * @version 0.2.11
 * @author Innoxia
 */
public class HarpyNympho extends NPC {

	public HarpyNympho() {
		this(false);
	}
	
	public HarpyNympho(boolean isImported) {
		super(isImported, new NameTriplet("Lexi"), "Holub",
				"One of the more notable harpy matriarchs, Lexi is the leader of a flock of harpies."
						+ " Either due to her obsession with sex, or perhaps because she's not as cruel as a typical harpy, Lexi is far more accepting of males than a typical matriarch."
						+ " As a result, her flock serves as her personal harem; it's members constantly trying to sate their matriarch's never-ending lust.",
				22, Month.APRIL, 13,
				7, Gender.F_V_B_FEMALE, Subspecies.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HARPY_NEST_PINK, true);
		
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.20")) {
			this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.SKIN_PINK_LIGHT), false);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.3.3")) {
			this.resetPerksMap(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.AHEGAO),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_HARPY_MATRIARCH);
	
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
		}
		
		// Body:

		// Core:
		this.setHeight(164);
		this.setFemininity(95);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, PresetColour.COVERING_PINK_LIGHT), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.SKIN_PINK_LIGHT), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, PresetColour.COVERING_PINK), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HARPY, PresetColour.COVERING_PINK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_PINK_LIGHT));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PINK_LIGHT));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.SEVEN_GAPING, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setAssPlasticity(OrificePlasticity.FOUR_ACCOMMODATING.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.FOUR_LOOSE, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet:
		// Foot shape
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_panties", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD, null, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, this);

		this.setPiercedEar(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#D60AB8";
			
		} else {
			return "#F967E3";
		}
	}

	@Override
	public void hourlyUpdate() {
		this.useItem(Main.game.getItemGen().generateItem("innoxia_pills_sterility"), this, false);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}

	// Combat:
	
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", HarpyNestNympho.HARPY_NEST_NYMPHO_FIGHT_BEAT_NYMPHO) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.nymphoPacified);
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_NYMPHO_LOLLIPOP), false, true));
					
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
					}
				}
			};
		} else {
			return new Response("", "", HarpyNestNympho.HARPY_NEST_NYMPHO_FIGHT_LOSE_TO_MATRIARCH);
		}
	}


	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		// You really shouldn't be able to alter this character's fetishes...
		if(user.isPlayer() && !target.isPlayer() && (item.getItemType().equals(ItemType.FETISH_UNREFINED) || item.getItemType().equals(ItemType.FETISH_REFINED))){
			return new Value<>(false,
					"<p>"
						+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and sighs,"
						+ " [npc.speechNoEffects(Mmm, yeah, that's not happening. I'm really not going to drink some random potion of yours!)]"
					+ "</p>");
		}
		
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
}