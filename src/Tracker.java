public class Tracker {

    private String ip = "127.168.1.1";
    private int port = 10000;

    private ConnectionServer connectionServer;


    public Tracker(){
        this.connectionServer = new ConnectionServer(port, ip);
    }

    public void start()throws Exception{
        connectionServer.initialize();
    }
}
