package entities;

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
    private ShoppingCart shoppingCart;
    private List<Order> currentOrders;
    private List<Order> ordersHistory;

    public Customer(String lastname, String firstname, String email, String pseudo, String password) {
        super(lastname, firstname);
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.shoppingCart = new ShoppingCart();
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
        if(!shoppingCart.containsKey(product.getId())) {
            throw new ProductNotFoundInCartException();
        }
        int leftQuantity = shoppingCart.get(product.getId()).getQuantity() - quantity;
        if(leftQuantity <= 0) {
            shoppingCart.remove(product.getId());
        } else {
            shoppingCart.put(product.getId(), new LineItem(leftQuantity, product));
        }
    }

    public void makeOrder() throws EmptyCartException {
        if(shoppingCart.isEmpty())
            throw new EmptyCartException();

    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

}
