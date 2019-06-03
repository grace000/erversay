package github.grace000.erversay;

import org.junit.Test;
import static org.junit.Assert.*;

public class HandleGetRequestTest {

    @Test
    public void whenClientMakesGetRequest_itReceives200StatusCode(){
        try (Client client = new Client.forLocalServer()) {
            client.connect();

            ResponseCode responseCode = 200;
            ResponseBody responseBody = "";

            ServerOutput serverOutput = (responseBody).getBytes();

            ServerResponse serverResponse = client.send(serverOutput);

            assertEquals(serverOutput, serverResponse);

        }
    }

}
