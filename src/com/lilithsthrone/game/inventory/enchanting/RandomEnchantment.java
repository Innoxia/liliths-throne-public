package com.lilithsthrone.game.inventory.enchanting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.10.10
 * @version 0.4.10.10
 * @author Innoxia
 */
public class RandomEnchantment {
	
	private boolean positiveEnchantment;
	private boolean applyDefaultSeal;
	
	private String spawnWeighting;
	
	private String namePrefix;
	private String namePostfix;
	private String nameOverride;
	
	private String conditionalPreParsing;
	
	private List<Value<String, List<ItemEffect>>> effects;
	
	public RandomEnchantment(boolean positiveEnchantment,
			boolean applyDefaultSeal,
			String spawnWeighting,
			String namePrefix,
			String namePostfix,
			String nameOverride,
			List<TFPotency> sealPotency,
			String conditionalPreParsing,
			List<Value<String, List<ItemEffect>>> effects) {
		this.positiveEnchantment = positiveEnchantment;
		this.applyDefaultSeal = applyDefaultSeal;
		this.spawnWeighting = spawnWeighting;
		this.namePrefix = namePrefix;
		this.namePostfix = namePostfix;
		this.nameOverride = nameOverride;
		this.conditionalPreParsing = conditionalPreParsing;
		this.effects = effects;
	}
	
