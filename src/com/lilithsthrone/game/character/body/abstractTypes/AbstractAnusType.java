package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import com.lilithsthrone.game.Game;
import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractAnusType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private boolean assHairAllowed;
	
	private List<String> names;
	private List<String> namesPlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private List<OrificeModifier> defaultRacialOrificeModifiers;
	
	/**
	 * @param coveringType What covers this anus.
	 * @param race What race has this ass type.
	 * @param names A list of singular names for this ass type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this ass type. Pass in null to use generic names.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ass type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ass type.
	 * @param defaultRacialOrificeModifiers Which modifiers this anus naturally spawns with.
	 */
	public AbstractAnusType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<OrificeModifier> defaultRacialOrificeModifiers) {

		this.assHairAllowed = race.getRacialClass().isAnthroHair();
		
		this.coveringType = coveringType;
		this.race = race;
		
		this.names = names;
		this.namesPlural = namesPlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
	}
	
	public AbstractAnusType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				DocumentBuilder dBuilder = Game.docFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.assHairAllowed = race.getRacialClass().isAnthroHair();
				if(coreElement.getOptionalFirstOf("assHairAllowed").isPresent()) {
					this.assHairAllowed = Boolean.valueOf(coreElement.getMandatoryFirstOf("assHairAllowed").getTextContent());
				}
				
				this.names = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("names").getAllOf("name")) {
					names.add(e.getTextContent());
				}
				
				this.namesPlural = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesPlural").getAllOf("name")) {
					namesPlural.add(e.getTextContent());
				}
				
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
				
				this.defaultRacialOrificeModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("defaultOrificeModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("defaultOrificeModifiers").getAllOf("modifier")) {
						defaultRacialOrificeModifiers.add(OrificeModifier.valueOf(e.getTextContent()));
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractAnusType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public boolean isAssHairAllowed() {
		return assHairAllowed;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(names==null || names.isEmpty()) {
			return UtilText.returnStringAtRandom("asshole", "back door", "rear entrance");
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null || namesPlural.isEmpty()) {
			return UtilText.returnStringAtRandom("assholes", "back doors", "rear entrances");
		}
		return Util.randomItemFrom(namesPlural);
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

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
