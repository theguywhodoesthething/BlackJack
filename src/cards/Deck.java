package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	List<Card> deck = new ArrayList<>(52);
	
	public Deck(){
        for (Rank r : Rank.values()) {
        	for (Suit s : Suit.values()) {
                deck.add(new Card(r, s));
            }
        }
	}
	
	public List<Card> getDeck() {
		return this.deck;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < deck.size(); i++) {
			builder.append(deck.get(i).toString());
		}
		return builder.toString();
	}
	
	
	
}
