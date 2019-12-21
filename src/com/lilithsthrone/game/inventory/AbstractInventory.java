package com.lilithsthrone.game.inventory;

import java.util.*;
import java.util.function.Function;

/**
 * AbstractInventory serves to deduplicate code between the handling of Item, Clothing and Weapon in CharacterInventory.
 *
 * Package-privateness is a feature in that case.
 * @param <T>
 */
class AbstractInventory<T extends AbstractCoreItem, U extends AbstractCoreType> {
	private final Comparator<T> comparator;
	private final Function<T, U> typeRetriever;
	private Map<T, Integer> duplicateCounts;

	AbstractInventory(Comparator<T> comparator, Function<T, U> typeRetriever) {
		this.comparator = comparator;
		this.typeRetriever = typeRetriever;
		duplicateCounts = new LinkedHashMap<>();
	}

	AbstractInventory(AbstractInventory<T, U> inventoryToCopy) {
		this.comparator = inventoryToCopy.comparator;
		this.typeRetriever = inventoryToCopy.typeRetriever;
		duplicateCounts = new LinkedHashMap<>(inventoryToCopy.duplicateCounts);
	}
	
	public void clear() {
		duplicateCounts.clear();
	}

	boolean isEmpty() {
		return duplicateCounts.isEmpty();
	}

	public void sort() {
		if (duplicateCounts.size() < 2) {
			return;
		}
		List<T> itemsToSort = new ArrayList<>(duplicateCounts.keySet());
		itemsToSort.sort(comparator);

		Map<T, Integer> newlySortedMap = new LinkedHashMap<>();
		for(T item : itemsToSort) {
			newlySortedMap.put(item, duplicateCounts.get(item));
		}
		duplicateCounts = newlySortedMap;
	}

	public void transform(Function<T, T> transformFunction) {
		Map<T, Integer> oldMap = new LinkedHashMap<>(duplicateCounts);
		duplicateCounts.clear();

		for(Map.Entry<T, Integer> e : oldMap.entrySet()) {
			T oldItem = e.getKey();
			T newItem = transformFunction.apply(oldItem);
			addItem(newItem, e.getValue());
		}
	}

	/**
	 * @return a Non-modifiable Map
	 */
	Map<T, Integer> getDuplicateCounts() {
		return Collections.unmodifiableMap(duplicateCounts);
	}

	int getQuestEntryCount() {
		return (int) duplicateCounts.keySet().stream().filter(e -> e.getRarity().equals(Rarity.QUEST)).count();
	}

	public int getTotalItemCount() {
		return duplicateCounts.values().stream().mapToInt(e -> e).sum();
	}

	public int getItemCount(T item) {
		return duplicateCounts.getOrDefault(item, 0);
	}

	void addFromMap(Map<T, Integer> map) {
		map.forEach(this::addItem);
	}

	void addItem(T item, int count) {
		duplicateCounts.merge(item, count, Integer::sum);
	}

	boolean hasItem(T item) {
		return duplicateCounts.containsKey(item);
	}

	private boolean removeItem(T item) {
		return removeItem(item, 1);
	}

	boolean removeItem(T item, int count) {
		boolean hasItem = hasItem(item);
		if (hasItem) {
			int newValue = duplicateCounts.get(item) - count;
			if (newValue <= 0) {
				duplicateCounts.remove(item);
			} else {
				duplicateCounts.put(item, newValue);
			}
		}
		return hasItem;
	}

	private Optional<T> getItemByType(U type) {
		return duplicateCounts.keySet().stream().filter(item -> typeRetriever.apply(item).equals(type)).findAny();
	}

	private Optional<T> getItemByRarity(Rarity rarity) {
		return duplicateCounts.keySet().stream().filter(item -> typeRetriever.apply(item).getRarity().equals(rarity)).findAny();
	}

	boolean hasItemType(U itemType) {
		return getItemByType(itemType).isPresent();
	}

	boolean removeAllItemsByRarity(Rarity rarity) {
		boolean removed = getItemByRarity(rarity).map(this::removeItem).orElse(false);
		while(removed) {
			removed = getItemByRarity(rarity).map(this::removeItem).orElse(false);
		}
		return removed;
	}

	boolean removeItemByType(U itemType) {
		return getItemByType(itemType).map(this::removeItem).orElse(false);
	}
}
