package com.base.game.character.npc.dominion;

import com.base.game.character.NameTriplet;
import com.base.game.character.QuestLine;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.character.effects.Fetish;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.places.dominion.harpyNests.HarpyNestDominant;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.item.ItemType;
import com.base.game.sex.SexPace;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.WorldType;
import com.base.world.places.HarpyNests;

/**
 * @since 0.1.8
 * @version 0.1.8
 * @author Innoxia
 */
public class HarpyDominant extends NPC {

	private static final long serialVersionUID = 1L;

	public HarpyDominant() {
		super(new NameTriplet("Diana"),
				"One of the more notable harpy matriarchs, Diana is the leader of a flock of harpies."
						+ " As cruel as harpies come, Diana rules her flock with an iron fist, harshly punishing any harpies that try to challenge her dominance.",
				7, Gender.FEMALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, HarpyNests.HARPY_NEST_RED, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.addFetish(Fetish.FETISH_DOMINANT);
		this.addFetish(Fetish.FETISH_SADIST);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_BROWN));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.FEATHERS_BLACK));
		this.setCovering(new Covering(BodyCoveringType.FEATHERS, Colour.FEATHERS_RED));
		this.setCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_EBONY));
		
		this.setFemininity(95);
		
		this.setVaginaVirgin(false);
		this.setVaginaWetness(Wetness.THREE_WET.getValue());
		this.setVaginaCapacity(Capacity.TWO_TIGHT.getMedianValue());
		
		this.setAssVirgin(true);
		this.setFaceVirgin(false);
		this.setBreastSize(CupSize.B.getMeasurement());
		
		this.setHeight(170);
		
		this.setPiercedEar(true);
		this.setPiercedLip(true);

		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.TORSO_PLUNGE_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.PIERCING_LIP_RINGS, Colour.CLOTHING_SILVER, false), true, this);
	}

	@Override
	public String getSpeechColour() {
		if(Main.getProperties().lightTheme) {
			return "#C13350";
			
		} else {
			return "#D7567D";
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public void applyReset() {
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
		if (applyEffects)
			applyReset();
	}
	
	@Override
	public SexPace getSexPaceSubPreference(){
		return SexPace.SUB_EAGER;
	}

	// Combat
	
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Attack attackType() {
		if(!getSpecialAttacks().isEmpty()) {
			if (Math.random() < 0.7) {
				return Attack.MAIN;
			} else if (Math.random() < 0.8) {
				return Attack.SEDUCTION;
			} else {
				return Attack.SPECIAL_ATTACK;
			}
			
		} else {
			if (Math.random() < 0.8) {
				return Attack.MAIN;
			} else {
				return Attack.SEDUCTION;
			}
		}
	}

	@Override
	public String getCombatDescription() {
		return UtilText.parse(this,
				"After watching you defeat [dominantHarpyCompanion.name], [dominantHarpy.name] rushes forwards, determined to teach you a lesson in front of her flock.");
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {

		if (attackType == Attack.MAIN) {
			switch (Util.random.nextInt(3)) {
			case 0:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] feints a punch, and as you dodge away, [npc.she] tries to deliver a kick aimed at your legs."
							+ (isHit ? "" : " You see [npc.her] kick coming and jump backwards out of harm's way.")
						+ "</p>");
			case 1:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] jumps forwards, trying to deliver a punch to your stomach."
							+ (isHit ? "" : " You manage to twist to one side, narrowly avoiding [npc.her] attack.")
						+ "</p>");
			default:
				return UtilText.parse(this,
						"<p>"
							+ "[npc.Name] darts forwards, throwing a punch at your torso."
							+ (isHit ? "" : " You manage to dodge [npc.her] attack by leaping to one side.")
						+ "</p>");
			}
		} else {
			if(isFeminine()) {
				switch (Util.random.nextInt(3)) {
					case 0:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] erotically runs [npc.her] hands down [npc.her] legs and bends forwards as [npc.she] teases you, "
									+ "[npc.speech(Come on baby, I can show you a good time!)]"
								+ "</p>");
					case 1:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] pushes out [npc.her] chest and lets out an erotic moan, "
									+ "[npc.speech(Come play with me!)]"
								+ "</p>");
					default:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] slowly runs [npc.her] hands down between [npc.her] thighs, "
									+ "[npc.speech(You know you want it!)]"
								+ "</p>");
				}
			} else {
				switch (Util.random.nextInt(3)) {
					case 0:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] winks at you and flexes [npc.his] muscles, "
									+ "[npc.speech(My body's aching for your touch!)]"
								+ "</p>");
					case 1:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] strikes a heroic pose before blowing a kiss your way, "
									+ "[npc.speech(Come on, I can show you a good time!)]"
								+ "</p>");
					default:
						return UtilText.parse(this,
								"<p>"
									+ "[npc.Name] grins at you as [npc.he] reaches down and grabs [npc.his] crotch, "
									+ "[npc.speech(You know you want a taste of this!)]"
								+ "</p>");
				}

			}
		}
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", HarpyNestDominant.HARPY_NEST_DOMINANT_FIGHT_BEAT_DOMINANT) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().dominantPacified = true;
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(ItemType.generateItem(ItemType.HARPY_MARTRIARCH_DOMINANT_PERFUME), false));
				}
				@Override
				public QuestLine getQuestLine() {
					return QuestLine.SIDE_HARPY_PACIFICATION;//TODO test
				}
			};
		} else {
			return new Response("", "", HarpyNestDominant.HARPY_NEST_DOMINANT_FIGHT_LOSE_TO_MATRIARCH);
		}
	}

}