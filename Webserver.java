import java.net.*;
import java.io.IOException;


public class Webserver {
    Webserver() {

    }
    public void run(int portNumber) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Serveur en ecoute sur le port " + portNumber);
            while (true) {
                Socket socketClient = serverSocket.accept();
                RequestProcessor processor = new RequestProcessor(socketClient);
                processor.process();
            }

        } catch (IOException e) {
            System.out.println("Erreur de serveur");
        }
    }
}

