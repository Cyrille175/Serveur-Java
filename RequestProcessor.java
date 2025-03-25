import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestProcessor {
    Socket socket;
    RequestProcessor(Socket sockets) {
        socket = sockets;
    }
    public void process() {
        HttpContext context = new HttpContext(socket);
        String methode = context.request.get_methode();
        String url = context.request.get_url();
        if (url.equals("/")) {
            context.get_response().ok();
        }
        else {
            context.get_response().notFound();
        }
    }

}
