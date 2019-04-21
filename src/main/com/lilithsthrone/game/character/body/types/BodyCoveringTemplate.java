package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Pimgd
 */
public class BodyCoveringTemplate {
	public final String determiner; 
	public final String namePlural;
	public final String nameSingular;
	public final List<CoveringModifier> naturalModifiers;
	public final List<CoveringModifier> extraModifiers;
	public final List<Colour> naturalColoursPrimary;
	public final List<Colour> dyeColoursPrimary;
	public final List<Colour> naturalColoursSecondary;
	public final List<Colour> dyeColoursSecondary;
	public final List<CoveringPattern> naturalPatterns;
	public final List<CoveringPattern> dyePatterns;
	public final boolean isDefaultPlural;

	public BodyCoveringTemplate(
			String determiner,
			boolean isDefaultPlural,
			String namePlural,
			String nameSingular,
			List<CoveringModifier> naturalModifiers,
			List<CoveringModifier> extraModifiers,
			List<CoveringPattern> naturalPatterns,
			List<CoveringPattern> dyePatterns,
			List<Colour> naturalColoursPrimary,
			List<Colour> dyeColoursPrimary,
			List<Colour> naturalColoursSecondary,
			List<Colour> dyeColoursSecondary) {
		
		this.determiner = determiner;
		this.namePlural = namePlural;
		this.nameSingular = nameSingular;
		this.isDefaultPlural = isDefaultPlural;
		
		this.naturalModifiers = getImmutableListFromNullableList(naturalModifiers);
		this.extraModifiers = getImmutableListFromNullableList(extraModifiers);
		
		this.naturalPatterns = getImmutableListFromNullableList(naturalPatterns, () -> Arrays.asList(CoveringPattern.NONE));
		List<CoveringPattern> dyePatternsList = getListFromNullableList(dyePatterns);
		dyePatternsList.removeAll(this.naturalPatterns);
		this.dyePatterns = Collections.unmodifiableList(dyePatternsList);
		
		this.naturalColoursPrimary = getImmutableListFromNullableList(naturalColoursPrimary);
		this.dyeColoursPrimary = getImmutableListFromNullableList(dyeColoursPrimary);
		this.naturalColoursSecondary = getImmutableListFromNullableList(naturalColoursSecondary);
		this.dyeColoursSecondary = getImmutableListFromNullableList(dyeColoursSecondary);
	}
	
	private <T> List<T> getImmutableListFromNullableList(List<T> nullableList) {
		return Collections.unmodifiableList(getListFromNullableList(nullableList));
	}
	
	private <T> List<T> getImmutableListFromNullableList(List<T> nullableList, Supplier<List<T>> replacement) {
		return Collections.unmodifiableList(Optional.ofNullable(nullableList).orElseGet(replacement));
	}
	
	private <T> List<T> getListFromNullableList(List<T> nullableList) {
		// Have to wrap in a new ArrayList, as the nullableList might be one of the preset lists from CoveringPattern (such as CoveringPattern.allHairCoveringPatterns).
		// If not wrapped in a new ArrayList, the "dyePatternsList.removeAll(this.naturalPatterns);" line removes elements from the underlying list.
		return new ArrayList<>(Optional.ofNullable(nullableList).orElse(new ArrayList<>()));
	}
}
