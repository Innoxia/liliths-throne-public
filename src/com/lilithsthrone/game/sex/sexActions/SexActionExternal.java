package com.lilithsthrone.game.sex.sexActions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * This class creates SexActions from external xml files.
 * 
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class SexActionExternal extends SexAction {
	
	private int renderingPriority = 0;
	private String actionConditional;
	private String endsSexString;
	private String priorityString;
	
	private List<AbstractFetish> performingFetishes;
	private List<AbstractFetish> targetedFetishes;

	private String startEffects;
	private String effects;
	private String endEffects;
	
	private List<SexAreaInterface> areasCummedIn;
	private List<CoverableArea> areasCummedOn;
	
	private String title;
	private String tooltip;
	private List<DescriptionContainer> descriptions;
	
	private class DescriptionContainer {
		String conditional;
		List<String> descriptions;
	}
	
	public SexActionExternal(File XMLFile, String author, boolean mod) {
		super(SexActionType.SPECIAL,
				ArousalIncrease.ZERO_NONE,
				ArousalIncrease.ZERO_NONE,
				CorruptionLevel.ZERO_PURE,
				null,
				SexParticipantType.NORMAL,
				null);
		
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in SexAction files it's <sexAction>
				
				this.mod = mod;
				this.fromExternalFile = true;
				this.author = author;
				
				if(elementPresentAndNotEmpty(coreElement, "buttonPriority")) {
					renderingPriority = Integer.valueOf(coreElement.getMandatoryFirstOf("buttonPriority").getTextContent().trim());
				}
				
				if(elementPresentAndNotEmpty(coreElement, "actionConditional")) {
					actionConditional = coreElement.getMandatoryFirstOf("actionConditional").getTextContent().trim();
				}

				if(elementPresentAndNotEmpty(coreElement, "type")) {
					sexActionType = SexActionType.valueOf(coreElement.getMandatoryFirstOf("type").getTextContent().trim());
				}

				if(elementPresentAndNotEmpty(coreElement, "pace")) {
					associatedSexPace = SexPace.valueOf(coreElement.getMandatoryFirstOf("pace").getTextContent().trim());
				}

				if(elementPresentAndNotEmpty(coreElement, "endsSex")) {
					endsSexString = coreElement.getMandatoryFirstOf("endsSex").getTextContent().trim();
				}

				if(elementPresentAndNotEmpty(coreElement, "participantType")) {
					participantType = SexParticipantType.valueOf(coreElement.getMandatoryFirstOf("participantType").getTextContent().trim());
				}

				if(elementPresentAndNotEmpty(coreElement, "priority")) {
					priorityString = coreElement.getMandatoryFirstOf("priority").getTextContent().trim();
				}

				if(elementPresentAndNotEmpty(coreElement, "arousalPerformer")) {
					selfArousalGain = ArousalIncrease.valueOf(coreElement.getMandatoryFirstOf("arousalPerformer").getTextContent().trim());
				}
				
				if(elementPresentAndNotEmpty(coreElement, "arousalTarget")) {
					targetArousalGain = ArousalIncrease.valueOf(coreElement.getMandatoryFirstOf("arousalTarget").getTextContent().trim());
				}
				
				if(elementPresentAndNotEmpty(coreElement, "associatedCorruption")) {
					minimumCorruptionNeeded = CorruptionLevel.valueOf(coreElement.getMandatoryFirstOf("associatedCorruption").getTextContent().trim());
				}
				
				if(elementPresentAndNotEmpty(coreElement, "associatedFetishes")) {
					Element fetishesContainer = coreElement.getMandatoryFirstOf("associatedFetishes");
					if(fetishesContainer.getOptionalFirstOf("performer").isPresent()) {
						performingFetishes = new ArrayList<>();
						for(Element fetish : fetishesContainer.getMandatoryFirstOf("performer").getAllOf("fetish")) {
							performingFetishes.add(Fetish.getFetishFromId(fetish.getTextContent().trim()));
						}
					}
					if(fetishesContainer.getOptionalFirstOf("target").isPresent()) {
						targetedFetishes = new ArrayList<>();
						for(Element fetish : fetishesContainer.getMandatoryFirstOf("target").getAllOf("fetish")) {
							targetedFetishes.add(Fetish.getFetishFromId(fetish.getTextContent().trim()));
						}
					}
				}
				
				this.sexAreaInteractions = new HashMap<>();
				SexAreaInterface performingArea = null;
				SexAreaInterface targetedArea = null;
				if(elementPresentAndNotEmpty(coreElement, "performingArea")) {
					Element performingElement = coreElement.getMandatoryFirstOf("performingArea");
					if(performingElement.getTextContent().startsWith("PENETRATION_")) {
						performingArea = SexAreaPenetration.valueOf(performingElement.getTextContent().replaceAll("PENETRATION_", "").trim());
					} else if(performingElement.getTextContent().startsWith("ORIFICE_")) {
						performingArea = SexAreaOrifice.valueOf(performingElement.getTextContent().replaceAll("ORIFICE_", "").trim());
					}
				}
				if(elementPresentAndNotEmpty(coreElement, "targetedArea")) {
					Element targetedElement = coreElement.getMandatoryFirstOf("targetedArea");
					if(targetedElement.getTextContent().startsWith("PENETRATION_")) {
						targetedArea = SexAreaPenetration.valueOf(targetedElement.getTextContent().replaceAll("PENETRATION_", "").trim());
					} else if(targetedElement.getTextContent().startsWith("ORIFICE_")) {
						targetedArea = SexAreaOrifice.valueOf(targetedElement.getTextContent().replaceAll("ORIFICE_", "").trim());
					}
				}
				if(performingArea!=null || targetedArea!=null) {
					sexAreaInteractions.put(performingArea, targetedArea);
				}

				if(elementPresentAndNotEmpty(coreElement, "applyStartEffects")) {
					startEffects = coreElement.getMandatoryFirstOf("applyStartEffects").getTextContent().trim();
				}
				
				if(elementPresentAndNotEmpty(coreElement, "applyEffects")) {
					effects = coreElement.getMandatoryFirstOf("applyEffects").getTextContent().trim();
				}
				
				if(elementPresentAndNotEmpty(coreElement, "applyEndEffects")) {
					endEffects = coreElement.getMandatoryFirstOf("applyEndEffects").getTextContent().trim();
				}
				
				if(elementPresentAndNotEmpty(coreElement, "areasCummedIn")) {
					areasCummedIn = new ArrayList<>();
					for(Element orificeElement : coreElement.getMandatoryFirstOf("areasCummedIn").getAllOf("orifice")) {
						areasCummedIn.add(SexAreaOrifice.valueOf(orificeElement.getTextContent().replaceAll("ORIFICE_", "").trim()));
					}
				}

				if(elementPresentAndNotEmpty(coreElement, "areasCummedOn")) {
					areasCummedOn = new ArrayList<>();
					for(Element areaElement : coreElement.getMandatoryFirstOf("areasCummedOn").getAllOf("area")) {
						areasCummedOn.add(CoverableArea.valueOf(areaElement.getTextContent().replaceAll("ORIFICE_", "").trim()));
					}
				}

				title = coreElement.getMandatoryFirstOf("title").getTextContent().trim();
				tooltip = coreElement.getMandatoryFirstOf("tooltip").getTextContent().trim();
				
				descriptions = new ArrayList<>();
				Element descriptionsContainerElement = coreElement.getMandatoryFirstOf("descriptions");
				for(Element descriptionsElement : descriptionsContainerElement.getAllOf("description")) {
					DescriptionContainer descriptionContainer = new DescriptionContainer();
					if(descriptionsElement.getOptionalFirstOf("descriptionConditional").isPresent()) {
						descriptionContainer.conditional = descriptionsElement.getMandatoryFirstOf("descriptionConditional").getTextContent().trim();
					} else {
						descriptionContainer.conditional = "true";
					}
					descriptionContainer.descriptions = new ArrayList<>();
					for(Element textElement : descriptionsElement.getAllOf("text")) {
						descriptionContainer.descriptions.add(textElement.getTextContent().trim());
					}
					descriptions.add(descriptionContainer);
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("WorldType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	private boolean elementPresentAndNotEmpty(Element element, String tag) {
		try {
			return element.getOptionalFirstOf(tag).isPresent() && !element.getMandatoryFirstOf(tag).getTextContent().isEmpty();
		} catch (XMLMissingTagException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public int getActionRenderingPriority() {
		return renderingPriority;
	}

	@Override
	public boolean isBaseRequirementsMet() {
		if(actionConditional!=null && !actionConditional.isEmpty()) {
			return Boolean.valueOf(UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), actionConditional).trim());
		}
		return true;
	}

	@Override
	public boolean endsSex() {
		if(endsSexString!=null && !endsSexString.isEmpty()) {
			return Boolean.valueOf(UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), endsSexString).trim());
		}
		return false;
	}
	
	@Override
	public SexActionPriority getPriority() {
		if(priorityString!=null && !priorityString.isEmpty()) {
			return SexActionPriority.valueOf(UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), priorityString).trim());
		}
		return SexActionPriority.NORMAL;
	}

	@Override
	public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
		if(character.equals(Main.sex.getCharacterPerformingAction())) {
			return performingFetishes;
		}
		if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
			return targetedFetishes;
		}
		return null;
	}

	@Override
	public String applyPreParsingEffects() {
		if(startEffects!=null && !startEffects.isEmpty()) {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), startEffects).trim();
		}
		return "";
	}

	@Override
	public String applyEffectsString(){
		if(effects!=null && !effects.isEmpty()) {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), effects).trim();
		}
		return "";
	}

	@Override
	public String applyEndEffects(){
		if(endEffects!=null && !endEffects.isEmpty()) {
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), endEffects).trim();
		}
		return "";
	}

	@Override
	public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
		if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
			return areasCummedIn;
		}
		return null;
	}

	@Override
	public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
		if(cumProvider.equals(Main.sex.getCharacterPerformingAction()) && cumTarget.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
			return areasCummedOn;
		}
		return null;
	}

	@Override
	public String getActionTitle() {
		return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), title);
	}
	
	@Override
	public String getActionDescription() {
		return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), tooltip);
	}

	@Override
	public String getDescription() {
		for(DescriptionContainer descriptionContainer : descriptions) {
			if(descriptionContainer.conditional==null
					|| descriptionContainer.conditional.isEmpty()
					|| Boolean.valueOf(UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), descriptionContainer.conditional).trim())) {
				return UtilText.parse(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), Util.randomItemFrom(descriptionContainer.descriptions));
			}
		}
		return "[style.italicsRed(Error: No suitable description found for this action!)]";
	}
}
