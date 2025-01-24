/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.sorting.strategy;

import hr.algebra.sorting.strategy.DateSortingStrategy;
import hr.algebra.model.AllAdsBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Tin
 */
public class DateSortingStrategyTest {

    private DateSortingStrategy strategy;
    private List<AllAdsBasic> ads;

    @BeforeEach
    void setUp() {
        strategy = new DateSortingStrategy();
        LocalDateTime now = LocalDateTime.now();
        ads = new ArrayList<>(Arrays.asList(
                new AllAdsBasic(1, "Ad1", 100, now.minusDays(1)),
                new AllAdsBasic(2, "Ad2", 150, now.minusDays(3)),
                new AllAdsBasic(3, "Ad3", 120, now.minusDays(2))
        ));
    }

    @Test
    void adsShouldBeSortedByDateDescending() {
        // Pretpostavljam da su oglasi dodani u listu
        List<AllAdsBasic> ads = Arrays.asList(
                new AllAdsBasic(1, "Ad3", 100, LocalDateTime.parse("2025-01-22T18:01:37.221471500")),
                new AllAdsBasic(2, "Ad2", 150, LocalDateTime.parse("2025-01-21T18:01:37.221471500")),
                new AllAdsBasic(3, "Ad1", 200, LocalDateTime.parse("2025-01-23T18:01:37.221471500"))
        );

        // Očekivani poredak je da najnoviji oglas (Ad1) bude prvi
        List<AllAdsBasic> sortedAds = strategy.sort(ads);

        assertEquals("Ad1", sortedAds.get(0).getName(), "First should be Ad1 due to the most recent date");
        assertEquals("Ad3", sortedAds.get(1).getName(), "Second should be Ad3");
        assertEquals("Ad2", sortedAds.get(2).getName(), "Third should be Ad2");
    }
}
