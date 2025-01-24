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
public class AdStringifyTest {

    @Test
    void testGetString() {
        // Arrange
        Ad ad = new Ad(1, "Test Ad", 2, 3, "path/to/image.jpg", "This is a test description", 100.5, 10);

        // Act
        String adString = AdStringify.getString(ad);

        // Assert
        assertNotNull(adString);
        assertTrue(adString.contains("id=1"));
        assertTrue(adString.contains("name='Test Ad'"));
        assertTrue(adString.contains("categoryId=2"));
        assertTrue(adString.contains("paymentTypeId=3"));
        assertTrue(adString.contains("imagePath='path/to/image.jpg'"));
        assertTrue(adString.contains("description='This is a test description'"));
        assertTrue(adString.contains("price=100.5"));
        assertTrue(adString.contains("userId=10"));
    }

    @Test
    void testGetStringWithEmptyFields() {
        // Arrange
        Ad ad = new Ad();
        ad.setId(0);
        ad.setName("");
        ad.setCategoryId(0);
        ad.setPaymentTypeId(0);
        ad.setImagePath("");
        ad.setDescription("");
        ad.setPrice(0.0);
        ad.setUserId(0);

        // Act
        String adString = AdStringify.getString(ad);

        // Assert
        assertNotNull(adString);
        assertTrue(adString.contains("id=0"));
        assertTrue(adString.contains("name=''"));
        assertTrue(adString.contains("categoryId=0"));
        assertTrue(adString.contains("paymentTypeId=0"));
        assertTrue(adString.contains("imagePath=''"));
        assertTrue(adString.contains("description=''"));
        assertTrue(adString.contains("price=0.0"));
        assertTrue(adString.contains("userId=0"));
    }

}
