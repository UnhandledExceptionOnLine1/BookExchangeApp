/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.sorting.strategy;
import hr.algebra.model.AllAdsBasic;
import java.util.List;

/**
 *
 * @author Tin
 */
public interface SortingStrategy {
    List<AllAdsBasic> sort(List<AllAdsBasic> ads);
}