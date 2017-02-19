package cards;

import java.util.List;
import java.util.Scanner;
import static cards.Rank.*;

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

		if (handValue(dealerHand) == 21 && handValue(playerHand) == 21) {
			System.out.println("Player Has:");
			System.out.println(showCards(playerHand));
			System.out.println("Dealer Has:");
			System.out.println(showCards(dealerHand));
			System.out.println("Dealer has 21");
			return 0;
		} else if (handValue(playerHand) == 21) {
			System.out.println("Player Has:");
			System.out.println(showCards(playerHand));
			System.out.println("You have BlackJack!");
			return (int) (((double) betAmount) * 1.5);
		} else if (handValue(dealerHand) == 21) {
			System.out.println("Player Has:");
			System.out.println(showCards(playerHand));
			System.out.println("Dealer Has:");
			System.out.println(showCards(dealerHand));
			System.out.println("Dealer has 21");
			return -betAmount;
		}

		while (handValue(playerHand) <= 21) {

			System.out.println("Player Has:");
			System.out.println(showCards(playerHand));

			while (true) {
				System.out.println("1: Hit\n2: Stay");

				if (okToDouble && playerHand.size() == 2) {
					System.out.println("3: Double down");
				}

				playerChoice = kb.nextInt();

				if (playerChoice > 3 || playerChoice < 1) {
					System.out.println("Please select:");
				} else if (!okToDouble && playerChoice == 3) {
					System.out.println("You don't have enough to double down. Please select:");
				} else if (playerChoice == 3 && playerHand.size() != 2) {
					System.out.println("You can't double right now");
				} else {
					break;
				}
			}

			if (playerChoice == 1) {
				dealer.playerHits(allWinners);
			} else if (playerChoice == 3) {
				betAmount *= 2;
				System.out.println("You double your bet to $" + betAmount);
				dealer.playerHits(allWinners);
				System.out.println(showCards(playerHand));
				break;
			} else {
				break;
			}
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

		if (softSeventeen(dealerHand)) {
			System.out.println("Dealer has a soft 17.\nDealer Hits");
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

	private int handValue(List<Card> hand) {

		int value = 0, numOfAces = 0;

		for (Card c : hand) {
			if (c.getValue().get(ACE) == null) {
				value += c.getValue().get(c.getRank());
			}
		}
		
		for (Card c : hand) {
			if (c.getValue().get(ACE) != null) {
				numOfAces++;
			}
		}

		for (int i = 0; i < numOfAces; i++) {
			if (value <= 10) {
				value += 11;
			} else {
				value += 1;
			}
		}

		return value;
	}

	private String showCards(List<Card> hand) {
		StringBuilder builder = new StringBuilder();
		for (Card c : hand) {
			builder.append(c.toString());
		}
		return builder.toString();
	}

	private boolean softSeventeen(List<Card> hand) {

		int value = 0, numOfAces = 0;

		for (Card c : hand) {
			if (c.getValue().get(ACE) != null) {
				numOfAces++;
			}
		}

		if (numOfAces != 0) {
			for (Card c : hand) {
				if (c.getValue().get(ACE) == null) {
					value += c.getValue().get(c.getRank());
				}
			}
			if (value + numOfAces == 7) {
				return true;
			}
		}
		return false;
	}
}
