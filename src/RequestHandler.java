import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

public class RequestHandler extends Thread{

    private final String[] REQUESTS = {"GET", "UPDATE", "BYE"};
    private final String[] RESPONSES = {"OK", "Not found", "Invalid request"};
    private Socket connectionToHandle;
    private BufferedReader in;
    private PrintWriter out;
    private Pattern requestPattern = Pattern.compile(" ", 2);

    public RequestHandler(Socket socket)throws Exception{

        this.connectionToHandle = socket;
        this.in = new BufferedReader(new InputStreamReader(connectionToHandle.getInputStream()));
        this.out = new PrintWriter(connectionToHandle.getOutputStream());
    }

    public void run(){
        try{

            for(String line; (line = in.readLine()) != null; ){

                String[] request = requestPattern.split(line, 2);
                String compiledRequest = request[0];

                if(compiledRequest.equals("BYE")){
                    System.out.println("DUPA");
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
