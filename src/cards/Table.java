package cards;

import java.util.List;
import java.util.Scanner;

public class Table {
	
	public int newHand(int betAmount, boolean okToDouble) {
		
		Scanner kb = new Scanner(System.in);
		
		Dealer dealer = new Dealer();
		Deck allWinners = dealer.getNewDeck();
		
		Integer playerChoice = 1, playerHandValue, dealerHandValue;
		
		dealer.initialDeal(allWinners);
		
		List<Card> dealerHand = dealer.getDealersHand();
		List<Card> playerHand = dealer.getPlayersHand();
		
		System.out.println("Dealer Shows:");
		System.out.println(dealerHand.get(0));
		
		while (handValue(playerHand) <= 21) {
		
			System.out.println("Player Has:");
			System.out.println(showCards(playerHand));
			
			while(true) {
				System.out.println("1: Hit\n2: Stay");
				
				if (okToDouble) {
					System.out.println("3: Double");
				}
				
				playerChoice = kb.nextInt();
				
				if (playerChoice > 3 || playerChoice < 1) {
					System.out.println("Please select:");
				} else if (!okToDouble && playerChoice == 3){
					System.out.println("You don't have enough to double. Please select:");
				} else { break; }
			}
		
			if(playerChoice == 1) {
				dealer.playerHits(allWinners);
			} else if (playerChoice == 3) {
				betAmount *= 2;
				System.out.println("You double your bet to $" + betAmount);
				dealer.playerHits(allWinners);
				break;
			} else { break; }		
		}
		
		playerHandValue = handValue(playerHand);
		
		if (playerHandValue > 21) {
			System.out.println(showCards(playerHand));
			System.out.println("You have " + playerHandValue + ". You bust!");
			return -betAmount;
		}
		
		System.out.println("Dealer Has:");
		System.out.println(showCards(dealerHand));
		
		while (handValue(dealerHand) <= 16) {
			System.out.println("Dealer Hits");
			
			dealer.dealerHits(allWinners);
		
			System.out.println("Dealer Has:");
			System.out.println(showCards(dealerHand));
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

	public String showCards(List<Card> hand) {
		StringBuilder builder = new StringBuilder();
		for(Card c : hand){
			builder.append(c.toString());
		}
		return builder.toString();
	}
}
