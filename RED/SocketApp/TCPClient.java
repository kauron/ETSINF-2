package RED.SocketApp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient extends Thread{
    private Socket socket;
    private Scanner inUser, inServer;
    private DataOutputStream outServer;

    public void connect(String server, int port) throws UnknownHostException, IOException{
        socket = new Socket(server, port);
		System.out.println("Connecting!");
		while (!socket.isConnected());
		System.out.printf("Connection established to %s:%d\n",server, port);
		inServer = new Scanner(socket.getInputStream());
        outServer = new DataOutputStream(socket.getOutputStream());
		this.start();
	}

    public boolean isConnected() {return socket.isConnected();}

    public void disconnect() throws IOException{
        inServer.close();
        outServer.close();
        socket.close();
    }

    public void input() throws IOException {
        inUser = new Scanner(System.in);
		System.out.println("BEGIN USER INPUT");
        while(this.getState() != Thread.State.TERMINATED && inUser.hasNext()) {
            outServer.writeBytes(inUser.nextLine() + '\n');
            outServer.flush();
		}
		System.out.println("END USER INPUT");
		inUser.close();
		if (!socket.isConnected()) disconnect();
    }
	
	public void run() {
		System.out.println("BEGIN SERVER INPUT");
		while(socket.isConnected() && inServer.hasNext())
			System.out.println(inServer.nextLine());
		System.out.println("END SERVER INPUT");
	}
}
