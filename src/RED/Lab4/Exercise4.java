import java.net.*;
import java.io.*;
import java.util.*;

public class Exercise4 {
	public static void main(String[] args) throws Exception{
		//init server and connection
		ServerSocket s = new ServerSocket(7777);
		Socket connection = s.accept();
		PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
		Scanner in = new Scanner(connection.getInputStream());

		//read input from user and send back response
		String request = "";
		String last;
		while(!(last = in.nextLine()).isEmpty()) {
			request += last + "\r\n";
		}
		out.print("HTTP/1.0 200 OK \r\n" + 
				"Content-Type: text/plain \r\n\r\n" +
				request);
		out.flush();

		//Close all streams and sockets
		in.close();
		out.close();
		connection.close();
		s.close();
	}
}
