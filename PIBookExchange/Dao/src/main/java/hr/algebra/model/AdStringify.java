/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;


public class AdStringify {
    
    public static String getString(Ad ad){
                return "Ad{" +
                "id=" + ad.getId() +
                ", name='" + ad.getName() + '\'' +
                ", categoryId=" + ad.getCategoryId() +
                ", paymentTypeId=" + ad.getPaymentTypeId() +
                ", imagePath='" + ad.getImagePath() + '\'' +
                ", description='" + ad.getDescription() + '\'' +
                ", price=" + ad.getPrice() +
                ", userId=" + ad.getUserId() +
                '}';
    }
}
