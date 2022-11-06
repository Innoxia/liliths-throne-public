package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Anus;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Clitoris;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.4.0
 * @author Innoxia
 */
public enum LegConfiguration {
	
	/**
	 * This LegConfiguration is the standard for humans, demons, angels, and the vast majority of animal-morphs.
	 */
	BIPEDAL("bipedal",
			0,
			0,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false,
			2,
			"The most common type of lower body; the character's legs and groin are in the same configuration as that of a regular human.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL,
			"") {
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL,
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues();
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			return null; // Bipedal configuration doesn't block any slots by default.
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			character.setLegType(LegType.DEMON_COMMON);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	},
	
	/**
	 * This LegConfiguration is available for almost every mammalian race, with some notable exceptions being humans, demons, and angels.
	 */
	QUADRUPEDAL("quadrupedal",
			-50,
			0,
			false,
			false,
			true,
			true,
			WingSize.FOUR_HUGE,
			true,
			4,
			"A configuration in which the character's legs and groin are replaced by the quadrupedal, feral body of the associated animal-morph, with their genitals shifting to be found in the same place as their animal equivalent."
				+ " The most common example of this is the 'centaur', in which the character's legs and groin are replaced by the body and genitals of a horse.",
			"Down beneath the groin of [npc.her] feral body,",
			TFModifier.TF_MOD_LEG_CONFIG_TAUR,
			"statusEffects/race/raceBackgroundLegQuadrupedal") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL,
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], only clothing suitable for quadrupedal-taurs or quadrupedal-ferals can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_TAUR_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_QUADRUPED_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_QUADRUPED_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.LEG,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only taur-suitable clothing can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
											ItemTag.FITS_TAUR_BODY)));
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_HORSE_HOOFED);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is available for snakes and eels.
	 */
	TAIL_LONG("serpent-tailed",
			0,
			0,
			true,
			true,
			false,
			false,
			WingSize.FOUR_HUGE,
			false,
			0,
			"A configuration in which the character's legs and groin are replaced by an extremely long tail of the associated animal-morph, with their genitals shifting to be located within a cloaca."
				+ " The most common example of this is the 'lamia', in which the character's legs and groin are replaced by the body and genitals of a snake.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG,
			"statusEffects/race/raceBackgroundLegTailLong") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND); // Shouldn't ever spawn by default, but give player the option
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], only clothing suitable for long-tails or long-tail-ferals can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_LONG_TAIL_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_LONG_TAIL_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_LONG_TAIL_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN),
								character.getLegType().getRace(),
								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only long-tail-suitable clothing can be worn in this slot.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_LONG_TAIL_BODY)));
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_SNAKE);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public boolean isAbleToGrowTail() {
			return false;
		}
		@Override
		public boolean isThighSexAvailable() {
			return false;
		}
		@Override
		public String getMovementVerbPresentFirstPersonSingular() {
			return "slither";
		}
		@Override
		public String getMovementVerbPresentThirdPersonSingular() {
			return "slithers";
		}
		@Override
		public String getMovementVerbPresentParticiple() {
			return "slithering";
		}
		@Override
		public String getMovementVerbPastParticiple() {
			return "slithered";
		}
		@Override
		public String getIndividualMovementVerbPresentFirstPersonSingular() {
			return "slide";
		}
		@Override
		public String getIndividualMovementVerbPresentThirdPersonSingular() {
			return "slides";
		}
		@Override
		public String getIndividualMovementVerbPresentParticiple() {
			return "sliding";
		}
		@Override
		public String getIndividualMovementVerbPastParticiple() {
			return "slid";
		}
	},

	/**
	 * This LegConfiguration is available for fish.
	 */
	TAIL("mer-tailed",
			25,
			-95,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false, 
			0,
			"A configuration in which the character's legs and groin are replaced by a tail of the associated animal-morph, with their genitals shifting to be located within a cloaca."
					+ " The most common example of this is the 'mermaid', in which the character's legs and groin are replaced by the body and genitals of a fish.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_TAIL,
			"statusEffects/race/raceBackgroundLegTailShort") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND); // Shouldn't ever spawn by default, but give player the option
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) { // Tail races will never be feral, but just in case...
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], only clothing suitable for tails or tail-ferals can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_TAIL_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_TAIL_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_TAIL_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else if(character.hasStatusEffect(StatusEffect.AQUATIC_TAIL_POSITIVE)) {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN),
								character.getLegType().getRace(),
								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only tail-suitable clothing can be worn in this slot.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_TAIL_BODY)));
				
			} else {
				return null; // When in bipedal configuration, doesn't block any slots.
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_FISH);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public boolean isAbleToGrowTail() {
			return false;
		}
		@Override
		public boolean isThighSexAvailable() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is available for spiders and scorpions.
	 */
	ARACHNID("arachnid",
			-25,
			100,
			false,
			true,
			true,
			true,
			WingSize.FOUR_HUGE,
			true,
			8,
			"A configuration in which the character's legs and groin are replaced by the eight-legged, feral body of the associated arachnid-morph, with their genitals shifting to be found in the same place as their animal equivalent."
					+ " The most common example of this is the 'arachne', in which the character's legs and groin are replaced by the body and genitals of a spider.",
			"Occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_ARACHNID,
			"statusEffects/race/raceBackgroundLegArachnid") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<FootStructure> getPermittedFootStructuresOverride() {
			return Util.newArrayListOfValues(FootStructure.ARACHNOID);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], only clothing suitable for arachnids or arachnid-ferals can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_ARACHNID_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_ARACHNID_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_ARACHNID_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN,
		//								InventorySlot.ANKLE,
										InventorySlot.FOOT,
										InventorySlot.SOCK),
								character.getLegType().getRace(),
								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only arachnid-suitable clothing can be worn in this slot.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_ARACHNID_BODY)));
			}
		}
		@Override
		public boolean isGenitalsExposed(GameCharacter character) { // As genitals are beneath the arachnid body, they are not easily visible.
			return false;
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_SPIDER);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public boolean isThighSexAvailable() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is available for octopuses and squids.<br/>
	 * <br/>
	 * <i>Below the thunders of the upper deep;<br/>
	 * Far far beneath in the abysmal sea,<br/>
	 * His ancient, dreamless, uninvaded sleep<br/>
	 * The Kraken sleepeth: faintest sunlights flee<br/>
	 * About his shadowy sides; above him swell<br/>
	 * Huge sponges of millennial growth and height;<br/>
	 * And far away into the sickly light,<br/>
	 * From many a wondrous grot and secret cell<br/>
	 * Unnumber'd and enormous polypi<br/>
	 * Winnow with giant arms the slumbering green.<br/>
	 * There hath he lain for ages, and will lie<br/>
	 * Battening upon huge seaworms in his sleep,<br/>
	 * Until the latter fire shall heat the deep;<br/>
	 * Then once by man and angels to be seen,<br/>
	 * In roaring he shall rise and on the surface die.</i>
	 */
	CEPHALOPOD("cephalopod",
			50,
			-75,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false,
			8,
			// I believe that "tentacled" is technically incorrect as a catch-all term for cephalopods, as octopuses have eight 'arms', while squids have eight arms plus two tentacles. Oh well.
			"A configuration in which the character's legs and groin are replaced by the tentacled, feral body of the associated cephalopod-morph, with their genitals shifting to be found in the same place as their animal equivalent."
					+ " The most common example of this is the 'kraken', in which the character's legs and groin are replaced by the body and genitals of a squid.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD,
			"statusEffects/race/raceBackgroundLegCephalopod") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], only clothing suitable for cephalopods or cephalopod-ferals can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_CEPHALOPOD_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_CEPHALOPOD_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_CEPHALOPOD_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN,
		//								InventorySlot.ANKLE,
										InventorySlot.FOOT,
										InventorySlot.SOCK),
								character.getLegType().getRace(),
								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only cephalopod-suitable clothing can be worn in this slot.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_CEPHALOPOD_BODY)));
			}
		}
		
		@Override
		public boolean isGenitalsExposed(GameCharacter character) { // Genitals are under tentacles, so are not visible even when naked.
			return false;
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_OCTOPUS);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public String getMovementVerbPresentFirstPersonSingular() {
			return "crawl";
		}
		@Override
		public String getMovementVerbPresentThirdPersonSingular() {
			return "crawls";
		}
		@Override
		public String getMovementVerbPresentParticiple() {
			return "crawling";
		}
		@Override
		public String getMovementVerbPastParticiple() {
			return "crawled";
		}
		@Override
		public String getIndividualMovementVerbPresentFirstPersonSingular() {
			return "slide";
		}
		@Override
		public String getIndividualMovementVerbPresentThirdPersonSingular() {
			return "slides";
		}
		@Override
		public String getIndividualMovementVerbPresentParticiple() {
			return "sliding";
		}
		@Override
		public String getIndividualMovementVerbPastParticiple() {
			return "slid";
		}
	},
	

	/**
	 * This LegConfiguration is a 'tauric' configuration for bird races.
	 */
	AVIAN("avian",
			0,
			0,
			false,
			true,
			true,
			true,
			WingSize.THREE_LARGE,
			true,
			2,
			"A configuration in which the character's legs and groin are replaced by the avian body of the associated animal-morph, with their genitals shifting to be found in a rear-facing cloaca."
					+ " The most common example of this is the 'harpy-moa', in which a regular harpy's legs and groin are replaced by the feral body and genitals of a bird.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_AVIAN,
			"statusEffects/race/raceBackgroundLegAvian") {
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], only clothing suitable for avians or avian-ferals can be worn in this slot.",
									Util.newArrayListOfValues(
											ItemTag.FITS_CEPHALOPOD_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_CEPHALOPOD_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_CEPHALOPOD_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN),
								character.getLegType().getRace(),
								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only avian-suitable clothing can be worn in this slot.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_AVIAN_BODY))
