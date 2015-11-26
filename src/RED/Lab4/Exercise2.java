import java.net.*;
import java.util.*;
import java.io.*;

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(7777);
		PrintWriter out = new PrintWriter(socket.accept().getOutputStream());
		
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		
		out.printf("%02d:%02d:%02d\n", h, m, s);
		out.flush();
		out.close();
		socket.close();
    }
}
