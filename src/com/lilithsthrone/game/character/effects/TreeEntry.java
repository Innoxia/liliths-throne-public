package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.99
 * @version 0.3.4
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(o instanceof TreeEntry){
			try {
				if(((TreeEntry<T, S>)o).getRow() == (this.getRow())) {
					if(((TreeEntry<T, S>)o).getCategory()==(this.getCategory())) {
						if(((TreeEntry<T, S>)o).getEntry().equals(this.getEntry())) {
//							System.out.println("ok");
//						&& ((TreeEntry<T, S>)o).getLinks().size() == (this.getLinks().size())
							return true;
						}
					}
				}
			} catch(Exception ex) {
//				System.out.println("broken");
				return false;
			}
			return false;
		}
//		System.out.println(o.getClass().getSimpleName());
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getRow();
		result = 31 * result + this.getCategory().hashCode();
		result = 31 * result + this.getEntry().hashCode();
//		result = 31 * result + this.getLinks().size();
		return result;
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

	public void setLinks(List<TreeEntry<T, S>> links) {
		this.links = new ArrayList<>(links);
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
