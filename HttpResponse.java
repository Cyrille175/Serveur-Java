import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpResponse {
    BufferedWriter output ;

    HttpResponse(Socket socket){
        try {
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ok() {
        try{
           output.write("HTTP/1.0 200 Message");
        }
        catch(IOException e){
            System.out.println("Erreur serveur");
        }
    }
    public void notFound() {
        try{
            output.write("HTTP/1.0 404 Message ");
        }
        catch(IOException e){
            System.out.println("Erreur serveur");
        }
    }
}
