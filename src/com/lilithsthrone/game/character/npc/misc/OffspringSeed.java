package com.lilithsthrone.game.character.npc.misc;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.*;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.time.LocalDateTime;

/**
 * @since 0.4.0
 * @version 0.4.0
 * @author Innoxia, AceXp
 */
public class OffspringSeed  implements XMLSaving {
	
	// Core variables:
	protected String id;
	protected NameTriplet nameTriplet;
	protected String surname;
	protected String description;
	protected LocalDateTime birthday;
	
	// Body:
	protected Body body;
	
	// Family:
	protected String motherId;
	protected String fatherId;
	protected String incubatorId;
	protected LocalDateTime conceptionDate;
	
	protected OffspringSeed(
			NameTriplet nameTriplet,
			String surname,
			String description,
			LocalDateTime birthday,
			AbstractSubspecies startingSubspecies,
			RaceStage stage) {
		
		GenericAndrogynousNPC template = new GenericAndrogynousNPC();
		
		id = "NOT_SET";
		this.nameTriplet = nameTriplet;
		this.surname = surname;
		this.description = description;
		this.birthday = birthday;
		
		// Set the character's starting body based on their gender and race:
		this.body = Main.game.getCharacterUtils().generateBody(template, Gender.getGenderFromUserPreferences(false, false), startingSubspecies, stage);
		
	}

	public OffspringSeed(GameCharacter mother, GameCharacter father) {
		this(mother, father, father.getTrueSubspecies(), father.getHalfDemonSubspecies());
	}
	
	public OffspringSeed(GameCharacter mother, GameCharacter father, AbstractSubspecies fatherSubspecies, AbstractSubspecies fatherHalfDemonSubspecies) {
		
		GenericAndrogynousNPC template = new GenericAndrogynousNPC();
		
		if(mother.getTrueSubspecies()== Subspecies.LILIN || mother.getTrueSubspecies()==Subspecies.ELDER_LILIN) {
			this.setSurname(mother.getName(false)+"martuilani");
			
		} else if(father!=null && (father.getTrueSubspecies()==Subspecies.LILIN || father.getTrueSubspecies()==Subspecies.ELDER_LILIN)) {
			this.setSurname(father.getName(false)+"martuilani");
			
		} else if(mother.getSurname()!=null && !mother.getSurname().isEmpty()) {
			this.setSurname(mother.getSurname());
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
			this.body = Main.game.getCharacterUtils().generateBody(template, gender, mother, father);;
		}
		
		setName(Name.getRandomTriplet(getRace()));
		
		this.setMother(mother);
		
		if(father!=null) {
			this.setFather(father);
		}
		
	}
		
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		return null;
	}
	
	public String getId() {	return id; }
	
	public void setId(String id) { this.id = id; }
	
	public String getSurname() { return surname; }
	
	public void setSurname(String surname) { this.surname = surname; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public LocalDateTime getBirthday() { return birthday; }
	
	public void setBirthday(LocalDateTime birthday) { this.birthday = birthday; }
	
	public String getMotherId() {
		return motherId;
	}
	
	public void setMother(String motherId) {
		this.motherId = motherId;
	}
	
	public void setMother(GameCharacter mother) { motherId = mother.getId(); }
	
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
	}
	
	public String getIncubatorId() { return incubatorId; }
	
	public void setIncubatorId(String incubatorId) { this.incubatorId = incubatorId; }
	
	public LocalDateTime getConceptionDate() { return conceptionDate; }
	
	public void setConceptionDate(LocalDateTime conceptionDate) { this.conceptionDate = conceptionDate; }
	
	public Body getBody() {	return body; }
	
	public void setBody(Body body) { this.body = body; }
	
	public NameTriplet getName() { return nameTriplet; }
	
	public void setName(NameTriplet nameTriplet) { this.nameTriplet = nameTriplet; }

	public AbstractRace getRace() {	return getSubspecies().getRace(); }
	
	public AbstractSubspecies getSubspecies() { return body.getSubspecies(); }
	
	public boolean isFeminine() {
		return body==null || body.isFeminine();
	}
	
	public boolean isTakesAfterMother() {
		return body.isTakesAfterMother();
	}
	
}
