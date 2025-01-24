/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hr.algebra.sorting.strategy;

import hr.algebra.model.AllAdsBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Tin
 */
public class AdSorterTest {

    @Mock
    private SortingStrategy mockStrategy;
    private AdSorter adSorter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adSorter = new AdSorter();
    }

    @Test
    void shouldSortAdsUsingProvidedStrategy() { //stvara sorted listu i unsorted
        List<AllAdsBasic> unsortedAds = Arrays.asList(new AllAdsBasic(2, "Ad B", 100, null), new AllAdsBasic(1, "Ad A", 200, null));
        List<AllAdsBasic> sortedAds = Arrays.asList(new AllAdsBasic(1, "Ad A", 200, null), new AllAdsBasic(2, "Ad B", 100, null));

        when(mockStrategy.sort(unsortedAds)).thenReturn(sortedAds);

        adSorter.setSortingStrategy(mockStrategy);
        List<AllAdsBasic> result = adSorter.sortAds(unsortedAds);

        verify(mockStrategy).sort(unsortedAds);
        assertEquals(sortedAds, result);
    }

    @Test
    void shouldThrowExceptionWhenNoStrategyIsSet() {
        List<AllAdsBasic> ads = Arrays.asList(new AllAdsBasic(1, "Ad", 100, null));

        assertThrows(IllegalStateException.class, () -> adSorter.sortAds(ads));
    }

    @Test
    void shouldHandleMultipleStrategies() {
        SortingStrategy anotherStrategy = mock(SortingStrategy.class);
        List<AllAdsBasic> ads = Arrays.asList(new AllAdsBasic(1, "Ad", 100, null));
        List<AllAdsBasic> sortedAds = Arrays.asList(new AllAdsBasic(1, "Ad", 100, null));

        when(anotherStrategy.sort(ads)).thenReturn(sortedAds);

        adSorter.setSortingStrategy(anotherStrategy);
        List<AllAdsBasic> result = adSorter.sortAds(ads);

        verify(anotherStrategy).sort(ads);
        assertNotSame(mockStrategy, anotherStrategy);
        assertEquals(sortedAds, result);
    }

}
