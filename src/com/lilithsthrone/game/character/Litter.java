package com.lilithsthrone.game.character;

import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.AdvancedRelationship;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @since 0.1.62
 * @version 0.2.11
 * @author Innoxia
 */
public class Litter implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	private LocalDateTime conceptionDate;
	private LocalDateTime birthDate;
	
	private String motherId;
	private String fatherId;
	
	private int sonsMother;
	private int daughtersMother;
	private int sonsFather;
	private int daughtersFather;
	
	private List<String> offspring;
	
	private Subspecies motherRace;
	private Subspecies fatherRace;

	public Litter(LocalDateTime conceptionDate, LocalDateTime birthDate, GameCharacter mother, GameCharacter father, List<NPC> offspring) {
		this.conceptionDate = LocalDateTime.of(conceptionDate.getYear(), conceptionDate.getMonth(), conceptionDate.getDayOfMonth(), 12, 0);
		this.birthDate = LocalDateTime.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth(), 12, 0);
		
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
	
	public Litter(LocalDateTime conceptionDate, LocalDateTime birthDate,
			String motherId, String fatherId,
			int sonsMother, int daughtersMother, int sonsFather, int daughtersFather,
			List<String> offspring,
			Subspecies motherRace, Subspecies fatherRace) {

		this.conceptionDate = LocalDateTime.of(conceptionDate.getYear(), conceptionDate.getMonth(), conceptionDate.getDayOfMonth(), 12, 0);
		this.birthDate = LocalDateTime.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth(), 12, 0);
		
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

