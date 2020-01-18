package com.lilithsthrone.game.dialogue.places.submission.dicePoker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum Hand {
	
	ONE_FIVE_OF_A_KIND(1, "Five of a kind", "33333", "Five dice of the same value."),
	TWO_FOUR_OF_A_KIND(2, "Four of a kind", "55551", "Four dice of the same value."),
	THREE_HIGH_STRAIGHT(3, "High straight", "23456", "Consecutive numbers starting at two."),
	FOUR_FULL_HOUSE(4, "Full house", "22666", "Two dice of the same value, plus three dice of a different, same value."),
	FIVE_THREE_OF_A_KIND(5, "Three of a kind", "44415", "Three dice of the same value."),
	SIX_LOW_STRAIGHT(6, "Low straight", "12345", "Consecutive numbers starting at one."),
	SEVEN_TWO_PAIR(7, "Two pair", "55331", "Two dice of the same value, plus two dice of a different, same value."),
	EIGHT_PAIR(8, "Pair", "22415", "Two dice of the same value."),
	NINE_RUNT(9, "Runt", "13456", "All dice of different values.");
	
	
	private int ranking;
	private String name;
	private String example;
	private String exampleDescription;
	
	private Hand(int ranking, String name, String example, String exampleDescription) {
		this.ranking = ranking;
		this.name = name;
		this.example = example;
		this.exampleDescription = exampleDescription;
	}
	

	/**
	 * @return negative value for player losing, 0 for draw, positive value for player winning
	 */
	public static int compareHands(List<Dice> playerDice, List<Dice> gamblerDice) {
		Hand playerHand = getHand(playerDice);
		Hand gamblerHand = getHand(gamblerDice);
		
		if(playerHand.getRanking()==gamblerHand.getRanking()) {
			return  getValue(playerDice) - getValue(gamblerDice);
		}
		
		return gamblerHand.getRanking() - playerHand.getRanking();
	}
	
	public static Hand getHand(List<Dice> dice) {
		Map<DiceFace, Integer> faceCount = new HashMap<>();
		for(Dice d : dice) {
			faceCount.putIfAbsent(d.getFace(), 0);
			faceCount.put(d.getFace(), faceCount.get(d.getFace())+1);
		}
		
		if(faceCount.size()==1) {
			return Hand.ONE_FIVE_OF_A_KIND;
		
		} else if(faceCount.containsValue(4)) {
			return Hand.TWO_FOUR_OF_A_KIND;
			
		} else if(faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)
				&& faceCount.containsKey(DiceFace.SIX)) {
			return Hand.THREE_HIGH_STRAIGHT;
			
		} else if(faceCount.containsValue(2) && faceCount.containsValue(3)) {
			return Hand.FOUR_FULL_HOUSE;
			
		} else if(faceCount.containsValue(3)) {
			return Hand.FIVE_THREE_OF_A_KIND;
			
		} else if(faceCount.containsKey(DiceFace.ONE)
				&& faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)) {
			return Hand.SIX_LOW_STRAIGHT;
			
		} else if(faceCount.containsValue(2) && faceCount.size()==3) {
			return Hand.SEVEN_TWO_PAIR;
			
		} else if(faceCount.containsValue(2)) {
			return Hand.EIGHT_PAIR;
		}
		
		return Hand.NINE_RUNT;
	}
	
	public static List<Dice> getDiceInHand(List<Dice> dice) {
		List<Dice> diceInHand = new ArrayList<>(dice);
		Map<DiceFace, Integer> faceCount = new HashMap<>();
		for(Dice d : dice) {
			faceCount.putIfAbsent(d.getFace(), 0);
			faceCount.put(d.getFace(), faceCount.get(d.getFace())+1);
		}
		
		if(faceCount.size()==1) {
		
		} else if(faceCount.containsValue(4)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=4) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)
				&& faceCount.containsKey(DiceFace.SIX)) {
			
		} else if(faceCount.containsValue(2) && faceCount.containsValue(3)) {
			
		} else if(faceCount.containsValue(3)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=3) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.ONE)
				&& faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)) {
			
		} else if(faceCount.containsValue(2) && faceCount.size()==3) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=2) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
			
		} else if(faceCount.containsValue(2)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()!=2) {
					diceInHand.removeIf((d)->d.getFace()==entry.getKey());
				}
			}
		}

		return diceInHand;
	}
	
	public static int getValue(List<Dice> dice) {
		Map<DiceFace, Integer> faceCount = new HashMap<>();
		for(Dice d : dice) {
			faceCount.putIfAbsent(d.getFace(), 0);
			faceCount.put(d.getFace(), faceCount.get(d.getFace())+1);
		}
		
		if(faceCount.size()==1) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				return entry.getKey().getValue()*5;
			}
		
		} else if(faceCount.containsValue(4)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==4) {
					return entry.getKey().getValue()*4;
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)
				&& faceCount.containsKey(DiceFace.SIX)) {
			return 2+3+4+5+6;
			
		} else if(faceCount.containsValue(2) && faceCount.containsValue(3)) {
			int total=0;
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				total += entry.getKey().getValue()*entry.getValue();
			}
			return total;
			
		} else if(faceCount.containsValue(3)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==3) {
					return entry.getKey().getValue()*3;
				}
			}
			
		} else if(faceCount.containsKey(DiceFace.ONE)
				&& faceCount.containsKey(DiceFace.TWO)
				&& faceCount.containsKey(DiceFace.THREE)
				&& faceCount.containsKey(DiceFace.FOUR)
				&& faceCount.containsKey(DiceFace.FIVE)) {
			return 1+2+3+4+5;
			
		} else if(faceCount.containsValue(2) && faceCount.size()==3) {
			int total=0;
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==2) {
					total+= entry.getKey().getValue()*2;
				}
			}
			return total;
			
		} else if(faceCount.containsValue(2)) {
			for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
				if(entry.getValue()==2) {
					return entry.getKey().getValue()*2;
				}
			}
		}
		
		int total=0;
		for(Entry<DiceFace, Integer> entry : faceCount.entrySet()) {
			total += entry.getKey().getValue()*entry.getValue();
		}
		return total;
	}
	
	public int getRanking() {
		return ranking;
	}

	public String getName() {
		return name;
	}

	public String getExample() {
		return example;
	}

	public String getExampleDescription() {
		return exampleDescription;
	}
	
}
