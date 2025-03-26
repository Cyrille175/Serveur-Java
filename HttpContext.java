import java.io.IOException;
import java.net.Socket;

public class HttpContext {
    Socket socket;

    HttpContext(Socket Sockets) {
        socket = Sockets;
    }

    HttpRequest get_request() {
        return new HttpRequest(socket);
    }
    HttpResponse get_response() {
        return new HttpResponse(socket);
    }
    public void close() throws IOException {
        socket.close();
    }
}

