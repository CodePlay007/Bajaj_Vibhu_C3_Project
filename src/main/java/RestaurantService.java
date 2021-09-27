import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.xml.soap.SOAPPart;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        Restaurant searchRestaurant = null;

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                searchRestaurant = restaurant;
            }
        }

        if(searchRestaurant == null)
            throw new restaurantNotFoundException(restaurantName);
        else
            return searchRestaurant;

    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
