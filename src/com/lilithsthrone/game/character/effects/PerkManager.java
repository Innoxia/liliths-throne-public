package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * Singleton enforced by Enum.
 * 
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public enum PerkManager {

	MANAGER;

	private StringBuilder treeSB = new StringBuilder();
	private StringBuilder lineSB = new StringBuilder();
	private StringBuilder entrySB = new StringBuilder();
	private Map<Integer, Map<PerkCategory, List<PerkEntry>>> perkTree;
	public static final int ROWS = 20;
	
	@SuppressWarnings("unused")
	private PerkManager() {
		perkTree = new HashMap<>();
		
		// Initialise perkTree:
		for(int i = 0; i<ROWS; i++) {
			perkTree.put(i, new HashMap<>());
			for(PerkCategory category : PerkCategory.values()) {
				perkTree.get(i).put(category, new ArrayList<>());
			}
		}
		
		PerkEntry arcane1, arcane2, arcane3;
		PerkEntry physical1, physical2, physical3, physical4;
		PerkEntry both1, both2, both3;
		
		physical1 = addPerkEntry(PerkCategory.PHYSICAL, 0, Perk.PHYSICAL_BASE);
		arcane1 = addPerkEntry(PerkCategory.ARCANE, 0, Perk.ARCANE_BASE);
		both1 = addPerkEntry(PerkCategory.BOTH, 0, Perk.SEDUCTION_1, physical1, arcane1);
		
		//Physical:
		physical2 = addPerkEntry(PerkCategory.PHYSICAL, 1, Perk.STRENGTH_1, physical1);
		addPerkEntry(PerkCategory.PHYSICAL, 1, Perk.OBSERVANT, physical1);
		
		physical1 = addPerkEntry(PerkCategory.PHYSICAL, 2, Perk.BRAWLER, physical2);
		physical3 = addPerkEntry(PerkCategory.PHYSICAL, 2, Perk.RUNNER, physical2);
		physical4 = addPerkEntry(PerkCategory.PHYSICAL, 2, Perk.STRENGTH_1, physical3);
		addPerkEntry(PerkCategory.PHYSICAL, 2, Perk.RUNNER_2, physical4);

		physical2 = addPerkEntry(PerkCategory.PHYSICAL, 3, Perk.STRENGTH_1, physical1, physical3);

		// Arcane:
		
		
		// Both:

		both2 = addPerkEntry(PerkCategory.BOTH, 1, Perk.SEDUCTION_3, both1);
		both3 = addPerkEntry(PerkCategory.BOTH, 2, Perk.SEDUCTION_5, both2);

		addPerkEntry(PerkCategory.BOTH, 3, Perk.NYMPHOMANIAC, both3);
		
		
		
		
		
		
		
//		b1 = addPerkEntry(PerkCategory.PHYSICAL, 1, Perk.BARREN, a1);
//		b2 = addPerkEntry(PerkCategory.PHYSICAL, 1, Perk.ACCURATE, a1);
//		b3 = addPerkEntry(PerkCategory.PHYSICAL, 1, Perk.RUNNER, a1);
//		
//		a1 = addPerkEntry(PerkCategory.PHYSICAL, 2, Perk.ACCURATE, b2, b1);
//		a2 = addPerkEntry(PerkCategory.PHYSICAL, 2, Perk.RUNNER, b3, a1);
//
//		b1 = addPerkEntry(PerkCategory.PHYSICAL, 3, Perk.RUNNER, a1);
//
//		
//		c1 = addPerkEntry(PerkCategory.BOTH, 1, Perk.SEDUCTION, b3);
		
		
//		a1 = addPerkEntry(PerkCategory.ARCANE, 0, Perk.ARCANE_BASE);

//		b1 = addPerkEntry(PerkCategory.ARCANE, 1, Perk.SPELL_POWER_1, a1, c1);
//		
//		addPerkEntry(PerkCategory.ARCANE, 2, Perk.SPELL_POWER_2, b1);
		
	}
	
	private PerkEntry addPerkEntry(PerkCategory category, int row, Perk perk, PerkEntry... links) {
		PerkEntry entry = new PerkEntry(category, row, perk);
		perkTree.get(row).get(category).add(entry);
		
		for(PerkEntry linkEntry : links) {
			entry.addLink(linkEntry);
		}
		
		return entry;
	}
	
	
	public String getPerkTreeDisplay() {
		treeSB.setLength(0);
		
		treeSB.append("<div class='container-full-width'>"
				+ "<h6 style='text-align:center;'>Perk Points Available: "+Main.game.getPlayer().getPerkPoints()+"</h6>");
		
		for(int i = 0; i<ROWS; i++) {
			treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; display: -ms-flex; display: -webkit-flex; display: flex;'>");
			appendPerkColumn(i, PerkCategory.PHYSICAL);
			appendPerkColumn(i, PerkCategory.BOTH);
			appendPerkColumn(i, PerkCategory.ARCANE);
			treeSB.append("</div>");
		}
		
		treeSB.append("</div>");
		
		return treeSB.toString();
	}
	
	private void appendPerkColumn(int row, PerkCategory category) {
		treeSB.append("<div class='container-full-width' style='width: 33.3%; padding:0; margin:0; flex:1;'>");
		
		List<PerkEntry> perkList = perkTree.get(row).get(category);
		int size = perkList.size();
		
		treeSB.append(getHorizontalLine(perkList));
		for(PerkEntry entry : perkList) {
			treeSB.append(getPerkEntry(entry, size));
		}
			
		treeSB.append("</div>");
	}
	

	private String getHorizontalLine(List<PerkEntry> perkList) {
		lineSB.setLength(0);
		
		for(PerkEntry entry : perkList) {
			float entryX = getX(entry.getRow(), entry);
			for(PerkEntry siblingEntry : entry.getSiblingLinks()) {
				float siblingX = getX(siblingEntry.getRow(), siblingEntry) + siblingDifference(entry.getCategory(), siblingEntry.getCategory());
				lineSB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
						+ "<svg width='100%' height='100%'><line x1='"+siblingX+"%' y1='50%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineSiblingColour(entry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
			}
			for(PerkEntry parentEntry : entry.getParentLinks()) {
				float parentX = getX(parentEntry.getRow(), parentEntry);
				String colour = getPerkLineParentColour(entry).toWebHexString();
						
				if(Math.abs(entryX-parentX)>0.01f) {
					lineSB.append("<div style='width:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events: none;'>"
							+ "<svg style='padding:0; margin:0;' width='100%'><line x1='"+entryX+"%' y1='0' x2='"+parentX+"%' y2='0' stroke='"+colour+"' stroke-width='4px'/></svg></div>");
				}
			}
		}
		
		return lineSB.toString();
	}
	
	private float siblingDifference(PerkCategory entryCategory, PerkCategory siblingCategory) {
		float entryX = 0;
		float siblingX = 0;
		
		switch(entryCategory) {
			case ARCANE:
				entryX = 300;
				break;
			case BOTH:
				entryX = 200;
				break;
			case PHYSICAL:
				entryX = 100;
				break;
		}
		switch(siblingCategory) {
			case ARCANE:
				siblingX = 300;
				break;
			case BOTH:
				siblingX = 200;
				break;
			case PHYSICAL:
				siblingX = 100;
				break;
		}
		
		return siblingX-entryX;
	}
	
	private float getMargin(int size) {
		return (100-(size*16))/(size*2f);
	}
	
	private float getX(int row, PerkEntry entry) {
		List<PerkEntry> list = perkTree.get(row).get(entry.getCategory());
		int size = list.size();
		float marginSize = getMargin(size);
		int column = list.indexOf(entry);
		
		return ((marginSize*(column)*2)+(column*16)+8+marginSize);
	}
	
	private String getPerkEntry(PerkEntry perkEntry, int size) {
		
		entrySB.setLength(0);
		
		boolean disabled = !isPerkOwned(perkEntry) && !isPerkAvailable(perkEntry);
		
		// Append up/down lines:
		float entryX = getX(perkEntry.getRow(), perkEntry);
		if(!perkEntry.getParentLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='0%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineParentColour(perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		if(!perkEntry.getChildLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='100%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineChildColour(perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		
		entrySB.append("<div class='square-button"+(perkEntry.getPerk().isMajor()?"":" round")+(disabled?" disabled":"")+"' style='width:16%; margin:16px "+getMargin(size)+"%; "+
							(isPerkOwned(perkEntry)?"border-color:"+perkEntry.getCategory().getColour().toWebHexString()+";":"")+"' id='"+perkEntry.getRow()+"_"+perkEntry.getPerk()+"'>"
				+ "<div class='square-button-content'>"+perkEntry.getPerk().getSVGString()+"</div>"
				+ (disabled
					?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; "+(perkEntry.getPerk().isMajor()?"border-radius:5px;":" border-radius:50%;")+"'></div>"
					:!isPerkOwned(perkEntry)
						?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; "+(perkEntry.getPerk().isMajor()?"border-radius:5px;":" border-radius:50%;")+"'></div>"
						:"")
			+ "</div>");
		
		return entrySB.toString();
	}
	
	public boolean isPerkOwned(PerkEntry perkEntry) {
		return Main.game.getPlayer().hasPerk(perkEntry.getRow(), perkEntry.getPerk());
	}
	
	public boolean isPerkOwned(int row, Perk perk) {
		return Main.game.getPlayer().hasPerk(row, perk);
	}
	
	public boolean isPerkAvailable(PerkEntry perkEntry) {
		if(!isPerkOwned(perkEntry)) {
			for(PerkEntry linkedEntry : perkEntry.getLinks()) {
				if(Main.game.getPlayer().hasPerk(linkedEntry.getRow(), linkedEntry.getPerk())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isPerkAvailable(int row, Perk perk) {
		if(!isPerkOwned(row, perk)) {
			PerkEntry perkEntry = null;
			loopy:
			for(Entry<PerkCategory, List<PerkEntry>> entry : perkTree.get(row).entrySet()) {
				for(PerkEntry pe : entry.getValue()) {
					if(pe.getPerk()==perk) {
						perkEntry = pe;
						break loopy;
					}
				}
			}
			if(perkEntry==null) {
				return false;
			}
			for(PerkEntry linkedEntry : perkEntry.getLinks()) {
				if(Main.game.getPlayer().hasPerk(linkedEntry.getRow(), linkedEntry.getPerk())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Colour getPerkLineParentColour(PerkEntry entry) {
		boolean parentOwned = false;
		for(PerkEntry parent : entry.getParentLinks()) {
			if(Main.game.getPlayer().hasPerk(parent.getRow(), parent.getPerk())) {
				parentOwned = true;
				break;
			}
		}
		
		return isPerkOwned(entry) && parentOwned
				?entry.getCategory().getColour()
				:isPerkAvailable(entry)
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	private Colour getPerkLineChildColour(PerkEntry entry) {
		boolean childOwned = false;
		boolean childAvailable = false;
		for(PerkEntry child : entry.getChildLinks()) {
			if(Main.game.getPlayer().hasPerk(child.getRow(), child.getPerk())) {
				childOwned = true;
			}
			if(isPerkAvailable(child)) {
				childAvailable = true;
			}
		}
		
		return isPerkOwned(entry) && childOwned
				?entry.getCategory().getColour()
				:childAvailable
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	private Colour getPerkLineSiblingColour(PerkEntry entry) {
		boolean siblingOwned = false;
		boolean siblingAvailable = false;
		for(PerkEntry sibling : entry.getSiblingLinks()) {
			if(Main.game.getPlayer().hasPerk(sibling.getRow(), sibling.getPerk())) {
				siblingOwned = true;
			}
			if((isPerkAvailable(sibling) && isPerkOwned(entry)) || (isPerkAvailable(entry) && isPerkOwned(sibling))) {
				siblingAvailable = true;
			}
		}
		
		return isPerkOwned(entry) && siblingOwned
				?entry.getCategory().getColour()
				:siblingAvailable
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	public int getPerkRow(Perk perk) {
		for(int i=0; i<ROWS; i++) {
			for(PerkCategory category : PerkCategory.values()) {
				for(PerkEntry entry : perkTree.get(i).get(category)) {
					if(entry.getPerk()==perk) {
						return i;
					}
				}
			}
		}
		System.err.println("PerkManager.getPerkRow(): Could not find Perk in any row! "+perk);
		return 0;
	}

	public Map<Integer, Map<PerkCategory, List<PerkEntry>>> getPerkTree() {
		return perkTree;
	}
}
