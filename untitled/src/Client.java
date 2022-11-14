import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args)
    {
        try {
            Socket socket = new Socket("localhost", Integer.parseInt(args[0]));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String line = null;
            boolean ok = true;

            while (ok) {
                System.out.print("Enter the information: ");
                line = sc.nextLine();

                if(Objects.equals(line, "exit")) {
                    ok = false;
                    System.out.println("Exited");
                    out.println(line);
                    out.flush();
                }
                else {
                    out.println(line);
                    out.flush();
                    if(Objects.equals(line, "?")) {
                        int size = Integer.parseInt(in.readLine());
                        String aux = null;
                        for(int i=0;i<size;i++) {
                            aux = in.readLine();
                            System.out.println(aux);
                        }
                    }
                    else {
                        System.out.println(in.readLine());
                    }
                }
            }

            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
