package vsiw2010.server;

import vsiw2010.interf.IHello;

/**
 * Die Klasse HelloImpl ist eine Beispiel-Implementierung 
 * für die Klasse eines einfachen. im Server des vorliegenden Beispiels
 * registrierten Objekts.
 * 
 * @author Wolfgang Wiedermann
 */
public class HelloImpl implements IHello {

	@Override
	public String sayHello(String name) {
		return "Hello "+name;
	}

}
