package entities;

import exceptions.InsufficientStockException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {
    private ArrayList<Product> products;
    private Customer customer1;

    public CustomerTest() {
        this.products = new ArrayList<Product>() {{
            add(new Product(1, "Aspirateur", 400.50, "Un aspirateur professionnel", 15));
            add(new Product(2, "Balai", 15.0, "Un balai espagnol", 16));
            add(new Product(3, "PQ", 1.49, "Attention à la pénurie", 2));
        }};

        this.customer1 = new Customer("Doe", "John", "johndoe@gmail.com", "johnny", "toto1234");
    }

    @Test
    public void isCartSumShouldBeEqualToPriceSumOfProductsWhenProductAddedToCart() throws InsufficientStockException {
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
        assertEquals(totalProductPrice, totalCartPrice);
    }

    @Test
    public void isQuantityHigherThanZeroWhenProductAddedToCart() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 3;
        Product product2 = products.get(1);
        int product2Quantity = 2;
        Product product3 = products.get(2);
        int product3Quantity = 0;
        this.customer1.addToCart(product1Quantity, product1);
        this.customer1.addToCart(product2Quantity, product2);
        this.customer1.addToCart(product3Quantity, product3);
        int numberOfProducts = 2;

        assertEquals(numberOfProducts, customer1.getCart().size());


    }

    @Test
    public void isProductQuantityIncrementedWhenProductAddMultipleTimes() throws InsufficientStockException {
        Product product2 = products.get(1);
        int product2Quantity = 2;
        int product2bisQuantity = 1;
        this.customer1.addToCart(product2Quantity, product2);
        this.customer1.addToCart(product2bisQuantity, product2);
        int totalQuantity = product2Quantity + product2bisQuantity;

        int product2CartQuantity = customer1.getCart().get(product2);

        assertEquals(totalQuantity, product2CartQuantity);

    }

    @Test
    public void isQuantityNotHigherThanMaxQuantityWhenProductAddedToCart() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 50;
        this.customer1.addToCart(product1Quantity, product1);
        int expectedCartSize = 0;

        assertEquals(expectedCartSize, customer1.getCart().size());
    }

    @Test
    public void isExceptionRaisedWhenQuantityIsHigherThanStockQuantityWhenProductAddedToCart() {
        Product product1 = products.get(0);
        int product1Quantity = 16;

        assertThrows(InsufficientStockException.class, () -> {
            this.customer1.addToCart(product1Quantity, product1);
        });
    }

    @Test
    public void isExceptionRaisedWhenTotalQuantityIsHigherThanStockQuantityWhenProductAddedToCartMultipleTimes() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        int product1BisQuantity = 10;

        this.customer1.addToCart(product1Quantity, product1);

        assertThrows(InsufficientStockException.class, () -> {
            this.customer1.addToCart(product1BisQuantity, product1);
        });
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
