package com.lilithsthrone.game.character.pregnancy;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.Map.Entry;

/**
 * @since 0.1.62
 * @version 0.4.7.1
 * @author Innoxia, orvail
 */
public class Litter implements XMLSaving {
	
	private String id;
	
	private LocalDateTime conceptionDate;
	private LocalDateTime birthDate;
	private LocalDateTime incubationStartDate;
	
	private String motherId;
	private String fatherId;
	private String incubatorId; // For if this litter was eggs incubated in a third party
	
	private int sonsMother;
	private int daughtersMother;
	private int sonsFather;
	private int daughtersFather;
	
	private FertilisationType fertilisationType;
	
	private List<String> offspring;
	
	private String birthedDescription;
	
	private AbstractSubspecies motherRace;
	private AbstractSubspecies fatherRace;

	public Litter(LocalDateTime conceptionDate, LocalDateTime birthDate, GameCharacter mother, GameCharacter father, FertilisationType fertilisationType, List<OffspringSeed> offspring) {
		this.id = mother.getId()+mother.getLittersGenerated();
		
		this.conceptionDate = LocalDateTime.of(conceptionDate.getYear(), conceptionDate.getMonth(), conceptionDate.getDayOfMonth(), 12, 0);
		this.birthDate = LocalDateTime.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth(), 12, 0);
		this.incubationStartDate = null;
		
		this.fertilisationType = fertilisationType;
		
		motherId = mother.getId();
		motherRace = mother.getSubspecies();
		if(father!=null) {
			fatherId = father.getId();
			fatherRace = father.getSubspecies();
		} else {
			fatherId = "";
			fatherRace = null;
		}
		incubatorId = "";

		sonsMother = 0;
		daughtersMother = 0;
		sonsFather = 0;
		daughtersFather = 0;
		this.offspring = new ArrayList<>();
		
		for(OffspringSeed os : offspring) {
			this.offspring.add(os.getId());
			if(os.isFeminine()) {
				if(os.isTakesAfterMother()) {
					daughtersMother++;
				} else {
					daughtersFather++;
				}
			} else {
				if(os.isTakesAfterMother()) {
					sonsMother++;
				} else {
					sonsFather++;
				}
			}
		}
		
