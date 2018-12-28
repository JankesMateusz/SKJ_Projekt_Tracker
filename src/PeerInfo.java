public class PeerInfo {

    private String number;
    private String ip;
    private String port;


    public PeerInfo(String number, String ip, String port){

        this.number = number;
        this.ip = ip;
        this.port = port;
    }


    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getNumber() {
        return number;
    }
}
