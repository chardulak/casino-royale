package croyale.games.slotmachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SlotMachineController {
	private SlotMachineModel model;
	private SlotMachineView view;

	public SlotMachineController(SlotMachineModel m, SlotMachineView v){
		model = m;
		view = v;
		
		view.addSpinListener(new SpinListener());
	}
	private class SpinListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			try{
				view.spinSpinners();
				ActionListener spinDelay = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	view.setSpinners(model.spin());
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
	
}


