import java.io.IOException;
import java.net.Socket;

public class HttpContext {
    Socket socket;
    HttpRequest request ;
    HttpResponse response;
    HttpContext(Socket Socket) {
        socket = Socket;
        request = new HttpRequest();
        response = new HttpResponse(Socket);
    }
    HttpRequest get_request() {
        return request;
    }
    HttpResponse get_response() {
        return response;
    }
    public void close() throws IOException {
        socket.close();
    }
}
