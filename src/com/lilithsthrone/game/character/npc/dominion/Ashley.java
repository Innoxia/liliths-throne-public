package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
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
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
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
 * @since 0.1.99
 * @version 0.1.99
 * @author Kumiko, Innoxia
 */
public class Ashley extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractItemType[] itemsForSale = new AbstractItemType[] {
			ItemType.GIFT_CHOCOLATES,
			ItemType.GIFT_PERFUME,
			ItemType.GIFT_ROSE_BOUQUET,
			ItemType.GIFT_TEDDY_BEAR};
	
	public Ashley() {
		this(false);
	}
	
	public Ashley(boolean isImported) {
		super(new NameTriplet("Ashley"),
				"Ashley is the owner of the shop 'Dream Lover', and is seemingly also its only working staff."
						+ " They are very stand-offish and loathe helping out their customers, to the point where they'd rather stare at the walls instead of offering any help.",
				200, Month.AUGUST, 14,//TODO
				10,
				Gender.N_P_TRAP,
				RacialBody.ANGEL,
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.SHOPPING_ARCADE,
				PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP,
				true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_ANGEL, Colour.EYE_BLUE));
			this.setHeight(186);
			
			this.setMoney(0);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_CLOAK, Colour.CLOTHING_BLACK, Colour.CLOTHING_SILVER, null, false), true, this);
			
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public int getAppearsAsAge() {
		return 25;
	}
	
	@Override
	public boolean isRaceConcealed() {
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
			GameCharacter target = Main.game.getNPCById(entry.getKey());
			if(!target.isPlayer()) {
				infoScreenSB.append("<br/>" + AffectionLevel.getDescription(this, target, AffectionLevel.getAffectionLevelFromValue(this.getAffection(target)), true));
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
					+ (Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)
							?"Despite the fact that you're highly observant, there's no giveaway whatsoever which would hint as to what Ashley's gender is."
							:"You have no idea what Ashley's gender is.")
					+ " Standing at full height, they measure 6'1\" (186cm)."
				+ "</p>"
				+ "<p>"
					+ "The hood of their cloak is pulled up, completely obscuring their facial features."
					+ " All that you can make out from the darkness of the hood is the sheen of a pair of deep blue eyes."
				+ "</p>"
				+ "<p>"
					+ "Ashley's cloak covers the entirety of their body, leaving you completely unable to see any part of their body."
					+ " You have no idea what race they are, much less the state of their breasts and genitals."
				+ "</p>");
		
		return infoScreenSB.toString();
	}
	
	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		for (AbstractItemType item : itemsForSale) {
			for (int i = 0; i < 3 + (Util.random.nextInt(6)); i++) {
				this.addItem(AbstractItemType.generateItem(item), false);
			}
		}

		this.addWeapon(AbstractWeaponType.generateWeapon(WeaponType.MAIN_FEATHER_DUSTER), false);
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public void endSex() {
	}

}