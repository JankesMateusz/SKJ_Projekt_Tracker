import com.sun.security.ntlm.Server;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ConnectionServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    private int port;
    private String ip;


    public ConnectionServer(int port, String ip){
        this.port = port;
        this.ip = ip;
    }


    public void initialize() throws Exception{

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(ip, port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        serverLog("Server initialized at port: " + port);
        serverLog("Bind address: " + serverSocketChannel.getLocalAddress());
        serverLog("Ready");

        serviceConnections();

    }

    private void serviceConnections(){

        boolean serverIsRunning = true;

        while(serverIsRunning){
            try{
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();

                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if(key.isAcceptable()){
                        SocketChannel channel = serverSocketChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        serverLog("Connection accepted");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void serverLog(String msg){
        System.out.println(msg);
    }
}
