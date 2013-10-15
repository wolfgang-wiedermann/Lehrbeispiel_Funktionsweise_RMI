package vsiw2010.client;

import vsiw2010.framework.NSContext;
import vsiw2010.interf.IHello;
import vsiw2010.interf.IStack;

public class Client {

	/**
	 * Das Beispiel zeigt das Laden eines Proxy-Objekts für das entfernte Objekt,
	 * das durch den symbolischen Namen "Beispiel2" referenziert wird.
	 * 
	 * Anschließend folgt der Aufruf der Methode "sayHello" bei dem über die
	 * Proxy-Methode die Methode sayHello des entsprechenden Objekts im Server
	 * aufgerufen wird.
	 * 
	 * Fügen Sie eine Log-Ausgabe zum Code von HelloImpl.java hinzu und Sie
	 * werden es sehen. (Aber nur, wenn Sie vorher den Server gestartet haben)
	 */
	public static void main(String[] args) throws Exception {

		// Beispiel 1: Aufruf einer Zustandsinvarianten Methode
		NSContext context = new NSContext();		
		IHello h = context.lookup("Beispiel2", IHello.class);
		System.out.println(h.sayHello("World"));
		
		// Beispiel 2: Aufrufe mit Zustandsänderung im serverseitigen Objekt
		IStack s1 = context.lookup("StackBeispiel", IStack.class);
		s1.push("Hallo 1");
		s1.push("Hallo 2");
		s1.push("Hallo 3");
		
		IStack s2 = context.lookup("StackBeispiel", IStack.class);
		System.out.println("s2.pop(): "+s2.pop());
		System.out.println("s1.pop(): "+s1.pop());
		System.out.println("s2.pop(): "+s2.pop());
	}

}
