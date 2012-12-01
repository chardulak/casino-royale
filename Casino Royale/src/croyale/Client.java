package croyale;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import croyale.rpc.Constants;
import croyale.rpc.ServerHostInterface;

public class Client implements Constants
{
	public static void main(String[] args)
	{
		try {
//			ServerHostInterface shi = ClientRPC.getServerInterface();
		
			Registry registry = LocateRegistry.getRegistry("174.100.38.37", 2020);
			ServerHostInterface shi = (ServerHostInterface)registry.lookup(SERVER_NAME);
			
			shi.checkPlayer("michael", "password");
			

			LoginWindow.init(shi);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginWindow.init();
	}
}
