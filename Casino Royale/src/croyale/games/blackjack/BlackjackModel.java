package croyale.games.blackjack;

//import java.lang.reflect.InvocationTargetException;

import java.math.BigInteger;

import javax.swing.JOptionPane;

import croyale.gameutil.BlackjackHand;
import croyale.gameutil.Card;
import croyale.gameutil.Deck;
//import croyale.gameutil.Play;
import croyale.gameutil.Hand;

public class BlackjackModel {
	
	private static final String INITIAL_VALUE = "100";
	private BigInteger money;
	
	public BlackjackModel() {
		reset();
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
	
	public Hand[] deal(){
		 Deck deck;                  // A deck of cards.  A new deck for each game.
	      BlackjackHand dealerHand;   // The dealer's hand.
	      BlackjackHand userHand;     // The user's hand.
	      
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
	      }
	      
	      if (userHand.getBlackjackValue() == 21) {
	           System.out.println("Dealer has the " + dealerHand.getCard(0)
	                                   + " and the " + dealerHand.getCard(1) + ".");
	           System.out.println("User has the " + userHand.getCard(0)
	                                     + " and the " + userHand.getCard(1) + ".");
	           System.out.println();
	           System.out.println("You have Blackjack.  You win.");
	      }
	      
	      //  If neither player has Blackjack, play the game.  First the user 
	      //    gets a chance to draw cards (i.e., to "Hit").  The while loop ends 
	      //    when the user chooses to "Stand".  If the user goes over 21,
	      //    the user loses immediately.
	      Hand hands[] = {userHand,dealerHand};
	      return hands;
	     }
	}
	/*public void wager(){   
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
     static boolean dealCards() {
         // Let the user play one game of Blackjack.
         // Return true if the user wins, false if the user loses.

      Deck deck;                  // A deck of cards.  A new deck for each game.
      BlackjackHand dealerHand;   // The dealer's hand.
      BlackjackHand userHand;     // The user's hand.
      
      deck = new Deck();
      dealerHand = new BlackjackHand();
      userHand = new BlackjackHand();

      //  Shuffle the deck, then deal two cards to each player. 
      
      deck.shuffle();
      dealerHand.addCard( deck.dealCard() );
      dealerHand.addCard( deck.dealCard() );
      userHand.addCard( deck.dealCard() );
      userHand.addCard( deck.dealCard() );
      
      System.out.println();
      System.out.println();
      
      // Check if one of the players has Blackjack (two cards totaling to 21).
      //   The player with Blackjack wins the game.  Dealer wins ties.
      
      
      if (dealerHand.getBlackjackValue() == 21) {
           System.out.println("Dealer has the " + dealerHand.getCard(0)
                                   + " and the " + dealerHand.getCard(1) + ".");
           System.out.println("User has the " + userHand.getCard(0)
                                     + " and the " + userHand.getCard(1) + ".");
           System.out.println();
           System.out.println("Dealer has Blackjack.  Dealer wins.");
           return false;
      }
      
      if (userHand.getBlackjackValue() == 21) {
           System.out.println("Dealer has the " + dealerHand.getCard(0)
                                   + " and the " + dealerHand.getCard(1) + ".");
           System.out.println("User has the " + userHand.getCard(0)
                                     + " and the " + userHand.getCard(1) + ".");
           System.out.println();
           System.out.println("You have Blackjack.  You win.");
           return true;
      }
      
      //  If neither player has Blackjack, play the game.  First the user 
      //    gets a chance to draw cards (i.e., to "Hit").  The while loop ends 
      //    when the user chooses to "Stand".  If the user goes over 21,
      //    the user loses immediately.
      
     }
    /*  boolean playBlackjack(){
 //     while (true) {
          
           // Display user's cards, and let user decide to Hit or Stand. 

           System.out.println();
           System.out.println();
           System.out.println("Your cards are:");
           for ( int i = 0; i < userHand.getCardCount(); i++ )
              System.out.println("    " + userHand.getCard(i));
           System.out.println("Your total is " + userHand.getBlackjackValue());
           System.out.println();
           System.out.println("Dealer is showing the " + dealerHand.getCard(0));
           System.out.println();
         //  System.out.print("Hit (H) or Stand (S)? ");
           /* If the user Hits, the user gets a card.  If the user Stands,
              the loop ends (and it's the dealer's turn to draw cards).
           */           
   //   	} // end while loop
      
      /* If we get to this point, the user has Stood with 21 or less.  Now, it's
         the dealer's chance to draw.  Dealer draws cards until the dealer's
         total is > 16.  If dealer goes over 21, the dealer loses.
      */
   /*   System.out.println();
      System.out.println("User stands.");
      System.out.println("Dealer's cards are");
      System.out.println("    " + dealerHand.getCard(0));
      System.out.println("    " + dealerHand.getCard(1));
      while (dealerHand.getBlackjackValue() <= 16) {
         Card newCard = deck.dealCard();
         System.out.println("Dealer hits and gets the " + newCard);
         dealerHand.addCard(newCard);
         if (dealerHand.getBlackjackValue() > 21) {
            System.out.println();
            System.out.println("Dealer busted by going over 21.  You win.");
            return true;
         }
      }
      System.out.println("Dealer's total is " + dealerHand.getBlackjackValue());
      
      // If we get to this point, both players have 21 or less.  We
      //   can determine the winner by comparing the values of their hands. 
      
      System.out.println();
      if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
         System.out.println("Dealer wins on a tie.  You lose.");
         return false;
      }
      else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
         System.out.println("Dealer wins, " + dealerHand.getBlackjackValue() 
                          + " points to " + userHand.getBlackjackValue() + ".");
         return false;
      }
      else {
         System.out.println("You win, " + userHand.getBlackjackValue() 
                          + " points to " + dealerHand.getBlackjackValue() + ".");
         return true;
      }

   }  // end playBlackjack()
*/

