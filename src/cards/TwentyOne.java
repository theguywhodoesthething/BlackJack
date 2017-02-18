package cards;

import java.util.Scanner;

public class TwentyOne {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);

		Table table = new Table();
		char playAgain = 'y';
		int betAmount, handResult;
		String wonOrLost;
		
		while (playAgain != 'q') {
			System.out.println("Please place your bet.");
			betAmount = kb.nextInt();
			
			handResult = table.newHand(betAmount);
			
			if (handResult == 0){
				wonOrLost = "You push.";
			} else if (handResult < 0){
				wonOrLost = "You lose $" + (-handResult);
			} else {
				wonOrLost = "You win $" + handResult;
			}
			
			System.out.println(wonOrLost);
			
			System.out.println("Play again? Press 'Q' to Quit.");
			playAgain = kb.next().toLowerCase().charAt(0);
		}
		
		kb.close();
	}
}
