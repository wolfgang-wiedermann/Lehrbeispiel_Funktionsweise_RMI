package vsiw2010.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;

/**
 * Objekte der Klasse ObjectService implementieren je einen Serverdienst, der
 * verschiedene Objekte aufnehmen kann und diese für entfernte Aufrufe zur Verfügung stellt.
 * Dazu bietet er eine Methode an, mittels der Objekte übergeben und gleichzeitig
 * bei dem ihm zugeordneten Namensdienst registriert werden können.
 * 
 * In diesem Beispiel ist der ObjectService nicht als eigener Thread realisiert. 
 * Die Umsetzung ist hier in Form eines einfachen sequenziellen Servers. 
 * 
 * @author Wolfgang Wiedermann
 */
public class ObjectService {

	public NameService ns;
	public HashMap<Long, Object> objects = new HashMap<Long, Object>();
	
	public ObjectService(NameService ns) {
		this.ns = ns;
	}
	
	public void registerObject(String name, Object object) {
		synchronized(this.objects) {
			Long newId = (objects.size() > 0)?Collections.max(objects.keySet())+1:0;
			this.ns.register(name, newId);
			this.objects.put(newId, object);
		}
	}
	
	/**
	 * ACHTUNG: Nach dem Aufruf von start ist wegen der Umsetzung als sequenzieller Server
	 * kein weiteres Registrieren von Objekten mittels registerObject mehr möglich, da die enthaltene
	 * Endlosschleife blockierend wirkt.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		
		ServerSocket server = new ServerSocket(9001);
		while(true) {
			Socket connection = server.accept();
			
			ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
			try
			{
				Request r = (Request)ois.readObject();
				Object o = objects.get(r.getObjectId());
				Method m = o.getClass().getMethod(r.getMethodName(), r.getParamTypes());
				Object value = m.invoke(o, r.getParams());
				Response response = new Response();
				response.setValue(value);
				oos.writeObject(response);
			} 
			catch(Exception ex)
			{
				// ClassNotFoundException; NoSuchMethodException; InvocationTargetException
				// gemeinsam gefangen, um das Übungsbeispiel kurz zu halten und die 
				// Endlosschleife des Servers im Fehlerfall nicht abzubrechen.
				ex.printStackTrace();
			}
		}

	}

}
