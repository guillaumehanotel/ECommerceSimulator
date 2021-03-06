package entities;

import exceptions.EmptyAddressesException;
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
    private Customer customer2;

    public CustomerTest() {
        this.products = new ArrayList<Product>() {{
            add(new Product("Aspirateur", 400.50, "Un aspirateur professionnel", 15));
            add(new Product("Balai", 15.0, "Un balai espagnol", 16));
            add(new Product("PQ", 1.49, "Attention à la pénurie", 2));
        }};

        // Client sans adresses
        this.customer1 = new Customer("Doe", "John", "johndoe@gmail.com", "johnny");

        // Client avec adresses
        this.customer2 = new Customer("Dupont", "Pierre", "pierredupont@gmail.com", "pirdu");
        this.customer2.setBillingAddress("2 plaine du caillou, Fumel");
        this.customer2.setShippingAddress("2 plaine du caillou, Fumel");
    }

    @Test
    public void cartShoppingSum_ShouldBeEqualTo_SumOfProductsPrice_WhenProductAddedToCart() throws InsufficientStockException {
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
    public void productQuantity_ShouldBeHigherThan_Zero_WhenProductAddedToCart() throws InsufficientStockException {
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
    public void productQuantity_ShouldBe_Incremented_WhenProductAddedMultipleTimes() throws InsufficientStockException {
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
    public void productQuantity_ShouldNotBeHigherThan_MaxQuantity_WhenProductAddedToCart() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 50;
        this.customer1.addToCart(product1Quantity, product1);
        int expectedCartSize = 0;

        assertEquals(expectedCartSize, customer1.getShoppingCart().size());
    }

    @Test
    public void exception_ShouldBeRaised_WhenQuantityIsHigherThanStockQuantity_WhenProductAddedToCart() {
        Product product1 = products.get(0);
        int product1Quantity = 16;

        assertThrows(InsufficientStockException.class, () -> this.customer1.addToCart(product1Quantity, product1));
    }

    @Test
    public void exception_ShouldBeRaised_WhenTotalQuantityIsHigherThanStockQuantity_WhenProductAddedToCartMultipleTimes() throws InsufficientStockException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        int product1BisQuantity = 10;

        this.customer1.addToCart(product1Quantity, product1);

        assertThrows(InsufficientStockException.class, () -> this.customer1.addToCart(product1BisQuantity, product1));
    }

    @Test
    public void product_ShouldBe_Present_BeforeRemovedFromCart() {
        Product product1 = products.get(0);
        int product1Quantity = 10;

        assertThrows(ProductNotFoundInCartException.class, () -> this.customer1.removeFromCart(product1Quantity, product1));
    }

    @Test
    public void product_ShouldBe_Absent_AfterRemovedFromCart() throws InsufficientStockException, ProductNotFoundInCartException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        this.customer1.addToCart(product1Quantity, product1);
        this.customer1.removeFromCart(product1Quantity, product1);

        assertNull(customer1.getShoppingCart().get(product1.getId()));
    }

    @Test
    public void productQuantity_ShouldBe_Decremented_WhenPartiallyRemovedFromCart() throws InsufficientStockException, ProductNotFoundInCartException {
        Product product1 = products.get(0);
        int product1Quantity = 10;
        this.customer1.addToCart(product1Quantity, product1);

        int removedQuantity = 6;
        this.customer1.removeFromCart(removedQuantity, product1);

        int expectedLeftQuantity = product1Quantity - removedQuantity;

        assertEquals(expectedLeftQuantity, customer1.getShoppingCart().get(product1.getId()).getQuantity());
    }

    @Test
    public void exception_ShouldBeRaised_WhenCartIsEmpty_WhenMakeOrder() {
        assertThrows(EmptyCartException.class, () -> customer1.makeOrder());
    }

    @Test
    public void exception_ShouldBeRaised_WhenCustomerAddressesAreEmpty_WhenMakeOrder() throws InsufficientStockException {
        Product product1 = products.get(0);
        this.customer1.addToCart(10, product1);
        assertThrows(EmptyAddressesException.class, () -> customer1.makeOrder());
    }

    @Test
    public void newOrder_ShouldBe_Created_AfterMakeOrder() throws InsufficientStockException, EmptyCartException, EmptyAddressesException {
        Product product1 = products.get(0);
        this.customer2.addToCart(10, product1);
        this.customer2.makeOrder();
        int expectedOrderNumber = 1;
        assertEquals(expectedOrderNumber, customer2.getCurrentOrders().size());
    }

    @Test
    public void cart_ShouldBe_Empty_AfterMakeOrder() throws InsufficientStockException, EmptyCartException, EmptyAddressesException {
        Product product1 = products.get(0);
        this.customer2.addToCart(10, product1);
        this.customer2.makeOrder();

        int expectedProductQuantityInCart = 0;
        assertEquals(expectedProductQuantityInCart, this.customer2.getShoppingCart().size());
    }

}
