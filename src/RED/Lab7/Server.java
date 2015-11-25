package RED.Lab7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;

public class Server{
    public static void main(String[] args) throws IOException {
        (new TCP()).start();
        (new UDP()).start();
    }
}

class TCP extends Thread{
    ServerSocket ss;
    Socket s;

    TCP () throws IOException {
        ss = new ServerSocket(7777);
    }

    TCP (Socket s) {this.s = s;}

    @Override
    public void run() {
        if (s != null) {
            try {
                Scanner in = new Scanner(s.getInputStream());
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                String echo;
                while(!(echo = in.nextLine()).equals("QUIT"))
                    out.println(echo);
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            while (!ss.isClosed())
                try {
                    (new TCP(ss.accept())).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

class UDP extends Thread {
    DatagramSocket ds;
    DatagramPacket in;

    UDP () throws IOException {
        ds = new DatagramSocket(7777);
    }

    UDP (DatagramPacket dp) {in = dp;}

    @Override
    public void run() {
        if (in != null) {
            try {
                while ((new String(in.getData()).trim().equals("QUIT"))) {
                    ds.send(in);
//                  ds.send(new DatagramPacket(
//                          in.getData(), in.getData().length, in.getAddress(), in.getPort()));
                    in.setData(new byte[1024]);
                    ds.receive(in);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            while (!ds.isClosed()) {
                try {
                    in = new DatagramPacket(new byte[1024], 1024);
                    ds.receive(in);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}