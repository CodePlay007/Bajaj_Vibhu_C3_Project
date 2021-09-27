public class    restaurantNotFoundException extends Throwable {
    public restaurantNotFoundException(String restaurantName) {
        super(restaurantName);
    }
}
/*
public class    restaurantNotFoundException extends RuntimeException {
    public restaurantNotFoundException(String msg) {
        super(msg);
    }
}*/
