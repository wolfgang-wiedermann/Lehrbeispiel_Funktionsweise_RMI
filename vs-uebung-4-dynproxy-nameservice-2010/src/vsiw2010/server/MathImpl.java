package vsiw2010.server;

import vsiw2010.interf.IMath;

/**
 * Die Klasse MathImpl ist eine Beispiel-Implementierung 
 * für die Klasse eines einfachen. im Server des vorliegenden Beispiels
 * registrierten Objekts.
 * 
 * @author Wolfgang Wiedermann
 */
public class MathImpl implements IMath {

	@Override
	public Double add(Double a, Double b) {		
		return a+b;
	}

	@Override
	public Double sub(Double a, Double b) {		
		return a-b;
	}

}
