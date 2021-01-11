package com.lilithsthrone.game.character.race;

import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class FeralAttributes {
	
	private String feralName;
	private String feralNamePlural;
	private String feralSingularMaleName;
	private String feralSingularFemaleName;
	private String feralPluralMaleName;
	private String feralPluralFemaleName;
	
	private LegConfiguration legConfiguration;
	private boolean sizeHeight;
	private int size;

	private boolean armsOrWingsPresent;
	private boolean fingerActionsAvailable;
	private boolean hairPresent;
	
	private int breastRowCount;
	private int nipplesPerBreastCount;
	private int crotchBreastRowCount;
	private int nipplesPerCrotchBreastCount;
	
	public FeralAttributes(
			String feralName,
			String feralNamePlural,
			LegConfiguration legConfiguration,
			int size,
			int breastRowCount,
			int nipplesPerBreastCount,
			int crotchBreastRowCount,
			int nipplesPerCrotchBreastCount,
			boolean hairPresent) {
		this(feralName,
				feralNamePlural,
				feralName,
				feralName,
				feralNamePlural,
				feralNamePlural,
				legConfiguration,
				true,
				size,
				breastRowCount,
				nipplesPerBreastCount,
				crotchBreastRowCount,
				nipplesPerCrotchBreastCount,
				hairPresent);
	}

	public FeralAttributes(
			String feralName,
			String feralNamePlural,
			LegConfiguration legConfiguration,
			boolean sizeHeight,
			int size,
			int breastRowCount,
			int nipplesPerBreastCount,
			int crotchBreastRowCount,
			int nipplesPerCrotchBreastCount,
			boolean hairPresent) {
		this(feralName,
				feralNamePlural,
				feralName,
				feralNamePlural,
				feralName,
				feralNamePlural,
				legConfiguration,
				sizeHeight,
				size,
				breastRowCount,
				nipplesPerBreastCount,
				crotchBreastRowCount,
				nipplesPerCrotchBreastCount,
				hairPresent);
	}
	
	public FeralAttributes(
			String feralName,
			String feralNamePlural,
			String feralSingularMaleName,
			String feralSingularFemaleName,
			String feralPluralMaleName,
			String feralPluralFemaleName,
			LegConfiguration legConfiguration,
			int size,
			int breastRowCount,
			int nipplesPerBreastCount,
			int crotchBreastRowCount,
			int nipplesPerCrotchBreastCount,
			boolean hairPresent) {
		this(feralName,
				feralNamePlural,
				feralSingularMaleName,
				feralSingularFemaleName,
				feralPluralMaleName,
				feralPluralFemaleName,
				legConfiguration,
				true,
				size,
				breastRowCount,
				nipplesPerBreastCount,
				crotchBreastRowCount,
				nipplesPerCrotchBreastCount,
				hairPresent);
	}
	
	public FeralAttributes(
			String feralName,
			String feralNamePlural,
			String feralSingularMaleName,
			String feralSingularFemaleName,
			String feralPluralMaleName,
			String feralPluralFemaleName,
			LegConfiguration legConfiguration,
			boolean sizeHeight,
			int size,
			int breastRowCount,
			int nipplesPerBreastCount,
			int crotchBreastRowCount,
			int nipplesPerCrotchBreastCount,
			boolean hairPresent) {
		this(feralName,
				feralNamePlural,
				feralSingularMaleName,
				feralSingularFemaleName,
				feralPluralMaleName,
				feralPluralFemaleName,
				legConfiguration,
				sizeHeight,
				size,
				breastRowCount,
				nipplesPerBreastCount,
				crotchBreastRowCount,
				nipplesPerCrotchBreastCount,
				false,
				false,
				hairPresent);
	}

	public FeralAttributes(
			String feralName,
			String feralNamePlural,
			String feralSingularMaleName,
			String feralSingularFemaleName,
			String feralPluralMaleName,
			String feralPluralFemaleName,
			LegConfiguration legConfiguration,
			boolean sizeHeight,
			int size,
			int breastRowCount,
			int nipplesPerBreastCount,
			int crotchBreastRowCount,
			int nipplesPerCrotchBreastCount,
			boolean armsOrWingsPresent,
			boolean fingerActionsAvailable,
			boolean hairPresent) {
		this.feralName = feralName;
		this.feralNamePlural = feralNamePlural;
		this.feralSingularMaleName = feralSingularMaleName;
		this.feralSingularFemaleName = feralSingularFemaleName;
		this.feralPluralMaleName = feralPluralMaleName;
		this.feralPluralFemaleName = feralPluralFemaleName;
		
		this.legConfiguration = legConfiguration;
		this.sizeHeight = sizeHeight;
		this.size = size;
		
		this.armsOrWingsPresent = armsOrWingsPresent;
		this.fingerActionsAvailable = fingerActionsAvailable;
		this.hairPresent = hairPresent;
		
		this.breastRowCount = breastRowCount;
		this.nipplesPerBreastCount = nipplesPerBreastCount;
		this.crotchBreastRowCount = crotchBreastRowCount;
		this.nipplesPerCrotchBreastCount = nipplesPerCrotchBreastCount;
	}
	
	public String getFeralName() {
		return feralName;
	}

	public String getFeralNamePlural() {
		return feralNamePlural;
	}

	public String getFeralSingularMaleName() {
		return feralSingularMaleName;
	}

	public String getFeralSingularFemaleName() {
		return feralSingularFemaleName;
	}

	public String getFeralPluralMaleName() {
		return feralPluralMaleName;
	}

	public String getFeralPluralFemaleName() {
		return feralPluralFemaleName;
	}

	public LegConfiguration getLegConfiguration() {
		return legConfiguration;
	}

	/**
	 * @return true if the getSize() method is returning the height from foot to shoulder/head (e.g. for horses), false if it is measuring from head to tail (e.g. for alligators).
	 */
	public boolean isSizeHeight() {
		return sizeHeight;
	}

	public int getSize() {
		return size;
	}

	/**
	 * @return true if this feral body has either arms or arm-wings.
	 */
	public boolean isArmsOrWingsPresent() {
		return armsOrWingsPresent;
	}
	
	public boolean isFingerActionsAvailable() {
		return fingerActionsAvailable;
	}
	
	public int getBreastRowCount() {
		return breastRowCount;
	}
	
	public int getNipplesPerBreastCount() {
		return nipplesPerBreastCount;
	}
	
	public int getCrotchBreastRowCount() {
		return crotchBreastRowCount;
	}
	
	public int getNipplesPerCrotchBreastCount() {
		return nipplesPerCrotchBreastCount;
	}
	
	public boolean isBreastsPresent() {
		return getBreastRowCount()!=0;
	}
	
	public boolean isHairPresent() {
		return hairPresent;
	}
}
