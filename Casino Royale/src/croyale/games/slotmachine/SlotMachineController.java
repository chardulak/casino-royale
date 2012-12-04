package croyale.games.slotmachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import croyale.Session;
import croyale.util.ImagePanel;

public class SlotMachineController {
	private SlotMachineModel model;
	private SlotMachineView view;
	private Session session;

	public SlotMachineController(SlotMachineModel m, SlotMachineView v){
		model = m;
		view = v;
		
		view.addSpinListener(new SpinListener());
		view.addBetListener(new BetListener());
		view.setBalance(model.getBalance());
		model.setBet(10);
	}
	
	public void setSession(Session s){
		session = s;
		session.addBalanceField(view.balanceBox);
	}
	
	private class SpinListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
			
			view.resultBar.removeAll();
			
			model.bet();
			//view.setBalance(model.getBalance() - model.bet);
			
	        JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/blankwinlose.png").getImage());
	        
	        view.resultBar.add(Box.createHorizontalStrut(10));
	        view.resultBar.add(gameResultDisplay);
	        view.resultBar.add(Box.createHorizontalStrut(50));
	        
	        view.resultBar.revalidate();
	        view.resultBar.repaint();
			
			try{
				view.spinSpinners();
				ActionListener spinDelay = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	view.setSpinners(model.spin());
		            	int result = model.checkResult();
		            	if (result == 0)
		            		view.drawLose(model.getBalance());
		            	else 
		            		view.drawWin(result, model.getBalance());
		            	
		            }
		            };
		        Timer timer = new Timer( 1000 , spinDelay);
		        timer.setRepeats(false);
		        timer.start();
			}catch (Exception e2){
				;
			}
		}
	}
	private class BetListener implements KeyListener {
		public void keyReleased(KeyEvent ke){
			model.setBet(Integer.parseInt(view.betBox.getText()));
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


