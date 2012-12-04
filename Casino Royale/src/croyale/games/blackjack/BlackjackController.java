package croyale.games.blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import croyale.Session;
import croyale.util.FormatUtility;

public class BlackjackController {
	private BlackjackModel model;
	private BlackjackView view;
	private Session session;

	public BlackjackController(BlackjackModel m, BlackjackView v){ 
		model = m;
		view = v;

		view.addHitListener(new HitListener());
		view.addStandListener(new StandListener ());
		view.addBetListener(new BetListener());
		view.addNewGameListener(new NewGameListener());
		
		view.setMoney(FormatUtility.formatCurrency(Double.parseDouble(model.getMoney())));
				
	}
	
	public void setSession(Session s){
		session = s;
		session.addBalanceField(view.balanceField);
		startGame();
	}
	
	private void startGame(){
		model.setBet(view.getBet());
		view.newGameButton.setVisible(true);
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
					else{
						model.setUserWins(true);
						view.drawWin();
					}
				}
				else{
					model.setUserWins(true);
					view.drawWin();
				}
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
			model.bet();
		}
	}

	private class BetListener implements KeyListener {
		public void keyReleased(KeyEvent ke){
			model.setBet(view.getBet());
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
