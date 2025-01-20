/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.strategy_pattern;

import hr.algebra.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bruno
 */
public class ContactServiceTest {

    @Test
    public void contactingUserWithValidStrategy() throws Exception {
        ContactUserStrategy dummy = new DummyStrategy();
        ContactService cs = new ContactService(dummy);
        User user = new User("Pero992", "mojaLozinka", "Milenko", "Grbic", "Grbina 29", "grba@gmail.com", " ", false);
        String message = "Welcome" + user.getFirstName();

        cs.contactUser(message, user);
    }

    @Test
    public void contactingUserWithoutValidStrategy() throws Exception {

        ContactService cs = new ContactService(null);
        User user = new User("Pero992", "mojaLozinka", "Milenko", "Grbic", "Grbina 29", "grba@gmail.com", " ", false);
        String message = "Welcome" + user.getFirstName();

        Exception e = assertThrows(Exception.class, () -> {
            cs.contactUser(message, user);
        });
        assertEquals("Strategy not set", e.getMessage());
    }

    private static class DummyStrategy implements ContactUserStrategy {

        @Override
        public void contactUser(User user, String message) {
            System.out.println("Contacting user: " + user.getUserName() + " with message: " + message);
        }
    }

}
