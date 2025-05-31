package com.lilithsthrone.game.character.npc.fields;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.main.Main;

/**
 * @since 0.4.9
 * @version 0.4.10.8
 * @author Innoxia
 */
public class Golix extends Elemental {

	public Golix() {
		this(false);
	}
	
	public Golix(boolean isImported) {
		super(isImported);
		
		if(!isImported) {
			this.setSurname("Oglixkamu");
		}
	}

	public Golix(Gender gender, GameCharacter summoner, boolean isImported) {
		super(gender, summoner, isImported);
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		super.loadFromXML(parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.8")) {
			this.setSurname("Oglixkamu");
			this.setElementalSchool(SpellSchool.EARTH);
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getArtworkFolderName() {
		return "Golix";
	}
	
}
