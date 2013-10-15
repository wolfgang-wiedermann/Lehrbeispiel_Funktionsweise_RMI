package vsiw2010.framework;

import java.io.Serializable;

/**
 * Die Klasse NSRequest kappselt eine Anfrage beim Namensdienst
 * als übertragbares Objekt.
 * 
 * Hier gilt: ja, auch einzelne Strings sind ohne weitere Kapeslung
 * übertragbar, aber ein Request beim Namensdienst könnte ja aus mehr
 * als nur einem einzelnen String bestehen ...
 * 
 * @author Wolfgang Wiedermann
 *
 */
public class NSRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
