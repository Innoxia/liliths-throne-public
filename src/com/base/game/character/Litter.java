package com.base.game.character;

import java.io.Serializable;
import java.util.List;

import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.62
 * @version 0.1.82
 * @author Innoxia
 */
public class Litter implements Serializable {

	private static final long serialVersionUID = 1L;

	private int dayOfConception, dayOfBirth;
	private GameCharacter mother, father;
	private int sonsMother, daughtersMother, sonsFather, daughtersFather;
	private List<NPC> offspring;
	private Race motherRace, fatherRace;

	public Litter(int dayOfConception, int dayOfBirth, GameCharacter mother, GameCharacter father, List<NPC> offspring) {
		this.dayOfConception = dayOfConception;
		this.dayOfBirth = dayOfBirth;
		
		this.mother = mother;
		this.father = father;
		motherRace = mother.getRace();
		fatherRace = father.getRace();
		
		this.offspring=offspring;
		
		sonsMother = 0;
		daughtersMother = 0;
		sonsFather = 0;
		daughtersFather = 0;
		for(GameCharacter gc : offspring) {
			if(gc.isFeminine()) {
				if(gc.getRace()==mother.getRace()) {
					daughtersMother++;
				} else {
					daughtersFather++;
				}
				
			} else {
				if(gc.getRace()==mother.getRace()) {
					sonsMother++;
				} else {
					sonsFather++;
				}
			}
		}
	}

	public int getDayOfConception() {
		return dayOfConception;
	}

	public int getDayOfBirth() {
		dayOfBirth = dayOfConception += 8;
		return dayOfBirth;
	}
	
	public void setDayOfBirth(int dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public GameCharacter getMother() {
		return mother;
	}

	public GameCharacter getFather() {
		return father;
	}

	public List<NPC> getOffspring() {
		return offspring;
	}

	public int getSonsFromMother() {
		return sonsMother;
	}

	public int getDaughtersFromMother() {
		return daughtersMother;
	}
	
	public int getSonsFromFather() {
		return sonsFather;
	}

	public int getDaughtersFromFather() {
		return daughtersFather;
	}
	
	public int getTotalLitterCount() {
		return sonsMother + daughtersMother + sonsFather + daughtersFather;
	}

	public Race getMotherRace() {
		return motherRace;
	}

	public Race getFatherRace() {
		return fatherRace;
	}
	
	private static StringBuilder descriptionSB = new StringBuilder();
	public String getBirthedDescriptionList() {
		descriptionSB.setLength(0);
		boolean foundChildren = false;
		
		if(getMotherRace()!=getFatherRace()) {
			if (getSonsFromMother() > 0) {
				descriptionSB.append(
						"<b>"+Util.intToString(getSonsFromMother())+ " </b><b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"
							+ (getSonsFromMother() > 1
								? getMotherRace().getOffspringMaleName()
								: getMotherRace().getOffspringMaleNameSingular())
						+ "</b>");
				foundChildren = true;
			}
			
			if (getSonsFromFather() > 0) {
				if(foundChildren) {
					if(getDaughtersFromMother()+getDaughtersFromFather() > 0) {
						descriptionSB.append(", ");
					} else {
						descriptionSB.append(" and ");
					}
				}
				descriptionSB.append(
						"<b>"+Util.intToString(getSonsFromFather())+ " </b><b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"
							+ (getSonsFromFather() > 1
								? getFatherRace().getOffspringMaleName()
								: getFatherRace().getOffspringMaleNameSingular())
						+ "</b>");
				foundChildren = true;
			}
			
		} else {
			if (getSonsFromMother()+getSonsFromFather() > 0) {
				descriptionSB.append(
						"<b>"+Util.intToString(getSonsFromMother()+getSonsFromFather())+ " </b><b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"
							+ (getSonsFromMother()+getSonsFromFather() > 1
								? getMotherRace().getOffspringMaleName()
								: getMotherRace().getOffspringMaleNameSingular())
						+ "</b>");
				foundChildren = true;
			}
		}
		
		if(getMotherRace()!=getFatherRace()) {
			if (getDaughtersFromMother() > 0) {
				if(foundChildren) {
					if(getDaughtersFromFather() > 0) {
						descriptionSB.append(", ");
					} else {
						descriptionSB.append(" and ");
					}
				}
				descriptionSB.append(
						"<b>"+Util.intToString(getDaughtersFromMother())+ " </b><b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>"
							+ (getDaughtersFromMother() > 1
								? getMotherRace().getOffspringFemaleName()
								: getMotherRace().getOffspringFemaleNameSingular())
						+ "</b>");
				foundChildren = true;
			}
			
			if (getDaughtersFromFather() > 0) {
				if(foundChildren) {
					descriptionSB.append(", and ");
				}
				descriptionSB.append(
						"<b>"+Util.intToString(getDaughtersFromFather())+ " </b><b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>"
							+ (getDaughtersFromFather() > 1
								? getFatherRace().getOffspringFemaleName()
								: getFatherRace().getOffspringFemaleNameSingular())
						+ "</b>");
			}
		} else {
			if (getDaughtersFromMother()+getDaughtersFromFather() > 0) {
				if(foundChildren) {
					descriptionSB.append(" and ");
				}
				descriptionSB.append(
						"<b>"+Util.intToString(getDaughtersFromMother()+getDaughtersFromFather())+ " </b><b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>"
							+ (getDaughtersFromMother()+getDaughtersFromFather() > 1
								? getMotherRace().getOffspringFemaleName()
								: getMotherRace().getOffspringFemaleNameSingular())
						+ "</b>");
			}
		}
		
		return descriptionSB.toString();
	}

}
