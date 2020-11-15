import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int port = 32222;
    
    @SuppressWarnings("resource")
	public static void main(String ... args) {
		Socket socket = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is running on port " + port);
		}catch(Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new UserThread(socket).start();
		}
	}
}

class UserThread extends Thread {
	protected Socket socket;

	public UserThread(Socket clientSocket) {
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
