package vsiw2010.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;



public class NameService extends Thread {
	
	private HashMap<String, Long> nameservice = new HashMap<String, Long>();
	
	public NameService()
	{
		this.start();
	}
	
	public void register(String name, long id)
	{
		synchronized(nameservice) {
			nameservice.put(name, id);
		}
	}
	
	public void run() 
	{
		try
		{
			ServerSocket nserver = new ServerSocket(9090);
			
			while(true) {
				Socket connection = nserver.accept();
				
				ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
				try
				{
					NSRequest r = (NSRequest)ois.readObject();
					Long oid = (Long)nameservice.get(r.getName());
					
					NSResponse response = new NSResponse();
					response.setHostname(nserver.getInetAddress().getHostName());
					response.setPort(9001);
					response.setObjectId(oid);
					
					oos.writeObject(response);
				}
				catch(Exception ex)
				{
					System.out.println("ERROR: "+ex.getMessage());
				}
			}

		}
		catch(Exception e)
		{
			System.out.println("ERROR: "+e.getMessage());
		}
	}

}