//		CharacterUtils.addAttribute(doc, element, "dayOfConception", String.valueOf(this.getDayOfConception()));
//		CharacterUtils.addAttribute(doc, element, "dayOfBirth", String.valueOf(this.getDayOfBirth()));

		CharacterUtils.createXMLElementWithValue(doc, element, "yearOfBirth", String.valueOf(this.getBirthDate().getYear()));
		CharacterUtils.createXMLElementWithValue(doc, element, "monthOfBirth", this.getBirthDate().getMonth().toString());
		CharacterUtils.createXMLElementWithValue(doc, element, "dayOfBirth", String.valueOf(this.getBirthDate().getDayOfMonth()));
		
		CharacterUtils.createXMLElementWithValue(doc, element, "yearOfConception", String.valueOf(this.getConceptionDate().getYear()));
		CharacterUtils.createXMLElementWithValue(doc, element, "monthOfConception", this.getConceptionDate().getMonth().toString());
		CharacterUtils.createXMLElementWithValue(doc, element, "dayOfConception", String.valueOf(this.getConceptionDate().getDayOfMonth()));
		
		
		
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
		NodeList offSpringList = element.getElementsByTagName("offspring");
		for(int i = 0; i < offSpringList.getLength(); i++){
			Element e = ((Element)offSpringList.item(i));
			
			offspring.add(e.getAttribute("id"));
		}
		
		Subspecies motherRace = Subspecies.HUMAN;
		Subspecies fatherRace = Subspecies.HUMAN;
		try {
			motherRace = Subspecies.valueOf(parentElement.getAttribute("motherRace"));
			fatherRace = Subspecies.valueOf(parentElement.getAttribute("fatherRace"));
		} catch(Exception ex) {
		}
		
		LocalDateTime loadedConceptionDate;
		LocalDateTime loadedBirthDate;
		
		if(parentElement.getElementsByTagName("dayOfConception").getLength()>0) {

			int day = Integer.valueOf(((Element)parentElement.getElementsByTagName("dayOfConception").item(0)).getAttribute("value"));
			Month month = Month.valueOf(((Element)parentElement.getElementsByTagName("monthOfConception").item(0)).getAttribute("value"));
			int year = Integer.valueOf(((Element)parentElement.getElementsByTagName("yearOfConception").item(0)).getAttribute("value"));
			
			loadedConceptionDate = LocalDateTime.of(year, month, day, 12, 0);
			

			day = Integer.valueOf(((Element)parentElement.getElementsByTagName("dayOfBirth").item(0)).getAttribute("value"));
			month = Month.valueOf(((Element)parentElement.getElementsByTagName("monthOfBirth").item(0)).getAttribute("value"));
			year = Integer.valueOf(((Element)parentElement.getElementsByTagName("yearOfBirth").item(0)).getAttribute("value"));
			
			loadedBirthDate = LocalDateTime.of(year, month, day, 12, 0);
			
		} else {
			int conceptionDay = Integer.valueOf(parentElement.getAttribute("dayOfConception"));
			loadedConceptionDate = Main.game.getStartingDate().plusDays(conceptionDay);
			
			int birthDay = Integer.valueOf(parentElement.getAttribute("dayOfBirth"));
			loadedBirthDate = Main.game.getStartingDate().plusDays(birthDay);
		}
		
		return new Litter(
				loadedConceptionDate,
				loadedBirthDate,
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

	public LocalDateTime getConceptionDate() {
		return conceptionDate;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getMotherId() {
		return motherId;
	}

	public String getFatherId() {
		return fatherId;
	}

	public GameCharacter getMother() {
		try {
			return Main.game.getNPCById(motherId);
		} catch (Exception e) {
			System.err.println("Main.game.getNPCById("+motherId+") returning null in method: Litter.getMother()");
			return Main.game.getGenericFemaleNPC();
		}
	}

	public boolean isMotherId(String motherId) {
		return this.motherId.equals(motherId);
	}

	public GameCharacter getFather() {
		try {
			return Main.game.getNPCById(fatherId);
		} catch (Exception e) {
			System.err.println("Main.game.getNPCById("+fatherId+") returning null in method: Litter.getFather()");
			return Main.game.getGenericMaleNPC();
		}
	}

	public boolean isFatherId(String fatherId) {
		return this.fatherId.equals(fatherId);
	}
	
	public List<String> getOffspring() {
		return offspring;
	}

	public Set<GameCharacter> getOffspringCharacters() {
		HashSet<GameCharacter> result = new HashSet<>();
		offspring.stream().map(x -> {
			try {
				return Main.game.getNPCById(x);
			} catch (Exception e) {
				return null;
			}
		}).filter(Objects::nonNull).forEach(result::add);
		return result;
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
								? getMotherRace().getOffspringSubspecies().getPluralMaleName(getMother())
								: getMotherRace().getOffspringSubspecies().getSingularMaleName(getMother()))
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
									? getFatherRace().getOffspringSubspecies().getPluralMaleName(getFather())
									: getFatherRace().getOffspringSubspecies().getSingularMaleName(getFather()))
						+ "</b>");
				foundChildren = true;
			}
			
		} else {
			if (getSonsFromMother()+getSonsFromFather() > 0) {
				descriptionSB.append(
						"<b>"+Util.intToString(getSonsFromMother()+getSonsFromFather())+ " </b><b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"
							+ (getSonsFromMother()+getSonsFromFather() > 1
									? getMotherRace().getOffspringSubspecies().getPluralMaleName(getMother())
									: getMotherRace().getOffspringSubspecies().getSingularMaleName(getMother()))
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
									? getMotherRace().getOffspringSubspecies().getPluralFemaleName(getMother())
									: getMotherRace().getOffspringSubspecies().getSingularFemaleName(getMother()))
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
									? getFatherRace().getOffspringSubspecies().getPluralFemaleName(getFather())
									: getFatherRace().getOffspringSubspecies().getSingularFemaleName(getFather()))
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
									? getMotherRace().getOffspringSubspecies().getPluralFemaleName(getMother())
									: getMotherRace().getOffspringSubspecies().getSingularFemaleName(getMother()))
						+ "</b>");
			}
		}

		// FIXME: this won't work when there are multiple children, even if they all have the same gender
		Set<PronounType> childrenPronouns = getOffspringCharacters().stream().map(x -> x.getGender().getType()).collect(Collectors.toSet());
		PronounType childrenPronoun = PronounType.NEUTRAL;
		if(childrenPronouns.size() == 1)
		    childrenPronoun = childrenPronouns.iterator().next();

		Set<AdvancedRelationship> relFather = Collections.emptySet();
		if(getFather() != null) {
			relFather = getOffspringCharacters().stream()
					.flatMap(x -> x.getAdvancedRelationshipTo(getFather()).stream())
					.collect(Collectors.toSet());
		}

		Set<AdvancedRelationship> relMother = Collections.emptySet();
		if(getFather() != null) {
			relMother = getOffspringCharacters().stream()
					.flatMap(x -> x.getAdvancedRelationshipTo(getMother()).stream())
					.collect(Collectors.toSet());
		}

		Set<AdvancedRelationship> relPlayer = Collections.emptySet();
		if(getFather() != null && !getFather().isPlayer() && getMother() != null && !getMother().isPlayer()) {
			relPlayer = getOffspringCharacters().stream()
					.flatMap(x -> x.getAdvancedRelationshipTo(Main.game.getPlayer()).stream())
					.collect(Collectors.toSet());
		}

		if(!relMother.isEmpty() || !relFather.isEmpty())
		{
			descriptionSB.append(" (");
			if(!relFather.isEmpty()) {
				if(getFather().isPlayer())
					descriptionSB.append("your ");
				else
					descriptionSB.append(getFather().getName() + "'s ");
				descriptionSB.append(GameCharacter.getAdvancedRelationshipStr(relFather, childrenPronoun));
				if(!relMother.isEmpty() || !relPlayer.isEmpty())
					descriptionSB.append(", ");
			}

			if(!relMother.isEmpty())
			{
				if(getMother().isPlayer())
					descriptionSB.append("your ");
				else
					descriptionSB.append(getMother().getName() + "'s ");
				descriptionSB.append(GameCharacter.getAdvancedRelationshipStr(relMother, childrenPronoun));
				if(!relPlayer.isEmpty())
					descriptionSB.append(", ");
			}

			if(!relPlayer.isEmpty())
			{
				descriptionSB.append("your ");
				descriptionSB.append(GameCharacter.getAdvancedRelationshipStr(relPlayer, childrenPronoun));
			}
			descriptionSB.append(")");
		}

		return descriptionSB.toString();
	}

}
