// CSD 2013, Pablo Galdámez

//
// This file provides a basic and simple command line parsing to configure our chat programs.
// A ChatConfiguration is always ready to be used as a singleton object. This object must be 
// created at early stages. You can see ChatServer.java or ChatClient.java main() to realize where
// argument parsing occurs.
//

public class ChatConfiguration {
    private String nsHost = "localhost";
    //private int nsPort = 1099;
    private int nsPort = 9000;
    private String serverName = "TestServer";
    private int myPort = 9001;
    private String channelName = "#Friends";

    //
    // Singleton pattern
    //
    private static ChatConfiguration the_ = null;

    public static ChatConfiguration the() {
        return the_;
    }

    public ChatConfiguration() {
        the_ = this;
    }

    public static ChatConfiguration parse(String[] args) {
        ChatConfiguration conf = new ChatConfiguration();
        conf.parseArguments(args);
        return conf;
    }

    //
    // Configurable parameters
    //
    public String getNameServiceHost() {
        return nsHost;
    }

    public int getNameServicePort() {
        return nsPort;
    }

    public String getServerName() {
        return serverName;
    }

    public int getMyPort() {
        return myPort;
    }

    public String getChannelName() {
        return channelName;
    }

    //
    // Private functions to simplify usage.
    //

    private void usage() {
        System.out.println("USAGE ERROR");
        System.out.println("Valid arguments are:");
        System.out.println("   nshost=<host where rmiregistry is running>");
        System.out.println("   nsport=<port where rmiregistry is listening>");
        System.out.println("   server=<ChatServer name we want to use>");
        System.out.println("   myport=<port number where this process will listen>");
        System.out.println("   channel=<Channel name we want to use>");
        System.out.println("");
        System.out.println("Example:");
        System.out.println("   nshost=pc0415.dsic.upv.es nsport=9000 server=test myport=9001");
        System.exit(1);
    }

    private void parseArguments(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("nshost=")) {
                nsHost = arg.substring(7);
            } else if (arg.startsWith("nsport=")) {
                try {
                    nsPort = new Integer(arg.substring(7));
                } catch (Exception e) {
                    usage();
                }
            } else if (arg.startsWith("myport=")) {
                try {
                    myPort = new Integer(arg.substring(7));
                } catch (Exception e) {
                    usage();
                }
            } else if (arg.startsWith("server=")) {
                serverName = arg.substring(7);

            } else if (arg.startsWith("channel=")) {
                serverName = arg.substring(8);
            } else {
                usage();
            }
        }
    }

}
