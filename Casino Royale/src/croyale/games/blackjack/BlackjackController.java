package croyale.games;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import croyale.gameutil.Card;

//import croyale.gameutil.Play;


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
		view.displayCards(model.deal());
	}
	private class HitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try{
				model.dealOneCard(model.getUserHand());
		         if (model.getUserHand().getBlackjackValue() > 21) {
		        	 view.drawLose();
		         }
			}catch (Exception e2){
				;
			}
		}
	}
	private class StandListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try {
			//String source = ((JButton)e.getSource()).getText();
			/*	if(model.getDealerHand().getBlackjackValue() == 21)
					view.drawLose();
				else if (model.getUserHand().getBlackjackValue() == 21)
					view.drawWin();
				*///else if (source == "Stand!"){

					while (model.getDealerHand().getBlackjackValue() <= 16) {
						 model.dealOneCard(model.getDealerHand());
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
				/*else if (source == "New Game?"){
					view.reset();
					view.displayCards(model.deal());
				}*/
				//Play currentPlay = BlackjackController.this.m_view.getCurrentPlay();

				//BlackjackController.this.m_view.setMoney(BlackjackController.this.m_model.getMoney());
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
	private class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.reset();
			view.displayCards(model.deal());
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
