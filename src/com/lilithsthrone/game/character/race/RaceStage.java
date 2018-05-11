package com.lilithsthrone.game.character.race;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.0
 * @version 0.2.4.5
 * @author Innoxia
 * fixed by 8ch!
 */
public enum RaceStage {	
	/**No animal-morph parts whatsoever.</br>
	 * <i>"Not furry"</i> by any standard.*/


	HUMAN("", Colour.TRANSFORMATION_HUMAN) {

		@Override
		public int getNumericalValue() {
			// TODO Auto-generated method stub
			return NOTHING;
		}
	},
	
	/**Some minor animal-morph parts.</br>
	 * When used in GameCharacter's setBody() method, will grant <b>only</b> ears, eyes, tail, horns, antenna, and wings (no genitalia).</br>
	 * <i>"Not furry"</i> by most standards.*/
	PARTIAL("partial", Colour.TRANSFORMATION_PARTIAL) {

		@Override
		public int getNumericalValue() {
			// TODO Auto-generated method stub
			return ANTENNA + EAR + EYE + HORN + TAIL;
		}
	},

	/**All minor animal-morph parts (including genitalia).</br>
	 * <i>"Borderline furry"</i> by most standards.*/
	PARTIAL_FULL("minor", Colour.TRANSFORMATION_PARTIAL_FULL) {

		@Override
		public int getNumericalValue() {
			// TODO Auto-generated method stub
			return ANTENNA + EAR + EYE + HAIR + HORN + PENIS + TAIL + VAGINA + WING;
		}
	},

	/**All minor animal-morph parts (including genitalia), plus animal-morph arms and legs.</br>
	 * <i>"Low-level furry"</i> by most standards.*/
	LESSER("lesser", Colour.TRANSFORMATION_LESSER) {
		@Override
		public int getNumericalValue() {
			// TODO Auto-generated method stub
			return ANTENNA + ARM + ASS + BREAST + EAR + EYE + HAIR + HORN + LEG + PENIS +TAIL + VAGINA + WING;
		}
	},

	/**All minor animal-morph parts, animal-morph arms and legs, and animal-morph skin and face.</br>
	 * <i>"Furry"</i> by all standards.*/
	GREATER("greater", Colour.TRANSFORMATION_GREATER) {

		@Override
		public int getNumericalValue() {
			// TODO Auto-generated method stub
			return ANTENNA + ARM + ASS + BREAST + EAR + EYE + FACE + HAIR+ HORN + LEG + PENIS + SKIN + TAIL + VAGINA + WING;
		}
	};
	
	
	
	/* This might be scary, but it's really simple.
	 * These constants are neatly equal to single bit value.
	 * This allows us to check if another value has at least these same bits.
	 * It's this, or having to write 15 methods for each race stage, with the only difference being returning a hard coded true or false value.
	 * With this we can add the bits which are true about the RaceStage into a number, and check if the bits are true in that number.
	 * If for some reason, complex method design is required later, the method can be overridden.
	 */
	
	//CONSTANT FLAG 
	private final static int NOTHING	=      0;  //0000 0000 - 0000 0000 =     0
	private final static int ANTENNA	= 1 << 0;  //0000 0000 - 0000 0001 =     1
	private final static int ARM		= 1 << 1;  //0000 0000 - 0000 0010 =     2
	private final static int ASS		= 1 << 2;  //0000 0000 - 0000 0100 =     4
	private final static int BREAST		= 1 << 3;  //0000 0000 - 0000 1000 =     8
	private final static int EAR		= 1 << 4;  //0000 0000 - 0001 0000 =    16
	private final static int EYE		= 1 << 5;  //0000 0000 - 0010 0000 =    32
	private final static int FACE		= 1 << 6;  //0000 0000 - 0100 0000 =    64
	private final static int HAIR		= 1 << 7;  //0000 0000 - 1000 0000 =   128
	private final static int HORN		= 1 << 8;  //0000 0001 - 0000 0000 =   256
	private final static int LEG		= 1 << 9;  //0000 0010 - 0000 0000 =   512
	private final static int PENIS		= 1 << 10; //0000 0100 - 0000 0000 =  1024
	private final static int SKIN		= 1 << 11; //0000 1000 - 0000 0000 =  2048
	private final static int TAIL		= 1 << 12; //0001 0000 - 0000 0000 =  4096
	private final static int VAGINA		= 1 << 13; //0010 0000 - 0000 0000 =  8192
	private final static int WING		= 1 << 14; //0100 0000 - 0000 0000 = 16384
	//CONSTANT FLAG END
	
	
	private String name;
	private Colour colour;

