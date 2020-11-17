import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private int port;
    private Set<UserThread> userThreads = new HashSet<UserThread>();

    public Server(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(32222);
        server.execute();
    }


    void broadcast(String message) {
        for (UserThread aUser : userThreads) {
            aUser.sendMessage(message);
        }
    }

    void removeUser(String userName, UserThread aUser) {
        userThreads.remove(aUser);
        System.out.println("User " + userName + " disconnected from server.");
    }

}

class UserThread extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String userName = reader.readLine();

            String serverMessage = "User "+ userName + " connected to server.";
            System.out.println(serverMessage);
            String clientMessage = "";

            while (clientMessage != null){
                clientMessage = reader.readLine();
                serverMessage = userName + ": " + clientMessage;
                if(clientMessage!=null)
                    server.broadcast(serverMessage);
            }
            server.removeUser(userName, this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }
}
