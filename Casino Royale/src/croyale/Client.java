package croyale;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import croyale.rpc.Constants;
import croyale.rpc.ServerHostInterface;
import croyale.security.ClientSecurity;

public class Client implements Constants
{
	public static void main(String[] args)
	{
		try {
			System.setProperty("java.rmi.server.hostname", "caladia.net");
			Registry registry = LocateRegistry.getRegistry("caladia.net", 1099);
			ServerHostInterface shi = (ServerHostInterface)registry.lookup(SERVER_NAME);
			
			ClientSecurity cs = new ClientSecurity(shi);
			cs.doKeyAgree();
			
			cs.checkPlayer("michael", "password");
			
			LoginWindow.init(cs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}