	private RaceStage(String name,Colour colour) {
		this.name = name;
		this.colour = colour;
//		this.printDebug();
	}

	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	/**
	 * WARNING, do not ever add a variable more than once, Multiply/devide or subtract. 
	 * @return the numerical representation of all the furry body parts you need for this race stage.
	 * @author 8ch
	 */
	public abstract int getNumericalValue();
//	*	return ANTENNA + PENIS;
//	*	// when checking for antenna or penis, compare bit will now return true.

	
	
	
	/**
	 * Used to check if a sample value has at least the bits on that are on in mustHaveBits  
	 * ^Grammar ever?
	 
	 * @param mustHaveBits All the bits, that sample has to have be true.
	 * @param sample The number which we're checking for bits.
	 * @return if the second variable has all the bits being checked
	 * @author 8ch
	 */
	public static final boolean comparebit(int mustHaveBits, int sample) {
				//Creates a number based on what bits both variable have on are.
				//If the sample has all the bits on as mustHaveBits, then it's true.
				//just compare the newly created number with the goal number. They should be equal.
				//any extra bits will be turned off, and any bits missing will also be off.
		return (mustHaveBits&sample)==mustHaveBits;  
	}
	
	/**
	 * Used to check if the RaceStage has this body part.
	 * 
	 * @param mustHaveBits Body parts we're checking for 
	 * @return does it have the body parts we're checking for.
	 * @author 8ch
	 */
	public final boolean comparebit(int mustHaveBits) {
		return RaceStage.comparebit(mustHaveBits, getNumericalValue());
	}
	
	public boolean isAntennaFurry()		{return comparebit(ANTENNA);}
	public boolean isArmFurry() 		{return comparebit(ARM);}
	public boolean isAssFurry() 		{return comparebit(ASS);}
	public boolean isBreastFurry()		{return comparebit(BREAST);}
	public boolean isEarFurry()			{return comparebit(EAR);}
	public boolean isEyeFurry()			{return comparebit(EYE);}
	public boolean isFaceFurry()		{return comparebit(FACE);}
	public boolean isHairFurry()		{return comparebit(HAIR);}
	public boolean isHornFurry()		{return comparebit(HORN);}
	public boolean isLegFurry()			{return comparebit(LEG);}
	public boolean isPenisFurry()		{return comparebit(PENIS);}
	public boolean isSkinFurry()		{return comparebit(SKIN);}
	public boolean isTailFurry()		{return comparebit(TAIL);}
	public boolean isVaginaFurry()		{return comparebit(VAGINA);}
	public boolean isWingFurry()		{return comparebit(WING);}
	
	//Method to figure out what bits return true or false on what raceStage. Likely no longer needed.
	/*private void printDebug() {
		
		String temp = "[ race "+name+"\n";
		
		 temp += "Has body part ANTENNA is "+ isAntennaFurry() +" with num "+ANTENNA+"\n";	 
		 temp += "Has body part ARM is     "+ isArmFurry() +" with num "+ARM+"\n"; 		 
		 temp += "Has body part ASS is     "+ isAssFurry() +" with num "+ASS+"\n"; 		 
		 temp += "Has body part BREAST is  "+ isBreastFurry() +" with num "+BREAST+"\n";		 
		 temp += "Has body part EAR is     "+ isEarFurry() +" with num "+EAR+"\n";			 
		 temp += "Has body part EYE is     "+ isEyeFurry() +" with num "+EYE+"\n";			 
		 temp += "Has body part FACE is    "+ isFaceFurry() +" with num "+FACE+"\n";		 
		 temp += "Has body part HAIR is    "+ isHairFurry() +" with num "+HAIR+"\n";		 
		 temp += "Has body part HORN is    "+ isHornFurry() +" with num "+HORN+"\n";		 
		 temp += "Has body part LEG is     "+ isLegFurry() +" with num "+LEG+"\n";		 
		 temp += "Has body part PENIS is   "+ isPenisFurry() +" with num "+PENIS+"\n";		 
		 temp += "Has body part SKIN is    "+ isSkinFurry() +" with num "+SKIN+"\n";		 
		 temp += "Has body part TAIL is    "+ isTailFurry() +" with num "+TAIL+"\n";		 
		 temp += "Has body part VAGINA is  "+ isVaginaFurry() +" with num "+VAGINA+"\n";		 
		 temp += "Has body part WING is    "+ isWingFurry() +" with num "+WING+"\n";		
		 temp += "END]";
		 
		 System.out.print(temp);
	}*/
}