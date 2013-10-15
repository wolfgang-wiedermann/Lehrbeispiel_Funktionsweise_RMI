package vsiw2010.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;


/**
 * Die Klasse NSContext kapselt den clientseitigen Zugriff auf den Namensdienst
 * und ermöglicht so den Client-Programmen die generierung eines Proxy-Objekts
 * unter Verwendung des symbolischen Namens des im Server vorliegenden Objekts.
 * 
 * @author Wolfgang Wiedermann
 *
 */
public class NSContext {
	
	private String nshost;
	private int nsport;

	/**
	 * Im Fall des vorliegenden Übungsbeispiels ist der Host, auf dem sich der
	 * Namensdienst befindet im Konstruktor der Klasse NSContext festgelegt,
	 * im realen Implementierungen wären die parameter nshost und nsport Parameter,
	 * die in eine Konfigurationsdatei ö.ä. auszulagern wären. 
	 */
	public NSContext() {
		nshost = "localhost";
		nsport = 9090;
	}
	
	
	/**
	 * Die Methode lookup ermöglicht dem Client die generierung eines Proxy-Objekts
	 * unter Angabe des symbolischen Namens und des dazu passenden Interfaces.
	 * 
	 * Alternativ dazu könnte der Typ des Remote-Objekts auch mittels NSResponse 
	 * vom Namensdienst geliefert werden. Dann wäre in diesem Fall aber keine
	 * Verwendung eines Typ-Parameters im Rückgabewert möglich.
	 *  
	 * @param name
	 * @param interf
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T> T lookup(String name, Class<T> interf) throws IOException, ClassNotFoundException
	{
		Socket s = new Socket(nshost, nsport);
		
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		NSRequest request = new NSRequest();
		request.setName(name);
		oos.writeObject(request);
		
		NSResponse response = (NSResponse)ois.readObject();
		
		Class<?> interfaces[] = {interf};
		return (T)Proxy.newProxyInstance(interf.getClassLoader()
				, interfaces
				, new InvocationHandlerImplementation(response.getHostname()
						, response.getPort(), response.getObjectId()));
		
	}
	
}
