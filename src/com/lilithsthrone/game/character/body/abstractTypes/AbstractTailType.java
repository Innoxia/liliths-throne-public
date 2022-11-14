package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractTailType implements BodyPartTypeInterface {

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
	
	private String tailTransformationDescription;
	private String tailBodyDescription;

	private List<BodyPartTag> tags;
	
	private boolean spinneret;
	
	/**
	 * @param coveringType What covers this tail type (i.e skin/fur/feather type).
	 * @param race What race has this tail type.
	 * @param defaultGirth The girth which this TailType spawns with.
	 * @param defaultLengthAsPercentageOfHeight The percentage, as a float from 0->1, of this tail's length as a proportion of the owner's body height.
	 * @param transformationName The name that should be displayed when offering this tail type as a transformation. Should be something like "demonic spaded" or "demonic hair-tipped".
	 * @param determiner The singular determiner which should be used for this tail type. Should normally be left blank unless the tail is of a special type (such as harpy 'tail feathers' needing 'a plume of' as the determiner).
	 * @param determinerPlural The plural determiner which should be used for this tail type, appended after a number. Should normally be left blank unless the tail is of a special type (such as harpy 'tail feathers' needing 'plumes of' as the determiner).
	 * @param name The singular name of the tail. This will usually just be "tail".
	 * @param namePlural The plural name of the tail. This will usually just be "tails".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this tail type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this tail type.
	 * @param tipName The singular name of the tip of this tail. This will usually just be "tip".
	 * @param tipNamePlural The plural name of tip of this tail. This will usually just be "tips".
	 * @param tipDescriptorsMasculine The descriptors that can be used to describe a masculine form of this tail type's tip. Will usually be blank.
	 * @param tipDescriptorsFeminine The descriptors that can be used to describe a feminine form of this tail type's tip. Will usually be blank.
	 * @param tailTransformationDescription A paragraph describing a character's tails transforming into this tail type. Parsing assumes that the character already has this tail type and associated skin covering.
	 * @param tailBodyDescription A sentence or two to describe this tail type, as seen in the character view screen. It should follow the same format as all of the other entries in the TailType class.
	 * @param tags The tags which define this tail's properties.
	 * @param spinneret true if this tail type has a spinneret.
	 */
	public AbstractTailType(
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
			String tailTransformationDescription,
			String tailBodyDescription,
			List<BodyPartTag> tags,
			boolean spinneret) {
		
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
		
		this.tailTransformationDescription = tailTransformationDescription;
		this.tailBodyDescription = tailBodyDescription;
		
		this.tags = tags;
		
		this.spinneret = spinneret;
	}
	
	public AbstractTailType(File XMLFile, String author, boolean mod) {
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
				
				this.spinneret = Boolean.valueOf(coreElement.getMandatoryFirstOf("spinneret").getTextContent());

				this.defaultGirth = Integer.valueOf(coreElement.getMandatoryFirstOf("defaultGirth").getTextContent());
				this.defaultLengthAsPercentageOfHeight = Float.valueOf(coreElement.getMandatoryFirstOf("defaultLengthAsPercentageOfHeight").getTextContent());

				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.getBodyPartTagFromId(e.getTextContent()));
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
				
				this.tailTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.tailBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractTailType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
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
		if(gc.getTailCount()==1) {
			return Util.intToString(gc.getTailCount())+" "+determiner;
		}
		return Util.intToString(gc.getTailCount())+" "+determinerPlural;
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
		if(isDefaultPlural(gc) || (gc!=null && gc.getTailCount()!=1)) {
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
	
	public String getTailTipNameSingular(GameCharacter gc) {
		return tipName;
	}
	
	public String getTailTipNamePlural(GameCharacter gc) {
		return tipNamePlural;
	}
	
	public String getTailTipDescriptor(GameCharacter gc) {
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
		return UtilText.parse(owner, tailBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, tailTransformationDescription);
	}
	
	/**
	 * @return A description of this tail's girth, based on the TYPE tag and the owner's girth.
	 */
	public String getGirthDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN) || this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tails] are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tail] is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>very thin<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>slender<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>quite narrow<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an <colourStart>average thickness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>quite thick<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>very thick<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>incredibly thick and girthy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>extremely thick and girthy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tails] are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tail] is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>very thin and severely lacking in fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>slender and lacking in fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>quite narrow and a little lacking in fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an <colourStart>average thickness and fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>quite big and very fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>very big and fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>incredibly thick and fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>extremely thick and fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] tufted tails are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] tufted tail is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>very small and significantly lacking in fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>quite small and lacking in fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>a little small and lacking in fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an <colourStart>average size and fluffiness<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>quite big and very fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>very big and extremely fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>incredibly thick and fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>extremely thick and fluffy<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] horse tails are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] horse tail is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>very much lacking in volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>lacking in volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>a little lacking in volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an <colourStart>average volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>quite voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>very voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>incredibly voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>extremely voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] plumes of feathers are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] plume of feathers is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>very small and lacking in volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>small and somewhat lacking in volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>a little narrow and lacking in volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an <colourStart>average size and volume<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>quite large and voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>very large and voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>incredibly voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>extremely voluminous<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] tails are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] tail is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>very small<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>somewhat small<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>a little narrow<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " of an <colourStart>average size<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>quite large<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>very large<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>incredibly large<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>extremely large<colourEnd> in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		
		String returnString = sb.toString();
		String colourStartTag = "<span style='color:"+owner.getTailGirth().getColour().toWebHexString()+";'>";
		String colourEndTag = "</span>";
		returnString = returnString.replaceAll("<colourStart>", colourStartTag);
		returnString = returnString.replaceAll("<colourEnd>", colourEndTag);
		
		return returnString;
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTailGirth());
	}

	public String getGirthDescriptor(Body body) {
		return getGirthDescriptor(body.getTail().getGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)) {
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
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			switch(girth) {
				case ZERO_THIN:
					return "tiny";
				case ONE_SLENDER:
					return "small";
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
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "small";
				case TWO_NARROW:
					return "narrow";
				case THREE_AVERAGE:
					return "average";
				case FOUR_GIRTHY:
					return "voluminous";
				case FIVE_THICK:
					return "extra-voluminous";
				case SIX_CHUBBY:
					return "extremely-voluminous";
				case SEVEN_FAT:
					return "unbelievably-voluminous";
			}
		}

		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "small";
				case TWO_NARROW:
					return "narrow";
				case THREE_AVERAGE:
					return "average";
				case FOUR_GIRTHY:
					return "voluminous";
				case FIVE_THICK:
					return "extra-voluminous";
				case SIX_CHUBBY:
					return "extremely-voluminous";
				case SEVEN_FAT:
					return "unbelievably-voluminous";
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			switch(girth) {
				case ZERO_THIN:
					return "tiny";
				case ONE_SLENDER:
					return "small";
				case TWO_NARROW:
					return "narrow";
				case THREE_AVERAGE:
					return "average";
				case FOUR_GIRTHY:
					return "large";
				case FIVE_THICK:
					return "huge";
				case SIX_CHUBBY:
					return "massive";
				case SEVEN_FAT:
					return "colossal";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		String tailText = "[npc.a_tailGirth] [npc.tail]";
		if(owner.getTailCount()>1) {
			tailText = "[npc.tailGirth] [npc.tails]";
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow thicker)]."
									:" [npc.tail] suddenly [style.boldGrow(grows thicker)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shrink down)]."
										:" [npc.tail] suddenly [style.boldShrink(shrinks down)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fluff up and grow bigger)]."
									:" [npc.tail] suddenly [style.boldGrow(fluffs up and grows bigger)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shrink down)]."
										:" [npc.tail] suddenly [style.boldShrink(shrinks down)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fill out and expand in volume)]."
									:" [npc.tail] suddenly [style.boldGrow(fills out and expands in volume)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shrink down and lose volume)]."
										:" [npc.tail] suddenly [style.boldShrink(shrinks down and loses volume)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fill out and expand in volume)]."
									:" [npc.tail] suddenly [style.boldGrow(fills out and expands in volume)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shrink down and lose volume)]."
										:" [npc.tail] suddenly [style.boldShrink(shrinks down and loses volume)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow larger)]."
									:" [npc.tail] suddenly [style.boldGrow(grows larger)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shrink down)]."
										:" [npc.tail] suddenly [style.boldShrink(shrinks down)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		return "";
	}
	
	public String getLengthTransformationDescription(GameCharacter owner, boolean positive) {
		String heightPercentageDescription = " (length is "+((int)(owner.getTailLengthAsPercentageOfHeight()*100))+"% of [npc.namePos] height)";
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow longer)]."
									:" [npc.tail] suddenly [style.boldGrow(grows longer)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shorten)]."
										:" [npc.tail] suddenly [style.boldShrink(shortens)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fluff up and grow longer)]."
									:" [npc.tail] suddenly [style.boldGrow(fluffs up and grows longer)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shorten)]."
										:" [npc.tail] suddenly [style.boldShrink(shortens)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow longer)]."
									:" [npc.tail] suddenly [style.boldGrow(grows longer)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shorten)]."
										:" [npc.tail] suddenly [style.boldShrink(shortens)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow longer)]."
									:" [npc.tail] suddenly [style.boldGrow(grows longer)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shorten)]."
										:" [npc.tail] suddenly [style.boldShrink(shortens)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow longer)]."
									:" [npc.tail] suddenly [style.boldGrow(grows longer)].")
							+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>"
								+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
								+ " Without any further warning of what's to come, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] suddenly [style.boldShrink(shorten)]."
										:" [npc.tail] suddenly [style.boldShrink(shortens)].")
								+ "<br/>"
							+ "[npc.She] now [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		return "";
	}
	
	public boolean isPrehensile() {
		return tags.contains(BodyPartTag.TAIL_PREHENSILE);
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(BodyPartTag.TAIL_SLEEP_HUGGING);
	}
	
	public boolean isSuitableForAttack() {
		return tags.contains(BodyPartTag.TAIL_ATTACK);
	}

	public boolean isOvipositor() {
		return tags.contains(BodyPartTag.TAIL_OVIPOSITOR);
	}

	@Override
	public TFModifier getTFModifier() {
		return this == TailType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TailType.getTailTypes(race));
	}
	
	public boolean hasSpinneret() {
		return spinneret;
	}
}
