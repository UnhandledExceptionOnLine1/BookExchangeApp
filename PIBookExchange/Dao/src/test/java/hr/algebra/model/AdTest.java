/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tin
 */
public class AdTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        Ad ad = new Ad();

        // Assert
        assertNotNull(ad);
        assertEquals(0, ad.getId());
        assertNull(ad.getName());
        assertEquals(0, ad.getCategoryId());
        assertEquals(0, ad.getPaymentTypeId());
        assertNull(ad.getImagePath());
        assertNull(ad.getDescription());
        assertEquals(0.0, ad.getPrice());
        assertEquals(0, ad.getUserId());
    }

    @Test
    void testConstructorWithoutId() {
        // Arrange & Act
        Ad ad = new Ad("Test Ad", 1, 2, "path/to/image.jpg", "This is a description", 10.5, 100);

        // Assert
        assertNotNull(ad);
        assertEquals("Test Ad", ad.getName());
        assertEquals(1, ad.getCategoryId());
        assertEquals(2, ad.getPaymentTypeId());
        assertEquals("path/to/image.jpg", ad.getImagePath());
        assertEquals("This is a description", ad.getDescription());
        assertEquals(10.5, ad.getPrice());
        assertEquals(100, ad.getUserId());
    }

    @Test
    void testConstructorWithId() {
        // Arrange & Act
        Ad ad = new Ad(10, "Test Ad", 1, 2, "path/to/image.jpg", "This is a description", 10.5, 100);

        // Assert
        assertNotNull(ad);
        assertEquals(10, ad.getId());
        assertEquals("Test Ad", ad.getName());
        assertEquals(1, ad.getCategoryId());
        assertEquals(2, ad.getPaymentTypeId());
        assertEquals("path/to/image.jpg", ad.getImagePath());
        assertEquals("This is a description", ad.getDescription());
        assertEquals(10.5, ad.getPrice());
        assertEquals(100, ad.getUserId());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Ad ad = new Ad();

        // Act
        ad.setId(10);
        ad.setName("Test Ad");
        ad.setCategoryId(1);
        ad.setPaymentTypeId(2);
        ad.setImagePath("path/to/image.jpg");
        ad.setDescription("This is a description");
        ad.setPrice(10.5);
        ad.setUserId(100);

        // Assert
        assertEquals(10, ad.getId());
        assertEquals("Test Ad", ad.getName());
        assertEquals(1, ad.getCategoryId());
        assertEquals(2, ad.getPaymentTypeId());
        assertEquals("path/to/image.jpg", ad.getImagePath());
        assertEquals("This is a description", ad.getDescription());
        assertEquals(10.5, ad.getPrice());
        assertEquals(100, ad.getUserId());
    }

    @Test
    void testToString() {
        // Arrange
        Ad ad = new Ad(10, "Test Ad", 1, 2, "path/to/image.jpg", "This is a description", 10.5, 100);

        // Act
        String adString = ad.toString();

        // Assert
        assertNotNull(adString);
        assertTrue(adString.contains("Test Ad"));
        assertTrue(adString.contains("path/to/image.jpg"));
        assertTrue(adString.contains("This is a description"));
    }

}
