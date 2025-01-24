/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.builder;

import hr.algebra.model.Ad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tin
 */
public class AdBuilderTest {

    @Test
    public void testBuild() {
        // Kreiranje AdBuilder instance s obaveznim parametrima
        AdBuilder builder = new AdBuilder("Prodaja auta", 1);

        // Postavljanje opcionalnih parametara
        builder.id(100)
                .categoryId(2)
                .paymentTypeId(3)
                .imagePath("path/to/image.jpg")
                .description("Prodajem auto u odličnom stanju")
                .price(5000.00);

        // Stvaranje Ad objekta koristeći build metodu
        Ad ad = builder.build();

        // Provjera da li su vrijednosti ispravno postavljene
        assertEquals(100, ad.getId());
        assertEquals("Prodaja auta", ad.getName());
        assertEquals(2, ad.getCategoryId());
        assertEquals(3, ad.getPaymentTypeId());
        assertEquals("path/to/image.jpg", ad.getImagePath());
        assertEquals("Prodajem auto u odličnom stanju", ad.getDescription());
        assertEquals(5000.00, ad.getPrice(), 0.001);
        assertEquals(1, ad.getUserId());
    }

}
