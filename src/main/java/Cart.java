import java.util.ArrayList;
import java.util.List;

public class Cart {

    public List<Item> getCart() {
        return cart;
    }

    private List<Item> cart = new ArrayList<Item>();

    public int getTotalCartValue() {
        return totalCartValue;
    }

    private int totalCartValue;

    public void addItemToCart(Restaurant restaurant, String itemName) {

        Item item = getItemFromMenu(restaurant, itemName);
        cart.add(item);
        totalCartValue += item.getPrice();
    }

    public void removeItemFromCart(Restaurant restaurant, String itemName) {

        Item item = getItemFromMenu(restaurant, itemName);
        cart.remove(item);
        totalCartValue -= item.getPrice();
    }


    private Item getItemFromMenu(Restaurant restaurant, String itemName) {
        List<Item> menu = restaurant.getMenu();
        for(Item item: menu) {
            if(item.getName() == itemName) {
                return item;
            }
        }
        return null;
    }

}
