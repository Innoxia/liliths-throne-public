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
import com.base.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
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
public class HarpyNympho extends NPC {

	private static final long serialVersionUID = 1L;

	public HarpyNympho() {
		super(new NameTriplet("Lexi"),
				"One of the more notable harpy matriarchs, Lexi is the leader of a flock of harpies."
						+ " Either due to her obsession with sex, or perhaps because she's not as cruel as a typical harpy, Lexi is far more accepting of males than a typical matriarch."
						+ " As a result, her flock serves as her personal harem; it's members constantly trying to sate their matriarch's never-ending lust.",
				7, Gender.FEMALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, HarpyNests.HARPY_NEST_PINK, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

		this.addFetish(Fetish.FETISH_CUM_ADDICT);
		this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
		this.addFetish(Fetish.FETISH_ORAL_GIVING);
		this.addFetish(Fetish.FETISH_BREASTS_SELF);
		this.addFetish(Fetish.FETISH_EXHIBITIONIST);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_GREEN));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.FEATHERS_PINK));
		this.setCovering(new Covering(BodyCoveringType.FEATHERS, Colour.FEATHERS_WHITE));
		this.setCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE));
		
		this.setFemininity(95);
		
		this.setVaginaVirgin(false);
		this.setVaginaWetness(Wetness.FOUR_SLIMY.getValue());
		this.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue());
		
		this.setAssVirgin(false);
		this.setFaceVirgin(false);
		this.setBreastSize(CupSize.C.getMeasurement());
		
		this.setHeight(164);
		
		this.setPiercedEar(true);

		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_PANTIES, Colour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.ANKLE_BRACELET, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
		
		this.equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().lightTheme) {
			return "#D60AB8";
			
		} else {
			return "#F967E3";
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

	// Combat:
	
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Attack attackType() {
		if(!getSpecialAttacks().isEmpty()) {
			if (Math.random() < 0.1) {
				return Attack.MAIN;
			} else if (Math.random() < 0.8) {
				return Attack.SEDUCTION;
			} else {
				return Attack.SPECIAL_ATTACK;
			}
			
		} else {
			if (Math.random() < 0.2) {
				return Attack.MAIN;
			} else {
				return Attack.SEDUCTION;
			}
		}
	}
	
	@Override
	public String getCombatDescription() {
		return UtilText.parse(this,
				"After watching you defeat [nymphoHarpyCompanion.name], [nymphoHarpy.name] rushes forwards, determined to teach you a lesson in front of her flock.");
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
			return new Response("", "", HarpyNestNympho.HARPY_NEST_NYMPHO_FIGHT_BEAT_NYMPHO) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().nymphoPacified = true;
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(ItemType.generateItem(ItemType.HARPY_MARTRIARCH_NYMPHO_LOLLIPOP), false));
				}
				@Override
				public QuestLine getQuestLine() {
					return QuestLine.SIDE_HARPY_PACIFICATION;//TODO test
				}
			};
		} else {
			return new Response("", "", HarpyNestNympho.HARPY_NEST_NYMPHO_FIGHT_LOSE_TO_MATRIARCH);
		}
	}

}