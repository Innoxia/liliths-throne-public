package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Anus;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Clitoris;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.3.1
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
			WingSize.THREE_LARGE,
			false,
			2,
			0,
			"The most common type of lower body; the character's legs and groin are in the same configuration as that of a regular human.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL) {
		@Override
		public boolean isGenitalConfigurationTransformable() {
			return true;
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getBestialParts() {
			return Util.newArrayListOfValues();
		}
		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			return null; // Bipedal configuration doesn't block any slots by default.
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			character.setLegType(LegType.DEMON_COMMON);
		}
	},

	/**
	 * This LegConfiguration is available for almost every mammalian race, with some notable exceptions being humans, demons, and angels.
	 */
	TAUR("taur",
			-50,
			0,
			false,
			false,
			WingSize.FOUR_HUGE,
			true,
			4,
			0,
			"A configuration in which the character's legs and groin are replaced by the quadrupedal, bestial body of the associated animal-morph, with their genitals shifting to be found in the same place as their animal equivalent."
				+ " The most common example of this is the 'centaur', in which the character's legs and groin are replaced by the body and genitals of a horse.",
			"Down beneath the groin on [npc.her] animal body,",
			TFModifier.TF_MOD_LEG_CONFIG_TAUR) {

			@Override
			public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
				return new BodyPartClothingBlock(
						Util.newArrayListOfValues(
								InventorySlot.LEG,
								InventorySlot.GROIN),
						character.getLegType().getRace(),
						"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only taur-suitable clothing can be worn in this slot.",
						Util.newArrayListOfValues(
								ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
								ItemTag.FITS_TAUR_BODY));
			}
			@Override
			public void setLegsToDemon(GameCharacter character) {
				character.setLegType(LegType.DEMON_HOOFED);
			}
	},

//	/**
//	 * This LegConfiguration is available for almost every mammalian race, with some notable exceptions being humans, demons, and angels.
//	 */
//	TAUR_WINGED("winged taur",
//			true,
//			true,
//			false,
//			"A configuration in which the character's legs and groin are replaced by the quadrupedal, winged, bestial body of the associated animal-morph, with their genitals shifting to be found in the same place as their animal equivalent."
//				+ " The most common example of this is the 'pegatuar', in which the character's legs and groin are replaced by the body and genitals of a winged horse.",
//			"Down beneath the groin on [npc.her] animal body,") {
//
//		@Override
//		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
//			return new BodyPartClothingBlock(
//					Util.newArrayListOfValues(
//							InventorySlot.LEG,
//							InventorySlot.GROIN),
//					character.getLegType().getRace(),
//					"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only taur-suitable clothing can be worn in this slot.",
//					Util.newArrayListOfValues(
//							ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
//							ItemTag.FITS_TAUR_BODY));
//		}
//	},

	/**
	 * This LegConfiguration is available for snakes and eels.
	 */
	TAIL_LONG("long-tailed",
			0,
			0,
			true,
			true,
			WingSize.FOUR_HUGE,
			false,
			0,
			0,
			"A configuration in which the character's legs and groin are replaced by an extremely long tail of the associated animal-morph, with their genitals shifting to be located within a cloaca."
				+ " The most common example of this is the 'lamia', in which the character's legs and groin are replaced by the body and genitals of a snake.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG) {

		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.LEG,
							InventorySlot.GROIN),
					character.getLegType().getRace(),
					"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only long-tail-suitable clothing can be worn in this slot.",
					Util.newArrayListOfValues(
							ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
							ItemTag.FITS_LONG_TAIL_BODY));
		}
	},

	/**
	 * This LegConfiguration is available for fish.
	 */
	TAIL("tailed",
			200,
			-90,
			true,
			true,
			WingSize.THREE_LARGE,
			false, 
			0, 
			0,
			"A configuration in which the character's legs and groin are replaced by a tail of the associated animal-morph, with their genitals shifting to be located within a cloaca."
					+ " The most common example of this is the 'mermaid', in which the character's legs and groin are replaced by the body and genitals of a fish.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_TAIL) {

		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.LEG,
							InventorySlot.GROIN),
					character.getLegType().getRace(),
					"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only tail-suitable clothing can be worn in this slot.",
					Util.newArrayListOfValues(
							ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
							ItemTag.FITS_TAIL_BODY));
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
			WingSize.FOUR_HUGE,
			true,
			8,
			0,
			"A configuration in which the character's legs and groin are replaced by the eight-legged, bestial body of the associated arachnid-morph, with their genitals shifting to be found in the same place as their animal equivalent."
					+ " The most common example of this is the 'arachne', in which the character's legs and groin are replaced by the body and genitals of a spider.",
			"Occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_ARACHNID) {

		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.LEG,
							InventorySlot.GROIN),
					character.getLegType().getRace(),
					"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only arachnid-suitable clothing can be worn in this slot.",
					Util.newArrayListOfValues(
							ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
							ItemTag.FITS_ARACHNID_BODY));
		}
		
		@Override
		public boolean isGenitalsExposed(GameCharacter character) { // When genitals are in a cloaca (i.e. beneath the arachnid body), they are not visible.
			return character.getGenitalArrangement()==GenitalArrangement.NORMAL;
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
			WingSize.THREE_LARGE,
			false,
			0,
			8,
			// I believe that "tentacled" is technically incorrect as a catch-all term for cephalopods, as octopuses have eight 'arms', while squids have eight arms plus two tentacles. Oh well.
			"A configuration in which the character's legs and groin are replaced by the tentacled, bestial body of the associated cephalopod-morph, with their genitals shifting to be found in the same place as their animal equivalent."
					+ " The most common example of this is the 'kraken', in which the character's legs and groin are replaced by the body and genitals of a squid.",
			"Above [npc.her] groin, occupying the lower region of [npc.her] humanoid abdomen,",
			TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD) {

		@Override
		public BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.LEG,
							InventorySlot.GROIN),
					character.getLegType().getRace(),
					"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only cephalopod-suitable clothing can be worn in this slot.",
					Util.newArrayListOfValues(
							ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
							ItemTag.FITS_CEPHALOPOD_BODY));
		}
		
		@Override
		public boolean isGenitalsExposed(GameCharacter character) { // Genitals are under tentacles, so are not visible even when naked.
			return false;
		}
	};

	private String name;
	private int landSpeedModifier;
	private int waterSpeedModifier;
	private boolean bipedalPositionedGenitals;
	private boolean bipedalPositionedCrotchBoobs;
	
	private WingSize minimumWingSizeForFlight;
	private boolean wingsOnLegConfiguration;
	
	int numberOfLegs;
	int numberOfTentacles;
	
	private String genericDescription;
	private String crotchBoobLocationDescription;

	private TFModifier tfModifier;
	
	private LegConfiguration(
			String name,
			int landSpeedModifier,
			int waterSpeedModifier,
			boolean bipedalPositionedGenitals,
			boolean bipedalPositionedCrotchBoobs,
			WingSize minimumWingSizeForFlight,
			boolean wingsOnLegConfiguration,
			int numberOfLegs,
			int numberOfTentacles,
			String genericDescription,
			String crotchBoobLocationDescription,
			TFModifier tfModifier) {
		
		this.name = name;
		
		this.landSpeedModifier = landSpeedModifier;
		this.waterSpeedModifier = waterSpeedModifier;
		
		this.bipedalPositionedGenitals = bipedalPositionedGenitals;
		this.bipedalPositionedCrotchBoobs = bipedalPositionedCrotchBoobs;
		
		this.minimumWingSizeForFlight=minimumWingSizeForFlight;
		this.wingsOnLegConfiguration=wingsOnLegConfiguration;
		
		this.numberOfLegs = numberOfLegs;
		this.numberOfTentacles = numberOfTentacles;
		
		this.genericDescription = genericDescription;
		this.crotchBoobLocationDescription = crotchBoobLocationDescription;

		this.tfModifier = tfModifier;
	}

	// I didn't implement this in the end, as upon further reflection, I came to the conclusion that blocking the player's choices for the sake of more 'realistic' taur bodies is a bad idea.
	// All of the pre-generated ones will be accurate anyway, so all this would do is limit the player unnecessarily.
