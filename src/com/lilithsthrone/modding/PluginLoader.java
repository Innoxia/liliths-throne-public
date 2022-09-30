package com.lilithsthrone.modding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.modding.fetishes.FetishGroup;
import com.lilithsthrone.modding.fetishes.FetishLoader;
import com.lilithsthrone.modding.perks.PerkLoader;

public final class PluginLoader {
	private static String MOD_DIR = "res/mods";
	private static String PLUGIN_DIR = "plugins";
	private static String PLUGIN_META_RESOURCE = "PLUGIN_METADATA.xml";
	/*
	 * PLUGIN_METADATA.xml
	 */

	private static PluginLoader INSTANCE = null;

	private Set<BasePlugin> plugins = new HashSet<BasePlugin>();
	private Map<UUID,BasePlugin> uuidToPlugin = new HashMap<UUID,BasePlugin>();

	private File modDir;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder docBuilder;
	
	private FetishLoader fetishes;
	private PerkLoader perks;

	public static PluginLoader getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PluginLoader();
		return INSTANCE;
	}

	public PluginLoader() {
		System.err.println("Plugin System initializing...");
		
		this.fetishes = new FetishLoader(this);
		this.perks = new PerkLoader(this);
		
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
		System.err.println("SHA256: "+GetSHA256ChecksumOfFile(pluginFile));

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
		this.uuidToPlugin.put(plugin.metadata.id, plugin);
	}
	
	public BasePlugin getPluginByUUID(String uuidAsString) {
		return getPluginByUUID(UUID.fromString(uuidAsString));
	}

	public BasePlugin getPluginByUUID(UUID uuid) {
		return this.uuidToPlugin.get(uuid);
	}
	
	public Set<BasePlugin> getPlugins() {
		return plugins;
	}

	public void forEachPlugin(Consumer<? super BasePlugin> callback) {
		plugins.forEach(callback);
	}

	//////////////////////////////////////////////////
	// Fetishes
	//////////////////////////////////////////////////
	public FetishLoader getFetishes() { return fetishes; }
	public List<FetishGroup> getAllFetishGroups() {
		return fetishes.getAllFetishGroups();
	}

	//////////////////////////////////////////////////
	// PERKS
	//////////////////////////////////////////////////
	public PerkLoader getPerks() { return perks; }


	//////////////////////////////////////////////////
	// SIGNALLING
	//////////////////////////////////////////////////

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
		this.fetishes.addToPairedFetishMap(pairedFetishMap);
	}

	public void addToUnpairedFetishMap(Map<AbstractFetish, Boolean> unpairedFetishMap) {
		this.fetishes.addToUnpairedFetishMap(unpairedFetishMap);
	}

	public void onSexActionFetishesForEitherPartner(SexAction sexAction, GameCharacter characterPerformingAction,
			Map<GameCharacter, Set<AbstractFetish>> characterFetishes,
			Map<GameCharacter, Set<AbstractFetish>> characterFetishesForPartner, List<CoverableArea> cummedOnList) {
	}

	public void onNPCGenerateTransformativePotion(NPC npc, GameCharacter target,
			List<PossibleItemEffect> possibleEffects) {
		plugins.forEach(p -> p.onNPCGenerateTransformativePotion(npc, target, possibleEffects));
	}

	public void registerRelatedStockFetishes(AbstractFetish dom, AbstractFetish sub) {
		this.fetishes.registerRelatedStockFetishes(dom,sub);
	}

	public void registerLonerStockFetish(AbstractFetish f) {
		this.fetishes.registerLonerStockFetish(f);
	}

	public Set<BasePlugin> getAllPlugins() {
		return plugins;
	}

	public void onGenerateSexChoicesAddSexTypes(GameCharacter ctx, boolean resetPositioningBan, GameCharacter target,
			List<SexType> request, Map<SexType, Integer> foreplaySexTypes, Map<SexType, Integer> mainSexTypes) {
		plugins.forEach(p -> p.onGenerateSexChoicesAddSexTypes(ctx, resetPositioningBan, target, request,
				foreplaySexTypes, mainSexTypes));
	}

}