//						new BodyPartClothingBlock(
//								Util.newArrayListOfValues(
//										InventorySlot.FOOT,
//										InventorySlot.SOCK),
//								character.getLegType().getRace(),
//								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only avian-suitable clothing can be worn in this slot.",
//								Util.newArrayListOfValues(
//										ItemTag.FITS_TALONS_EXCLUSIVE,
//										ItemTag.FITS_TALONS))
						);
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_EAGLE);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is a configuration for feral biped-ish races with wings instead of forelegs.<br/>
	 * <b>This should only ever be used for ferals!</b>
	 */
	WINGED_BIPED("winged-biped",
			0,
			0,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false,
			2,
			"A configuration in which the character's legs and groin are replaced by the body of the associated animal-morph, while their arm-wings are used in place of forelegs."
					+ " The most common examples of this are feral wyverns and feral bats, which both have wings instead of arms, and which use these arm-wings to walk on all fours.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_WINGED_BIPED,
			"statusEffects/race/raceBackgroundLegAvian") {
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL,
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Due to the fact that [npc.nameHasFull] the feral body of [npc.a_legRace], [npc.she] cannot wield regular weapons!",
									Util.newArrayListOfValues(
											ItemTag.FITS_ARM_WINGS,
											ItemTag.FITS_ARM_WINGS_EXCLUSIVE)));
				
			} else {
				return null; // This is a feral only leg configuration.
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			character.setLegType(LegType.DEMON_COMMON);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	};

	private String name;
	private int landSpeedModifier;
	private int waterSpeedModifier;
	private boolean bipedalPositionedGenitals;
	private boolean bipedalPositionedCrotchBoobs;
	private boolean largeGenitals;
	private boolean tall;
	
	private WingSize minimumWingSizeForFlight;
	private boolean wingsOnLegConfiguration;
	
	private int numberOfLegs;
	
	private String genericDescription;
	private String crotchBoobLocationDescription;

	private TFModifier tfModifier;

	private String subspeciesStatusEffectBackgroundPath;
	
	private LegConfiguration(
			String name,
			int landSpeedModifier,
			int waterSpeedModifier,
			boolean bipedalPositionedGenitals,
			boolean bipedalPositionedCrotchBoobs,
			boolean largeGenitals,
			boolean tall,
			WingSize minimumWingSizeForFlight,
			boolean wingsOnLegConfiguration,
			int numberOfLegs,
			String genericDescription,
			String crotchBoobLocationDescription,
			TFModifier tfModifier,
			String subspeciesStatusEffectBackgroundPath) {
		
		this.name = name;
		
		this.landSpeedModifier = landSpeedModifier;
		this.waterSpeedModifier = waterSpeedModifier;
		
		this.bipedalPositionedGenitals = bipedalPositionedGenitals;
		this.bipedalPositionedCrotchBoobs = bipedalPositionedCrotchBoobs;
		this.largeGenitals = largeGenitals;
		this.tall = tall;
		
		this.minimumWingSizeForFlight=minimumWingSizeForFlight;
		this.wingsOnLegConfiguration=wingsOnLegConfiguration;
		
		this.numberOfLegs = numberOfLegs;
		
		this.genericDescription = genericDescription;
		this.crotchBoobLocationDescription = crotchBoobLocationDescription;

		this.tfModifier = tfModifier;
		
		this.subspeciesStatusEffectBackgroundPath = subspeciesStatusEffectBackgroundPath;
	}

	public static LegConfiguration getValueFromString(String configuration) {
		if(configuration.equalsIgnoreCase("TAUR")) {
			configuration = "QUADRUPEDAL";
		}
		return LegConfiguration.valueOf(configuration);
	}
	
	/**
	 * @return A list of BodyPartInterface classes which are considered to be fully animalistic as part of this LegConfiguration. e.g. A taur's Tail, Ass, BreastCrotch, Penis, and Vagina are all animalistic.
	 */
	public abstract List<Class<? extends BodyPartInterface>> getFeralParts();
	
	/**
	 * @return true if this LegConfiguration removes the character's tail when applying its transformation.
	 */
	public abstract boolean isTailLostOnInitialTF();
	
	/**
	 * @return true if this LegConfiguration prevents the character from growing a tail.
	 */
	public boolean isAbleToGrowTail() {
		return true;
	}
	
	public String getName() {
		return name;
	}

	public String getMovementVerbPresentFirstPersonSingular() {
		return "walk";
	}

	public String getMovementVerbPresentThirdPersonSingular() {
		return "walks";
	}

	public String getMovementVerbPresentParticiple() {
		return "walking";
	}

	public String getMovementVerbPastParticiple() {
		return "walked";
	}

	public String getIndividualMovementVerbPresentFirstPersonSingular() {
		return "step";
	}

	public String getIndividualMovementVerbPresentThirdPersonSingular() {
		return "steps";
	}

	public String getIndividualMovementVerbPresentParticiple() {
		return "stepping";
	}

	public String getIndividualMovementVerbPastParticiple() {
		return "stepped";
	}
	
	public int getLandSpeedModifier() {
		return landSpeedModifier;
	}

	public int getWaterSpeedModifier() {
		return waterSpeedModifier;
	}

	/**
	 * @param body The corresponding body.
	 * @return The minimum WingSize required for flight.
	 */
	public WingSize getMinimumWingSizeForFlight(Body body) {
		return body.isFeral() ? WingSize.THREE_LARGE : minimumWingSizeForFlight;
	}

	public boolean isWingsOnLegConfiguration() {
		return wingsOnLegConfiguration;
	}

	/**
	 * @return true If this leg configuration has genitals in roughly the same place as on a biped.
	 */
	public boolean isBipedalPositionedGenitals() {
		return bipedalPositionedGenitals;
	}

	/**
	 * @return true If this leg configuration has crotch-boobs in roughly the same place as on a biped.
	 */
	public boolean isBipedalPositionedCrotchBoobs() {
		return bipedalPositionedCrotchBoobs;
	}

	public boolean isLargeGenitals() {
		return largeGenitals;
	}

	/**
	 * @return true if this configuration is classed as being 'tall' (such as quadrupeds).
	 */
	public boolean isTall() {
		return tall;
	}

	public boolean isThighSexAvailable() {
		return true;
	}
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}

	public List<FootStructure> getPermittedFootStructuresOverride() {
		return new ArrayList<>();
	}
	
	public boolean isGenitalsExposed(GameCharacter character) {
		return true;
	}

	public abstract List<GenitalArrangement> getAvailableGenitalConfigurations();

	public String getGenericDescription() {
		return genericDescription;
	}

	public String getCrotchBoobLocationDescription() {
		return crotchBoobLocationDescription;
	}

	public TFModifier getTFModifier() {
		return tfModifier;
	}
	
	public void setLegsToDemon(GameCharacter character) {
		throw new IllegalArgumentException("Demon legs for this leg configuration is not yet implemented!");
	}

	public void setLegsToAvailableDemonLegs(GameCharacter character, AbstractLegType legType) {
		this.setLegsToAvailableDemonLegs(character, legType, LegType.DEMON_COMMON);
	}

	public void setLegsToAvailableDemonLegs(GameCharacter character, AbstractLegType legType, AbstractLegType fallBackLegType) {
		character.setLegType(legType.isAvailableForSelfTransformMenu(character) ? legType : fallBackLegType);
	}

	public void setWingsToDemon(GameCharacter character) {
		character.setWingType(WingType.DEMON_COMMON);
		character.setWingSize(this.minimumWingSizeForFlight.getValue());
	}

	/**
	 * @return A list of BodyPartClothingBlock objects which define how this LegConfiguration is blocking InventorySlots. Returns null if it doesn't affect inventorySlots in any way.
	 */
	public abstract List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character);

	public String getSubspeciesStatusEffectBackgroundPath() {
		return subspeciesStatusEffectBackgroundPath;
	}
	
	/**
	 * @return How many times longer a character's serpent tail is than their height.
	 */
	public static int getDefaultSerpentTailLengthMultiplier() {
		return 5;
	}
}
