package com.lilithsthrone.game.occupantManagement;

import com.lilithsthrone.game.Season;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Max Nobody
 */
public class HuntingContract
{	
	public HuntingContract() {
		// Determine the level of the contract. Preference for mid-level contracts. Probabilities might need skewing
		// Level affect which criterias are possible, which are certain, probability of non-certain criterias, 
		// and precision of criterias.
		double levelRoll = Math.random();
		if (levelRoll < 0.05f) {
			this.level = 1; // Easiest. Partial furry if non-human.
		}
		else if (levelRoll < 0.1f) {
			this.level = 2; // Add possibility of Sexual orientation. Minor furry minimum if non-human.
		}
		else if (levelRoll < 0.18f) {
			this.level = 3; // Add possibility of Obedience. Minor furry minimum if non-human.
		}
		else if (levelRoll < 0.32f) {
			this.level = 4; // Add possibility of Anus Capacity. Lesser furry minimum if non-human.
		}
		else if (levelRoll < 0.50f) {
			this.level = 5; // Vaginal capacity/Penis Size are now a certain criteria. Add possibility of fetishes. Lesser furry minimum if non-human.
		}
		else if (levelRoll < 0.68f) {
			this.level = 6; // Cup Size is now a certain criteria. Add possibility of Height. Greater furry if non-human.
		}
		else if (levelRoll < 0.82f) {
			this.level = 7; // Anus Capacity is now a certain criteria. Greater furry if non-human.
		}
		else if (levelRoll < 0.9f) {
			this.level = 8; // Only increases non-certain criteria probability and precision. Greater furry if non-human.
		}
		else if (levelRoll < 0.95f) {
			this.level = 9; // Obedience, Height, Part color, Body Shape, Fetishes, are now a certain criteria. Add possibility of a derived fetish. Greater furry if non-human.
		}
		else {
			this.level = 10; // Only increases non-certain criteria probability and precision. Greater furry if non-human.
		}
		this.valueMultiplier = 1.5f;
		int simulateValue = 0; // No rolled value is actually added, a temp value is needed to store the rolled value.
		switch (this.level) {
		case 1:
			this.ObedienceMin = -200;
			this.ObedienceMax = -200;
			this.HeightMin = -200;
			this.HeightMax = -200;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.28f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.35f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			if (Math.random() <= 0.6f) {
				this.valueMultiplier += 0.8f;
				if (this.gender.getGenderName().isHasBreasts()) {
					simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
					this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 8));
					this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 8));
				}
				else {
					this.cupSizeMin = CupSize.FLAT;
					this.cupSizeMax = CupSize.FLAT;
				}
			} else {
				this.cupSizeMin = null;
				this.cupSizeMax = null;
			}
			if (this.gender.getGenderName().isHasVagina() && Math.random() <= 0.5f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 5);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 5);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis() && Math.random() <= 0.5f) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 10);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 10);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			this.anusMax = -200;
			this.anusMin = -200;
			this.fetishes = null;
			this.derivedFetish = null;
			if (Math.random() <= 0.22f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.02f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.02f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			this.sexualOrientation = null;
			break;
		case 2:
			this.ObedienceMin = -200;
			this.ObedienceMax = -200;
			this.HeightMin = -200;
			this.HeightMax = -200;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.44f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.40f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			if (Math.random() <= 0.8f) {
				this.valueMultiplier += 0.8f;
				if (this.gender.getGenderName().isHasBreasts()) {
					simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
					this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 8));
					this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 8));
				}
				else {
					this.cupSizeMin = CupSize.FLAT;
					this.cupSizeMax = CupSize.FLAT;
				}
			} else {
				this.cupSizeMin = null;
				this.cupSizeMax = null;
			}
			if (this.gender.getGenderName().isHasVagina() && Math.random() <= 0.7f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 5);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 5);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis() && Math.random() <= 0.7f) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 10);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 10);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			this.anusMax = -200;
			this.anusMin = -200;
			this.fetishes = null;
			this.derivedFetish = null;
			if (Math.random() <= 0.26f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.04f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.04f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.08f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 3:
			if (Math.random() <= 0.4f) {
				simulateValue = (int)Math.floor(Math.random() * 200) - 100;
				this.ObedienceMax = Math.min(simulateValue + 40, 100);
				this.ObedienceMin = Math.max(simulateValue - 40, -100);
				this.valueMultiplier += 0.3f;
			} else {
				this.ObedienceMin = -200;
				this.ObedienceMax = -200;
			}
			this.HeightMin = -200;
			this.HeightMax = -200;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.58f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.45f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			if (Math.random() <= 0.85f) {
				this.valueMultiplier += 0.8f;
				if (this.gender.getGenderName().isHasBreasts()) {
					simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25 + 3));
					this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 7));
					this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 7));
				}
				else {
					this.cupSizeMin = CupSize.FLAT;
					this.cupSizeMax = CupSize.FLAT;
				}
			} else {
				this.cupSizeMin = null;
				this.cupSizeMax = null;
			}
			if (this.gender.getGenderName().isHasVagina() && Math.random() <= 0.8f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 5);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 5);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis() && Math.random() <= 0.8f) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 10);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 10);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			this.anusMax = -200;
			this.anusMin = -200;
			this.fetishes = null;
			this.derivedFetish = null;
			if (Math.random() <= 0.3f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.06f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.06f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.17f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 4:
			if (Math.random() <= 0.5f) {
				simulateValue = (int)Math.floor(Math.random() * 200) - 100;
				this.ObedienceMax = Math.min(simulateValue + 30, 100);
				this.ObedienceMin = Math.max(simulateValue - 30, -100);
				this.valueMultiplier += 0.3f;
			} else {
				this.ObedienceMin = -200;
				this.ObedienceMax = -200;
			}
			this.HeightMin = -200;
			this.HeightMax = -200;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.7f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.5f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			if (Math.random() <= 0.90f) {
				this.valueMultiplier += 0.8f;
				if (this.gender.getGenderName().isHasBreasts()) {
					simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
					this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 6));
					this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 6));
				}
				else {
					this.cupSizeMin = CupSize.FLAT;
					this.cupSizeMax = CupSize.FLAT;
				}
			} else {
				this.cupSizeMin = null;
				this.cupSizeMax = null;
			}
			if (this.gender.getGenderName().isHasVagina() && Math.random() <= 0.9f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 4);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 4);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis() && Math.random() <= 0.9f) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 9);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 9);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			if (Math.random() <= 0.3f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 12);
				this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 12);
				this.valueMultiplier += 0.5f;
			} else {
				this.anusMax = -200;
				this.anusMin = -200;
			}
			this.fetishes = null;
			this.derivedFetish = null;
			if (Math.random() <= 0.34f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.08f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.08f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.26f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 5:
			if (Math.random() <= 0.60f) {
				simulateValue = (int)Math.floor(Math.random() * 200) - 100;
				this.ObedienceMax = Math.min(simulateValue + 25, 100);
				this.ObedienceMin = Math.max(simulateValue - 25, -100);
				this.valueMultiplier += 0.3f;
			} else {
				this.ObedienceMin = -200;
				this.ObedienceMax = -200;
			}
			this.HeightMin = -200;
			this.HeightMax = -200;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.8f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.6f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			if (Math.random() <= 0.95f) {
				this.valueMultiplier += 0.8f;
				if (this.gender.getGenderName().isHasBreasts()) {
					simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
					this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 5));
					this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 5));
				}
				else {
					this.cupSizeMin = CupSize.FLAT;
					this.cupSizeMax = CupSize.FLAT;
				}
			} else {
				this.cupSizeMin = null;
				this.cupSizeMax = null;
			}
			if (this.gender.getGenderName().isHasVagina()) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 4);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 4);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis()) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 8);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 8);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			if (Math.random() <= 0.6f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 12);
				this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 12);
				this.valueMultiplier += 0.5f;
			} else {
				this.anusMax = -200;
				this.anusMin = -200;
			}
			if (Math.random() <= 0.1f) {
				this.fetishes = new ArrayList<Fetish>();
				this.fetishes.add(randomNormalFetish());
			} else {
				this.fetishes = null;
			}
			this.derivedFetish = null;
			if (Math.random() <= 0.38f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.10f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.10f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.35f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 6:
			if (Math.random() <= 0.7f) {
				simulateValue = (int)Math.floor(Math.random() * 200) - 100;
				this.ObedienceMax = Math.min(simulateValue + 18, 100);
				this.ObedienceMin = Math.max(simulateValue - 18, -100);
				this.valueMultiplier += 0.3f;
			} else {
				this.ObedienceMin = -200;
				this.ObedienceMax = -200;
			}
			if (Math.random() <= 0.2f) {
				this.HeightMax = (int)Math.floor(Math.random() * 20) + 168; // TODO : Rework size to take race into account
				this.HeightMin = this.HeightMax - 16;
				this.valueMultiplier += 0.3f;
			}
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.88f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.7f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			this.valueMultiplier += 0.8f;
			if (this.gender.getGenderName().isHasBreasts()) {
				simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
				this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 4));
				this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 4));
			}
			else {
				this.cupSizeMin = CupSize.FLAT;
				this.cupSizeMax = CupSize.FLAT;
			}
			if (this.gender.getGenderName().isHasVagina()) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 3);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 3);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis()) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 7);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 7);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			if (Math.random() <= 0.8f) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 10);
				this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 10);
				this.valueMultiplier += 0.5f;
			} else {
				this.anusMax = -200;
				this.anusMin = -200;
			}
			if (Math.random() <= 0.25f) {
				this.fetishes = new ArrayList<Fetish>();
				this.fetishes.add(randomNormalFetish());
			} else {
				this.fetishes = null;
			}
			this.derivedFetish = null;
			if (Math.random() <= 0.42f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.12f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.12f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.44f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 7:
			if (Math.random() <= 0.8f) {
				simulateValue = (int)Math.floor(Math.random() * 200) - 100;
				this.ObedienceMax = Math.min(simulateValue + 14, 100);
				this.ObedienceMin = Math.max(simulateValue - 14, -100);
				this.valueMultiplier += 0.3f;
			} else {
				this.ObedienceMin = -200;
				this.ObedienceMax = -200;
			}
			if (Math.random() <= 0.6f) {
				this.HeightMax = (int)Math.floor(Math.random() * 20) + 168; // TODO : Rework size to take race into account
				this.HeightMin = this.HeightMax - 16;
				this.valueMultiplier += 0.3f;
			}
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.94f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.8f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			this.valueMultiplier += 0.8f;
			if (this.gender.getGenderName().isHasBreasts()) {
				simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
				this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 3));
				this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 3));
			}
			else {
				this.cupSizeMin = CupSize.FLAT;
				this.cupSizeMax = CupSize.FLAT;
			}
			if (this.gender.getGenderName().isHasVagina()) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 3);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 3);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis()) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 6);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 6);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
			this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 8);
			this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 8);
			this.valueMultiplier += 0.5f;
			if (Math.random() <= 0.5f) {
				this.fetishes = new ArrayList<Fetish>();
				this.fetishes.add(randomNormalFetish());
				if (Math.random() <= 0.3f) {
					this.fetishes.add(randomNormalFetish());
					while (fetishes.get(0) == fetishes.get(1)) {
						this.fetishes.remove(1);
						this.fetishes.add(randomNormalFetish());
					}
				}
			} else {
				this.fetishes = null;
			}
			this.derivedFetish = null;
			if (Math.random() <= 0.46f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.14f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.14f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.53f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 8:
			if (Math.random() <= 0.9f) {
				simulateValue = (int)Math.floor(Math.random() * 200) - 100;
				this.ObedienceMax = Math.min(simulateValue + 12, 100);
				this.ObedienceMin = Math.max(simulateValue - 12, -100);
				this.valueMultiplier += 0.3f;
			} else {
				this.ObedienceMin = -200;
				this.ObedienceMax = -200;
			}
			if (Math.random() <= 0.8f) {
				this.HeightMax = (int)Math.floor(Math.random() * 20) + 166; // TODO : Rework size to take race into account
				this.HeightMin = this.HeightMax - 12;
				this.valueMultiplier += 0.3f;
			}
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			if (Math.random() <= 0.98f) {
				this.skinColour = randomSkinFromRace(this.race);
				this.EyeColour = randomEyeColourFromRace(this.race);
				this.HairColour = randomHairColourFromRace(this.race);
				this.valueMultiplier += 0.1f;
			} else {
				this.skinColour = null;
				this.HairColour = null;
				this.EyeColour = null;
			}
			if (Math.random() <= 0.9f) {
				this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
				this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
				this.valueMultiplier += 0.5f;
			} else {
				this.bodySize = null;
				this.muscle = null;
			}
			this.valueMultiplier += 0.8f;
			if (this.gender.getGenderName().isHasBreasts()) {
				simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
				this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 2));
				this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 2));
			}
			else {
				this.cupSizeMin = CupSize.FLAT;
				this.cupSizeMax = CupSize.FLAT;
			}
			if (this.gender.getGenderName().isHasVagina()) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 2);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 2);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis()) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 5);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 5);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
			this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 6);
			this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 6);
			this.valueMultiplier += 0.5f;
			if (Math.random() <= 0.75f) {
				this.fetishes = new ArrayList<Fetish>();
				this.fetishes.add(randomNormalFetish());
				this.fetishes.add(randomNormalFetish());
				while (fetishes.get(1) == fetishes.get(0)) {
					this.fetishes.remove(1);
					this.fetishes.add(randomNormalFetish());
				}
				if (Math.random() <= 0.4f) {
					this.fetishes.add(randomNormalFetish());
					while (fetishes.get(2) == fetishes.get(1) || fetishes.get(2) == fetishes.get(0)) {
						this.fetishes.remove(2);
						this.fetishes.add(randomNormalFetish());
					}
				}
			} else {
				this.fetishes = null;
			}
			this.derivedFetish = null;
			if (Math.random() <= 0.5f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.16f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.16f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.62f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 9:
			simulateValue = (int)Math.floor(Math.random() * 200) - 100;
			this.ObedienceMax = Math.min(simulateValue + 10, 100);
			this.ObedienceMin = Math.max(simulateValue - 10, -100);
			this.valueMultiplier += 0.3f;
			this.HeightMax = (int)Math.floor(Math.random() * 20) + 166; // TODO : Rework size to take race into account
			this.HeightMin = this.HeightMax - 12;
			this.valueMultiplier += 0.3f;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			this.skinColour = randomSkinFromRace(this.race);
			this.EyeColour = randomEyeColourFromRace(this.race);
			this.HairColour = randomHairColourFromRace(this.race);
			this.valueMultiplier += 0.1f;
			this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
			this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
			this.valueMultiplier += 0.5f;
			this.valueMultiplier += 0.8f;
			if (this.gender.getGenderName().isHasBreasts()) {
				simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
				this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue + 1));
				this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue - 1));
			}
			else {
				this.cupSizeMin = CupSize.FLAT;
				this.cupSizeMax = CupSize.FLAT;
			}
			if (this.gender.getGenderName().isHasVagina()) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 2);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 2);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis()) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 4);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 4);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
			this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 4);
			this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 4);
			this.valueMultiplier += 0.5f;
			this.fetishes = new ArrayList<Fetish>();
			this.fetishes.add(randomNormalFetish());
			this.fetishes.add(randomNormalFetish());
			while (fetishes.get(1) == fetishes.get(0)) {
				this.fetishes.remove(1);
				this.fetishes.add(randomNormalFetish());
			}
			if (Math.random() <= 0.4f) {
				this.fetishes.add(randomNormalFetish());
				while (fetishes.get(2) == fetishes.get(1) || fetishes.get(2) == fetishes.get(0)) {
					this.fetishes.remove(2);
					this.fetishes.add(randomNormalFetish());
				}
			}
			if (Math.random() <= 0.3f) {
				simulateValue = (int)Math.floor(Math.random() * 4);
				switch(simulateValue) {
				case 0:
					this.derivedFetish = Fetish.FETISH_LUSTY_MAIDEN;
					break;
				case 1:
					this.derivedFetish = Fetish.FETISH_SADOMASOCHIST;
					break;
				case 2:
					this.derivedFetish = Fetish.FETISH_BREEDER;
					break;
				case 3:
					this.derivedFetish = Fetish.FETISH_SWITCH;
					break;
				}
				this.valueMultiplier += 1;
			} else {
				this.derivedFetish = null;
			}
			if (Math.random() <= 0.55f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.18f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.18f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.71f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		case 10:
			simulateValue = (int)Math.floor(Math.random() * 200) - 100;
			this.ObedienceMax = Math.min(simulateValue + 5, 100);
			this.ObedienceMin = Math.max(simulateValue - 5, -100);
			this.valueMultiplier += 0.3f;
			this.HeightMax = (int)Math.floor(Math.random() * 20) + 163; // TODO : Rework size to take race into account
			this.HeightMin = this.HeightMax - 6;
			this.valueMultiplier += 0.3f;
			this.gender = Gender.getGenderFromUserPreferences(false, false);
			this.race = randomRace(this.gender);
			this.skinColour = randomSkinFromRace(this.race);
			this.EyeColour = randomEyeColourFromRace(this.race);
			this.HairColour = randomHairColourFromRace(this.race);
			this.valueMultiplier += 0.1f;
			this.bodySize = BodySize.valueOf((int)Math.floor(Math.random() * 100));
			this.muscle = Muscle.valueOf((int)Math.floor(Math.random() * 100));
			this.valueMultiplier += 0.5f;
			this.valueMultiplier += 0.8f;
			if (this.gender.getGenderName().isHasBreasts()) {
				simulateValue = (int)Math.max(CupSize.AA.getMeasurement(), Math.floor(Math.random() * 25) + 3);
				this.cupSizeMax = CupSize.getCupSizeFromInt((int)Math.min(CupSize.XXX_N.getMeasurement(), simulateValue));
				this.cupSizeMin = CupSize.getCupSizeFromInt((int)Math.max(CupSize.AA.getMeasurement(), simulateValue));
			}
			else {
				this.cupSizeMin = CupSize.FLAT;
				this.cupSizeMax = CupSize.FLAT;
			}
			if (this.gender.getGenderName().isHasVagina()) {
				simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				this.pussyMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 1);
				this.pussyMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 1);
				this.valueMultiplier += 0.5f;
			} else {
				this.pussyMin = -200;
				this.pussyMax = -200;
			}
			if (this.gender.getGenderName().isHasPenis()) {
				simulateValue = (int)Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), Math.floor(Math.random() * 20));
				this.penisMax = Math.min(PenisSize.SEVEN_STALLION.getMaximumValue(), simulateValue + 2);
				this.penisMin = Math.max(PenisSize.ZERO_MICROSCOPIC.getMinimumValue(), simulateValue - 2);
				this.valueMultiplier += 0.5f;
			} else {
				this.penisMax = -200;
				this.penisMin = -200;
			}
			simulateValue = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.FIVE_ROOMY.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
			this.anusMax = Math.min(Capacity.SEVEN_GAPING.getMaximumValue(), simulateValue + 2);
			this.anusMin = Math.max(Capacity.ZERO_IMPENETRABLE.getMinimumValue(), simulateValue - 2);
			this.valueMultiplier += 0.5f;
			this.fetishes = new ArrayList<Fetish>();
			this.fetishes.add(randomNormalFetish());
			this.fetishes.add(randomNormalFetish());
			while (fetishes.get(1) == fetishes.get(0)) {
				this.fetishes.remove(1);
				this.fetishes.add(randomNormalFetish());
			}
			this.fetishes.add(randomNormalFetish());
			while (fetishes.get(2) == fetishes.get(1) || fetishes.get(2) == fetishes.get(0)) {
				this.fetishes.remove(2);
				this.fetishes.add(randomNormalFetish());
			}
			if (Math.random() <= 0.5f) {
				this.fetishes.add(randomNormalFetish());
				while (fetishes.get(3) == fetishes.get(2) || fetishes.get(3) == fetishes.get(1) || fetishes.get(3) == fetishes.get(0)) {
					this.fetishes.remove(3);
					this.fetishes.add(randomNormalFetish());
				}
			}
			if (Math.random() <= 0.4f) {
				simulateValue = (int)Math.floor(Math.random() * 4);
				switch(simulateValue) {
				case 0:
					this.derivedFetish = Fetish.FETISH_LUSTY_MAIDEN;
					break;
				case 1:
					this.derivedFetish = Fetish.FETISH_SADOMASOCHIST;
					break;
				case 2:
					this.derivedFetish = Fetish.FETISH_BREEDER;
					break;
				case 3:
					this.derivedFetish = Fetish.FETISH_SWITCH;
					break;
				}
				this.valueMultiplier += 1;
			} else {
				this.derivedFetish = null;
			}
			if (Math.random() <= 0.60f && this.pussyMin != -200) {
				this.vaginalVirgin = true;
				this.valueMultiplier += 1f;
			} else {
				this.vaginalVirgin = false;
			}
			if (Math.random() <= 0.2f) {
				this.analVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.analVirgin = false;
			}
			if (Math.random() <= 0.2f) {
				this.oralVirgin = true;
				this.valueMultiplier += 2f;
			} else {
				this.oralVirgin = false;
			}
			if (Math.random() <= 0.8f) {
				if (Math.random() <= 0.33f) {
					this.sexualOrientation = SexualOrientation.AMBIPHILIC;
				} else if (Math.random() <= 0.5f) {
					this.sexualOrientation = SexualOrientation.ANDROPHILIC;
				} else {
					this.sexualOrientation = SexualOrientation.GYNEPHILIC;
				}
			} else {
				this.sexualOrientation = null;
			}
			break;
		}
		this.valueMultiplier *= 10;
		this.valueMultiplier = Math.floor(this.valueMultiplier) / 10; // To avoid things like 2.30000000675
	}
	
	private Race randomRace(Gender gender) {
		double humanChance = 0;
		if(Main.getProperties().humanEncountersLevel==1) {
			humanChance = 0.05f;
		} else if(Main.getProperties().humanEncountersLevel==2) {
			humanChance = 0.25f;
		} else if(Main.getProperties().humanEncountersLevel==3) {
			humanChance = 0.5f;	
		} else if(Main.getProperties().humanEncountersLevel==4) {
			humanChance = 0.75f;
		}
		if (Math.random() < humanChance) {
			return Race.HUMAN;
		}
		Map<Subspecies, Integer> availableRaces = new HashMap<>();
		for(Subspecies s : Subspecies.values()) {
			switch(s) {
			case ANGEL:
			case IMP:
			case HUMAN:
			case IMP_ALPHA:
			case FOX_ASCENDANT:
			case FOX_ASCENDANT_FENNEC:
			case ELEMENTAL_AIR:
			case ELEMENTAL_ARCANE:
			case ELEMENTAL_EARTH:
			case ELEMENTAL_FIRE:
			case ELEMENTAL_WATER:
				break;
			case ALLIGATOR_MORPH:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case SLIME:
				addToSubspeciesMap(1, gender, s, availableRaces);
				break;
			case RAT_MORPH:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case BAT_MORPH:
				addToSubspeciesMap(1, gender, s, availableRaces);
				break;
			case HARPY:
				addToSubspeciesMap(4, gender, s, availableRaces);
				break;
			case HARPY_RAVEN:
				addToSubspeciesMap(1, gender, s, availableRaces);
				break;
			case HARPY_BALD_EAGLE:
				addToSubspeciesMap(1, gender, s, availableRaces);
				break;
			case REINDEER_MORPH:
				if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					addToSubspeciesMap(10, gender, s, availableRaces);
				}
				break;
			case CAT_MORPH:
				addToSubspeciesMap(25, gender, s, availableRaces);
				break;
			case DEMON:
				addToSubspeciesMap(10, gender, s, availableRaces);
			case CAT_MORPH_LEOPARD:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case CAT_MORPH_LEOPARD_SNOW:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case CAT_MORPH_LION:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case CAT_MORPH_TIGER:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case CAT_MORPH_LYNX:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case CAT_MORPH_CHEETAH:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case CAT_MORPH_CARACAL:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case COW_MORPH:
				addToSubspeciesMap(15, gender, s, availableRaces);
				break;
			case DOG_MORPH:
				addToSubspeciesMap(15, gender, s, availableRaces);
				break;
			case DOG_MORPH_DOBERMANN:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case DOG_MORPH_BORDER_COLLIE:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case FOX_MORPH:
				addToSubspeciesMap(10, gender, s, availableRaces);
				break;
			case FOX_MORPH_FENNEC:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case HORSE_MORPH:
				addToSubspeciesMap(20, gender, s, availableRaces);
				break;
			case HORSE_MORPH_ZEBRA:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case SQUIRREL_MORPH:
				addToSubspeciesMap(10, gender, s, availableRaces);
				break;
			case WOLF_MORPH:
				addToSubspeciesMap(25, gender, s, availableRaces);
				break;
			case RABBIT_MORPH:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			case RABBIT_MORPH_LOP:
				addToSubspeciesMap(5, gender, s, availableRaces);
				break;
			}
		}
		return (Util.getRandomObjectFromWeightedMap(availableRaces).getRace());
	}
	
	public Fetish randomNormalFetish() {
		int roll = (int)Math.floor(Math.random() * (Fetish.values().length - 4)); // -4 to remove the derived fetishes. When adding a derived fetish, please increment
		int i = 0;
		for (Fetish fetish : Fetish.values()) {
			if (i == roll) {
				return fetish;
			}
			i++;
		}
		return Fetish.FETISH_ANAL_GIVING; 
	}
	
	private Colour randomSkinFromRace(Race race) {
		switch (race) {
		case HUMAN:
			return Colour.humanSkinColours.get((int)Math.floor(Math.random() * Colour.humanSkinColours.size()));
		case DEMON:
			return Colour.demonSkinColours.get((int)Math.floor(Math.random() * Colour.demonSkinColours.size()));
		case COW_MORPH:
		case DOG_MORPH:
		case WOLF_MORPH:
		case FOX_MORPH:
		case CAT_MORPH:
		case HORSE_MORPH:
		case REINDEER_MORPH:
		case SQUIRREL_MORPH:
		case RAT_MORPH:
		case RABBIT_MORPH:
		case BAT_MORPH:
			return Colour.naturalFurColours.get((int)Math.floor(Math.random() * Colour.naturalFurColours.size()));
		case ALLIGATOR_MORPH:
			return Colour.naturalScaleColours.get((int)Math.floor(Math.random() * Colour.naturalScaleColours.size()));
		case SLIME:
			return Colour.naturalSlimeColours.get((int)Math.floor(Math.random() * Colour.naturalSlimeColours.size()));
		case HARPY:
			return Colour.naturalFeatherColours.get((int)Math.floor(Math.random() * Colour.naturalFeatherColours.size()));
		case ELEMENTAL_EARTH:
		case ELEMENTAL_WATER:
		case ELEMENTAL_AIR:
		case ELEMENTAL_FIRE:
		case ELEMENTAL_ARCANE:
		default:
			return null;
		}
	}
	
	private Colour randomHairColourFromRace(Race race) {
		switch (race) {
		case HUMAN:
		case DEMON:
		case COW_MORPH:
		case DOG_MORPH:
		case WOLF_MORPH:
		case FOX_MORPH:
		case CAT_MORPH:
		case HORSE_MORPH:
		case REINDEER_MORPH:
		case SQUIRREL_MORPH:
		case RAT_MORPH:
		case RABBIT_MORPH:
		case BAT_MORPH:
		case HARPY:
			return Colour.allCoveringColours.get((int)Math.floor(Math.random() * Colour.allCoveringColours.size()));
		case SLIME:
			return Colour.naturalSlimeColours.get((int)Math.floor(Math.random() * Colour.naturalSlimeColours.size()));
		case ALLIGATOR_MORPH:
		case ELEMENTAL_EARTH:
		case ELEMENTAL_WATER:
		case ELEMENTAL_AIR:
		case ELEMENTAL_FIRE:
		case ELEMENTAL_ARCANE:
		default:
			return null;
		}
	}
	
	private Colour randomEyeColourFromRace(Race race) {
		switch (race) {
		case HUMAN:
		case COW_MORPH:
		case DOG_MORPH:
		case WOLF_MORPH:
		case FOX_MORPH:
		case CAT_MORPH:
		case HORSE_MORPH:
		case REINDEER_MORPH:
		case SQUIRREL_MORPH:
		case RAT_MORPH:
		case RABBIT_MORPH:
		case BAT_MORPH:
		case HARPY:
		case ALLIGATOR_MORPH:
			return Colour.naturalIrisColours.get((int)Math.floor(Math.random() * Colour.naturalIrisColours.size()));
		case SLIME:
			return Colour.naturalSlimeColours.get((int)Math.floor(Math.random() * Colour.naturalSlimeColours.size()));
		case DEMON:
			return Colour.naturalDemonIrisColours.get((int)Math.floor(Math.random() * Colour.naturalDemonIrisColours.size()));
		case ELEMENTAL_EARTH:
		case ELEMENTAL_WATER:
		case ELEMENTAL_AIR:
		case ELEMENTAL_FIRE:
		case ELEMENTAL_ARCANE:
		default:
			return null;
		}
	}
	
	protected void addToSubspeciesMap(int weight, Gender gender, Subspecies subspecies, Map<Subspecies, Integer> map) {
		if(gender.isFeminine()) {
			if(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
					&& Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().getSubspeciesFemininePreferencesMap().get(subspecies).getValue());
			}
		} else {
			if(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(subspecies)!=FurryPreference.HUMAN
					&& Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue()>0) {
				map.put(subspecies, weight*Main.getProperties().getSubspeciesMasculinePreferencesMap().get(subspecies).getValue());
			}
		}
	}
	
	public boolean isMatchContract(NPC npc) {
		if (this.ObedienceMin != -200 && this.ObedienceMax != -200) {
			if (npc.getObedienceValue() > this.ObedienceMax || npc.getObedienceValue() < this.ObedienceMin) {
				return false;
			}
		}
		if (this.HeightMin != -200 && this.HeightMin != -200) {
			if (npc.getHeightValue() > this.HeightMax || npc.getHeightValue() < this.HeightMin) {
				return false;
			}
		}
		if (this.pussyMin != -200 && this.pussyMax != -200) {
			if (npc.getVaginaRawCapacityValue() > this.pussyMax || npc.getVaginaRawCapacityValue() < this.pussyMin) {
				return false;
			}
		}
		if (this.anusMin != -200 && this.anusMax != -200) {
			if (npc.getAssRawCapacityValue() > this.anusMax || npc.getAssRawCapacityValue() < this.anusMin) {
				return false;
			}
		}
		if (this.penisMin != -200 && this.penisMax != -200) {
			if (npc.getPenisRawSizeValue() > this.penisMax || npc.getPenisRawCapacityValue() < this.penisMin) {
				return false;
			}
		}
		if (this.vaginalVirgin && !npc.isVaginaVirgin()) {
			return false;
		}
		if (this.analVirgin && !npc.isAssVirgin()) {
			return false;
		}
		if (this.oralVirgin && !npc.isFaceVirgin()) {
			return false;
		}
		if (this.race != npc.getRace() 
				|| (this.level >= 2 && npc.getRaceStage() == RaceStage.PARTIAL)
				|| (this.level >= 4 && npc.getRaceStage() == RaceStage.PARTIAL_FULL)
				|| (this.level >= 6 && npc.getRaceStage() == RaceStage.LESSER)) {
			return false;
		}
		if (this.skinColour != null) {
			if (!matchSkin(npc)) {
				return false;
			}
		}
		if (this.EyeColour != null) {
			if (!matchEye(npc)) {
				return false;
			}
		}
		if (this.HairColour != null) {
			if (!matchHair(npc)) {
				return false;
			}
		}
		if (this.bodySize != null && this.muscle != null) {
			if (this.bodySize != npc.getBodySize() || this.muscle != npc.getMuscle()) {
				return false;
			}
		}
		if (this.gender != npc.getGender()) {
			return false;
		}
		if (this.cupSizeMax != null && this.cupSizeMin != null) {
			if (this.cupSizeMax.getMeasurement() < npc.getBreastSize().getMeasurement() || this.cupSizeMin.getMeasurement() > npc.getBreastSize().getMeasurement()) {
				return false;
			}
		}
		if (this.fetishes != null) {
			if (!npc.getFetishes().containsAll(this.fetishes)) {
				return false;
			}
		}
		if (this.derivedFetish != null) {
			if (!npc.getFetishes().contains(this.derivedFetish)) {
				return false;
			}
		}
		if (this.sexualOrientation != null) {
			if (this.sexualOrientation != npc.getSexualOrientation()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean matchSkin(NPC npc) {
		Covering testSkin = npc.getBody().getCoverings().get(npc.getSkinType().getBodyCoveringType(npc.getBody()));
		testSkin.setPrimaryColour(this.skinColour);
		if (testSkin.equals(npc.getBody().getCoverings().get(npc.getSkinType().getBodyCoveringType(npc.getBody())))) {
			return true;
		}
		return false;
	}
	
	private boolean matchEye(NPC npc) {
		Covering testEye = npc.getBody().getCoverings().get(npc.getEyeType().getBodyCoveringType(npc.getBody()));
		testEye.setPrimaryColour(this.EyeColour);
		if (testEye.equals(npc.getBody().getCoverings().get(npc.getEyeType().getBodyCoveringType(npc.getBody())))) {
			return true;
		}
		return false;
	}
	
	private boolean matchHair(NPC npc) {
		Covering testHair = npc.getBody().getCoverings().get(npc.getHairType().getBodyCoveringType(npc.getBody()));
		testHair.setPrimaryColour(this.HairColour);
		if (testHair.equals(npc.getBody().getCoverings().get(npc.getHairType().getBodyCoveringType(npc.getBody())))) {
			return true;
		}
		return false;
	}
	
	public String getDescription(int number){
		UtilText.nodeContentSB.setLength(0);
		int count = 0; 
		UtilText.nodeContentSB.append("<p>"
				+ "The ");
		if (number > 0) {
			UtilText.nodeContentSB.append(Util.intToPosition(number) + " ");
		}
		UtilText.nodeContentSB.append("contract is for a <span style=\"color:"
				+ this.getGender().getColour().toWebHexString()
				+ "\">"
				+ this.getGender().getName()
				+ "</span> (");
		if (this.getGender().getGenderName().isHasVagina()) {
			UtilText.nodeContentSB.append("<span style=\"color:"+Colour.GENERIC_GOOD.toWebHexString()+"\">");
		} else {
			UtilText.nodeContentSB.append("<span style=\"color:"+Colour.GENERIC_BAD.toWebHexString()+"\">");
		}
		UtilText.nodeContentSB.append("Vagina</span> ");
		if (this.getGender().getGenderName().isHasPenis()) {
			UtilText.nodeContentSB.append("<span style=\"color:"+Colour.GENERIC_GOOD.toWebHexString()+"\">");
		} else {
			UtilText.nodeContentSB.append("<span style=\"color:"+Colour.GENERIC_BAD.toWebHexString()+"\">");
		}
		UtilText.nodeContentSB.append("Penis</span> ");
		if (this.getGender().getGenderName().isHasBreasts()) {
			UtilText.nodeContentSB.append("<span style=\"color:"+Colour.GENERIC_GOOD.toWebHexString()+"\">");
		} else {
			UtilText.nodeContentSB.append("<span style=\"color:"+Colour.GENERIC_BAD.toWebHexString()+"\">");
		}
		UtilText.nodeContentSB.append(
				"Breasts</span>)"
				+ " ");
		if (this.getLevel() == 1) {
			UtilText.nodeContentSB.append(RaceStage.PARTIAL.getName());
		} else if (this.getLevel() <= 3) {
			UtilText.nodeContentSB.append(RaceStage.PARTIAL_FULL.getName());
		} else if (this.getLevel() <= 5) {
			UtilText.nodeContentSB.append(RaceStage.LESSER.getName());
		} else {
			UtilText.nodeContentSB.append(RaceStage.GREATER.getName());
		}
		UtilText.nodeContentSB.append(
				" "
				+ this.getRace().getName());
		boolean saidWith = false;
		if (this.getObedienceMax() != -200) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					"an obedience between "
					+ this.getObedienceMin()
					+ " and "
					+ this.getObedienceMax());
		}
		if (this.getHeightMax() != -200) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					"an height between "
					+ this.getHeightMin()
					+ " and "
					+ this.getHeightMax()
					+ " cm");
		}
		if (this.getPussyMax() != -200) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					"a vaginal capacity between "
					+ this.getPussyMin()
					+ " and "
					+ this.getPussyMax()
					+ " inches");
		}
		if (this.getAnusMax() != -200) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					"an anal capacity between "
					+ this.getAnusMin()
					+ " and "
					+ this.getAnusMax()
					+ " inches");
		}
		if (this.getPenisMax() != -200) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					"a penis between "
					+ this.getPenisMin()
					+ " and "
					+ this.getPenisMax()
					+ " inches long");
		}
		if (this.getVaginalVirgin()) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					this.getGender().getPossessiveBeforeNoun()
					+ " vaginal virginity");
		}
		if (this.getAnalVirgin()) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					this.getGender().getPossessiveBeforeNoun()
					+ " anal virginity");
		}
		if (this.getOralVirgin()) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					this.getGender().getPossessiveBeforeNoun()
					+ " oral virginity");
		}
		if (this.getSkinColour() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					this.getSkinColour().getName()
					+ " skin/fur");
		}
		if (this.getEyeColour() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					this.getEyeColour().getName()
					+ " eyes");
		}
		if (this.getHairColour() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					this.getHairColour().getName()
					+ " hair");
		}
		if (this.getMuscle() != null && this.getBodySize() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					BodyShape.valueOf(this.getMuscle(), this.getBodySize()).getName(true)
					+ " (<span style=\"color:"
					+ this.getMuscle().getColour().toWebHexString()
					+ "\">"
					+ this.getMuscle().getName(false)
					+ "</span> <span style=\"color:"
					+ this.getBodySize().getColour().toWebHexString()
					+ "\">"
					+ this.getBodySize().getName(false)
					+ "</span>) body shape");
		}
		if (this.getCupSizeMax() != null && this.getCupSizeMin() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append("breasts between "
					+ this.getCupSizeMin().getCupSizeName()
					+ "-cup and "
					+ this.getCupSizeMax().getCupSizeName()
					+ "-cup");
		}
		if (this.getFetishes() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			count = 0;
			UtilText.nodeContentSB.append("the following fetish");
			if (this.getFetishes().size() != 1) {
				UtilText.nodeContentSB.append("es");
			}
			UtilText.nodeContentSB.append(" :");
			while (count < this.getFetishes().size()) {
				UtilText.nodeContentSB.append(" "
						+ this.getFetishes().get(count).getName(Main.game.getPlayer()));
				if (count != this.getFetishes().size() - 1) {
					UtilText.nodeContentSB.append(",");
				}
				count++;
			}
		}
		if (this.getDerivedFetish() != null) {
			if (!saidWith) {
				saidWith = true;
				UtilText.nodeContentSB.append(" with ");
			} else {
				UtilText.nodeContentSB.append(", ");
			}
			UtilText.nodeContentSB.append(
					"the following derived fetish : "
					+ this.getDerivedFetish().getName(Main.game.getPlayer()));
		}
		UtilText.nodeContentSB.append(".");
		return UtilText.nodeContentSB.toString();
	}
	
	public static void saveAsXML(Document doc, Element parent, HuntingContract contract) {
		int i = 0;
		CharacterUtils.addAttribute(doc, parent, "level", String.valueOf(contract.getLevel()));
		CharacterUtils.addAttribute(doc, parent, "obedienceMin", String.valueOf(contract.getObedienceMin()));
		CharacterUtils.addAttribute(doc, parent, "obedienceMax", String.valueOf(contract.getObedienceMax()));
		CharacterUtils.addAttribute(doc, parent, "heightMin", String.valueOf(contract.getHeightMin()));
		CharacterUtils.addAttribute(doc, parent, "heightMax", String.valueOf(contract.getHeightMax()));
		CharacterUtils.addAttribute(doc, parent, "pussyMin", String.valueOf(contract.getPussyMin()));
		CharacterUtils.addAttribute(doc, parent, "pussyMax", String.valueOf(contract.getPussyMax()));
		CharacterUtils.addAttribute(doc, parent, "anusMin", String.valueOf(contract.getAnusMin()));
    		CharacterUtils.addAttribute(doc, parent, "anusMax", String.valueOf(contract.getAnusMax()));
		CharacterUtils.addAttribute(doc, parent, "penisMin", String.valueOf(contract.getPenisMin()));
		CharacterUtils.addAttribute(doc, parent, "penisMax", String.valueOf(contract.getPenisMin()));
		CharacterUtils.addAttribute(doc, parent, "valueMultiplier", String.valueOf(contract.getValueMultiplier()));
		CharacterUtils.addAttribute(doc, parent, "vaginalVirgin", String.valueOf(contract.getVaginalVirgin()));
		CharacterUtils.addAttribute(doc, parent, "analVirgin", String.valueOf(contract.getAnalVirgin()));
		CharacterUtils.addAttribute(doc, parent, "oralVirgin", String.valueOf(contract.getOralVirgin()));
		CharacterUtils.addAttribute(doc, parent, "race", String.valueOf(contract.getRace()));
		CharacterUtils.addAttribute(doc, parent, "skinColour", String.valueOf(contract.getSkinColour()));
		CharacterUtils.addAttribute(doc, parent, "eyeColour", String.valueOf(contract.getEyeColour()));
		CharacterUtils.addAttribute(doc, parent, "hairColour", String.valueOf(contract.getHairColour()));
		CharacterUtils.addAttribute(doc, parent, "bodySize", String.valueOf(contract.getBodySize()));
		CharacterUtils.addAttribute(doc, parent, "muscle", String.valueOf(contract.getMuscle()));
		CharacterUtils.addAttribute(doc, parent, "gender", String.valueOf(contract.getGender()));
		CharacterUtils.addAttribute(doc, parent, "cupSizeMin", String.valueOf(contract.getCupSizeMin()));
		CharacterUtils.addAttribute(doc, parent, "cupSizeMax", String.valueOf(contract.getCupSizeMax()));
		while (contract.getFetishes() != null && i < contract.getFetishes().size()) {
			CharacterUtils.addAttribute(doc, parent, "fetish"+i, String.valueOf(contract.getFetishes().get(i)));
			i++;
		}
		CharacterUtils.addAttribute(doc, parent, "derivedFetish", String.valueOf(contract.getDerivedFetish()));
		CharacterUtils.addAttribute(doc, parent, "sexualOrientation", String.valueOf(contract.getSexualOrientation()));
	}
	
	// Getters
	
	public int getLevel() {
		return this.level;
	}
	
	public int getObedienceMin() {
		return this.ObedienceMin;
	}
	
	public int getObedienceMax() {
		return this.ObedienceMax;
	}
	
	public int getHeightMin() {
		return this.HeightMin;
	}
	
	public int getHeightMax() {
		return this.HeightMax;
	}
	
	public int getPussyMin() {
		return this.pussyMin;
	}
	
	public int getPussyMax() {
		return this.pussyMax;
	}
	
	public int getAnusMin() {
		return this.anusMin;
	}
	
	public int getAnusMax() {
		return this.anusMax;
	}
	
	public int getPenisMin() {
		return this.penisMin;
	}
	
	public int getPenisMax() {
		return this.penisMax;
	}
	
	public double getValueMultiplier() {
		return this.valueMultiplier;
	}
	
	public boolean getVaginalVirgin() {
		return this.vaginalVirgin;
	}
	
	public boolean getAnalVirgin() {
		return this.analVirgin;
	}
	
	public boolean getOralVirgin() {
		return this.oralVirgin;
	}
	
	public Race getRace() {
		return this.race;
	}
	
	public Colour getSkinColour() {
		return this.skinColour;
	}
	
	public Colour getEyeColour() {
		return this.EyeColour;
	}
	
	public Colour getHairColour() {
		return this.HairColour;
	}
	
	public BodySize getBodySize() {
		return this.bodySize;
	}
	
	public Muscle getMuscle() {
		return this.muscle;
	}
	
	public Gender getGender() {
		return this.gender;
	}
	
	public CupSize getCupSizeMin() {
		return this.cupSizeMin;
	}
	
	public CupSize getCupSizeMax() {
		return this.cupSizeMax;
	}
	
	public List<Fetish> getFetishes() {
		return this.fetishes;
	}
	
	public Fetish getDerivedFetish() {
		return this.derivedFetish;
	}
	
	public SexualOrientation getSexualOrientation() {
		return this.sexualOrientation;
	}
	
	// Setters
	// On normal situations you won't be needing these
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setObedienceMin(int ObedienceMin) {
		this.ObedienceMin = ObedienceMin;
	}
	
	public void setObedienceMax(int ObedienceMax) {
		this.ObedienceMax = ObedienceMax;
	}
	
	public void setHeightMin(int HeightMin) {
		this.HeightMin = HeightMin;
	}
	
	public void setHeightMax(int HeightMax) {
		this.HeightMax = HeightMax;
	}
	
	public void setPussyMin(int pussyMin) {
		this.pussyMin = pussyMin;
	}
	
	public void setPussyMax(int pussyMax) {
		this.pussyMax = pussyMax;
	}
	
	public void setAnusMin(int anusMin) {
		this.anusMin = anusMin;
	}
	
	public void setAnusMax(int anusMax) {
		this.anusMax = anusMax;
	}
	
	public void setPenisMin(int penisMin) {
		this.penisMin = penisMin;
	}
	
	public void setPenisMax(int penisMax) {
		this.penisMax = penisMax;
	}
	
	public void setValueMultiplier(double valueMultiplier) {
		this.valueMultiplier = valueMultiplier;
	}
	
	public void setVaginalVirgin(boolean vaginalVirgin) {
		this.vaginalVirgin = vaginalVirgin;
	}
	
	public void setAnalVirgin(boolean analVirgin) {
		this.analVirgin = analVirgin;
	}
	
	public void setOralVirgin(boolean oralVirgin) {
		this.oralVirgin = oralVirgin;
	}
	
	public void setRace(Race race) {
		this.race = race;
	}
	
	public void setSkinColour(Colour skinColour) {
		this.skinColour = skinColour;
	}
	
	public void setEyeColour(Colour EyeColour) {
		this.EyeColour = EyeColour;
	}
	
	public void setHairColour(Colour HairColour) {
		this.HairColour = HairColour;
	}
	
	public void setBodySize(BodySize bodySize) {
		this.bodySize = bodySize;
	}
	
	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setCupSizeMin(CupSize cupSizeMin) {
		this.cupSizeMin = cupSizeMin;
	}
	
	public void setCupSizeMax(CupSize cupSizeMax) {
		this.cupSizeMax = cupSizeMax;
	}
	
	public void setFetishes(List<Fetish> fetishes) {
		this.fetishes = fetishes;
	}
	
	public void setDerivedFetish(Fetish derivedFetish) {
		this.derivedFetish = derivedFetish;
	}
	
	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}
	
	// Variables
	
	// A value of -200 or null indicates any value is accepted
	private int level; // Note : Level of the contract, not of the target
	private int ObedienceMin;
	private int ObedienceMax;
	private int HeightMin;
	private int HeightMax;
	private int pussyMin;
	private int pussyMax;
	private int anusMin;
	private int anusMax;
	private int penisMin;
	private int penisMax;
	private double valueMultiplier;
	private boolean vaginalVirgin;
	private boolean analVirgin;
	private boolean oralVirgin;
	private Race race; // Note : Contract level determines race stage.
	private Colour skinColour;
	private Colour EyeColour;
	private Colour HairColour;
	private BodySize bodySize;
	private Muscle muscle;
	private Gender gender;
	private CupSize cupSizeMin;
	private CupSize cupSizeMax;
	private List<Fetish> fetishes;
	private Fetish derivedFetish;
	private SexualOrientation sexualOrientation;
}