		generateBirthedDescription();
	}
	
	public Litter(String id,
			LocalDateTime conceptionDate,
			LocalDateTime birthDate,
			String motherId, String fatherId,
			FertilisationType fertilisationType,
			int sonsMother, int daughtersMother,
			int sonsFather, int daughtersFather,
			List<String> offspring,
			AbstractSubspecies motherRace, AbstractSubspecies fatherRace,
			String birthedDescription) {
		this.id = id;

		this.conceptionDate = LocalDateTime.of(conceptionDate.getYear(), conceptionDate.getMonth(), conceptionDate.getDayOfMonth(), 12, 0);
		this.birthDate = LocalDateTime.of(birthDate.getYear(), birthDate.getMonth(), birthDate.getDayOfMonth(), 12, 0);
		this.incubationStartDate = null;
		
		this.motherId = motherId;
		this.fatherId = fatherId;
		incubatorId = "";

		this.fertilisationType = fertilisationType;
		
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

		Element idElement = doc.createElement("id");
		element.appendChild(idElement);
		idElement.setTextContent(id);

		XMLUtil.createXMLElementWithValue(doc, element, "yearOfBirth", String.valueOf(this.getBirthDate().getYear()));
		XMLUtil.createXMLElementWithValue(doc, element, "monthOfBirth", this.getBirthDate().getMonth().toString());
		XMLUtil.createXMLElementWithValue(doc, element, "dayOfBirth", String.valueOf(this.getBirthDate().getDayOfMonth()));
		
		XMLUtil.createXMLElementWithValue(doc, element, "yearOfConception", String.valueOf(this.getConceptionDate().getYear()));
		XMLUtil.createXMLElementWithValue(doc, element, "monthOfConception", this.getConceptionDate().getMonth().toString());
		XMLUtil.createXMLElementWithValue(doc, element, "dayOfConception", String.valueOf(this.getConceptionDate().getDayOfMonth()));
		
		if(this.getIncubationStartDate()!=null) {
			XMLUtil.createXMLElementWithValue(doc, element, "yearOfIncubationStart", String.valueOf(this.getIncubationStartDate().getYear()));
			XMLUtil.createXMLElementWithValue(doc, element, "monthOfIncubationStart", this.getIncubationStartDate().getMonth().toString());
			XMLUtil.createXMLElementWithValue(doc, element, "dayOfIncubationStart", String.valueOf(this.getIncubationStartDate().getDayOfMonth()));
		}
		
		XMLUtil.addAttribute(doc, element, "motherId", this.getMotherId());
		XMLUtil.addAttribute(doc, element, "fatherId", this.getFatherId());
		XMLUtil.addAttribute(doc, element, "incubatorId", this.getIncubatorId());

		XMLUtil.addAttribute(doc, element, "fertilisationType", this.getFertilisationType().toString());
		
		XMLUtil.addAttribute(doc, element, "sonsMother", String.valueOf(this.getSonsFromMother()));
		XMLUtil.addAttribute(doc, element, "daughtersMother", String.valueOf(this.getDaughtersFromMother()));
		XMLUtil.addAttribute(doc, element, "sonsFather", String.valueOf(this.getSonsFromFather()));
		XMLUtil.addAttribute(doc, element, "daughtersFather", String.valueOf(this.getDaughtersFromFather()));
		
		XMLUtil.addAttribute(doc, element, "motherRace", Subspecies.getIdFromSubspecies(this.getMotherRace()));
		XMLUtil.addAttribute(doc, element, "fatherRace", Subspecies.getIdFromSubspecies(this.getFatherRace()));

		XMLUtil.addAttribute(doc, element, "birthedDescription", this.getBirthedDescription());
		
		
		Element innerElement = doc.createElement("offspringList");
		element.appendChild(innerElement);
		
		for(String offspring : this.getOffspring()) {

			element = doc.createElement("offspring");
			innerElement.appendChild(element);
			
			XMLUtil.addAttribute(doc, element, "id", offspring);
		}
		
		return element;
	}
	
	public static Litter loadFromXML(Element parentElement, Document doc) {
		String loadedId = "";

		if(parentElement.getElementsByTagName("id").item(0)!=null) {
			loadedId = parentElement.getElementsByTagName("id").item(0).getTextContent();
		}
		
		Element element = (Element) parentElement.getElementsByTagName("offspringList").item(0);
		List<String> offspring = new ArrayList<>();
		NodeList offSpringList = element.getElementsByTagName("offspring");
		for(int i = 0; i < offSpringList.getLength(); i++){
			Element e = ((Element)offSpringList.item(i));
			
			offspring.add(e.getAttribute("id"));
		}
		
		AbstractSubspecies motherRace = Subspecies.HUMAN;
		AbstractSubspecies fatherRace = Subspecies.HUMAN;
		try {
			motherRace = Subspecies.getSubspeciesFromId(parentElement.getAttribute("motherRace"));
			fatherRace = Subspecies.getSubspeciesFromId(parentElement.getAttribute("fatherRace"));
		} catch(Exception ex) {
		}
		
		LocalDateTime loadedConceptionDate;
		LocalDateTime loadedBirthDate;
		LocalDateTime loadedIncubationStartDate = null;
		
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

		if(parentElement.getElementsByTagName("dayOfIncubationStart").getLength()>0) {
			int day = Integer.valueOf(((Element)parentElement.getElementsByTagName("dayOfIncubationStart").item(0)).getAttribute("value"));
			Month month = Month.valueOf(((Element)parentElement.getElementsByTagName("monthOfIncubationStart").item(0)).getAttribute("value"));
			int year = Integer.valueOf(((Element)parentElement.getElementsByTagName("yearOfIncubationStart").item(0)).getAttribute("value"));
			
			loadedIncubationStartDate = LocalDateTime.of(year, month, day, 12, 0);
		}
		
		
		String birthedDescription = "";
		try {
			birthedDescription = parentElement.getAttribute("birthedDescription");
		} catch(Exception ex) {
		}
		
		Litter litter = new Litter(loadedId,
				loadedConceptionDate,
				loadedBirthDate,
				parentElement.getAttribute("motherId"),
				parentElement.getAttribute("fatherId"),
				FertilisationType.NORMAL,
				Integer.valueOf(parentElement.getAttribute("sonsMother")),
				Integer.valueOf(parentElement.getAttribute("daughtersMother")),
				Integer.valueOf(parentElement.getAttribute("sonsFather")),
				Integer.valueOf(parentElement.getAttribute("daughtersFather")),
				offspring,
				motherRace,
				fatherRace,
				birthedDescription);
		
		if(parentElement.hasAttribute("incubatorId")) {
			litter.setIncubatorId(parentElement.getAttribute("incubatorId"));
		}

		if(parentElement.hasAttribute("fertilisationType")) {
			litter.setFertilisationType(FertilisationType.valueOf(parentElement.getAttribute("fertilisationType")));
		}

		if(loadedIncubationStartDate!=null) {
			litter.setIncubationStartDate(loadedIncubationStartDate);
		}
		
		return litter;
	}

	public String getId() {
		return id;
	}

	public boolean isSelfImpregnation() {
		return this.getMotherId().equals(this.getFatherId());
	}
	
	public LocalDateTime getConceptionDate() {
		return conceptionDate;
	}

	public LocalDateTime getIncubationStartDate() {
		return incubationStartDate;
	}
	
	public void setIncubationStartDate(LocalDateTime incubationStartDate) {
		this.incubationStartDate = incubationStartDate;
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

	public String getIncubatorId() {
		return incubatorId;
	}

	public void setIncubatorId(String incubatorId) {
		this.incubatorId = incubatorId;
	}
	
	public GameCharacter getIncubator() {
		try {
			return Main.game.getNPCById(incubatorId);
		} catch (Exception e) {
//			if(!incubatorId.equals("NOT_SET")) {
//				Util.logGetNpcByIdError("Litter.incubatorId()", incubatorId);
//			}
//			return Main.game.getNpc(GenericFemaleNPC.class);
			return null;
		}
	}
	
	public GameCharacter getMother() {
		try {
			return Main.game.getNPCById(motherId);
		} catch (Exception e) {
//			if(!motherId.equals("NOT_SET")) {
//				Util.logGetNpcByIdError("Litter.getMother()", motherId);
//			}
//			return Main.game.getNpc(GenericFemaleNPC.class);
			return null;
		}
	}

	public boolean isMotherId(String motherId) {
		return this.motherId.equals(motherId);
	}

	public GameCharacter getFather() {
		try {
			return Main.game.getNPCById(fatherId);
		} catch (Exception e) {
			return null;
		}
	}

	public String getFatherName() {
		if(getFather()==null) {
			return "someone";
		}
		return getFather().getName();
	}
	
	
	public boolean isFatherId(String fatherId) {
		return this.fatherId.equals(fatherId);
	}
	
	public FertilisationType getFertilisationType() {
		return fertilisationType;
	}

	public void setFertilisationType(FertilisationType fertilisationType) {
		this.fertilisationType = fertilisationType;
	}
	
	/**
	 * This is currently unused, as usually offspring may be both GameCharacter and OffspringSeed classes.
	 * To access all offspring, use getOffspring which returns the Id of the offspring as a string, and then check which one it is.
	 */
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
	
	/**
	 * This method can only be used on Pregnant litters, as only in this case will all offspring be of the OffspringSeed class.
	 * To access all offspring for other litters, use getOffspring which returns the Id of the offspring as a string. It can be
	 * both GameCharacter and OffspringSeed classes, so check which one it is in those cases.
	 */
	public Set<OffspringSeed> getOffspringSeed() {
		HashSet<OffspringSeed> result = new HashSet<>();
		offspring.stream().map(x -> {
			try {
				return Main.game.getOffspringSeedById(x);
			} catch (Exception e) {
				return null;
			}
		}).filter(Objects::nonNull).forEach(result::add);
		return result;
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

	public AbstractSubspecies getMotherRace() {
		return motherRace;
	}

	public AbstractSubspecies getFatherRace() {
		return fatherRace;
	}

	public void setFatherRace(AbstractSubspecies fatherSubspecies) {
		fatherRace = fatherSubspecies;
	}
	
	public void generateBirthedDescription() {
		Map<String, Integer> sons = new HashMap<>();
		Map<String, Integer> feralSons = new HashMap<>();
		Map<String, Integer> daughters = new HashMap<>();
		Map<String, Integer> feralDaughters = new HashMap<>();
		String feralString = "<b style='color:" + RaceStage.FERAL.getColour().toWebHexString() + ";'>" + RaceStage.FERAL.getName() + "</b>";
		
		for(String id : this.getOffspring()) {
			try {
				
				OffspringSeed offspring = Main.game.getOffspringSeedById(id);
				AbstractSubspecies subspecies = offspring.getSubspecies();
				if(offspring.isFeminine()) {
					String nameId = subspecies.getSingularFemaleName(offspring.getBody())+"|"+subspecies.getPluralFemaleName(offspring.getBody());
					if(offspring.isFeral()) {
						feralDaughters.putIfAbsent(nameId, 0);
						feralDaughters.put(nameId, feralDaughters.get(nameId)+1);
					} else {
						daughters.putIfAbsent(nameId, 0);
						daughters.put(nameId, daughters.get(nameId)+1);
					}
				} else {
					String nameId = subspecies.getSingularMaleName(offspring.getBody())+"|"+subspecies.getPluralMaleName(offspring.getBody());
					if(offspring.isFeral()) {
						feralSons.putIfAbsent(nameId, 0);
						feralSons.put(nameId, feralSons.get(nameId)+1);
					} else {
						sons.putIfAbsent(nameId, 0);
						sons.put(nameId, sons.get(nameId)+1);
					}
				}
			} catch (Exception e) {
			}
		}
		
		List<String> entries = new ArrayList<>();
		
		for(Entry<String, Integer> entry : sons.entrySet()) {
			entries.add("<b>"+Util.intToString(entry.getValue())+"</b> <b style='color:"+ PresetColour.MASCULINE.toWebHexString()+ ";'>"
						+(entry.getValue() > 1
							? entry.getKey().split("\\|")[1]
							: entry.getKey().split("\\|")[0])
						+"</b>");
		}
		for(Entry<String, Integer> entry : feralSons.entrySet()) {
			entries.add("<b>"+Util.intToString(entry.getValue())+"</b> "+feralString+" <b style='color:"+ PresetColour.MASCULINE.toWebHexString()+ ";'>"
						+(entry.getValue() > 1
							? entry.getKey().split("\\|")[1]
							: entry.getKey().split("\\|")[0])
						+"</b>");
		}
		for(Entry<String, Integer> entry : daughters.entrySet()) {
			entries.add("<b>"+Util.intToString(entry.getValue())+"</b> <b style='color:"+ PresetColour.FEMININE.toWebHexString()+ ";'>"
						+(entry.getValue() > 1
							? entry.getKey().split("\\|")[1]
							: entry.getKey().split("\\|")[0])
						+"</b>");
		}
		for(Entry<String, Integer> entry : feralDaughters.entrySet()) {
			entries.add("<b>"+Util.intToString(entry.getValue())+"</b> "+feralString+" <b style='color:"+ PresetColour.FEMININE.toWebHexString()+ ";'>"
						+(entry.getValue() > 1
							? entry.getKey().split("\\|")[1]
							: entry.getKey().split("\\|")[0])
						+"</b>");
		}

		if(!entries.isEmpty()) {
			birthedDescription = Util.stringsToStringList(entries, false);
		}
	}
	
	//TODO Add this to the generateBirthedDescription() method above.
//	private String getRelationshipInformation() {
//		StringBuilder descriptionSB = new StringBuilder();
//
//		// FIXME: this won't work when there are multiple children, even if they all have the same gender
//		Set<PronounType> childrenPronouns = getOffspringCharacters().stream().map(x -> x.getGender().getType()).collect(Collectors.toSet());
//		PronounType childrenPronoun = PronounType.NEUTRAL;
//		if(childrenPronouns.size() == 1) {
//		    childrenPronoun = childrenPronouns.iterator().next();
//		}
//
//		Set<Relationship> relFather = Collections.emptySet();
//		if(getFather() != null) {
//			relFather = getOffspringCharacters().stream()
//					.flatMap(x -> x.getRelationshipsTo(getFather()).stream())
//					.collect(Collectors.toSet());
//		}
//
//		Set<Relationship> relMother = Collections.emptySet();
//		if(getFather() != null) {
//			relMother = getOffspringCharacters().stream()
//					.flatMap(x -> x.getRelationshipsTo(getMother()).stream())
//					.collect(Collectors.toSet());
//		}
//
//		Set<Relationship> relPlayer = Collections.emptySet();
//		if(getFather() != null && !getFather().isPlayer() && getMother() != null && !getMother().isPlayer()) {
//			relPlayer = getOffspringCharacters().stream()
//					.flatMap(x -> x.getRelationshipsTo(Main.game.getPlayer()).stream())
//					.collect(Collectors.toSet());
//		}
//
//		if(!relMother.isEmpty() || !relFather.isEmpty()) {
//			descriptionSB.append(" (");
//			if(!relFather.isEmpty()) {
//				if(getFather().isPlayer()) {
//					descriptionSB.append("your ");
//				} else {
//					descriptionSB.append(getFather().getName(true) + "'s ");
//				}
//				descriptionSB.append(GameCharacter.getRelationshipStr(relFather, childrenPronoun));
//				if(!relMother.isEmpty() || !relPlayer.isEmpty()) {
//					descriptionSB.append(", ");
//				}
//			}
//
//			if(!relMother.isEmpty()) {
//				if(getMother().isPlayer()) {
//					descriptionSB.append("your ");
//				} else {
//					descriptionSB.append(getMother().getName(true) + "'s ");
//				}
//				descriptionSB.append(GameCharacter.getRelationshipStr(relMother, childrenPronoun));
//				if(!relPlayer.isEmpty())
//					descriptionSB.append(", ");
//			}
//
//			if(!relPlayer.isEmpty()) {
//				descriptionSB.append("your ");
//				descriptionSB.append(GameCharacter.getRelationshipStr(relPlayer, childrenPronoun));
//			}
//			descriptionSB.append(")");
//		}
//		return descriptionSB.toString();
//	}

	public String getBirthedDescription() {
		if(birthedDescription==null || birthedDescription.isEmpty()) {
			generateBirthedDescription();
		}
		return birthedDescription;
	}

}
