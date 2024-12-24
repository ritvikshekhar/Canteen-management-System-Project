import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Map;
import static org.junit.Assert.assertNull;

public class LoginTest {
    public Map<String, Customer> link;

    @Before
    public void setUp(){
        try (ObjectInputStream IN = new ObjectInputStream(new FileInputStream("users.txt"))) {
            link = (Map<String, Customer>) IN.readObject();
            System.out.println("Data deserialized successfully for link.");

        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during deserialization for link.");
        }
//
    }
    @Test
    public void testLogin(){
        String username = "kamleswar";
        Object obj=link.get(username);

        assertNull("Test Case Failed: able to login ", obj);
        if(obj==null){
            System.out.println("Test Case Passed: Not able to login ");
        }

    }

}

