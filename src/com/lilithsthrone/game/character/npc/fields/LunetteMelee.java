package com.lilithsthrone.game.character.npc.fields;

import java.util.List;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class LunetteMelee extends RandomNPC {

	private static final List<String> defaultNamePrefixes = Util.newArrayListOfValues("angry", "furious", "wrathful");
	private static final String defaultName = "marauder";
	
	public LunetteMelee(NPCGenerationFlag... generationFlags) {
		this(false, defaultNamePrefixes, defaultName, generationFlags);
	}
	
	public LunetteMelee(boolean isImported) {
		this(isImported, defaultNamePrefixes, defaultName);
	}
	
	public LunetteMelee(List<String> namePrefixes, String name, NPCGenerationFlag... generationFlags) {
		this(false, namePrefixes, name, generationFlags);
	}
	
	public LunetteMelee(boolean isImported, List<String> namePrefixes, String name, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Lunette NPCs have a special setup
		this.setupMarauder(false, namePrefixes, name, generationFlags);
		this.setStartingBody(true);
		this.setDescription("This demonic centaur is a member of the faction 'Lunette's Marauders', and loves nothing more than to break anything and anyone who gets in [npc.her] way.");
	}
	
	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_MELEE_EXPERT);
		this.addSpecialPerk(Util.randomItemFrom(new AbstractPerk[] {
				Perk.SPECIAL_MARTIAL_BACKGROUND,
				Perk.SPECIAL_HEALTH_FANATIC
		}));
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 10),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 1)));
	}

	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		equipMove("strike");
		equipMove("offhand-strike");
		equipMove("twin-strike");
		equipMove("block");
		this.equipAllSpecialMoves();
		this.equipAllSpellMoves();
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
		
		this.setHeight(210+Util.random.nextInt(41));
		
		if(this.hasPenis()) {
			this.growPenis(); // To set correct modifiers
		}

		if(this.hasVagina()) {
			this.growVagina(); // To set correct modifiers
		}
		
		if(setPersona) {
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
			
			if(Math.random()<0.5f) {
				this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			}
		}
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.setEssenceCount(200+Util.random.nextInt(101));
		
		Util.random.setSeed(this.getDayOfBirth()*this.getBirthMonth().getValue()); // Set seed based on birthday so that the clothing is always the same
		
		Colour clothingColour = Util.randomItemFromValues(
				PresetColour.CLOTHING_BLACK,
				PresetColour.CLOTHING_BLACK,
				PresetColour.CLOTHING_BLACK_JET,
				PresetColour.CLOTHING_RED,
				PresetColour.CLOTHING_RED_BURGUNDY,
				PresetColour.CLOTHING_RED_DARK,
				PresetColour.CLOTHING_RED_VERY_DARK
		);
		
		Colour accessoryColour = Util.randomItemFromValues(
				PresetColour.CLOTHING_BLACK_STEEL,
				PresetColour.CLOTHING_SILVER
		);

		if(!this.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(
					Util.randomItemFromValues(ClothingType.getClothingTypeFromId("innoxia_chest_tube_top"), ClothingType.getClothingTypeFromId("innoxia_chest_sports_bra")), clothingColour, false), true, this);
		}

		if(Math.random()<0.5f) {
			if(Math.random()<0.5f) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WRISTBANDS, clothingColour, false), true, this);
			} else {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_BANGLE, accessoryColour, false), true, this);
			}
		}
		
		if(Math.random()<0.5f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_horseshoe_necklace", accessoryColour, accessoryColour, accessoryColour, false), true, this);
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_gemstone_necklace", accessoryColour, clothingColour, accessoryColour, false), true, this);
		}
		
		if(this.hasFetish(Fetish.FETISH_ANAL_RECEIVING) && Math.random()<0.1f) {
			if(Math.random()<0.5f) {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(
						Util.randomItemFromValues("innoxia_buttPlugs_butt_plug_jewel", "innoxia_buttPlugs_butt_plug_heart"), accessoryColour, clothingColour, accessoryColour, false), true, this);
			} else {
				this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_anus_ribbed_dildo", PresetColour.CLOTHING_BLACK, false), true, this);
			}
		}
		
		// Piercings:
		if(this.isPiercedEar()) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(Util.randomItemFromValues("innoxia_piercing_ear_ball_studs", "innoxia_piercing_ear_ring"), accessoryColour, false), true, this);
		}
		if(this.isPiercedNose() && Math.random()<0.5f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", accessoryColour, false), true, this);
		}
		if(this.isPiercedTongue() && Math.random()<0.5f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", accessoryColour, false), true, this);
		}
		if(this.isPiercedLip() && Math.random()<0.5f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", accessoryColour, false), true, this);
		}
		if(this.isPiercedNavel() && Math.random()<0.5f) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(Util.randomItemFromValues("innoxia_piercing_gemstone_barbell", "innoxia_piercing_ringed_barbell"), accessoryColour, false), true, this);
		}
		if(this.isPiercedNipple() && this.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(
					Util.randomItemFromValues("innoxia_piercing_basic_barbell_pair", "norin_piercings_piercing_nipple_rings", "norin_piercings_piercing_nipple_chain"), accessoryColour, false), true, this);
		}
		
		
		// Weapon:
		
		// Randomise weapon:
		int rnd = Util.random.nextInt(100);
		DamageType dt = DamageType.PHYSICAL;//Util.randomItemFrom(new DamageType[] {DamageType.PHYSICAL, DamageType.POISON, DamageType.FIRE, DamageType.ICE});
		if(rnd<25) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_spear_dory", dt));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_feather_epic", dt));
			
		} else if(rnd<50) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_europeanSwords_zweihander", dt));
			
		} else if(rnd<75) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_europeanSwords_arming_sword", dt));
			this.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_europeanSwords_arming_sword", dt));
			
		} else {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_axe_battle", dt));
		}
		
		Util.random.setSeed(System.nanoTime()); // Reset seed to be close to random
	}
	
	// Combat:
	@Override
	public void applyEscapeCombatEffects() {
		if(this.getLocationPlace().getPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra_raiders")) {
			Main.game.getPlayer().setLocation(Main.game.getPlayer().getLastCell());
		} else {
			Main.game.banishNPC(this); // Only remove if this NPC is not a raider in Themiscyra
		}
	}
	
	public int getPaymentDemand() {
		return (Math.max(2500, Math.min(Main.game.getPlayer().getMoney()/10, 10000))/500) * 500; // Round to nearest 500
	}
	
	public void growPenis() {
		this.setPenisType(PenisType.DEMON_COMMON);
		this.setPenisSize(PenisLength.FIVE_ENORMOUS.getMedianValue()+Util.random.nextInt(10));
		this.setPenisGirth(PenetrationGirth.SIX_CHUBBY);
		
		this.clearPenisModifiers();
		this.addPenisModifier(PenetrationModifier.FLARED);
		this.addPenisModifier(PenetrationModifier.VEINY);
		this.addPenisModifier(PenetrationModifier.SHEATHED);
		this.addPenisModifier(PenetrationModifier.KNOTTED);
		this.setTesticleSize(TesticleSize.FIVE_MASSIVE);
		
		this.setPenisCumExpulsion(80);
		this.setPenisCumStorage(2000);
		this.fillCumToMaxStorage();
		this.setInternalTesticles(false);
	}

	public void growVagina() {
		this.setVaginaType(VaginaType.DEMON_COMMON);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue() + Util.random.nextInt(3));
		
		this.clearVaginaOrificeModifiers();
		this.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL);
		this.addVaginaOrificeModifier(OrificeModifier.PUFFY);
		if(Math.random()<0.5) {
			this.addVaginaOrificeModifier(OrificeModifier.RIBBED);
		}
		if(Math.random()<0.5) {
			this.addVaginaOrificeModifier(OrificeModifier.TENTACLED);
		}
		
		if(Math.random()<0.5) {
			this.setVaginaWetness(Wetness.TWO_MOIST);
			this.setVaginaSquirter(false);
		} else {
			this.setVaginaWetness(Wetness.THREE_WET.getValue() + Util.random.nextInt(5));
			this.setVaginaSquirter(true);
		}
		this.setVaginaCapacity(Util.randomItemFrom(Util.newArrayListOfValues(Capacity.TWO_TIGHT, Capacity.THREE_SLIGHTLY_LOOSE, Capacity.FOUR_LOOSE)), true);
		this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue() + Util.random.nextInt(3));
		this.setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue() + Util.random.nextInt(3));
	}
}