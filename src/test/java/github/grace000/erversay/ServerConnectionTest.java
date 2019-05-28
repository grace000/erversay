package github.grace000.erversay;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServerConnectionTest {

    @Test
    public void it_accepts_connection() {
       ServerConnection connection = new ServerConnection();
        assertEquals(connection.acceptConnection(), "Howdy");

    }

}
