import java.net.*;
import java.io.*;
/**
 * 
 * @author Jompol Sermsook 5610450063
 *		        จอมพล	เสริมสุข 
 */
public class TCPClient {
	
	String sentence, fromSev;
	PrintWriter outToServer;
	BufferedReader inFromServer;
	Socket clientSocket;
	
	public TCPClient(String ip, int port) throws Exception {
		clientSocket = new Socket(ip, port);
		outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	public void sendText(String inFrom) throws Exception {
		sentence = inFrom;
		outToServer.println(sentence);
	}
	public String fromServer() throws Exception {
		fromSev =  inFromServer.readLine();
		return fromSev;
	}
	public void closeSocket() throws Exception {
		clientSocket.close();
	}

}