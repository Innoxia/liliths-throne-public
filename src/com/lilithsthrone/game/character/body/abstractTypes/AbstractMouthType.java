package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.3.9.1
 * @author Innoxia
 */
public abstract class AbstractMouthType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private AbstractRace race;
	private AbstractTongueType tongueType;
	
	private List<String> names;
	private List<String> namesPlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String mouthBodyDescription;
	
	List<OrificeModifier> defaultRacialOrificeModifiers;

	public AbstractMouthType(AbstractRace race, AbstractTongueType tongueType) {
		this(BodyCoveringType.MOUTH,
				race,
				tongueType,
				null,
				null,
				Util.newArrayListOfValues(""),
				Util.newArrayListOfValues(""),
				null,
				Util.newArrayListOfValues());
	}
	
	/**
	 * @param skinType What covers this mouth type (i.e skin/fur/feather type). This is never used, as skin type covering mouth is determined by torso covering.
	 * @param race What race has this mouth type.
	 * @param tongueType The type of tongue this mouth contains.
	 * @param names A list of singular names for this mouth type. Pass in null to use generic names.
	 * @param namesPlural A list of plural names for this mouth type. Pass in null to use generic names.
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this mouth type.
	 * @param mouthBodyDescription A sentence or two to describe this mouth type, as seen in the character view screen. It should follow the same format as all of the other entries in the MouthType class. Pass in null to use a generic description.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this mouth type.
	 */
	public AbstractMouthType(BodyCoveringType skinType,
			AbstractRace race,
			AbstractTongueType tongueType,
			List<String> names,
			List<String> namesPlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String mouthBodyDescription,
			List<OrificeModifier> defaultRacialOrificeModifiers) {
		
		this.skinType = skinType;
		this.race = race;
		this.tongueType = tongueType;
		
		this.names = names;
		this.namesPlural = namesPlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		if(mouthBodyDescription==null || mouthBodyDescription.isEmpty()) {
			this.mouthBodyDescription ="[npc.SheHasFull] [npc.lipSize], [npc.mouthColourPrimary(true)] [npc.lips]"
						+ "#IF(npc.isWearingLipstick())"
							+ "#IF(npc.isPiercedLip()), which have been pierced, and#ELSE, which#ENDIF"
							+ " are currently [npc.materialCompositionDescriptor]"
							+ "#IF(npc.isHeavyMakeup(BODY_COVERING_TYPE_MAKEUP_LIPSTICK) && game.isLipstickMarkingEnabled())"
								+ " a [style.colourPinkDeep(heavy layer)] of"
							+ "#ENDIF"
							+ " [#npc.getLipstick().getFullDescription(npc, true)]."
						+ "#ELSE"
							+ "#IF(npc.isPiercedLip()), which have been pierced#ENDIF."
						+ "#ENDIF"
						+ " [npc.Her] throat is [npc.mouthColourSecondary(true)] in colour.";
		} else {
			this.mouthBodyDescription = mouthBodyDescription;
		}

		if(defaultRacialOrificeModifiers==null) {
			this.defaultRacialOrificeModifiers = new ArrayList<>();
		} else {
			this.defaultRacialOrificeModifiers = defaultRacialOrificeModifiers;
		}
	}
	
	public AbstractTongueType getTongueType() {
		return tongueType;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(names==null) {
			return UtilText.returnStringAtRandom("mouth");
		}
		return Util.randomItemFrom(names);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(namesPlural==null) {
			return UtilText.returnStringAtRandom("mouths");
		}
		return Util.randomItemFrom(namesPlural);
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
	public AbstractRace getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, mouthBodyDescription);
	}
	
	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}
