package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.99
 * @version 0.2.4
 * @author Innoxia
 */
public class TreeEntry<T, S> {

	private int row;
	private List<TreeEntry<T, S>> links;
	private T category;
	private S entry;
	
	public TreeEntry(T category, int row, S perk) {
		this.category = category;
		this.row = row;
		this.links = new ArrayList<>();
		this.entry = perk;
	}

	public T getCategory() {
		return category;
	}
	
	public int getRow() {
		return row;
	}
	
	public List<TreeEntry<T, S>> getParentLinks() {
		List<TreeEntry<T, S>> parentLinks = new ArrayList<>(links);
		parentLinks.removeIf((e) -> e.getRow()>=this.getRow());
		return parentLinks;
	}
	
	public List<TreeEntry<T, S>> getChildLinks() {
		List<TreeEntry<T, S>> childLinks = new ArrayList<>(links);
		childLinks.removeIf((e) -> e.getRow()<=this.getRow());
		return childLinks;
	}
	
	public List<TreeEntry<T, S>> getSiblingLinks() {
		List<TreeEntry<T, S>> siblingLinks = new ArrayList<>(links);
		siblingLinks.removeIf((e) -> e.getRow()!=this.getRow());
		return siblingLinks;
	}
	
	public List<TreeEntry<T, S>> getLinks() {
		return links;
	}
	
	public void addLink(TreeEntry<T, S> entry) {
		if(!getLinks().contains(entry)) {
			links.add(entry);
		}

		if(!entry.getLinks().contains(this)) {
			entry.addLink(this);
		}
	}

	public S getEntry() {
		return entry;
	}
	
}