	public RandomEnchantment(File file) {
		if (file.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(file);
				
				// Cast magic:
				doc.getDocumentElement().normalize();

				Element coreElement = Element.getDocumentRootElement(file); // Loads the document and returns the root element - in randomEnchantment files it's <enchantment>
				
				this.positiveEnchantment = false;
				if(coreElement.getOptionalFirstOf("positiveEnchantment").isPresent()) {
					try {
						this.positiveEnchantment = Boolean.valueOf(coreElement.getMandatoryFirstOf("positiveEnchantment").getTextContent());
					} catch(Exception ex) {
						System.err.println("RandomEnchantment loading error in '"+file.getName()+"': positiveEnchantment");
					}
				}
				
				this.applyDefaultSeal = true;
				if(coreElement.getOptionalFirstOf("applyDefaultSeal").isPresent()) {
					try {
						this.applyDefaultSeal = Boolean.valueOf(coreElement.getMandatoryFirstOf("applyDefaultSeal").getTextContent());
					} catch(Exception ex) {
						System.err.println("RandomEnchantment loading error in '"+file.getName()+"': applyDefaultSeal");
					}
				}
				
				this.spawnWeighting = "";
				if(coreElement.getOptionalFirstOf("spawnWeighting").isPresent()) {
					try {
						this.spawnWeighting = coreElement.getMandatoryFirstOf("spawnWeighting").getTextContent();
					} catch(Exception ex) {
						System.err.println("RandomEnchantment loading error in '"+file.getName()+"': spawnWeighting");
					}
				}

				this.namePrefix = null;
				this.namePostfix = null;
				this.nameOverride = null;
				if(coreElement.getOptionalFirstOf("namePrefix").isPresent()) {
					this.namePrefix = coreElement.getMandatoryFirstOf("namePrefix").getTextContent().trim();
				}
				if(coreElement.getOptionalFirstOf("namePostfix").isPresent()) {
					this.namePostfix = coreElement.getMandatoryFirstOf("namePostfix").getTextContent().trim();
				}
				if(coreElement.getOptionalFirstOf("nameOverride").isPresent()) {
					this.nameOverride = coreElement.getMandatoryFirstOf("nameOverride").getTextContent().trim();
				}
				
				
				this.conditionalPreParsing = "";
				if(coreElement.getOptionalFirstOf("conditionalPreParsing").isPresent()) {
					this.conditionalPreParsing = coreElement.getMandatoryFirstOf("conditionalPreParsing").getTextContent().trim();
				}
				
				this.effects = new ArrayList<>();
				for(Element e : coreElement.getAllOf("itemEffects")) {
					try {
						String conditional = "true";
						if(e.getOptionalFirstOf("conditional").isPresent()) {
							conditional = e.getMandatoryFirstOf("conditional").getTextContent();
						}
						
						List<ItemEffect> effectsList = new ArrayList<>();
						for(Element effectElement : e.getAllOf("effect")) {
							@SuppressWarnings("deprecation")
							ItemEffect effect = ItemEffect.loadFromXML(effectElement.getInnerElement(), doc);
							effect.getTimer().setSecondsPassed(0);
							effectsList.add(effect);
						}
						
						effects.add(new Value<>(conditional, effectsList));
						
					} catch(Exception ex) {
						System.err.println("RandomEnchantment loading error in '"+file.getName()+"': effects");
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof RandomEnchantment){
			if(((RandomEnchantment)o).positiveEnchantment == this.positiveEnchantment
					&& ((RandomEnchantment)o).applyDefaultSeal == this.applyDefaultSeal
					&& Objects.equals(((RandomEnchantment)o).spawnWeighting, this.spawnWeighting)
					&& Objects.equals(((RandomEnchantment)o).namePrefix, this.namePrefix)
					&& Objects.equals(((RandomEnchantment)o).namePostfix, this.namePostfix)
					&& Objects.equals(((RandomEnchantment)o).nameOverride, this.nameOverride)
					&& Objects.equals(((RandomEnchantment)o).conditionalPreParsing, this.conditionalPreParsing)
					&& Objects.equals(((RandomEnchantment)o).effects, this.effects)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (positiveEnchantment ? 1 : 0);
		result = 31 * result + (applyDefaultSeal ? 1 : 0);
		result = 31 * result + spawnWeighting.hashCode();
		if(namePrefix!=null) {
			result = 31 * result + namePrefix.hashCode();
		}
		if(namePostfix!=null) {
			result = 31 * result + namePostfix.hashCode();
		}
		if(nameOverride!=null) {
			result = 31 * result + nameOverride.hashCode();
		}
		if(conditionalPreParsing!=null) {
			result = 31 * result + conditionalPreParsing.hashCode();
		}
		result = 31 * result + effects.hashCode();
		return result;
	}

    public boolean isPositiveEnchantment() {
		return positiveEnchantment;
	}

	public boolean isAvailable(AbstractClothingType clothingType) {
		return getWeighting(clothingType)>0;
	}
	
	public int getWeighting(AbstractClothingType clothingType) {
		UtilText.setClothingTypeForParsing(clothingType);
		return Integer.valueOf(UtilText.parse(spawnWeighting).trim());
	}
	
	public String getName(AbstractClothingType clothingType) {
		if(nameOverride!=null && !nameOverride.isEmpty()) {
			return nameOverride;
		}
		StringBuilder sb = new StringBuilder();
		if(namePrefix!=null && !namePrefix.isEmpty()) {
			sb.append(namePrefix+" ");
		}
		sb.append(clothingType.getName());
		if(namePostfix!=null && !namePostfix.isEmpty()) {
			sb.append(" "+namePostfix);
		}
		return sb.toString();
	}
	
	public void executeConditionalPreParsing(AbstractClothing clothing) {
		UtilText.setClothingTypeForParsing(clothing.getClothingType());
		UtilText.parse(conditionalPreParsing);
	}
	
	public void applyEffects(AbstractClothing clothing) {
		AbstractClothingType type = clothing.getClothingType();
		
		clothing.clearEffects();

		UtilText.setClothingTypeForParsing(type);
		executeConditionalPreParsing(clothing);
		
		clothing.setHiddenName(UtilText.parse(getName(type)));
		
		boolean increaseTFStrength = false;
		
		for(Value<String, List<ItemEffect>> entry : effects) {
			if(Boolean.valueOf(UtilText.parse(entry.getKey()).trim())) {
				for(ItemEffect effect : entry.getValue()) {
					if(increaseTFStrength && TFModifier.getTFRacialBodyPartsList().contains(effect.getPrimaryModifier())) {
						effect.setPotency(TFPotency.MAJOR_BOOST);
					}
					clothing.addEffect(effect);
				}
			}
		}
		
		// Add default sealing:
		if(!this.positiveEnchantment && (this.applyDefaultSeal || !clothing.isSealed())) {
			clothing.addEffect(ItemEffect.getDefaultSealEffect());
		}
	}
	
	// Static loading block for random effects and associated methods:

	private static List<RandomEnchantment> allPositiveClothingEnchantments = new ArrayList<>();
	private static List<RandomEnchantment> allNegativeClothingEnchantments = new ArrayList<>();
    
    public static List<RandomEnchantment> getAllPositiveClothingEnchantments() {
		return new ArrayList<>(allPositiveClothingEnchantments);
	}
    public static List<RandomEnchantment> getAllNegativeClothingEnchantments() {
		return new ArrayList<>(allNegativeClothingEnchantments);
	}
    
	static {
    	initAllRandomEnchantments(); // init allClothingEnchantments list
    }
	
	public static void initAllRandomEnchantments() {
		allPositiveClothingEnchantments = new ArrayList<>();
		allNegativeClothingEnchantments = new ArrayList<>();
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/randomEnchantments");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					RandomEnchantment enchantment = new RandomEnchantment(innerEntry.getValue()) {};
					if(enchantment.isPositiveEnchantment()) {
						allPositiveClothingEnchantments.add(enchantment);
					} else {
						allNegativeClothingEnchantments.add(enchantment);
					}
//					System.out.println("res randomEnchantment: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading RandomEnchantment failed at 'getAllRandomEnchantments' (RES). File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}

		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/randomEnchantments");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					RandomEnchantment enchantment = new RandomEnchantment(innerEntry.getValue()) {};
					if(enchantment.isPositiveEnchantment()) {
						allPositiveClothingEnchantments.add(enchantment);
					} else {
						allNegativeClothingEnchantments.add(enchantment);
					}
//					System.out.println("modded randomEnchantment: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading RandomEnchantment failed at 'getAllRandomEnchantments' (MODS). File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
	}	
}
