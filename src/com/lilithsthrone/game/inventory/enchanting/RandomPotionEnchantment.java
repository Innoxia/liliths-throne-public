package com.lilithsthrone.game.inventory.enchanting;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.11.4
 * @version 0.4.11.4
 * @author Innoxia
 */
public class RandomPotionEnchantment {
	
	private boolean positiveEnchantment;
	
	private String spawnWeighting;

	private String applicationSpeech;
	private String applicationSpeechEnd;
	
	private String conditionalPreParsing;
	
	private List<Value<String, String>> effects;
	
	public RandomPotionEnchantment(boolean positiveEnchantment,
			String spawnWeighting,
			String applicationSpeech,
			String applicationSpeechEnd,
			List<TFPotency> sealPotency,
			String conditionalPreParsing,
			List<Value<String, String>> effects) {
		this.positiveEnchantment = positiveEnchantment;
		
		this.spawnWeighting = spawnWeighting;
		this.applicationSpeech = applicationSpeech;
		this.applicationSpeechEnd = applicationSpeechEnd;
		
		this.conditionalPreParsing = conditionalPreParsing;
		this.effects = effects;
	}
	
	public RandomPotionEnchantment(File file) {
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
						System.err.println("RandomPotionEnchantment loading error in '"+file.getName()+"': positiveEnchantment");
					}
				}
				
				this.spawnWeighting = "";
				if(coreElement.getOptionalFirstOf("spawnWeighting").isPresent()) {
					try {
						this.spawnWeighting = coreElement.getMandatoryFirstOf("spawnWeighting").getTextContent();
					} catch(Exception ex) {
						System.err.println("RandomPotionEnchantment loading error in '"+file.getName()+"': spawnWeighting");
					}
				}

				this.applicationSpeech = "";
				if(coreElement.getOptionalFirstOf("applicationSpeechStart").isPresent()) {
					try {
						this.applicationSpeech = coreElement.getMandatoryFirstOf("applicationSpeechStart").getTextContent();
					} catch(Exception ex) {
						System.err.println("RandomPotionEnchantment loading error in '"+file.getName()+"': applicationSpeechStart");
					}
				}
				
				this.applicationSpeechEnd = "";
				if(coreElement.getOptionalFirstOf("applicationSpeechEnd").isPresent()) {
					try {
						this.applicationSpeechEnd = coreElement.getMandatoryFirstOf("applicationSpeechEnd").getTextContent();
					} catch(Exception ex) {
						System.err.println("RandomPotionEnchantment loading error in '"+file.getName()+"': applicationSpeechEnd");
					}
				}
				
				
				this.conditionalPreParsing = "";
				if(coreElement.getOptionalFirstOf("conditionalPreParsing").isPresent()) {
					this.conditionalPreParsing = coreElement.getMandatoryFirstOf("conditionalPreParsing").getTextContent().trim();
				}
				
				this.effects = new ArrayList<>();
				for(Element e : coreElement.getAllOf("potionEffects")) {
					try {
						String conditional = "true";
						if(e.getOptionalFirstOf("conditional").isPresent() && !e.getMandatoryFirstOf("conditional").getTextContent().isEmpty()) {
							conditional = e.getMandatoryFirstOf("conditional").getTextContent();
						}

						String effect = "";
						if(e.getOptionalFirstOf("effect").isPresent()) {
							effect = e.getMandatoryFirstOf("effect").getTextContent();
						}
						
						effects.add(new Value<>(conditional, effect));
						
					} catch(Exception ex) {
						System.err.println("RandomPotionEnchantment loading error in '"+file.getName()+"': effects");
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof RandomPotionEnchantment){
			if(((RandomPotionEnchantment)o).positiveEnchantment == this.positiveEnchantment
					&& Objects.equals(((RandomPotionEnchantment)o).spawnWeighting, this.spawnWeighting)
					&& Objects.equals(((RandomPotionEnchantment)o).applicationSpeech, this.applicationSpeech)
					&& Objects.equals(((RandomPotionEnchantment)o).applicationSpeechEnd, this.applicationSpeechEnd)
					&& Objects.equals(((RandomPotionEnchantment)o).conditionalPreParsing, this.conditionalPreParsing)
					&& Objects.equals(((RandomPotionEnchantment)o).effects, this.effects)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (positiveEnchantment ? 1 : 0);
		result = 31 * result + spawnWeighting.hashCode();
		if(applicationSpeech!=null) {
			result = 31 * result + applicationSpeech.hashCode();
		}
		if(applicationSpeechEnd!=null) {
			result = 31 * result + applicationSpeechEnd.hashCode();
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

	public boolean isAvailable(GameCharacter target) {
		return getWeighting(target)>0;
	}
	
	public int getWeighting(GameCharacter target) {
		return Integer.valueOf(UtilText.parse(target, spawnWeighting).trim());
	}
	
	public String getApplicationSpeech() {
		return applicationSpeech;
	}

	public String getApplicationSpeechEnd() {
		return applicationSpeechEnd;
	}
	
	public void executeConditionalPreParsing(GameCharacter target) {
		UtilText.parse(target, conditionalPreParsing);
	}
	
	public String applyEffects(GameCharacter target) {
		executeConditionalPreParsing(target);
		
		StringBuilder sb = new StringBuilder();
		
		boolean initialSpeechApplied = false;
		if(this.getApplicationSpeech()!=null && !this.getApplicationSpeech().isEmpty()) {
			sb.append("<p>");
				if(target.isPlayer()) {
					sb.append("As you swallow down the last of the potion, you hear a ghostly, female voice speaking inside your head, ");
				} else {
					sb.append(UtilText.parse(target, "As [npc.name] swallows down the last of the potion, [npc.she] hears a ghostly, female voice speaking inside [npc.her] head, "));
				}
				sb.append(UtilText.parse(target, "[style.thoughtFeminineStrong("+this.getApplicationSpeech()+")]"));
			sb.append("</p>");
			initialSpeechApplied = true;
		}
		
		for(Value<String, String> entry : effects) {
			if(Boolean.valueOf(UtilText.parse(entry.getKey()).trim())) {
				sb.append(UtilText.parse(target, entry.getValue()));
			}
		}

		if(this.getApplicationSpeechEnd()!=null && !this.getApplicationSpeechEnd().isEmpty()) {
			sb.append("<p>");
				if(target.isPlayer()) {
					sb.append("As the potion's effects come to an end, you hear "+(initialSpeechApplied?"the ethereal feminine voice speaking to you once again,":"a ghostly, female voice speaking inside your head,"));
				} else {
					sb.append(UtilText.parse(target, "As the potion's effects come to an end, [npc.name] hears "
							+(initialSpeechApplied
									?"the ethereal feminine voice speaking to [npc.herHim] once again,"
									:"a ghostly, female voice speaking inside [npc.her] head,")));
				}
				sb.append(UtilText.parse(target, "[style.thoughtFeminineStrong("+this.getApplicationSpeechEnd()+")]"));
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	public static String applyRandomPotionEffect(GameCharacter target) {
		Map<RandomPotionEnchantment, Integer> weightedMap = new HashMap<>();
		for(RandomPotionEnchantment enchantment : allPotionEnchantments) {
			int weighting = enchantment.getWeighting(target);
			weightedMap.put(enchantment, weighting);
		}
		if(!weightedMap.isEmpty() && Util.checkWeightedMap(weightedMap, false)) {
			RandomPotionEnchantment randomlySelectedEnchantment = Util.getRandomObjectFromWeightedMap(weightedMap);
			return randomlySelectedEnchantment.applyEffects(target);
		}
		System.err.println("Error: RandomPotionEnchantment.applyRandomPotionEffect("+target.getNameIgnoresPlayerKnowledge()+") could not find an effect to apply!");
		return "";
	}
	
	// Static loading block for random effects and associated methods:

	private static List<RandomPotionEnchantment> allPositivePotionEnchantments = new ArrayList<>();
	private static List<RandomPotionEnchantment> allNegativePotionEnchantments = new ArrayList<>();
	private static List<RandomPotionEnchantment> allPotionEnchantments = new ArrayList<>();
    
    public static List<RandomPotionEnchantment> getAllPositivePotionEnchantments() {
		return new ArrayList<>(allPositivePotionEnchantments);
	}
    public static List<RandomPotionEnchantment> getAllNegativePotionEnchantments() {
		return new ArrayList<>(allNegativePotionEnchantments);
	}
    
	static {
    	initAllRandomPotionEnchantments(); // init allPotionEnchantments list
    	allPotionEnchantments = new ArrayList<>();
    	allPotionEnchantments.addAll(allPositivePotionEnchantments);
    	allPotionEnchantments.addAll(allNegativePotionEnchantments);
    }
	
	public static void initAllRandomPotionEnchantments() {
		allPositivePotionEnchantments = new ArrayList<>();
		allNegativePotionEnchantments = new ArrayList<>();
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/randomEnchantments/innoxia/potion");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					RandomPotionEnchantment enchantment = new RandomPotionEnchantment(innerEntry.getValue()) {};
					if(enchantment.isPositiveEnchantment()) {
						allPositivePotionEnchantments.add(enchantment);
					} else {
						allNegativePotionEnchantments.add(enchantment);
					}
					System.out.println("res randomPotionEnchantment: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading RandomPotionEnchantment failed at 'initAllRandomPotionEnchantments' (RES). File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}

		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/randomEnchantments/potion");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					RandomPotionEnchantment enchantment = new RandomPotionEnchantment(innerEntry.getValue()) {};
					if(enchantment.isPositiveEnchantment()) {
						allPositivePotionEnchantments.add(enchantment);
					} else {
						allNegativePotionEnchantments.add(enchantment);
					}
					System.out.println("modded randomPotionEnchantment: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading RandomPotionEnchantment failed at 'initAllRandomPotionEnchantments' (MODS). File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
	}	
}
