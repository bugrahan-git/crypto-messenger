import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private Socket socket;
    private DataInputStream in;
    
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
			while (true) {
				System.out.println("test");
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
