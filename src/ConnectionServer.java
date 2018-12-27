import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ConnectionServer {

    private ServerSocket serverSocket;
    private int port;
    private String ip;


    public ConnectionServer(Tracker tracker){
        this.port = tracker.getPort();
        this.ip = tracker.getIp();
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
            new RequestHandler(connection).start();
        }
    }

    private void serverLog(String msg){
        System.out.println(msg);
    }
}
