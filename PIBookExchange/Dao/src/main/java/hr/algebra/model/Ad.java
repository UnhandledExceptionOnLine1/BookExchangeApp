package hr.algebra.model;

/**
 * Represents an Advertisement (Ad) in the system.
 */
public final class Ad {

    private int id;
    private String name;
    private int categoryId;
    private int paymentTypeId;
    private String imagePath;
    private String description;
    private double price;
    private int userId;

    // Default constructor
    public Ad() {
    }

    // Constructor without ID (for new ads before saving to DB)
    public Ad(String name, int categoryId, int paymentTypeId, String imagePath, String description, double price, int userId) {
        setName(name);
        setCategoryId(categoryId);
        setPaymentTypeId(paymentTypeId);
        setImagePath(imagePath);
        setDescription(description);
        setPrice(price);
        setUserId(userId);
    }

    // Constructor with ID (for existing ads from DB)
    public Ad(int id, String name, int categoryId, int paymentTypeId, String imagePath, String description, double price, int userId) {
        this(name, categoryId, paymentTypeId, imagePath, description, price, userId);
        setId(id);
    }

    // Getters and setters
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", paymentTypeId=" + paymentTypeId +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}