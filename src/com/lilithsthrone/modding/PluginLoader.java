package com.lilithsthrone.modding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.modding.fetishes.FetishGroup;
import com.lilithsthrone.modding.fetishes.LooseFetishGroup;
import com.lilithsthrone.modding.fetishes.RelatedFetishGroup;
import com.lilithsthrone.utils.Util;

public final class PluginLoader {
	private static String MOD_DIR = "res/mods";
	private static String PLUGIN_DIR = "plugins";
	private static String PLUGIN_META_RESOURCE = "PLUGIN_METADATA.xml";
	/*
	 * PLUGIN_METADATA.xml
	 */

	private static PluginLoader INSTANCE = null;

	private HashSet<BasePlugin> plugins = new HashSet<BasePlugin>();
	private HashSet<UUID> loadedPlugins = new HashSet<UUID>();

	private File modDir;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder docBuilder;

	public static PluginLoader getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PluginLoader();
		return INSTANCE;
	}

	public PluginLoader() {
		System.err.println("Plugin System initializing...");
		this.dbf = DocumentBuilderFactory.newInstance();
		try {
			this.docBuilder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.modDir = new File(MOD_DIR);
		if (!this.modDir.exists()) {
			System.err.println("Plugin loader failed to initialize (" + MOD_DIR.toString() + " does not exist)");
			return;
		}
		if (!this.modDir.isDirectory()) {
			System.err.println("Plugin loader failed to initialize (" + MOD_DIR.toString() + " is not a directory)");
			return;
		}
		// String authorId;
		File pluginDir;
		for (File subDir : this.modDir.listFiles(File::isDirectory)) {
			// authorId = subDir.getName();
			pluginDir = subDir.toPath().resolve(PLUGIN_DIR).toFile();
			if (pluginDir.isDirectory()) {
				for (File pluginFile : pluginDir.listFiles(File::isFile)) {
					tryLoadingPluginFromFile(pluginFile);
				}
			}
		}

		HashSet<String> availableTags = new HashSet<String>();
		HashSet<BasePlugin> pluginsToLoad = new HashSet<BasePlugin>(plugins);
		for (int i = 0; i < 100; i++) {
			for (BasePlugin plugin : new HashSet<BasePlugin>(pluginsToLoad)) {
				if (plugin.isSatisfied(availableTags)) {
					plugin.onStartup();
					availableTags.addAll(plugin.getProvidedTags());
					pluginsToLoad.remove(plugin);
					if (pluginsToLoad.isEmpty()) {
						System.err.println("---------------------------------------------------------");
						onPluginsLoaded();
						return;
					}
				}
			}
		}
		System.err.println("---------------------------------------------------------");
		System.err.println("Failed to load plugins (could not satisfy dependencies within 100 iterations):");
		for (BasePlugin plug : pluginsToLoad) {
			System.err.println(plug.metadata.id + ": " + plug.metadata.name);
		}
	}

	private String GetSHA256ChecksumOfFile(File file) {
		try {
			return getChecksumOfFile(file, MessageDigest.getInstance("SHA-256"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "[BUG: SEE ERROR LOG]";
		}
	}

	private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

	private String byte2hex(byte[] bytes) {
		char[] c = new char[bytes.length * 2];
		int v = 0;
		for (int i = 0; i < bytes.length; i++) {
			v = bytes[i] & 0xFF; // Mask off anything other than the last 8 bits
			c[i * 2] = HEX_CHARS[v >>> 4]; // Shift down four bits, truncating the lower four
			c[i * 2 + 1] = HEX_CHARS[v & 0x0f]; // Mask off the upper four bits.
		}
		return new String(c);
	}

	private String getChecksumOfFile(File file, MessageDigest algorithm) {
		algorithm.reset();
		try (DigestInputStream dis = new DigestInputStream(new FileInputStream(file), algorithm)) {
			while (dis.read() != -1) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "[BUG: SEE ERROR LOG]";
		}
		return byte2hex(algorithm.digest());
	}

	private void tryLoadingPluginFromFile(File pluginFile) {
		URLClassLoader ucl;
		try {
			ucl = URLClassLoader.newInstance(new URL[] { pluginFile.toURI().toURL() });
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

		PluginMetadata meta = new PluginMetadata();
		try (InputStream is = ucl.getResourceAsStream(PLUGIN_META_RESOURCE)) {
			// I would so much like to use JSON or something, but people here are used to
			// XML :(
			Document doc = docBuilder.parse(is);

			// Cast magic:
			doc.getDocumentElement().normalize();
			meta.loadFromElement((Element) doc.getElementsByTagName("plugin").item(0));
		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		System.err.println("---------------------------------------------------------");
		System.err.println(String.format("%s v%s (%s)", meta.name, meta.version, meta.id));
		System.err.println("Hash: "+GetSHA256ChecksumOfFile(pluginFile));

		Class<?> cls = null;
		try {
			cls = Class.forName(meta.mainClass, true, ucl);
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace(); // TODO
			return;
		}

		if (BasePlugin.class.isAssignableFrom(cls)) {
			@SuppressWarnings("unchecked") // It's checked, wtf eclipse
			Class<? extends BasePlugin> mainClass = (Class<? extends BasePlugin>) cls;
			BasePlugin plugin;
			try {
				plugin = (BasePlugin) mainClass.getDeclaredConstructors()[0].newInstance();
				plugin.metadata = meta;
				this.addPlugin(plugin);
			} catch (InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException|SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addPlugin(BasePlugin plugin) {
		this.plugins.add(plugin);
	}

	//////////////////////////////////////////////////
	// Fetishes
	//////////////////////////////////////////////////

	private List<AbstractFetish> stockFetishes = null;
	public List<AbstractFetish> getStockFetishes() {
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

	private List<AbstractFetish> providedFetishes = null;
	public List<AbstractFetish> getProvidedFetishes() {
		if (providedFetishes == null) {
			providedFetishes = new ArrayList<AbstractFetish>();
			for(BasePlugin p : plugins) {
				for(AbstractFetish f : p.getFetishes()) {
					providedFetishes.add(f);
					Fetish.addFetish(p, f.getID(), f);
				}
			}
		}
		return providedFetishes;
	}


	private List<AbstractFetish> allFetishes = null;
	private HashSet<AbstractFetish> notIncludedInPotions = null;
	private List<FetishGroup> allFetishGroups = null;
	public List<AbstractFetish> getAllFetishes() {
		if (allFetishes == null) {
			allFetishes = new ArrayList<AbstractFetish>();
			allFetishes.addAll(getStockFetishes());
			allFetishes.addAll(getProvidedFetishes());
			System.err.println("Discovered Fetishes");
			System.err.println("---------------------------------------------------------");
			for(AbstractFetish f : allFetishes) {
				System.err.println(String.format("%s - %s", f.getClass().getName(), f.getName(null)));
			}
			System.err.println("---------------------------------------------------------");
		}
		return allFetishes;
	}

	//////////////////////////////////////////////////
	// PERKS
	//////////////////////////////////////////////////

	private List<AbstractPerk> stockPerks = null;
	public List<AbstractPerk> getStockPerks() {
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

	private List<AbstractPerk> providedPerks = null;
	public List<AbstractPerk> getProvidedPerks() {
		if (providedPerks == null) {
			providedPerks = new ArrayList<AbstractPerk>();
			for(BasePlugin p : plugins) {
				for(AbstractPerk P : p.getPerks()) {
					providedPerks.add(P);
					Perk.addPerk(p, P.getID(), P);
				}
			}
		}
		return providedPerks;
	}


	private List<AbstractPerk> allPerks = null;
	public List<AbstractPerk> getAllPerks() {
		if (allPerks == null) {
			allPerks = new ArrayList<AbstractPerk>();
			allPerks.addAll(getStockPerks());
			allPerks.addAll(getProvidedPerks());
			System.err.println("Discovered Perks");
			System.err.println("---------------------------------------------------------");
			for(AbstractPerk f : allPerks) {
				System.err.println(String.format("%s - %s (%s)", f.getClass().getName(), f.getID(), f.getName(null)));
			}
			System.err.println("---------------------------------------------------------");
		}
		return allPerks;
	}

	//////////////////////////////////////////////////
	// SIGNALLING
	//////////////////////////////////////////////////

	public void forEachPlugin(Consumer<? super BasePlugin> callback) {
		plugins.forEach(callback);
	}

	private void onPluginsLoaded() {
		plugins.forEach(p -> p.onPluginsLoaded());
	}

	public void onInitUniqueNPCs(List<Class<? extends NPC>> addedNpcs) {
		plugins.forEach(p -> p.onInitUniqueNPCs(addedNpcs));
	}

	public void onMainStart() {
		plugins.forEach(p -> p.onMainStart());
	}

	public void onInitPerks() {
		plugins.forEach(p -> p.onInitPerks());
	}

	public void onMainMain() {
		plugins.forEach(p -> p.onMainMain());
	}

	public void onGenerateDesiresAvailableFetishesFixup(GameCharacter character,
			List<AbstractFetish> availableFetishes) {
		plugins.forEach(p -> p.onGenerateDesiresAvailableFetishesFixup(character, availableFetishes));
	}

	public void onAfterGenerateDesires(GameCharacter character, List<AbstractFetish> availableFetishes,
			Map<AbstractFetish, Integer> desireMap, Map<AbstractFetish, Integer> negativeMap, int desiresAssigned) {
		plugins.forEach(
				p -> p.onAfterGenerateDesires(character, availableFetishes, desireMap, negativeMap, desiresAssigned));
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

	public void onSexActionFetishesForEitherPartner(SexAction sexAction, GameCharacter characterPerformingAction,
			Map<GameCharacter, Set<AbstractFetish>> characterFetishes,
			Map<GameCharacter, Set<AbstractFetish>> characterFetishesForPartner, List<CoverableArea> cummedOnList) {
	}

	public void onNPCGenerateTransformativePotion(NPC npc, GameCharacter target,
			List<PossibleItemEffect> possibleEffects) {
		plugins.forEach(p -> p.onNPCGenerateTransformativePotion(npc, target, possibleEffects));
	}

	private void registerRelatedStockFetishes(AbstractFetish dom, AbstractFetish sub) {
		if(!this.stockFetishes.contains(dom))
			this.stockFetishes.add(dom);
		if(!this.stockFetishes.contains(sub))
			this.stockFetishes.add(sub);
		this.allFetishGroups .add(new RelatedFetishGroup(dom,sub));
	}

	private void registerLonerStockFetish(AbstractFetish f) {
		if(!this.stockFetishes.contains(f))
			this.stockFetishes.add(f);
		this.allFetishGroups.add(new LooseFetishGroup(f, true));
	}
	private void initStockFetishes() {
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
			plugins.forEach(p -> allFetishGroups.addAll(p.getFetishGroups()));
		}
		return allFetishGroups;
	}

}
