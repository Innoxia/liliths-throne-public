package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.Litter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.*;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.offspring.GenericOffspringDialogue;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

import static com.lilithsthrone.game.character.persona.Name.surnames;

/**
 * @since 0.4.0
 * @version 0.4.0
 * @author Innoxia, AceXp
 */
public class OffspringSeed implements XMLSaving {
	
	// Core variables:
	protected String id;
	protected Boolean fromPlayer;
	protected Boolean born;
	protected NameTriplet nameTriplet;
	protected String surname;
	protected String description;
	protected LocalDateTime birthday;
	
	// Body:
	protected AbstractSubspecies subspecies;
	protected Body body;
	
	// Family:
	protected String motherId;
	protected String fatherId;
	protected String incubatorId;
	protected LocalDateTime conceptionDate;
	// Used as a backup for when motherId is pointing to a null NPC:
	protected String motherName = "???";
	protected Femininity motherFemininity = Femininity.FEMININE;
	protected AbstractSubspecies motherSubspecies = Subspecies.HUMAN;
	// Used as a backup for when fatherId is pointing to a null NPC:
	protected String fatherName = "???";
	protected Femininity fatherFemininity = Femininity.MASCULINE;
	protected AbstractSubspecies fatherSubspecies = Subspecies.HUMAN;
	// Used as a backup for when incubatorId is pointing to a null NPC:
	protected String incubatorName = "???";
	protected Femininity incubatorFemininity = Femininity.ANDROGYNOUS;
	protected AbstractSubspecies incubatorSubspecies = Subspecies.HUMAN;
	
	public OffspringSeed() {
	}

