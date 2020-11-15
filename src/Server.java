import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private Socket socket = null;
    private DataInputStream in = null;
    public Server() {
	this.port = 32222;
    }
    
    public static void main(String ... args) {
	Server S = new Server();
	
	S.start();
    }    
    
    private void start() {
		System.out.println("Server is running on port " + this.port);
		try(ServerSocket ss = new ServerSocket(this.port)) {
			while (true){
				socket = ss.accept();
				in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
				System.out.println(in.readUTF());
			}
	}catch(Exception e) {
	    e.printStackTrace();
	}
    }
}
