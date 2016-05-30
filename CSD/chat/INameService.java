// CSD 2013, Pablo Galdámez
// This file must be studied when completing activity 3.
//

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

//
// Name Service interface similar to that of RMI (rmiregistry)
//
public interface INameService extends Remote {
    // Tries to bind an object to a name. If name was already present,
    // throws an "AlreadyBoundException"
    void bind(String name, Remote obj) throws RemoteException,
            AlreadyBoundException;

    // Similar to sequentially invoke unbind() followed by bind.
    void rebind(String name, Remote obj) throws RemoteException;

    // If not found, this method does nothing and does not throw any exception
    void unbind(String name) throws RemoteException;

    // Returns the bound object or null of not found.
    Remote resolve(String name) throws RemoteException;
}