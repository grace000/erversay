package github.grace000.erversay.Response;

import java.io.*;

import static github.grace000.erversay.Constants.HTTPLines.*;

public class ResponseWriter {
    public void write(OutputStream outputStream, Response response) {
        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            System.out.println("Here is your header: " + response.headers);
//            System.out.println("Here is your body: " + response.body.toString());
            System.out.println("Here is your status!: " + response.status);


//            output.println(DEFAULT_VERSION + SP + response.status);
//            output.println(response.headers);
//            output.println("Content-Length: " + response.contentLength);
            byte[] status = (DEFAULT_VERSION + SP + response.status + CRLF).getBytes();
            byte[] headers = (response.headers).getBytes();
//            byte[] content = ("Content-Length: " + response.contentLength + CRLF).getBytes();

            outputStream.write(status);
            outputStream.write(headers);
//            outputStream.write(content);
//            outputStream.write(response.body);
//            output.print(response.body);
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
