package cards;

public class Table {
	public static void main(String[] args) {
		
		Dealer dealer = new Dealer();
		Deck allWinners = dealer.getNewDeck();
		char playAgain = 'y';
		
		while (playAgain != 'q'){
		
			dealer.initialDeal(allWinners);
			System.out.println("Player Has:");
			System.out.println(dealer.getPlayersHand());
			System.out.println("Dealer Has:");
			System.out.println(dealer.getDealersHand());
			System.out.println("What would you like to do?");
			
		}
	}
}
