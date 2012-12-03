package croyale.games.slotmachine;

import java.util.Random;

public class SlotMachineModel {

	public int[] spin(){
		Random generator = new Random(System.currentTimeMillis());
		
		int a[] = {(generator.nextInt()%5+1),(generator.nextInt()%5+1),(generator.nextInt()%5+1)};
		return a;
	}
}
