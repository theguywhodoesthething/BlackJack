package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

	private Deck oneDeck = new Deck();
	private List<Card> playersHand = new ArrayList<>();
	private List<Card> dealersHand = new ArrayList<>();

	public void playerHits(Deck d) {
		playersHand.add(d.getDeck().remove(0));
	}

	public void dealerHits(Deck d) {
		dealersHand.add(d.getDeck().remove(0));
	}

	public void initialDeal(Deck d) {
		playerHits(d);
		dealerHits(d);
		playerHits(d);
		dealerHits(d);
	}

	public Deck getNewDeck() {
		Deck oneDeck = new Deck();
		Collections.shuffle(oneDeck.getDeck());
		return oneDeck;
	}

	public Deck getOneDeck() {
		return oneDeck;
	}

	public List<Card> getPlayersHand() {
		return playersHand;
	}

	public List<Card> getDealersHand() {
		return dealersHand;
	}
}
