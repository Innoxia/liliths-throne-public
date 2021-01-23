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
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.NippleType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractBreastType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private String transformationName;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;
	private AbstractNippleType nippleType;
	private AbstractFluidType fluidType;
	
	private List<String> namesFlat;
	private List<String> namesFlatPlural;
	private List<String> descriptorsFlat;

	private List<String> namesBreasts;
	private List<String> namesBreastsPlural;
	private List<String> descriptorsBreasts;
	
	private String breastsTransformationDescription;
	private String breastsBodyDescription;
	private String breastsCrotchTransformationDescription;
	private String breastsCrotchBodyDescription;
	
	/**
	 * @param coveringType What covers this breasts type (i.e skin/fur/feather type). This is never used, as skin type covering breasts is determined by torso covering.
	 * @param race What race has this breasts type.
	 * @param nippleType The type of nipples that these breasts have.
	 * @param fluidType The type of milk that these breasts produce.
	 * @param namesFlat A list of singular names for this breasts type for when the character has a flat chest. Pass in null to use generic names.
	 * @param namesFlatPlural A list of plural names for this breasts type for when the character has a flat chest. Pass in null to use generic names.
	 * @param descriptorsFlat The descriptors that can be used to describe this breasts type for when the character has a flat chest.
	 * @param namesBreasts A list of singular names for this breasts type for when the character has breasts, not a flat chest. Pass in null to use generic names.
	 * @param namesBreastsPlural A list of plural names for this breasts type for when the character has breasts, not a flat chest. Pass in null to use generic names.
	 * @param descriptorsBreasts The descriptors that can be used to this breasts type for when the character has breasts, not a flat chest.
	 * @param breastsTransformationDescription A paragraph describing a character's breasts transforming into this breasts type. Parsing assumes that the character already has this breasts type and associated skin covering.
	 * @param breastsBodyDescription A sentence or two to describe this breasts type, as seen in the character view screen. It should follow the same format as all of the other entries in the BreastType class.
	 * @param breastsCrotchTransformationDescription A paragraph describing a character's crotch-boobs transforming into this breasts type. Parsing assumes that the character already has this breasts type and associated skin covering.
	 * @param breastsCrotchBodyDescription A sentence or two to describe this crotch-boob type, as seen in the character view screen. It should follow the same format as all of the other entries in the BreastType class.
	 */
	public AbstractBreastType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			AbstractNippleType nippleType,
			AbstractFluidType fluidType,
			List<String> namesFlat,
			List<String> namesFlatPlural,
			List<String> descriptorsFlat,
			List<String> namesBreasts,
			List<String> namesBreastsPlural,
			List<String> descriptorsBreasts,
			String breastsTransformationDescription,
			String breastsBodyDescription,
			String breastsCrotchTransformationDescription,
			String breastsCrotchBodyDescription) {
		
		this.coveringType = coveringType;
		this.race = race;
		this.nippleType = nippleType;
		this.fluidType = fluidType;

		this.transformationName = null; // Use default race transformation name
		
		this.namesFlat = namesFlat;
		this.namesFlatPlural = namesFlatPlural;
		this.descriptorsFlat = descriptorsFlat;

		this.namesBreasts = namesBreasts;
		this.namesBreastsPlural = namesBreastsPlural;
		this.descriptorsBreasts = descriptorsBreasts;
		
		this.breastsTransformationDescription = breastsTransformationDescription;
		this.breastsBodyDescription = breastsBodyDescription;
		
		this.breastsCrotchTransformationDescription = breastsCrotchTransformationDescription;
		this.breastsCrotchBodyDescription = breastsCrotchBodyDescription;
	}
	
	public AbstractBreastType(AbstractBodyCoveringType skinType,
			AbstractRace race,
			AbstractNippleType nippleType,
			AbstractFluidType fluidType,
			String breastsTransformationDescription,
			String breastsBodyDescription,
			String breastsCrotchTransformationDescription,
			String breastsCrotchBodyDescription) {
		this(skinType,
				race,
				nippleType,
				fluidType,
				null, null, null, null, null, null,
				breastsTransformationDescription,
				breastsBodyDescription,
				breastsCrotchTransformationDescription,
				breastsCrotchBodyDescription);
	}

	public AbstractBreastType(File XMLFile, String author, boolean mod) {
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

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.nippleType = NippleType.getNippleTypeFromId(coreElement.getMandatoryFirstOf("nippleType").getTextContent());
				this.fluidType = FluidType.getFluidTypeFromId(coreElement.getMandatoryFirstOf("fluidType").getTextContent());
				
				this.namesBreasts = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("names").getAllOf("name")) {
					namesBreasts.add(e.getTextContent());
				}
				this.namesBreastsPlural = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesPlural").getAllOf("name")) {
					namesBreastsPlural.add(e.getTextContent());
				}
				this.descriptorsBreasts = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptors").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptors").getAllOf("descriptor")) {
						descriptorsBreasts.add(e.getTextContent());
					}
				}
				
				this.namesFlat = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesFlat").getAllOf("name")) {
					namesFlat.add(e.getTextContent());
				}
				this.namesFlatPlural = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesPluralFlat").getAllOf("name")) {
					namesFlatPlural.add(e.getTextContent());
				}
				this.descriptorsFlat = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsFlat").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsFlat").getAllOf("descriptor")) {
						descriptorsFlat.add(e.getTextContent());
					}
				}
				
				this.breastsTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.breastsBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();

				this.breastsCrotchTransformationDescription = coreElement.getMandatoryFirstOf("crotchBoobsTransformationDescription").getTextContent();
				this.breastsCrotchBodyDescription = coreElement.getMandatoryFirstOf("crotchBoobsBodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractBreastType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}
	
	public AbstractNippleType getNippleType() {
		return nippleType;
	}

	public AbstractFluidType getFluidType() {
		return fluidType;
	}

	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "a set of";
		}
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else {
			return Util.intToString(gc.getBreastRows())+" pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if (gc.hasBreasts()) {
			if(namesBreasts==null || namesBreasts.isEmpty()) {
				return UtilText.returnStringAtRandom("breast", "boob", "tit");
			}
			return Util.randomItemFrom(namesBreasts);
			
		} else {
			if(namesFlat==null || namesFlat.isEmpty()) {
				return UtilText.returnStringAtRandom("pec");
			}
			return Util.randomItemFrom(namesFlat);
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if (gc.hasBreasts()) {
			if(namesBreastsPlural==null || namesBreastsPlural.isEmpty()) {
				return UtilText.returnStringAtRandom("breasts", "boobs", "mammaries", "tits");
			}
			return Util.randomItemFrom(namesBreastsPlural);
			
		} else {
			if(namesFlatPlural==null || namesFlatPlural.isEmpty()) {
				return UtilText.returnStringAtRandom("pecs");
			}
			return Util.randomItemFrom(namesFlatPlural);
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.hasBreasts()) {
			if(descriptorsBreasts==null || descriptorsBreasts.isEmpty()) {
				return "";
			}
			return Util.randomItemFrom(descriptorsBreasts);
		} else {
			if(descriptorsFlat==null || descriptorsFlat.isEmpty()) {
				return "";
			}
			return Util.randomItemFrom(descriptorsFlat);
		}
	}

	public String getCrotchNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("crotch-breast", "crotch-boob", "crotch-boob", "crotch-boob", "crotch-tit");
	}
	
	public String getCrotchNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("crotch-breasts", "crotch-boobs", "crotch-boobs", "crotch-boobs", "crotch-tits");
	}

	@Override
	/**
	 * <b>This should never be used - the covering of breasts is determined by the torso's covering!</b>
	 */
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		if(body!=null) {
			return body.getTorso().getBodyCoveringType(body);
		}
		return coveringType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsBodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsTransformationDescription);
	}

	public String getTransformationCrotchDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsCrotchTransformationDescription);
	}
	
	public String getBodyCrotchDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsCrotchBodyDescription);
	}
	
}
