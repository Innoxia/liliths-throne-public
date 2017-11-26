package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.universal.SMDomStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public class Amber extends NPC {

	private static final long serialVersionUID = 1L;

	public Amber() {
		this(false);
	}
	
	private Amber(boolean isImported) {
		super(new NameTriplet("Amber"),
				"The highest-ranking of Zaranix's maids, Amber is clearly outraged by the fact that you're wandering around her master's house unsupervised.",
				12, Gender.F_P_V_B_FUTANARI, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);

		if(!isImported) {
			this.setPlayerKnowsName(false);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHeight(180);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, Colour.EYE_AMBER, true, Colour.EYE_AMBER, true));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, Colour.COVERING_AMBER, true, Colour.COVERING_AMBER, true), true);
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
			this.setHairStyle(HairStyle.SIDECUT);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_EBONY), true);
			
			this.setBreastSize(CupSize.DD.getMeasurement());
			
			this.setHornType(HornType.SWEPT_BACK);
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DEFLOWERING);

			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.FIRE));
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_RED, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_UNDERBUST_CORSET, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		}
	}
	
	@Override
	public Amber loadFromXML(Element parentElement, Document doc) {
		Amber npc = new Amber(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}

	@Override
	public String getName() {
		if(!playerKnowsName) {
			return "Fiery Maid";
			
		} else {
			return "Amber";
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#FFB38A";
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects) {
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getCombatDescription() {
		return "The maid's fiery amber hair and eyes both glow with an intense fury as she fiercely launches her attack!";
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		if (attackType == Attack.MAIN) {
			return "<p>"
						+ "The maid strikes out at you!"
					+ "</p>";
		} else {
			return "<p>"
						+ "The maid lets out an angry shout as she casts a spell!"
					+ "</p>";
		}

	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY);
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! You are victorious. You can use the maid (who, still resisting, demands you address her as Amber), or leave her and carry on."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false));
				
			} if(index==2) {
				return new ResponseSex("Use Amber", "Amber commands and lift your ass towards her.",
						false, false, Main.game.getAmber(), new SMDomStanding(), AFTER_SEX_VICTORY);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Continue", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Continue through the house."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeated", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! You are defeated. The maid pushes you down onto all fours. Introduces herself as Amber."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Amber commands you to lift your ass towards her.",
						true, true, Main.game.getAmber(), new SMAmberDoggyFucked(), AFTER_SEX_DEFEAT,
						"<p>"
							+ "You obediently lift your ass towards Amber, letting out a little cry as you suddenly feel the sharp slap of her hand across your right cheek, before she growls out,"
							+ " [amber.speech(Squeal all you want bitch, <i>you're mine now!</i>)]"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Amber throws you out."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Amber throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	@Override
	public Attack attackType() {
		if (Math.random() < 0.6
				|| this.getManaPercentage() <= 0.4f
				|| (Main.game.getPlayer().getStatusEffects().contains(StatusEffect.DAZED)
						&& this.getStatusEffects().contains(StatusEffect.ARCANE_SHIELD))) {
			return Attack.MAIN;
		} else {
			return Attack.SPELL;
		}
	}

	@Override
	public Spell getSpell() {
		if (!Main.game.getPlayer().getStatusEffects().contains(StatusEffect.DAZED)) {
			return Spell.SLAM_1;
		} else {
			return Spell.ARCANE_SHIELD;
		}
	}
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public int getExperienceFromVictory() {
		return 50;
	}
	
	public int getLootMoney() {
		return 100;
	}

	// Sex:
	
	public SexType getForeplayPreference() {
		if(Sex.getSexManager().getPosition() == SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return new SexType(PenetrationType.FINGER_PARTNER, OrificeType.VAGINA_PLAYER);
			} else {
				return new SexType(PenetrationType.FINGER_PARTNER, OrificeType.ANUS_PLAYER);
			}
		}
		
		return super.getForeplayPreference();
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexManager().getPosition() == SexPosition.DOGGY_PLAYER_ON_ALL_FOURS) {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER);
			} else {
				return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER);
			}
		}

		return super.getMainSexPreference();
	}
	
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
}