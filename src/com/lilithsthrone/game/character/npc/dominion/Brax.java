package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
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
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
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
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.4.9")) {
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.BRAVE);
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
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
		this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_GREY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_GREY), true);
		this.setHairLength(HairLength.ZERO_BALD);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_LYCAN_FUR, Colour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PURPLE));
		
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
		this.setPenisGirth(PenisGirth.THREE_THICK);
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
			AbstractClothing collar = AbstractClothingType.generateClothing("innoxia_bdsm_metal_collar", false);
			collar.setSealed(true);
			collar.setColour(Colour.CLOTHING_SILVER);
			this.equipClothingFromNowhere(collar, true, this);
		}
		
		if(isFeminine()) {
			if(hasFetish(Fetish.FETISH_BIMBO)) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_PANTIES, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NIPPLE_TAPE_CROSSES, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_FISHNET_TOP, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_leg_micro_skirt_pleated", Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_fishnets", Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_hand_fishnet_gloves", Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_finger_ring", Colour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);

				this.setPiercedEar(true);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNose(true);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNavel(true);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);
				
			} else {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_sock_kneehigh_socks", Colour.CLOTHING_WHITE, false), true, this);

				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfskirt", Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_flsldshirt", Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_neck_tie", Colour.CLOTHING_BLACK, false), true, this);
			}
			
		} else {
			// These are hanging up in his office:
//			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_uniques_enfdjacket_brax", Colour.CLOTHING_BLACK, false), true, this);
//			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdbelt", Colour.CLOTHING_DESATURATED_BROWN, false), true, this);
//			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_pcap", Colour.CLOTHING_BLACK, false), true, this);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_STEEL, false), true, this);

			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdslacks", Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_ssldshirt", Colour.CLOTHING_BLUE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing("innoxia_neck_tie", Colour.CLOTHING_BLACK, false), true, this);
			
			if(Main.game.getPlayer().hasQuest(QuestLine.MAIN) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
					this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon("innoxia_crystal_epic", DamageType.FIRE));
					this.setEssenceCount(TFEssence.ARCANE, 10);
					this.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.getWeaponTypeFromId("dsg_eep_pbweap_pbpistol")));
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
		if(this.isSlave() && !this.getOwner().isPlayer() && !Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isWorkTime()) {
				this.returnToHome();
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
	public Map<TFEssence, Integer> getLootEssenceDrops() {
		return Util.newHashMapOfValues(new Value<>(TFEssence.ARCANE, 8));
	}
	

//	// Penetrations
//	@Override
//	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
//		if(this.isSlave()) {
//			return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
//		}
//		if(Math.random()>0.3) {
//			if(Sex.getSexPositionSlot(characterPenetrated)==SexSlotLyingDown.COWGIRL){
//				if(orifice == SexAreaOrifice.VAGINA) {
//					if(penetrationType == SexAreaPenetration.PENIS && characterPenetrated.equals(this)) {
//						return UtilText.returnStringAtRandom(
//								"You keep bouncing up and down, slamming [brax.namePos] [npc.penis+] in and out of your [pc.pussy+].",
//								"With lewd little moans, you continue bouncing up and down on [brax.namePos] [npc.penis+].",
//								"You feel [brax.namePos] [npc.penis+] lewdly spreading out your [pc.pussy+] as you ride him.",
//								"You let out a gasp as you carry on spearing your [pc.pussy+] on [brax.namePos] [npc.penis+].");
//					} else if(penetrationType == SexAreaPenetration.TONGUE && characterPenetrated.isPlayer()) {
//						return UtilText.returnStringAtRandom(
//								"You hold the top of [brax.namePos] head, moaning softly as he carries on eating you out.",
//								"With a little giggle, you grind your [pc.pussy+] down on [brax.namePos] wolf-like muzzle.",
//								"You feel [brax.namePos] tongue eagerly lapping away at your [pc.pussy+].",
//								"You sink down a little further onto [brax.namePos] face, letting out a delighted sigh as you feel his tongue spearing deep into your [pc.pussy+].");
//					}
//				}
//				
//				if(orifice == SexAreaOrifice.ANUS) {
//					if(penetrationType == SexAreaPenetration.PENIS && characterPenetrated.equals(this)) {
//						return UtilText.returnStringAtRandom(
//								"You keep bouncing up and down, slamming [brax.namePos] [npc.penis+] in and out of your [pc.asshole+].",
//								"With lewd little moans, you continue bouncing up and down on [brax.namePos] [npc.penis+].",
//								"You feel [brax.namePos] [npc.penis+] lewdly spreading out your [pc.asshole+] as you ride him.",
//								"You let out a gasp as you carry on spearing your [pc.asshole+] on [brax.namePos] [npc.penis+].");
//						
//					} else if(characterPenetrated.isPlayer()) {
//						return UtilText.returnStringAtRandom(
//								"You hold the top of [brax.namePos] head, moaning softly as he carries on licking your [pc.asshole+].",
//								"With a little giggle, you grind your [pc.asshole+] down on [brax.namePos] wolf-like muzzle.",
//								"You feel [brax.namePos] tongue eagerly lapping away at your [pc.asshole+].",
//								"You sink down a little further onto [brax.namePos] face, letting out a delighted sigh as you feel his tongue spearing deep into your [pc.asshole+].");
//					}
//				}
//			}
//			
//			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.ANUS && characterPenetrated.equals(this)) {
//				return UtilText.returnStringAtRandom(
//						"You carry on slamming your [pc.penis+] into [brax.namePos] [npc.asshole+].",
//						"Holding his hips, you carry on fucking [brax.namePos] [npc.asshole+].",
//						"You feel [brax.namePos] [npc.asshole+] lewdly spreading out around your [pc.penis+] as you thrust into him.",
//						"[brax.name] gasps and groans as you carry on spearing your [pc.penis+] into his [npc.asshole+].");
//			}
//		}
//		
//		return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
//	}
	
}
