package com.lilithsthrone.game.character.body;

import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.TesticleType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.2.7
 * @author Innoxia
 */
public class Testicle implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int MIN_TESTICLE_COUNT = 2;
	public static final int MAX_TESTICLE_COUNT = 8;

	public static final int MINIMUM_VALUE_FOR_ALL_CUM_TO_BE_EXPELLED = 5; //ml
	
	
	protected TesticleType type;
	protected int testicleSize;
	protected int cumStorage;
	protected float cumStored;
	protected int cumRegeneration;
	protected int testicleCount;
	protected int cumExpulsion;
	protected boolean internal;
	
	protected FluidCum cum;

	public Testicle(TesticleType type, int testicleSize, int cumStorage, int testicleCount) {
		this.type = type;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		this.cumStorage = cumStorage;
		cumStored = cumStorage;
		cumRegeneration = FluidRegeneration.ONE_AVERAGE.getValue();
		cumExpulsion = FluidExpulsion.THREE_LARGE.getMinimumValue();
		
		this.testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		internal = type.isInternal();
		
		cum = new FluidCum(type.getFluidType());
	}

	public FluidCum getCum() {
		return cum;
	}

	@Override
	public TesticleType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getTesticleCount()==2) {
			return "a pair of";
		} else if(gc.getTesticleCount()==3) {
			return "a trio of";
		} else {
			return Util.intToString(gc.getTesticleCount());
		}
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public void setType(GameCharacter owner, TesticleType type) {
		this.type = type;
		cum.setType(type.getFluidType());
	}
	
	public TesticleSize getTesticleSize() {
		return TesticleSize.getTesticleSizeFromInt(testicleSize);
	}

	public String setTesticleSize(GameCharacter owner, int testicleSize) {
		if(!owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int oldSize = this.testicleSize;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		int sizeChange = this.testicleSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.balls] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] [npc.balls] doesn't change...)]</p>");
			}
			
		} else if (sizeChange > 0) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(let)] out a lewd moan as [npc.she] [npc.verb(feel)] [npc.her] [npc.balls] suddenly swell and [style.boldGrow(grow larger)].<br/>"
					+ "[npc.She] now [npc.has] [style.boldSex(" + owner.getTesticleSize().getDescriptor() + " [pc.balls])]!");
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a surprised gasp as you feel your [pc.balls] suddenly [style.boldShrink(shrink)].<br/>"
							+ "You now have [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [pc.balls])]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] lets out a surprised gasp as [npc.she] feels [npc.her] [npc.balls] suddenly [style.boldShrink(shrink)].<br/>"
							+ "[npc.She] now has [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [npc.balls])]!"
						+ "</p>");
			}
		}
	}

	public int getTesticleCount() {
		return testicleCount;
	}

	public String setTesticleCount(GameCharacter owner, int testicleCount) {
		testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		if(owner.getTesticleCount() == testicleCount || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingTesticles = this.testicleCount > testicleCount;
		this.testicleCount = testicleCount;
		
		if(removingTesticles) {
			if(owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "A tingling feeling spreads down into your [pc.balls], and you let out a little cry as you feel some of them shrinking away and [style.boldShrink(disappearing)].<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_balls])]."
						+ "</p>");
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads down into [npc.namePos] [npc.balls], and [npc.she] lets out a little cry as [npc.she] feels some of them shrinking away and [style.boldShrink(disappearing)].<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_balls])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return UtilText.parse(owner, "<p>"
							+ "A tingling feeling spreads down into your [pc.balls], and you let out a little cry as you feel them [style.boldGrow(multiplying)].<br/>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_balls])]."
						+ "</p>");
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads down into [npc.namePos] [npc.balls], and [npc.she] lets out a little cry as [npc.she] feels them [style.boldGrow(multiplying)].<br/>"
							+ "After a few moments, [npc.sheIs] left with [style.boldTfGeneric([npc.a_balls])]."
						+ "</p>");
			}
		}
	}

	public boolean isInternal() {
		return internal;
	}

	public String setInternal(GameCharacter owner, boolean internal) {
		if(owner==null) {
			this.internal = internal;
			return "";
		}
		
		if(owner.isInternalTesticles() == internal || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}

		this.internal = internal;
		
		if(internal) {
			if(owner.isPlayer()) {
				return "<p>"
							+ "You feel your [pc.balls] tightening and [style.boldShrink(withdrawing)] up into your groin, and you let out a little cry as you feel them shift up inside your body.<br/>"
							+ "Your [pc.balls+] [style.boldTfGeneric(are now internal)]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels [npc.her] [npc.balls] tightening and [style.boldShrink(withdrawing)] up into [npc.her] groin, and [npc.she] lets out a little cry as [npc.she] feels them shift up inside [npc.her] body.<br/>"
							+ "[npc.Her] [npc.balls+] [style.boldTfGeneric(are now internal)]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>"
							+ "You feel your internal [pc.balls] slackening and [style.boldGrow(dropping down)], and you let out a little cry as they settle down into an external sack.<br/>"
							+ "Your [pc.balls+] [style.boldTfGeneric(are now external)]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels [npc.her] internal [npc.balls] slackening and [style.boldGrow(dropping down)], and [npc.she] lets out a little cry as they settle down into an external sack.<br/>"
							+ "[npc.Her] [npc.balls+] [style.boldTfGeneric(are now external)]."
						+ "</p>");
			}
		}
	}
	
	// Cum storage and regeneration:
	

	// CumProduction:

	public CumProduction getCumStorage() {
		return CumProduction.getCumProductionFromInt(cumStorage);
	}

	public int getRawCumStorageValue() {
		return cumStorage;
	}

	/**
	 * Sets the cumStorage. Value is bound to >=0 && <=CumProduction.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()
	 */
	public String setCumStorage(GameCharacter owner, int cumStorage) {
		int oldCumProduction = this.cumStorage;
		this.cumStorage = Math.max(0, Math.min(cumStorage, CumProduction.SEVEN_MONSTROUS.getMaximumValue()));
		int cumChange = this.cumStorage - oldCumProduction;

		if(owner==null) {
			return "";
		}
		
		if (cumChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The amount of [pc.cum] that you're able to produce doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The amount of [npc.cum] that [npc.name] is able to produce doesn't change...)]</p>");
			}
		}
		
		String cumDescriptor = getCumStorage().getDescriptor();
		if (cumChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange bubbling and churning taking place deep within [npc.her] [npc.balls],"
							+ " and [npc.she] can't help but let out [npc.a_moan+] as a small squirt of precum suddenly drools out from [npc.her] [npc.cock];"
								+ " clear evidence that that [npc.her] [npc.cum] production has [style.boldGrow(increased)].<br/>"
						+ "[npc.SheIsFull] now able to produce [style.boldSex(" + cumDescriptor + " [npc.cum])]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] a strange sucking sensation taking place deep within [npc.her] [npc.balls],"
							+ " and [npc.she] can't help but let out a shocked gasp as [npc.she] [npc.verb(realise)] that [npc.sheIs] feeling [npc.her] [npc.cum] production [style.boldShrink(drying up)].<br/>"
						+ "[npc.SheIsFull] now able to produce [style.boldSex(" + cumDescriptor + " [npc.cum])]!"
					+ "</p>");
		}
	}
	
	// Stored cum:

	public CumProduction getStoredCum() {
		return CumProduction.getCumProductionFromInt((int)cumStored);
	}
	
	public float getRawStoredCumValue() {
		return cumStored;
	}

	/**
	 * Sets the cumStorage. Value is bound to >=0 && <=getRawCumStorageValue()
	 */
	public String setStoredCum(GameCharacter owner, float cumStored) {
		float oldStoredCum = this.cumStored;
		this.cumStored = Math.max(0, (Math.min(cumStored, getRawCumStorageValue())));
		float cumChange = oldStoredCum - this.cumStored;

		if(owner==null) {
			return "";
		}
		
		if (cumChange <= 0) {
			return "";
		} else {
			return UtilText.parse(owner, "<p style='text-align:center;'><i style='color:"+Colour.CUM.toWebHexString()+";'>"
					+ UtilText.returnStringAtRandom(
							cumChange+"ml of [npc.cum+] squirts out of [npc.her] [npc.cock+].",
							cumChange+"ml of [npc.cum+] shoots out of [npc.her] [npc.cock+].",
							cumChange+"ml of [npc.cum+] spurts out of [npc.her] [npc.cock+].")
				+ "</i>"
				+ (this.cumStored==0
					?"<br/><i>[npc.Name] now [npc.has] no more [npc.cum] stored in [npc.her] [npc.balls]!</i>"
					:"")
				+ "</p>");
		}
	}

	// Regeneration:

	public FluidRegeneration getCumProductionRegeneration() {
		return FluidRegeneration.getFluidRegenerationFromInt(cumRegeneration);
	}

	public int getRawCumProductionRegenerationValue() {
		return cumRegeneration;
	}

	/**
	 * Sets the cumRegeneration. Value is bound to >=0 && <=FluidRegeneration.FOUR_MAXIMUM.getMaximumValue()
	 */
	public String setCumProductionRegeneration(GameCharacter owner, int cumRegeneration) {
		int oldRegeneration = this.cumRegeneration;
		this.cumRegeneration = Math.max(0, Math.min(cumRegeneration, FluidRegeneration.FOUR_MAXIMUM.getValue()));
		int regenerationChange = this.cumRegeneration - oldRegeneration;
		
		if(owner==null) {
			return "";
		}
		
		if (regenerationChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your rate of [pc.cum] regeneration doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.cum] regeneration doesn't change...)]</p>");
			}
		}
		
		String regenerationDescriptor = getCumProductionRegeneration().getName();
		if (regenerationChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] an alarming bubbling and churning taking place deep within [npc.her] [npc.balls],"
							+ " and [npc.she] can't help but let out [npc.a_moan+] as a small squirt of precum suddenly drools out from [npc.her] [npc.cock];"
								+ " clear evidence that that [npc.her] [npc.cum] regeneration has [style.boldGrow(increased)].<br/>"
						+ "[npc.Her] rate of [npc.cum] regeneration is now [style.boldSex(" + regenerationDescriptor + ")]!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(feel)] strange sucking sensation taking place deep within [npc.her] [npc.balls],"
							+ " and [npc.she] can't help but let out a shocked gasp as [npc.she] [npc.verb(realise)] that [npc.sheIs] feeling [npc.her] [npc.cum] regeneration [style.boldShrink(decreasing)].<br/>"
						+ "[npc.Her] rate of [npc.cum] regeneration is now [style.boldSex(" + regenerationDescriptor + ")]!"
					+ "</p>");
		}
	}

	// Expulsion:

	public FluidExpulsion getCumExpulsion() {
		return FluidExpulsion.getFluidExpulsionFromInt(cumExpulsion);
	}

	public int getRawCumExpulsionValue() {
		return cumExpulsion;
	}

	public String setCumExpulsion(GameCharacter owner, int cumExpulsion) {
		int oldExpulsion = this.cumExpulsion;
		this.cumExpulsion = Math.max(0, Math.min(cumExpulsion, FluidExpulsion.FOUR_HUGE.getMaximumValue()));
		int expulsionChange = this.cumExpulsion - oldExpulsion;

		if(owner==null) {
			return "";
		}
		
		if (expulsionChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your rate of [pc.cum] expulsion doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.namePos] rate of [npc.cum] expulsion doesn't change...)]</p>");
			}
		}
		
		String expulsionDescriptor = getCumExpulsion().getDescriptor();
		if (expulsionChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] suddenly [npc.verb(feel)] a strange tightening and building up of pressure deep within [npc.her] [npc.balls],"
								+ " and [npc.she] can't help but let out [npc.a_moan+] as a small amount of precum powerfully squirts out from [npc.her] [npc.cock];"
									+ " clear evidence that that [npc.her] [npc.cum] expulsion has [style.boldGrow(increased)].<br/>"
						+ "[npc.She] will now expel [style.boldSex(" + UtilText.generateSingularDeterminer(expulsionDescriptor) + " "+expulsionDescriptor+")] amount of stored cum at each orgasm!"
					+ "</p>");
			
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] suddenly [npc.verb(feel)] a strange loosening and reduction of pressure deep within [npc.her] [npc.balls],"
							+ " and [npc.she] can't help but let out [npc.a_moan+] as a small amount of precum weakly dribbles out from [npc.her] [npc.cock];"
								+ " clear evidence that that [npc.her] [npc.cum] expulsion has [style.boldShrink(decreased)].<br/>"
						+ "[npc.She] will now expel [style.boldSex(" + UtilText.generateSingularDeterminer(expulsionDescriptor) + " "+expulsionDescriptor+")] amount of stored cum at each orgasm!"
					+ "</p>");
		}
	}
}