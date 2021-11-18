package com.lilithsthrone.modding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.vdurmont.semver4j.Semver;

public class ModSpec {
	public enum ComparisonType {
		EQUAL("=="),
		GREATER(">"),
		GREATER_OR_EQUAL(">="),
		LESSER("<"),
		LESSER_OR_EQUAL("<="),
		NOT_EQUAL("!=");
		
		public String symbol = "";
		
		ComparisonType(String symbol) {
			this.symbol = symbol;
		}
	}
    public String modID = "";
    public Semver version = null;
    public ComparisonType comparator = null;
    
    public ModSpec() {}
    public ModSpec(String modID) {
    	this(modID, null, null);
    }
    public ModSpec(String modID, Semver version) {
    	this(modID, ComparisonType.EQUAL, version);
    }
	public ModSpec(String modID, ComparisonType comparator, Semver version) {
		this.modID = modID;
		this.comparator = comparator;
		this.version = version;
	}
	public static ModSpec loadFromXML(Element parentElement, Document doc) {
		ModSpec ms = new ModSpec();
		ms.modID = parentElement.getAttribute("id");
		if(parentElement.hasAttribute("version")) {
			ms.setVersionSpec(parentElement.getAttribute("version"));
		}
		return ms;
	}
	
	public void setVersionSpec(String value) {
		// 1.2.3
		// >1.2.3
		for(ComparisonType ct : ComparisonType.values()) {
			if(value.startsWith(ct.symbol)) {
				this.version = new Semver(value.substring(ct.symbol.length()).strip());
				this.comparator = ct;
			}
			return;
		}
		this.version = new Semver(value.strip());
		this.comparator = ComparisonType.EQUAL;
	}
}
