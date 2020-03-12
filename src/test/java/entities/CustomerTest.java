package entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CustomerTest {
    private ArrayList<Product> arrayListProduct;
    private Customer customer;

    public CustomerTest(){
        this.arrayListProduct=new ArrayList<Product>() {{
            add(new Product(1, "Aspirateur", 400.50, "Un aspirateur professionnel", 3));
            add(new Product(2, "Balai", 15.0, "Un balai espagnol", 16));
            add(new Product(3, "PQ", 1.49, "Attention à la pénurie", 2));
        }};

        this.customer=new Customer("Lacheteur", "Bob", "bob.lacheteur@email.com", "bob", "bobby");
    }
    @Test
    public void isCartSumShouldBeEqualToPriceSumOfProductsWhenProductAddedToCart() {
        double totalPrice=arrayListProduct.stream().map(Product::getPrice).reduce(0.0, Double::sum);
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
