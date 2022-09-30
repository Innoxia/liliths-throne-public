/**
 * 
 */
package com.lilithsthrone.modding;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.UUID;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class PluginMetadata {

	public UUID id;
    public String name;
    public PluginContributor[] contributors;
    public String url = null;
    public String license;
    public String mainClass;
    public String version;
    
    /**
     * Things that this plugin provides that other plugins can require
     * 
     * e.g. {"sex-position:butter-churner", "race:tanuki"}
     * 
     * There is no format enforced, although it would be nice if your tags were consistent. :)
     */
    public HashSet<String> provides_tags = new HashSet<String>();
    
    /**
     * Will wait to load until all the given tags are provided.
     */
    public HashSet<String> requires_tags = new HashSet<String>();

	public final void loadFromElement(Element pluginElement) {
		this.id = UUID.fromString(pluginElement.getAttribute("id"));
		this.version = pluginElement.getAttribute("version");
		this.name = pluginElement.getAttribute("name");
		if(pluginElement.hasAttribute("url"))
			this.url = pluginElement.getAttribute("url");
		this.license = pluginElement.getAttribute("license");
		this.mainClass = pluginElement.getAttribute("mainClass");
		
		HashSet<PluginContributor> ctors = new LinkedHashSet<PluginContributor>();
		NodeList contribElements = ((Element)pluginElement.getElementsByTagName("contribs").item(0)).getElementsByTagName("contrib");
		for(int i = 0;i<contribElements.getLength();i++) {
			PluginContributor contrib = PluginContributor.FromElement((Element) contribElements.item(i));
			ctors.add(contrib);
		}
		this.contributors = new PluginContributor[ctors.size()];
		ctors.toArray(this.contributors);

		NodeList requiredTagsElements = ((Element)pluginElement.getElementsByTagName("requiresTags").item(0)).getElementsByTagName("tag");
		for(int i = 0;i<requiredTagsElements.getLength();i++) {
			requires_tags.add(((Element) requiredTagsElements.item(i)).getTextContent());
		}
		
		NodeList providesTagsElements = ((Element)pluginElement.getElementsByTagName("providesTags").item(0)).getElementsByTagName("tag");
		for(int i = 0;i<providesTagsElements.getLength();i++) {
			provides_tags.add(((Element) providesTagsElements.item(i)).getTextContent());
		}
	}
}