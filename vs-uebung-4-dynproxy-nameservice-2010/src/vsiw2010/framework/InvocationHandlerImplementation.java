package vsiw2010.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;



public class InvocationHandlerImplementation implements InvocationHandler {
	
	private String server;
	private int port;
	private long objectId;
	
	public InvocationHandlerImplementation(String server, int port, long objectId)
	{
		this.server = server;
		this.port = port;
		this.objectId = objectId;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{
		Socket socket = new Socket(server, port);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		
		Request request = new Request();
		request.setObjectId(this.objectId); // TODO: Dynamisch gestalten
		request.setMethodName(method.getName());
		request.setParamTypes(method.getParameterTypes());
		request.setParams(args);
		
		oos.writeObject(request);
		Response resp = (Response)ois.readObject();
		socket.close();
		
		return resp.getValue();
	}

}
