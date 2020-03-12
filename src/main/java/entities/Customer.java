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

    public boolean addToCart(Product product) {
        return false;
    }
}
