import java.net.*;
import java.util.*;
import java.io.*;

public class Exercise5 {
	static Socket s;
	static Scanner in;
	static PrintWriter out;
	
	public static void main(String[] args) throws Exception{
		//initialize variables
		s = new Socket("smtp.upv.es", 25);
		in = new Scanner(s.getInputStream());
		out = new PrintWriter(s.getOutputStream(), true);

		if (s.isConnected()) {
			in.nextLine();
			send("HELO i.am.groot");
			send("MAIL FROM:<cargaji@inf.upv.es>");
			send("RCPT TO:<cargaji@inf.upv.es>");
			send("DATA");
			send("Subject: Hello world\r\nHello World\r\n.");
			send("QUIT");
		}
	}

	private static String send (String message) {
		out.println(message);
		return in.nextLine();
	}
}
