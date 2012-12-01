package croyale.games.slotmachine;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SlotMachineMVC extends JFrame
{
	
	public SlotMachineMVC(JPanel panel){
		System.out.println("MVC");

		SlotMachineModel model = new SlotMachineModel();
		SlotMachineView view = new SlotMachineView(panel);
		SlotMachineController controller = new SlotMachineController(model,view);

		view.setVisible(true);
	}
	
}
