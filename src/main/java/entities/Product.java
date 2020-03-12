package entities;

public class Product {

    private static int productNumber = 0;

    private int id;
    private String title;
    private double price;
    private String description;
    private int stock;
    public static final int MAX_QUANTITY=30;

    public Product(String title, double price, String description, int stock) {
        productNumber++;
        this.id = productNumber;
        this.title = title;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getId() { return id; }
}
