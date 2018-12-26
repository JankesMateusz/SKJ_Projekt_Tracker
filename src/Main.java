public class Main {

    public static void main(String... args)throws Exception{

        String ip = "";
        int port = 0;

        if(args.length > 0){
            ip = args[0];
            if(args.length > 1){
                port = Integer.parseInt(args[1]);
            }
        }

        Tracker tracker = new Tracker(ip, port);
        ConnectionServer connectionServer = new ConnectionServer(tracker);
        connectionServer.initialize();

    }
}
