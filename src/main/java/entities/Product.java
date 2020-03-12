package entities;

public class Product {

    private int id;
    private String title;
    private double price;
    private String description;
    private int stock;
    public static final int MAX_QUANTITY=30;

    public Product(int id, String title, double price, String description, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
