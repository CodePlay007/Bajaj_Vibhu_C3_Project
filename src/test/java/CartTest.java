import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart cart;
    Restaurant restaurant;

//Refactoring code //
    private void getRestaurantWithMenu() {
        restaurant = new Restaurant("The Liquor Hub", "Delhi", LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));
        restaurant.addToMenu("JW Gold Label", 8000);
        restaurant.addToMenu("Jack Daniels Bourbon Whisky", 450);
        restaurant.addToMenu("Grey Goose Vodka STRAWBERRY & LEMONGRASS", 7500);
        restaurant.addToMenu("Teremana Tequila BLANCO", 7500);
        restaurant.addToMenu("Teremana Tequila REPOSAD", 7500);
        restaurant.addToMenu("Beefeater London Dry Gin 750ml", 3000);
    }


// TEST CASES //
    @Test
    public void check_if_after_adding_item_to_cart_its_total_cart_value_returns_correct_amount(){

        getRestaurantWithMenu();
        cart = new Cart();
        cart.addItemToCart(restaurant, "JW Gold Label");
        cart.addItemToCart(restaurant, "Jack Daniels Bourbon Whisky");
        cart.addItemToCart(restaurant, "Teremana Tequila REPOSAD");

        int total = restaurant.findItemByName("JW Gold Label").getPrice() +
                restaurant.findItemByName("Jack Daniels Bourbon Whisky").getPrice() +
                restaurant.findItemByName("Teremana Tequila REPOSAD").getPrice();

        assertEquals(total, cart.getTotalCartValue());
    }

    @Test
    public void check_If_addItemToCart_Method_Adds_Items_To_Cart() {

        getRestaurantWithMenu();
        cart = new Cart();

        //Verifying Initial size of cart
        assertEquals(0, cart.getCart().size());

        //Adding items to cart
        cart.addItemToCart(restaurant, "Teremana Tequila REPOSAD");
        cart.addItemToCart(restaurant, "Grey Goose Vodka STRAWBERRY & LEMONGRASS");

        assertEquals("Teremana Tequila REPOSAD", cart.getCart().get(0).getName());
        assertEquals("Grey Goose Vodka STRAWBERRY & LEMONGRASS", cart.getCart().get(1).getName());
    }

    @Test
    public void checks_if_removing_item_from_cart_decrease_its_size_by_1_and_updates_cart_total_amount() {
        getRestaurantWithMenu();
        cart = new Cart();
        cart.addItemToCart(restaurant, "Jack Daniels Bourbon Whisky");
        cart.addItemToCart(restaurant, "Beefeater London Dry Gin 750ml");
        cart.addItemToCart(restaurant, "Teremana Tequila REPOSAD");

        //Storing cart size in cartSize variable.
        int cartSize = cart.getCart().size();
        int cartTotal = cart.getTotalCartValue();

        //Removing item from cart.
        cart.removeItemFromCart(restaurant, "Teremana Tequila REPOSAD");

        assertEquals(cartSize-1, cart.getCart().size());
        assertEquals(cartTotal-7500, cart.getTotalCartValue());
    }

}