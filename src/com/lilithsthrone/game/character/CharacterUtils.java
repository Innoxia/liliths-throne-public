package com.lilithsthrone.game.character;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Ear;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Face;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.BlockedParts;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.clothing.CoverableArea;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.Dominion;

/**
 * @since 0.1.67
 * @version 0.1.86
 * @author Innoxia
 */
public class CharacterUtils {
	
	public static void saveCharacterAsXML(GameCharacter character){
		try {
//			long timeStart = System.nanoTime();
//			System.out.println(timeStart);
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element properties = doc.createElement("character");
			doc.appendChild(properties);

			// GameCharacter class:
			
			// Core information:
			Element characterCoreInfo = doc.createElement("core");
			Comment comment = doc.createComment("If you want to edit any of these values, just be warned that it might break the game...");
			properties.appendChild(characterCoreInfo);
			createXMLElementWithValue(doc, characterCoreInfo, "name", character.getName());
			createXMLElementWithValue(doc, characterCoreInfo, "surname", character.getSurname());
			createXMLElementWithValue(doc, characterCoreInfo, "level", String.valueOf(character.getLevel()));
			createXMLElementWithValue(doc, characterCoreInfo, "version", Main.VERSION_NUMBER);
			createXMLElementWithValue(doc, characterCoreInfo, "sexualOrientation", String.valueOf(character.getSexualOrientation()));
			characterCoreInfo.getParentNode().insertBefore(comment, characterCoreInfo);
			
			// Attributes:
			Element characterCoreAttributes = doc.createElement("attributes");
			properties.appendChild(characterCoreAttributes);
			for(Attribute att : Attribute.values()){
				Element element = doc.createElement("attribute");
				characterCoreAttributes.appendChild(element);
				
				addAttribute(doc, element, "type", att.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getBaseAttributeValue(att)));
			}
			
			// Perks:
			Element characterPerks = doc.createElement("perks");
			properties.appendChild(characterPerks);
			for(Perk p : Perk.values()){
				Element element = doc.createElement("perk");
				characterPerks.appendChild(element);
				
				addAttribute(doc, element, "type", p.toString());
				addAttribute(doc, element, "value", String.valueOf(character.hasPerk(p)));
			}
			
			// Fetishes:
			Element characterFetishes = doc.createElement("fetishes");
			properties.appendChild(characterFetishes);
			for(Fetish f : Fetish.values()){
				Element element = doc.createElement("fetish");
				characterFetishes.appendChild(element);
				
				addAttribute(doc, element, "type", f.toString());
				addAttribute(doc, element, "value", String.valueOf(character.hasFetish(f)));
			}
			
			// Status effects:
			Element characterStatusEffects = doc.createElement("statusEffects");
			properties.appendChild(characterStatusEffects);
			for(StatusEffect se : character.getStatusEffects()){
				Element element = doc.createElement("statusEffect");
				characterStatusEffects.appendChild(element);
				
				addAttribute(doc, element, "type", se.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getStatusEffectDuration(se)));
			}
			
			// Combat (spells and special attacks):
			Element characterCombat = doc.createElement("combat");
			properties.appendChild(characterCombat);
			for(Spell s : Spell.values()){
				Element element = doc.createElement("spell");
				characterCombat.appendChild(element);
				
				addAttribute(doc, element, "type", s.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getSpells().contains(s)));
			}
			for(SpecialAttack sa : SpecialAttack.values()){
				Element element = doc.createElement("specialAttack");
				characterCombat.appendChild(element);
				
				addAttribute(doc, element, "type", sa.toString());
				addAttribute(doc, element, "value", String.valueOf(character.getSpecialAttacks().contains(sa)));
			}
			
			// Pregnancy: TODO
//			Element characterPregnancy = doc.createElement("pregnancy");
//			properties.appendChild(characterPregnancy);
//			
//			Element characterPregnancyPossibility = doc.createElement("pregnancyPossibilities");
//			characterPregnancy.appendChild(characterPregnancyPossibility);
//			for(PregnancyPossibility pregPoss : character.getPotentialPartnersAsMother()) {
//				Element element = doc.createElement("possibility");
//				characterPregnancyPossibility.appendChild(element);
//				
////				addAttribute(doc, element, "fatherName", pregPoss.getFatherName());
////				addAttribute(doc, element, "gender", pregPoss.getGender().toString());
////				addAttribute(doc, element, "race", pregPoss.getRace().toString());
////				addAttribute(doc, element, "raceStage", pregPoss.getRaceStage().toString());
//				addAttribute(doc, element, "probability", String.valueOf(pregPoss.getProbability()));
//			}
//			
//			Element characterPregnancyCurrentLitter = doc.createElement("pregnantLitter");
//			characterPregnancy.appendChild(characterPregnancyCurrentLitter);
//			if (character.getPregnantLitter() != null) {
//				Element element = doc.createElement("litter");
//				characterPregnancyCurrentLitter.appendChild(element);
//
//				addAttribute(doc, element, "dayOfConception", String.valueOf(character.getPregnantLitter().getDayOfBirth()));
//				addAttribute(doc, element, "dayOfBirth", String.valueOf(character.getPregnantLitter().getDayOfBirth()));
////				addAttribute(doc, element, "fatherName", character.getPregnantLitter().getPartner().getName("a"));
////				addAttribute(doc, element, "race", character.getPregnantLitter().getRace().toString());
//				addAttribute(doc, element, "sons", String.valueOf(character.getPregnantLitter().getSons()));
//				addAttribute(doc, element, "daughters", String.valueOf(character.getPregnantLitter().getDaughters()));
//			}
//			
//			Element characterPregnancyBirthedLitters = doc.createElement("birthedLitters");
//			characterPregnancy.appendChild(characterPregnancyBirthedLitters);
//			for(Litter litter : character.getLittersBirthed()) {
//				Element element = doc.createElement("birthedLitter");
//				characterPregnancyBirthedLitters.appendChild(element);
//
//				addAttribute(doc, element, "dayOfConception", String.valueOf(litter.getDayOfBirth()));
//				addAttribute(doc, element, "dayOfBirth", String.valueOf(litter.getDayOfBirth()));
////				addAttribute(doc, element, "fatherName", litter.getPartner().getName("a"));
////				addAttribute(doc, element, "race", litter.getRace().toString());
//				addAttribute(doc, element, "sons", String.valueOf(litter.getSons()));
//				addAttribute(doc, element, "daughters", String.valueOf(litter.getDaughters()));
//			}
			
			// Body (oh here's the fun one...)
			Element characterBody = doc.createElement("body");
			properties.appendChild(characterBody);
			
			// Core:
			Element bodyCore = doc.createElement("bodyCore");
			characterBody.appendChild(bodyCore);
			addAttribute(doc, bodyCore, "piercedStomach", String.valueOf(character.isPiercedNavel()));
			addAttribute(doc, bodyCore, "height", String.valueOf(character.getHeightValue()));
			addAttribute(doc, bodyCore, "femininity", String.valueOf(character.getFemininityValue()));
			addAttribute(doc, bodyCore, "bodySize", String.valueOf(character.getBodySizeValue()));
			addAttribute(doc, bodyCore, "muscle", String.valueOf(character.getMuscleValue()));
			addAttribute(doc, bodyCore, "pubicHair", String.valueOf(character.getPubicHair()));
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				Element element = doc.createElement("bodyCovering");
				bodyCore.appendChild(element);
				
				addAttribute(doc, element, "type", bct.toString());
				addAttribute(doc, element, "pattern", character.getCovering(bct).getPattern().toString());
				addAttribute(doc, element, "colourPrimary", character.getCovering(bct).getPrimaryColour().toString());
				addAttribute(doc, element, "glowPrimary", String.valueOf(character.getCovering(bct).isPrimaryGlowing()));
				addAttribute(doc, element, "colourSecondary", character.getCovering(bct).getSecondaryColour().toString());
				addAttribute(doc, element, "glowSecondary", String.valueOf(character.getCovering(bct).isSecondaryGlowing()));
				addAttribute(doc, element, "discovered", String.valueOf(character.getBodyCoveringTypesDiscovered().contains(bct)));
			}
			

			// Antennae:
			Element bodyAntennae = doc.createElement("antennae");
			characterBody.appendChild(bodyAntennae);
				addAttribute(doc, bodyAntennae, "type", character.getAntennaType().toString());
				addAttribute(doc, bodyAntennae, "rows", String.valueOf(character.getAntennaRows()));
			
			// Arm:
			Element bodyArm = doc.createElement("arm");
			characterBody.appendChild(bodyArm);
				addAttribute(doc, bodyArm, "type", character.getArmType().toString());
				addAttribute(doc, bodyArm, "rows", String.valueOf(character.getArmRows()));
				addAttribute(doc, bodyArm, "underarmHair", character.getUnderarmHair().toString());
			
			// Ass:
			Element bodyAss = doc.createElement("ass");
			characterBody.appendChild(bodyAss);
				addAttribute(doc, bodyAss, "type", character.getAssType().toString());
				addAttribute(doc, bodyAss, "assSize", String.valueOf(character.getAssSize().getValue()));
				addAttribute(doc, bodyAss, "hipSize", String.valueOf(character.getHipSize().getValue()));

			Element bodyAnus = doc.createElement("anus");
			characterBody.appendChild(bodyAnus);
				addAttribute(doc, bodyAnus, "wetness", String.valueOf(character.getAssWetness().getValue()));
				addAttribute(doc, bodyAnus, "elasticity", String.valueOf(character.getAssElasticity().getValue()));
				addAttribute(doc, bodyAnus, "plasticity", String.valueOf(character.getAssPlasticity().getValue()));
				addAttribute(doc, bodyAnus, "capacity", String.valueOf(character.getAssRawCapacityValue()));
				addAttribute(doc, bodyAnus, "stretchedCapacity", String.valueOf(character.getAssStretchedCapacity()));
				addAttribute(doc, bodyAnus, "virgin", String.valueOf(character.isAssVirgin()));
				addAttribute(doc, bodyAnus, "bleached", String.valueOf(character.isAssBleached()));
				addAttribute(doc, bodyAnus, "assHair", character.getAssHair().toString());
				Element anusModifiers = doc.createElement("anusModifiers");
				bodyAnus.appendChild(anusModifiers);
				for(OrificeModifier om : OrificeModifier.values()) {
					addAttribute(doc, anusModifiers, om.toString(), String.valueOf(character.hasAssOrificeModifier(om)));
				}
			
			// Breasts:
			Element bodyBreast = doc.createElement("breasts");
			characterBody.appendChild(bodyBreast);
				addAttribute(doc, bodyBreast, "type", character.getBreastType().toString());
				addAttribute(doc, bodyBreast, "shape", character.getBreastShape().toString());
				addAttribute(doc, bodyBreast, "size", String.valueOf(character.getBreastSize().getMeasurement()));
				addAttribute(doc, bodyBreast, "rows", String.valueOf(character.getBreastRows()));
				addAttribute(doc, bodyBreast, "lactation", String.valueOf(character.getBreastRawLactationValue()));
				addAttribute(doc, bodyBreast, "nippleCountPerBreast", String.valueOf(character.getNippleCountPerBreast()));

			Element bodyNipple = doc.createElement("nipples");
			characterBody.appendChild(bodyNipple);
				addAttribute(doc, bodyNipple, "elasticity", String.valueOf(character.getNippleElasticity().getValue()));
				addAttribute(doc, bodyNipple, "plasticity", String.valueOf(character.getNipplePlasticity().getValue()));
				addAttribute(doc, bodyNipple, "capacity", String.valueOf(character.getNippleRawCapacityValue()));
				addAttribute(doc, bodyNipple, "stretchedCapacity", String.valueOf(character.getNippleStretchedCapacity()));
				addAttribute(doc, bodyNipple, "virgin", String.valueOf(character.isNippleVirgin()));
				addAttribute(doc, bodyNipple, "pierced", String.valueOf(character.isPiercedNipple()));
				addAttribute(doc, bodyNipple, "nippleSize", String.valueOf(character.getNippleSize().getValue()));
				addAttribute(doc, bodyNipple, "nippleShape", String.valueOf(character.getNippleShape()));
				addAttribute(doc, bodyNipple, "areolaeSize", String.valueOf(character.getAreolaeSize().getValue()));
				addAttribute(doc, bodyNipple, "areolaeShape", String.valueOf(character.getAreolaeShape()));
				Element nippleModifiers = doc.createElement("nippleModifiers");
				bodyNipple.appendChild(nippleModifiers);
				for(OrificeModifier om : OrificeModifier.values()) {
					addAttribute(doc, nippleModifiers, om.toString(), String.valueOf(character.hasNippleOrificeModifier(om)));
				}
				
			Element bodyMilk = doc.createElement("milk");
			characterBody.appendChild(bodyMilk);
				addAttribute(doc, bodyMilk, "flavour", String.valueOf(character.getMilkFlavour()));
				Element milkModifiers = doc.createElement("milkModifiers");
				bodyMilk.appendChild(milkModifiers);
				for(FluidModifier fm : FluidModifier.values()) {
					addAttribute(doc, milkModifiers, fm.toString(), String.valueOf(character.hasMilkModifier(fm)));
				}
				//TODO transformativeEffects;
				
				
			// Ear:
			Element bodyEar = doc.createElement("ear");
			characterBody.appendChild(bodyEar);
				addAttribute(doc, bodyEar, "type", character.getEarType().toString());
				addAttribute(doc, bodyEar, "pierced", String.valueOf(character.isPiercedEar()));

			// Eye:
			Element bodyEye = doc.createElement("eye");
			characterBody.appendChild(bodyEye);
				addAttribute(doc, bodyEye, "type", character.getEyeType().toString());
				addAttribute(doc, bodyEye, "eyePairs", String.valueOf(character.getEyePairs()));
				addAttribute(doc, bodyEye, "irisShape", character.getIrisShape().toString());
				addAttribute(doc, bodyEye, "pupilShape", character.getPupilShape().toString());
			
			// Face:
			Element bodyFace = doc.createElement("face");
			characterBody.appendChild(bodyFace);
				addAttribute(doc, bodyFace, "type", character.getFaceType().toString());
				addAttribute(doc, bodyFace, "piercedNose", String.valueOf(character.isPiercedNose()));
				addAttribute(doc, bodyFace, "facialHair", character.getFacialHair().toString());

			Element bodyMouth = doc.createElement("mouth");
			characterBody.appendChild(bodyMouth);
				addAttribute(doc, bodyMouth, "elasticity", String.valueOf(character.getFaceElasticity().getValue()));
				addAttribute(doc, bodyMouth, "plasticity", String.valueOf(character.getFacePlasticity().getValue()));
				addAttribute(doc, bodyMouth, "capacity", String.valueOf(character.getFaceRawCapacityValue()));
				addAttribute(doc, bodyMouth, "stretchedCapacity", String.valueOf(character.getFaceStretchedCapacity()));
				addAttribute(doc, bodyMouth, "virgin", String.valueOf(character.isFaceVirgin()));
				addAttribute(doc, bodyMouth, "piercedLip", String.valueOf(character.isPiercedLip()));
				addAttribute(doc, bodyMouth, "lipSize", String.valueOf(character.getLipSizeValue()));
				Element mouthModifiers = doc.createElement("mouthModifiers");
				bodyMouth.appendChild(mouthModifiers);
				for(OrificeModifier om : OrificeModifier.values()) {
					addAttribute(doc, mouthModifiers, om.toString(), String.valueOf(character.hasFaceOrificeModifier(om)));
				}
				
			Element bodyTongue = doc.createElement("tongue");
			characterBody.appendChild(bodyTongue);
				addAttribute(doc, bodyTongue, "piercedTongue", String.valueOf(character.isPiercedTongue()));
				addAttribute(doc, bodyTongue, "tongueLength", String.valueOf(character.getTongueLengthValue()));
				Element tongueModifiers = doc.createElement("tongueModifiers");
				bodyTongue.appendChild(tongueModifiers);
				for(TongueModifier tm : TongueModifier.values()) {
					addAttribute(doc, tongueModifiers, tm.toString(), String.valueOf(character.hasTongueModifier(tm)));
				}
				
			
			// Hair:
			Element bodyHair = doc.createElement("hair");
			characterBody.appendChild(bodyHair);
				addAttribute(doc, bodyHair, "type", character.getHairType().toString());
				addAttribute(doc, bodyHair, "length", String.valueOf(character.getHairRawLengthValue()));
				addAttribute(doc, bodyHair, "hairStyle", character.getHairStyle().toString());
			
			// Horn:
			Element bodyHorn = doc.createElement("horn");
			characterBody.appendChild(bodyHorn);
				addAttribute(doc, bodyHorn, "type", character.getHornType().toString());
				addAttribute(doc, bodyHorn, "rows", String.valueOf(character.getHornRows()));
			
			// Leg:
			Element bodyLeg = doc.createElement("leg");
			characterBody.appendChild(bodyLeg);
				addAttribute(doc, bodyLeg, "type", character.getLegType().toString());
			
			// Penis:
			Element bodyPenis = doc.createElement("penis");
			characterBody.appendChild(bodyPenis);
				addAttribute(doc, bodyPenis, "type", character.getPenisType().toString());
				addAttribute(doc, bodyPenis, "size", String.valueOf(character.getPenisRawSizeValue()));
				addAttribute(doc, bodyPenis, "pierced", String.valueOf(character.isPiercedPenis()));
				Element penisModifiers = doc.createElement("penisModifiers");
				bodyPenis.appendChild(penisModifiers);
				for(PenisModifier pm : PenisModifier.values()) {
					addAttribute(doc, penisModifiers, pm.toString(), String.valueOf(character.hasPenisModifier(pm)));
				}
				addAttribute(doc, bodyPenis, "elasticity", String.valueOf(character.getUrethraElasticity().getValue()));
				addAttribute(doc, bodyPenis, "plasticity", String.valueOf(character.getUrethraPlasticity().getValue()));
				addAttribute(doc, bodyPenis, "capacity", String.valueOf(character.getPenisRawCapacityValue()));
				addAttribute(doc, bodyPenis, "stretchedCapacity", String.valueOf(character.getPenisStretchedCapacity()));
				addAttribute(doc, bodyPenis, "virgin", String.valueOf(character.isUrethraVirgin()));
				Element urethraModifiers = doc.createElement("urethraModifiers");
				bodyPenis.appendChild(urethraModifiers);
				for(OrificeModifier om : OrificeModifier.values()) {
					addAttribute(doc, urethraModifiers, om.toString(), String.valueOf(character.hasUrethraOrificeModifier(om)));
				}
				
			Element bodyTesticle = doc.createElement("testicles");
			characterBody.appendChild(bodyTesticle);
				addAttribute(doc, bodyTesticle, "testicleSize", String.valueOf(character.getTesticleSize().getValue()));
				addAttribute(doc, bodyTesticle, "cumProduction", String.valueOf(character.getPenisRawCumProductionValue()));
				addAttribute(doc, bodyTesticle, "numberOfTesticles", String.valueOf(character.getPenisNumberOfTesticles()));
				addAttribute(doc, bodyTesticle, "internal", String.valueOf(character.isInternalTesticles()));
			
			Element bodyCum = doc.createElement("cum");
			characterBody.appendChild(bodyCum);
				addAttribute(doc, bodyCum, "flavour", String.valueOf(character.getCumFlavour()));
				Element cumModifiers = doc.createElement("cumModifiers");
				bodyCum.appendChild(cumModifiers);
				for(FluidModifier fm : FluidModifier.values()) {
					addAttribute(doc, cumModifiers, fm.toString(), String.valueOf(character.hasCumModifier(fm)));
				}
				//TODO transformativeEffects;
			
			
			// Skin:
			Element bodySkin = doc.createElement("skin");
			characterBody.appendChild(bodySkin);
				addAttribute(doc, bodySkin, "type", character.getSkinType().toString());
			
			// Tail:
			Element bodyTail = doc.createElement("tail");
			characterBody.appendChild(bodyTail);
				addAttribute(doc, bodyTail, "type", character.getTailType().toString());
				addAttribute(doc, bodyTail, "count", String.valueOf(character.getTailCount()));
			
			// Vagina
			Element bodyVagina = doc.createElement("vagina");
			characterBody.appendChild(bodyVagina);
				addAttribute(doc, bodyVagina, "type", character.getVaginaType().toString());
				addAttribute(doc, bodyVagina, "labiaSize", String.valueOf(character.getVaginaRawLabiaSizeValue()));
				addAttribute(doc, bodyVagina, "clitSize", String.valueOf(character.getVaginaRawClitorisSizeValue()));
				addAttribute(doc, bodyVagina, "pierced", String.valueOf(character.isPiercedVagina()));
				
				addAttribute(doc, bodyVagina, "wetness", String.valueOf(character.getVaginaWetness().getValue()));
				addAttribute(doc, bodyVagina, "elasticity", String.valueOf(character.getVaginaElasticity().getValue()));
				addAttribute(doc, bodyVagina, "plasticity", String.valueOf(character.getVaginaPlasticity().getValue()));
				addAttribute(doc, bodyVagina, "capacity", String.valueOf(character.getVaginaRawCapacityValue()));
				addAttribute(doc, bodyVagina, "stretchedCapacity", String.valueOf(character.getVaginaStretchedCapacity()));
				addAttribute(doc, bodyVagina, "virgin", String.valueOf(character.isVaginaVirgin()));
				
			Element bodyGirlcum = doc.createElement("girlcum");
			characterBody.appendChild(bodyGirlcum);
				addAttribute(doc, bodyGirlcum, "flavour", String.valueOf(character.getGirlcumFlavour()));
				Element girlcumModifiers = doc.createElement("girlcumModifiers");
				bodyGirlcum.appendChild(girlcumModifiers);
				for(FluidModifier fm : FluidModifier.values()) {
					addAttribute(doc, girlcumModifiers, fm.toString(), String.valueOf(character.hasGirlcumModifier(fm)));
				}
				//TODO transformativeEffects;
				
			
			// Wing:
			Element bodyWing = doc.createElement("wing");
			characterBody.appendChild(bodyWing);
			addAttribute(doc, bodyWing, "type", character.getWingType().toString());

