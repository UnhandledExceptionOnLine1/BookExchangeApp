package hr.algebra.model;

public final class AdDetails {

    private final int id;
    private final String name;
    private final String categoryName; // Naziv kategorije
    private final String paymentTypeName; // Naziv vrste naplate
    private final String imagePath;
    private final String description;
    private final double price;
    private final String userName; // Ime korisnika

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