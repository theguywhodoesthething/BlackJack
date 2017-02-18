package cards;

import java.util.HashMap;
import java.util.Map;

public class Card {
	
	private Suit suit;
	private Rank rank;
	private Map<Rank, Integer> value = new HashMap<>();
	
	public Card(Rank r, Suit s) {
		suit = s;
		rank = r;
		value.put(r, setValue(r));
	}
	
	public Map<Rank, Integer> getValue() {
		return value;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	private static Integer setValue(Rank r) {
		
		Rank[] allRanks = Rank.values();
		Integer[] allValues = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
		
		for (int i = 0; i < allRanks.length; i++) {
			if (allRanks[i] == r){
				return allValues[i];
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(rank + " of ");
		builder.append(suit + "\n");
		return builder.toString();
	}
	
	
}
