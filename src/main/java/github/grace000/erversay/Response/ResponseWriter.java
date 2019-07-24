package github.grace000.erversay.Response;

import java.io.*;

import static github.grace000.erversay.Constants.HTTPLines.*;

public class ResponseWriter {
    public void write(OutputStream outputStream, Response response) {
        try {
            byte[] status = (DEFAULT_VERSION + SP + response.status + CRLF).getBytes();
            byte[] headers = (response.headers).getBytes();

            outputStream.write(status);
            outputStream.write(headers);
            outputStream.write((DOUBLE_LINE_FEED).getBytes());
            outputStream.write(response.body);

            System.out.println("WRITER: MESSAGE SENT");
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("WRITER: WRITE RESPONSE ERROR");
            e.printStackTrace();
        }
    }
}
