package croyale.games.blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackjackController {
	private BlackjackModel model;
	private BlackjackView view;

	public BlackjackController(BlackjackModel m, BlackjackView v){ 
		model = m;
		view = v;
		view.setMoney(model.getMoney());

		view.addHitListener(new HitListener());
		view.addStandListener(new StandListener ());
		view.addBetListener(new BetListener());
		view.addNewGameListener(new NewGameListener());
		
		startGame();
		
	}
	
	private void startGame(){
		int winner = model.deal();
		if (winner == 1){
			view.drawWin();
		}
		else if (winner == -1){
			view.drawLose();
		}
		
		view.displayCards(model.getHands());
	}
	
	private class HitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try{
				model.dealOneCard(model.getUserHand());
				view.displayCards(model.getHands());
		         if (model.getUserHand().getBlackjackValue() > 21) {
		        	 view.drawLose();
		         }
		         else if (model.getUserHand().getBlackjackValue() == 21){
		        	 view.standButton.doClick();
		         }
			}catch (Exception e2){
				;
			}
		}
	}
	
	private class StandListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try {
				while (model.getDealerHand().getBlackjackValue() <= 16) {
					 model.dealOneCard(model.getDealerHand());
					 view.displayCards(model.getHands());
			         if (model.getDealerHand().getBlackjackValue() > 21) {
			           // Dealer busted by going over 21.  You win.
			        	model.setUserWins(true);
			         }
			    }
				if(!model.getUserWins()){
					if (model.getDealerHand().getBlackjackValue() >= model.getUserHand().getBlackjackValue())
						view.drawLose();
					else
						view.drawWin();
				}
				else
					view.drawWin();
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
	private class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.reset();
			int winner = model.deal();
			if (winner == 1){
				view.drawWin();
			}
			else if (winner == -1){
				view.drawLose();
			}
			view.displayCards(model.getHands());
		}
	}
	private class BetListener implements ActionListener {
		public BetListener() {
			// TODO Auto-generated constructor stub
		}

		public void actionPerformed(ActionEvent e){
			int bet;
			try {
				bet = BlackjackController.this.view.getBet();
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
}
