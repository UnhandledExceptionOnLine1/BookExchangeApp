/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.sorting.strategy;

import hr.algebra.model.AllAdsBasic;
import java.util.List;

/**
 *
 * @author Tin
 */
public class AdSorter {

    private SortingStrategy sortingStrategy;

    // Postavljanje strategije
    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    // Sortiranje oglasa
    public List<AllAdsBasic> sortAds(List<AllAdsBasic> ads) {
        if (sortingStrategy == null) {
            throw new IllegalStateException("Sorting strategy not set");
        }
        return sortingStrategy.sort(ads);
    }
}
