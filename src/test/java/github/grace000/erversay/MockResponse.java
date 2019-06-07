package github.grace000.erversay;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.*;


public class MockResponse {
    private Server server;
    private ServerThread serverThread;
    private HttpClient client;


    @Test
    public void whenClientMakesGetRequest_itReceives200StatusCode() throws Exception {

        ServerSocket serverSocket = new ServerSocket(5000);
        Socket mockConnection = serverSocket.accept();


        serverThread = new ServerThread(mockConnection);
        OutputStream outputFromServer = mockConnection.getOutputStream();
        PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);



        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        serverThread.run();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:5000")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        assertEquals(200, response.statusCode());
        assertEquals("", response.body());
    }


}





