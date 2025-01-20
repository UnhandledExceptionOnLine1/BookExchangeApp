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
public class UserStringifyTest {

    @Test
    public void thisShouldReturnFirstAndLastName() {
        User user = new User();
        user.setFirstName("Milenko");
        user.setLastName("Grbic");
        String result = UserStringify.getString(user);
        assertEquals("Milenko Grbic", result);
    }

    @Test
    public void thisShouldReturnEmptyString() {
        User user = new User();
        user.setFirstName("");
        user.setLastName("");
        String result = UserStringify.getString(user);
        assertEquals(" ", result);
    }

    @Test
    public void thisShouldReturnNullNameParameters() {
        User user = new User();
        user.setFirstName(null);
        user.setLastName(null);
        String result = UserStringify.getString(user);
        assertEquals("null null", result);
    }

}
