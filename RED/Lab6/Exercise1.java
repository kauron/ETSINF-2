package RED.Lab6;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Exercise1 {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getByName("localhost"));
    }
}
