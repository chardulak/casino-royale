package croyale.games.blackjack;

//import java.lang.reflect.InvocationTargetException;

import java.math.BigInteger;

import croyale.gameutil.BlackjackHand;
import croyale.gameutil.Deck;
import croyale.gameutil.Hand;

public class BlackjackModel {

	private static final String INITIAL_VALUE = "100";
	private BigInteger money;
    boolean userWins;   // Did the user win the game?

	public BlackjackModel() {
		reset();
	}
	public Hand[] getHands(){
        Hand hands[] = {userHand,dealerHand};
        return hands;
   }
	public boolean getUserWins(){
		return userWins;
	}
	public void setUserWins(boolean wins){
		userWins = wins;
	}
	public void reset() {
		money = new BigInteger(INITIAL_VALUE);
	}
   
	public String getMoney(){
    	return money.toString();
    }
    
	public void setMoney(String value){
    	money = new BigInteger(value);
    }

	public void hit(){

	}

	public void stand(){

	}
	private Deck deck;                  // A deck of cards.  A new deck for each game.
	private BlackjackHand dealerHand;	// The dealer's hand.
	private BlackjackHand userHand;		// The user's hand.
	public BlackjackHand getDealerHand(){
		System.out.println("dealerHand: " + dealerHand.getBlackjackValue());
		return dealerHand;	
	}
	public BlackjackHand getUserHand(){
		System.out.println("userHand: " + userHand.getBlackjackValue());
		return userHand;
	}
	public Hand dealOneCard(BlackjackHand myHand){
		myHand.addCard(deck.dealCard());
		return myHand;
	}
	public int deal(){   

	      deck = new Deck();
	      dealerHand = new BlackjackHand();
	      userHand = new BlackjackHand();

	      //  Shuffle the deck, then deal two cards to each player. 

	      deck.shuffle();
	      dealerHand.addCard( deck.dealCard() );
	      dealerHand.addCard( deck.dealCard() );
	      userHand.addCard( deck.dealCard() );
	      userHand.addCard( deck.dealCard() );


	      // Check if one of the players has Blackjack (two cards totaling to 21).
	      //   The player with Blackjack wins the game.  Dealer wins ties.


	      if (dealerHand.getBlackjackValue() == 21) {
	           System.out.println("Dealer has the " + dealerHand.getCard(0)
	                                   + " and the " + dealerHand.getCard(1) + ".");
	           System.out.println("User has the " + userHand.getCard(0)
	                                     + " and the " + userHand.getCard(1) + ".");
	           System.out.println();
	           System.out.println("Dealer has Blackjack.  Dealer wins.");
	           return -1;
	      }
	      
	      if (userHand.getBlackjackValue() == 21) {
	           System.out.println("Dealer has the " + dealerHand.getCard(0)
	                                   + " and the " + dealerHand.getCard(1) + ".");
	           System.out.println("User has the " + userHand.getCard(0)
	                                     + " and the " + userHand.getCard(1) + ".");
	           System.out.println();
	           System.out.println("You have Blackjack.  You win.");
	           return 1;
	      }
	      
	      //  If neither player has Blackjack, play the game.  First the user 
	      //    gets a chance to draw cards (i.e., to "Hit").  The while loop ends 
	      //    when the user chooses to "Stand".  If the user goes over 21,
	      //    the user loses immediately.
	      return 0;
	     }
	}
/*		public void wager(){   
  //      int money;          // Amount of money the user has.
        int bet;            // Amount user bets on a game.
        boolean userWins;   // Did the user win the game?
                   
   //     money = 100;  // User starts with $100.
     
        while (true) {
            do {
          	  bet = Integer.parseInt(JOptionPane.showInputDialog("How many dollars do you want to bet?  (Enter 0 to end.)"));
          	  if (bet < 0 || bet > money)
                   System.out.println("Your answer must be between 0 and " + money + '.');
            } while (bet < 0 || bet > money);
            if (bet == 0)
               break;
            dealCards();
           // userWins = playBlackjack();
            if (userWins)
               money = money + bet;
            else
               money = money - bet;
                    if (money == 0) {
               System.out.println("Looks like you've are out of money!");
               break;
            }
        }
        
        System.out.println("You leave with $" + money + '.');
     
     } // end playGame()
     
 */
