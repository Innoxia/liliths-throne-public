package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.inventory.*;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 3.3.0
 * @version
 * @author NukeBait, Innoxia
 */
public class Akari extends NPC {

	private List<AbstractItem> moddedItems;
	private List<AbstractClothing> moddedClothes;
	private List<AbstractWeapon> moddedWeapons;
	
	public Akari() {
		this(false);
	}
	
	public Akari(boolean isImported) {
		super(isImported, new NameTriplet("Akari"), "Himeko",
				"Akari is the sole employee of her hodge-podge bargain store."
						+ " She is extremely cheerful. A cheeky, ear to ear smile never seems to leave her face.",
				124, Month.JANUARY, 12,//TODO
				10,
				Gender.F_V_B_FEMALE,
				Subspecies.FOX_ASCENDANT,
				RaceStage.LESSER,
				new CharacterInventory(10),
				WorldType.SHOPPING_ARCADE,
				PlaceType.SHOPPING_ARCADE_AKARIS_SHOP,
				true);

		moddedClothes = new ArrayList<>();
		moddedItems = new ArrayList<>();
		moddedWeapons = new ArrayList<>();
			if(!isImported) {
			dailyReset();
		}


	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {

		this.setRaceConcealed(false);
		
		// Persona:

		if(setPersona) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 10);
			this.setAttribute(Attribute.MAJOR_ARCANE, 50);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
	
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}
		// Body:

		// Core:
		this.setHeight(165);
		this.setFemininity(85);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ONE_SLENDER.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_FOX_MORPH, Colour.EYE_GOLD));
		this.setSkinCovering(new Covering(BodyCoveringType.FOX_FUR, Colour.COVERING_GINGER), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_FOX_FUR, Colour.COVERING_GINGER), true);
		this.setHairLength(HairLength.TWO_SHORT.getMedianValue());
		this.setHairStyle(HairStyle.MESSY);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, Colour.COVERING_GINGER), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_FOX_FUR, Colour.COVERING_GINGER), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_RED));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_RED));
		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED_LIGHT));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_PINK));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.C.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ONE_SMALL.getValue());
		this.setAreolaeSize(AreolaeSize.TWO_BIG.getValue());
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		// No penis
		
		// Vagina:
		this.setVaginaVirgin(true);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(false);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.THREE_WET);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ZERO_RUBBERY.getValue());
		
		// Feet:
//		this.setFootStructure(FootStructure.PLANTIGRADE);
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		// Tattoos
		// Scars

		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.setPiercedNose(true);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BIKINI, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_BIKINI, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_TIARA, Colour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_AVIATORS, Colour.CLOTHING_GOLD, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getCharacterInformationScreen() {
		infoScreenSB.setLength(0);
		
		infoScreenSB.append(
				"<h4>Background</h4>"
				+ "<p>"
					+ this.getDescription()
				+ "</p>"
				+ "<br/>"
				+ "<h4>Relationships</h4>"
				+ "<p>"
					+ "[style.boldAffection(Affection:)]<br/>"
					+ AffectionLevel.getDescription(this, Main.game.getPlayer(),
							AffectionLevel.getAffectionLevelFromValue(this.getAffection(Main.game.getPlayer())), true));
		
		for(Entry<String, Float> entry : this.getAffectionMap().entrySet()) {
			try {
				GameCharacter target = Main.game.getNPCById(entry.getKey());
				if(!target.isPlayer()) {
					infoScreenSB.append("<br/>" + AffectionLevel.getDescription(this, target, AffectionLevel.getAffectionLevelFromValue(this.getAffection(target)), true));
				}
			} catch (Exception e) {
				Util.logGetNpcByIdError("Akari.getCharacterInformationScreen()", entry.getKey());
			}
		}
		
		infoScreenSB.append("<br/><br/>"
					+ "[style.boldObedience(Obedience:)]<br/>"
					+ UtilText.parse(this,
							(this.isSlave()
								?"[npc.Name] [style.boldArcane(is a slave)], owned by "+(this.getOwner().isPlayer()?"you!":this.getOwner().getName("a")+".")
								:"[npc.Name] [style.boldGood(is not a slave)]."))
					+ "<br/>"+ObedienceLevel.getDescription(this, ObedienceLevel.getObedienceLevelFromValue(this.getObedienceValue()), true, true)
				+"</p>"
				+ "<br/>"
					+ "<h4>Appearance</h4>"
				+ "<p>"
					+ " You eye the small kitsune standing in front of you, "
					+ " Even on her tip toes, she only barely measures 5 feet \" (152cm)."
				+ "</p>"
				+ "<p>"
					+ "Two fluffy ears sit right on top of her head, twitching occasionally to pick up distant sounds"
					+ " A pair of golden eyes stare right back at you as you give her the once over."
				+ "</p>"
				+ "<p>"
					+ "Even though she is short, Akari would not be \"Petite\" by any standards."
					+ " Lightly muscled and just ever so slightly plush, you can tell her body has withstood the trials of adventuring."
				+ "</p>");
		
		return infoScreenSB.toString();
	}


	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		moddedItems.clear();
		moddedWeapons.clear();
		moddedClothes.clear();

		List<AbstractCoreType> types = new ArrayList<>();

		for(AbstractWeaponType wt : WeaponType.getAllWeapons()) {
			if(wt.getItemTags().contains(ItemTag.SOLD_BY_AKARI)) {
				types.add(wt);
			}
		}
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_AKARI)) {
				types.add(item);
			}
		}
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			try {
				if(clothing!=null && clothing.getItemTags().contains(ItemTag.SOLD_BY_AKARI)) {
					types.add(clothing);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		Collections.shuffle(types);
		int count=0;
		for(AbstractCoreType type : types) {
			if(type instanceof AbstractWeaponType) {
				for(int i=0; i<1+Util.random.nextInt(3); i++){
					moddedWeapons.add(AbstractWeaponType.generateWeapon((AbstractWeaponType) type));
				}

			} else if(type instanceof AbstractItemType) {
				moddedItems.add(AbstractItemType.generateItem((AbstractItemType) type));

			} else if(type instanceof AbstractClothingType) {
				moddedClothes.add(AbstractClothingType.generateClothing((AbstractClothingType) type));
			}
			count++;
			if(count>=this.getMaximumInventorySpace()) {
				break;
			}
		}

	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	public List<AbstractWeapon> getWeaponsForSale() {
		return moddedWeapons;
	}

	public List<AbstractItem> getItemsForSale() {
		return moddedItems;
	}

	public List<AbstractClothing> getClothingForSale() {
		return moddedClothes;
	}

}