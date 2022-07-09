/**
 * 
 */
package com.lilithsthrone.modding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.modding.fetishes.FetishGroup;
import com.lilithsthrone.modding.fetishes.LooseFetishGroup;
import com.lilithsthrone.modding.fetishes.RelatedFetishGroup;

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

	public List<AbstractFetish> fetishes = new ArrayList<AbstractFetish>();
	private List<FetishGroup> fetishGroups = new ArrayList<FetishGroup>();

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
	public void onPluginsLoaded() {
	}

	/**
	 * Do stuff in here to start up this plugin
	 */
	public void onStartup() {
	}

	/**
	 * Declare two fetishes related by a dominant(top)/submissive(bottom)
	 * relationship.
	 */
	protected final RelatedFetishGroup declareRelatedFetishes(AbstractFetish dominant, AbstractFetish submissive) {
		RelatedFetishGroup rfg = new RelatedFetishGroup(dominant, submissive);
		fetishGroups.add(rfg);
		this.fetishes.add(dominant);
		this.fetishes.add(submissive);
		return rfg;
	}

	/**
	 * Declare a fetish that is unrelated to other fetishes.
	 */
	protected final LooseFetishGroup declareLooseFetish(AbstractFetish loner) {
		return this.declareLooseFetish(loner, true);
	}

	/**
	 * Declare a fetish that is unrelated to other fetishes.
	 */
	protected final LooseFetishGroup declareLooseFetish(AbstractFetish loner, boolean forceOnVictims) {
		LooseFetishGroup lfg = new LooseFetishGroup(loner, forceOnVictims);
		fetishGroups.add(lfg);
		this.fetishes.add(loner);
		return lfg;
	}

	/**
	 * @return All AbstractFetishes defined by this plugin.
	 */
	public final List<AbstractFetish> getFetishes() {
		return fetishes;
	}

	public final List<FetishGroup> getFetishGroups() {
		return fetishGroups;
	}

	public void addPerks(Set<AbstractPerk> providedPerks) {
	}

	/**
	 * Set up NPCs here.
	 * 
	 * @param addedNpcs
	 */
	public void onInitUniqueNPCs(List<Class<? extends NPC>> addedNpcs) {
	}

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

	/**
	 * @see com.lilithsthrone.game.sex.sexActions.SexActionInterface::getRelatedFetishes
	 */
	public void getRelatedFetishes(List<AbstractFetish> fetishes, GameCharacter characterPerforming,
			GameCharacter characterTargeted, boolean isPenetration, boolean isOrgasm) {
	}

	/**
	 * @see com.lilithsthrone.game.character.CharacterUtils.generateDesires(GameCharacter)
	 */
	public void onGenerateDesiresAvailableFetishesFixup(GameCharacter character,
			List<AbstractFetish> availableFetishes) {
	}

	/**
	 * Tweak character desires here.
	 * 
	 * @see com.lilithsthrone.game.character.CharacterUtils.generateDesires(GameCharacter)
	 */
	public void onAfterGenerateDesires(GameCharacter character, List<AbstractFetish> availableFetishes,
			Map<AbstractFetish, Integer> desireMap, Map<AbstractFetish, Integer> negativeMap, int desiresAssigned) {
	}

	public void onNPCGenerateTransformativePotion(NPC npc, GameCharacter target,
			List<PossibleItemEffect> possibleEffects) {
	}
}
