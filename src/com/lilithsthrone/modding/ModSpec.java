package com.lilithsthrone.modding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private final Pattern PATTERN_VERSION = Pattern.compile("^([<>=]{1,2}) ?(\\d+\\..+)$")

	public void setVersionSpec(String value) {
		// 1.2.3
		// >1.2.3
		Matcher m = PATTERN_VERSION.matcher(value);
		if(m != null && m.matches()) {
			String comparatorStr = m.group(1);
			String versionStr = m.group(2);
			for(ComparisonType ct : ComparisonType.values()) {
				if(comparatorStr.equals(ct.symbol)) {
					this.version = new Semver(versionStr);
					this.comparator = ct;
					return;
				}
			}
		}
		this.version = new Semver(value.strip());
		this.comparator = ComparisonType.EQUAL;
	}

	public String getSpecString() {
		String str = "";
		if(this.comparator != null) {
			str += comparator.symbol;
		}
		if(this.version != null) {
			str += version.toString();
		}
		return str;
	}
}
