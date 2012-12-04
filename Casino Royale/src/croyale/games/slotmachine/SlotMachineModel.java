package croyale.games.slotmachine;

import java.util.Random;

import croyale.Session;

public class SlotMachineModel {
	private int a[] = {0,0,0};
	
	public final static int BAR = 1;
	public final static int BELL = 2;
	public final static int CHERRY = 3; 
	public final static int LEMON = 4;
	public final static int PLUM = 5;
	public final static int SEVEN = 6;
	
	private int betMultiplier = 0;
	protected int bet = 0;
	private double balance;
	private Session session;

	public SlotMachineModel(Session s){
		session = s;
		balance = session.getBalance();
	}
	
	public int[] spin(){
		Random generator = new Random(System.currentTimeMillis());
		
		a[0] = Math.abs(generator.nextInt())%5+1;
		a[1] = Math.abs(generator.nextInt())%5+1;
		a[2] = Math.abs(generator.nextInt())%5+1;
		return a;
	}
	
	public void bet(){
		balance = balance - bet;
		session.updateBalance(balance);
	}
	
	public int checkResult(){
		
		betMultiplier = 0;
		
		switch(a[0]){
		case CHERRY:
			if (a[1] == CHERRY) {
				if (a[2] == CHERRY)
					betMultiplier = 150;
				else 
					betMultiplier = 10;
			}
			else 
				betMultiplier = 2;
			break;
		case BAR:
			if (a[1] == BAR && a[2] == BAR)
				betMultiplier = 20;
			else 
				betMultiplier = 0;
			break;
		case SEVEN:
			if (a[1] == SEVEN && a[2] == SEVEN)
				betMultiplier = 1000;
			else 
				betMultiplier = 0;
			break;
		default:
			betMultiplier = 0;
			break;
		}
		if (a[0] != CHERRY){
			if (a[1] == CHERRY) {
				if (a[2] == CHERRY)
					betMultiplier = 10;
				else 
					betMultiplier = 2;
			}
			else if (a[2] == CHERRY)
				betMultiplier = 2;
		}
		
		balance = balance +  bet*betMultiplier;
		System.out.println("New balance is " + balance);
		
		session.updateBalance(balance);
		
			
		return bet*betMultiplier;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setBet(int b){
		bet = b;
	}
}
