import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    private void createRestaurant(String restaurantName ,String location ,String openingTime, String closingTime) {
        restaurant = new Restaurant(restaurantName, location, LocalTime.parse(openingTime), LocalTime.parse(closingTime));
    }

    private void addItemToMenu(String itemName, int price) {
        restaurant.addToMenu(itemName, price);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        createRestaurant("Amelie's cafe", "Chennai", "10:00:00", "22:30:00");
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime time = LocalTime.parse("11:30:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(time);

        assertTrue(spyRestaurant.isRestaurantOpen());

    }


    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        createRestaurant("Amelie's cafe", "Chennai", "10:00:00", "22:30:00");
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime mockTime = LocalTime.parse("22:50:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(mockTime);

        assertFalse(spyRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        createRestaurant("Amelie's cafe", "Chennai", "10:00:00", "22:30:00");
        addItemToMenu("Sweet corn soup", 119);
        addItemToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        createRestaurant("Amelie's cafe", "Chennai", "10:00:00", "22:30:00");
        addItemToMenu("Sweet corn soup", 119);
        addItemToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        createRestaurant("Amelie's cafe", "Chennai", "10:00:00", "22:30:00");
        addItemToMenu("Sweet corn soup", 119);
        addItemToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}