//			System.out.println("Difference1: "+(System.nanoTime()-timeStart)/1000000000f);
			
			// PlayerCharacter specific:
			
			// Core:
			Element characterPlayerCoreInfo = doc.createElement("playerCore");
			properties.appendChild(characterPlayerCoreInfo);
			createXMLElementWithValue(doc, characterPlayerCoreInfo, "experience", String.valueOf(((PlayerCharacter)character).getExperience()));
			createXMLElementWithValue(doc, characterPlayerCoreInfo, "levelUpPoints", String.valueOf(((PlayerCharacter)character).getLevelUpPoints()));
			createXMLElementWithValue(doc, characterPlayerCoreInfo, "perkPoints", String.valueOf(((PlayerCharacter)character).getPerkPoints()));
			
			// Sex stats:
			Element characterSexStats = doc.createElement("sexStats");
			properties.appendChild(characterSexStats);
			
			Element characterCumCount = doc.createElement("cumCounts");
			characterSexStats.appendChild(characterCumCount);
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					Element element = doc.createElement("cumCount");
					characterCumCount.appendChild(element);
					
					addAttribute(doc, element, "penetrationType", pt.toString());
					addAttribute(doc, element, "orificeType", ot.toString());
					addAttribute(doc, element, "count", String.valueOf(((PlayerCharacter)character).getCumCount(new SexType(pt, ot))));
				}
			}

			Element characterSexCount = doc.createElement("sexCounts");
			characterSexStats.appendChild(characterSexCount);
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					Element element = doc.createElement("sexCount");
					characterSexCount.appendChild(element);
					
					addAttribute(doc, element, "penetrationType", pt.toString());
					addAttribute(doc, element, "orificeType", ot.toString());
					addAttribute(doc, element, "count", String.valueOf(((PlayerCharacter)character).getSexCount(new SexType(pt, ot))));
				}
			}

			Element characterVirginityTakenBy = doc.createElement("virginityTakenBy");
			characterSexStats.appendChild(characterVirginityTakenBy);
			for(PenetrationType pt : PenetrationType.values()) {
				for(OrificeType ot : OrificeType.values()) {
					if(((PlayerCharacter)character).getVirginityLoss(new SexType(pt, ot))!=null) {
						Element element = doc.createElement("virginity");
						characterVirginityTakenBy.appendChild(element);
						
						addAttribute(doc, element, "penetrationType", pt.toString());
						addAttribute(doc, element, "orificeType", ot.toString());
						addAttribute(doc, element, "takenBy", String.valueOf(((PlayerCharacter)character).getVirginityLoss(new SexType(pt, ot))));
					}
				}
			}
			

//			System.out.println("Difference2: "+(System.nanoTime()-timeStart)/1000000000f);
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer1 = tf.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

//			System.out.println("Difference3: "+(System.nanoTime()-timeStart)/1000000000f);
			
			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
//			System.out.println("Difference4: "+(System.nanoTime()-timeStart)/1000000000f);
			
