package RED.Lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Exercise5 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.send(new DatagramPacket(new byte[]{0x4e}, 1,
                InetAddress.getByName("localhost"), 7777));
        byte[] b = new byte[256];
        DatagramPacket p = new DatagramPacket(b, b.length);
        socket.receive(p);
        System.out.println(Arrays.toString(p.getData()));
        System.out.println(new String(p.getData()));
    }
}
