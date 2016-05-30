// CSD 2013, Pablo Galdámez

import java.rmi.Remote;
import java.rmi.RemoteException;

//
// Channels have a set of connected users. Sending a message to a channel means to broadcast it to 
// the channel users.
//
public interface IChatChannel extends Remote {
    boolean join(IChatUser usr) throws RemoteException;

    boolean leave(IChatUser usr) throws RemoteException;

    void sendMessage(IChatMessage msg) throws RemoteException;

    IChatUser[] listUsers() throws RemoteException;

    String getName() throws RemoteException;
}
