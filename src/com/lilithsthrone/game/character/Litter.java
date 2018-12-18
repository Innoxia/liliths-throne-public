package com.lilithsthrone.game.character;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

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
	
	private String birthedDescription;
	
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
		
		generateBirthedDescription();
	}
	
	public Litter(LocalDateTime conceptionDate, LocalDateTime birthDate,
			String motherId, String fatherId,
			int sonsMother, int daughtersMother, int sonsFather, int daughtersFather,
			List<String> offspring,
			Subspecies motherRace, Subspecies fatherRace, String birthedDescription) {

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
		
		this.birthedDescription = birthedDescription;
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

		CharacterUtils.addAttribute(doc, element, "birthedDescription", this.getBirthedDescription());
		
		
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
		
		String birthedDescription = "";
		try {
			birthedDescription = parentElement.getAttribute("birthedDescription");
		} catch(Exception ex) {
		}
		
		Litter litter = new Litter(
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
				fatherRace,
				birthedDescription);
		
//		if(birthedDescription.isEmpty()) {
//			litter.generateBirthedDescription();
//		}
		
		return litter;
	}

	public boolean isSelfImpregnation() {
		return this.getMotherId().equals(this.getFatherId());
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
			Util.logGetNpcByIdError("Litter.getMother()", motherId);
			return Main.game.getNpc(GenericFemaleNPC.class);
		}
	}

	public boolean isMotherId(String motherId) {
		return this.motherId.equals(motherId);
	}

	public GameCharacter getFather() {
		try {
			return Main.game.getNPCById(fatherId);
		} catch (Exception e) {
			Util.logGetNpcByIdError("Litter.getFather()", fatherId);
			return Main.game.getNpc(GenericMaleNPC.class);
		}
	}

	public boolean isFatherId(String fatherId) {
		return this.fatherId.equals(fatherId);
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
	
	private void generateBirthedDescription() {
		Map<String, Integer> sons = new HashMap<>();
		Map<String, Integer> daughters = new HashMap<>();
		
		for(String id : this.getOffspring()) {
			try {
				GameCharacter character = Main.game.getNPCById(id);
				Subspecies subspecies = character.getSubspecies();
				if(Main.game.getNPCById(id).isFeminine()) {
					String nameId = subspecies.getSingularMaleName(character)+"|"+subspecies.getPluralMaleName(character);
					sons.putIfAbsent(nameId, 0);
					sons.put(nameId, sons.get(nameId)+1);
				} else {
					String nameId = subspecies.getSingularFemaleName(character)+"|"+subspecies.getPluralFemaleName(character);
					daughters.putIfAbsent(nameId, 0);
					daughters.put(nameId, daughters.get(nameId)+1);
				}
			} catch (Exception e) {
			}
		}
		
		List<String> entries = new ArrayList<>();
		
		for(Entry<String, Integer> entry : sons.entrySet()) {
			entries.add("<b>"+Util.intToString(entry.getValue())+"</b> <b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>"
						+(entry.getValue() > 1
							? entry.getKey().split("\\|")[1]
							: entry.getKey().split("\\|")[0])
						+"</b>");
		}
		for(Entry<String, Integer> entry : daughters.entrySet()) {
			entries.add("<b>"+Util.intToString(entry.getValue())+"</b> <b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>"
						+(entry.getValue() > 1
							? entry.getKey().split("\\|")[1]
							: entry.getKey().split("\\|")[0])
						+"</b>");
		}
		
		birthedDescription = Util.stringsToStringList(entries, false);
	}

	public String getBirthedDescription() {
		if(birthedDescription.isEmpty()) {
			generateBirthedDescription();
		}
		return birthedDescription;
	}

}
