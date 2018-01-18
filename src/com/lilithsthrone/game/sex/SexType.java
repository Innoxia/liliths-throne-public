package com.lilithsthrone.game.sex;

import java.io.Serializable;

/**
 * @since 0.1.53
 * @version 0.1.98
 * @author Innoxia
 */
public class SexType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private SexParticipantType asParticipant;
	private PenetrationType penetrationType;
	private OrificeType orificeType;

	public SexType(SexParticipantType asParticipant, PenetrationType penetrationType, OrificeType orificeType) {
		this.asParticipant = asParticipant;
		this.penetrationType=penetrationType;
		this.orificeType = orificeType;
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof SexType){
			if(((SexType)o).getAsParticipant().equals(getAsParticipant())
				&& ((SexType)o).getPenetrationType().equals(getPenetrationType())
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

	public SexParticipantType getAsParticipant() {
		return asParticipant;
	}

	public PenetrationType getPenetrationType() {
		return penetrationType;
	}

	public OrificeType getOrificeType() {
		return orificeType;
	}
}
