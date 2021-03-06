package cards;

import java.util.Scanner;

public class Casino {
	
	public static int buyIn = 10000;

	public static void twentyOne() {
		Scanner kb = new Scanner(System.in);

		Table table = new Table();
		char playAgain = 'y', rebuy;
		int betAmount, handResult;
		String wonOrLost;
		boolean okToDouble = true;

		System.out.println("Welcome to the Single Deck BlackJack Table.");
		System.out.println("Good Luck!");

		while (playAgain == 'y' && buyIn > 0) {

			System.out.println("You have have $" + buyIn);

			while (true) {
				System.out.println("Please place your bet.");
				betAmount = kb.nextInt();

				if (betAmount * 2 > buyIn) {
					okToDouble = false;
				}

				if (betAmount <= 0) {
					System.out.println("I'm not falling for that.");
				} else if (betAmount > buyIn) {
					System.out.println("You only have $" + buyIn);
				} else {
					break;
				}
			}

			handResult = table.newHand(betAmount, okToDouble);

			buyIn += handResult;

			if (handResult == 0) {
				wonOrLost = "You push.";
			} else if (handResult < 0) {
				wonOrLost = "You lose $" + (-handResult);
			} else {
				wonOrLost = "You win $" + handResult;
			}

			System.out.println(wonOrLost);

			if (buyIn <= 0) {
				System.out.println("You're broke. Would you like to rebuy? (Y/N)");
				rebuy = kb.next().toLowerCase().charAt(0);
				if (rebuy != 'y') {
					break;
				} else {
					buyIn = 10000;
				}
			}

			okToDouble = true;

			System.out.println("Play again? (Y/N)");
			playAgain = kb.next().toLowerCase().charAt(0);
		}

		System.out.println("You leave the table with $" + buyIn);
		System.out.println("Thanks for playing");

		kb.close();
	}

}
