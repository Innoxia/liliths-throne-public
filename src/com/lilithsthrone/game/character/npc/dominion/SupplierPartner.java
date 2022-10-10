package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
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
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.3.4
 * @author Innoxia
 */
public class SupplierPartner extends NPC {

	public SupplierPartner() {
		this(false);
	}
	
	public SupplierPartner(boolean isImported) {
		super(isImported, new NameTriplet("Karl", "Karl", "Karla"), "Hummel",
				"Karl is the junior partner of the dobermann bounty-hunter duo which you first met at Kay's Textiles.",
				28, Month.AUGUST, 8,
				10,
				null, null, null,
				new CharacterInventory(10),
				WorldType.TEXTILES_WAREHOUSE,
				PlaceType.TEXTILE_WAREHOUSE_OVERSEER_STATION,
				true);
		
		if(!isImported) {
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 25);
		}
		
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(10);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.14")) {
			this.setStartingBody(true);
		}
		this.setDescription("Karl is the junior partner of the dobermann bounty-hunter duo which you first met at Kay's Textiles.");
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MARTIAL_BACKGROUND);
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
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
			this.setCombatBehaviour(CombatBehaviour.ATTACK);
			
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_BOUNTY_HUNTER);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_BREASTS_OTHERS);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_CUM_STUD);

			this.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			this.setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.ONE_DISLIKE);
		}
		
		
		// Body:
		this.setBody(Gender.M_P_MALE, Subspecies.DOG_MORPH_DOBERMANN, RaceStage.GREATER, false);

		// Core:
		this.setHeight(185);
		this.setFemininity(5);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DOG_MORPH, PresetColour.EYE_BROWN));
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_CANINE_FUR, PresetColour.COVERING_BLACK), true);
		this.setHairLength(0);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_CANINE_FUR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

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
		this.setPenisSize(15);
		this.setTesticleSize(TesticleSize.TWO_AVERAGE);
		this.setPenisCumStorage(65);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_BLACK, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#d2ba74";
	}
	
	@Override
	public void endSex() {
		setPendingClothingDressing(true);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	public void moveToBountyHunterLodge() {
		this.setLocation(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, PlaceType.BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS, true);
		if(Main.game.getHourOfDay()<2 || Main.game.getHourOfDay()>=10) {
			Vector2i barLocation = Main.game.getWorlds().get(WorldType.BOUNTY_HUNTER_LODGE).getCell(PlaceType.BOUNTY_HUNTER_LODGE_BAR).getLocation();
			this.setLocation(WorldType.BOUNTY_HUNTER_LODGE, new Vector2i(barLocation.getX()-1, barLocation.getY()), true);
		}
	}

	@Override
	public void turnUpdate() {
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)
				&& !Main.game.getCharactersPresent().contains(this)
				&& this.getMinutesSinceLastTimeHadSex()>15) {
			this.moveToBountyHunterLodge();
		}
		if(!this.hasStatusEffect(StatusEffect.RECENTLY_SMOKED) && Main.game.getDayMinutes()%15==0 && Math.random()<0.25f) {
			this.useItem(Main.game.getItemGen().generateItem(ItemType.CIGARETTE), this, false);
		}
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", KaysWarehouse.DOBERMANNS_COMBAT_PLAYER_VICTORY) {
				@Override
				public void effects() {
					if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN)) {
						KaysWarehouse.applySuppliersBeatenEffects();
					}
				}
			};
		} else {
			return new Response("", "", KaysWarehouse.DOBERMANNS_COMBAT_PLAYER_LOSS);
		}
	}
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	public List<AbstractCoreItem> getLootItems() {
		return Util.newArrayListOfValues(Main.game.getItemGen().generateItem("innoxia_race_dog_canine_crush"));
	}

	// Sex:

	public GameCharacter getPreferredSexTarget() {
		return Main.game.getPlayer();
	}
}
