package entities;

import exceptions.EmptyAddressesException;
import exceptions.EmptyCartException;
import exceptions.InsufficientStockException;
import exceptions.ProductNotFoundInCartException;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un compte enregistré auprès du site marchand
 */
public class Customer extends Person {

    private String email;
    private String pseudo;
    private String password;
    private String shippingAddress;
    private String billingAddress;

    private ShoppingCart shoppingCart;
    private List<Order> currentOrders;
    private List<Order> ordersHistory;

    public Customer(String lastname, String firstname, String email, String pseudo) {
        super(lastname, firstname);
        this.email = email;
        this.pseudo = pseudo;
        this.shoppingCart = new ShoppingCart();
        this.currentOrders = new ArrayList<>();
        this.ordersHistory = new ArrayList<>();
    }

    public void addToCart(int quantity, Product product) throws InsufficientStockException {
        int updatedQuantity = (shoppingCart.containsKey(product.getId()) ? shoppingCart.get(product.getId()).getQuantity() : 0) + quantity;
        if (updatedQuantity > product.getStock()) {
            throw new InsufficientStockException();
        }
        if (updatedQuantity > 0 && quantity <= Product.MAX_QUANTITY) {
            shoppingCart.put(product.getId(), new LineItem(updatedQuantity, product));
        }
    }

    public void removeFromCart(int quantity, Product product) throws ProductNotFoundInCartException {
        if (!shoppingCart.containsKey(product.getId())) {
            throw new ProductNotFoundInCartException();
        }
        int leftQuantity = shoppingCart.get(product.getId()).getQuantity() - quantity;
        if (leftQuantity <= 0) {
            shoppingCart.remove(product.getId());
        } else {
            shoppingCart.put(product.getId(), new LineItem(leftQuantity, product));
        }
    }

    public void makeOrder() throws EmptyCartException, EmptyAddressesException {
        if (shoppingCart.isEmpty())
            throw new EmptyCartException();
        if (shippingAddress == null && billingAddress == null)
            throw new EmptyAddressesException();

        this.currentOrders.add(new Order(
                this.shippingAddress, this.getShoppingCart()
        ));
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<Order> getCurrentOrders() {
        return currentOrders;
    }

    public List<Order> getOrdersHistory() {
        return ordersHistory;
    }
}
