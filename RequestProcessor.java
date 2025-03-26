import java.io.IOException;
import java.net.Socket;

public class RequestProcessor {

    Socket socket;
    HttpContext context;

    RequestProcessor(Socket sockets) {
        socket = sockets;
        context = new HttpContext(sockets);
    }

    public void process() {
        try{
            String methode = context.get_request().get_methode();
            String url = context.get_request().get_url();
            System.out.println("Methode: " + methode);
            System.out.println("URL: " + url);
            //if (url.equals("/")) {
                context.get_response().ok();
            //} else {
                context.get_response().notFound();
            //}
            context.close();
            System.out.println("Connexion fermée sur le port ");
        } catch (IOException e) {
            System.out.println("Process close error");
        }
    }

}

