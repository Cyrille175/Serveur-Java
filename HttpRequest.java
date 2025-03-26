import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest {
    String methode;
    String url;

    HttpRequest(Socket socket) {
        readClientRequest(socket);
    }

    public void readClientRequest(Socket socket) {
        try (BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String request = entree.readLine();
            System.out.println("Request: " + request);
            if(request != null) {
                String[] parties = request.split(" ");
                if (parties.length >= 2) {
                    methode = parties[0];
                    url = parties[1];
                }
                else {
                    methode = "methode non reconnue";
                    url = "url non reconnue";
                }
            }
        } catch (IOException e) {
            System.out.println("readClientRequest error");
        }
    }

    public String get_methode() {
        return methode;
    }
    public String get_url() {
        return url;
    }
}
