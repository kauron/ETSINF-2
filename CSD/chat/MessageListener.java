// CSD 2013, Pablo Galdámez

// Simple interface to be implemented by Chat Client programs.
public interface MessageListener {
    void messageArrived(IChatMessage msg);
}