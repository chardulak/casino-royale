package croyale.games.blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//import croyale.gameutil.Play;


public class BlackjackController {
	private BlackjackModel model;
	private BlackjackView view;
	
	public BlackjackController(BlackjackModel m, BlackjackView v){ 
		model = m;
		view = v;
		view.setMoney(model.getMoney());
		
		view.addPlayListener(new PlayListener());
		view.addBetListener(new BetListener());
		
		view.displayCards(model.deal());
	}
	
	private class PlayListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try {
				String source = ((JButton)e.getSource()).getText();
				if (source == "Hit!"){
					model.hit();
					view.drawLose();
				}
				else if (source == "Stand!"){
					model.stand();
					view.drawWin();
				}
				else if (source == "New Game?"){
					view.reset();
					view.displayCards(model.deal());
				}
				//Play currentPlay = BlackjackController.this.m_view.getCurrentPlay();
				
				//BlackjackController.this.m_view.setMoney(BlackjackController.this.m_model.getMoney());
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
	private class BetListener implements ActionListener {
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
