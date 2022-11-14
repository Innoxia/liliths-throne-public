package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractLegType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private Map<LegConfiguration, FootStructure> defaultFootStructure;
	private AbstractFootType footType;
	
	private String determiner;
	
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private List<String> footDescriptorsMasculine;
	private List<String> footDescriptorsFeminine;
	
	private List<String> toeDescriptorsMasculine;
	private List<String> toeDescriptorsFeminine;
	
	private String legTransformationDescription;
	private String legBodyDescription;
	
	private List<LegConfiguration> allowedLegConfigurations;
	
	private boolean spinneret;

	private AbstractTentacleType tentacleType;
	private int tentacleCount;
	
	/**
	 * @param coveringType What covers this leg type (i.e skin/fur/feather type).
	 * @param race What race has this leg type.
	 * @param defaultFootStructure The default foot structure for this leg type.
	 * @param footType The type of foot attached to this leg type.
	 * @param determiner Will usually be "a pair of".
	 * @param name The singular name of the leg. This will usually just be "leg".
	 * @param namePlural The plural name of the leg. This will usually just be "legs".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this leg type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this leg type.
	 * @param footDescriptorsMasculine The <b>additional</b> descriptors that are applied to a masculine form of the associated foot type. (Base descriptors are in the AbstractFootType class.)
	 * @param footDescriptorsFeminine The <b>additional</b> descriptors that are applied to a feminine form of the associated foot type. (Base descriptors are in the AbstractFootType class.)
	 * @param toeDescriptorsMasculine The <b>additional</b> descriptors that are applied to a masculine form of the associated toe type. (Base descriptors are in the AbstractFootType class.)
	 * @param toeDescriptorsFeminine The <b>additional</b> descriptors that are applied to a feminine form of the associated toe type. (Base descriptors are in the AbstractFootType class.)
	 * @param legTransformationDescription A paragraph describing a character's legs transforming into this leg type. Parsing assumes that the character already has this leg type and associated skin covering.
	 * @param legBodyDescription A sentence or two to describe this leg type, as seen in the character view screen. It should follow the same format as all of the other entries in the LegType class.
	 * @param allowedLegConfigurations A list of LegConfigurations that are allowed for this LegType.
	 * @param spinneret true if this leg type has a spinneret.
	 */
	public AbstractLegType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			FootStructure defaultFootStructure,
			AbstractFootType footType,
			String determiner,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<String> footDescriptorsMasculine,
			List<String> footDescriptorsFeminine,
			List<String> toeDescriptorsMasculine,
			List<String> toeDescriptorsFeminine,
			String legTransformationDescription,
			String legBodyDescription,
			List<LegConfiguration> allowedLegConfigurations,
			boolean spinneret) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.transformationName = null; // Use default race transformation name
		
		this.defaultFootStructure = Util.newHashMapOfValues(new Value<>(LegConfiguration.BIPEDAL, defaultFootStructure));
		this.footType = footType;
		
		this.allowedLegConfigurations = allowedLegConfigurations;
		
		this.determiner = determiner;
		
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.footDescriptorsMasculine = footDescriptorsMasculine;
		this.footDescriptorsFeminine = footDescriptorsFeminine;
		
		this.toeDescriptorsMasculine = toeDescriptorsMasculine;
		this.toeDescriptorsFeminine = toeDescriptorsFeminine;
		
		this.legTransformationDescription = legTransformationDescription;
		this.legBodyDescription = legBodyDescription;
		
		this.spinneret = spinneret;
		
		this.tentacleType = TentacleType.NONE;
		this.tentacleCount = 0;
	}
	
	public AbstractLegType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.defaultFootStructure = new HashMap<>();
				FootStructure defaultStructure = FootStructure.valueOf(coreElement.getMandatoryFirstOf("defaultFootStructure").getTextContent());
				for(LegConfiguration config : LegConfiguration.values()) {
					this.defaultFootStructure.put(config, defaultStructure);
				}
				for(Element e : coreElement.getAllOf("additionalFootStructure")) {
					this.defaultFootStructure.put(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), FootStructure.valueOf(e.getTextContent()));
				}
				
				this.footType = FootType.getFootTypeFromId(coreElement.getMandatoryFirstOf("footType").getTextContent());
				this.spinneret = Boolean.valueOf(coreElement.getMandatoryFirstOf("spinneret").getTextContent());
				
				this.tentacleType = TentacleType.getTentacleTypeFromId(coreElement.getMandatoryFirstOf("tentacleType").getTextContent());
				this.tentacleCount = Integer.valueOf(coreElement.getMandatoryFirstOf("tentacleCount").getTextContent());
				
				this.allowedLegConfigurations = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("allowedLegConfigurations").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("allowedLegConfigurations").getAllOf("configuration")) {
						allowedLegConfigurations.add(LegConfiguration.valueOf(e.getTextContent()));
					}
				}
				if(allowedLegConfigurations.isEmpty()) {
					allowedLegConfigurations.add(LegConfiguration.BIPEDAL);
				}
				
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

				this.footDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("footDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("footDescriptorsMasculine").getAllOf("descriptor")) {
						footDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.footDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("footDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("footDescriptorsFeminine").getAllOf("descriptor")) {
						footDescriptorsFeminine.add(e.getTextContent());
					}
				}

				this.toeDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("toeDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("toeDescriptorsMasculine").getAllOf("descriptor")) {
						toeDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.toeDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("toeDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("toeDescriptorsFeminine").getAllOf("descriptor")) {
						toeDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.legTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.legBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractLegType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
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
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return determiner;
		}
		if(gc.getLegCount()==1) {
			return "a";
		} else if(gc.getLegCount()==2) {
			return "a pair of";
		}
		return Util.intToString(gc.getLegCount());
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		if(gc==null) {
			return true;
		}
		return gc.getLegCount()>1;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc!=null) {
			switch(gc.getLegConfiguration()) {
				case ARACHNID:
				case AVIAN:
				case BIPEDAL:
				case QUADRUPEDAL:
				case WINGED_BIPED:
					return "leg";
				case CEPHALOPOD:
					return "tentacle";
				case TAIL:
				case TAIL_LONG:
					return "tail";
			}
		}
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc!=null) {
			switch(gc.getLegConfiguration()) {
				case ARACHNID:
				case AVIAN:
				case BIPEDAL:
				case QUADRUPEDAL:
				case WINGED_BIPED:
					return "legs";
				case CEPHALOPOD:
					return "tentacles";
				case TAIL:
				case TAIL_LONG:
					return "tails";
			}
		}
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc!=null && !gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsMasculine);
		} else {
			return Util.randomItemFrom(descriptorsFeminine);
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
		return getTFTypeModifier(LegType.getLegTypes(race));
	}

	public AbstractFootType getFootType() {
		return footType;
	}

	public FootStructure getDefaultFootStructure(LegConfiguration legConfiguration) {
		if(!defaultFootStructure.containsKey(legConfiguration)) {
			return defaultFootStructure.get(LegConfiguration.BIPEDAL);
		}
		return defaultFootStructure.get(legConfiguration);
	}
	
	
	public String getFootNameSingular(GameCharacter gc) {
		return this.getFootType().getFootName();
	}
	
	public String getFootNamePlural(GameCharacter gc) {
		return this.getFootType().getFootNamePlural();
	}

	public String getFootDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getFootDescriptorsFeminine(), footDescriptorsFeminine));
		} else {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getFootDescriptorsMasculine(), footDescriptorsMasculine));
		}
	}
	

	public String getToeNameSingular(GameCharacter gc) {
		return this.getFootType().getToeSingularName();
	}
	
	public String getToeNamePlural(GameCharacter gc) {
		return this.getFootType().getToePluralName();
	}

	public String getToeDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getToeDescriptorsFeminine(), toeDescriptorsFeminine));
		} else {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getToeDescriptorsMasculine(), toeDescriptorsMasculine));
		}
	}
	
	
