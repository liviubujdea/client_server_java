import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            boolean ok = true;

            while (ok) {
                line = in.readLine();
                System.out.println("Sent from the client: " + line);
                String[] splited = line.split("\\s+");

                String type = splited[0];
                if(Objects.equals(type, "exit")) {
                    ok = false;
                    System.out.println("Client exited");
                }
                else {
                    if (Objects.equals(type, "?")) {
                        ArrayList<Human> aux = Server.getData();
                        out.println(aux.size());
                        for(Human i:aux) {
                            out.println(i.toString());
                        }
                        out.flush();
                    } else {

                        String nume = splited[1], prenume = splited[2], facultate = splited[3];
                        int varsta = Integer.parseInt(splited[4]);
                        int an;
                        String materie;

                        Human obj = null;
                        if (Objects.equals(type, "Student")) {
                            an = Integer.parseInt(splited[5]);
                            obj = new Student(nume, prenume, facultate, varsta, an);
                            out.println("Serverul a adaugat studentul!");
                            out.flush();
                        }
                        if (Objects.equals(type, "Profesor")) {
                            materie = splited[5];
                            obj = new Profesor(nume, prenume, facultate, varsta, materie);
                            out.println("Serverul a adaugat profesorul!");
                            out.flush();
                        }

                        if (obj != null) {
                            Server.addPerson(obj);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
