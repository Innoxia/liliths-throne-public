package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.PregnancyDescriptor;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.4
 * @version 0.2.11
 * @author Innoxia
 */
public class Elemental extends NPC {
	private String summonerID;

	public Elemental(boolean isImported) {
		this(Gender.F_V_B_FEMALE, null, isImported);
	}
	
	public Elemental(Gender gender, GameCharacter summoner, boolean isImported) {
		super(isImported, null, null, "", summoner==null?18:summoner.getAgeValue(), summoner==null?Month.JANUARY:summoner.getBirthMonth(), summoner==null?1:summoner.getDayOfBirth(), 20, gender, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.EMPTY, PlaceType.GENERIC_EMPTY_TILE, false);

		if(!isImported) {
			this.setLocation(summoner, false);
			
			setLevel(summoner.getLevel());
			
			this.setSummoner(summoner);
			this.setStartingBody(true);
			
			this.setLegType(LegType.DEMON_COMMON);
			
			this.setHistory(Occupation.ELEMENTAL_ARCANE);
			
			// RACE & NAME:
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(Race.DEMON));
			this.setPlayerKnowsName(true);
			
			// INVENTORY:
			
			resetInventory(true);
			
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);

			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 0);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element npcSpecific = doc.createElement("elementalSpecial");
		properties.appendChild(npcSpecific);

		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "summoner", this.getSummoner().getId());
		
		return properties;
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		Element npcSpecificElement = (Element) parentElement.getElementsByTagName("elementalSpecial").item(0);
		this.setSummoner(((Element)npcSpecificElement.getElementsByTagName("summoner").item(0)).getAttribute("value"));
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.6")) {
			this.setAttribute(Attribute.MAJOR_PHYSIQUE, 0);
			this.setAttribute(Attribute.MAJOR_ARCANE, 0);
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 0);
			this.resetPerksMap();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.12")) {
			this.setElementalSchool(this.getCurrentSchool());
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		GameCharacter summoner = this.getSummoner();
		if(summoner==null) {
			return;
		}
		
		// Body:
		this.setAgeAppearanceDifferenceToAppearAsAge(summoner.getAppearsAsAgeValue());
		this.setTailType(TailType.DEMON_COMMON);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		this.setLegType(LegType.DEMON_COMMON);
		if(summoner.getHornType().equals(HornType.NONE) || summoner.getHornType().getRace()==Race.DEMON) {
			this.setHornType(summoner.getHornType());
		} else if(this.isFeminine()) {
			this.setHornType(HornType.SWEPT_BACK);
		} else {
			this.setHornType(HornType.STRAIGHT);
		}

		// Core:
		this.setHeight(summoner.getHeightValue());
		this.setFemininity(75);
		this.setMuscle(Muscle.TWO_TONED.getMedianValue());
		this.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
			
		// Coverings:
