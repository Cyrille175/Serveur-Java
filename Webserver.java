import java.io.*;
import java.net.*;
import java.io.IOException;


public class Webserver {
    Webserver() {

    }
    public void run(int portNumber) {
            try(ServerSocket serverSocket = new ServerSocket(portNumber)){
                while(true){
                    System.out.println("Serveur en ecoute sur le port " + portNumber);
                    Socket socketClient = serverSocket.accept();
                    RequestProcessor processor = new RequestProcessor(socketClient);
                    processor.process();
                    socketClient.close();
                }
            } catch (IOException e) {
                System.out.println("Erreur de serveur");
            }
        }
    }

