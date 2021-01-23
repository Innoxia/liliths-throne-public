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
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.9
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractTentacleType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private int defaultGirth;
	private float defaultLengthAsPercentageOfHeight;

	private String determiner;
	private String determinerPlural;
	
	private String name;
	private String namePlural;
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;

	private String tipName;
	private String tipNamePlural;
	private List<String> tipDescriptorsMasculine;
	private List<String> tipDescriptorsFeminine;
	
	private String tentacleTransformationDescription;
	private String tentacleBodyDescription;

	private List<BodyPartTag> tags;
	
	/**
	 * @param coveringType What covers this tentacle type (i.e skin/fur/feather type).
	 * @param race What race has this tentacle type.
	 * @param defaultGirth The girth which this TentacleType spawns with.
	 * @param lengthAsPercentageOfHeight The percentage, as a float from 0->1, of this tentacle's length as a proportion of the owner's body height.
	 * @param transformationName The name that should be displayed when offering this tentacle type as a transformation. Should be something like "demonic spaded" or "demonic hair-tipped".
	 * @param determiner The singular determiner which should be used for this tentacle type. Should normally be left blank unless the tentacle is of a special type (such as harpy 'tentacle feathers' needing 'a plume of' as the determiner).
	 * @param determinerPlural The plural determiner which should be used for this tentacle type, appended after a number. Should normally be left blank unless the tentacle is of a special type (such as harpy 'tentacle feathers' needing 'plumes of' as the determiner).
	 * @param name The singular name of the tentacle. This will usually just be "tentacle".
	 * @param namePlural The plural name of the tentacle. This will usually just be "tentacles".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this tentacle type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this tentacle type.
	 * @param tipName The singular name of the tip of this tentacle. This will usually just be "tip".
	 * @param tipNamePlural The plural name of tip of this tentacle. This will usually just be "tips".
	 * @param tipDescriptorsMasculine The descriptors that can be used to describe a masculine form of this tentacle type's tip. Will usually be blank.
	 * @param tipDescriptorsFeminine The descriptors that can be used to describe a feminine form of this tentacle type's tip. Will usually be blank.
	 * @param tentacleTransformationDescription A paragraph describing a character's tentacles transforming into this tentacle type. Parsing assumes that the character already has this tentacle type and associated skin covering.
	 * @param tentacleBodyDescription A sentence or two to describe this tentacle type, as seen in the character view screen. It should follow the same format as all of the other entries in the TentacleType class.
	 * @param tags The tags which define this tentacle's properties.
	 */
	public AbstractTentacleType(
			AbstractBodyCoveringType coveringType,
			AbstractRace race,
			PenetrationGirth defaultGirth,
			float defaultLengthAsPercentageOfHeight,
			String transformationName,
			String determiner,
			String determinerPlural,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String tipName,
			String tipNamePlural,
			List<String> tipDescriptorsMasculine,
			List<String> tipDescriptorsFeminine,
			String tentacleTransformationDescription,
			String tentacleBodyDescription,
			List<BodyPartTag> tags) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.defaultGirth = defaultGirth.getValue();
		this.defaultLengthAsPercentageOfHeight = defaultLengthAsPercentageOfHeight;
		
		this.transformationName = transformationName;
		
		this.determiner = determiner;
		this.determinerPlural = determinerPlural;
		
		this.name = name;
		this.namePlural = namePlural;
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.tipName = tipName;
		this.tipNamePlural = tipNamePlural;
		this.tipDescriptorsMasculine = tipDescriptorsMasculine;
		this.tipDescriptorsFeminine = tipDescriptorsFeminine;
		
		this.tentacleTransformationDescription = tentacleTransformationDescription;
		this.tentacleBodyDescription = tentacleBodyDescription;
		
		this.tags = tags;
	}

	public AbstractTentacleType(File XMLFile, String author, boolean mod) {
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

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.defaultGirth = Integer.valueOf(coreElement.getMandatoryFirstOf("defaultGirth").getTextContent());
				this.defaultLengthAsPercentageOfHeight = Float.valueOf(coreElement.getMandatoryFirstOf("defaultLengthAsPercentageOfHeight").getTextContent());

				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.valueOf(e.getTextContent()));
					}
				}
				if(tags.isEmpty()) {
					tags.add(BodyPartTag.TAIL_TYPE_GENERIC);
					tags.add(BodyPartTag.TAIL_TAPERING_NONE);
				}
				
				this.determiner = coreElement.getMandatoryFirstOf("determiner").getTextContent();
				this.determinerPlural = coreElement.getMandatoryFirstOf("determinerPlural").getTextContent();
				
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
				
				this.tipName = coreElement.getMandatoryFirstOf("tipName").getTextContent();
				this.tipNamePlural = coreElement.getMandatoryFirstOf("tipNamePlural").getTextContent();
				this.tipDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tipDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tipDescriptorsMasculine").getAllOf("descriptor")) {
						tipDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.tipDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tipDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tipDescriptorsFeminine").getAllOf("descriptor")) {
						tipDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.tentacleTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.tentacleBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractTentacleType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public int getDefaultGirth() {
		return defaultGirth;
	}

	public float getDefaultLengthAsPercentageOfHeight() {
		return defaultLengthAsPercentageOfHeight;
	}
	
	@Override
	public List<BodyPartTag> getTags() {
		return tags;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return "";
		}
		if(gc.getTentacleCount()==1) {
			return Util.intToString(gc.getTentacleCount())+" "+determiner;
		}
		return Util.intToString(gc.getTentacleCount())+" "+determinerPlural;
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}
	
	@Override
	public String getName(GameCharacter gc){
		if(isDefaultPlural(gc) || (gc!=null && gc.getTentacleCount()!=1)) {
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
	
	public String getTentacleTipNameSingular(GameCharacter gc) {
		return tipName;
	}
	
	public String getTentacleTipNamePlural(GameCharacter gc) {
		return tipNamePlural;
	}
	
	public String getTentacleTipDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(tipDescriptorsFeminine);
		} else {
			return Util.randomItemFrom(tipDescriptorsMasculine);
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

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, tentacleBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, tentacleTransformationDescription);
	}
	
	/**
	 * @return A description of this tentacle's girth, based on the TYPE tag and the owner's girth.
	 */
	public String getGirthDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(owner.getTentacleCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tentacles] are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tentacle] is"));
			}
			switch(owner.getTentacleGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very thin and severely lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " slender and lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " quite narrow and a little lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average thickness and fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " quite big and fluffy in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " very big and fluffy in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " incredibly thick and fluffy in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " extremely thick and fluffy in proportion to the rest of [npc.her] body."));
					break;
			}
			
		} else {
			if(owner.getTentacleCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tentacles] are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tentacle] is"));
			}
			switch(owner.getTentacleGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very thin in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " slender in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " quite narrow in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average thickness in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " quite thick in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " very thick in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " incredibly thick and girthy in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " extremely thick and girthy in proportion to the rest of [npc.her] body."));
					break;
			}	
		}
		return sb.toString();
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTentacleGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "slender";
				case TWO_NARROW:
					return "narrow";
				case THREE_AVERAGE:
					return "fluffy";
				case FOUR_GIRTHY:
					return "very-fluffy";
				case FIVE_THICK:
					return "extra-fluffy";
				case SIX_CHUBBY:
					return "extremely-fluffy";
				case SEVEN_FAT:
					return "unbelievably-fluffy";
			}
			
		} else {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "slender";
				case TWO_NARROW:
					return "narrow";
				case THREE_AVERAGE:
					return "average";
				case FOUR_GIRTHY:
					return "thick";
				case FIVE_THICK:
					return "extra-thick";
				case SIX_CHUBBY:
					return "extremely-thick";
				case SEVEN_FAT:
					return "unbelievably-thick";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		String tentacleText = "[npc.a_tentacleGirth] [npc.tentacle]";
		if(owner.getTentacleCount()>1) {
			tentacleText = "[npc.tentacleGirth] [npc.tentacles]";
		}
		
		if(positive) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] [npc.tentacles]."
						+ " Without any further warning of what's to come, [npc.her]"
						+(owner.getTentacleCount()>1
								?" [npc.tentacles] suddenly [style.boldGrow(grow thicker)]."
								:" [npc.tentacle] suddenly [style.boldGrow(grows thicker)].")
						+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex("+tentacleText+")]!"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] [npc.tentacles]."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTentacleCount()>1
									?" [npc.tentacles] suddenly [style.boldShrink(shrink down)]."
									:" [npc.tentacle] suddenly [style.boldShrink(shrinks down)].")
							+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex("+tentacleText+")]!"
					+ "</p>");
		}
	}
	
	public String getLengthTransformationDescription(GameCharacter owner, boolean positive) {
		String heightPercentageDescription = " (length is "+((int)(owner.getTentacleLengthAsPercentageOfHeight()*100))+"% of [npc.namePos] height)";
		if(positive) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the [npc.tentacleTip] of each of [npc.her] [npc.tentacles]."
						+ " Without any further warning of what's to come, [npc.her] [npc.tentacles] suddenly [style.boldGrow(grow longer)]."
						+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.tentacleLength] [npc.tentacles])]"+heightPercentageDescription+"!"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the [npc.tentacleTip] of each of [npc.her] [npc.tentacles]."
						+ " Without any further warning of what's to come, [npc.her] [npc.tentacles] suddenly [style.boldShrink(shorten)]."
						+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.tentacleLength] [npc.tentacles])]"+heightPercentageDescription+"!"
					+ "</p>");
		}
	}
	
	public boolean isPrehensile() {
		return tags.contains(BodyPartTag.TAIL_PREHENSILE);
	}
	
	public boolean isSuitableForPenetration() {
		return this.isPrehensile() && tags.contains(BodyPartTag.TAIL_SUTABLE_FOR_PENETRATION);
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(BodyPartTag.TAIL_SLEEP_HUGGING);
	}

	@Override
	public TFModifier getTFModifier() {
		return this==TentacleType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TentacleType.getTentacleTypes(race));
	}
}
