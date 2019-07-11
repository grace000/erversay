package github.grace000.erversay;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestReader {
//    public String readRequest(BufferedReader bufferedReader) throws IOException {
//        String input;
//        StringBuilder requestLine = new StringBuilder();
//
//
//        while(true) {
//            input = bufferedReader.readLine();
//
//            if(input == null || input.equals("")) {
//                break;
//            } else {
//                requestLine.append(input);
//            }
//        }
//        System.out.println(requestLine.toString());
//        return requestLine.toString();
//    }

//    public String readRequest(BufferedReader bufferedReader) throws IOException {
//        String input;
//        StringBuilder requestLine = new StringBuilder();
//
//            while (bufferedReader.readLine() != null) {
//                input = bufferedReader.readLine();
////                System.out.println(requestLine.append(input));
//                requestLine.append(input);
//            }
//
//        System.out.println(requestLine);
//        return requestLine.toString();
//    }

//    public String readBody(BufferedReader bufferedReader) throws IOException {
//        char[] charBuffer = new char[128];
//        int bytesRead = -1;
//
//        StringBuilder body = new StringBuilder();
//
////        while((input = bufferedReader.read()) !=-1) {
////            c = (char)input;
////            System.out.println(c);
////        }
//
//        for (int i = 0; i < 128; i++) {
////            System.out.println((char)bufferedReader.read());
//            body.append((char)bufferedReader.read());
//        }
////        System.out.println(input);
//        System.out.println(body);
//        return body.toString();
//    }

    public LinkedHashMap<LinkedHashMap, String> readRequest(BufferedReader bufferedReader) throws IOException {

        String firstLine = bufferedReader.readLine();
        LinkedHashMap<LinkedHashMap, String> bufferedInput = null;
        if (firstLine != null){
            LinkedHashMap<String, String> headers = getHeaders(bufferedReader);
            String contentLengthValue = headers.get("Content-Length");
            System.out.println(contentLengthValue);
            String body = getBody(contentLengthValue, bufferedReader);
            System.out.println(body);

            bufferedInput.put(headers, body);
            System.out.println(bufferedInput);
        }

        return bufferedInput;
    }

    private LinkedHashMap<String, String> getHeaders(BufferedReader bufferedReader) throws IOException {
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("")) {
                break;
            } else {
                String[] elementsOfLine = line.split(" ", 2);
                String key = elementsOfLine[0].substring(0, elementsOfLine[0].length() - 1);
                String value = elementsOfLine[1].trim();
                headers.put(key, value);
            }
        }
        System.out.println(headers);
        return headers;
    }

    public String getBody(String contentLengthValue, BufferedReader bufferedReader) throws IOException {
        if (contentLengthValue == null) {
            return null;
        } else {
            int contentLengthAsInt = Integer.parseInt(contentLengthValue);
            StringBuilder body = new StringBuilder();
            for (int i = 0; i < contentLengthAsInt; i++) {
                body.append((char) bufferedReader.read());
            }
            System.out.println(body);
            return body.toString().trim();
        }
    }


}
