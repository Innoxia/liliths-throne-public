package com.lilithsthrone.game.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.62
 * @version 0.1.99
 * @author Innoxia
 */
public class Litter implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	private int dayOfConception, dayOfBirth;
	private String motherId, fatherId;
	private int sonsMother, daughtersMother, sonsFather, daughtersFather;
	private List<String> offspring;
	private Subspecies motherRace, fatherRace;

	public Litter(int dayOfConception, int dayOfBirth, GameCharacter mother, GameCharacter father, List<NPC> offspring) {
		this.dayOfConception = dayOfConception;
		this.dayOfBirth = dayOfBirth;
		
		motherId = mother.getId();
		fatherId = father.getId();
		motherRace = mother.getSubspecies();
		fatherRace = father.getSubspecies();
		
		this.offspring = new ArrayList<>();
		for(NPC npc : offspring) {
			this.offspring.add(npc.getId());
		}
		
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
	
	public Litter(int dayOfConception, int dayOfBirth, String motherId, String fatherId, int sonsMother, int daughtersMother, int sonsFather, int daughtersFather, List<String> offspring, Subspecies motherRace, Subspecies fatherRace) {
		this.dayOfConception = dayOfConception;
		this.dayOfBirth = dayOfBirth;
		this.motherId = motherId;
		this.fatherId = fatherId;
		this.sonsMother = sonsMother;
		this.daughtersMother = daughtersMother;
		this.sonsFather = sonsFather;
		this.daughtersFather = daughtersFather;
		this.offspring = offspring;
		this.motherRace = motherRace;
		this.fatherRace = fatherRace;
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("litter");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "dayOfConception", String.valueOf(this.getDayOfConception()));
		CharacterUtils.addAttribute(doc, element, "dayOfBirth", String.valueOf(this.getDayOfBirth()));
		CharacterUtils.addAttribute(doc, element, "motherId", this.getMotherId());
		CharacterUtils.addAttribute(doc, element, "fatherId", this.getFatherId());
		
		CharacterUtils.addAttribute(doc, element, "sonsMother", String.valueOf(this.getSonsFromMother()));
		CharacterUtils.addAttribute(doc, element, "daughtersMother", String.valueOf(this.getDaughtersFromMother()));
		CharacterUtils.addAttribute(doc, element, "sonsFather", String.valueOf(this.getSonsFromFather()));
		CharacterUtils.addAttribute(doc, element, "daughtersFather", String.valueOf(this.getDaughtersFromFather()));
		
		CharacterUtils.addAttribute(doc, element, "motherRace", String.valueOf(this.getMotherRace()));
		CharacterUtils.addAttribute(doc, element, "fatherRace", String.valueOf(this.getFatherRace()));
		
		Element innerElement = doc.createElement("offspringList");
		element.appendChild(innerElement);
		
		for(String offspring : this.getOffspring()) {

			element = doc.createElement("offspring");
			innerElement.appendChild(element);
			
			CharacterUtils.addAttribute(doc, element, "id", offspring);
		}
		
		return element;
	}
	
	public static Litter loadFromXML(Element parentElement, Document doc) {
		List<String> offspring = new ArrayList<>();
		
		Element element = (Element) parentElement.getElementsByTagName("offspringList").item(0);
		for(int i=0; i<element.getElementsByTagName("offspring").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("offspring").item(i));
			
			offspring.add(e.getAttribute("id"));
		}
		
		Subspecies motherRace = Subspecies.HUMAN;
		Subspecies fatherRace = Subspecies.HUMAN;
		try {
			motherRace = Subspecies.valueOf(parentElement.getAttribute("motherRace"));
			fatherRace = Subspecies.valueOf(parentElement.getAttribute("fatherRace"));
		} catch(Exception ex) {
		}
		return new Litter(
				Integer.valueOf(parentElement.getAttribute("dayOfConception")),
				Integer.valueOf(parentElement.getAttribute("dayOfBirth")),
				parentElement.getAttribute("motherId"),
				parentElement.getAttribute("fatherId"),
				Integer.valueOf(parentElement.getAttribute("sonsMother")),
				Integer.valueOf(parentElement.getAttribute("daughtersMother")),
				Integer.valueOf(parentElement.getAttribute("sonsFather")),
				Integer.valueOf(parentElement.getAttribute("daughtersFather")),
				offspring,
				motherRace,
				fatherRace);
	}

	public int getDayOfConception() {
		return dayOfConception;
	}

	public int getDayOfBirth() {
		return dayOfBirth;
	}
	
	public void setDayOfBirth(int dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	
	public String getMotherId() {
		return motherId;
	}

	public String getFatherId() {
		return fatherId;
	}

	public GameCharacter getMother() {
		return Main.game.getNPCById(motherId);
	}

	public GameCharacter getFather() {
		return Main.game.getNPCById(fatherId);
	}

	public List<String> getOffspring() {
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

	public Subspecies getMotherRace() {
		return motherRace;
	}

	public Subspecies getFatherRace() {
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
