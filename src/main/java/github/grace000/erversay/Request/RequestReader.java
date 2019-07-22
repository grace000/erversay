package github.grace000.erversay.Request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestReader {
    public BufferedReader read(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
