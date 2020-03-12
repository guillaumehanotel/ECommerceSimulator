package entities;

import java.util.List;

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

    public void addToCart(int quantity, Product product) {
        if(quantity>0) {
            cart.put(product, quantity);
        }
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
