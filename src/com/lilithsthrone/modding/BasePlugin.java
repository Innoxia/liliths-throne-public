/**
 * 
 */
package com.lilithsthrone.modding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.world.Cell;

/**
 * A simple entry point for modders to use for adding, modifying, or removing stuff from the game.
 * 
 * @author Anonymous-BCFED
 * @since FIXME
 * TODO: Add common configuration interfaces (per-save and global)
 * TODO: Example mod
 */
public abstract class BasePlugin {
	public BasePlugin()
	{
		// Add on-init stuff here
	}

	/**
	 * Called before the engine loads any res/ data from disk.
	 */
	public void beforeMainLoad() {}
	
	/**
	 * Called after the game loads res/ data from disk.
	 */
	public void afterMainLoad() {}
	
	/**
	 * Called just after a save is deserialized from XML
	 * 
	 * @param savefile The save just loaded.
	 * @param pc The loaded PlayerCharacter.
	 */
	public void afterSaveDeserialized(String savefile, PlayerCharacter pc) {}
	
	/**
	 * Handle a save just before it's loaded into game structures.
	 * 
	 * NOTE: Please put mod settings into /modSettings/yourModID.
	 * 
	 * @param savefile Save file that's been read into an XML Document, but not deserialized yet.
	 * @param rootElement Root element of the document
	 * @param doc XML Document
	 */
	public void beforeSaveDeserialized(String savefile, Element rootElement, Document doc) {}
	
	/**
	 * Handle a save just before it is serialized to XML.
	 * 
	 * @param savefile
	 * @param pc
	 */
	public void beforeSaveSerialized(String savefile, PlayerCharacter pc) {}
	
	/**
	 * Handle a save just after it is serialized to XML, and just before it is saved to disk.
	 * 
	 * @param savefile
	 * @param rootElement
	 * @param doc
	 */
	public void afterSaveSerialized(String savefile, Element rootElement, Document doc) {}
	
	//public void onPlayerMoving(PlayerCharacter pc, Cell before, Cell after) {}
}
