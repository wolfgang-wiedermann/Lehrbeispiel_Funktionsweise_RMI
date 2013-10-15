package vsiw2010.framework;

import java.io.Serializable;

/**
 * Die Klasse Response kapselt das Ergebnis eines Methodenaufruf als
 * über das Netz übertragbares, weil serialisierbares Objekt.
 *  
 * @author Wolfgang Wiedermann
 */
public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
