package hr.algebra.model;

import java.time.LocalDateTime;


public class AllAdsBasic {
    private int id;
    private String name;
    private double price;
    private LocalDateTime publishTime;

    // Konstruktor
    public AllAdsBasic(int id, String name, double price, LocalDateTime publishTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.publishTime = publishTime;
    }

    // Getteri i Setteri
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f Eur - Objavljeno: %s", 
                name, 
                price, 
                publishTime != null ? publishTime.toString() : "N/A");
    }
}