	public OffspringSeed(NPC npc) {
		this.fromPlayer = (npc.getMother()!=null && npc.getMother().isPlayer() ||
				          (npc.getFather()!=null && npc.getFather().isPlayer()) ||
						  (npc.getIncubator()!=null && npc.getIncubator().isPlayer()));
		this.born = false;
		this.nameTriplet = npc.getNameTriplet();
		this.surname = npc.getSurname();
		this.description = npc.getDescription();
		this.birthday = npc.getBirthday();
		this.subspecies = npc.getSubspecies();
		this.body = npc.getBody();
		this.motherId = npc.getMotherId();
		this.fatherId = npc.getFatherId();
		this.incubatorId = npc.getIncubatorId();
		this.conceptionDate = npc.getConceptionDate();
		this.motherName = npc.getMotherName();
		this.motherFemininity = npc.getMotherFemininity();
		this.motherSubspecies = npc.getMotherSubspecies();
		this.fatherName = npc.getFatherName();
		this.fatherFemininity = npc.getFatherFemininity();
		this.fatherSubspecies = npc.getFatherSubspecies();
		this.incubatorName = npc.getIncubatorName();
		this.incubatorFemininity = npc.getIncubatorFemininity();
		this.incubatorSubspecies = npc.getIncubatorSubspecies();
		
		try {
			boolean carried = false;
			String osId = Main.game.addOffspringSeed(this, false);
			
			if(npc.getMother()!=null && npc.getMother().getPregnantLitter()!=null && npc.getMother().getPregnantLitter().getOffspring().contains(npc.getId())) {
				carried = true;
			} else if(npc.getIncubator()!=null) {
				for (Map.Entry<SexAreaOrifice, Litter> entry : npc.getIncubator().getIncubatingLitters().entrySet()) {
					Litter litter = entry.getValue();
					if (litter != null && litter.getOffspring().contains(npc.getId())) {
						carried = true;
						break;
					}
				}
			}
			this.born = !carried;
			
			if(npc.getMother()!=null) {
				npc.getMother().swapLitters(npc.getId(), osId);
			}
			if(npc.getFather()!=null){
				npc.getFather().swapLitters(npc.getId(), osId);
			}
			if(npc.getIncubator()!=null){
				npc.getIncubator().swapLitters(npc.getId(), osId);
			}
			
			// Too slow to print
//			if(Main.game.isDebugMode()) {
//				System.out.println("Converted NPC "+npc.getId()+" to offspringSeed "+osId);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Main.game.getOffspring().remove(npc);
		Main.game.removeNPC(npc);
	}
	
	public OffspringSeed(GameCharacter mother, GameCharacter father) {
		this(mother, father, father.getTrueSubspecies(), father.getHalfDemonSubspecies());
	}
	
	public OffspringSeed(GameCharacter mother, GameCharacter father, AbstractSubspecies fatherSubspecies, AbstractSubspecies fatherHalfDemonSubspecies) {
		
		this.fromPlayer = (mother.isPlayer() || (father!=null && father.isPlayer()));
		this.born = false;
		
		GenericAndrogynousNPC template = new GenericAndrogynousNPC();
		
		if(mother.getTrueSubspecies()==Subspecies.LILIN || mother.getTrueSubspecies()==Subspecies.ELDER_LILIN) {
			this.setSurname(mother.getName(false)+"martuilani");
			
		} else if(father!=null && (father.getTrueSubspecies()==Subspecies.LILIN || father.getTrueSubspecies()==Subspecies.ELDER_LILIN)) {
			this.setSurname(father.getName(false)+"martuilani");

		} else if(mother.getMother()!=null && (mother.getMother().getTrueSubspecies()==Subspecies.LILIN || mother.getMother().getTrueSubspecies()==Subspecies.ELDER_LILIN)) {
			this.setSurname(mother.getMother().getName(false)+"martu");

		} else if(mother.getSurname()!=null && !mother.getSurname().isEmpty()) {
			this.setSurname(mother.getSurname());
			
		} else {
			this.setSurname(""); // To make sure that surname is not null for the following check: this.surname.contains("martu")
		}

		Gender gender = Gender.getGenderFromUserPreferences(false, false);
		
		Body preGeneratedBody;
		if(father!=null) {
			preGeneratedBody = AbstractSubspecies.getPreGeneratedBody(template, gender, mother, father);
		} else {
			preGeneratedBody = AbstractSubspecies.getPreGeneratedBody(template, gender, mother.getTrueSubspecies(), mother.getHalfDemonSubspecies(), fatherSubspecies, fatherHalfDemonSubspecies);
		}
		if(preGeneratedBody!=null) {
			setBody(preGeneratedBody);
		} else {
			this.body = Main.game.getCharacterUtils().generateBody(template, gender, mother, father);
		}

		AbstractRace race;
		if(this.body.getBodyMaterial()==BodyMaterial.SLIME) {
        	race = Race.SLIME;
		} else {
			race = this.body.getRaceFromPartWeighting();
		}
		this.subspecies = AbstractSubspecies.getSubspeciesFromBody(this.body, race);

		//For Imps, don't use any of the demon surnames but just a regular surname
		if (this.surname.contains("martu") && (this.subspecies==Subspecies.IMP || this.subspecies==Subspecies.IMP_ALPHA)) {
			this.setSurname(surnames[Util.random.nextInt(surnames.length)]);
		}

		setName(Name.getRandomTriplet(getRace()));
		
		this.setMother(mother);
		
		if(father!=null) {
			this.setFather(father);
		}
		
		this.setConceptionDate(Main.game.getDateNow());
	}
		
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element offspringSeedData = doc.createElement("data");
		parentElement.appendChild(offspringSeedData);
		
		XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "id", this.getId());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "fromPlayer", this.isFromPlayer().toString());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "born", this.isBorn().toString());
		Element name = doc.createElement("name");
		offspringSeedData.appendChild(name);
		XMLUtil.addAttribute(doc, name, "nameFeminine", this.getNameTriplet().getFeminine());
		XMLUtil.addAttribute(doc, name, "nameAndrogynous", this.getNameTriplet().getAndrogynous());
		XMLUtil.addAttribute(doc, name, "nameMasculine", this.getNameTriplet().getMasculine());
		
		XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "surname", this.getSurname());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "description", this.getDescription());
		if(birthday!=null) {
			XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "yearOfBirth", String.valueOf(this.getBirthday().getYear()));
			XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "monthOfBirth", String.valueOf(this.getBirthday().getMonth()));
			XMLUtil.createXMLElementWithValue(doc, offspringSeedData, "dayOfBirth", String.valueOf(this.getBirthday().getDayOfMonth()));
		}
		
		// ************** Body **************//
		
		Element offspringSeedBody = doc.createElement("body");
		parentElement.appendChild(offspringSeedBody);

		XMLUtil.addAttribute(doc, offspringSeedBody, "subspecies", Subspecies.getIdFromSubspecies(this.subspecies));
		this.body.saveAsXML(offspringSeedBody, doc);
		
		// ************** Family **************//
		
		Element offspringSeedFamily = doc.createElement("family");
		parentElement.appendChild(offspringSeedFamily);
		
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "motherId", this.getMotherId());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "motherName", this.getMotherName());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "motherFemininity", this.getMotherFemininity().toString());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "motherSubspecies", Subspecies.getIdFromSubspecies(this.getMotherSubspecies()));
		
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "fatherId", this.getFatherId());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "fatherName", this.getFatherName());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "fatherFemininity", this.getFatherFemininity().toString());
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "fatherSubspecies", Subspecies.getIdFromSubspecies(this.getFatherSubspecies()));
		if(incubatorId!=null) {
			XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "incubatorId", this.getIncubatorId());
			XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "incubatorName", this.getIncubatorName());
			XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "incubatorFemininity", this.getIncubatorFemininity().toString());
			XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "incubatorSubspecies", Subspecies.getIdFromSubspecies(this.getIncubatorSubspecies()));
		}
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "yearOfConception", String.valueOf(this.getConceptionDate().getYear()));
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "monthOfConception", String.valueOf(this.getConceptionDate().getMonth()));
		XMLUtil.createXMLElementWithValue(doc, offspringSeedFamily, "dayOfConception", String.valueOf(this.getConceptionDate().getDayOfMonth()));

		return parentElement;
	}
	
	public static OffspringSeed loadFromXML(Element parentElement, Document doc) {
		StringBuilder sb = new StringBuilder();
		
		OffspringSeed os = new OffspringSeed();
		
		NodeList nodes = parentElement.getElementsByTagName("data");
		Element element = (Element) nodes.item(0);
		
		String loadedCharacterId = getValueFromElementWithTagName(element, "id");
		if (loadedCharacterId != null) {
			os.setId(loadedCharacterId);
		}
		os.setFromPlayer(getValueFromElementWithTagName(element, "fromPlayer").equals("true"));
		os.setBorn(getValueFromElementWithTagName(element, "born").equals("true"));
		
		// Name:
		Element nameElement = (Element) element.getElementsByTagName("name").item(0);
		String nameElementValue = nameElement.getAttribute("value");
		if (!nameElementValue.isEmpty()) {
			os.setName(new NameTriplet(nameElementValue));
		} else {
			String nameMasculine = nameElement.getAttribute("nameMasculine");
			String nameAndrogynous = nameElement.getAttribute("nameAndrogynous");
			String nameFeminine = nameElement.getAttribute("nameFeminine");
			NameTriplet backup = Name.getRandomTriplet(Race.HUMAN);
			os.setName(new NameTriplet(
					nameMasculine.isEmpty()?backup.getMasculine():nameMasculine,
					nameAndrogynous.isEmpty()?backup.getAndrogynous():nameAndrogynous,
					nameFeminine.isEmpty()?backup.getFeminine():nameFeminine));
		}
		
		// Surname:
		if(element.getElementsByTagName("surname")!=null && element.getElementsByTagName("surname").getLength()>0) {
			String surname = ((Element)element.getElementsByTagName("surname").item(0)).getAttribute("value");
			if(surname!=null && !surname.isEmpty()) {
				os.setSurname(surname);
			}
		}
		
		if(element.getElementsByTagName("description").getLength()!=0) {
			os.setDescription(((Element)element.getElementsByTagName("description").item(0)).getAttribute("value"));
		}
		
		// Birthday:
		try {
			int day = Integer.parseInt(((Element)element.getElementsByTagName("dayOfBirth").item(0)).getAttribute("value"));
			Month month = Month.valueOf(((Element)element.getElementsByTagName("monthOfBirth").item(0)).getAttribute("value"));
			int year = Integer.parseInt(((Element)element.getElementsByTagName("yearOfBirth").item(0)).getAttribute("value"));
			
			os.setBirthday(LocalDateTime.of(year, month, (month==Month.FEBRUARY&&day==29?28:day), 12, 0));
			
		} catch(Exception ex) {
		}
		
		// ************** Body **************//
		element = (Element) parentElement.getElementsByTagName("body").item(0);
		os.subspecies = Subspecies.getSubspeciesFromId(element.getAttribute("subspecies"));

		os.body = Body.loadFromXML(sb, (Element) parentElement.getElementsByTagName("body").item(0), doc);
		os.body.calculateRace(null);
		
		// ************** Family **************//
		
		nodes = parentElement.getElementsByTagName("family");
		Element familyElement = (Element) nodes.item(0);
		if(familyElement!=null) {
			os.setMother(((Element)familyElement.getElementsByTagName("motherId").item(0)).getAttribute("value"));
			try {
				os.motherName = (((Element)familyElement.getElementsByTagName("motherName").item(0)).getAttribute("value"));
				os.motherFemininity = Femininity.valueOf(((Element)familyElement.getElementsByTagName("motherFemininity").item(0)).getAttribute("value"));
				os.motherSubspecies = Subspecies.getSubspeciesFromId(((Element)familyElement.getElementsByTagName("motherSubspecies").item(0)).getAttribute("value"));
			} catch(Exception ex) {
			}
			
			os.setFather(((Element)familyElement.getElementsByTagName("fatherId").item(0)).getAttribute("value"));
			try {
				os.fatherName = (((Element)familyElement.getElementsByTagName("fatherName").item(0)).getAttribute("value"));
				os.fatherFemininity = Femininity.valueOf(((Element)familyElement.getElementsByTagName("fatherFemininity").item(0)).getAttribute("value"));
				os.fatherSubspecies = Subspecies.getSubspeciesFromId(((Element)familyElement.getElementsByTagName("fatherSubspecies").item(0)).getAttribute("value"));
			} catch(Exception ex) {
			}
			
			if(familyElement.getElementsByTagName("incubatorId").getLength()>0) {
				os.setIncubator(((Element)familyElement.getElementsByTagName("incubatorId").item(0)).getAttribute("value"));
			}
			try {
				os.incubatorName = (((Element)familyElement.getElementsByTagName("incubatorName").item(0)).getAttribute("value"));
				os.incubatorFemininity = Femininity.valueOf(((Element)familyElement.getElementsByTagName("incubatorFemininity").item(0)).getAttribute("value"));
				os.incubatorSubspecies = Subspecies.getSubspeciesFromId(((Element)familyElement.getElementsByTagName("incubatorSubspecies").item(0)).getAttribute("value"));
			} catch(Exception ex) {
			}
			
			try {
				int day = Integer.parseInt(((Element)familyElement.getElementsByTagName("dayOfConception").item(0)).getAttribute("value"));
				Month month = Month.valueOf(((Element)familyElement.getElementsByTagName("monthOfConception").item(0)).getAttribute("value"));
				int year = Integer.parseInt(((Element)familyElement.getElementsByTagName("yearOfConception").item(0)).getAttribute("value"));
				
				os.setConceptionDate(LocalDateTime.of(year, month, day, 12, 0));
				
			} catch(Exception ex) {
			}
		}
		
		return os;
	}
	
	public static String getValueFromElementWithTagName(Element parentElement, String tagName) {
		return getValueFromElementWithTagName(parentElement, tagName, null);
	}
	
	public static String getValueFromElementWithTagName(Element parentElement, String tagName, String defaultValue) {
		Element x = (Element) parentElement.getElementsByTagName(tagName).item(0);
		return x != null ? x.getAttribute("value") : defaultValue;
	}
	
	public String getId() {	return id; }
	
	public void setId(String id) { this.id = id; }
	
	public Boolean isFromPlayer() { return fromPlayer; }
	
	public void setFromPlayer(Boolean fromPlayer) {	this.fromPlayer = fromPlayer; }
	
	public Boolean isBorn() { return born; }
	
	public void setBorn(Boolean born) { this.born = born; }
	
	private NameTriplet getNameTriplet() {	return nameTriplet;	}
	
	public String getSurname() { return surname; }
	
	public void setSurname(String surname) { this.surname = surname; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public LocalDateTime getBirthday() { return birthday; }
	
	public void setBirthday(LocalDateTime birthday) { this.birthday = birthday; }
	
	public GameCharacter getMother() {
		if(motherId==null || motherId.isEmpty() || motherId.equals("NOT_SET")) {
			return null;
		}
		try {
			return Main.game.getNPCById(motherId);
		} catch(Exception e) {
			return null;
		}
	}

	public String getMotherId() {
		return motherId;
	}
	
	public void setMother(String motherId) {
		this.motherId = motherId;
	}
	
	public void setMother(GameCharacter mother) {
		motherId = mother.getId();
		motherName = mother.getNameIgnoresPlayerKnowledge();
		motherFemininity = mother.getFemininity();
		motherSubspecies = mother.getTrueSubspecies();
	}
	
	public String getMotherName() {
		return motherName;
	}
	
	public Femininity getMotherFemininity() {
		return motherFemininity;
	}
	
	public AbstractSubspecies getMotherSubspecies() {
		return motherSubspecies;
	}
	
	public GameCharacter getFather() {
		if(fatherId==null || fatherId.isEmpty() || fatherId.equals("NOT_SET")) {
			return null;
		}
		try {
			return Main.game.getNPCById(fatherId);
		} catch(Exception e) {
			return null;
		}
	}
	
	public String getFatherId() {
		return fatherId;
	}
	
	public void setFather(String fatherId) {
		this.fatherId = fatherId;
	}
	
	public void setFather(GameCharacter father) {
		fatherId = father.getId();
		fatherName = father.getNameIgnoresPlayerKnowledge();
		fatherFemininity = father.getFemininity();
		fatherSubspecies = father.getTrueSubspecies();
	}
	
	public String getFatherName() {
		return fatherName;
	}
	
	public Femininity getFatherFemininity() {
		return fatherFemininity;
	}
	
	public AbstractSubspecies getFatherSubspecies() {
		return fatherSubspecies;
	}
	public String getIncubatorId() { return incubatorId; }
	
	public void setIncubator(String incubatorId) {
		this.incubatorId = incubatorId;
		if(this.getIncubator()!=null && this.getIncubator().isPlayer()) {
			this.setFromPlayer(true);
		}
	}
	
	public void setIncubator(GameCharacter incubator) {
		incubatorId = incubator.getId();
		incubatorName = incubator.getNameIgnoresPlayerKnowledge();
		incubatorFemininity = incubator.getFemininity();
		incubatorSubspecies = incubator.getTrueSubspecies();
	}
	
	public GameCharacter getIncubator() {
		if(getIncubatorId()==null || getIncubatorId().isEmpty() || getIncubatorId().equals("NOT_SET")) {
			return null;
		}
		try {
			return Main.game.getNPCById(getIncubatorId());
		} catch(Exception e) {
			return null;
		}
	}
	
	public String getIncubatorName() {
		return incubatorName;
	}
	
	public Femininity getIncubatorFemininity() {
		return incubatorFemininity;
	}
	
	public AbstractSubspecies getIncubatorSubspecies() {
		return incubatorSubspecies;
	}
	
	public LocalDateTime getConceptionDate() { return conceptionDate; }
	
	public void setConceptionDate(LocalDateTime conceptionDate) { this.conceptionDate = conceptionDate; }
	
	public Body getBody() {	return body; }
	
	public void setBody(Body body) { this.body = body; }
	
	public void setBody(Gender startingGender, GameCharacter mother, GameCharacter father) {
		GenericAndrogynousNPC template = new GenericAndrogynousNPC();
		body = Main.game.getCharacterUtils().generateBody(template, startingGender, mother, father);
	}
	
	public String getName() {
		switch(this.getFemininity()) {
			case MASCULINE_STRONG:
			case MASCULINE:
				return nameTriplet.getMasculine();
			case ANDROGYNOUS:
				return nameTriplet.getAndrogynous();
			case FEMININE:
			case FEMININE_STRONG:
			default:
				return nameTriplet.getFeminine();
		}
	}
	
	public void setName(NameTriplet nameTriplet) { this.nameTriplet = nameTriplet; }

	public AbstractRace getRace() {	return getSubspecies().getRace(); }
	
	public AbstractSubspecies getSubspecies() { return subspecies; }
	
	public boolean isFeminine() {
		return body==null || body.isFeminine();
	}
	
	public Femininity getFemininity() {
		return Femininity.valueOf(body.getFemininity());
	}
	
	public boolean isTakesAfterMother() {
		return body.isTakesAfterMother();
	}
	
	public AbstractSubspecies getHalfDemonSubspecies() {
		return body.getHalfDemonSubspecies();
	}
	
	public DialogueNode getEncounterDialogue() {
		return GenericOffspringDialogue.OFFSPRING_ENCOUNTER;
	}
	
	public String hisHer() {
		if(this.isFeminine()) {
			return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getFeminine();
		} else {
			return GenderPronoun.POSSESSIVE_BEFORE_NOUN.getMasculine();
		}
	}
	
	public Gender getGender() {
		return body.getGender();
	}
	
	public String getGenderName() {
		return this.getGender().getNounYoung();
	}
	
	public BodyShape getBodyShape() {
		return BodyShape.valueOf(Muscle.valueOf(body.getMuscle()), BodySize.valueOf(body.getBodySize()));
	}
	
	public Height getHeight() {
		return body.getHeight();
	}
	
}
