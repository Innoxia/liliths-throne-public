package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionExpressCentaur;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public class DominionExpress {
	
	private static SexAreaInterface sleepSexAreaWanted = null;
	private static int slavePointsReward = 1;
	private static GameCharacter activeSlave;

	private static List<FillyReward> fillyRewards;
	static {
		fillyRewards = new ArrayList<>();
		// Basic filly: Add horse-morph penis/ass, feminine++, boobs++:
		fillyRewards.add(
				new FillyReward("Basic Filly", "Get rid of your pussy and gain a horse-morph cock and ass, as well as big boobs and a feminine figure!", 5) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BASIC_FILLY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().setVaginaType(VaginaType.NONE));
						sb.append(Main.game.getPlayer().setAssType(AssType.HORSE_MORPH));
						sb.append(Main.game.getPlayer().setPenisType(PenisType.EQUINE));
						if(Main.game.getPlayer().getFemininityValue()<75) {
							sb.append(Main.game.getPlayer().setFemininity(75));
						}
						if(Main.game.getPlayer().getBreastSize().getMeasurement()<CupSize.D.getMeasurement()) {
							sb.append(Main.game.getPlayer().setBreastSize(CupSize.D));
							if(Main.game.getPlayer().getNippleSize().getValue()<NippleSize.TWO_BIG.getValue()) {
								Main.game.getPlayer().setNippleSize(NippleSize.TWO_BIG);
							}
							if(Main.game.getPlayer().getAreolaeSize().getValue()<AreolaeSize.TWO_BIG.getValue()) {
								Main.game.getPlayer().setAreolaeSize(AreolaeSize.TWO_BIG);
							}
						}
						return sb.toString();
					}
				});
		// Taur: Makes lower body taur:
		fillyRewards.add(
				new FillyReward("Taurrific", "Become a centaur and finally get that feral horse body you've always dreamed of!", 5) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						if(Main.game.getPlayer().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
							return new Value<>(false, "You are already a taur, so this reward will do nothing!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_TAUR"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
							if(Main.game.getPlayer().getLegType()!=LegType.DEMON_HORSE_HOOFED) {
								sb.append(Main.game.getPlayer().setLegType(LegType.DEMON_HORSE_HOOFED));
							}
							sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.QUADRUPEDAL, true));
						} else {
							if(Main.game.getPlayer().getLegType()!=LegType.HORSE_MORPH) {
								sb.append(Main.game.getPlayer().setLegType(LegType.HORSE_MORPH));
							}
							sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.QUADRUPEDAL, true));
						}
						return sb.toString();
					}
				});
		// Ass-pussy: Lubrication, puffy, depth, muscles.
		fillyRewards.add(
				new FillyReward("Ass-pussy", "Have your ass turned into the perfect sexual orifice!", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_ASS_PUSSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementAssWetness(3));
						sb.append(Main.game.getPlayer().incrementAssDepth(3));
						sb.append(Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
						return sb.toString();
					}
				});
		// Throat-pussy: Bigger lips, wetter throat, depth, muscles.
		fillyRewards.add(
				new FillyReward("Throat-pussy", "Sucking horse-cock will never be the same again after this!", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_THROAT_PUSSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementFaceWetness(3));
						sb.append(Main.game.getPlayer().incrementFaceDepth(3));
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
						return sb.toString();
					}
				});
		// Ass-licker: Bigger lips, wetter tongue, longer tongue.
		fillyRewards.add(
				new FillyReward("Ass-licker", "Big, puffy lips and a long, strong tongue are just what you need to give all those horse-asses the love they deserve!", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_ASS_LICKER"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementFaceWetness(3));
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().incrementTongueLength(10));
						sb.append(Main.game.getPlayer().addTongueModifier(TongueModifier.STRONG));
						return sb.toString();
					}
				});
		// Hung: Penis size+, more cum, bigger balls.
		fillyRewards.add(
				new FillyReward("Hung like a Horse", "You'll love the feeling of having a big, thick cock swinging back and forth while you're getting rutted!", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							return new Value<>(false, "You need to have a penis in order to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_HUNG"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementPenisSize(10));
						if(Main.game.getPlayer().getPenisGirth().getValue()<PenetrationGirth.FIVE_THICK.getValue()) {
							sb.append(Main.game.getPlayer().incrementPenisGirth(1));
						}
						if(Main.game.getPlayer().getTesticleSize().getValue()<TesticleSize.FIVE_MASSIVE.getValue()) {
							sb.append(Main.game.getPlayer().incrementTesticleSize(1));
						}
						sb.append(Main.game.getPlayer().incrementPenisCumStorage(50));
						return sb.toString();
					}
				});
		// Sissy filly: Penis size-, smaller balls.
		fillyRewards.add(
				new FillyReward("Sissy-filly", "Show your partners what a submissive little sissy-filly you are by having a pathetically-tiny cock between your legs!", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							return new Value<>(false, "You need to have a penis in order to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_SISSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						if(Main.game.getPlayer().getPenisRawSizeValue()>PenisLength.ONE_TINY.getMedianValue()) {
							if(Main.game.getPlayer().getPenisRawSizeValue()>PenisLength.ONE_TINY.getMedianValue()+10) {
								sb.append(Main.game.getPlayer().incrementPenisSize(-10));
							} else {
								sb.append(Main.game.getPlayer().setPenisSize(PenisLength.ONE_TINY.getMedianValue()));
							}
						}
						if(Main.game.getPlayer().getPenisGirth().getValue()>PenetrationGirth.THREE_AVERAGE.getValue()) {
							sb.append(Main.game.getPlayer().incrementPenisGirth(-1));
						}
						if(Main.game.getPlayer().getTesticleSize().getValue()>TesticleSize.ONE_TINY.getValue()) {
							sb.append(Main.game.getPlayer().incrementTesticleSize(-1));
						}
						return sb.toString();
					}
				});
		// Top-heavy: Breast size+, puffy nipples, nipple size+
		fillyRewards.add(
				new FillyReward("Top-heavy", "Boost the size of your breasts and give those centaur slaves something to stare at (and squeeze)!", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_TOP_HEAVY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementBreastSize(3));
						sb.append(Main.game.getPlayer().incrementNippleSize(1));
						sb.append(Main.game.getPlayer().incrementAreolaeSize(1));
						sb.append(Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY));
						return sb.toString();
					}
				});
		// Subby slut: submissive, oral giving, anal giving, penis receiving, anal receiving
		fillyRewards.add(
				new FillyReward("Subby Slut", "Let us help you to remember what a dirty little filly slut you are!", 50) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_SUBBY"));
						sb.append(Main.game.getPlayer().removeFetish(Fetish.FETISH_DOMINANT, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_PENIS_RECEIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						return sb.toString();
					}
				});
		// Bimbo: Add bimbo fetish, big lips, bleach-blonde hair, breast size+
		fillyRewards.add(
				new FillyReward("Bimbolicious", "Thinking about stuff other than submitting to horse-cock and horse-ass is, like, totally annoying, isn't it?", 50) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "You need to be able to access your mouth to receive this reward!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BIMBO"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_MENTAL_END"));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_BIMBO, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE, true));
						
						if(Main.game.getPlayer().getFemininityValue()<95) {
							sb.append(Main.game.getPlayer().setFemininity(95));
						}
						
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						
						sb.append(Main.game.getPlayer().incrementBreastSize(6));
						sb.append(Main.game.getPlayer().incrementNippleSize(1));
						sb.append(Main.game.getPlayer().incrementAreolaeSize(1));
						sb.append(Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY));

						sb.append(Main.game.getPlayer().setHairCovering(
								new Covering(Main.game.getPlayer().getHairType().getBodyCoveringType(Main.game.getPlayer()), CoveringPattern.HIGHLIGHTS, PresetColour.COVERING_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), false));
						
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BIMBO_END"));
						Main.game.getPlayer().addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
						
						return sb.toString();
					}
				});
		
		fillyRewards.add(
				new FillyReward("Silver collar", "Get that shiny upgrade! With this, you'll be permitted to service Mistress Natalya once per day!", 100) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(getPlayerCollar().getColour(0)!=PresetColour.CLOTHING_BRONZE) {
							return new Value<>(false, "You've already upgraded your collar to silver!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), true);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_SILVER_COLLAR"));
						getPlayerCollar().setColour(0, PresetColour.CLOTHING_SILVER);
						return sb.toString();
					}
				});
		
		fillyRewards.add(
				new FillyReward("Gold collar", "Mistress Natalya has promised to let any gold filly dominate her, but let's be real here; there's no way you're ever going to be able to afford this!", 1000) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(getPlayerCollar().getColour(0)==PresetColour.CLOTHING_GOLD) {
							return new Value<>(false, "You've already upgraded your collar to gold!");
						}
						if(getPlayerCollar().getColour(0)!=PresetColour.CLOTHING_SILVER) {
							return new Value<>(false, "You need to upgrade your collar to silver before advancing to gold!");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), true);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_GOLD_COLLAR"));
						getPlayerCollar().setColour(0, PresetColour.CLOTHING_GOLD);
						return sb.toString();
					}
				});
	}
	
	
	private static AbstractClothing getPlayerCollar() {
		return Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
	}
	
	private static boolean wearingFillyCollar() {
		return getPlayerCollar()!=null && getPlayerCollar().getClothingType().getId().equals("innoxia_neck_filly_choker");
	}
	
	private static AbstractClothing generateCollar() {
		return Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_filly_choker"), false);
	}
	
	private static boolean isPlayerBodyCorrect() {
		return Main.game.getPlayer().isFeminine()
				&& Main.game.getPlayer().getBreastSize().getMeasurement()>=CupSize.D.getMeasurement()
				&& Main.game.getPlayer().hasPenis()
				&& !Main.game.getPlayer().hasVagina();
	}
	
	private static String applyTransformation() {
		StringBuilder sb =  new StringBuilder();
		
		TransformativePotion potion = Main.game.getNpc(Natalya.class).generateTransformativePotion(Main.game.getPlayer());

		if(Main.game.getPlayer().isAbleToHaveRaceTransformed()) {
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.COVERING_BLACK), false);
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.COVERING_BLACK), false);
		}
		
		for(PossibleItemEffect pe : potion.getEffects()) {
			sb.append(pe.getEffect().applyEffect(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), 1));
		}
		
		return sb.toString();
	}
	
	public static GameCharacter spawnSlave(boolean feminine) {
		return spawnSlave(feminine, null);
	}
	
	public static GameCharacter spawnSlave(boolean feminine, Colour collarColour) {
		slavePointsReward = 2+Util.random.nextInt(4); // Slaves give the player 2-5 points to service them
		
		NPC npc;
		if(collarColour==null) {
			collarColour = PresetColour.CLOTHING_GOLD;
			double rnd = Math.random();
			if(rnd<0.8f) {
				collarColour = PresetColour.CLOTHING_BRONZE;
			} else if(rnd<0.95f) {
				collarColour = PresetColour.CLOTHING_SILVER;
			}
		}
		if(feminine) {
			npc = new DominionExpressCentaur(Gender.F_P_B_SHEMALE, collarColour);
		} else {
			npc = new DominionExpressCentaur(Gender.M_P_MALE, collarColour);
		}
		npc.setLocation(Main.game.getPlayer(), false);
		
		npc.setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed()-(25*60*60));
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.getNpc(Natalya.class).addSlave(npc);
		
		return npc;
	}
	
	public static void applySadistSlave(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_SADIST);
		
		slave.setName(new NameTriplet("Thunder"));
		slave.setPlayerKnowsName(true);

		slave.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		slave.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		slave.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		slave.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		
		slave.setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_HAZEL), false);
		slave.setSkinCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_HAZEL), false);
		
		if(slave.isFeminine()) {
			slave.setHairLength(HairLength.FOUR_MID_BACK);
			slave.setHairStyle(HairStyle.BRAIDED);
		} else {
			slave.setHairLength(HairLength.ONE_VERY_SHORT);
			slave.setHairStyle(HairStyle.NONE);
		}
		
		slave.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.COVERING_BLACK), false);
		slave.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.COVERING_BLACK), false);
		
		slave.setHeight(235);
		
		Main.game.getDialogueFlags().setSadistNatalyaSlave(slave.getId());
	}
	
	private static List<GameCharacter> getSavedSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION_EXPRESS).getCell(PlaceType.DOMINION_EXPRESS_STABLES)));
		list.removeIf((npc)->!(npc instanceof DominionExpressCentaur));
		return list;
	}
	
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->!(npc instanceof DominionExpressCentaur));
		return list;
	}

	private static GameCharacter getSadistSlave() {
		try {
			return Main.game.getNPCById(Main.game.getDialogueFlags().getSadistNatalyaSlave());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void banishSlave(GameCharacter slave, boolean delete) {
		if(delete) {
			Main.game.banishNPC((NPC) slave);
			
		} else {
			slave.setHomeLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_STABLES);
			slave.returnToHome();
		}
		activeSlave = null;
	}
	
	public static final DialogueNode INITIAL_ENTRANCE = new DialogueNode("", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
				return ENTRANCE.getResponse(responseTab, index);
				
			} else {
				if(index==0) {
					return new Response("Exit", "Tell the receptionist that you made a mistake and head back out into the warehouse district.", Warehouses.WAREHOUSE_DISTRICT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
						}
					};
					
				} else if(index==1) {
					return new Response("Show card", "Show the receptionist the card you received from Natalya.", INITIAL_ENTRANCE_CARD_SHOWN);
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode INITIAL_ENTRANCE_CARD_SHOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE_CARD_SHOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Exit", "Head back out into the warehouse district.", Warehouses.WAREHOUSE_DISTRICT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(wearingFillyCollar()) {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Attract attention", "As you are unable to access your mouth, you cannot service any centaur slaves, so there's no point in trying to attract their attention...", null);
						
					} else {
						return new ResponseEffectsOnly(
								"Look for partner",
								"With your filly choker on display, you know that you'll easily be able to attract the attention of a passing centaur slave..."){
								@Override
								public int getSecondsPassed() {
									return 5*60;
								}
								@Override
								public void effects() {
									DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode STORAGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode FILLY_STATION = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Machine", "Approach the machine and see if you can interact with it...", FILLY_STATION_MACHINE);
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==FILLY_STATION_POSTER) {
					return new Response("Poster", "You're already reading the informative poster...", null);
				}
				return new Response("Poster", "Read the informative poster that's taped to the wall beside the arcane machine.", FILLY_STATION_POSTER);
			}
			return null;
		}
	};

	public static final DialogueNode FILLY_STATION_POSTER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_POSTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return FILLY_STATION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode FILLY_STATION_MACHINE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE"));
			
			if(!wearingFillyCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE_REJECTED"));
				
			} else {
				UtilText.addSpecialParsingString(String.valueOf(Main.game.getDialogueFlags().getNatalyaPoints()), true);
				Colour collarColour = getPlayerCollar().getColour(0);
				UtilText.addSpecialParsingString("<span style='color:"+collarColour.toWebHexString()+";'>"+Util.capitaliseSentence(collarColour.getName())+"</span>", false);
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE_ACCEPTED"));
				
				sb.append("<div class='container-full-width'>");
					for(FillyReward fr : fillyRewards) {
						int points = Main.game.getDialogueFlags().getNatalyaPoints();
						Value<Boolean, String> available = fr.isAvailable();
						sb.append("<div class='container-full-width inner' style='margin-bottom:4px;'>");
							sb.append((available.getKey()?"[style.boldPink(":"[style.boldBad(")+fr.getName()+":)] ");
							sb.append((points<fr.getCost()?"[style.colourBad(":"[style.colourPinkLight(")+fr.getCost()+")] [style.colourPinkLight(Filly Points)]<br/>");
							sb.append(fr.getDescription());
							if(!available.getKey()) {
								sb.append("<br/>[style.italicsBad("+available.getValue()+")]");
							}
						sb.append("</div>");
					}
				sb.append("</div>");
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Step back", "Step away from the machine...", FILLY_STATION);
			}
			if(wearingFillyCollar()) {
				List<Response> responses = new ArrayList<>();
				for(FillyReward fr : fillyRewards) {
					int points = Main.game.getDialogueFlags().getNatalyaPoints();
					Value<Boolean, String> available = fr.isAvailable();
					if(!available.getKey()) {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsBad("+available.getValue()+")]", null));
						
					} else if(points<fr.getCost()) {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsBad(You "+(points==0?"have no":"only have "+points)+" filly points, so cannot afford the cost of "+fr.getCost()+"!)]", null));
						
					} else {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsPinkLight(You have "+points+" filly points, and this will cost you "+fr.getCost()+"!)]", FILLY_STATION_REWARD) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(fr.applyEffect());
								Main.game.getTextStartStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-fr.getCost()));
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
			}
			return null;
		}
	};

	public static final DialogueNode FILLY_STATION_REWARD = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue interacting with the filly reward station.", FILLY_STATION_MACHINE);
			}
			return null;
		}
	};
	
	
	public static final DialogueNode STABLES = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES"));
			if(!wearingFillyCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_NO_COLLAR"));
			} else if(!isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_WRONG_BODY"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTER"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					if(!wearingFillyCollar()) {
						return new Response("Enter", "You cannot see Mistress Natalya without wearing your filly choker!", null);
					} else if(!isPlayerBodyCorrect()) {
						return new Response("Enter", "You cannot see Mistress Natalya without being a busty [style.shemale]!", null);
					}
					return new Response("Enter", "Enter the stables and look for centaur slaves to service...", STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTERED"));
						}
					};
					
				} else {
					return new Response("Enter", "As you are not a slave or qualified filly, you cannot enter the stables...", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLES_INTERIOR = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Stables";
			} else if(index==1) {
				return "Favourites";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Exit", "Exit the stables and head back out into the main warehouse...", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_EXIT"));
					}
				};
				
			}
			if(responseTab==0) {
				if(index==1) {
					if(getSavedSlaves().size()>=10) {
						return new Response("Centaur", "You already have the maximum amount of favourite slaves (10)...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Centaur", "You cannot gain access to your mouth, and so cannot service any slaves...", null);
					}
					return new Response("Centaur", "Search for a centaur to service...", STABLE_SEX) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.MASCULINE;
						}
						@Override
						public void effects() {
							activeSlave = spawnSlave(false);
							activeSlave.setPlayerKnowsName(true);
							UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", activeSlave));
						}
					};
					
				} else if(index==2) {
					if(getSavedSlaves().size()>=10) {
						return new Response("Centauress", "You already have the maximum amount of favourite slaves (10)...", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Centauress", "You cannot gain access to your mouth, and so cannot service any slaves...", null);
					}
					return new Response("Centauress", "Search for a centauress to service...", STABLE_SEX) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE;
						}
						@Override
						public void effects() {
							activeSlave = spawnSlave(true);
							activeSlave.setPlayerKnowsName(true);
							UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", activeSlave));
						}
					};
					
				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==STABLE_SHOWER) {
						return new Response("Shower", "You have just taken a shower!", null);
					}
					return new Response("Shower",
								"Use the stable's shower facilities to clean yourself."
									+ "<br/>[style.italicsGood(Cleans <b>a maximum of "+Units.fluid(500)+"</b> of fluids from all orifices.)]"
									+ "<br/>[style.italicsGood(This will clean <b>only</b> your currently equipped clothing.)]",
								STABLE_SHOWER);
				}
				
			} else if(responseTab==1) {
				List<Response> responses = new ArrayList<>();
				for(GameCharacter slave : getSavedSlaves()) {
					if(!getSlaves().contains(slave)) {
						responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "[npc.Name] is out working at the moment, and as such is unavailable for you to sexually service..."), null));
						
					} else {
						responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "Find [npc.namePos] stall and offer to sexually service [npc.herHim] again..."), STABLE_SEX) {
							@Override
							public void effects() {
								activeSlave = slave;
								UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
								if(activeSlave.equals(getSadistSlave())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER_SADIST", activeSlave));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER_REPEAT", activeSlave));
								}
							}
						});
					}
				}
				for(int i=0; i<responses.size(); i++) {
					if(index-1==i) {
						return responses.get(i);
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLE_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Suck cock",
						UtilText.parse(activeSlave, "Drop down onto your knees and suck [npc.namePos] cock."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(activeSlave, new HashMap<>());
								map.get(activeSlave).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(activeSlave).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), activeSlave, PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Anilingus",
						UtilText.parse(activeSlave, "Drop down onto your knees behind [npc.name] and give [npc.herHim] a rimjob."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), activeSlave, TongueAnus.ANILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("Mounted", UtilText.parse(activeSlave, "You cannot gain access to your anus, and so cannot get mounted by [npc.name]..."), null);
				}
				return new ResponseSex(
						"Mounted",
						UtilText.parse(activeSlave, "Present your [pc.asshole+] to [npc.name] and let [npc.herHim] mount you."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(activeSlave, new HashMap<>());
								map.get(activeSlave).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(activeSlave).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_MOUNTED")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX = new DialogueNode("Finished", "The slave has finished with you...", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(activeSlave, "[npc.Name] has finished with you...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(slavePointsReward));
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX", activeSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave ([style.colourMinorGood(Favourite)])",
						UtilText.parse(activeSlave, "Decide to make a note of [npc.namePos] stall so that you can visit [npc.herHim] again as you head back out into the stables."
								+ "<br/>[style.colourMinorGood(You will be able to meet [npc.name] again!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, false);
					}
				};
				
			} else if(index==2) {
				if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
					return new Response("Leave (Forget)", UtilText.parse(activeSlave, "You cannot forget [npc.name]..."), null);
				}
				return new Response("Leave ([style.colourMinorBad(Forget)])",
						UtilText.parse(activeSlave, "Decide not to ever visit [npc.name] again as you exit [npc.her] stall and head back out into the stables."
								+ "<br/>[style.colourMinorBad(You will not be able to meet [npc.name] again!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, true);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getHourOfDay()>4 && Main.game.getHourOfDay()<22) {
					return new Response("Sleep", UtilText.parse(activeSlave, "You can only sleep with a slave between [style.time(22)] and [style.time(4)]."), null);
					
				} else {
					return new Response("Sleep", UtilText.parse(activeSlave, "Accept [npc.namePos] offer to sleep with [npc.herHim] for the night."), AFTER_STABLE_SEX_SLEEP);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_STABLE_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			List<SexAreaInterface> list = Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				list.remove(SexAreaOrifice.MOUTH);
				list.remove(SexAreaPenetration.TONGUE);
			}
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				list.remove(SexAreaOrifice.ANUS);
			}
			if(list.isEmpty() || Math.random()<0.1f) {
				sleepSexAreaWanted = null;
			} else {
				sleepSexAreaWanted = Util.randomItemFrom(list);
			}
			if(sleepSexAreaWanted==null) {
				Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
			}
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(6*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP", activeSlave));
			if(sleepSexAreaWanted==null) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_NO_SEX", activeSlave));
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_BLOWJOB", activeSlave));
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_ANILINGUS", activeSlave));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_MOUNTED", activeSlave));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(sleepSexAreaWanted==null) {
				if(index==1) {
					return new Response("Leave ([style.colourMinorGood(Favourite)])",
							UtilText.parse(activeSlave, "Decide to make a note of [npc.namePos] stall so that you can visit [npc.herHim] again as you head back out into the stables."
									+ "<br/>[style.colourMinorGood(You will be able to meet [npc.name] again!)]"),
							STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", activeSlave));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
							banishSlave(activeSlave, false);
						}
					};
					
				} else if(index==2) {
					if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
						return new Response("Leave (Forget)", UtilText.parse(activeSlave, "You cannot forget [npc.name]..."), null);
					}
					return new Response("Leave ([style.colourMinorBad(Forget)])",
							UtilText.parse(activeSlave, "Decide not to ever visit [npc.name] again as you exit [npc.her] stall and head back out into the stables."
									+ "<br/>[style.colourMinorBad(You will not be able to meet [npc.name] again!)]"),
							STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", activeSlave));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
							banishSlave(activeSlave, true);
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				if(index==1) {
					return new ResponseSex(
							"Suck cock",
							UtilText.parse(activeSlave, "You have little choice but to suck on [npc.namePos] fat, feral horse-cock."),
							true,
							false,
							new SMDominionExpress(SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_BLOWJOB", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				if(index==1) {
					return new ResponseSex(
							"Anilingus",
							UtilText.parse(activeSlave, "You have little choice but to give [npc.name] a rimjob."),
							true,
							false,
							new SMDominionExpress(SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotLyingDown.FACE_SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_ANILINGUS", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"Fucked",
							UtilText.parse(activeSlave, "You have little choice but to let [npc.name] pound your asshole."),
							true,
							false,
							new SMDominionExpress(SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_MOUNTED", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX = new DialogueNode("Finished", "The slave has finished with you...", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(activeSlave, "[npc.Name] has finished with you...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getForeplayPreference(activeSlave, Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_BLOWJOB", activeSlave));
				
			} else if(Main.sex.getForeplayPreference(activeSlave, Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_ANILINGUS", activeSlave));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_MOUNTED", activeSlave));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_FINISH", activeSlave));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave ([style.colourMinorGood(Favourite)])",
						UtilText.parse(activeSlave, "Decide to make a note of [npc.namePos] stall so that you can visit [npc.herHim] again as you head back out into the stables."
								+ "<br/>[style.colourMinorGood(You will be able to meet [npc.name] again!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, false);
					}
				};
				
			} else if(index==2) {
				if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
					return new Response("Leave (Forget)", UtilText.parse(activeSlave, "You cannot forget [npc.name]..."), null);
				}
				return new Response("Leave ([style.colourMinorBad(Forget)])",
						UtilText.parse(activeSlave, "Decide not to ever visit [npc.name] again as you exit [npc.her] stall and head back out into the stables."
								+ "<br/>[style.colourMinorBad(You will not be able to meet [npc.name] again!)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STABLE_SHOWER = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(false));
			Main.game.getPlayer().calculateStatusEffects(0);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(false, true));
			Main.game.getPlayer().cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 10 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SHOWER");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return STABLES_INTERIOR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STABLES_INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isAnalContentEnabled() && !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					return new Response("Enter",
							"You get the feeling that you don't want anything to do with Natalya..."
									+ "<br/>[style.italicsMinorBad(Natalya's scenes involve anal content, and as such will be disabled for as long as your 'Anal Content' setting is turned off.)]",
							null);
				}
				
				return new Response("Enter",
						"Knock on the door to Natalya's office and step inside."
						+ (!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
							?"<br/>[style.italicsQuestRomance(This will start Natalya's romance quest!)]"
							:""),
						OFFICE_STABLE_ENTRY) {
					@Override
					public Colour getHighlightColour() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
							return PresetColour.QUEST_RELATIONSHIP;
						}
						return super.getHighlightColour();
					}
				};
			}
			return null;
		}
	};
	
	private static boolean isOfficeEntryDenied() {
		return (Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && (!isPlayerBodyCorrect() || !wearingFillyCollar()))
				|| Main.game.getPlayer().isPregnant()
				|| Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)
				|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaBusy);
	}
	
	public static final DialogueNode OFFICE_STABLE_ENTRY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!isOfficeEntryDenied()) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_NATALYA));
				}
				if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
					Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD);
					Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD);
					Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD_STAMPED), false);
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(isOfficeEntryDenied()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_DENIED");
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY"));

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW"));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_REPEAT"));
				if(!isPlayerBodyCorrect()) { // If entering after interview has started, that means the player passed & signed the contact, and as such requires Natalya's preferred body:
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WRONG_BODY"));
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_2"));
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_3", getSadistSlave()));
					
				} else { // Qualified filly:
					if(getPlayerCollar().getColour(0)!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_UPGRADED"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WITH_COLLAR"));
					}
				}
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isOfficeEntryDenied()) {
				if(index==1) {
					return new Response("Leave", "As you're not allowed to see Natalya in your current state, there's nothing left for you to do except leave...", OFFICE_STABLE_EXIT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_DENIED_ENTRY"));
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				if(index==1) {
					return new Response("Interview", "Accept Natalya's offer of an interview.", OFFICE_STABLE_INTERVIEW_1) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewOffered, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED));
						}
					};
					
				} else if(index==2) {
					return new Response("Refuse", "Refuse to be interviewed by Natalya.<br/>[style.italicsTerrible(This will fail Natalya's romance quest!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_TERRIBLE;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
							Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
							Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_0"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
						}
					};
				}
				
			} else {
				if(!isPlayerBodyCorrect()) {
					if(index==1) {
						return new Response("Drink potion", "Drink the potion and be transformed into [style.a_shemale].", OFFICE_STABLE_ENTRY_TRANSFORMED){
							@Override
							public Colour getHighlightColour() {
								return PresetColour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								String transformationText = applyTransformation();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START"));
								Main.game.getTextStartStringBuilder().append(transformationText);
								
								if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-25));
								}
							}
						};
					}
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
						if(index==1) {
							return new Response("Kneel", "Do as Mistress Natalya says and [pc.step] over to kneel down beside her...", OFFICE_STABLE_TRAINING_2);
						}
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
						if(index==1) {
							return new Response("Kneel", "Do as Mistress Natalya says and [pc.step] over to kneel down beside her...", OFFICE_STABLE_TRAINING_3);
						}
							
					} else { // Qualified filly:
						AbstractClothing collar = getPlayerCollar();
						
						if(index==0) {
							return new Response("Leave", "Tell Natalya that you'll get back to your duties and leave...", OFFICE_STABLE_EXIT_NO_CONTENT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_FILLY_LEAVE"));
									Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
								}
							};
							
						// Silver:
						} else if(index==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("Suck cock", "You've already serviced Natalya today, and as such she has no more time for you...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Suck cock",
										"You do not have a high enough filly ranking for this!<br/>[style.italics(Requires a ranking of at least 'silver', and you are only '"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return new Response("Suck cock", "As you cannot gain access to your mouth, you cannot suck Mistress Natalya's cock!", null);
								
							} else {
								return new Response("Suck cock", "Tell Natalya that you'd like to make use of your filly ranking's benefit and suck her cock.", OFFICE_STABLE_FILLY_GIVE_BLOWJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==2) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("Give rimjob", "You've already serviced Natalya today, and as such she has no more time for you...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Give rimjob",
										"You do not have a high enough filly ranking for this!<br/>[style.italics(Requires a ranking of at least 'silver', and you are only '"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return new Response("Give rimjob", "As you cannot gain access to your mouth, you cannot give Mistress Natalya a rimjob!", null);
								
							} else {
								return new Response("Give rimjob", "Tell Natalya that you'd like to make use of your filly ranking's benefit and eat her ass.", OFFICE_STABLE_FILLY_GIVE_RIMJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==3) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("Get mounted", "You've already serviced Natalya today, and as such she has no more time for you...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Get mounted",
										"You do not have a high enough filly ranking for this!<br/>[style.italics(Requires a ranking of at least 'silver', and you are only '"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
								return new Response("Get mounted", "As you cannot gain access to your asshole, Natalya cannot mount you!", null);
								
							} else {
								return new Response("Get mounted", "Tell Natalya that you'd like to make use of your filly ranking's benefit and get mounted by her.", OFFICE_STABLE_FILLY_GET_MOUNTED) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						// Gold:
						} else if(index==6) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("Receive blowjob", "You've already dominated Mistress Natalya today, and as such she will not let you do it again until tomorrow...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Receive blowjob",
										"You do not have a high enough filly ranking for this!<br/>[style.italics(Requires a ranking of at least 'gold', and you are only '"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return new Response("Receive blowjob", "As you cannot gain access to your penis, you cannot get Mistress Natalya to suck your cock!", null);
								
							} else {
								return new Response("Receive blowjob", "Order Mistress Natalya to drop down onto her knees and suck your cock.", OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==7) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("Receive rimjob", "You've already dominated Mistress Natalya today, and as such she will not let you do it again until tomorrow...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Receive rimjob",
										"You do not have a high enough filly ranking for this!<br/>[style.italics(Requires a ranking of at least 'gold', and you are only '"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
								return new Response("Receive rimjob", "As you cannot gain access to your asshole, you cannot get Mistress Natalya to give you a rimjob!", null);
								
							} else {
								return new Response("Receive rimjob", "Order Mistress Natalya to drop down onto her knees and eat your ass.", OFFICE_STABLE_FILLY_RECEIVE_RIMJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==8) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("Mount her", "You've already dominated Mistress Natalya today, and as such she will not let you do it again until tomorrow...", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("Mount her",
										"You do not have a high enough filly ranking for this!<br/>[style.italics(Requires a ranking of at least 'gold', and you are only '"+collar.getColour(0).getName()+"'...)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return new Response("Mount her", "As you cannot gain access to your penis, you cannot mount Natalya!", null);
								
							} else {
								return new Response("Mount her", "Order Mistress Natalya to present you with her asshole so that you can mount her and start anally rutting her.", OFFICE_STABLE_FILLY_MOUNT_HER) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
						}
						
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_ENTRY_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START_TRAINING_2_END");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START_TRAINING_3_END");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Strip & kneel", "Do as Natalya commands and strip naked before kneeling down before her.", OFFICE_STABLE_INTERVIEW_2) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to kneel before Natalya.<br/>[style.italicsTerrible(This will fail Natalya's romance quest!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_1"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_2 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAnyEquippedClothingSealed()) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_STRIP_SEALED"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_STRIP"));
			}
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_END"));
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Lift skirt", "Do as Natalya commands and lift her skirt...", OFFICE_STABLE_INTERVIEW_3) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to lift Natalya's skirt.<br/>[style.italicsTerrible(This will fail Natalya's romance quest!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_2"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_3 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_3");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Reluctant kiss",
						"Hesitantly kiss Natalya's asshole and then get started on sucking her cock.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_RELUCTANT_KISS")
						+ UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"Eager kiss",
						"Eagerly tongue Natalya's asshole and then get started on sucking her cock.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_EAGER_KISS")
						+ UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3) {
				return new Response("Refuse", "Refuse to kiss Natalya's asshole.<br/>[style.italicsTerrible(This will fail Natalya's romance quest!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_3"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_FULL_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_AFTER_SEX = new DialogueNode("Finished", "Natalya is satisfied with your performance.", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Clean cock", "Clean Natalya's cock while she prepares your contract.", OFFICE_STABLE_CONTRACT_OFFERED) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).replaceAllClothing();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_OFFERED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED"));
			if(isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_NO_TF"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_TF"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sign", "Do as Natalya says and sign the contract.", OFFICE_STABLE_CONTRACT_SIGNED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_3_TRAINING_1));
					}
				};
				
			} else if(index==2) {
				return new Response("Refuse", "Refuse to sign the contract.<br/>[style.italicsTerrible(This will fail Natalya's romance quest!)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_CONTRACT);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_REFUSED"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_SIGNED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isPlayerBodyCorrect()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_NO_TF");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isPlayerBodyCorrect()) {
				return OFFICE_STABLE_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("Drink potion", "Drink the potion and transform into [style.a_shemale] filly.", OFFICE_STABLE_TRANSFORMED){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							String transformationText = applyTransformation();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_TRANSFORMED"));
							Main.game.getTextStartStringBuilder().append(transformationText);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRANSFORMED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Centaur", "Tell Natalya that you'd prefer to service a centaur.", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
					@Override
					public void effects() {
						applySadistSlave(spawnSlave(false, PresetColour.CLOTHING_BRONZE));
					}
				};
				
			} else if(index==2) {
				return new Response("Centauress", "Tell Natalya that you'd prefer to service a centauress.", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
					@Override
					public void effects() {
						applySadistSlave(spawnSlave(true, PresetColour.CLOTHING_BRONZE));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reluctant kiss", UtilText.parse(getSadistSlave(), "Hesitantly kiss [npc.namePos] asshole."), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_RELUCTANT_KISS", getSadistSlave()));
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.TWO_NEUTRAL, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Eager kiss", UtilText.parse(getSadistSlave(), "Eagerly kiss [npc.namePos] asshole."), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_EAGER_KISS", getSadistSlave()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE, true));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_1_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Lick cock",
						UtilText.parse(getSadistSlave(), "Do as Mistress Natalya commands and drop down to lick [npc.namePos] feral horse-cock."),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_1_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_SEX_SUCK_COCK")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), getSadistSlave(), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE, true));
						}
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE, true));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1_AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlave(getSadistSlave(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_4_TRAINING_2));
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getSadistSlave(), "[npc.Name] has finished with you, and slides [npc.her] cock out from your abused throat.");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Obey", UtilText.parse(getSadistSlave(), "Tell Mistress Natalya that you're a slutty filly who loves horse-ass and horse-cock, before following [npc.name] out of the office..."), OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2"));
			if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_LIFT_SKIRT"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToWearMakeup()) {
				if(index==1) {
					return new Response("Matte dark blue", "Choose the matte dark blue lipstick.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_DARK_BLUE"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.MATTE, PresetColour.COVERING_BLUE_DARK, false, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
					
				} else if(index==2) {
					return new Response("Sparkly gold", "Choose the sparkly gold lipstick.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_SPARKLY_GOLD"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.SPARKLY, PresetColour.COVERING_GOLD, false, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
					
				} else if(index==3) {
					return new Response("Glowing pink", "Choose the glowing pink lipstick.", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_GLOWING_PINK"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.GLOSSY, PresetColour.COVERING_PINK, true, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
				}
				return null;
				
			} else {
				return OFFICE_STABLE_TRAINING_2_MAKEUP.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_LIFT_SKIRT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reluctant kisses", "Hesitantly start kissing Natalya's asshole.", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_RELUCTANT_KISSES", getSadistSlave()));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						}
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ORAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Eager kisses", "Eagerly start kissing Natalya's asshole.", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_EAGER_KISSES", getSadistSlave()));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						}
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ORAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Eager anilingus",
						"Start using your tongue to perform anilingus on Natalya.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_2_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_AFTER_SEX = new DialogueNode("Finished", "Mistress Natalya is satisfied with your performance...", true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_5_TRAINING_3));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Natalya has no more time for you today, so you'll have to return tomorrow to continue your training.", OFFICE_STABLE_EXIT_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(UtilText.parse(getSadistSlave(), "[npc.Name]"),
						UtilText.parse(getSadistSlave(), "Mistress Natalya hurries off to fetch [npc.name], so that you can perform anilingus on [npc.herHim] while she fucks your ass."),
						OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY) {
					@Override
					public void effects() {
						getSadistSlave().setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Reluctantly beg", "Hesitantly tell Natalya that you want her to rut you.", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY", getSadistSlave()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY_END", getSadistSlave()));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("Eagerly beg", "Eagerly beg for Mistress Natalya to start rutting you.", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_EAGERLY_OBEY", getSadistSlave()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY_END", getSadistSlave()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						}
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_FUCKED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Push back",
						UtilText.parse(getSadistSlave(), "Push back and penetrate yourself on Natalya's cock while you perform anilingus on [npc.name]..."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND),
										new Value<>(getSadistSlave(), SexSlotAllFours.IN_FRONT_ANAL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
										new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.PRECUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_FUCKED_START", getSadistSlave())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getSadistSlave(), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX = new DialogueNode("Finished", "Mistress Natalya has had her fun, and slides her cock out of your well-rutted ass...", true) {
		@Override
		public void applyPreParsingEffects() {
			getSadistSlave().fillCumToMaxStorage();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Round two",
						UtilText.parse(getSadistSlave(), "Present your ass to [npc.name] so that [npc.she] can mount and anally rut you..."),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								
								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA, LubricationType.PRECUM, LubricationType.CUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_ROUND_TWO", getSadistSlave())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getSadistSlave(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND = new DialogueNode("Finished", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlave(getSadistSlave(), false);
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getSadistSlave(), "Having contributed to the ruined, cum-stuffed state of your asshole, [npc.name] steps back and slides [npc.her] cock out from your ass.");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(index==1) {
					return new Response("Filly choker", "Let Natalya strap your new choker around your neck...", OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(generateCollar(), true, Main.game.getNpc(Natalya.class)));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(10));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString("", true);
			if(Main.game.getPlayer().getHoldingClothing().values().stream().anyMatch(c->c.getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar"))) {
				UtilText.addSpecialParsingString(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_AMBER_SPECIAL"), true);
			}
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Do as Natalya says and exit her office...", OFFICE_STABLE_TRAINING_3_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_LEAVE"));
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						Main.game.getPlayer().equipAllClothingFromHoldingInventory(Util.newArrayListOfValues(InventorySlot.NECK));
						if(Main.game.getPlayer().getHoldingClothing().containsKey(InventorySlot.NECK)) {
							Main.game.getPlayer().addClothing(Main.game.getPlayer().getHoldingClothing().get(InventorySlot.NECK), false);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_EXIT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GIVE_BLOWJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Kiss asshole",
						"Kiss Natalya's asshole and then get started on sucking her cock.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GIVE_RIMJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_RIMJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Anilingus",
						"Kiss Natalya's asshole and then start using your tongue to perform anilingus on her.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_RIMJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GET_MOUNTED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GET_MOUNTED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Kiss asshole",
						"Kiss Natalya's asshole and then present yourself to be mounted by her.",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GET_MOUNTED_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Dominate her",
						"Dominate Mistress Natalya by thrusting your cock down her throat.",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_FILLY_RECEIVE_RIMJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_RIMJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Dominate her",
						"Dominate Mistress Natalya by pushing your hips back and smothering her face with your [pc.asshole+].",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_RIMJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_MOUNT_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"Dominate her",
						"Dominate Mistress Natalya by thrusting your [pc.cock+] into her [natalya.asshole+].",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB = new DialogueNode("Finished", "Mistress Natalya is satisfied, and so brings an end to your fun...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_RIMJOB");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_MOUNTED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM = new DialogueNode("Finished", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_RIMJOB");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_MOUNTED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
}
