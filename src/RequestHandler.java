import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class RequestHandler extends Thread {

    private final String[] REQUESTS = {"GET", "UPDATE", "BYE"};
    private final String[] RESPONSES = {"OK", "Not found", "Invalid request"};

    private Socket connectionToHandle;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<PeerInfo> peers;

    public RequestHandler(Socket socket, ArrayList<PeerInfo> peers) throws Exception {

        this.connectionToHandle = socket;
        this.peers = peers;
    }

    public synchronized void run() {
        try {

            this.in = new BufferedReader(new InputStreamReader(connectionToHandle.getInputStream()));
            this.out = new PrintWriter(connectionToHandle.getOutputStream());

            String line = in.readLine();

            if(new RegistrationValidator().validate(line)){

                String[] msgSequence = line.split("_");

                if(peers.size() != 0) {
                    boolean canAdd = true;
                    for (PeerInfo peer : peers) {
                        if (peer.getIp().equals(msgSequence[1])) {
                            out.println("Peer already registered!");
                            out.flush();
                            System.out.println("Peer already registered");
                            canAdd = false;
                        }
                    }
                    if(canAdd){
                        updatePeerList(msgSequence[0], msgSequence[1], msgSequence[1]);

                        System.out.println("Registered client " + msgSequence[0] +
                                " with IP " + msgSequence[1] +
                                " at port " + msgSequence[2]);

                        out.println("OK");
                        out.flush();
                    }
                }
                else{
                    updatePeerList(msgSequence[0], msgSequence[1], msgSequence[1]);

                    System.out.println("Registered client " + msgSequence[0] +
                            " with IP " + msgSequence[1] +
                            " at port " + msgSequence[2]);

                    out.println("OK");
                    out.flush();
                }
            }

            else{
                out.println("Wrong registration form!");
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePeerList(String number, String ip, String port){
        peers.add(new PeerInfo(number, ip, port));
    }
}
