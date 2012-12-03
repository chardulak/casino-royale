package croyale.games.slotmachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				//Thread.sleep(3000);
				//view.setSpinners(model.spin());
			}catch (Exception e2){
				;
			}
		}
	}
	
}

