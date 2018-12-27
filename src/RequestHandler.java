import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RequestHandler extends Thread{

    private final String[] REQUESTS = {"GET", "UPDATE", "BYE"};
    private final String[] RESPONSES = {"OK", "Not found", "Invalid request"};

    private ConnectionServer server;
    private Socket connectionToHandle;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectInputStream objectInputStream;
    private Pattern requestPattern = Pattern.compile(" ");

    public RequestHandler(Socket socket, ConnectionServer server)throws Exception{

        this.connectionToHandle = socket;
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(connectionToHandle.getInputStream()));
        this.out = new PrintWriter(connectionToHandle.getOutputStream());
        this.objectInputStream = new ObjectInputStream(connectionToHandle.getInputStream());
    }

    public void run(){
        try{

            for(String line; (line = in.readLine()) != null; ){

                String[] request = requestPattern.split(line);
                String compiledRequest = request[0];

                if(compiledRequest.equals("BYE")){
                    out.println("Bye then!");
                    System.out.println("Closing Connection...");
                    connectionToHandle.close();
                    System.out.println("Closed");
                    break;
                }
                else if(compiledRequest.equals("GET")){
                    System.out.println("GET WHAT?");
                    System.out.println(request[1]);
                }
                else if(compiledRequest.equals("INTRODUCE")){
                    String number = request[1];
                    String ip = request[2];
                    String port = request[3];
                    Object object = objectInputStream.readObject();
                    ArrayList<String> files = (ArrayList<String>) object;
                    server.updatePeers(new PeerInfo(number, ip, port, files));
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
