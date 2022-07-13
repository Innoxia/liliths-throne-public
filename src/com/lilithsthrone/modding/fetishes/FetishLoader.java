package com.lilithsthrone.modding.fetishes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.modding.BasePlugin;
import com.lilithsthrone.modding.PluginLoader;
import com.lilithsthrone.utils.Util;

public class FetishLoader {
	private PluginLoader pluginLoader;
	
	private List<AbstractFetish> allFetishes = null;
	public HashSet<AbstractFetish> notIncludedInPotions = null;
	private List<AbstractFetish> providedFetishes = null;
	private List<AbstractFetish> stockFetishes = null;

	private List<FetishGroup> allFetishGroups = null;
	
	public FetishLoader(PluginLoader pluginLoader) {
		this.pluginLoader = pluginLoader;
	}
	
	public List<AbstractFetish> getAll() {
		if (allFetishes == null) {
			allFetishes = new ArrayList<AbstractFetish>();
			allFetishes.addAll(getStock());
			allFetishes.addAll(getProvided());
			System.err.println("Discovered Fetishes");
			System.err.println("---------------------------------------------------------");
			for(AbstractFetish f : allFetishes) {
				System.err.println(String.format("%s - %s", f.getClass().getName(), f.getName(null)));
			}
			System.err.println("---------------------------------------------------------");
		}
		return allFetishes;
	}
	public List<AbstractFetish> getProvided() {
		if (providedFetishes == null) {
			providedFetishes = new ArrayList<AbstractFetish>();
			for(BasePlugin p : pluginLoader.getAllPlugins()) {
				for(AbstractFetish f : p.getFetishes()) {
					providedFetishes.add(f);
					Fetish.addFetish(p, f.getID(), f);
				}
			}
		}
		return providedFetishes;
	}
	public List<AbstractFetish> getStock() {
		if (stockFetishes == null) {
			stockFetishes = new ArrayList<>();
			Field[] fields = Fetish.class.getFields();
			for (Field f : fields) {
				if (AbstractFetish.class.isAssignableFrom(f.getType())) {

					AbstractFetish fetish;

					try {
						fetish = ((AbstractFetish) f.get(null));
						stockFetishes.add(fetish);
						Fetish.addFetish(null, f.getName(), fetish);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return stockFetishes;
	}

	public void addToPairedFetishMap(Map<AbstractFetish, AbstractFetish> pairedFetishMap) {
		//plugins.forEach(p -> p.addToPairedFetishMap(pairedFetishMap));
		if(notIncludedInPotions==null) {
			notIncludedInPotions = new HashSet<AbstractFetish>(Util.newArrayListOfValues(
				Fetish.FETISH_TRANSFORMATION_GIVING, 
				Fetish.FETISH_TRANSFORMATION_RECEIVING,
				Fetish.FETISH_KINK_GIVING, 
				Fetish.FETISH_KINK_RECEIVING
			));
		}
		getAllFetishGroups().stream()
        	.filter(g -> g instanceof RelatedFetishGroup)
            .map(RelatedFetishGroup.class::cast)
            .filter(rfg -> notIncludedInPotions.contains(rfg.getDominantFetish()) && !notIncludedInPotions.contains(rfg.getSubmissiveFetish()))
            .forEachOrdered(rfg -> pairedFetishMap.put(rfg.getDominantFetish(),rfg.getSubmissiveFetish()));
	}

	public void addToUnpairedFetishMap(Map<AbstractFetish, Boolean> unpairedFetishMap) {
		getAllFetishGroups().stream()
			.filter(g -> g instanceof LooseFetishGroup)
	        .map(LooseFetishGroup.class::cast)
	        .filter(lfg -> notIncludedInPotions.contains(lfg.getMember()))
	        .forEachOrdered(rfg -> unpairedFetishMap.put(rfg.getMember(), rfg.shouldShareWithVictims()));
	}

	public void registerRelatedStockFetishes(AbstractFetish dom, AbstractFetish sub) {
		if(!this.stockFetishes.contains(dom))
			this.stockFetishes.add(dom);
		if(!this.stockFetishes.contains(sub))
			this.stockFetishes.add(sub);
		this.allFetishGroups .add(new RelatedFetishGroup(dom,sub));
	}

	public void registerLonerStockFetish(AbstractFetish f) {
		if(!this.stockFetishes.contains(f))
			this.stockFetishes.add(f);
		this.allFetishGroups.add(new LooseFetishGroup(f, true));
	}
	
	public void initStockFetishes() {
		registerRelatedStockFetishes(Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE);
		registerRelatedStockFetishes(Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING);
		registerRelatedStockFetishes(Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING);
		if(Main.game.isAnalContentEnabled()) {
			registerRelatedStockFetishes(Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
		registerRelatedStockFetishes(Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF);
		if(Main.game.isLactationContentEnabled()) {
			registerRelatedStockFetishes(Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF);
		}
		registerRelatedStockFetishes(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING);
		registerRelatedStockFetishes(Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER);
		if(Main.game.isFootContentEnabled()) {
			registerRelatedStockFetishes(Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING);
		}
		if(Main.game.isArmpitContentEnabled()) {
			registerRelatedStockFetishes(Fetish.FETISH_ARMPIT_GIVING, Fetish.FETISH_ARMPIT_RECEIVING);
		}
		registerRelatedStockFetishes(Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT);
		registerRelatedStockFetishes(Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN);
		registerRelatedStockFetishes(Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY);
		registerRelatedStockFetishes(Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING);
		registerRelatedStockFetishes(Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING);
		registerRelatedStockFetishes(Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST);
		if(Main.game.isNonConEnabled()) {
			registerRelatedStockFetishes(Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB);
		}

		registerRelatedStockFetishes(Fetish.FETISH_BONDAGE_APPLIER, Fetish.FETISH_BONDAGE_VICTIM);
		registerRelatedStockFetishes(Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF);
		registerRelatedStockFetishes(Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST);
		registerLonerStockFetish(Fetish.FETISH_BIMBO);
		registerLonerStockFetish(Fetish.FETISH_CROSS_DRESSER);
		/*
		if(Main.game.isIncestEnabled()) {
			registerLonerStockFetish(Fetish.FETISH_MASTURBATION);
			registerLonerStockFetish(Fetish.FETISH_INCEST);
			if(Main.game.isPenetrationLimitationsEnabled()) {
				registerLonerStockFetish(Fetish.FETISH_SIZE_QUEEN);
			}
		} else {
			if(Main.game.isPenetrationLimitationsEnabled()) {
				registerLonerStockFetish(Fetish.FETISH_MASTURBATION);
				registerLonerStockFetish(Fetish.FETISH_SIZE_QUEEN);
			} else {
				registerLonerStockFetish(Fetish.FETISH_MASTURBATION);
			}
		*/
		registerLonerStockFetish(Fetish.FETISH_MASTURBATION);
		if (Main.game.isIncestEnabled()) {
			registerLonerStockFetish(Fetish.FETISH_INCEST);
		}
		if (Main.game.isPenetrationLimitationsEnabled()) {
			registerLonerStockFetish(Fetish.FETISH_SIZE_QUEEN);
		}
	}
	public List<FetishGroup> getAllFetishGroups() {
		if(allFetishGroups == null) {
			allFetishGroups = new ArrayList<FetishGroup>();
			initStockFetishes();
			this.pluginLoader.forEachPlugin(p -> allFetishGroups.addAll(p.getFetishGroups()));
		}
		return allFetishGroups;
	}
}
