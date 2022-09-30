package com.lilithsthrone.modding.perks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.modding.BasePlugin;
import com.lilithsthrone.modding.PluginLoader;

public class PerkLoader {
	private PluginLoader pluginLoader;

	private List<AbstractPerk> stockPerks = null;
	private List<AbstractPerk> providedPerks = null;
	private List<AbstractPerk> allPerks = null;
	
	public PerkLoader(PluginLoader pluginLoader) {
		this.pluginLoader = pluginLoader;
	}
	
	public List<AbstractPerk> getStock() {
		if (stockPerks == null) {
			stockPerks = new ArrayList<>();
			Field[] fields = Perk.class.getFields();
			for (Field f : fields) {
				if (AbstractPerk.class.isAssignableFrom(f.getType())) {
					AbstractPerk perk;
					try {
						perk = ((AbstractPerk) f.get(null));
						stockPerks.add(perk);
						Perk.addPerk(null, f.getName(), perk);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			Perk.generateSubspeciesPerks();
		}
		return stockPerks;
	}
	
	public List<AbstractPerk> getProvided() {
		if (providedPerks == null) {
			providedPerks = new ArrayList<AbstractPerk>();
			for(BasePlugin p : pluginLoader.getAllPlugins()) {
				for(AbstractPerk P : p.getPerks()) {
					providedPerks.add(P);
					Perk.addPerk(p, P.getID(), P);
				}
			}
		}
		return providedPerks;
	}

	public List<AbstractPerk> getAll() {
		if (allPerks == null) {
			allPerks = new ArrayList<AbstractPerk>();
			allPerks.addAll(getStock());
			allPerks.addAll(getProvided());
			System.err.println("Discovered Perks");
			System.err.println("---------------------------------------------------------");
			for(AbstractPerk f : allPerks) {
				System.err.println(String.format("%s - %s (%s)", f.getClass().getName(), f.getID(), f.getName(null)));
			}
			System.err.println("---------------------------------------------------------");
		}
		return allPerks;
	}

}
