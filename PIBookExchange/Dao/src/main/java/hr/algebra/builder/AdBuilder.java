/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.builder;
import hr.algebra.model.Ad;
/**
 *
 * @author Tin
 */
public class AdBuilder {
    private int id;
    private String name;
    private int categoryId;
    private int paymentTypeId;
    private String imagePath;
    private String description;
    private double price;
    private int userId;

    public AdBuilder(String name, int userId) { // Obavezni parametri
        this.name = name;
        this.userId = userId;
    }

    public AdBuilder id(int id) {
        this.id = id;
        return this;
    }

    public AdBuilder categoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public AdBuilder paymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
        return this;
    }

    public AdBuilder imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public AdBuilder description(String description) {
        this.description = description;
        return this;
    }

    public AdBuilder price(double price) {
        this.price = price;
        return this;
    }

    public Ad build() {
        Ad ad = new Ad();
        ad.setId(this.id);
        ad.setName(this.name);
        ad.setCategoryId(this.categoryId);
        ad.setPaymentTypeId(this.paymentTypeId);
        ad.setImagePath(this.imagePath);
        ad.setDescription(this.description);
        ad.setPrice(this.price);
        ad.setUserId(this.userId);
        return ad;
    }
}