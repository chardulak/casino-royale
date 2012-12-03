package croyale.games.slotmachine;

import java.util.Random;

public class SlotMachineModel {

	public int[] spin(){
		Random generator = new Random(System.currentTimeMillis());
		
		int a[] = {(Math.abs(generator.nextInt())%5+1),(Math.abs(generator.nextInt())%5+1),(Math.abs(generator.nextInt())%5+1)};
		return a;
	}
}
