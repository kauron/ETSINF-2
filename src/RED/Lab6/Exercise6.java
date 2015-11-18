package RED.Lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Exercise6 {
    static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(7777);
        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[256], 256);
            socket.receive(packet);
            byte[] buf = dateFormat.format(Calendar.getInstance().getTime()).getBytes();
            socket.send(new DatagramPacket(buf, buf.length,
                    packet.getAddress(), packet.getPort()));
//            socket.disconnect();
        }
    }
}
