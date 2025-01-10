package hr.algebra.model;

public final class AdDetails {

    private int id;
    private String name;
    private String categoryName; // Naziv kategorije
    private String paymentTypeName; // Naziv vrste naplate
    private String imagePath;
    private String description;
    private double price;
    private String userName; // Ime korisnika

    public AdDetails(int id, String name, String categoryName, String paymentTypeName, String imagePath, String description, double price, String userName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.paymentTypeName = paymentTypeName;
        this.imagePath = imagePath;
        this.description = description;
        this.price = price;
        this.userName = userName;
    }

    // Getteri
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "AdDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", paymentTypeName='" + paymentTypeName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", userName='" + userName + '\'' +
                '}';
    }
}