package wiiudev.gecko.client.connector;

import java.io.*;
import java.net.Socket;

/**
 * Client implementation for connecting to the Nintendo Wii U AND communicating with the
 * <a href="https://github.com/wiiudev/pyGecko/blob/master/codehandler/main.c">code handler server</a>
 */
public class Connector
{
	private Socket clientSocket;
	protected DataOutputStream dataSender;
	protected DataInputStream dataReceiver;

	public static final int PORT = 7331;
	private static Connector connector;

	/**
	 * @return The <a href="http://www.javaworld.com/article/2073352/core-java/simply-singleton.html">Singleton</a>
	 * instance of {@link Connector}
	 */
	public static Connector getInstance()
	{
		if (connector == null)
		{
			connector = new Connector();
		}

		return connector;
	}

	/**
	 * A private constructor to prevent instantiation without using {@link Connector#getInstance()}
	 */
	private Connector()
	{

	}

	public Socket getClientSocket()
	{
		return clientSocket;
	}

	/**
	 * Connects to a Nintendo Wii U console via its local IP address AND port {@value #PORT}
	 *
	 * @param ipAddress The local IP address of the Nintendo Wii U console to connect to
	 * @throws IOException
	 */
	public void connect(String ipAddress) throws IOException
	{
		clientSocket = new Socket(ipAddress, PORT);

		dataSender = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
		dataReceiver = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
	}

	public DataOutputStream getDataSender()
	{
		return dataSender;
	}

	public DataInputStream getDataReceiver()
	{
		return dataReceiver;
	}

	/**
	 * Ends the session by closing the socket
	 */
	public void closeConnection() throws IOException, InterruptedException
	{
		try
		{
			clientSocket.close();
			dataSender.close();
			dataReceiver.close();
		}
		catch(Exception ignored)
		{

		}
	}
}