//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, legBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, legTransformationDescription);
	}


	/**
	 * Applies the related leg configuration transformation for this leg type, and returns a description of the changes.<br/><br/>
	 * 
	 * <b>When overriding, consider:</b><br/>
	 * Ass.class (type)<br/>
	 * BreastCrotch.class (type)<br/>
	 * Tail.class (type)<br/>
	 * Tentacle.class (type)<br/>
	 * Penis.class (type, size, cloaca)<br/>
	 * Vagina.class (type, capacity, cloaca)<br/>
	 * 
	 * @param legConfiguration The leg configuration to be applied.
	 * @param character The character which is being transformed.
	 * @param applyEffects Whether the transformative effects should be applied. Pass in false to get the transformation description without applying any of the actual effects.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 * 
	 * @return A description of the transformation.
	 */
	public String applyLegConfigurationTransformation(GameCharacter character, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		StringBuilder feralStringBuilder = new StringBuilder();

		if(character.isFeral()) {
			return "<p style='text-align:center;'>"
						+ UtilText.parse(character, "[style.italicsDisabled(Nothing happens, for as [npc.sheIsFull] a feral [npc.race], [npc.name] cannot have [npc.her] leg configuration transformed!)]")
					+ "</p>";
		}
		
		if(character.getLegConfiguration()==legConfiguration && character.getLegType().equals(this)) {
			return "<p>"
						+ UtilText.parse(character, "[style.italicsDisabled(Nothing happens, as [npc.name] already [npc.has] [npc.a_legRace]'s lower body in the '"+legConfiguration.getName()+"' configuration...)]")
					+ "</p>";
		}
		
		if(!character.getLegType().isLegConfigurationAvailable(legConfiguration)) {
			return "<p>"
					+ UtilText.parse(character, "[style.italicsDisabled(Nothing happens, as [npc.namePos] current lower body cannot be transformed into the '"+legConfiguration.getName()+"' configuration...)]")
				+ "</p>";
		}
		
		feralStringBuilder.append(handleLegConfigurationChanges(character.getBody(), legConfiguration, applyEffects, applyFullEffects));

		feralStringBuilder.append("<p style='text-align:center;'>");
			if(character.hasWings()) {
				if(legConfiguration.isWingsOnLegConfiguration()) {
					feralStringBuilder.append("[style.italicsFeral([npc.Her] [npc.wingSize], [npc.wings+] are now located on the sides of [npc.her] "+legConfiguration.getName()+" body!)]");
				} else {
					feralStringBuilder.append("[style.italicsFeral([npc.Her] [npc.wingSize], [npc.wings+] are now located on the back of [npc.her] upper body.)]");
				}
			} else {
				if(legConfiguration.isWingsOnLegConfiguration()) {
					feralStringBuilder.append("[style.italicsFeral(If [npc.she] [npc.verb(grow)] any wings, they will be located on the sides of [npc.her] "+legConfiguration.getName()+" body!)]");
				} else {
					feralStringBuilder.append("[style.italicsFeral(If [npc.she] [npc.verb(grow)] any wings, they will be located on the back of [npc.her] upper body.)]");
				}
			}
			if(!legConfiguration.isAbleToGrowTail()) {
				feralStringBuilder.append("<br/>");
				feralStringBuilder.append("[style.italicsFeral(This leg configuration will prevent [npc.name] from growing a tail!)]");
			}
		feralStringBuilder.append("</p>");
		
		if(applyEffects) {
			character.getBody().getLeg().setLegConfigurationForced(this, legConfiguration);
		}
		
		character.calculateStatusEffects(0);
		
		feralStringBuilder.append("<p>"
									+ character.postTransformationCalculation()
								+ "</p>");
		
		return UtilText.parse(character, feralStringBuilder.toString());
	}
	
	/**
	 * For use in modifying bodies without an attached character. Outside of the Subspecies class, you should probably always be calling the version of this method that takes in a GameCharacter.
	 * 
	 * @param body The body to be modified.
	 * @param legConfiguration The LegConfiguration to be applied.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 */
	public void applyLegConfigurationTransformation(Body body, LegConfiguration legConfiguration, boolean applyFullEffects) {
		handleLegConfigurationChanges(body, legConfiguration, true, applyFullEffects);
		body.getLeg().setLegConfigurationForced(this, legConfiguration);
	}

	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 */
	private String handleLegConfigurationChanges(Body body, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		
		String feralRaceName = this.getRace().getFeralName(legConfiguration, false);
		String feralRaceNameDeterminer = UtilText.generateSingularDeterminer(feralRaceName);
		StringBuilder feralStringBuilder = new StringBuilder();
		String feralRaceNameWithDeterminer = feralRaceNameDeterminer+" "+feralRaceName;
		String raceColorString = this.getRace().getColour().toWebHexString();
		boolean feral = true;
		
		switch(legConfiguration) {
			case BIPEDAL:
				feral = false;
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, body.getLeg().getLegConfiguration(), legConfiguration.isLargeGenitals(), applyFullEffects); // revert feral parts based on current configuration
					// Changing back to bipedal reverts crotch-boobs based on preferences:
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					if(body.getRaceStage()!=RaceStage.GREATER || Main.getProperties().getUddersLevel()<2 || !body.getGender().isFeminine()) {
						body.setBreastCrotch(
								new BreastCrotch(
									BreastType.NONE,
									Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
									startingBodyType.getBreastCrotchSize(),
									startingBodyType.getBreastCrotchLactationRate(),
									startingBodyType.getBreastCrotchCount(),
									startingBodyType.getBreastCrotchNippleSize(),
									startingBodyType.getBreastCrotchNippleShape(),
									startingBodyType.getBreastCrotchAreolaeSize(),
									startingBodyType.getBreastCrotchAreolaeShape(),
									startingBodyType.getNippleCountPerBreastCrotch(),
									startingBodyType.getBreastCrotchCapacity(),
									startingBodyType.getBreastCrotchDepth(),
									startingBodyType.getBreastCrotchElasticity(),
									startingBodyType.getBreastCrotchPlasticity(), 
									true));
					}
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms back into a regular, bipedal configuration, with [npc.her] genitals shifting back to their normal position between [npc.her] [npc.legs].<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(bipedal)] <b style='color:"+raceColorString+";'>"+this.getTransformName()+" legs</b>, which are covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case ARACHNID:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					if(!legConfiguration.getAvailableGenitalConfigurations().contains(body.getGenitalArrangement())) {
						body.setGenitalArrangement(legConfiguration.getAvailableGenitalConfigurations().get(0));
					}
				}
				feralStringBuilder.append(
						"<p>"
							+ "Without warning, [npc.name] suddenly [npc.verb(lose)] all of the strength in [npc.her] [npc.legs], and [npc.she] [npc.verb(collapse)] to the ground with a startled cry."
							+ " Before [npc.sheIs] able to react to this alarming development, [npc.her] lower body starts to rapidly transform..."
						+ "</p>"
						+ "<p>"
							+ "Looking down, [npc.name] [npc.verb(watch)] in disbelief as [npc.her] [npc.legs] split and transform into eight long, segmented legs."
							+ " The changes don't stop there, however, as [npc.her] lower body continues to rapidly morph into that of a huge, eight-legged "+feralRaceName+"."
							+ " A horny [npc.moan] bursts out of [npc.her] mouth as [npc.her] genitals shift to be on the underside of [npc.her] massive arachnid body,"
									+ " while [npc.her] anus "+(body.getLeg().getType().hasSpinneret()?"and spinneret are":"is")+" positioned near the tip of [npc.her] abdomen."
							+ " [style.italicsSex(As [npc.her] genitals are only visible from below, [npc.she] [npc.do]n't feel embarrassed to have no clothing covering [npc.her] arachnid body.)]<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(arachnid body)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case AVIAN:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					if(!legConfiguration.getAvailableGenitalConfigurations().contains(body.getGenitalArrangement())) {
						body.setGenitalArrangement(legConfiguration.getAvailableGenitalConfigurations().get(0));
					}
				}
				feralStringBuilder.append(
						"<p>"
							+ "Without warning, [npc.name] suddenly [npc.verb(lose)] all of the strength in [npc.her] [npc.legs], and [npc.she] [npc.verb(collapse)] to the ground with a startled cry."
							+ " Before [npc.sheIs] able to react to this alarming development, [npc.her] lower body starts to rapidly transform..."
						+ "</p>"
						+ "<p>"
							+ "Looking down, [npc.name] [npc.verb(watch)] in disbelief as the entire of [npc.her] lower body rapidly morphs into that of a huge "+feralRaceName+"."
							+ " A horny [npc.moan] bursts out of [npc.her] mouth as [npc.her] genitals and asshole shift to be located within a cloaca that's found on the rear-facing underside of [npc.her] massive avian body."
							+ " [style.italicsSex(As [npc.her] genitals are only visible from below, [npc.she] [npc.do]n't feel embarrassed to have no clothing covering [npc.her] avian body.)]<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(avian body)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case CEPHALOPOD:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>"
							+ "Without warning, [npc.name] suddenly [npc.verb(lose)] all of the strength in [npc.her] [npc.legs], and [npc.she] [npc.verb(collapse)] to the ground with a startled cry."
							+ " Before [npc.sheIs] able to react to this alarming development, [npc.her] lower body starts to rapidly transform..."
						+ "</p>"
						+ "<p>"
							+ "Looking down, [npc.name] [npc.verb(watch)] in disbelief as [npc.her] [npc.legs] split and transform into eight long, strong tentacles."
							+ " The changes don't stop there, however, as [npc.her] lower body continues to rapidly morph into that of a huge, eight-legged "+feralRaceName+"."
							+ " A horny [npc.moan] bursts out of [npc.her] mouth as [npc.her] genitals and asshole shift to sit within a cloaca located in the central underside of [npc.her] new tentacles."
							+ " [style.italicsSex(As [npc.her] cloaca is only visible from below, [npc.she] [npc.do]n't feel embarrassed to have no clothing covering [npc.her] tentacled body.)]<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(tentacled body)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case TAIL:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms into that of "+feralRaceNameWithDeterminer+"'s tail, with [npc.her] genitals and asshole shifting to sit within a front-facing cloaca,"
									+ " located in the equivalent place to where [npc.her] regular intercrural genitalia would be.<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(tail)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case TAIL_LONG:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms into that of a huge "+feralRaceName+"'s tail, with [npc.her] genitals and asshole shifting to sit within a front-facing cloaca,"
									+ " located in the equivalent place to where [npc.her] regular intercrural genitalia would be.<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(huge tail)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case WINGED_BIPED:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}

				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms back into a bipedal configuration, with [npc.her] genitals shifting back to their normal position between [npc.her] [npc.legs]."
							+ " Letting out a surprised cry, [npc.name] [npc.verb(bend)] down and [npc.verb(stoop)] over as [npc.her] spine rapidly reshapes itself."
							+ " The transformation is over within a matter of moments, leaving [npc.name] to naturally use [npc.her] [npc.arms] in place of forelegs so as to support [npc.her] newly-shaped body.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(bipedal)] <b style='color:"+raceColorString+";'>"+this.getTransformName()+" legs</b>, which are covered in [npc.legFullDescription],"
									+ " and [style.boldTfGeneric([npc.verb(use)] [npc.her] [npc.arms] as forelegs)]."
						+ "</p>");
				break;
			case QUADRUPEDAL:
				feralStringBuilder.append(
						"<p>"
							+ "An extremely unsettling, tingling feeling starts to spread down into [npc.namePos] [npc.legs+],");
				
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(body.getLegType().getRace().getRacialBody().getGenitalArrangement());
				}
				
				feralStringBuilder.append(
						" and [npc.she] [npc.verb(let)] out an alarmed cry as [npc.she] [npc.verb(lose)] [npc.her] balance and [npc.verb(tumble)] to the floor."
							+ " Right before [npc.her] [npc.eyes], [npc.her] lower body shifts and transforms, with [npc.her] limbs pulling back into an intermediary mass of [npc.bodyMaterial],"
								+ " before almost immediately pushing back out in a quadrupedal configuration."
						+ "</p>"
						+ "<p>"
							+ "As this is happening, [npc.her] [npc.ass+] rapidly expands and pushes out, quickly forming the body to which [npc.her] four new legs are anchored."
							+ " Although it could be described as nothing less than extreme, the transformation is surprisingly pain-free, and the gasps and [npc.moans] that burst out from [npc.namePos] mouth are purely from shock and exertion."
						+ "</p>"
						+ "<p>"
							+ "After just a few moments more, [npc.namePos] lower body has completely transformed into that of "+feralRaceNameWithDeterminer
								+", with [npc.her] genitals and asshole shifting to sit in the same place as that of a feral "+feralRaceName+".<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(lower body)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription(true)]."
						+ "</p>");
				break;
		}

		// Increase or decrease height based on configuration:
		if(applyEffects) {
			if(!body.getLegConfiguration().isTall() && legConfiguration.isTall()) {
				int newHeight = (int) (body.getHeightValue()*1.33f);
				if(body.isShortStature()) {
					newHeight = Math.min(Height.getShortStatureCutOff()-1, newHeight);
				}
				body.setHeight(newHeight);
				String colouredHeightValue = "<span style='color:"+body.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
				feralStringBuilder.append("<p>The size of [npc.namePos] new lower body has resulted in [npc.herHim] getting taller, so now when standing at full height [npc.she] [npc.verb(measure)] "+colouredHeightValue+".</p>");
				
			} else if(body.getLegConfiguration().isTall() && !legConfiguration.isTall()) {
				int newHeight = (int) (body.getHeightValue()/1.33f);
				if(!body.isShortStature()) {
					newHeight = Math.max(Height.getShortStatureCutOff(), newHeight);
				}
				body.setHeight(newHeight);
				String colouredHeightValue = "<span style='color:"+body.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
				feralStringBuilder.append("<p>The reduced size of [npc.namePos] new lower body has resulted in [npc.herHim] getting shorter, so now when standing at full height [npc.she] [npc.verb(measure)] "+colouredHeightValue+".</p>");
			}
		}
		
		
		if(legConfiguration.isTailLostOnInitialTF()) {
			if(body.getTail().getType()!=TailType.NONE) {
				body.getTail().setType(null, TailType.NONE);
				// Tail description is handled below
//				feralStringBuilder.append(
//						"<p style='text-align:center;'>"
//								+ "[style.italicsFeral(As part of the transformation, [npc.name] [nc.has] lost [npc.her] tail!)]"
//						+ "</p>");
			}
		}
		
		if(Main.getProperties().getUddersLevel()==0 && !body.isFeral()) {
			AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
			body.setBreastCrotch(
					new BreastCrotch(
						BreastType.NONE,
						Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
						startingBodyType.getBreastCrotchSize(),
						startingBodyType.getBreastCrotchLactationRate(),
						startingBodyType.getBreastCrotchCount(),
						startingBodyType.getBreastCrotchNippleSize(),
						startingBodyType.getBreastCrotchNippleShape(),
						startingBodyType.getBreastCrotchAreolaeSize(),
						startingBodyType.getBreastCrotchAreolaeShape(),
						startingBodyType.getNippleCountPerBreastCrotch(),
						startingBodyType.getBreastCrotchCapacity(),
						startingBodyType.getBreastCrotchDepth(),
						startingBodyType.getBreastCrotchElasticity(),
						startingBodyType.getBreastCrotchPlasticity(), 
						true));
		}
		
		feralStringBuilder.append("<p><i>"
				+ "[style.boldTfGeneric(Every part)] of [npc.her] lower body has transformed into that of a "+(feral?"feral "+feralRaceName:"regular "+this.getRace().getName(false))+", meaning that [npc.she] now [npc.has]");
		
		List<String> partsList = new ArrayList<>();
		// Tail:
		if(body.getTail().getType()==TailType.NONE) {
			partsList.add(" [style.boldTfGeneric(no tail)]");
		} else {
			partsList.add(" [style.boldTfGeneric("
					+(body.getTail().getTailCount()==1
						?(body.getTail().getType()==TailType.HARPY?"a plume of ":UtilText.generateSingularDeterminer(body.getTail().getName(null))+" ")
						:Util.intToString(body.getTail().getTailCount())+" "+(body.getTail().getType()==TailType.HARPY?" plumes of ":""))
					+body.getTail().getName(null)+")]");
		}
		// Ass:
		partsList.add(feralRaceNameWithDeterminer+"'s ass");
		// Crotch boobs:
		if((Main.getProperties().getUddersLevel()==1 && legConfiguration!=LegConfiguration.BIPEDAL)
				|| (Main.getProperties().getUddersLevel()==2 && body.getRaceStage()==RaceStage.GREATER)
				|| body.isFeral()) {
			if(body.getBreastCrotch().getType()!=BreastType.NONE && !legConfiguration.isBipedalPositionedCrotchBoobs()) {
				partsList.add("animal-like "+body.getBreastCrotch().getName(null));
			}
		}
		feralStringBuilder.append(Util.stringsToStringList(partsList, false)+".");
		
		Penis penis = body.getPenis();
		if(penis.getType()!=PenisType.NONE) {
			if(feral) {
				feralStringBuilder.append(" [npc.Her] cock has similarly transformed into that of a feral "+feralRaceName+"'s, and not only produces musky, animal-like cum, but is also an impressive "+Units.size(penis.getRawLengthValue())+" long.");
			} else {
				feralStringBuilder.append(" [npc.Her] cock has similarly transformed into that of a regular "+this.getRace().getName(false)+", and is "+Units.size(penis.getRawLengthValue())+" long.");
			}
		}
		
		Vagina vagina = body.getVagina();
		if(vagina.getType()!=VaginaType.NONE) {
			if(feral) {
				feralStringBuilder.append(" [npc.Her] now-musky pussy is now also identical to that of "+feralRaceNameWithDeterminer
						+", and has shifted into being "+vagina.getOrificeVagina().getCapacity().getDescriptor()+" in order to accommodate a matching feral cock.");
			} else {
				feralStringBuilder.append(" [npc.Her] now-"+vagina.getOrificeVagina().getCapacity().getDescriptor()+" pussy has similarly transformed into that of a regular.");
			}
		}
		feralStringBuilder.append("</i></p>");
		
		if(feral) {
			feralStringBuilder.append("<p><i>Feeling as though it's only natural, [npc.she] [style.colourGood(no longer [npc.verb(get)] embarrassed)] about having [npc.her] [style.italicsFeral(animalistic genitals"
					+(legConfiguration.isBipedalPositionedCrotchBoobs()?(body.getBreastCrotch().getShape()==BreastShape.UDDERS?" or udders":" or crotch-boobs"):"")
					+ ")] on display!</i></p>");
		}

		if(body.getLeg().getType().hasSpinneret()) {
			feralStringBuilder.append("<p>[npc.Her] abdomen has [style.italicsFeral(a spinneret)], which, along with creating webbing, [style.italicsSex(can be penetrated and used as a sexual orifice)]!</p>");
		} else if(body.getTail().getType().hasSpinneret()) {
			feralStringBuilder.append("<p>[npc.Her] tail has [style.italicsFeral(a spinneret)], which, along with creating webbing, [style.italicsSex(can be penetrated and used as a sexual orifice)]!</p>");
		}
		
		int landSpeed = body.getLeg().getType().getLandSpeedModifier() + legConfiguration.getLandSpeedModifier();
		if(landSpeed>0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.Her] new lower body is not as well-adapted to movement on land as a usual biped,"
												+ " and as a result, [style.colourTerrible([npc.she] [npc.verb(move)] slower than usual)] [style.colourEarth(while on land)]!"
											+ "<br/>"
											+ "[style.colourTerrible(+"+landSpeed+"%)] travel time while on land!"
										+ "</i></p>");
		} else if(landSpeed<0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.Her] new lower body is capable of speeds greater than that attainable by a usual biped,"
												+ " and as a result, [style.colourExcellent([npc.she] [npc.verb(move)] faster than usual)] [style.colourEarth(while on land)]!"
											+ "<br/>"
											+ "[style.colourExcellent("+landSpeed+"%)] travel time while on land!"
										+ "</i></p>");
		}

		int waterSpeed = body.getLeg().getType().getWaterSpeedModifier() + legConfiguration.getWaterSpeedModifier();
		if(waterSpeed>0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.SheIsFull] now a lot less capable at moving in water than a usual biped,"
												+ " and as a result, [style.colourTerrible([npc.she] [npc.verb(move)] slower than usual)] [style.colourWater(while in water)]!"
											+ "<br/>"
											+ "[style.colourTerrible(+"+waterSpeed+"%)] travel time while in water!"
										+ "</i></p>");
		} else if(waterSpeed<0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "[npc.SheIsFull] now in possession of a body that's well-suited to moving in water,"
												+ " and as a result, [style.colourExcellent([npc.she] [npc.verb(move)] faster than usual)] [style.colourWater(while in water)]!"
											+ "<br/>"
											+ "[style.colourExcellent("+waterSpeed+"%)] travel time while in water!"
										+ "</i></p>");
		}
		
		if(legConfiguration==LegConfiguration.TAIL) {
			feralStringBuilder.append("<p style='text-align:center;'><i>"
											+ "While [style.colourTan(on land)], [npc.namePos] lower body will now [style.colourTfGeneric(automatically transform into a bipedal configuration)]!"
											+ " While in this form, [npc.she] will suffer some negative side-effects."
											+ "<br/>"
											+ "Conversely, while [style.colourBlueLight(in water)], [npc.namePos] lower body will [style.colourTfGeneric(automatically transform back into its true tail-like form)]!"
											+ " While in this form, [npc.she] will benefit from some positive side-effects."
										+ "</i></p>");
		}
		
		return feralStringBuilder.toString();
	}
	
	// Setting parts is applied directly through body to circumvent transformation blocks
	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 **/
	private void applyExtraLegConfigurationTransformations(Body body, LegConfiguration legConfiguration, boolean largeGenitals, boolean applyFullEffects) {
		AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
		
		boolean demon = body.getRace()==Race.DEMON;
		
		if(legConfiguration.getFeralParts().contains(Ass.class)) { // Ass (includes Anus):
			if(!applyFullEffects) {
				body.getAss().setType(null, (demon
						?AssType.DEMON_COMMON
						:startingBodyType.getAssType()));
			} else {
				boolean virgin = body.getAss().getAnus().getOrificeAnus().isVirgin();
				body.setAss(
						new Ass(
							(demon
								?AssType.DEMON_COMMON
								:startingBodyType.getAssType()),
							(body.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
							(body.isFeminine() ? startingBodyType.getFemaleHipSize() : startingBodyType.getMaleHipSize()),
							startingBodyType.getAnusWetness(),
							(float) (startingBodyType.getAnusCapacity()*(largeGenitals?2.5:1)),
							startingBodyType.getAnusDepth()+(largeGenitals?2:0),
							startingBodyType.getAnusElasticity(),
							startingBodyType.getAnusPlasticity(),
							true));
				body.getAss().getAnus().getOrificeAnus().setVirgin(virgin);
			}
		}
		if(legConfiguration.getFeralParts().contains(BreastCrotch.class)) { // Crotch-boobs:
			AbstractBreastType crotchBoobType = BreastType.NONE;
			if(body.isFeminine()) {
				if(demon) {
					crotchBoobType = BreastType.DEMON_COMMON;
				} else {
					crotchBoobType = startingBodyType.getBreastCrotchType();
				}
			}
			
			if(!applyFullEffects) {
				body.getBreastCrotch().setType(null, crotchBoobType);
				
			} else {
				body.setBreastCrotch(
						new BreastCrotch(
							crotchBoobType,
							Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
							startingBodyType.getBreastCrotchSize(),
							startingBodyType.getBreastCrotchLactationRate(),
							startingBodyType.getBreastCrotchCount(),
							startingBodyType.getBreastCrotchNippleSize(),
							startingBodyType.getBreastCrotchNippleShape(),
							startingBodyType.getBreastCrotchAreolaeSize(),
							startingBodyType.getBreastCrotchAreolaeShape(),
							startingBodyType.getNippleCountPerBreastCrotch(),
							startingBodyType.getBreastCrotchCapacity(),
							startingBodyType.getBreastCrotchDepth(),
							startingBodyType.getBreastCrotchElasticity(),
							startingBodyType.getBreastCrotchPlasticity(), 
							true));
			}
		}
		if(legConfiguration.getFeralParts().contains(Tail.class)) { // Tail:
			if(body.getLeg().getType().getRace()==Race.DEMON) {
				body.setTail(new Tail(TailType.DEMON_HORSE));
			} else {
				if(body.getTail().getType().getRace()!=startingBodyType.getTailType().get(0).getRace()) {
					body.setTail(new Tail(startingBodyType.getTailType().get(0)));
				}
			}
		}
		if(legConfiguration.getFeralParts().contains(Tentacle.class)) { // Tentacle:
			body.setTentacle(new Tentacle(startingBodyType.getTentacleType()));
		}
		if(legConfiguration.getFeralParts().contains(Penis.class)) { // Penis (includes Testicle):
			if(!applyFullEffects) {
				if(body.getPenis().getType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.DILDO) {
					body.getPenis().setType(null,
								(demon
									?PenisType.DEMON_COMMON
									:startingBodyType.getPenisType()));
				}
				
			} else {
				boolean virgin = body.getPenis().isVirgin();
				body.setPenis(body.getPenis().getType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.DILDO
						? new Penis(
							(demon
								?PenisType.DEMON_COMMON
								:startingBodyType.getPenisType()),
							(int) (startingBodyType.getPenisSize()*(largeGenitals?2.5:1)),
							true,
							startingBodyType.getPenisGirth()+(largeGenitals?1:0),
							startingBodyType.getTesticleSize()+(largeGenitals?1:0),
							startingBodyType.getCumProduction()*(largeGenitals?10:1),
							startingBodyType.getTesticleQuantity())
						: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2));
//				body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getPenis().setVirgin(virgin);
			}
		}
		if(legConfiguration.getFeralParts().contains(Vagina.class)) { // Vagina (includes Clitoris):
			if(!applyFullEffects) {
				if(body.getVagina().getType()!=VaginaType.NONE) {
					body.getVagina().setType(null,
								(demon
									?VaginaType.DEMON_COMMON
									:startingBodyType.getVaginaType()));
				}
				
			} else {
				boolean virgin = body.getVagina().getType()!=VaginaType.NONE?body.getVagina().getOrificeVagina().isVirgin():true;
				body.setVagina(
						body.getVagina().getType()!=VaginaType.NONE
							? new Vagina(
									(demon
										?VaginaType.DEMON_COMMON
										:startingBodyType.getVaginaType()),
									LabiaSize.getRandomLabiaSize().getValue(),
									startingBodyType.getClitSize(),
									startingBodyType.getClitGirth(),
									startingBodyType.getVaginaWetness(),
									(float) (startingBodyType.getVaginaCapacity()*(largeGenitals?2.5:1)),
									startingBodyType.getVaginaDepth()+(largeGenitals?2:0),
									startingBodyType.getVaginaElasticity(),
									startingBodyType.getVaginaPlasticity(),
									true)
							: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 0, 2, 3, 3, true));
//				body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getVagina().getOrificeVagina().setVirgin(virgin);
			}
		}
 	}
	
	public List<LegConfiguration> getAllowedLegConfigurations() {
		return allowedLegConfigurations;
	}

	/**
	 * @param legConfiguration The configuration to check transformation availability of.
	 * @return True if this configuration is allowed for this LegType.
	 */
	public boolean isLegConfigurationAvailable(LegConfiguration legConfiguration) {
		return allowedLegConfigurations.contains(legConfiguration);
	}
	
	public boolean hasSpinneret() {
		return spinneret;
	}

	/**
	 * @return Usually TentacleType.NONE. If it returns an actual TentacleType, then that means this LegType has tentacles in place of legs.
	 */
	public AbstractTentacleType getTentacleType() {
		return tentacleType;
	}
	
	public boolean isLegsReplacedByTentacles() {
		return getTentacleType()!=TentacleType.NONE;
	}
	
	public int getTentacleCount() {
		return tentacleCount;
	}
	
	/**
	 * @return By default, LegTypes return a modification of 0, but if a LegType requires a modifier, then this can be overridden and its effects will be handled alongside LegConfiguration's getLandSpeedModifier().
	 */
	public int getLandSpeedModifier() {
		return 0;
	}

	/**
	 * @return By default, LegTypes return a modification of 0, but if a LegType requires a modifier, then this can be overridden and its effects will be handled alongside LegConfiguration's getWaterSpeedModifier().
	 */
	public int getWaterSpeedModifier() {
		return 0;
	}
	
	@Override
	public BodyPartClothingBlock getBodyPartClothingBlock() {
		if(this.getFootType()==FootType.HOOFS) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.FOOT),
					this.getRace(),
					"Due to the shape of [npc.namePos] hoofs, only hoof-compatible clothing can be worn in this slot.",
					Util.newArrayListOfValues(ItemTag.FITS_HOOFS, ItemTag.FITS_HOOFS_EXCLUSIVE));
		}
		if(this.getFootType()==FootType.TALONS) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.FOOT),
					this.getRace(),
					"Due to the shape of [npc.namePos] talons, only talon-compatible clothing can be worn in this slot.",
					Util.newArrayListOfValues(ItemTag.FITS_TALONS, ItemTag.FITS_TALONS_EXCLUSIVE));
		}
		return null;
	}
}
