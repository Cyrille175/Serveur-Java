import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest {
    String methode;
    String url;
    public void readClientRequest(Socket socket) {
        try (BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String request = entree.readLine();
            String[] parties = request.split(" ");
            methode = parties[0];
            url = parties[1];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
