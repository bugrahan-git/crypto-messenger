import javax.crypto.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

public class Server {
    private final int port;
    private final Set<UserThread> userThreads = new HashSet<>();
    private byte[] IvDES = new byte[8];
    private byte[] IvAES = new byte[16];
    private Key keyDES;
    private Key keyAES;

    public Server(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            keygen();
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
        for (UserThread ut : userThreads) {
            ut.sendMessage(message);
        }
    }

    void removeUser(String userName, UserThread ut) {
        userThreads.remove(ut);
        System.out.println("User " + userName + " disconnected from server.");
    }

    private void keygen() {
        SecureRandom secRandom = new SecureRandom();

        try {
            secRandom.nextBytes(IvDES);
            secRandom.nextBytes(IvAES);

            KeyGenerator keygenDES = KeyGenerator.getInstance("DES");
            KeyGenerator keygenAES = KeyGenerator.getInstance("AES");

            keygenDES.init(secRandom);
            keygenAES.init(secRandom);

            keyDES = keygenDES.generateKey();
            keyAES = keygenAES.generateKey();

            setIvAES(IvAES);
            setIvDES(IvDES);

            setKeyAES(keyAES);
            setKeyDES(keyDES);

            System.out.println("Generated Key (AES): " + getKeyAES());
            System.out.println("Generated Key (DES): " + getKeyDES());
            System.out.println("Generated IV (AES): " + getIvAES());
            System.out.println("Generated IV (DES): " + getIvDES());
            System.out.println();
            FileWrite.getInstance().write("Generated Key (AES): " + getKeyAES());
            FileWrite.getInstance().write("Generated Key (DES): " + getKeyDES());
            FileWrite.getInstance().write("Generated IV (AES): " + getIvAES());
            FileWrite.getInstance().write("Generated IV (DES): " + getIvDES()+ "\n");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getKeyDES() {
        return Base64.getEncoder().encodeToString(keyDES.getEncoded());
    }

    public void setKeyDES(Key keyDES) {
        this.keyDES = keyDES;
    }

    public String getKeyAES() {
        return Base64.getEncoder().encodeToString(keyAES.getEncoded());
    }

    public void setKeyAES(Key keyAES) {
        this.keyAES = keyAES;
    }

    public String getIvDES() {
        return Base64.getEncoder().encodeToString(IvDES);
    }

    public void setIvDES(byte[] ivDES) {
        IvDES = ivDES;
    }

    public String getIvAES() {
        return Base64.getEncoder().encodeToString(IvAES);
    }

    public void setIvAES(byte[] ivAES) {
        IvAES = ivAES;
    }

}

class UserThread extends Thread {
    private final Socket socket;
    private final Server server;
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
            writer.println(server.getKeyAES());
            writer.println(server.getKeyDES());
            writer.println(server.getIvAES());
            writer.println(server.getIvDES());

            while(clientMessage != null) {
                clientMessage = reader.readLine();
                serverMessage = userName + "\u708e" + clientMessage;
                if(clientMessage != null)
                    server.broadcast(serverMessage);
            }

            server.removeUser(userName, this);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }
}
