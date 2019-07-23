package github.grace000.erversay.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static github.grace000.erversay.Constants.HTTPLines.*;

public class ResponseWriter {
    public void write(OutputStream outputStream, Response response) {
        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            System.out.println("Here is your header: " + response.headers);
            System.out.println("Here is your body: " + response.body);
            System.out.println("Here is your status!: " + response.status);
            output.println(DEFAULT_VERSION + SP + response.status);
            output.println(response.headers);
            output.println("Content-Length: " + response.contentLength);
            output.println(CRLF + response.body);
            System.out.println("WRITER: MESSAGE SENT");
            output.flush();
        } catch (IOException e) {
            System.out.println("WRITE RESPONSE ERROR");
            e.printStackTrace();
        }
    }
}
