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
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractNippleType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	List<OrificeModifier> defaultRacialOrificeModifiers;
	
	/**
	 * @param coveringType What covers this nipple.
	 * @param race What race has this ass type.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this ass type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this ass type.
	 * @param defaultRacialOrificeModifiers Which modifiers this nipple naturally spawns with.
	 */
	public AbstractNippleType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		
		this.coveringType = coveringType;
		this.race = race;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
	}

	public AbstractNippleType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());
				
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
				System.err.println("AbstractNippleType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
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
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else if(gc.getBreastRows()==2) {
			return "two pairs of";
		} else {
			return "three pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(gc.getNippleShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("lipple", "nipple-lip");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("nipple", "teat");
				} else {
					return "nipple";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("nipple-cunt", "nipple-pussy");
		}
		return "";
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(gc.getNippleShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("lipples", "nipple-lips");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("nipples", "teats");
				} else {
					return "nipples";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("nipple-cunts", "nipple-pussies");
		}
		return "";
	}

	public String getNameCrotchSingular(GameCharacter gc) {
		switch(gc.getNippleCrotchShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("lipple", "nipple-lip");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("nipple", "teat");
				} else {
					return "nipple";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("nipple-cunt", "nipple-pussy");
		}
		return "";
	}
	
	public String getNameCrotchPlural(GameCharacter gc) {
		switch(gc.getNippleCrotchShape()) {
			case LIPS:
				return  UtilText.returnStringAtRandom("lipples", "nipple-lips");
			case INVERTED:
			case NORMAL:
				if(gc.hasBreasts()) {
					return UtilText.returnStringAtRandom("nipples", "teats");
				} else {
					return "nipples";
				}
			case VAGINA:
				return UtilText.returnStringAtRandom("nipple-cunts", "nipple-pussies");
		}
		return "";
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
