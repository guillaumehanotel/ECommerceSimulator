package entities;

import exceptions.EmptyCartException;
import exceptions.InsufficientStockException;
import exceptions.ProductNotFoundInCartException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private List<Product> products;
    private Customer customer1;

    public CustomerTest() {
        this.products = new ArrayList<Product>() {{
            add(new Product("Aspirateur", 400.50, "Un aspirateur professionnel", 15));
            add(new Product("Balai", 15.0, "Un balai espagnol", 16));
            add(new Product("PQ", 1.49, "Attention à la pénurie", 2));
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

        double expectedTotalProductPrice = (product1.getPrice() * product1Quantity) + (product2.getPrice() * product2Quantity) + (product3.getPrice() * product3Quantity);
        double totalCartPrice = 0.0;

        for (Map.Entry<Integer, LineItem> entry : this.customer1.getShoppingCart().entrySet()) {
            LineItem lineItem = entry.getValue();
            totalCartPrice += lineItem.getProduct().getPrice() * lineItem.getQuantity();
        }
        assertEquals(expectedTotalProductPrice, totalCartPrice);
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

        int expectedNumberOfProducts = 2;

        assertEquals(expectedNumberOfProducts, customer1.getShoppingCart().size());
    }

    @Test
    public void isProductQuantityIncrementedWhenProductAddMultipleTimes() throws InsufficientStockException {
        Product product2 = products.get(1);
        int product2Quantity = 2;
        int product2bisQuantity = 1;
        this.customer1.addToCart(product2Quantity, product2);
        this.customer1.addToCart(product2bisQuantity, product2);

        int expectedTotalQuantity = product2Quantity + product2bisQuantity;
        int product2CartQuantity = customer1.getShoppingCart().get(product2.getId()).getQuantity();

        assertEquals(expectedTotalQuantity, product2CartQuantity);
    }

    @Test
    public void isQuantityNotHigherThanMaxQuantityWhenProductAddedToCart() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 50;
        this.customer1.addToCart(product1Quantity, product1);
        int expectedCartSize = 0;

        assertEquals(expectedCartSize, customer1.getShoppingCart().size());
    }

    @Test
    public void isExceptionRaisedWhenQuantityIsHigherThanStockQuantityWhenProductAddedToCart() {
        Product product1 = products.get(0);
        int product1Quantity = 16;

        assertThrows(InsufficientStockException.class, () -> this.customer1.addToCart(product1Quantity, product1));
    }

    @Test
    public void isExceptionRaisedWhenTotalQuantityIsHigherThanStockQuantityWhenProductAddedToCartMultipleTimes() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        int product1BisQuantity = 10;

        this.customer1.addToCart(product1Quantity, product1);

        assertThrows(InsufficientStockException.class, () -> this.customer1.addToCart(product1BisQuantity, product1));
    }

    @Test
    public void isProductPresentBeforeRemovedFromCart() {
        Product product1 = products.get(0);
        int product1Quantity = 10;

        assertThrows(ProductNotFoundInCartException.class, () -> this.customer1.removeFromCart(product1Quantity, product1));
    }

    @Test
    public void isProductAbsentAfterRemovedFromCart() throws InsufficientStockException, ProductNotFoundInCartException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        this.customer1.addToCart(product1Quantity, product1);
        this.customer1.removeFromCart(product1Quantity, product1);

        assertNull(customer1.getShoppingCart().get(product1.getId()));
    }

    @Test
    public void isProductQuantityDecrementedWhenPartiallyRemovedFromCart() throws InsufficientStockException, ProductNotFoundInCartException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        this.customer1.addToCart(product1Quantity, product1);

        int removedQuantity = 6;
        this.customer1.removeFromCart(removedQuantity, product1);

        int expectedLeftQuantity = product1Quantity - removedQuantity;

        assertEquals(expectedLeftQuantity, customer1.getShoppingCart().get(product1.getId()).getQuantity());
    }

//    @Test
//    public void isCartNotEmptyWhenStartValidateCart() {
//        assertThrows(EmptyCartException.class, () -> customer1.makeOrder(null, null));
//    }
//
//    @Test
//    public void isAddressFilledWhenValidateCart() {
//
//    }
//
//    @Test
//    public void isCartEmptyAfterValidateCart() {
//
//    }
//
//    @Test
//    public void isCommandCorrectWhenValidateCart() {
//
//    }

}
