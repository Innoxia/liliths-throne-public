package com.lilithsthrone.game.dialogue.places.submission.dicePoker;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum DicePokerTable {
	
	COPPER("copper", 500, 250, Colour.CLOTHING_COPPER),
	SILVER("silver", 2500, 1000, Colour.CLOTHING_SILVER),
	GOLD("gold", 10000, 5000, Colour.CLOTHING_GOLD);
	
	private String name;
	private Colour colour;
	private int initialBet;
	private int raiseAmount;
	
	private DicePokerTable(String name, int initialBet, int raiseAmount, Colour colour) {
		this.name = name;
		this.colour = colour;
		this.initialBet = initialBet;
		this.raiseAmount = raiseAmount;
	}
	
	public String getName() {
		return name;
	}
	
	public int getInitialBet() {
		return initialBet;
	}

	public int getRaiseAmount() {
		return raiseAmount;
	}

	public Colour getColour() {
		return colour;
	}
}
