package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.tags.TentacleTypeTag;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.8.9
 * @version 0.3.8.9
 * @author Innoxia
 */
public abstract class AbstractTentacleType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;
	
	private int defaultGirth;
	private float lengthAsPercentageOfHeight;

	private String transformationName;
	
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

	private List<TentacleTypeTag> tags;
	
	/**
	 * @param skinType What covers this tentacle type (i.e skin/fur/feather type).
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
			BodyCoveringType skinType,
			Race race,
			PenetrationGirth defaultGirth,
			float lengthAsPercentageOfHeight,
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
			List<TentacleTypeTag> tags) {
		
		this.skinType = skinType;
		this.race = race;

		this.defaultGirth = defaultGirth.getValue();
		this.lengthAsPercentageOfHeight = lengthAsPercentageOfHeight;
		
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

	public int getDefaultGirth() {
		return defaultGirth;
	}
	
	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}
	
	public List<TentacleTypeTag> getTags() {
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
	public String getTransformName() {
		return transformationName;
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
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
		
		if(this.getTags().contains(TentacleTypeTag.TYPE_FUR)) {
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
				case FOUR_THICK:
					sb.append(UtilText.parse(owner, " quite big and very fluffy in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_FAT:
					sb.append(UtilText.parse(owner, " very big and extremely fluffy in proportion to the rest of [npc.her] body."));
					break;
				case SIX_GIRTHY:
					sb.append(UtilText.parse(owner, " incredibly thick and fluffy in proportion to the rest of [npc.her] body."));
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
				case FOUR_THICK:
					sb.append(UtilText.parse(owner, " quite thick in proportion to the rest of [npc.her] body."));
					break;
				case FIVE_FAT:
					sb.append(UtilText.parse(owner, " very thick in proportion to the rest of [npc.her] body."));
					break;
				case SIX_GIRTHY:
					sb.append(UtilText.parse(owner, " incredibly thick and girthy in proportion to the rest of [npc.her] body."));
					break;
			}	
		}
		return sb.toString();
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTentacleGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(TentacleTypeTag.TYPE_FUR)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "slender";
				case TWO_NARROW:
					return "narrow";
				case THREE_AVERAGE:
					return "fluffy";
				case FOUR_THICK:
					return "very-fluffy";
				case FIVE_FAT:
					return "extra-fluffy";
				case SIX_GIRTHY:
					return "extremely-fluffy";
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
				case FOUR_THICK:
					return "thick";
				case FIVE_FAT:
					return "extra-thick";
				case SIX_GIRTHY:
					return "extremely-thick";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		if(positive) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] [npc.tentacles]."
						+ " Without any further warning of what's to come, [npc.her]"
						+(owner.getTentacleCount()>1
								?" [npc.tentacles] suddenly [style.boldGrow(grow thicker)]."
								:" [npc.tentacle] suddenly [style.boldGrow(grows thicker)].")
						+ "<br/>"
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_tentacleGirth] [npc.tentacle])]!"
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
						+ "[npc.She] now [npc.has] [style.boldSex([npc.a_tentacleGirth] [npc.tentacle])]!"
					+ "</p>");
		}
	}
	
	public boolean isPrehensile() {
		return tags.contains(TentacleTypeTag.PREHENSILE);
	}
	
	public boolean isSuitableForPenetration() {
		return this.isPrehensile() && tags.contains(TentacleTypeTag.SUTABLE_FOR_PENETRATION);
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(TentacleTypeTag.SLEEP_HUGGING);
	}

	@Override
	public TFModifier getTFModifier() {
		return this==TentacleType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TentacleType.getTentacleTypes(race));
	}
}
