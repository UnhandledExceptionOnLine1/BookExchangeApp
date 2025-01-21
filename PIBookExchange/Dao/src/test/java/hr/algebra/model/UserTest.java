/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bruno
 */
public class UserTest {

    @Test
    public void alertMethod_PrintIfUserIsAdmin() {

        User admin = new User();
        admin.setUserName("Adminko");
        admin.setIsAdmin(true);
        String message = "New notification";
        String result = admin.alert(message);
        String expectedOutput = "Hej " + admin.getUserName() + "! " + message;
        assertEquals(expectedOutput, result);
    }

    @Test
    public void alertMethod_PrintIfUserIsNotAdmin() {
      
        User admin = new User();
        admin.setUserName("Adminko");
        admin.setIsAdmin(false);
        String message = "New notification";
        Exception e = assertThrows(
                IllegalStateException.class, 
                () -> admin.alert(message));
        assertEquals("Non-admin users cannot receive alerts.",
                e.getMessage());
    }
}
