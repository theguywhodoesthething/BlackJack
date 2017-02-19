package cards;

import java.util.List;
import java.util.Scanner;
import static cards.Rank.*;


public class Table {
	
	public int newHand(int betAmount, boolean okToDouble) {
		
		Scanner kb = new Scanner(System.in);
		
		Dealer dealer = new Dealer();
		Deck allWinners = dealer.getNewDeck();
		
		Integer playerChoice = 1, insurance = 0, playerHandValue, dealerHandValue;
		
		dealer.initialDeal(allWinners);
		
		List<Card> dealerHand = dealer.getDealersHand();
		List<Card> playerHand = dealer.getPlayersHand();
		
		System.out.println("Dealer Shows:");
		System.out.println(dealerHand.get(0));
		
		while (handValue(playerHand) <= 21) {
		
			System.out.println("Player Has:");
			System.out.println(showCards(playerHand));
			
//			if (dealerHand.get(0).getRank() == ACE) {
//				System.out.println("Insurance?\n1: Yes\n2: No");
//				insurance = kb.nextInt(); 	//working to implement insurance option, need
//											// figure out how to subtract insurance from the bet
//			}
			
			if (handValue(dealerHand) == 21 && handValue(playerHand) == 21) {
				System.out.println(showCards(dealerHand));
				System.out.println("Dealer has 21");
				return 0;
			} else if(handValue(playerHand) == 21) {
				System.out.println("You have BlackJack!");
				return (int)(((double)betAmount) * 1.5);
			} else if (handValue(dealerHand) == 21) {
				System.out.println(showCards(dealerHand));
				System.out.println("Dealer has 21");
				return -betAmount;
			}
			
			while(true) {
				System.out.println("1: Hit\n2: Stay");
				
				if (okToDouble && playerHand.size() == 2) {
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

	private String showCards(List<Card> hand) {
		StringBuilder builder = new StringBuilder();
		for(Card c : hand){
			builder.append(c.toString());
		}
		return builder.toString();
	}
	
//	private int bust(List<Card> hand) {
//		int oneOrEleven = 0;
//		
//		for (Card c : hand) {
//			if (c.getRank() == ACE) {
//				oneOrEleven = 10;
//			}
//		}
//		
//		return oneOrEleven;
//		
//	}
}
