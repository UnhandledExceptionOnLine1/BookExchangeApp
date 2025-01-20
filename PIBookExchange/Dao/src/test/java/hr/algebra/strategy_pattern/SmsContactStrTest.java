/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.strategy_pattern;

import hr.algebra.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bruno
 */
public class SmsContactStrTest {
    
    @Test
    public void thisShouldContactUserBySMS() {
        MessageContactStr m = new MessageContactStr();
        User user = new User("Pero992", "mojaLozinka", "Milenko", "Grbic", "Grbina 29", "grba@gmail.com", "09123456789", false);
        String message = "Sending SMS to " + user.getTelephone();
        m.contactUser(user, message);
    }
}
