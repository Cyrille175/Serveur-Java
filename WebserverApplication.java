import java.io.IOException;

public class WebserverApplication {

    public static void main(String[] args) throws IOException {
        Webserver server = new Webserver();
        server.run(9090);
    }

}
