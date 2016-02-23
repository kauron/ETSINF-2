import java.net.*;

public class Exercise1 {
	public static void main(String[] args) throws Exception{
		ServerSocket socket = new ServerSocket(7777);
		socket.accept();
		System.out.println("Connection established");
        socket.close();
	}
}
