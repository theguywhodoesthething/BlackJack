package cards;

import java.util.List;
import java.util.Scanner;

public class Table {
	
	public int newHand(int betAmount) {
		
		Scanner kb = new Scanner(System.in);
		
		Dealer dealer = new Dealer();
		Deck allWinners = dealer.getNewDeck();
		
		char playerChoice;
		int playerHandValue;
		int dealerHandValue;
		
		dealer.initialDeal(allWinners);
		
		List<Card> dealerHand = dealer.getDealersHand();
		List<Card> playerHand = dealer.getPlayersHand();
		
		System.out.println("Dealer Shows:");
		System.out.println(dealerHand.get(0).toString());
		
		while (handValue(playerHand) <= 21) {
		
			System.out.println("Player Has:");
			System.out.println(playerHand);
			
			while(true) {
				System.out.println("(H)it or (S)tand");
				playerChoice = kb.next().toLowerCase().charAt(0);
				if (playerChoice != 'h' && playerChoice != 's') {
					System.out.print("You must either ");
				} else { break; }
			}
		
			if(playerChoice == 'h') {
				dealer.playerHits(allWinners);
			} else { break; }
		
		}
		
		playerHandValue = handValue(playerHand);
		
		if (playerHandValue > 21) {
			System.out.println("You have " + playerHandValue + ". You bust!");
			return -betAmount;
		}
		
		System.out.println("Dealer Has:");
		System.out.println(dealer.getDealersHand().toString());
		
		while (handValue(dealerHand) <= 16) {
			System.out.println("Dealer Hits");
			
			dealer.dealerHits(allWinners);
		
			System.out.println("Dealer Has:");
			System.out.println(dealer.getDealersHand().toString());
		}
		
		dealerHandValue = handValue(dealerHand);
		String msg = "Dealer has " + dealerHandValue + ". You have " + playerHandValue;
		
		if (dealerHandValue > 21) {
			System.out.println("Dealer has " + dealerHandValue + ". Dealer busts!");
			return betAmount;
		} else if (dealerHandValue > playerHandValue) {
			System.out.println(msg);
			return -betAmount;
		} else if (dealerHandValue < playerHandValue) {
			System.out.println(msg);
			return betAmount;
		} else {
			System.out.println(msg);
			return 0;
		}
	}
	
	private int handValue(List<Card> hand){
		
		int value = 0;
		
		for(Card c : hand){
			value += c.getValue().get(c.getRank());
		}
		
		return value;
	}

}
