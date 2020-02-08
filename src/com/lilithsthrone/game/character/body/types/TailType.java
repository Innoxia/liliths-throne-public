package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.tags.TailTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.6
 * @author Innoxia
 */
public enum TailType implements BodyPartTypeInterface {
	
	NONE(null, Race.NONE, PenetrationGirth.TWO_AVERAGE, 0f),

	DEMON_COMMON(BodyCoveringType.DEMON_COMMON, Race.DEMON, PenetrationGirth.ZERO_THIN, 0.65f, TailTag.PREHENSILE, TailTag.SUTABLE_FOR_PENETRATION, TailTag.SLEEP_HUGGING),
	DEMON_HAIR_TIP(BodyCoveringType.DEMON_COMMON, Race.DEMON, PenetrationGirth.ONE_SLENDER, 0.65f, TailTag.PREHENSILE, TailTag.SLEEP_HUGGING),
	DEMON_HORSE(BodyCoveringType.HAIR_DEMON, Race.DEMON, PenetrationGirth.TWO_AVERAGE, 0.3f),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, PenetrationGirth.TWO_AVERAGE, 0.4f),
	DOG_MORPH_STUBBY(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH, PenetrationGirth.TWO_AVERAGE, 0.1f),
	
	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH, PenetrationGirth.THREE_THICK, 0.4f, TailTag.SLEEP_HUGGING),
	
	FOX_MORPH(BodyCoveringType.FOX_FUR, Race.FOX_MORPH, PenetrationGirth.THREE_THICK, 0.6f, TailTag.SLEEP_HUGGING),
	FOX_MORPH_MAGIC(BodyCoveringType.FOX_FUR, Race.FOX_MORPH, PenetrationGirth.THREE_THICK, 0.8f, TailTag.PREHENSILE, TailTag.SLEEP_HUGGING),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH, PenetrationGirth.ONE_SLENDER, 0.35f),
	
	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, PenetrationGirth.ONE_SLENDER, 0.8f, TailTag.PREHENSILE, TailTag.SLEEP_HUGGING),
	CAT_MORPH_SHORT(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, PenetrationGirth.ONE_SLENDER, 0.2f),
	CAT_MORPH_TUFTED(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH, PenetrationGirth.TWO_AVERAGE, 0.2f, TailTag.PREHENSILE, TailTag.SLEEP_HUGGING),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH, PenetrationGirth.FOUR_FAT, 1f, TailTag.SLEEP_HUGGING),

	BAT_MORPH(BodyCoveringType.BAT_SKIN, Race.BAT_MORPH, PenetrationGirth.ZERO_THIN, 0.2f),
	
	RAT_MORPH(BodyCoveringType.RAT_SKIN, Race.RAT_MORPH, PenetrationGirth.TWO_AVERAGE, 0.6f, TailTag.PREHENSILE, TailTag.SUTABLE_FOR_PENETRATION, TailTag.SLEEP_HUGGING),
	
	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH, PenetrationGirth.THREE_THICK, 0.1f),
	
	ALLIGATOR_MORPH(BodyCoveringType.ALLIGATOR_SCALES, Race.ALLIGATOR_MORPH, PenetrationGirth.FOUR_FAT, 0.6f, TailTag.PREHENSILE, TailTag.SUTABLE_FOR_PENETRATION, TailTag.SLEEP_HUGGING),
	
	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH, PenetrationGirth.TWO_AVERAGE, 0.35f),
	HORSE_MORPH_ZEBRA(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH, PenetrationGirth.TWO_AVERAGE, 0.35f),

	REINDEER_MORPH(BodyCoveringType.REINDEER_FUR, Race.REINDEER_MORPH, PenetrationGirth.TWO_AVERAGE, 0.1f),
	
	HARPY(BodyCoveringType.FEATHERS, Race.HARPY, PenetrationGirth.THREE_THICK, 0.5f, TailTag.SLEEP_HUGGING);

	private BodyCoveringType skinType;
	private Race race;
	private int girth;
	private List<TailTag> tags;
	private float lengthAsPercentageOfHeight;

	private TailType(BodyCoveringType skinType, Race race, PenetrationGirth girth, float lengthAsPercentageOfHeight, TailTag... tags) {
		this.skinType = skinType;
		this.race = race;
		this.girth = girth.getValue();
		this.lengthAsPercentageOfHeight = lengthAsPercentageOfHeight;
		this.tags = new ArrayList<>(Arrays.asList(tags));
	}

	/**
	 * Use instead of <i>valueOf()</i>.
	 */
	public static TailType getTypeFromString(String value) {
		if(value.equals("IMP")) {
			value = "DEMON_COMMON";
		}
		return valueOf(value);
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return "";
		}
		if(gc.getTailCount()==1) {
			switch(this){
				case HARPY:
					return "a plume of";
				default:
					return "";
			}
		} else {
			switch(this){
				case HARPY:
					return Util.intToString(gc.getTailCount())+" plumes of";
				default:
					return Util.intToString(gc.getTailCount());
			}
		}
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("tail feathers");
			default:
				return UtilText.returnStringAtRandom("tail");
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			case HARPY:
				return UtilText.returnStringAtRandom("tail feathers");
			default:
				return UtilText.returnStringAtRandom("tails");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(Util.random.nextInt(2)){
			case 0:
				switch(this){
					case CAT_MORPH:
						return UtilText.returnStringAtRandom("cat-like");
					case CAT_MORPH_SHORT:
						return UtilText.returnStringAtRandom("cat-like", "short");
					case CAT_MORPH_TUFTED:
						return UtilText.returnStringAtRandom("cat-like", "tufted");
					case COW_MORPH:
						return UtilText.returnStringAtRandom("cow-like", "tufted");
					case DEMON_COMMON:
						return UtilText.returnStringAtRandom("spaded", "demonic");
					case DEMON_HAIR_TIP:
						return UtilText.returnStringAtRandom("hair-tipped", "demonic");
					case DEMON_HORSE:
						return UtilText.returnStringAtRandom("horse-like");
					case DOG_MORPH:
						return UtilText.returnStringAtRandom("dog-like");
					case DOG_MORPH_STUBBY:
						return UtilText.returnStringAtRandom("stubby", "dog-like");
					case ALLIGATOR_MORPH:
						return UtilText.returnStringAtRandom("alligator-like");
					case HARPY:
						return UtilText.returnStringAtRandom("colourful", "bird-like");
					case HORSE_MORPH:
						return UtilText.returnStringAtRandom("horse-like");
					case HORSE_MORPH_ZEBRA:
						return UtilText.returnStringAtRandom("zebra-like");
					case REINDEER_MORPH:
						return UtilText.returnStringAtRandom("reindeer-like");
					case LYCAN:
						return UtilText.returnStringAtRandom("wolf-like");
					case FOX_MORPH:
						return UtilText.returnStringAtRandom("fox-like", "fluffy");
					case FOX_MORPH_MAGIC:
						return UtilText.returnStringAtRandom("arcane", "fox-like", "fluffy");
					case SQUIRREL_MORPH:
						return UtilText.returnStringAtRandom("squirrel-like", "fluffy");
					case NONE:
						return UtilText.returnStringAtRandom("");
					case RAT_MORPH:
						return UtilText.returnStringAtRandom("rat-like");
					case BAT_MORPH:
						return UtilText.returnStringAtRandom("bat-like");
					case RABBIT_MORPH:
						return UtilText.returnStringAtRandom("rabbit-like", "fluffy");
				}
				break;
			case 1:
				return gc.getTailGirth()==PenetrationGirth.TWO_AVERAGE?"":gc.getTailGirth().getName();
		}
		
		return "";
	}

	@Override
	public String getTransformName() {
		switch(this){
			case CAT_MORPH:
				return "feline";
			case CAT_MORPH_SHORT:
				return "short feline";
			case CAT_MORPH_TUFTED:
				return "tufted feline";
			case COW_MORPH:
				return "bovine";
			case DEMON_COMMON:
				return "demonic spaded";
			case DEMON_HAIR_TIP:
				return "demonic hair-tipped";
			case DEMON_HORSE:
				return "demonic horse";
			case DOG_MORPH:
				return "canine";
			case DOG_MORPH_STUBBY:
				return "stubby canine";
			case HARPY:
				return "harpy plume";
			case HORSE_MORPH:
				return "equine";
			case HORSE_MORPH_ZEBRA:
				return "zebra";
			case REINDEER_MORPH:
				return "reindeer";
			case LYCAN:
				return "wolf";
			case FOX_MORPH:
				return "fox";
			case SQUIRREL_MORPH:
				return "squirrel";
			case FOX_MORPH_MAGIC:
				return "arcane fox";
			case ALLIGATOR_MORPH:
				return "alligator";
			case NONE:
				return "none";
			case RAT_MORPH:
				return "rat";
			case BAT_MORPH:
				return "bat";
			case RABBIT_MORPH:
				return "rabbit";
		}
		return "";
	}
	
	public String getTailTipName(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("tip");
		}
	}
	
	public String getTailTipDescriptor(GameCharacter gc) {
		switch(this){
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("spaded");
			default:
				return UtilText.returnStringAtRandom("");
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
	
	@Override
	public TFModifier getTFModifier() {
		return getTFTypeModifier(getTailTypes(race));
	}

	public int getGirth() {
		return girth;
	}

	public float getLengthAsPercentageOfHeight() {
		return lengthAsPercentageOfHeight;
	}
	
	public String getGirthDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		switch(this){
			case CAT_MORPH:
			case CAT_MORPH_SHORT:
			case COW_MORPH:
			case DEMON_COMMON:
			case DEMON_HAIR_TIP:
			case DOG_MORPH:
			case DOG_MORPH_STUBBY:
			case HORSE_MORPH_ZEBRA:
			case LYCAN:
			case FOX_MORPH:
			case SQUIRREL_MORPH:
			case FOX_MORPH_MAGIC:
			case ALLIGATOR_MORPH:
			case RAT_MORPH:
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
				return sb.toString();
				
			case CAT_MORPH_TUFTED:
			case REINDEER_MORPH:
			case RABBIT_MORPH:
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
				return sb.toString();
				
			case DEMON_HORSE:
			case HORSE_MORPH:
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
				return sb.toString();
				
			case HARPY:
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
				return sb.toString();
				
			case NONE:
				return "";
				
			case BAT_MORPH:
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
				return sb.toString();
		}
		return "";
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTailGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		switch(this){
			case COW_MORPH:
			case DEMON_COMMON:
			case DEMON_HAIR_TIP:
			case HORSE_MORPH_ZEBRA:
			case ALLIGATOR_MORPH:
			case RAT_MORPH:
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
				return "";

			case CAT_MORPH:
			case CAT_MORPH_SHORT:
			case FOX_MORPH:
			case SQUIRREL_MORPH:
			case LYCAN:
			case FOX_MORPH_MAGIC:
			case DOG_MORPH:
			case DOG_MORPH_STUBBY:
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
				return "";
				
			case CAT_MORPH_TUFTED:
			case REINDEER_MORPH:
			case RABBIT_MORPH:
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
				return "";
				
			case DEMON_HORSE:
			case HORSE_MORPH:
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
				return "";
				
			case HARPY:
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
				return "";
				
			case NONE:
				return "";
				
			case BAT_MORPH:
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
				return "";
		}
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		switch(this){
			case CAT_MORPH:
			case CAT_MORPH_SHORT:
			case COW_MORPH:
			case DEMON_COMMON:
			case DEMON_HAIR_TIP:
			case DOG_MORPH:
			case DOG_MORPH_STUBBY:
			case HORSE_MORPH_ZEBRA:
			case LYCAN:
			case FOX_MORPH:
			case SQUIRREL_MORPH:
			case FOX_MORPH_MAGIC:
			case ALLIGATOR_MORPH:
			case RAT_MORPH:
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
				
			case CAT_MORPH_TUFTED:
			case REINDEER_MORPH:
			case RABBIT_MORPH:
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
				
			case DEMON_HORSE:
			case HORSE_MORPH:
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
				
			case HARPY:
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
				
			case NONE:
				return "";
				
			case BAT_MORPH:
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
		return tags.contains(TailTag.PREHENSILE);
	}

	/**
	 * Takes into account whether player has 'Allow furry tail penetrations' turned on or off.
	 */
	public boolean isSuitableForPenetration() {
		return this.isPrehensile() && (tags.contains(TailTag.SUTABLE_FOR_PENETRATION) || Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent));
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(TailTag.SLEEP_HUGGING);
	}

	private static Map<Race, List<TailType>> typesMap = new HashMap<>();
	public static List<TailType> getTailTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<TailType> types = new ArrayList<>();
		for(TailType type : TailType.values()) {
			if(type.getRace()==r && type!=TailType.FOX_MORPH_MAGIC) {
				types.add(type);
			}
		}
		if(types.isEmpty()) {
			types.add(TailType.NONE);
		}
		typesMap.put(r, types);
		return types;
	}
	

	public static List<TailType> getTailTypesSuitableForTransformation(List<TailType> options) {
		if (!options.contains(TailType.NONE)) {
			return options;
		}
		
		List<TailType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(TailType.NONE);
		return duplicatedOptions;
	}
}