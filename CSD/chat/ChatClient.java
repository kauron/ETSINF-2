// CSD 2013, Pablo Galdámez

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//
// Main Client process.
// It creates a user interface. On UI events that happen at the UI interface, this class 
// methods are invoked to connect to the server, to retrieve its channels, to disconnect, etc.
//
// It also contains a basic message listener for one chat user.
//
//
public class ChatClient
        implements MessageListener {
    private ChatConfiguration conf;
    private IChatServer srv = null;   // We just connect to one single server
    private IChatUser myUser = null;  // Our own user
    private ChatUI ui = null;         // User interface for fancy UI-driven chatting.
    private boolean bConnected = false;

    public ChatClient(ChatConfiguration conf) {
        this.conf = conf;
    }

    // Utility functions
    private void setUI(ChatUI ui) {
        this.ui = ui;
    }

    public String getDefaultServerName() {
        return conf.getServerName();
    }

    //
    // The first thing to do before chatting is to connect to a ChatServer!!
    //
    // For us, connect means to locate it, register a new user into it and retrieve its channel list.
    // On success, returns the server channel list.
    //
    public String[] doConnect(String serverName, String nick) throws Exception {

        // Locate server using the name service
        try {
            Registry reg = LocateRegistry.getRegistry(conf.getNameServiceHost(),
                    conf.getNameServicePort());

            srv = (IChatServer) reg.lookup(serverName);
            //System.out.println ("LOG==> ChatServer: " + srv);
        } catch (java.rmi.ConnectException e) {
            throw new Exception("rmiregistry not found at '" +
                    conf.getNameServiceHost() + ":" + conf.getNameServicePort() + "'");
        } catch (java.rmi.NotBoundException e) {
            throw new Exception("Server '" + serverName + "' not found.");
        }

        // Once we've got the server, we create a local user object and register it into the server
        myUser = new ChatUser(nick, this);
        boolean done = srv.connectUser(myUser);
        if (!done) throw new Exception("Nick already in use");

        // Once we've registered, retrieve the channel list
        IChatChannel[] channels = srv.listChannels();
        if (channels == null || channels.length == 0)
            throw new Exception("Server has no channels");

        // Convert channel list to string list, since we don't want the UI to know about invokable
        // objects. It is a plain UI which does not depend on RMI,
        String list[] = new String[channels.length];
        for (int i = 0; i < channels.length; i++) {
            list[i] = channels[i].getName();
        }

        // Things went fine :)
        bConnected = true;

        return list;
    }

    //
    // Disconnect allows server to free up resources and remove stale references.
    //
    public void doDisconnect() {
        bConnected = false;
        try {
            if (myUser != null) srv.disconnectUser(myUser);
        } catch (Exception e) {
        }
    }

    //
    // When users leave a chat lounge they exit the Channel using this method.
    //
    public void doLeaveChannel(String channelName) throws Exception {
        IChatChannel ch = srv.getChannel(channelName);
        if (ch != null) {
            ch.leave(myUser);
        }
    }

    //
    // To chat in a channel we require users to join.
    // On success, returns the user list
    //
    public String[] doJoinChannel(String channelName) throws Exception {

        IChatChannel ch = srv.getChannel(channelName);
        if (ch == null) {
            throw new Exception("Channel not found");
        }

        ch.join(myUser); // join
        IChatUser[] users = ch.listUsers();
        if (users == null || users.length == 0)
            throw new Exception("BUG. Tell professor there are no users after joining");

        String[] userList = new String[users.length];
        for (int i = 0; i < users.length; i++) {
            userList[i] = users[i].getNick();
        }

        return userList;
    }

    //
    // UI wants to send a message to a channel... lets do it creating a IChatMessage
    //
    public void doSendChannelMessage(String dst, String msg) throws Exception {
        try {
            IChatChannel c_dst = srv.getChannel(dst);
            IChatMessage c_msg = new ChatMessage(myUser, c_dst, msg);
            c_dst.sendMessage(c_msg);
        } catch (Exception e) {
            throw new Exception("Cannot send message: " + e);
        }
    }

    //
    // UI wants to send a private message to some user... lets do it creating a IChatMessage
    //
    public void doSendPrivateMessage(String dst, String msg) throws Exception {
        try {
            IChatUser u_dst = srv.getUser(dst);
            IChatMessage c_msg = new ChatMessage(myUser, u_dst, msg);
            if (u_dst == null) throw new Exception("User disconnected");
            else u_dst.sendMessage(c_msg);
        } catch (Exception e) {
            throw new Exception("Cannot send message: " + e);
        }

    }

    //
    // On window close, try to disconnect
    //
    public void doTerminate() {
        doDisconnect();
        System.exit(0);
    }

    //
    // ISA MessageListener
    // Messages come from a channel or from a remote user.
    //
    public void messageArrived(IChatMessage msg) {
        try {
            IChatUser src = msg.getSender();
            Remote dst = msg.getDestination();
            String str = msg.getText();

            if (msg.isPrivate()) {
                IChatUser u_dst = (IChatUser) dst;
                ui.showPrivateMessage(src.getNick(), u_dst.getNick(), str);

            } else {
                IChatChannel c_dst = (IChatChannel) dst;
                if (src == null) { // Control message from the channel itself
                    String nick;
                    if (str.startsWith(ChatChannel.LEAVE)) {
                        nick = str.substring(ChatChannel.LEAVE.length() + 1);
                        ui.showUserLeavesChannel(c_dst.getName(), nick);
                    } else if (str.startsWith(ChatChannel.JOIN)) {
                        nick = str.substring(ChatChannel.JOIN.length() + 1);
                        ui.showUserEntersChannel(c_dst.getName(), nick);
                    }
                } else { // Normal channel message
                    ui.showChannelMessage(src.getNick(), c_dst.getName(), str);
                }
            }
        } catch (Exception e) {
            ui.showErrorMessage("Error when receiving message: " + e.getMessage());
        }
    }

    //
    // Main program, just creates the Client object, the program frame and shows it.
    //
    public static void main(String[] args) {
        ChatClient cc = new ChatClient(ChatConfiguration.parse(args));
        ChatUI ui = new ChatUI(cc);
        cc.setUI(ui);
        ui.show();
    }

}