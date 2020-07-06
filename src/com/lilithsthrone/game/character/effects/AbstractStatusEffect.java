package com.lilithsthrone.game.character.effects;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.combat.moves.CombatMove;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.2
 * @version 0.3.8.2
 * @author Innoxia
 */
public abstract class AbstractStatusEffect {

	private boolean mod;
	private boolean fromExternalFile;
	private int renderingPriority;

	private EffectBenefit beneficial;
	private boolean renderInEffectsPanel;
	private boolean combatEffect;
	private boolean sexEffect;
	
	private String name;
	private String description;
	private String pathName;
	private List<Colour> colourShades;

	private boolean requiresApplicationCheck;
	private int effectInterval;
	private String applicationCondition;
	private String applyEffectString;
	private String applyRemovalString;
	private String applyPostRemovalString;
	
	private Map<Attribute, Float> attributeModifiers;
	private List<String> combatMoveIds;
	private List<String> spellIds;

	protected String SVGString;

	protected List<String> extraEffects;

	public AbstractStatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority, name, pathName, colourShade, colourShade, colourShade, beneficial, attributeModifiers, extraEffects);
	}
	
	public AbstractStatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			Colour colourShadeSecondary,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority, name, pathName, colourShade, colourShadeSecondary, colourShade, beneficial, attributeModifiers, extraEffects);
	}
	
	public AbstractStatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			Colour colourShadeSecondary,
			Colour colourShadeTertiary,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		
		this.mod = false;
		this.fromExternalFile = false;
		
		this.renderingPriority = renderingPriority;
		this.name = name;
		this.description = ""; // As all new status effects should be added via xml files, this shouldn't be an issue.
		
		this.pathName = pathName;
		SVGString = null;
		
		this.colourShades = Util.newArrayListOfValues(colourShade, colourShadeSecondary, colourShadeTertiary);
		
		if(beneficial) { // As all new status effects should be added via xml files, not giving the option to set as NEUTRAL shouldn't be an issue.
			this.beneficial = EffectBenefit.BENEFICIAL;
		} else {
			this.beneficial = EffectBenefit.DETRIMENTAL;
		}
		
		this.requiresApplicationCheck = true;
		this.renderInEffectsPanel = true;
		this.combatEffect = false;
		this.sexEffect = false;
		
		this.effectInterval = 0;
		this.applicationCondition = null;
		
		if(attributeModifiers==null) {
			this.attributeModifiers = new HashMap<>();
		} else {
			this.attributeModifiers = attributeModifiers;
		}
		
		this.spellIds = null;
		this.combatMoveIds = null;
		
		if(extraEffects == null) {
			this.extraEffects = new ArrayList<>();
		} else {
			this.extraEffects = extraEffects;
		}
	}
	
	public AbstractStatusEffect(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>
				
				this.mod = mod;
				this.fromExternalFile = true;
				
				this.renderingPriority = Integer.valueOf(coreElement.getMandatoryFirstOf("renderingPriority").getTextContent());
				
				this.beneficial = EffectBenefit.valueOf(coreElement.getMandatoryFirstOf("beneficial").getTextContent());
				this.renderInEffectsPanel = Boolean.valueOf(coreElement.getMandatoryFirstOf("renderInEffectsPanel").getTextContent());
				this.combatEffect = Boolean.valueOf(coreElement.getMandatoryFirstOf("combatEffect").getTextContent());
				this.sexEffect = Boolean.valueOf(coreElement.getMandatoryFirstOf("sexEffect").getTextContent());
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.description = coreElement.getMandatoryFirstOf("description").getTextContent();

				this.pathName = XMLFile.getParentFile().getAbsolutePath() + "/" + coreElement.getMandatoryFirstOf("imageName").getTextContent();
				SVGString = null;
				
				Colour colourShade = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("colourPrimary").getTextContent());
				Colour colourShadeSecondary = null;
				if(coreElement.getOptionalFirstOf("colourSecondary").isPresent() && !coreElement.getMandatoryFirstOf("colourSecondary").getTextContent().isEmpty()) {
					colourShadeSecondary = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("colourSecondary").getTextContent());
				}
				Colour colourShadeTertiary = null;
				if(coreElement.getOptionalFirstOf("colourTertiary").isPresent() && !coreElement.getMandatoryFirstOf("colourTertiary").getTextContent().isEmpty()) {
					colourShadeTertiary = PresetColour.getColourFromId(coreElement.getMandatoryFirstOf("colourTertiary").getTextContent());
				}
				this.colourShades = Util.newArrayListOfValues(colourShade, colourShadeSecondary, colourShadeTertiary);
				
				this.attributeModifiers = new HashMap<>();
				if(coreElement.getOptionalFirstOf("attributeModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("attributeModifiers").getAllOf("modifier")) {
						attributeModifiers.put(Attribute.valueOf(e.getTextContent()), Float.valueOf(e.getAttribute("value")));
					}
				}

				this.combatMoveIds = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("combatMoves").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("combatMoves").getAllOf("move")) {
						this.combatMoveIds.add(e.getTextContent());
					}
				}
				
				this.spellIds = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("spells").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("spells").getAllOf("spell")) {
						this.spellIds.add(e.getTextContent());
					}
				}
			
				this.extraEffects = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("extraEffects").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("extraEffects").getAllOf("effect")) {
						extraEffects.add(e.getTextContent());
					}
				}
				
				// Logic:
				
				this.applicationCondition = coreElement.getMandatoryFirstOf("applicationCondition").getTextContent();
				if(this.applicationCondition.trim().equals("false")) {
					requiresApplicationCheck = false;
				} else {
					requiresApplicationCheck = true;
				}
				
				if(!coreElement.getMandatoryFirstOf("applyEffect").getAttribute("interval").isEmpty()) {
					this.effectInterval = Integer.valueOf(coreElement.getMandatoryFirstOf("applyEffect").getAttribute("interval"));
				} else {
					this.effectInterval = 0;
				}
				this.applyEffectString = coreElement.getMandatoryFirstOf("applyEffect").getTextContent();
				this.applyRemovalString = coreElement.getMandatoryFirstOf("applyRemovalEffect").getTextContent();
				this.applyPostRemovalString = coreElement.getMandatoryFirstOf("applyPostRemovalEffect").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("SetBonus was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}

	public String getId() {
		return StatusEffect.getIdFromStatusEffect(this);
	}
	
	protected List<String> attributeModifiersToStringList(Map<Attribute, Float> attributeMap) {
		List<String> attributeModifiersList = new ArrayList<>();
		
		if (attributeMap != null) {
			for (Entry<Attribute, Float> e : attributeMap.entrySet()) {
				attributeModifiersList.add(
						"<b>"+(e.getValue()>0?"+":"")+Units.number(e.getValue(), 1, 1)+"</b>"
						+ " <b style='color:"+e.getKey().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(e.getKey().getAbbreviatedName())+"</b>");
			}
		}
		
		return attributeModifiersList;
	}

	public boolean isRequiresApplicationCheck() {
		return requiresApplicationCheck;
	}

	/**
	 * @param target
	 * @return True if this status effect should be applied to the target.
	 *  False if conditions are not met <b>or</b> this status effect is only for timed purposes (i.e. the only time it should be applied is with a time condition.).
	 */
	public boolean isConditionsMet(GameCharacter target) {
		if(this.isFromExternalFile() && target!=null && Main.game.isStarted()) {
			String parsedResult = UtilText.parse(target, applicationCondition);
			parsedResult = parsedResult.replaceAll("\\s", "");
			return Boolean.valueOf(parsedResult);
		}
		if(!this.isFromExternalFile()) { // Easiest way to make sure that this status effect doens't need to be checked on every update is to see whether or not isConditionsMet has been overridden (in which case requiresApplicationCheck remains true)
			requiresApplicationCheck = false;
		}
		return false;
	}
	
	public boolean renderInEffectsPanel() {
		return renderInEffectsPanel;
	}
	
	public boolean isCombatEffect() {
		return combatEffect;
	}
	
	public boolean isSexEffect() {
		return sexEffect;
	}
	
	public String applyEffect(GameCharacter target, int secondsPassed, long totalSecondsPassed) {
		if(this.isFromExternalFile() && target!=null) {
			String parsedResult = UtilText.parse(target, applyEffectString.replaceAll("TOTAL_SECONDS_PASSED", String.valueOf(totalSecondsPassed)).replaceAll("SECONDS_PASSED", String.valueOf(secondsPassed)));
			if(parsedResult.trim().length()>0) {
				return parsedResult;
			} else {
				return "";
			}
		}
		return "";
	}

	public final String applyRemoveStatusEffect(GameCharacter target) {
		return extraRemovalEffects(target);
	}
	
	protected String extraRemovalEffects(GameCharacter target){
		if(this.isFromExternalFile() && target!=null) {
			String parsedResult = UtilText.parse(target, applyRemovalString);
			if(parsedResult.trim().length()>0) {
				return parsedResult;
			} else {
				return "";
			}
		}
		return "";
	}
	
	public String applyPostRemovalStatusEffect(GameCharacter target) {
		if(this.isFromExternalFile() && target!=null) {
			String parsedResult = UtilText.parse(target, applyPostRemovalString);
			if(parsedResult.trim().length()>0) {
				return parsedResult;
			} else {
				return "";
			}
		}
		return "";
	}
	
	public boolean isMod() {
		return mod;
	}
	
	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public String getName(GameCharacter target) {
		if(target!=null) {
			return UtilText.parse(target, name);
		}
		return name;
	}

	public String getDescription(GameCharacter target) {
		if(description!=null && target!=null) {
			return UtilText.parse(target, description);
		}
		return "";
	}

	/**
	 * Used to display extra effects in its own description box, such as ongoing sex descriptions.
	 * @return A Value whose key is the line height and whose value is the String to be displayed.
	 */
	protected Value<Integer, String> getAdditionalDescription(GameCharacter target) {
		return null;
	}
	
	/**
	 * Used to display multiple extra effects in their own description boxes, such as ongoing sex descriptions.
	 * @return A List of Values whose key is the line height and whose value is the String to be displayed.
	 */
	public List<Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
		Value<Integer, String> additional = getAdditionalDescription(target);
		if(additional!=null) {
			return Util.newArrayListOfValues(additional);
		}
		return null;
	}

	public List<String> getModifiersAsStringList(GameCharacter target) {
		ArrayList<String> fullModList = new ArrayList<>(attributeModifiersToStringList(getAttributeModifiers(target)));
		fullModList.addAll(getExtraEffects(target));
		return fullModList;
	}
	
	public EffectBenefit getBeneficialStatus() {
		return beneficial;
	}

	public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
		return attributeModifiers;
	}
	
	// This has to be overridden, as defining CombatMoves in a status effect's constructor can cause initialisation errors.
	public List<CombatMove> getCombatMoves() {
		if(this.isFromExternalFile()) {
			List<CombatMove> combatMoves = new ArrayList<>();
			for(String moveID : combatMoveIds) {
				combatMoves.add(CombatMove.getMove(moveID));
			}
			return combatMoves;
		}
		return new ArrayList<>();
	}

	// This has to be overridden, as defining Spells in a status effect's constructor can cause initialisation errors.
	public List<Spell> getSpells() {
		if(this.isFromExternalFile()) {
			List<Spell> spells = new ArrayList<>();
			for(String spellID : spellIds) {
				spells.add(Spell.valueOf(spellID));
			}
			return spells;
		}
		return new ArrayList<>();
	}
	
	public Colour getColour() {
		return colourShades.get(0);
	}

	public List<Colour> getColourShades() {
		return colourShades;
	}

	public int getEffectInterval() {
		return effectInterval;
	}

	public List<String> getExtraEffects(GameCharacter target) {
		return extraEffects;
	}

	public String getSVGString(GameCharacter owner) {
		if(SVGString==null) {
			if(pathName!=null && !pathName.isEmpty()) {
				try {
					if(isFromExternalFile()) {
						List<String> lines = Files.readAllLines(Paths.get(pathName));
						StringBuilder sb = new StringBuilder();
						for(String line : lines) {
							sb.append(line);
						}
						SVGString = sb.toString();
						SVGString = SvgUtil.colourReplacement(this.getId(), colourShades, null, SVGString);
						
					} else {
						InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/" + pathName + ".svg");
						if(is==null) {
							System.err.println("Error! StatusEffect icon file does not exist (Trying to read from '"+pathName+"')!");
						}
						SVGString = SvgUtil.colourReplacement(this.getId(), colourShades, null, Util.inputStreamToString(is));
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				SVGString = "";
			}
		}
		return SVGString;
	}
	
	
	
	
	//******************************** Methods for sex effects: ********************************//
	
	public float getArousalPerTurnSelf(GameCharacter self) {
		return 0;
	}
	
	public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
		return 0;
	}
	
	public static float getPenetrationArousalPerTurn(GameCharacter target, SexAreaPenetration penetration) {
		float arousal = 0;
		
		if(!Main.sex.getOngoingSexAreas(target, penetration).isEmpty()) {
			arousal+=penetration.getBaseArousalWhenPenetrating();
			
			if(!Main.sex.hasLubricationTypeFromAnyone(target, penetration)) {
				arousal += penetration.getArousalChangePenetratingDry();
			}
		}
		
		return arousal;
	}
	
	public List<String> getPenetrationModifiersAsStringList(GameCharacter target, SexAreaPenetration penetration) {
		List<String> modifiersList = new ArrayList<>();

		if(!Main.sex.getOngoingSexAreas(target, penetration).isEmpty()
				&& !Collections.disjoint(Main.sex.getOngoingSexAreas(target, penetration).get(Main.sex.getCharacterOngoingSexArea(target, penetration).get(0)), Util.newArrayListOfValues(SexAreaPenetration.values()))) {

			modifiersList.add("[style.boldSex(Arousal/turn:)]");

			String targetName = target.isPlayer()?"You":UtilText.parse(target, "[npc.Name]");
			modifiersList.add("+"+penetration.getBaseArousalWhenPenetrating()+" <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>("+targetName+"</b> - [style.boldSex(Sex)])");
			
			if(!Main.sex.hasLubricationTypeFromAnyone(target, penetration)) {
				modifiersList.add(penetration.getArousalChangePenetratingDry()+ " <b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>("+targetName+"</b> - [style.boldBad(Dry)])");
			}
			
		} else {
			modifiersList.add("[style.colourDisabled(No bonuses)]");
		}
		return modifiersList;
	}
	
	public static float getOrificeArousalPerTurnSelf(GameCharacter target, SexAreaOrifice orifice) {
		float arousal = 0;
		
		if(!Main.sex.getOngoingSexAreas(target, orifice).isEmpty()) {
			arousal+=orifice.getBaseArousalWhenPenetrated();
			
			if(Main.sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				if(target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_SIZE_QUEEN)) { // Positive for masochist and size queen:
					arousal += Math.abs(orifice.getArousalChangePenetratedStretching());
					
				} else {
					arousal += orifice.getArousalChangePenetratedStretching();
				}
			}
			if(Main.sex.getAreasTooLoose(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedTooLoose();
			}
			if(!Main.sex.hasLubricationTypeFromAnyone(target, orifice)) {
				arousal += orifice.getArousalChangePenetratedDry();
			}

			if(Main.game.isPenetrationLimitationsEnabled() && orifice.isInternalOrifice()) {
				if(!Main.sex.getCharactersPenetratingTooDeep(target, (SexAreaOrifice)orifice).isEmpty()) {
					if(target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_SIZE_QUEEN)) { // Positive for masochist and size queen:
						arousal += 2.5f;
						
					} else {
						arousal -= 10;
					}
					
				} else if(!Main.sex.getCharactersPenetratingFarTooShallow(target, (SexAreaOrifice)orifice).isEmpty()) {
					arousal -= 2;
				}
			}
		}
		
		return arousal;
	}
	
	public float getOrificeArousalPerTurnPartner(GameCharacter self, GameCharacter target, SexAreaOrifice orifice) {
		float arousal = 0;
		
		if(Main.sex.getOngoingSexAreas(self, orifice).containsKey(target)) {
			for(SexAreaInterface sArea : Main.sex.getOngoingSexAreas(self, orifice).get(target)) {
				if(sArea.isPenetration()) {
					arousal+=((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating();
				} else if(sArea.isOrifice()) {
					arousal+=((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated();
				}
			}
			
			if(Main.sex.getAreasCurrentlyStretching(self).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratingStretching();
			}
			if(Main.sex.getAreasTooLoose(self).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratingTooLoose();
			}
			if(!Main.sex.hasLubricationTypeFromAnyone(self, orifice)) {
				arousal += orifice.getArousalChangePenetratingDry();
			}
		}
		
		return arousal;
	}
	
	public List<String> getOrificeModifiersAsStringList(GameCharacter target, SexAreaOrifice orifice) {
		List<String> modifiersList = new ArrayList<>();
		
		modifiersList.add("[style.boldSex(Arousal/turn:)]");

		String targetName = target.isPlayer()?"You":UtilText.parse(target, "[npc.Name]");
		
		if(!Main.sex.getOngoingSexAreas(target, orifice).isEmpty()) {
			modifiersList.add("+"+orifice.getBaseArousalWhenPenetrated()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldSex(Sex)])");
			
			if(Main.sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				float arousalIncrement = orifice.getArousalChangePenetratedStretching();
				if(target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_SIZE_QUEEN)) { // Positive for masochist and size queen:
					arousalIncrement = Math.abs(arousalIncrement);
					modifiersList.add((arousalIncrement>0?"+":"")+arousalIncrement+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldSex(Stretching)])");

				} else {
					modifiersList.add((arousalIncrement>0?"+":"")+arousalIncrement+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Stretching)])");
				}
			}
			if(Main.sex.getAreasTooLoose(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedTooLoose()>0?"+":"")
						+orifice.getArousalChangePenetratedTooLoose()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Too loose)])");
			}
			if(!Main.sex.hasLubricationTypeFromAnyone(target, orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedDry()>0?"+":"")
						+orifice.getArousalChangePenetratedDry()+" (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Dry)])");
			}

			if(Main.game.isPenetrationLimitationsEnabled() && orifice.isInternalOrifice()) {
				if(!Main.sex.getCharactersPenetratingTooDeep(target, (SexAreaOrifice)orifice).isEmpty()) {
					if(target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_SIZE_QUEEN)) { // Positive for masochist and size queen:
						modifiersList.add("+2.5 (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldSex(Too deep)])");
						
					} else {
						modifiersList.add("-10 (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Too deep)])");
						
					}
					
				} else if(!Main.sex.getCharactersPenetratingFarTooShallow(target, (SexAreaOrifice)orifice).isEmpty()) {
					modifiersList.add("-2 (<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>"+targetName+"</b> - [style.boldBad(Too shallow)])");
				}
			}
			
			for(GameCharacter penetrator : Main.sex.getOngoingSexAreas(target, orifice).keySet()) {
				String penetratorName = penetrator.isPlayer()?"You":UtilText.parse(penetrator, "[npc.Name]");
				
				if(!Main.sex.getOngoingSexAreas(target, orifice).isEmpty()) {
					modifiersList.add("+"+getOrificeArousalPerTurnPartner(target, penetrator, orifice)+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldSex(Sex)])");
					
					if(Main.sex.getAreasCurrentlyStretching(target).contains(orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingStretching()>0?"+":"")
								+orifice.getArousalChangePenetratingStretching()+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldGood(Tight)])");
					}
					if(Main.sex.getAreasTooLoose(target).contains(orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingTooLoose()>0?"+":"")
								+orifice.getArousalChangePenetratingTooLoose()+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldBad(Too loose)])");
					}
					if(!Main.sex.hasLubricationTypeFromAnyone(target, orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingDry()>0?"+":"")
								+orifice.getArousalChangePenetratingDry()+" (<b style='color:"+penetrator.getFemininity().getColour().toWebHexString()+";'>"+penetratorName+"</b> - [style.boldBad(Dry)])");
					}
				}
			}
			
		} else {
			modifiersList.add("[style.colourDisabled(No bonuses)]");
		}
		return modifiersList;
	}
	
	protected static List<Value<Integer, String>> getInternalOrificeExtraDescriptions(GameCharacter target, SexAreaOrifice orifice) {
		if(Main.sex.getCharactersHavingOngoingActionWith(target, orifice).isEmpty()) {
			return null;
		}
		
		List<GameCharacter> ongoingCharacters = Main.sex.getCharactersHavingOngoingActionWith(target, orifice);
		GameCharacter partner = ongoingCharacters.get(Util.random.nextInt(ongoingCharacters.size()));
		
		List<Value<Integer, String>> additionalDescriptions = new ArrayList<>();
		
		additionalDescriptions.add(
				new Value<>(3,
						Main.sex.formatPenetration(
						target.getPenetrationDescription(false,
								partner,
								(SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next(),
								target,
								orifice))));
		
		StringBuilder sb = new StringBuilder();
		sb.append(UtilText.parse(target,
				"<p style='text-align:center; margin:0; padding:0;'>"
					+ "<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.NamePos]</b> [style.boldSex("+Util.capitaliseSentence(orifice.getName(target, true))+")]:"
					+ "<br/>Capacity: "+Units.size(Units.round(orifice.getCapacity(target, true), 1))
					+ (Main.game.isPenetrationLimitationsEnabled()
						?"<br/>Depth Comfortable / Uncomfortable: "+Units.size(Units.round(orifice.getMaximumPenetrationDepthComfortable(target), 1))+" / "+Units.size(Units.round(orifice.getMaximumPenetrationDepthUncomfortable(target), 1))
						:"")
				+ "</p>"));
		additionalDescriptions.add(new Value<>(3, sb.toString()));
		
		for(GameCharacter character : ongoingCharacters) {
			SexAreaPenetration penetration = (SexAreaPenetration)Main.sex.getOngoingActionsMap(target).get(orifice).get(partner).iterator().next();
			
			if(penetration.appliesStretchEffects(character)) {
				sb = new StringBuilder();
				int length = (int) character.getPenetrationLengthInserted(penetration, target, orifice);
				boolean knotting = Objects.equals(Main.sex.getCharacterKnotting(character), target);
				sb.append(UtilText.parse(character,
						"<p style='text-align:center; margin:0; padding:0;'>"//TODO toy length/diameter
							+ "<b style='color:"+character.getFemininity().getColour().toWebHexString()+";'>[npc.NamePos]</b> [style.boldSex("+Util.capitaliseSentence(penetration.getName(character, true))+")]:"
							+ "<br/>Diameter"+(knotting?"[style.boldSex(*2 from swollen knot)]":"")+": "+Units.size(Units.round(penetration.getDiameter(character, (penetration.getLength(character, true))-length)*(knotting?2:1), 1))
							+ (Main.game.isPenetrationLimitationsEnabled()
									?"<br/>Inserted / Total length: "+Units.size(length)+" / "+Units.size(penetration.getLength(character, true))
									:"")
						+ "</p>"));
				additionalDescriptions.add(new Value<>(3, sb.toString()));
			}
		}
		
		return additionalDescriptions;
	
	}
	
	public void appendPenetrationAdditionGenericDescriptions(GameCharacter owner, SexAreaPenetration penetration, String penetrationName, StringBuilder stringBuilderToAppendTo) {
		if(!Main.sex.hasLubricationTypeFromAnyone(owner, penetration)) {
			stringBuilderToAppendTo.append("<br/>"+penetrationName+" "+(penetration.isPlural()?"are":"is")+" [style.boldBad(dry)]!");
			
		} else {
			stringBuilderToAppendTo.append("<br/>"+penetrationName+" "+(penetration.isPlural()?"have":"has")+" been [style.boldSex(lubricated)] by:<br/>");
			int i=0;
			List<String> lubricants = new ArrayList<>();
			for(GameCharacter lubricantProvider : Main.sex.getAllParticipants()) {
				for(LubricationType lt : Main.sex.getWetAreas(owner).get(penetration).get(lubricantProvider)) {
					if(i==0) {
						lubricants.add(lubricantProvider==null
								?Util.capitaliseSentence(lt.getName(lubricantProvider))
								:UtilText.parse(lubricantProvider, "[npc.NamePos] <span style='"+lt.getColour().toWebHexString()+"'>"+lt.getName(lubricantProvider)+"</span>"));
					} else {
						lubricants.add(lubricantProvider==null
								?lt.getName(lubricantProvider)
								:UtilText.parse(lubricantProvider, "[npc.namePos] <span style='"+lt.getColour().toWebHexString()+"'>"+lt.getName(lubricantProvider)+"</span>"));
					}
					i++;
				}
			}
			for(LubricationType lt : Main.sex.getWetAreas(owner).get(penetration).get(null)) {
				if(i==0) {
					lubricants.add(Util.capitaliseSentence(lt.getName(null)));
				} else {
					lubricants.add(lt.getName(null));
				}
				i++;
			}
			stringBuilderToAppendTo.append(Util.stringsToStringList(lubricants, false)+".");
		}
		
		stringBuilderToAppendTo.append("</p>");
	}
	
	public void appendOrificeAdditionGenericDescriptions(GameCharacter owner, SexAreaOrifice orificeType, String orificeName, StringBuilder stringBuilderToAppendTo) {
		if(Main.sex.getAreasCurrentlyStretching(owner).contains(orificeType)) {
			if(Main.sex.getFirstOngoingSexAreaPenetration(owner, orificeType)==null) {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" has been <b style='color:"+PresetColour.BASE_PINK_DEEP.toWebHexString()+";'>stretched</b>!");
				
			} else {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" being <b style='color:"+PresetColour.BASE_PINK_DEEP.toWebHexString()+";'>stretched</b>!");
			}
			
		} else if(Main.sex.getAreasTooLoose(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" <b style='color:"+PresetColour.BASE_RED.toWebHexString()+";'>too loose</b>!");
			
		} else if(Main.sex.getAreasStretched(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" has been <b style='color:"+PresetColour.BASE_PINK_DEEP.toWebHexString()+";'>stretched</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/><b style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>No stretch effect.</b>");
		}
		
		if(Main.game.isPenetrationLimitationsEnabled() && orificeType.isInternalOrifice()) {
			if(!Main.sex.getCharactersPenetratingTooDeep(owner, orificeType).isEmpty()) {
				if(owner.hasFetish(Fetish.FETISH_MASOCHIST) || owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					stringBuilderToAppendTo.append("<br/>"+orificeName+" is being penetrated [style.boldSex(too deeply)]!");
				} else {
					stringBuilderToAppendTo.append("<br/>"+orificeName+" is being penetrated [style.boldTerrible(too deeply)]!");
				}
				
			} else if(!Main.sex.getCharactersPenetratingFarTooShallow(owner, orificeType).isEmpty()) {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" is [style.boldPinkLight(not being penetrated deep enough)]!");
			}
		}
		
		if(!Main.sex.hasLubricationTypeFromAnyone(owner, orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" [style.boldBad(dry)]!");
			
		} else {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"have":"has")+" been [style.boldSex(lubricated)] by:<br/>");
			int i=0;
			List<String> lubricants = new ArrayList<>();
			for(GameCharacter lubricantProvider : Main.sex.getAllParticipants()) {
				for(LubricationType lt : Main.sex.getWetAreas(owner).get(orificeType).get(lubricantProvider)) {
					if(i==0) {
						lubricants.add(lubricantProvider==null
								?Util.capitaliseSentence(lt.getName(lubricantProvider))
								:UtilText.parse(lubricantProvider, "[npc.NamePos] "+lt.getName(lubricantProvider)));
					} else {
						lubricants.add(lubricantProvider==null
								?lt.getName(lubricantProvider)
								:UtilText.parse(lubricantProvider, "[npc.namePos] "+lt.getName(lubricantProvider)));
					}
					i++;
				}
			}
			for(LubricationType lt : Main.sex.getWetAreas(owner).get(orificeType).get(null)) {
				if(i==0) {
					lubricants.add(Util.capitaliseSentence(lt.getName(null)));
				} else {
					lubricants.add(lt.getName(null));
				}
				i++;
			}
			stringBuilderToAppendTo.append(Util.stringsToStringList(lubricants, false)+".");
		}
		stringBuilderToAppendTo.append("</p>");
	}
	
	public String getPenetrationSVGString(GameCharacter owner, SexAreaInterface penetration, String baseSVG) {
		StringBuilder SVGImageSB = new StringBuilder();

		if(!Main.sex.getOngoingSexAreas(owner, penetration).isEmpty()) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getActiveSexBackground()+"</div>");
		}
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");
		
		if(!Main.sex.getOngoingSexAreas(owner, penetration).isEmpty()) {
			SexAreaPenetration firstPenetration = null;
			outerloop:
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(owner, penetration).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						firstPenetration = (SexAreaPenetration) sArea;
						break outerloop;
					}
				}
			}
			if(firstPenetration!=null) {
				switch(firstPenetration){
					case FINGER:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
						break;
					case PENIS:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
						break;
					case TAIL:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
						break;
					case TONGUE:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
						break;
					default:
						break;
				}
			}
		}
		
		if(!Main.sex.hasLubricationTypeFromAnyone(owner, penetration)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
		} else {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
		}
		
		return SVGImageSB.toString();
	}
	
	public String getOrificeSVGString(GameCharacter owner, SexAreaInterface orifice, String baseSVG) {
		StringBuilder SVGImageSB = new StringBuilder();

		if(!Main.sex.getOngoingSexAreas(owner, orifice).isEmpty()) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getActiveSexBackground()+"</div>");
		}
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");
		
		if(!Main.sex.getOngoingSexAreas(owner, orifice).isEmpty()) {
			int rightOffset = 0;
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Main.sex.getOngoingSexAreas(owner, orifice).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
								break;
							case PENIS:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
								break;
							case TAIL:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
								break;
							case TONGUE:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
								break;
							case CLIT:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeClit()+"</div>");
								break;
							case FOOT:
								SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:"+rightOffset+"%;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFoot()+"</div>");
								break;
							case TENTACLE:
								break;
						}
						rightOffset+=8;
					}
				}
			}
			if(Main.game.isPenetrationLimitationsEnabled() && orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice()) {
				if(!Main.sex.getCharactersPenetratingTooDeep(owner, (SexAreaOrifice)orifice).isEmpty()) {
					SVGImageSB.append("<div style='width:45%;height:45%;position:absolute;left:2.5%;bottom:2.5%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDepthMaximum()+"</div>");
					
				} else if(!Main.sex.getCharactersPenetratingFarTooShallow(owner, (SexAreaOrifice)orifice).isEmpty()) {
					SVGImageSB.append("<div style='width:45%;height:45%;position:absolute;left:2.5%;bottom:2.5%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDepthMinimum()+"</div>");
				}
			}
		}
		
		if(Main.sex.getAreasCurrentlyStretching(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationStretching()+"</div>");
		}
		
		if(Main.sex.getAreasTooLoose(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationTooLoose()+"</div>");
		}
		
		if(Main.game.isPenetrationLimitationsEnabled()) {
			if(Main.sex.hasLubricationTypeFromAnyone(owner, orifice)) {
				SVGImageSB.append("<div style='width:20%;height:20%;position:absolute;left:2%;top:2%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
			}
			
		} else {
			if(!Main.sex.hasLubricationTypeFromAnyone(owner, orifice)) {
				SVGImageSB.append("<div style='width:45%;height:45%;position:absolute;left:2.5%;bottom:2.5%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
			} else {
				SVGImageSB.append("<div style='width:45%;height:45%;position:absolute;left:2.5%;bottom:2.5%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
			}
		}
		
		return SVGImageSB.toString();
	}
	
	public String getCreampieSVGString(GameCharacter owner, SexAreaOrifice orifice) {
		StringBuilder SVGImageSB = new StringBuilder();
		
		boolean justCum = owner.isOnlyCumInArea(orifice);
		
		if(isCumEffectPositive(owner)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"
									+ (justCum
											?SVGImages.SVG_IMAGE_PROVIDER.getCreampieMasochist()
											:SVGImages.SVG_IMAGE_PROVIDER.getFluidIngestedMasochist())
								+"</div>");
		} else {
			SVGImageSB.append((justCum
					?SVGImages.SVG_IMAGE_PROVIDER.getCreampie()
					:SVGImages.SVG_IMAGE_PROVIDER.getFluidIngested()));
		}
		
		switch(orifice) {
			case ANUS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
				break;
			case ASS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
				break;
			case BREAST:
				if(owner.hasBreasts()) {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts()+"</div>");
				} else {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat()+"</div>");
				}
				break;
			case NIPPLE:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple()+"</div>");
				break;
			case BREAST_CROTCH:
				if(owner.getBreastCrotchShape()==BreastShape.UDDERS) {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUdders()+"</div>");
				} else {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch()+"</div>");
				}
				break;
			case NIPPLE_CROTCH:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNippleCrotch()+"</div>");
				break;
			case MOUTH:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div>");
				break;
			case THIGHS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs()+"</div>");
				break;
			case URETHRA_PENIS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis()+"</div>");
				break;
			case URETHRA_VAGINA:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina()+"</div>");
				break;
			case VAGINA:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
				break;
		}
		
		return SVGImageSB.toString();
	}
	
	protected static boolean isCumEffectPositive(GameCharacter target) {
		return target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_CUM_ADDICT);
	}
	
	public static boolean isExposedParts(GameCharacter target, boolean requiresBreastsExposed, boolean requiresGenitalsExposed) {
		boolean breastsExposed = (((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaVisible(CoverableArea.NIPPLES)) || (target.hasBreastsCrotch() && target.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH)));
		boolean genitalsExposed = (target.isCoverableAreaVisible(CoverableArea.ANUS) || (target.isCoverableAreaVisible(CoverableArea.PENIS) && target.hasPenis()) || (target.isCoverableAreaVisible(CoverableArea.VAGINA) && target.hasVagina()));
		
		return !Main.game.isInSex()
				&& target.getLegConfiguration().isGenitalsExposed(target)
				&& (requiresBreastsExposed?breastsExposed:!breastsExposed)
				&& (requiresGenitalsExposed?genitalsExposed:!genitalsExposed);
	}
	
	public static String getExposedPartsNamesList(GameCharacter owner) {
		List<String> names = new ArrayList<>();
		
		if(owner.hasBreasts() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES)) {
			names.add("breasts");
		} else if (owner.isFeminine() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES)) {
			names.add("nipples");
		}
		if(owner.hasBreastsCrotch()
				&& Main.getProperties().udders>0
				&& owner.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH)) {
			names.add(UtilText.parse(owner, "[npc.crotchBoobs]"));
		}
		if(owner.isCoverableAreaVisible(CoverableArea.ANUS)) {
			names.add("asshole");
		}
		if(owner.hasPenis() && owner.isCoverableAreaVisible(CoverableArea.PENIS)) {
			names.add("cock");
		}
		if(owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA)) {
			names.add("pussy");
		}
		if(!owner.hasPenis() && !owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA)) {
			names.add("genderless mound");
		}
		
		return Util.stringsToStringChoice(names, false);
	}
	
	public static String getExposedStatus(GameCharacter owner, String baseSVG) {
		StringBuilder SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");

		
		boolean breastsExposed = owner.hasBreasts() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES);
		boolean nipplesExposed = ! breastsExposed && owner.isFeminine() && owner.isCoverableAreaVisible(CoverableArea.NIPPLES);
		boolean crotchBoobsExposed = owner.hasBreastsCrotch()
				&& Main.getProperties().udders>0
				&& owner.isCoverableAreaVisible(CoverableArea.NIPPLES_CROTCH);

		boolean anusExposed = owner.isCoverableAreaVisible(CoverableArea.ANUS);
		
		boolean penisExposed = owner.hasPenis() && owner.isCoverableAreaVisible(CoverableArea.PENIS);
		boolean vaginaExposed = owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA);
		boolean moundExposed = !owner.hasPenis() && !owner.hasVagina() && owner.isCoverableAreaVisible(CoverableArea.VAGINA);

		int exposedAreas = 0;
		if(moundExposed) {
			exposedAreas++;
		} else {
			exposedAreas+= (penisExposed?1:0) + (vaginaExposed?1:0);
		}
		exposedAreas+= (anusExposed?1:0) + (breastsExposed || nipplesExposed?1:0) + (crotchBoobsExposed?1:0);
		
		int size = Math.min(50, (100/(exposedAreas+1)));
		int marginTop = (100-(exposedAreas*size))/2;
		int marginLeft = (50-size)/2;

		if(breastsExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts()+"</div>");
			marginTop+=size;
		} else if (nipplesExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat()+"</div>");
			marginTop+=size;
		}
		if(crotchBoobsExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsCrotch()+"</div>");
			marginTop+=size;
		}
		if(anusExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
			marginTop+=size;
		}
		if(moundExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMound()+"</div>");
			marginTop+=size;
		}
		if(penisExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
			marginTop+=size;
		}
		if(vaginaExposed) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
			marginTop+=size;
		}
		
		return SVGImageSB.toString();
	}
	
	public static String getRecoveringOrificeStatus(GameCharacter owner, String baseSVG) {
		StringBuilder SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;top:25%;'>"+baseSVG+"</div>");

		boolean vaginaRecovering = owner.hasVagina() && owner.getVaginaRawCapacityValue()!=owner.getVaginaStretchedCapacity();
		boolean anusRecovering = owner.getAssRawCapacityValue()!=owner.getAssStretchedCapacity();
		boolean throatRecovering = owner.getFaceRawCapacityValue()!=owner.getFaceStretchedCapacity();
		boolean nipplesRecovering = owner.getNippleRawCapacityValue()!=owner.getNippleStretchedCapacity();
		boolean nipplesCrotchRecovering = owner.hasBreastsCrotch()
				&& Main.getProperties().udders>0
				&& owner.getNippleCrotchRawCapacityValue()!=owner.getNippleCrotchStretchedCapacity();
		boolean penileUrethraRecovering = owner.hasPenis() && owner.getPenisRawCapacityValue()!=owner.getPenisStretchedCapacity();
		boolean vaginalUrethraRecovering = owner.hasVagina() && owner.getVaginaUrethraRawCapacityValue()!=owner.getVaginaUrethraStretchedCapacity();
		
		int exposedAreas = (vaginaRecovering?1:0) + (anusRecovering?1:0) + (throatRecovering?1:0) + (nipplesRecovering?1:0) + (nipplesCrotchRecovering?1:0) + (penileUrethraRecovering?1:0) + (vaginalUrethraRecovering?1:0);
		
		int size = Math.min(50, (100/(exposedAreas+1)));
		int marginTop = (100-(exposedAreas*size))/2;
		int marginLeft = (50-size)/2;

		if(vaginaRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
			marginTop+=size;
		}
		if(anusRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
			marginTop+=size;
		}
		if(throatRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div>");
			marginTop+=size;
		}
		if(nipplesRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple()+"</div>");
			marginTop+=size;
		}
		if(nipplesCrotchRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNippleCrotch()+"</div>");
			marginTop+=size;
		}
		if(penileUrethraRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis()+"</div>");
			marginTop+=size;
		}
		if(vaginalUrethraRecovering) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina()+"</div>");
			marginTop+=size;
		}
		
		return SVGImageSB.toString();
	}
	
	public static String getStretchingOrificeStatus(GameCharacter owner, boolean stretch, boolean stretchRecoveryPrevented, boolean tooDeep, Set<SexAreaOrifice> orifices) {
		StringBuilder SVGImageSB = new StringBuilder();
		
		int iconSize = stretch&&stretchRecoveryPrevented&&tooDeep?33:50;
		int iconRight = stretch&&stretchRecoveryPrevented&&tooDeep?(50-33)/2:0;
		
		int topMargin = 0;
		if(stretch) {
			SVGImageSB.append("<div style='width:"+iconSize+"%;height:"+iconSize+"%;position:absolute;right:"+iconRight+";top:"+(tooDeep||stretchRecoveryPrevented?"0":"25%")+";'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationStretching()+"</div>");
			topMargin+=iconSize;
		}
		if(stretchRecoveryPrevented) {
			SVGImageSB.append("<div style='width:"+iconSize+"%;height:"+iconSize+"%;position:absolute;right:"+iconRight+";top:"+(stretch||tooDeep?topMargin+"%":"25%")+";'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationStretchRecoveryPrevented()+"</div>");
			topMargin+=iconSize;
		}
		if(tooDeep) {
			SVGImageSB.append("<div style='width:"+iconSize+"%;height:"+iconSize+"%;position:absolute;right:"+iconRight+";top:"+(stretch||stretchRecoveryPrevented?topMargin+"%":"25%")+";'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDepthMaximum()+"</div>");
		}
		
		int size = Math.min(50, (100/(orifices.size()+1)));
		int marginTop = (100-(orifices.size()*size))/2;
		int marginLeft = (50-size)/2;

		if(orifices.contains(SexAreaOrifice.VAGINA)) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
			marginTop+=size;
		}
		if(orifices.contains(SexAreaOrifice.ANUS)) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
			marginTop+=size;
		}
		if(orifices.contains(SexAreaOrifice.NIPPLE)) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple()+"</div>");
			marginTop+=size;
		}
		if(orifices.contains(SexAreaOrifice.MOUTH)) {
			SVGImageSB.append("<div style='width:"+size+"%;height:"+size+"%;position:absolute;left:"+marginLeft+"%;top:"+marginTop+"%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div>");
			marginTop+=size;
		}
		
		return SVGImageSB.toString();
	}
}
