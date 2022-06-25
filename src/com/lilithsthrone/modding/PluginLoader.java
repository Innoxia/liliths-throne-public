package com.lilithsthrone.modding;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.fetishes.Fetish;

public final class PluginLoader {
	private static String MOD_DIR = "res/mods";
	private static String PLUGIN_DIR = "plugins";
	private static String PLUGIN_META_RESOURCE = "/PLUGIN_METADATA.xml";

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
		this.dbf = DocumentBuilderFactory.newInstance();
		try {
			this.docBuilder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.modDir = new File(MOD_DIR);
		if (this.modDir.exists())
			return;
		if (!this.modDir.isDirectory())
			return;
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
						onPluginsLoaded();
						return;
					}
				}
			}
		}
		System.err.println("Failed to load plugins (could not satisfy dependencies within 100 iterations):");
		for(BasePlugin plug : pluginsToLoad) {
			System.err.println(plug.metadata.id+": "+plug.metadata.name);
		}
	}

	private void onPluginsLoaded() {
		for (BasePlugin plugin : plugins)
			plugin.onPluginsLoaded();
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
		}

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

	HashSet<Fetish> providedFetishes = null;
	public Collection<? extends Fetish> getFetishes() {
		if (providedFetishes == null) {
			providedFetishes = new HashSet<Fetish>();
			for (BasePlugin p : plugins)
				p.addFetishes(providedFetishes);
		}
		return providedFetishes;
	}
	
	HashSet<AbstractPerk> providedPerks = null;
	public Collection<? extends AbstractPerk> getPerks() {
		if (providedPerks == null) {
			providedPerks = new HashSet<AbstractPerk>();
			for (BasePlugin p : plugins)
				p.addPerks(providedPerks); // So mods can intercept other mods' perks
		}
		return providedPerks;
	}

	public void onInitUniqueNPCs() {
		for (BasePlugin p : plugins)
			p.onInitUniqueNPCs();
	}

	public void onMainStart() {
		for (BasePlugin p : plugins)
			p.onMainStart();
	}

}
