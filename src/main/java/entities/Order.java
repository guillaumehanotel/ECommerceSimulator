package entities;

import enums.OrderStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private static int orderNumber = 0;

    private int number;
    private Date orderedDate;
    private double total;
    private OrderStatus status;
    private String shippedAddress;
    private HashMap<Integer, LineItem> products;

    public Order(String shippedAddress, ShoppingCart shoppingCart) {
        orderNumber++;
        this.number = orderNumber;
        this.orderedDate = new Date();
        this.status = OrderStatus.NEW;
        this.shippedAddress = shippedAddress;
        this.products = shoppingCart;
        this.total = getTotal();
    }

    private double getTotal() {
        double totalCartPrice = 0.0;
        for (Map.Entry<Integer, LineItem> entry : this.products.entrySet()) {
            LineItem lineItem = entry.getValue();
            totalCartPrice += lineItem.getProduct().getPrice() * lineItem.getQuantity();
        }
        return  totalCartPrice;
    }
}