//		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_RED));
//		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PALE), true);
//		
//		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_WHITE), false);
//
//		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BROWN_DARK), true);
//		this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
//		this.setHairStyle(HairStyle.LOOSE);
//		
//		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, Colour.COVERING_BLACK), false);
//		this.setUnderarmHair(BodyHair.ZERO_NONE);
//		this.setAssHair(BodyHair.ZERO_NONE);
//		this.setPubicHair(BodyHair.ZERO_NONE);
//		this.setFacialHair(BodyHair.ZERO_NONE);
//
//			this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_PURPLE));
//			this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_PURPLE));
//				this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
//				this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//			this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//				this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
			
			// Face:
			this.setFaceVirgin(true);
			this.setLipSize(LipSize.TWO_FULL);
			this.setFaceCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
			// Throat settings and modifiers
			this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
			// Tongue modifiers
			
			// Chest:
			this.setNippleVirgin(true);
			if(summoner.hasBreasts()) {
				this.setBreastSize(summoner.getBreastSize());
				this.setBreastShape(summoner.getBreastShape());
				this.setNippleSize(summoner.getNippleSize());
				this.setAreolaeSize(summoner.getAreolaeSize());
			} else {
				this.setBreastSize(CupSize.F.getMeasurement());
				this.setBreastShape(BreastShape.ROUND);
				this.setNippleSize(NippleSize.THREE_LARGE);
				this.setAreolaeSize(AreolaeSize.THREE_LARGE);
			}
			// Nipple settings and modifiers
			
			// Ass:
			this.setAssVirgin(true);
			this.setAssBleached(false);
			this.setAssSize(AssSize.FOUR_LARGE);
			this.setHipSize(HipSize.FOUR_WOMANLY);
			// Anus settings and modifiers
			
			// Penis:
			// n/a
			
			// Vagina:
			this.setVaginaVirgin(true);
			this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
			this.setVaginaLabiaSize(LabiaSize.ZERO_TINY);
			this.setVaginaSquirter(true);
			this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
			this.setVaginaWetness(Wetness.THREE_WET);
			this.setVaginaElasticity(OrificeElasticity.TWO_FIRM.getValue());
			this.setVaginaPlasticity(OrificePlasticity.SIX_MALLEABLE.getValue());
			
			// Feet:
			// Foot shape
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// Not needed
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getSurname() {
		if(this.getSummoner()!=null) {
			return this.getSummoner().getNameIgnoresPlayerKnowledge()+"kamu"; // Akkadian for bind
		} else {
			return "kamu";
		}
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this, getSummoner(), "");
	}
	
	@Override
	protected int getTrueLevel() {
		if(this.getSummoner()==null) {
			return level;
		}
		return getSummoner().getLevel();
	}
	
	@Override
	public int getLevel() {
		return getTrueLevel();
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public String rollForPregnancy(GameCharacter partner, float cum) {
		return PregnancyDescriptor.NO_CHANCE.getDescriptor(this, partner)
				+"<p style='text-align:center;'>[style.italicsMinorBad(Elementals cannot get pregnant!)]<br/>[style.italicsDisabled(I will add support for impregnating/being impregnated by elementals soon!)]</p>";
	}

	@Override
	public String incrementExperience(int increment, boolean withExtraModifiers) {
		return ""; // Elementals don't gain experience, but instead automatically level up alongside their summoner.
	}
	
	@Override
	public boolean addPerk(int row, Perk perk) {
		perks.putIfAbsent(row, new HashSet<>());
		
		if (perks.get(row).contains(perk)) {
			return false;
		}
		
		perks.get(row).add(perk);
		
		if(!perk.isEquippableTrait()) {
			applyPerkGainEffects(perk);
		}

		calculateSpells(getCurrentSchool());
		
		return true;
	}
	
	private void calculateSpells(SpellSchool school) {
		this.resetSpells();
		
		// Add spells:
		for(Set<Perk> perkSet : this.getPerksMap().values()) {
			for(Perk p : perkSet) {
				if(p.getSchool()==school) {
					if(p.getSpellUpgrade()!=null) {
						this.addSpellUpgrade(p.getSpellUpgrade());
					} else {
						this.addSpell(p.getSpell());
						
					}
				}
			}
		}
	}
	
	public SpellSchool getCurrentSchool() {
		switch(this.getBodyMaterial()) {
			case AIR:
				return SpellSchool.AIR;
			case ARCANE:
				return SpellSchool.ARCANE;
			case FIRE:
				return SpellSchool.FIRE;
			case FLESH:
			case SLIME:
				break;
			case RUBBER:
			case STONE:
				return SpellSchool.EARTH;
			case ICE:
			case WATER:
				return SpellSchool.WATER;
		}
		return SpellSchool.ARCANE;
	}
	
	public void setElementalSchool(SpellSchool school) {
		setElementalSchool(school, null);
	}
	
	public void setElementalSchool(SpellSchool school, BodyMaterial preferredMaterial) {
		
		switch(school) {
			case AIR:
				this.setBodyMaterial(BodyMaterial.AIR);
				this.setHistory(Occupation.ELEMENTAL_AIR);
				break;
				
			case ARCANE:
				this.setBodyMaterial(BodyMaterial.ARCANE);
				this.setHistory(Occupation.ELEMENTAL_ARCANE);
				break;
				
			case EARTH:
				if(preferredMaterial==BodyMaterial.RUBBER) {
					this.setBodyMaterial(BodyMaterial.RUBBER);
				} else {
					this.setBodyMaterial(BodyMaterial.STONE);
				}
				this.setHistory(Occupation.ELEMENTAL_EARTH);
				break;
				
			case FIRE:
				this.setBodyMaterial(BodyMaterial.FIRE);
				this.setHistory(Occupation.ELEMENTAL_FIRE);
				break;
				
			case WATER:
				if(preferredMaterial==BodyMaterial.ICE) {
					this.setBodyMaterial(BodyMaterial.ICE);
				} else {
					this.setBodyMaterial(BodyMaterial.WATER);
				}
				this.setHistory(Occupation.ELEMENTAL_WATER);
				break;
		}
		calculateSpells(school);
	}
	
	public GameCharacter getSummoner() {
		try {
			return Main.game.getNPCById(summonerID);
		} catch (Exception e) {
//			Util.logGetNpcByIdError("getSummoner()", id);
			return null;
//			throw new NullPointerException();
		}
	}

	public void setSummoner(String summonerID) {
		this.summonerID = summonerID;
	}
	
	public void setSummoner(GameCharacter summoner) {
		this.summonerID = summoner.getId();
	}
	

}