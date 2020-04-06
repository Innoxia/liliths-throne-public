package com.lilithsthrone.game.character.body.abstractTypes;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class AbstractHornType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;
	
	private int defaultHornsPerRow;

	private String transformationName;
	private String name;
	private String namePlural;

	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private String hornTransformationDescription;
	private String hornBodyDescription;
	
	/**
	 * @param skinType What covers this horn type (i.e skin/fur/feather type).
	 * @param race What race has this horn type.
	 * @param defaultHornsPerRow The number of horns per row by default for this horn type.
	 * @param transformationName The name that should be displayed when offering this horn type as a transformation. Should be something like "curved" or "straight".
	 * @param name The singular name of the horn. This will usually just be "horn" or "antler".
	 * @param namePlural The plural name of the horn. This will usually just be "horns" or "antlers".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this horn type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this horn type.
	 * @param hornTransformationDescription A paragraph describing a character's horns transforming into this horn type. Parsing assumes that the character already has this horn type and associated skin covering.
	 * @param hornBodyDescription A sentence or two to describe this horn type, as seen in the character view screen. It should follow the same format as all of the other entries in the HornType class.
	 */
	public AbstractHornType(
			BodyCoveringType skinType,
			Race race,
			int defaultHornsPerRow,
			String transformationName,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String hornTransformationDescription,
			String hornBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		
		this.transformationName = transformationName;
		this.name = name;
		this.namePlural = namePlural;
		
		this.defaultHornsPerRow = defaultHornsPerRow;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.hornTransformationDescription = hornTransformationDescription;
		this.hornBodyDescription = hornBodyDescription;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getHornRows()==1) {
			if(gc.getHornsPerRow()==1) {
				return "a solitary";
			} else if(gc.getHornsPerRow()==2) {
				return "a pair of";
			} else if(gc.getHornsPerRow()==3) {
				return "a trio of";
			} else {
				return "a quartet of";
			}
			
		} else {
			if(gc.getHornsPerRow()==1) {
				return Util.intToString(gc.getHornRows())+" vertically-aligned";
			} else if(gc.getHornsPerRow()==2) {
				return Util.intToString(gc.getHornRows())+" pairs of";
			} else if(gc.getHornsPerRow()==3) {
				return Util.intToString(gc.getHornRows())+" trios of";
			} else {
				return Util.intToString(gc.getHornRows())+" quartets of";
			}
		}
	}

	public int getDefaultHornsPerRow() {
		return defaultHornsPerRow;
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
		return UtilText.parse(owner, hornBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, hornTransformationDescription);
	}
}
