package entities;

import exceptions.InsufficientStockException;

/**
 * Classe représentant un compte enregistré auprès du site marchand
 */
public class Customer extends Person {

    private String email;
    private String pseudo;
    private String password;
    private Cart cart;

    public Customer(String lastname, String firstname, String email, String pseudo, String password) {
        super(lastname, firstname);
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.cart = new Cart();
    }

    public void addToCart(int quantity, Product product) throws InsufficientStockException {
        int updatedQuantity = cart.getOrDefault(product, 0) + quantity;
        if (updatedQuantity > product.getStock()) {
            throw new InsufficientStockException();
        }
        if (updatedQuantity > 0 && quantity <= Product.MAX_QUANTITY) {
            cart.put(product, updatedQuantity);
        }
    }

    public void removeFromCart(int quantity, Product product) {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

}
