import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpResponse {
    private BufferedWriter output;

    // Constructeur : initialise le BufferedWriter pour écrire dans le flux de sortie
    public HttpResponse(Socket socket) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            output = new BufferedWriter(osw);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'initialisation du BufferedWriter.");
        }
    }

    // Méthode pour envoyer une réponse HTTP 200 OK
    public void ok() {
        try {
            String message = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    "<html><body><h1>Hello, world!</h1></body></html>";
            output.write(message);  // Écriture du message dans le flux de sortie
            output.flush();         // Assure l'envoi des données au client
        } catch (IOException e) {
            System.out.println("Erreur lors de l'envoi de la réponse HTTP 200 OK.");
        } finally {
            try {
                output.close();  // Fermeture du BufferedWriter après envoi
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du BufferedWriter.");
            }
        }
    }

    // Méthode pour envoyer une réponse HTTP 404 Not Found
    public void notFound() {
        try {
            String message = "HTTP/1.1 404 Not Found\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    "<html><body><h1>404 - Page Not Found</h1></body></html>";
            output.write(message);  // Écriture du message dans le flux de sortie
            output.flush();         // Assure l'envoi des données au client
        } catch (IOException e) {
            System.out.println("Erreur lors de l'envoi de la réponse HTTP 404 Not Found.");
        } finally {
            try {
                output.close();  // Fermeture du BufferedWriter après envoi
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du BufferedWriter.");
            }
        }
    }
}
