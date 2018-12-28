import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ConnectionServer {

    private ServerSocket serverSocket;
    private int port;
    private String ip;
    private ArrayList<PeerInfo> peers;


    public ConnectionServer(Tracker tracker){
        this.port = tracker.getPort();
        this.ip = tracker.getIp();
        peers = new ArrayList<>();
    }


    public void initialize() throws Exception{

        InetSocketAddress address = new InetSocketAddress(ip, port);
        serverSocket = new ServerSocket();
        serverSocket.bind(address);

        serverLog("Server initialized at port: " + port);
        serverLog("Bind address: " + serverSocket.getInetAddress());
        serverLog("Ready");

        serviceConnections();
    }

    private void serviceConnections()throws Exception{

        boolean serverRunning = true;
        while(serverRunning){
            Socket connection = serverSocket.accept();
            serverLog("Connection established");

            RegistrationHandler handler = new RegistrationHandler(connection, peers);
            handler.start();
        }
    }

    private void serverLog(String msg){
        System.out.println(msg);
    }
    
}
