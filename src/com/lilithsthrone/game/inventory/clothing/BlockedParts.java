package com.lilithsthrone.game.inventory.clothing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.6?
 * @version 0.2.7
 * @author Innoxia
 */
public class BlockedParts implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;
	
	public DisplacementType displacementType;
	public List<ClothingAccess> clothingAccessRequired;
	public List<CoverableArea> blockedBodyParts;
	public List<ClothingAccess> clothingAccessBlocked;
	public List<InventorySlot> concealedSlots;

	/**
	 * A class that holds information about what clothing-related parts are
	 * blocked.
	 * 
	 * @param displacementType The type of displacement that's required to block/unblock the areas.
	 * @param clothingAccessRequired The access required in order to achieve the  DisplacementType.
	 * @param blockedBodyParts The body parts that this displacement blocks/reveals.
	 * @param clothingAccessBlocked The clothing access that this displacement blocks/reveals.
	 * @param concealedSlots Slots that are concealed by this displacementType.
	 */
	public BlockedParts(DisplacementType displacementType,
			List<ClothingAccess> clothingAccessRequired,
			List<CoverableArea> blockedBodyParts,
			List<ClothingAccess> clothingAccessBlocked,
			List<InventorySlot> concealedSlots) {

		this.displacementType = displacementType;

		if (clothingAccessRequired != null) {
			this.clothingAccessRequired = clothingAccessRequired;
		} else {
			this.clothingAccessRequired = new ArrayList<>();
		}
		
		if (blockedBodyParts != null) {
			this.blockedBodyParts = blockedBodyParts;
		} else {
			this.blockedBodyParts = new ArrayList<>();
		}
		
		if (clothingAccessBlocked != null) {
			this.clothingAccessBlocked = clothingAccessBlocked;
		} else {
			this.clothingAccessBlocked = new ArrayList<>();
		}
		
		if (concealedSlots != null) {
			this.concealedSlots = concealedSlots;
		} else {
			this.concealedSlots = new ArrayList<>();
		}
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element blockedParts = doc.createElement("blockedParts");
		parentElement.appendChild(blockedParts);
		
		Element displacementTypeElement = doc.createElement("displacementType");
		blockedParts.appendChild(displacementTypeElement);
		displacementTypeElement.setTextContent(this.displacementType.toString());
		
		Element clothingAccessRequiredElement = doc.createElement("clothingAccessRequired");
		blockedParts.appendChild(clothingAccessRequiredElement);
		for(ClothingAccess clothingAccess : this.clothingAccessRequired) {
			Element clothingAccessElement = doc.createElement("clothingAccess");
			clothingAccessRequiredElement.appendChild(clothingAccessElement);
			clothingAccessElement.setTextContent(clothingAccess.toString());
		}
		
		Element blockedBodyPartsElement = doc.createElement("blockedBodyParts");
		blockedParts.appendChild(blockedBodyPartsElement);
		for(CoverableArea coverableArea : this.blockedBodyParts) {
			Element coverableAreaElement = doc.createElement("bodyPart");
			blockedBodyPartsElement.appendChild(coverableAreaElement);
			coverableAreaElement.setTextContent(coverableArea.toString());
		}

		Element clothingAccessBlockedElement = doc.createElement("clothingAccessBlocked");
		blockedParts.appendChild(clothingAccessBlockedElement);
		for(ClothingAccess clothingAccess : this.clothingAccessBlocked) {
			Element clothingAccessElement = doc.createElement("clothingAccess");
			clothingAccessBlockedElement.appendChild(clothingAccessElement);
			clothingAccessElement.setTextContent(clothingAccess.toString());
		}
		
		Element concealedSlotsElement = doc.createElement("concealedSlots");
		blockedParts.appendChild(concealedSlotsElement);
		for(InventorySlot inventorySlot : this.concealedSlots) {
			Element inventorySlotElement = doc.createElement("slot");
			concealedSlotsElement.appendChild(inventorySlotElement);
			inventorySlotElement.setTextContent(inventorySlot.toString());
		}
		
		return blockedParts;
	}
	
	public static BlockedParts loadFromXML(Element parentElement, Document doc, String contextForErrors) {

		DisplacementType displacementType = DisplacementType.valueOf(parentElement.getElementsByTagName("displacementType").item(0).getTextContent());
		
		String errorCode = "Unknown Clothing";
		Element clothingElement = ((Element) doc.getElementsByTagName("clothing").item(0));
		if(clothingElement.getElementsByTagName("coreAtributes").getLength()>0) {
			errorCode = ((Element) clothingElement.getElementsByTagName("coreAtributes").item(0)).getElementsByTagName("name").item(0).getTextContent(); // Support for old versions
		} else {
			errorCode = ((Element) clothingElement.getElementsByTagName("coreAttributes").item(0)).getElementsByTagName("name").item(0).getTextContent(); // Fix typo
		}
		
		List<ClothingAccess> loadedClothingAccessRequired = new ArrayList<>();
		Element clothingAccessRequiredElement = (Element)parentElement.getElementsByTagName("clothingAccessRequired").item(0);
		try {
			for(int i=0; i<clothingAccessRequiredElement.getElementsByTagName("clothingAccess").getLength(); i++){
				Element e = ((Element)clothingAccessRequiredElement.getElementsByTagName("clothingAccess").item(i));
				loadedClothingAccessRequired.add(ClothingAccess.valueOf(e.getTextContent()));
			}
		} catch(Exception ex) {
			System.err.println("BlockedParts loading failed for "+errorCode+". Code 1 (clothingAccessRequired -> clothingAccess); Context: "+contextForErrors);
			System.err.println(ex);
			printHelpfulErrorForEnumValueMismatches(ex, getPossibleEnumValues());
		}
		
		List<CoverableArea> loadedBlockedBodyParts = new ArrayList<>();
		Element blockedBodyPartsElement = (Element)parentElement.getElementsByTagName("blockedBodyParts").item(0);
		try {
			for(int i=0; i<blockedBodyPartsElement.getElementsByTagName("bodyPart").getLength(); i++){
				Element e = ((Element)blockedBodyPartsElement.getElementsByTagName("bodyPart").item(i));
				loadedBlockedBodyParts.add(CoverableArea.valueOf(e.getTextContent()));
			}
		} catch(Exception ex) {
			System.err.println("BlockedParts loading failed for "+errorCode+". Code 2 (blockedBodyParts -> bodyPart); Context: "+contextForErrors);
			System.err.println(ex);
			printHelpfulErrorForEnumValueMismatches(ex, getPossibleEnumValues());
		}
		
		List<ClothingAccess> loadedClothingAccessBlocked = new ArrayList<>();
		Element clothingAccessBlockedElement = (Element)parentElement.getElementsByTagName("clothingAccessBlocked").item(0);
		try {
			for(int i=0; i<clothingAccessBlockedElement.getElementsByTagName("clothingAccess").getLength(); i++){
				Element e = ((Element)clothingAccessBlockedElement.getElementsByTagName("clothingAccess").item(i));
				loadedClothingAccessBlocked.add(ClothingAccess.valueOf(e.getTextContent()));
			}
		} catch(Exception ex) {
			System.err.println("BlockedParts loading failed for "+errorCode+". Code 3 (clothingAccessBlocked -> clothingAccess); Context: "+contextForErrors);
			System.err.println(ex);
			printHelpfulErrorForEnumValueMismatches(ex, getPossibleEnumValues());
		}
		
		List<InventorySlot> loadedConcealedSlots = new ArrayList<>();
		Element concealedSlotsElement = (Element)parentElement.getElementsByTagName("concealedSlots").item(0);
		if(!concealedSlotsElement.getAttribute("values").isEmpty()) {
			try {
				loadedConcealedSlots = PresetConcealmentLists.valueOf(concealedSlotsElement.getAttribute("values")).getPresetInventorySlotList();
			} catch(Exception ex) {
				System.err.println("BlockedParts loading failed for "+errorCode+". Code 4a (concealedSlots.values); Context: "+contextForErrors);
				System.err.println(ex);
				printHelpfulErrorForEnumValueMismatches(ex, getPossibleEnumValues());
			}
		} else {
			try {
				for(int i=0; i<concealedSlotsElement.getElementsByTagName("slot").getLength(); i++){
					Element e = ((Element)concealedSlotsElement.getElementsByTagName("slot").item(i));
					loadedConcealedSlots.add(InventorySlot.valueOf(e.getTextContent()));
				}
			} catch(Exception ex) {
				System.err.println("BlockedParts loading failed for "+errorCode+". Code 4b (concealedSlots -> slot); Context: "+contextForErrors);
				System.err.println(ex);
				printHelpfulErrorForEnumValueMismatches(ex, getPossibleEnumValues());
			}
		}
		
		return new BlockedParts(displacementType,
				loadedClothingAccessRequired,
				loadedBlockedBodyParts,
				loadedClothingAccessBlocked,
				loadedConcealedSlots);
	}

	private static Map<Class, Object[]> getPossibleEnumValues() {
		Map<Class, Object[]> possibleEnumValues = new HashMap<>();
		possibleEnumValues.put(ClothingAccess.class, ClothingAccess.values());
		possibleEnumValues.put(InventorySlot.class, InventorySlot.values());
		possibleEnumValues.put(CoverableArea.class, CoverableArea.values());
		possibleEnumValues.put(PresetConcealmentLists.class, PresetConcealmentLists.values());
		return possibleEnumValues;
	}

	private static void printHelpfulErrorForEnumValueMismatches(Exception ex, Map<Class, Object[]> possibleEnumValues) {
		String exMessage = ex.getMessage();
		if (exMessage.startsWith("No enum constant")){
			for (Map.Entry<Class, Object[]> possibleMatch : possibleEnumValues.entrySet()) {
				if (exMessage.contains(possibleMatch.getKey().getCanonicalName())) {
					StringJoiner valueLister = new StringJoiner(",");
					Arrays.asList(possibleMatch.getValue()).forEach(enumValue -> valueLister.add(enumValue.toString()));
					System.err.println("Possible values for "+possibleMatch.getKey().getSimpleName()+" are " + valueLister.toString());
				}
			}
		}
	}
}
