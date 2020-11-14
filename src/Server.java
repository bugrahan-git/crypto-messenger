import java.net.ServerSocket;

public class Server {
    private int port;
    
    public Server() {
	this.port = 32222;
    }
    
    public static void main(String ... args) {
	Server S = new Server();
	
	S.start();
    }    
    
    private void start() {
	try(ServerSocket ss = new ServerSocket(this.port)) {
	    System.out.println("Server is running on port " + this.port); 
	    while(true);
	    
	}catch(Exception e) {
	    e.printStackTrace();
	}
    }
}
