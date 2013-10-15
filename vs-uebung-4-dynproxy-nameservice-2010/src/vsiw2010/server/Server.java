package vsiw2010.server;

import java.io.IOException;

import vsiw2010.framework.NameService;
import vsiw2010.framework.ObjectService;

/**
 * Die Klasse Server zeigt die Umsetzung eines einfachen Servers unter
 * Verwendung des vorliegenden Remoting-Frameworks.
 * 
 * In ihm sind zwei Objekte verschiedener Klassen unter den symbolischen
 * Namen "Beispiel1" und "Beispiel2" mit den Objekt-Ids 0 und 1 registriert.
 * 
 * Der Server enthält dazu sowohl den erforderlichen Namensdienst als auch
 * den erforderlichen Objektserver-Dienst. Prinzipiell kann der Namensdienst 
 * von mehreren Objektserver-Diensten gemeinsam genutzt werden. 
 * 
 * @author Wolfgang Wiedermann
 *
 */
public class Server {

	public static NameService ns = new NameService();
	public static ObjectService os = new ObjectService(ns);
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		os.registerObject("Beispiel1", new MathImpl());
		os.registerObject("Beispiel2", new HelloImpl());
		os.registerObject("StackBeispiel", new StackImpl());
		os.start();
	}

}
