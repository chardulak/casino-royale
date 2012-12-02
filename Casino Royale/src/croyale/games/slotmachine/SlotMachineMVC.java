package croyale.games.slotmachine;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SlotMachineMVC
{
	
	public SlotMachineMVC(JPanel panel){

		SlotMachineModel model = new SlotMachineModel();
		SlotMachineView view = new SlotMachineView(panel);
		SlotMachineController controller = new SlotMachineController(model,view);

		view.setVisible(true);
	}
	
}
