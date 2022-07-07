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
import java.util.Collection;
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
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.sex.sexActions.SexAction;

public final class PluginLoader {
	private static String MOD_DIR = "res/mods";
	private static String PLUGIN_DIR = "plugins";
	private static String PLUGIN_META_RESOURCE = "PLUGIN_METADATA.xml";
	/*
	 * PLUGIN_METADATA.xml
	 */

	private static PluginLoader INSTANCE = null;

	private HashSet<BasePlugin> plugins = new HashSet<BasePlugin>();
	HashSet<UUID> loadedPlugins = new HashSet<UUID>();

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
		System.err.println(GetSHA256ChecksumOfFile(pluginFile));

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
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addPlugin(BasePlugin plugin) {
		this.plugins.add(plugin);
	}

	private Set<AbstractFetish> stockFetishes = null;

	public Collection<? extends AbstractFetish> getStockFetishes() {
		if (stockFetishes == null) {
			stockFetishes = new HashSet<>();
			Field[] fields = Perk.class.getFields();
			for (Field f : fields) {
				if (AbstractFetish.class.isAssignableFrom(f.getType())) {

					AbstractFetish fetish;

					try {
						fetish = ((AbstractFetish) f.get(null));
						stockFetishes.add(fetish);
						Fetish.addFetish(f.getName(), fetish);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return stockFetishes;
	}

	private Set<AbstractFetish> providedFetishes = null;

	public Collection<? extends AbstractFetish> getProvidedFetishes() {
		if (providedFetishes == null) {
			providedFetishes = new HashSet<AbstractFetish>();
			plugins.forEach(p -> p.addFetishes(providedFetishes));
			for (AbstractFetish f : providedFetishes)
				Fetish.addFetish(f.getID(), f);
		}
		return providedFetishes;
	}

	private Set<AbstractFetish> allFetishes = null;

	public Collection<? extends AbstractFetish> getAllFetishes() {
		if (allFetishes == null) {
			allFetishes = new HashSet<AbstractFetish>();
			allFetishes.addAll(getStockFetishes());
			allFetishes.addAll(getProvidedFetishes());
		}
		return allFetishes;
	}

	public void forEachPlugin(Consumer<? super BasePlugin> callback) {
		plugins.forEach(callback);
	}

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
		plugins.forEach(p -> p.addToPairedFetishMap(pairedFetishMap));
	}

	public void addToUnpairedFetishMap(Map<AbstractFetish, Boolean> unpairedFetishMap) {
		plugins.forEach(p -> p.addToUnpairedFetishMap(unpairedFetishMap));
	}

	public void appendPhoneFetishRows(StringBuilder journalSB, DialogueNode dialogueNode) {
		plugins.forEach(p -> p.appendPhoneFetishRows(journalSB, dialogueNode));
	}

	public void onSexActionFetishesForEitherPartner(SexAction sexAction, GameCharacter characterPerformingAction,
			Map<GameCharacter, Set<AbstractFetish>> characterFetishes,
			Map<GameCharacter, Set<AbstractFetish>> characterFetishesForPartner, List<CoverableArea> cummedOnList) {
	}

	public void onNPCGenerateTransformativePotion(NPC npc, GameCharacter target,
			List<PossibleItemEffect> possibleEffects) {
		plugins.forEach(p -> p.onNPCGenerateTransformativePotion(npc, target, possibleEffects));
	}

}
