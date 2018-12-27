import java.util.ArrayList;

public class PeerInfo {

    private String number;
    private String ip;
    private String port;
    private ArrayList<String> files;


    public PeerInfo(String number, String ip, String port, ArrayList<String> files){

        this.number = number;
        this.ip = ip;
        this.port = port;
        this.files = files;
    }


    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString(){
        return number + " " + ip + " " + port;
    }
}
