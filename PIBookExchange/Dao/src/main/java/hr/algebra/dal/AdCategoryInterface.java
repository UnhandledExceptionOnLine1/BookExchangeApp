/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;


public interface AdCategoryInterface extends Repository {

    int getCategoryIdByName(String categoryName) throws Exception;

}
