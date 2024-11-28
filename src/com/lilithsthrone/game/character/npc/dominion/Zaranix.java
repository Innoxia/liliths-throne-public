package com.lilithsthrone.game.character.npc.dominion;

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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
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
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.moves.CMBasicAttack;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ZaranixDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.?
 * @version 0.3.5.5
 * @author Innoxia
 */
public class Zaranix extends NPC {

	public Zaranix() {
		this(false);
	}
	
	public Zaranix(boolean isImported) {
		super(isImported, new NameTriplet("Zaranix", "Zaranix", "Zaranix"), "Lyniximartu",
				"Zaranix is one of the few demons that feels more comfortable in his incubus, rather than succubus, form."
						+ " Muscular, tall, and handsome, Zaranix uses both his cunning mind and good looks to get what he wants.",
				204, Month.JULY, 3,
				15,
				null, null, null,
				new CharacterInventory(10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(32);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.8.5")) {
			this.setTesticleCount(2);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4")) {
			this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_PURPLE_DARK), true);
			this.clearPenisModifiers();
			this.addPenisModifier(PenetrationModifier.RIBBED);
			this.setName(new NameTriplet("Zaranix", "Zaranix", "Zaranix"));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
			this.setAge(204);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 10)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_ARCANE_RESEARCHER);
	
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		}
		
		
		// Body
		// Add full body reset as this method is called after leaving Zaranix's house:
		this.setAgeAppearanceAbsolute(32);
		this.setBody(Gender.M_P_MALE, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setLegType(LegType.DEMON_COMMON);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		this.setHornType(HornType.STRAIGHT);

		// Core:
		this.setHeight(190);
		this.setFemininity(15);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_ORANGE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_PURPLE), true);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_PURPLE_DARK), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.THREE_TRIMMED);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		// Leave as default
		
		// Chest:
		this.setNippleVirgin(true);
		// Leave as default
		
		// Ass:
		this.setAssVirgin(true);
		// Leave as default
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(25);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(550);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.RIBBED);
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_epic", DamageType.PHYSICAL));
		
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_GREY, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(this.isFeminine()) {
			return "#EB82ED";
		} else {
			return "#42C6FF";
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
	public void endSex() {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	
	public void transformFeminine() {
		this.setAgeAppearanceAbsolute(32);
		this.setBody(Gender.F_P_B_SHEMALE, Subspecies.DEMON, RaceStage.GREATER, false);
		this.setLegType(LegType.DEMON_COMMON);
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.NONE);
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		this.setHornType(HornType.STRAIGHT);

		// Core:
		this.setHeight(180);
		this.setFemininity(75);
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_ORANGE));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_PURPLE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_BLACK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ONE_STUBBLE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);
		
		// Face:
		this.setFaceVirgin(true);
		// Leave as default
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.B.getMeasurement());
		this.setBreastShape(BreastShape.PERKY);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(25);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(550);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.RIBBED);
	}

	// Combat:

	@Override
	public void resetDefaultMoves() {
		super.resetDefaultMoves();
		this.unequipMove(CombatMove.getIdFromCombatMove(CMBasicAttack.BASIC_TEASE));
		this.unequipMove(CombatMove.getIdFromCombatMove(CMBasicAttack.BASIC_TEASE_BLOCK));
	}
	
	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		return "<p>"
				+ UtilText.parse(target,
						UtilText.returnStringAtRandom(
						"With a booming shout, [zaranix.name] delivers a solid kick to [npc.namePos] torso!",
						"With an angry roar, [zaranix.name] punches [npc.name] square in the chest!",
						"[Zaranix.name] lets out a furious shout as he punches [npc.name] in the [npc.arm]!")) 
			+ "</p>";
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Letting out a booming roar, Zaranix thrusts his arm into the air and casts a spell!",
						"Zaranix steps back, and with an angry shout, casts a spell!") 
			+ "</p>";
	}

	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("Victory", "You have defeated Zaranix!", ZaranixDialogue.AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_H_THE_GREAT_ESCAPE) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
					}
				}
			};
		} else {
			return new Response("Defeat", "You have been defeated...", ZaranixDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	
	// Sex:

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(Main.game.getNpc(Zaranix.class))==SexSlotSitting.SITTING && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(Main.game.getNpc(Zaranix.class))==SexSlotSitting.SITTING && this.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}

		return super.getMainSexPreference(target);
	}
	
	@Override
	public GameCharacter getPreferredSexTarget() {
		if(Main.sex.getSexManager().getDominants().containsKey(Main.game.getNpc(Amber.class))) { // Amber is assisting the player to suck Zaranix's cock:
			return Main.game.getPlayer();
		}
		return super.getPreferredSexTarget();
	}
	
	// Misc.:
	
	public void generateNewTile() {
		if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ZARANIX)==null) {
			Vector2i towerLoc = new Vector2i(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_LILITHS_TOWER).getLocation());
			towerLoc.setX(towerLoc.getX()+1);
			towerLoc.setY(towerLoc.getY()-2);
			if(!Main.game.getWorlds().get(WorldType.DOMINION).getCell(towerLoc).getPlace().getPlaceType().equals(PlaceType.DOMINION_DEMON_HOME)) {
				towerLoc = new Vector2i(Main.game.getWorlds().get(WorldType.DOMINION).getRandomCell(PlaceType.DOMINION_DEMON_HOME).getLocation());
			}
			Main.game.getWorlds().get(WorldType.DOMINION).getCell(towerLoc).setPlace(
					new GenericPlace(PlaceType.DOMINION_DEMON_HOME_ZARANIX) {
						@Override
						public String getName() {
							return PlaceType.DOMINION_DEMON_HOME_ZARANIX.getName();
						}
					},
					false);
		}
	}

}