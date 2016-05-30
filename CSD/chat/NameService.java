// This file MUST be implemented by students when completing activity 3
// 

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NameService extends UnicastRemoteObject
        implements INameService {
    public NameService() throws RemoteException {
        super(ChatConfiguration.the().getMyPort());
    }

    // ISA INameService
    public void bind(String name, Remote obj) throws RemoteException,
            AlreadyBoundException {
        System.err.println("This method must be implemented!!!");
    }

    // ISA INameService
    public void rebind(String name, Remote obj) throws RemoteException {
        System.err.println("This method must be implemented!!!");
    }

    // ISA INameService
    public void unbind(String name) throws RemoteException {
        System.err.println("This method must be implemented!!!");
    }

    // ISA INameService
    public Remote resolve(String name) throws RemoteException {
        System.err.println("This method must be implemented!!!");
        return null;
    }

}
