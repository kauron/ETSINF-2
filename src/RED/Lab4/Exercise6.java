import java.net.*;
import java.util.*;
import java.io.*;

public class Exercise6 {
	static Socket s;
	static Scanner in;
	static PrintWriter out;

	static final int HELO = 0, MAIL_FROM = 1, RCPT_TO = 2, DATA = 3, BODY = 4, QUIT = 5;

	static final String[] COMMANDS = {
		"HELO i.am.groot",
		"MAIL FROM:<cargaji@inf.upv.es>",
		"RCPT TO:<cargaji@inf.upv.es>",
		"DATA",
		"Subject: Hello World\r\nHello world\r\n.",
		"QUIT"
	};

	static final String[] RESPONSES = {
		"250", "250", "250", "354", "250", "221"
	};
	
	public static void main(String[] args) throws Exception{
		//initialize variables
		s = new Socket("smtp.upv.es", 25);
		in = new Scanner(s.getInputStream());
		out = new PrintWriter(s.getOutputStream(), true);

		if (s.isConnected()) {
			//Read the first line of code
			System.out.println(in.nextLine()); 
			for (int i = 0; i < COMMANDS.length; i++) {
				//send each line of code and listen to the response (une line each)
				if (!sendAndCheck(COMMANDS[i], RESPONSES[i])) break;
			}
					
		}
	}

	private static boolean sendAndCheck (String message, String response) {
		out.println(message);
		System.out.println(message);
		//if the code is not the correct one, quit
		String fromServer = in.nextLine();
		System.out.println(fromServer);
		if (fromServer.indexOf(response) != 0) {
			sendAndCheck(COMMANDS[QUIT], RESPONSES[QUIT]);
			return false;
		} else return true;
	}
}
