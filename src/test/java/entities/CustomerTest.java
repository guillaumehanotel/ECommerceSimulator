package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;

class CustomerTest {
    private ArrayList<Product> products;
    private Customer customer1;

    public CustomerTest() {
        this.products = new ArrayList<Product>() {{
            add(new Product(1, "Aspirateur", 400.50, "Un aspirateur professionnel", 3));
            add(new Product(2, "Balai", 15.0, "Un balai espagnol", 16));
            add(new Product(3, "PQ", 1.49, "Attention à la pénurie", 2));
        }};

        this.customer1 = new Customer("Doe", "John", "johndoe@gmail.com", "johnny", "toto1234");
    }

    @Test
    public void isCartSumShouldBeEqualToPriceSumOfProductsWhenProductAddedToCart() {
        Product product1 = products.get(0);
        int product1Quantity = 3;
        Product product2 = products.get(1);
        int product2Quantity = 2;
        Product product3 = products.get(2);
        int product3Quantity = 1;
        this.customer1.addToCart(product1Quantity, product1);
        this.customer1.addToCart(product2Quantity, product2);
        this.customer1.addToCart(product3Quantity, product3);

        double totalProductPrice = (product1.getPrice() * product1Quantity) + (product2.getPrice() * product2Quantity) + (product3.getPrice() * product3Quantity);
        double totalCartPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : this.customer1.getCart().entrySet()) {
            totalCartPrice += entry.getKey().getPrice() * entry.getValue();
        }
        assertEquals(totalCartPrice, totalProductPrice);
    }

    @Test
    public void isQuantityHigherThanZeroWhenProductAddedToCart() {

    }

    @Test
    public void isProductQuantityIncrementedWhenProductAddMultipleTimes() {

    }

    @Test
    public void isQuantityNotHigherThanMaxQuantityWhenProductAddedToCart() {

    }

    @Test
    public void isExceptionRaisedWhenQuantityIsHigherThanStockQuantityWhenProductAddedToCart() {

    }

    @Test
    public void isProductAbsentWhenRemovedFromCart() {

    }

    @Test
    public void isCartNotEmptyWhenStartValidateCart() {

    }

    @Test
    public void isAddressFilledWhenValidateCart() {

    }

    @Test
    public void isCartEmptyAfterValidateCart() {

    }

    @Test
    public void isCommandCorrectWhenValidateCart() {

    }

}
