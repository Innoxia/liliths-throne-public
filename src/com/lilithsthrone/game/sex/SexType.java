package com.lilithsthrone.game.sex;

import java.io.Serializable;

/**
 * @since 0.1.53
 * @version 0.1.78
 * @author Innoxia
 */
public class SexType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private PenetrationType penetrationType;
	private OrificeType orificeType;

	public SexType(PenetrationType penetrationType, OrificeType orificeType) {
		this.penetrationType=penetrationType;
		this.orificeType = orificeType;
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof SexType){
			if(((SexType)o).getPenetrationType().equals(getPenetrationType())
				&& ((SexType)o).getOrificeType().equals(getOrificeType())){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getPenetrationType().hashCode();
		result = 31 * result + getOrificeType().hashCode();
		return result;
	}
	
	public String getName() {
		return "";
	}

	public PenetrationType getPenetrationType() {
		return penetrationType;
	}

	public OrificeType getOrificeType() {
		return orificeType;
	}
}
