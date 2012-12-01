package croyale;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import croyale.rpc.Constants;
import croyale.rpc.ServerHost;

public class Server implements Constants
{
	static ServerHost sh;
	static Registry registry;
	
	public static void main(String[] args)
	{
		System.setProperty("java.rmi.server.hostname", "caladia.net");
		registry = null;
		try {
			registry = LocateRegistry.createRegistry(1099);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				registry = LocateRegistry.getRegistry(1099);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Database dbf = new Database("connect");
			sh = new ServerHost(dbf);
			
			registry.rebind(SERVER_NAME, sh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
}