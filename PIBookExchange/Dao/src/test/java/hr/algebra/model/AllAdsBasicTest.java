/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tin
 */
public class AllAdsBasicTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String name = "Test Ad";
        double price = 99.99;
        LocalDateTime publishTime = LocalDateTime.of(2025, 1, 24, 10, 30);

        // Act
        AllAdsBasic ad = new AllAdsBasic(id, name, price, publishTime);

        // Assert
        assertEquals(id, ad.getId());
        assertEquals(name, ad.getName());
        assertEquals(price, ad.getPrice(), 0.01);
        assertEquals(publishTime, ad.getPublishTime());
    }

    @Test
    void testSetters() {
        // Arrange
        AllAdsBasic ad = new AllAdsBasic(0, "", 0.0, null);

        // Act
        ad.setId(2);
        ad.setName("Updated Ad");
        ad.setPrice(49.99);
        ad.setPublishTime(LocalDateTime.of(2025, 1, 25, 15, 45));

        // Assert
        assertEquals(2, ad.getId());
        assertEquals("Updated Ad", ad.getName());
        assertEquals(49.99, ad.getPrice(), 0.01);
        assertEquals(LocalDateTime.of(2025, 1, 25, 15, 45), ad.getPublishTime());
    }

    @Test
    void testToString() {
        // Arrange
        String name = "Test Ad";
        double price = 19.99;
        LocalDateTime publishTime = LocalDateTime.of(2025, 1, 24, 12, 0);

        AllAdsBasic ad = new AllAdsBasic(1, name, price, publishTime);

        // Act
        String result = ad.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains(name));
        assertTrue(result.contains(String.format("%.2f", price)));
        assertTrue(result.contains(publishTime.toString()));
    }

    @Test
    void testToStringWithNullPublishTime() {
        // Arrange
        String name = "Test Ad";
        double price = 19.99;
        AllAdsBasic ad = new AllAdsBasic(1, name, price, null);

        // Act
        String result = ad.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains(name));
        assertTrue(result.contains(String.format("%.2f", price)));
        assertTrue(result.contains("N/A"));
    }

}