//			String output = writer.getBuffer().toString();
//			System.out.println("Difference: "+(System.nanoTime()-timeStart)/1000000000f);
//			System.out.println(output);
			
			// Save this xml:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			File dir = new File("data/");
			dir.mkdir();
			
			File dirCharacter = new File("data/characters/");
			dirCharacter.mkdir();
			
			int saveNumber = 0;
			String saveLocation = "data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+".xml";
			if(new File("data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+".xml").exists())
				saveLocation = "data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";

			while(new File("data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml").exists()) {
				saveNumber++;
				saveLocation = "data/characters/"+character.getName()+"_day"+Main.game.getDayNumber()+"("+saveNumber+").xml";
			}
			
			StreamResult result = new StreamResult(new File(saveLocation));
			
			transformer.transform(source, result);
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	private static void createXMLElementWithValue(Document doc, Element parentElement, String elementName, String value){
		Element element = doc.createElement(elementName);
		parentElement.appendChild(element);
		Attr attr = doc.createAttribute("value");
		attr.setValue(value);
		element.setAttributeNode(attr);
	}
	
	private static void addAttribute(Document doc, Element parentElement, String attributeName, String value){
		Attr attr = doc.createAttribute(attributeName);
		attr.setValue(value);
		parentElement.setAttributeNode(attr);
	}
	
	private static StringBuilder characterImportLog;
	public static String getCharacterImportLog() {
		return characterImportLog.toString();
	}
	// This is a complete mess...
	public static PlayerCharacter startLoadingCharacterFromXML(){
		PlayerCharacter importedCharacter = new PlayerCharacter(new NameTriplet("Player"), "", 1, Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, null, WorldType.DOMINION, Dominion.CITY_AUNTS_HOME);
		return importedCharacter;
	}
	public static PlayerCharacter loadCharacterFromXML(File xmlFile, PlayerCharacter importedCharacter){
		
		characterImportLog = new StringBuilder();
		
		if (xmlFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				// Core info:
				NodeList nodes = doc.getElementsByTagName("core");
				Element element = (Element) nodes.item(0);

				// Name:
				importedCharacter.setName(new NameTriplet(((Element)element.getElementsByTagName("name").item(0)).getAttribute("value")));
				characterImportLog.append("</br>Set name: " + ((Element)element.getElementsByTagName("name").item(0)).getAttribute("value"));
				
				// Surname:
				if(element.getElementsByTagName("surname")!=null && element.getElementsByTagName("surname").getLength()>0) {
					importedCharacter.setSurname(((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
					characterImportLog.append("</br>Set surname: " + ((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value"));
				}
				
				// Level:
				importedCharacter.setLevel(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
				characterImportLog.append("</br>Set level: " + Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")));
				
				// Sexual Orientation:
				if(element.getElementsByTagName("sexualOrientation").getLength()!=0) {
					if(!((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value").equals("null")) {
						importedCharacter.setSexualOrientation(SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
						characterImportLog.append("</br>Set Sexual Orientation: " + SexualOrientation.valueOf(((Element)element.getElementsByTagName("sexualOrientation").item(0)).getAttribute("value")));
					} else {
						importedCharacter.setSexualOrientation(SexualOrientation.AMBIPHILIC);
						characterImportLog.append("</br>Set Sexual Orientation: " + SexualOrientation.AMBIPHILIC);
					}
				}
				
				// Temp fix for perk points:
				importedCharacter.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")))-1);
				characterImportLog.append("</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))-1));
				
				String importSavefileVersion = "";
				int extraLevelUpPoints=0;
				// If there is a version number, attributes should be working correctly:
				if(element.getElementsByTagName("version").item(0)!=null) {
					importSavefileVersion = ((Element) element.getElementsByTagName("version").item(0)).getAttribute("value");
					nodes = doc.getElementsByTagName("attributes");
					element = (Element) nodes.item(0);
					for(int i=0; i<element.getElementsByTagName("attribute").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("attribute").item(i));
						
						try {
							importedCharacter.setAttribute(Attribute.valueOf(e.getAttribute("type")), Float.valueOf(e.getAttribute("value")));
							characterImportLog.append("</br>Set Attribute: "+Attribute.valueOf(e.getAttribute("type")).getName() +" to "+ Float.valueOf(e.getAttribute("value")));
						}catch(IllegalArgumentException ex){
						}
					}
					
				} else {
					extraLevelUpPoints = (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5);
					characterImportLog.append("</br>Old character version. Extra LevelUpPoints set to: "+(Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value")) * 5));
				}
				
				// Fetishes:
				nodes = doc.getElementsByTagName("fetishes");
				element = (Element) nodes.item(0);
				if(element!=null) {
					for(int i=0; i<element.getElementsByTagName("fetish").getLength(); i++){
						Element e = ((Element)element.getElementsByTagName("fetish").item(i));
						
						try {
							if(Boolean.valueOf(e.getAttribute("value"))) {
								if(Fetish.valueOf(e.getAttribute("type")) != null) {
									importedCharacter.addFetish(Fetish.valueOf(e.getAttribute("type")));
									characterImportLog.append("</br>Added Fetish: "+Fetish.valueOf(e.getAttribute("type")).getName(importedCharacter));
								}
							}
						}catch(IllegalArgumentException ex){
						}
					}
				}
				
				// Perks:
//				nodes = doc.getElementsByTagName("perks");
//				element = (Element) nodes.item(0);
//				for(int i=0; i<element.getElementsByTagName("perk").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("perk").item(i));
//					
//					try {
//						if(Boolean.valueOf(e.getAttribute("value"))) {
//							importedCharacter.addPerk(Perk.valueOf(e.getAttribute("type")));
//							characterImportLog.append("</br>Added Perk: "+Perk.valueOf(e.getAttribute("type")).getName(importedCharacter));
//						}
//					}catch(IllegalArgumentException ex){
//					}
//				}
				
				// Status Effects:
				nodes = doc.getElementsByTagName("statusEffects");
				element = (Element) nodes.item(0);
				for(int i=0; i<element.getElementsByTagName("statusEffect").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("statusEffect").item(i));
					
					try {
						if(Integer.valueOf(e.getAttribute("value"))!=-1) {
							importedCharacter.addStatusEffect(StatusEffect.valueOf(e.getAttribute("type")), Integer.valueOf(e.getAttribute("value")));
							characterImportLog.append("</br>Added Status Effect: "+StatusEffect.valueOf(e.getAttribute("type")).getName(importedCharacter)+" ("+Integer.valueOf(e.getAttribute("value"))+" minutes)");
						}
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Combat:
				// TODO Combat is all derived from weapons/perks/body parts, so there's no reason for this to even be here...
				
				// Pregnancy: TODO
//				nodes = doc.getElementsByTagName("pregnancy");
//				// Possibilities:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnancyPossibilities").item(0);
//				for(int i=0; i<element.getElementsByTagName("possibility").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("possibility").item(i));
//					
//					try {
//						importedCharacter.getPotentialPartnersAsMother().add(new PregnancyPossibility(
//								null,
//								null,
//								Float.valueOf(e.getAttribute("probability"))));
//						characterImportLog.append("</br>Added Pregnancy Possibility: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Litter:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("pregnantLitter").item(0);
//				for(int i=0; i<element.getElementsByTagName("litter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("litter").item(i));
//					
//					try {
//						importedCharacter.setPregnantLitter(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						characterImportLog.append("</br>Added Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
//				// Birthed Litters:
//				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("birthedLitters").item(0);
//				for(int i=0; i<element.getElementsByTagName("birthedLitter").getLength(); i++){
//					Element e = ((Element)element.getElementsByTagName("birthedLitter").item(i));
//					
//					try {
//						importedCharacter.getLittersBirthed().add(new Litter(
//								Integer.valueOf(e.getAttribute("dayOfConception")),
//								Integer.valueOf(e.getAttribute("dayOfBirth")),
//								null,
//								null,
//								Race.valueOf(e.getAttribute("race")),
//								Integer.valueOf(e.getAttribute("sons")),
//								Integer.valueOf(e.getAttribute("daughters"))));
//						characterImportLog.append("</br>Added Birthed Litter: Father:"+e.getAttribute("fatherName"));
//					}catch(IllegalArgumentException ex){
//					}
//				}
				
				// Body:
				// Core:
				nodes = doc.getElementsByTagName("body");
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("bodyCore").item(0);
				importedCharacter.setFemininity(Integer.valueOf(element.getAttribute("femininity")));
				characterImportLog.append("</br>Body: Set femininity: "+Integer.valueOf(element.getAttribute("femininity")));
				
				importedCharacter.setHeight(Integer.valueOf(element.getAttribute("height")));
				characterImportLog.append("</br>Body: Set height: "+Integer.valueOf(element.getAttribute("height")));
				
				importedCharacter.setPiercedNavel(Boolean.valueOf(element.getAttribute("piercedStomach")));
				characterImportLog.append("</br>Body: Set piercedStomach: "+Boolean.valueOf(element.getAttribute("piercedStomach")));
				
				if(element.getAttribute("bodySize")!=null && element.getAttribute("bodySize").length()>0) {
					importedCharacter.setBodySize(Integer.valueOf(element.getAttribute("bodySize")));
					characterImportLog.append("</br>Body: Set body size: "+Integer.valueOf(element.getAttribute("bodySize")));
				}
				
				if(element.getAttribute("muscle")!=null && element.getAttribute("muscle").length()>0) {
					importedCharacter.setMuscle(Integer.valueOf(element.getAttribute("muscle")));
					characterImportLog.append("</br>Body: Set muscle: "+Integer.valueOf(element.getAttribute("muscle")));
				}
				
				try {
					if(element.getAttribute("pubicHair")!=null && element.getAttribute("pubicHair").length()>0) {
						importedCharacter.setPubicHair(BodyHair.valueOf(element.getAttribute("pubicHair")));
						characterImportLog.append("</br>Body: Set pubicHair: "+importedCharacter.getPubicHair());
					}
				}catch(IllegalArgumentException ex){
				}
				
				
				for(int i=0; i<element.getElementsByTagName("bodyCovering").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("bodyCovering").item(i));
					
					try {
						importedCharacter.setBodyCoveringForXMLImport(BodyCoveringType.valueOf(e.getAttribute("type")), CoveringPattern.valueOf(e.getAttribute("pattern")),
								Colour.valueOf(e.getAttribute("colourPrimary")), Boolean.valueOf(e.getAttribute("glowPrimary")),
								Colour.valueOf(e.getAttribute("colourSecondary")), Boolean.valueOf(e.getAttribute("glowSecondary")));
						
						if(Boolean.valueOf(e.getAttribute("discovered"))) {
							importedCharacter.getBodyCoveringTypesDiscovered().add(BodyCoveringType.valueOf(e.getAttribute("type")));
						}
						
						characterImportLog.append("</br>Body: Set bodyCovering: "+e.getAttribute("type") +" pattern:"+CoveringPattern.valueOf(e.getAttribute("pattern"))
							+" "+Colour.valueOf(e.getAttribute("colourPrimary")) +" glow:"+Boolean.valueOf(e.getAttribute("glowPrimary"))
							+" | "+Colour.valueOf(e.getAttribute("colourSecondary")) +" glow:"+Boolean.valueOf(e.getAttribute("glowSecondary"))
							+" (discovered: "+e.getAttribute("discovered")+")");
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Body parts:

				// Antenna:
				try {
					Element antennae = (Element)((Element) nodes.item(0)).getElementsByTagName("antennae").item(0);
					if (antennae != null){
					importedCharacter.setAntennaType(AntennaType.valueOf(antennae.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Antennae:"+ "</br>type: "+importedCharacter.getAntennaType());

					importedCharacter.setAntennaRows(Integer.valueOf(antennae.getAttribute("rows")));
					characterImportLog.append("</br>rows: "+importedCharacter.getAntennaRows());}
					
				}catch(IllegalArgumentException ex){
				}
				
				// Arm:
				try {
					Element arm = (Element)((Element) nodes.item(0)).getElementsByTagName("arm").item(0);
					
					importedCharacter.setArmType(ArmType.valueOf(arm.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Arm:"+ "</br>type: "+importedCharacter.getArmType());

					importedCharacter.setArmRows(Integer.valueOf(arm.getAttribute("rows")));
					characterImportLog.append("</br>rows: "+importedCharacter.getArmRows());

					importedCharacter.setUnderarmHair(BodyHair.valueOf(arm.getAttribute("underarmHair")));
					characterImportLog.append("</br>underarm hair: "+importedCharacter.getUnderarmHair());
					
				}catch(IllegalArgumentException ex){
				}
				
				
				// Ass:
				try {
					Element ass = (Element)((Element) nodes.item(0)).getElementsByTagName("ass").item(0);
					importedCharacter.setAssType(AssType.valueOf(ass.getAttribute("type")));
					importedCharacter.setAssSize(Integer.valueOf(ass.getAttribute("assSize")));
					importedCharacter.setHipSize(Integer.valueOf(ass.getAttribute("hipSize")));
					
					Element anus = (Element)((Element) nodes.item(0)).getElementsByTagName("anus").item(0);
					if (anus != null){
						importedCharacter.setAssWetness(Integer.valueOf(anus.getAttribute("wetness")));
						importedCharacter.setAssElasticity(Integer.valueOf(anus.getAttribute("elasticity")));
						importedCharacter.setAssPlasticity(Integer.valueOf(anus.getAttribute("plasticity")));
						importedCharacter.setAssCapacity(Float.valueOf(anus.getAttribute("capacity")));
						importedCharacter.setAssStretchedCapacity(Float.valueOf(anus.getAttribute("stretchedCapacity")));
						importedCharacter.setAssVirgin(Boolean.valueOf(anus.getAttribute("virgin")));
						importedCharacter.setAssBleached(Boolean.valueOf(anus.getAttribute("bleached")));
						importedCharacter.setAssHair(BodyHair.valueOf(anus.getAttribute("assHair")));
					}
					
					
					characterImportLog.append("</br></br>Body: Ass:"
							+ "</br>type: "+importedCharacter.getAssType()
							+ "</br>assSize: "+importedCharacter.getAssSize()
							+ "</br>hipSize: "+importedCharacter.getHipSize());
					
					if (anus != null) {
						characterImportLog.append("</br></br>Anus:"
								+ "</br>wetness: "+importedCharacter.getAssWetness()
								+ "</br>elasticity: "+importedCharacter.getAssElasticity()
								+ "</br>elasticity: "+importedCharacter.getAssPlasticity()
								+ "</br>capacity: "+importedCharacter.getAssCapacity()
								+ "</br>stretchedCapacity: "+importedCharacter.getAssStretchedCapacity()
								+ "</br>virgin: "+importedCharacter.isAssVirgin()
								+ "</br>bleached: "+importedCharacter.isAssBleached()
								+ "</br>assHair: "+importedCharacter.getAssHair()
								+"</br>Modifiers:");
						Element anusModifiers = (Element)anus.getElementsByTagName("anusModifiers").item(0);
						
						for(OrificeModifier om : OrificeModifier.values()) {
							if(Boolean.valueOf(anusModifiers.getAttribute(om.toString()))) {
								importedCharacter.addAssOrificeModifier(om);
								characterImportLog.append("</br>"+om.toString()+":true");
							} else {
								characterImportLog.append("</br>"+om.toString()+":false");
							}
						}
					}
				}catch(IllegalArgumentException ex){
				}
				
				
				// Breasts:
				try {
					Element breasts = (Element)((Element) nodes.item(0)).getElementsByTagName("breasts").item(0);
						importedCharacter.setBreastType(BreastType.valueOf(breasts.getAttribute("type")));
						if(breasts.getAttribute("shape").length()!=0) {
							importedCharacter.setBreastShape(BreastShape.valueOf(breasts.getAttribute("shape")));
						}
						importedCharacter.setBreastSize(Integer.valueOf(breasts.getAttribute("size")));
						importedCharacter.setBreastRows(Integer.valueOf(breasts.getAttribute("rows")));
						importedCharacter.setBreastLactation(Integer.valueOf(breasts.getAttribute("lactation")));
						importedCharacter.setNippleCountPerBreast(Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")));

					Element nipples = (Element)((Element) nodes.item(0)).getElementsByTagName("nipples").item(0);
						importedCharacter.setNippleElasticity(Integer.valueOf(nipples.getAttribute("elasticity")));
						importedCharacter.setNipplePlasticity(Integer.valueOf(nipples.getAttribute("plasticity")));
						importedCharacter.setNippleCapacity(Float.valueOf(nipples.getAttribute("capacity")));
						importedCharacter.setNippleStretchedCapacity(Float.valueOf(nipples.getAttribute("stretchedCapacity")));
						importedCharacter.setNippleVirgin(Boolean.valueOf(nipples.getAttribute("virgin")));
						importedCharacter.setPiercedNipples(Boolean.valueOf(nipples.getAttribute("pierced")));
						importedCharacter.setNippleSize(Integer.valueOf(nipples.getAttribute("nippleSize")));
						importedCharacter.setNippleShape(NippleShape.valueOf(nipples.getAttribute("nippleShape")));
						importedCharacter.setAreolaeSize(Integer.valueOf(nipples.getAttribute("areolaeSize")));
						importedCharacter.setAreolaeShape(AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));
					
					characterImportLog.append("</br></br>Body: Breasts:"
							+ "</br>type: "+importedCharacter.getBreastType()
							+ "</br>size: "+importedCharacter.getBreastSize()
							+ "</br>rows: "+importedCharacter.getBreastRows()
							+ "</br>lactation: "+importedCharacter.getBreastRawLactationValue()
							+ "</br>nippleCountPerBreast: "+importedCharacter.getNippleCountPerBreast()
							
							+ "</br></br>Nipples:"
							+ "</br>elasticity: "+importedCharacter.getNippleElasticity()
							+ "</br>plasticity: "+importedCharacter.getNipplePlasticity()
							+ "</br>capacity: "+importedCharacter.getNippleRawCapacityValue()
							+ "</br>stretchedCapacity: "+importedCharacter.getNippleStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isNippleVirgin()
							+ "</br>pierced: "+importedCharacter.isPiercedNipple()
							+ "</br>nippleSize: "+importedCharacter.getNippleSize()
							+ "</br>nippleShape: "+importedCharacter.getNippleShape()
							+ "</br>areolaeSize: "+importedCharacter.getAreolaeSize()
							+ "</br>areolaeShape: "+importedCharacter.getAreolaeShape()
							+"</br>Modifiers:");
					
					Element nippleModifiers = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
					for(OrificeModifier om : OrificeModifier.values()) {
						if(Boolean.valueOf(nippleModifiers.getAttribute(om.toString()))) {
							importedCharacter.addNippleOrificeModifier(om);
							characterImportLog.append("</br>"+om.toString()+":true");
						} else {
							characterImportLog.append("</br>"+om.toString()+":false");
						}
					}
					
					characterImportLog.append("</br></br>Milk:");
					
					Element milk = (Element)((Element) nodes.item(0)).getElementsByTagName("milk").item(0);
					importedCharacter.setMilkFlavour(FluidFlavour.valueOf(milk.getAttribute("flavour")));
					
					characterImportLog.append(
							" flavour: "+importedCharacter.getMilkFlavour()
							+ "</br>Modifiers:");
					
					Element milkModifiers = (Element)milk.getElementsByTagName("milkModifiers").item(0);
					for(FluidModifier fm : FluidModifier.values()) {
						if(Boolean.valueOf(milkModifiers.getAttribute(fm.toString()))) {
							importedCharacter.addMilkModifier(fm);
							characterImportLog.append("</br>"+fm.toString()+":true");
						} else {
							characterImportLog.append("</br>"+fm.toString()+":false");
						}
					}
					
					
				}catch(IllegalArgumentException ex){
				}
				
				// Ear:
				try {
					Element ear = (Element)((Element) nodes.item(0)).getElementsByTagName("ear").item(0);
					importedCharacter.setEarType(EarType.valueOf(ear.getAttribute("type")));
					importedCharacter.setPiercedEar(Boolean.valueOf(ear.getAttribute("pierced")));
					characterImportLog.append("</br></br>Body: Ear:"
							+ "</br>type: "+importedCharacter.getEarType()
							+ "</br>pierced: "+importedCharacter.isPiercedEar());
					
				}catch(IllegalArgumentException ex){
				}
				
				// Eye:
				try {
					Element eye = (Element)((Element) nodes.item(0)).getElementsByTagName("eye").item(0);
					String eyeTypeFromSave = eye.getAttribute("type");
					if (importSavefileVersion.startsWith("0.1.82") || importSavefileVersion.startsWith("0.1.83P") || importSavefileVersion.isEmpty()) { //TODO replace with proper version checks!
						Map<String, String> eyeTypeConverterMap = new HashMap<>();
						eyeTypeConverterMap.put("EYE_HUMAN", "HUMAN");
						eyeTypeConverterMap.put("EYE_ANGEL", "ANGEL");
						eyeTypeConverterMap.put("EYE_DEMON_COMMON", "DEMON_COMMON");
						eyeTypeConverterMap.put("EYE_DOG_MORPH", "DOG_MORPH");
						eyeTypeConverterMap.put("EYE_LYCAN", "LYCAN");
						eyeTypeConverterMap.put("EYE_FELINE", "CAT_MORPH");
						eyeTypeConverterMap.put("EYE_SQUIRREL", "SQUIRREL_MORPH");
						eyeTypeConverterMap.put("EYE_HORSE_MORPH", "HORSE_MORPH");
						eyeTypeConverterMap.put("EYE_HARPY", "HARPY");
						eyeTypeConverterMap.put("EYE_SLIME", "SLIME");
						eyeTypeFromSave = eyeTypeConverterMap.get(eyeTypeFromSave);
					}
					importedCharacter.setEyeType(EyeType.valueOf(eyeTypeFromSave));
					importedCharacter.setEyePairs(Integer.valueOf(eye.getAttribute("eyePairs")));
					importedCharacter.setIrisShape(EyeShape.valueOf(eye.getAttribute("irisShape")));
					importedCharacter.setPupilShape(EyeShape.valueOf(eye.getAttribute("pupilShape")));
					
					characterImportLog.append("</br></br>Body: Eye:"
							+ "</br>type: "+importedCharacter.getEyeType()
							+ "</br>pairs: "+importedCharacter.getEyePairs()
							+ "</br>iris shape: "+importedCharacter.getIrisShape()
							+ "</br>pupil shape: "+importedCharacter.getPupilShape());
					
				}catch(IllegalArgumentException ex){
				}
				
				// Face:
				try {
					Element face = (Element)((Element) nodes.item(0)).getElementsByTagName("face").item(0);
						importedCharacter.setFaceType(FaceType.valueOf(face.getAttribute("type")));
						importedCharacter.setPiercedNose(Boolean.valueOf(face.getAttribute("piercedNose")));
						importedCharacter.setFacialHair(BodyHair.valueOf(face.getAttribute("facialHair")));
					
						characterImportLog.append("</br></br>Body: Face: "
								+ "</br>type: "+importedCharacter.getFaceType()
								+ "</br>piercedNose: "+importedCharacter.isPiercedNose()
								+ "</br>facial hair: "+importedCharacter.getFacialHair()
								
								+ "</br></br>Mouth: ");
						
					Element mouth = (Element)((Element) nodes.item(0)).getElementsByTagName("mouth").item(0);
						importedCharacter.setFaceElasticity(Integer.valueOf(mouth.getAttribute("elasticity")));
						importedCharacter.setFacePlasticity(Integer.valueOf(mouth.getAttribute("plasticity")));
						importedCharacter.setFaceCapacity(Float.valueOf(mouth.getAttribute("capacity")));
						importedCharacter.setFaceStretchedCapacity(Float.valueOf(mouth.getAttribute("stretchedCapacity")));
						importedCharacter.setFaceVirgin(Boolean.valueOf(mouth.getAttribute("virgin")));
						importedCharacter.setPiercedLip(Boolean.valueOf(mouth.getAttribute("piercedLip")));
						importedCharacter.setLipSize(Integer.valueOf(mouth.getAttribute("lipSize")));
						
						characterImportLog.append(
								"</br>elasticity: "+importedCharacter.getFaceElasticity()
								+ "</br>plasticity: "+importedCharacter.getFacePlasticity()
								+ "</br>capacity: "+importedCharacter.getFaceCapacity()
								+ "</br>stretchedCapacity: "+importedCharacter.getFaceStretchedCapacity()
								+ "</br>virgin: "+importedCharacter.isFaceVirgin()
								+ "</br>piercedLip: "+importedCharacter.isPiercedLip()
								+ "</br>lipSize: "+importedCharacter.getLipSize()
								+ "</br>Modifiers: ");
						
					Element mouthModifiers = (Element)mouth.getElementsByTagName("mouthModifiers").item(0);
						for(OrificeModifier om : OrificeModifier.values()) {
							if(Boolean.valueOf(mouthModifiers.getAttribute(om.toString()))) {
								importedCharacter.addFaceOrificeModifier(om);
								characterImportLog.append("</br>"+om.toString()+":true");
							} else {
								characterImportLog.append("</br>"+om.toString()+":false");
							}
						}

					Element tongue = (Element)((Element) nodes.item(0)).getElementsByTagName("tongue").item(0);
						importedCharacter.setPiercedTongue(Boolean.valueOf(tongue.getAttribute("piercedTongue")));
						importedCharacter.setTongueLength(Integer.valueOf(tongue.getAttribute("tongueLength")));
						
						characterImportLog.append(
								"</br></br>Tongue: "
								+ "</br>piercedTongue: "+importedCharacter.isPiercedTongue()
								+ "</br>tongueLength: "+importedCharacter.getTongueLength()
								+ "</br>Modifiers: ");
						
						Element tongueModifiers = (Element)tongue.getElementsByTagName("tongueModifiers").item(0);
						for(TongueModifier tm : TongueModifier.values()) {
							if(Boolean.valueOf(tongueModifiers.getAttribute(tm.toString()))) {
								importedCharacter.addTongueModifier(tm);
								characterImportLog.append("</br>"+tm.toString()+":true");
							} else {
								characterImportLog.append("</br>"+tm.toString()+":false");
							}
						}
					
				}catch(IllegalArgumentException ex){
				}
				
				// Hair:
				try {
					Element hair = (Element)((Element) nodes.item(0)).getElementsByTagName("hair").item(0);
					String hairTypeFromSave = hair.getAttribute("type");
					if (importSavefileVersion.startsWith("0.1.82") || importSavefileVersion.startsWith("0.1.83P") || importSavefileVersion.isEmpty()) { //TODO replace with proper version checks!
						Map<String, String> hairTypeConverterMap = new HashMap<>();
						hairTypeConverterMap.put("HAIR_HUMAN", "HUMAN");
						hairTypeConverterMap.put("HAIR_ANGEL", "ANGEL");
						hairTypeConverterMap.put("HAIR_DEMON", "DEMON_COMMON");
						hairTypeConverterMap.put("HAIR_CANINE_FUR", "DOG_MORPH");
						hairTypeConverterMap.put("HAIR_LYCAN_FUR", "LYCAN");
						hairTypeConverterMap.put("HAIR_FELINE_FUR", "CAT_MORPH");
						hairTypeConverterMap.put("HAIR_HORSE_HAIR", "HORSE_MORPH");
						hairTypeConverterMap.put("HAIR_SQUIRREL_FUR", "SQUIRREL_MORPH");
						hairTypeConverterMap.put("HAIR_SLIME", "SLIME");
						hairTypeConverterMap.put("HAIR_HARPY", "HARPY");
						hairTypeFromSave = hairTypeConverterMap.get(hairTypeFromSave);
					}
					importedCharacter.setHairType(HairType.valueOf(hairTypeFromSave));
					importedCharacter.setHairLength(Integer.valueOf(hair.getAttribute("length")));
					importedCharacter.setHairStyle(HairStyle.valueOf(hair.getAttribute("hairStyle")));
						
					
					characterImportLog.append("</br></br>Body: Hair: "
							+ "</br>type: "+importedCharacter.getHairType()
							+ "</br>length: "+importedCharacter.getHairLength()
							+ "</br>hairStyle: "+importedCharacter.getHairStyle());
					
				}catch(IllegalArgumentException ex){
				}
				
				// Horn:
				try {
					Element horn = (Element)((Element) nodes.item(0)).getElementsByTagName("horn").item(0);
					importedCharacter.setHornType(HornType.valueOf(horn.getAttribute("type")));
					importedCharacter.setHornRows(Integer.valueOf(horn.getAttribute("rows")));
					
					characterImportLog.append("</br></br>Body: Horn: "
							+ "</br>type: "+importedCharacter.getHornType()
							+ "</br>rows: "+importedCharacter.getHornRows());
				}catch(IllegalArgumentException ex){
				}
				
				// Leg:
				try {
					Element leg = (Element)((Element) nodes.item(0)).getElementsByTagName("leg").item(0);
					importedCharacter.setLegType(LegType.valueOf(leg.getAttribute("type")));
					
					characterImportLog.append("</br></br>Body: Leg: "
							+ "</br>type: "+importedCharacter.getLegType());
					
				}catch(IllegalArgumentException ex){
				}
				
				// Penis:
				try {
					Element penis = (Element)((Element) nodes.item(0)).getElementsByTagName("penis").item(0);
					importedCharacter.setPenisType(PenisType.valueOf(penis.getAttribute("type")));
					importedCharacter.setPenisSize(Integer.valueOf(penis.getAttribute("size")));
					importedCharacter.setPiercedPenis(Boolean.valueOf(penis.getAttribute("pierced")));
					
					characterImportLog.append("</br></br>Body: Penis: "
							+ "</br>type: "+importedCharacter.getPenisType()
							+ "</br>size: "+importedCharacter.getPenisRawSizeValue()
							+ "</br>pierced: "+importedCharacter.isPiercedPenis()
							+ "</br>Penis Modifiers: ");
					
					Element penisModifiers = (Element)penis.getElementsByTagName("penisModifiers").item(0);
					for(PenisModifier pm : PenisModifier.values()) {
						if(penisModifiers != null && Boolean.valueOf(penisModifiers.getAttribute(pm.toString()))) {
							importedCharacter.addPenisModifier(pm);
							characterImportLog.append("</br>"+pm.toString()+":true");
						} else {
							characterImportLog.append("</br>"+pm.toString()+":false");
						}
					}
					
					
					importedCharacter.setUrethraElasticity(Integer.valueOf(penis.getAttribute("elasticity")));
					importedCharacter.setUrethraPlasticity(Integer.valueOf(penis.getAttribute("plasticity")));
					importedCharacter.setPenisCapacity(Float.valueOf(penis.getAttribute("capacity")));
					importedCharacter.setPenisStretchedCapacity(Float.valueOf(penis.getAttribute("stretchedCapacity")));
					importedCharacter.setUrethraVirgin(Boolean.valueOf(penis.getAttribute("virgin")));

					characterImportLog.append(
							"</br>elasticity: "+importedCharacter.getUrethraElasticity()
							+ "</br>plasticity: "+importedCharacter.getUrethraPlasticity()
							+ "</br>capacity: "+importedCharacter.getPenisCapacity()
							+ "</br>stretchedCapacity: "+importedCharacter.getPenisStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isUrethraVirgin()
							+ "</br>Urethra Modifiers:");
					
					Element urethraModifiers = (Element)penis.getElementsByTagName("urethraModifiers").item(0);
					for(OrificeModifier om : OrificeModifier.values()) {
						if(Boolean.valueOf(urethraModifiers.getAttribute(om.toString()))) {
							importedCharacter.addUrethraOrificeModifier(om);
							characterImportLog.append("</br>"+om.toString()+":true");
						} else {
							characterImportLog.append("</br>"+om.toString()+":false");
						}
					}
					
					Element testicles = (Element)((Element) nodes.item(0)).getElementsByTagName("testicles").item(0);
						importedCharacter.setCumProduction(Integer.valueOf(testicles.getAttribute("cumProduction")));
						importedCharacter.setPenisNumberOfTesticles(Integer.valueOf(testicles.getAttribute("numberOfTesticles")));
						importedCharacter.setTesticleSize(Integer.valueOf(testicles.getAttribute("testicleSize")));
						importedCharacter.setInternalTesticles(Boolean.valueOf(testicles.getAttribute("internal")));
					
					characterImportLog.append("</br></br>Testicles: "
							+ "</br>cumProduction: "+importedCharacter.getPenisRawCumProductionValue()
							+ "</br>numberOfTesticles: "+importedCharacter.getPenisNumberOfTesticles()
							+ "</br>testicleSize: "+importedCharacter.getTesticleSize()
							+ "</br>internal: "+importedCharacter.isInternalTesticles());
					
					
					characterImportLog.append("</br></br>Cum:");
					
					Element cum = (Element)((Element) nodes.item(0)).getElementsByTagName("cum").item(0);
					importedCharacter.setCumFlavour(FluidFlavour.valueOf(cum.getAttribute("flavour")));
					
					characterImportLog.append(
							" flavour: "+importedCharacter.getCumFlavour()
							+ "</br>Modifiers:");
					
					Element cumModifiers = (Element)cum.getElementsByTagName("cumModifiers").item(0);
					for(FluidModifier fm : FluidModifier.values()) {
						if(Boolean.valueOf(cumModifiers.getAttribute(fm.toString()))) {
							importedCharacter.addCumModifier(fm);
							characterImportLog.append("</br>"+fm.toString()+":true");
						} else {
							characterImportLog.append("</br>"+fm.toString()+":false");
						}
					}
					
				}catch(IllegalArgumentException ex){
				}
				
				// Skin:
				try {
					Element skin = (Element)((Element) nodes.item(0)).getElementsByTagName("skin").item(0);
					String skinTypeFromSave = skin.getAttribute("type");
					if (importSavefileVersion.startsWith("0.1.82") || importSavefileVersion.startsWith("0.1.83P") || importSavefileVersion.isEmpty()) { //TODO replace with proper version checks!
						Map<String, String> skinTypeConverterMap = new HashMap<>();
						skinTypeConverterMap.put("HUMAN", "HUMAN");
						skinTypeConverterMap.put("ANGEL", "ANGEL");
						skinTypeConverterMap.put("DEMON_COMMON", "DEMON_COMMON");
						skinTypeConverterMap.put("CANINE_FUR", "DOG_MORPH");
						skinTypeConverterMap.put("LYCAN_FUR", "LYCAN");
						skinTypeConverterMap.put("FELINE_FUR", "CAT_MORPH");
						skinTypeConverterMap.put("SQUIRREL_FUR", "SQUIRREL_MORPH");
						skinTypeConverterMap.put("HORSE_HAIR", "HORSE_MORPH");
						skinTypeConverterMap.put("SLIME", "SLIME");
						skinTypeConverterMap.put("FEATHERS", "HARPY");
						skinTypeFromSave = skinTypeConverterMap.get(skinTypeFromSave);
					}
					importedCharacter.setSkinType(SkinType.valueOf(skinTypeFromSave));
					characterImportLog.append("</br></br>Body: Skin: "
							+ "</br>type: "+importedCharacter.getSkinType());
				}catch(IllegalArgumentException ex){
				}
				
				// Tail:
				try {
					Element tail = (Element)((Element) nodes.item(0)).getElementsByTagName("tail").item(0);
					importedCharacter.setTailType(TailType.valueOf(tail.getAttribute("type")));
					importedCharacter.setTailCount(Integer.valueOf(tail.getAttribute("count")));
					
					characterImportLog.append("</br></br>Body: Tail: "
							+ "</br>type: "+importedCharacter.getTailType()
							+ "</br>count: "+importedCharacter.getTailCount());
				}catch(IllegalArgumentException ex){
				}
				
				
				// Vagina:
				try {
					Element vagina = (Element)((Element) nodes.item(0)).getElementsByTagName("vagina").item(0);
					importedCharacter.setVaginaType(VaginaType.valueOf(vagina.getAttribute("type")), true);
					if(vagina.getAttribute("labiaSize").length()!=0) {
						importedCharacter.setVaginaLabiaSize(Integer.valueOf(vagina.getAttribute("labiaSize")));
					}
					importedCharacter.setVaginaClitorisSize(Integer.valueOf(vagina.getAttribute("clitSize")));
					importedCharacter.setPiercedVagina(Boolean.valueOf(vagina.getAttribute("pierced")));

					importedCharacter.setVaginaWetness(Integer.valueOf(vagina.getAttribute("wetness")));
					importedCharacter.setVaginaElasticity(Integer.valueOf(vagina.getAttribute("elasticity")));
					importedCharacter.setVaginaPlasticity(Integer.valueOf(vagina.getAttribute("plasticity")));
					importedCharacter.setVaginaCapacity(Float.valueOf(vagina.getAttribute("capacity")));
					importedCharacter.setVaginaStretchedCapacity(Float.valueOf(vagina.getAttribute("stretchedCapacity")));
					importedCharacter.setVaginaVirgin(Boolean.valueOf(vagina.getAttribute("virgin")));
					

					characterImportLog.append("</br></br>Body: Vagina: "
							+ "</br>type: "+importedCharacter.getVaginaType()
							+ "</br>clitSize: "+importedCharacter.getVaginaClitorisSize()
							+ "</br>pierced: "+importedCharacter.isPiercedVagina()

							+ "</br>wetness: "+importedCharacter.getVaginaWetness()
							+ "</br>elasticity: "+importedCharacter.getVaginaElasticity()
							+ "</br>plasticity: "+importedCharacter.getVaginaPlasticity()
							+ "</br>capacity: "+importedCharacter.getVaginaCapacity()
							+ "</br>stretchedCapacity: "+importedCharacter.getVaginaStretchedCapacity()
							+ "</br>virgin: "+importedCharacter.isVaginaVirgin()
							
							+ "</br></br>Girlcum:");
					
					Element girlcum = (Element)((Element) nodes.item(0)).getElementsByTagName("girlcum").item(0);
					importedCharacter.setGirlcumFlavour(FluidFlavour.valueOf(girlcum.getAttribute("flavour")));
					
					characterImportLog.append(
							" flavour: "+importedCharacter.getGirlcumFlavour()
							+ "</br>Modifiers:");
					
					Element girlcumModifiers = (Element)girlcum.getElementsByTagName("girlcumModifiers").item(0);
					for(FluidModifier fm : FluidModifier.values()) {
						if(Boolean.valueOf(girlcumModifiers.getAttribute(fm.toString()))) {
							importedCharacter.addGirlcumModifier(fm);
							characterImportLog.append("</br>"+fm.toString()+":true");
						} else {
							characterImportLog.append("</br>"+fm.toString()+":false");
						}
					}
					
					
				}catch(IllegalArgumentException ex){
				}
				
				// Wing: TODO
				try {
					Element wing = (Element)((Element) nodes.item(0)).getElementsByTagName("wing").item(0);
					importedCharacter.setWingType(WingType.valueOf(wing.getAttribute("type")));
					characterImportLog.append("</br></br>Body: Wing: "
							+ "</br>type: "+importedCharacter.getWingType()+"</br>");
				}catch(IllegalArgumentException ex){
				}
				
				// Player Core info:
				nodes = doc.getElementsByTagName("playerCore");
				element = (Element) nodes.item(0);

				// Experience:
				importedCharacter.incrementExperience(Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
				characterImportLog.append("</br>Set experience: " + Integer.valueOf(((Element)element.getElementsByTagName("experience").item(0)).getAttribute("value")));
				
				// Level up points:
				importedCharacter.setLevelUpPoints(Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints);
				characterImportLog.append("</br>Set levelUpPoints: " + (Integer.valueOf(((Element)element.getElementsByTagName("levelUpPoints").item(0)).getAttribute("value")) + extraLevelUpPoints));

				// Perk points:
				//importedCharacter.setPerkPoints(Integer.valueOf(((Element)element.getElementsByTagName("perkPoints").item(0)).getAttribute("value")));
//				importedCharacter.setPerkPoints((Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
//				
//				characterImportLog.append("</br>Set perkPoints: (TEMP FIX) " + (Integer.valueOf(((Element)element.getElementsByTagName("level").item(0)).getAttribute("value"))));
				
				// Sex stats:
				nodes = doc.getElementsByTagName("sexStats");
				
				// Cum counts:
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("cumCounts").item(0);
				for(int i=0; i<element.getElementsByTagName("cumCount").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("cumCount").item(i));
					
					try {
						for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
							importedCharacter.incrementCumCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
						characterImportLog.append("</br>Added cum count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Sex counts:
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("sexCounts").item(0);
				for(int i=0; i<element.getElementsByTagName("sexCount").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("sexCount").item(i));
					
					try {
						for(int it =0 ; it<Integer.valueOf(e.getAttribute("count")) ; it++)
							importedCharacter.incrementSexCount(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))));
						characterImportLog.append("</br>Added sex count:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" x "+Integer.valueOf(e.getAttribute("count")));
					}catch(IllegalArgumentException ex){
					}
				}
				
				// Virginity losses:
				element = (Element) ((Element) nodes.item(0)).getElementsByTagName("virginityTakenBy").item(0);
				for(int i=0; i<element.getElementsByTagName("virginity").getLength(); i++){
					Element e = ((Element)element.getElementsByTagName("virginity").item(i));

					importedCharacter.setVirginityLoss(new SexType(PenetrationType.valueOf(e.getAttribute("penetrationType")), OrificeType.valueOf(e.getAttribute("orificeType"))), e.getAttribute("takenBy"));
					characterImportLog.append("</br>Added virginity loss:"+e.getAttribute("penetrationType")+" "+e.getAttribute("orificeType")+" (taken by:"+e.getAttribute("takenBy")+")");
					
				}
				
				
				importedCharacter.setLocation(new Vector2i(0, 0));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return importedCharacter;
	}
	
	
	public static Body generateBody(Gender startingGender, GameCharacter mother, GameCharacter father) {
		RacialBody startingBodyType = RacialBody.HUMAN;
		RacialBody motherBody = RacialBody.valueOfRace(mother.getRace());
		RacialBody fatherBody = RacialBody.valueOfRace(father.getRace());
		RaceStage stage = RaceStage.HUMAN;
		boolean takesAfterMother = true;
		boolean raceFromMother = true;
		boolean feminineGender = startingGender.isFeminine();
		NPC blankNPC = Main.game.getGenericAndrogynousNPC();
		GameCharacter parentTakesAfter = mother;
		
		// Core body type is random:
		if(Math.random()<=0.5) {
			startingBodyType = motherBody;
			stage = mother.getRaceStage();
		} else {
			startingBodyType = fatherBody;
			stage = father.getRaceStage();
			raceFromMother = false;
		}
		
		
		Body body = generateBody(startingGender, startingBodyType, stage);
		
		// Genetics! (Sort of...)
		
		// Takes other features from the parent closest to their femininity:
		if(Math.abs(mother.getFemininityValue()-body.getFemininity()) > Math.abs(father.getFemininityValue()-body.getFemininity())) {
			takesAfterMother = false;
			parentTakesAfter = father;
		}
		
		float takesAfterMotherChance = takesAfterMother?0.75f:0.25f;
		
		List<BodyCoveringType> typesToInfluence = new ArrayList<>();
		// Skin & fur colours:
		for(BodyPartInterface bp : body.getAllBodyParts()){
			if(bp.getType().getBodyCoveringType()!=null
					&& bp.getType().getBodyCoveringType().getRace()!=null
					&& !(bp instanceof Eye)) {
				
				typesToInfluence.add(bp.getType().getBodyCoveringType());
			}
		}
		typesToInfluence.add(BodyCoveringType.ANUS);
		typesToInfluence.add(BodyCoveringType.NIPPLES);
		typesToInfluence.add(BodyCoveringType.MOUTH);
		typesToInfluence.add(BodyCoveringType.TONGUE);
		
		if(raceFromMother) {
			typesToInfluence = setCoveringColours(body, mother, typesToInfluence);
			setCoveringColours(body, father, typesToInfluence);
		} else {
			typesToInfluence = setCoveringColours(body, father, typesToInfluence);
			setCoveringColours(body, mother, typesToInfluence);
		}
		
		body.updateCoverings(false, false, true, false);
		
		
		// Iris colour:
		if(Math.random()>=0.9f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(body.getEye().getType().getBodyCoveringType(),
						new Covering(body.getEye().getType().getBodyCoveringType(), mother.getEyeIrisCovering().getPattern(),
								mother.getEyeIrisCovering().getPrimaryColour(), mother.getEyeIrisCovering().isPrimaryGlowing(),
								mother.getEyeIrisCovering().getPrimaryColour(), mother.getEyeIrisCovering().isPrimaryGlowing()));
			} else {
				body.getCoverings().put(body.getEye().getType().getBodyCoveringType(),
						new Covering(body.getEye().getType().getBodyCoveringType(), father.getEyeIrisCovering().getPattern(),
								father.getEyeIrisCovering().getPrimaryColour(), father.getEyeIrisCovering().isPrimaryGlowing(),
								father.getEyeIrisCovering().getPrimaryColour(), father.getEyeIrisCovering().isPrimaryGlowing()));
			}
		}
		// Pupil colour:
		if(Math.random()>=0.4f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getCoverings().put(BodyCoveringType.EYE_PUPILS,
						new Covering(body.getEye().getType().getBodyCoveringType(), mother.getEyePupilCovering().getPattern(),
								mother.getEyePupilCovering().getPrimaryColour(), mother.getEyePupilCovering().isPrimaryGlowing(),
								mother.getEyePupilCovering().getPrimaryColour(), mother.getEyePupilCovering().isPrimaryGlowing()));
			} else {
				body.getCoverings().put(BodyCoveringType.EYE_PUPILS,
						new Covering(body.getEye().getType().getBodyCoveringType(), father.getEyePupilCovering().getPattern(),
								father.getEyePupilCovering().getPrimaryColour(), father.getEyePupilCovering().isPrimaryGlowing(),
								father.getEyePupilCovering().getPrimaryColour(), father.getEyePupilCovering().isPrimaryGlowing()));
			}
		}
		
		// Body core:
		// Height:
		body.setHeight(getSizeFromGenetics(
				body.getHeightValue(),
				true, mother.getHeightValue(),
				true, father.getHeightValue()));
		
		// Femininity:
		switch(startingGender.getType()) {
			case FEMININE:
				if(takesAfterMother) {
					if(mother.getFemininityValue()>=Femininity.FEMININE.getMinimumFemininity()) {
						body.setFemininity(mother.getFemininityValue());
					}
				} else {
					if(father.getFemininityValue()>=Femininity.FEMININE.getMinimumFemininity()) {
						body.setFemininity(father.getFemininityValue());
					}
				}
				break;
			case NEUTRAL:
				if(takesAfterMother) {
					if(mother.getFemininity()==Femininity.ANDROGYNOUS) {
						body.setFemininity(mother.getFemininityValue());
					}
				} else {
					if(father.getFemininity()==Femininity.ANDROGYNOUS) {
						body.setFemininity(father.getFemininityValue());
					}
				}
				break;
			case MASCULINE:
				if(takesAfterMother) {
					if(mother.getFemininityValue()<Femininity.ANDROGYNOUS.getMinimumFemininity()) {
						body.setFemininity(mother.getFemininityValue());
					}
				} else {
					if(father.getFemininityValue()<Femininity.ANDROGYNOUS.getMinimumFemininity()) {
						body.setFemininity(father.getFemininityValue());
					}
				}
				break;
		}
		
		// Body size:
		int minimumSize = Math.min(mother.getBodySizeValue(), father.getBodySizeValue()) - Util.random.nextInt(5);
		int maximumSize = Math.min(mother.getBodySizeValue(), father.getBodySizeValue()) + Util.random.nextInt(5);
		if(takesAfterMother) {
			minimumSize = Math.max(minimumSize, (feminineGender?motherBody.getFemaleBodySize()-30:motherBody.getMaleBodySize()-30));
			maximumSize = Math.max(maximumSize, (feminineGender?motherBody.getFemaleBodySize()+30:motherBody.getMaleBodySize()+30));
		} else {
			minimumSize = Math.max(minimumSize, (feminineGender?fatherBody.getFemaleBodySize()-30:fatherBody.getMaleBodySize()-30));
			maximumSize = Math.max(maximumSize, (feminineGender?fatherBody.getFemaleBodySize()+30:fatherBody.getMaleBodySize()+30));
		}
		int variance = (maximumSize==minimumSize?0:Util.random.nextInt(maximumSize-minimumSize));
		body.setBodySize(minimumSize + variance);
		
		// Muscle:
		int minimumMuscle = Math.min(mother.getMuscleValue(), father.getMuscleValue()) - Util.random.nextInt(5);
		int maximumMuscle = Math.min(mother.getMuscleValue(), father.getMuscleValue()) + Util.random.nextInt(5);
		if(takesAfterMother) {
			minimumMuscle = Math.max(minimumMuscle, (feminineGender?motherBody.getFemaleMuscle()-30:motherBody.getMaleMuscle()-30));
			maximumMuscle = Math.max(maximumMuscle, (feminineGender?motherBody.getFemaleMuscle()+30:motherBody.getMaleMuscle()+30));
		} else {
			minimumMuscle = Math.max(minimumMuscle, (feminineGender?fatherBody.getFemaleMuscle()-30:fatherBody.getMaleMuscle()-30));
			maximumMuscle = Math.max(maximumMuscle, (feminineGender?fatherBody.getFemaleMuscle()+30:fatherBody.getMaleMuscle()+30));
		}
		variance = (maximumMuscle==minimumMuscle?0:Util.random.nextInt(maximumMuscle-minimumMuscle));
		body.setMuscle(minimumMuscle + variance);
		
		
		// Body parts:
		
		boolean inheritsFromMotherFemininity = mother.isFeminine() == body.isFeminine();
		boolean inheritsFromFatherFemininity = father.isFeminine() == body.isFeminine();
		
		// Arm:
		if(Math.random()>0.75) {
			body.getArm().setArmRows(blankNPC, parentTakesAfter.getArmRows());
		}
		
		// Ass:
		// Ass size:
		body.getAss().setAssSize(blankNPC, getSizeFromGenetics(
				body.getAss().getAssSize().getValue(),
				inheritsFromMotherFemininity, mother.getAssSize().getValue(),
				inheritsFromFatherFemininity, father.getAssSize().getValue()));
		// Hip size:
		body.getAss().setHipSize(blankNPC, getSizeFromGenetics(
				body.getAss().getHipSize().getValue(),
				inheritsFromMotherFemininity, mother.getHipSize().getValue(),
				inheritsFromFatherFemininity, father.getHipSize().getValue()));
		
		
		// Breasts:
		boolean inheritsFromMotherBreasts = mother.hasBreasts();
		boolean inheritsFromFatherBreasts = father.hasBreasts();
		if(body.getBreast().getRawSizeValue()>0) {
			// Breast shape:
			if(Math.random()>=0.8f) {
				if(inheritsFromMotherBreasts && inheritsFromFatherBreasts) {
					if(Math.random()>=takesAfterMotherChance) {
						body.getBreast().setShape(blankNPC, mother.getBreastShape());
					} else {
						body.getBreast().setShape(blankNPC, father.getBreastShape());
					}
				} else if(inheritsFromMotherBreasts) {
					body.getBreast().setShape(blankNPC, mother.getBreastShape());
				} else if(inheritsFromFatherBreasts) {
					body.getBreast().setShape(blankNPC, father.getBreastShape());
				}
			}
			// Breast size:
			body.getBreast().setSize(blankNPC, getSizeFromGenetics(
					body.getBreast().getSize().getMeasurement(),
					inheritsFromMotherBreasts, mother.getBreastSize().getMeasurement(),
					inheritsFromFatherBreasts, father.getBreastSize().getMeasurement()));
			// Breast rows:
			if(Math.random()>=0.75) {
				if(Math.random()>=takesAfterMotherChance) {
					body.getBreast().setRows(blankNPC, mother.getBreastRows());
				} else {
					body.getBreast().setRows(blankNPC, father.getBreastRows());
				}
			}
			// Modifiers:
			for(OrificeModifier om : OrificeModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherBreasts && inheritsFromFatherBreasts) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasNippleOrificeModifier(om)) {
								body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
							}
						} else {
							if(father.hasNippleOrificeModifier(om)) {
								body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
							}
						}
					} else if(inheritsFromMotherBreasts) {
						if(mother.hasNippleOrificeModifier(om)) {
							body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
						}
					} else if(inheritsFromFatherBreasts) {
						if(father.hasNippleOrificeModifier(om)) {
							body.getBreast().getNipples().getOrificeNipples().addOrificeModifier(blankNPC, om);
						}
					}
				}
			}
		}
		// Nipple count:
		if(Math.random()>0.75f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getBreast().setNippleCountPerBreast(blankNPC, mother.getNippleCountPerBreast());
			} else {
				body.getBreast().setNippleCountPerBreast(blankNPC, father.getNippleCountPerBreast());
			}
		}
		// Nipple shape:
		if(Math.random()>=0.75f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getBreast().getNipples().setNippleShape(blankNPC, mother.getNippleShape());
			} else {
				body.getBreast().getNipples().setNippleShape(blankNPC, father.getNippleShape());
			}
		}
		// Areolae shape:
		if(Math.random()>=0.75f) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getBreast().getNipples().setAreolaeShape(blankNPC, mother.getAreolaeShape());
			} else {
				body.getBreast().getNipples().setAreolaeShape(blankNPC, father.getAreolaeShape());
			}
		}
		// Nipple size:
		body.getBreast().getNipples().setNippleSize(blankNPC, getSizeFromGenetics(
				body.getBreast().getNipples().getNippleSizeValue(),
				inheritsFromMotherBreasts, mother.getNippleSize().getValue(),
				inheritsFromFatherBreasts, father.getNippleSize().getValue()));
		// Areolae size:
		body.getBreast().getNipples().setAreolaeSize(blankNPC, getSizeFromGenetics(
				body.getBreast().getNipples().getAreolaeSizeValue(),
				inheritsFromMotherBreasts, mother.getAreolaeSize().getValue(),
				inheritsFromFatherBreasts, father.getAreolaeSize().getValue()));
		
		// Face:
		// Lip size:
		body.getFace().getMouth().setLipSize(blankNPC, getSizeFromGenetics(
				body.getFace().getMouth().getLipSizeValue(),
				inheritsFromMotherFemininity, mother.getLipSizeValue(),
				inheritsFromFatherFemininity, father.getLipSizeValue()));
		// Mouth modifiers:
		for(OrificeModifier om : OrificeModifier.values()) {
			if(Math.random()>=0.5) {
				if(Math.random()>=takesAfterMotherChance) {
					if(mother.hasFaceOrificeModifier(om)) {
						body.getFace().getMouth().getOrificeMouth().addOrificeModifier(blankNPC, om);
					}
				} else {
					if(father.hasFaceOrificeModifier(om)) {
						body.getFace().getMouth().getOrificeMouth().addOrificeModifier(blankNPC, om);
					}
				}
			}
		}
		// Tongue modifiers:
		for(TongueModifier tm : TongueModifier.values()) {
			if(Math.random()>=0.5) {
				if(Math.random()>=takesAfterMotherChance) {
					if(mother.hasTongueModifier(tm)) {
						body.getFace().getTongue().addTongueModifier(blankNPC, tm);
					}
				} else {
					if(father.hasTongueModifier(tm)) {
						body.getFace().getTongue().addTongueModifier(blankNPC, tm);
					}
				}
			}
		}
		
		// Eyes
		// Eye pairs:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getEye().setEyePairs(blankNPC, mother.getEyePairs());
			} else {
				body.getEye().setEyePairs(blankNPC, father.getEyePairs());
			}
		}
		// Iris shape:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getEye().setIrisShape(blankNPC, mother.getIrisShape());
			} else {
				body.getEye().setIrisShape(blankNPC, father.getIrisShape());
			}
		}
		// Pupil shape:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getEye().setPupilShape(blankNPC, mother.getPupilShape());
			} else {
				body.getEye().setPupilShape(blankNPC, father.getPupilShape());
			}
		}
		
		// Horn:
		// Horn rows:
		if(Math.random()>=0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getHorn().setHornRows(blankNPC, mother.getHornRows());
			} else {
				body.getHorn().setHornRows(blankNPC, father.getHornRows());
			}
		}
		
		// Penis:
		boolean inheritsFromMotherPenis = mother.hasPenis();
		boolean inheritsFromFatherPenis = father.hasPenis();
		if(body.getPenis().getType()!=PenisType.NONE) {
			// Penis size:
			body.getPenis().setPenisSize(blankNPC, getSizeFromGenetics(
					body.getPenis().getRawSizeValue(),
					inheritsFromMotherPenis, mother.getPenisRawSizeValue(),
					inheritsFromFatherPenis, father.getPenisRawSizeValue()));
			// Penis modifiers:
			for(PenisModifier pm : PenisModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasPenisModifier(pm)) {
								body.getPenis().addPenisModifier(blankNPC, pm);
							}
						} else {
							if(father.hasPenisModifier(pm)) {
								body.getPenis().addPenisModifier(blankNPC, pm);
							}
						}
					} else if(inheritsFromMotherPenis) {
						if(mother.hasPenisModifier(pm)) {
							body.getPenis().addPenisModifier(blankNPC, pm);
						}
					} else if(inheritsFromFatherPenis) {
						if(father.hasPenisModifier(pm)) {
							body.getPenis().addPenisModifier(blankNPC, pm);
						}
					}
				}
			}
			// Urethra modifiers:
			for(OrificeModifier om : OrificeModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasUrethraOrificeModifier(om)) {
								body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
							}
						} else {
							if(father.hasUrethraOrificeModifier(om)) {
								body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
							}
						}
					} else if(inheritsFromMotherPenis) {
						if(mother.hasUrethraOrificeModifier(om)) {
							body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
						}
					} else if(inheritsFromFatherPenis) {
						if(father.hasUrethraOrificeModifier(om)) {
							body.getPenis().getOrificeUrethra().addOrificeModifier(blankNPC, om);
						}
					}
				}
			}
			// Testicles:
			// Testicle size:
			body.getPenis().getTesticle().setTesticleSize(blankNPC, getSizeFromGenetics(
					body.getPenis().getTesticle().getTesticleSize().getValue(),
					inheritsFromMotherPenis, mother.getTesticleSize().getValue(),
					inheritsFromFatherPenis, father.getTesticleSize().getValue()));
			// Testicle count:
			if(Math.random()>=0.75) {
				if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
					if(Math.random()>=takesAfterMotherChance) {
						body.getPenis().getTesticle().setTesticleCount(blankNPC, mother.getTesticleCount());
					} else {
						body.getPenis().getTesticle().setTesticleCount(blankNPC, father.getTesticleCount());
					}
				} else if(inheritsFromMotherPenis) {
					body.getPenis().getTesticle().setTesticleCount(blankNPC, mother.getTesticleCount());
				} else if(inheritsFromFatherPenis) {
					body.getPenis().getTesticle().setTesticleCount(blankNPC, father.getTesticleCount());
				}
			}
			// Internal testicles:
			if(Math.random()>=0.75) {
				if(inheritsFromMotherPenis && inheritsFromFatherPenis) {
					if(Math.random()>=takesAfterMotherChance) {
						if(mother.isInternalTesticles()) {
							body.getPenis().getTesticle().setInternal(blankNPC, true);
						}
					} else {
						if(father.isInternalTesticles()) {
							body.getPenis().getTesticle().setInternal(blankNPC, true);
						}
					}
				} else if(inheritsFromMotherPenis) {
					if(mother.isInternalTesticles()) {
						body.getPenis().getTesticle().setInternal(blankNPC, true);
					}
				} else if(inheritsFromFatherPenis) {
					if(father.isInternalTesticles()) {
						body.getPenis().getTesticle().setInternal(blankNPC, true);
					}
				}
			}
			// Cum Production:
			body.getPenis().getTesticle().setCumProduction(blankNPC, getSizeFromGenetics(
					body.getPenis().getTesticle().getRawCumProductionValue(),
					inheritsFromMotherPenis, mother.getPenisRawCumProductionValue(),
					inheritsFromFatherPenis, father.getPenisRawCumProductionValue()));
		}
		
		// Tail:
		if(Math.random()>0.75) {
			if(Math.random()>=takesAfterMotherChance) {
				body.getTail().setTailCount(blankNPC, mother.getTailCount());
			} else {
				body.getTail().setTailCount(blankNPC, father.getTailCount());
			}
		}
		
		// Vagina:
		boolean inheritsFromMotherVagina = mother.hasVagina();
		boolean inheritsFromFatherVagina = father.hasVagina();
		if(body.getVagina().getType()!=VaginaType.NONE) {
			// Clitoris size:
			body.getVagina().setClitorisSize(blankNPC, getSizeFromGenetics(
					body.getVagina().getRawClitorisSizeValue(),
					inheritsFromMotherVagina, mother.getVaginaRawClitorisSizeValue(),
					inheritsFromFatherVagina, father.getVaginaRawClitorisSizeValue()));
			// Labia size:
			body.getVagina().setLabiaSize(blankNPC, getSizeFromGenetics(
					body.getVagina().getRawLabiaSizeValue(),
					inheritsFromMotherVagina, mother.getVaginaRawLabiaSizeValue(),
					inheritsFromFatherVagina, father.getVaginaRawLabiaSizeValue()));
//			// Capacity:
//			body.getVagina().getOrificeVagina().setCapacity(blankNPC, getSizeFromGenetics(
//					(int) body.getVagina().getOrificeVagina().getRawCapacityValue(),
//					inheritsFromMotherVagina, (int) mother.getVaginaRawCapacityValue(),
//					inheritsFromFatherVagina, (int) father.getVaginaRawCapacityValue()));
			// Wetness:
			body.getVagina().getOrificeVagina().setWetness(blankNPC, getSizeFromGenetics(
					body.getVagina().getOrificeVagina().getWetness(blankNPC).getValue(),
					inheritsFromMotherVagina, mother.getVaginaWetness().getValue(),
					inheritsFromFatherVagina, father.getVaginaWetness().getValue()));
			// Modifiers:
			for(OrificeModifier om : OrificeModifier.values()) {
				if(Math.random()>=0.5) {
					if(inheritsFromMotherVagina && inheritsFromFatherVagina) {
						if(Math.random()>=takesAfterMotherChance) {
							if(mother.hasVaginaOrificeModifier(om)) {
								body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
							}
						} else {
							if(father.hasVaginaOrificeModifier(om)) {
								body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
							}
						}
					} else if(inheritsFromMotherVagina) {
						if(mother.hasVaginaOrificeModifier(om)) {
							body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
						}
					} else if(inheritsFromFatherVagina) {
						if(father.hasVaginaOrificeModifier(om)) {
							body.getVagina().getOrificeVagina().addOrificeModifier(blankNPC, om);
						}
					}
				}
			}
		}
		
		return body;
	}
	
	private static List<BodyCoveringType> setCoveringColours(Body body, GameCharacter character, List<BodyCoveringType> typesToInfluence) {
		List<BodyCoveringType> tempList = new ArrayList<>(typesToInfluence);
		
		// Skin & fur colours:
		for(BodyPartInterface bp : character.getAllBodyParts()){
			if(bp.getType().getBodyCoveringType()!=null
					&& bp.getType().getBodyCoveringType().getRace()!=null
					&& !(bp instanceof Eye)) {
				
				if(tempList.contains(bp.getType().getBodyCoveringType())) {
					Covering covering = character.getCovering(bp.getType().getBodyCoveringType());
					body.getCoverings().put(
							bp.getType().getBodyCoveringType(),
							new Covering(covering.getType(), covering.getPattern(), covering.getPrimaryColour(), covering.isPrimaryGlowing(), covering.getSecondaryColour(), covering.isSecondaryGlowing()));
					tempList.remove(bp.getType().getBodyCoveringType());
//					System.out.println("Set: "+bp.getType().getName(character)+" : "+bp.getType().getBodyCoveringType().getName(character)+"("+bp.getType().getRace().getName()+") : "+covering.getPrimaryColour().getName());
				}
			}
		}
		
		List<BodyCoveringType> extraCoverings = new ArrayList<>();
		extraCoverings.add(BodyCoveringType.ANUS);
		extraCoverings.add(BodyCoveringType.NIPPLES);
		extraCoverings.add(BodyCoveringType.MOUTH);
		extraCoverings.add(BodyCoveringType.TONGUE);
		
		for(BodyCoveringType bct : extraCoverings) {
			if(tempList.contains(bct)) {
				Covering covering = character.getCovering(bct);
					body.getCoverings().put(
							bct,
							new Covering(covering.getType(), covering.getPattern(), covering.getPrimaryColour(), covering.isPrimaryGlowing(), covering.getSecondaryColour(), covering.isSecondaryGlowing()));
					tempList.remove(bct);
			}
//				System.out.println("Set: "+bct+" : "+bct.getName(character)+" : "+covering.getPrimaryColour().getName());
		}
//		System.out.println("------------------------------");
		
		return tempList;
	}
	
	private static int getSizeFromGenetics(int baseSize, boolean inheritsFromMother, int motherSize, boolean inheritsFromFather, int fatherSize) {
		// $BaseRaceSize + RandomAmount($ParentSize - $BaseRaceSize), then +-10%
		int variation = 0;
		if(inheritsFromMother && inheritsFromFather) {
			variation = (motherSize + fatherSize)/2;
		} else if(inheritsFromMother) {
			variation = motherSize;
		} else if(inheritsFromFather) {
			variation = fatherSize;
		}
		
		int difference = variation - baseSize;
		
		return (int) ((baseSize + (Math.signum(difference)*Util.random.nextInt(Math.abs(difference) +1)))*(0.9f+(Math.random()*0.2f)));
	}
	
	
	public static Body generateBody(Gender startingGender, RacialBody startingBodyType, RaceStage stage) {
		
		boolean hasVagina = startingGender.getGenderName().isHasVagina();
		boolean hasPenis = startingGender.getGenderName().isHasPenis();
		boolean hasBreasts = startingGender.getGenderName().isHasBreasts();
		
		Body body = new Body.BodyBuilder(
				new Arm((stage.isArmFurry()?startingBodyType.getArmType():ArmType.HUMAN), startingBodyType.getArmRows()),
				new Ass(stage.isAssFurry()?startingBodyType.getAssType():AssType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
						startingBodyType.getAnusWetness(),
						startingBodyType.getAnusCapacity(),
						startingBodyType.getAnusElasticity(),
						startingBodyType.getAnusPlasticity(),
						true),
				new Breast(stage.isBreastFurry()?startingBodyType.getBreastType():BreastType.HUMAN,
						BreastShape.getRandomBreastShape(),
						(hasBreasts? startingBodyType.getBreastSize() : startingBodyType.getNoBreastSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLactationRate() : startingBodyType.getMaleLactationRate()),
						((stage.isSkinFurry() && Main.getProperties().multiBreasts==1) || (stage.isBreastFurry() && Main.getProperties().multiBreasts==2)
								?(startingGender.isFeminine() ? startingBodyType.getBreastCountFemale() : startingBodyType.getBreastCountMale())
								:1),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleSize() : startingBodyType.getMaleNippleSize()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleNippleShape() : startingBodyType.getMaleNippleShape()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleAreolaeSize() : startingBodyType.getMaleAreolaeSize()),
						(stage.isBreastFurry() ? (startingGender.isFeminine() ? startingBodyType.getFemaleNippleCountPerBreast() : startingBodyType.getMaleNippleCountPerBreast()) : 1),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastCapacity() : startingBodyType.getMaleBreastCapacity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastElasticity() : startingBodyType.getMaleBreastElasticity()),
						(startingGender.isFeminine() ? startingBodyType.getFemaleBreastPlasticity() : startingBodyType.getMaleBreastPlasticity()), 
						true),
				new Face((stage.isFaceFurry()?startingBodyType.getFaceType():FaceType.HUMAN),
						(startingGender.isFeminine() ? startingBodyType.getFemaleLipSize() : startingBodyType.getMaleLipSize())),
				new Eye(stage.isEyeFurry()?startingBodyType.getEyeType():EyeType.HUMAN),
				new Ear(stage.isEarFurry()?startingBodyType.getEarType():EarType.HUMAN),
				new Hair(stage.isHairFurry()?startingBodyType.getHairType():HairType.HUMAN,
						(startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()),
						HairStyle.getRandomHairStyle((startingGender.isFeminine() ? startingBodyType.getFemaleHairLength() : startingBodyType.getMaleHairLength()))),
				new Leg(stage.isLegFurry()?startingBodyType.getLegType():LegType.HUMAN),
				new Skin(stage.isSkinFurry()?startingBodyType.getSkinType():SkinType.HUMAN),
				startingBodyType.getBodyMaterial(),
				startingBodyType.getGenitalArrangement(),
				(startingGender.isFeminine() ? startingBodyType.getFemaleHeight() : startingBodyType.getMaleHeight()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleFemininity() : startingBodyType.getMaleFemininity()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleBodySize() : startingBodyType.getMaleBodySize()),
				(startingGender.isFeminine() ? startingBodyType.getFemaleMuscle() : startingBodyType.getMaleMuscle()))
						.vagina(hasVagina
								? new Vagina(stage.isVaginaFurry()?startingBodyType.getVaginaType():VaginaType.HUMAN,
										LabiaSize.getRandomLabiaSize().getValue(),
										startingBodyType.getClitSize(),
										startingBodyType.getVaginaWetness(),
										startingBodyType.getVaginaCapacity(),
										startingBodyType.getVaginaElasticity(),
										startingBodyType.getVaginaPlasticity(),
										true)
								: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 3, 3, true))
						.penis(hasPenis
								? new Penis(stage.isPenisFurry()?startingBodyType.getPenisType():PenisType.HUMAN,
									startingBodyType.getPenisSize(),
									startingBodyType.getTesticleSize(),
									startingBodyType.getCumProduction(),
									startingBodyType.getTesticleQuantity())
								: new Penis(PenisType.NONE, 0, 0, 0, 2))
						.horn(startingBodyType.getHornTypeFemale() == HornType.NONE ? new Horn(HornType.NONE) : new Horn(!startingGender.isFeminine()
								? (stage.isHornFurry()?startingBodyType.getHornTypeMale():HornType.NONE)
								: (stage.isHornFurry()?startingBodyType.getHornTypeFemale():HornType.NONE)))
						.antenna(new Antenna(stage.isAntennaFurry()?startingBodyType.getAntennaType():AntennaType.NONE))
						.tail(new Tail(stage.isTailFurry()?startingBodyType.getTailType():TailType.NONE))
						.wing(new Wing(stage.isWingFurry()?startingBodyType.getWingType():WingType.NONE))
						.build();
		
		// Pubic hair:
		BodyHair hair = BodyHair.getRandomBodyHair();
		body.setPubicHair(hair);
		body.getFace().setFacialHair(null, hair);
		body.getArm().setUnderarmHair(null, hair);
		body.getAss().getAnus().setAssHair(null, hair);
		
		return body;
	}
	
	public static void randomiseBody(GameCharacter character) {
		// Piercings (in order of probability that they'll have them, based on some random website that orders popularity):
		// All piercings are reliant on having ear piercings first:
		if (Math.random() >= (character.isFeminine()?0.1f:0.9f) || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
			character.setPiercedEar(true);
			
			if (Math.random() <= 0.33f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedNavel(true);
			}
			if (Math.random() <= 0.19f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedNose(true);
			}
			if (Math.random() <= 0.1f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedTongue(true);
			}
			if (Math.random() <= 0.1f || character.hasFetish(Fetish.FETISH_MASOCHIST)) {
				character.setPiercedNipples(true);
			}
			if (Math.random() <= 0.1f || character.hasFetish(Fetish.FETISH_MASOCHIST)) { // It said lip piercings are only in 4% of the pierced population, but that seems too low for the game.
				character.setPiercedLip(true);
			}
			// Genitals:
			if (character.hasPenis() && (Math.random() <= 0.05f || character.hasFetish(Fetish.FETISH_MASOCHIST))) {
				character.setPiercedPenis(true);
			}
			if (character.hasVagina() && (Math.random() <= 0.05f || character.hasFetish(Fetish.FETISH_MASOCHIST))) {
				character.setPiercedVagina(true);
			}
		}
		
		//Ass:
		if(character.hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
			character.setAssVirgin(false);
			character.setAssCapacity(character.getAssRawCapacityValue()*1.2f);
			character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		} else {
			character.setAssVirgin(true);
		}
		
		// Body:
		character.setHeight(character.getHeightValue()-15 + Util.random.nextInt(30) +1);
		
		//Breasts:
		if(Main.getProperties().multiBreasts==0) {
			character.setBreastRows(1);
			
		} else if(Main.getProperties().multiBreasts==1) {
			if(character.getSkinType() == SkinType.HUMAN) {
				character.setBreastRows(1);
			}
		}
		
		if(character.hasBreasts()) {
			character.setBreastSize(Math.max(CupSize.AA.getMeasurement(), character.getBreastSize().getMeasurement() -2 +(Util.random.nextInt(5)))); // Random size between -2 and +2 of base value.
		}
		
		// Face:
		if(character.hasFetish(Fetish.FETISH_ORAL_GIVING)) {
			character.setFaceCapacity(Capacity.FIVE_ROOMY.getMedianValue());
			character.setFaceStretchedCapacity(character.getFaceRawCapacityValue());
			character.setFaceVirgin(false);
			
		} else {
			if(Math.random()>0.15f) {
				character.setFaceVirgin(true);
			} else {
				character.setFaceVirgin(false);
			}
		}
		
		// Hair:
		character.setHairLength(character.getHairLength().getMinimumValue() + Util.random.nextInt(character.getHairLength().getMaximumValue() - character.getHairLength().getMinimumValue()) +1);
		
		// Penis:
		if(character.hasPenis()) {
			if((character.getGender()==Gender.F_P_TRAP || character.getGender()==Gender.N_P_TRAP) && Math.random()>=0.1f) { // Most traps have a small cock:
				character.setPenisSize(PenisSize.ONE_TINY.getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
				character.setTesticleSize(TesticleSize.ONE_TINY.getValue());
				character.setCumProduction(CumProduction.ONE_TRICKLE.getMedianValue());
			} else {
				character.setPenisSize(character.getPenisSize().getMinimumValue() + Util.random.nextInt(character.getPenisSize().getMaximumValue() - character.getPenisSize().getMinimumValue()) +1);
			}
		}
		
		// Vagina:
		if(character.hasVagina()) {
			if(character.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
				character.setVaginaVirgin(true);
				int capacity = Capacity.ZERO_IMPENETRABLE.getMinimumValue() + Util.random.nextInt(Capacity.TWO_TIGHT.getMaximumValue()-Capacity.ZERO_IMPENETRABLE.getMinimumValue());
				character.setVaginaCapacity(capacity);
				
			} else {
				if(Math.random()>0.25f || character.getHistory()==History.PROSTITUTE) {
					character.setVaginaVirgin(false);
					character.setVaginaCapacity(character.getVaginaRawCapacityValue()*1.2f);
					character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
				} else {
					character.setVaginaVirgin(true);
				}
			}
			character.setVaginaWetness(character.getVaginaWetness().getValue() -1 + Util.random.nextInt(3)); // +1 or -1 either way
		}
		
		character.setAssStretchedCapacity(character.getAssRawCapacityValue());
		character.setNippleStretchedCapacity(character.getNippleRawCapacityValue());
		character.setFaceStretchedCapacity(character.getFaceRawCapacityValue());
		character.setPenisStretchedCapacity(character.getPenisRawCapacityValue());
		character.setVaginaStretchedCapacity(character.getVaginaRawCapacityValue());
	}
	
	public static void addFetishes(GameCharacter character) {
		
		List<Fetish> availableFetishes = new ArrayList<>();
		for(Fetish f : Fetish.values()) {
			if (f==Fetish.FETISH_PURE_VIRGIN) {
				if(character.hasVagina() && character.getHistory()!=History.PROSTITUTE)
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_BIMBO) {
				if(character.isFeminine())
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_PREGNANCY) {
				if(character.hasVagina())
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_IMPREGNATION) {
				if(character.hasPenis())
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_SEEDER) {
				if(character.hasPenis())
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_BROODMOTHER) {
				if(character.hasVagina())
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_CUM_STUD) {
				if(character.hasPenis())
					availableFetishes.add(f);
				
			}else if (f==Fetish.FETISH_BREASTS_SELF) {
				if(character.hasBreasts())
					availableFetishes.add(f);
				
			// Fetishes for content locks:
			} else if (f==Fetish.FETISH_NON_CON) {
				if(Main.getProperties().nonConContent)
					availableFetishes.add(f);
				
			} else if (f==Fetish.FETISH_INCEST) {
				if(Main.getProperties().incestContent)
					availableFetishes.add(f);
				
			} else if (f.getFetishesForAutomaticUnlock().isEmpty()){
				availableFetishes.add(f);
			}
		}
		
		int[] numberProb = new int[] {1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5};
		int numberOfFetishes = numberProb[Util.random.nextInt(numberProb.length)];
		
		int fetishesAssigned = 0;
		
		if(((character.getMother()!=null && character.getMother().isPlayer()) || (character.getFather()!=null && character.getFather().isPlayer()))) {
			if(Main.getProperties().incestContent && Math.random()>0.5f) {
				character.addFetish(Fetish.FETISH_INCEST);
				availableFetishes.remove(Fetish.FETISH_INCEST);
				fetishesAssigned++;
			}
		} else { // If not offspring, give them a higher chance for TF fetish:
			if(Math.random()>0.35f) {
				character.addFetish(Fetish.FETISH_TRANSFORMATION_GIVING);
				availableFetishes.remove(Fetish.FETISH_TRANSFORMATION_GIVING);
				fetishesAssigned++;
			}
		}
		
		
		
		while(fetishesAssigned < numberOfFetishes) {
			Fetish f = availableFetishes.get(Util.random.nextInt(availableFetishes.size()));
			character.addFetish(f);
			availableFetishes.remove(f);
			fetishesAssigned++;
		}
	}
	
	public static void equipClothing(GameCharacter character, boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		Colour primaryColour = Colour.allClothingColours.get(Util.random.nextInt(Colour.allClothingColours.size())),
				secondaryColour = Colour.allClothingColours.get(Util.random.nextInt(Colour.allClothingColours.size())),
				lingerieColour = Colour.lingerieColours.get(Util.random.nextInt(Colour.lingerieColours.size()));
		
		List<InventorySlot> inventorySlotsInPriorityOrder = new ArrayList<>();
		inventorySlotsInPriorityOrder.add(InventorySlot.TORSO_UNDER); // Torso needs to be randomly decided first, to give girls a chance to wear a dress.
		for(InventorySlot slot : InventorySlot.values()) {
			if(slot!=InventorySlot.TORSO_UNDER) {
				inventorySlotsInPriorityOrder.add(slot);
			}
		}
		
		if((character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER))) {
			for(InventorySlot slot : inventorySlotsInPriorityOrder) {
				if(replaceUnsuitableClothing) {
					if(character.getClothingInSlot(slot)!=null) {
						if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.MASCULINE) {
							character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
						}
					}
				}
				
				if(!slot.isCoreClothing() && onlyAddCoreClothing) {
					// Don't add clothing if not core
				} else {
					if((slot.isCoreClothing() || Math.random()>0.75f || slot.isJewellery()) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
						if(!ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot).isEmpty() && (slot.slotBlockedByRace(character) != character.getRace())) {
							
							List<AbstractClothingType> clothingToUse = ClothingType.getCommonClothingMapFemaleIncludingAndrogynous().get(slot);
							
							if(character.getHistory()==History.PROSTITUTE) {
								clothingToUse = suitableFeminineClothing.get(History.PROSTITUTE);
							}
							AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
							
							clothingToUse.remove(ClothingType.PENIS_CONDOM);
							
							if(ct!=null) {
								character.equipClothingFromNowhere(AbstractClothingType.generateClothing(
										ct,
										(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
												? lingerieColour
												: (slot.isCoreClothing()
														?primaryColour
														:secondaryColour)),
										false), true, character);
							}
						}
					}
				}
			}
			
		} else {
			for(InventorySlot slot : inventorySlotsInPriorityOrder) {
				if(replaceUnsuitableClothing) {
					if(character.getClothingInSlot(slot)!=null) {
						if(character.getClothingInSlot(slot).getClothingType().getFemininityRestriction() == Femininity.FEMININE) {
							character.unequipClothingIntoVoid(character.getClothingInSlot(slot), true, character);
						}
					}
				}
				
				if(!slot.isCoreClothing() && onlyAddCoreClothing) {
					// Don't add clothing if not core
				} else {
					if((slot.isCoreClothing() || Math.random()>0.75f || slot.isJewellery()) && !character.isSlotIncompatible(slot) && character.getClothingInSlot(slot)==null) {
						if(!ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot).isEmpty() && (slot.slotBlockedByRace(character) != character.getRace())) {
							

							List<AbstractClothingType> clothingToUse = ClothingType.getCommonClothingMapMaleIncludingAndrogynous().get(slot);
							clothingToUse.remove(ClothingType.PENIS_CONDOM);
							AbstractClothingType ct = getClothingTypeForSlot(character, slot, clothingToUse);
							
							if(ct!=null) {
							character.equipClothingFromNowhere(AbstractClothingType.generateClothing(
									ct,
									(slot == InventorySlot.GROIN || slot==InventorySlot.CHEST || slot==InventorySlot.SOCK
											? lingerieColour
											: (slot.isCoreClothing()
													?primaryColour
													:secondaryColour)),
									false), true, character);
							}
								
						}
					}
				}
			}
		}
	}
	
	private static AbstractClothingType getClothingTypeForSlot(GameCharacter character, InventorySlot slot, List<AbstractClothingType> clothingOptions) {
		List<AbstractClothingType> availableClothing = new ArrayList<>();

		boolean canEquip=true;
		
		for(AbstractClothingType ct : clothingOptions) {
			if(ct.getSlot()!=slot) {
				continue;
			}
			canEquip=true;
			
			if(slot==InventorySlot.CHEST && !character.hasBreasts()) {
				canEquip = false;
				
			} else if(character.hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
				
				for(BlockedParts bp : ct.getBlockedPartsList()) {
					boolean leavesAnusExposed = character.isCoverableAreaExposed(CoverableArea.ANUS) && !bp.blockedBodyParts.contains(CoverableArea.ANUS),
							leavesNipplesExposed = character.isCoverableAreaExposed(CoverableArea.NIPPLES) && !bp.blockedBodyParts.contains(CoverableArea.NIPPLES),
							leavesPenisExposed = character.hasPenis()?(character.isCoverableAreaExposed(CoverableArea.PENIS) && !bp.blockedBodyParts.contains(CoverableArea.PENIS)):true,
							leavesVaginaExposed = character.hasVagina()?(character.isCoverableAreaExposed(CoverableArea.VAGINA) && !bp.blockedBodyParts.contains(CoverableArea.VAGINA)):true;
					//TODO
					if(!leavesNipplesExposed || (!leavesAnusExposed || !leavesPenisExposed && !leavesVaginaExposed)) {
						canEquip = false;
					}
				}
				
				
			} else {
				for(InventorySlot is : ct.getIncompatibleSlots()) {
					if(character.getClothingInSlot(is) != null) {
						canEquip = false;
					}
				}
			}
			
			if(canEquip) {
				availableClothing.add(ct);
			}
		}
		
		if(availableClothing.isEmpty()) {
			return null;
			
		} else {
			return availableClothing.get(Util.random.nextInt(availableClothing.size()));
		}
	}
	
	public static void applyMakeup(GameCharacter character, boolean overideExistingMakeup) {
		if((character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER))) {
			List<Colour> colours = Util.newArrayListOfValues(
					new ListValue<>(Colour.COVERING_NONE),
					new ListValue<>(Colour.COVERING_CLEAR),
					new ListValue<>(Colour.COVERING_RED),
					new ListValue<>(Colour.COVERING_PINK),
					new ListValue<>(Colour.COVERING_BLUE));
			
			if(character.getHistory()==History.PROSTITUTE) {
				colours.remove(Colour.COVERING_NONE);
				colours.remove(Colour.COVERING_CLEAR);
			}
			
			Colour colourForCoordination = colours.get(Util.random.nextInt(colours.size()));
			Colour colourForNails = colours.get(Util.random.nextInt(colours.size()));
			
			character.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, colourForCoordination));
			character.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
			character.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, colourForCoordination));
			character.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, colourForCoordination));
			
			character.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, colourForNails));
			character.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, colourForNails));
			
		} else {
			// Masculine characters
		}
	}
	

	private static Map<History, ArrayList<AbstractClothingType>> suitableFeminineClothing = new HashMap<>();
	
	static {
		suitableFeminineClothing.put(History.PROSTITUTE,
				Util.newArrayListOfValues(
						new ListValue<>(ClothingType.ANKLE_BRACELET),
						new ListValue<>(ClothingType.CHEST_LACY_PLUNGE_BRA),
						new ListValue<>(ClothingType.CHEST_OPEN_CUP_BRA),
						new ListValue<>(ClothingType.CHEST_PLUNGE_BRA),
						new ListValue<>(ClothingType.EYES_AVIATORS),
						new ListValue<>(ClothingType.FINGER_RING),
						new ListValue<>(ClothingType.FOOT_ANKLE_BOOTS),
						new ListValue<>(ClothingType.FOOT_HEELS),
						new ListValue<>(ClothingType.FOOT_THIGH_HIGH_BOOTS),
						new ListValue<>(ClothingType.FOOT_STILETTO_HEELS),
						new ListValue<>(ClothingType.GROIN_BACKLESS_PANTIES),
						new ListValue<>(ClothingType.GROIN_CROTCHLESS_PANTIES),
						new ListValue<>(ClothingType.GROIN_CROTCHLESS_THONG),
						new ListValue<>(ClothingType.GROIN_LACY_PANTIES),
						new ListValue<>(ClothingType.GROIN_THONG),
						new ListValue<>(ClothingType.GROIN_VSTRING),
						new ListValue<>(ClothingType.HAND_ELBOWLENGTH_GLOVES),
						new ListValue<>(ClothingType.HEAD_HEADBAND),
						new ListValue<>(ClothingType.HEAD_HEADBAND_BOW),
						new ListValue<>(ClothingType.LEG_CROTCHLESS_CHAPS),
						new ListValue<>(ClothingType.LEG_MICRO_SKIRT_BELTED),
						new ListValue<>(ClothingType.LEG_MICRO_SKIRT_PLEATED),
						new ListValue<>(ClothingType.LEG_MINI_SKIRT),
						new ListValue<>(ClothingType.LEG_SKIRT),
						new ListValue<>(ClothingType.NECK_HEART_NECKLACE),
						new ListValue<>(ClothingType.NECK_ANKH_NECKLACE),
						new ListValue<>(ClothingType.NIPPLE_TAPE_CROSSES),
						new ListValue<>(ClothingType.SOCK_FISHNET_STOCKINGS),
						new ListValue<>(ClothingType.SOCK_TIGHTS),
						new ListValue<>(ClothingType.STOMACH_OVERBUST_CORSET),
						new ListValue<>(ClothingType.STOMACH_UNDERBUST_CORSET),
						new ListValue<>(ClothingType.TORSO_FISHNET_TOP),
						new ListValue<>(ClothingType.TORSO_KEYHOLE_CROPTOP),
						new ListValue<>(ClothingType.TORSO_SHORT_CROPTOP),
						new ListValue<>(ClothingType.WRIST_BANGLE),
						new ListValue<>(ClothingType.WRIST_WOMENS_WATCH),
						
						new ListValue<>(ClothingType.PIERCING_EAR_BASIC_RING),
						new ListValue<>(ClothingType.PIERCING_LIP_RINGS),
						new ListValue<>(ClothingType.PIERCING_NAVEL_GEM),
						new ListValue<>(ClothingType.PIERCING_NIPPLE_BARS),
						new ListValue<>(ClothingType.PIERCING_NOSE_BASIC_RING),
						new ListValue<>(ClothingType.PIERCING_PENIS_RING),
						new ListValue<>(ClothingType.PIERCING_TONGUE_BAR),
						new ListValue<>(ClothingType.PIERCING_VAGINA_BARBELL_RING)));
	}
	
//	private static void equipPreset(GameCharacter character, boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
//		boolean feminineClothing = (character.isFeminine() && !character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) || (!character.isFeminine() && character.hasFetish(Fetish.FETISH_CROSS_DRESSER));
//		
//		switch(character.getHistory()) {
//			case PROSTITUTE:
//				if(feminineClothing) {
//					equipIfNothingInSlot(character, );
//				} else {
//					
//				}
//				break;
//			default:
//				break;
//		}
//	}
//	
//	private static void equipIfNothingInSlot(GameCharacter character, AbstractClothing clothing) {
//		if(character.getClothingInSlot(clothing.getClothingType().getSlot()) == null) {
//			character.equipClothingFromNowhere(clothing, true, character);
//		}
//	}
}
