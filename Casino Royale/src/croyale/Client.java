package croyale;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import croyale.rpc.ServerHostInterface;
import croyale.screen.main.MainScreenMVC;
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
			cs.createSession();
			cs.doKeyAgree();
			
			Session mySession = new Session(cs);
			new MainScreenMVC(mySession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}