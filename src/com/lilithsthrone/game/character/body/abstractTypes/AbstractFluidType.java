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
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.2
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractFluidType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private String transformationName;
	
	private FluidTypeBase baseFluidType;
	private FluidFlavour flavour;
	private AbstractRace race;
	
	private List<String> namesMasculine;
	private List<String> namesFeminine;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	List<FluidModifier> defaultFluidModifiers;
	
	/**
	 * @param baseFluidType The base type of this fluid (milk, cum, or girlcum).
	 * @param flavour The default flavour for this fluid.
	 * @param race What race has this fluid type.
	 * @param names A list of singular names for this fluid type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic fluid name to the end of it before returning.
	 * @param namesPlural A list of plural names for this fluid type.
	 *  Pass in null to use generic names.
	 *  Empty values also use generic names.
	 *  Names ending in '-' are handled in a special manner by appending a generic fluid name to the end of it before returning.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this fluid type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this fluid type.
	 * @param defaultFluidModifiers Which modifiers this fluid naturally spawns with.
	 */
	public AbstractFluidType(
			FluidTypeBase baseFluidType,
			FluidFlavour flavour,
			AbstractRace race,
			List<String> namesMasculine,
			List<String> namesFeminine,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<FluidModifier> defaultFluidModifiers) {
		
		this.baseFluidType = baseFluidType;
		this.flavour = flavour;
		this.race = race;
		
		this.transformationName = null; // Use default race transformation name
		
		this.namesMasculine = namesMasculine;
		this.namesFeminine = namesFeminine;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.defaultFluidModifiers = defaultFluidModifiers;
	}
	
	public AbstractFluidType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.baseFluidType = FluidTypeBase.valueOf(coreElement.getMandatoryFirstOf("baseFluidType").getTextContent());
				this.flavour = FluidFlavour.valueOf(coreElement.getMandatoryFirstOf("flavour").getTextContent());
				
				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.namesMasculine = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesMasculine").getAllOf("name")) {
					namesMasculine.add(e.getTextContent());
				}
				
				this.namesFeminine = new ArrayList<>();
				for(Element e : coreElement.getMandatoryFirstOf("namesFeminine").getAllOf("name")) {
					namesFeminine.add(e.getTextContent());
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
				
				this.defaultFluidModifiers = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("defaultFluidModifiers").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("defaultFluidModifiers").getAllOf("modifier")) {
						defaultFluidModifiers.add(FluidModifier.valueOf(e.getTextContent()));
					}
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractFluidType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
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
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String toString() {
		System.err.println("Warning! AbstractFluidType is calling toString()");
		return super.toString();
	}
	
	public String getId() {
		return FluidType.getIdFromFluidType(this);
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "some";
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		String name;
		
		if(gc==null || gc.isFeminine()) {
			if(namesFeminine==null || namesFeminine.isEmpty()) {
				return Util.randomItemFrom(baseFluidType.getNames());
			}
			name = Util.randomItemFrom(namesFeminine);
			
		} else {
			if(namesMasculine==null || namesMasculine.isEmpty()) {
				return Util.randomItemFrom(baseFluidType.getNames());
			}
			name = Util.randomItemFrom(namesMasculine);
		}
		
		if(name==null || name.isEmpty()) {
			return Util.randomItemFrom(baseFluidType.getNames());
		}
		if(name.endsWith("-")) {
			if(Math.random()<0.25f) { // 25% chance to return this '-' name.
				return name + Util.randomItemFrom(baseFluidType.getNames());
			} else {
				return Util.randomItemFrom(baseFluidType.getNames());
			}
		}
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return getNameSingular(gc);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc==null || gc.isFeminine()) {
			if(descriptorsFeminine==null || descriptorsFeminine.isEmpty()) {
				return "";
			}
			return Util.randomItemFrom(descriptorsFeminine);
			
		} else {
			if(descriptorsMasculine==null || descriptorsMasculine.isEmpty()) {
				return "";
			}
			return Util.randomItemFrom(descriptorsMasculine);
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return baseFluidType.getCoveringType();
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}
	
	public FluidTypeBase getBaseType() {
		return baseFluidType;
	}
	
	public FluidFlavour getFlavour() {
		return flavour;
	}
	
	public List<FluidModifier> getDefaultFluidModifiers() {
		return defaultFluidModifiers;
	}
	
	public float getValueModifier() {
		return 1f;
	}
}
