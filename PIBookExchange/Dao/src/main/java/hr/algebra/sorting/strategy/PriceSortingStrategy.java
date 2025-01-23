/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.sorting.strategy;

import hr.algebra.model.AllAdsBasic;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author Tin
 */
public class PriceSortingStrategy implements SortingStrategy {
    @Override
    public List<AllAdsBasic> sort(List<AllAdsBasic> ads) {
        ads.sort(Comparator.comparingDouble(AllAdsBasic::getPrice)); // Sortiranje uzlazno po cijeni
        return ads;
    }
}
