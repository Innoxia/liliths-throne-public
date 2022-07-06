/**
 * 
 */
package com.lilithsthrone.modding;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.utils.Util;

/**
 * @author Anon
 *
 */
public class BasePlugin {
    /**
     * Mod metadata (name, version, etc)
     */
    public PluginMetadata metadata;

    public HashSet<GameCharacter> providedGameCharacters = new HashSet<GameCharacter>();

    /**
     * Returns true when the plugin should be loaded.
     * 
     * @param loadedTags Currently loaded tags
     * @return Ready to load
     */
    public boolean isSatisfied(HashSet<String> loadedTags) {
        return loadedTags.containsAll(metadata.requires_tags);
    }

    /**
     * Returns the tags that this plugin provides to other plugins.
     */
    public Collection<? extends String> getProvidedTags() {
        return this.metadata.provides_tags;
    }

    /**
     * All plugins loaded
     */
    public void onPluginsLoaded() {}

    /**
     * Do stuff in here to start up this plugin
     */
    public void onStartup() {}

    public void addFetishes(Set<AbstractFetish> providedFetishes) {}

    public void addPerks(Set<AbstractPerk> providedPerks) {}

    /**
     * Set up NPCs here.
     * @param addedNpcs 
     */
    public void onInitUniqueNPCs(List<Class<? extends NPC>> addedNpcs) {}

    /**
     * Called after Main.start()
     */
    public void onMainStart() {}

    /**
     * Called after Perk static, but before hiddens are visually sorted.
     * 
     * Use Perks.addPerk.
     */
    public void onInitPerks() {}

    /**
     * Called after Main.main()
     */
    public void onMainMain() {}

	public void getRelatedFetishes(List<AbstractFetish> fetishes, GameCharacter characterPerforming,
			GameCharacter characterTargeted, boolean isPenetration, boolean isOrgasm) {}

	public void onGenerateDesiresAvailableFetishesFixup(GameCharacter character,
			List<AbstractFetish> availableFetishes) {}

	public void onAfterGenerateDesires(GameCharacter character, List<AbstractFetish> availableFetishes,
			Map<AbstractFetish, Integer> desireMap, Map<AbstractFetish, Integer> negativeMap, int desiresAssigned) {}

	public void addToPairedFetishMap(Map<AbstractFetish, AbstractFetish> pairedFetishMap) {}

	public void addToUnpairedFetishMap(Map<AbstractFetish, Boolean> unpairedFetishMap) {}

	public void appendPhoneFetishRows(StringBuilder journalSB, DialogueNode dialogueNode) {}
}
