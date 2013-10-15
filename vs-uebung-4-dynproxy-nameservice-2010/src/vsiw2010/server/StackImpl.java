package vsiw2010.server;

import java.util.Stack;

import vsiw2010.interf.IStack;

/**
 * Implementierung eines serverseitigen Stack-Objekts zur Visualisierung der Auswirkung
 * von serverseitigem Objektzustand auf die Entwicklung im Client zeigen zu können. 
 * 
 * Hier auch ohne weitere Thread-Synchronisation, um in späteren Beispielen das
 * Verhalten bei konkurrierenden Zugriffen aus mehreren Clients zeigen zu können.
 * 
 * @author Wolfgang Wiedermann
 *
 */
public class StackImpl implements IStack {
	
	private Stack<String> stack = new Stack<String>();

	@Override
	public void push(String elem) {
		this.stack.push(elem);
	}

	@Override
	public String pop() {
		return this.stack.pop();
	}

	@Override
	public String peek() {
		return this.stack.peek();
	}

}