//	/**
//	 * @return A list of BodyPartInterface classes of whose <b>type</b> cannot be transformed outside of the LegType's Race while the character has this LegConfiguration.
//	 *  Their other modifiers can still be freely transformed. Penis and Vagina can still be set to NONE.
//	 */
//	public List<Class<? extends BodyPartInterface>> getBlockedTransformations() {
//		return getBestialParts();
//	}

	/**
	 * @return A list of BodyPartInterface classes which are considered to be fully animalistic as part of this LegConfiguration. e.g. A taur's Tail, Ass, BreastCrotch, Penis, and Vagina are all animalistic.
	 */
	public List<Class<? extends BodyPartInterface>> getBestialParts() {
		return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
	}

	public String getName() {
		return name;
	}

	public int getLandSpeedModifier() {
		return landSpeedModifier;
	}

	public int getWaterSpeedModifier() {
		return waterSpeedModifier;
	}

	/**
	 * @return true If this leg configuration blocks flight from the usual arms or wings.
	 */
	public WingSize getMinimumWingSizeForFlight() {
		return minimumWingSizeForFlight;
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
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}

	public int getNumberOfTentacles() {
		return numberOfTentacles;
	}

	public boolean isGenitalsExposed(GameCharacter character) {
		return true;
	}

	public boolean isGenitalConfigurationTransformable() {
		return false;
	}

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

	public void setWingsToDemon(GameCharacter character) {
		character.setWingType(WingType.DEMON_COMMON);
		character.setWingSize(this.minimumWingSizeForFlight.getValue());
	}

	/**
	 * @return A BodyPartClothingBlock object which defines how this LegConfiguration is blocking InventorySlots. Returns null if it doesn't affect inventorySlots in any way.
	 */
	public abstract BodyPartClothingBlock getBodyPartClothingBlock(GameCharacter character);
}
