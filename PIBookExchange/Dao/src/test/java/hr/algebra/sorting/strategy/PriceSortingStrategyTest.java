/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.sorting.strategy;

import hr.algebra.model.AllAdsBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author Tin
 */
public class PriceSortingStrategyTest {
     private PriceSortingStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new PriceSortingStrategy();
    }

    @Test
    void adsShouldBeSortedByPriceAscending() {
        List<AllAdsBasic> ads = Arrays.asList(
            new AllAdsBasic(1, "Ad1", 300.00, LocalDateTime.now()),
            new AllAdsBasic(2, "Ad2", 150.00, LocalDateTime.now()),
            new AllAdsBasic(3, "Ad3", 200.00, LocalDateTime.now())
        );

        // Apply the sorting strategy
        List<AllAdsBasic> sortedAds = strategy.sort(ads);

        // Assert the ads are sorted by price in ascending order
        assertEquals(150.00, sortedAds.get(0).getPrice(), "First should be the cheapest");
        assertEquals(200.00, sortedAds.get(1).getPrice(), "Second should be mid-priced");
        assertEquals(300.00, sortedAds.get(2).getPrice(), "Third should be the most expensive");
    }
 
    
}
