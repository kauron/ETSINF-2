package RED.Lab5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class EchoServer {
    static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    protected static final int THREADS = 10;
    protected static final String logfile = "/home/carlos/javalog.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(7777);
        String[] logs = new String[THREADS];
        EchoSocket[] th = new EchoSocket[THREADS];
        WaitThread wt = new WaitThread(th, logs);
        wt.start();
        while(!ss.isClosed()) {
            //Check free position
            int position = -1;
            //loop until some client disconnects
            for (int i = 0; i < THREADS; i++) {
                if (th[i] == null) {
                    position = i;
                    break;
                }
            }
            if (position != -1) {
                th[position] = new EchoSocket(ss.accept(), logs, position);
                th[position].start();
            }
        }
    }
}

class WaitThread extends Thread {
    String[] logs;
    EchoSocket[] th;

    WaitThread(EchoSocket[] th, String[] logs) {
        this.th = th;
        this.logs = logs;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw;
            //Free space if necessary
            while (true) {
                for (int i = 0; i < EchoServer.THREADS; i++) {
                    //check if ready, then wait
                    if (th[i] != null && !th[i].isAlive()) {
                        th[i].join();
                        th[i] = null;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EchoSocket extends Thread {
    protected Socket s;
    private static final String QUIT = "QUIT";
    private static final String SOURCE = "https://github.com/kauron/ETSINF-2/blob/master/src/RED/Lab5/EchoServer.java";
    private String[] log;
    private int position;


    EchoSocket(Socket s, String[] log, int position) {
        this.s = s;
        this.log = log;
        this.position = position;
    }

    @Override
    public void run() {
        try {
            //initial setup
            Scanner input = new Scanner(s.getInputStream());
            PrintWriter output = new PrintWriter(s.getOutputStream(), true);
            String echo;
            //welcome to the server
            output.print("My cool raspberry echo server\n");
            output.print("Source code at: " + SOURCE + '\n');
            output.println("To exit type " + QUIT);
            //print date and user ip for entrance
            (new LogThread(true, s.getRemoteSocketAddress().toString())).start();
            //begin scanning
            while (!(echo = input.nextLine() + '\n').equals(QUIT + '\n')) {
                log[position] += echo;
                output.printf(echo);
                (new LogThread(echo, s.getRemoteSocketAddress().toString())).start();
            }
            //print date and user ip for exit
            (new LogThread(false, s.getRemoteSocketAddress().toString())).start();
            //close connections
            output.close();
            input.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class LogThread extends Thread {
    private String log, ip;
    private static final String FILE = "/home/carlos/javalog.txt";

    LogThread(String log, String ip) {this.log = log; this.ip = ip;}
    LogThread(boolean connection, String ip) {
        if (connection) log =   "------Connected----\n";
        else log =              "----Disconnected---\n";
        this.ip = ip;
    }

    @Override
    public synchronized void run() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(FILE, true), true);
            pw.printf("%s\t%s\t\t%s",
                    EchoServer.dateFormat.format(Calendar.getInstance().getTime()),
                    ip,
                    log);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
