import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Webserver server = new Webserver();
        server.run(8000);

    }

}
