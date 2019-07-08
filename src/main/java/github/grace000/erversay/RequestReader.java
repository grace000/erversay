package github.grace000.erversay;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {
    public String readRequest(BufferedReader bufferedReader) throws IOException {
        String input;
        StringBuilder requestLine = new StringBuilder();

        while(true) {
            input = bufferedReader.readLine();
            if(input == null || input.equals("")) {
                break;
            } else {
                requestLine.append(input);
            }
        }
        return requestLine.toString();
    }
}
