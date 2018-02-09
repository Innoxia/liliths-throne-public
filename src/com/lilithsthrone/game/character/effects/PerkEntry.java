package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class PerkEntry {

	private PerkCategory category;
	private int row;
	private List<PerkEntry> links;
	private Perk perk;
	
	public PerkEntry(PerkCategory category, int row, Perk perk) {
		this.category = category;
		this.row = row;
		this.links = new ArrayList<>();
		this.perk = perk;
	}

	public PerkCategory getCategory() {
		return category;
	}
	
	public int getRow() {
		return row;
	}
	
	public List<PerkEntry> getParentLinks() {
		List<PerkEntry> parentLinks = new ArrayList<>(links);
		parentLinks.removeIf((e) -> e.getRow()>=this.getRow());
		return parentLinks;
	}
	
	public List<PerkEntry> getChildLinks() {
		List<PerkEntry> childLinks = new ArrayList<>(links);
		childLinks.removeIf((e) -> e.getRow()<=this.getRow());
		return childLinks;
	}
	
	public List<PerkEntry> getSiblingLinks() {
		List<PerkEntry> siblingLinks = new ArrayList<>(links);
		siblingLinks.removeIf((e) -> e.getRow()!=this.getRow());
		return siblingLinks;
	}
	
	public List<PerkEntry> getLinks() {
		return links;
	}
	
	public void addLink(PerkEntry entry) {
		if(!getLinks().contains(entry)) {
			links.add(entry);
		}

		if(!entry.getLinks().contains(this)) {
			entry.addLink(this);
		}
	}

	public Perk getPerk() {
		return perk;
	}
	
}
