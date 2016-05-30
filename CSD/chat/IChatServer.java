// CSD 2013, Pablo Galdámez

import java.rmi.Remote;
import java.rmi.RemoteException;

//
// ChatServer object interface. ChatServers have a set of channels and a set of connected users.
//
public interface IChatServer extends Remote {
    IChatChannel[] listChannels() throws RemoteException;

    IChatChannel getChannel(String name) throws RemoteException;

    IChatChannel createChannel(String name) throws RemoteException;

    IChatUser getUser(String nick) throws RemoteException;

    boolean connectUser(IChatUser usr) throws RemoteException;

    boolean disconnectUser(IChatUser usr) throws RemoteException;
}
