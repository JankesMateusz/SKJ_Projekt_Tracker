public class Tracker {

    private String ip;
    private int port;


    public Tracker(String ip, int port){

        this.ip = ip;
        this.port = port;
    }

    public String getIp(){return ip;}

    public int getPort() {
        return port;
    }
}
