package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractHornType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private int defaultHornsPerRow;

	private String name;
	private String namePlural;

	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String hornTransformationDescription;
	private String hornBodyDescription;
	
	/**
	 * @param coveringType What covers this horn type (i.e skin/fur/feather type).
	 * @param race What race has this horn type.
	 * @param defaultHornsPerRow The number of horns per row by default for this horn type.
	 * @param transformationName The name that should be displayed when offering this horn type as a transformation. Should be something like "curved" or "straight".
	 * @param name The singular name of the horn. This will usually just be "horn" or "antler".
	 * @param namePlural The plural name of the horn. This will usually just be "horns" or "antlers".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this horn type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this horn type.
	 * @param hornTransformationDescription A paragraph describing a character's horns transforming into this horn type. Parsing assumes that the character already has this horn type and associated skin covering.
	 * @param hornBodyDescription A sentence or two to describe this horn type, as seen in the character view screen. It should follow the same format as all of the other entries in the HornType class.
	 */
	public AbstractHornType(
			AbstractBodyCoveringType coveringType,
			AbstractRace race,
			int defaultHornsPerRow,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String hornTransformationDescription,
			String hornBodyDescription) {
		
		this.coveringType = coveringType;
		this.race = race;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.defaultHornsPerRow = defaultHornsPerRow;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.hornTransformationDescription = hornTransformationDescription;
		this.hornBodyDescription = hornBodyDescription;
	}
	
	public AbstractHornType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.docBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());
				
				this.defaultHornsPerRow = Integer.valueOf(coreElement.getMandatoryFirstOf("defaultHornsPerRow").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();
				

				this.descriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsMasculine").getAllOf("descriptor")) {
						descriptorsMasculine.add(e.getTextContent());
					}
				}
				
				this.descriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsFeminine").getAllOf("descriptor")) {
						descriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.hornTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.hornBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractAntennaType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getHornRows()==1) {
			if(gc.getHornsPerRow()==1) {
				return "a solitary";
			} else if(gc.getHornsPerRow()==2) {
				return "a pair of";
			} else if(gc.getHornsPerRow()==3) {
				return "a trio of";
			} else {
				return "a quartet of";
			}
			
		} else {
			if(gc.getHornsPerRow()==1) {
				return Util.intToString(gc.getHornRows())+" vertically-aligned";
			} else if(gc.getHornsPerRow()==2) {
				return Util.intToString(gc.getHornRows())+" pairs of";
			} else if(gc.getHornsPerRow()==3) {
				return Util.intToString(gc.getHornRows())+" trios of";
			} else {
				return Util.intToString(gc.getHornRows())+" quartets of";
			}
		}
	}

	public int getDefaultHornsPerRow() {
		return defaultHornsPerRow;
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return true;
	}

	@Override
	public String getName(GameCharacter gc){
		if(isDefaultPlural(gc) && (gc.getHornsPerRow()>1 || gc.getHornRows()>1)) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

	@Override
	public TFModifier getTFModifier() {
		return this == HornType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(HornType.getHornTypes(race, false));
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, hornBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, hornTransformationDescription);
	}
}
