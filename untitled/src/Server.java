import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private static Server instance = null;
    private ServerSocket server = null;
    private int port;
    private static ReentrantLock mutex = new ReentrantLock();
    private static ArrayList<Human> persons = new ArrayList<Human>();

    private Server(int port) {
        try {
            System.out.println("Server has started!");
            this.port = port;
            this.server = new ServerSocket(this.port);
            this.server.setReuseAddress(true);

            while (true) {
                Socket client = server.accept();

                System.out.println("New client connected " + client.getInetAddress().getHostAddress());

                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance(int port) {
        if(instance==null) {
            instance=new Server(port);
        }
        return instance;
    }

    public static void addPerson(Human obj) {
        mutex.lock();
        persons.add(obj);
        persons.sort(Human::compareTo);
        mutex.unlock();
    }

    public static ArrayList<Human> getData() {
        return persons;
    }

    public static void main(String[] args) {
        Server server = Server.getInstance(Integer.parseInt(args[0]));
    }
}
