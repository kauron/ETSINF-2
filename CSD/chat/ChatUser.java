// CSD 2013, Pablo Galdámez

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//
// Simple ChatUser implementation. Notice how this implementation just calls a listener
//
public class ChatUser
        extends UnicastRemoteObject
        implements IChatUser {
    private String nick;
    private MessageListener lis;

    public ChatUser(String nick, MessageListener lis) throws RemoteException {
        super(ChatConfiguration.the().getMyPort());
        this.nick = nick;
        this.lis = lis;
    }

    public String getNick() throws RemoteException {
        return nick;
    }

    public void sendMessage(IChatMessage msg) throws RemoteException {
        lis.messageArrived(msg);
    }
}