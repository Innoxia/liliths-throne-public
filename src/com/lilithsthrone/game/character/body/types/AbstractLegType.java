package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class AbstractLegType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;

	private FootStructure defaultFootStructure;
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
	
	/**
	 * @param skinType What covers this leg type (i.e skin/fur/feather type).
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
	 */
	public AbstractLegType(BodyCoveringType skinType,
			Race race,
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
			List<LegConfiguration> allowedLegConfigurations) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.defaultFootStructure = defaultFootStructure;
		this.footType = footType;
		
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
		
		this.allowedLegConfigurations = allowedLegConfigurations;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return determiner;
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public AbstractFootType getFootType() {
		return footType;
	}

	public FootStructure getDefaultFootStructure() {
		return defaultFootStructure;
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
	 * @param configuration The leg configuration to be applied.
	 * @param character The character which is being transformed.
	 * @param applyEffects Whether the transformative effects should be applied. Pass in false to get the transformation description without applying any of the actual effects.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 * 
	 * @return A description of the transformation.
	 */
	public String applyLegConfigurationTransformation(GameCharacter character, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		StringBuilder bestialStringBuilder = new StringBuilder();
		
		if(character.getLegConfiguration()==legConfiguration && character.getLegType().equals(this)) {
			return "<p>"
						+ "[style.italicsDisabled(Nothing happens, as [npc.name] already [npc.has] [npc.a_legRace]'s lower body in the '"+legConfiguration.getName()+"' configuration...)]"
					+ "</p>";
		}
		
		bestialStringBuilder.append(handleLegConfigurationChanges(character.getBody(), legConfiguration, applyEffects, applyFullEffects));

		bestialStringBuilder.append("<p>");
		if(character.hasWings()) {
			if(legConfiguration.isWingsOnLegConfiguration()) {
				bestialStringBuilder.append("[style.italicsBestial([npc.Her] [npc.wingSize], [npc.wings+] are now located on the sides of [npc.her] "+legConfiguration.getName()+" body!)]");
			} else {
				bestialStringBuilder.append("[style.italicsBestial([npc.Her] [npc.wingSize], [npc.wings+] are now located on the back of [npc.her] upper body.)]");
			}
		} else {
			if(legConfiguration.isWingsOnLegConfiguration()) {
				bestialStringBuilder.append("[style.italicsBestial(If [npc.she] [npc.verb(grow)] any wings, they will be located on the sides of [npc.her] "+legConfiguration.getName()+" body!)]");
			} else {
				bestialStringBuilder.append("[style.italicsBestial(If [npc.she] [npc.verb(grow)] any wings, they will be located on the back of [npc.her] upper body.)]");
			}
		}
		bestialStringBuilder.append("</p>");

		if(applyEffects) {
			character.getBody().getLeg().setLegConfigurationForced(this, legConfiguration);
		}
		
		bestialStringBuilder.append("<p>"
									+ character.postTransformationCalculation()
								+ "</p>");
		
		return UtilText.parse(character, bestialStringBuilder.toString());
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
		
		String bestialRaceName = this.getRace().getName(true);
		String bestialRaceNameDeterminer = UtilText.generateSingularDeterminer(bestialRaceName);
		StringBuilder bestialStringBuilder = new StringBuilder();
		String bestialRaceNameWithDeterminer = bestialRaceNameDeterminer+" "+bestialRaceName;
		String raceColorString = this.getRace().getColour().toWebHexString();
		boolean feral = true;
		
		switch(legConfiguration) {
			case BIPEDAL:
				feral = false;
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, body.getLeg().getLegConfiguration(), false, applyFullEffects); // revert feral parts based on current configuration
					// Changing back to bipedal reverts crotch-boobs based on preferences:
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					if(body.getRaceStage()!=RaceStage.GREATER || Main.getProperties().udders<2 || !body.getGender().isFeminine()) {
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
									startingBodyType.getNippleCountPerBreastCrotch(),
									startingBodyType.getBreastCrotchCapacity(),
									startingBodyType.getBreastCrotchElasticity(),
									startingBodyType.getBreastCrotchPlasticity(), 
									true));
					}
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}
				bestialStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms back into a regular, bipedal configuration, with [npc.her] genitals shifting back to their normal position between [npc.her] [npc.legs].<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(bipedal)] <b style='color:"+raceColorString+";'>"+this.getTransformName()+" legs</b>, which are covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case ARACHNID:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, true, applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				bestialStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms into that of a huge, eight-legged "+bestialRaceName+", with [npc.her] genitals and asshole shifting to sit within a cloaca on the underside of [npc.her] massive arachnid abdomen."
									+ " [style.italicsSex(As [npc.her] cloaca is only visible from below, [npc.she] [npc.do]n't feel embarrassed to have no clothing covering [npc.her] arachnid body.)]<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(arachnid body)] of <b style='color:"+raceColorString+";'>"+bestialRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case CEPHALOPOD:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, false, applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				bestialStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms into that of a huge, eight-legged "+bestialRaceName+", with [npc.her] genitals and asshole shifting to sit within a cloaca located in the central underside of [npc.her] tentacles."
									+ " [style.italicsSex(As [npc.her] cloaca is only visible from below, [npc.she] [npc.do]n't feel embarrassed to have no clothing covering [npc.her] tentacled body.)]<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(tentacled body)] of <b style='color:"+raceColorString+";'>"+bestialRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case TAIL:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, false, applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				bestialStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms into that of "+bestialRaceNameWithDeterminer+"'s tail, with [npc.her] genitals and asshole shifting to sit within a front-facing cloaca,"
									+ " located in the equivalent place as where [npc.her] regular intercrural genitalia would be.<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(tail)] of <b style='color:"+raceColorString+";'>"+bestialRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case TAIL_LONG:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, false, applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				bestialStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms into that of a huge "+bestialRaceName+"'s tail, with [npc.her] genitals and asshole shifting to sit within a front-facing cloaca,"
									+ " located in the equivalent place as where [npc.her] regular intercrural genitalia would be.<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(huge tail)] of <b style='color:"+raceColorString+";'>"+bestialRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription]."
						+ "</p>");
				break;
			case TAUR:
				bestialStringBuilder.append(
						"<p>"
							+ "An extremely unsettling, tingling feeling starts to spread down into [npc.namePos] [npc.legs+],");
				
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, true, applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.NORMAL);
				}
				
				bestialStringBuilder.append(
						" and [npc.she] [npc.verb(let)] out an alarmed cry as [npc.she] [npc.verb(lose)] [npc.her] balance and [npc.verb(tumble)] to the floor."
							+ " Right before [npc.her] [npc.eyes], [npc.her] lower body shifts and transforms, with [npc.her] limbs pulling back into an intermediary mass of [npc.bodyMaterial],"
								+ " before almost immediately pushing back out in a quadrupedal configuration."
						+ "</p>"
						+ "<p>"
							+ "As this is happening, [npc.her] [npc.ass+] rapidly expands and pushes out, quickly forming the body to which [npc.her] four new legs are anchored."
							+ " Although it could be described as nothing less than extreme, the transformation is surprisingly pain-free, and the gasps and [npc.moans] that burst out from [npc.namePos] mouth are purely from shock and exertion."
						+ "</p>"
						+ "<p>"
							+ "After just a few moments more, [npc.namePos] lower body has completely transformed into that of "+bestialRaceNameWithDeterminer
								+", with [npc.her] genitals and asshole shifting to sit in the same place as that of a feral "+bestialRaceName+".<br/>"
							+ "[npc.Name] now [npc.has] the [style.boldTfGeneric(lower body)] of <b style='color:"+raceColorString+";'>"+bestialRaceNameWithDeterminer+"</b>, which is covered in [npc.legFullDescription(true)]."
						+ "</p>");
				break;
		}

		if(Main.getProperties().udders==0) {
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
						startingBodyType.getNippleCountPerBreastCrotch(),
						startingBodyType.getBreastCrotchCapacity(),
						startingBodyType.getBreastCrotchElasticity(),
						startingBodyType.getBreastCrotchPlasticity(), 
						true));
		}
		
		bestialStringBuilder.append("<p><i>"
				+ "[style.boldTfGeneric(Every part)] of [npc.her] lower body has transformed into that of a "+(feral?"feral "+bestialRaceName:"regular "+this.getRace().getName(false))+", meaning that [npc.she] now [npc.has]");
		
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
		partsList.add(bestialRaceNameWithDeterminer+"'s ass");
		// Crotch boobs:
		if((Main.getProperties().udders==1 && legConfiguration!=LegConfiguration.BIPEDAL) || (Main.getProperties().udders==2 && body.getRaceStage()==RaceStage.GREATER)) {
			if(body.getBreastCrotch().getType()!=BreastType.NONE && !legConfiguration.isBipedalPositionedCrotchBoobs()) {
				partsList.add("animal-like "+body.getBreastCrotch().getName(null));
			}
		}
		bestialStringBuilder.append(Util.stringsToStringList(partsList, false)+".");
		
		Penis penis = body.getPenis();
		if(penis.getType()!=PenisType.NONE) {
			if(feral) {
				bestialStringBuilder.append(" [npc.Her] cock has similarly transformed into that of a feral "+bestialRaceName+"'s, and not only produces musky, animal-like cum, but is also an impressive "+Units.size(penis.getRawSizeValue())+" long.");
			} else {
				bestialStringBuilder.append(" [npc.Her] cock has similarly transformed into that of a regular "+this.getRace().getName(false)+", and is "+Units.size(penis.getRawSizeValue())+" long.");
			}
		}
		
		Vagina vagina = body.getVagina();
		if(vagina.getType()!=VaginaType.NONE) {
			if(feral) {
				bestialStringBuilder.append(" [npc.Her] now-musky pussy is now also identical to that of "+bestialRaceNameWithDeterminer
						+", and has expanded to be "+vagina.getOrificeVagina().getCapacity().getDescriptor()+" in order to accommodate a matching feral cock.");
			} else {
				bestialStringBuilder.append(" [npc.Her] now-"+vagina.getOrificeVagina().getCapacity().getDescriptor()+" pussy has similarly transformed into that of a regular.");
			}
		}
		bestialStringBuilder.append("</i></p>");
		
		if(feral) {
			bestialStringBuilder.append("<p><i>Feeling as though it's only natural, [npc.she] [style.colourGood(no longer [npc.verb(get)] embarrassed)] about having [npc.her] [style.italicsBestial(animalistic genitals"
					+(legConfiguration.isBipedalPositionedCrotchBoobs()?(body.getBreastCrotch().getShape()==BreastShape.UDDERS?" or udders":" or crotch-boobs"):"")
					+ ")] on display!</i></p>");
		}
		
		
		if(legConfiguration.getLandSpeedModifier()>0) {
			bestialStringBuilder.append("<p><i>"
										+ "[npc.Her] new lower body is not as well-adapted to movement on land as a usual biped,"
											+ " and as a result, [style.colourTerrible([npc.she] [npc.verb(move)] slower than usual)] [style.colourEarth(while on land)]!"
									+ "</i></p>");
		} else if(legConfiguration.getLandSpeedModifier()<0) {
			bestialStringBuilder.append("<p><i>"
										+ "[npc.Her] new lower body is capable of speeds greater than that attainable by a usual biped,"
											+ " and as a result, [style.colourExcellent([npc.she] [npc.verb(move)] faster than usual)] [style.colourEarth(while on land)]!"
									+ "</i></p>");
		}
		
		if(legConfiguration.getWaterSpeedModifier()>0) {
			bestialStringBuilder.append("<p><i>"
										+ "[npc.SheIsFull] now a lot less capable at moving in water than a usual biped,"
											+ " and as a result, [style.colourTerrible([npc.she] [npc.verb(move)] slower than usual)] [style.colourWater(while in water)]!"
									+ "</i></p>");
		} else if(legConfiguration.getWaterSpeedModifier()<0) {
			bestialStringBuilder.append("<p><i>"
										+ "[npc.SheIsFull] now in possession of a body that's well-suited to moving in water,"
											+ " and as a result, [style.colourExcellent([npc.she] [npc.verb(move)] faster than usual)] [style.colourWater(while in water)]!"
									+ "</i></p>");
		}
		return bestialStringBuilder.toString();
	}
	
	// Setting parts is applied directly through body to circumvent transformation blocks
	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 **/
	private void applyExtraLegConfigurationTransformations(Body body, LegConfiguration legConfiguration, boolean largeGenitals, boolean applyFullEffects) {
		AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
		
		boolean demon = body.getRace()==Race.DEMON;
		
		if(legConfiguration.getBestialParts().contains(Ass.class)) { // Ass (includes Anus):
			if(!applyFullEffects) {
				body.getAss().setType(null, (demon
						?AssType.DEMON_COMMON
						:startingBodyType.getAssType()));
			} else {
				body.setAss(
						new Ass(
							(demon
								?AssType.DEMON_COMMON
								:startingBodyType.getAssType()),
							(body.isFeminine()
								? startingBodyType.getFemaleAssSize()
								: startingBodyType.getMaleAssSize()),
							startingBodyType.getAnusWetness(),
							startingBodyType.getAnusCapacity(),
							startingBodyType.getAnusElasticity(),
							startingBodyType.getAnusPlasticity(),
							true));
			}
		}
		if(legConfiguration.getBestialParts().contains(BreastCrotch.class)) { // Crotch-boobs:
			if(!applyFullEffects) {
				body.getBreastCrotch().setType(null,
						(body.isFeminine()
							?(demon
								?BreastType.DEMON_COMMON
								:startingBodyType.getBreastType())
							:BreastType.NONE));
				
			} else {
				body.setBreastCrotch(
						new BreastCrotch(
						(body.isFeminine()
							?(demon
								?BreastType.DEMON_COMMON
								:startingBodyType.getBreastType())
							:BreastType.NONE),
						Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
						startingBodyType.getBreastCrotchSize(),
						startingBodyType.getBreastCrotchLactationRate(),
						startingBodyType.getBreastCrotchCount(),
						startingBodyType.getBreastCrotchNippleSize(),
						startingBodyType.getBreastCrotchNippleShape(),
						startingBodyType.getBreastCrotchAreolaeSize(),
						startingBodyType.getNippleCountPerBreastCrotch(),
						startingBodyType.getBreastCrotchCapacity(),
						startingBodyType.getBreastCrotchElasticity(),
						startingBodyType.getBreastCrotchPlasticity(), 
						true));
			}
		}
		if(legConfiguration.getBestialParts().contains(Tail.class)) { // Tail:
			if(body.getLeg().getType().getRace()==Race.DEMON) {
				body.setTail(new Tail(TailType.DEMON_HORSE));
			} else {
				if(body.getTail().getType().getRace()!=startingBodyType.getTailType().get(0).getRace()) {
					body.setTail(new Tail(startingBodyType.getTailType().get(0)));
				}
			}
		}
		if(legConfiguration.getBestialParts().contains(Tentacle.class)) { // Tentacle:
			body.setTentacle(new Tentacle(startingBodyType.getTentacleType()));
		}
		if(legConfiguration.getBestialParts().contains(Penis.class)) { // Penis (includes Testicle):
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
							startingBodyType.getCumProduction()*(largeGenitals?4:1),
							startingBodyType.getTesticleQuantity())
						: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2));
				body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getPenis().setVirgin(virgin);
			}
		}
		if(legConfiguration.getBestialParts().contains(Vagina.class)) { // Vagina (includes Clitoris):
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
									startingBodyType.getVaginaWetness(),
									(float) (startingBodyType.getVaginaCapacity()*(largeGenitals?2.5:1)),
									startingBodyType.getVaginaElasticity(),
									startingBodyType.getVaginaPlasticity(),
									true)
							: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 3, 3, true));
				body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getVagina().getOrificeVagina().setVirgin(virgin);
			}
		}
 	}
	
	/**
	 * @param legConfiguration The configuration to check transformation availability of.
	 * @return True if this configuration is allowed for this LegType.
	 */
	public boolean isLegConfigurationAvailable(LegConfiguration legConfiguration) {
		return allowedLegConfigurations.contains(legConfiguration);
	}
}
