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
//			Registry registry = LocateRegistry.getRegistry("174.100.38.37", 2020);
//			ServerHostInterface shi = (ServerHostInterface)registry.lookup(SERVER_NAME);
//			
//			shi.checkPlayer("michael", "password");
			
			System.setProperty("java.rmi.server.hostname", "174.100.38.37");
            Registry registry = LocateRegistry.getRegistry("174.100.38.37", 1099);
            ServerHostInterface shi = (ServerHostInterface)registry.lookup("SERVER_NAME");
            shi.checkPlayer("michael", "password");
			

			LoginWindow.init(shi);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginWindow.init();
	}
}
