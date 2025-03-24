import java.io.*;
import java.net.*;
import java.io.IOException;


public class Webserver {
    public void run(int portNumber) throws IOException {
           try(ServerSocket serverSocket = new ServerSocket(portNumber)){
               System.out.println("Serveur en ecoute sur le port " + portNumber);
               Socket socketClient = serverSocket.accept();
               readRequest(socketClient);
               readResponse(socketClient);
               socketClient.close();
           }
    }

    public void readRequest(Socket socket) {
        try (BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            String request = entree.readLine();
            System.out.println("Client received: " + request);
        }
        catch (IOException e){
            System.out.println("Erreur de connexion");
        }
    }

    public void readResponse(Socket socket) {
        try (BufferedWriter sortie = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            sortie.write("HTTP/1.1 200 OK\r\n");
            sortie.flush();
        } catch (IOException e) {
            System.out.println("Erreur serveur");
        }
    }

}
