import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.net.*;
import java.security.Key;
import java.security.SecureRandom;
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
            crypt();
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

    private void crypt()
    {
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

        }catch(Exception e) {
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
