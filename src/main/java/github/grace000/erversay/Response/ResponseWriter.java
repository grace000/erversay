package github.grace000.erversay.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static github.grace000.erversay.Constants.HTTPLines.DOUBLE_LINE_FEED;

public class ResponseWriter {
    public void write(OutputStream outputStream, Response response) {
        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            output.println(response.status);
            output.println(response.headers);
            output.println(DOUBLE_LINE_FEED);
            output.println(response.body);
            output.flush();
        } catch (IOException e) {
            System.out.println("WRITE RESPONSE ERROR");
            e.printStackTrace();
        }
    }
}
