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
public class AdDetailsTest {
    
    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String name = "Test Ad";
        String categoryName = "Electronics";
        String paymentTypeName = "Credit Card";
        String imagePath = "path/to/image.jpg";
        String description = "This is a test description.";
        double price = 99.99;
        String userName = "John Doe";

        // Act
        AdDetails adDetails = new AdDetails(id, name, categoryName, paymentTypeName, imagePath, description, price, userName);

        // Assert
        assertEquals(id, adDetails.getId());
        assertEquals(name, adDetails.getName());
        assertEquals(categoryName, adDetails.getCategoryName());
        assertEquals(paymentTypeName, adDetails.getPaymentTypeName());
        assertEquals(imagePath, adDetails.getImagePath());
        assertEquals(description, adDetails.getDescription());
        assertEquals(price, adDetails.getPrice());
        assertEquals(userName, adDetails.getUserName());
    }

    @Test
    void testToString() {
        // Arrange
        int id = 1;
        String name = "Test Ad";
        String categoryName = "Electronics";
        String paymentTypeName = "Credit Card";
        String imagePath = "path/to/image.jpg";
        String description = "This is a test description.";
        double price = 99.99;
        String userName = "John Doe";

        AdDetails adDetails = new AdDetails(id, name, categoryName, paymentTypeName, imagePath, description, price, userName);

        // Act
        String adDetailsString = adDetails.toString();

        // Assert
        assertNotNull(adDetailsString);
        assertTrue(adDetailsString.contains("id=1"));
        assertTrue(adDetailsString.contains("name='Test Ad'"));
        assertTrue(adDetailsString.contains("categoryName='Electronics'"));
        assertTrue(adDetailsString.contains("paymentTypeName='Credit Card'"));
        assertTrue(adDetailsString.contains("imagePath='path/to/image.jpg'"));
        assertTrue(adDetailsString.contains("description='This is a test description.'"));
        assertTrue(adDetailsString.contains("price=99.99"));
        assertTrue(adDetailsString.contains("userName='John Doe'"));
    }
    
}
