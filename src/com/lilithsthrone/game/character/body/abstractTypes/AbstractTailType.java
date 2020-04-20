package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.tags.TailTypeTag;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.7
 * @author Innoxia
 */
public abstract class AbstractTailType implements BodyPartTypeInterface {

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
	
	private String tailTransformationDescription;
	private String tailBodyDescription;

	private List<TailTypeTag> tags;
	
	/**
	 * @param skinType What covers this tail type (i.e skin/fur/feather type).
	 * @param race What race has this tail type.
	 * @param defaultGirth The girth which this TailType spawns with.
	 * @param lengthAsPercentageOfHeight The percentage, as a float from 0->1, of this tail's length as a proportion of the owner's body height.
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
	 */
	public AbstractTailType(
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
			String tailTransformationDescription,
			String tailBodyDescription,
			List<TailTypeTag> tags) {
		
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
		
		this.tailTransformationDescription = tailTransformationDescription;
		this.tailBodyDescription = tailBodyDescription;
		
		this.tags = tags;
	}

	public int getDefaultGirth() {
		return defaultGirth;
	}
	
	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}
	
	public List<TailTypeTag> getTags() {
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
	public String getTransformName() {
		return transformationName;
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
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
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
		
		if(this.getTags().contains(TailTypeTag.TYPE_SKIN)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tails] are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tail] is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very thin in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " quite slender in proportion to the rest of [npc.her] body."));
					break;
				case TWO_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average thickness in proportion to the rest of [npc.her] body."));
					break;
				case THREE_THICK:
					sb.append(UtilText.parse(owner, " quite thick in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_FAT:
					sb.append(UtilText.parse(owner, " very thick in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_FUR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tails] are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tail] is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very thin and severely lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " quite slender and lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case TWO_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average thickness and fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case THREE_THICK:
					sb.append(UtilText.parse(owner, " quite big and very fluffy in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_FAT:
					sb.append(UtilText.parse(owner, " very big and extremely fluffy in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_TUFT)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] tufted tails are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] tufted tail is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very small and significantly lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " quite small and lacking in fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case TWO_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average size and fluffiness in proportion to the rest of [npc.her] body."));
					break;
				case THREE_THICK:
					sb.append(UtilText.parse(owner, " quite big and very fluffy in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_FAT:
					sb.append(UtilText.parse(owner, " very big and extremely fluffy in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_HAIR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] horse tails are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] horse tail is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very much lacking in volume in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " lacking in volume in proportion to the rest of [npc.her] body."));
					break;
				case TWO_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average volume in proportion to the rest of [npc.her] body."));
					break;
				case THREE_THICK:
					sb.append(UtilText.parse(owner, " quite voluminous in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_FAT:
					sb.append(UtilText.parse(owner, " very voluminous in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_FEATHER)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] plumes of feathers are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] plume of feathers is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very small and lacking in volume in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " small and somewhat lacking in volume in proportion to the rest of [npc.her] body."));
					break;
				case TWO_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average size and volume in proportion to the rest of [npc.her] body."));
					break;
				case THREE_THICK:
					sb.append(UtilText.parse(owner, " quite large and voluminous in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_FAT:
					sb.append(UtilText.parse(owner, " very large and voluminous in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_GENERIC)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] bat tails are"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] bat tail is"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " very small in proportion to the rest of [npc.her] body."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " somewhat small in proportion to the rest of [npc.her] body."));
					break;
				case TWO_AVERAGE:
					sb.append(UtilText.parse(owner, " of an average size in proportion to the rest of [npc.her] body."));
					break;
				case THREE_THICK:
					sb.append(UtilText.parse(owner, " quite large in proportion to the rest of [npc.her] body."));
					break;
				case FOUR_FAT:
					sb.append(UtilText.parse(owner, " very large in proportion to the rest of [npc.her] body."));
					break;
			}
		}
		return sb.toString();
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTailGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(TailTypeTag.TYPE_SKIN)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "slender";
				case TWO_AVERAGE:
					return "average";
				case THREE_THICK:
					return "thick";
				case FOUR_FAT:
					return "extra-thick";
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_FUR)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "slender";
				case TWO_AVERAGE:
					return "fluffy";
				case THREE_THICK:
					return "extra-fluffy";
				case FOUR_FAT:
					return "super-fluffy";
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_TUFT)) {
			switch(girth) {
				case ZERO_THIN:
					return "tiny";
				case ONE_SLENDER:
					return "small";
				case TWO_AVERAGE:
					return "fluffy";
				case THREE_THICK:
					return "extra-fluffy";
				case FOUR_FAT:
					return "super-fluffy";
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_HAIR)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "small";
				case TWO_AVERAGE:
					return "average";
				case THREE_THICK:
					return "voluminous";
				case FOUR_FAT:
					return "extra-voluminous";
			}
		}

		if(this.getTags().contains(TailTypeTag.TYPE_FEATHER)) {
			switch(girth) {
				case ZERO_THIN:
					return "thin";
				case ONE_SLENDER:
					return "small";
				case TWO_AVERAGE:
					return "average";
				case THREE_THICK:
					return "voluminous";
				case FOUR_FAT:
					return "extra-voluminous";
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_GENERIC)) {
			switch(girth) {
				case ZERO_THIN:
					return "tiny";
				case ONE_SLENDER:
					return "small";
				case TWO_AVERAGE:
					return "average";
				case THREE_THICK:
					return "large";
				case FOUR_FAT:
					return "huge";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		if(this.getTags().contains(TailTypeTag.TYPE_SKIN) || this.getTags().contains(TailTypeTag.TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow thicker)]."
									:" [npc.tail] suddenly [style.boldGrow(grows thicker)].")
							+ "<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
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
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fluff up and grow bigger)]."
									:" [npc.tail] suddenly [style.boldGrow(fluffs up and grows bigger)].")
							+ "<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
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
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fill out and expand in volume)]."
									:" [npc.tail] suddenly [style.boldGrow(fills out and expands in volume)].")
							+ "<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
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
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(fill out and expand in volume)]."
									:" [npc.tail] suddenly [style.boldGrow(fills out and expands in volume)].")
							+ "<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
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
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(TailTypeTag.TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a deep throbbing sensation building up at the base of [npc.her] spine."
							+ " Without any further warning of what's to come, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] suddenly [style.boldGrow(grow larger)]."
									:" [npc.tail] suddenly [style.boldGrow(grows larger)].")
							+ "<br/>"
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
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
							+ "[npc.She] now has [style.boldSex([npc.a_tailGirth] [npc.tail])]!"
						+ "</p>");
			}
		}
		return "";
	}
	
	public boolean isPrehensile() {
		return tags.contains(TailTypeTag.PREHENSILE);
	}

	/**
	 * Takes into account whether player has 'Allow furry tail penetrations' turned on or off.
	 */
	public boolean isSuitableForPenetration() {
		return this.isPrehensile() && (tags.contains(TailTypeTag.SUTABLE_FOR_PENETRATION) || Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent));
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(TailTypeTag.SLEEP_HUGGING);
	}

	@Override
	public TFModifier getTFModifier() {
		return this == TailType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TailType.getTailTypes(race));
	}
}
