import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;

    public Server() {
    	this.port = 32222;
    }
    
    public static void main(String ... args) {
		Socket socket = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(32222);
			System.out.println("Server is running on port " + 32222);

		}catch(Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new EchoThread(socket).start();
		}
	}
}

class EchoThread extends Thread {
	protected Socket socket;

	public EchoThread(Socket clientSocket) {
		this.socket = clientSocket;
	}

	public void run() {
		DataInputStream inp;
		while(true){
		try {
			inp = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			System.out.println(inp.readUTF());

		} catch (IOException e) {
			return;
		}
	}}
}
