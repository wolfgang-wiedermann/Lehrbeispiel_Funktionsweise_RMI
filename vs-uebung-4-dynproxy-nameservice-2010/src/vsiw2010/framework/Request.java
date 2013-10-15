package vsiw2010.framework;

import java.io.Serializable;

/**
 * Die Klasse Request kapselt einen Methodenaufruf als
 * Serialisierbares und somit vom Client zum Server übertragbares Objekt.
 * 
 * Dabei existiert u.A. folgendes Problem. Objekte der Klasse java.lang.reflect.Method
 * sind nicht serialisierbar. Deshalb müssen ihre Inhalte methodName, paramTypes und params
 * einzeln benannt werden. 
 * 
 * (Java-Methoden sind über die Kombination aus methodName und paramTypes eindeutig identifizierbar)
 * 
 * @author Wolfgang Wiedermann
 *
 */
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long objectId;
	private String methodName;
	private Class<?>[] paramTypes;
	private Object[] params;
	
	public long getObjectId() {
		return objectId;
	}
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	
}
